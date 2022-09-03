/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * 角色用户Entity
 * @author guoxue 2018-01-19
 *
 */
public class UserRole extends DataEntity<UserRole> {

	private static final long serialVersionUID = 1L;
//
	private String roleId;
	
	private String userId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	

	
	
}