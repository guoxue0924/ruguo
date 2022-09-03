package com.foundation.modules.datasync.user.dao;

import java.util.List;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.datasync.user.entity.UserRoleSync;

/**
 * Created on 2018年01月17日
 * Description 用户角色关系同步 Dao
 * @author liuhuan
 */
@MyBatisDao
public interface UserRoleSyncDao {
	
	/**
	 * Created on 2018年01月17日
	 * Description 创建用户角色关系 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void insertUserRole(List<UserRoleSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 物理删除用户角色关系 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void physicsDeleteUserRole(List<UserRoleSync> list);
}