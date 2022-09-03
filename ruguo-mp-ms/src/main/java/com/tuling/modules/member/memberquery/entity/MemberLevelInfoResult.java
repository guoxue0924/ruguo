package com.tuling.modules.member.memberquery.entity;

import com.foundation.common.persistence.DataEntity;

public class MemberLevelInfoResult extends DataEntity<MemberLevelInfoResult>{
	private static final long serialVersionUID = 1L;
	private String memberLevelCode; ///会员等级编码
	private String memberLevelName;// 会员等级名称
	private String memberLevelDesc;// 会员等级描述
	private String memberLevelMin;// 会员等级升级下限
	private String memberLevelMax;// 会员等级升级上线
	private String memberLevelDiscount;// 会员折扣，原价的90%，则字段值为90
	
	
	public String getMemberLevelCode() {
		return memberLevelCode;
	}
	public void setMemberLevelCode(String memberLevelCode) {
		this.memberLevelCode = memberLevelCode;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public String getMemberLevelDesc() {
		return memberLevelDesc;
	}
	public void setMemberLevelDesc(String memberLevelDesc) {
		this.memberLevelDesc = memberLevelDesc;
	}
	public String getMemberLevelMin() {
		return memberLevelMin;
	}
	public void setMemberLevelMin(String memberLevelMin) {
		this.memberLevelMin = memberLevelMin;
	}
	public String getMemberLevelMax() {
		return memberLevelMax;
	}
	public void setMemberLevelMax(String memberLevelMax) {
		this.memberLevelMax = memberLevelMax;
	}
	public String getMemberLevelDiscount() {
		return memberLevelDiscount;
	}
	public void setMemberLevelDiscount(String memberLevelDiscount) {
		this.memberLevelDiscount = memberLevelDiscount;
	}
	
}
