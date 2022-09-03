/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goodsclass.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.tuling.modules.goods.goodsclass.dao.GoodsClassDao;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;

/**
 * GoodsClassService
 * 
 * @author guoxue
 * @version 2017-06-17
 */
@Service
@Transactional(readOnly = true)
public class GoodsClassService extends CrudService<GoodsClassDao, GoodsClassInfo> {

	@Autowired
	private GoodsClassDao goodsClassDao;

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
	public GoodsClassInfo findById(String id) {
		GoodsClassInfo filter = new GoodsClassInfo();
		filter.setId(id);
		GoodsClassInfo goodsClassInfo = goodsClassDao.findById(filter);
		return goodsClassInfo;
	}

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
	public List<GoodsClassInfo> findByParentCode(String parentCode) {
		GoodsClassInfo filter = new GoodsClassInfo();
		filter.setParentClassCode(parentCode);
		List<GoodsClassInfo> lists = goodsClassDao.findByParentCode(filter);
		return lists;
	}

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
	public GoodsClassInfo findByCodeAndName(GoodsClassInfo goodsClassInfo) {
		GoodsClassInfo haveGoodsClassInfo = goodsClassDao.findByCodeAndName(goodsClassInfo);
		return haveGoodsClassInfo;
	}

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
	public GoodsClassInfo findByCode(GoodsClassInfo classInfo) {
		GoodsClassInfo goodsClassInfo = goodsClassDao.findByCode(classInfo);
		return goodsClassInfo;
	}

	/**
	 * 
	 * Created on 2017年6月28日 .
	 * <p>
	 * Description {获取树的全部节点}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findAll() {
		// TODO Auto-generated method stub
		List<GoodsClassInfo> list = goodsClassDao.findAll();
		return list;
	}

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {通过分类实体查询seqnum小于此分类的分类list}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findUpByCondation(GoodsClassInfo entity) {
		List<GoodsClassInfo> list = goodsClassDao.findUpByCondation(entity);
		return list;
	}

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {通过分类实体查询seqnum大于此分类的分类list}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findDownByCondation(GoodsClassInfo entity) {
		List<GoodsClassInfo> list = goodsClassDao.findDownByCondation(entity);
		return list;
	}

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
	public List<GoodsClassInfo> findClassListByParentCode(GoodsClassInfo goodsClassInfo) {
		List<GoodsClassInfo> list = goodsClassDao.findClassListByParentCode(goodsClassInfo);
		return list;
	}
	
	
}