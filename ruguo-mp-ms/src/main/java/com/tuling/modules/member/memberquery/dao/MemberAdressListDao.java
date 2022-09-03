/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.dao;



import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberAdressList;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;





/**
 * 会员地址DAO接口
 * @author wanggang
 * @version 2017-06-03
 */
@MyBatisDao
public interface MemberAdressListDao extends CrudDao<MemberAdressList> {
	
	/**
	 * 查询会员地址信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public List<MemberAdressList> queryMemberAdressList(MemberAdressList memberAdressList);

	/**
	 * 
	 * Created on 2017年9月21日 .
	 * <p>
	 * Description {获取收货信息}
	 *
	 * @author guoxue 
	 * @param memberBasicInfo
	 * @return List<MemberAdressFilter>
	 */
	public List<MemberAdressFilter> getMemberAdressFilter(MemberBasicInfo memberBasicInfo);
	
	
}