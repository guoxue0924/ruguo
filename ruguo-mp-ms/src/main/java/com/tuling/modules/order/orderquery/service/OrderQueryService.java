package com.tuling.modules.order.orderquery.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.constant.CommonConstant;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.dao.OrganizationDao;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.order.orderquery.dao.OrderQueryDao;
import com.tuling.modules.order.orderquery.entity.MyOrderFilter;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;

/**
 * orderQueryService
 * 
 * @author yuelinyun
 * @version 2017-06-05
 */
@Service
public class OrderQueryService extends CrudService<OrderQueryDao, Order> {

	@Autowired
	private OrderQueryDao orderQueryDao;
	
	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单列表}
	 *
	 * @author yuelingyun
	 * @param orderFilter
	 * @ruturn List
	 */
	public List<Order> getOrderList (OrderFilter orderFilter) {
		String orderStatus = orderFilter.getOrderStatus();
		// 如果订单状态为9，说明需要查询全部的订单数据，9为前台默认代表“全部”的值
		if(CommonConstant.orderStatus.DICT_ORDER_STATUS_ALL.equals(orderStatus)){
			orderFilter.setOrderStatus("");
		}
		List<Order> list = orderQueryDao.getOrderList(orderFilter);
	

		return list;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单列表}
	 *
	 * @author yuelingyun
	 * @param orderFilter
	 * @ruturn List
	 */
	public List<OrderAndRefundFilter> getOrderAndRefundList (OrderFilter orderFilter) {
		String orderStatus = orderFilter.getOrderStatus();
		// 如果订单状态为9，说明需要查询全部的订单数据，9为前台默认代表“全部”的值
		if(CommonConstant.orderStatus.DICT_ORDER_STATUS_ALL.equals(orderStatus)){
			orderFilter.setOrderStatus("");
		}
		List<OrderAndRefundFilter> list = orderQueryDao.getOrderAndRefundList(orderFilter);

		return list;

	}

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单信息}
	 *
	 * @author yuelingyun
	 * @param String
	 * @ruturn order
	 */
	public Order getOrderByOrderCode(String orderCode) {
		Order order = new Order();
		order.setOrderCode(orderCode);
		// 查询数据
		Order returnOrder = orderQueryDao.getOrderByOrderCode(order);

		return returnOrder;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单信息}
	 *
	 * @author yuelingyun
	 * @param String
	 * @ruturn order
	 */
	public Order getOrderInfoByOrderCode(String orderCode) {
		Order order = new Order();
		order.setOrderCode(orderCode);
		// 查询数据
		Order returnOrder = orderQueryDao.getOrderInfoByOrderCode(order);

		return returnOrder;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {更新订单信息}
	 *
	 * @author yuelingyun
	 * @param order
	 * @ruturn void
	 */
	public void updateOrderStatus(Order order) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			order.setUpdateBy(user);
		}
		order.setUpdateTime(new Date());
		orderQueryDao.updateOrderStatus(order);
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {显示机构的下拉列表}
	 *
	 * @author yuelingyun
	 * @param order
	 * @ruturn void
	 */
	public List<Organization> queryOrganizationPullList(Organization organization) {
		// 查询数据
		List<Organization> list = organizationDao.getOfficeListByName(organization);
		return list;
	}

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
	public List<MyOrderFilter> getmyOrder(MemberBasicInfo memberBasicInfo) {
		List<MyOrderFilter> list = orderQueryDao.getmyOrder(memberBasicInfo);
		return list;
	}
	
	
}