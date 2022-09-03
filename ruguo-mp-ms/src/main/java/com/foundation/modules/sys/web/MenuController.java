/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.web;

import java.util.List;
import java.util.Map;

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
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Menu;
import com.foundation.modules.sys.service.SystemService;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 菜单Controller
 * 
 * @author zou
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "sys/menu")
public class MenuController extends BaseController {

	@Autowired
	private SystemService systemService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ModelAttribute("menu")
	public Menu get(@RequestParam(required = false) String id) {
		Menu menu = new Menu();
		if (StringUtils.isNotBlank(id)) {
			menu = systemService.getMenu(id);
		}
		return menu;
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		List<Menu> list = Lists.newArrayList();
		List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(list, sourcelist, Menu.getRootId(), true);
		model.addAttribute("list", list);
		return "layout1.sys.menu.menuIndex";
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		if (menu.getParent() == null || menu.getParent().getId() == null) {
			menu.setParent(new Menu(Menu.getRootId()));
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(menu.getId())) {
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = systemService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0) {
				menu.setSort(list.get(list.size() - 1).getSort() + 30);
			}
		}
		model.addAttribute("menu", menu);
		return "layout3.sys.menu.menuEdit";
	}

	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(Menu menu, Model model) {
		if (!UserUtils.getUser().isAdmin()) {
			responseHelper.setFail("越权操作，只有超级管理员才能添加或修改数据！");
		} else {
			systemService.saveMenu(menu);
			responseHelper.setSuccess("保持菜单成功！");
		}
		return responseHelper;

	}

	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseHelper delete(Menu menu) {
		if (!UserUtils.getUser().isAdmin()) {
			responseHelper.setFail("越权操作，只有超级管理员才能添加或修改数据！");
		} else {
			systemService.deleteMenu(menu);
			responseHelper.setSuccess("删除菜单成功！");
		}
		return responseHelper;

	}

	/**
	 * 批量修改菜单排序
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "updateSort")
	@ResponseBody
	public ResponseHelper updateSort(String[] ids, Integer[] sorts) {
		for (int i = 0; i < ids.length; i++) {
			Menu menu = new Menu(ids[i]);
			menu.setSort(sorts[i]);
			systemService.updateMenuSort(menu);
		}
		responseHelper.setSuccess("保存菜单排序成功!");
		return responseHelper;
	}

	/**
	 * isShowHide是否显示隐藏菜单
	 * 
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestBody String param) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		JSONObject obj = JSONObject.parseObject(param);
		String extId = obj.getString("extId");
		String isShowHide = obj.getString("isShowHide");

		List<Menu> list = systemService.findAllMenu();
		for (int i = 0; i < list.size(); i++) {
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
				if (isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")) {
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 一级菜单跳转
	 * 
	 * @param model
	 * @return
	 * @author hl
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "show")
	public String show(@RequestParam("pid") String parentid) {

		String initPath = UserUtils.getInitMenu(parentid);
		if (StringUtils.isNoneBlank(initPath)) {
			return "redirect:" + initPath;
		} else {
			return "layout1.sys.sysIndex";
		}
	}
}
