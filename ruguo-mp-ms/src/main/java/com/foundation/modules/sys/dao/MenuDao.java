/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author zou
 * @version 2014-05-16
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);

	public String getParentName(Menu menu);
	
}
