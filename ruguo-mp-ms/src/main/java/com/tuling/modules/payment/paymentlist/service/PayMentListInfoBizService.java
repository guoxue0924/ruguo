/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoFilter;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoResult;





/**
 * 支付列表业务Service
 * 
 * @author wanggang
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class PayMentListInfoBizService {

	@Autowired
	private PayMentListInfoService payMentListInfoService;

	@Autowired
	private PageHelper<PayMentListInfoResult> pageHelper;
	
	/**
	 * 显示支付列表信息
	 * @author wanggang
	 * @date 2017-06-15
	 */
	public PageHelper<PayMentListInfoResult> queryPayMentListInfoResultList(Page page, PayMentListInfoFilter payMentListInfoFilter) {

		// 分页查询，需要在filter实体中set分页信息
		payMentListInfoFilter.setPage(page);
		// 查询数据
		List<PayMentListInfoResult> list = payMentListInfoService.queryPayMentListInfoResultList(payMentListInfoFilter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;
	}
	
	/**
	 * 查询支付详细信息
	 * @author wanggang
	 * @date 2017-06-15
	 */
	public PayMentListInfoResult get(PayMentListInfoResult payMentListInfoResult) {
		payMentListInfoResult = payMentListInfoService.get(payMentListInfoResult);
		return payMentListInfoResult;
	}
	
}