package com.tuling.modules.service.service.entity;



import com.foundation.common.persistence.DataEntity;

/**
 * MyServiceInfoEntity
 * @author guoxue
 * @version 2017-09-26
 */
public class MyServiceInfoParameter extends DataEntity<MyServiceInfoParameter> {
	
	private static final long serialVersionUID = 1L;
	private String memberName;						// 会员名称
	private String relaPerName;						// 被服务人姓名
	private String disServiceEndTime;                    //距离服务到期时间
	private String startTime;                    //购买服务起止时间
	private String endTime;                    //购买服务起止时间
	
	
	public MyServiceInfoParameter() {
		super();
	}

	public MyServiceInfoParameter(String id){
		super(id);
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRelaPerName() {
		return relaPerName;
	}

	public void setRelaPerName(String relaPerName) {
		this.relaPerName = relaPerName;
	}

	public String getDisServiceEndTime() {
		return disServiceEndTime;
	}

	public void setDisServiceEndTime(String disServiceEndTime) {
		this.disServiceEndTime = disServiceEndTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	
}