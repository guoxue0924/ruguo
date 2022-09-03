/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foundation.common.config.Global;
import com.foundation.common.security.shiro.session.SessionDAO;
import com.foundation.common.servlet.ValidateCodeServlet;
import com.foundation.common.utils.CacheUtils;
import com.foundation.common.utils.CookieUtils;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.security.FormAuthenticationFilter;
import com.foundation.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.foundation.modules.sys.service.SysUserService;
import com.foundation.modules.sys.utils.UserUtils;
import com.google.common.collect.Maps;

/**
 * 登录Controller
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private  SysUserService sysUserService;
	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + "/sys/sysindex";
		}
		return "modules/sys/sysLogin";
	}
	
	/**
	 * 忘记密码
	 * @author guoxue
	 * @date 2017-09-26
	 */
	@RequestMapping(value = "forgetpassword")
	public String forgetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<User> user = sysUserService.getManagerEmail();
		model.addAttribute("user", user.get(0));
		return "modules/sys/forgetPassword";
			
		}

	/**
	 * 登陆系统成功
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-05-02
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/sys/sysindex")
	public String sysIndex(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = UserUtils.getPrincipal();
		
		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)) {
				CookieUtils.setCookie(response, "LOGINED", "true");
			} else if (StringUtils.equals(logined, "true")) {
				UserUtils.getSubject().logout();
				return "redirect:" + "/login";
			}
		}
		
/*			// 如果是手机登录，则返回JSON字符串
			if (principal.isMobileLogin()) {
				if (request.getParameter("login") != null) {
					return renderString(response, principal);
				}
				if (request.getParameter("index") != null) {
					return "modules/sys/sysIndex";
				}
				return "redirect:" + "/login";
			}*/
		//设置
		UserUtils.set(principal.getId());

		// 登陆成功后获取本地IP和当前登陆时间存入用户信息 by liuhuan at 20170515 start **
//		String hostAddress = "";
//		try {
//		    InetAddress address = InetAddress.getLocalHost();
//		    hostAddress = address.getHostAddress();
//		} catch (UnknownHostException e) {
//		    e.printStackTrace();
//		}
//		UmUser umUser = new UmUser();
//		umUser.setId(UserUtils.getUser().getId());
//		umUser.setLoginDate(new Date());
//		umUser.setLoginIp(hostAddress);
//		personService.updatePerson(umUser);
		// 登陆成功后获取本地IP和当前登陆时间存入用户信息 by liuhuan at 20170515 end **
		
//		// 登录成功后，获取上次登录的当前站点ID
//		UserUtils.putCache("siteId", StringUtils.toLong(CookieUtils.getCookie(request, "siteId")));

//		System.out.println("==========================a");
//		try {
//			byte[] bytes = com.foundation.common.utils.FileUtils.readFileToByteArray(
//					com.foundation.common.utils.FileUtils.getFile("c:\\sxt.dmp"));
//			UserUtils.getSession().setAttribute("kkk", bytes);
//			UserUtils.getSession().setAttribute("kkk2", bytes);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		for (int i=0; i<1000000; i++){
////			//UserUtils.getSession().setAttribute("a", "a");
////			request.getSession().setAttribute("aaa", "aa");
////		}
//		System.out.println("==========================b");
		//加载配置'init'的页面
		UserUtils.getUser();
		String firstMenuID = UserUtils.getFristMenuID();
		String initPath = UserUtils.getInitMenu(firstMenuID);
		if (StringUtils.isNoneBlank(initPath)) {
			return "redirect:" + initPath;
		} else {
			return "layout1.sys.sysIndex";
		}
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + "/sys/sysindex";
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
	        return renderString(response, model);
		}
		
		return "modules/sys/sysLogin";
	}

	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
