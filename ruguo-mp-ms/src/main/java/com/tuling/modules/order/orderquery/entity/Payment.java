package com.tuling.modules.order.orderquery.entity;


import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * paymentEntity
 * @author yuelingyun
 * @version 2017-06-19
 */
public class Payment extends DataEntity<Payment> {
	
	private static final long serialVersionUID = 1L;
	private String paymentId;						// 支付单号
	private String orderCode;						// 平台订单号
	private String merchantId;						// 微信支付分配的商户号
	private String publicAccountId;					// 公众账号ID
	private String payAmount;						// 支付金额
	private Date transactionTime;					// 交易时间
	private String payStatus;						// 支付状态，0失败；1成功
	private String payWayCode;						// 支付方式编码
	private String payReturnCode;					// 支付返回码
	private String payReturnMessage;				// 支付返回信息
	private Date createTime; 						// 产生的时间
	private Date updateTime; 						// 更新的时间
	private String delFlag; 						// 标识记录是否删除 
	private String remark; 						// 备注
	
	
	public Payment() {
		super();
	}

	public Payment(String id){
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
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}