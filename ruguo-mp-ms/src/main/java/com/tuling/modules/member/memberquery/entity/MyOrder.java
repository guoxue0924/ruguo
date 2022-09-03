/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.entity;




import com.foundation.common.persistence.DataEntity;

/**
 * 购买商品Entity
 * @author guoxue
 * @version 2017-09-21
 */
public class MyOrder extends DataEntity<MyOrder> {
	
	private static final long serialVersionUID = 1L;
    
    private String goodsName;    // 商品名称
	private String goodsServiceNames;      // 商品简介
	private String transactionTime;      // 购买时间
	private String goodsValidity;      // 服务周期
	private String endTime;      // 服务截止时间
	
	public MyOrder() {
		super();
	}
	
	public MyOrder(String id){
		super(id);
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsServiceNames() {
		return goodsServiceNames;
	}

	public void setGoodsServiceNames(String goodsServiceNames) {
		this.goodsServiceNames = goodsServiceNames;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getGoodsValidity() {
		return goodsValidity;
	}

	public void setGoodsValidity(String goodsValidity) {
		this.goodsValidity = goodsValidity;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	

}