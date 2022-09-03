package com.tuling.modules.order.orderquery.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * MyOrderFilter
 * @author guoxue
 * @version 2017-09-22
 */
public class MyOrderFilter extends DataEntity<MyOrderFilter> {
	
	private static final long serialVersionUID = 1L;
	private String goodsName;				// 商品名称
	private String goodsType;		// 商品类型
	private String goodsTitle;		// 商品标题
	private String goodsServiceCodes;		// 产品包包含商品code
	private String goodsServiceNames;		// 产品包包含商品名称
	private String transactionTime;		// 交易时间
	private String goodsValidityNum;		// 时效性
	private String goodsValidityUnit;			// 时效性单位
	
	public MyOrderFilter() {
		super();
	}

	public MyOrderFilter(String id){
		super(id);
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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

	public String getGoodsValidityNum() {
		return goodsValidityNum;
	}

	public void setGoodsValidityNum(String goodsValidityNum) {
		this.goodsValidityNum = goodsValidityNum;
	}

	public String getGoodsValidityUnit() {
		return goodsValidityUnit;
	}

	public void setGoodsValidityUnit(String goodsValidityUnit) {
		this.goodsValidityUnit = goodsValidityUnit;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsServiceCodes() {
		return goodsServiceCodes;
	}

	public void setGoodsServiceCodes(String goodsServiceCodes) {
		this.goodsServiceCodes = goodsServiceCodes;
	}
	
	
}