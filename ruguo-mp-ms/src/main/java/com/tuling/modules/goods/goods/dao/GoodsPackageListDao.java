/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;


import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.SqlEntity;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPackageList;

/**
 * GoodsPackageListDao接口
 * @author guoxue
 * @version 2017-06-06
 */
@MyBatisDao
public interface GoodsPackageListDao extends CrudDao<GoodsPackageList> {
	
	/**
	 * 
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description 插入商品数据
	 *
	 * @author yuelingyun 
	 * @param goods
	 * @return Goods
	 */
	public void insertGoodsPackageSql(SqlEntity sqlEntity);
	
	/**
	 * 
	 * Created on 2017年9月25日 .
	 * <p>
	 * Description {删除产品包商品}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public void deleteGoodsPackageGoods(Goods goods);

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
	public List<GoodsPackageList> findListByGoodsCode(Goods goods);

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
	public List<GoodsPackageList> findPackageListByGoodsCode(Goods goods);

}