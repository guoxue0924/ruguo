/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * GoodsRateListEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsRateList extends DataEntity<GoodsRateList> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String memberLevelCode;		// 会员等级编码
	private String memberRate;		// 会员折扣
	
	
	public GoodsRateList() {
		super();
	}

	public GoodsRateList(String id){
		super(id);
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getMemberLevelCode() {
		return memberLevelCode;
	}

	public void setMemberLevelCode(String memberLevelCode) {
		this.memberLevelCode = memberLevelCode;
	}



	public String getMemberRate() {
		return memberRate;
	}

	public void setMemberRate(String memberRate) {
		this.memberRate = memberRate;
	}

	

	
}