/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.Role;

/**
 * 角色DAO接口
 * 
 * @author zou
 * @version 2013-12-05
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	public Role getByName(Role role);

	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	/**
	 * 查询所属角色下的账号数量 by liuhuan at 20170609
	 * 
	 * @param roleId
	 * @return
	 */
	public int findRoleUserCount(String roleId);

}
