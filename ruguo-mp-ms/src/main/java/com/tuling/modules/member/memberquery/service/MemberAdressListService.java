/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.tuling.modules.member.memberquery.dao.MemberAdressListDao;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberAdressList;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;





/**
 * 会员地址Service
 * 
 * @author wanggang
 * @version 2017-06-10
 */
@Service
@Transactional(readOnly = true)
public class MemberAdressListService extends CrudService<MemberAdressListDao, MemberAdressList> {

	@Autowired
	private MemberAdressListDao memberAdressListDao;

	/**
	 * 查詢会员地址信息
	 * @author wanggang
	 * @date 2017-06-10
	 */
	public List<MemberAdressList> queryMemberAdressList(MemberAdressList memberAdressList){
		
		List<MemberAdressList> memberAdress = memberAdressListDao.queryMemberAdressList(memberAdressList);
		
		return memberAdress;
	}
	
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
	public List<MemberAdressFilter> getMemberAdressFilter(MemberBasicInfo memberBasicInfo) {
		List<MemberAdressFilter> list = memberAdressListDao.getMemberAdressFilter(memberBasicInfo);
		return list;
	}
	
}