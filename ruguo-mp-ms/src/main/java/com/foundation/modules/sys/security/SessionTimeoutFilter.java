package com.foundation.modules.sys.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 校验ajax请求时session是否过期,统一返回状态码4081
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-05-08
 */
public class SessionTimeoutFilter extends AccessControlFilter {

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String header = httpRequest.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;

		if (isAjax) {
			//登陆超时
			WebUtils.toHttp(response).sendError(4081);
		} else {
			saveRequestAndRedirectToLogin(request, response);
		}
		return false;
	}

	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {
			return false;
		} else {
			return true;
		}

	}
}
