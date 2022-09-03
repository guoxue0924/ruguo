/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.web;




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
import com.tuling.modules.payment.refundlist.entity.RefundListInfoFilter;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoResult;
import com.tuling.modules.payment.refundlist.service.RefundListInfoBizService;




/**
 * 退款列表信息Controller
 * 
 * @author wanggang
 * @version 2017-06-17
 */
@Controller
@RequestMapping(value = "payment/refundlist")
public class RefundListInfoController extends BaseController {

	@Autowired
	private RefundListInfoBizService refundListInfoBizService;	

	
	@ModelAttribute
	public RefundListInfoResult get(@RequestParam(required = false) String id) {
		RefundListInfoResult entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new RefundListInfoResult(id);
			entity = refundListInfoBizService.get(entity);
		}
		if (entity == null) {
			entity = new RefundListInfoResult();
			entity = new RefundListInfoResult();
		}

		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.payment.refundlist.refundListInfoIndex";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<RefundListInfoResult> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		// 分页step1
	    JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		RefundListInfoFilter refundListInfoFilter = obj.toJavaObject(RefundListInfoFilter.class);
		// 分页step3
		PageHelper<RefundListInfoResult> page = refundListInfoBizService.queryRefundListInfoResultList(new Page(obj), refundListInfoFilter);
	
		return page;

	}
	
	/**
	 * 显示退款列表详细信息
	 * 
	 * @author wanggang
	 * @version 2017-06-18
	 */
	@RequestMapping(value = "detail")
	public String detail(RefundListInfoResult refundListInfoResult,Model model) {
    	// 添加退款列表信息
		model.addAttribute("refundListInfoResult", refundListInfoResult);
		return "layout3.payment.refundlist.refundListInfoDetail";
	}
	
}