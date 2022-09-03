package com.tuling.modules.home.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foundation.common.web.BaseController;
import com.tuling.modules.home.entity.Home;
import com.tuling.modules.home.service.HomeBizService;

/**
 * HomeController
 * @author guoxue 
 * @date 2017-06-19
 */
@Controller
@RequestMapping(value = "home/home")
public class HomeController extends BaseController{
	
	@Autowired
	private HomeBizService homeBizService;
	
	/**
	 * 
	 * Created on 2017年6月19日 .
	 * <p>
	 * Description {进入后台管理平台首页}
	 *
	 * @author guoxue 
	 * @param model
	 * @return String
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "index")
	public String itemIndex(Model model) {
		Home home = new Home();
		home = homeBizService.getHomePageContent();
		model.addAttribute("home", home);
		return "layout1.home.home";
	}

}
