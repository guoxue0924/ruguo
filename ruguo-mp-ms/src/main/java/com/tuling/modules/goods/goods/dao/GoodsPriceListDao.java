/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;


import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPriceList;

/**
 * GoodsPriceListDao接口
 * @author guoxue
 * @version 2018-01-24
 */
@MyBatisDao
public interface GoodsPriceListDao extends CrudDao<GoodsPriceList> {

	/**
	 * 
	 * Created on 2017年6月15日 .
	 * <p>
	 * Description {获取商品的价格列表}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPriceList>
	 */
	public List<GoodsPriceList> findGoodsPriceListByGoodsCode(Goods goods);

	
	
	
}