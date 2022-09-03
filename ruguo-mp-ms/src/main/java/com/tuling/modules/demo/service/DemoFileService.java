/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.modules.sys.entity.FileInfo;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.UploadHelper;
import com.tuling.modules.demo.dao.DemoFileDao;
import com.tuling.modules.demo.entity.DemoFile;
import com.tuling.modules.demo.entity.DemoUser;

/**
 * 用户上传资料Service
 * @author hl
 * @version 2017-04-23
 */
@Service
@Transactional(readOnly = true)
public class DemoFileService extends CrudService<DemoFileDao, DemoFile> {

	@Autowired
	private DemoFileDao demoFileDao;
	
	@Autowired
	private PageHelper<DemoFile> pageHelper;
	
	@Autowired
	private UploadHelper uploadHelper;
	
//	public DemoFile get(String id) {
//		return super.get(id);
//	}
	
	
//	public Page<DemoFile> findPage(Page<DemoFile> page, DemoFile demoFile) {
//		return super.findPage(page, demoFile);
//	}
	
	@Transactional(readOnly = false)
	public void save(DemoFile demoFile) {
		super.save(demoFile);
	}
	
	@Transactional(readOnly = false,rollbackFor=UploadException.class)
	public boolean deleteFile(DemoFile demoFile) throws UploadException{
		
		//从服务器删除附件信息
		boolean bool = uploadHelper.deleteFile(demoFile.getFileId());
		if(bool){
			//从业务表删除附件信息
			int count = demoFileDao.delete(demoFile);
			if(count==0){
				bool = false;
			}
		}
		return bool;

	}
	
//	@Transactional(readOnly = false)
	public void save(List<FileInfo> fileList,DemoUser demoUser){
//		List<DemoFile> demoFileList = new ArrayList<DemoFile>();
		
		for (FileInfo fileInfo : fileList) {
			DemoFile demoFile = new DemoFile();
			demoFile.setFileId(fileInfo.getId());
			demoFile.setType(fileInfo.getType());
			demoFile.setFileName(fileInfo.getName());
			demoFile.setDemoUser(demoUser);
//			demoFile.preInsert();
//			demoFileList.add(demoFile);
			super.save(demoFile);
		}
//		demoFileDao.insertByBatch(demoFileList);
		
	}
	
	/**
	 * 分页查询信息
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-23
	 */
	public PageHelper<DemoFile> findList(Page page, DemoFile demoFile) {

		// 分页查询，需要在filter实体中set分页信息
		demoFile.setPage(page);
		// 查询数据
		List<DemoFile> list = demoFileDao.findList(demoFile);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;

	}
	
}