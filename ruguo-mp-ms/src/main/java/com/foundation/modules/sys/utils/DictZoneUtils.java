/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.utils;

import java.util.List;
import java.util.Map;

import com.foundation.common.constant.CommonConstant;
import com.foundation.common.utils.CacheUtils;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.common.utils.StringUtils;
import com.foundation.modules.sys.dao.DictZoneDao;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.DictZoneFilter;
import com.google.common.collect.Maps;

/**
 * 区划字典工具类
 * 
 * @author xiaoh
 * @version 2013-5-29
 */
public class DictZoneUtils {

	private static DictZoneDao dictZoneDao = SpringContextHolder.getBean(DictZoneDao.class);



	public static final String CACHE_DICT_ZONE_MAP = "dictZoneMap";

	public static String getDictZoneName(String code) {
		if (StringUtils.isNotBlank(code)) {
			return getDictZoneMap().get(code).getName();
		}
		return null;
	}

	public static Map<String, DictZone> getDictZoneMap() {
		@SuppressWarnings("unchecked")
		Map<String, DictZone> dictZoneMap = (Map<String, DictZone>) CacheUtils.get(CACHE_DICT_ZONE_MAP);
		if (dictZoneMap == null) {
			dictZoneMap = Maps.newHashMap();
			for (DictZone dictZone : dictZoneDao.findAllList(new DictZone())) {
				if (!dictZoneMap.containsKey(dictZone.getCode())) {
					dictZoneMap.put(dictZone.getCode(), dictZone);
				}
			}
			CacheUtils.put(CACHE_DICT_ZONE_MAP, dictZoneMap);
		}

		return dictZoneMap;
	}

	/**
	 * 通过区划编码获取区划级别
	 * 
	 * @param code
	 * @return String
	 */
	public static String getLevelByDictZoneCode(String code) {
		// 区划编码为空或位数不对（不等于9）返回空
		if (StringUtils.isEmpty(code) || code.length() != 9) {
			return null;
		}

		String provinceStr = code.substring(0, 2); // 省级编码
		String cityStr = code.substring(2, 4); // 市级编码
		String countyStr = code.substring(4, 6); // 区级编码
		String townStr = code.substring(6, 9); // 乡级编码

		if (CommonConstant.DICT_ZONE_STATECENTER.equals(code)) { // 国家级
			return CommonConstant.DictZoneLevel.state;
		} else if (StringUtils.toInteger(provinceStr) > 0 && StringUtils.toInteger(cityStr) == 0
				&& StringUtils.toInteger(countyStr) == 0 && StringUtils.toInteger(townStr) == 0) { // 省级
			return CommonConstant.DictZoneLevel.provice;
		} else if (StringUtils.toInteger(provinceStr) > 0 && StringUtils.toInteger(cityStr) > 0
				&& StringUtils.toInteger(countyStr) == 0 && StringUtils.toInteger(townStr) == 0) { // 市级
			return CommonConstant.DictZoneLevel.city;
		} else if (StringUtils.toInteger(provinceStr) > 0 && StringUtils.toInteger(cityStr) > 0
				&& StringUtils.toInteger(countyStr) > 0 && StringUtils.toInteger(townStr) == 0) { // 区级
			return CommonConstant.DictZoneLevel.county;
		} else if (StringUtils.toInteger(provinceStr) > 0 && StringUtils.toInteger(cityStr) > 0
				&& StringUtils.toInteger(countyStr) > 0 && StringUtils.toInteger(townStr) > 0) { // 乡级
			return CommonConstant.DictZoneLevel.town;
		}

		return null;
	}

	/**
	 * 校验区划级别和区划编码
	 * 
	 * @param level
	 * @param code
	 * @return boolean 不符合规范返回false
	 */
	public static boolean checkLevelAndCode(String level, String code) {
		// 区划编码为空或位数不对（不等于9）返回空
		if (StringUtils.isEmpty(level)) {
			return false;
		}

		// 区划编码不是数字返回false
		if (!StringUtils.isNumeric(code)) {
			return false;
		}

		// 区划级别和区划编码是否匹配
		if (level.equals(getLevelByDictZoneCode(code))) {
			return true;
		}

		return false;
	}

