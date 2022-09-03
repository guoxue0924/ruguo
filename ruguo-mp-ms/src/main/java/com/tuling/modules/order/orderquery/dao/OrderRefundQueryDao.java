package com.tuling.modules.order.orderquery.dao;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderRefund;

/**
 * RefundDAO接口
 * @author yuelingyun
 * @version 2017-06-19
 */
@MyBatisDao
public interface OrderRefundQueryDao extends CrudDao<OrderRefund> {

	/**
	 * 
	 * Created on 2017年9月20日 .
	 * <p>
	 * Description {通过code获取订单退款信息}
	 *
	 * @author guoxue 
	 * @param order
	 * @return OrderRefund
	 */
	public OrderRefund getOrderRefundInfoByCode(Order order);

	/**
	 * 
	 * Created on 2017年9月20日 .
	 * <p>
	 * Description {保存退款运营审核信息}
	 *
	 * @author guoxue 
	 * @param orderRefund 
	 */
	public void saveOperationExaminer(OrderRefund orderRefund);
	

}