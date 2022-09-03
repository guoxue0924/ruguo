/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goodsclass.dao;


import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;

/**
 * GoodsClassDao接口
 * @author guoxue
 * @version 2017-06-17
 */
@MyBatisDao
public interface GoodsClassDao extends CrudDao<GoodsClassInfo> {

	/**
	 * 
	 * Created on 2017年6月17日 .
	 * <p>
	 * Description {根据id查询商品分类}
	 *
	 * @author guoxue 
	 * @param id
	 * @return GoodsClassInfo
	 */
	public GoodsClassInfo findById(GoodsClassInfo filter);

	/**
	 * 
	 * Created on 2017年6月17日 .
	 * <p>
	 * Description {根据父级分类code查询商品分类}
	 *
	 * @author guoxue 
	 * @param parentCode
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findByParentCode(GoodsClassInfo filter);

	/**
	 * 
	 * Created on 2017年6月22日 .
	 * <p>
	 * Description {查找分类名相同的实体}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @return GoodsClassInfo
	 */
	public GoodsClassInfo findByCodeAndName(GoodsClassInfo goodsClassInfo);

	/**
	 * 
	 * Created on 2017年6月23日 .
	 * <p>
	 * Description {通过Code查找分类的entity}
	 *
	 * @author guoxue 
	 * @param classInfo
	 * @return GoodsClassInfo
	 */
	public GoodsClassInfo findByCode(GoodsClassInfo classInfo);

	/**
	 * 
	 * Created on 2017年6月28日 .
	 * <p>
	 * Description {获取树的全部节点}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findAll();

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {通过分类实体查询seqnum小于此分类的分类list}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findUpByCondation(GoodsClassInfo entity);

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {通过分类实体查询seqnum大于此分类的分类list}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findDownByCondation(GoodsClassInfo entity);

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {通过ParentCode查找classEntity}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findClassListByParentCode(GoodsClassInfo goodsClassInfo);
	
	

}