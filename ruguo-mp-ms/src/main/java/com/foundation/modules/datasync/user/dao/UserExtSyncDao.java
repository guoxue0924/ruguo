package com.foundation.modules.datasync.user.dao;

import java.util.List;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.datasync.user.entity.UserExtSync;

/**
 * Created on 2018年01月17日
 * Description 用户扩展表同步 Dao
 * @author liuhuan
 */
@MyBatisDao
public interface UserExtSyncDao {
	
	/**
	 * Created on 2018年01月17日
	 * Description 扩展表创建用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void insertUser(List<UserExtSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 扩展表修改用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void updateUser(List<UserExtSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 扩展表逻辑删除用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void logicDeleteUser(List<UserExtSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 扩展表物理删除用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void physicsDeleteUser(List<UserExtSync> list);
}