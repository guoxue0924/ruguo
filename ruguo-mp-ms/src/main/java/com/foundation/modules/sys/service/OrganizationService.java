package com.foundation.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.persistence.Page;
import com.foundation.common.security.Digests;
import com.foundation.common.service.CrudService;
import com.foundation.common.utils.Encodes;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.SequenceUtils;
import com.foundation.common.utils.StringUtils;
import com.foundation.modules.sys.dao.OrganizationDao;
import com.foundation.modules.sys.dao.UserDao;
import com.foundation.modules.sys.dao.UserInfoDao;
import com.foundation.modules.sys.entity.DictZone;
import com.foundation.modules.sys.entity.Organization;
import com.foundation.modules.sys.entity.User;
import com.foundation.modules.sys.utils.PageHelper;

/**
 * 机构管理服务
 * 
 * @author yanqizh
 * @version 2017-03-15
 */
@Service
@Transactional(readOnly = true)
public class OrganizationService extends CrudService<OrganizationDao, Organization> {
	@Autowired
	private OrganizationDao organizationDao;
	// 账号管理DAO
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private PageHelper<Organization> pageHelper;

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {2.4.1 查询服务机构列表}
	 * @author yanqizh
	 * @param Organization
	 *            （服务机构实体）
	 * @return List<Organization>
	 */
	public PageHelper<Organization> getOfficeList(Page page, Organization organization) {

		// 分页查询，需要在filter实体中set分页信息
		organization.setPage(page);
		List<Organization> lists = new ArrayList<Organization>();
		lists = organizationDao.getOfficeList(organization);
		// for (Organization bean : lists) {
		// DictCommonUtils.setDictZoneNameByCode(bean, "ownZoneCode",
		// "ownZoneName");
		// DictCommonUtils.setDictZoneNameByCode(bean, "zoneCode", "zoneName");
		// }
		pageHelper.setRows(page, lists);
		return pageHelper;
	}
	
	
	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {查询内部机构列表}
	 * @author guoxue
	 * @param Organization
	 *            （服务机构实体）
	 * @return List<Organization>
	 */
	public PageHelper<Organization> getOfficeListInside(Page page, Organization organization) {

		// 分页查询，需要在filter实体中set分页信息
		organization.setPage(page);
		List<Organization> list = new ArrayList<Organization>();
		List<Organization> lists = new ArrayList<Organization>();
		lists = organizationDao.getOfficeListInside(organization);
		for(Organization org:lists){
			org.setProvince(org.getProvince() +"   "+org.getCity()+"   "+org.getCounty());
			list.add(org);
		}
		pageHelper.setRows(page, list);
		return pageHelper;
	}

