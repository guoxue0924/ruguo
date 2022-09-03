package com.tuling.modules.service.service.entity;



import com.foundation.common.persistence.DataEntity;

/**
 * MyServiceInfoEntity
 * @author guoxue
 * @version 2017-09-26
 */
public class MyServiceInfoFilter extends DataEntity<MyServiceInfoFilter> {
	
	private static final long serialVersionUID = 1L;
	private String serviceCode;						// 服务编码
	private String memberName;						// 会员名称
	private String mobilePhone;						// 会员名称
	private String genderName;                      //会员性别
	private String relaPerName;						// 被服务人姓名
	private String relaPerMobilePhone;						// 被服务人手机
	private String relaPerGenderName;                  //关系人性别
	private String goodsName;						// 商品的名称
	private String goodsTitle;						// 商品的介绍
	private String serviceCycle;                        //服务周期
	private String serviceStartTime;                   //购买服务时间
	private String serviceEndTime;                    //服务结束时间
	
	private String disServiceEndTime;                    //距离服务到期时间
	private String startTime;                    //购买服务起止时间
	private String endTime;                    //购买服务起止时间
	
	
	public MyServiceInfoFilter() {
		super();
	}

	public MyServiceInfoFilter(String id){
		super(id);
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getRelaPerName() {
		return relaPerName;
	}

	public void setRelaPerName(String relaPerName) {
		this.relaPerName = relaPerName;
	}

	public String getRelaPerMobilePhone() {
		return relaPerMobilePhone;
	}

	public void setRelaPerMobilePhone(String relaPerMobilePhone) {
		this.relaPerMobilePhone = relaPerMobilePhone;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getServiceCycle() {
		return serviceCycle;
	}

	public void setServiceCycle(String serviceCycle) {
		this.serviceCycle = serviceCycle;
	}

	public String getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(String serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public String getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRelaPerGenderName() {
		return relaPerGenderName;
	}

	public void setRelaPerGenderName(String relaPerGenderName) {
		this.relaPerGenderName = relaPerGenderName;
	}

	
}