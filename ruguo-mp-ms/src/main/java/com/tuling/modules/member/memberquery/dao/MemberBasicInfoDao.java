/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResultResult;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;
import com.tuling.modules.member.memberquery.entity.MyConsultation;







/**
 * 会员个人信息DAO接口
 * @author wanggang
 * @version 2017-06-08
 */
@MyBatisDao
public interface MemberBasicInfoDao extends CrudDao<MemberBasicInfoResult> {
	
	/**
	 * 显示会员个人信息
	 * @author wanggang
	 * @date 2017-06-08
	 */
	public List<MemberBasicInfoResult> queryList(MemberBasicInfoFilter memberBasicInfoFilter);
		
	/**
	 * 通过会员等级编码查询会员等级名称
	 * @author wanggang
	 * @date 2017-06-5
	 */
	public String queryByCode(MemberBasicInfo memberBasicInfo);
	/**
	 * 查询会员消费金额信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResult(MemberRelationshipInfo memberRelationshipInfo);
	
	/**
	 * 根据code查询会员信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public MemberBasicInfoResult queryMemberBasicInfoResultByCode(MemberRelationshipInfo memberRelationshipInfo);

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取会员数量}
	 *
	 * @author guoxue 
	 * @return int
	 */
	public int getMemberCount();
	
	/**
	 * 查询会员个人信息中的会员等级编码
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public List<MemberBasicInfo> queryByMemberLevelCode(MemberBasicInfo memberBasicInfo);

	/**
	 * 
	 * Created on 2017年9月21日 .
	 * <p>
	 * Description {获取收藏文章信息}
	 *
	 * @author guoxue 
	 * @param memberBasicInfo
	 * @return List<MyConsultation>
	 */
	public List<MyConsultation> getmyConsultation(MemberBasicInfo memberBasicInfo);
	/**
	 * 查询会员个人信息用于导出功能
	 * @author zhuming
	 * @date 2017-09-22
	 */
	public List<MemberBasicInfoResultResult> queryExportList(MemberBasicInfoFilter memberBasicInfoFilter);
	/**
	 * 根据会员编码找对应的会员等级编码
	 * @param memberBasicInfoFilter
	 * @return
	 */
	public MemberBasicInfoResult getMemberLevelCode(String memberCode);

	
}