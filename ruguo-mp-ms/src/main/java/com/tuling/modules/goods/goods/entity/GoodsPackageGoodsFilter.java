/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * GoodsPackageListGoodsFilterEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsPackageGoodsFilter extends DataEntity<GoodsPackageGoodsFilter> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String goodsName;		// 商品名称
	private String goodsPicId;		// 商品封面图片
	
	
	public GoodsPackageGoodsFilter() {
		super();
	}

	public GoodsPackageGoodsFilter(String id){
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

	public String getGoodsPicId() {
		return goodsPicId;
	}

	public void setGoodsPicId(String goodsPicId) {
		this.goodsPicId = goodsPicId;
	}

	

	

	
}