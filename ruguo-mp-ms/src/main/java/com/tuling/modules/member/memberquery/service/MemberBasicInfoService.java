/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.service;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.dao.OrganizationDao;
import com.foundation.modules.sys.entity.Organization;
import com.tuling.modules.member.memberlevel.dao.MemberLevelInfoDao;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberquery.dao.GetMemberLevelInfoDao;
import com.tuling.modules.member.memberquery.dao.MemberBasicInfoDao;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResultResult;
import com.tuling.modules.member.memberquery.entity.MemberLevelInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;
import com.tuling.modules.member.memberquery.entity.MyConsultation;





/**
 * 会员个人信息Service
 * 
 * @author wanggang
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class MemberBasicInfoService extends CrudService<MemberBasicInfoDao, MemberBasicInfoResult> {

	@Autowired
	private MemberBasicInfoDao memberBasicInfoDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private MemberLevelInfoDao memberLevelInfoDao;
	
	@Autowired
	private GetMemberLevelInfoDao GetmemberLevelInfoDao;

	/**
	 * 显示会员个人信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public List<MemberBasicInfoResult> findList(MemberBasicInfoFilter memberBasicInfoFilter) {


		// 查询数据
		List<MemberBasicInfoResult> list = memberBasicInfoDao.queryList(memberBasicInfoFilter);


		return list;
	}
	/**
	 * 查詢会员消费金额信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResult(MemberRelationshipInfo memberRelationshipInfo){
		MemberBasicInfoResult memberBasicInfo = memberBasicInfoDao.queryMemberBasicInfoResult(memberRelationshipInfo);
		return memberBasicInfo;
	}
	
	/**
	 * 根据code查詢会员信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResultByCode(MemberRelationshipInfo memberRelationshipInfo){
		
		MemberBasicInfoResult memberBasicInfo = memberBasicInfoDao.queryMemberBasicInfoResultByCode(memberRelationshipInfo);
		MemberBasicInfoResult memberBasicInfoWithMoney = queryMemberBasicInfoResult(memberRelationshipInfo);
		if (null == memberBasicInfoWithMoney) {
			 memberBasicInfo.setMonetary(new BigDecimal("0").toString());
		} else {
			DecimalFormat df = new DecimalFormat(",###,###.00");			
			memberBasicInfo.setMonetary(df.format(Double.parseDouble(memberBasicInfoWithMoney.getMonetary())));
		}
		
		String memberCode = memberRelationshipInfo.getMemberCode();
		MemberBasicInfoResult memberBasicInfoResult=new MemberBasicInfoResult();
		if(memberCode!="" && memberCode!=null){
			memberBasicInfoResult=memberBasicInfoDao.getMemberLevelCode(memberCode);
		}
		if(memberBasicInfoResult!=null){
			MemberLevelInfoResult memberLevelInfo=new MemberLevelInfoResult();
			String memberLevelCode = memberBasicInfoResult.getMemberBasicInfo().getMemberLevelCode();
		
			if(memberLevelCode!="" && memberLevelCode!=null){
				memberLevelInfo=GetmemberLevelInfoDao.getMemberLevelName(memberLevelCode);
			}
			
			memberBasicInfo.setMemberLevelName(memberLevelInfo.getMemberLevelName());
		}
		
		
		return memberBasicInfo;
	}
	
	
	
	/**
	 * 显示当前用户管理-服务机构和管理机构的信息
	 * @author wanggang
	 * @date 2017-06-28
	 */
	public List<Organization> queryOrganizationPullDownList(Organization organization) {
		// 查询数据
		List<Organization> list = organizationDao.getOfficeListByName(organization);
		return list;
	}
	
	/**
	 * 查询会员等级名称
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public List<MemberLevelInfo> findList(MemberLevelInfo memberLevelInfo) {
		List<MemberLevelInfo> memberLevelInfoPullList = memberLevelInfoDao.findList(memberLevelInfo);
		return memberLevelInfoPullList;
	}
	
	
	/**
	 * 
	 * Created on 2017年9月21日 .
	 * <p>
	 * Description {获取收藏文章信息}
	 *
	 * @author guoxue 
	 * @param memberBasicInfo
	 * @return List<MemberAdressFilter>
	 */
	public List<MyConsultation> getmyConsultation(MemberBasicInfo memberBasicInfo) {
		List<MyConsultation> list = memberBasicInfoDao.getmyConsultation(memberBasicInfo);
		return list;
	}
	/**
	 * 查询会员个人信息用于导出功能
	 * @author zhuming
	 * @date 2017-09-22
	 */
	public List<MemberBasicInfoResultResult> queryExportList(MemberBasicInfoFilter memberBasicInfoFilter){
		return memberBasicInfoDao.queryExportList(memberBasicInfoFilter);
	}
	
}