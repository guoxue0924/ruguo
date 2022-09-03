package com.foundation.modules.sys.utils;

import java.util.List;

import com.foundation.common.utils.SpringContextHolder;
import com.foundation.modules.sys.dao.RoleDao;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserRoleDao;
import com.foundation.modules.sys.entity.Role;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.entity.UserRole;

/**
 * 角色工具类
 * 
 * @author jiyingming
 * @version 2017-04-05
 */
public class RoleUtils {

	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static UserRoleDao userRoleDao = SpringContextHolder.getBean(UserRoleDao.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	/**
	 * 返回角色列表（List集合）
	 * 
	 * @return add by jiyingming at 2017-04-05
	 */
	public static List<Role> getRoleList(String roleType) {

		Role role = new Role();

		// if(StringUtils.isNotEmpty(roleType)){
		// role.setRoleType(roleType);
		// }
		
		List<Role> roleList = roleDao.findList(role);

		return roleList;
	}
}
