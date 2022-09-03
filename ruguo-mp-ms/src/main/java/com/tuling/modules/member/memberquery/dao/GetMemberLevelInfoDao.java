/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.dao;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.member.memberquery.entity.MemberLevelInfoResult;



/**
 * 会员个人信息DAO接口
 * @author wanggang
 * @version 2017-06-08
 */
@MyBatisDao
public interface GetMemberLevelInfoDao extends CrudDao<MemberLevelInfoResult> {
	
    /**
	 * 根据会员编码找对应的会员等级编码
	 * @param memberBasicInfoFilter
	 * @return
	 */
	public MemberLevelInfoResult getMemberLevelName(String memberLevelCode);
	
	
}