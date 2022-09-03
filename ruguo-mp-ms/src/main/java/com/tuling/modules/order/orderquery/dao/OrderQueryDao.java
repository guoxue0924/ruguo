package com.tuling.modules.order.orderquery.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.home.entity.HomeOrderFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.order.orderquery.entity.MyOrderFilter;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;

/**
 * OrderQueryDAO接口
 * @author yuelingyun
 * @version 2017-06-19
 */
@MyBatisDao
public interface OrderQueryDao extends CrudDao<Order> {
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {查询订单列表}
	 *
	 * @author yuelingyun 
	 * @param orderFilter
	 * @return List
	 */
	public List<Order> getOrderList(OrderFilter orderFilter);
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {查询订单列表}
	 *
	 * @author yuelingyun 
	 * @param orderFilter
	 * @return List
	 */
	public List<OrderAndRefundFilter> getOrderAndRefundList(OrderFilter orderFilter);
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {查询订单信息}
	 *
	 * @author yuelingyun 
	 * @param order
	 * @return Order
	 */
	public Order getOrderByOrderCode(Order order);
	/**
	 * 
	 * 
	 * <p>
	 * Description {根据订单编号查询订单名称}
	 *
	 * @author 
	 * @param order
	 * @return Order
	 */
	public Order getGoodName(String orderCode);
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {查询订单信息}
	 *
	 * @author yuelingyun 
	 * @param order
	 * @return Order
	 */
	public Order getOrderInfoByOrderCode(Order order);
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {更改订单状态}
	 *
	 * @author yuelingyun 
	 * @param order
	 * @return void
	 */
	public void updateOrderStatus(Order order);
	

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入后台管理平台首页}
	 *
	 * @author guoxue 
	 * @param order
	 * @return HomeOrderFilter
	 */
	public HomeOrderFilter getOrderContent(Order order);

	/**
	 * 
	 * Created on 2017年9月22日 .
	 * <p>
	 * Description {获取我的购买记录}
	 *
	 * @author guoxue 
	 * @param memberBasicInfo
	 * @return List<MyOrderFilter>
	 */
	public List<MyOrderFilter> getmyOrder(MemberBasicInfo memberBasicInfo);

}