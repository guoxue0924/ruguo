package com.foundation.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foundation.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created on 2017年05月06日
 * Description
 * Copyright tuling (c) 2017 .
 *
 * @author liuqing
 */
public class SqlEntity extends DataEntity<DictZoneFilter> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sqlString; // sql语句

    private Date currentDate; // 当前时间

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}