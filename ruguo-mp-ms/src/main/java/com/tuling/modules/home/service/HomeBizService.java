/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.home.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuling.modules.home.entity.Home;
import com.tuling.modules.home.entity.HomeOrderFilter;

/**
 * HomeBizService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class HomeBizService {

	@Autowired
	private HomeService homeService;
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入后台管理平台首页准备数据}
	 *
	 * @author guoxue 
	 * @return Home
	 */
	public Home getHomePageContent() {
		Home home = new Home();
		
		//获取订单相关信息
		for(int i = 0 ; i <= 7 ; i++){
			DecimalFormat df = new DecimalFormat("###,###,##0.00"); 
			if(i == 0){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setNoPayCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setNoPaySum(df.format(sum));
				}else{
					home.setNoPayCount("0");
					home.setNoPaySum("0。00");
				}
				
				
			}else if(i == 1){
				
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setPayCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setPaySum(df.format(sum));
				}else{
					home.setPayCount("0");
					home.setPaySum("0.00");
				}
			}else if(i == 7){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setOrderCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setOrderSum(df.format(sum));
				}else{
					home.setOrderCount("0");
					home.setOrderSum("0.00");
				}
				
				
			}else if(i == 2){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setRefundApplicationCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setRefundApplicationSum(df.format(sum));
				}else{
					home.setRefundApplicationCount("0");
					home.setRefundApplicationSum("0.00");
				}
				
			}else if(i == 3){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setRefundingCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setRefundingSum(df.format(sum));
				}else{
					home.setRefundingCount("0");
					home.setRefundingSum("0.00");
				}
				
			}else if(i == 4){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setRefundedCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setRefundedSum(df.format(sum));
				}else{
					home.setRefundedCount("0");
					home.setRefundedSum("0.00");
				}
				
			}else if(i == 5){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setCancelCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setCancelSum(df.format(sum));
				}else{
					home.setCancelCount("0");
					home.setCancelSum("0.00");
				}
				
			}else if(i == 6){
				HomeOrderFilter OrderFilter = homeService.getOrderContent(i);
				if(OrderFilter != null){
					home.setClosedCount(OrderFilter.getOrderCount());
					double sum = 0;
					if(OrderFilter.getPriceCount() != null){
						sum = Double.parseDouble(OrderFilter.getPriceCount());
					}
					home.setClosedSum(df.format(sum));
				}else{
					home.setClosedCount("0");
					home.setClosedSum("0。00");
				}
				
			}
		}
		//获取商品相关信息
		int goodsCount = homeService.getGoodsCount();
		int goodsOnShelvesCount = homeService.getGoodsOnShelvesCount();
		int goodsOffShelvesCount = homeService.getGoodsOffShelvesCount();
		home.setGoodsCount(goodsCount+"");
		home.setGoodsOnShelvesCount(goodsOnShelvesCount+"");
		home.setGoodsOffShelvesCount(goodsOffShelvesCount+"");
		//获取用户相关信息
		int UserCount = homeService.getMemberCount();
		home.setUserCount(UserCount+"");
		home.setIdConfirmUserCount("198210");
		home.setIdUnconfirmUserCount("1790");
		return home;
	}
	
	

	
}