/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * GoodsPackageListEntity
 * @author guoxue
 * @version 2017-06-06
 */
public class GoodsPackageList extends DataEntity<GoodsPackageList> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String packGoodsCode;		// 被包含商品编码
	private String packGoodsSeq;		// 被包含商品顺序号
	
	public GoodsPackageList() {
		super();
	}

	public GoodsPackageList(String id){
		super(id);
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getPackGoodsCode() {
		return packGoodsCode;
	}

	public void setPackGoodsCode(String packGoodsCode) {
		this.packGoodsCode = packGoodsCode;
	}

	public String getPackGoodsSeq() {
		return packGoodsSeq;
	}

	public void setPackGoodsSeq(String packGoodsSeq) {
		this.packGoodsSeq = packGoodsSeq;
	}

	

	
}