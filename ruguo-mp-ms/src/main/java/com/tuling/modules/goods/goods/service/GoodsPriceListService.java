/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.foundation.common.utils.IdGen;
import com.foundation.modules.sys.entity.FileInfo;
import com.tuling.modules.goods.goods.dao.GoodsPicListDao;
import com.tuling.modules.goods.goods.dao.GoodsPriceListDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPicList;
import com.tuling.modules.goods.goods.entity.GoodsPriceList;

/**
 * GoodsPicListService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsPriceListService extends CrudService<GoodsPriceListDao, GoodsPriceList> {
	
	@Autowired
	private GoodsPriceListDao goodsPriceListDao;
	

	/**
	 * 
	 * Created on 2018年1月25日 .
	 * <p>
	 * Description {描述}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPriceList>
	 */
	public List<GoodsPriceList> findGoodsPriceListByGoodsCode(Goods goods) {
		List<GoodsPriceList> goodsPriceList = goodsPriceListDao.findGoodsPriceListByGoodsCode(goods);
		return goodsPriceList;
	}

	/**
	 * 
	 * Created on 2018年1月25日 .
	 * <p>
	 * Description {保存商品价格}
	 *
	 * @author guoxue 
	 * @param goodsPriceList void
	 */
	public void saveGoodsPriceList(Goods goods,GoodsPriceList goodsPriceList) {
		//新产品价格保存
		if(goodsPriceList.getGoodsPriceCode()== null){
			String[] goodsPrice = goodsPriceList.getGoodsPrice().split(",");
			String[] goodsValidityNum = goodsPriceList.getGoodsValidityNum().split(",");
			
			for(int i=0;i<goodsPrice.length;i++){
				GoodsPriceList newGoodsPriceList = new GoodsPriceList();
				newGoodsPriceList.setGoodsPriceCode(IdGen.uuid().substring(0, 10));
				newGoodsPriceList.setGoodsCode(goods.getGoodsCode());
				newGoodsPriceList.setGoodsType("1");
				newGoodsPriceList.setGoodsValidityNum(goodsValidityNum[i]);
				newGoodsPriceList.setGoodsValidityUnit("1");
				newGoodsPriceList.setGoodsPrice(goodsPrice[i]);
				if(i==0){
					newGoodsPriceList.setIsDefault("1");
				}else{
					newGoodsPriceList.setIsDefault("0");
				}
				
				super.save(newGoodsPriceList);
			}
			
		}else{
			//老产品价格修改
			String[] goodsPriceCode = goodsPriceList.getGoodsPriceCode().split(",");
			String[] goodsPrice = goodsPriceList.getGoodsPrice().split(",");
			String[] goodsValidityNum = goodsPriceList.getGoodsValidityNum().split(",");
			for(int i=0;i<goodsPriceCode.length;i++){
				GoodsPriceList newGoodsPriceList = new GoodsPriceList();
				newGoodsPriceList.setGoodsPriceCode(goodsPriceCode[i]);
				newGoodsPriceList.setId(goodsPriceListDao.get(newGoodsPriceList).getId());
				newGoodsPriceList.setGoodsPrice(goodsPrice[i]);
				newGoodsPriceList.setGoodsValidityNum(goodsValidityNum[i]);
				super.save(newGoodsPriceList);
			}
			
			
		}
	
		
		
	}

	
	
	
}