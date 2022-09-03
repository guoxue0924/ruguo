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
import com.tuling.modules.goods.goods.dao.GoodsDao;
import com.tuling.modules.goods.goods.dao.GoodsPicListDao;
import com.tuling.modules.goods.goods.dao.GoodsPriceListDao;
import com.tuling.modules.goods.goods.dao.GoodsTagsListDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPicList;
import com.tuling.modules.goods.goods.entity.GoodsPriceList;
import com.tuling.modules.goods.goods.entity.GoodsTagsList;

/**
 * GoodsPicListService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
public class GoodsTagsListService extends CrudService<GoodsTagsListDao, GoodsTagsList> {
	
	@Autowired
	private GoodsTagsListDao goodsTagsListDao;

	public void saveTags(List<GoodsTagsList> goodsTagsListList) {
		for(GoodsTagsList goodsTagsList:goodsTagsListList){
			super.save(goodsTagsList);
		}
		
	}

	public  List<GoodsTagsList> getTagsByGoodsCode(GoodsTagsList goodsTagsList) {
		List<GoodsTagsList> list = goodsTagsListDao.getTagsByGoodsCode(goodsTagsList);
		return list;
	}
	

	
	

	

	
	
	
}