/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;





/**
 * 会员关系DAO接口
 * @author wanggang
 * @version 2017-06-03
 */
@MyBatisDao
public interface MemberRelationshipInfoDao extends CrudDao<MemberRelationshipInfo> {
	
	/**
	 * 显示会员等级信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public List<MemberRelationshipInfo> queryMemberRelationshipInfo(MemberRelationshipInfo memberRelationshipInfo);
	/**
	 * 显示会员信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public List<MemberRelationshipInfo> findList(MemberRelationshipInfo memberRelationshipInfo);
}