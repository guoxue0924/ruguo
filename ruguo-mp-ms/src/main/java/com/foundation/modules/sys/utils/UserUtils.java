/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.foundation.common.utils.CacheUtils;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.common.utils.StringUtils;
import com.foundation.modules.sys.dao.MenuDao;
import com.foundation.modules.sys.dao.RoleDao;
import com.foundation.modules.sys.dao.RoleMenuDao;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserRoleDao;
import com.foundation.modules.sys.entity.Menu;
import com.foundation.modules.sys.entity.Role;
import com.foundation.modules.sys.entity.RoleMenu;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.entity.UserRole;
import com.foundation.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.google.common.collect.Maps;

/**
 * 用户工具类
 * 
 * @author zou
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static RoleMenuDao roleMenuDao = SpringContextHolder.getBean(RoleMenuDao.class);
	private static UserRoleDao userRoleDao = SpringContextHolder.getBean(UserRoleDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_ALL_MENU_LIST = "allmenuList";
	public static final String CACHE_FIRST_MENU_ID = "firstMenuId";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	public static final String CACHE_MENU_INIT_PATH_MAP = "menuInit";
	public static final String SHARDDB_CACHE = "sharddbCache";
	public static final String SHARDDB_CACHE_PROVINCECODE = "provinceCode";

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id) {
		// CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + id);
		// UserUtils.clearCache(getUser());
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			User param = new User();
			param.setId(id);
			param.setShardDBName("00");
			user = userDao.get(param);
			if (user == null) {
				return null;
			}
			UserRole userRole = new  UserRole();
			if(!(param.getId().isEmpty())){
				//通过userId获取roleid
				userRole.setUserId(param.getId());
				userRole = userRoleDao.get(userRole);
			}
			Role newrole = new Role();
			if(!(userRole.getRoleId().isEmpty())){
				newrole.setId(userRole.getRoleId());
			}	
			user.setRoleList(roleDao.findList(newrole));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static void set(String id) {
		User param = new User();
		param.setId(id);
		User user = userDao.get(param);
		if (user != null) {
			UserRole userRole = new  UserRole();
			if(!(user.getId().isEmpty())){
				//通过userId获取roleid
				userRole.setUserId(user.getId());
				userRole = userRoleDao.get(userRole);
			}
			Role newrole = new Role();
			if(!(userRole.getRoleId().isEmpty())){
				newrole.setId(userRole.getRoleId());
			}	
			user.setRoleList(roleDao.findList(newrole));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null) {
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null) {
				return null;
			}
			User user1 = userDao.get(user);
			UserRole userRole = new  UserRole();
			if(!(user1.getId().isEmpty())){
				//通过userId获取roleid
				userRole.setUserId(user1.getId());
				userRole = userRoleDao.get(userRole);
			}
			Role newrole = new Role();
			if(!(userRole.getRoleId().isEmpty())){
				newrole.setId(userRole.getRoleId());
			}	
			user1.setRoleList(roleDao.findList(newrole));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user1.getId(), user1);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user1.getLoginName(), user1);
		}
		return user;
	}

	public static void setByLoginName(String loginName) {
		User user = userDao.getByLoginName(new User(null, loginName));
		if (user != null) {
			User user1 = userDao.get(user);
			UserRole userRole = new  UserRole();
			if(!(user1.getId().isEmpty())){
				//通过userId获取roleid
				userRole.setUserId(user1.getId());
				userRole = userRoleDao.get(userRole);
			}
			Role newrole = new Role();
			if(!(userRole.getRoleId().isEmpty())){
				newrole.setId(userRole.getRoleId());
			}	
			user1.setRoleList(roleDao.findList(newrole));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user1.getId(), user1);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user1.getLoginName(), user1);
		}
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_AUTH_INFO);
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		removeCache(CACHE_MENU_INIT_PATH_MAP);
		removeCache(SHARDDB_CACHE);
		removeCache(SHARDDB_CACHE_PROVINCECODE);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		// if (user.getOffice() != null && user.getOffice().getId() != null){
		// CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ +
		// user.getOffice().getId());
		// }
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(String id, String loginName) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + id);
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		// if (user.getOffice() != null && user.getOffice().getId() != null){
		// CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ +
		// user.getOffice().getId());
		// }
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getUser() {
		Principal principal = getPrincipal();
		if (principal != null && principal.getId() != null && !"".equals(principal.getId())) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	public static List<Role> getRoleList() {
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
		if (roleList == null) {
			// User user = getUser();
			// if (user.isAdmin()){
			roleList = roleDao.findAllList(new Role());
			// }else{
			// Role role = new Role();
			// role.getSqlMap().put("dsf",
			// BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
			// roleList = roleDao.findList(role);
			// }
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
		List<Menu> newMenuList = new ArrayList<Menu>();
		if (menuList == null) {
			User user = getUser();
			if(StringUtils.isBlank(user.getId())){
				return null;
			}
			if (user.isAdmin()) {
				menuList = menuDao.findAllList(new Menu());
				for(Menu menu : menuList){
					String name = menuDao.getParentName(menu);
					menu.getParent().setName(name);
					newMenuList.add(menu);
				}
				menuList = newMenuList;
			} else {
				Menu m = new Menu();
				m.setUserId(user.getId());
				//通过userId获取RoleId
				String roleId = userDao.get(user.getId()).getRoleId();
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				List<RoleMenu> roleMenulist = roleMenuDao.findList(roleMenu);
				for(RoleMenu newRoleMenu : roleMenulist){
					Menu menu = new Menu();
					Menu newMenu = new Menu();
					menu.setId(newRoleMenu.getMenuId());
					newMenu = menuDao.get(menu);
					String name = menuDao.getParentName(newMenu);
					newMenu.getParent().setName(name);
					newMenuList.add(newMenu);
				}
				menuList = newMenuList;
				//通过roleId获取menuId
//				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	public static List<Menu> getAllMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(CACHE_ALL_MENU_LIST);
		List<Menu> newMenuList = new ArrayList<Menu>();
		if (menuList == null) {
			menuList = menuDao.findAllList(new Menu());
			for(Menu menu : menuList){
				String name = menuDao.getParentName(menu);
				menu.getParent().setName(name);
				newMenuList.add(menu);
			}
			menuList = newMenuList;
			putCache(CACHE_ALL_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前请求的menu对象
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-03
	 */
	public static Menu getMenuByUri(String requestUri, List<Menu> menuList) {
		Menu currentMenu = new Menu();
		for (Menu menu : menuList) {
			if (StringUtils.isNotBlank(menu.getHref())) {
				int x = menu.getHref().indexOf("?");
				boolean bool = false;
				if (x == -1) {
					if (menu.getHref().equals(requestUri)) {
						bool = true;
					}
				} else {
					String uri = menu.getHref().substring(0, x);
					if (uri.equals(requestUri)) {
						bool = true;
					}
				}
				if (bool) {
					currentMenu = menu;
					String[] parentids = StringUtils.split(menu.getParentIds(), ",");
					for (Menu m : menuList) {
						if (m.getId().equals(parentids[2])) {
							currentMenu.getParent().setParent(m);
							break;
						}
					}
					break;
				}
			}
		}
		return currentMenu;
	}

	/**
	 * 获取当前用户授权菜单中首个一级菜单id（用户登陆后默认加载首个一级菜单下子菜单）
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-26
	 */
	public static String getFristMenuID() {

		String firstMenuId = (String) getCache(CACHE_FIRST_MENU_ID);

		if (StringUtils.isBlank(firstMenuId)) {
			List<Menu> menuList = getMenuList();
			List<Menu> firstMenuList = new ArrayList<Menu>();
			for (Menu menu : menuList) {
				if ("1".equals(menu.getParentId())) {
					firstMenuList.add(menu);
				}
			}
			/*
			 * // 按sort排序 升序 if (!firstMenuList.isEmpty()) { if
			 * (firstMenuList.size() > 1) { Collections.sort(firstMenuList, new
			 * Comparator<Menu>() { public int compare(Menu arg0, Menu arg1) {
			 * return arg0.getSort().compareTo(arg1.getSort()); } }); }
			 * 
			 * }
			 */
			if (!firstMenuList.isEmpty()) {
				firstMenuId = firstMenuList.get(0).getId();
				putCache(CACHE_FIRST_MENU_ID, firstMenuId);
			}
		}
		return firstMenuId;
	}

	/**
	 * 获取一级菜单下默认初始化页面路径。
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-05-03
	 */
	public static String getInitMenu(String parentid) {

		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>) getCache(CACHE_MENU_INIT_PATH_MAP);

		if (menuMap == null) {
			menuMap = Maps.newHashMap();
			List<Menu> menuList = getMenuList();
			for (Menu menu : menuList) {
				String menuPath = menu.getHref();
				if (StringUtils.isNotBlank(menuPath)) {
					if ("init".equals(menu.getTarget())) {
						String[] parentids = StringUtils.split(menu.getParentIds(), ",");
						String pid = parentids[2];
						if (StringUtils.isNoneBlank(pid)) {
							menuMap.put(pid, menuPath);
						}
					}
				}
			}

			putCache(CACHE_MENU_INIT_PATH_MAP, menuMap);
		}

		String initMenuPath = menuMap.get(parentid);
		if (initMenuPath == null) {
			return "";
		}
		return initMenuPath;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		// Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		// getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		// getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

	// public static Map<String, Object> getCacheMap(){
	// Principal principal = getPrincipal();
	// if(principal!=null){
	// return principal.getCacheMap();
	// }
	// return new HashMap<String, Object>();
	// }
}
