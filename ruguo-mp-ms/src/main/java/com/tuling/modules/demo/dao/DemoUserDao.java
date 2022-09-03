/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.demo.entity.DemoUser;
import com.tuling.modules.demo.entity.DemoUserFilter;

/**
 * demo用户DAO接口
 * @author hl
 * @version 2017-04-08
 */
@MyBatisDao
public interface DemoUserDao extends CrudDao<DemoUser> {
	
	/**
	 * 分页查询用户信息
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-14
	 */
	public List<DemoUser> findUserList(DemoUserFilter userFilter);
}