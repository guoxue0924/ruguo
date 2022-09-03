package com.foundation.modules.datasync.user.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.web.BaseController;
import com.foundation.modules.datasync.user.entity.UserExtSync;
import com.foundation.modules.datasync.user.entity.UserMainSync;
import com.foundation.modules.datasync.user.entity.UserRoleSync;
import com.foundation.modules.datasync.user.service.UserSyncService;
import com.foundation.modules.sys.utils.ResponseHelper;

/**
 * Created on 2018年01月16日
 * Description 用户同步 Controller
 * @author liuhuan
 */
@Controller
@RequestMapping(value = "user/sync")
public class UserSyncController extends BaseController {
	
	@Autowired
	private UserSyncService userSyncService;
	
	@Autowired
	private ResponseHelper responseHelper;

	/**
	 * Created on 2018年01月17日 .
	 * Description 创建用户账号信息
	 * @author liuhuan 
	 * @param param
	 * @return responseHelper
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public ResponseHelper addUser(@RequestBody String param) {
		JSONArray array = JSONArray.parseArray(param);
		if (array.isEmpty()) {
			// 返回失败信息
			responseHelper.setFail("数据异常，请核对！", param);
		} else {
			// 用户主表集合
			List<UserMainSync> userMainSyncList = new ArrayList<UserMainSync>();
			// 用户扩展表集合
			List<UserExtSync> userExtSyncList = new ArrayList<UserExtSync>();
			// 要添加的用户角色关系表集合
			List<UserRoleSync> userRoleSyncAddList = new ArrayList<UserRoleSync>();
			// 要删除的用户角色关系表集合
			List<UserRoleSync> userRoleSyncDelList = new ArrayList<UserRoleSync>();
			
			// 遍历 JSONArray
			for (int i = 0; i < array.size(); i++) {
				
				JSONObject obj = array.getJSONObject(i);
				// 用户主表
				JSONObject userMainObj = obj.getJSONObject("user");
				// 用户扩展表
				JSONObject userExtObj = obj.getJSONObject("userExt");
				// 角色关系表
				JSONObject userRoleObj = obj.getJSONObject("userRole");
				
				if (userMainObj.isEmpty() || userExtObj.isEmpty() || userRoleObj.isEmpty()) {
					
					// 清空用户主表集合
					userMainSyncList.clear();
					// 清空用户扩展表集合
					userExtSyncList.clear();
					// 清空要添加的用户角色关系表集合
					userRoleSyncAddList.clear();
					// 清空要删除的用户角色关系表集合
					userRoleSyncDelList.clear();
					
					break;
					
				} else {
					// 存储用户主表数据
					UserMainSync userMainSyncEntity = userMainObj.toJavaObject(UserMainSync.class);
					userMainSyncList.add(userMainSyncEntity);
					
					// 存储用户扩展数据
					UserExtSync userExtSyncEntity = userExtObj.toJavaObject(UserExtSync.class);
					userExtSyncList.add(userExtSyncEntity);
					
					// 存储用户角色关系数据
					UserRoleSync userRoleSyncEntity = userRoleObj.toJavaObject(UserRoleSync.class);
					// 存储要删除的用户角色关系数据
					userRoleSyncDelList.add(userRoleSyncEntity);
					// 角色不为空：存储要添加的用户角色关系表集合
					if (!StringUtils.isEmpty(userRoleSyncEntity.getRoleId())) {
						userRoleSyncAddList.add(userRoleSyncEntity);
					}
				}
			}
			
			// 批量创建用户
			if (CollectionUtils.isEmpty(userMainSyncList) || CollectionUtils.isEmpty(userExtSyncList) || CollectionUtils.isEmpty(userRoleSyncDelList)) {
				// 返回失败信息
				responseHelper.setFail("数据异常，请核对！", param);
			} else {
				try {
					// 创建成功
					userSyncService.addUser(userMainSyncList, userExtSyncList, userRoleSyncDelList, userRoleSyncAddList);
					responseHelper.setSuccess("账号创建成功！");
				} catch (Exception e) {
					// 创建失败
					logger.error("账号创建失败！", e);
					responseHelper.setFail("账号创建失败！", param);
				}
			}
		}
		
		return responseHelper;
	}
	
	/**
	 * Created on 2018年01月17日 .
	 * Description 修改用户账号信息
	 * @author liuhuan 
	 * @param param
	 * @return responseHelper
	 */
	@RequestMapping(value = "modify")
	@ResponseBody
	public ResponseHelper modifyUser(@RequestBody String param) {
		JSONArray array = JSONArray.parseArray(param);
		if (array.isEmpty()) {
			// 返回失败信息
			responseHelper.setFail("数据异常，请核对！", param);
		} else {
			// 用户主表集合
			List<UserMainSync> userMainSyncList = new ArrayList<UserMainSync>();
			// 用户扩展表集合
			List<UserExtSync> userExtSyncList = new ArrayList<UserExtSync>();
			// 要添加的用户角色关系表集合
			List<UserRoleSync> userRoleSyncAddList = new ArrayList<UserRoleSync>();
			// 要删除的用户角色关系表集合
			List<UserRoleSync> userRoleSyncDelList = new ArrayList<UserRoleSync>();
			
			// 遍历 JSONArray
			for (int i = 0; i < array.size(); i++) {
				
				JSONObject obj = array.getJSONObject(i);
				// 用户主表
				JSONObject userMainObj = obj.getJSONObject("user");
				// 用户扩展表
				JSONObject userExtObj = obj.getJSONObject("userExt");
				// 角色关系表
				JSONObject userRoleObj = obj.getJSONObject("userRole");
				
				if (userMainObj.isEmpty() || userExtObj.isEmpty() || userRoleObj.isEmpty()) {
					
					// 清空用户主表集合
					userMainSyncList.clear();
					// 清空用户扩展表集合
					userExtSyncList.clear();
					// 清空要添加的用户角色关系表集合
					userRoleSyncAddList.clear();
					// 清空要删除的用户角色关系表集合
					userRoleSyncDelList.clear();
					
					break;
					
				} else {
					// 存储用户主表数据
					UserMainSync userMainSyncEntity = userMainObj.toJavaObject(UserMainSync.class);
					userMainSyncList.add(userMainSyncEntity);
					
					// 存储用户扩展数据
					UserExtSync userExtSyncEntity = userExtObj.toJavaObject(UserExtSync.class);
					userExtSyncList.add(userExtSyncEntity);
					
					// 存储用户角色关系数据
					UserRoleSync userRoleSyncEntity = userRoleObj.toJavaObject(UserRoleSync.class);
					// 存储要删除的用户角色关系数据
					userRoleSyncDelList.add(userRoleSyncEntity);
					// 角色不为空：存储要添加的用户角色关系表集合
					if (!StringUtils.isEmpty(userRoleSyncEntity.getRoleId())) {
						userRoleSyncAddList.add(userRoleSyncEntity);
					}
				}
			}
			
			// 批量修改用户
			if (CollectionUtils.isEmpty(userMainSyncList) || CollectionUtils.isEmpty(userExtSyncList) || CollectionUtils.isEmpty(userRoleSyncDelList)) {
				// 返回失败信息
				responseHelper.setFail("数据异常，请核对！", param);
			} else {
				try {
					// 修改成功
					userSyncService.modifyUser(userMainSyncList, userExtSyncList, userRoleSyncDelList, userRoleSyncAddList);
					responseHelper.setSuccess("账号修改成功！");
				} catch (Exception e) {
					// 修改失败
					logger.error("账号创建失败！", e);
					responseHelper.setFail("账号创建失败！", param);
				}
			}
		}
		
		return responseHelper;
	}
	
