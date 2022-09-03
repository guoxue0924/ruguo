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

/**
 * GoodsPicListDao接口
 * @author guoxue
 * @version 2017-06-06
 */
@MyBatisDao
public interface GoodsPicListDao extends CrudDao<GoodsPicList> {

	/**
	 * 
	 * Created on 2017年6月15日 .
	 * <p>
	 * Description {获取商品的图片}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPicList>
	 */
	public List<GoodsPicList> findAllGoodsPicList(Goods goods);

	/**
	 * 
	 * Created on 2017年6月16日 .
	 * <p>
	 * Description {删除商品图片}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public void deleteGoodsPicListByGoodsId(Goods goods);
	/**
	 * 
	 * Created on 2017年6月15日 .
	 * <p>
	 * Description {获取商品的图片}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return GoodsPicList
	 */
	public GoodsPicList findGoodsLogo(Goods goodsById);

	public void deletePicByGoodsCode(GoodsPicList goodsPicList);


	
	
}