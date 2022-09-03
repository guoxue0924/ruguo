package com.foundation.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.security.Digests;
import com.foundation.common.utils.Encodes;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.OrganizationService;
import com.foundation.modules.sys.service.SysUserService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * Created on 2017年04月18日 Description Copyright tuling (c) 2017 .
 *
 * @author liuqing
 */

@Controller
@RequestMapping(value = "common/selectTree")
public class SelectTreeController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private SysUserService sysUserService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 机构选择对话框
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "showOrgDialog")
	public String showOrgDialog() {
		return "layout3.tree.orgTreeselect";
	}

	/**
	 * 用户选择对话框
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "showUserDialog")
	public String showUserDialog() {
		return "layout3.tree.userTreeselect";
	}

	/**
	 * 菜单选择树
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "showMenuDialog")
	public String showMenuDialog() {
		return "layout3.tree.menuTreeselect";
	}

	/**
	 * 商品分类
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "showGoodsDialog")
	public String showGoodsDialog() {
		return "layout3.tree.goodsTreeselect";
	}

	/**
	 * 用户选择对话框
	 * 
	 * @return
	 */
	@RequestMapping(value = "showSelectUserDialog")
	public String showSelectUserDialog() {
		return "layout3.sys.selectUserDlg";
	}

	// /**
	// * 根据父机构code查询子机构
	// * @param param
	// * @return
	// */
	// @RequiresPermissions("user")
	// @RequestMapping(value = "getSelectUserList")
	// @ResponseBody
	// public PageHelper<SysUmUser> getSelectUserList(@RequestBody String param)
	// {
	// JSONObject obj = JSONObject.parseObject(param);
	// SysUmUser filter = new SysUmUser();
	//
	// String zoneCode = UserUtils.getUser().getZonecode(); // 根据当前用户所在区划
	// String orgId = UserUtils.getUser().getOrgId();
	//
	// UmOrganization orgFilter = new UmOrganization();
	// orgFilter.setId(orgId);
	// UmOrganization orgEntity = sysOfficeService.get(orgFilter);
	// String orgType= orgEntity.getOrgType();
	// String level= orgEntity.getLevel();
	//
	// if (CommonConstant.DictOrgStyle.manager.equals(orgType)) {
	// if (CommonConstant.DictZoneLevel.provice.equals(level)) {
	// filter.setProvinceCode(zoneCode);
	// } else if (CommonConstant.DictZoneLevel.city.equals(level)) {
	// filter.setCityCode(zoneCode);
	// } else if (CommonConstant.DictZoneLevel.county.equals(level)) {
	// filter.setCountyCode(zoneCode);
	// } else if (CommonConstant.DictZoneLevel.town.equals(level)) {
	// filter.setTownCode(zoneCode);
	// }
	// } else {
	// filter.setSysOrgId(orgId);
	// }
	//
	// filter.setName(obj.getString("name"));
	// PageHelper<SysUmUser> pageHelper = sysUserService.getUserList(new
	// Page(obj),filter);
	// return pageHelper;
	// }

	/**
	 * 根据父机构code查询子机构
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getSelectUserList")
	@ResponseBody
	public PageHelper<User> getSelectUserList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		User filter = new User();
		String orgId = UserUtils.getUser().getOrgId();
		filter.setOrgId(orgId);
		Page page = new Page(obj);
		page.setRowCount(-1);
		PageHelper<User> pageHelper = sysUserService.getUserList(page, filter);
		return pageHelper;
	}

	// /**
	// * 根据父机构code查询子机构
	// * @param param
	// * @return
	// */
	// @RequiresPermissions("user")
	// @RequestMapping(value = "saveSelectUser")
	// @ResponseBody
	// public String saveSelectUser(@RequestBody String param) {
	// JSONObject obj = JSONObject.parseObject(param);
	//// SysUmUser entity = new SysUmUser();
	// User user = UserUtils.getUser();
	//
	// JSONObject result = saveUser(CommonConstant.DictOrgStyle.service,
	// user.getRoleId(),
	// user.getOrgId(),
	// obj.getString("name"),
	// CommonConstant.DictStartStatus.stop);
	//
	// return result.toString();
	// }
	// /**
	// * Created on 2017年3月22日
	// * Description 创建账号
	// * @author yanqizh
	// * @param orgType （机构类型）
	// * @param roleId （角色ID）
	// * @param orgId （机构ID）
	// * @param name （用户名称）
	// */
	// private JSONObject saveUser(String orgType,String roleId,String
	// orgId,String name,String loginFlag){
	// JSONObject result = new JSONObject();
	// UserInfo umbean = new UserInfo();
	// UserInfo sysbean = new UserInfo();
	// UserInfo roleBean = new UserInfo();
	// String id = IdGen.uuid();
	// sysbean.setId(id);
	// umbean.setId(id);
	// String loginName = IdGen.uuid();
	// sysbean.setSysLoginName(loginName);
	// sysbean.setSysName(name);
	// sysbean.setPassword(entryptPassword("111111"));
	// sysbean.setSysLoginFlag(loginFlag);
	// sysbean.setSysOrgId(orgId);
	// sysbean.setUserType(orgType);
	// sysbean.preInsert();
	// umbean.setLoginName(loginName);
	// umbean.setName(name);
	// umbean.setIsResetPwd(CommonConstant.COMMON_YES);
	// umbean.preInsert();
	// umbean.setId(sysbean.getId());
	// roleBean.setRoleId(roleId);
	// roleBean.preInsert();
	// roleBean.setUserId(sysbean.getId());
	//// userInfoDao.insertSysUserRole(roleBean);
	//// userInfoDao.insertSysUser(sysbean);
	//// userInfoDao.insertUmUser(umbean);
	// JSONObject object =
	// JSONObject.parseObject(JSONObject.toJSONString(sysbean));
	// object.put("name",object.getString("sysName"));
	// result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
	// result.put("data", object);
	// return result;
	// }

	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	private String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 根据父机构code查询子机构
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getOrgByParentCode")
	@ResponseBody
	public String getOrgByParentCode(@RequestBody String param) {
		JSONObject result = new JSONObject();
		JSONObject obj = JSONObject.parseObject(param);

		List<Organization> list = organizationService.findByParentCode(obj.getString("parentCode"));

		String listString = JSONObject.toJSONString(list);
		JSONArray arrayObj = JSONArray.parseArray(listString);
		setInfo(arrayObj);

		result.put("data", arrayObj);
		return result.toString();
	}

	private void setInfo(JSONArray arrayObj) {
		int size = arrayObj.size();
		for (int i = 0; i < size; i++) {
			JSONObject object = arrayObj.getJSONObject(i);
			object.put("orgLevel", object.getString("level"));
			object.put("orgType", object.getString("orgType"));
			if (CommonConstant.DictZoneLevel.town.equals(object.getString("level"))) {
				object.put("isParent", false);
			} else {
				object.put("isParent", true);
			}

		}
	}

	/**
	 * 根据父机构Id查询子机构
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getOrgByParentId")
	@ResponseBody
	public JSONArray getOrgByParentId(@RequestBody String param) {
		JSONArray result = new JSONArray();
		JSONObject obj = JSONObject.parseObject(param);

		List<Organization> entityList = null;
		if (obj.getBoolean("onlyManageOrg") != null && obj.getBoolean("onlyManageOrg")) {
			Organization filter = new Organization();
			filter.setParentId(obj.getString("parentId"));
			filter.setOrgType(CommonConstant.DictOrgStyle.manager);
			entityList = organizationService.getOfficeList(filter);
		} else if (null != obj.getBoolean("onlyTown") && obj.getBoolean("onlyTown")) {
			Organization childFilter = new Organization();
			childFilter.setParentId(obj.getString("parentId"));
			entityList = organizationService.getOfficeList(childFilter);
		} else {
			entityList = organizationService.findByParentId(obj.getString("parentId"));
		}

		getObjects(entityList, result);
		return result;
	}

	private JSONArray getObjects(List<Organization> entityList, JSONArray result) {
		for (Organization entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentId", entity.getParentId());
			jsonObject.put("code", entity.getCode());
			jsonObject.put("name", entity.getName());
			jsonObject.put("isParent", true);
			jsonObject.put("orgType", entity.getOrgType());
			result.add(jsonObject);
		}
		return result;
	}

	/**
	 * 根据id查询机构
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getOrgById")
	@ResponseBody
	public JSONArray getOrgById(@RequestBody String param) {
		JSONArray result = new JSONArray();
		JSONObject obj = JSONObject.parseObject(param);
		String onlyChild = obj.getString("onlyChild");
		String onlySelf = obj.getString("onlySelf");
		String zoneCode = UserUtils.getUser().getOwnZoneCode(); // 根据当前用户所在区划
		String orgId = UserUtils.getUser().getOrgId();

		if (StringUtils.isEmpty(zoneCode) || StringUtils.isEmpty(orgId)) { // 登录人没有区划和机构时不能查询数据
			return new JSONArray();
		}

		List<Organization> entityList = null;
		String parentId = null;
		if ("true".equals(onlySelf)) {
			entityList = new ArrayList<Organization>();
			Organization entity = organizationService.findById(orgId);
			entityList.add(entity);
		} else {
			Organization filter = new Organization();
			// filter.setOwnZoneCode(zoneCode);
			filter.setOrgType(CommonConstant.DictOrgStyle.manager);
			entityList = organizationService.getOfficeList(filter);

			if (null == entityList || entityList.size() == 0) {
				Organization entity = organizationService.findById(orgId);
				if (null != entity) {
					entity = organizationService.findById(entity.getParentId());
					entityList.add(entity);
					parentId = entity.getId();
				}
			} else {
				parentId = entityList.get(0).getId();
			}
		}

		for (Organization entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentId", entity.getParentId());
			jsonObject.put("code", entity.getCode());
			jsonObject.put("name", entity.getName());
			jsonObject.put("orgType", entity.getOrgType());
			if ("true".equals(onlySelf)) {
				jsonObject.put("isParent", false);
			} else {
				jsonObject.put("isParent", true);
			}
			result.add(jsonObject);
		}

		if ("true".equals(onlyChild) && !"true".equals(onlySelf) && entityList != null && entityList.size() > 0) {
			List<Organization> childList = null;
			if (obj.getBoolean("onlyManageOrg") != null && obj.getBoolean("onlyManageOrg")) {
				Organization childFilter = new Organization();
				childFilter.setParentId(parentId);
				childFilter.setOrgType(CommonConstant.DictOrgStyle.manager);
				childList = organizationService.getOfficeList(childFilter);
			} else if (null != obj.getBoolean("onlyTown") && obj.getBoolean("onlyTown")) {
				Organization childFilter = new Organization();
				childFilter.setParentId(parentId);
				childList = organizationService.getOfficeList(childFilter);
			} else {
				childList = organizationService.findByParentId(parentId);
			}
			// List<UmOrganization> childList =
			// sysOfficeService.findByParentId(entityList.get(0).getId());
			for (Organization entity : childList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", entity.getId());
				jsonObject.put("parentId", entity.getParentId());
				jsonObject.put("code", entity.getCode());
				jsonObject.put("name", entity.getName());
				jsonObject.put("orgType", entity.getOrgType());
				jsonObject.put("isParent", false);

				result.add(jsonObject);
			}
		}
		return result;
	}

	/**
	 * 根据id查询机构
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getOrgByIdForUser")
	@ResponseBody
	public JSONArray getOrgByIdForUser(@RequestBody String param) {
		JSONArray result = new JSONArray();
		JSONObject obj = JSONObject.parseObject(param);
		String onlyChild = obj.getString("onlyChild");
		String onlySelf = obj.getString("onlySelf");
		String zoneCode = UserUtils.getUser().getOwnZoneCode(); // 根据当前用户所在区划
		String orgId = UserUtils.getUser().getOrgId();

		if (StringUtils.isEmpty(zoneCode) || StringUtils.isEmpty(orgId)) { // 登录人没有区划和机构时不能查询数据
			return new JSONArray();
		}

		Organization filter = new Organization();
		// filter.setOwnZoneCode(zoneCode);
		filter.setOrgType(CommonConstant.DictOrgStyle.manager);
		List<Organization> entityList = organizationService.getOfficeList(filter);
		if (null == entityList || entityList.size() == 0) {
			Organization entity = organizationService.findById(orgId);
			if (null != entity) {
				entity = organizationService.findById(entity.getParentId());
				entityList.add(entity);
			}
		}

		for (Organization entity : entityList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentId", entity.getParentId());
			jsonObject.put("code", entity.getCode());
			jsonObject.put("name", entity.getName());
			jsonObject.put("orgType", entity.getOrgType());
			if ("true".equals(onlySelf)) {
				jsonObject.put("isParent", false);
			} else {
				jsonObject.put("isParent", true);
			}
			result.add(jsonObject);
		}

		if ("true".equals(onlyChild) && !"true".equals(onlySelf) && entityList != null && entityList.size() > 0) {
			List<Organization> childList = organizationService.findByParentId(entityList.get(0).getId());
			for (Organization entity : childList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", entity.getId());
				jsonObject.put("parentId", entity.getParentId());
				jsonObject.put("code", entity.getCode());
				jsonObject.put("name", entity.getName());
				jsonObject.put("orgType", entity.getOrgType());
				jsonObject.put("isParent", true);

				result.add(jsonObject);
			}
		}
		return result;
	}

	/**
	 *
	 * Created on 2017年3月30日 .
	 * <p>
	 * Description {根据机构id查询机构账号信息查询}
	 *
	 * @author liuqing
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getListForUserTreeByOrgId")
	@ResponseBody
	public JSONArray getListForUserTreeByOrgId(@RequestBody String param) {
		JSONArray result = new JSONArray();
		JSONObject obj = JSONObject.parseObject(param);
		String orgId = obj.getString("parentId");

		if (StringUtils.isEmpty(orgId)) {
			return result;
		}

		List<Organization> entityList = organizationService.findByParentId(obj.getString("parentId"));
		getObjects(entityList, result);

		User filter = new User();
		filter.setOrgId(orgId);
		List<User> list = sysUserService.getUserList(filter);

		if (list == null || list.size() == 0) {
			return result;
		}

		for (User entity : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", entity.getId());
			jsonObject.put("parentId", orgId);
			jsonObject.put("loginName", entity.getLoginName());
			jsonObject.put("name", entity.getName());
			jsonObject.put("isUser", "1");
			jsonObject.put("isParent", false);

			result.add(jsonObject);
		}
		return result;
	}

}