/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * 会员关系Entity
 * @author wanggang
 * @version 2017-06-10
 */
public class MemberRelationshipInfo extends DataEntity<MemberRelationshipInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;//姓名
    private String relaPerCode;      // 会员关系人编码
    private String memberCode;    // 会员编码
	private String relaTypeCode;      // 关系类型id
	private String relaTypeName;      // 关系类型名称
	private String relaPerName;      // 关系人姓名
	private String relaPerGenderCode;       // 关系人性别编码
	private String relaPerGenderName;       // 关系人性别名称
	private String relaPerBirthday;       // 关系人出生年月日
	private String relaPerCertificateType;       // 关系人证件类型
	private String relaPerCertificateName;       // 关系人证件名称
	private String relaPerCertificateNo;       // 关系人证件号码
	private String relaPerMobilePhone;       // 关系人手机号
	private String relaPerEmail;       // 关系人电子邮箱
	private String startCreateTime;//注册起始时间
	private String endCreateTime;//注册终止时间
	private String memberLevelName;//会员等级名称
	private String isChronicDisease;//是否有慢病
	
	
	public String getIsChronicDisease() {
		return isChronicDisease;
	}

	public void setIsChronicDisease(String isChronicDisease) {
		this.isChronicDisease = isChronicDisease;
	}

	public String getMemberLevelName() {
		return memberLevelName;
	}

	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MemberRelationshipInfo() {
		super();
	}
	
	public MemberRelationshipInfo(String id){
		super(id);
	}
	
	public String getRelaPerCode() {
		return relaPerCode;
	}
	
	public void setRelaPerCode(String relaPerCode) {
		this.relaPerCode = relaPerCode;
	}
	
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	public String getRelaTypeCode() {
		return relaTypeCode;
	}
	
	public void setRelaTypeCode(String relaTypeCode) {
		this.relaTypeCode = relaTypeCode;
	}
	
	public String getRelaTypeName() {
		return relaTypeName;
	}
	
	public void setRelaTypeName(String relaTypeName) {
		this.relaTypeName = relaTypeName;
	}
	
	public String getRelaPerName() {
		return relaPerName;
	}
	
	public void setRelaPerName(String relaPerName) {
		this.relaPerName = relaPerName;
	}
	
	public String getRelaPerGenderCode() {
		return relaPerGenderCode;
	}
	
	public void setRelaPerGenderCode(String relaPerGenderCode) {
		this.relaPerGenderCode = relaPerGenderCode;
	}
	
	public String getRelaPerGenderName() {
		return relaPerGenderName;
	}
	
	public void setRelaPerGenderName(String relaPerGenderName) {
		this.relaPerGenderName = relaPerGenderName;
	}
	
	public String getRelaPerBirthday() {
		return relaPerBirthday;
	}
	
	public void setRelaPerBirthday(String relaPerBirthday) {
		this.relaPerBirthday = relaPerBirthday;
	}
	
	public String getRelaPerCertificateType() {
		return relaPerCertificateType;
	}
	
	public void setRelaPerCertificateType(String relaPerCertificateType) {
		this.relaPerCertificateType = relaPerCertificateType;
	}
	
	public String getRelaPerCertificateName() {
		return relaPerCertificateName;
	}
	
	public void setRelaPerCertificateName(String relaPerCertificateName) {
		this.relaPerCertificateName = relaPerCertificateName;
	}
	
	public String getRelaPerCertificateNo() {
		return relaPerCertificateNo;
	}
	
	public void setRelaPerCertificateNo(String relaPerCertificateNo) {
		this.relaPerCertificateNo = relaPerCertificateNo;
	}
	
	public String getRelaPerMobilePhone() {
		return relaPerMobilePhone;
	}
	
	public void setRelaPerMobilePhone(String relaPerMobilePhone) {
		this.relaPerMobilePhone = relaPerMobilePhone;
	}
	
	public String getRelaPerEmail() {
		return relaPerEmail;
	}
	
	public void setRelaPerEmail(String relaPerEmail) {
		this.relaPerEmail = relaPerEmail;
	}

}