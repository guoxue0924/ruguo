package com.foundation.modules.sys.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.foundation.common.utils.SpringContextHolder;
import com.foundation.modules.sys.dao.DictCommonUtilsDao;
import com.foundation.modules.sys.entity.SqlEntity;

/**
 * Created on 2017年03月28日
 * Description 字典公共功能工具类
 * Copyright tuling (c) 2017 .
 *
 * @author liuqing
 */
public class DictCommonUtils {

    private static DictCommonUtilsDao dictCommonUtilsDao = SpringContextHolder.getBean(DictCommonUtilsDao.class);

    private final static String SELECT = "SELECT ";
    private final static String UPDATE = "UPDATE ";
    private final static String SET = " SET ";
    private final static String FROM  = " FROM ";
    private final static String WHERE = " WHERE ";
    private final static String EQUALS = " = ";
    private final static String QUOTATION_MARK = "'";
//    private final static String AND = " AND ";
    private final static String DELIMITER = "_";

    /**
     * Created on 2017年03月29日 .
     * Description entity特定字段赋值
     * @param entity entity对象
     * @param fieldName 字段名称
     * @param fieldValue 字段值
     * @param fieldType 参数类型
     * @author liuqing
     * @return void
     */
    public static void setEntityFieldValue(Object entity, String fieldName, Object fieldValue, Class fieldType) {

        if (entity == null) {
            return;
        }
        Class<?> c = entity.getClass();
        try {
            StringBuffer method = new StringBuffer(32);
            method.append("set").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
            Method m = c.getMethod(method.toString(), fieldType);
            m.invoke(entity, fieldValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Created on 2017年03月29日 .
     * Description entity特定字段取值
     * @param obj entity对象
     * @param fieldName 字段名称
     * @author liuqing
     * @return void
     */
    public static Object getEntityFieldValue(Object obj, String fieldName) {

        if (obj == null) {
            return null;
        }
        Class<?> c = obj.getClass();
        try {
            StringBuffer method = new StringBuffer(32);
            method.append("get").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
            Method m = c.getMethod(method.toString());
            return m.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Created on 2017年03月29日 .
     * Description 执行动态sql
     * @param sql
     * @author liuqing
     * @return Map
     */
    private static List<Map<String, Object>> selectSql(String sql, String shardDBName) {
        SqlEntity entity = new SqlEntity();
        if (!StringUtils.isEmpty(shardDBName)) {
            entity.setShardDBName(shardDBName);
        }
        entity.setSqlString(sql);
        return dictCommonUtilsDao.selectSql(entity);
    }

    /**
     * Created on 2017年03月29日 .
     * Description 执行动态sql更新
     * @param sql
     * @author liuqing
     * @return Map
     */
    private static int updateSql(String sql, String shardDBName) {
        SqlEntity entity = new SqlEntity();
        if (!StringUtils.isEmpty(shardDBName)) {
            entity.setShardDBName(shardDBName);
        }
        entity.setSqlString(sql);
        return dictCommonUtilsDao.updateSql(entity);
    }

    /**
     * Created on 2017年03月29日 .
     * Description 查询结果
     * @param filterField
     * @param filterValue
     * @param resultField
     * @param tableName
     * @author liuqing
     * @return void
     */
    public static List<Map<String, Object>> getResultMap(
            String filterField,
            Object filterValue,
            String resultField,
            String tableName,
            String shardDBName) {

        if (StringUtils.isEmpty(filterValue)
                || StringUtils.isEmpty(filterField)
                || StringUtils.isEmpty(resultField)
                || StringUtils.isEmpty(tableName)) {
            return null;
        }

        StringBuffer strBuffer = new StringBuffer(256);

        strBuffer.append(SELECT);
        strBuffer.append(resultField);
        strBuffer.append(FROM);
        strBuffer.append(tableName);
        strBuffer.append(WHERE);
        strBuffer.append(filterField);
        strBuffer.append(EQUALS);
        strBuffer.append(getSqlFilterValue(filterValue));

        List<Map<String, Object>> list = selectSql(strBuffer.toString(), shardDBName);

        return list;
    }

    /**
     * Created on 2017年03月29日 .
     * Description 根据某个特定字段更新某个表的另一个字段
     * @param filterField
     * @param filterValue
     * @param updateField
     * @param updateValue
     * @param tableName
     * @author liuqing
     * @return void
     */
    public static int updateTable(
            String filterField,
            Object filterValue,
            String updateField,
            String updateValue,
            String tableName,
            String shardDBName) {

        if (StringUtils.isEmpty(filterValue)
                || StringUtils.isEmpty(filterField)
                || StringUtils.isEmpty(updateField)
                || StringUtils.isEmpty(updateValue)
                || StringUtils.isEmpty(tableName)) {
            return 0;
        }

        StringBuffer strBuffer = new StringBuffer(256);

        strBuffer.append(UPDATE);
        strBuffer.append(tableName);
        strBuffer.append(SET);
        strBuffer.append(updateField);
        strBuffer.append(EQUALS);
        strBuffer.append(getSqlFilterValue(updateValue));
        strBuffer.append(WHERE);
        strBuffer.append(filterField);
        strBuffer.append(EQUALS);
        strBuffer.append(getSqlFilterValue(filterValue));

        return updateSql(strBuffer.toString(), shardDBName);

    }

    /**
     * Created on 2017年03月29日 .
     * Description 根据查询条件值类型返回相应拼接字符串
     * @param value
     * @author liuqing
     * @return String
     */
    private static String getSqlFilterValue(Object value) {

        String result = "";

        if (value == null) {
            return result;
        }
        String type = value.getClass().getName();

        switch (type) {
            case "java.lang.String":
                result = (new StringBuffer()).append(QUOTATION_MARK).append(value).append(QUOTATION_MARK).toString();
                break;
            case "java.lang.Character":
                result = (new StringBuffer()).append(QUOTATION_MARK).append(value).append(QUOTATION_MARK).toString();
                break;
            case "java.lang.Integer":
                result = String.valueOf(value);
                break;
            case "java.lang.Long":
                result = String.valueOf(value);
                break;
            case "java.lang.Double":
                result = String.valueOf(value);
                break;
            case "java.lang.Short":
                result = String.valueOf(value);
                break;
            case "java.lang.Float":
                result = String.valueOf(value);
                break;
//            case "java.util.Date":
//                result = String.valueOf(value);
//                break;
            default:
                result = String.valueOf(value);
        }
        return result;
    }

    /**
     * Created on 2017年03月29日 .
     * Description entity特定机构名称字段赋值
     * @param entity entity对象
     * @param filterName 字段名称
     * @param resultName 查询结果字段名称
     * @author liuqing
     * @return void
     */
    public static void setOrgNameById(Object entity, String filterName, String resultName) {
        if (entity == null || StringUtils.isEmpty(filterName) || StringUtils.isEmpty(resultName)) {
            return;
        }

        Object filterNameValue = getEntityFieldValue(entity, filterName);

        List<Map<String, Object>> list = getResultMap("id", filterNameValue, "name", "sys_organization", "");
        String value = (String)filterNameValue;
        if (list != null && list.size() > 0) {
            value = (String)list.get(0).get("name");
        }
        setEntityFieldValue(entity, resultName, value, String.class);

    }

    /**
     * Created on 2017年03月29日 .
     * Description entity特定角色名称字段赋值
     * @param entity entity对象
     * @param filterName 字段名称
     * @param resultName 查询结果字段名称
     * @author liuqing
     * @return void
     */
    public static void setRoleNameById(Object entity, String filterName, String resultName) {
        if (entity == null || StringUtils.isEmpty(filterName) || StringUtils.isEmpty(resultName)) {
            return;
        }

        Object filterNameValue = getEntityFieldValue(entity, filterName);

        List<Map<String, Object>> list = getResultMap("id", filterNameValue, "name", "sys_role", "");
        String value = "";
        if (list != null && list.size() > 0) {
            value = (String)list.get(0).get("name");
        }
        setEntityFieldValue(entity, resultName, value, String.class);

    }

    /**
     * Created on 2017年03月29日 .
     * Description entity特定用户名称字段赋值
     * @param entity entity对象
     * @param filterName 字段名称
     * @param resultName 查询结果字段名称
     * @author liuqing
     * @return void
     */
    public static void setUserNameById(Object entity, String filterName, String resultName) {
        if (entity == null || StringUtils.isEmpty(filterName) || StringUtils.isEmpty(resultName)) {
            return;
        }

        Object filterNameValue = getEntityFieldValue(entity, filterName);

        List<Map<String, Object>> list = getResultMap("id", filterNameValue, "name", "sys_user", "");

        String value = null;
        if (list != null && list.size() > 0) {
            value = (String)list.get(0).get("name");
        }
        setEntityFieldValue(entity, resultName, value, String.class);

    }

    /**
     * Created on 2017年03月29日 .
     * Description entity特定区划名称字段赋值
     * @param entity entity对象
     * @param filterName 字段名称
     * @param resultName 查询结果字段名称
     * @author liuqing
     * @return void
     */
    public static void setDictZoneNameByCode(Object entity, String filterName, String resultName) {
        if (entity == null || StringUtils.isEmpty(filterName) || StringUtils.isEmpty(resultName)) {
            return;
        }

        Object filterNameValue = getEntityFieldValue(entity, filterName);

        List<Map<String, Object>> list = getResultMap("code", filterNameValue, "name", "sys_dict_zone", "");

        String value = null;
        if (list != null && list.size() > 0) {
            value = (String)list.get(0).get("name");
        }
        setEntityFieldValue(entity, resultName, value, String.class);

    }

    /**
     * Created on 2017年03月30日 .
     * Description 驼峰命名改成下划线
     * @param param
     * @author liuqing
     * @return String
     */
    public static String formatCamelToUnderline(String param){
        StringBuffer sbf = new StringBuffer(32);
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char tempChr = param.charAt(i);
            if (tempChr >= 'A' && tempChr <= 'Z') {
                sbf.append(DELIMITER); // 如果是大写字母，则在字符前面插入一个下划线
                sbf.append(String.valueOf(tempChr).toLowerCase());
            } else {
                sbf.append(tempChr);
            }
        }
        return sbf.toString();
    }

    /**
     * Created on 2017年03月30日 .
     * Description 下划线改成驼峰命名
     * @param param
     * @author liuqing
     * @return String
     */
    public static String formatUnderlineToCamel(String param){
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()) {
            int position=mc.end() - (i++);
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }

    public static Date getCurrentDate() {
        SqlEntity entity = dictCommonUtilsDao.getCurrentDate();
        return entity.getCurrentDate();
    }

}