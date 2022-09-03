/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoFilter;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoResult;





/**
 * 退款列表业务Service
 * 
 * @author wanggang
 * @version 2017-06-17
 */
@Service
@Transactional(readOnly = true)
public class RefundListInfoBizService {

	@Autowired
	private RefundListInfoService refundListInfoService;

	@Autowired
	private PageHelper<RefundListInfoResult> pageHelper;
	
	/**
	 * 显示退款列表信息
	 * @author wanggang
	 * @date 2017-06-17
	 */
	public PageHelper<RefundListInfoResult> queryRefundListInfoResultList(Page page, RefundListInfoFilter refundListInfoFilter) {

		// 分页查询，需要在filter实体中set分页信息
		refundListInfoFilter.setPage(page);
		// 查询数据
		List<RefundListInfoResult> list = refundListInfoService.queryRefundListInfoResultList(refundListInfoFilter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;
	}
	
	/**
	 * 查询退款列表详细信息
	 * @author wanggang
	 * @date 2017-06-17
	 */
	public RefundListInfoResult get(RefundListInfoResult refundListInfoResult) {
		refundListInfoResult = refundListInfoService.get(refundListInfoResult);
		return refundListInfoResult;
	}
	
}