/**
 * Copyright &copy; 2016 All rights reserved.
 */
package com.foundation.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.foundation.common.mapper.JsonMapper;
import com.foundation.common.utils.CacheUtils;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.modules.sys.dao.DictDao;
import com.foundation.modules.sys.entity.Dict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典工具类
 * @author zou
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";

	// sys_dict表 type 缓存变量 add by jiyingming at 2017-03-24
	public static final String CACHE_DICT_TYPE_LIST = "dictTypeList";
	
	public static String getDictLabelObject(Object value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && value != null && StringUtils.isNotBlank(String.valueOf(value))){
			for (Dict dict : getDictList(type)){
				if (dict.getType().equals(type) && dict.getValue()!=null &&dict.getValue().equals(value)){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		//CacheUtils.remove(CACHE_DICT_MAP);
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		List<Dict> tempList = new ArrayList<Dict>();
		Dict d = new Dict();
		d.setId("");
		d.setLabel("");
		d.setType(type);

		tempList.addAll(dictList);
		tempList.add(0,d);
		return tempList;
	}

	public static List<Dict> getDictListNoBlank(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}

		return dictList;
	}

	/**
	 * 返回字典所有type类型列表（JSON）
	 * @return
	 * add by jiyingming at 2017-03-24
	 */
	public static List<Dict> getDictAllType() {

		@SuppressWarnings("unchecked")
		List<Dict> dictTypeList = (List<Dict>) CacheUtils.get(CACHE_DICT_TYPE_LIST);

		if (dictTypeList == null) {
			dictTypeList = Lists.newArrayList();

			for (String dictType : dictDao.getDictAllType(new Dict())) {
				Dict d = new Dict();
				d.setLabel(dictType);
				d.setType(dictType);
				dictTypeList.add(d);
			}

			CacheUtils.put(CACHE_DICT_TYPE_LIST, dictTypeList);

		}
		return dictTypeList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