	/**
	 * 根据条件查询区划信息
	 * 
	 * @param id
	 *            区划主键
	 * @param dictCode
	 *            区划编码
	 * @return boolean 区划编码是否存在
	 */
	public static boolean isExistCode(String id, String dictCode) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictCode)) {
			return false;
		}

		DictZoneFilter filter = new DictZoneFilter();
		if (id == null) {
			id = "";
		}
		filter.setId(id);
		filter.setCode(dictCode);
		List<DictZone> list = dictZoneDao.getSameCodeDict(filter);
		return list.size() > 0;
	}

	/**
	 * 根据条件查询区划信息
	 *
	 * @param id
	 *            区划主键
	 * @param dictName
	 *            区划名称
	 * @return boolean 区划编码是否存在
	 */
	public static boolean isExistName(String id, String dictName, String parentId) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictName)) {
			return false;
		}

		DictZoneFilter filter = new DictZoneFilter();
		if (id == null) {
			id = "";
		}
		filter.setId(id);
		filter.setName(dictName);
		filter.setParentId(parentId);
		List<DictZone> list = dictZoneDao.getSameNameDict(filter);
		return list.size() > 0;
	}

	/**
	 * 根据区划编码查询区划信息
	 * 
	 * @param dictCode
	 *            区划编码
	 * @return DictZone
	 */
	public static DictZone getDictZoneByCode(String dictCode) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictCode)) {
			return null;
		}

		DictZoneFilter filter = new DictZoneFilter();
		filter.setCode(dictCode);
		List<DictZone> list = dictZoneDao.getDistrictList(filter);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据区划主键查询区划信息
	 * 
	 * @param id
	 *            区划主键
	 * @return DictZone
	 */
	public static DictZone getDictZoneById(String id) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(id)) {
			return null;
		}

		DictZoneFilter filter = new DictZoneFilter();
		filter.setId(id);
		List<DictZone> list = dictZoneDao.getDistrictList(filter);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据条件查询下一级子区划信息
	 * 
	 * @param dictCode
	 *            区划编码
	 * @return List<DictZone>
	 */
	public static List<DictZone> getDictZoneByParentCode(String dictCode) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictCode)) {
			return null;
		}

		DictZone dictZone = getDictZoneByCode(dictCode);

		return getChildDictZone(dictZone, "");
	}

	/**
	 * 根据条件查询下一级子区划信息
	 * 
	 * @param dictCode
	 *            区划编码
	 * @param isEnable
	 *            启用状态
	 * @return List<DictZone>
	 */
	public static List<DictZone> getDictZoneByParentCode(String dictCode, String isEnable) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictCode)) {
			return null;
		}

		DictZone dictZone = getDictZoneByCode(dictCode);

		return getChildDictZone(dictZone, isEnable);
	}

	/**
	 * 根据条件查询下一级子区划信息
	 * 
	 * @param id
	 *            区划编码
	 * @return List<DictZone>
	 */
	public static List<DictZone> getDictZoneByParentId(String id) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(id)) {
			return null;
		}

		DictZone dictZone = getDictZoneById(id);

		return getChildDictZone(dictZone, "");
	}

	/**
	 * 根据条件查询下一级子区划信息
	 * 
	 * @param dictZone
	 *            区划
	 * @param isEnable
	 *            启用状态
	 * @return List<DictZone>
	 */
	private static List<DictZone> getChildDictZone(DictZone dictZone, String isEnable) {
		if (dictZone == null) {
			return null;
		}

		String level = dictZone.getLevel();

		DictZoneFilter filter = new DictZoneFilter();
		filter.setParentId(dictZone.getId());
		filter.setIsEnable(isEnable);
		if (!CommonConstant.DictZoneLevel.town.equals(level)) {
			filter.setLevel(String.valueOf(Integer.parseInt(level) + 1));
		}

		return dictZoneDao.getDistrictList(filter);
	}

	/**
	 * 根据条件查询所有子区划信息
	 * 
	 * @param dictCode
	 *            区划编码
	 * @return List<DictZone>
	 */
	public static List<DictZone> getAllDictZoneByParentCode(String dictCode) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(dictCode)) {
			return null;
		}

		DictZone dictZone = getDictZoneByCode(dictCode);

		return getAllDictZoneByParent(dictZone);
	}

	/**
	 * 根据条件查询所有子区划信息
	 * 
	 * @param id
	 *            区划编码
	 * @return List<DictZone>
	 */
	public static List<DictZone> getAllDictZoneByParentId(String id) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(id)) {
			return null;
		}

		DictZone dictZone = getDictZoneById(id);

		return getAllDictZoneByParent(dictZone);
	}

	/**
	 * 根据父区划查询所有子区划信息
	 * 
	 * @param dictZone
	 * @return List<DictZone>
	 */
	private static List<DictZone> getAllDictZoneByParent(DictZone dictZone) {
		if (dictZone == null) {
			return null;
		}

		String level = dictZone.getLevel();

		DictZoneFilter filter = new DictZoneFilter();
		if (setFilterByLevel(dictZone, level, filter)) {
			return null;
		}

		return dictZoneDao.getDistrictList(filter);
	}

	/**
	 * 根据父区划查询下一级启用的子区划信息
	 * 
	 * @param dictZone
	 * @return List<DictZone>
	 */
	public static List<DictZone> getStartUseDictZoneByParent(DictZone dictZone) {
		if (dictZone == null) {
			return null;
		}

		String level = dictZone.getLevel();

		DictZoneFilter filter = new DictZoneFilter();
		filter.setIsEnable(CommonConstant.DictStartStatus.start); // 启用
		if (setFilterByLevel(dictZone, level, filter)) {
			return null;
		}

		return dictZoneDao.getDistrictList(filter);
	}

	/**
	 * 根据父区划查询下一级启用的子区划信息数量
	 * 
	 * @param dictZone
	 * @return Integer
	 */
	public static Integer getStartUseDictZoneCountByParent(DictZone dictZone) {
		if (dictZone == null) {
			return 0;
		}

		String level = dictZone.getLevel();

		DictZoneFilter filter = new DictZoneFilter();
		filter.setIsEnable(CommonConstant.DictStartStatus.start); // 启用
		if (setFilterByLevel(dictZone, level, filter)) {
			return 0;
		}

		return dictZoneDao.getDictZoneCount(filter);
	}

	/**
	 * Created on 2017年03月16日 . Description 根据区划级别设置查询条件
	 * 
	 * @param dictZone
	 * @param level
	 * @param filter
	 * @author liuqing
	 * @return boolean
	 */
	private static boolean setFilterByLevel(DictZone dictZone, String level, DictZoneFilter filter) {
		if (CommonConstant.DictZoneLevel.town.equals(level)) {
			return true;
		}
//		else if (CommonConstant.DictZoneLevel.county.equals(level)) {
//			filter.setCountyCode(dictZone.getCode());
//		} else if (CommonConstant.DictZoneLevel.city.equals(level)) {
//			filter.setCityCode(dictZone.getCode());
//		} else if (CommonConstant.DictZoneLevel.provice.equals(level)) {
//			filter.setProvinceCode(dictZone.getCode());
//		}
		filter.setParentId(dictZone.getId());
		return false;
	}

	/**
	 * 根据父区划赋值子区划
	 * 
	 * @param parentDictZone
	 * @param childDictZone
	 * @return
	 */
	public static void setParentIdsAndAllCode(DictZone parentDictZone, DictZone childDictZone) {
		if (parentDictZone == null || childDictZone == null) {
			return;
		}

		childDictZone.setParentIds(
				parentDictZone.getParentIds() + childDictZone.getId() + CommonConstant.DICT_PARENTIDS_SEPARATOR);

		String childLevel = childDictZone.getLevel();
		if (CommonConstant.DictZoneLevel.provice.equals(childLevel)) {
			childDictZone.setProvince(childDictZone.getName());
			childDictZone.setProvinceCode(childDictZone.getCode());
			childDictZone.setCity("");
			childDictZone.setCityCode("");
			childDictZone.setCounty("");
			childDictZone.setCountyCode("");
			childDictZone.setTown("");
			childDictZone.setTownCode("");
		} else if (CommonConstant.DictZoneLevel.city.equals(childLevel)) {
			childDictZone.setProvince(parentDictZone.getName());
			childDictZone.setProvinceCode(parentDictZone.getCode());
			childDictZone.setCity(childDictZone.getName());
			childDictZone.setCityCode(childDictZone.getCode());
			childDictZone.setCounty("");
			childDictZone.setCountyCode("");
			childDictZone.setTown("");
			childDictZone.setTownCode("");
		} else if (CommonConstant.DictZoneLevel.county.equals(childLevel)) {
			childDictZone.setProvince(parentDictZone.getProvince());
			childDictZone.setProvinceCode(parentDictZone.getProvinceCode());
			childDictZone.setCity(parentDictZone.getName());
			childDictZone.setCityCode(parentDictZone.getCode());
			childDictZone.setCounty(childDictZone.getName());
			childDictZone.setCountyCode(childDictZone.getCode());
			childDictZone.setTown("");
			childDictZone.setTownCode("");
		} else if (CommonConstant.DictZoneLevel.town.equals(childLevel)) {
			childDictZone.setProvince(parentDictZone.getProvince());
			childDictZone.setProvinceCode(parentDictZone.getProvinceCode());
			childDictZone.setCity(parentDictZone.getCity());
			childDictZone.setCityCode(parentDictZone.getCityCode());
			childDictZone.setCounty(parentDictZone.getName());
			childDictZone.setCountyCode(parentDictZone.getCode());
			childDictZone.setTown(childDictZone.getName());
			childDictZone.setTownCode(childDictZone.getCode());
		} else if (CommonConstant.DictZoneLevel.state.equals(childLevel)) {
			childDictZone.setProvince("");
			childDictZone.setProvinceCode(CommonConstant.DICT_ZONE_STATECENTER);
			childDictZone.setCity("");
			childDictZone.setCityCode(CommonConstant.DICT_ZONE_STATECENTER);
			childDictZone.setCounty("");
			childDictZone.setCountyCode(CommonConstant.DICT_ZONE_STATECENTER);
			childDictZone.setTown("");
			childDictZone.setTownCode(childDictZone.getCode());
		}
	}

	/**
	 * Created on 2017年03月29日 .
	 * Description 根据区划编号获取所有同级区划
	 * @param code
	 * @author liuqing
	 * @return List<DictZone>
	 */
	public static List<DictZone> getAllSameLevelDictZoneByCode(String code) {
		if (code == null) {
			return null;
		}
		DictZone d = getDictZoneByCode(code);
		if (d == null) {
			return null;
		}

		DictZoneFilter filter = new DictZoneFilter();
		filter.setParentId(d.getParentId());
		filter.setIsEnable(CommonConstant.DictStartStatus.start);

		return dictZoneDao.getDistrictList(filter);
	}

	/**
	 * Created on 2017年03月29日 .
	 * Description 根据区划编号获取所有同级区划（除了该区划）
	 * @param code
	 * @author liuqing
	 * @return List<DictZone>
	 */
	public static List<DictZone> getAllSameLevelDictZoneExceptByCode(String code) {
		if (code == null) {
			return null;
		}
		DictZone d = getDictZoneByCode(code);
		if (d == null) {
			return null;
		}

		DictZoneFilter filter = new DictZoneFilter();
		filter.setParentId(d.getParentId());
		filter.setExceptCode(d.getCode());
		filter.setIsEnable(CommonConstant.DictStartStatus.start);

		return dictZoneDao.getDistrictList(filter);

	}

	/**
	 * 根据区划编号查询父级区划
	 *
	 * @param code
	 *            区划编码
	 * @return DictZone
	 */
	public static DictZone getParentDictZoneByCode(String code) {
		// 编号为空时返回false
		if (org.springframework.util.StringUtils.isEmpty(code)) {
			return null;
		}

		DictZoneFilter filter = new DictZoneFilter();
		filter.setCode(code);
		List<DictZone> list = dictZoneDao.getDistrictList(filter);
		if (list.size() > 0) {
			return getDictZoneById(list.get(0).getParentId());
		}
		return null;
	}

	/**
	 * 根据区划编号查询父级区划
	 * @param filter 条件
	 * @return DictZone
	 */
	public static List<DictZone> getDictZoneList(DictZoneFilter filter) {
		return dictZoneDao.getDistrictList(filter);
	}

}
