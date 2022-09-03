package com.foundation.modules.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.service.CrudService;
import com.foundation.modules.sys.dao.DictZoneDao;
import com.foundation.modules.sys.dao.OrganizationDao;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.DictZoneFilter;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.utils.DictZoneUtils;
import com.foundation.modules.sys.utils.PageHelper;

/**
 * Created on 2017年3月14日
 * Description 区域管理服务
 * Copyright tuling (c) 2017 .
 * @author liuqing
 */
@Service
public class DictZoneService extends CrudService<DictZoneDao, DictZone> {

    @Autowired
    private DictZoneDao dictZoneDao;
    @Autowired
    private OrganizationDao umOrganizationDao;

    @Autowired
    private OrganizationService sysOfficeService;

    @Autowired
    private PageHelper<DictZone> pageHelper;

    /**
     * Created on 2017年03月16日 .
     * Description 保存区划信息
     * @param entity
     * @author liuqing
     * @return JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject saveDistrict(DictZone entity) {
        JSONObject result = new JSONObject();
        // 编号查重
        try {
            if (DictZoneUtils.isExistCode(entity.getId(), entity.getCode())) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "区划代码重复，请重新编写！");
                return result;
            }

            // 校验区划级别和区划代码
            if (!DictZoneUtils.checkLevelAndCode(entity.getLevel(), entity.getCode())) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "区划级别和区划代码对应有误！");
                return result;
            }

            DictZone parentDictZone = DictZoneUtils.getDictZoneById(entity.getParentId());

            String parentLevel = parentDictZone.getLevel();
            String level = entity.getLevel();

            // 父区划级别与子区划级别相差不为1，不符合规范
            if (Integer.parseInt(level) - Integer.parseInt(parentLevel) != 1) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "区划级别和父区划级别对应有误！");
                return result;
            }

            // 父区划代码与子区划代码不符合规范
            String parentCode = parentDictZone.getCode();
            String childCode = entity.getCode();
            String subParentCode = parentCode.substring(0, (Integer.parseInt(parentLevel) - 1) * 2);
            String subChildCode = childCode.substring(0, (Integer.parseInt(parentLevel) - 1) * 2);
            if (subParentCode != null && !subParentCode.equals(subChildCode)) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "区划代码不符合规则！");
                return result;
            }

            String id = entity.getId();
            if (DictZoneUtils.isExistName(entity.getId(), entity.getName(), entity.getParentId())) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "区划名称重复，请重新编写！");
                return result;
            }

            if (StringUtils.isEmpty(id)) {
                // 设置父区划ids和省市区乡code
                DictZoneUtils.setParentIdsAndAllCode(parentDictZone, entity);

                entity.setIsEnable(CommonConstant.DictStartStatus.notstart);
                super.save(entity);
            } else {

                // 设置父区划ids和省市区乡code
                DictZoneUtils.setParentIdsAndAllCode(parentDictZone, entity);

                super.save(entity);
                dictZoneDao.updateProvince(entity);
                dictZoneDao.updateCity(entity);
                dictZoneDao.updateCounty(entity);
                Organization umOrganization = new Organization();
                umOrganization.setProvince(entity.getProvince());
                umOrganization.setCity(entity.getCity());
                umOrganization.setCounty(entity.getCounty());
                umOrganization.setProvinceCode(entity.getCode());
                umOrganization.setCityCode(entity.getCode());
                umOrganization.setCountyCode(entity.getCode());
                umOrganization.setCode(entity.getCode());
                umOrganizationDao.updateProvince(umOrganization);
                umOrganizationDao.updateCity(umOrganization);
                umOrganizationDao.updateCounty(umOrganization);
                umOrganizationDao.updateName(umOrganization);
                
            }

            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
            result.put(CommonConstant.ERROR_MSG, "");
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 根据条件查询区划信息
     * @param filter
     * @author liuqing
     * @return java.util.List<com.foundation.modules.dict.entity.DictZone>
     */
    public List<DictZone> getDistrictList(DictZoneFilter filter) {

        String name = filter.getName();
        if (!StringUtils.isEmpty(name)) {
            filter.setName("%" + name + "%");
        }
        String codeFuzzy = filter.getCodeFuzzy();
        if (!StringUtils.isEmpty(codeFuzzy)) {
            filter.setCodeFuzzy("%" + codeFuzzy + "%");
        }

        List<DictZone> list = dictZoneDao.getDistrictList(filter);

        for (DictZone entity : list) {
            String level = entity.getLevel();
            if (CommonConstant.DictZoneLevel.provice.equals(level)) {
                entity.setParentFullName(CommonConstant.DICT_ZONE_STATECENTERNAME);
            }
        }

        return list;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 根据条件查询区划信息 翻页
     * @param filter
     * @author liuqing
     * @return PageHelper<DictZone>
     */
    public PageHelper<DictZone> getDistrictList(Page page, DictZoneFilter filter) {
        filter.setPage(page);
        List<DictZone> list = getDistrictList(filter);
        pageHelper.setRows(page, list);
        return pageHelper;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 停用区划信息
     * @param id
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject stopUseDistrict(String id) {
        JSONObject result = new JSONObject();
        try {
            DictZone entity = new DictZone();
            entity.setId(id);
            entity = dictZoneDao.get(entity);

            if (entity == null) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "不存在id为" + id + "的区划！");
                return result;
            }

            if (DictZoneUtils.getStartUseDictZoneCountByParent(entity) > 0) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "存在已启用的下级区划，不能停用！");
                return result;
            }

