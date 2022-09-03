/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberlevel.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;





/**
 * 会员等级DAO接口
 * @author wanggang
 * @version 2017-06-03
 */
@MyBatisDao
public interface MemberLevelInfoDao extends CrudDao<MemberLevelInfo> {
	
	/**
	 * 显示会员等级信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public List<MemberLevelInfo> findLevelManageList(MemberLevelInfo memberLevelInfo);
	
	/**
	 * 查询会员等级名称
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public String findMemberLevelName(MemberLevelInfo memberLevelInfo);	
	
	/**
	 * 显示当前会员等级以外的信息
	 * @author wanggang
	 * @date 2017-06-21
	 */
	public List<MemberLevelInfo> findPresentInfoOutsideList(MemberLevelInfo memberLevelInfo);
	
	
}