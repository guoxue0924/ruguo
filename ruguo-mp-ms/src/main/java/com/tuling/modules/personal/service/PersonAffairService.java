package com.tuling.modules.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserInfoDao;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SystemService;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * Created on 2017年3月6日 Description 个人事务服务 Copyright tuling (c) 2017 .
 * 
 * @author liuqing
 */
@Service
public class PersonAffairService {

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private SystemService systemService;

	@Autowired
	private ResponseHelper responseHelper;

	/**
	 * Created on 2017年1月17日 . Description 个人信息管理-查询
	 * 
	 * @author liuqing
	 * @param filter
	 * @return String
	 */
	// public List<User> getUserList(UserFilter filter) {
	// return userInfoDao.getUserById(filter);
	// }

	/**
	 * 更新个人用户信息
	 * 
	 * @param user
	 * @return
	 */
	public ResponseHelper updatePersonInfo(User user) {
		if (user == null) {
			responseHelper.setFail("参数错误，保存失败！");
			return responseHelper;
		}
		user.preUpdate();
		userInfoDao.update(user);
		userDao.updateName(user);
		UserUtils.clearCache(user);

		responseHelper.setSuccess("保存成功！");
		return responseHelper;
	}

	/**
	 * Created on 2017年1月17日 . Description 校验密码服务
	 * 
	 * @author liuqing
	 * @param loginName
	 * @param newPassword
	 * @param oldPassword
	 * @return JSONObject
	 */
	public JSONObject checkPassword(String loginName, String newPassword, String oldPassword) {
		JSONObject result = new JSONObject();
		User user = systemService.getUserByLoginName(loginName);
		if (systemService.validatePassword(oldPassword, user.getPassword())) {
			if (oldPassword.equals(newPassword)) {
				result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
				result.put(CommonConstant.ERROR_MSG, "新密码与旧密码一致，请重新输入！");
			} else {
				result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
				result.put(CommonConstant.ERROR_MSG, "");
			}
		} else {
			result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
			result.put(CommonConstant.ERROR_MSG, "旧密码错误，请重新输入！");
		}

		return result;
	}

	/**
	 * Created on 2017年1月17日 . Description 修改密码服务
	 * 
	 * @author liuqing
	 * @param loginName
	 * @param newPassword
	 * @param oldPassword
	 * @return JSONObject
	 */
	public JSONObject updatePassword(String loginName, String newPassword, String oldPassword) {
		JSONObject result = new JSONObject();
		User user = systemService.getUserByLoginName(loginName);
		if (systemService.validatePassword(oldPassword, user.getPassword())) {
			systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
			result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
			result.put(CommonConstant.ERROR_MSG, "");
		} else {
			result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
			result.put(CommonConstant.ERROR_MSG, "旧密码错误！");
		}

		return result;
	}
}