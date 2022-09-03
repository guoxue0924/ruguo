/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.entity;




import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * PayMentListInfoFilterEntity
 * @author wanggang
 * @version 2017-06-15
 */
public class PayMentListInfoFilter extends DataEntity<PayMentListInfoFilter> {
	
	private static final long serialVersionUID = 1L;
    

	private String paymentId;      // 支付单号
	private String orderPayWay;       // 付款方式
	private String payStatus;       // 支付状态，0失败；1成功
	private Date startTransactionTime;       // 开始交易时间
	private Date endTransactionTime;       // 结束交易时间
	
	public PayMentListInfoFilter() {
		super();
	}
	
	public PayMentListInfoFilter(String id){
		super(id);
	}
	
	public String getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	public String getOrderPayWay() {
		return orderPayWay;
	}
	
	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getStartTransactionTime() {
		return startTransactionTime;
	}

	public void setStartTransactionTime(Date startTransactionTime) {
		this.startTransactionTime = startTransactionTime;
	}

	public Date getEndTransactionTime() {
		return endTransactionTime;
	}

	public void setEndTransactionTime(Date endTransactionTime) {
		this.endTransactionTime = endTransactionTime;
	}
	

}