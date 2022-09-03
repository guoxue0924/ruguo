package com.foundation.modules.roledo.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.roledo.entity.RoleInfoEntity;

/**
 * 权限查询接口
 * @author Administrator
 *
 */
@MyBatisDao
public interface RoleDoDao extends CrudDao<RoleInfoEntity> {
	
	/**
	 * 查询符合条件的角色列表
	 * @param roleInfoEntity
	 * @return
	 */
	public List<RoleInfoEntity> queryRoleList(RoleInfoEntity roleInfoEntity);
	
	/**
	 * 查询角色账号对应关系
	 * @param roleInfoEntity
	 * @return
	 */
	public RoleInfoEntity queryUserRole(RoleInfoEntity roleInfoEntity);
}