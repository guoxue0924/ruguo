package com.foundation.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.security.Digests;
import com.foundation.common.service.CrudService;
import com.foundation.common.utils.Encodes;
import com.foundation.common.utils.SequenceUtils;
import com.foundation.common.utils.StringUtils;
import com.foundation.modules.sys.dao.RoleDao;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserInfoDao;
import com.foundation.modules.sys.entity.Role;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.UserUtils;

/**
 * 用户管理服务
 * 
 * @author yanqizh
 * @version 2017-03-22
 */
@Service
@Transactional(readOnly = true)
public class SysUserService extends CrudService<UserInfoDao, User> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
//	@Autowired
//	private OrganizationDao organizationDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PageHelper<User> pageHelper;

	/**
	 * Created on 2017年3月25日 .
	 * <p>
	 * Description {检查管理查询档案信息列表}
	 * 
	 * @author zhouhouyun
	 * @param busiListInfoFilter
	 * @return List<BusiListInfo>
	 */
	public PageHelper<User> getUserList(Page page, User bean) {
		bean.setPage(page);
		List<User> list = userInfoDao.getUserList(bean);
		pageHelper.setRows(page, list);
		return pageHelper;
	}

	/**
	 * Created on 2017年3月22日 Description {机构账号管理}
	 * 
	 * @author yanqizh
	 * @param SysUmUser
	 *            （机构账号管理实体）
	 * @return List<UmUser>
	 */
	public List<User> getUserList(User bean) {
		List<User> lists = new ArrayList<User>();
		lists = userInfoDao.getUserList(bean);
		return lists;
	}
	
	public List<User> getUserListByLoginName(User user) {
		List<User> list = userInfoDao.getUserListByLoginName(user);
		return list;
	}

	/**
	 * 
	 * Created on 2017年6月21日 .
	 * <p>
	 * Description {获取所有角色信息}
	 *
	 * @author zhangchunhua 
	 * @return List<Role>
	 */
	public List<Role> getAllRoleList() {
		List<Role> list = new ArrayList<Role>();
		list = roleDao.findAllList(new Role());
		return list;
	}

	/**
	 * Created on 2017年3月22日 Description {机构账号管理删除用户}
	 * 
	 * @author yanqizh
	 * @param User
	 *            （机构账号管理实体）
	 */
	@Transactional(readOnly = false)
	public JSONObject deleteUser(List<User> lists) {
		JSONObject result = new JSONObject();
		if (null != lists && lists.size() > 0) {
			for (User bean : lists) {
				User user = new User();
				user.setId(bean.getId());
				user.preUpdate();
				userInfoDao.deleteUmUser(user);
				userInfoDao.deleteSysUser(user);
				// 清除用户缓存
				UserUtils.clearCache(user);
			}
		}
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;
	}

	// /**
	// * Created on 2017年3月22日
	// * Description {修改密码}
	// * @author yanqizh
	// * @param User （机构账号管理实体）
	// */
	// public JSONObject updatePassword(User bean){
	// JSONObject result = new JSONObject();
	// try{
	// userInfoDao.updatePassword(bean);
	// result.put("result", CommonConstant.RESULT_SUCCESS);
	// return result;
	// }catch (Exception e) {
	// e.printStackTrace();
	// result.put("result", CommonConstant.RESULT_FAILURE);
	// return result;
	// }
	// }
	/**
	 * Created on 2017年3月22日 Description {是否启用}
	 * 
	 * @author yanqizh
	 * @param User
	 *            （机构账号管理实体）
	 */
	@Transactional(readOnly = false)
	public void whetherLoginFlag(List<User> lists) {

		if (null != lists && lists.size() > 0) {
			for (User bean : lists) {
				bean.preUpdate();
				userInfoDao.whetherLoginFlag(bean);
				UserUtils.clearCache(bean.getId(), bean.getLoginName());
			}
		}

	}

	// /**
	// * Created on 2017年3月22日
	// * Description {验证账号是否重复} sys_user
	// * @author yanqizh
	// * @param User （机构账号管理实体）
	// */
	// public JSONObject checkAccount(String loginName){
	// JSONObject result = new JSONObject();
	// User umbean = new User();
	// User sysbean = new User();
	// User filter = new User();
	// filter.setLoginName(loginName);
	// umbean = userInfoDao.findByLoginName(filter);
	// filter = new User();
	// sysbean = userInfoDao.findBySysLoginName(filter);
	// if(null!=umbean||null!=sysbean){
	// result.put("result", CommonConstant.RESULT_FAILURE);
	// result.put("content", "账号重复");
	// return result;
	// }else{
	// result.put("result", CommonConstant.RESULT_SUCCESS);
	// return result;
	// }
	// }
	/**
	 * Created on 2017年3月22日 Description 创建账号
	 * 
	 * @author yanqizh
	 * @param orgType
	 *            （机构类型）
	 * @param roleId
	 *            （角色ID）
	 * @param orgId
	 *            （机构ID）
	 * @param name
	 *            （用户名称）
	 */
	@Transactional(readOnly = false)
	public JSONObject setUpAccount(String roleId, String account, String name) {
		JSONObject result = new JSONObject();
		User user = new User();
		

		user.preInsert();
		// String id = sysbean.getId();
		user.setLoginName(account);
		user.setName(name);
		user.setPassword(entryptPassword("111111"));
		user.setLoginFlag("0");

		// user.preInsert();
		// user.setId(id);
		// user.setLoginName(account);
		// user.setName(name);
		user.setIsResetPwd(CommonConstant.COMMON_YES);

		// roleBean.preInsert();
		user.setRoleId(roleId);
		// roleBean.setUserId(id);

		userInfoDao.insertSysUserRole(user);
		userDao.insert(user);
		userInfoDao.insert(user);

		result.put("id", user.getId());
		result.put("account", account);
		return result;
	}

	/**
	 * 
	 * Created on 2017年4月11日 .
	 * <p>
	 * Description {生成账号规则}
	 *
	 * @author liuhuiqun
	 * @param value
	 * @param code
	 * @return String
	 */
	private String getAccount(String value, String code) {

		String name = "";
		if (value.indexOf("临床医生") > 0) {
			name = "D" + code;
		} else if (value.indexOf("检验医生") > 0) {
			name = "T" + code;
		} else if (value.indexOf("影像医生") > 0) {
			name = "I" + code;
		} else if (value.indexOf("随访员") > 0) {
			name = "F" + code;
		} else if (value.indexOf("录入员") > 0) {
			name = "K" + code;
		} else if (value.indexOf("管理员") > 0) {
			name = "A" + code;
		} else if (value.indexOf("信息员") > 0) {
			name = "S" + code;
		} else if (value.indexOf("临检中心") > 0) {
			name = "L" + code;
		} else {
			name = "A" + code;
		}
		String seq = SequenceUtils.getNextSequence(name);
		seq = String.format("%02d", StringUtils.toInteger(seq));
		SequenceUtils.lockNextSequence(name);
		return name + seq;
	}

	/**
	 * Created on 2017年3月22日 Description 重置密码
	 * 
	 * @author yanqizh
	 * @param userId
	 *            （用户ID）
	 */
	@Transactional(readOnly = false)
	public void reSetPassword(String userId) {
		User sysbean = new User();
		sysbean.setId(userId);
		sysbean.setPassword(entryptPassword("111111"));
		sysbean.preUpdate();
		userInfoDao.updatePassword(sysbean);
	}

	/**
	 * Created on 2017年3月22日 Description {修改角色} sys_user_role
	 * 
	 * @author yanqizh
	 * @param userId
	 *            （用户ID）
	 * @param roleId
	 *            （角色ID）
	 */
	public JSONObject updateRoleId(String userId, String roleId) {
		JSONObject result = new JSONObject();
		User sysbean = new User();
		try {
			sysbean.setUserId(userId);
			sysbean.setRoleId(roleId);
			userInfoDao.updateRoleId(sysbean);
			result.put("result", CommonConstant.RESULT_SUCCESS);
			result.put("content", "修改成功！");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", CommonConstant.RESULT_FAILURE);
			result.put("content", "修改失败！");
			return result;
		}
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	/**
	 * 
	 * Created on 2017年9月26日 .
	 * <p>
	 * Description {通过id获取user}
	 *
	 * @author guoxue 
	 * @param id
	 * @return User
	 */
	public User getUserById(String id) {
		User user = new User();
		user.setId(id);
		return userDao.getUserById(user);
	}

	/**
	 * 
	 * Created on 2017年9月26日 .
	 * <p>
	 * Description {获取userEmail}
	 *
	 * @author guoxue 
	 * @return List<User>
	 */
	public List<User> getManagerEmail() {
		List<User> list = userDao.getManagerEmail();
		return list;
	}

}
