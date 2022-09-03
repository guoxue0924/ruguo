/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.foundation.common.service.BaseService;
import com.foundation.common.utils.DateUtils;
import com.foundation.modules.sys.entity.Menu;
import com.foundation.modules.sys.utils.LogUtils;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * 日志拦截器
 * @author hl
 * @version 2017-06-15 16:57:24
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal =
			new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		String requestUri = request.getServletPath();
		
		Menu menu = UserUtils.getMenuByUri(requestUri, UserUtils.getAllMenuList());
		if (StringUtils.isNotBlank(menu.getId())) {
			Menu menu_self = UserUtils.getMenuByUri(requestUri, UserUtils.getMenuList());
			if (StringUtils.isBlank(menu_self.getId())) {
				request.getRequestDispatcher("/WEB-INF/views/error/403.jsp").forward(request, response);
				return false;
			}
		}
		
		if (logger.isDebugEnabled()){
			long beginTime = System.currentTimeMillis();//1、开始时间  
	        startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）  
	        logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
	        	.format(beginTime), request.getRequestURI());
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		String requestUri = request.getServletPath();
		if(UserUtils.getMenuList() == null){
			return;
		}
		//查询当前URL对应系统菜单ID
		Menu menu = UserUtils.getMenuByUri(requestUri, UserUtils.getMenuList());
		if(StringUtils.isNotBlank(menu.getId())){
			modelAndView.addObject("currentMenu", menu);
		}
		if (modelAndView != null){
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {

		// 保存日志
		LogUtils.saveLog(request, handler, ex, null);
		
		// 打印JVM信息。
		if (logger.isDebugEnabled()){
			long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
			long endTime = System.currentTimeMillis(); 	//2、结束时间  
	        logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
	        		new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), 
	        		DateUtils.formatDateTime(endTime - beginTime),
					request.getRequestURI(), 
					Runtime.getRuntime().maxMemory()/1024/1024, 
					Runtime.getRuntime().totalMemory()/1024/1024, 
					Runtime.getRuntime().freeMemory()/1024/1024, 
				   (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024); 
	        //删除线程变量中的数据，防止内存泄漏
	        startTimeThreadLocal.remove();
		}
	}

}
