/**
 * Copyright &copy; 2012-2013  All rights reserved.
 */
package com.foundation.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.common.utils.DateUtils;
import com.foundation.modules.sys.dao.LogDao;
import com.foundation.modules.sys.entity.Log;
import com.foundation.modules.sys.utils.PageHelper;

/**
 * 日志Service
 * 
 * @author zou
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	@Autowired
	private PageHelper<Log> pageHelper;

	/**
	 * 新版分页
	 * @author hl huanglin@bjtuling.com
	 * @date 2017-04-14
	 */
	public PageHelper<Log> findLogList(Page page, Log log) {

		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null) {
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null) {
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}

		// 分页查询，需要在filter实体中set分页信息
		log.setPage(page);
		// 查询数据
		List<Log> list = dao.findList(log);
		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;

	}

}
