package com.tuling.modules.personal.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.ResponseHelper;
import com.foundation.modules.sys.utils.UserUtils;
import com.tuling.modules.personal.service.PersonAffairService;

/**
 * 
 * Created on 2017年3月06日
 * <p>
 * Description {个人信息管理Controller}
 * <p>
 * Copyright tuling (c) 2016 .
 *
 * @author qinzhen
 */
@Controller
@RequestMapping(value = "personal/PersonAffair")
public class PersonAffairController extends BaseController {
	
	@Autowired
    private PersonAffairService personAffairService;
	
	@Autowired
	private ResponseHelper responseHelper;
	
	/**
	 * 
	 * Created on 2017年3月06日 .
	 * <p>
	 * Description {显示个人信息管理页}
	 *
	 * @author qinzhen 
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
    @RequestMapping(value = "index")
    public String peisonalInfoIndex(HttpServletResponse response, Model model) {
    	User user = UserUtils.getUser();
        model.addAttribute("userObj",user);
    	model.addAttribute("isFirst","0");
    	//by liuhuan at 20170518 修改条件判断
//    	if ((!StringUtils.isEmpty(userType) && !StringUtils.isEmpty(account))  && userType.equals("2") && "A".equals(account.substring(0, 1)) && account.length() == 9 ) {
//    		model.addAttribute("isServiceAdmin","1");
//    	} else {
//    		model.addAttribute("isServiceAdmin","0");
//    	}
        return "layout1.personal.personAffair.PersonAffairForm";
    }
    
    @RequestMapping(value = "indexFirst")
    public String peisonalIndexFirst(HttpServletResponse response, Model model) {
    	User user = UserUtils.getUser();
        model.addAttribute("userObj",user);
    	model.addAttribute("isFirst","1");
//    	String userType = user.getUserType();
    	//String account = user.getLoginName();
    	//by liuhuan at 20170518 修改条件判断
//    	if ((!StringUtils.isEmpty(userType) && !StringUtils.isEmpty(account))  && userType.equals("2") && "A".equals(account.substring(0, 1))) {
//    		model.addAttribute("isServiceAdmin","1");
//    	} else {
//    		model.addAttribute("isServiceAdmin","0");
//    	}
        return "layout2.personal.personAffair.PersonAffairForm";
    }
    
    /**
     * 
     * Created on 2017年3月06日 .
     * <p>
     * Description {保存个人信息管理页面内容}
     *
     * @author qinzhen   
     * @param param
     * @return String
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "save")
	@ResponseBody
	public ResponseHelper save(User user) throws UnsupportedEncodingException {
    	
    	responseHelper = personAffairService.updatePersonInfo(user);
		return responseHelper;
	}
    
}
