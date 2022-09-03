package com.foundation.modules.sys.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * ajax调用的时候服务端返回client的数据结构
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-09
 */
@Component
public class ResponseHelper {

	private String status; // 消息状态 "fail","success"

	private String msg; // 消息内容

	private Object data; // 自定义传递数据

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setState(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setFail() {
		status = "fail";
	}

	public void setFail(String message) {
		status = "fail";
		this.msg = message;
	}

	public void setFail(String message, Object data) {
		status = "fail";
		this.msg = message;
		this.data = data;
	}

	public void setSuccess() {
		status = "success";
	}

	public void setSuccess(String message) {
		status = "success";
		msg = message;
	}

	public void setSuccess(String message, Object data) {
		this.status = "success";
		this.msg = message;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * IE8下带附件表单的请求头，只能接收text/html相应头。
	 * 此情况请使用text/html响应头。
	 */
	public void writeResponseToText(HttpServletResponse response, ResponseHelper responseHelper) throws IOException{
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=utf-8");
	    response.getWriter().write(JSON.toJSONString(responseHelper));
	}

}
