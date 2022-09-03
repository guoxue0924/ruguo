/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.service;

import java.util.List;

import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.service.CrudService;
import com.foundation.common.utils.CacheUtils;
import com.foundation.modules.sys.dao.DictDao;
import com.foundation.modules.sys.entity.Dict;
import com.foundation.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author zou
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

	@Autowired
	private PageHelper<Dict> pageHelper;

	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		CacheUtils.remove(DictUtils.CACHE_DICT_TYPE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		CacheUtils.remove(DictUtils.CACHE_DICT_TYPE_LIST);
	}

	/**
	 * Created on 2017年6月8日
	 * Description {查询}
	 * @author jiyingming
	 * @param dict 传递参数
	 * @return PageHelper<Dict> 返回参数
	 */
	public PageHelper<Dict> query(Page page, Dict dict){

		// 分页查询，需要在filter实体中set分页信息
		dict.setPage(page);

		List<Dict> list = dao.findList(dict);

		// 装载数据
		pageHelper.setRows(page, list);

		return pageHelper;
	}

}
