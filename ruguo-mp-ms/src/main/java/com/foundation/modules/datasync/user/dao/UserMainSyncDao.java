package com.foundation.modules.datasync.user.dao;

import java.util.List;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.datasync.user.entity.UserMainSync;

/**
 * Created on 2018年01月17日
 * Description 用户主表同步 Dao
 * @author liuhuan
 */
@MyBatisDao
public interface UserMainSyncDao {
	
	/**
	 * Created on 2018年01月17日
	 * Description 主表创建用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void insertUser(List<UserMainSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 主表修改用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void updateUser(List<UserMainSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 主表修改用户登陆状态 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void updateLoginFlag(List<UserMainSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 主表逻辑删除用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void logicDeleteUser(List<UserMainSync> list);
	
	/**
	 * Created on 2018年01月17日
	 * Description 主表物理删除用户 Dao
	 * @author liuhuan
	 * @param list
	 */
	public void physicsDeleteUser(List<UserMainSync> list);
}