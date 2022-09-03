package com.foundation.modules.roledo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.foundation.modules.roledo.dao.RoleDoDao;
import com.foundation.modules.roledo.entity.RoleInfoEntity;
import com.tuling.modules.demo.dao.DemoFileDao;
import com.tuling.modules.demo.entity.DemoFile;

/**
 * 权限查询接口
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleDoService extends CrudService<DemoFileDao, DemoFile> {

	@Autowired
	private RoleDoDao roleDoDao;

	/**
	 * 查询符合条件的角色列表
	 * @param roleInfo
	 * @return
	 */
	public List<RoleInfoEntity> queryRoleList(RoleInfoEntity roleInfo){
		return roleDoDao.queryRoleList(roleInfo);
	}
	
	/**
	 * 查询角色账号对应关系
	 * @param roleInfo
	 * @return
	 */
	public RoleInfoEntity queryUserRole(RoleInfoEntity roleInfo){
		if(roleInfo.getUserId() != null && !"".equals(roleInfo.getUserId())){
			return roleDoDao.queryUserRole(roleInfo);
		} else {
			return new RoleInfoEntity();
		}
	}
	
}