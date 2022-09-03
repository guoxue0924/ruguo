/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foundation.common.service.CrudService;
import com.tuling.modules.goods.goods.dao.GoodsRateListDao;
import com.tuling.modules.goods.goods.entity.GoodsRateList;

/**
 * GoodsRateListService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsRateListService extends CrudService<GoodsRateListDao, GoodsRateList> {

	@Autowired
	private GoodsRateListDao goodsRateListDao;
	
	/**
	 * 
	 * Created on 2017年6月13日 .
	 * <p>
	 * Description {通过goodsCode查询GoodsRateList}
	 *
	 * @author guoxue 
	 * @param goodsCode
	 * @return List<GoodsRateList>
	 */
	public List<GoodsRateList> findAll(GoodsRateList goodsRateList) {
		List<GoodsRateList> list = goodsRateListDao.findAll(goodsRateList);
		return list;
	}



	
	
}