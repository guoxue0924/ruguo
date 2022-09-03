/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberquery.web;




import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.utils.PageHelper;
import com.tuling.modules.goods.goods.utils.ExportConstants;
import com.tuling.modules.goods.goods.utils.ExportExcelUtils;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberquery.entity.MemberAdressFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfo;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoFilter;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResult;
import com.tuling.modules.member.memberquery.entity.MemberBasicInfoResultResult;
import com.tuling.modules.member.memberquery.entity.MemberRelationshipInfo;
import com.tuling.modules.member.memberquery.entity.MyConsultation;
import com.tuling.modules.member.memberquery.entity.MyOrder;
import com.tuling.modules.member.memberquery.service.MemberBasicInfoBizService;
import com.tuling.modules.member.memberquery.service.MemberBasicInfoService;
import com.tuling.modules.member.memberquery.service.MemberRelationshipInfoService;





/**
 * 会员个人信息Controller
 * 
 * @author wanggang
 * @version 2017-06-08
 */
@Controller
@RequestMapping(value = "member/memberquery")
public class MemberBasicInfoController extends BaseController {

	@Autowired
	private MemberBasicInfoBizService memberBasicInfoBizService;
	@Autowired
	private MemberRelationshipInfoService memberRelationshipInfoService;
	@Autowired
	private MemberBasicInfoService memberBasicInfoService;
	
	@ModelAttribute
	public MemberBasicInfoResult get(@RequestParam(required = false) String id) {
		MemberBasicInfoResult entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new MemberBasicInfoResult(id);
			entity = memberBasicInfoBizService.get(entity);
		}
		if (entity == null) {
			entity = new MemberBasicInfoResult();
			entity = new MemberBasicInfoResult();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.member.memberquery.memberBasicInfoIndex";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<MemberRelationshipInfo> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
		// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		MemberRelationshipInfo memberRelationshipInfo = obj.toJavaObject(MemberRelationshipInfo.class);
		// 分页step3
		PageHelper<MemberRelationshipInfo> page = memberRelationshipInfoService.findList(new Page(obj), memberRelationshipInfo);
		return page;

	}
	
	/**
	 * 显示会员详细信息
	 * 
	 * @author wanggang
	 * @version 2017-06-08
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request,Model model) throws UnsupportedEncodingException {
		// 获取会员信息
		MemberRelationshipInfo memberRelationshipInfo=new MemberRelationshipInfo();
		String code = request.getParameter("code");
		memberRelationshipInfo.setMemberCode(code);
		MemberBasicInfoResult memberBasicItemResult = memberBasicInfoService.queryMemberBasicInfoResultByCode(memberRelationshipInfo);
//		memberBasicItemResult.setOrgName(java.net.URLDecoder.decode(memberBasicInfoResult.getOrgName(),"UTF-8"));
	    // 添加会员信息
		memberBasicItemResult.setCode(code);
		model.addAttribute("memberBasicItemResult", memberBasicItemResult);
	   
		return "layout3.member.memberquery.memberBasicInfoDetail";
	}
	
	/**
	 * 显示会员地址详细信息
	 * 
	 * @author guoxue
	 * @version 2017-09-21
	 */
	@RequestMapping(value = "address")
	@ResponseBody
	public PageHelper<MemberAdressFilter> address(@RequestBody String param,Model model) {
		JSONObject obj = JSONObject.parseObject(param);
		MemberBasicInfo memberBasicInfo = obj.toJavaObject(MemberBasicInfo.class);
		// 获取会员地址信息
		PageHelper<MemberAdressFilter> page  = memberBasicInfoBizService.getMemberAdressFilter(new Page(obj), memberBasicInfo);
		return page;
	}	
	/**
	 * 显示会员关系人信息
	 * 
	 * @author liubing
	 * @version 2018-01-26
	 */
	@RequestMapping(value = "relationshipInfo")
	@ResponseBody
	public PageHelper<MemberRelationshipInfo> relationshipInfo(@RequestBody String param,Model model) {
		JSONObject obj = JSONObject.parseObject(param);
		MemberBasicInfo memberBasicInfo = obj.toJavaObject(MemberBasicInfo.class);
		MemberRelationshipInfo memberRelationshipInfo = new MemberRelationshipInfo();
		memberRelationshipInfo.setMemberCode(memberBasicInfo.getCode());
		// 获取会员地址信息MemberRelationshipInfoService
		PageHelper<MemberRelationshipInfo> page  = memberRelationshipInfoService.getMemberRelationshipInfo(new Page(obj), memberRelationshipInfo);
		return page;
	}	
	/**
	 * 显示会员地址详细信息
	 * 
	 * @author guoxue
	 * @version 2017-09-21
	 */
	@RequestMapping(value = "myconsultation")
	@ResponseBody
	public PageHelper<MyConsultation> myConsultation(@RequestBody String param,Model model) {
		JSONObject obj = JSONObject.parseObject(param);
		MemberBasicInfo memberBasicInfo = obj.toJavaObject(MemberBasicInfo.class);
		// 获取会员地址信息
		PageHelper<MyConsultation> page  = memberBasicInfoBizService.getmyConsultation(new Page(obj), memberBasicInfo);
		return page;
	}
	
