package com.tuling.modules.goods.goods.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.FileInfo;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UploadHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mysql.fabric.xmlrpc.base.Array;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageList;
import com.tuling.modules.goods.goods.entity.GoodsPackageListGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageTagsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPicList;
import com.tuling.modules.goods.goods.entity.GoodsPriceList;
import com.tuling.modules.goods.goods.entity.GoodsRateList;
import com.tuling.modules.goods.goods.entity.GoodsRateListFilter;
import com.tuling.modules.goods.goods.entity.GoodsTagsList;
import com.tuling.modules.goods.goods.entity.GoodsTagsResult;
import com.tuling.modules.goods.goods.entity.ServicePackage;
import com.tuling.modules.goods.goods.service.GoodsBizService;
import com.tuling.modules.goods.goods.utils.CheckExcelUtils;
import com.tuling.modules.goods.goods.utils.ExportConstants;
import com.tuling.modules.goods.goods.utils.ExportExcelUtils;
import com.tuling.modules.goods.goods.utils.ImportExcelConstants;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberlevel.service.MemberLevelInfoService;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * goods Controller
 * @author guoxue 
 * @date 2017-06-03
 */
@Controller
@RequestMapping(value = "goods/goods")
public class GoodsController extends BaseController{
	
	@Autowired
	private GoodsBizService goodsBizService;
	
	
	@Autowired
	private MemberLevelInfoService memberLevelInfoService;
	
	@Autowired
	private UploadHelper uploadHelper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${fileServer}")
	private String fileServer;
	
	@ModelAttribute
	public Goods get(@RequestParam(required = false) String id) {
		Goods entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new Goods(id);
			entity = goodsBizService.get(entity);
		}
		if (entity == null) {
			entity = new Goods();
		}
		return entity;
	}
	
	/**
	 * 
	 * Created on 2017???6???3??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guo xue
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String itemIndex(Model model) {
		return "layout1.goods.goods.goodsIndex";
	}
	
	/**
	 * 
	 * Created on 2017???6???3??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page2<Log>
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<Goods> list(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {

		PageHelper<Goods> page = null;
		// ??????step1
		JSONObject obj = JSONObject.parseObject(param);
		// ??????step2
		GoodsFilter goodsFilter = obj.toJavaObject(GoodsFilter.class);
		// ??????step3
		page = goodsBizService.findGoodsList(new Page(obj), goodsFilter);

		return page;
	}
	
	/**
	 * 
	 * Created on 2017???6???5??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "addgoods")
	public String addGoods(Model model) {
		String goodsCode = IdGen.uuid().substring(0, 10);
		model.addAttribute("goodsCode", goodsCode);
		//??????mongo?????????????????????
		List<Map> list = goodsBizService.findAllServicePackage();
		List<ServicePackage> servicePackageList = new ArrayList<ServicePackage>();
		for(Map map:list){
			ServicePackage servicePackage = new ServicePackage();
			servicePackage.setServicePackageCode(map.get("packageId").toString());
			servicePackage.setServicePackageName(map.get("packageName").toString());
			servicePackageList.add(servicePackage);
		}
		model.addAttribute("ServicePackageName", servicePackageList);
		return "layout3.goods.goods.addGoods";
	}
	
	/**
	 * 
	 * Created on 2017???6???6??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param request
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(Goods goods,GoodsPriceList goodsPriceList, @RequestParam("files") MultipartFile[] files,@RequestParam("logopic") 
MultipartFile[] logopic,  @RequestParam("recommendPic") MultipartFile[] recommendPic, HttpServletResponse response)throws UploadException{
		
		String id = goods.getId();
		if (StringUtils.isBlank(id)) {
			// ???????????????
			Goods haveGoodsCode = goodsBizService.findByGoodsCode(goods);
			Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
			if (haveGoodsCode != null) {
				responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
				return responseHelper;
			}
			if (haveGoodsName != null) {
				responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
				return responseHelper;
			}
		} else {
			// ????????????
			Goods goodsEntity = goodsBizService.get(id);
			if (!goodsEntity.getGoodsName().equals(goods.getGoodsName())) {
				Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
				if (haveGoodsName != null) {
					responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
					return responseHelper;
				}
			}
			
			/*if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals(goods.getGoodsStatus())){
				List<Goods> packageList = goodsBizService.findGoodsPackageListByGoodsCode(goods);
				for(Goods packageGoods:packageList){
					if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE.equals(packageGoods.getGoodsStatus())){
						responseHelper.setFail("????????????????????????"+packageGoods.getGoodsName()+"?????????????????????????????????????????????");// ?????????????????? ??? ??????
						return responseHelper;
					}
				}
			}*/
			
		}

		// ??????????????????
		String goodsClassId = goods.getGoodsClassCode();
		GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassCode(goodsClassId);
		if (goodsClassInfo != null) {
			goods.setGoodsClassCode(goodsClassInfo.getGoodsClassCode());
			goods.setGoodsClassName(goodsClassInfo.getGoodsClassName());
			if("????????????".equals(goodsClassInfo.getGoodsClassName())){
				goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_ZERO);
			}else if("?????????".equals(goodsClassInfo.getGoodsClassName())){
				goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_ONE);
			}else{
				goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_TWO);
			}

		} else {
			goods.setGoodsClassCode("0");
		}
		if(goods.getGoodsStatus() == null){
			goods.setGoodsStatus("0");
		}
		
		
		if(logopic.length>0){
			List<String> goodsIconUrl = savePicInfo(logopic);
			if(!goodsIconUrl.isEmpty()){
				goods.setGoodsIcon("http://10.101.22.250:8899"+goodsIconUrl.get(0));
			}
		}
		if(recommendPic.length>0){
			List<String> recommendUrl = savePicInfo(recommendPic);
			if(!recommendUrl.isEmpty()){
				goods.setRecommendPicUrl("http://10.101.22.250:8899"+recommendUrl.get(0));
			}
		}
		List<String> filesUrl = new ArrayList<String>();
		if(files.length>0){
			filesUrl = savePicInfo(files);
			goods.setIsDetailsBanner(CommonConstant.COMMON_YES);
		}else{
			if(!goodsBizService.findAllGoodsPicList(goods).isEmpty()){
				goods.setIsDetailsBanner(CommonConstant.COMMON_YES);
			}else{
				goods.setIsDetailsBanner(CommonConstant.COMMON_NO);
			}
			
		}
		
	
		
