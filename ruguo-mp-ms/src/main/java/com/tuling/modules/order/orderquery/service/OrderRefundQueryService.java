package com.tuling.modules.order.orderquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.BaseEntity;
import com.foundation.common.service.CrudService;
import com.tuling.modules.order.orderquery.dao.OrderRefundQueryDao;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderRefund;

/**
 * orderRefundQueryService
 * 
 * @author yuelinyun
 * @version 2017-06-19
 */
@Service
public class OrderRefundQueryService extends CrudService<OrderRefundQueryDao, OrderRefund> {
	
	@Autowired
	private OrderRefundQueryDao orderRefundQueryDao;
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存退款信息}
	 *
	 * @author yuelingyun
	 * @param OrderRefund
	 * @return void
	 */
	public void saveAudit(OrderRefund orderRefund) {
		orderRefund.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
		super.save(orderRefund);
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存退款信息}
	 *
	 * @author yuelingyun
	 * @param OrderRefund
	 * @return void
	 */
	public void saveOperationExaminer(OrderRefund orderRefund) {
		orderRefundQueryDao.saveOperationExaminer(orderRefund);
	}

	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {通过code获取RefundInfo}
	 *
	 * @author guoxue 
	 * @param orderCode
	 * @return OrderRefund
	 */
	public OrderRefund getOrderRefundInfoByCode(Order order) {
		OrderRefund orderRefund = new OrderRefund();
		orderRefund = orderRefundQueryDao.getOrderRefundInfoByCode(order);
		return orderRefund;
	}
	
}