/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.entity;

import com.foundation.common.persistence.DataEntity;

/**
 * ServicePackageEntity
 * @author guoxue
 * @version 2017-06-05
 */
public class ServicePackage extends DataEntity<ServicePackage> {
	
	private static final long serialVersionUID = 1L;
	private String servicePackageCode;		// 商品编码
	private String servicePackageName;	// 商品名称
	
	
	public ServicePackage() {
		super();
	}

	public ServicePackage(String id){
		super(id);
	}

	public String getServicePackageCode() {
		return servicePackageCode;
	}

	public void setServicePackageCode(String servicePackageCode) {
		this.servicePackageCode = servicePackageCode;
	}

	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	

	
	
}