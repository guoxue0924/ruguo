/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberlevel.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.tuling.modules.member.memberlevel.dao.MemberLevelInfoDao;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberquery.dao.MemberBasicInfoDao;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;






/**
 * 会员等级Service
 * 
 * @author wanggang
 * @version 2017-06-03
 */
@Service
@Transactional(readOnly = true)
public class MemberLevelInfoService extends CrudService<MemberLevelInfoDao, MemberLevelInfo> {

	@Autowired
	private MemberLevelInfoDao memberLevelInfoDao;
	
	@Autowired
	private MemberBasicInfoDao memberBasicInfoDao;
	
	/**
	 * 显示会员等级信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public List<MemberLevelInfo> findLevelManageList(MemberLevelInfo memberLevelInfo) {
		// 查询数据
		List<MemberLevelInfo> list = memberLevelInfoDao.findLevelManageList(memberLevelInfo);			
		return list;
	}
	
	/**
	 * 显示当前会员等级以外的信息
	 * @author wanggang
	 * @date 2017-06-21
	 */
	public List<MemberLevelInfo> findPresentInfoOutsideList(MemberLevelInfo memberLevelInfo) {
		// 查询数据
		List<MemberLevelInfo> list = memberLevelInfoDao.findPresentInfoOutsideList(memberLevelInfo);
		return list;
	}
	
	/**
	 * 查询会员等级名称
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public String findMemberLevelName(MemberLevelInfo memberLevelInfo) {
		String memberLevelName = memberLevelInfoDao.findMemberLevelName(memberLevelInfo);
		return memberLevelName;
	}
	
	/**
	 * 查询会员个人信息中的会员等级编码
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public List<MemberBasicInfo> queryByMemberLevelCode(MemberBasicInfo memberBasicInfo) {
		List<MemberBasicInfo> memberLevelCode = memberBasicInfoDao.queryByMemberLevelCode(memberBasicInfo);
		return memberLevelCode;
	}
	
	/**
	 * 插入会员等级信息
	 * @author wanggang
	 * @date 2017-06-5
	 */
	@Transactional(readOnly = false)
	public void saveMemberLevelInfo(MemberLevelInfo memberLevelInfo) {
	    super.save(memberLevelInfo);
	}
	
}