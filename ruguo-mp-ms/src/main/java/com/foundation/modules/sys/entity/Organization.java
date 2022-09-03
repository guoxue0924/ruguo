/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.foundation.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.foundation.common.persistence.DataEntity;

/**
 * 机构管理服务Entity
 * @author yanqizhi
 * @version 2017-03-16
 */
public class Organization extends DataEntity<Organization> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;		// 父级机构ID
	private String parentIds;		// 所有父级机构IDS
	private String code;		// 机构代码
	private String name;		// 机构名称
	private String orgType;		// 组织机构类型
	private String orgPlatformType;		// 机构平台类型
	private String orgPostCode; //邮编
	private String orgFax;	//传真
	private String orgUrl;  //网址
	private String address;		// 详细地址
	private String contactPerson;		// 联系人
	private String contactPhone;		// 联系电话
	private String province;		// 省名称
	private String provinceCode;		// 省份编码
	private String city;		// 城市名称
	private String cityCode;		// 城市编码
	private String county;		// 县名称
	private String countyCode;		// 县编码
	private String townCode;		// 乡镇编码
	private String town;		// 乡镇名称
	
	public Organization() {
		super();
	}

	public Organization(String id){
		super(id);
	}

	@Length(min=0, max=32, message="父级机构ID长度必须介于 0 和 32 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=2000, message="所有父级机构IDS长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=50, message="机构代码长度必须介于 1 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=50, message="机构名称长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2, message="组织机构类型长度必须介于 1 和 2 之间")
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	
	@Length(min=0, max=255, message="详细地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	@Length(min=0, max=2, message="机构平台类型长度必须介于 0 和 2 之间")
	public String getOrgPlatformType() {
		return orgPlatformType;
	}

	public void setOrgPlatformType(String orgPlatformType) {
		this.orgPlatformType = orgPlatformType;
	}

	@Length(min=0, max=20, message="机构平台类型长度必须介于 0 和 20 之间")
	public String getOrgPostCode() {
		return orgPostCode;
	}

	public void setOrgPostCode(String orgPostCode) {
		this.orgPostCode = orgPostCode;
	}
	@Length(min=0, max=20, message="机构平台类型长度必须介于 0 和 20 之间")
	public String getOrgFax() {
		return orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}
	@Length(min=0, max=200, message="机构平台类型长度必须介于 0 和 200 之间")
	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="联系人长度必须介于 0 和 50 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=0, max=50, message="联系电话长度必须介于 0 和 50 之间")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
		
	@Length(min=0, max=50, message="省名称长度必须介于 0 和 50 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=50, message="省份编码长度必须介于 0 和 50 之间")
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Length(min=0, max=50, message="城市名称长度必须介于 0 和 50 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=50, message="城市编码长度必须介于 0 和 50 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=50, message="县名称长度必须介于 0 和 50 之间")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=50, message="县编码长度必须介于 0 和 50 之间")
	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	
	@Length(min=0, max=50, message="乡镇编码长度必须介于 0 和 50 之间")
	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	
	@Length(min=0, max=50, message="乡镇名称长度必须介于 0 和 50 之间")
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	
}