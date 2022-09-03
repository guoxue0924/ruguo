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
	 * 分页查询用户信息
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
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description {根据id获得GoodsList}
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
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量设置商品上架}
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
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量设置商品下架}
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
	 * Created on 2017年6月7日 .
	 * <p>
	 * Description {批量删除商品}
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
	 * Created on 2017年6月8日 .
	 * <p>
	 * Description {获取商品列表}
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
	 * Created on 2017年6月9日 .
	 * <p>
	 * Description {通过code查询goods}
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
	 * Created on 2017年6月22日 .
	 * <p>
	 * Description {通过name查询goods}
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
	 * Created on 2017年6月12日 .
	 * <p>
	 * Description {查询商品和产品包的数据}
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
	 * Created on 2017年06月12日 .
	 * Description 导入商品Excel
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
		// 需要导入的对象
		ImportExcelEntity importExcelEntity = ImportExcelConstants.getImportExcelEntity(type);
		// 导入对象的集合
		List<ImportExcelElement> elementList = importExcelEntity.getListElement();
		String sheetNumString = importExcelEntity.getSheetNum();
		if (StringUtils.isEmpty(sheetNumString)) {
			sheetNumString = "0";
		}
		String[] sheetNumStringArray = sheetNumString.split(",");
		// 创建XSSFWorkbook对象
		XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
		int sheetLength = sheetNumStringArray.length;
		int totalSheetRowCount = 0;
		String columnBuffer = "";// 插入商品基本信息的字段
		String columnBufferPackage = "";// 插入被包含的商品基本信息的字段
		String valueBuffer = "";// 插入商品基本信息的value
		String valueBufferPackage = "";// 插入被包含的商品基本信息的value
		String goodsCode = "";// 当前行的商品编码
		String goods_name = "";// 当前行的商品名称
		String goodsType = "";// 当前行的商品类型
		String packGoodsCode = "";// 当前行的被包含的商品编码
		String columnType = "";
		Boolean isAutoUUID = false;
		String value = "";
		for (int index = 0; index < sheetLength; index++) {
			// 只导入第一个sheet页的数据
			if (index > 0) {
				break;
			}
			XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetNumStringArray[index]));
			int totalRowCount = sheet.getLastRowNum();// 当前sheet页的数量
			for (int i = 1; i <= totalRowCount; i++) {
				XSSFRow row = sheet.getRow(i);// 当前的行
				columnBuffer = "";
				columnBufferPackage = "";
				valueBuffer = "";
				valueBufferPackage = "";
				for (ImportExcelElement element : elementList) {
					columnType = element.getColumnType();// 字段类型
					// 自动生成uuid
					isAutoUUID = element.getIsAutoUUID();
					// 判断是不是自增
					if (null != isAutoUUID && isAutoUUID) {
						columnBuffer += element.getTableColumn() + ",";// 拼接id
						valueBuffer += "'" + IdGen.uuid() + "',";
					} else {
						XSSFCell cell = row.getCell(element.getColumnNum());
						if ("String".equals(columnType)) {
							value = getCellValue(cell);
							if (ExcelConstant.Goods.DEL_FLAG.equals(element.getTableColumn())) {
								value = element.getDefaultFlag();
								columnBufferPackage += element.getTableColumn() + ",";// 拼接del_flag
								valueBufferPackage += "'" + value + "',";
							} else if (ExcelConstant.Goods.CREATE_BY.equals(element.getTableColumn())) {
								value = userId;
								columnBufferPackage += element.getTableColumn() + ",";// 拼接create_by
								valueBufferPackage += "'" + userId + "',";
							} else if (ExcelConstant.Goods.UPDATE_BY.equals(element.getTableColumn())) {
								value = userId;
								columnBufferPackage += element.getTableColumn() + ",";// 拼接update_by
								valueBufferPackage += "'" + userId + "',";
							} else if (ExcelConstant.Goods.GOODS_NAME.equals(element.getTableColumn())) {
								goods_name = value;
							} else if (ExcelConstant.Goods.GOODS_PHONETICIZE.equals(element.getTableColumn())) {
								value = PinYinUtils.getEname(goods_name);// 获取当前商品名称的拼音码
							} else if (ExcelConstant.Goods.IS_RECOMMEND.equals(element.getTableColumn())) {
								value = element.getDefaultFlag();
							} else if (ExcelConstant.Goods.GOODS_CODE.equals(element.getTableColumn())) {
								goodsCode = value;
								columnBufferPackage += element.getTableColumn() + ",";// 拼接goods_code
								valueBufferPackage += "'" + value + "',";
							} else if (ExcelConstant.Goods.PACK_GOODS_CODE.equals(element.getTableColumn())) {
								// 如果是产品包，赋值给当前变量，否则将pack_goods_code拼接
								if (CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(goodsType)) {
									packGoodsCode = value;
								} else {
									columnBufferPackage += element.getTableColumn() + ",";// 拼接pack_goods_code
									valueBufferPackage += "'" + goodsCode + "',";
								}
								continue;
							} else if (ExcelConstant.Goods.PACK_GOODS_SEQ.equals(element.getTableColumn())) {
								if (CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(goodsType)) {
									columnBufferPackage += element.getTableColumn() + ",";// 拼接pack_goods_seq
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
								value = "str_to_date(SYSDATE(),'%Y-%m-%d %H:%i:%s')";// 拼接create_date
							} else if (ExcelConstant.Goods.UPDATE_TIME.equals(element.getTableColumn())) {
								value = "str_to_date(SYSDATE(),'%Y-%m-%d %H:%i:%s')";// 拼接update_date
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
				// 插入商品表
				SqlEntity sqlEntity = new SqlEntity();
				sqlEntity.setSqlString(sqlBuffer.toString());
				goodsDao.insertGoodsSql(sqlEntity);
				// 判断被包含的商品是否为空，如果为空，说明导入的是商品
				if (StringUtil.isNotEmpty(packGoodsCode)) {
					// 说明导入的是产品包，包含逗号说明导入的是多个商品
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
							// 插入产品包表
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
						// 插入产品包表
						SqlEntity sqlPackageEntity = new SqlEntity();
						sqlPackageEntity.setSqlString(sqlPackageBuffer.toString());
						goodsPackageListDao.insertGoodsPackageSql(sqlPackageEntity);
					}
				}
			}
			totalSheetRowCount = totalSheetRowCount + totalRowCount;
		}
		obj.put("status", CommonConstant.RESULT_SUCCESS);
		obj.put("msg", totalSheetRowCount + "行数据，导入成功！");
		return obj;
	}

	/**
	 * Created on 2017年06月12日 . Description 将当前单元格的值根据类型进行转换
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
		case XSSFCell.CELL_TYPE_STRING:// String类型
			cellValue = cell.getRichStringCellValue().getString().trim();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:// Number类型
			HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
			cellValue = dataFormatter.formatCellValue(cell);
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:// boolean类型
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
	 * Created on 2018年1月25日 .
	 * <p>
	 * Description {保存商品}
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