/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foundation.common.persistence.DataEntity;
import com.foundation.common.utils.Collections3;
import com.foundation.common.utils.excel.annotation.ExcelField;
import com.google.common.collect.Lists;

/**
 * 用户Entity
 * @author huanglin 2017-06-14
 *
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;
//	private Office company;	    // 归属公司
//	private Office office;	    // 归属部门
	private String loginName;   // 登录名
	private String password;    // 密码
	private String name;	    // 姓名
	private String loginIp;	    // 最后登陆IP
	private Date loginDate;	    // 最后登陆日期
	private Integer loginNum;   // 总登陆次数
	private String orgId;		// 所属机构ID
	private String loginFlag;   // 是否允许登陆
	private Date enableDate;	// 启用时间
	private Date pwdLastSettime;	// 上次设置密码时间
	private Date pwdExpiredTime;	// 密码过期时间
	private String isResetPwd;		// 是否需要重设密码
	
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	
	private String token;
	private String oldLoginIp;	// 上次登陆IP
	private Date oldLoginDate;	// 上次登陆日期
	
//	sys_user_info
	private String certificateType;		// 证件类型
	private String certificateNo;		// 证件号码
	private String gender;		// 性别
	private Date birthday;		// 出生年月
	private String educateGrade;		// 文化程度
	private String positionTitle;		// 职称
	private String telephone;		// 电话
	private String mobilePhone;		// 手机
	private String email;		// 邮箱
	private String wechat;		// 微信
	private String qq;		// QQ
	private String department;		// 部门/科室
	private String address;		// 机构地址
    private String otherDept;	//其他部门/科室	
    private String otherProf;	//其他职称
	private String unitName;		// 单位名称

	private String serviceOrganizationId;//服务机构ID
	private String manageOrganizationId;//管理机构ID
	private String serviceOrganizationName;//服务机构ID
	private String manageOrganizationName;//管理机构ID
	
	private String orgName;//机构名称
	private String ownZoneCode;//行政区划
	
	private String orgLevel;//区划级别
	
	private String provinceCode;//省
	private String cityCode;//市
	private String countyCode;//县
	private String townCode;//乡
	
	private String userId;
	private String roleId;
	
	private String userRole;
	private String userRoleName;
	private String userRoleLevel;
	
	private String contactWay;//联系方式
	

	private Role role;	// 根据角色查询用户条件
	
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	private User userInfoCreateBy;
	private Date userInfoCreateTime;
	private User userInfoUpdateBy;
	private Date userInfoUpdateTime;
	private String userInfoRemark;
	private String userInfoDelFlag;

	public User() {
		super();
		//this.loginFlag = Global.YES;
	}
	
	public User(String id){
		super(id);
	}

	public User(String id, String loginName){
		super(id);
		this.loginName = loginName;
	}

	public User(Role role){
		super();
		this.role = role;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="最后登录日期", type=1, align=1, sort=110)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		if (oldLoginIp == null){
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOldLoginDate() {
		if (oldLoginDate == null){
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
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

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPwdLastSettime() {
		return pwdLastSettime;
	}

	public void setPwdLastSettime(Date pwdLastSettime) {
		this.pwdLastSettime = pwdLastSettime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducateGrade() {
		return educateGrade;
	}

	public void setEducateGrade(String educateGrade) {
		this.educateGrade = educateGrade;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getServiceOrganizationId() {
		return serviceOrganizationId;
	}

	public void setServiceOrganizationId(String serviceOrganizationId) {
		this.serviceOrganizationId = serviceOrganizationId;
	}

	public String getManageOrganizationId() {
		return manageOrganizationId;
	}

	public void setManageOrganizationId(String manageOrganizationId) {
		this.manageOrganizationId = manageOrganizationId;
	}

	public String getServiceOrganizationName() {
		return serviceOrganizationName;
	}

	public void setServiceOrganizationName(String serviceOrganizationName) {
		this.serviceOrganizationName = serviceOrganizationName;
	}

	public String getManageOrganizationName() {
		return manageOrganizationName;
	}

	public void setManageOrganizationName(String manageOrganizationName) {
		this.manageOrganizationName = manageOrganizationName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

//	public String getZoneCode() {
//		return zoneCode;
//	}
//
//	public void setZoneCode(String zoneCode) {
//		this.zoneCode = zoneCode;
//	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public String getOwnZoneCode() {
		return ownZoneCode;
	}

	public void setOwnZoneCode(String ownZoneCode) {
		this.ownZoneCode = ownZoneCode;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserRoleLevel() {
		return userRoleLevel;
	}

	public void setUserRoleLevel(String userRoleLevel) {
		this.userRoleLevel = userRoleLevel;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	@Override
	public String toString() {
		return id;
	}

	public String getOtherDept() {
		return otherDept;
	}

	public void setOtherDept(String otherDept) {
		this.otherDept = otherDept;
	}

	public String getOtherProf() {
		return otherProf;
	}

	public void setOtherProf(String otherProf) {
		this.otherProf = otherProf;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public User getUserInfoCreateBy() {
		return userInfoCreateBy;
	}

	public void setUserInfoCreateBy(User userInfoCreateBy) {
		this.userInfoCreateBy = userInfoCreateBy;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUserInfoCreateTime() {
		return userInfoCreateTime;
	}

	public void setUserInfoCreateTime(Date userInfoCreateTime) {
		this.userInfoCreateTime = userInfoCreateTime;
	}

	public User getUserInfoUpdateBy() {
		return userInfoUpdateBy;
	}

	public void setUserInfoUpdateBy(User userInfoUpdateBy) {
		this.userInfoUpdateBy = userInfoUpdateBy;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUserInfoUpdateTime() {
		return userInfoUpdateTime;
	}

	public void setUserInfoUpdateTime(Date userInfoUpdateTime) {
		this.userInfoUpdateTime = userInfoUpdateTime;
	}

	public String getUserInfoRemark() {
		return userInfoRemark;
	}

	public void setUserInfoRemark(String userInfoRemark) {
		this.userInfoRemark = userInfoRemark;
	}

	public String getUserInfoDelFlag() {
		return userInfoDelFlag;
	}

	public void setUserInfoDelFlag(String userInfoDelFlag) {
		this.userInfoDelFlag = userInfoDelFlag;
	}
	
}