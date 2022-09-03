/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.foundation.modules.roledo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.web.BaseController;
import com.foundation.modules.roledo.entity.RoleInfoEntity;
import com.foundation.modules.roledo.service.RoleDoService;
import com.foundation.modules.sys.utils.ResponseHelper;

/**
 * 权限查询接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "userRole")
public class RoleDoController extends BaseController {

	@Autowired
	private RoleDoService roleDoService;
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseHelper list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {

		JSONObject obj = JSONObject.parseObject(param);
		RoleInfoEntity role = obj.toJavaObject(RoleInfoEntity.class);
		role.setRoleLevel("");
		role.setRoleType("");
		List<RoleInfoEntity> roleList = roleDoService.queryRoleList(role);
		RoleInfoEntity roleInfoEntity = roleDoService.queryUserRole(role);
		Map<String, Object> parentMap = new HashMap<>();
		parentMap.put("userRole", roleInfoEntity);
		parentMap.put("roleList", roleList);
		ResponseHelper rh = new ResponseHelper();
		rh.setData(parentMap);
		rh.setSuccess();	
		return rh;
	}
}