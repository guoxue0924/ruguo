/**
 * Copyright &copy; 2012-2016 All rights reserved.
 */
package com.tuling.modules.goods.goods.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.service.CrudService;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.common.utils.upload.UploadException;
import com.foundation.modules.sys.dao.DictDao;
import com.foundation.modules.sys.entity.Dict;
import com.foundation.modules.sys.entity.SqlEntity;
import com.github.pagehelper.util.StringUtil;
import com.tuling.modules.goods.goods.dao.GoodsDao;
import com.tuling.modules.goods.goods.dao.GoodsPackageListDao;
import com.tuling.modules.goods.goods.dao.ServicePackageMongoDao;
import com.tuling.modules.goods.goods.entity.Goods;
import com.tuling.modules.goods.goods.entity.GoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsFilterClassCodes;
import com.tuling.modules.goods.goods.entity.GoodsPackageGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageListGoodsFilter;
import com.tuling.modules.goods.goods.entity.GoodsPackageTagsFilter;
import com.tuling.modules.goods.goods.utils.ExcelConstant;
import com.tuling.modules.goods.goods.utils.ImportExcelConstants;
import com.tuling.modules.goods.goods.utils.ImportExcelElement;
import com.tuling.modules.goods.goods.utils.ImportExcelEntity;
import com.tuling.util.PinYinUtils;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * goodsService
 * 
 * @author guoxue
 * @version 2017-06-05
 */
@Service
public class GoodsService extends CrudService<GoodsDao, Goods> {

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private DictDao dictDao;
	@Autowired
	private ServicePackageMongoDao servicePackageMongoDao;
	
	

	@Autowired
	private  GoodsPackageListDao goodsPackageListDao;
//	private  GoodsPackageListDao goodsPackageListDao = SpringContextHolder.getBean(GoodsPackageListDao.class);

