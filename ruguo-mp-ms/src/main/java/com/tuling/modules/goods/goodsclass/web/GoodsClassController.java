package com.tuling.modules.goods.goodsclass.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goodsclass.entity.GoodsClassInfo;
import com.foundation.common.constant.CommonConstant;
import com.tuling.modules.goods.goodsclass.service.GoodsClassBizService;

/**
 * goodsClass Controller
 * @author guoxue 
 * @date 2017-06-17
 */
@Controller
@RequestMapping(value = "goodsclass/goodsclass")
public class GoodsClassController extends BaseController{
	
	@Autowired
	private GoodsClassBizService goodsClassBizService;
	
	
	/**
	 * 
	 * Created on 2017年6月17日 .
	 * <p>
	 * Description {商品分类管理首页}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String itemIndex(Model model) {
		return "layout1.goods.goodsclass.goodsClassIndex";
	}
	
	/**
	 * 
	 * Created on 2017年6月17日 .
	 * <p>
	 * Description {商品分类管理首页}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "indexwithcondation")
	@ResponseBody
	public JSONObject itemIndexWithCondation(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		String id = obj.getString("id");
		JSONObject result = new JSONObject();
		if (!StringUtils.isBlank(id)) {
			GoodsClassInfo entity = goodsClassBizService.findById(id);
			result.put("id", entity.getId());
			result.put("parentClassCode", entity.getParentClassCode());
			result.put("code", entity.getGoodsClassCode());
			result.put("name", entity.getGoodsClassName());
			result.put("goodsClassDesc", entity.getGoodsClassDesc());
			result.put("isMinClass", entity.getIsMinClass());
			result.put("classLevel", entity.getClassLevel());
			result.put("classSeqNum", entity.getClassSeqNum());
		}
		return result;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {保存分类信息}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param request
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "edit")
	@ResponseBody
	public ResponseHelper edit(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {
		String id = goodsClassInfo.getId();
		if ( StringUtils.isBlank(id)) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		String goodsClassCode = goodsClassInfo.getGoodsClassCode();
		String isMinClass = goodsClassInfo.getIsMinClass();
		GoodsClassInfo parentEntity = goodsClassBizService.findById(id);
		goodsClassInfo.setParentClassCode(parentEntity.getParentClassCode());
		String goodsClassName = goodsClassInfo.getGoodsClassName();

		if (!goodsClassName.equals(parentEntity.getGoodsClassName())) {
			GoodsClassInfo haveGoodsClassInfo = goodsClassBizService.findByCodeAndName(goodsClassInfo);
			if (haveGoodsClassInfo != null) {
				responseHelper.setFail("此分类名称已经存在！");// 返回成功状态 和 消息
				return responseHelper;
			}
		}

		if ((CommonConstant.isMinClass.DICT_ISMINCLASS_ONE).equals(isMinClass)) {
			List<GoodsClassInfo> entityList = null;
			entityList = goodsClassBizService.findByParentCode(goodsClassCode);
			if (entityList.size() > 0) {
				responseHelper.setFail("分类下包含下级分类，不能修改为是最小分类！");// 返回成功状态 和 消息
				return responseHelper;
			}
		}

		goodsClassBizService.save(goodsClassInfo);
		responseHelper.setData(id);
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {删除分类信息}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseHelper delete(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {
		String goodsClassCode = goodsClassInfo.getGoodsClassCode();
		List<GoodsClassInfo> entityList = null;
		
		
		if("0".equals(goodsClassCode)){
			responseHelper.setFail("分类目录，不能修删除！");// 返回成功状态 和 消息
			return responseHelper;
		}
		
		entityList = goodsClassBizService.findByParentCode(goodsClassCode);
		if (entityList.size() > 0) {
			responseHelper.setFail("分类下包含下级分类，不能修删除！");// 返回成功状态 和 消息
			return responseHelper;
		}
		//查询商品表此分类是否被使用
		Goods goods = new Goods();
		goods.setGoodsClassCode(goodsClassCode);
		List<Goods> list = goodsClassBizService.findGoodsByClassCode(goods);
		if(list.size()>0){
			responseHelper.setFail("分类下存在商品，不能修删除！");// 返回成功状态 和 消息
			return responseHelper;
		}

		goodsClassInfo.setDelFlag(CommonConstant.DelFlag.delete);
		goodsClassBizService.save(goodsClassInfo);
		responseHelper.setSuccess("删除信息成功");// 返回成功状态 和 消息
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {添加子分类信息}
	 *
	 * @author  guoxue
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {
		String id = goodsClassInfo.getId();
		if ( StringUtils.isBlank(id)) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		GoodsClassInfo parentEntity = goodsClassBizService.findById(id);
		String parentGoodsClassCode = parentEntity.getGoodsClassCode();
		String parentIsMinClass = parentEntity.getIsMinClass();

		goodsClassInfo.setParentClassCode(parentEntity.getGoodsClassCode());
		GoodsClassInfo haveGoodsClassInfo = goodsClassBizService.findByCodeAndName(goodsClassInfo);
		if (haveGoodsClassInfo != null) {
			responseHelper.setFail("此分类名称已经存在！");// 返回成功状态 和 消息
			return responseHelper;
		}

		int classSeqNum = 0;
		String classLevel = "";
		if ((CommonConstant.isMinClass.DICT_ISMINCLASS_ONE).equals(parentIsMinClass)) {
			responseHelper.setFail("分类是最小分类，不能添加下级分类！");// 返回成功状态 和 消息
			return responseHelper;
		} else {
			List<GoodsClassInfo> entityList = null;
			entityList = goodsClassBizService.findByParentCode(parentGoodsClassCode);

			if (entityList.size() > 0) {
				for (int i = 0; i < entityList.size(); i++) {
					if (classSeqNum < Integer.parseInt(entityList.get(i).getClassSeqNum())) {
						classSeqNum = Integer.parseInt(entityList.get(i).getClassSeqNum());
					}
				}
				classLevel = entityList.get(0).getClassLevel();
			} else {
				classSeqNum = 1;
				classLevel = (Integer.parseInt(parentEntity.getClassLevel()) + 1) + "";
			}

		}

		GoodsClassInfo newGoodsClassInfo = new GoodsClassInfo();
		newGoodsClassInfo.setGoodsClassCode(parentGoodsClassCode+"#"+(int) (Math.random() * 90000 + 10000) + "");
		newGoodsClassInfo.setGoodsClassName(goodsClassInfo.getGoodsClassName());
		newGoodsClassInfo.setGoodsClassDesc(goodsClassInfo.getGoodsClassDesc());
		newGoodsClassInfo.setIsMinClass(goodsClassInfo.getIsMinClass());
		newGoodsClassInfo.setParentClassCode(parentGoodsClassCode);
		newGoodsClassInfo.setClassLevel(classLevel);
		newGoodsClassInfo.setClassSeqNum("000" + (classSeqNum + 1));

		goodsClassBizService.save(newGoodsClassInfo);
		responseHelper.setData(id);
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {分类 的上移}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "moveup")
	@ResponseBody
	
	public ResponseHelper moveUp(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {

		if (StringUtils.isBlank(goodsClassInfo.getId())) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		GoodsClassInfo entity = goodsClassBizService.findById(goodsClassInfo.getId());
		String parentClassCode = entity.getParentClassCode();
		GoodsClassInfo changeEntity = null;

		// 是root位，无法上移
		if (StringUtils.isBlank(parentClassCode)) {
			responseHelper.setFail("此分类无法上移！");// 返回成功状态 和 消息
			return responseHelper;
		} else {
			List<GoodsClassInfo> list = goodsClassBizService.findByParentCode(parentClassCode);
			if (list.size() == 1) {
				// 父级分类下只有自己，无法上移
				responseHelper.setFail("此分类无法上移！");// 返回成功状态 和 消息
				return responseHelper;
			} else {
				//获取在同一父级别下seqnum小于当前选择分类的分类list
				List<GoodsClassInfo> listMin = goodsClassBizService.findUpByCondation(entity);
				if (listMin.size() == 0) {
					// 已在所有分类最顶端，无法上移
					responseHelper.setFail("此分类无法上移！");// 返回成功状态 和 消息
					return responseHelper;
				} else {
					// 进行上移动作，与最近的del_flag不为1的交换
					String entityClassSeqNum = entity.getClassSeqNum();
					String changeEntityClassSeqNum = listMin.get(0).getClassSeqNum();
					
					changeEntity = listMin.get(0);
					
					entity.setClassSeqNum(changeEntityClassSeqNum);
					changeEntity.setClassSeqNum(entityClassSeqNum);
					
					goodsClassBizService.saveChange(entity,changeEntity);
					
					responseHelper.setData(goodsClassInfo.getId());
					responseHelper.setSuccess("上移成功！");// 返回成功状态 和 消息
				}
			}

		}

		return responseHelper;
	}
	
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {分类 的下移}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "movedown")
	@ResponseBody
	public ResponseHelper moveDown(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {

		if (StringUtils.isBlank(goodsClassInfo.getId())) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		GoodsClassInfo entity = goodsClassBizService.findById(goodsClassInfo.getId());
		String parentClassCode = entity.getParentClassCode();
		GoodsClassInfo changeEntity = null;

		// 是root位，无法下移
		if (StringUtils.isBlank(parentClassCode)) {
			responseHelper.setFail("此分类无法下移！");// 返回成功状态 和 消息
			return responseHelper;
		} else {
			List<GoodsClassInfo> list = goodsClassBizService.findByParentCode(parentClassCode);
			if (list.size() == 1) {
				// 父级分类下只有自己，无法下移
				responseHelper.setFail("此分类无法下移！");// 返回成功状态 和 消息
				return responseHelper;
			} else {
				List<GoodsClassInfo> listMin = goodsClassBizService.findDownByCondation(entity);
				if (listMin.size() == 0) {
					// 已在所有分类最底端，无法下移
					responseHelper.setFail("此分类无法下移！");// 返回成功状态 和 消息
					return responseHelper;
				} else {
					// 进行下移动作，与最近的del_flag不为1的交换
					String entityClassSeqNum = entity.getClassSeqNum();
					String changeEntityClassSeqNum = listMin.get(0).getClassSeqNum();

					changeEntity = listMin.get(0);
					
					entity.setClassSeqNum(changeEntityClassSeqNum);
					changeEntity.setClassSeqNum(entityClassSeqNum);
					
					goodsClassBizService.saveChange(entity,changeEntity);
					
					responseHelper.setData(goodsClassInfo.getId());
					responseHelper.setSuccess("下移成功！");// 返回成功状态 和 消息
				}
			}
		}
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {分类 的置顶}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "movetop")
	@ResponseBody
	public ResponseHelper moveTop(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {

		if (StringUtils.isBlank(goodsClassInfo.getId())) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		GoodsClassInfo entity = goodsClassBizService.findById(goodsClassInfo.getId());
		String parentClassCode = entity.getParentClassCode();

		// 是root位，无法置顶
		if (StringUtils.isBlank(parentClassCode)) {
			responseHelper.setFail("此分类无法置顶！");// 返回成功状态 和 消息
			return responseHelper;
		} else {
			List<GoodsClassInfo> list = goodsClassBizService.findByParentCode(parentClassCode);
			if (list.size() == 1) {
				// 父级分类下只有自己，无法置顶
				responseHelper.setFail("此分类无法置顶！");// 返回成功状态 和 消息
				return responseHelper;
			} else {
				List<GoodsClassInfo> listMin = goodsClassBizService.findUpByCondation(entity);
				if (listMin.size() == 0) {
					// 已在所有分类最顶端，无法置顶
					responseHelper.setFail("此分类无法置顶！");// 返回成功状态 和 消息
					return responseHelper;
				} else {
						goodsClassBizService.saveTop(entity,listMin);
				}
				responseHelper.setData(goodsClassInfo.getId());
				responseHelper.setSuccess("置顶成功！");// 返回成功状态 和 消息
			}
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {分类 的置底}
	 *
	 * @author guoxue 
	 * @param goodsClassInfo
	 * @param response
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "movebottom")
	@ResponseBody
	public ResponseHelper moveBottom(GoodsClassInfo goodsClassInfo, HttpServletResponse response) {

		if (StringUtils.isBlank(goodsClassInfo.getId())) {
			responseHelper.setFail("请选择一个分类！");// 返回成功状态 和 消息
			return responseHelper;
		}
		GoodsClassInfo entity = goodsClassBizService.findById(goodsClassInfo.getId());
		String parentClassCode = entity.getParentClassCode();

		// 是root位，无法置底
		if (StringUtils.isBlank(parentClassCode)) {
			responseHelper.setFail("此分类无法置底！");// 返回成功状态 和 消息
			return responseHelper;
		} else {
			List<GoodsClassInfo> list = goodsClassBizService.findByParentCode(parentClassCode);
			if (list.size() == 1) {
				// 父级分类下只有自己，无法置底
				responseHelper.setFail("此分类无法置底！");// 返回成功状态 和 消息
				return responseHelper;
			} else {
				List<GoodsClassInfo> listMin = goodsClassBizService.findDownByCondation(entity);
				if (listMin.size() == 0) {
					// 已在所有分类最底端，无法置底
					responseHelper.setFail("此分类无法置底！");// 返回成功状态 和 消息
					return responseHelper;
				} else {
					goodsClassBizService.saveDown(entity,listMin);
				}
				responseHelper.setData(goodsClassInfo.getId());
				responseHelper.setSuccess("置底成功！");// 返回成功状态 和 消息
			}
		}

		return responseHelper;
	}
	
	 /**
	  * 
	  * Created on 2017年6月20日 .
	  * <p>
	  * Description {根据父分类Id查询子分类}
	  *
	  * @author guoxe 
	  * @param param
	  * @return JSONArray
	  */
    @RequiresPermissions("user")
    @RequestMapping(value = "getgoodsclassbyparentcode")
    @ResponseBody
	public JSONArray getGoodsClassByParentCode(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray result = new JSONArray();
		List<GoodsClassInfo> entityList = null;
		GoodsClassInfo goodsClassInfo = goodsClassBizService.findById(obj.getString("parentId"));
		entityList = goodsClassBizService.findByParentCode(goodsClassInfo.getGoodsClassCode());
		for (GoodsClassInfo entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentClassCode", entity.getParentClassCode());
			jsonObject.put("code", entity.getGoodsClassCode());
			jsonObject.put("name", entity.getGoodsClassName());
			jsonObject.put("isMinClass", entity.getIsMinClass());
			jsonObject.put("classLevel", entity.getClassLevel());
			jsonObject.put("classSeqNum", entity.getClassSeqNum());
			jsonObject.put("isParent", true);
			result.add(jsonObject);
		}

		return result;
	}
	
