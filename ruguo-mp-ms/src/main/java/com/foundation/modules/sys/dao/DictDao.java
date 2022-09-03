/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author zou
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);

	public List<String> getDictAllType(Dict dict);
	
}
