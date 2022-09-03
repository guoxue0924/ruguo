/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberlevel.service;



import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IDHelper;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberlevel.utils.IntervalCompareUtils;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;






/**
 * 会员等级Service
 * 
 * @author wanggang
 * @version 2017-06-03
 */
@Service
@Transactional(readOnly = true)
public class MemberLevelInfoBizService  {

	@Autowired
	private MemberLevelInfoService memberLevelInfoService;	
	
	@Autowired
	protected ResponseHelper responseHelper;
	
	@Autowired
	private PageHelper<MemberLevelInfo> pageHelper;
	
	/**
	 * 显示会员等级信息
	 * @author wanggang
	 * @date 2017-06-3
	 */
	public PageHelper<MemberLevelInfo> findLevelManageList(Page page, MemberLevelInfo memberLevelInfo) {

		// 分页查询，需要在filter实体中set分页信息
		memberLevelInfo.setPage(page);
		// 查询数据
		List<MemberLevelInfo> list = memberLevelInfoService.findLevelManageList(memberLevelInfo);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;
	}
	
	/**
	 * 查询会员等级信息
	 * @author wanggang
	 * @date 2017-06-14
	 */
	public MemberLevelInfo get(MemberLevelInfo memberLevelInfo) {
		memberLevelInfo = memberLevelInfoService.get(memberLevelInfo);
		return memberLevelInfo;
	}	
	
	/**
	 * 插入会员等级信息
	 * @author wanggang
	 * @date 2017-06-5
	 */
	@Transactional(readOnly = false)
	public ResponseHelper saveMemberLevelInfo(MemberLevelInfo memberLevelInfo) {
		boolean intervalFlag;
		boolean nameFlag = false;
		List<MemberLevelInfo> intervalInfoList = null;

		// 根据是否是新纪录的数据进行判断是插入还是修改
	    if (memberLevelInfo.getIsNewRecord()) {
			// 查询会员等级姓名
			String memberLevelName = memberLevelInfoService.findMemberLevelName(memberLevelInfo);
	    	// 获取现有的会员等级区间信息
	        intervalInfoList = memberLevelInfoService.findLevelManageList(memberLevelInfo);
	        // 判断会员等级区间是否重叠
	        intervalFlag = intervalInfo(intervalInfoList,memberLevelInfo);
	        // 会员等级名称不存在
	        if (StringUtils.isBlank(memberLevelName)) {
	        	//如果会员区间不重叠
	        	if (intervalFlag) {
        		    memberLevelInfo.setMemberLevelCode(IDHelper.genUuid());
				    memberLevelInfoService.saveMemberLevelInfo(memberLevelInfo);
				    responseHelper.setSuccess("操作成功!");
	        	} else {
	        		responseHelper.setFail("输入会员上下限区间与已存在区间发生冲突,请重新输入!");
	        	}
	    	   
		    } else {
			    responseHelper.setFail("会员等级名称:" + memberLevelName + ",已存在无法添加!"); 
		    }
	    } else {
	    	// 获取现有的会员等级区间信息
	    	intervalInfoList = memberLevelInfoService.findPresentInfoOutsideList(memberLevelInfo);
	    	intervalFlag = intervalInfo(intervalInfoList,memberLevelInfo);
	    	if (!intervalInfoList.isEmpty()) {
		    	for (MemberLevelInfo intervalInfo:intervalInfoList) {
		    		if (intervalInfo.getMemberLevelName().equals(memberLevelInfo.getMemberLevelName())) {
		    			nameFlag = false; 
		    			break;
		    		} else {
		    			nameFlag = true;
		    		}
		    	}
	    	} else {
	    		nameFlag = true;
	    	}	    		 
	    	// 如果会员区间不重叠
	    	if (intervalFlag) {
	    		if (nameFlag) {
	    			memberLevelInfoService.saveMemberLevelInfo(memberLevelInfo);
			        responseHelper.setSuccess("操作成功!");
	    		} else {
	    			responseHelper.setFail("会员等级名称:" + memberLevelInfo.getMemberLevelName() + ",已存在无法添加!");
	    		}
		        
	    	} else {
	    		responseHelper.setFail("输入会员上下限区间与已存在区间发生冲突,请重新输入!");
	    	}
	    }
	    return responseHelper;

	}
	public Boolean intervalInfo(List<MemberLevelInfo> intervalInfoList, MemberLevelInfo memberLevelInfo) {
		boolean intervalFlag = true;
		if (!intervalInfoList.isEmpty()) {
			for (MemberLevelInfo intervalInfo:intervalInfoList) {
				// 判断会员等级区间是否重叠
			    intervalFlag = IntervalCompareUtils.IntervalCompare(intervalInfo.getMemberLevelMin(), memberLevelInfo.getMemberLevelMin(), intervalInfo.getMemberLevelMax(), memberLevelInfo.getMemberLevelMax());
			    if (!intervalFlag) {
			    	break;
			    }
			}
		} 
		return intervalFlag;
	}
	
	/**
     * Created on 2017年6月7日
     * Description {根据会员等级ID，删除会员等级信息}
     * @author wanggang
     * @param lists （会员等级实体集合）
     * @return {success：0}  0代表成功  1代表失败
     */
	@Transactional(readOnly = false)
	public JSONObject deleteOffice(String[] lists){
		JSONObject result = new JSONObject();
		MemberLevelInfo entity = new MemberLevelInfo();
		MemberBasicInfo memberBasicInfo = new MemberBasicInfo();
		String memberLevelName = null;
		String memberLevelCode = null;
		List<MemberBasicInfo> memberLevelCodeList = null;		
		if(null!=lists&&lists.length>0){
			for (String id : lists) {
				entity.setId(id);
				List<MemberLevelInfo> levelInfolist = memberLevelInfoService.findList(entity);
				for (MemberLevelInfo memberLevelInfo : levelInfolist) {
					// 查询会员信息表中的会员等级code
					memberBasicInfo.setMemberLevelCode(memberLevelInfo.getMemberLevelCode());
					memberLevelCodeList = memberLevelInfoService.queryByMemberLevelCode(memberBasicInfo);
					for (MemberBasicInfo memberLevelInfoCode : memberLevelCodeList) {
					     memberLevelCode = memberLevelInfoCode.getMemberLevelCode();
					     break;
					}					 
				}
				if (StringUtils.isBlank(memberLevelCode)) {
					entity.preUpdate();
				    memberLevelInfoService.delete(entity);
				    result.put("content", "操作成功!");
					  
				} else {
				    entity.setMemberLevelCode(memberLevelCode);
				    memberLevelName = memberLevelInfoService.findMemberLevelName(entity);
				    result.put("error","会员等级名称:"+ memberLevelName +",有会员使用无法删除!"); 
		        }
			}
		}			
		return result;

	}
}