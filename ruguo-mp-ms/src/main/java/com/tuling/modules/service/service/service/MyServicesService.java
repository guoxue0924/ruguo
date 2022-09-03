package com.tuling.modules.service.service.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.lang.StringUtil;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.service.CrudService;
import com.mysql.fabric.xmlrpc.base.Data;
import com.tuling.modules.service.service.dao.MyServicesDao;
import com.tuling.modules.service.service.entity.MyServiceInfo;
import com.tuling.modules.service.service.entity.MyServiceInfoFilter;
import com.tuling.modules.service.service.entity.MyServiceInfoParameter;

/**
 * MyServicesService
 * 
 * @author guoxue
 * @version 2017-09-26
 */
@Service
public class MyServicesService extends CrudService<MyServicesDao, MyServiceInfo> {

	@Autowired
	private MyServicesDao myServicesDao;

	/**
	 * 
	 * Created on 2017年9月27日 .
	 * <p>
	 * Description {获取服务列表}
	 *
	 * @author guoxue 
	 * @param myServiceInfoFilter
	 * @return List<MyServiceInfoFilter>
	 */
	public List<MyServiceInfoFilter> getServiceList(MyServiceInfoParameter myServiceInfoParameter) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(myServiceInfoParameter.getDisServiceEndTime())){
			if(CommonConstant.serviceDistanceDate.DICT_DISTANCE_DATE_ONE.equals(myServiceInfoParameter.getDisServiceEndTime())){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, 1);
				myServiceInfoParameter.setDisServiceEndTime(f.format(c.getTime()));
			}
			if(CommonConstant.serviceDistanceDate.DICT_DISTANCE_DATE_TWO.equals(myServiceInfoParameter.getDisServiceEndTime())){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, 2);
				myServiceInfoParameter.setDisServiceEndTime(f.format(c.getTime()));
			}
			if(CommonConstant.serviceDistanceDate.DICT_DISTANCE_DATE_THREE.equals(myServiceInfoParameter.getDisServiceEndTime())){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, 3);
				myServiceInfoParameter.setDisServiceEndTime(f.format(c.getTime()));
			}
			if(CommonConstant.serviceDistanceDate.DICT_DISTANCE_DATE_FOUR.equals(myServiceInfoParameter.getDisServiceEndTime())){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, 6);
				myServiceInfoParameter.setDisServiceEndTime(f.format(c.getTime()));
			}
		}
		
		List<MyServiceInfoFilter> list = myServicesDao.getServiceList(myServiceInfoParameter);
		List<MyServiceInfoFilter> newlist = new ArrayList<MyServiceInfoFilter>();
		if(!list.isEmpty()){
			for(MyServiceInfoFilter myServiceInfoFil : list){
				if(StringUtils.isBlank(myServiceInfoFil.getRelaPerName()) ){
					myServiceInfoFil.setRelaPerName(myServiceInfoFil.getMemberName());
					myServiceInfoFil.setRelaPerMobilePhone(myServiceInfoFil.getMobilePhone());
				}
				newlist.add(myServiceInfoFil);
			}
		}else{
			if(StringUtils.isNotBlank(myServiceInfoParameter.getRelaPerName())){
				List<MyServiceInfoFilter> myServiceInfoFilterList = myServicesDao.getServiceListT(myServiceInfoParameter);
				if(!myServiceInfoFilterList.isEmpty()){
					for(MyServiceInfoFilter myServiceInfoFil : myServiceInfoFilterList){
						if(StringUtils.isBlank(myServiceInfoFil.getRelaPerName())){
							myServiceInfoFil.setRelaPerName(myServiceInfoFil.getMemberName());
							myServiceInfoFil.setRelaPerMobilePhone(myServiceInfoFil.getMobilePhone());
						}
						newlist.add(myServiceInfoFil);
					}
				}
			}
		}
		return newlist;
	}

	public MyServiceInfoFilter getMyServiceInfoDetail(MyServiceInfoFilter myServiceInfoFilter) {
		MyServiceInfoFilter myServiceInfo = myServicesDao.getMyServiceInfoDetail(myServiceInfoFilter);
		if(StringUtils.isBlank(myServiceInfo.getRelaPerName()) ){
			myServiceInfo.setRelaPerName(myServiceInfo.getMemberName());
			myServiceInfo.setRelaPerMobilePhone(myServiceInfo.getMobilePhone());
			myServiceInfo.setRelaPerGenderName(myServiceInfo.getGenderName());
		}
		return myServiceInfo;
	}
	

	
}