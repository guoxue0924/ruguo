package com.tuling.modules.personal.dao;

import java.util.List;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisDao;
import com.tuling.modules.personal.entity.MemberFeedbackFilter;
import com.tuling.modules.personal.entity.MemberFeedbackResult;

/**
 * 意见反馈Dao
 * @author zhuming
 */
@MyBatisDao
public interface MemberFeedbackDao extends CrudDao<MemberFeedbackResult>{
	
	/**
	 * 查询意见反馈列表
	 * @param memberFeedbackFilter
	 */
	public List<MemberFeedbackResult> queryMemberFeedbackList(MemberFeedbackFilter memberFeedbackFilter);
	/**
	 * 查询状态集合
	 * @param memberFeedbackResult
	 */
	public List<MemberFeedbackResult> queryOperationStateList(MemberFeedbackResult memberFeedbackResult);
	
	/**
	 * 查询回复内容
	 */
	public MemberFeedbackResult queryReplyContent(MemberFeedbackResult memberFeedbackResult);
	
	/**
	 * 更新回复人，回复内容，回复状态
	 * @param memberFeedbackResult
	 */
	public void updateMemberFeedback(MemberFeedbackResult memberFeedbackResult);

}
