/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.foundation.common.utils.exception.ExceptionUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Dict;
import com.foundation.modules.sys.service.DictService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典Controller
 * 
 * @author zou
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ModelAttribute
	public Dict get(@RequestParam(required = false) String id) {
		Dict dict = new Dict();
		if (StringUtils.isNotBlank(id)) {
			dict.setId(id);
			dict = dictService.get(dict);
		}
		return dict;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * Created on 2017年6月8日 Description {数据字典新增页面}
	 * 
	 * @author jiyingming
	 * @return url
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "add")
	public String add(Dict dict, Model model) {
		Dict dict_new = new Dict();
		dict_new.setType(dict.getType());
		dict_new.setDescription(dict.getDescription());

		model.addAttribute("result", dict);
		return "layout3.sys.dict.dictForm";
	}

	/**
	 * Created on 2017年6月8日 Description {数据字典新增页面}
	 * 
	 * @author jiyingming
	 * @return url
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "update")
	public String update(String id, Model model) {
		Dict dict = new Dict();
		dict.setId(id);

		Dict item = dictService.get(dict);

		model.addAttribute("result", item);
		return "layout3.sys.dict.dictForm";
	}

	/**
	 * Created on 2017年6月8日 Description {数据字典详细页面}
	 * 
	 * @author jiyingming
	 * @return url
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = { "list", "" })
	public String list() {
		return "layout1.sys.dict.dictList";
	}

	/**
	 * Created on 2017年6月8日 Description {查询}
	 * 
	 * @author jiyingming
	 * @return ResponseHelper
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "doQuery")
	@ResponseBody
	public PageHelper<Dict> doQuery(@RequestBody String param) {
		PageHelper<Dict> page = null;
		// 传入参数json
		JSONObject jsonObject = JSONObject.parseObject(param);
		// 查询条件
		Dict filter = jsonObject.toJavaObject(Dict.class);

		page = dictService.query(new Page(jsonObject), filter);
		return page;
	}

	/**
	 * Created on 2017年6月8日 Description {保存}
	 * 
	 * @author jiyingming
	 * @param param
	 *            传递参数
	 * @return 业务处理结果
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "doSave")
	@ResponseBody
	public ResponseHelper doSave(@RequestBody String param) {

		// 传入参数json
		JSONObject jsonObject = JSONObject.parseObject(param);

		Dict dict = jsonObject.toJavaObject(Dict.class);

		try {
			// 保存
			dictService.save(dict);
			responseHelper.setSuccess("保存成功！");

		} catch (Exception e) {
			log.error(ExceptionUtils.getAllExp(e));
			responseHelper.setFail("保存失败，保存过程中出现异常！");
		}

		return responseHelper;
	}

	/**
	 * Created on 2017年6月8日 Description {删除}
	 * 
	 * @author jiyingming
	 * @param id
	 *            传递参数
	 * @return 业务处理结果
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "doDelete")
	@ResponseBody
	public ResponseHelper doDelete(String id) {

		Dict dict = new Dict();
		dict.setId(id);

		// 删除
		dictService.delete(dict);
		responseHelper.setSuccess("删除成功！");

		return responseHelper;
	}

}
