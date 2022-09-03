/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goodsclass.entity;


import com.foundation.common.persistence.DataEntity;

/**
 * GoodsClassInfoEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class GoodsClassInfo extends DataEntity<GoodsClassInfo> {
	
	private static final long serialVersionUID = 1L;
	private String goodsClassCode;		// 商品分类编码
	private String goodsClassName;		// 商品分类名称
	private String goodsClassDesc;		// 商品分类描述
	private String isMinClass;		// 是否是最小分类，0不是；1是
	private String classLevel;		// 分类级别，1、2、3
	private String parentClassCode;		// 父分类编码
	private String classSeqNum;		// 同一父分类下排序号，如0001
	
	
	public GoodsClassInfo() {
		super();
	}

	public GoodsClassInfo(String id){
		super(id);
	}

	public String getGoodsClassCode() {
		return goodsClassCode;
	}

	public void setGoodsClassCode(String goodsClassCode) {
		this.goodsClassCode = goodsClassCode;
	}

	public String getGoodsClassName() {
		return goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public String getGoodsClassDesc() {
		return goodsClassDesc;
	}

	public void setGoodsClassDesc(String goodsClassDesc) {
		this.goodsClassDesc = goodsClassDesc;
	}

	public String getIsMinClass() {
		return isMinClass;
	}

	public void setIsMinClass(String isMinClass) {
		this.isMinClass = isMinClass;
	}

	public String getClassLevel() {
		return classLevel;
	}

	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}

	public String getParentClassCode() {
		return parentClassCode;
	}

	public void setParentClassCode(String parentClassCode) {
		this.parentClassCode = parentClassCode;
	}

	public String getClassSeqNum() {
		return classSeqNum;
	}

	public void setClassSeqNum(String classSeqNum) {
		this.classSeqNum = classSeqNum;
	}

	
}