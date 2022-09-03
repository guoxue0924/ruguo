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
public class GoodsPackageListGoodsFilter extends DataEntity<GoodsPackageListGoodsFilter> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String goodsName;		// 商品名称
	private String goodsPhoneticize;		// 商品拼音码
	private String goodsNature;		// 商品性质
	private String goodsType;		// 商品类型
	private String goodsTitle;		// 商品副标题
	private String goodsClassCode;		// 商品分类编码
	private String goodsValidityNum;		// 商品时效性数
	private String goodsValidityUnit;		// 商品时效性单位
	private String goodsPrice;		// 商品价格
	private String goodsStock;		// 商品库存
	private String goodsStatus;		// 商品状态
	private String goodsDetails;		// 商品详情
	private String isRecommend;		// 是否推荐
	private String packGoodsSeq;		// goodsPackageList表中被包含商品顺序号
	private String packageId;             //goodsPackageList表中id
	private String packGoodsCode;
	
	public GoodsPackageListGoodsFilter() {
		super();
	}

	public GoodsPackageListGoodsFilter(String id){
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

	public String getGoodsPhoneticize() {
		return goodsPhoneticize;
	}

	public void setGoodsPhoneticize(String goodsPhoneticize) {
		this.goodsPhoneticize = goodsPhoneticize;
	}

	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsClassCode() {
		return goodsClassCode;
	}

	public void setGoodsClassCode(String goodsClassCode) {
		this.goodsClassCode = goodsClassCode;
	}

	public String getGoodsValidityNum() {
		return goodsValidityNum;
	}

	public void setGoodsValidityNum(String goodsValidityNum) {
		this.goodsValidityNum = goodsValidityNum;
	}

	public String getGoodsValidityUnit() {
		return goodsValidityUnit;
	}

	public void setGoodsValidityUnit(String goodsValidityUnit) {
		this.goodsValidityUnit = goodsValidityUnit;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getPackGoodsSeq() {
		return packGoodsSeq;
	}

	public void setPackGoodsSeq(String packGoodsSeq) {
		this.packGoodsSeq = packGoodsSeq;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackGoodsCode() {
		return packGoodsCode;
	}

	public void setPackGoodsCode(String packGoodsCode) {
		this.packGoodsCode = packGoodsCode;
	}

	

	
}