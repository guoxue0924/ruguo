package com.tuling.modules.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserInfoDao;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.service.SystemService;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.personal.dao.MemberFeedbackDao;
import com.tuling.modules.personal.entity.MemberFeedbackFilter;
import com.tuling.modules.personal.entity.MemberFeedbackResult;

/**
 * 意见反馈Service
 * @author zhuming
 */
@Service
@Transactional(readOnly = true)
public class MemberFeedbackService extends CrudService<MemberFeedbackDao,MemberFeedbackResult>{
	
	@Autowired 
	private MemberFeedbackDao memberFeedbackDao;
	
	@Autowired
	private PageHelper<MemberFeedbackResult> pageHelper;
	
	/**
	 * 查询意见反馈列表
	 * @param page
	 * @param memberFeedbackFilter
	 */
	public PageHelper<MemberFeedbackResult> queryMemberFeedbackList(Page page, MemberFeedbackFilter memberFeedbackFilter) {

		// 分页查询，需要在filter实体中set分页信息
		memberFeedbackFilter.setPage(page);
		// 查询数据
		List<MemberFeedbackResult> list = memberFeedbackDao.queryMemberFeedbackList(memberFeedbackFilter);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;
	}
	
	/**
	 * 查询操作状态集合
	 * @param memberFeedbackResult
	 */
	public List<MemberFeedbackResult> queryOperationStateList(MemberFeedbackResult memberFeedbackResult){
		
		return  memberFeedbackDao.queryOperationStateList(memberFeedbackResult);
		
	}
	
	/**
	 * 查询回复内容
	 * @param memberFeedbackResult
	 */
	public MemberFeedbackResult queryReplyContent(MemberFeedbackResult memberFeedbackResult){
		
		return memberFeedbackDao.queryReplyContent(memberFeedbackResult);
		
	}
	/**
	 * 执行更新回复相关内容操作
	 */
	public String updateMemberFeedback(MemberFeedbackResult memberFeedbackResult){
		String success = "0";
		try {
			memberFeedbackDao.updateMemberFeedback(memberFeedbackResult);
		} catch (Exception e) {
			e.printStackTrace();
		    success = "1";
		}
		return success;
	}


}