package com.foundation.modules.sys.entity;

/**
 * 上传附件实体类
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-24
 */
public class FileInfo {

	private String id;
	
	private String name;
	
	private String path;
	
	private String type;
	
	private String size;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
