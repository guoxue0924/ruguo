/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.goods.goods.entity.GoodsRateList;

/**
 * GoodsRateListDao接口
 * @author guoxue
 * @version 2017-06-05
 */
@MyBatisDao
public interface GoodsRateListDao extends CrudDao<GoodsRateList> {

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
	public List<GoodsRateList> findAll(GoodsRateList goodsRateList);
	
	

}