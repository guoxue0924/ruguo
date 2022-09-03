/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.home.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * HomeEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class HomeOrderFilter extends DataEntity<HomeOrderFilter> {
	
	private static final long serialVersionUID = 1L;
	private String orderCount;		// 订单数量
	private String priceCount;		// 订单价格
	
	
	public HomeOrderFilter() {
		super();
	}

	public HomeOrderFilter(String id){
		super(id);
	}

	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	public String getPriceCount() {
		return priceCount;
	}

	public void setPriceCount(String priceCount) {
		this.priceCount = priceCount;
	}

	
}