package com.tuling.modules.order.orderquery.entity;


import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * orderGoodsEntity
 * @author yuelingyun
 * @version 2017-06-19
 */
public class OrderGoods extends DataEntity<OrderGoods> {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;						// 订单编码
	private String goodsCode;						// 商品编码
	private String goodsName;						// 商品名称
	private String goodsType;						// 商品类型
	private String goodsPrice;						// 商品原价
	private String goodsDiscountPrice;            	// 商品优惠价
	private String goodsUintNum;					// 商品个数
	private String goodsServiceCodes;				// 商品包含服务（商品）编码信息结果集，通过#分隔
	private String goodsServiceNames;				// 商品包含服务名称信息结果集，通过#分隔。
	private Date createTime; 						// 产生的时间
	private Date updateTime; 						// 更新的时间
	private String delFlag; 						// 标识记录是否删除 
	private String remark; 						// 备注
	
	
	public OrderGoods() {
		super();
	}

	public OrderGoods(String id){
		super(id);
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsUintNum() {
		return goodsUintNum;
	}

	public void setGoodsUintNum(String goodsUintNum) {
		this.goodsUintNum = goodsUintNum;
	}

	public String getGoodsServiceCodes() {
		return goodsServiceCodes;
	}

	public void setGoodsServiceCodes(String goodsServiceCodes) {
		this.goodsServiceCodes = goodsServiceCodes;
	}

	public String getGoodsServiceNames() {
		return goodsServiceNames;
	}

	public void setGoodsServiceNames(String goodsServiceNames) {
		this.goodsServiceNames = goodsServiceNames;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}

	public void setGoodsDiscountPrice(String goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}
}