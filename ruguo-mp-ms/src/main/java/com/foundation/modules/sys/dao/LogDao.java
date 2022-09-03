/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author zou
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
