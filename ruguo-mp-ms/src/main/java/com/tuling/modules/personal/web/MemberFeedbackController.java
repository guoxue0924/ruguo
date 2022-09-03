package com.tuling.modules.personal.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.personal.entity.MemberFeedbackFilter;
import com.tuling.modules.personal.entity.MemberFeedbackResult;
import com.tuling.modules.personal.service.MemberFeedbackService;
import com.tuling.modules.personal.service.PersonAffairService;

/**
 * 
 * Created on 2017年9月26日
 * Description {意见反馈}
 * Copyright tuling (c) 2016 .
 * @author zhuming
 */
@Controller
@RequestMapping(value = "feedback/information")
public class MemberFeedbackController extends BaseController{
	
	@Autowired
	private MemberFeedbackService memberFeedbackService;
    
	/**
	 * 意见反馈页面
	 * @param model
	 */
    @RequestMapping(value = "index")
    public String passwordIndex(Model model){
        return "layout1.personal.feedback.feedbackIndex";
    }
    
    /**
     * 查询操作状态集合
     * @return
     */
    @RequestMapping(value = "queryOperationStateList")
    @ResponseBody
    public List<MemberFeedbackResult> queryOperationStateList(Model model,MemberFeedbackResult memberFeedbackResult){
    	
    	List<MemberFeedbackResult> operationStateList = memberFeedbackService.queryOperationStateList(memberFeedbackResult);
    	
    	return operationStateList;
    	
    }
    
    /**
     * 查询意见反馈列表数据
     * @param param
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<MemberFeedbackResult> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		MemberFeedbackFilter memberFeedbackFilter = obj.toJavaObject(MemberFeedbackFilter.class);
		// 分页step3
		PageHelper<MemberFeedbackResult> page = memberFeedbackService.queryMemberFeedbackList(new Page(obj), memberFeedbackFilter);
		return page;

	}
    
    /**
	 * 回复意见反馈
	 * @version 2017-09-28
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "queryReplyContent")
	public String queryReplyContent(MemberFeedbackResult memberFeedbackResult,HttpServletRequest request,
			HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		String id = memberFeedbackResult.getId();
		memberFeedbackResult = memberFeedbackService.queryReplyContent(memberFeedbackResult);
		String replyContent = memberFeedbackResult.getReplyContent();
		if (StringUtils.isEmpty(replyContent)||"undefined".equals(replyContent)) {
			replyContent = "";
		}
		model.addAttribute("replyContent", replyContent);
		model.addAttribute("id",id);
		return "layout3.personal.feedback.replyContentIndex";
	}
    
	/**
	 * 修改回复内容
	 * @param memberFeedbackResult
	 * @throws UploadException
	 */
	@RequestMapping(value = "updateMemberFeedback")
	@ResponseBody
	public ResponseHelper updateMemberFeedback(@RequestBody String str,HttpServletRequest request,HttpServletResponse response){
		User user = UserUtils.getUser();
		JSONObject param = JSON.parseObject(str);
		String id = param.getString("id");
		String replyContent = param.getString("replyContent");
		MemberFeedbackResult memberFeedbackResult = new MemberFeedbackResult();
		memberFeedbackResult.setId(id);
		memberFeedbackResult.setReplyContent(replyContent);
		memberFeedbackResult.setReplyPersonnelCode(user.getId());
		memberFeedbackResult.setReplyPersonnelName(user.getName());
		memberFeedbackService.updateMemberFeedback(memberFeedbackResult);
		responseHelper.setSuccess("保持信息成功");// 返回成功状态 和 消息
		return responseHelper;
	}
   
}
