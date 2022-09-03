/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.demo.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.demo.entity.DemoFile;

/**
 * 用户上传资料DAO接口
 * @author hl
 * @version 2017-04-23
 */
@MyBatisDao
public interface DemoFileDao extends CrudDao<DemoFile> {
	
	/**
	 * 批量保存附件信息
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-23
	 */
	int insertByBatch(List<DemoFile> demoFileList);
}