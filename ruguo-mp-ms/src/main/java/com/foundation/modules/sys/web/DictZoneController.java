package com.foundation.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.constant.CommonConstant.DictStartStatus;
import com.foundation.common.constant.CommonConstant.DictZoneLevel;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.DictZoneFilter;
import com.foundation.modules.sys.service.DictZoneService;
import com.foundation.modules.sys.service.OrganizationService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;

/**
 * 
 * Created on 2017年3月16日
 * <p>
 * 区划管理Controller
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author yjun
 */
@Controller
@RequestMapping(value = "area/areamanager")
public class DictZoneController {
	@Autowired
	private DictZoneService sysDistrictService;

	@Autowired
	private OrganizationService sysOfficeService;

	@Autowired
	private ResponseHelper responseHelper;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * Created on 2017年3月16日 .
	 * <p>
	 * 区划管理菜单
	 *
	 * @author yjun
	 * @return String
	 */

	@RequestMapping(value = "index")
	public String dictZoneIndex() {
		return "layout1.dictzone.dictZoneList";
	}

	/**
	 * 
	 * Created on 2017年3月16日 .
	 * <p>
	 * 查询区划信息接口层
	 *
	 * @author yjun
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "getZoneInfoList")
	@ResponseBody
	public PageHelper<DictZone> getZoneInfoList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		DictZoneFilter filter = obj.toJavaObject(DictZoneFilter.class);
		PageHelper<DictZone> page = sysDistrictService.getDistrictList(new Page(obj), filter);
		return page;

	}

	/**
	 * 
	 * Created on 2017年3月16日 .
	 * <p>
	 * 添加下级区划对话框
	 *
	 * @author yjun
	 * @param param
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "addZoneDictInfo")
	public String AddZoneDictInfo(DictZoneFilter dictZoneFilter, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<DictZone> list = sysDistrictService.getDistrictList(dictZoneFilter);
		DictZone dictZone = null;
		if (list != null) {
			dictZone = list.get(0);
		}
		int level = Integer.parseInt(dictZone.getLevel());
		if (!DictZoneLevel.town.equals(level)) {
			level = level + 1;
		}
		dictZone.setLevel(String.valueOf(level));
		model.addAttribute("result", dictZone);
		return "layout3.dictzone.dictZoneAdd";
	}

	/**
	 * 
	 * Created on 2017年3月16日 .
	 * <p>
	 * 添加区划保存接口层
	 *
	 * @author yjun
	 * @param pojo
	 * @return String
	 */

	@RequestMapping(value = "saveDistrict")
	@ResponseBody
	public ResponseHelper saveDistrict(DictZone pojo) {
		pojo.setIsEnable(DictStartStatus.notstart);
		JSONObject result = sysDistrictService.saveDistrict(pojo);
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("保存信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("保存信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年3月16日 .
	 * <p>
	 * 编辑跳转
	 *
	 * @author Administrator
	 * @param param
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "modifyZoneDictInfo")
	public String ModifyZoneDictInfo(DictZoneFilter dictZoneFilter, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<DictZone> list = sysDistrictService.getDistrictList(dictZoneFilter);
		DictZone dictZone = null;
		if (list != null) {
			dictZone = list.get(0);
		}
		model.addAttribute("result", dictZone);
		return "layout3.dictzone.dictZoneModify";

	}

	/**
	 * 
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划编辑保存接口层
	 *
	 * @author yjun
	 * @param pojo
	 * @return String
	 */
	@RequestMapping(value = "modifyDistrict")
	@ResponseBody
	public ResponseHelper modifyDistrict(DictZone pojo) {
		String id = pojo.getParentId();
		DictZoneFilter dictZoneFilter = new DictZoneFilter();
		dictZoneFilter.setId(id);
		List<DictZone> list = sysDistrictService.getDistrictList(dictZoneFilter);
		DictZone dict = null;
		if (list != null) {
			dict = list.get(0);
		}
		dict.setName(pojo.getName());
		dict.setCode(pojo.getCode());
		dict.setLevel(pojo.getLevel());

		// 根据区划级别赋值
		if (pojo.getLevel().equals(DictZoneLevel.provice)) {

			dict.setProvince(pojo.getName());
		} else if (pojo.getLevel().equals(DictZoneLevel.city)) {
			dict.setCity(pojo.getName());

		} else if (pojo.getLevel().equals(DictZoneLevel.county)) {
			dict.setCounty(pojo.getName());
		} else if (pojo.getLevel().equals(DictZoneLevel.town)) {
			dict.setTown(pojo.getName());

		}

		dict.setZoneTag(pojo.getZoneTag());
		dict.setIsPoorCounty(pojo.getIsPoorCounty());
		dict.setIsAllCover(pojo.getIsAllCover());
		JSONObject result = sysDistrictService.saveDistrict(dict);
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("保存区划信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("保存区划信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;

	}

	/**
	 * 
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划删除单笔接口层
	 *
	 * @author yjun
	 * @param pojo
	 * @return String
	 */
	@RequestMapping(value = "deleteZoneDictInfo")
	@ResponseBody
	public ResponseHelper deleteZoneDictInfo(DictZone pojo) {
		String id = pojo.getId();
		JSONObject result = sysDistrictService.deleteDistrict(id);
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("删除信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("删除信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;

	}

	/**
	 * 
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划删除批量接口层
	 *
	 * @author yjun
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "deleteZoneDictList")
	@ResponseBody
	public ResponseHelper deleteZoneDictList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		JSONObject result = sysDistrictService.deleteDistrict(list);

		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("删除信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("删除信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;

	}

	/**
	 * 
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划批量启用接口层
	 *
	 * @author yjun
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "startUseDistrictList")
	@ResponseBody
	public ResponseHelper startUseDistrictList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		JSONObject result = sysDistrictService.startUseDistrict(list);
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("启用信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setFail("启用信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划批量停用接口层
	 *
	 * @author yjun
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "stopUseDistrictList")
	@ResponseBody
	public ResponseHelper stopUseDistrictList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		JSONArray id = obj.getJSONArray("id");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < id.size(); i++) {
			list.add(id.getJSONObject(i).getString("id"));
		}
		JSONObject result = sysDistrictService.stopUseDistrict(list);
		if (CommonConstant.RESULT_SUCCESS.equals(result.get(CommonConstant.SUCCESS))) {
			responseHelper.setSuccess("停用信息成功！");// 返回成功状态 和 消息
		} else {
			responseHelper.setSuccess("停用信息失败：" + result.get(CommonConstant.ERROR_MSG));
		}
		return responseHelper;
	}

	/**
	 *
	 * Created on 2017年3月20日 .
	 * <p>
	 * 区划批量启用接口层
	 *
	 * @author yjun
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "startAllOrg")
	@ResponseBody
	public ResponseHelper startAllOrg(@RequestBody String param) {
		sysOfficeService.findByList();
		responseHelper.setSuccess("启用信息成功！");// 返回成功状态 和 消息
		return responseHelper;
	}

}
