package com.foundation.modules.sys.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.common.web.BaseController;
import com.foundation.modules.sys.entity.Log;
import com.foundation.modules.sys.service.LogService;
import com.foundation.modules.sys.utils.PageHelper;

/**
 * DemoLog Controller
 * 
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-01
 */
@Controller
@RequestMapping(value = "sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 项目查询主页
	 * 
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-01
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "")
	public String index(Model model) {
		// layout1(页面模板).demo(工程modules下文件目录).log(工程modules下文件目录).logIndex(logIndex.jsp)
		return "layout1.sys.log.logIndex";
		// return "redirect:" + "/demo/itemList";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "list")
	@ResponseBody
	public PageHelper<Log> list(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		Log log = obj.toJavaObject(Log.class);
		PageHelper<Log> page = logService.findLogList(new Page(obj), log);
		return page;
	}

}
