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
public class GoodsPicList extends DataEntity<GoodsPicList> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String goodsPicUrl;		// 商品图片ID
	private String goodsPicSeq;		// 商品图片顺序号
	
	public GoodsPicList() {
		super();
	}

	public GoodsPicList(String id){
		super(id);
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	

	public String getGoodsPicSeq() {
		return goodsPicSeq;
	}

	public void setGoodsPicSeq(String goodsPicSeq) {
		this.goodsPicSeq = goodsPicSeq;
	}

	public String getGoodsPicUrl() {
		return goodsPicUrl;
	}

	public void setGoodsPicUrl(String goodsPicUrl) {
		this.goodsPicUrl = goodsPicUrl;
	}

	
	
	
}