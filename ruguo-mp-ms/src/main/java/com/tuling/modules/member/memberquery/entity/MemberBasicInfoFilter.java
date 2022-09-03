/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * MemberBasicInfoFilterEntity
 * @author wanggang
 * @version 2017-06-08
 */
public class MemberBasicInfoFilter extends DataEntity<MemberBasicInfoFilter> {
	
	private static final long serialVersionUID = 1L;
    

	private String name;      // 会员名称
	private String mobilePhone;       // 手机号
	private String memberLevelName;       // 会员等级名称
	private String orgName;       // 渠道机构名称
	private String orgCode;       // 渠道机构编码
	private String memberLevelCode;       // 渠道机构名称
	private String startCreateTime;//注册起始时间
	private String endCreateTime;//注册终止时间
	private String relaPerMobilePhone;//手机号
	private String memberCode;//会员编号
	
	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getRelaPerMobilePhone() {
		return relaPerMobilePhone;
	}

	public void setRelaPerMobilePhone(String relaPerMobilePhone) {
		this.relaPerMobilePhone = relaPerMobilePhone;
	}

	public MemberBasicInfoFilter() {
		super();
	}
	
	public MemberBasicInfoFilter(String id){
		super(id);
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getMemberLevelName() {
		return memberLevelName;
	}
	
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getMemberLevelCode() {
		return memberLevelCode;
	}
	
	public void setMemberLevelCode(String memberLevelCode) {
		this.memberLevelCode = memberLevelCode;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	
    
}