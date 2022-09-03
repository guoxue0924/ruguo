package com.tuling.modules.service.service.web;



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
import com.tuling.modules.service.service.entity.MyServiceInfo;
import com.tuling.modules.service.service.entity.MyServiceInfoFilter;
import com.tuling.modules.service.service.entity.MyServiceInfoParameter;
import com.tuling.modules.service.service.service.MyServicesBizService;

/**
 * MyServiceController
 * @author guoxue 
 * @date 2017-09-26
 */
@Controller
@RequestMapping(value = "myservices/myservices")
public class MyServicesController extends BaseController{
	
	@Autowired
	private MyServicesBizService myServicesBizService;
	
	
	@ModelAttribute
	public MyServiceInfo get(@RequestParam(required = false) String id) {
		MyServiceInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new MyServiceInfo(id);
			entity = myServicesBizService.get(entity);
		}
		if (entity == null) {
			entity = new MyServiceInfo();
		}
		return entity;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入服务管理首页}
	 *
	 * @author guoxue
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String MyServiceIndex(Model model) {
		return "layout1.service.service.myServiceIndex";
	}
	
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取服务列表}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page<Log>
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<MyServiceInfoFilter> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		
		PageHelper<MyServiceInfoFilter> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		MyServiceInfoParameter serviceFilter = obj.toJavaObject(MyServiceInfoParameter.class);
		// 分页step3
		page = myServicesBizService.getServiceList(new Page(obj), serviceFilter);
			
		return page;
	}
	
	
	/**
	 * 显示会员详细信息
	 * 
	 * @author guoxue
	 * @version 2017-09-28
	 * 
	 */
	@RequestMapping(value = "detail")
	public String detail(MyServiceInfoFilter myServiceInfoFilter,Model model){
		// 获取会员信息
		MyServiceInfoFilter myServiceInfo = myServicesBizService.getMyServiceInfoDetail(myServiceInfoFilter);
	    // 添加会员信息
		model.addAttribute("myServiceInfo", myServiceInfo);
	   
		return "layout3.service.service.myServiceInfoDetail";
	}
	

}
