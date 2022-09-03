/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.entity.FileInfo;
import com.tuling.modules.goods.goods.dao.GoodsPicListDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPicList;

/**
 * GoodsPicListService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsPicListService extends CrudService<GoodsPicListDao, GoodsPicList> {
	
	@Autowired
	private GoodsPicListDao goodsPicListDao;

	/**
	 * 
	 * Created on 2017年6月16日 .
	 * <p>
	 * Description {保存商品logo图片}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param fileList 
	 */
	/*public void saveLogoPic(Goods goods, List<FileInfo> fileList) {
		int count = 0;
		GoodsPicList goodsLogo = goodsPicListDao.findGoodsLogo(goods);
		if(goodsLogo == null){
			GoodsPicList goodsPicList = new GoodsPicList();
			goodsPicList.setGoodsPicId(fileList.get(0).getId());
			goodsPicList.setGoodsPicSeq((++count)+"");
			goodsPicList.setGoodsPicFlag("1");
			goodsPicList.setGoodsCode(goods.getGoodsCode());
			super.save(goodsPicList);
		}else{
			GoodsPicList goodsPicList = new GoodsPicList();
			goodsPicList.setId(goodsLogo.getId());
			goodsPicList.setGoodsPicId(fileList.get(0).getId());
			goodsPicList.setGoodsPicSeq((++count)+"");
			goodsPicList.setGoodsPicFlag("1");
			goodsPicList.setGoodsCode(goods.getGoodsCode());
			super.save(goodsPicList);
		}
		
	}*/
	/**
	 * 
	 * Created on 2017年6月16日 .
	 * <p>
	 * Description {保存商品图片}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param fileList 
	 */
	public void save(Goods goods, List<String> filesUrl) {
		int count = 0;
		List<GoodsPicList> list = goodsPicListDao.findAllGoodsPicList(goods);
		if(list.size()>0){
			for(int i = 0;i<list.size();i++){
				if(count < Integer.parseInt(list.get(i).getGoodsPicSeq())){
					count = Integer.parseInt(list.get(i).getGoodsPicSeq());
				}
			}
		}
		for (String fileUrl : filesUrl) {
			GoodsPicList goodsPicList = new GoodsPicList();
			goodsPicList.setGoodsPicUrl("http://10.101.22.250:8899"+fileUrl);
			goodsPicList.setGoodsPicSeq((++count)+"");
			goodsPicList.setGoodsCode(goods.getGoodsCode());
			super.save(goodsPicList);
		}
	}
	
	/**
	 * 
	 * Created on 2017年6月15日 .
	 * <p>
	 * Description {获取商品的封面图片}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPicList>
	 */
	public GoodsPicList findGoodsLogo(Goods goodsById) {
		GoodsPicList goodsPicList =new GoodsPicList();
		goodsPicList = goodsPicListDao.findGoodsLogo(goodsById);
		return goodsPicList;
	}

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
	public List<GoodsPicList> findAllGoodsPicList(Goods goods) {
		List<GoodsPicList> list = goodsPicListDao.findAllGoodsPicList(goods);
		return list;
	}

	/**
	 * 
	 * Created on 2017年6月16日 .
	 * <p>
	 * Description {删除商品图片}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public void deleteGoodsPicListByGoodsId(Goods goods) {
		goodsPicListDao.deleteGoodsPicListByGoodsId(goods);
	}

	public void deletePicByGoodsCode(GoodsPicList goodsPicList) {
		goodsPicListDao.deletePicByGoodsCode(goodsPicList);
		
	}
	
	
	
}