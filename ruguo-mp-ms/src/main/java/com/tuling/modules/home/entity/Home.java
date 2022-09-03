/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.home.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * HomeEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class Home extends DataEntity<Home> {
	
	private static final long serialVersionUID = 1L;
	private String orderCount;		// 订单总量
	private String orderSum;		// 订单总金额
	private String payCount;		// 已付款数量   status = 1
	private String paySum;		// 已付款金额
	private String noPayCount;		// 未付款数量   status = 0
	private String noPaySum;		// 未付款金额
	private String cancelCount;		// 已取消数量   status = 5
	private String cancelSum;		// 已取消金额
	private String refundApplicationCount;		// 申请退款数量   status = 2
	private String refundApplicationSum;		// 申请退款金额
	private String refundingCount;		// 退款中数量            status = 3
	private String refundingSum;		// 退款中金额
	private String refundedCount;		// 已退款数量            status = 4
	private String closedSum;		// 退款中金额
	private String closedCount;		// 已退款数量            status = 6
	private String refundedSum;		// 已退款金额
	private String goodsCount;   // 商品总量
	private String goodsOnShelvesCount;   // 上架商品总量
	private String goodsOffShelvesCount;   // 下架商品总量
	private String userCount;   // 注册用户总量
	private String idConfirmUserCount;   // 身份认证总量
	private String idUnconfirmUserCount;   // 未认证身份总量
	
	public Home() {
		super();
	}

	public Home(String id){
		super(id);
	}

	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	public String getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}

	public String getPayCount() {
		return payCount;
	}

	public void setPayCount(String payCount) {
		this.payCount = payCount;
	}

	public String getPaySum() {
		return paySum;
	}

	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}

	public String getNoPayCount() {
		return noPayCount;
	}

	public void setNoPayCount(String noPayCount) {
		this.noPayCount = noPayCount;
	}

	public String getNoPaySum() {
		return noPaySum;
	}

	public void setNoPaySum(String noPaySum) {
		this.noPaySum = noPaySum;
	}

	public String getCancelCount() {
		return cancelCount;
	}

	public void setCancelCount(String cancelCount) {
		this.cancelCount = cancelCount;
	}

	public String getCancelSum() {
		return cancelSum;
	}

	public void setCancelSum(String cancelSum) {
		this.cancelSum = cancelSum;
	}

	public String getRefundApplicationCount() {
		return refundApplicationCount;
	}

	public void setRefundApplicationCount(String refundApplicationCount) {
		this.refundApplicationCount = refundApplicationCount;
	}

	public String getRefundApplicationSum() {
		return refundApplicationSum;
	}

	public void setRefundApplicationSum(String refundApplicationSum) {
		this.refundApplicationSum = refundApplicationSum;
	}

	public String getRefundingCount() {
		return refundingCount;
	}

	public void setRefundingCount(String refundingCount) {
		this.refundingCount = refundingCount;
	}

	public String getRefundingSum() {
		return refundingSum;
	}

	public void setRefundingSum(String refundingSum) {
		this.refundingSum = refundingSum;
	}

	public String getRefundedCount() {
		return refundedCount;
	}

	public void setRefundedCount(String refundedCount) {
		this.refundedCount = refundedCount;
	}

	public String getRefundedSum() {
		return refundedSum;
	}

	public void setRefundedSum(String refundedSum) {
		this.refundedSum = refundedSum;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsOnShelvesCount() {
		return goodsOnShelvesCount;
	}

	public void setGoodsOnShelvesCount(String goodsOnShelvesCount) {
		this.goodsOnShelvesCount = goodsOnShelvesCount;
	}

	public String getGoodsOffShelvesCount() {
		return goodsOffShelvesCount;
	}

	public void setGoodsOffShelvesCount(String goodsOffShelvesCount) {
		this.goodsOffShelvesCount = goodsOffShelvesCount;
	}

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	public String getIdConfirmUserCount() {
		return idConfirmUserCount;
	}

	public void setIdConfirmUserCount(String idConfirmUserCount) {
		this.idConfirmUserCount = idConfirmUserCount;
	}

	public String getIdUnconfirmUserCount() {
		return idUnconfirmUserCount;
	}

	public void setIdUnconfirmUserCount(String idUnconfirmUserCount) {
		this.idUnconfirmUserCount = idUnconfirmUserCount;
	}

	public String getClosedSum() {
		return closedSum;
	}

	public void setClosedSum(String closedSum) {
		this.closedSum = closedSum;
	}

	public String getClosedCount() {
		return closedCount;
	}

	public void setClosedCount(String closedCount) {
		this.closedCount = closedCount;
	}

	

	
}