/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.ComSequence;

/**
 * 
 * Created on 2017年4月11日
 * <p>
 * Description {序列Dao接口}
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author liuhuiqun
 */
@MyBatisDao
public interface ComSequenceDao extends CrudDao<ComSequenceDao> {
	
	public void insert(ComSequence comSequence);
	public int lockComSequence(String seqName);
	public void nextComSequence(String seqName);
	public String getComSequence(String seqName);

}
