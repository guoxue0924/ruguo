package com.tuling.modules.demo.web.layout;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foundation.common.web.BaseController;

/**
 * DemoLayoutController
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-11
 */
@Controller
@RequestMapping(value = "demo")
public class DemoLayoutController extends BaseController {

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout1")
	public String layout1(Model model) {

		// layout1(页面模板).demo(工程modules下文件目录).layout(工程modules下文件目录).layout1(layout1.jsp)
		return "layout1.demo.layout.layout1";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout2")
	public String layout2(Model model) {

		return "layout2.demo.layout.layout2";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout3")
	public String layout3(Model model) {

		return "layout2.demo.layout.layout3";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout4")
	public String layout4(Model model) {

		return "layout1.demo.layout.layout4";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout5")
	public String layout5(Model model) {

		return "layout1.demo.layout.layout5";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "layout/layout6")
	public String layout6(Model model) {

		return "layout1.demo.layout.layout6";
	}

}
