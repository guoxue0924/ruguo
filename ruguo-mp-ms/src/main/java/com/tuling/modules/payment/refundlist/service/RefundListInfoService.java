/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.tuling.modules.payment.refundlist.dao.RefundListInfoDao;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoFilter;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoResult;





/**
 * 退款列表信息Service
 * 
 * @author wanggang
 * @version 2017-06-17
 */
@Service
@Transactional(readOnly = true)
public class RefundListInfoService extends CrudService<RefundListInfoDao, RefundListInfoResult> {

	@Autowired
	private RefundListInfoDao refundListInfoDao;

	/**
	 * 显示退款列表信息
	 * @author wanggang
	 * @date 2017-06-17
	 */
	public List<RefundListInfoResult> queryRefundListInfoResultList(RefundListInfoFilter refundListInfoFilter) {
		// 查询数据
		List<RefundListInfoResult> list = refundListInfoDao.queryRefundListInfoResultList(refundListInfoFilter);
		return list;
	}
}