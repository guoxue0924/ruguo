package com.tuling.modules.service.service.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.home.entity.HomeOrderFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.order.orderquery.entity.MyOrderFilter;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.service.service.entity.MyServiceInfo;
import com.tuling.modules.service.service.entity.MyServiceInfoFilter;
import com.tuling.modules.service.service.entity.MyServiceInfoParameter;

/**
 * OrderQueryDAO接口
 * @author yuelingyun
 * @version 2017-06-19
 */
@MyBatisDao
public interface MyServicesDao extends CrudDao<MyServiceInfo> {

	/**
	 * 
	 * Created on 2017年9月27日 .
	 * <p>
	 * Description {获取服务列表}
	 *
	 * @author guoxue 
	 * @param myServiceInfoFilter
	 * @return List<MyServiceInfoFilter>
	 */
	public List<MyServiceInfoFilter> getServiceList(MyServiceInfoParameter myServiceInfoParameter);
	
	/**
	 * 
	 * Created on 2017年9月27日 .
	 * <p>
	 * Description {获取服务列表}
	 *
	 * @author guoxue 
	 * @param myServiceInfoFilter
	 * @return List<MyServiceInfoFilter>
	 */
	public List<MyServiceInfoFilter> getServiceListT(MyServiceInfoParameter myServiceInfoParameter);
	
	/**
	 * 
	 * Created on 2017年9月28日 .
	 * <p>
	 * Description {获取服务详情}
	 *
	 * @author guoxue 
	 * @param myServiceInfoFilter
	 * @return MyServiceInfoFilter
	 */
	public MyServiceInfoFilter getMyServiceInfoDetail(MyServiceInfoFilter myServiceInfoFilter);

	
	
	

}