package com.tuling.modules.demo.web.item;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foundation.common.web.BaseController;
/**
 * Demo Controller
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-01
 */
@Controller
@RequestMapping(value = "demo")
public class DemoItemController extends BaseController{
	
	/**
	 * 项目查询主页
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-01
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "item/index")
	public String itemIndex(Model model) {
		
		//layout1(页面模板).demo(工程modules下文件目录).item(工程modules下文件目录).itemIndex(itemIndex.jsp)
		return "layout1.demo.item.itemIndex";
//		return "redirect:" + "/demo/itemList";
	}
	
	@RequiresPermissions("user")
	@RequestMapping(value = "item/mgrindex")
	public String itemIndex2(Model model) {
		
		return "layout1.demo.item.mgrindex";
	}
	
	

}
