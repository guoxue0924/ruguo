/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoFilter;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoResult;







/**
 * 支付列表信息DAO接口
 * @author wanggang
 * @version 2017-06-15
 */
@MyBatisDao
public interface PayMentListInfoDao extends CrudDao<PayMentListInfoResult> {
	
	/**
	 * 显示支付信息
	 * @author wanggang
	 * @date 2017-06-15
	 */
	public List<PayMentListInfoResult> queryPayMentListInfoResultList(PayMentListInfoFilter payMentListInfoFilter);
	
}