	public List<Organization> findByAddress(Organization organization) {
		List<Organization> lists = new ArrayList<Organization>();
		lists = organizationDao.getOfficeList(organization);
		return lists;
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {添加下级服务机构}
	 * @author yanqizh
	 * @param Organization
	 *            （服务机构实体）
	 * @return {success：0} 0代表成功 1代表失败
	 */
	@Transactional(readOnly = false)
	public JSONObject addLowerLevelOffice(Organization organization) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(organization.getId())) {
			organization.setId(IdGen.uuid());
			organizationDao.insertOrganization(organization);
		} else {
			organizationDao.updateOrganization(organization);
		}
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {保存服务机构}
	 * @author yanqizh
	 * @param Organization
	 *            （服务机构实体）
	 * @return {success：0} 0代表成功 1代表失败
	 */
	@Transactional(readOnly = false)
	public JSONObject saveOffice(Organization organization) {
		JSONObject result = new JSONObject();
//		Organization bean = new Organization();
		if (StringUtils.isEmpty(organization.getId())) {

			//Organization filter = new Organization();
			//bean = organizationDao.findByOwnZoneCode(filter);

			organization.setId(IdGen.uuid());

			// String str = new SimpleDateFormat("yy").format(new Date());
			// String code = "";
			// if (null != organization.getOwnZoneCode() &&
			// organization.getOwnZoneCode().length() > 2) {
			// code = organization.getOwnZoneCode().substring(0, 2);
			// }
			// 添加流水号
			// organization.setCode(code + Getnum(code));
			// if (null != bean) {
			// organization.setParentId(bean.getId());
			// organization.setParentIds(bean.getParentIds() +
			// organization.getId() + ",");
			// }
			organizationDao.insertOrganization(organization);
			// 创建账号
			// result = automaticSysUser(organization);
			// if(result.get("result")!=null&&result.get("result").equals("1")){
			// return result;
			// }
		} else {
			organizationDao.updateOrganization(organization);
		}
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {自动生成服务机构账号}
	 * @author yanqizh
	 * @return {success：0} 0代表成功 1代表失败
	 */
	@Transactional(readOnly = false)
	public JSONObject automaticSysUser(Organization beanOrg) {
		Organization organization = organizationDao.findById(beanOrg);
		JSONObject result = new JSONObject();
		User bean = new User();
		User rolebean = new User();
		String id = IdGen.uuid();
		bean.setId(id);
		// bean.setSysUserId(id);
		bean.setLoginName("A" + organization.getCode());
		bean.setName(organization.getName());
		bean.setOrgId(organization.getId());
		bean.setLoginName(organization.getName());
		bean.setLoginFlag(CommonConstant.DictStartStatus.start);
		bean.setPassword(entryptPassword("111111"));
		bean.setDelFlag(CommonConstant.DelFlag.normal);
//		bean.setUserType(CommonConstant.DictOrgStyle.service);

		bean.setLoginName("A" + organization.getCode());
		bean.setName(organization.getName());
		bean.setIsResetPwd("1");
		bean.setAddress(organization.getAddress());
		bean.preInsert();
		userDao.insert(bean);
		userInfoDao.insert(bean);

		rolebean.setUserId(bean.getId());
		// if (null != level && level.equals("2")) {
		// rolebean.setRoleId("9");
		// } else if (null != level && level.equals("3")) {
		// rolebean.setRoleId("10");
		// } else if (null != level && level.equals("4")) {
		// rolebean.setRoleId("11");
		// } else if (null != level && level.equals("5")) {
		// rolebean.setRoleId("12");
		// }
		userInfoDao.insertSysUserRole(rolebean);
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {根据服务机构ID，删除服务机构信息}
	 * @author yanqizh
	 * @param lists
	 *            （服务机构实体集合）
	 * @return {success：0} 0代表成功 1代表失败
	 */
	@Transactional(readOnly = false)
	public JSONObject deleteOffice(String[] lists) {
		JSONObject result = new JSONObject();
		try {
			if (null != lists && lists.length > 0) {
				Organization entity = new Organization();
				for (String id : lists) {
					entity.setId(id);
					entity.preUpdate();
					organizationDao.delete(entity);
				}

			}
			result.put("result", CommonConstant.RESULT_SUCCESS);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", CommonConstant.RESULT_FAILURE);
			return result;
		}
	}

	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public JSONObject delete(String id) {
		JSONObject result = new JSONObject();
		try {
			organizationDao.delete(id);
			result.put("result", CommonConstant.RESULT_SUCCESS);
			return result;
		} catch (Exception e) {
			result.put("result", CommonConstant.RESULT_FAILURE);
			return result;
		}
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {是否启用服务机构}
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	/*
	 * public JSONObject whetherIsEnable(Organization organization) { JSONObject
	 * result = new JSONObject(); try {
	 * organizationDao.whetherIsEnable(organization); result.put("result",
	 * CommonConstant.RESULT_SUCCESS); return result; } catch (Exception e) {
	 * e.printStackTrace(); result.put("result", CommonConstant.RESULT_FAILURE);
	 * return result; } }
	 */

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {通过机构ID查询机构实体}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	public Organization findById(String id) {
		Organization filter = new Organization();
		filter.setId(id);
		Organization organization = organizationDao.findById(filter);
		if (organization != null) {
			return organization;
		} else {
			return null;
		}
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {是否启用服务机构}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	/*
	 * public JSONObject whetherIsEnables(List<Organization> lists) { JSONObject
	 * result = new JSONObject(); for (Organization organization : lists) { //
	 * Organization filter = new Organization(); //
	 * filter.setId(organization.getId()); // List<Organization> benas =
	 * organizationDao.findByParentId(filter); // if (null != benas &&
	 * benas.size() > 0) { // result.put("result",
	 * CommonConstant.RESULT_FAILURE); // result.put("content",
	 * "机构下属有未停用/启用的机构"); // return result; // } // User user = new User(); //
	 * user.setOrgId(organization.getId()); //
	 * user.setLoginFlag(organization.getIsEnable()); // // 停用/启用机构下个账号 //
	 * userInfoDao.updateOrgId(user); // 停用/启用机构
	 * organizationDao.whetherIsEnable(organization); } result.put("result",
	 * CommonConstant.RESULT_SUCCESS); return result; }
	 */

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {区划添加，自动生成管理机构添加}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （机构实体）
	 * @return
	 */
	@Transactional(readOnly = false)
	public JSONObject saveManagerOrg(DictZone entity) {
		JSONObject result = new JSONObject();
		Organization organization = new Organization();
		Organization bean = findByOwnZoneCode(entity);
		// String id = IdGen.uuid();
		organization.preInsert();
		// organization.setId(id);
		if (null != bean) {
			organization.setParentId(bean.getId());
			// if(bean.getId().equals("000000000")){
			// organization.setParentIds("000000000,"+organization.getId()+",");
			// }else{
			// }
			organization.setParentIds(bean.getParentIds() + organization.getId() + ",");
		}
		organization.setName(getDIctOrgName(entity));
		organization.setOrgType(CommonConstant.DictOrgStyle.manager);
		organization.setProvince(entity.getProvince());
		organization.setProvinceCode(entity.getProvinceCode());
		organization.setCity(entity.getCity());
		organization.setCityCode(entity.getCityCode());
		organization.setCounty(entity.getCounty());
		organization.setCountyCode(entity.getCountyCode());
		organization.setTown(entity.getTown());
		organization.setTownCode(entity.getTownCode());
//		organization.setIsEnable(entity.getIsEnable());
//		organization.setEnableDate(entity.getEnableDate());
		organization.setDelFlag(entity.getDelFlag());
		organization.setRemark(entity.getRemark());
		organization.setCode(entity.getCode());

		try {
			organizationDao.insertOrganization(organization);
			// 添加区划自动创建账号
			result = saveManagerSysUser(organization);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", CommonConstant.RESULT_FAILURE);
			return result;
		}
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {自动生成服务机构账号}
	 * 
	 * @author yanqizh
	 * @return {success：0} 0代表成功 1代表失败
	 */
	@Transactional(readOnly = false)
	public JSONObject saveManagerSysUser(Organization organization) {
		JSONObject result = new JSONObject();
		// String level = organization.getLevel();
		// if (null != level && level.equals("1")) {
		// // 国家级 A S L
		// List<String> lists = new ArrayList<String>();
		// lists.add("A");// 角色ID 1111（国家级管理机构管理员（国家级管理员））
		// lists.add("S");// 角色ID 3 （国家级信息员）
		// lists.add("L");// 角色ID 7（国家级临检中心）
		// result = saveUser(organization, lists);
		// } else if (null != level && level.equals("2")) {
		// // 省级别 A S L
		// List<String> lists = new ArrayList<String>();
		// lists.add("A");// 角色ID 9（省级服务机构管理员）
		// lists.add("S");// 角色ID 4（省级信息员）
		// lists.add("L");// 角色ID 8（省级临检中心）
		// result = saveUser(organization, lists);
		// } else if (null != level && level.equals("3")) {
		// // 市级 A S
		// List<String> lists = new ArrayList<String>();
		// // lists.add("A");//角色ID 10（市级服务机构管理员）
		// lists.add("S");// 角色ID 5（市级信息员）
		// result = saveUser(organization, lists);
		// } else if (null != level && level.equals("4")) {
		// // 县级 A S
		// List<String> lists = new ArrayList<String>();
		// // lists.add("A");//角色ID 11（县级服务机构管理员）
		// lists.add("S");// 角色ID 6（县级信息员）
		// result = saveUser(organization, lists);
		// }
		return result;
	}

	@Transactional(readOnly = false)
	public JSONObject saveUser(Organization organization, List<String> strs) {
		JSONObject result = new JSONObject();
		// String level = organization.getLevel();
		for (String str : strs) {
			String name = "";
			User bean = new User();
			User rolebean = new User();
			// 管理员账号：A开头+9位代码
			// 信息员账号：S开头+9位代码
			// 临检中心账号：L开头+9位代码
			// if (null != level && level.equals("1")) {
			// if (str.equals("A")) {
			// name = "管理员账号";
			// rolebean.setRoleId("1111");
			// } else if (str.equals("S")) {
			// name = "信息员账号";
			// rolebean.setRoleId("3");
			// } else if (str.equals("L")) {
			// name = "临检中心账号";
			// rolebean.setRoleId("7");
			// }
			// } else if (null != level && level.equals("2")) {
			// if (str.equals("A")) {
			// name = "管理员账号";
			// rolebean.setRoleId("2");
			// } else if (str.equals("S")) {
			// name = "信息员账号";
			// rolebean.setRoleId("4");
			// } else if (str.equals("L")) {
			// name = "临检中心账号";
			// rolebean.setRoleId("8");
			// }
			// } else if (null != level && level.equals("3")) {
			// if (str.equals("A")) {
			// name = "管理员账号";
			// rolebean.setRoleId("10");
			// } else if (str.equals("S")) {
			// name = "信息员账号";
			// rolebean.setRoleId("5");
			// }
			// } else if (null != level && level.equals("4")) {
			// if (str.equals("A")) {
			// name = "管理员账号";
			// rolebean.setRoleId("11");
			// } else if (str.equals("S")) {
			// name = "信息员账号";
			// rolebean.setRoleId("6");
			// }
			// }
			bean.setLoginName(str + organization.getCode());
			bean.setName(name);
			bean.setOrgId(organization.getId());
			bean.setLoginName(organization.getName());
			bean.setLoginFlag(CommonConstant.DictStartStatus.start);
			bean.setPassword(entryptPassword("111111"));
			bean.setDelFlag(CommonConstant.DelFlag.normal);
//			bean.setUserType(CommonConstant.DictOrgStyle.manager);

			bean.setLoginName(str + organization.getCode());
			bean.setName(name);
			bean.setIsResetPwd("1");
			bean.setAddress(organization.getAddress());
			bean.preInsert();
			// userInfoDao.insertSysUser(bean);
			// userInfoDao.insertUmUser(bean);
			userDao.insert(bean);
			userInfoDao.insert(bean);

			rolebean.setUserId(bean.getId());
			userInfoDao.insertSysUserRole(rolebean);
		}
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;
	}

	public String getDIctOrgName(DictZone entity) {
		String name = "";
		name = entity.getProvince();
		if (null != entity.getCity() && entity.getCity().length() > 0) {
			name += entity.getCity();
		}
		if (null != entity.getCounty() && entity.getCounty().length() > 0) {
			name += entity.getCounty();
		}
		return name;
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {获取父级ID}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	public Organization findByOwnZoneCode(DictZone entity) {
		Organization bean = new Organization();
		String code = "";
		if (null != entity.getProvinceCode() && (entity.getCity() == null || entity.getCity().length() <= 0)) {
			code = CommonConstant.DICT_ZONE_STATECENTER;
		} else if (null != entity.getCity() && (null == entity.getCounty() || entity.getCounty().length() <= 0)) {
			code = entity.getProvinceCode();
		} else if (null != entity.getCounty() && (null == entity.getTown() || entity.getTown().length() <= 0)) {
			code = entity.getCityCode();
		} else if (null != entity.getTown() && entity.getTown().length() >= 0) {
			code = entity.getCountyCode();
		}
		Organization filter = new Organization();
		// filter.setOwnZoneCode(code);
		bean = organizationDao.findByOwnZoneCode(filter);
		return bean;
	}

	/**
	 *          * 生成流水号         * @return         
	 */
	public String Getnum(String seqName) {
		String seq = SequenceUtils.getNextSequence(seqName);
		int seqInt = Integer.parseInt(seq);
		seq = String.format("%06d", seqInt);
		SequenceUtils.lockNextSequence(seqName);
		return seq;
	}

	/**
	 * Created on 2017年3月22日
	 * 
	 * @Description {通过机构ID查询机构下级实体}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	public List<Organization> findByParentId(String id) {
		Organization filter = new Organization();
		filter.setParentId(id);
		List<Organization> lists = organizationDao.findByParentId(filter);
		return lists;
	}

	/**
	 * Created on 2017年3月22日
	 * 
	 * @Description {通过机构ID查询机构下级实体}
	 * 
	 * @author yanqizh
	 * @param Organization
	 *            （机构实体）
	 * @return
	 */
	public List<Organization> findByParentId(Organization bean) {
		List<Organization> lists = organizationDao.findByParentIdBean(bean);
		return lists;
	}

	/**
	 * Created on 2017年3月2日
	 * 
	 * @Description {通过机构CODE查询机构下级实体}
	 * 
	 * @author yanqizh
	 * @param id
	 *            （服务机构ID）
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	public List<Organization> findByParentCode(String code) {
		Organization codefilter = new Organization();
		codefilter.setCode(code);
		Organization bean = organizationDao.findByCode(codefilter);
		Organization filter = new Organization();
		filter.setParentId(bean.getId());
		List<Organization> lists = organizationDao.findByParentId(filter);
		return lists;
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	/**
	 * Created on 2017年3月2日
	 * 
	 * @Description {通过机构CODE查询机构实体}
	 * 
	 * @author yanqizh
	 * @param isEnable
	 *            （是否启用0是1否）
	 * @return
	 */
	public JSONObject findByCode(String code, String umOrganizationid) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(umOrganizationid)) {
			Organization filter = new Organization();
			// filter.setOwnZoneCode(code);
			List<Organization> bean = organizationDao.findByCodeList(filter);
			if (bean.size() == 0) {
				result.put("result", CommonConstant.RESULT_SUCCESS);
				return result;
			} else {
				result.put("result", CommonConstant.RESULT_FAILURE);
				result.put("content", "机构重复！");
				return result;
			}
		} else {
			Organization filter = new Organization();
			filter.setId(umOrganizationid);
			//Organization organization = organizationDao.findById(filter);

			Organization codeFilter = new Organization();
			List<Organization> bean = organizationDao.findByCodeList(codeFilter);
			if (bean.size() == 0) {
				result.put("result", CommonConstant.RESULT_SUCCESS);
				return result;
			} else {
				result.put("result", CommonConstant.RESULT_FAILURE);
				result.put("content", "机构重复！");
				return result;
			}

		}
	}

	/**
	 * Created on 2017年3月2日
	 * 
	 * @Description {验证机构是否重复}
	 * 
	 * @author yanqizh
	 * @return
	 */
	public JSONObject findByName(String name, String code, String orgId) {
		
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(orgId)) {
			if (StringUtils.isNotEmpty(code)) {
				Organization nameFilter = new Organization();
				nameFilter.setCode(code);

				// nameFilter.setZoneCode(zoneCode);
				List<Organization> bean = organizationDao.findByCodeName(nameFilter);
				if (bean.size() > 0) {
					result.put("result", CommonConstant.RESULT_FAILURE);
					result.put("content", "机构编码重复！");
					return result;
				}
			}
			if (StringUtils.isNotEmpty(name)) {
				Organization nameFilter = new Organization();
				nameFilter.setName(name);
				// nameFilter.setZoneCode(zoneCode);
				List<Organization> bean = organizationDao.findByCodeName(nameFilter);
				if (bean.size() > 0) {
					result.put("result", CommonConstant.RESULT_FAILURE);
					result.put("content", "机构名称重复！");
					return result;
				}
			}
		} else {
			Organization filter = new Organization();
			filter.setId(orgId);
			Organization organization = organizationDao.findById(filter);
			if (null != organization.getCode() && !organization.getCode().equals(code)) {
				Organization codeFilter = new Organization();
				codeFilter.setCode(code);
				// nameFilter.setZoneCode(zoneCode);
				List<Organization> bean = organizationDao.findByCodeName(codeFilter);
				if (bean.size() > 0)  {
					result.put("result", CommonConstant.RESULT_FAILURE);
					result.put("content", "机构编码重复！");
					return result;
				}
			}
			if (null != organization.getName() && !organization.getName().equals(name)) {
				Organization nameFilter = new Organization();
				nameFilter.setName(name);
				// nameFilter.setZoneCode(zoneCode);
				List<Organization> bean = organizationDao.findByCodeName(nameFilter);
				if (bean.size() > 0)  {
					result.put("result", CommonConstant.RESULT_FAILURE);
					result.put("content", "机构名称重复！");
					return result;
				}
			}
		}
		result.put("result", CommonConstant.RESULT_SUCCESS);
		return result;

	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {组织机构树控件查询}
	 * 
	 * @author liuqing
	 * @param organization
	 *            （服务机构实体）
	 * @return List<Organization>
	 */
	public List<Organization> getOfficeList(Organization organization) {
		return organizationDao.getOfficeList(organization);
	}

	/**
	 * Created on 2017年3月15日
	 * 
	 * @Description {查询所有机构}
	 * 
	 * @author liuqing
	 */
	public void findByList() {
		List<Organization> list = organizationDao.findByList();
		for (Organization bean : list) {
			// 等于1，是管理机构
			if (null != bean && null != bean.getOrgType() && bean.getOrgType().equals("1")) {
				// 添加管理机构账号
				saveManagerSysUser(bean);
				// 等于2，服务机构
			} else if (null != bean && null != bean.getOrgType() && bean.getOrgType().equals("2")) {
				// 添加服务机构账号
				automaticSysUser(bean);
			}
			// 启用机构
			/*
			 * bean.setEnableDate(new Date());
			 * bean.setIsEnable(CommonConstant.DictStartStatus.start);
			 * whetherIsEnable(bean);
			 */
		}
	}

}
