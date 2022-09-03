/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.web.user;

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
import com.foundation.modules.sys.utils.ResponseHelper;
import com.tuling.modules.demo.entity.DemoUser;
import com.tuling.modules.demo.entity.DemoUserFilter;
import com.tuling.modules.demo.service.DemoUserService;

/**
 * demo用户Controller
 * 
 * @author hl
 * @version 2017-04-08
 */
@Controller
@RequestMapping(value = "demo/user")
public class DemoUserController extends BaseController {

	@Autowired
	private DemoUserService demoUserService;

//	@Autowired
//	private ResponseHelper responseHelper;

	@ModelAttribute
	public DemoUser get(@RequestParam(required = false) String id) {
		DemoUser entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new DemoUser(id);
			entity = demoUserService.get(entity);
		}
		if (entity == null) {
			entity = new DemoUser();
			entity = new DemoUser();
		}
		return entity;
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.demo.user.userIndex";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "regist")
	public String regist(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).userRegist(userRegist.jsp)
		return "layout1.demo.user.userRegist";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "regist2")
	public String regist2(Model model) {

		return "layout3.demo.user.userEdit";
	}

	// @RequiresPermissions("demo:user:demoUser:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<DemoUser> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {

	/*	//获得指定Cookie的值
		String userQuery = CookieUtils.getCookie(request, "userQuery");
		//获得指定Cookie的值，并删除。
		String userQuery2 = CookieUtils.getCookie(request, response ,"userQuery");
		//设置 Cookie（生成时间为1天）
		CookieUtils.setCookie(response, "userQuery", param);*/
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		DemoUserFilter userFilter = obj.toJavaObject(DemoUserFilter.class);
		// 分页step3
		PageHelper<DemoUser> page = demoUserService.findUserList(new Page(obj), userFilter);
		
		return page;

	}

	// @RequiresPermissions("demo:user:demoUser:view")
	@RequestMapping(value = "form")
	public String form(DemoUser demoUser, Model model) {
		model.addAttribute("demoUser", demoUser);
		// return "nfpc/demo/user/demoUserForm";
		return "layout3.demo.user.userEdit";
	}

	@RequestMapping(value = "detail")
	public String detail(DemoUser demoUser, Model model) {
		model.addAttribute("demoUser", demoUser);
		// return "nfpc/demo/user/demoUserForm";
		return "layout3.demo.user.userDetail";
	}

	// @RequiresPermissions("demo:user:demoUser:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(DemoUser demoUser, @RequestBody String param,HttpServletRequest request) {
//		JSONObject obj = JSONObject.parseObject(param);
		demoUserService.save(demoUser);
		// responseHelper.setSuccess("保持信息成功",demoUser);//返回成功状态 和 消息 和 自定义数据
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息
		// result.setFail("保存信息失败");//返回错误状态 和 消息
		return responseHelper;
	}
	/**
	 * 接收json格式
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-16
	 */
	// @RequiresPermissions("demo:user:demoUser:edit")
	@RequestMapping(value = "save2")
	@ResponseBody
	public ResponseHelper save2(@RequestBody DemoUser demoUser, HttpServletRequest request) {
//		JSONObject obj = JSONObject.parseObject(param);
		demoUserService.save(demoUser);
		// responseHelper.setSuccess("保持信息成功",demoUser);//返回成功状态 和 消息 和 自定义数据
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息
		// result.setFail("保存信息失败");//返回错误状态 和 消息
		return responseHelper;
	}

	// @RequiresPermissions("demo:user:demoUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseHelper delete(DemoUser demoUser) {
		demoUserService.delete(demoUser);
		// addMessage(redirectAttributes, "删除demo用户成功");
		responseHelper.setSuccess("删除信息成功");// 返回成功状态 和 消息
		return responseHelper;
	}
	/*
	 * @RequiresPermissions("demo:user:demoUser:edit")
	 * 
	 * @RequestMapping(value = "delete") public String delete(DemoUser demoUser,
	 * RedirectAttributes redirectAttributes) {
	 * demoUserService.delete(demoUser); addMessage(redirectAttributes,
	 * "删除demo用户成功"); return ""; }
	 */

}