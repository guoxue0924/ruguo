package com.tuling.modules.order.orderquery.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.order.orderquery.entity.OrderGoods;

/**
 * OrderQueryDAO接口
 * @author yuelingyun
 * @version 2017-06-19
 */
@MyBatisDao
public interface OrderGoodsQueryDao extends CrudDao<Order> {
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {查询订单信息}
	 *
	 * @author yuelingyun 
	 * @param orderFilter
	 * @return List
	 */
	public List<OrderGoods> getOrderGoodsList(OrderFilter orderFilter);
	
}