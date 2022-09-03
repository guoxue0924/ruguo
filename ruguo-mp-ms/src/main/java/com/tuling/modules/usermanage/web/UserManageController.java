package com.tuling.modules.usermanage.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.entity.Role;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SysUserService;
import com.foundation.modules.sys.utils.DictCommonUtils;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;

@Controller
@RequestMapping(value = "sys/user")
public class UserManageController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ResponseHelper responseHelper;
	@Autowired
	private UserDao userDao;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String queryUser(Model model) {
		List<Role> roleList = sysUserService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		return "layout1.usermanage.userQuery";
	}

	/**
	 * 
	 * Created on 2017年3月30日 .
	 * <p>
	 * Description {机构账号信息查询}
	 *
	 * @author hao
	 * @param param
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "getUserList")
	@ResponseBody
	public PageHelper<User> getUserList(@RequestBody String param) {
		/* 翻页功能参数处理 start */
		// 分页step1
		JSONObject filterData = JSONObject.parseObject(param);
		// 分页step2
		User userFilter = filterData.toJavaObject(User.class);
		// 分页step3
		PageHelper<User> list = sysUserService.getUserList(new Page(filterData), userFilter);
		for (User obj : list.getRows()) {
			DictCommonUtils.setRoleNameById(obj, "userRole", "userRoleName");
		}
		return list;
	}

	/**
	 * 新增服务机构账号-用于点击增加或者修改的时候回显
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "addServiceUser")
	public String addServiceUser(String param, HttpServletRequest request, HttpServletResponse response, Model model) {
		User sysUser = new User();
		// sysUser.setOrgId(UserUtils.getUser().getOrgId()); // by
		// // liuhuan,20170505
		// sysUser.setOrgName(UserUtils.getUser().getOrgName()); // by
		// liuhuan,20170505
		// sysUser.setUserType(request.getParameter("userType"));
		/** by liuhuan,20170511 获取当前机构级别、当前区划编码 start */
		// User user = UserUtils.getUser();
		// String orgLevel = user.getOrgLevel(); // 获取当前机构级别
		// String zoneCode = user.getOwnZoneCode(); // 获取当前区划编码
		// model.addAttribute("orgLevel", orgLevel);
		// model.addAttribute("zoneCode", zoneCode);
		/** by liuhuan,20170511 获取当前机构级别、当前区划编码 end */
		model.addAttribute("userType", request.getParameter("userType"));
		model.addAttribute("result", sysUser);
		return "layout3.usermanage.userEdit";
	}

	/**
	 * 修改管理机构账号-用于点击增加或者修改的时候回显
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "updateServiceUser")
	public String updateServiceUser(User sysUmUser, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// sysUmUser.setId(request.getParameter("param"));
		// List<User> list = sysUserService.getUserList(sysUmUser);
		User user = userDao.get(request.getParameter("param"));
		model.addAttribute("result", user);
		// if(list.size()>0){
		// model.addAttribute("result", list.get(0));
		// }else{
		// model.addAttribute("result", new User());
		// }
		return "layout3.usermanage.userEdit";
	}

	/**
	 * 
	 * Created on 2017年4月5日 .
	 * <p>
	 * Description {保存用户账号信息}
	 *
	 * @author hao
	 * @param user
	 * @return
	 * @throws ParseException
	 *             String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "saveUser")
	@ResponseBody
	public ResponseHelper saveUser(User user, RedirectAttributes redirectAttributes) throws ParseException {
		// String orgType = user.getUserType();
		String roleId = user.getUserRole();
		String name = user.getName();
		String loginName = user.getLoginName();
		List<User> list = sysUserService.getUserListByLoginName(user);
		if (null != list && list.size() > 0) {
			responseHelper.setFail("账号创建失败，‘" + loginName + "’已经存在！");
			return responseHelper;
		}
		JSONObject obj = sysUserService.setUpAccount(roleId, loginName, name);

		responseHelper.setSuccess("账号创建成功，您设置的账号为:" + obj.getString("account"), obj.get("id")); // 返回成功状态
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年4月6日 .
	 * <p>
	 * Description {删除用户信息}
	 *
	 * @author hao
	 * @param sysUmUser
	 * @return SysUmUser
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "deleteUser")
	@ResponseBody
	public ResponseHelper deleteUser(@RequestBody String param, RedirectAttributes redirectAttributes) {
		JSONObject obj = JSONObject.parseObject(param);
		String id = (String) obj.get("id");
		User user = new User();
		user.setId(id);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		sysUserService.deleteUser(userList);
		addMessage(redirectAttributes, "删除overdue成功！");
		responseHelper.setSuccess("删除信息成功！");// 返回成功状态 和 消息
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年4月6日 .
	 * <p>
	 * Description {启用用户}
	 *
	 * @author hao
	 * @param user
	 * @param redirectAttributes
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "openUser")
	@ResponseBody
	public ResponseHelper openUser(@RequestBody String param) {
		if (StringUtils.isBlank(param)) {
			responseHelper.setFail("参数错误，账号启用失败！");
			return responseHelper;
		}
		JSONObject obj = JSONObject.parseObject(param);
		@SuppressWarnings("unchecked")
		List<JSONObject> ids = (List<JSONObject>) obj.get("id");
		List<User> list = new ArrayList<User>();
		if (ids != null && ids.size() > 0) {
			for (JSONObject idObj : ids) {
				String id = idObj.getString("id");
				User user = new User();
				user.setId(id);
				user.setLoginFlag(CommonConstant.DictStartStatus.start);
				list.add(user);
			}
		}
		sysUserService.whetherLoginFlag(list);
		responseHelper.setSuccess("账号启用成功！");// 返回成功状态 和 消息
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年4月6日 .
	 * <p>
	 * Description {停用用户}
	 *
	 * @author hao
	 * @param user
	 * @param redirectAttributes
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "stopUser")
	@ResponseBody
	public ResponseHelper stopUser(@RequestBody String param) {
		if (StringUtils.isBlank(param)) {
			responseHelper.setFail("参数错误，账号停用失败！");
			return responseHelper;
		}
		JSONObject obj = JSONObject.parseObject(param);
		@SuppressWarnings("unchecked")
		List<JSONObject> ids = (List<JSONObject>) obj.get("id");
		List<User> list = new ArrayList<User>();
		if (ids != null && ids.size() > 0) {
			for (JSONObject idObj : ids) {
				String id = idObj.getString("id");
				User user = new User();
				user.setId(id);
				user.setLoginFlag(CommonConstant.DictStartStatus.stop);
				list.add(user);
			}
		}
		sysUserService.whetherLoginFlag(list);
		responseHelper.setSuccess("机构账号停用成功！");// 返回成功状态 和 消息
		return responseHelper;
	}

	/**
	 * 
	 * Created on 2017年4月6日 .
	 * <p>
	 * Description {重置用户密码}
	 *
	 * @author hao
	 * @param user
	 * @param redirectAttributes
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "resetKey")
	@ResponseBody
	public ResponseHelper resetKey(@RequestBody String param, RedirectAttributes redirectAttributes) {
		JSONObject obj = JSONObject.parseObject(param);
		String id = obj.get("id").toString();
		sysUserService.reSetPassword(id);
		responseHelper.setSuccess("重置密码成功！");// 返回成功状态 和 消息
		return responseHelper;
	}
}