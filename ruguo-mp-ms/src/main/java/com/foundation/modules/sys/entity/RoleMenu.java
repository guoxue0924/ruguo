/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * 角色菜单Entity
 * @author guoxue 2018-01-19
 *
 */
public class RoleMenu extends DataEntity<RoleMenu> {

	private static final long serialVersionUID = 1L;
//
	private String roleId;
	
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	

	
	
}