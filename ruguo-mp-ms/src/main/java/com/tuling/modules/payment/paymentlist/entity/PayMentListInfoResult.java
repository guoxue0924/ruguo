/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.entity;





import com.foundation.common.persistence.DataEntity;

/**
 * PayMentListInfoResultEntity
 * @author wanggang
 * @version 2017-06-15
 */
public class PayMentListInfoResult extends DataEntity<PayMentListInfoResult> {
	
	private static final long serialVersionUID = 1L;
    
	private PayMentListInfo payMentListInfo; // 支付列表信息Entity
	private String orderPayWay;       // 付款方式
	
	public PayMentListInfoResult() {
		super();
	}
	
	public PayMentListInfoResult(String id){
		super(id);
	}

	public PayMentListInfo getPayMentListInfo() {
		return payMentListInfo;
	}

	public void setPayMentListInfo(PayMentListInfo payMentListInfo) {
		this.payMentListInfo = payMentListInfo;
	}

	public String getOrderPayWay() {
		return orderPayWay;
	}

	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}

}