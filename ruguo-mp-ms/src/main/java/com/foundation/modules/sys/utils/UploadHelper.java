package com.foundation.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.foundation.common.utils.upload.UploadException;
import com.foundation.modules.sys.entity.FileInfo;
import com.foundation.modules.sys.service.FileInMongoService;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * 上传附件工具类
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-23
 */
@Component
public class UploadHelper {

	private static Logger logger = LoggerFactory.getLogger(UploadHelper.class);
	@Autowired
	FileInMongoService fileInMongoService;

	/**
	 * 保存文件
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-23
	 */
	public List<FileInfo> saveFiles(MultipartFile[] files) throws UploadException {

		List<FileInfo> list = new ArrayList<FileInfo>();
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件

			for (MultipartFile file : files) {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				// 获取文件类型
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

				String fileSize = String.valueOf(file.getSize());

				if (Integer.valueOf(fileSize) == 0) {
					continue;
				}
				// 获取文件id
				String fileId = fileInMongoService.storeFile(fileType, file);
				FileInfo fileInfo = new FileInfo();
				fileInfo.setId(fileId);
				fileInfo.setName(fileName);
				fileInfo.setType(fileType);
				fileInfo.setSize(fileSize);
				list.add(fileInfo);

				logger.info("上传附件 id:{} name:{} size:{}", fileId, fileName, fileSize);

			}

		}
		return list;
	}

	/**
	 * 删除附件
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-23
	 */
	public boolean deleteFile(String fileId) throws UploadException {

		try {
			fileInMongoService.delete(fileId);
			logger.info("删除附件id:{}", fileId);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * 获取附件
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @throws Exception
	 * @date 2017-04-23
	 */
	public GridFSDBFile getFile(String fileId) throws UploadException {

		GridFSDBFile file = fileInMongoService.getFile(fileId);

		return file;
	}
}
