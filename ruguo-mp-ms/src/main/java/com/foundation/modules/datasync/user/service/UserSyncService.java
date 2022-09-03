package com.foundation.modules.datasync.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.foundation.modules.datasync.user.dao.UserExtSyncDao;
import com.foundation.modules.datasync.user.dao.UserMainSyncDao;
import com.foundation.modules.datasync.user.dao.UserRoleSyncDao;
import com.foundation.modules.datasync.user.entity.UserExtSync;
import com.foundation.modules.datasync.user.entity.UserMainSync;
import com.foundation.modules.datasync.user.entity.UserRoleSync;

/**
 * Created on 2018年01月17日
 * Description 用户同步 Service
 * @author liuhuan
 */
@Service
public class UserSyncService {
	
	@Autowired
	private UserMainSyncDao userMainSyncDao;
	
	@Autowired
	private UserExtSyncDao userExtSyncDao;
	
	@Autowired
	private UserRoleSyncDao userRoleSyncDao;
	
	/**
	 * Created on 2018年01月17日
	 * Description 创建用户 Service
	 * @author liuhuan
	 * @param list
	 */
	@Transactional("transactionManagerBiz")
	public void addUser(List<UserMainSync> userMainSyncList, List<UserExtSync> userExtSyncList, List<UserRoleSync> userRoleSyncDelList, List<UserRoleSync> userRoleSyncAddList) {
		// 批量添加用户主表
		userMainSyncDao.insertUser(userMainSyncList);
		// 批量添加用户扩展表
		userExtSyncDao.insertUser(userExtSyncList);
		// 批量添加用户角色关系表 ： 先删除后添加
		userRoleSyncDao.physicsDeleteUserRole(userRoleSyncDelList);
		// 角色集合不为空 ： 添加角色
		if (!CollectionUtils.isEmpty(userRoleSyncAddList)) {
			userRoleSyncDao.insertUserRole(userRoleSyncAddList);
		}
	}
	
	/**
	 * Created on 2018年01月17日
	 * Description 修改用户 Service
	 * @author liuhuan
	 * @param list
	 */
	@Transactional("transactionManagerBiz")
	public void modifyUser(List<UserMainSync> userMainSyncList, List<UserExtSync> userExtSyncList, List<UserRoleSync> userRoleSyncDelList, List<UserRoleSync> userRoleSyncAddList) {
		// 批量修改用户主表
		userMainSyncDao.updateUser(userMainSyncList);
		// 批量修改用户扩展表
		userExtSyncDao.updateUser(userExtSyncList);
		// 批量修改用户角色关系表 ： 先删除后添加
		userRoleSyncDao.physicsDeleteUserRole(userRoleSyncDelList);
		// 角色集合不为空 ： 添加角色
		if (!CollectionUtils.isEmpty(userRoleSyncAddList)) {
			userRoleSyncDao.insertUserRole(userRoleSyncAddList);
		}
	}
	
	/**
	 * Created on 2018年01月17日
	 * Description 逻辑删除用户 Service
	 * @author liuhuan
	 * @param list
	 */
	@Transactional("transactionManagerBiz")
	public void logicDeleteUser(List<UserMainSync> userMainSyncList, List<UserExtSync> userExtSyncList) {
		// 批量逻辑删除用户主表
		userMainSyncDao.logicDeleteUser(userMainSyncList);
		// 批量逻辑删除用户扩展表
		userExtSyncDao.logicDeleteUser(userExtSyncList);
	}
	
	/**
	 * Created on 2018年01月17日
	 * Description 物理删除用户 Service
	 * @author liuhuan
	 * @param list
	 */
	@Transactional("transactionManagerBiz")
	public void physicsDeleteUser(List<UserMainSync> userMainSyncList, List<UserExtSync> userExtSyncList, List<UserRoleSync> userRoleSyncList) {
		// 批量逻辑删除用户主表
		userMainSyncDao.physicsDeleteUser(userMainSyncList);
		// 批量逻辑删除用户扩展表
		userExtSyncDao.physicsDeleteUser(userExtSyncList);
		// 批量删除用户角色关系表
		userRoleSyncDao.physicsDeleteUserRole(userRoleSyncList);
	}
}
