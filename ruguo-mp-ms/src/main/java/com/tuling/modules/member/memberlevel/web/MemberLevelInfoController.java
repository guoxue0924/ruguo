/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.member.memberlevel.web;





import java.math.BigDecimal;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.utils.PageHelper;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.tuling.modules.member.memberlevel.entity.MemberLevelInfo;
import com.tuling.modules.member.memberlevel.service.MemberLevelInfoBizService;



/**
 * 会员等级管理Controller
 * 
 * @author wanggang
 * @version 2017-06-03
 */
@Controller
@RequestMapping(value = "member/memberlevel")
public class MemberLevelInfoController extends BaseController {

	@Autowired
	private MemberLevelInfoBizService memberLevelInfoBizService;
	
	@ModelAttribute
	public MemberLevelInfo get(@RequestParam(required = false) String id) {
		MemberLevelInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = new MemberLevelInfo(id);
			entity = memberLevelInfoBizService.get(entity);
		}
		if (entity == null) {
			entity = new MemberLevelInfo();
			entity = new MemberLevelInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).user(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.member.memberlevel.memberLevelIndex";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "regist2")
	public String regist2(Model model) {

		return "layout3.member.memberlevel.memberLevelEdit";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<MemberLevelInfo> list(@RequestBody String param,HttpServletRequest request, HttpServletResponse response) {
    	// 分页step1
		JSONObject obj = JSONObject.parseObject(param);
		// 分页step2
		MemberLevelInfo levelManage = obj.toJavaObject(MemberLevelInfo.class);
		// 分页step3
		PageHelper<MemberLevelInfo> page = memberLevelInfoBizService.findLevelManageList(new Page(obj), levelManage);
		
		return page;

	}
	
	@RequestMapping(value = "edit")
	public String edit(MemberLevelInfo memberLevelInfo, Model model) {
		Double memberLevelMax = Double.parseDouble(memberLevelInfo.getMemberLevelMax().toString());
		Double memberLevelMin = Double.parseDouble(memberLevelInfo.getMemberLevelMin().toString());
		if ((memberLevelMax.intValue() - memberLevelMax) == 0) {
			memberLevelInfo.setMemberLevelMax(new BigDecimal(memberLevelMax.intValue()));
		} else {
			memberLevelInfo.setMemberLevelMax(memberLevelInfo.getMemberLevelMax());
		}
		if ((memberLevelMin.intValue() - memberLevelMin) == 0) {
			memberLevelInfo.setMemberLevelMin(new BigDecimal(memberLevelMin.intValue()));
		} else {
			memberLevelInfo.setMemberLevelMin(memberLevelInfo.getMemberLevelMin());
		}
		model.addAttribute("memberLevelInfo", memberLevelInfo);
		return "layout3.member.memberlevel.memberLevelEdit";
	}
	
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(MemberLevelInfo memberLevelInfo, HttpServletRequest request) {
	    responseHelper = memberLevelInfoBizService.saveMemberLevelInfo(memberLevelInfo);
		return responseHelper;
	}
	
    /**
     * 
     * Created on 2017年6月7日 .
     * <p>
     * Description {批量刪除}
     *
     * @author wanggang
     * @param pojo
     * @return String
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "delete")
    @ResponseBody
    public String deleteRecordList(@RequestBody String pojo) {
        JSONObject obj = JSONObject.parseObject(pojo);
        JSONArray id = obj.getJSONArray("id");
        String[] ids = new String[]{};
        JSONObject json = null;
    	for (int i = 0; i < id.size(); i++) {
 	        ids = insert(ids,id.getJSONObject(i).getString("id").toString());
 		}
        json = memberLevelInfoBizService.deleteOffice(ids);		
        return json.toString();
    }
    private static String[] insert(String[] arr, String str){
	    int size = arr.length;
	    String[] tmp = new String[size + 1];
	    System.arraycopy(arr, 0, tmp, 0, size);
	    tmp[size] = str;
	    return tmp;
    }   
    
}