	/**
	 * ????????????????????????
	 * 
	 * @author guoxue
	 * @date 2017-06-5
	 */
	public List<Goods> findGoodsList(GoodsFilterClassCodes goodsFilterClassCodes) {
		List<Goods> list = goodsDao.findGoodsList(goodsFilterClassCodes);
		return list;

	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????id??????GoodsList}
	 *
	 * @author guoxue
	 * @param idList
	 * @return JSONObject
	 */
	public JSONObject findGoodsListById(List<String> idList) {
		JSONObject result = new JSONObject();

		List<Goods> list = new ArrayList<Goods>();
		for (String id : idList) {
			Goods goods = goodsDao.get(id);
			if (goods != null) {
				list.add(goods);
			}
			if (list != null) {
				result.put("goodsList", list);
			}
		}
		return result;

	}



	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject modifyGoodsStatusOn(Goods entity) {
		JSONObject result = new JSONObject();
		goodsDao.modifyGoodsStatusOn(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {????????????????????????}
	 *
	 * @author guoxue
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject modifyGoodsStatusOff(Goods entity) {
		JSONObject result = new JSONObject();
		goodsDao.modifyGoodsStatusOff(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???7??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject deleteGoods(Goods entity) {
		JSONObject result = new JSONObject();
		goodsDao.deleteGoods(entity);
		result.put(CommonConstant.SUCCESS, CommonConstant.RESULT_SUCCESS);
		return result;
	}

	/**
	 * 
	 * Created on 2017???6???8??? .
	 * <p>
	 * Description {??????????????????}
	 *
	 * @author guoxue
	 * @return List<Goods>
	 */
	public List<Goods> findGoods() {
		List<Goods> list = new ArrayList<Goods>();
		list = goodsDao.findGoods();
		return list;
	}

	/**
	 * 
	 * Created on 2017???6???9??? .
	 * <p>
	 * Description {??????code??????goods}
	 *
	 * @author guoxue
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsCode(Goods goods) {
		Goods haveGoods = goodsDao.findByGoodsCode(goods);
		return haveGoods;
	}

	/**
	 * 
	 * Created on 2017???6???22??? .
	 * <p>
	 * Description {??????name??????goods}
	 *
	 * @author guoxue
	 * @param goods
	 * @return Goods
	 */
	public Goods findByGoodsName(Goods goods) {
		Goods haveGoods = goodsDao.findByGoodsName(goods);
		return haveGoods;
	}

	/**
	 * 
	 * Created on 2017???6???12??? .
	 * <p>
	 * Description {?????????????????????????????????}
	 *
	 * @author yuelingyun
	 * @param goodsFilter
	 * @return List
	 */
	public List<Goods> findGoodsAndPackageList(GoodsFilter goodsFilter) {
		List<Goods> list = goodsDao.findGoodsAndPackageList(goodsFilter);
//		List<Goods> goodsList = new ArrayList<Goods>();
//		Map<String, Goods> goodsMap = new HashMap<String, Goods>();
//		String packGoodsCode = "";
//		for (Goods goods : list) {
//			if (goodsMap.containsKey(goods.getGoodsCode())) {
//				packGoodsCode = goodsMap.get(goods.getGoodsCode()).getPackGoodsCode() + "," + goods.getPackGoodsCode();
//				goods.setPackGoodsCode(packGoodsCode);
//				goodsMap.put(goods.getGoodsCode(), goods);
//			} else {
//				goodsMap.put(goods.getGoodsCode(), goods);
//			}
//		}
//		for (Entry<String, Goods> entry : goodsMap.entrySet()) {
//			goodsList.add(entry.getValue());
//		}
		return list;
	}

	/**
	 * Created on 2017???06???12??? .
	 * Description ????????????Excel
	 * @param
	 * @author yuelingyun
	 * @return com.alibaba.fastjson.JSONObject
	 * @throws IOException 
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	@Transactional(value = "transactionManagerBiz", rollbackFor = Exception.class)
	public JSONObject importGoodsExcel(CommonsMultipartFile file, String type, String userId)
			throws UploadException, IOException, BadHanyuPinyinOutputFormatCombination {

		JSONObject obj = new JSONObject();
		// ?????????????????????
		ImportExcelEntity importExcelEntity = ImportExcelConstants.getImportExcelEntity(type);
		// ?????????????????????
		List<ImportExcelElement> elementList = importExcelEntity.getListElement();
		String sheetNumString = importExcelEntity.getSheetNum();
		if (StringUtils.isEmpty(sheetNumString)) {
			sheetNumString = "0";
		}
		String[] sheetNumStringArray = sheetNumString.split(",");
		// ??????XSSFWorkbook??????
		XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
		int sheetLength = sheetNumStringArray.length;
		int totalSheetRowCount = 0;
		String columnBuffer = "";// ?????????????????????????????????
		String columnBufferPackage = "";// ?????????????????????????????????????????????
		String valueBuffer = "";// ???????????????????????????value
		String valueBufferPackage = "";// ???????????????????????????????????????value
		String goodsCode = "";// ????????????????????????
		String goods_name = "";// ????????????????????????
		String goodsType = "";// ????????????????????????
		String packGoodsCode = "";// ????????????????????????????????????
		String columnType = "";
		Boolean isAutoUUID = false;
		String value = "";
		for (int index = 0; index < sheetLength; index++) {
			// ??????????????????sheet????????????
			if (index > 0) {
				break;
			}
			XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetNumStringArray[index]));
			int totalRowCount = sheet.getLastRowNum();// ??????sheet????????????
			for (int i = 1; i <= totalRowCount; i++) {
				XSSFRow row = sheet.getRow(i);// ????????????
				columnBuffer = "";
				columnBufferPackage = "";
				valueBuffer = "";
				valueBufferPackage = "";
				for (ImportExcelElement element : elementList) {
					columnType = element.getColumnType();// ????????????
					// ????????????uuid
					isAutoUUID = element.getIsAutoUUID();
					// ?????????????????????
					if (null != isAutoUUID && isAutoUUID) {
						columnBuffer += element.getTableColumn() + ",";// ??????id
						valueBuffer += "'" + IdGen.uuid() + "',";
					} else {
						XSSFCell cell = row.getCell(element.getColumnNum());
						if ("String".equals(columnType)) {
							value = getCellValue(cell);
							if (ExcelConstant.Goods.DEL_FLAG.equals(element.getTableColumn())) {
								value = element.getDefaultFlag();
								columnBufferPackage += element.getTableColumn() + ",";// ??????del_flag
								valueBufferPackage += "'" + value + "',";
							} else if (ExcelConstant.Goods.CREATE_BY.equals(element.getTableColumn())) {
								value = userId;
								columnBufferPackage += element.getTableColumn() + ",";// ??????create_by
								valueBufferPackage += "'" + userId + "',";
							} else if (ExcelConstant.Goods.UPDATE_BY.equals(element.getTableColumn())) {
								value = userId;
								columnBufferPackage += element.getTableColumn() + ",";// ??????update_by
								valueBufferPackage += "'" + userId + "',";
							} else if (ExcelConstant.Goods.GOODS_NAME.equals(element.getTableColumn())) {
								goods_name = value;
							} else if (ExcelConstant.Goods.GOODS_PHONETICIZE.equals(element.getTableColumn())) {
								value = PinYinUtils.getEname(goods_name);// ????????????????????????????????????
							} else if (ExcelConstant.Goods.IS_RECOMMEND.equals(element.getTableColumn())) {
								value = element.getDefaultFlag();
							} else if (ExcelConstant.Goods.GOODS_CODE.equals(element.getTableColumn())) {
								goodsCode = value;
								columnBufferPackage += element.getTableColumn() + ",";// ??????goods_code
								valueBufferPackage += "'" + value + "',";
							} else if (ExcelConstant.Goods.PACK_GOODS_CODE.equals(element.getTableColumn())) {
								// ??????????????????????????????????????????????????????pack_goods_code??????
								if (CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(goodsType)) {
									packGoodsCode = value;
								} else {
									columnBufferPackage += element.getTableColumn() + ",";// ??????pack_goods_code
									valueBufferPackage += "'" + goodsCode + "',";
								}
								continue;
							} else if (ExcelConstant.Goods.PACK_GOODS_SEQ.equals(element.getTableColumn())) {
								if (CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(goodsType)) {
									columnBufferPackage += element.getTableColumn() + ",";// ??????pack_goods_seq
									valueBufferPackage += "'2',";
								}
								continue;
							} else if (ExcelConstant.Goods.GOODS_TYPE.equals(element.getTableColumn())) {
								goodsType = value;
							} else if(ExcelConstant.Goods.GOODS_PRICE.equals(element.getTableColumn())){
								value = value.replace(",", "");
							}
							valueBuffer += "'" + value + "',";
						} else if ("Date".equals(columnType)) {
							if (ExcelConstant.Goods.CREATE_TIME.equals(element.getTableColumn())) {
								value = "str_to_date(SYSDATE(),'%Y-%m-%d %H:%i:%s')";// ??????create_date
							} else if (ExcelConstant.Goods.UPDATE_TIME.equals(element.getTableColumn())) {
								value = "str_to_date(SYSDATE(),'%Y-%m-%d %H:%i:%s')";// ??????update_date
							}
							columnBufferPackage += element.getTableColumn() + ",";
							valueBuffer += value + ",";
							valueBufferPackage += value + ",";
						}
						columnBuffer += element.getTableColumn() + ",";
					}
				}
				StringBuffer sqlBuffer = new StringBuffer();
				sqlBuffer.append(" INSERT INTO ");
				sqlBuffer.append(importExcelEntity.getTableName());
				sqlBuffer.append(" ( ");
				sqlBuffer.append(columnBuffer.substring(0, columnBuffer.length() - 1));
				sqlBuffer.append(" ) ");
				sqlBuffer.append(" VALUES ");
				sqlBuffer.append(" ( ");
				sqlBuffer.append(valueBuffer.substring(0, valueBuffer.length() - 1));
				sqlBuffer.append(" ) ");
				// ???????????????
				SqlEntity sqlEntity = new SqlEntity();
				sqlEntity.setSqlString(sqlBuffer.toString());
				goodsDao.insertGoodsSql(sqlEntity);
				// ??????????????????????????????????????????????????????????????????????????????
				if (StringUtil.isNotEmpty(packGoodsCode)) {
					// ????????????????????????????????????????????????????????????????????????
					if (packGoodsCode.contains(",")) {
						String column = columnBufferPackage + ExcelConstant.Goods.PACK_GOODS_CODE + "," + ExcelConstant.Goods.PACK_GOODS_SEQ + ","
								+ ExcelConstant.Goods.ID;
						String arr[] = packGoodsCode.split(",");
						for (int n = 0; n < arr.length; n++) {
							String columnValue = valueBufferPackage + "'" + arr[n] + "'," + "'" + (n + 2) + "'" + "," + "'" + IdGen.uuid() + "'";
							StringBuffer sqlPackageBuffer = new StringBuffer();
							sqlPackageBuffer.append(" INSERT INTO ");
							sqlPackageBuffer.append(importExcelEntity.getTableNameSheet());
							sqlPackageBuffer.append(" ( ");
							sqlPackageBuffer.append(column);
							sqlPackageBuffer.append(" ) ");
							sqlPackageBuffer.append(" VALUES ");
							sqlPackageBuffer.append(" ( ");
							sqlPackageBuffer.append(columnValue);
							sqlPackageBuffer.append(" ) ");
							// ??????????????????
							SqlEntity sqlPackageEntity = new SqlEntity();
							sqlPackageEntity.setSqlString(sqlPackageBuffer.toString());
							goodsPackageListDao.insertGoodsPackageSql(sqlPackageEntity);
						}
					} else {
						String column = columnBufferPackage + ExcelConstant.Goods.PACK_GOODS_CODE + "," + ExcelConstant.Goods.PACK_GOODS_SEQ + ","
								+ ExcelConstant.Goods.ID;
						String columnValue = valueBufferPackage + "'" + packGoodsCode + "'," + "'2'" + "," + "'" + IdGen.uuid() + "'";
						StringBuffer sqlPackageBuffer = new StringBuffer();
						sqlPackageBuffer.append(" INSERT INTO ");
						sqlPackageBuffer.append(importExcelEntity.getTableNameSheet());
						sqlPackageBuffer.append(" ( ");
						sqlPackageBuffer.append(column);
						sqlPackageBuffer.append(" ) ");
						sqlPackageBuffer.append(" VALUES ");
						sqlPackageBuffer.append(" ( ");
						sqlPackageBuffer.append(columnValue);
						sqlPackageBuffer.append(" ) ");
						// ??????????????????
						SqlEntity sqlPackageEntity = new SqlEntity();
						sqlPackageEntity.setSqlString(sqlPackageBuffer.toString());
						goodsPackageListDao.insertGoodsPackageSql(sqlPackageEntity);
					}
				}
			}
			totalSheetRowCount = totalSheetRowCount + totalRowCount;
		}
		obj.put("status", CommonConstant.RESULT_SUCCESS);
		obj.put("msg", totalSheetRowCount + "???????????????????????????");
		return obj;
	}

	/**
	 * Created on 2017???06???12??? . Description ????????????????????????????????????????????????
	 * 
	 * @param
	 * @author yuelingyun
	 * @return com.alibaba.fastjson.JSONObject
	 */
	private static String getCellValue(XSSFCell cell) {
		String cellValue = "";
		if (null == cell) {
			return "";
		}
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:// String??????
			cellValue = cell.getRichStringCellValue().getString().trim();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:// Number??????
			HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
			cellValue = dataFormatter.formatCellValue(cell);
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:// boolean??????
			cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		default:
			cellValue = "";
		}
		return cellValue;
	}

	public List<Goods> getGoodsPackageGoods(GoodsFilterClassCodes goodsFilterClassCodes) {
		List<Goods> list = goodsDao.getGoodsPackageGoods(goodsFilterClassCodes);
		return list;
	}

	public GoodsPackageGoodsFilter getPackageGoods(Goods goods) {
		GoodsPackageGoodsFilter goodsPackageGoodsFilter = new GoodsPackageGoodsFilter();
		goodsPackageGoodsFilter = goodsDao.getPackageGoods(goods);
		return goodsPackageGoodsFilter;
	}

	/**
	 * 
	 * Created on 2018???1???25??? .
	 * <p>
	 * Description {????????????}
	 *
	 * @author guoxue 
	 * @param goods void
	 */
	public void saveGoods(Goods goods) {
		if("2".equals(goods.getGoodsType())){
			goods.setServiceOrgLevelCode("");
			goods.setServiceOrgLevelName("");
			goods.setServiceOrgRankCode("");
			goods.setServiceOrgRankName("");
			goods.setServicePersonLevelCode("");
			goods.setServicePersonLevelName("");
			goods.setServicePersonTypeCode("");
			goods.setServicePersonTypeName("");
			goods.setServicePersonMajorCode("");
			goods.setServicePersonMajorName("");
		}else{
			if(!goods.getServiceOrgLevelCode().isEmpty()){
				Dict dict4 = new Dict();
				List<Dict> list4 = new ArrayList<Dict>();
				dict4.setType("DICT_HEALTH_ORG_LEVEL");
				dict4.setValue(goods.getServiceOrgLevelCode());
				list4 = dictDao.findList(dict4);
				goods.setServiceOrgLevelName(list4.get(0).getLabel());
		}
			
			if(!goods.getServiceOrgRankCode().isEmpty()){
				Dict dict5 = new Dict();
				List<Dict> list5 = new ArrayList<Dict>();
				dict5.setType("DICT_HOS_GRADE");
				dict5.setValue(goods.getServiceOrgRankCode());
				list5 = dictDao.findList(dict5);
				goods.setServiceOrgRankName(list5.get(0).getLabel());
		}
		
		if(!goods.getServicePersonTypeCode().isEmpty()){
				Dict dict1 = new Dict();
				List<Dict> list1 = new ArrayList<Dict>();
				dict1.setType("DICT_SERVICE_PERSON_TYPE");
				dict1.setValue(goods.getServicePersonTypeCode());
				list1 = dictDao.findList(dict1);
				goods.setServicePersonTypeName(list1.get(0).getLabel());
		}
		
		if(!(goods.getServicePersonLevelCode() == null)){
				Dict dict2 = new Dict();
				List<Dict> list2 = new ArrayList<Dict>();
				dict2.setType("DICT_SERVICE_PERSON_LEVEL");
				dict2.setValue(goods.getServicePersonLevelCode());
				list2 = dictDao.findList(dict2);
				goods.setServicePersonLevelName(list2.get(0).getLabel());
		}
		
		if(!goods.getServicePersonMajorCode().isEmpty()){
				Dict dict3 = new Dict();
				List<Dict> list3 = new ArrayList<Dict>();
				dict3.setType("DICT_DOC_PRACTICE_SCOPE");
				dict3.setValue(goods.getServicePersonMajorCode());
				list3 = dictDao.findList(dict3);
				goods.setServicePersonMajorName(list3.get(0).getLabel());
			
		}
		}
		
		
		super.save(goods);
	}

	


}