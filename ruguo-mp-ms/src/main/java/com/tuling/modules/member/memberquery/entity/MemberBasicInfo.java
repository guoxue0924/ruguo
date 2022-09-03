/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * 会员个人信息Entity
 * @author wanggang
 * @version 2017-06-08
 */
public class MemberBasicInfo extends DataEntity<MemberBasicInfo> {
	
	private static final long serialVersionUID = 1L;
    
	private String code;      // 会员编码
	private String name;      // 会员名称
	private String genderCode;      // 性别编码
	private String genderName;       // 性别名称
	private String birthday;       // 出生年月日
	private String certificateTypeCode;       // 证件类型
	private String certificateName;       // 证件名称
	private String certificateNo;       // 证件号码
	private String mobilePhone;       // 手机号
	private String email;       // 电子邮箱
	private String memberLevelCode;       // 会员等级编码
	private String hight;       // 身高cm
	private String weight;       // 体重kg
	private String waistline;       // 腰围cm
	private String occupationCode;       // 职业编码
	private String occupationName;       // 职业名称
	private String isChronicDisease;       // 是否有慢病，0无，1有
	private String chronicDiseaseContent;       // 慢病内容
	private String orgCode;       // 渠道机构代码

	
	public MemberBasicInfo() {
		super();
	}
	
	public MemberBasicInfo(String id){
		super(id);
	}

	public String getMemberLevelCode() {
		return memberLevelCode;
	}
	
	public void setMemberLevelCode(String memberLevelCode) {
		this.memberLevelCode = memberLevelCode;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGenderCode() {
		return genderCode;
	}
	
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	
	public String getGenderName() {
		return genderName;
	}
	
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCertificateTypeCode() {
		return certificateTypeCode;
	}
	
	public void setCertificateTypeCode(String certificateTypeCode) {
		this.certificateTypeCode = certificateTypeCode;
	}
	
	public String getCertificateName() {
		return certificateName;
	}
	
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	
	public String getCertificateNo() {
		return certificateNo;
	}
	
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
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

	public String getHight() {
		return hight;
	}
	
	public void setHight(String hight) {
		this.hight = hight;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getWaistline() {
		return waistline;
	}
	
	public void setWaistline(String waistline) {
		this.waistline = waistline;
	}
	
	public String getOccupationCode() {
		return occupationCode;
	}
	
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}
	
	public String getOccupationName() {
		return occupationName;
	}
	
	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	
	public String getIsChronicDisease() {
		return isChronicDisease;
	}
	
	public void setIsChronicDisease(String isChronicDisease) {
		this.isChronicDisease = isChronicDisease;
	}
	
	public String getChronicDiseaseContent() {
		return chronicDiseaseContent;
	}
	
	public void setChronicDiseaseContent(String chronicDiseaseContent) {
		this.chronicDiseaseContent = chronicDiseaseContent;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}