    /**
     * 
     * Created on 2017年6月17日 .
     * <p>
     * Description {根据id查询商品分类}
     *
     * @author guoxue 
     * @param param
     * @return JSONArray
     */
    @SuppressWarnings("null")
	@RequiresPermissions("user")
    @RequestMapping(value = "getgoodsclassbyid")
    @ResponseBody
	public JSONArray getGoodsClassById(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		String onlyChild = obj.getString("onlyChild");
		String onlySelf = obj.getString("onlySelf");
		String id = obj.getString("id");

		List<GoodsClassInfo> entityList = null;
		String parentCode = null;
		JSONArray result = new JSONArray();
		if ("true".equals(onlySelf)) {
			entityList = new ArrayList<GoodsClassInfo>();
			GoodsClassInfo entity = goodsClassBizService.findById(id);
			entityList.add(entity);
		} else {
			entityList = new ArrayList<GoodsClassInfo>();
			GoodsClassInfo entity = goodsClassBizService.findById(id);
			entityList.add(entity);
			parentCode = entity.getParentClassCode();
		}
		for (GoodsClassInfo entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentClassCode", parentCode);
			jsonObject.put("code", entity.getGoodsClassCode());
			jsonObject.put("name", entity.getGoodsClassName());
			jsonObject.put("isMinClass", entity.getIsMinClass());
			jsonObject.put("classLevel", entity.getClassLevel());
			jsonObject.put("classSeqNum", entity.getClassSeqNum());

			if ("true".equals(onlySelf)) {
				jsonObject.put("isParent", false);
			} else {
				jsonObject.put("isParent", true);
			}
			result.add(jsonObject);
		}

		if ("true".equals(onlyChild) && !"true".equals(onlySelf) && entityList != null && entityList.size() > 0) {
			List<GoodsClassInfo> childList = null;
			childList = goodsClassBizService.findByParentCode(parentCode);

			for (GoodsClassInfo entity : childList) {
				// GoodsClassInfo goodsClassInfo =
				// goodsClassBizService.getGoodsClass(entity.getParentClassCode());
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", entity.getId());
				jsonObject.put("parentClassCode", entity.getParentClassCode());
				jsonObject.put("code", entity.getGoodsClassCode());
				jsonObject.put("name", entity.getGoodsClassName());
				jsonObject.put("isMinClass", entity.getIsMinClass());
				jsonObject.put("classLevel", entity.getClassLevel());
				jsonObject.put("classSeqNum", entity.getClassSeqNum());
				jsonObject.put("isParent", false);
				result.add(jsonObject);
			}
		}
		return result;
	}	
    
    /**
	  * 
	  * Created on 2017年6月20日 .
	  * <p>
	  * Description {根据父分类Id查询子分类}
	  *
	  * @author guoxe 
	  * @param param
	  * @return JSONArray
	  */
   @RequiresPermissions("user")
   @RequestMapping(value = "getgoodsclassallbyrootid")
   @ResponseBody
	public JSONArray getGoodsClassAllByRootId(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		String id = obj.getString("id");

		JSONArray result = new JSONArray();
		List<GoodsClassInfo> entityList = goodsClassBizService.findAll();

		for (GoodsClassInfo entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentClassCode", entity.getParentClassCode());
			jsonObject.put("code", entity.getGoodsClassCode());
			jsonObject.put("name", entity.getGoodsClassName());
			jsonObject.put("isMinClass", entity.getIsMinClass());
			jsonObject.put("classLevel", entity.getClassLevel());
			jsonObject.put("classSeqNum", entity.getClassSeqNum());
			if (!StringUtils.isBlank(id)) {
				if (id.equals(entity.getId())) {
					jsonObject.put("checked", true);
				}
			}
			jsonObject.put("isParent", true);
			result.add(jsonObject);
		}
		return result;
	}
	

}
