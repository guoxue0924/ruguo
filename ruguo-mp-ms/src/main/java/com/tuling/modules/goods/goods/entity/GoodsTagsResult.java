/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.foundation.common.persistence.DataEntity;

/**
 * goodsEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsTagsResult extends DataEntity<GoodsTagsResult> {
	
	private static final long serialVersionUID = 1L;
	private JSONArray resultR;	
	private JSONArray resultL;
	
	public JSONArray getResultR() {
		return resultR;
	}
	public void setResultR(JSONArray resultR) {
		this.resultR = resultR;
	}
	public JSONArray getResultL() {
		return resultL;
	}
	public void setResultL(JSONArray resultL) {
		this.resultL = resultL;
	}	
	
	
}