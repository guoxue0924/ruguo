/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;


import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.FileInfo;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPicList;
import com.tuling.modules.goods.goods.entity.GoodsTagsList;

/**
 * GoodsTagsListDao接口
 * @author guoxue
 * @version 2017-06-06
 */
@MyBatisDao
public interface GoodsTagsListDao extends CrudDao<GoodsTagsList> {
	
	/**
	 * 
	 * Created on 2018年1月31日 .
	 * <p>
	 * Description {通过goodsCode查找tags}
	 *
	 * @author guoxue 
	 * @param goodsCode
	 * @return List<GoodsTagsList>
	 */
	public List<GoodsTagsList> getTagsByGoodsCode(GoodsTagsList goodsTagsList);

	

	
	
}