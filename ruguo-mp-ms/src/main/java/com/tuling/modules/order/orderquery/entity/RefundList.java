/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.order.orderquery.entity;




import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * 退款列表信息Entity
 * @author yuelingyun
 * @version 2017-06-30
 */
public class RefundList extends DataEntity<RefundList> {
	
	private static final long serialVersionUID = 1L;
	
	private String refundId;      				// 退款单号
	private String paymentId;      				// 支付单号
	private String orderCode;      				// 平台订单号
	private String merchantId;      			// 微信支付分配的商户号
	private String publicAccountId;       		// 公众账号ID
	private String refundAmount;       			// 退款金额
	private Date refundTime;       				// 退款时间
	private String refundStatus;       			// 退款状态，0失败；1成功
	private String refundWayCode;       		// refund方式编码
	private String refundReturnCode;       		// 退款返回码
	private String refundReturnMessage;       	// refund返回信息

	
	public RefundList() {
		super();
	}
	
	public RefundList(String id){
		super(id);
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPublicAccountId() {
		return publicAccountId;
	}

	public void setPublicAccountId(String publicAccountId) {
		this.publicAccountId = publicAccountId;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getRefundWayCode() {
		return refundWayCode;
	}

	public void setRefundWayCode(String refundWayCode) {
		this.refundWayCode = refundWayCode;
	}

	public String getRefundReturnCode() {
		return refundReturnCode;
	}

	public void setRefundReturnCode(String refundReturnCode) {
		this.refundReturnCode = refundReturnCode;
	}

	public String getRefundReturnMessage() {
		return refundReturnMessage;
	}

	public void setRefundReturnMessage(String refundReturnMessage) {
		this.refundReturnMessage = refundReturnMessage;
	}
}