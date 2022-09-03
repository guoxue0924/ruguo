package com.tuling.modules.goods.goods.utils;

/**
 * Created on 2017年06月13日
 * Description 导出excel元素
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class ExportExcelElement {

    private String name; // 实体类对应属性名称

    private String displayName; // 导出excel中显示名称

    private Integer width; // 宽度

    private String sysDict; // 字典

    private String type; // 数据类型

    private String format; // 格式

    public ExportExcelElement(String name, String displayName, Integer width) {

        this.name = name;

        this.displayName = displayName;

        this.width = width;
    }

    public ExportExcelElement(String name, String displayName, Integer width, String sysDict) {

        this.name = name;

        this.displayName = displayName;

        this.width = width;

        this.sysDict = sysDict;
    }

    public ExportExcelElement(String name, String displayName, Integer width, String sysDict, String type, String format) {

        this.name = name;

        this.displayName = displayName;

        this.width = width;

        this.sysDict = sysDict;

        this.type = type;

        this.format = format;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSysDict() {
        return sysDict;
    }

    public void setSysDict(String sysDict) {
        this.sysDict = sysDict;
    }
}