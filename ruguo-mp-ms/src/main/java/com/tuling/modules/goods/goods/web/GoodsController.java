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
	 * Created on 2017年6月3日 .
	 * <p>
	 * Description {进入商品管理首页}
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
	 * Created on 2017年6月3日 .
	 * <p>
	 * Description {获取商品列表}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page2<Log>
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<Goods> list(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {

		PageHelper<Goods> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		GoodsFilter goodsFilter = obj.toJavaObject(GoodsFilter.class);
		// 分页step3
		page = goodsBizService.findGoodsList(new Page(obj), goodsFilter);

		return page;
	}
	
	/**
	 * 
	 * Created on 2017年6月5日 .
	 * <p>
	 * Description {进入添加商品页面}
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
		//获取mongo上的服务包数据
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
	 * Created on 2017年6月6日 .
	 * <p>
	 * Description {保存商品信息}
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
			// 新添加商品
			Goods haveGoodsCode = goodsBizService.findByGoodsCode(goods);
			Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
			if (haveGoodsCode != null) {
				responseHelper.setFail("商品编码已存在");// 返回成功状态 和 消息
				return responseHelper;
			}
			if (haveGoodsName != null) {
				responseHelper.setFail("商品名称已存在");// 返回成功状态 和 消息
				return responseHelper;
			}
		} else {
			// 修改商品
			Goods goodsEntity = goodsBizService.get(id);
			if (!goodsEntity.getGoodsName().equals(goods.getGoodsName())) {
				Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
				if (haveGoodsName != null) {
					responseHelper.setFail("商品名称已存在");// 返回成功状态 和 消息
					return responseHelper;
				}
			}
			
			/*if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals(goods.getGoodsStatus())){
				List<Goods> packageList = goodsBizService.findGoodsPackageListByGoodsCode(goods);
				for(Goods packageGoods:packageList){
					if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE.equals(packageGoods.getGoodsStatus())){
						responseHelper.setFail("商品所在产品包【"+packageGoods.getGoodsName()+"】是上架状态，请先下架产品包！");// 返回成功状态 和 消息
						return responseHelper;
					}
				}
			}*/
			
		}

		// 设置分类数据
		String goodsClassId = goods.getGoodsClassCode();
		GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassCode(goodsClassId);
		if (goodsClassInfo != null) {
			goods.setGoodsClassCode(goodsClassInfo.getGoodsClassCode());
			goods.setGoodsClassName(goodsClassInfo.getGoodsClassName());
			if("医生服务".equals(goodsClassInfo.getGoodsClassName())){
				goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_ZERO);
			}else if("基因类".equals(goodsClassInfo.getGoodsClassName())){
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
		responseHelper.setSuccess("产品保存成功");// 返回成功状态 和 消息

		return responseHelper;
	}
	
	
	@RequestMapping(value = "savetags")
    @ResponseBody
	public ResponseHelper saveTags(@RequestParam String goodsTags,@RequestParam String goodsCode) {
		goodsBizService.saveTags(goodsCode,goodsTags);
		responseHelper.setSuccess("标签保存成功");// 返回成功状态 和 消息
		return responseHelper;
    }	
	
	
	/**
	 * 
	 * Created on 2017年6月6日 .s
	 * <p>
	 * Description {添加产品包}
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
	 * Created on 2017年6月6日 .
	 * <p>
	 * Description {保存产品包信息}
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
			// 查看商品code是否存在
			Goods haveGoodsCode = goodsBizService.findByGoodsCode(goods);
			Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
			if (haveGoodsCode != null) {
				responseHelper.setFail("商品编码已存在");// 返回成功状态 和 消息
				return responseHelper;
			}
			if (haveGoodsName != null) {
				responseHelper.setFail("商品名称已存在");// 返回成功状态 和 消息
				return responseHelper;
			}
			
			if (null == packageCodes) {
				responseHelper.setFail("必须添加包含商品！");// 返回成功状态 和 消息
				return responseHelper;
			}
		} else {
			Goods goodsEntity = goodsBizService.get(id);
			if (!(goodsEntity.getGoodsName()).equals(goods.getGoodsName())) {
				Goods haveGoodsName = goodsBizService.findByGoodsName(goods);
				if (haveGoodsName != null) {
					responseHelper.setFail("商品名称已存在");// 返回成功状态 和 消息
					return responseHelper;
				}
			}
			if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE.equals(goods.getGoodsStatus())){
				List<Goods> packageList = goodsBizService.findGoodsPackageListGoodsByGoodsCode(goods);
				for(Goods packageGoods:packageList){
					if(CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals(packageGoods.getGoodsStatus())){
						responseHelper.setFail("产品包中含有下架商品【"+packageGoods.getGoodsName()+"】请先上架商品，才能上架产品包！");// 返回成功状态 和 消息
						return responseHelper;
					}
				}
			}
		}
		
		//删除产品包中原有商品
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
		

		// 设置商品类型和是否推荐
		goods.setGoodsType(CommonConstant.goodsType.DICT_GOODS_TYPE_TWO);
		goods.setIsRecommend(CommonConstant.isRecommend.DICT_ISRECOMMEND_ZERO);

		// 设置分类数据
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
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息

		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月26日 .
	 * <p>
	 * Description {删除选定商品图片}
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
				responseHelper.setSuccess("删除图片成功！");
			}else{
				responseHelper.setFail("删除商品图片失败！");
			}
			return responseHelper;
		
	}
	
	
	/**
	 * 
	 * Created on 2017年6月6日 .
	 * <p>
	 * Description {修改商品}
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
		// 获取商品
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
		//获取商品封面
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
		// 获取商品价格
		List<GoodsPriceList> goodsPriceList = goodsBizService.findGoodsPriceListByGoodsCode(goodsById);
		model.addAttribute("goodsPriceList", goodsPriceList);
		// 获取商品图片
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
		//获取mongo上的服务包数据
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
		//获取商品标签数
				GoodsTagsList goodsTagsList = new GoodsTagsList();
				goodsTagsList.setGoodsCode(goodsById.getGoodsCode());
				List<GoodsTagsList> tagsList = goodsBizService.getTagsByGoodsCode(goodsTagsList);
				model.addAttribute("tagMatchedNumCount", tagsList.size());
				
		/*String goodsType = goodsById.getGoodsType();

		if (goodsType.equals(CommonConstant.goodsType.DICT_GOODS_TYPE_ONE)) {*/
			returnPage = "layout3.goods.goods.editGoods";
		/*} else {
			// 准备商品数据
			List<Goods> listGoods = goodsBizService.findGoods();
			model.addAttribute("goodsList1", listGoods);

			// 查询产品包包含的商品
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
	 * Created on 2017年9月14日 .
	 * <p>
	 * Description {商品详情}
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
		// 获取商品
		Goods goodsById = goodsBizService.get(goods.getId());
		String goodsClassCode = goodsById.getGoodsClassCode();
		if(!StringUtils.isBlank(goodsClassCode)){
			GoodsClassInfo goodsClassInfo = goodsBizService.getGoodsClassName(goodsClassCode);
			goodsById.setGoodsClassCode(goodsClassInfo.getId());
			goodsById.setGoodsClassName(goodsClassInfo.getGoodsClassName());
		}
		
		model.addAttribute("goods", goodsById);
		//获取商品封面
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
		
		// 获取商品图片
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
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {商品批量上架}
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
		// 根据id获取goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		/*if (goodsList != null) {
			for (Goods goods : goodsList) {
				String goodsType = goods.getGoodsType();
				if (CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(goodsType)) {
					// 获取产品包包含商品
					List<Goods> List = goodsBizService.findGoodsPackageListGoodsByGoodsCode(goods);
					if (List != null) {
						// 有产品包的商品
						for (Goods pcakage : List) {
							if (CommonConstant.goodsStatus.DICT_GOODS_STATUS_ZERO.equals

(pcakage.getGoodsStatus())) {
								responseHelper.setFail("产品包包含商品必须都是上架状态:");
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
			responseHelper.setSuccess("上架成功");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("上架失败:" + result.get(CommonConstant.ERROR_MSG));// 返回成功状态
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {商品批量下架}
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

		// 根据id获取goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		/*if (goodsList != null) {
			for (Goods goods : goodsList) {
				String goodsType = goods.getGoodsType();
				if ((CommonConstant.goodsType.DICT_GOODS_TYPE_ONE).equals(goodsType)) {
					// 看是否属于某个产品包的商品
					List<Goods> List = goodsBizService.findGoodsPackageListByGoodsCode(goods);
					for (Goods pcakageGoods : List) {
						if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals

(pcakageGoods.getGoodsStatus())) {
							responseHelper.setFail("商品所在的产品包必须都是下架状态:");
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
			responseHelper.setSuccess("下架成功");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("下架失败:" + result.get(CommonConstant.ERROR_MSG));// 返回成功状态
		}

		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量删除}
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
		// 根据id获取goodsEntity
		JSONObject GoodsListResult = goodsBizService.findGoodsListById(list);
		@SuppressWarnings("unchecked")
		List<Goods> goodsList = (List<Goods>) GoodsListResult.get("goodsList");
		if (goodsList != null) {
			for (Goods goods : goodsList) {
				/*String goodsType = goods.getGoodsType();
				if ((CommonConstant.goodsType.DICT_GOODS_TYPE_TWO).equals(goodsType)) {
					// 产品包
					if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals(goods.getGoodsStatus())) 

{
						responseHelper.setFail("产品包上架状态不能删除");
						return responseHelper;
					}
				} else {
					// 商品
					List<Goods> List = goodsBizService.findGoodsPackageListByGoodsCode(goods);
					if (List.size() == 0) {*/
						if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals(goods.getGoodsStatus())) {
							responseHelper.setFail("商品上架状态不能删除");
							return responseHelper;
						}
					/*} else {
						for (Goods pcakage : List) {
							if ((CommonConstant.DelFlag.normal).equals(pcakage.getDelFlag())) {
								responseHelper.setFail("商品所在的产品包必须都是删除状态:");
								return responseHelper;
							} else {
								if ((CommonConstant.goodsStatus.DICT_GOODS_STATUS_ONE).equals

(goods.getGoodsStatus())) {
									responseHelper.setFail("商品上架状态不能删除");
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
			responseHelper.setSuccess("删除成功");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("删除失败:" + result.get(CommonConstant.ERROR_MSG));// 返回成功状态
		}

		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年6月9日 .s
	 * <p>
	 * Description {价格管理}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "pricemanage")
	public String priceManage(Goods goods, Model model) {

		// 获取商品entity通过id
		Goods goodsById = goodsBizService.get(goods.getId());
		model.addAttribute("goods", goodsById);

		// 通过id获取goodsRateList表中的list
		GoodsRateList goodsRateList = new GoodsRateList();
		goodsRateList.setGoodsCode(goodsById.getGoodsCode());
		List<GoodsRateList> list = goodsBizService.findAll(goodsRateList);
		List<GoodsRateListFilter> newlist = new ArrayList<GoodsRateListFilter>();
		if (list.size() == 0) {
			// list为null时说明没有设置价格管理
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
	 * Created on 2017年6月12日 .
	 * <p>
	 * Description {保存商品rateList}
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
		responseHelper.setSuccess("添加成功");// 返回成功状态 和 消息
		return responseHelper;
	}
	
	
	/**
	 * 
	 * Created on 2017年9月13日 .
	 * <p>
	 * Description {添加产品包商品}
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
	 * Created on 2018年1月29日 .
	 * <p>
	 * Description {添加标签}
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
	        //通过goodsCode查找tags
		GoodsTagsList newGoodsTagsList = new GoodsTagsList();
		newGoodsTagsList.setGoodsCode(goodsCode);
		List<GoodsTagsList> goodsTagsList = goodsBizService.getTagsByGoodsCode(newGoodsTagsList);
		List<Map> list = goodsBizService.findAllTags();
		if(goodsTagsList.isEmpty()){
			 //获取mongo上的服务包数据
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
	 * Created on 2018年1月29日 .
	 * <p>
	 * Description {添加标签}
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
	 * Created on 2017年9月13日 .
	 * <p>
	 * Description {获取可添加给产品包的商品}
	 *
	 * @author guoxue 
	 * @param param
	 * @return Page2<Log>
	 */
	@RequestMapping(value = "getgoodspackagegoods")
	@ResponseBody
	public PageHelper<Goods> getGoodsPackageGoods(@RequestBody String param, HttpServletRequest request, HttpServletResponse response) {

		PageHelper<Goods> page = null;
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		GoodsFilter goodsFilter = obj.toJavaObject(GoodsFilter.class);
		// 分页step3
		page = goodsBizService.getGoodsPackageGoods(new Page(obj), goodsFilter);

		return page;
	}
	
	/**
	 * 
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量删除}
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
		// 根据id获取addGoodsPackageGoodsEntity
		List<GoodsPackageGoodsFilter> goodsPackageGoodslist = goodsBizService.getPackageGoods(list);
		
		return goodsPackageGoodslist;
	}
	
	/**
	 * 
	 * Created on 2017年6月13日 .
	 * <p>
	 * Description {去掉制表符等符号}
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
	 * Created on 2017年6月12日 .
	 * <p>
	 * Description {商品导入弹出页面}
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
	 * Created on 2017年6月12日 .
	 * <p>
	 * Description {商品导入}
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
    	// 文件存在
        if (multipartFile != null) {
            CommonsMultipartFile file = (CommonsMultipartFile) multipartFile;
            String type = ImportExcelConstants.GOODS;
        	resultObj = CheckExcelUtils.checkGoodsExcel(file, type);
        	// 校验成功，执行导入接口
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
	 * Created on 2017年6月12日 .
	 * <p>
	 * Description {商品导出}
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
        // 查询导出的数据
        PageHelper<Goods> pageHelper = goodsBizService.findGoodsAndPackageList(page, goodsFilter);
        List<Goods> list = pageHelper.getRows();
        
    	// 调用导出接口
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
					// 涓婁紶鐘舵��
					String setStatus = resJSON.getString("setStatus");
					if ("500".equals(setStatus)) {
						throw new Exception("涓婁紶鍥剧墖鏈嶅姟鍣ㄥけ璐�-500");
					}
					// 鍥剧墖鏈嶅姟鍣ㄨ矾寰�
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
					throw new Exception("涓婁紶鍥剧墖鏈嶅姟鍣�-鍙戠敓寮傚父" + e.getStackTrace());
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
			responseHelper.setFail("鎻愪氦澶辫触");
		}
		return filePathList;
	}

	public void deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 閫掑綊鍒犻櫎鐩綍涓殑瀛愮洰褰曚笅
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(dir, children[i]));
			}
		}
		// 鐩綍姝ゆ椂涓虹┖锛屽彲浠ュ垹闄�
		dir.delete();
	}
}
