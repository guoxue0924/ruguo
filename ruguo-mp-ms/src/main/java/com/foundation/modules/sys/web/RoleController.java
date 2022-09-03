/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Menu;
import com.foundation.modules.sys.entity.Role;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SystemService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * 角色Controller
 * 
 * @author liuqing
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "sys/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService systemService;

	// @Autowired
	// private OfficeService officeService;

	@Autowired
	private PageHelper<Role> pageHelper;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { "list", "" })
	public String list(Role role, Model model) {
		return "layout1.sys.role.roleList";
	}

	/**
	 * 维护角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "roleForm")
	public String indexRoleForm() {
		return "layout3.sys.role.roleForm";
	}

	@ModelAttribute("role")
	public Role get(@RequestParam(required = false) String id) {
		Role role = null;
		if (StringUtils.isNotBlank(id)) {
			role = systemService.getRole(id);
		} else {
			role = new Role();
		}
		return role;
	}

	/**
	 *
	 * Created on 2017年3月16日 . 查询角色列表信息
	 * 
	 * @author liuqing
	 * @param param
	 * @return PageHelper<Role>
	 */
	@RequestMapping(value = "getAllRoleList")
	@ResponseBody
	public PageHelper<Role> getAllRoleList(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		List<Role> list = systemService.findAllRole();
		Page page = new Page(obj);
		pageHelper.setRows(page, list);
		return pageHelper;
	}

	/**
	 *
	 * Created on 2017年3月16日 . 查询角色信息
	 * 
	 * @author liuqing
	 * @param param
	 * @return PageHelper<Role>
	 */
	@RequestMapping(value = "getRoleInfo")
	@ResponseBody
	public ResponseHelper getRoleInfo(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);

		Role role = systemService.getRole(obj.getString("id"));

		List<Menu> menuList = null;
		if (UserUtils.getUser().getLoginName() != null && "A000000000".equals(UserUtils.getUser().getLoginName())) {
			menuList = UserUtils.getAllMenuList();
		} else {
			menuList = systemService.findAllMenu();
		}
		JSONObject result = new JSONObject();
		result.put("menuList", menuList);
		result.put("role", role == null ? "" : role);
		responseHelper.setSuccess("", result);
		return responseHelper;
	}

	/**
	 *
	 * Created on 2017年3月16日 . 保存角色列表信息
	 * 
	 * @author liuqing
	 * @param role
	 * @return PageHelper<Role>
	 */
	@RequestMapping(value = "saveRole")
	@ResponseBody
	public ResponseHelper saveRole(Role role) {
		if (!"true".equals(checkName(role.getOldName(), role.getName()))) {
			responseHelper.setFail("保存角色'" + role.getName() + "'失败, 角色名已存在！");
			return responseHelper;
		}
		if (!"true".equals(checkEnname(role.getOldEnname(), role.getEnname()))) {
			responseHelper.setFail("保存角色'" + role.getName() + "'失败, 英文名已存在！");
			return responseHelper;
		}
		systemService.saveRole(role);
		responseHelper.setSuccess("保存角色'" + role.getName() + "'成功！");
		return responseHelper;
	}

	/**
	 *
	 * Created on 2017年3月16日 . 删除角色列表信息
	 * 
	 * @author liuqing
	 * @param entity
	 * @return PageHelper<Role>
	 */
	@RequestMapping(value = "deleteRole")
	@ResponseBody
	public ResponseHelper deleteRole(Role entity) {
		Role role = systemService.getRole(entity.getId());
		// if (!UserUtils.getUser().isAdmin() &&
		// Global.YES.equals(role.getSysData())) {
		// responseHelper.setFail("越权操作，只有超级管理员才能修改此数据！");
		// return responseHelper;
		// }
		// if (Global.isDemoMode()) {
		// responseHelper.setFail("演示模式，不允许操作！");
		// return responseHelper;
		// }
		if (systemService.findRoleUserCount(role.getId()) > 0) {
			responseHelper.setFail("被分配了账号的角色，不允许删除！");
			return responseHelper;
		}
		
		systemService.deleteRole(role);
		responseHelper.setSuccess("删除角色成功！");
		return responseHelper;
	}

	/**
	 * 角色分配页面
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	// @RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assign")
	public String assign(Role role, Model model) {
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		model.addAttribute("userList", userList);
		return "modules/sys/roleAssign";
	}

	/**
	 * 角色分配 -- 打开角色分配对话框
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	// @RequiresPermissions("sys:role:view")
	// @RequestMapping(value = "usertorole")
	// public String selectUserToRole(Role role, Model model) {
	// List<User> userList = systemService.findUser(new User(new
	// Role(role.getId())));
	// model.addAttribute("role", role);
	// model.addAttribute("userList", userList);
	// model.addAttribute("selectIds", Collections3.extractToString(userList,
	// "name", ","));
	// model.addAttribute("officeList", officeService.findAll());
	// return "modules/sys/selectUserToRole";
	// }

	/**
	 * 验证角色名是否有效
	 * 
	 * @param oldName
	 * @param name
	 * @return
	 */
	// @RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name != null && name.equals(oldName)) {
			return "true";
		} else if (name != null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证角色英文名是否有效
	 * 
	 * @param oldEnname
	 * @param enname
	 * @return
	 */
	// @RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkEnname")
	public String checkEnname(String oldEnname, String enname) {
		if (enname != null && enname.equals(oldEnname)) {
			return "true";
		} else if (enname != null && systemService.getRoleByEnname(enname) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * Created on 2017年4月7日 Description {根据角色类型查询角色列表接口层}
	 * 
	 * @author jiyingming
	 * @param param
	 *            传递参数（角色类型）
	 * @return List<Role> 角色列表集合
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getRoleList")
	@ResponseBody
	public ResponseHelper getRoleList() {
		List<Role> roleList = systemService.findRole(new Role());

		roleList.add(0, new Role());

		responseHelper.setData(roleList);
		return responseHelper;
	}

}
