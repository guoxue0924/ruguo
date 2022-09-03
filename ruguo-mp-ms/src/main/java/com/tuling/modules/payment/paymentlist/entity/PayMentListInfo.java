/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.paymentlist.entity;




import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foundation.common.persistence.DataEntity;

/**
 * 支付列表信息Entity
 * @author wanggang
 * @version 2017-06-15
 */
public class PayMentListInfo extends DataEntity<PayMentListInfo> {
	
	private static final long serialVersionUID = 1L;
    
	private String paymentId;      // 支付单号
	private String orderCode;      // 平台订单号
	private String merchantId;      // 微信支付分配的商户号
	private String publicAccountId;       // 公众账号ID
	private String payAmount;       // 支付金额
	private Date transactionTime;       // 交易时间
	private String payStatus;       // 支付状态，0失败；1成功
	private String payWayCode;       // 支付方式编码
	private String payReturnCode;       // 支付返回码
	private String payReturnMessage;       // 支付返回信息

	
	public PayMentListInfo() {
		super();
	}
	
	public PayMentListInfo(String id){
		super(id);
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
	
	public String getPayAmount() {
		/*if (null == payAmount) {
			payAmount = new BigDecimal("0").toString();
		} else {
			DecimalFormat df = new DecimalFormat(",###,###.00");
			payAmount = df.format(Double.parseDouble(payAmount));
		}*/
		return payAmount;
	}
	
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTransactionTime() {
		return transactionTime;
	}
	
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayWayCode() {
		return payWayCode;
	}
	
	public void setPayWayCode(String payWayCode) {
		this.payWayCode = payWayCode;
	}
	
	public String getPayReturnCode() {
		return payReturnCode;
	}
	
	public void setPayReturnCode(String payReturnCode) {
		this.payReturnCode = payReturnCode;
	}
	
	public String getPayReturnMessage() {
		return payReturnMessage;
	}
	
	public void setPayReturnMessage(String payReturnMessage) {
		this.payReturnMessage = payReturnMessage;
	}

}