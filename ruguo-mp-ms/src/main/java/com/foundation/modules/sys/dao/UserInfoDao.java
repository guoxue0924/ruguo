/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.User;

/**
 * 用户管理DAO接口
 * @author yanqizhi
 * @version 2017-03-21
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<User> {
	/**
     * Created on 2017年3月22日
     * Description {机构账号管理}
     * @author yanqizh
     * @param User （机构账号管理实体）
     * @return List<SysUmUser>
     */
	public List<User> getUserList(User bean);
	
	/**
     * Created on 2017年3月22日
     * Description {机构账号管理根据登录名获取账户信息}
     * @author zhangchunhua
     * @param User （机构账号管理实体）
     * @return List<SysUmUser>
     */
	public List<User> getUserListByLoginName(User user);
	
	/**
     * Created on 2017年3月22日
     * Description {机构账号管理删除用户}
     * @author yanqizh
     * @param filter id （机构账号管理ID）
     */
	public void deleteUmUser(User filter);
	/**
     * Created on 2017年3月22日
     * Description {机构账号管理删除用户}
     * @author yanqizh
     * @param filter id （机构账号管理ID）
     */
	public void deleteSysUser(User filter);
	/**
     * Created on 2017年3月22日
     * Description {修改密码}
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public void updatePassword(User bean);
	/**
     * Created on 2017年5月8日
     * Description {修改密码}
     * @author zhouhao
     * @param User （机构账号管理实体）
     */
	public void updateReset(User bean);
	/**
     * Created on 2017年3月22日
     * Description {是否启用}
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public void whetherLoginFlag(User bean);
//	/**
//     * Created on 2017年3月22日
//     * Description {保存用户} sys_user
//     * @author yanqizh
//     * @param User （机构账号管理实体）
//     */
//	public void insertSysUser(User bean);
//	/**
//     * Created on 2017年3月22日
//     * Description {保存用户} sys_user_info
//     * @author yanqizh
//     * @param User （机构账号管理实体）
//     */
//	public void insertUmUser(User bean);
	/**
     * Created on 2017年3月22日
     * Description {验证账号是否重复} sys_user_info
     * @author yanqizh
     * @param filter （机构账号管理实体）
     */
	public User findByLoginName(User filter);
	/**
     * Created on 2017年3月22日
     * Description {验证账号是否重复} sys_user
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public User findBySysLoginName(User filter);
	/**
     * Created on 2017年3月22日
     * Description {通过机构ID查询账号实体} orgId
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public void updateOrgId(User bean);
	/**
     * Created on 2017年3月22日
     * Description {保存用户} sys_user_role
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public void insertSysUserRole(User bean);
	/**
     * Created on 2017年3月22日
     * Description {修改角色} sys_user_role
     * @author yanqizh
     * @param User （机构账号管理实体）
     */
	public void updateRoleId(User bean);
	
}