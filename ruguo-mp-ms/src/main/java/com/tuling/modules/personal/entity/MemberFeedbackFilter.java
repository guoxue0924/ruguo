package com.tuling.modules.personal.entity;

import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * 意见反馈Entity
 * @author zhuming
 */
public class MemberFeedbackFilter extends DataEntity<MemberFeedbackFilter>{
	
	private static final long serialVersionUID = 1L;
	
	private String name;//用户名
	private Date startTime;//开始时间
	private Date endTime;//终止时间
	private String operationState;//操作状态
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getOperationState() {
		return operationState;
	}
	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}
	
	
	
	
}
