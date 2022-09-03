package com.tuling.modules.service.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SysUserService;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.order.orderquery.entity.Order;
import com.tuling.modules.order.orderquery.entity.OrderAndRefundFilter;
import com.tuling.modules.order.orderquery.entity.OrderFilter;
import com.tuling.modules.order.orderquery.entity.OrderGoods;
import com.tuling.modules.order.orderquery.entity.OrderRefund;
import com.tuling.modules.order.orderquery.entity.RefundList;
import com.tuling.modules.service.service.entity.MyServiceInfo;
import com.tuling.modules.service.service.entity.MyServiceInfoFilter;
import com.tuling.modules.service.service.entity.MyServiceInfoParameter;

/**
 * MyServicesBizService
 * 
 * @author guoxue
 * @version 2017-09-26
 */
@Service
@Transactional(readOnly = true)
public class MyServicesBizService {

	@Autowired
	private MyServicesService myServicesService;
	
	@Autowired
	private PageHelper<MyServiceInfoFilter> pageHelper;
	
	/**
	 * 
	 * Created on 2017年9月26日 .
	 * <p>
	 * Description {获取}
	 *
	 * @author guoxue 
	 * @param entity
	 * @return MyServiceInfo
	 */
	public MyServiceInfo get(MyServiceInfo entity) {
		MyServiceInfo myServiceInfo = myServicesService.get(entity);
		return myServiceInfo;
	}

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取服务列表}
	 *
	 * @author guoxue
	 * @param page
	 * @ruturn page
	 */
	public PageHelper<MyServiceInfoFilter> getServiceList(Page page, MyServiceInfoParameter myServiceInfoParameter) {

		// 分页查询，需要在filter实体中set分页信息
		myServiceInfoParameter.setPage(page);
		// 查询数据
		List<MyServiceInfoFilter> list = myServicesService.getServiceList(myServiceInfoParameter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;

	}

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
	public MyServiceInfoFilter getMyServiceInfoDetail(MyServiceInfoFilter myServiceInfoFilter) {
		MyServiceInfoFilter myServiceInfo = myServicesService.getMyServiceInfoDetail(myServiceInfoFilter);
		return myServiceInfo;
	}

	
	
	

	

	
}