/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.tuling.modules.payment.paymentlist.dao.PayMentListInfoDao;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoFilter;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoResult;





/**
 * 支付信息Service
 * 
 * @author wanggang
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class PayMentListInfoService extends CrudService<PayMentListInfoDao, PayMentListInfoResult> {

	@Autowired
	private PayMentListInfoDao payMentListInfoDao;

	/**
	 * 显示支付信息
	 * @author wanggang
	 * @date 2017-06-15
	 */
	public List<PayMentListInfoResult> queryPayMentListInfoResultList(PayMentListInfoFilter payMentListInfoFilter) {
		// 查询数据
		List<PayMentListInfoResult> list = payMentListInfoDao.queryPayMentListInfoResultList(payMentListInfoFilter);
		return list;
	}
}