/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.foundation.modules.sys.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.sys.entity.Organization;

/**
 * 机构管理服务DAO接口
 * @author yanqizhi
 * @version 2017-03-15
 */
@MyBatisDao
public interface OrganizationDao extends CrudDao<Organization> {
	/**
     * Created on 2017年3月15日
     * Description {2.4.1	查询服务机构列表}
     * @author yanqizh
     * @param Organization （服务机构实体）
     * @return List<Organization>
     */
	public List<Organization> getOfficeList(Organization organization);
	/**
     * Created on 2017年9月4日
     * Description {查询内部机构列表}
     * @author guoxue
     * @param Organization （服务机构实体）
     * @return List<Organization>
     */
	public List<Organization> getOfficeListInside(Organization organization);
	/**
     * Created on 2017年6月30日
     * Description {2.4.1	查询服务机构列表}
     * @author yuelingyun
     * @param Organization （服务机构实体）
     * @return List<Organization>
     */
	public List<Organization> getOfficeListByName(Organization organization);
	/**
     * Created on 2017年5月10日
     * Description {2.4.1	查询服务机构列表}
     * @author yanqizh
     * @param Organization （服务机构实体）
     * @return List<Organization>
     */
	public List<Organization> findByAddress(Organization organization);
	
	/**
     * Created on 2017年3月15日
     * Description {添加下级服务机构}
     * @author yanqizh
     * @param Organization （服务机构实体）
	 * @return 
     */
	public void insertOrganization(Organization organization);
	/**
     * Created on 2017年3月15日
     * Description {修改下级服务机构}
     * @author yanqizh
     * @param Organization （服务机构实体）
	 * @return 
     */
	public void updateOrganization(Organization organization);
//	/**
//     * Created on 2017年3月15日
//     * Description {批量删除服务机构}
//     * @author yanqizh
//     * @param organization （服务机构实体）
//	 * @return
//     */
//	public void deleteOffice(String[] UmOrganizationids);

	/**
     * Created on 2017年3月15日
     * Description {是否启用服务机构}
     * @author yanqizh
     * @param id （服务机构ID）
     * @param isEnable （是否启用0是1否）
	 * @return 
     */
	//public void whetherIsEnable(Organization organization);
	/**
     * Created on 2017年3月15日
     * Description {通过机构ID查询机构实体}
     * @author yanqizh
     * @param filter （服务机构ID）
	 * @return 
     */
	public Organization findById(Organization filter);
	/**
     * Created on 2017年3月15日
     * Description {通过所属区划查询机构实体}
     * @author yanqizh
     * @param filter ownZoneCode （所属区划）
	 * @return 
     */
	public Organization findByOwnZoneCode(Organization filter);
	/**
     * Created on 2017年3月22日
     * Description {通过机构ID查询机构下级实体}
     * @author yanqizh
     * @param filter （服务机构ID）
	 * @return 
     */
	public List<Organization> findByParentId(Organization filter);
	/**
     * Created on 2017年3月22日
     * Description {通过机构ID查询机构下级实体}
     * @author yanqizh
     * @param Organization （机构实体）
	 * @return 
     */
	public List<Organization> findByParentIdBean(Organization bean);
	/**
     * Created on 2017年3月2日
     * Description {通过机构CODE查询机构实体}
     * @author yanqizh
     * @param filter code （服务机构ID）
	 * @return 
     */
	public Organization findByCode(Organization filter);
	
	/**
     * Created on 2017年3月2日
     * Description {通过机构CODE查询机构实体}
     * @author yanqizh
     * @param filter id （服务机构ID）
	 * @return 
     */
	public List<Organization> findByCodeList(Organization filter);
	
	/**
     * Created on 2017年3月2日
     * Description {通过机构CODE查询机构实体}
     * @author yanqizh
     * @param filter name （服务机构Name）
	 * @return 
     */
	public List<Organization> findByCodeName(Organization filter);
	/**
     * Created on 2017年05月12日 .
     * Description 更新区划信息省的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateProvince(Organization entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新区划信息市的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateCity(Organization entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新区划信息区县的名字
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateCounty(Organization entity);
    /**
     * Created on 2017年05月12日 .
     * Description 更新管理机构的机构名称
     * @param entity
     * @author yanqizhi
     * @return
     */
    public void updateName(Organization entity);
    /**
     * Created on 2017年05月12日 .
     * Description 查询所有机构
     * @param entity
     * @author yanqizhi
     * @return
     */
    public List<Organization> findByList();
	
}