package com.foundation.modules.datasync.user.entity;

import java.util.Date;

public class UserMainSync {
	
	private String id;
	private String loginName;
	private String password;
	private String name;
	private String userType;
	private String orgId;
	private String loginFlag;
	private Date enableDate;
	private Date pwdLastSettime;
	private Date pwdExpiredTime;
	private String isResetPwd;
	private String token;
	private String delFlag;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private String remarks;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public Date getEnableDate() {
		return enableDate;
	}
	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}
	public Date getPwdLastSettime() {
		return pwdLastSettime;
	}
	public void setPwdLastSettime(Date pwdLastSettime) {
		this.pwdLastSettime = pwdLastSettime;
	}
	public Date getPwdExpiredTime() {
		return pwdExpiredTime;
	}
	public void setPwdExpiredTime(Date pwdExpiredTime) {
		this.pwdExpiredTime = pwdExpiredTime;
	}
	public String getIsResetPwd() {
		return isResetPwd;
	}
	public void setIsResetPwd(String isResetPwd) {
		this.isResetPwd = isResetPwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
