/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goodsclass.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.service.GoodsService;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;

/**
 * GoodsClassBizService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsClassBizService {

	@Autowired
	private GoodsClassService goodsClassService;
	
	@Autowired
	private GoodsService goodsService;

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
		GoodsClassInfo goodsClassInfo = goodsClassService.findById(id);
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
		List<GoodsClassInfo> list = goodsClassService.findByParentCode(parentCode);
		return list;
	}


	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {添加子分类信息}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo void
	 */
	public void save(GoodsClassInfo goodsClassInfo) {
		goodsClassService.save(goodsClassInfo);
	}


	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {保存上移，下移的分类}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo void
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveChange(GoodsClassInfo goodsClassInfo,GoodsClassInfo changeGoodsClassInfo) {
		goodsClassService.save(goodsClassInfo);
		goodsClassService.save(changeGoodsClassInfo);
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
		GoodsClassInfo haveGoodsClassInfo = goodsClassService.findByCodeAndName(goodsClassInfo);
		return haveGoodsClassInfo;
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
		List<GoodsClassInfo> list = goodsClassService.findAll();
		return list;
	}

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {查询分类是否被商品所使用}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	public List<Goods> findGoodsByClassCode(Goods goods) {
		 List<Goods> list = goodsService.findList(goods);
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
		 List<GoodsClassInfo> list = goodsClassService.findUpByCondation(entity);
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
		 List<GoodsClassInfo> list = goodsClassService.findDownByCondation(entity);
			return list;
	}

	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {循环保存置顶的entity}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveTop(GoodsClassInfo entity, List<GoodsClassInfo> listMin) {
		String entityClassSeqNum = entity.getClassSeqNum();
		for (GoodsClassInfo goodsClass:listMin) {
			String SeqNum = goodsClass.getClassSeqNum();
			goodsClass.setClassSeqNum(entityClassSeqNum);
			entityClassSeqNum = SeqNum;
			goodsClassService.save(goodsClass);
		}
		entity.setClassSeqNum(entityClassSeqNum);
		goodsClassService.save(entity);
	}
	
	/**
	 * 
	 * Created on 2017年7月3日 .
	 * <p>
	 * Description {循环保存置底的entity}
	 *
	 * @author guoxue 
	 * @return List<GoodsClassInfo>
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void saveDown(GoodsClassInfo entity, List<GoodsClassInfo> listMin) {
		String entityClassSeqNum = entity.getClassSeqNum();
		for (GoodsClassInfo goodsClass:listMin) {
			String SeqNum = goodsClass.getClassSeqNum();
			goodsClass.setClassSeqNum(entityClassSeqNum);
			entityClassSeqNum = SeqNum;
			goodsClassService.save(goodsClass);
		}
		entity.setClassSeqNum(entityClassSeqNum);
		goodsClassService.save(entity);
	}

	
	
}