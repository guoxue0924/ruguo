package com.tuling.modules.order.orderquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.service.CrudService;
import com.tuling.modules.order.orderquery.dao.OrderRefundQueryDao;
import com.tuling.modules.order.orderquery.entity.OrderRefund;
import com.tuling.modules.order.orderquery.entity.RefundList;
import com.tuling.modules.payment.refundlist.dao.RefundListInfoDao;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoResult;

/**
 * orderRefundQueryService
 * 
 * @author yuelinyun
 * @version 2017-06-19
 */
@Service
public class RefundListInfoQueryService extends CrudService<OrderRefundQueryDao, OrderRefund> {
	
	@Autowired
	private RefundListInfoDao refundListInfoDao;
	
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存退款信息}
	 *
	 * @author yuelingyun
	 * @param refundListInfo
	 * @return void
	 */
	public void saveRefundListInfo(RefundList entity) {
		RefundListInfoResult refundListInfoResult = refundListInfoDao.queryRefundListInfo(entity);
		if(refundListInfoResult != null){
			entity.preUpdate();
			entity.setRefundTime(entity.getUpdateTime());
			refundListInfoDao.updateRefundListInfo(entity);
		}else{
			entity.preInsert();
			entity.setRefundTime(entity.getCreateTime());
			refundListInfoDao.insertRefundListInfo(entity);
		}
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存退款信息}
	 *
	 * @author yuelingyun
	 * @param OrderRefund
	 * @return void
	 */
	public RefundListInfoResult queryRefundListInfo(RefundList entity) {
		RefundListInfoResult refundListInfoResult = refundListInfoDao.queryRefundListInfo(entity);
		return refundListInfoResult;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {保存退款信息}
	 *
	 * @author yuelingyun
	 * @param OrderRefund
	 * @return void
	 */
	public void insertRefundListInfo(RefundList refundList) {
		refundListInfoDao.insertRefundListInfo(refundList);
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {更新退款信息}
	 *
	 * @author yuelingyun
	 * @param OrderRefund
	 * @return void
	 */
	public void updateRefundListInfo(RefundList refundList) {
		refundListInfoDao.updateRefundListInfo(refundList);
	}
	
}