/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.DictZoneFilter;

/**
 * 区划字典DAO接口
 * 
 * @author xiaoh
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictZoneDao extends CrudDao<DictZone> {

    /**
     * 根据条件查询区划信息
     * @param filter
     * @return List<DictZone>
     */
    public List<DictZone> getDistrictList(DictZoneFilter filter);

    /**
     * 查询相同编号的区划信息
     * @param filter
     * @return List<DictZone>
     */
    public List<DictZone> getSameCodeDict(DictZoneFilter filter);

    /**
     * 查询相同编号的区划信息
     * @param filter
     * @return List<DictZone>
     */
    public List<DictZone> getSameNameDict(DictZoneFilter filter);

    /**
     * Created on 2017年03月16日 .
     * Description 查询符合条件的区划信息数量
     * @param filter
     * @author liuqing
     * @return Integer
     */
    public Integer getDictZoneCount(DictZoneFilter filter);

    /**
     * Created on 2017年03月16日 .
     * Description 逻辑删除区划
     * @param entity
     * @author liuqing
     * @return
     */
    public void deleteLogical(DictZone entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新区划信息省的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateProvince(DictZone entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新区划信息市的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateCity(DictZone entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新区划信息区县的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateCounty(DictZone entity);
    
    /**
     * Created on 2017年05月20日 .
     * Description 根据编码获取机构信息
     * @param entity
     * @author liuhuan
     * @return DictZone
     */
    public DictZone getByZoneCode(DictZone entity);
}