	/**
	 * 显示会员地址详细信息
	 * 
	 * @author guoxue
	 * @version 2017-09-21
	 * @throws ParseException 
	 */
	@RequestMapping(value = "myorder")
	@ResponseBody
	public PageHelper<MyOrder> myOrder(@RequestBody String param,Model model) throws ParseException {
		JSONObject obj = JSONObject.parseObject(param);
		MemberBasicInfo memberBasicInfo = obj.toJavaObject(MemberBasicInfo.class);
		
		PageHelper<MyOrder> page  = memberBasicInfoBizService.getmyOrder(new Page(obj), memberBasicInfo);
		return page;
	}
	
	/**
	 * 
	 * Created on 2017年6月20日 .
	 * <p>
	 * Description {查询列表}
	 *
	 * @author wanggang
	 * @param param
	 * @return List
	 */
	@RequestMapping(value = "organizationpulllist")
    @ResponseBody
    public List<Organization> queryOrganizationPullDownList(Model model,Organization organization) {
		// 机构平台类型为渠道类型
		organization.setOrgPlatformType(CommonConstant.platformType.channel);
		// 查询数据
		List<Organization>  organizationPullList = memberBasicInfoBizService.queryOrganizationPullDownList(organization);
        return organizationPullList;
	}
	
	/**
	 * 会员查询下拉列表显示
	 * @author wanggang
	 * @date 2017-06-7
	 */
    @RequestMapping(value = "memberlevelinfopulllist")
    @ResponseBody
    public List<MemberLevelInfo> queryMemberlevelPullDownList(Model model,MemberLevelInfo memberLevelInfo) {
    	// 查询数据
    	List<MemberLevelInfo> memberLevelInfoPullList =  memberBasicInfoBizService.findList(memberLevelInfo);
        return memberLevelInfoPullList;
	}
    
    /**
	 * 
	 * Created on 2017年9月19日 .
	 * Description {会员信息导出}
	 * @author zhuming
	 * @param model
	 * @return String
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@RequestMapping(value = "exportexcel")
	public void exportExcel(@RequestParam("param") String param, HttpServletResponse response) throws UploadException, 

NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		JSONObject obj = JSONObject.parseObject(param);
		MemberBasicInfoFilter memberBasicInfoFilter = obj.toJavaObject(MemberBasicInfoFilter.class);
        Page page = new Page(obj);
        Boolean isPage = obj.getBoolean("isPage");
        if (isPage == null || !isPage) {
            page.setRowCount(-1);
        }
        // 查询导出的数据
        PageHelper<MemberBasicInfoResultResult> pageHelper = memberBasicInfoBizService.queryExportList(page, memberBasicInfoFilter);
        List<MemberBasicInfoResultResult> list = pageHelper.getRows();
    	// 调用导出接口
    	ExportExcelUtils.exportExcel(response, list, ExportConstants.MemberBasicInfoResultResult);
	}
    
}