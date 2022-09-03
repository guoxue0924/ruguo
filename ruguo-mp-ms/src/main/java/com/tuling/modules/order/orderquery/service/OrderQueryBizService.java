package com.tuling.modules.order.orderquery.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SysUserService;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.order.orderquery.entity.OrderGoods;
import com.tuling.modules.order.orderquery.entity.OrderRefund;
import com.tuling.modules.order.orderquery.entity.RefundList;

/**
 * orderQueryBizService
 * 
 * @author yuelingyun
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class OrderQueryBizService {

	@Autowired
	private OrderQueryService orderQueryService;
	
	@Autowired
	private OrderRefundQueryService orderRefundQueryService;
	
	@Autowired
	private OrderGoodsQueryService orderGoodsQueryService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private PageHelper<Order> pageHelper;
	
	@Autowired
	private PageHelper<OrderAndRefundFilter> pageHelper2;
	
	@Autowired
	private PageHelper<OrderGoods> pageGoodsHelper;
	
	@Autowired
	private RefundListInfoQueryService refundListInfoQueryService;
	

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单列表}
	 *
	 * @author yuelingyun
	 * @param page
	 * @ruturn page
	 */
	public PageHelper<Order> getOrderList(Page page, OrderFilter orderFilter) {

		// 分页查询，需要在filter实体中set分页信息
		orderFilter.setPage(page);
		// 查询数据
		List<Order> list = orderQueryService.getOrderList(orderFilter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单列表}
	 *
	 * @author yuelingyun
	 * @param page
	 * @ruturn page
	 */
	public PageHelper<OrderAndRefundFilter> getOrderAndRefundList(Page page, OrderFilter orderFilter) {

		// 分页查询，需要在filter实体中set分页信息
		orderFilter.setPage(page);
		// 查询数据
		List<OrderAndRefundFilter> list = orderQueryService.getOrderAndRefundList(orderFilter);
		// 装载数据
		pageHelper2.setRows(page, list);

		return pageHelper2;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单信息}
	 *
	 * @author yuelingyun 
	 * @param String
	 * @ruturn page
	 */
	public Order getOrderByOrderCode(String orderCode) {

		// 查询数据
		Order order = orderQueryService.getOrderByOrderCode(orderCode);

		return order;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单信息}
	 *
	 * @author yuelingyun
	 * @param order
	 * @ruturn order
	 */
	public Order getOrderInfoByOrderCode(String orderCode) {

		// 查询数据
		Order order = orderQueryService.getOrderInfoByOrderCode(orderCode);

		return order;

	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单商品列表}
	 *
	 * @author yuelingyun 
	 * @param orderFilter
	 * @ruturn page
	 */
	public PageHelper<OrderGoods> getOrderGoodsList(Page page, OrderFilter orderFilter) {

		// 分页查询，需要在filter实体中set分页信息
		orderFilter.setPage(page);
		// 查询数据
		List<OrderGoods> list = orderGoodsQueryService.getOrderGoodsList(orderFilter);
		// 装载数据
		pageGoodsHelper.setRows(page, list);

		return pageGoodsHelper;

	}
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {保存退款申请人信息}
	 *
	 * @author guoxue
	 * @param orderRefund
	 * @ruturn void
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveRefundPerson(OrderRefund orderRefund) {
		orderRefund.setRefundTime(new Date());
		orderRefundQueryService.saveAudit(orderRefund);
		Order order = new Order();
		order.setOrderCode(orderRefund.getOrderCode());
		order.setOrderStatus(CommonConstant.orderStatus.DICT_ORDER_STATUS_TWO);
		orderQueryService.updateOrderStatus(order);
	}
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {保存退款申请人信息}
	 *
	 * @author guoxue
	 * @param orderRefund
	 * @ruturn void
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveOperationExaminer(OrderRefund orderRefund) {
		orderRefund.setOperationExamineTime(new Date());
		orderRefundQueryService.saveOperationExaminer(orderRefund);
		if(CommonConstant.DictApplyStatus.unaudit.equals(orderRefund.getApprovalStatus())){
			Order order = new Order();
			order.setOrderCode(orderRefund.getOrderCode());
			order.setOrderStatus(CommonConstant.orderStatus.DICT_ORDER_STATUS_ONE);
			orderQueryService.updateOrderStatus(order);
		}
			
	}
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {获取订单退款信息}
	 *
	 * @author guoxue 
	 * @param orderCode
	 * @return OrderRefund
	 */
	public OrderRefund getOrderRefundInfo(Order order) {
		OrderRefund orderRefund = new OrderRefund();
		orderRefund = orderRefundQueryService.getOrderRefundInfoByCode(order);
		return orderRefund;
	}
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {通过用户id获取用户名}
	 *
	 * @author guoxue 
	 * @param refundPerson
	 * @return User
	 */
	public User getUser(String id) {
		return sysUserService.getUserById(id);
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存审核信息}
	 *
	 * @author yuelingyun
	 * @param orderRefund
	 * @ruturn void
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveAudit(OrderRefund orderRefund) {
		// 更新审核信息
		orderRefundQueryService.saveAudit(orderRefund);
		// 判断是否同意，如果同意，则调用后续的接口，根据返回状态，将订单状态变为已退款，不同意，则将状态变为已付款
		Order order = new Order();
		// 赋值订单编号
		order.setOrderCode(orderRefund.getOrderCode());
		// 如果是同意状态
		if(CommonConstant.agreeOrNot.DICT_AGREE_OR_NOT_ONE.equals(orderRefund.getApprovalStatus())){
			
			// ------------------------------------------------------
			// 	TO DO 调用支付接口，获取返回状态,根据返回状态插入退款表信息，更改订单状态
			// ------------------------------------------------------
			// 查询支付信息
			Order orderInfo = orderQueryService.getOrderByOrderCode(orderRefund.getOrderCode());
			RefundList refundList = new RefundList();
			if(orderInfo != null){
				refundList.setPaymentId(orderInfo.getPayment().getPaymentId());// 支付单号
				refundList.setMerchantId(orderInfo.getPayment().getMerchantId());// 微信支付分配的商户号
				refundList.setPublicAccountId(orderInfo.getPayment().getPublicAccountId());// 公众账号ID
				refundList.setRefundAmount(orderInfo.getPayment().getPayAmount());// 退款金额
			}
			refundList.setOrderCode(orderRefund.getOrderCode());
			refundList.setRefundStatus(CommonConstant.successOrFail.DICT_SUCCESS_OR_FAILURE_ONE);// 退款状态，0失败；1成功
			// 保存退款信息
			refundListInfoQueryService.saveRefundListInfo(refundList);
			order.setOrderStatus(CommonConstant.orderStatus.DICT_ORDER_STATUS_FOUR);// 将订单状态改为已退款
		}else{
			order.setOrderStatus(CommonConstant.orderStatus.DICT_ORDER_STATUS_ONE);// 将订单状态改为已付款
		}
		// 更新订单状态
		orderQueryService.updateOrderStatus(order);
	}
	
	/**
	 * 
	 * Created on 2017年6月28日 .
	 * <p>
	 * Description {Order的get方法}
	 *
	 * @author yuelingyun
	 * @param entity
	 * @return OrderRefund
	 */
	public OrderRefund get(OrderRefund entity) {
		OrderRefund orderRefund = orderRefundQueryService.get(entity);
		return orderRefund;
	}
	
	/**
	 * 
	 * Created on 2017年6月28日 .
	 * <p>
	 * Description {获取机构信息}
	 *
	 * @author yuelingyun
	 * @param organization
	 * @return List
	 */
	public List<Organization> queryOrganizationPullList(Organization organization) {
		// 查询数据
		List<Organization> list = orderQueryService.queryOrganizationPullList(organization);
		return list;
	}

	

	
}