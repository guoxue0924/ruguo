/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.modules.sys.entity.FileInfo;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.goods.goods.dao.GoodsPriceListDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsFilterClassCodes;
import com.tuling.modules.goods.goods.entity.GoodsPackageGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageList;
import com.tuling.modules.goods.goods.entity.GoodsPackageTagsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPicList;
import com.tuling.modules.goods.goods.entity.GoodsPriceList;
import com.tuling.modules.goods.goods.entity.GoodsRateList;
import com.tuling.modules.goods.goods.entity.GoodsTagsList;
import com.tuling.modules.goods.goods.entity.GoodsTagsListFilter;
import com.tuling.modules.goods.goods.entity.ServicePackage;
import com.tuling.modules.goods.goods.entity.ServicePackageListFilte;
import com.tuling.modules.goods.goods.utils.ReduceHtml2Text;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;
import com.tuling.modules.goods.goodsclass.service.GoodsClassService;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * goodsBizService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
public class GoodsBizService {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsRateListService goodsRateListService;

	@Autowired
	private GoodsPackageListService goodsPackageListService;

	@Autowired
	private GoodsPicListService goodsPicListService;
	
	@Autowired
	private GoodsPriceListService goodsPriceListService;
	
	@Autowired
	private GoodsClassService goodsClassService;
	
	@Autowired
	private GoodsTagsListService goodsTagsListService;

	@Autowired
	private PageHelper<Goods> pageHelper;
	
	@Autowired
	private ServicePackageMongoService servicePackageMongoService;
	
	private List<GoodsClassInfo> newClassCodeList = new ArrayList<GoodsClassInfo>();

	/**
	 * ????????????????????????
	 * @author guoxue 
	 * @date 2017-06-5
	 */
	public PageHelper<Goods> findGoodsList(Page page, GoodsFilter goodsFilter) {
		// ????????????????????????filter?????????set????????????
		GoodsFilterClassCodes goodsFilterClassCodes = new GoodsFilterClassCodes();
		goodsFilterClassCodes.setPage(page);
		goodsFilterClassCodes.setGoodsCode(goodsFilter.getGoodsCode());
		goodsFilterClassCodes.setGoodsName(goodsFilter.getGoodsName());
		goodsFilterClassCodes.setGoodsStatus(goodsFilter.getGoodsStatus());
		newClassCodeList.clear();
		//????????????????????????????????????
		if (!StringUtils.isBlank(goodsFilter.getGoodsClassCode())) {
			List<String> goodsClassCodes = new ArrayList<String>();
			//??????id??????????????????
			GoodsClassInfo goodsClassInfo = goodsClassService.findById(goodsFilter.getGoodsClassCode());
			goodsClassCodes.add(goodsClassInfo.getGoodsClassCode());
			List<GoodsClassInfo> listClass = new ArrayList<GoodsClassInfo>();
			listClass.add(goodsClassInfo);
			List<GoodsClassInfo> goodsClassInfoList = findClassListByParentCode(listClass);
			if (goodsClassInfoList.size() > 0) {
				for (int i = 0; i < goodsClassInfoList.size(); i++) {
					goodsClassCodes.add(goodsClassInfoList.get(i).getGoodsClassCode());
				}
			}
			goodsFilterClassCodes.setGoodsClassCode(goodsClassCodes);
		}
		
		// ????????????
		List<Goods> list = goodsService.findGoodsList(goodsFilterClassCodes);
		List<Goods> newList = new ArrayList<Goods>();
		for(Goods goods : list){
			//??????classCode??????
			GoodsClassInfo goodsClassInfo = new GoodsClassInfo();
			goodsClassInfo.setGoodsClassCode(goods.getGoodsClassCode());
			goods.setGoodsClassName(goodsClassService.findByCode(goodsClassInfo).getGoodsClassName());
			//??????goodsCode????????????list
			List<GoodsPriceList> goodsPriceList = goodsPriceListService.findGoodsPriceListByGoodsCode(goods);
			goods.setGoodsPriceList(goodsPriceList);
			newList.add(goods);
		}
		// ????????????
		pageHelper.setRows(page, newList);

		return pageHelper;

	}
	
