/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.entity;

import org.hibernate.validator.constraints.Length;

import com.foundation.common.persistence.DataEntity;

/**
 * 用户上传资料Entity
 * @author hl
 * @version 2017-04-23
 */
public class DemoFile extends DataEntity<DemoFile> {

	private static final long serialVersionUID = 1L;
	private String type;		// 文件类型
	private DemoUser demoUser;		// 用户ID
	private String fileName;		// 文件名称
	private String fileId;		// 文件ID
	
	public DemoUser getDemoUser() {
		return demoUser;
	}

	public void setDemoUser(DemoUser demoUser) {
		this.demoUser = demoUser;
	}

	public DemoFile() {
		super();
	}

	public DemoFile(String id){
		super(id);
	}

	@Length(min=1, max=10, message="文件类型长度必须介于 1 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@Length(min=1, max=100, message="文件名称长度必须介于 1 和 100 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=1, max=50, message="文件ID长度必须介于 1 和 50 之间")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}