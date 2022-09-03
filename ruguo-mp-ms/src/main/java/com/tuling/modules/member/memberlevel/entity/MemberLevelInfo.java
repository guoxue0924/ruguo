/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberlevel.entity;



import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.foundation.common.persistence.DataEntity;

/**
 * 会员等级Entity
 * @author wanggang
 * @version 2017-06-03
 */
public class MemberLevelInfo extends DataEntity<MemberLevelInfo> {
	
	private static final long serialVersionUID = 1L;
    
	private String memberLevelCode;      // 会员等级编码
	private String memberLevelName;      // 会员等级姓名
	private String memberLevelDesc;      // 会员等级描述
	private BigDecimal  memberLevelMin;       // 会员等级升级下限
	private BigDecimal  memberLevelMax;       // 会员等级升级上限
	private String memberLevelDiscount;      //会员折扣标准
	
	
	public MemberLevelInfo() {
		super();
	}
	
	public MemberLevelInfo(String id){
		super(id);
	}
	
	public String getMemberLevelCode() {
		return memberLevelCode;
	}
	
	public void setMemberLevelCode(String memberLevelCode) {
		this.memberLevelCode = memberLevelCode;
	}
	
	@Length(min=1, max=50, message="会员等级名称长度必须介于 1 和 50 之间")
	public String getMemberLevelName() {
		return memberLevelName;
	}
	
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	
	@Length(min=1, max=200, message="会员等级详细长度必须介于 1 和 200 之间")
	public String getMemberLevelDesc() {
		return memberLevelDesc;
	}
	
	public void setMemberLevelDesc(String memberLevelDesc) {
		this.memberLevelDesc = memberLevelDesc;
	}

	@Length(min=1, max=10, message="会员等级升级下限长度必须介于 1 和 10 之间")
	public BigDecimal getMemberLevelMin() {
		return memberLevelMin;
	}
	
	public void setMemberLevelMin(BigDecimal memberLevelMin) {
		this.memberLevelMin = memberLevelMin;
	}
    
	@Length(min=1,max=10, message="会员等级升级上限长度必须介于 1 和 10 之间")
	public BigDecimal getMemberLevelMax() {
		return memberLevelMax;
	}

	public void setMemberLevelMax(BigDecimal memberLevelMax) {
		this.memberLevelMax = memberLevelMax;
	}

	public String getMemberLevelDiscount() {
		return memberLevelDiscount;
	}

	public void setMemberLevelDiscount(String memberLevelDiscount) {
		this.memberLevelDiscount = memberLevelDiscount;
	}

	
	

}