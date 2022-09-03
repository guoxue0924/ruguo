/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.SqlEntity;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsFilterClassCodes;
import com.tuling.modules.goods.goods.entity.GoodsPackageGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageListGoodsFilter;

/**
 * GoodsDAO接口
 * @author guoxue
 * @version 2017-06-05
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {
	
	/**
	 * 分页查询用户信息
	 * @author guoxue 
	 * @date 2017-06-05
	 */
	public List<Goods> findGoodsList(GoodsFilterClassCodes goodsFilterClassCodes);

	/**
	 * 
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量设置商品上架}
	 *
	 * @author guoxue 
	 * @param entity void
	 */
	public void modifyGoodsStatusOn(Goods entity);

	/**
	 * 
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量设置商品下架}
	 *
	 * @author guoxue 
	 * @param entity void
	 */
	public void modifyGoodsStatusOff(Goods entity);
	
	/**
	 * 
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description {批量删除商品}
	 *
	 * @author guoxue 
	 * @param entity void
	 */
	public void deleteGoods(Goods entity);

	/**
	 * 
	 * Created on 2017年6月8日 .
	 * <p>
	 * Description {获取商品列表}
	 *
	 * @author guoxue 
	 * @return List<Goods>
	 */
	public List<Goods> findGoods();
	
	
	

	/**
	 * 
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description {通过code查询goods}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsCode(Goods goods);

	/**
	 * 
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description {通过name查询goods}
	 *
	 * @author yuelingyun
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsName(Goods goods);
	/**
	 * 
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description 插入商品数据
	 *
	 * @author yuelingyun 
	 * @param goods
	 * @return void
	 */
	public void insertGoodsSql(SqlEntity sqlEntity);
	
	/**
	 * 分页查询用户信息
	 * @author guoxue 
	 * @date 2017-06-05
	 */
	public List<Goods> findGoodsAndPackageList(GoodsFilter goodsFilter);
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品数量}
	 *
	 * @author guoxue 
	 * @return int
	 */
	public int getGoodsCount();

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品上架数量}
	 *
	 * @author guoxue 
	 * @return int
	 */
	public int getGoodsOnShelvesCount();

	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {获取商品下架数量}
	 *
	 * @author guoxue 
	 * @return int
	 */
	public int getGoodsOffShelvesCount();

	public List<Goods> getGoodsPackageGoods(GoodsFilterClassCodes goodsFilterClassCodes);

	public GoodsPackageGoodsFilter getPackageGoods(Goods goods);

}