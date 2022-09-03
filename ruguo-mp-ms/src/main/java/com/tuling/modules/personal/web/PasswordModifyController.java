package com.tuling.modules.personal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.utils.StringUtils;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.personal.service.PersonAffairService;

/**
 * 
 * Created on 2017年4月13日
 * <p>
 * Description {修改密码}
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author liuxianyin
 */
@Controller
@RequestMapping(value = "personal/password")
public class PasswordModifyController {

    @Autowired
    private PersonAffairService personAffairService;
    
    @Autowired
    private ResponseHelper responseHelper;
    
    @RequestMapping(value = "index")
    public String passwordIndex(Model model){
    	model.addAttribute("resetPaword", "0");
        return "layout1.personal.password.passwordModify";
    }
    
    @RequestMapping(value = "indexOnly")
    public String passwordindexOnly(Model model){
    	model.addAttribute("resetPaword", "1");
    	//修改个人信息
		String department = UserUtils.getUser().getDepartment();
		if (StringUtils.isEmpty(department)) {
			model.addAttribute("resetUserInfo", "1");
		} else {
			model.addAttribute("resetUserInfo", "0");
		}
        return "layout2.personal.password.passwordModify";
    }
    
    @RequestMapping(value = "checkPassword")
    @ResponseBody
    public ResponseHelper checkPassword(HttpServletRequest request){
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
        User user = UserUtils.getUser();
        JSONObject result = personAffairService.checkPassword(user.getLoginName(), newPassword, oldPassword);
        responseHelper.setSuccess("", result);
        return responseHelper;
    }
    
    
    
    @RequestMapping(value = "modifyPassword")
    @ResponseBody
    public ResponseHelper modifyPassword(HttpServletRequest request){
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
//        String resetPaword = request.getParameter("resetPaword");
        User user = UserUtils.getUser();
        JSONObject result = personAffairService.updatePassword(user.getLoginName(), newPassword, oldPassword);
        responseHelper.setSuccess("", result);
        return responseHelper;
    }
    
}
