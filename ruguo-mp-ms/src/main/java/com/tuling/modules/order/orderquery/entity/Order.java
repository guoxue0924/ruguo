package com.tuling.modules.order.orderquery.entity;


import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * orderEntity
 * @author yuelingyun
 * @version 2017-06-19
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;						// 订单编码
	private String orderStatus;						// 订单状态，0未付款；1已付款；2申请退款；3退款中；4已退款；5已取消；6已关闭
	private String orderPayWay;						// 付款方式
	private String payPerCode;						// 付款人编码
	private String payPerName;						// 付款人姓名
	private String payPerMoblie;					// 付款人手机
	private String relaPerCode;						// 服务对象编码
	private String relaPerName;						// 服务对象姓名
	private String relaCode;						// 服务对象关系编码
	private String relaName;						// 服务对象关系名称
	private String relaCertificateTypeCode;			// 服务对象证件类型编码
	private String relaCertificateTypeName;			// 服务对象证件名称
	private String relaCertificateNo;				// 服务对象证件号码
	private String isMsgNotice;						// 是否短信通知，0否；1是
	private String addrCode;   						// 邮寄地址编码
	private String expressCode; 					// 快递公司编码
	private String expressName; 					// 快递公司名称
	private String expressPrice; 					// 快递费用
	private Date createTime; 						// 产生的时间
	private Date updateTime; 						// 更新的时间
	private String delFlag; 						// 标识记录是否删除 
	private String remark; 							// 备注
	private String goodsName;	
	private String goodsPrice;						// 商品原价
	private String goodsDiscountPrice;            	// 商品优惠价
	private Date transactionTime;
	private String outTradeNo;
	private Payment payment;						// 付款对象
	
	private OrderRefund orderRefund;            	// 退款对象
	


	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderPayWay() {
		return orderPayWay;
	}

	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}

	public String getPayPerCode() {
		return payPerCode;
	}

	public void setPayPerCode(String payPerCode) {
		this.payPerCode = payPerCode;
	}

	public String getPayPerName() {
		return payPerName;
	}

	public void setPayPerName(String payPerName) {
		this.payPerName = payPerName;
	}

	public String getPayPerMoblie() {
		return payPerMoblie;
	}

	public void setPayPerMoblie(String payPerMoblie) {
		this.payPerMoblie = payPerMoblie;
	}

	public String getRelaPerCode() {
		return relaPerCode;
	}

	public void setRelaPerCode(String relaPerCode) {
		this.relaPerCode = relaPerCode;
	}

	public String getRelaPerName() {
		return relaPerName;
	}

	public void setRelaPerName(String relaPerName) {
		this.relaPerName = relaPerName;
	}

	public String getRelaCode() {
		return relaCode;
	}

	public void setRelaCode(String relaCode) {
		this.relaCode = relaCode;
	}

	public String getRelaName() {
		return relaName;
	}

	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}

	public String getRelaCertificateTypeCode() {
		return relaCertificateTypeCode;
	}

	public void setRelaCertificateTypeCode(String relaCertificateTypeCode) {
		this.relaCertificateTypeCode = relaCertificateTypeCode;
	}

	public String getRelaCertificateTypeName() {
		return relaCertificateTypeName;
	}

	public void setRelaCertificateTypeName(String relaCertificateTypeName) {
		this.relaCertificateTypeName = relaCertificateTypeName;
	}

	public String getRelaCertificateNo() {
		return relaCertificateNo;
	}

	public void setRelaCertificateNo(String relaCertificateNo) {
		this.relaCertificateNo = relaCertificateNo;
	}

	public String getIsMsgNotice() {
		return isMsgNotice;
	}

	public void setIsMsgNotice(String isMsgNotice) {
		this.isMsgNotice = isMsgNotice;
	}

	public String getAddrCode() {
		return addrCode;
	}

	public void setAddrCode(String addrCode) {
		this.addrCode = addrCode;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressPrice() {
		return expressPrice;
	}

	public void setExpressPrice(String expressPrice) {
		this.expressPrice = expressPrice;
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

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}

	public void setGoodsDiscountPrice(String goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public OrderRefund getOrderRefund() {
		return orderRefund;
	}

	public void setOrderRefund(OrderRefund orderRefund) {
		this.orderRefund = orderRefund;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}