	/**
	 * 
	 * Created on 2017???7???4??? .
	 * <p>
	 * Description {????????????????????????????????????}
	 *
	 * @author guoxue 
	 * @param list
	 * @return List<GoodsClassInfo>
	 */
	public List<GoodsClassInfo> findClassListByParentCode(List<GoodsClassInfo> list) {
		List<GoodsClassInfo> listChild =new ArrayList<GoodsClassInfo>();
		if(list.size() > 0){
			for(GoodsClassInfo goodsClassInfo : list){
				listChild = goodsClassService.findClassListByParentCode(goodsClassInfo);
				newClassCodeList.addAll(listChild);
			}
		}
		if(listChild.size() > 0){
			findClassListByParentCode(listChild);
		}
		
		return newClassCodeList;

	}
	

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????id??????GoodsList}
	 *
	 * @author guoxue 
	 * @param idList
	 * @return JSONObject
	 */
	public JSONObject findGoodsListById(List<String> idList) {
		JSONObject result = new JSONObject();

		List<Goods> list = new ArrayList<Goods>();
		for (String id : idList) {
			Goods goods = goodsService.get(id);
			if (goods != null) {
				list.add(goods);
			}
			if (list != null) {
				result.put("goodsList", list);
			}
		}
		return result;

	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????goodsCode???????????????????????????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public List<Goods> findGoodsPackageListGoodsByGoodsCode(Goods goods) {
		//??????goodsCode???????????????????????????
		List<GoodsPackageList> GoodsPackageList = goodsPackageListService.findListByGoodsCode(goods);
		//??????goodsCode????????????
		List<Goods> list = new ArrayList<Goods>();
		for(GoodsPackageList goodsPackageList:GoodsPackageList){
			Goods nGoods = new Goods();
			nGoods.setGoodsCode(goodsPackageList.getPackGoodsCode());
			Goods newGoods = goodsService.findByGoodsCode(nGoods);
			list.add(newGoods);
		}
		
		return list;

	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????goodsCode???????????????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public List<Goods> findGoodsPackageListByGoodsCode(Goods goods) {
		//??????goodsCode??????goodsPackageList
		List<GoodsPackageList> GoodsPackageList = goodsPackageListService.findPackageListByGoodsCode(goods);
		List<Goods> list = new ArrayList<Goods>();
		if(GoodsPackageList != null){
			//??????goodsCode????????????
			for(GoodsPackageList goodsPackageList:GoodsPackageList){
				Goods nGoods = new Goods();
				nGoods.setGoodsCode(goodsPackageList.getGoodsCode());
				Goods newGoods = goodsService.findByGoodsCode(nGoods);
				list.add(newGoods);
			}
		}
		return list;

	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????goodsCode????????????GoodsPackageListGoods??????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public List<GoodsPackageGoodsFilter> findGoodsPackageListGoods(Goods goods) {
		//??????goodsCode???????????????????????????
		List<GoodsPackageList> GoodsPackageList = goodsPackageListService.findListByGoodsCode(goods);
		//??????goodsCode????????????
		List<GoodsPackageGoodsFilter> GoodsPackageGoodsFilterList =  new ArrayList<GoodsPackageGoodsFilter>();
		for(GoodsPackageList goodsPackageList:GoodsPackageList){
			Goods nGoods = new Goods();
			GoodsPackageGoodsFilter goodsPackageGoodsFilter = new GoodsPackageGoodsFilter();
			nGoods.setGoodsCode(goodsPackageList.getPackGoodsCode());
			Goods newGoods = goodsService.findByGoodsCode(nGoods);
			goodsPackageGoodsFilter.setGoodsCode(newGoods.getGoodsCode());
			goodsPackageGoodsFilter.setGoodsName(newGoods.getGoodsName());
			
			//??????goodsCode??????goodspicid
			GoodsPicList goodsPicList = goodsPicListService.findGoodsLogo(newGoods);
			if(goodsPicList != null){
				goodsPackageGoodsFilter.setGoodsPicId(goodsPicList.getGoodsPicUrl());
			}
			GoodsPackageGoodsFilterList.add(goodsPackageGoodsFilter);
		}
		return GoodsPackageGoodsFilterList;

	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param idList
	 * @return JSONObject
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public JSONObject modifyGoodsStatusOn(List<String> idList) {
		JSONObject result = new JSONObject();
		for (String id : idList) {
			result = modifyGoodsStatusOn(id);
			if (CommonConstant.RESULT_SUCCESS.equals(result.getString(CommonConstant.SUCCESS))) {
				result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
			}
		}
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject modifyGoodsStatusOn(String id) {
		JSONObject result = new JSONObject();
		Goods entity = new Goods();
		entity.setId(id);
		entity.setUpdateBy(UserUtils.getUser());
		goodsService.modifyGoodsStatusOn(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param idList
	 * @return JSONObject
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public JSONObject modifyGoodsStatusOff(List<String> idList) {
		JSONObject result = new JSONObject();

		for (String id : idList) {
			result = modifyGoodsStatusOff(id);
			if (CommonConstant.RESULT_SUCCESS.equals(result.getString(CommonConstant.SUCCESS))) {
				result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
			}
		}
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject modifyGoodsStatusOff(String id) {
		JSONObject result = new JSONObject();
		Goods entity = new Goods();
		entity.setId(id);
		entity.setUpdateBy(UserUtils.getUser());
		goodsService.modifyGoodsStatusOff(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param idList
	 * @return JSONObject
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public JSONObject deleteGoods(List<String> idList) {
		JSONObject result = new JSONObject();
		for (String id : idList) {
			result = deleteGoods(id);
			if (CommonConstant.RESULT_SUCCESS.equals(result.getString(CommonConstant.SUCCESS))) {
				result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
			}
			Goods goods = goodsService.get(id);
			//????????????
			GoodsPicList goodsPicList = new GoodsPicList();
			goodsPicList.setGoodsCode(goods.getGoodsCode());
			goodsPicListService.deletePicByGoodsCode(goodsPicList);
			//????????????
			GoodsPriceList goodsPriceList = new GoodsPriceList();
			goodsPriceList.setGoodsCode(goods.getGoodsCode());
			goodsPriceListService.delete(goodsPriceList);
			//????????????
			GoodsTagsList goodsTagsList = new GoodsTagsList();
			goodsTagsList.setGoodsCode(goods.getGoodsCode());
			goodsTagsListService.delete(goodsTagsList);
			/*//??????mongo?????????
			servicePackageMongoService.deleteGoodsInfo(goods);*/
		}
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject deleteGoods(String id) {
		JSONObject result = new JSONObject();
		Goods entity = new Goods();
		entity.setId(id);
		entity.setUpdateBy(UserUtils.getUser());
		goodsService.deleteGoods(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???8??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @return List<Goods>
	 */
	public List<Goods> findGoods() {
		List<Goods> list = goodsService.findGoods();
		return list;
	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????code??????goods}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsCode(Goods goods) {
		Goods haveGoods = goodsService.findByGoodsCode(goods);
		return haveGoods;
	}

	/**
	 * 
	 * Created on 2017???6???22??? .
	 * <p>
	 * Description {??????name??????goods}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsName(Goods goods) {
		Goods haveGoods = goodsService.findByGoodsName(goods);
		return haveGoods;
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {??????goodsCode??????GoodsRateList}
	 *
	 * @author guoxue 
	 * @param goodsCode
	 * @return List<GoodsRateList>
	 */
	public List<GoodsRateList> findAll(GoodsRateList goodsRateList) {
		List<GoodsRateList> list = goodsRateListService.findAll(goodsRateList);
		return list;
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void save(Goods goods, List<String> filesUrl,GoodsPriceList goodsPriceList) {

		//??????????????????
		if(!filesUrl.isEmpty()) {
			goodsPicListService.save(goods, filesUrl);
		}
		//??????????????????
		goodsPriceListService.saveGoodsPriceList(goods,goodsPriceList);
		
		//??????packageId??????packageName
		String[] packageCode = goods.getServicePackageCodeArr();
		String packageName ="";
		String onePackageCode ="";
		for(String packageId :packageCode){
			List<Map> list = servicePackageMongoService.findServicePackage(packageId);
			for(Map map:list){
				packageName = packageName + map.get("packageName").toString() +",";
				onePackageCode = onePackageCode + packageId +",";
			}
		}
		goods.setServicePackageName(packageName);
		goods.setServicePackageCode(onePackageCode);
		//
		goodsService.saveGoods(goods);
	}
	
	public void saveTags(String goodsCode,String goodsTags) {
		//??????goodsCode?????????????????????????????????
				GoodsTagsList newGoodsTagsList = new GoodsTagsList();
				newGoodsTagsList.setGoodsCode(goodsCode);
				List<GoodsTagsList> newgoodsTagsList = goodsTagsListService.getTagsByGoodsCode(newGoodsTagsList);
				if(!newgoodsTagsList.isEmpty()){
					//????????????????????????????????????
					goodsTagsListService.delete(newGoodsTagsList);
				}
				//??????????????????
				if(!StringUtils.isBlank(goodsTags)){
					String[] tagsCode = goodsTags.split(",");
					List<GoodsTagsList> goodsTagsListList = new ArrayList<GoodsTagsList>();
					for(String tagCode:tagsCode){
						GoodsTagsList goodsTagsList = new GoodsTagsList();
						List<Map> list = servicePackageMongoService.findTags(tagCode);
						goodsTagsList.setTagCode(tagCode);
						goodsTagsList.setGoodsCode(goodsCode);
						goodsTagsList.setDataElementCode(list.get(0).get("dataElementCode").toString());
						goodsTagsList.setDataElementValue(list.get(0).get("dataElementValue").toString());
						goodsTagsList.setTagName(list.get(0).get("tagName").toString());
						goodsTagsList.setTagType(list.get(0).get("tagType").toString());
						goodsTagsListList.add(goodsTagsList);
					}
					goodsTagsListService.saveTags(goodsTagsListList);
				}
		
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {???????????????}
	 *
	 * @author guoxue 
	 * @param goodsPackageList void
	 */
	public void save(GoodsPackageList goodsPackageList) {
		goodsPackageListService.save(goodsPackageList);
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {??????id????????????}
	 *
	 * @author guoxue 
	 * @param id
	 * @return Goods
	 */
	public Goods get(String id) {
		return goodsService.get(id);
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {??????id??????????????????}
	 *
	 * @author guoxue 
	 * @param goodsRateList void
	 */
	public void save(GoodsRateList goodsRateList) {
		goodsRateListService.save(goodsRateList);
	}

	/**
	 * 
	 * Created on 2017???6???6??? .
	 * <p>
	 * Description {?????????????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param param
	 * @param request
	 * @return ResponseHelper
	 */
	/*@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public void savePackag(Goods goods, List<GoodsPackageList> list, List<FileInfo> logofile, List<FileInfo> fileList) {

		
		if (!fileList.isEmpty()) {
			goodsPicListService.save(goods, fileList);
		} 
		
		goodsService.save(goods);
		for (int i = 0; i < list.size(); i++) {
			goodsPackageListService.save(list.get(i));
		}
		

	}*/
	
	/**
	 * 
	 * Created on 2017???6???15??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPicList>
	 */
	public GoodsPicList findGoodsLogo(Goods goodsById) {
		GoodsPicList goodsPicList = new GoodsPicList();
		goodsPicList = goodsPicListService.findGoodsLogo(goodsById);
		return goodsPicList;
	}

	/**
	 * 
	 * Created on 2017???6???15??? .
	 * <p>
	 * Description {?????????????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @return List<GoodsPicList>
	 */

	public List<GoodsPicList> findAllGoodsPicList(Goods goods) {
		List<GoodsPicList> list = goodsPicListService.findAllGoodsPicList(goods);
		return list;
	}

	/**
	 * 
	 * Created on 2017???6???16??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public void deleteGoodsPicListByGoodsId(Goods goods) {
		goodsPicListService.deleteGoodsPicListByGoodsId(goods);
	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {???????????????????????????????????????}
	 *
	 * @author yuelingyun 
	 * @param page goodsFilter
	 * @return page
	 */
	public PageHelper<Goods> findGoodsAndPackageList(Page page, GoodsFilter goodsFilter) {

		// ????????????????????????filter?????????set????????????
		goodsFilter.setPage(page);
		// ????????????
		List<Goods> list = goodsService.findGoodsAndPackageList(goodsFilter);
		List<Goods> newList = new ArrayList<Goods>();
		for(Goods goods : list){
			if(StringUtils.isNotBlank(goods.getGoodsType())){
				if(CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(goods.getGoodsType())){
					goods.setGoodsType("??????");
				}else{
					goods.setGoodsType("?????????");
				}
			}
			/*if(StringUtils.isNotBlank(goods.getGoodsValidityUnit())){
				if(CommonConstant.goodsValidityUnit.DICT_GOODS_VALIDITY_UNIT_ZERO.equals(goods.getGoodsValidityUnit())){
					goods.setGoodsValidityUnit("???");
				}else{
					goods.setGoodsValidityUnit("???");
				}
			}*/
			if(StringUtils.isNotBlank(goods.getGoodsStatus())){
				if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals(goods.getGoodsStatus())){
					goods.setGoodsStatus("??????");
				}else{
					goods.setGoodsStatus("??????");
				}
			}
			if(StringUtils.isNotBlank(goods.getGoodsDetails())){
				goods.setGoodsDetails(ReduceHtml2Text.getTextFromHtml(goods.getGoodsDetails()));
			}
			
		}
		// ????????????
		pageHelper.setRows(page, list);

		return pageHelper;

	}

	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {?????????????????????????????????}
	 *
	 * @author yuelingyun 
	 * @param CommonsMultipartFile type userId
	 * @return json
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 * @throws IOException 
	 */
	public JSONObject importGoodsExcel(CommonsMultipartFile file, String type, String userId)
			throws UploadException, IOException, BadHanyuPinyinOutputFormatCombination {
		return goodsService.importGoodsExcel(file, type, userId);
	}

	/**
	 * 
	 * Created on 2017???6???23??? .
	 * <p>
	 * Description {??????Id????????????info}
	 *
	 * @author guoxue 
	 * @param goodsClassId
	 * @return GoodsClassInfo
	 */
	public GoodsClassInfo getGoodsClassCode(String goodsClassId) {
		GoodsClassInfo goodsClassInfo = goodsClassService.findById(goodsClassId);
		return goodsClassInfo;
	}

	/**
	 * 
	 * Created on 2017???6???23??? .
	 * <p>
	 * Description {??????Code???????????????entity}
	 *
	 * @author guoxue 
	 * @param goodsClassCode
	 * @return GoodsClassInfo
	 */
	public GoodsClassInfo getGoodsClassName(String goodsClassCode) {
		GoodsClassInfo classInfo = new GoodsClassInfo();
		classInfo.setGoodsClassCode(goodsClassCode);
		GoodsClassInfo goodsClassInfo = goodsClassService.findByCode(classInfo);
		return goodsClassInfo;
	}

	/**
	 * 
	 * Created on 2017???6???28??? .
	 * <p>
	 * Description {goods???get??????}
	 *
	 * @author guoxue 
	 * @param entity
	 * @return Goods
	 */
	public Goods get(Goods entity) {
		Goods goods = goodsService.get(entity);
		return goods;
	}

	public PageHelper<Goods> getGoodsPackageGoods(Page page, GoodsFilter goodsFilter) {
		// ????????????????????????filter?????????set????????????
				GoodsFilterClassCodes goodsFilterClassCodes = new GoodsFilterClassCodes();
				goodsFilterClassCodes.setPage(page);
				goodsFilterClassCodes.setGoodsCode(goodsFilter.getGoodsCode());
				goodsFilterClassCodes.setGoodsName(goodsFilter.getGoodsName());
				goodsFilterClassCodes.setGoodsStatus(goodsFilter.getGoodsStatus());
				newClassCodeList.clear();
				//????????????????????????????????????
				if (!StringUtils.isBlank(goodsFilter.getGoodsClassCode())) {
					List<String> goodsClassCodes = new ArrayList<String>();
					//??????id??????????????????
					GoodsClassInfo goodsClassInfo = goodsClassService.findById(goodsFilter.getGoodsClassCode());
					goodsClassCodes.add(goodsClassInfo.getGoodsClassCode());
					List<GoodsClassInfo> listClass = new ArrayList<GoodsClassInfo>();
					listClass.add(goodsClassInfo);
					List<GoodsClassInfo> goodsClassInfoList = findClassListByParentCode(listClass);
					if (goodsClassInfoList.size() > 0) {
						for (int i = 0; i < goodsClassInfoList.size(); i++) {
							goodsClassCodes.add(goodsClassInfoList.get(i).getGoodsClassCode());
						}
					}
					goodsFilterClassCodes.setGoodsClassCode(goodsClassCodes);
				}
				
				// ????????????
				List<Goods> list = goodsService.getGoodsPackageGoods(goodsFilterClassCodes);
				
				List<Goods> newList = new ArrayList<Goods>();
				for(Goods goods : list){
					//??????classCode??????
					GoodsClassInfo goodsClassInfo = new GoodsClassInfo();
					goodsClassInfo.setGoodsClassCode(goods.getGoodsClassCode());
					goods.setGoodsClassName(goodsClassService.findByCode(goodsClassInfo).getGoodsClassName());
					newList.add(goods);
				}
				// ????????????
				pageHelper.setRows(page, list);

				return pageHelper;
	}

	public List<GoodsPackageGoodsFilter> getPackageGoods(List<String> idList) {
		List<GoodsPackageGoodsFilter> list = new ArrayList<GoodsPackageGoodsFilter>();
		for (String id : idList) {
			GoodsPackageGoodsFilter goodsPackageGoodsFilter = new GoodsPackageGoodsFilter();
			Goods entity = new Goods();
			entity.setId(id);
			goodsPackageGoodsFilter = goodsService.getPackageGoods(entity);
			//??????goodsCode????????????logo
			Goods goods = new Goods();
			goods.setGoodsCode(goodsPackageGoodsFilter.getGoodsCode());
			GoodsPicList goodsPicList = goodsPicListService.findGoodsLogo(goods);
			if(goodsPicList != null){
				goodsPackageGoodsFilter.setGoodsPicId(goodsPicList.getGoodsPicUrl());
			}
			list.add(goodsPackageGoodsFilter);
		}
		return list;
	}

	public void deleteGoodsPackageGoods(Goods goods) {
		goodsPackageListService.deleteGoodsPackageGoods(goods);
		
	}

	/**
	 * 
	 * Created on 2018???1???27??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param goodsById
	 * @return List<GoodsPriceList>
	 */
	public List<GoodsPriceList> findGoodsPriceListByGoodsCode(Goods goodsById) {
		List<GoodsPriceList> goodsPriceList= goodsPriceListService.findGoodsPriceListByGoodsCode(goodsById);
		return goodsPriceList;
	}

	/**
	 * 
	 * Created on 2018???1???27??? .
	 * <p>
	 * Description {??????????????????id}
	 *
	 * @author guoxue 
	 * @param id void
	 */
	public void deleteGoodsPicListById(GoodsPicList goodsPicList) {
		goodsPicListService.delete(goodsPicList);		
	}

	/**
	 * 
	 * Created on 2018???1???30??? .
	 * <p>
	 * Description {??????mongo?????????????????????}
	 *
	 * @author guoxue 
	 * @return List<Map<String,String>>
	 */
	public List<Map> findAllServicePackage() {
		return servicePackageMongoService.findAllServicePackage();
	}
	/**
	 * 
	 * Created on 2018???1???30??? .
	 * <p>
	 * Description {??????mongo????????????}
	 *
	 * @author guoxue 
	 * @return List<Map>
	 */
	public List<Map> findAllTags() {
		return servicePackageMongoService.findAllTags();
	}

	/**
	 * 
	 * Created on 2018???1???31??? .
	 * <p>
	 * Description {??????goodsCode??????tags}
	 *
	 * @author guoxue 
	 * @param goodsCode
	 * @return List<GoodsTagsList>
	 */
	public List<GoodsTagsList> getTagsByGoodsCode(GoodsTagsList goodsTagsList) {
		List<GoodsTagsList> list = goodsTagsListService.getTagsByGoodsCode(goodsTagsList);
		return list;
	}

	public void insertGoodsInfo(List<Goods> goodsList) {
		List<GoodsPackageTagsFilter> goodsPackageTagsFilterList = new ArrayList<GoodsPackageTagsFilter>();
		for(Goods goods : goodsList){
			//??????tags
			GoodsTagsList newgoodsTagsList = new GoodsTagsList();
			newgoodsTagsList.setGoodsCode(goods.getGoodsCode());
			List<GoodsTagsList> goodsTagsList = goodsTagsListService.getTagsByGoodsCode(newgoodsTagsList);
			
			GoodsPackageTagsFilter goodsPackageTagsFilter = new GoodsPackageTagsFilter();
			goodsPackageTagsFilter.setGoodsCode(goods.getGoodsCode());
			goodsPackageTagsFilter.setTagMatchedNum(goods.getTagMatchedNum());
			
			String[] servicePackage = goods.getServicePackageCode().split(",");
			List<ServicePackageListFilte> servicePackageListFilteList = new ArrayList<ServicePackageListFilte>();
			for(String packageCode : servicePackage){
				ServicePackageListFilte servicePackageListFilte = new ServicePackageListFilte();
				servicePackageListFilte.setServicePackageCode(packageCode);
				servicePackageListFilteList.add(servicePackageListFilte);
				goodsPackageTagsFilter.setServicePackList(servicePackageListFilteList);
				
			}
			List<GoodsTagsListFilter> goodsTagsListFilterList = new ArrayList<GoodsTagsListFilter>();
			for(GoodsTagsList goodsTag : goodsTagsList){
				GoodsTagsListFilter goodsTagsListFilter = new GoodsTagsListFilter();
				goodsTagsListFilter.setTagCode(goodsTag.getTagCode());
				goodsTagsListFilter.setTagType(goodsTag.getTagType());
				goodsTagsListFilter.setDataElementCode(goodsTag.getDataElementCode());
				goodsTagsListFilter.setDataElementValue(goodsTag.getDataElementValue());
				goodsTagsListFilterList.add(goodsTagsListFilter);
				goodsPackageTagsFilter.setTagList(goodsTagsListFilterList);
				
			}
			goodsPackageTagsFilterList.add(goodsPackageTagsFilter);
			
		}
		servicePackageMongoService.insertGoodsInfo(goodsPackageTagsFilterList);
		
	}

	public List<Map> getMongoGoods() {
		return servicePackageMongoService.getMongoGoods();
	}

	public void deleteGoodsInfo(List<Goods> goodsList) {
		for(Goods goods : goodsList){
			servicePackageMongoService.deleteGoodsInfo(goods);
		}
		
	}

	

	



	
}