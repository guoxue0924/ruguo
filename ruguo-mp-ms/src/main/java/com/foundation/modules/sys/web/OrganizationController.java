package com.foundation.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.OrganizationService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "organization/organ")
public class OrganizationController {

	@Autowired
	private OrganizationService sysOfficeService;

	@Autowired
	private ResponseHelper responseHelper;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String organizationIndex() {
		// 调用接口获取数据

		return "layout1.organization.organList";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "indexInside")
	public String organizationIndexInside() {
		// 调用接口获取数据

		return "layout1.organization.organListInside";
	}

	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {查询列表}
	 *
	 * @author caihe
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<Organization> getOrganizationList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Organization umOrganization = obj.toJavaObject(Organization.class);
		PageHelper<Organization> page = sysOfficeService.getOfficeList(new Page(obj), umOrganization);
		return page;
	}
	
	
	/**
	 * 
	 * Created on 2017年9月4日 .
	 * <p>
	 * Description {查询内部列表}
	 *
	 * @author guoxue
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "listInside")
	@ResponseBody
	public PageHelper<Organization> getOrganizationListInside(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Organization umOrganization = obj.toJavaObject(Organization.class);
		PageHelper<Organization> page = sysOfficeService.getOfficeListInside(new Page(obj), umOrganization);
		return page;
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "add")
	public String organizationAdd(Model model) {
		// 调用接口获取数据
		Organization  organization = new Organization();
		String orgCode = IdGen.uuid().substring(0, 23);
		organization.setCode(orgCode);
		model.addAttribute("result", organization);
		return "layout3.organization.organEdit";
	}
	
	/**
	 * 
	 * Created on 2017年9月5日 .
	 * <p>
	 * Description {添加内部机构}
	 *
	 * @author guoxue
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "addInside")
	public String organizationAddInside() {
		// 调用接口获取数据

		return "layout3.organization.organEditInside";
	}


	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {插入}
	 *
	 * @author caihe
	 * @param pojo
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper organizationSave(Organization pojo, HttpServletRequest request)
			throws UnsupportedEncodingException {
		User user = UserUtils.getUser();
		pojo.setDelFlag(CommonConstant.DelFlag.normal);
		pojo.setCreateBy(user);
		pojo.setCreateTime(new Date());
		pojo.setUpdateBy(user);
		pojo.setUpdateTime(new Date());
		pojo.setOrgPlatformType("2");
		// JSONObject code =
		// sysOfficeService.findByCode(pojo.getOwnZoneCode(),pojo.getId());
		JSONObject name = sysOfficeService.findByName(pojo.getName(), pojo.getCode(), pojo.getId());
		if (name.getString("result").equals(CommonConstant.RESULT_SUCCESS)) {

			sysOfficeService.saveOffice(pojo);
			responseHelper.setSuccess("保存信息成功！");

		} else {
			responseHelper.setFail(name.get("content").toString());
		}
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {内部机构插入}
	 *
	 * @author guoxue
	 * @param pojo
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saveInside")
	@ResponseBody
	public ResponseHelper organizationSaveInside(Organization pojo, HttpServletRequest request)
			throws UnsupportedEncodingException {
		User user = UserUtils.getUser();
		pojo.setDelFlag(CommonConstant.DelFlag.normal);
		pojo.setCreateBy(user);
		pojo.setCreateTime(new Date());
		pojo.setUpdateBy(user);
		pojo.setUpdateTime(new Date());
		pojo.setOrgPlatformType(CommonConstant.orgPlatformType.DICT_ORG_PLATFORM_TYPE_ONE);
		String orgCode = IdGen.uuid();
		pojo.setCode(orgCode);
		// JSONObject code =
		// sysOfficeService.findByCode(pojo.getOwnZoneCode(),pojo.getId());
		JSONObject name = sysOfficeService.findByName(pojo.getName(), pojo.getCode(), pojo.getId());
		if (name.getString("result").equals(CommonConstant.RESULT_SUCCESS)) {
			sysOfficeService.saveOffice(pojo);
			responseHelper.setSuccess("保存信息成功！");

		} else {
			responseHelper.setFail(name.get("content").toString());
		}
		return responseHelper;
	}
	
	/**
	 * 
	 * Created on 2017年9月5日 .
	 * <p>
	 * Description {内部机构详情}
	 *
	 * @author guoxue
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "detailInside")
	public String organizationDetailInside(String id, Model model) {
		Organization umOrganization = sysOfficeService.findById(id);
		model.addAttribute("result", umOrganization);
		return "layout3.organization.organDetailInside";
	}
	
	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {修改}
	 *
	 * @author caihe
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update")
	public String organizationUpdate(String id, Model model) {
		// JSONObject filterData = CommonPagerUtil.getJsonObject(pojo);
		// Model model = null;
		Organization umOrganization = sysOfficeService.findById(id);

		String quhua = "{\'provinceCode\': \'" + umOrganization.getProvinceCode() + "\',\'cityCode\': \'"
				+ umOrganization.getCityCode() + "\',\'countyCode\': \'" + umOrganization.getCountyCode()
				+ "\',\'townCode\':\'" + umOrganization.getTownCode() + "\'}";
		// quhua.replace("\"", "\\"");
		model.addAttribute("result", umOrganization);
		model.addAttribute("quhua", quhua);
		return "layout3.organization.organEdit";
	}
	
	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {修改}
	 *
	 * @author caihe
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "updateInside")
	public String organizationUpdateInside(String id, Model model) {
		// JSONObject filterData = CommonPagerUtil.getJsonObject(pojo);
		// Model model = null;
		Organization umOrganization = sysOfficeService.findById(id);
		model.addAttribute("result", umOrganization);
		return "layout3.organization.organEditInside";
	}

	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {删除}
	 *
	 * @author caihe
	 * @param pojo
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "deleteRecord")
	@ResponseBody
	public ResponseHelper organizationDel(String id) {
		// JSONObject obj = JSONObject.parseObject(pojo);
		String[] ids = new String[] { id };
		JSONObject json = sysOfficeService.deleteOffice(ids);
		if (json.getString("result").equals(CommonConstant.RESULT_SUCCESS)) {
			responseHelper.setSuccess("删除信息成功！");
		} else {
			responseHelper.setFail("删除信息失败！");
		}
		return responseHelper;
		// return str;
	}

	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {启用停用}
	 *
	 * @author caihe
	 * @param pojo
	 * @return String
	 */
	/*
	 * @RequiresPermissions("user")
	 * 
	 * @RequestMapping(value = "enableRecord")
	 * 
	 * @ResponseBody public String organizationEnable(@RequestBody String pojo)
	 * { JSONObject json = null; try { JSONObject obj =
	 * JSONObject.parseObject(pojo); JSONArray id = obj.getJSONArray("id");
	 * String str = obj.getString("isEnable");
	 * 
	 * List<Organization> lists = new ArrayList<Organization>(); for (int i = 0;
	 * i < id.size(); i++) { Organization org = new Organization();
	 * org.setId(id.getJSONObject(i).getString("id"));
	 * org.setIsEnable(obj.getString("isEnable")); if
	 * (str.equals(CommonConstant.DictStartStatus.start)) {
	 * org.setEnableDate(new Date()); // 自动生成服务机构账号
	 * sysOfficeService.automaticSysUser(org); }
	 * 
	 * lists.add(org); } json = sysOfficeService.whetherIsEnables(lists); }
	 *  (Exception e) { log.error(ExceptionUtils.getAllExp(e)); }
	 * 
	 * // obj.put("result","0"); return json.toString(); // return str; }
	 */

	/**
	 * 
	 * Created on 2017年3月21日 .
	 * <p>
	 * Description {批量刪除}
	 *
	 * @author caihe
	 * @param pojo
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "deleteRecordList")
	@ResponseBody
	public String deleteRecordList(@RequestBody String pojo) {
		JSONObject obj = JSONObject.parseObject(pojo);
		JSONArray id = obj.getJSONArray("id");
		String[] ids = new String[] {};
		for (int i = 0; i < id.size(); i++) {
			ids = insert(ids, id.getJSONObject(i).getString("id").toString());
		}
		JSONObject json = sysOfficeService.deleteOffice(ids);
		// obj.put("result","0");
		return json.toString();
		// return str;
	}

	private static String[] insert(String[] arr, String str) {
		int size = arr.length;
		String[] tmp = new String[size + 1];
		System.arraycopy(arr, 0, tmp, 0, size);
		tmp[size] = str;
		return tmp;
	}

}
