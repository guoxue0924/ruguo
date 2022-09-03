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
public class GoodsPackageTagsFilter  {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String tagMatchedNum;		
	private List<ServicePackageListFilte> servicePackList;		
	private  List<GoodsTagsListFilter> tagList;	
	

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getTagMatchedNum() {
		return tagMatchedNum;
	}

	public void setTagMatchedNum(String tagMatchedNum) {
		this.tagMatchedNum = tagMatchedNum;
	}

	public List<ServicePackageListFilte> getServicePackList() {
		return servicePackList;
	}

	public void setServicePackList(List<ServicePackageListFilte> servicePackList) {
		this.servicePackList = servicePackList;
	}

	public List<GoodsTagsListFilter> getTagList() {
		return tagList;
	}

	public void setTagList(List<GoodsTagsListFilter> tagList) {
		this.tagList = tagList;
	}

	


	

	
	
}