/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.demo.dao.DemoUserDao;
import com.tuling.modules.demo.entity.DemoUser;
import com.tuling.modules.demo.entity.DemoUserFilter;

/**
 * demo用户Service
 * 
 * @author hl
 * @version 2017-04-08
 */
@Service
@Transactional(readOnly = true)
public class DemoUserService extends CrudService<DemoUserDao, DemoUser> {

	@Autowired
	private DemoUserDao demoUserDao;

	@Autowired
	private PageHelper<DemoUser> pageHelper;

//	public DemoUser get(String id) {
//		return super.get(id);
//	}

	/**
	 * 分页查询用户信息
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-14
	 */
	public PageHelper<DemoUser> findUserList(Page page, DemoUserFilter userFilter) {

		// 分页查询，需要在filter实体中set分页信息
		userFilter.setPage(page);
		// 查询数据
		List<DemoUser> list = demoUserDao.findUserList(userFilter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;

	}

	@Transactional(readOnly = false)
	public void save(DemoUser demoUser) {
		super.save(demoUser);
	}

	@Transactional(readOnly = false)
	public void delete(DemoUser demoUser) {
		super.delete(demoUser);
	}

}