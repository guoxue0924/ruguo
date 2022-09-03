package com.tuling.modules.order.orderquery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.service.CrudService;
import com.tuling.modules.order.orderquery.dao.OrderGoodsQueryDao;
import com.tuling.modules.order.orderquery.dao.OrderQueryDao;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.order.orderquery.entity.OrderGoods;

/**
 * orderQueryService
 * 
 * @author yuelinyun
 * @version 2017-06-05
 */
@Service
public class OrderGoodsQueryService extends CrudService<OrderQueryDao, Order> {

	@Autowired
	private OrderGoodsQueryDao orderGoodsQueryDao;

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单商品列表}
	 *
	 * @author yuelingyun
	 * @param orderFilter
	 * @return List
	 */
	public List<OrderGoods> getOrderGoodsList (OrderFilter orderFilter) {
		
		List<OrderGoods> list = orderGoodsQueryDao.getOrderGoodsList(orderFilter);

		return list;

	}
	
}