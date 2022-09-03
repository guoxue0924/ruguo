/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.tuling.modules.goods.goods.dao.GoodsPackageListDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPackageList;

/**
 * GoodsPackageListService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsPackageListService extends CrudService<GoodsPackageListDao, GoodsPackageList> {

	@Autowired
	private GoodsPackageListDao goodsPackageListDao;
	/**
	 * 
	 * Created on 2017年11月30日 .
	 * <p>
	 * Description {删除产品包商品}
	 *
	 * @author guxoue 
	 * @param goods void
	 */
	public void deleteGoodsPackageGoods(Goods goods) {
		goodsPackageListDao.deleteGoodsPackageGoods(goods);
		
	}

	/**
	 * 
	 * Created on 2017年11月30日 .
	 * <p>
	 * Description {查询产品包商品}
	 *
	 * @author guxoue 
	 * @param goods
	 * @return List<GoodsPackageList>
	 */
	public List<GoodsPackageList> findListByGoodsCode(Goods goods) {
		List<GoodsPackageList> list = goodsPackageListDao.findListByGoodsCode(goods);
		return list;
	}

	/**
	 * 
	 * Created on 2017年11月30日 .
	 * <p>
	 * Description {查询商品所在产品包}
	 *
	 * @author guxoue 
	 * @param goods
	 * @return List<GoodsPackageList>
	 */
	public List<com.tuling.modules.goods.goods.entity.GoodsPackageList> findPackageListByGoodsCode(Goods goods) {
		List<GoodsPackageList> list = goodsPackageListDao.findPackageListByGoodsCode(goods);
		return list;
	}


	
	
}