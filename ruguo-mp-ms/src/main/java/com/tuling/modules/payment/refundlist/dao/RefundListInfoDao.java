/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.payment.refundlist.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.order.orderquery.entity.RefundList;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoFilter;
import com.tuling.modules.payment.refundlist.entity.RefundListInfoResult;







/**
 * 支付列表信息DAO接口
 * @author wanggang
 * @version 2017-06-16
 */
@MyBatisDao
public interface RefundListInfoDao extends CrudDao<RefundListInfoResult> {
	
	/**
	 * 显示退款信息
	 * @author wanggang
	 * @date 2017-06-16
	 */
	public List<RefundListInfoResult> queryRefundListInfoResultList(RefundListInfoFilter refundListInfoFilter);
	
	/**
	 * 
	 * Created on 2017年6月30日 .
	 * <p>
	 * Description {查询退款列表信息}
	 *
	 * @author yuelingyun 
	 * @param refundListInfo
	 * @return refundListInfo
	 */
	public RefundListInfoResult queryRefundListInfo(RefundList refundList);
	
	/**
	 * 
	 * Created on 2017年6月30日 .
	 * <p>
	 * Description {插入退款信息}
	 *
	 * @author yuelingyun 
	 * @param refundListInfo
	 * @return void
	 */
	public void insertRefundListInfo(RefundList refundList);
	
	/**
	 * 
	 * Created on 2017年6月30日 .
	 * <p>
	 * Description {更新退款信息}
	 *
	 * @author yuelingyun 
	 * @param refundListInfo
	 * @return void
	 */
	public void updateRefundListInfo(RefundList refundList);
		
}