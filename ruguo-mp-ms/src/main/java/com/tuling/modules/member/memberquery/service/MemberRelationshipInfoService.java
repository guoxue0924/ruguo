/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.member.memberquery.dao.GetMemberLevelInfoDao;
import com.tuling.modules.member.memberquery.dao.MemberBasicInfoDao;
import com.tuling.modules.member.memberquery.dao.MemberRelationshipInfoDao;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberLevelInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;




/**
 * 会员关系Service
 * 
 * @author wanggang
 * @version 2017-06-10
 */
@Service
@Transactional(readOnly = true)
public class MemberRelationshipInfoService extends CrudService<MemberRelationshipInfoDao, MemberRelationshipInfo> {

	@Autowired
	private MemberRelationshipInfoDao memberRelationshipInfoDao;
	
	@Autowired
	private MemberBasicInfoDao memberBasicInfoDao;

	@Autowired
	private PageHelper<MemberRelationshipInfo> pageHelperMemberRelationshipInfo;
	
	@Autowired
	private GetMemberLevelInfoDao memberLevelInfoDao;
	
	/**
	 * 查詢会员关系信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public List<MemberRelationshipInfo> queryMemberRelationshipInfo(MemberRelationshipInfo memberRelationshipInfo){
		List<MemberRelationshipInfo> memberRelationship= memberRelationshipInfoDao.queryMemberRelationshipInfo(memberRelationshipInfo);
		return memberRelationship;
	}
	
	/**
	 * 
	 * Created on 2018年1月26日 .
	 * <p>
	 * Description {获取关系人信息}
	 *
	 * @author liubing
	 * @param page
	 * @param memberRelationshipInfo
	 * @return PageHelper<MemberRelationshipInfo>
	 */
	public PageHelper<MemberRelationshipInfo> getMemberRelationshipInfo(Page page, MemberRelationshipInfo memberRelationshipInfo) {
		// 分页查询，需要在filter实体中set分页信息
		memberRelationshipInfo.setPage(page);
				// 查询数据
		List<MemberRelationshipInfo> list = memberRelationshipInfoDao.queryMemberRelationshipInfo(memberRelationshipInfo);
				// 装载数据
		pageHelperMemberRelationshipInfo.setRows(page, list);

		return pageHelperMemberRelationshipInfo;
	}
	/**
	 * 显示会员个人信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public PageHelper<MemberRelationshipInfo> findList(Page page, MemberRelationshipInfo memberRelationshipInfo) {

		// 分页查询，需要在filter实体中set分页信息
		memberRelationshipInfo.setPage(page);
		// 查询数据
		List<MemberRelationshipInfo> list = memberRelationshipInfoDao.findList(memberRelationshipInfo);
		if(list.size()>0){
			for(int i=0; i<list.size();i++){
				String memberCode = list.get(i).getMemberCode();
				MemberBasicInfoResult memberBasicInfoResult=new MemberBasicInfoResult();
				if(memberCode!="" && memberCode!=null){
					memberBasicInfoResult=memberBasicInfoDao.getMemberLevelCode(memberCode);
				}else{
					continue;
				}
				if(memberBasicInfoResult==null){
					continue;
				}else{
					MemberLevelInfoResult memberLevelInfo=new MemberLevelInfoResult();
					String memberLevelCode = memberBasicInfoResult.getMemberBasicInfo().getMemberLevelCode();
				
					if(memberLevelCode!="" && memberLevelCode!=null){
						memberLevelInfo=memberLevelInfoDao.getMemberLevelName(memberLevelCode);
					}else{
						continue;
					}
				}
				
				list.get(i).setMemberLevelName("1级");
//				list.get(i).setMemberLevelName(memberLevelInfo.getMemberLevelName());
			}
		}
		// 装载数据
		pageHelperMemberRelationshipInfo.setRows(page, list);

		return pageHelperMemberRelationshipInfo;
	}

	
	
}