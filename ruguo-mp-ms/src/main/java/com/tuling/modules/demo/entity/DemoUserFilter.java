/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foundation.common.persistence.DataEntity;

/**
 * demo用户Entity
 * @author hl
 * @version 2017-04-08
 */
public class DemoUserFilter extends DataEntity<DemoUserFilter> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// org_id
	private String loginName;		// 登录名
	private String name;		// 姓名
	private String email;		// 邮箱
	private String phone;		// 电话
	private String mobile;		// 手机
	private String userType;		// 用户类型
	private Date birthday;		//出生日期
	private String loginFlag;		// 是否可登录
	
	public DemoUserFilter() {
		super();
	}

	public DemoUserFilter(String id){
		super(id);
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
}