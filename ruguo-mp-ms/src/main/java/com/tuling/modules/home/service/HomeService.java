/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.tuling.modules.goods.goods.dao.GoodsDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.home.entity.HomeOrderFilter;
import com.tuling.modules.member.memberquery.dao.MemberBasicInfoDao;
import com.tuling.modules.order.orderquery.dao.OrderQueryDao;
import com.tuling.modules.order.orderquery.entity.Order;

/**
 * goodsService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class HomeService extends CrudService<GoodsDao, Goods> {

	@Autowired
	private OrderQueryDao orderQueryDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private MemberBasicInfoDao memberBasicInfoDao;
	
	
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取首页订单相关数据}
	 *
	 * @author guoxue 
	 * @param i
	 * @return HomeOrderFilter
	 */
	public HomeOrderFilter getOrderContent(int i) {
		String orderStatus = i+"";
		Order order = new Order();
		if(i == 7){
			order.setOrderStatus("");
		}else{
			order.setOrderStatus(orderStatus);
		}
		
		HomeOrderFilter orderFilter = orderQueryDao.getOrderContent(order);
		
		return orderFilter;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品数量}
	 *
	 * @author guoxue
	 * @return int
	 */
	public int getGoodsCount() {
		int goodsCount = goodsDao.getGoodsCount();
		return goodsCount;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品上架数量}
	 *
	 * @author guoxue
	 * @return int
	 */
	public int getGoodsOnShelvesCount() {
		int goodsOnShelvesCount = goodsDao.getGoodsOnShelvesCount();
		return goodsOnShelvesCount;
	}

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品下架数量}
	 *
	 * @author guoxue
	 * @return int
	 */
	public int getGoodsOffShelvesCount() {
		int goodsOffShelvesCount = goodsDao.getGoodsOffShelvesCount();
		return goodsOffShelvesCount;
	}
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取会员数量}
	 *
	 * @author guoxue 
	 * @return int
	 */
	public int getMemberCount() {
		int memberCount = memberBasicInfoDao.getMemberCount();
		return memberCount;
	}
	
}