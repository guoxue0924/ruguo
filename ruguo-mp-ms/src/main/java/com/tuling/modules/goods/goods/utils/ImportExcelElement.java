package com.tuling.modules.goods.goods.utils;

import java.math.BigDecimal;

/**
 * Created on 2017年06月13日
 * Description 导入Excel列配置
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class ImportExcelElement {

    private Integer columnNum; // 列序号

    private String columnName; // 列名称

    private String columnType; // 列类型

    private String sysDict; // 列对应字典

    private String sql; // 子查询

    private String tableColumn; // 对应数据库字段

    private Boolean isPrimaryKey; // 是否主键

    private String length; // 长度

    private Boolean isNotNull; // 是否不能为空

    private BigDecimal max; // 最大值

    private BigDecimal min; // 最小值

    private Boolean isAutoUUID; // UUID自动生成

    private String defaultFlag; // 默认值
    
    private Boolean isExcel; // 不来自excel
    
    private Boolean number; // 是否为数字

	public ImportExcelElement() {

    }

    public ImportExcelElement(Integer columnNum, String tableColumn, String columnName) {
        this.columnNum = columnNum;
        this.tableColumn = tableColumn;
        this.columnName = columnName;
        this.columnType = "String";
    }

    public ImportExcelElement(Integer columnNum, String tableColumn,
                              String columnName, String columnType,
                                Boolean isAutoUUID) {
        this.columnNum = columnNum;
        this.tableColumn = tableColumn;
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public ImportExcelElement(Integer columnNum, String tableColumn,
                              String columnName, String columnType,
                              String sysDict, String sql) {
        this.columnNum = columnNum;
        this.tableColumn = tableColumn;
        this.columnName = columnName;
        this.columnType = columnType;
        this.sysDict = sysDict;
        this.sql = sql;
    }
    
	public Boolean getIsExcel() {
		return isExcel;
	}

	public void setIsExcel(Boolean isExcel) {
		this.isExcel = isExcel;
	}

	public String getDefaultFlag() {
    	return defaultFlag;
    }
    
    public void setDefaultFlag(String defaultFlag) {
    	this.defaultFlag = defaultFlag;
    }

	public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getSysDict() {
        return sysDict;
    }

    public void setSysDict(String sysDict) {
        this.sysDict = sysDict;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Boolean getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(Boolean isNotNull) {
        this.isNotNull = isNotNull;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public Boolean getIsAutoUUID() {
        return isAutoUUID;
    }

    public void setIsAutoUUID(Boolean isAutoUUID) {
        this.isAutoUUID = isAutoUUID;
    }

	public Boolean getNumber() {
		return number;
	}

	public void setNumber(Boolean number) {
		this.number = number;
	}
}