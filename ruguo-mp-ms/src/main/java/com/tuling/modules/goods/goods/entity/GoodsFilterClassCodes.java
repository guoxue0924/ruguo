/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;

import java.util.List;

import com.foundation.common.persistence.DataEntity;

/**
 * goodsFilterEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsFilterClassCodes extends DataEntity<GoodsFilterClassCodes> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String goodsName;		// 商品名称
	private List<String> goodsClassCode;		// 商品分类编码
	private String goodsStatus;		// 商品状态
	
	public GoodsFilterClassCodes() {
		super();
	}

	public GoodsFilterClassCodes(String id){
		super(id);
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public List<String> getGoodsClassCode() {
		return goodsClassCode;
	}

	public void setGoodsClassCode(List<String> goodsClassCode) {
		this.goodsClassCode = goodsClassCode;
	}

	

	


	

	
	
}