            entity.setIsEnable(CommonConstant.DictStartStatus.stop);
            super.save(entity);

            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
            result.put(CommonConstant.ERROR_MSG, "");
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 停用用区划信息
     * @param idList
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject stopUseDistrict(List<String> idList) {
        JSONObject result = new JSONObject();

        if (idList == null) {
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "停用区划为空！");
            return result;
        }
        try {
            for (String id : idList) {
                result = stopUseDistrict(id);
                if (CommonConstant.RESULT_FAILURE.equals(result.getString(CommonConstant.SUCCESS))) {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 启用区划信息
     * @param id
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject startUseDistrict(String id) {
        JSONObject result = new JSONObject();
        try {
            DictZone entity = new DictZone();
            entity.setId(id);
            entity = dictZoneDao.get(entity);

            if (entity == null) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "不存在id为" + id + "的区划！");
                return result;
            }

            DictZone parentDictZone = DictZoneUtils.getDictZoneById(entity.getParentId());
            if (parentDictZone != null && !CommonConstant.DictStartStatus.start.equals(parentDictZone.getIsEnable())) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "上级区划非启用状态，不可启用！");
                return result;
            }

            if (CommonConstant.DictStartStatus.start.equals(entity.getIsEnable())) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "请选择未启用的区划！");
                return result;
            }

            entity.setIsEnable(CommonConstant.DictStartStatus.start);
            entity.setEnableDate(new Date());
            super.save(entity);

            String level = entity.getLevel();
            if (!CommonConstant.DictZoneLevel.town.equals(level)
                    && !CommonConstant.DictZoneLevel.state.equals(level)) {
                sysOfficeService.saveManagerOrg(entity);
            }

            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
            result.put(CommonConstant.ERROR_MSG, "");
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 启用区划信息
     * @param idList
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject startUseDistrict(List<String> idList) {
        JSONObject result = new JSONObject();
        try {
            if (idList == null) {
                result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
                result.put(CommonConstant.ERROR_MSG, "启用区划为空！");
                return result;
            }

            for (String id : idList) {
                result = startUseDistrict(id);
                if (CommonConstant.RESULT_FAILURE.equals(result.getString(CommonConstant.SUCCESS))) {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 删除区划
     * @param idList
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject deleteDistrict(List<String> idList) {
        JSONObject result = new JSONObject();

        if (idList == null) {
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "区划信息为空");
            return result;
        }
        try {
            for (String id : idList) {
                result = deleteDistrict(id);
                if (CommonConstant.RESULT_FAILURE.equals(result.getString(CommonConstant.SUCCESS))) {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

    /**
     * Created on 2017年03月16日 .
     * Description 删除区划
     * @param id
     * @author liuqing
     * @return com.alibaba.fastjson.JSONObject
     */
    @Transactional(readOnly = false)
    public JSONObject deleteDistrict(String id) {
        JSONObject result = new JSONObject();
        try {
            DictZone entity = new DictZone();
            entity.setId(id);

            dictZoneDao.deleteLogical(entity);

            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
            result.put(CommonConstant.ERROR_MSG, "");
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_FAILURE);
            result.put(CommonConstant.ERROR_MSG, "发生异常！");
            return result;
        }
        return result;
    }

}