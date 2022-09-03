/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * GoodsTagsListEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsTagsListFilter {
	
	private static final long serialVersionUID = 1L;
	private String tagCode;	// 商品名称
	private String tagType;	// 商品名称
	private String dataElementCode;	// 商品名称
	private String dataElementValue;	// 商品名称
	
	
	
	
	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	
	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getDataElementCode() {
		return dataElementCode;
	}

	public void setDataElementCode(String dataElementCode) {
		this.dataElementCode = dataElementCode;
	}

	public String getDataElementValue() {
		return dataElementValue;
	}

	public void setDataElementValue(String dataElementValue) {
		this.dataElementValue = dataElementValue;
	}

	

	
	
}