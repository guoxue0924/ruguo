/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * 会员地址Entity
 * @author wanggang
 * @version 2017-06-10
 */
public class MemberAdressList extends DataEntity<MemberAdressList> {
	
	private static final long serialVersionUID = 1L;
    
    private String addrCode;      // 地址编码
    private String memberCode;    // 会员编码
	private String personName;      // 人员姓名
	private String mobilePhone;      // 人员手机号
	private String addrssType;      // 地址类型,0家庭住址；1单位地址；2临时地址
	private String provinceCode;       // 所在省份编码
	private String provinceName;       // 所在省名称
	private String cityCode;       // 所在市编码
	private String cityName;       // 所在市名称
	private String countyCode;       // 所在区县编码
	private String countyName;       // 所在区县名称
	private String townCode;       // 所在街道、乡镇编码
	private String townName;       // 所在街道、乡镇名称
	private String address;       // 详细地址
	private String postCode;       // 邮编
	private String isDefault;       // 是否是默认地址，0否，1是
	
	
	public MemberAdressList() {
		super();
	}
	
	public MemberAdressList(String id){
		super(id);
	}
	
	public String getAddrCode() {
		return addrCode;
	}
	
	public void setAddrCode(String addrCode) {
		this.addrCode = addrCode;
	}
	
	public String getMemberCode() {
		return memberCode;
	}
	
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getAddrssType() {
		return addrssType;
	}
	
	public void setAddrssType(String addrssType) {
		this.addrssType = addrssType;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCountyCode() {
		return countyCode;
	}
	
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	
	public String getCountyName() {
		return countyName;
	}
	
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	public String getTownCode() {
		return townCode;
	}
	
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	
	public String getTownName() {
		return townName;
	}
	
	public void setTownName(String townName) {
		this.townName = townName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getIsDefault() {
		return isDefault;
	}
	
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}