//		List<FileInfo> logofile = uploadHelper.saveFiles(logofiles);
		
		
		
		goodsBizService.save(goods,filesUrl,goodsPriceList);
		responseHelper.setSuccess("??????????????????");// ?????????????????? ??? ??????

		return responseHelper;
	}
	
	
	@RequestMapping(value = "savetags")
    @ResponseBody
	public ResponseHelper saveTags(@RequestParam String goodsTags,@RequestParam String goodsCode) {
		goodsBizService.saveTags(goodsCode,goodsTags);
		responseHelper.setSuccess("??????????????????");// ?????????????????? ??? ??????
		return responseHelper;
    }	
	
	
	/**
	 * 
	 * Created on 2017???6???6??? .s
	 * <p>
	 * Description {???????????????}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	
	@RequiresPermissions("user")
	@RequestMapping(value = "addgoodspackage")
	public String addGoodsPackage(Model model) {
		String goodsCode = IdGen.uuid().substring(0, 10);
		model.addAttribute("goodsCode", goodsCode);
		return "layout3.goods.goods.addGoodsPackage";
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
	 * @throws UploadException 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "savepackage")
	@ResponseBody
	public ResponseHelper savePackage(Goods goods, GoodsPackageListGoodsFilter goodsPackageList,
			@RequestParam("files") MultipartFile[] files,@RequestParam("logofiles") MultipartFile[] logofiles, 

HttpServletResponse response) throws UploadException {

		String id = goods.getId();
		String packageCodes = goodsPackageList.getPackGoodsCode();
		String[] packageCode = null;
		
		if (StringUtils.isBlank(id)) {
			// ????????????code????????????
			Goods haveGoodsCode = goodsBizService.findByGoodsCode(goods);
			Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
			if (haveGoodsCode != null) {
				responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
				return responseHelper;
			}
			if (haveGoodsName != null) {
				responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
				return responseHelper;
			}
			
			if (null == packageCodes) {
				responseHelper.setFail("???????????????????????????");// ?????????????????? ??? ??????
				return responseHelper;
			}
		} else {
			Goods goodsEntity = goodsBizService.get(id);
			if (!(goodsEntity.getGoodsName()).equals(goods.getGoodsName())) {
				Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
				if (haveGoodsName != null) {
					responseHelper.setFail("?????????????????????");// ?????????????????? ??? ??????
					return responseHelper;
				}
			}
			if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE.equals(goods.getGoodsStatus())){
				List<Goods> packageList = goodsBizService.findGoodsPackageListGoodsByGoodsCode(goods);
				for(Goods packageGoods:packageList){
					if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals(packageGoods.getGoodsStatus())){
						responseHelper.setFail("?????????????????????????????????"+packageGoods.getGoodsName()+"????????????????????????????????????????????????");// ?????????????????? ??? ??????
						return responseHelper;
					}
				}
			}
		}
		
		//??????????????????????????????
		goodsBizService.deleteGoodsPackageGoods(goods);
		List<GoodsPackageList> list = new ArrayList<GoodsPackageList>();
		String packageIds = goodsPackageList.getPackageId();
		String[] packageId = null;
		int packageCount = 0;
		if (packageIds != null) {
			packageId = packageIds.split(",");
			packageCount = packageId.length;
		}
		
		if (!StringUtils.isBlank(packageCodes)) {
			packageCode = packageCodes.split(",");
			
			for (int i = 0; i < packageCode.length; i++) {
				GoodsPackageList newGoodsPackageList = new GoodsPackageList();
				if (i >= packageCount) {
					newGoodsPackageList.setId("");
				} else {
					newGoodsPackageList.setId(packageId[i]);
				}

				newGoodsPackageList.setGoodsCode(goods.getGoodsCode());
				newGoodsPackageList.setPackGoodsCode(packageCode[i]);
				newGoodsPackageList.setPackGoodsSeq(i + 2 + "");
				list.add(newGoodsPackageList);
			}
		}
		

		// ?????????????????????????????????
		goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_TWO);
		goods.setIsRecommend(CommonConstant.isRecommend.DICT_ISRECOMMEND_ZERO);

		// ??????????????????
		String goodsClassId = goods.getGoodsClassCode();
		GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassCode(goodsClassId);
		if (goodsClassInfo != null) {
			goods.setGoodsClassCode(goodsClassInfo.getGoodsClassCode());

		} else {
			goods.setGoodsClassCode("0");
		}

		/*List<FileInfo> fileList = uploadHelper.saveFiles(files);
		List<FileInfo> logofile = uploadHelper.saveFiles(logofiles);*/
		/*goodsBizService.savePackag(goods, list,logofile, fileList);*/
		responseHelper.setSuccess("??????????????????");// ?????????????????? ??? ??????

		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017???6???26??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param id
	 * @return String
	 * @throws UploadException 
	 */
	@RequestMapping(value = "deletepic")
	@ResponseBody
	public ResponseHelper deletepic(@RequestBody String param) throws UploadException  {
		
			JSONObject obj = JSONObject.parseObject(param);
			String id = obj.getString("id");
			if(!StringUtils.isBlank(id)){
				/*uploadHelper.deleteFile(id);
				Goods goods = new Goods();
				goods.setId(id);*/
				GoodsPicList goodsPicList = new GoodsPicList();
				goodsPicList.setId(id);
				goodsBizService.deleteGoodsPicListById(goodsPicList);
				responseHelper.setSuccess("?????????????????????");
			}else{
				responseHelper.setFail("???????????????????????????");
			}
			return responseHelper;
		
	}
	
	
	/**
	 * 
	 * Created on 2017???6???6??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param model
	 * @return StringSSS
	 * @throws UploadException 
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "form")
	public String form(Goods goods, Model model) throws UploadException {

		String returnPage = "";
		// ????????????
		Goods goodsById = goodsBizService.get(goods.getId());
		String goodsClassCode = goodsById.getGoodsClassCode();
		if(!StringUtils.isBlank(goodsClassCode)){
			GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassName(goodsClassCode);
			goodsById.setGoodsClassCode(goodsClassInfo.getId());
			goodsById.setGoodsClassName(goodsClassInfo.getGoodsClassName());
			String[] servicePackageCodeArr = goodsById.getServicePackageCode().split(",");
			goodsById.setServicePackageCodeArr(servicePackageCodeArr);
		}
		
		model.addAttribute("goods", goodsById);
		//??????????????????
		/*GoodsPicList goodsLogo = new GoodsPicList();
		goodsLogo = goodsBizService.findGoodsLogo(goodsById);
		FileInfo goodsLogoFileInfo = new FileInfo();
		if(goodsLogo != null){
			goodsLogoFileInfo.setId(goodsLogo.getGoodsPicUrl());
			GridFSDBFile gridFSDBFile = uploadHelper.getFile(goodsLogo.getGoodsPicUrl());
			DBObject dBObjectLogo = gridFSDBFile.getMetaData();
			goodsLogoFileInfo.setName(dBObjectLogo.get("fileName").toString());
		}
		model.addAttribute("goodsLogoFileInfo", goodsLogoFileInfo);*/
		// ??????????????????
		List<GoodsPriceList> goodsPriceList = goodsBizService.findGoodsPriceListByGoodsCode(goodsById);
		model.addAttribute("goodsPriceList", goodsPriceList);
		// ??????????????????
		List<GoodsPicList> GoodsPicList = goodsBizService.findAllGoodsPicList(goodsById);
		/*List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		if (list.size() > 0) {
			for (GoodsPicList goodsPicList : list) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setId(goodsPicList.getGoodsPicUrl());
				GridFSDBFile GridFSDBFile = uploadHelper.getFile(goodsPicList.getGoodsPicUrl());
				DBObject dBObject = GridFSDBFile.getMetaData();
				fileInfo.setName(dBObject.get("fileName").toString());
				fileInfoList.add(fileInfo);
			}
		}*/
		model.addAttribute("fileInfoList", GoodsPicList);
		//??????mongo?????????????????????
				List<Map> list = goodsBizService.findAllServicePackage();
				List<ServicePackage> servicePackageList = new ArrayList<ServicePackage>();
				for(Map map:list){
					ServicePackage servicePackage = new ServicePackage();
					servicePackage.setServicePackageCode(map.get("packageId").toString());
					servicePackage.setServicePackageName(map.get("packageName").toString());
					servicePackageList.add(servicePackage);
				}
				List<String> packageCode = new ArrayList<String>();
				String[] packageCodes = goodsById.getServicePackageCode().split(",");
				for(String pac : packageCodes){
					packageCode.add(pac);
				}
				model.addAttribute("SelectPackageName", packageCode);
				model.addAttribute("ServicePackageName", servicePackageList);
		//?????????????????????
				GoodsTagsList goodsTagsList = new GoodsTagsList();
				goodsTagsList.setGoodsCode(goodsById.getGoodsCode());
				List<GoodsTagsList> tagsList = goodsBizService.getTagsByGoodsCode(goodsTagsList);
				model.addAttribute("tagMatchedNumCount", tagsList.size());
				
		/*String goodsType = goodsById.getGoodsType();

		if (goodsType.equals(CommonConstant.goodsType.DICT_GOODS_TYPE_ONE)) {*/
			returnPage = "layout3.goods.goods.editGoods";
		/*} else {
			// ??????????????????
			List<Goods> listGoods = goodsBizService.findGoods();
			model.addAttribute("goodsList1", listGoods);

			// ??????????????????????????????
			List<GoodsPackageListGoodsFilter> listGoodsPackageListGoodsFilter = goodsBizService
					.findGoodsPackageListGoods(goodsById);
			model.addAttribute("goodsList2", listGoodsPackageListGoodsFilter);
			int countNum = 0;
			for (int i = 0; i < listGoodsPackageListGoodsFilter.size(); i++) {
				int count = Integer.parseInt(listGoodsPackageListGoodsFilter.get(i).getPackGoodsSeq());
				if (count > countNum) {
					countNum = count;
				}

			}
			
			List<GoodsPackageGoodsFilter>  goodsPackageGoodsList = goodsBizService.findGoodsPackageListGoods

(goods);
			
			
			model.addAttribute("GoodsPackageGoodsFilter", goodsPackageGoodsList);
			returnPage = "layout3.goods.goods.editGoodsPackage";
		}*/
		return returnPage;

	}
	
	
	/**
	 * 
	 * Created on 2017???9???14??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param model
	 * @return StringSSS
	 * @throws UploadException 
	 */
	@RequestMapping(value = "goodsdetail")
	public String goodsDetail(Goods goods, Model model) throws UploadException {

		String returnPage = "";
		// ????????????
		Goods goodsById = goodsBizService.get(goods.getId());
		String goodsClassCode = goodsById.getGoodsClassCode();
		if(!StringUtils.isBlank(goodsClassCode)){
			GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassName(goodsClassCode);
			goodsById.setGoodsClassCode(goodsClassInfo.getId());
			goodsById.setGoodsClassName(goodsClassInfo.getGoodsClassName());
		}
		
		model.addAttribute("goods", goodsById);
		//??????????????????
		GoodsPicList goodsLogo = new GoodsPicList();
		goodsLogo = goodsBizService.findGoodsLogo(goodsById);
		FileInfo goodsLogoFileInfo = new FileInfo();
		if(goodsLogo != null){
			goodsLogoFileInfo.setId(goodsLogo.getGoodsPicUrl());
			GridFSDBFile gridFSDBFile = uploadHelper.getFile(goodsLogo.getGoodsPicUrl());
			DBObject dBObjectLogo = gridFSDBFile.getMetaData();
			goodsLogoFileInfo.setName(dBObjectLogo.get("fileName").toString());
		}
		model.addAttribute("goodsLogoFileInfo", goodsLogoFileInfo);
		
		// ??????????????????
		List<GoodsPicList> list = goodsBizService.findAllGoodsPicList(goodsById);
		List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
		if (list.size() > 0) {
			for (GoodsPicList goodsPicList : list) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setId(goodsPicList.getGoodsPicUrl());
				GridFSDBFile GridFSDBFile = uploadHelper.getFile(goodsPicList.getGoodsPicUrl());
				DBObject dBObject = GridFSDBFile.getMetaData();
				fileInfo.setName(dBObject.get("fileName").toString());
				fileInfoList.add(fileInfo);
			}
		}
		model.addAttribute("fileInfoList", fileInfoList);
			returnPage = "layout3.goods.goods.goodsDetail";
		
		return returnPage;

	}
	
	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifygoodsstatuson")
	@ResponseBody
	public ResponseHelper modifyGoodsStatusOn(@RequestBody String param, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		// ??????id??????goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		/*if (goodsList != null) {
			for (Goods goods : goodsList) {
				String goodsType = goods.getGoodsType();
				if (CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(goodsType)) {
					// ???????????????????????????
					List<Goods> List = goodsBizService.findGoodsPackageListGoodsByGoodsCode(goods);
					if (List != null) {
						// ?????????????????????
						for (Goods pcakage : List) {
							if (CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals

(pcakage.getGoodsStatus())) {
								responseHelper.setFail("?????????????????????????????????????????????:");
								return responseHelper;
							}
						}
					}

				}
			}
		}*/

		JSONObject result = goodsBizService.modifyGoodsStatusOn(list);
		goodsBizService.insertGoodsInfo(goodsList);
		
		List<Map> list1  = goodsBizService.getMongoGoods();

		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("????????????");// ?????????????????? ??? ??????
		} else {
			responseHelper.setFail("????????????:" + result.get(CommonConstant.ERROR_MSG));// ??????????????????
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @param request
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifygoodsstatusoff")
	@ResponseBody
	public ResponseHelper modifyGoodsStatusOff(@RequestBody String param, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}

		// ??????id??????goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		/*if (goodsList != null) {
			for (Goods goods : goodsList) {
				String goodsType = goods.getGoodsType();
				if ((CommonConstant.goodsType.DICT_GOODS_TYPE_ONE).equals(goodsType)) {
					// ???????????????????????????????????????
					List<Goods> List = goodsBizService.findGoodsPackageListByGoodsCode(goods);
					for (Goods pcakageGoods : List) {
						if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals

(pcakageGoods.getGoodsStatus())) {
							responseHelper.setFail("????????????????????????????????????????????????:");
							return responseHelper;
						}
					}

				}
			}
		}*/

		JSONObject result = goodsBizService.modifyGoodsStatusOff(list);
		goodsBizService.deleteGoodsInfo(goodsList);
		List<Map> list1  = goodsBizService.getMongoGoods();
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("????????????");// ?????????????????? ??? ??????
		} else {
			responseHelper.setFail("????????????:" + result.get(CommonConstant.ERROR_MSG));// ??????????????????
		}

		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @param request
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "deletegoods")
	@ResponseBody
	public ResponseHelper deleteGoods(@RequestBody String param, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		// ??????id??????goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		if (goodsList != null) {
			for (Goods goods : goodsList) {
				/*String goodsType = goods.getGoodsType();
				if ((CommonConstant.goodsType.DICT_GOODS_TYPE_TWO).equals(goodsType)) {
					// ?????????
					if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals(goods.getGoodsStatus())) 

{
						responseHelper.setFail("?????????????????????????????????");
						return responseHelper;
					}
				} else {
					// ??????
					List<Goods> List = goodsBizService.findGoodsPackageListByGoodsCode(goods);
					if (List.size() == 0) {*/
						if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals(goods.getGoodsStatus())) {
							responseHelper.setFail("??????????????????????????????");
							return responseHelper;
						}
					/*} else {
						for (Goods pcakage : List) {
							if ((CommonConstant.DelFlag.normal).equals(pcakage.getDelFlag())) {
								responseHelper.setFail("????????????????????????????????????????????????:");
								return responseHelper;
							} else {
								if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals

(goods.getGoodsStatus())) {
									responseHelper.setFail("??????????????????????????????");
									return responseHelper;
								}
							}
						}
					}

				}*/
			}
		}

		JSONObject result = goodsBizService.deleteGoods(list);

		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("????????????");// ?????????????????? ??? ??????
		} else {
			responseHelper.setFail("????????????:" + result.get(CommonConstant.ERROR_MSG));// ??????????????????
		}

		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017???6???9??? .s
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "pricemanage")
	public String priceManage(Goods goods, Model model) {

		// ????????????entity??????id
		Goods goodsById = goodsBizService.get(goods.getId());
		model.addAttribute("goods", goodsById);

		// ??????id??????goodsRateList?????????list
		GoodsRateList goodsRateList = new GoodsRateList();
		goodsRateList.setGoodsCode(goodsById.getGoodsCode());
		List<GoodsRateList> list = goodsBizService.findAll(goodsRateList);
		List<GoodsRateListFilter> newlist = new ArrayList<GoodsRateListFilter>();
		if (list.size() == 0) {
			// list???null?????????????????????????????????
			MemberLevelInfo memberLevelInfo = new MemberLevelInfo();
			List<MemberLevelInfo> memberLevelInfoPullList = memberLevelInfoService.findList(memberLevelInfo);
			model.addAttribute("memberLevelInfoPullList", memberLevelInfoPullList);
			return "layout3.goods.goods.addGoodsPriceManage";
		} else {

			MemberLevelInfo memberLevelInfo = new MemberLevelInfo();
			List<MemberLevelInfo> memberLevelInfoPullList = memberLevelInfoService.findList(memberLevelInfo);

			for (MemberLevelInfo memberLevel : memberLevelInfoPullList) {
				String memberLeverCode = memberLevel.getMemberLevelCode();
				String goodsRate = "";
				String id = "";
				for (int i = 0; i < list.size(); i++) {
					if ((list.get(i).getMemberLevelCode()).equals(memberLeverCode)) {
						id = list.get(i).getId();
						goodsRate = list.get(i).getMemberRate();
					}
				}
				GoodsRateListFilter goodsRateListFilter = new GoodsRateListFilter();
				goodsRateListFilter.setId(id);
				goodsRateListFilter.setGoodsCode(goodsById.getGoodsCode());
				goodsRateListFilter.setMemberLevelCode(memberLeverCode);
				goodsRateListFilter.setMemberLevelName(memberLevel.getMemberLevelName());
				goodsRateListFilter.setMemberRate(goodsRate);
				newlist.add(goodsRateListFilter);
			}
			model.addAttribute("memberLevelInfoPullList", newlist);
		}

		return "layout3.goods.goods.editGoodsPriceManage";

	}
	
	/**
	 * 
	 * Created on 2017???6???12??? .
	 * <p>
	 * Description {????????????rateList}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param param
	 * @param request
	 * @return ResponseHelper
	 */
	@RequestMapping(value = "savegoodsratelist")
	@ResponseBody
	public ResponseHelper saveGoodsRateList(@RequestBody String param, HttpServletRequest request) {

		JSONObject obj = JSONObject.parseObject(param);
		JSONArray rateList = obj.getJSONArray("goodsRateList");
		for (int i = 0; i < rateList.size(); i++) {

			GoodsRateList goodsRateList = new GoodsRateList();
			String id = rateList.getJSONObject(i).getString("id");
			if (StringUtils.isBlank(id)) {
				goodsRateList.setId("");
			} else {
				goodsRateList.setId(deleteTabs(rateList.getJSONObject(i).getString("id")));
			}

			goodsRateList.setGoodsCode(rateList.getJSONObject(i).getString("goodsCode"));
			goodsRateList.setMemberLevelCode(deleteTabs(rateList.getJSONObject(i).getString("memberLevelCode")));
			goodsRateList.setMemberRate(rateList.getJSONObject(i).getString("memberRate"));

			goodsBizService.save(goodsRateList);
		}
		responseHelper.setSuccess("????????????");// ?????????????????? ??? ??????
		return responseHelper;
	}
	
	
	/**
	 * 
	 * Created on 2017???9???13??? .
	 * <p>
	 * Description {?????????????????????}
	 *
	 * @author guoxue 
	 * @param goods
	 * @param param
	 * @param request
	 * @return ResponseHelper
	 */
	
	@RequiresPermissions("user")
	@RequestMapping(value = "goodspackageaddgoods")
	public String goodsPackageAddGoods(Model model) {
		return "layout3.goods.goods.goodsPackageAddGoods";
	}
	
	/**
	 * 
	 * Created on 2018???1???29??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "addtags")
	public String addTags(Model model,String goodsCode) {
		model.addAttribute("goodsCode", goodsCode);
		return "layout3.goods.goods.addTags";
	}
	
	@RequestMapping(value = "gettags")
    @ResponseBody
	public GoodsTagsResult getTags(@RequestParam String goodsCode) {
		/* Map<String, String> map1 = new HashMap();
			map1.put("id","1");
			map1.put("name","lisa");
			Map<String, String> map2 = new HashMap();
			map2.put("id","2");
			map2.put("name","ann");
			Map<String, String> map3 = new HashMap();
			map3.put("id","3");
			map3.put("name","shelly");
			JSONObject json=new JSONObject(); 
	        JSONObject json1=new JSONObject(); 
	        JSONObject json2=new JSONObject(); 
	        JSONArray result=new JSONArray();  
	        json1.putAll(map1);
	        json2.putAll(map2);
	        json.putAll(map3);
	        result.add(json1);
	        result.add(json2);
	        result.add(json);*/
		GoodsTagsResult goodsTagsResult = new GoodsTagsResult();
		JSONArray resultL=new JSONArray();  
		JSONArray resultR=new JSONArray(); 
	        //??????goodsCode??????tags
		GoodsTagsList newGoodsTagsList = new GoodsTagsList();
		newGoodsTagsList.setGoodsCode(goodsCode);
		List<GoodsTagsList> goodsTagsList = goodsBizService.getTagsByGoodsCode(newGoodsTagsList);
		List<Map> list = goodsBizService.findAllTags();
		if(goodsTagsList.isEmpty()){
			 //??????mongo?????????????????????
			  for(Map map:list){
				  Map<String, String> tagsMap = new HashMap();
				  tagsMap.put("id", map.get("_id").toString());
				  tagsMap.put("name", map.get("tagName").toString());
				  resultL.add(tagsMap);
				  goodsTagsResult.setResultL(resultL);
			  }
		}else{
			for(GoodsTagsList goodsTag : goodsTagsList){
				for(int i=0;i<list.size();i++){
					if(goodsTag.getTagCode().equals(list.get(i).get("_id").toString())){
						 list.remove(i);
					}
					  
				  }
				
			
				Map<String, String> tagsRMap = new HashMap();
				 tagsRMap.put("id", goodsTag.getTagCode());
				 tagsRMap.put("name", goodsTag.getTagName());
				 resultR.add(tagsRMap);
				 goodsTagsResult.setResultR(resultR);
			}
			for(Map map : list){
					Map<String, String> tagsLMap = new HashMap();
					  tagsLMap.put("id", map.get("_id").toString());
					  tagsLMap.put("name", map.get("tagName").toString());
					  resultL.add(tagsLMap);
					  goodsTagsResult.setResultL(resultL);
			}
			
		}
	     

	  return goodsTagsResult;
    }	
	/**
	 * 
	 * Created on 2018???1???29??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "edittags")
	public String editTags(Model model,String goodsCode) {
		model.addAttribute("goodsCode", goodsCode);
		return "layout3.goods.goods.editTags";
	}
	
	/**
	 * 
	 * Created on 2017???9???13??? .
	 * <p>
	 * Description {????????????????????????????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page2<Log>
	 */
	@RequestMapping(value = "getgoodspackagegoods")
	@ResponseBody
	public PageHelper<Goods> getGoodsPackageGoods(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {

		PageHelper<Goods> page = null;
		// ??????step1
		JSONObject obj = JSONObject.parseObject(param);
		// ??????step2
		GoodsFilter goodsFilter = obj.toJavaObject(GoodsFilter.class);
		// ??????step3
		page = goodsBizService.getGoodsPackageGoods(new Page(obj), goodsFilter);

		return page;
	}
	
	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param param
	 * @param request
	 * @param response
	 * @return ResponseHelper
	 */
	@RequestMapping(value = "addgoodspackagegoods")
	@ResponseBody
	public List<GoodsPackageGoodsFilter> addGoodsPackageGoods(@RequestBody String param, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		// ??????id??????addGoodsPackageGoodsEntity
		List<GoodsPackageGoodsFilter> goodsPackageGoodslist = goodsBizService.getPackageGoods(list);
		
		return goodsPackageGoodslist;
	}
	
	/**
	 * 
	 * Created on 2017???6???13??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue 
	 * @param str
	 * @return String
	 */
	public String deleteTabs(String str) {
		String dest = "";
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		dest = m.replaceAll("");
		return dest;
	}
	
	/**
	 * 
	 * Created on 2017???6???12??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author yuelingyun
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "importgoods")
	public String importGoods(Model model) {
		return "layout3.goods.goods.importGoods";
	}
	
	/**
	 * 
	 * Created on 2017???6???12??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author yuelingyun
	 * @param model
	 * @return String
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "importexcel")
	@ResponseBody
	public ResponseHelper importExcel(@RequestParam("files") MultipartFile multipartFile, HttpServletResponse response) 

throws UploadException, IOException, BadHanyuPinyinOutputFormatCombination {
    	String userId = UserUtils.getUser().getId();
    	JSONObject resultObj = null;
    	// ????????????
        if (multipartFile != null) {
            CommonsMultipartFile file = (CommonsMultipartFile) multipartFile;
            String type = ImportExcelConstants.GOODS;
        	resultObj = CheckExcelUtils.checkGoodsExcel(file, type);
        	// ?????????????????????????????????
        	if(resultObj.getString("status").equals(CommonConstant.RESULT_SUCCESS)){
        		resultObj = goodsBizService.importGoodsExcel(file, type, userId);
        	}
            responseHelper.setStatus(resultObj.getString("status"));
    		responseHelper.setSuccess(resultObj.getString("msg")); 
        }
        return responseHelper;
    }
	
	/**
	 * 
	 * Created on 2017???6???12??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author yuelingyun
	 * @param model
	 * @return String
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "exportexcel")
	public void exportExcel(@RequestParam("param") String param, HttpServletResponse response) throws UploadException, 

NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		JSONObject obj = JSONObject.parseObject(param);
		GoodsFilter goodsFilter = obj.toJavaObject(GoodsFilter.class);
        Page page = new Page(obj);
        Boolean isPage = obj.getBoolean("isPage");
        if (isPage == null || !isPage) {
            page.setRowCount(-1);
        }
        // ?????????????????????
        PageHelper<Goods> pageHelper = goodsBizService.findGoodsAndPackageList(page, goodsFilter);
        List<Goods> list = pageHelper.getRows();
        
    	// ??????????????????
    	ExportExcelUtils.exportExcel(response, list, ExportConstants.GOODS);
	}
	
	
	
	public List<String> savePicInfo(
			@RequestParam("files") MultipartFile[] files) {

		List<String> filePathList =new ArrayList<String>();
		try {
			
			boolean uploadFileFlag = false;
			for (MultipartFile file : files) {
				File temFile = null;
				// OutputStream os = null;
				// InputStream ins = null;
				try {
					// String url = "http://" + this.fileServer +
					// "/file/upload";

					// String tempPath = this.tempFilePath +
					// file.getOriginalFilename();
					// ins = file.getInputStream();
					// temFile = new File(tempPath);
					// os = new FileOutputStream(temFile);
					//
					// int bytesRead = 0;
					// byte[] buffer = new byte[2048];
					// while ((bytesRead = ins.read(buffer, 0, 2048)) != -1) {
					// os.write(buffer, 0, bytesRead);
					// }
					String OriginalFilename = file.getOriginalFilename();
					String fileName = OriginalFilename.substring(0, OriginalFilename.lastIndexOf('.'));
					String fileExt = OriginalFilename.substring(OriginalFilename.lastIndexOf('.'));

					temFile = File.createTempFile("rg_mp_ms_" + fileName, fileExt);
					file.transferTo(temFile);

					FileSystemResource resource = new FileSystemResource(temFile);
					MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
					param.add("jarFile", resource);
					param.add("appCode", "ruguo-mp-ms");
					param.add("level", "2");
					param.add("compress", "true");
					param.add("photoType", "info");
					
					String resJson = restTemplate.postForObject(this.fileServer, param, String.class);

					JSONObject resJSON = JSONObject.parseObject(resJson);
					// ?????????????????????
					String setStatus = resJSON.getString("setStatus");
					if ("500".equals(setStatus)) {
						throw new Exception("??????????????????????????????????????????-500");
					}
					// ?????????????????????????????????
				    String filePath = resJSON.getString("filePath");

					// foodMaterialInfo.setPicPath(this.fileAddr + filePath);
					if ("certificateFront".equals(fileName)) {
//						momemberBasicInfo.setCertificateFrontUrl(filePath);
					} else if ("certificateBack".equals(fileName)) {
//						momemberBasicInfo.setCertificateBackUrl(filePath);
					}
					filePathList.add(filePath);
					uploadFileFlag = true;

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("?????????????????????????????????-??????????????????" + e.getStackTrace());
				} finally {
					// if(null != os){
					// os.close();
					// }
					// if(null != ins){
					// ins.close();
					// }
					if (null != temFile) {
						this.deleteDir(temFile);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			responseHelper.setFail("??????????????????");
		}
		return filePathList;
	}

	public void deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// ??????????????????????????????????????????????????????
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(dir, children[i]));
			}
		}
		// ???????????????????????????????????????????????????
		dir.delete();
	}
}
