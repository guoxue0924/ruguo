package com.tuling.modules.order.orderquery.web.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.order.orderquery.entity.OrderGoods;
import com.tuling.modules.order.orderquery.entity.OrderRefund;
import com.tuling.modules.order.orderquery.service.OrderQueryBizService;

/**
 * goods Controller
 * @author yuelingyun 
 * @date 2017-06-19
 */
@Controller
@RequestMapping(value = "order/orderquery")
public class OrderQueryController extends BaseController{
	
	@Autowired
	private OrderQueryBizService orderQueryBizService;
	
	/**
	 * 获取详细信息
	 * @param id
	 * @return
	 */
	@ModelAttribute
	public OrderRefund get(@RequestParam(required = false) String id) {
		OrderRefund entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new OrderRefund(id);
			entity = orderQueryBizService.get(entity);
		}
		if (entity == null) {
			entity = new OrderRefund();
			entity = new OrderRefund();
		}
		return entity;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入订单管理运营首页}
	 *
	 * @author yuelingyun
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "indexoperation")
	public String itemIndexOperation(Model model) {
		Organization organization = new Organization();
		// 机构平台类型为渠道类型
		organization.setOrgPlatformType(CommonConstant.platformType.channel);
		// 查询机构列表
		List<Organization> list = orderQueryBizService.queryOrganizationPullList(organization);
		model.addAttribute("organizationPullList", list);
			
		return "layout1.order.orderquery.orderQueryIndexOperation";
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入订单管理客服首页}
	 *
	 * @author guoxue
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "indexcustomerservice")
	public String itemIndexCustomerService(Model model) {
		Organization organization = new Organization();
		// 机构平台类型为渠道类型
		organization.setOrgPlatformType(CommonConstant.platformType.channel);
		// 查询机构列表
		List<Organization> list = orderQueryBizService.queryOrganizationPullList(organization);
		model.addAttribute("organizationPullList", list);
			
		return "layout1.order.orderquery.orderQueryIndexCustomerService";
	}
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取订单列表}
	 *
	 * @author yuelingyun 
	 * @param param
	 * @return Page<Log>
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<Order> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		
		PageHelper<Order> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		OrderFilter orderFilter = obj.toJavaObject(OrderFilter.class);
		// 分页step3
		page = orderQueryBizService.getOrderList(new Page(obj), orderFilter);
			
		return page;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取订单和退款列表}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page<Log>
	 */
	@RequestMapping(value = "orderrefundlist")
	@ResponseBody
	public PageHelper<OrderAndRefundFilter> orderAndRefundlist(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		
		PageHelper<OrderAndRefundFilter> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		OrderFilter orderFilter = obj.toJavaObject(OrderFilter.class);
		// 分页step3
		page = orderQueryBizService.getOrderAndRefundList(new Page(obj), orderFilter);
			
		return page;
	}
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取订单信息}
	 *
	 * @author yuelingyun 
	 * @param order
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "form")
	public String form(Order order, Model model)  {
		//获取订单
		Order returnOrder = orderQueryBizService.getOrderByOrderCode(order.getOrderCode());
		model.addAttribute("order", returnOrder);
		
		OrderRefund orderRefund = new OrderRefund();
		orderRefund = orderQueryBizService.getOrderRefundInfo(order);
		if(orderRefund != null){
			model.addAttribute("orderRefund", orderRefund);
			User refundPerson = new User();
			User operationExaminer = new User();
			if(orderRefund.getRefundPerson()!="" && orderRefund.getRefundPerson()!=null ){
				refundPerson = orderQueryBizService.getUser(orderRefund.getRefundPerson());
				model.addAttribute("refundPersonName", refundPerson.getName());
			}
			String a = orderRefund.getOperationExaminer();
			if(orderRefund.getOperationExaminer()!="" && orderRefund.getOperationExaminer()!=null){
				operationExaminer = orderQueryBizService.getUser(orderRefund.getOperationExaminer());
				model.addAttribute("operationExaminer", operationExaminer.getName());
			}
			
		}
		
		
		
		return "layout3.order.orderquery.orderBasicInfoDetail";
		
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获得订单商品列表}
	 *
	 * @author yuelingyun 
	 * @param param
	 * @param request
	 * @return page
	 */
	@RequestMapping(value = "getordergoodslist")
	@ResponseBody
	public PageHelper<OrderGoods> getOrderGoodsList(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		
		PageHelper<OrderGoods> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		OrderFilter orderFilter = obj.toJavaObject(OrderFilter.class);
		// 分页step3
		page = orderQueryBizService.getOrderGoodsList(new Page(obj), orderFilter);
			
		return page;

	}
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取订单信息}
	 *
	 * @author yuelingyun 
	 * @param order
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "audit")
	public String audit(Order order, Model model)  {
		
		//获取订单
		Order returnOrder = orderQueryBizService.getOrderInfoByOrderCode(order.getOrderCode());
		model.addAttribute("order", returnOrder);
		//获取当前登录人
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		//获取退款信息
		OrderRefund orderRefund = new OrderRefund();
		orderRefund = orderQueryBizService.getOrderRefundInfo(order);
		model.addAttribute("orderRefund", orderRefund);
		//通过refundPerson获取他的姓名
		User refundPerson = new User();
		refundPerson = orderQueryBizService.getUser(orderRefund.getRefundPerson());
		model.addAttribute("refundPersonName", refundPerson.getName());
		return "layout3.order.orderquery.orderBasicInfoAudit";
		
	}
	
	
	/**
	 * 
	 * Created on 2017年9月15日 .
	 * <p>
	 * Description {获取订单信息进入退款申请页面}
	 *
	 * @author guoxue 
	 * @param order
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "applyrefund")
	public String applyrefund(Order order, Model model)  {
		
		//获取订单
		Order returnOrder = orderQueryBizService.getOrderInfoByOrderCode(order.getOrderCode());
		model.addAttribute("order", returnOrder);
		//获取当前登录人
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		return "layout3.order.orderquery.orderBasicInfoApplyRefund";
		
	}
	
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {保存退款申请人信息}
	 *
	 * @author guoxuxe 
	 * @param orderRefund
	 * @param request
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saverefundperson")
	@ResponseBody
	public ResponseHelper saveRefundPerson(OrderRefund orderRefund, HttpServletResponse response)throws IOException {
		orderQueryBizService.saveRefundPerson(orderRefund);
		responseHelper.setSuccess("申请成功!");// 返回成功状态和消息
		return responseHelper;
	}
	
	
	/**
	 * 
	 * Created on 2017年9月18日 .
	 * <p>
	 * Description {保存运营审核信息}
	 *
	 * @author guoxuxe 
	 * @param orderRefund
	 * @param request
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saveoperationexaminer")
	@ResponseBody
	public ResponseHelper saveOperationExaminer(OrderRefund orderRefund, HttpServletResponse response)throws IOException {
		orderQueryBizService.saveOperationExaminer(orderRefund);
		responseHelper.setSuccess("审核成功!");// 返回成功状态和消息
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存审核信息}
	 *
	 * @author yuelingyun 
	 * @param orderRefund
	 * @param request
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saveaudit")
	@ResponseBody
	public ResponseHelper saveAudit(OrderRefund orderRefund, HttpServletResponse response)throws IOException {
		orderQueryBizService.saveAudit(orderRefund);
		responseHelper.setSuccess("审核成功!");// 返回成功状态和消息
		return responseHelper;
	}

}
