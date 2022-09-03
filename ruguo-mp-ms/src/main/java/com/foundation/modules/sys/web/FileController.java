package com.foundation.modules.sys.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.service.FileInMongoService;
import com.foundation.modules.sys.utils.UploadHelper;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping(value = "common/file")
public class FileController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	FileInMongoService fileInMongoService;

	@Autowired
	private UploadHelper uploadHelper;

	@RequiresPermissions("user")
	@RequestMapping("/upload")
	public void upLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("json");
		response.setCharacterEncoding("UTF-8");

		try {

			// 转型为MultipartHttpRequest
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得上传的文件（根据前台的name名称得到上传的文件）
			MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();

			List<MultipartFile> files = multiValueMap.get("uploadFileInput");
			// 文件存在
			if (files.size() > 0) {

				CommonsMultipartFile file = (CommonsMultipartFile) files.get(0);

				DiskFileItem fi = (DiskFileItem) file.getFileItem();
				// 获取文件名
				String fileName = fi.getName();
				// 获取文件类型
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				// 获取文件id
				String fileId = fileInMongoService.storeFile(fileType, file);

				response.setStatus(200);
				JSONObject resultObj = new JSONObject();// 返回结果
				resultObj.put("imgName", fileName); // 图片名称
				resultObj.put("fileId", fileId); // 图片id
				resultObj.put("fileType", fileType); // 文件类型
				response.getWriter().print(resultObj);
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	/**
	 * 删除附件（不建议使用此方法删除附件，请先删除业务关联再删除服务器附件内容。请参考：DemoComponentController.delfile()）
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-05-16
	 */
	@Deprecated
	@RequiresPermissions("user")
	@RequestMapping("delete")
	public void deleteFile(@RequestParam("fileId") String fileId) throws UploadException {
		try {
			// 从服务器删除附件信息
			if (StringUtils.isNotBlank(fileId)) {
				uploadHelper.deleteFile(fileId);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new UploadException("删除图片失败！", e);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-24
	 */
	@RequiresPermissions("user")
	@RequestMapping("down")
	public void downFile(@RequestParam("fileId") String fileId, @RequestParam("fileName") String fileName,
			HttpServletResponse response) throws UploadException {
		InputStream in = null;
		OutputStream out = null;
		try {
			GridFSDBFile file = uploadHelper.getFile(fileId);
			response.setContentType(file.getContentType());
			response.setContentLength((int) file.getLength());

			// 处理文件名
			// String str = request.getParameter("filename");
			// String str = file.getMetaData().get("fileName").toString();
			// String filename = "test";
			if (StringUtils.isBlank(fileName)) {
				fileName = file.getMetaData().get("fileName").toString();
			}
			// if (str != null && !"".equals(str)) {
			// filename = new String(str.getBytes("iso8859-1"), "UTF-8");
			// }
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			// 读取要下载的文件，保存到文件输入流
			in = file.getInputStream();
			// 创建输出流
			out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error("FileController.downFile download file is error.", e);
			throw new UploadException("文件下载失败！");
		} finally {
			// 关闭文件输入流
			if (in != null) {
				try {
					in.close();
				} catch (IOException io) {
					logger.error("FileController.downFile close inputStream is error.", io);
				}
			}
			// 关闭输出流
			if (out != null) {
				try {
					out.close();
				} catch (IOException io) {
					logger.error("FileController.downFile close outputStream is error.", io);
				}
			}
		}

	}

	/**
	 * 预览图片
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-24
	 */
	@RequiresPermissions("user")
	@RequestMapping("show")
	public void showFile(@RequestParam("fileId") String fileId, HttpServletResponse response) throws UploadException {
		try {

			GridFSDBFile file = uploadHelper.getFile(fileId);
			response.setContentType(file.getContentType());
			response.setContentLength((int) file.getLength());

			response.setCharacterEncoding("UTF-8");
			file.writeTo(response.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();

			throw new UploadException("图片预览失败！", e);
		}

	}
}
