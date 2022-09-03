/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * GoodsPicListEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsPriceList extends DataEntity<GoodsPriceList> {
	
	private static final long serialVersionUID = 1L;
	private String goodsPriceCode;  //商品价格编码
	private String goodsCode;		// 商品编码
	private String goodsType;		// 商品类型
	private String goodsValidityNum;		// 商品时效性数
	private String goodsValidityUnit;		// 商品时效性单位
	private String goodsPrice; //商品价格
	private String isDefault; //是否是默认价格
	
	
	
	public GoodsPriceList() {
		super();
	}

	public GoodsPriceList(String id){
		super(id);
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsPriceCode() {
		return goodsPriceCode;
	}

	public void setGoodsPriceCode(String goodsPriceCode) {
		this.goodsPriceCode = goodsPriceCode;
	}

	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	

	
	
}