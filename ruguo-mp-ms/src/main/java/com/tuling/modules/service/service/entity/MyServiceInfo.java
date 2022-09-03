package com.tuling.modules.service.service.entity;



import com.foundation.common.persistence.DataEntity;

/**
 * MyServiceInfoEntity
 * @author guoxue
 * @version 2017-09-26
 */
public class MyServiceInfo extends DataEntity<MyServiceInfo> {
	
	private static final long serialVersionUID = 1L;
	private String serviceCode;						// 服务编码
	private String memberCode;						// 会员编码
	private String orderCode;						// 订单编码
	private String productCode;						// 产品包编码，其实就是商品类型为产品包的商品编码；汝果不是产品包则为空
	private String productName;						// 产品包名称，其实就是商品类型为产品包的商品名称；如果不是产品包则为空
	private String goodsCode;					// 产品包中商品的编码
	private String goodsName;						// 产品包中商品的名称
	private String relaPerCode;						// 服务对象编码
	private String executeStatus;						// 服务当前执行状态：	1 邮寄样本采集器、	2 填写健康问卷、	3 补充电子病历、	4 检测样本合格率、	5 样本检测、	6 出具基因报告、	7 出局个性化报告、	8 服务完成
	private String serviceCycle;                        //服务周期
	private String serviceStartTime;                   //购买服务时间
	private String serviceEndTime;                    //服务结束时间
	
	public MyServiceInfo() {
		super();
	}

	public MyServiceInfo(String id){
		super(id);
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getRelaPerCode() {
		return relaPerCode;
	}

	public void setRelaPerCode(String relaPerCode) {
		this.relaPerCode = relaPerCode;
	}

	public String getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getServiceCycle() {
		return serviceCycle;
	}

	public void setServiceCycle(String serviceCycle) {
		this.serviceCycle = serviceCycle;
	}

	public String getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(String serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public String getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	
}