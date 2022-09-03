package com.tuling.modules.demo.web.comp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.FileInfo;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UploadHelper;
import com.tuling.modules.demo.entity.DemoFile;
import com.tuling.modules.demo.entity.DemoUser;
import com.tuling.modules.demo.service.DemoFileService;
import com.tuling.modules.demo.service.DemoUserService;

/**
 * DemoComponentController
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-20
 */
@Controller
@RequestMapping(value = "demo")
public class DemoComponentController extends BaseController {

	@Autowired
	private DemoUserService demoUserService;
	@Autowired
	private DemoFileService demoFileService;
	
	@Autowired
	private UploadHelper uploadHelper;

	@RequiresPermissions("user")
	@RequestMapping(value = "upload/pictureuploadindex")
	public String pictureUploadIndex(Model model) {

		return "layout1.demo.upload.pictureuploadIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "upload/fileuploadindex")
	public String fileUploadIndex(Model model) {

		return "layout1.demo.upload.fileuploadIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "editor/index")
	public String editorIndex(Model model) {

		return "layout1.demo.editor.editorIndex";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "typeahead/index")
	public String typeaheadIndex(Model model) {
		
		return "layout1.demo.typeahead.typeaheadIndex";
	}
	
	/**
	 * 保存附件信息
	 * @author hl huanglin@bjtuling.com
	 * @throws IOException 
	 * @date 2017-04-24
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "upload/fileupload")
	public void fileUpload(DemoUser demoUser, @RequestParam("files") MultipartFile[] files, HttpServletResponse response) throws UploadException, IOException {

		List<FileInfo> fileList = uploadHelper.saveFiles(files);

		if (!fileList.isEmpty()) {
			
			demoUserService.save(demoUser);
			
			demoFileService.save(fileList,demoUser);
			
			responseHelper.setSuccess("保存成功");// 返回成功状态 和 消息
			
		}else{
			responseHelper.setFail("保存失败");// 返回成功状态 和 消息
		}

		//IE8上传附件特殊处理，否则会提示下载信息。
		responseHelper.writeResponseToText(response, responseHelper);
	}
	
	/**
	 * 分页查询附件列表
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-24
	 */
	@RequestMapping(value = "upload/fileupload/list")
	@ResponseBody
	public PageHelper<DemoFile> filelist(@RequestBody String param) {

		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		DemoFile userFilter = obj.toJavaObject(DemoFile.class);
		// 分页step3
		PageHelper<DemoFile> page = demoFileService.findList(new Page(obj), userFilter);

		return page;

	}
	/**
	 * 删除附件信息
	 * @author hl huanglin@bjtuling.com
	 * @throws UploadException 
	 * @date 2017-04-24
	 */
	@RequestMapping(value = "upload/fileupload/delete")
	@ResponseBody
	public ResponseHelper delfile(@RequestParam("id") String id) throws UploadException {
		
		if(StringUtils.isNotBlank(id)){
			
			DemoFile demoFile = new DemoFile(id);
			demoFile = demoFileService.get(demoFile);
			boolean bool = demoFileService.deleteFile(demoFile);
			if(bool){
				responseHelper.setSuccess("删除成功");// 返回成功状态 和 消息
			}else{
				responseHelper.setFail("删除失败");// 返回成功状态 和 消息
			}
		}
		
		return responseHelper;
		
	}
}
