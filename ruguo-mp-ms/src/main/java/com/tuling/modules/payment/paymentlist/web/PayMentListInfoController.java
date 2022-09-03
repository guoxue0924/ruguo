/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.web;




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
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoFilter;
import com.tuling.modules.payment.paymentlist.entity.PayMentListInfoResult;
import com.tuling.modules.payment.paymentlist.service.PayMentListInfoBizService;




/**
 * 支付信息Controller
 * 
 * @author wanggang
 * @version 2017-06-15
 */
@Controller
@RequestMapping(value = "payment/paymentlist")
public class PayMentListInfoController extends BaseController {

	@Autowired
	private PayMentListInfoBizService payMentListInfoBizService;	
	
	@ModelAttribute
	public PayMentListInfoResult get(@RequestParam(required = false) String id) {
		PayMentListInfoResult entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new PayMentListInfoResult(id);
			entity = payMentListInfoBizService.get(entity);
		}
		if (entity == null) {
			entity = new PayMentListInfoResult();
			entity = new PayMentListInfoResult();
		}
		
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.payment.paymentlist.payMentListInfoIndex";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<PayMentListInfoResult> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		// 分页step1
	    JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		PayMentListInfoFilter payMentListInfoFilter = obj.toJavaObject(PayMentListInfoFilter.class);
		// 分页step3
	    PageHelper<PayMentListInfoResult> page = payMentListInfoBizService.queryPayMentListInfoResultList(new Page(obj), payMentListInfoFilter);
		
		return page;

	}
	
	/**
	 * 显示支付列表详细信息
	 * 
	 * @author wanggang
	 * @version 2017-06-15
	 */
	@RequestMapping(value = "detail")
	public String detail(PayMentListInfoResult payMentListInfoResult,Model model) {
    	// 添加支付列表信息
		model.addAttribute("payMentListInfoResult", payMentListInfoResult);
	   
		return "layout3.payment.paymentlist.payMentListInfoDetail";
	}
	
}