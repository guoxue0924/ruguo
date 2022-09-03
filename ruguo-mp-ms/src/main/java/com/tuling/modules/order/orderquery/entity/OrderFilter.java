package com.tuling.modules.order.orderquery.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * orderFilterEntity
 * @author yuelingyun
 * @version 2017-06-19
 */
public class OrderFilter extends DataEntity<OrderFilter> {
	
	private static final long serialVersionUID = 1L;
	private String id;				// 主键
	private String orderCode;		// 订单编号
	private String payPerName;		// 付款人姓名
	private String payPerCode;		// 用户编号
	private String startTime;		// 下单开始时间
	private String endTime;			// 下单结束时间
	private String orgCode;			// 来源渠道
	private String orderStatus;     // 订单状态
	private String goodsName;
	private String orderPayWay;
	
	public OrderFilter() {
		super();
	}

	public OrderFilter(String id){
		super(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPayPerName() {
		return payPerName;
	}

	public void setPayPerName(String payPerName) {
		this.payPerName = payPerName;
	}

	public String getPayPerCode() {
		return payPerCode;
	}

	public void setPayPerCode(String payPerCode) {
		this.payPerCode = payPerCode;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getOrderPayWay() {
		return orderPayWay;
	}

	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}
	
}