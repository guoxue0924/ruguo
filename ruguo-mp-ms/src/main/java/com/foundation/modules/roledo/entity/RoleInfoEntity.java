package com.foundation.modules.roledo.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * 权限查询接口实体
 * @author Administrator
 *
 */
public class RoleInfoEntity extends DataEntity<RoleInfoEntity> {

	private static final long serialVersionUID = 342215853840639739L;

	/**
	 * 账号ID
	 */
	private String userId;
	
	/**
	 * 角色ID
	 */
	private String roleId;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色英文名称
	 */
	private String enname;
	
	/**
	 * 角色类型
	 */
	private String roleType;
	
	/**
	 * 角色级别
	 */
	private String roleLevel;
	
	/**
	 * 数据范围
	 */
	private String dataScope;
	
	/**
	 * 是否系统数据DICT_IDENTITY_FLAG
	 */
	private String isSys;
	
	/**
	 * 是否可用DICT_IDENTITY_FLAG
	 */
	private String useable;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
}