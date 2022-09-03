/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.common.persistence.CrudDao;
import com.foundation.common.persistence.DataEntity;
import com.foundation.common.persistence.Page;
import com.foundation.modules.sys.utils.PageHelper;

/**
 * Service基类
 * @author zou
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	@Autowired
	private PageHelper<T> pageHelper;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象 bootgrid 
	 * @param entity
	 * @return
	 */
	
	public PageHelper<T> findPage(Page page, T entity) {
		// 分页查询，需要在filter实体中set分页信息
		entity.setPage(page);
	    // 查询数据
		List<T> list = dao.findList(entity);
		// 装载数据
		pageHelper.setRows(page, list);
        return pageHelper;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
