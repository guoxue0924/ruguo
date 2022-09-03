package com.foundation.modules.sys.entity;

import java.util.Date;

import com.foundation.common.persistence.DataEntity;

/**
 * Created on 2017年03月14日
 * Description 区划信息查询条件entity
 * Copyright tuling (c) 2017 .
 * @author liuqing
 */
public class DictZoneFilter extends DataEntity <DictZoneFilter> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; // 主键ID

    private String parentId; // 父区划

    private String name; // 区划名称

    private String code; // 区划编码

    private String codeFuzzy; // 区划编码模糊

    private String spell; // 区划名称对应的汉语拼音

    private String province; // 省名称

    private String provinceCode; // 省份编码

    private String city; // 城市名称

    private String cityCode; // 城市编码

    private String county; // 区县名称

    private String countyCode; // 区县编码

    private String town; // 乡镇名称

    private String townCode; // 乡镇编码

    private String level; // 区划级别

    private String zoneTag; // 区划标识是否是国家试点县

    private String isPoorCounty; // 是否是贫困县

    private String isAllCover; // 是否是全口径覆盖地区

    private String isEnable; // 是否启用

    private Date enableDate; // 启用时间

    private Date startEnableDate; // 启用时间

    private Date endEnableDate; // 启用时间

    private String exceptCode; // 除去此code

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeFuzzy() {
        return codeFuzzy;
    }

    public void setCodeFuzzy(String codeFuzzy) {
        this.codeFuzzy = codeFuzzy;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getZoneTag() {
        return zoneTag;
    }

    public void setZoneTag(String zoneTag) {
        this.zoneTag = zoneTag;
    }

    public String getIsPoorCounty() {
        return isPoorCounty;
    }

    public void setIsPoorCounty(String isPoorCounty) {
        this.isPoorCounty = isPoorCounty;
    }

    public String getIsAllCover() {
        return isAllCover;
    }

    public void setIsAllCover(String isAllCover) {
        this.isAllCover = isAllCover;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public Date getStartEnableDate() {
        return startEnableDate;
    }

    public void setStartEnableDate(Date startEnableDate) {
        this.startEnableDate = startEnableDate;
    }

    public Date getEndEnableDate() {
        return endEnableDate;
    }

    public void setEndEnableDate(Date endEnableDate) {
        this.endEnableDate = endEnableDate;
    }

    public String getExceptCode() {
        return exceptCode;
    }

    public void setExceptCode(String exceptCode) {
        this.exceptCode = exceptCode;
    }

}