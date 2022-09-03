package com.tuling.modules.order.orderquery.entity;


import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * orderRefundEntity
 * @author yuelingyun
 * @version 2017-06-19
 */
public class OrderRefund extends DataEntity<OrderRefund> {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;						// 平台订单号
	private String refundPerson;                    //申请发起人
	private String refundAmount;                    //退款金额 
	private Date refundTime;                      //申请时间
	private String refundReason;					// 退款原因
	private String operationExaminer;               //运营审核人
	private String approvalStatus;					// 运营审批状态，0不同意；1同意
	private String approvalOpinion;					// 运营审批意见
	private Date operationExamineTime;            //运营审批时间
	private String financeExaminer;                 //财务审核人
	private String financeExamineStatus;            //财务审核状态
	private String financeExamineOpinion;           //财务审核意见
	private Date financeExamineTime;              //财务审核时间
	
	
	
	public OrderRefund() {
		super();
	}

	public OrderRefund(String id){
		super(id);
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getRefundPerson() {
		return refundPerson;
	}

	public void setRefundPerson(String refundPerson) {
		this.refundPerson = refundPerson;
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

	public String getOperationExaminer() {
		return operationExaminer;
	}

	public void setOperationExaminer(String operationExaminer) {
		this.operationExaminer = operationExaminer;
	}

	public Date getOperationExamineTime() {
		return operationExamineTime;
	}

	public void setOperationExamineTime(Date operationExamineTime) {
		this.operationExamineTime = operationExamineTime;
	}

	public String getFinanceExaminer() {
		return financeExaminer;
	}

	public void setFinanceExaminer(String financeExaminer) {
		this.financeExaminer = financeExaminer;
	}

	public String getFinanceExamineStatus() {
		return financeExamineStatus;
	}

	public void setFinanceExamineStatus(String financeExamineStatus) {
		this.financeExamineStatus = financeExamineStatus;
	}

	public String getFinanceExamineOpinion() {
		return financeExamineOpinion;
	}

	public void setFinanceExamineOpinion(String financeExamineOpinion) {
		this.financeExamineOpinion = financeExamineOpinion;
	}

	public Date getFinanceExamineTime() {
		return financeExamineTime;
	}

	public void setFinanceExamineTime(Date financeExamineTime) {
		this.financeExamineTime = financeExamineTime;
	}
	
	
	
}