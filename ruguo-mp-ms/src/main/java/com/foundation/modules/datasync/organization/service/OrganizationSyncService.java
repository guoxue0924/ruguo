package com.foundation.modules.datasync.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.modules.datasync.organization.dao.OrganizationSyncDao;
import com.foundation.modules.datasync.organization.entity.OrganizationInfo;
import com.foundation.modules.datasync.user.dao.UserMainSyncDao;
import com.foundation.modules.datasync.user.entity.UserMainSync;
import com.google.common.collect.Lists;


@Service
public class OrganizationSyncService{
	
	@Autowired
	private OrganizationSyncDao organizationDao;
	
	@Autowired
	private UserMainSyncDao userMainSyncDao;
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(新增)
	 */
	@Transactional("transactionManagerAdmin")
	public void addOrganization(List<OrganizationInfo> organizationList){
		organizationDao.addOrganization(organizationList);
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(修改)
	 */
	@Transactional("transactionManagerAdmin")
	public void modifyOrganization(List<OrganizationInfo> organizationList){
		organizationDao.modifyOrganization(organizationList);
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(逻辑删除)
	 */
	@Transactional("transactionManagerAdmin")
	public void deleteOrganization(List<OrganizationInfo> organizationList){
		organizationDao.deleteOrganization(organizationList);
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-17
	 * desc:机构同步(物理删除)
	 */
	@Transactional("transactionManagerAdmin")
	public void deleteOrganizationByPhysical(List<OrganizationInfo> organizationList){
		organizationDao.deleteOrganizationByPhysical(organizationList);
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-18
	 * desc:机构同步(机构启用)
	 */
	@Transactional("transactionManagerAdmin")
	public void enableOrganization(List<OrganizationInfo> organizationList){
		
		// 调用机构启用方法
		organizationDao.modifyOrganization(organizationList);
		
		// 构造userList参数
		List<UserMainSync> userList = Lists.newArrayList();
		
		// 循环机构list
		for(OrganizationInfo o : organizationList){
			
			// 构造user对象
			UserMainSync u = new UserMainSync();
			
			// user对象赋值(orgId)
			u.setOrgId(o.getId());
			
			// loginFlag
			u.setLoginFlag("1");
			
			// updateBy
			u.setUpdateBy(o.getUpdateBy());
			
			// updateTime
			u.setUpdateTime(o.getUpdateTime());
			
			// list添加数据
			userList.add(u);
		}
		
		// 调用机构下用户启用方法
		userMainSyncDao.updateLoginFlag(userList);
	}
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-18
	 * desc:机构同步(机构停用)
	 */
	@Transactional("transactionManagerAdmin")
	public void disableOrganization(List<OrganizationInfo> organizationList){
		
		// 调用机构停用方法
		organizationDao.modifyOrganization(organizationList);
		
		// 构造userList参数
		List<UserMainSync> userList = Lists.newArrayList();
		
		// 循环机构list
		for(OrganizationInfo o : organizationList){
			
			// 构造user对象
			UserMainSync u = new UserMainSync();
			
			// user对象赋值(orgId)
			u.setOrgId(o.getId());
			
			// loginFlag
			u.setLoginFlag("2");
			
			// updateBy
			u.setUpdateBy(o.getUpdateBy());
			
			// updateTime
			u.setUpdateTime(o.getUpdateTime());
			
			// list添加数据
			userList.add(u);
		}
		
		// 调用机构下用户启用方法
		userMainSyncDao.updateLoginFlag(userList);
		
	}
}
