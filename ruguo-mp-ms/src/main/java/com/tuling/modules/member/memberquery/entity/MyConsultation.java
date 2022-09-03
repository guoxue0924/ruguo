/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * 收藏文章Entity
 * @author guoxue
 * @version 2017-09-21
 */
public class MyConsultation extends DataEntity<MyConsultation> {
	
	private static final long serialVersionUID = 1L;
    
    private String title;    // 会员名称
	private String synopsis;      // 收货姓名
	private String picId;      // 收货手机号
	private String consultationName;      // 地址
	private String memberCode;      // 地址
	private String consultationId;      // 地址
	
	public MyConsultation() {
		super();
	}
	
	public MyConsultation(String id){
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getConsultationName() {
		return consultationName;
	}

	public void setConsultationName(String consultationName) {
		this.consultationName = consultationName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}

	

}