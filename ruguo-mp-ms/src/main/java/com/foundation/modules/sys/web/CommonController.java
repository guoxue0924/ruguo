package com.foundation.modules.sys.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.modules.sys.entity.Dict;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.DictCommonUtils;
import com.foundation.modules.sys.utils.DictUtils;
import com.foundation.modules.sys.utils.DictZoneUtils;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * Created on 2017年04月21日 Description Copyright tuling (c) 2017 .
 *
 * @author liuqing
 */
@Controller
@RequestMapping(value = "common/")
public class CommonController {

	//private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Created on 2017年3月16日 Description 数据保存接口层
	 * 
	 * @author liuqing
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getCurrentUser")
	@ResponseBody
	public User getCurrentUser(@RequestBody String param) {
		User user = UserUtils.getUser();
		return user;
	}

	/**
	 * Created on 2017年3月16日 Description 数据保存接口层
	 * 
	 * @author liuqing
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getCurrentDate")
	@ResponseBody
	public String getCurrentDate(@RequestBody String param) {
		Date currentDate = DictCommonUtils.getCurrentDate();
		JSONObject object = new JSONObject();
		object.put("currentDate", currentDate);
		return object.toString();
	}

	/**
	 * 获取当前用户菜单权限标识
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-27
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getAuth")
	@ResponseBody
	public Collection<String> getCurrentUserAuth(@RequestBody String param) {
		AuthorizationInfo info = (AuthorizationInfo) UserUtils.getCache(UserUtils.CACHE_AUTH_INFO);
		return info.getStringPermissions();
	}

	/**
	 * 获取codelist数据集
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-10
	 */
	@RequestMapping(value = "getCodelist", method = RequestMethod.POST)
	@ResponseBody
	public String getCodelist(@RequestBody String param, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String[] codeTypeList = request.getParameterValues("codeTypeList[]");

		for (String codeType : codeTypeList) {
			List<Dict> dictList = DictUtils.getDictListNoBlank(codeType);
			if (dictList == null || dictList.size() == 0) {
				continue;
			}
			JSONArray jsonArray = new JSONArray();
			for (Dict dict : dictList) {
				JSONObject o = new JSONObject();
				o.put("id", dict.getId());
				o.put("value", dict.getValue());
				o.put("description", dict.getDescription());
				o.put("label", dict.getLabel());
				o.put("parentId", dict.getParentId());
				o.put("sort", dict.getSort());
				o.put("type", dict.getType());
				o.put("remark", dict.getRemark());
				jsonArray.add(o);
			}
			result.put(codeType, jsonArray);
		}
		return result.toString();
	}

	/**
	 * 查询省、市、县、乡
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-06-17
	 */
	@RequestMapping(value = "getDictZoneList")
	@ResponseBody
	public Map<String,Object> getDictZoneList(@RequestBody String param) {
//		JSONObject result = new JSONObject();

		Map<String,Object> result = new HashMap<String, Object>();
		
		JSONObject obj = JSONObject.parseObject(param);
		String rootCode = obj.getString("rootCode");
		String provinceCode = obj.getString("provinceCode");
		String cityCode = obj.getString("cityCode");
		String countyCode = obj.getString("countyCode");
		String isEnable = obj.getString("isEnable");

		if (!StringUtils.isEmpty(rootCode)) {
			List<DictZone> provincelist = new ArrayList<DictZone>();
			provincelist = DictZoneUtils.getDictZoneByParentCode(rootCode, isEnable);
			result.put("provincelist", provincelist);
		}
		if (!StringUtils.isEmpty(provinceCode)) {
			List<DictZone> citylist = new ArrayList<DictZone>();
			citylist = DictZoneUtils.getDictZoneByParentCode(provinceCode, isEnable);
			result.put("citylist", citylist);
		}
		if (!StringUtils.isEmpty(cityCode)) {
			List<DictZone> countylist = new ArrayList<DictZone>();
			countylist = DictZoneUtils.getDictZoneByParentCode(cityCode, isEnable);
			result.put("countylist", countylist);
		}
		if (!StringUtils.isEmpty(countyCode)) {
			List<DictZone> townlist = new ArrayList<DictZone>();
			townlist = DictZoneUtils.getDictZoneByParentCode(countyCode, isEnable);
			result.put("townlist", townlist);
		}
		return result;
	}

	/**
	 * 查询省、市、县、乡（带权限过滤）
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-06-18
	 */
	@RequestMapping(value = "getDictZoneListForAuth")
	@ResponseBody
	public Map<String,Object> getDictZoneListForAuth(@RequestBody String param) {
//		JSONObject result = new JSONObject();
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		JSONObject obj = JSONObject.parseObject(param);
		String rootCode = obj.getString("rootCode");
		String provinceCode = obj.getString("provinceCode");
		String cityCode = obj.getString("cityCode");
		String countyCode = obj.getString("countyCode");
		String isEnable = obj.getString("isEnable");

		// 当前登录人所属区划代码
		String zoneCode = UserUtils.getUser().getOwnZoneCode();
		// 当前登录人所属区划
		DictZone userDictZone = DictZoneUtils.getDictZoneByCode(zoneCode);
		String userDictZoneLevel = userDictZone != null ? userDictZone.getLevel() : "0";

		if (!StringUtils.isEmpty(rootCode)) {
			List<DictZone> provincelist = new ArrayList<DictZone>();

			if (Integer.valueOf(userDictZoneLevel) > Integer.valueOf(CommonConstant.DictZoneLevel.state)) {
				DictZone dictZone = DictZoneUtils.getDictZoneByCode(userDictZone.getProvinceCode());
				provincelist.add(dictZone);
			} else {
				provincelist = DictZoneUtils.getDictZoneByParentCode(rootCode, isEnable);
			}
			result.put("provincelist", provincelist);
		}
		if (!StringUtils.isEmpty(provinceCode)) {
			List<DictZone> citylist = new ArrayList<DictZone>();
			if (Integer.valueOf(userDictZoneLevel) > Integer.valueOf(CommonConstant.DictZoneLevel.provice)) {
				DictZone dictZone = DictZoneUtils.getDictZoneByCode(userDictZone.getCityCode());
				citylist.add(dictZone);
			} else {
				citylist = DictZoneUtils.getDictZoneByParentCode(provinceCode, isEnable);
			}
			result.put("citylist", citylist);
		}
		if (!StringUtils.isEmpty(cityCode)) {
			List<DictZone> countylist = new ArrayList<DictZone>();
			if (Integer.valueOf(userDictZoneLevel) > Integer.valueOf(CommonConstant.DictZoneLevel.city)) {
				DictZone dictZone = DictZoneUtils.getDictZoneByCode(userDictZone.getCountyCode());
				countylist.add(dictZone);
			} else {
				countylist = DictZoneUtils.getDictZoneByParentCode(cityCode, isEnable);
			}
			result.put("countylist", countylist);
		}
		if (!StringUtils.isEmpty(countyCode)) {
			List<DictZone> townlist = new ArrayList<DictZone>();
			if (Integer.valueOf(userDictZoneLevel) > Integer.valueOf(CommonConstant.DictZoneLevel.county)) {
				DictZone dictZone = DictZoneUtils.getDictZoneByCode(userDictZone.getTownCode());
				townlist.add(dictZone);
			} else {
				townlist = DictZoneUtils.getDictZoneByParentCode(countyCode, isEnable);
			}
			result.put("townlist", townlist);
		}
		return result;
	}
}