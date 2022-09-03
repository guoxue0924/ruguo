/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.entity;




import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * RefundListInfoFilterEntity
 * @author wanggang
 * @version 2017-06-16
 */
public class RefundListInfoFilter extends DataEntity<RefundListInfoFilter> {
	
	private static final long serialVersionUID = 1L;
    
	
	private String refundId;      // 退款单号
	private String refundWay;       // 退款方式
	private String refundStatus;       // 退款状态，0失败；1成功
	private Date startRefundTime;       // 开始退款时间
	private Date endRefundTime;       // 结束退款时间
	
	public RefundListInfoFilter() {
		super();
	}
	
	public RefundListInfoFilter(String id){
		super(id);
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(String refundWay) {
		this.refundWay = refundWay;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getStartRefundTime() {
		return startRefundTime;
	}

	public void setStartRefundTime(Date startRefundTime) {
		this.startRefundTime = startRefundTime;
	}

	public Date getEndRefundTime() {
		return endRefundTime;
	}

	public void setEndRefundTime(Date endRefundTime) {
		this.endRefundTime = endRefundTime;
	}
	
    
	

}