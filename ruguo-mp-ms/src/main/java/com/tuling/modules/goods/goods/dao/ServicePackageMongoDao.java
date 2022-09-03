/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.dao;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsPackageTagsFilter;


/**
 * ServicePackageMongoDao接口
 * @author guoxue
 * @version 2017-06-06
 */
@Repository
public class ServicePackageMongoDao {
	
	@Autowired
    private MongoTemplate mongoTemplateCommon;

	
	public List<Map> findAllServicePackage() {
		// 设置查询条件
				DBObject dbObject = new BasicDBObject();
		// 构建查询条件对象
				Query query = new BasicQuery(dbObject, new BasicDBObject());
		List<Map> list = mongoTemplateCommon.find(query, Map.class, "service_package");
		return list;
	}


	public List<Map> findServicePackage(String packageId) {
		// 设置查询条件
		DBObject dbObject = new BasicDBObject();
		dbObject.put("packageId", packageId);
		// 构建查询条件对象
		Query query = new BasicQuery(dbObject, new BasicDBObject());
		List<Map> list = mongoTemplateCommon.find(query, Map.class, "service_package");
		return list;
	}


	public List<Map> findAllTags() {
		// 设置查询条件
		DBObject dbObject = new BasicDBObject();
		// 构建查询条件对象
		Query query = new BasicQuery(dbObject, new BasicDBObject());
		List<Map> list = mongoTemplateCommon.find(query, Map.class, "tagInfo");
		return list;
	}


	public List<Map> findTags(String tagCode) {
		// 设置查询条件
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", tagCode);
		// 构建查询条件对象
		Query query = new BasicQuery(dbObject, new BasicDBObject());
		List<Map> list = mongoTemplateCommon.find(query, Map.class, "tagInfo");
		return list;
	}


	public void insertGoodsInfo(List<GoodsPackageTagsFilter> goodsPackageTagsFilterList) {
		for(GoodsPackageTagsFilter goodsPackageTagsFilter : goodsPackageTagsFilterList){
			mongoTemplateCommon.insert(goodsPackageTagsFilter, "goods_info");
		}
		
	}


	public List<Map> getMongoGoods() {
		// 设置查询条件
				DBObject dbObject = new BasicDBObject();
				// 构建查询条件对象
				Query query = new BasicQuery(dbObject, new BasicDBObject());
				List<Map> list = mongoTemplateCommon.find(query, Map.class, "goods_info");
				return list;
	}


	public void deleteGoodsInfo(Goods goods) {
		// 设置查询条件
				DBObject dbObject = new BasicDBObject();
				dbObject.put("goodsCode", goods.getGoodsCode());
				// 构建查询条件对象
				Query query = new BasicQuery(dbObject, new BasicDBObject());
				mongoTemplateCommon.remove(query, Map.class, "goods_info");
		
	}
}