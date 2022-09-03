package com.foundation.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foundation.common.persistence.TreeEntity;

import java.util.Date;

/**
 * Created on 2017骞�03鏈�14鏃�
 * Description 鍖哄垝淇℃伅entity
 * Copyright tuling (c) 2017 .
 * @author liuqing
 */
public class DictZone extends TreeEntity<DictZone> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private String id; // 涓婚敭ID

    private String parentId; // 鐖跺尯鍒�

    private String name; // 鍖哄垝鍚嶇О

    private String fullName; // 鍖呭惈鐖剁骇鍖哄垝鍏ㄨ矾寰�

    private String parentFullName; // 鐖剁骇鍖哄垝鍏ㄨ矾寰�

    private String code; // 鍖哄垝缂栫爜

    private String spell; // 鍖哄垝鍚嶇О瀵瑰簲鐨勬眽璇嫾闊�

    private String province; // 鐪佸悕绉�

    private String provinceCode; // 鐪佷唤缂栫爜

    private String city; // 鍩庡競鍚嶇О

    private String cityCode; // 鍩庡競缂栫爜

    private String county; // 鍖哄幙鍚嶇О

    private String countyCode; // 鍖哄幙缂栫爜

    private String town; // 涔￠晣鍚嶇О

    private String townCode; // 涔￠晣缂栫爜

    private String level; // 鍖哄垝绾у埆

    private String zoneTag; // 鍖哄垝鏍囪瘑鏄惁鏄浗瀹惰瘯鐐瑰幙

    private String isPoorCounty; // 鏄惁鏄传鍥板幙

    private String isAllCover; // 鏄惁鏄叏鍙ｅ緞瑕嗙洊鍦板尯

    private String isEnable; // 鏄惁鍚敤

    private Date enableDate; // 鍚敤鏃堕棿

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    @Override
    public DictZone getParent() {
        return parent;
    }

    @Override
    public void setParent(DictZone parent) {
        this.parent = parent;
    }

}