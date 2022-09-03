package com.foundation.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.SqlEntity;

/**
 * Created on 2017年03月29日
 * Description
 * Copyright tuling (c) 2017 .
 *
 * @author liuqing
 */
@MyBatisDao
public interface DictCommonUtilsDao {

    /**
     * Created on 2017年03月29日 .
     * Description 执行SQL
     * @param entity
     * @author liuqing
     * @return
     */
    public List<Map<String, Object>> selectSql(SqlEntity entity);

    /**
     * Created on 2017年03月29日 .
     * Description 执行SQL
     * @param entity
     * @author liuqing
     * @return
     */
    public int updateSql(SqlEntity entity);

    /**
     * Created on 2017年03月29日 .
     * Description 执行SQL
     * @param entity
     * @author liuqing
     * @return
     */
    public int insertSql(SqlEntity entity);

    /**
     * Created on 2017年03月29日 .
     * Description 获取当前时间
     * @param entity
     * @author liuqing
     * @return
     */
    public SqlEntity getCurrentDate();
}
