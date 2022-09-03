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
public class MemberAdressFilter extends DataEntity<MemberAdressFilter> {
	
	private static final long serialVersionUID = 1L;
    
    private String memberName;    // 会员名称
	private String personName;      // 收货姓名
	private String mobilePhone;      // 收货手机号
	private String address;      // 地址
	
	
	
	public MemberAdressFilter() {
		super();
	}
	
	public MemberAdressFilter(String id){
		super(id);
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}