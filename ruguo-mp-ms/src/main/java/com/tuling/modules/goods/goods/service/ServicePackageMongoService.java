/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.tuling.modules.goods.goods.dao.GoodsPackageListDao;
import com.tuling.modules.goods.goods.dao.ServicePackageMongoDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPackageList;
import com.tuling.modules.goods.goods.entity.GoodsPackageTagsFilter;

/**
 * ServicePackageMongoService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class ServicePackageMongoService {

	@Autowired
	private ServicePackageMongoDao servicePackageMongoDao;

	public List<Map> findAllServicePackage() {
		return servicePackageMongoDao.findAllServicePackage();
	}

	public List<Map> findServicePackage(String packageId) {
		return servicePackageMongoDao.findServicePackage(packageId);
	}

	public List<Map> findAllTags() {
		return servicePackageMongoDao.findAllTags();
	}

	public List<Map> findTags(String tagCode) {
		return servicePackageMongoDao.findTags(tagCode);
	}

	public List<Map> getMongoGoods() {
		// TODO Auto-generated method stub
		return servicePackageMongoDao.getMongoGoods();
	}

	public void insertGoodsInfo(List<GoodsPackageTagsFilter> goodsPackageTagsFilterList) {
		servicePackageMongoDao.insertGoodsInfo(goodsPackageTagsFilterList);
		
	}

	public void deleteGoodsInfo(Goods goods) {
		servicePackageMongoDao.deleteGoodsInfo(goods);
	}
	


	
	
}