/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.entity;





import com.foundation.common.persistence.DataEntity;

/**
 * RefundListInfoResultEntity
 * @author wanggang
 * @version 2017-06-16
 */
public class RefundListInfoResult extends DataEntity<RefundListInfoResult> {
	
	private static final long serialVersionUID = 1L;
    
	private RefundListInfo refundListInfo; // 退款列表信息Entity
	private String refundWay;       // 退款方式
	
	public RefundListInfoResult() {
		super();
	}
	
	public RefundListInfoResult(String id){
		super(id);
	}

	public RefundListInfo getRefundListInfo() {
		return refundListInfo;
	}

	public void setRefundListInfo(RefundListInfo refundListInfo) {
		this.refundListInfo = refundListInfo;
	}

	public String getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(String refundWay) {
		this.refundWay = refundWay;
	}

}