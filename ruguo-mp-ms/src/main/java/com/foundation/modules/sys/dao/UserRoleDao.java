/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;


import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.RoleMenu;
import com.foundation.modules.sys.entity.UserRole;

/**
 * 角色用户DAO接口
 * @author guoxue
 * @version 2018-01-19
 */
@MyBatisDao
public interface UserRoleDao extends CrudDao<UserRole> {

	
	
}
