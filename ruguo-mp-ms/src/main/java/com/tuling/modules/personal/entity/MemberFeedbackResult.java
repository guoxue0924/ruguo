package com.tuling.modules.personal.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * 意见反馈Entity
 * @author zhuming
 */
public class MemberFeedbackResult extends DataEntity<MemberFeedbackResult>{
	
	private static final long serialVersionUID = 1L;
	
	private String feedbackCode;//意见编码
	private String feedbackType;//意见类别
	private String memberCode;//会员编码
	private String document;//意见内容
	private String operationState;//操作状态
	private String replyContent;//回复内容
	private String replyPersonnelCode;//回复操作人编码
	private String replyPersonnelName;//回复操作人姓名
	
	private String name;//会员姓名（反馈人用户名）
	private String mobilePhone;//手机号
	private String email;//电子邮箱
	
	public String getFeedbackCode() {
		return feedbackCode;
	}
	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getOperationState() {
		return operationState;
	}
	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyPersonnelCode() {
		return replyPersonnelCode;
	}
	public void setReplyPersonnelCode(String replyPersonnelCode) {
		this.replyPersonnelCode = replyPersonnelCode;
	}
	public String getReplyPersonnelName() {
		return replyPersonnelName;
	}
	public void setReplyPersonnelName(String replyPersonnelName) {
		this.replyPersonnelName = replyPersonnelName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
