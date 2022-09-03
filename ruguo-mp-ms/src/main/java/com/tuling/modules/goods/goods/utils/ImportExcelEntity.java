package com.tuling.modules.goods.goods.utils;
import java.util.List;

/**
 * Created on 2017年06月13日
 * Description 导入Excel配置实体
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class ImportExcelEntity {

    private String tableName; // 表名

    private String shardDBName; // 数据库

    private String sheetNum;

    private List<ImportExcelElement> listElement;
    
    private String tableNameSheet;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getShardDBName() {
        return shardDBName;
    }

    public void setShardDBName(String shardDBName) {
        this.shardDBName = shardDBName;
    }

    public List<ImportExcelElement> getListElement() {
        return listElement;
    }

    public void setListElement(List<ImportExcelElement> listElement) {
        this.listElement = listElement;
    }

    public String getSheetNum() {
        return sheetNum;
    }

    public void setSheetNum(String sheetNum) {
        this.sheetNum = sheetNum;
    }

	public String getTableNameSheet() {
		return tableNameSheet;
	}

	public void setTableNameSheet(String tableNameSheet) {
		this.tableNameSheet = tableNameSheet;
	}
}