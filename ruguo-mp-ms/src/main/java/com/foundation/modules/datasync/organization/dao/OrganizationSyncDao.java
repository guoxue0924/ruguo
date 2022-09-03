package com.foundation.modules.datasync.organization.dao;

import java.util.List;

import com.foundation.common.persistence.annotation.MyBatisDao;
import com.foundation.modules.datasync.organization.entity.OrganizationInfo;

@MyBatisDao
public interface OrganizationSyncDao{

	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(新增)
	 */
	public void addOrganization(List<OrganizationInfo> list);
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(修改)
	 */
	public void modifyOrganization(List<OrganizationInfo> list);
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(逻辑删除)
	 */
	public void deleteOrganization(List<OrganizationInfo> list);
	
	/**
	 * author:zhaoyufeng
	 * date:2018-01-16
	 * desc:机构同步(物理删除)
	 */
	public void deleteOrganizationByPhysical(List<OrganizationInfo> list);
	
}
