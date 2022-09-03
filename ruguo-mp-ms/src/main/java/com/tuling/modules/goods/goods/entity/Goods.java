/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;


import java.util.List;

import com.foundation.common.persistence.DataEntity;

/**
 * goodsEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class Goods extends DataEntity<Goods> {
	
	private static final long serialVersionUID = 1L;
	private String goodsCode;		// 商品编码
	private String goodsClassCode;		// 商品分类编码
	private String goodsName;		// 商品名称
	private String goodsPhoneticize;		// 商品拼音码
	private String goodsTitle;		// 商品副标题
	private String goodsType;		// 商品类型
//	private String goodsNature;		// 商品性质
	private String goodsClassName;		// 商品分类编码
//	private String goodsValidityNum;		// 商品时效性数
//	private String goodsValidityUnit;		// 商品时效性单位
	
	private String serviceOrgLevelCode;  //服务机构级别code
	private String serviceOrgLevelName;  //服务机构级别name
	private String serviceOrgRankCode;  //服务机构等级code
	private String serviceOrgRankName;  //服务机构等级name
	private String servicePersonTypeCode;  //服务人员类型code
	private String servicePersonTypeName;  //服务人员类型name
	private String servicePersonLevelCode;  //服务人员级别code
	private String servicePersonLevelName;  //服务人员级别name
	private String servicePersonMajorCode;  //服务人员专业code
	private String servicePersonMajorName; //服务人员专业name
	private String probationPeriod;  //商品试用期，天数
	private String servicePackageCode; //服务包编码
	private String servicePackageName; //服务包名称
	
	
//	private String goodsPrice;		// 商品价格
	private String goodsStock;		// 商品库存
	private String goodsStatus;		// 商品状态
	private String tagMatchedNum;		// 是否推荐
	private String goodsDetails;		// 商品详情
	private String goodsDetailsMobile; //商品详情字符串，给app使用
	private String goodsIcon;    //商品图标
	private String isDetailsBanner; //是否有详情轮播图
	
	private String isRecommend;		// 是否推荐
	private String recommendPicUrl; //推荐轮播图url
//	private String packGoodsCode;   // 被包含商品编码
	private String remark; 
	
	private String[] servicePackageCodeArr;
	
	private List<GoodsPriceList> goodsPriceList;
	
	public Goods() {
		super();
	}

	public Goods(String id){
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

	public String getGoodsClassCode() {
		return goodsClassCode;
	}

	public void setGoodsClassCode(String goodsClassCode) {
		this.goodsClassCode = goodsClassCode;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getServiceOrgLevelCode() {
		return serviceOrgLevelCode;
	}

	public void setServiceOrgLevelCode(String serviceOrgLevelCode) {
		this.serviceOrgLevelCode = serviceOrgLevelCode;
	}

	public String getServiceOrgLevelName() {
		return serviceOrgLevelName;
	}

	public void setServiceOrgLevelName(String serviceOrgLevelName) {
		this.serviceOrgLevelName = serviceOrgLevelName;
	}

	public String getServicePersonTypeCode() {
		return servicePersonTypeCode;
	}

	public void setServicePersonTypeCode(String servicePersonTypeCode) {
		this.servicePersonTypeCode = servicePersonTypeCode;
	}

	public String getServicePersonTypeName() {
		return servicePersonTypeName;
	}

	public void setServicePersonTypeName(String servicePersonTypeName) {
		this.servicePersonTypeName = servicePersonTypeName;
	}

	public String getServicePersonLevelCode() {
		return servicePersonLevelCode;
	}

	public void setServicePersonLevelCode(String servicePersonLevelCode) {
		this.servicePersonLevelCode = servicePersonLevelCode;
	}

	public String getServicePersonLevelName() {
		return servicePersonLevelName;
	}

	public void setServicePersonLevelName(String servicePersonLevelName) {
		this.servicePersonLevelName = servicePersonLevelName;
	}

	public String getServicePersonMajorCode() {
		return servicePersonMajorCode;
	}

	public void setServicePersonMajorCode(String servicePersonMajorCode) {
		this.servicePersonMajorCode = servicePersonMajorCode;
	}

	public String getServicePersonMajorName() {
		return servicePersonMajorName;
	}

	public void setServicePersonMajorName(String servicePersonMajorName) {
		this.servicePersonMajorName = servicePersonMajorName;
	}

	public String getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(String probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	

	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
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

	public String getGoodsDetailsMobile() {
		return goodsDetailsMobile;
	}

	public void setGoodsDetailsMobile(String goodsDetailsMobile) {
		this.goodsDetailsMobile = goodsDetailsMobile;
	}

	public String getGoodsIcon() {
		return goodsIcon;
	}

	public void setGoodsIcon(String goodsIcon) {
		this.goodsIcon = goodsIcon;
	}

	public String getIsDetailsBanner() {
		return isDetailsBanner;
	}

	public void setIsDetailsBanner(String isDetailsBanner) {
		this.isDetailsBanner = isDetailsBanner;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getRecommendPicUrl() {
		return recommendPicUrl;
	}

	public void setRecommendPicUrl(String recommendPicUrl) {
		this.recommendPicUrl = recommendPicUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodsClassName() {
		return goodsClassName;
	}

	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}

	public List<GoodsPriceList> getGoodsPriceList() {
		return goodsPriceList;
	}

	public void setGoodsPriceList(List<GoodsPriceList> goodsPriceList) {
		this.goodsPriceList = goodsPriceList;
	}

	public String getServiceOrgRankCode() {
		return serviceOrgRankCode;
	}

	public void setServiceOrgRankCode(String serviceOrgRankCode) {
		this.serviceOrgRankCode = serviceOrgRankCode;
	}

	public String getServiceOrgRankName() {
		return serviceOrgRankName;
	}

	public void setServiceOrgRankName(String serviceOrgRankName) {
		this.serviceOrgRankName = serviceOrgRankName;
	}

	public String getTagMatchedNum() {
		return tagMatchedNum;
	}

	public void setTagMatchedNum(String tagMatchedNum) {
		this.tagMatchedNum = tagMatchedNum;
	}

	public String getServicePackageCode() {
		return servicePackageCode;
	}

	public void setServicePackageCode(String servicePackageCode) {
		this.servicePackageCode = servicePackageCode;
	}

	public String[] getServicePackageCodeArr() {
		return servicePackageCodeArr;
	}

	public void setServicePackageCodeArr(String[] servicePackageCodeArr) {
		this.servicePackageCodeArr = servicePackageCodeArr;
	}

	
	

	
	
}