/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;






import com.foundation.common.persistence.DataEntity;

/**
 * MemberBasicInfoResult用户Entity
 * @author wanggang
 * @version 2017-06-08
 */
public class MemberBasicInfoResult extends DataEntity<MemberBasicInfoResult> {
	
	private static final long serialVersionUID = 1L;
                      
	private String code;               // 会员编码
	private MemberBasicInfo memberBasicInfo;
	private String monetary;       // 消费金额
	private String memberLevelName;       // 会员等级名称
	private String orgName;            // 机构名称
	private String relaPerName;//会员姓名
	private String relaPerGenderName;//会员性别
	private String relaPerBirthday;//出生日期
	private String relaPerCertificateName;//证件类型名称
	private String relaPerCertificateType;//证件类型
	private String relaPerCertificateNo;//证件号码
	private String relaPerMobilePhone;//手机号
	
	public String getRelaPerCertificateName() {
		return relaPerCertificateName;
	}

	public void setRelaPerCertificateName(String relaPerCertificateName) {
		this.relaPerCertificateName = relaPerCertificateName;
	}

	public String getRelaPerCertificateType() {
		return relaPerCertificateType;
	}

	public void setRelaPerCertificateType(String relaPerCertificateType) {
		this.relaPerCertificateType = relaPerCertificateType;
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

	public String getRelaPerName() {
		return relaPerName;
	}

	public void setRelaPerName(String relaPerName) {
		this.relaPerName = relaPerName;
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

	public MemberBasicInfoResult() {
		super();
	}
	
	public MemberBasicInfoResult(String id){
		super(id);
	}

	public String getMonetary() {		
		return monetary;
	}
	
	public void setMonetary(String monetary) {
		this.monetary = monetary;
	}
	
	public MemberBasicInfo getMemberBasicInfo() {
		return memberBasicInfo;
	}
	
	public void setMemberBasicInfo(MemberBasicInfo memberBasicInfo) {
		this.memberBasicInfo = memberBasicInfo;
	}
	
	public String getMemberLevelName() {
		return memberLevelName;
	}
	
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
    
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}