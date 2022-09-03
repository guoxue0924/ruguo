package com.foundation.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.foundation.common.persistence.Page;
/**
 * bootgrid分页组件
 * @author hl huanglin@bjtuling.com
 * @date 2017-04-13
 */
@Component
public class PageHelper<T> {
	/**
	 * 当前页
	 */
	private int current = 1;

	/**
	 * 每页记录数
	 */
	private int rowCount = 10;

	/**
	 * 总记录数
	 */
	private int total = 0;

	/**
	 * 数据集
	 */
	private List<T> rows = new ArrayList<T>();

	public PageHelper() {

	}

	public PageHelper(Page page) {
		this.current = page.getCurrent();
		this.rowCount = page.getRowCount();
		this.total = page.getTotal();
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public void setRows(Page page, List<T> rows) {
		this.current = page.getCurrent();
		this.rowCount = page.getRowCount();
		this.total = page.getRowCount() != -1 ? page.getTotal() : rows.size();
		this.rows = rows;
	}

	public void setSort(String sort) {

	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