	/**
	 * Created on 2018年01月17日 .
	 * Description 删除用户账号信息
	 * @author liuhuan 
	 * @param param
	 * @return responseHelper
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseHelper deleteUser(@RequestBody String param) {
		JSONArray array = JSONArray.parseArray(param);
		
		// 删除标志
		String delFlag = "";
		
		if (array.isEmpty()) {
			// 返回失败信息
			responseHelper.setFail("数据异常，请核对！", param);
		} else {
			// 用户主表集合
			List<UserMainSync> userMainSyncList = new ArrayList<UserMainSync>();
			// 用户扩展表集合
			List<UserExtSync> userExtSyncList = new ArrayList<UserExtSync>();
			// 用户角色关系表集合
			List<UserRoleSync> userRoleSyncList = new ArrayList<UserRoleSync>();
			
			// 遍历 JSONArray
			for (int i = 0; i < array.size(); i++) {
				
				JSONObject obj = array.getJSONObject(i);
				
				// 删除标志
				delFlag = obj.getString("type");
				
				// 要删除的用户
				JSONObject userObj = obj.getJSONObject("user");
				
				if (userObj.isEmpty()) {
					
					// 清空用户主表集合
					userMainSyncList.clear();
					// 清空用户扩展表集合
					userExtSyncList.clear();
					// 清空用户角色关系表集合
					userRoleSyncList.clear();
					
					break;
					
				} else {
					// 存储用户主表数据
					UserMainSync userMainSyncEntity = userObj.toJavaObject(UserMainSync.class);
					userMainSyncList.add(userMainSyncEntity);
					
					// 存储用户扩展数据
					UserExtSync userExtSyncEntity = userObj.toJavaObject(UserExtSync.class);
					userExtSyncList.add(userExtSyncEntity);
					
					// 存储用户角色关系数据
					UserRoleSync userRoleSyncEntity = new UserRoleSync();
					userRoleSyncEntity.setUserId(userMainSyncEntity.getId());
					userRoleSyncList.add(userRoleSyncEntity);
				}
			}
			
			// 批量修改用户
			if (CollectionUtils.isEmpty(userMainSyncList) || CollectionUtils.isEmpty(userExtSyncList) || CollectionUtils.isEmpty(userRoleSyncList)) {
				// 返回失败信息
				responseHelper.setFail("数据异常，请核对！", param);
			} else {
				try {
					// 逻辑删除
					if (delFlag.equals("delete")) {
						userSyncService.logicDeleteUser(userMainSyncList, userExtSyncList);
					}
					// 物理删除
					if (delFlag.equals("physical")) {
						userSyncService.physicsDeleteUser(userMainSyncList, userExtSyncList, userRoleSyncList);
					}
					responseHelper.setSuccess("账号删除成功！");
				} catch (Exception e) {
					// 修改失败
					logger.error("账号创建失败！", e);
					responseHelper.setFail("账号删除失败！", param);
				}
			}
		}
		
		return responseHelper;
	}

}
