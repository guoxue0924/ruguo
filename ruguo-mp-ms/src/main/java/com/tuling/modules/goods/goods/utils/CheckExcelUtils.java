package com.tuling.modules.goods.goods.utils;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.constant.CommonConstant;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.common.utils.upload.UploadException;
import com.tuling.modules.goods.goods.dao.GoodsDao;
import com.tuling.modules.goods.goods.entity.Goods;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017年06月13日
 * Description 校验Excel
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class CheckExcelUtils {

	private static GoodsDao goodsDao = SpringContextHolder.getBean(GoodsDao.class);

	/**
	 * Created on 2017年06月13日 .
	 * Description 校验商品导入的Excel
	 * @param CommonsMultipartFile type
	 * @author yuelingyun
	 * @return com.alibaba.fastjson.JSONObject
	 * @throws IOException 
	 */
	public static JSONObject checkGoodsExcel(CommonsMultipartFile file, String type) throws UploadException, IOException {

		JSONObject obj = new JSONObject();
		StringBuffer resultBuffer = new StringBuffer();
		// 创建放入所有商品编号的Map
		Map<String, String> goodsCodeMap = new HashMap<String, String>();
		// 创建放入所有产品包编号的Map
		Map<String, String> goodsCodePackageMap = new HashMap<String, String>();
		// 获取要导入的实体对象
		ImportExcelEntity importExcelEntity = ImportExcelConstants.getImportExcelEntity(type);
		// 获取要导入的实体对象集合
		List<ImportExcelElement> elementList = importExcelEntity.getListElement();
		// 获取要导入的sheet页数
		String sheetNumString = importExcelEntity.getSheetNum();
		if (StringUtils.isEmpty(sheetNumString)) {
			sheetNumString = "0";
		}
		String[] sheetNumStringArray = sheetNumString.split(",");
		boolean status = false;
		// 创建XSSFWorkbook对象
		XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
		int sheetLength = sheetNumStringArray.length;
		String goodsCode = "";// 创建当前行的商品编号的变量
		String goodsName = "";// 创建当前行的商品名称的变量
		String goodsType = "";// 创建当前行的商品类型的变量
		for (int index = 0; index < sheetLength; index++) {
			// 默认只校验第一个sheet页的信息
			if(index > 0){
				break;
			}
			XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetNumStringArray[index]));
			// 每个sheet页要导入的数据条数
			int totalRowCount = sheet.getLastRowNum();
			// 创建校验商品编号是否重复的Map
			Map<String, String> checkGoodsCodeMap = new HashMap<String, String>();
			// 创建校验商品名称是否重复的Map
			Map<String, String> checkGoodsNameMap = new HashMap<String, String>();
			// 创建校验商品编号是否在数据库中存在的Map
			String checkGoodsCodeExistStr = "";
			// 创建校验商品名称是否在数据库中存在的Map
			String checkGoodsNameExistStr = "";
			// 校验是否有相同的商品编号，默认为false
			boolean checkSameGoodsCode = false;
			// 校验是否有相同的商品名称，默认为false
			boolean checkSameGoodsName = false;
			// 创建校验产品包中的商品编号是否都在被导入的商品编号中的集合
			List<String> packageList = new ArrayList<String>();
			for (int i = 1; i <= totalRowCount; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row == null) {
					resultBuffer.append("第" + (i + 1) + "行不允许有空行，请删除！\n");
					status = true;
					continue;
				}
				for (ImportExcelElement element : elementList) {
					Boolean isAutoUUID = element.getIsAutoUUID();
					if (!(null != isAutoUUID && isAutoUUID)) {
						XSSFCell cell = row.getCell(element.getColumnNum());
						String value = getCellValue(cell);
						// 判断当前数据是否从Excel中获取
						if (element.getIsExcel()) {
							if (null != element.getIsNotNull() && element.getIsNotNull()) {
								// 判断导入的数据是否超过长度范围
								if (!StringUtils.isEmpty(element.getLength())) {
									int length = Integer.parseInt(element.getLength());
									if (null != value && value.length() > length) {
										resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "长度超出范围，请修改！\n");
										status = true;
									}
								}
								// 判断导入的数据是否允许为空
								if (StringUtils.isEmpty(value)) {
									resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "不允许为空，请修改！\n");
									status = true;
								} else {
									// 判断导入的数据是否满足正整数的判断
									if (element.getNumber()) {
										String rex = "^?[0-9]+";
										Pattern p = Pattern.compile(rex);
										Matcher m = p.matcher(value);
										if (!m.matches()) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "不是正整数，请修改！\n");
											status = true;
										}
									}
									// 判断商品类型是否是1或2，并放入对应的Map中
									if (ExcelConstant.Goods.GOODS_TYPE.equals(element.getTableColumn())) {
										if (!CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(value) && !CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(value)) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "录入的值不符合要求，请修改！\n");
											status = true;
										} else {
											goodsType = value;
											if (CommonConstant.goodsType.DICT_GOODS_TYPE_ONE.equals(value)) {
												goodsCodeMap.put(goodsCode, goodsCode);
											} else {
												goodsCodePackageMap.put(goodsCode, goodsCode);
											}
										}
									// 判断时效性单位是否为0或1
									} else if (ExcelConstant.Goods.GOODS_VALIDITY_UNIT.equals(element.getTableColumn())) {
										if (!CommonConstant.goodsValidityUnit.DICT_GOODS_VALIDITY_UNIT_ZERO.equals(value) && !CommonConstant.goodsValidityUnit.DICT_GOODS_VALIDITY_UNIT_ONE.equals(value)) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "录入的值不符合要求，请修改！\n");
											status = true;
										}
									// 判断价格是否为包含两位的正小数
									} else if (ExcelConstant.Goods.GOODS_PRICE.equals(element.getTableColumn())) {
										value = value.replace(",", "");
										String rex = "^[0-9]+(.[0-9]{2})?$";
										Pattern p = Pattern.compile(rex);
										Matcher m = p.matcher(value);
										if (!m.matches()) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "所填金额不正确，请修改！\n");
											status = true;
										}
									} else if (ExcelConstant.Goods.GOODS_CODE.equals(element.getTableColumn())) {
										goodsCode = value;
										// 判断商品编码是否在数据库中存在
										Goods goods = new Goods();
										goods.setGoodsCode(value);
										Goods entity = goodsDao.findByGoodsCode(goods);
										if (null != entity) {
											checkGoodsCodeExistStr += (i + 1) + ",";
											status = true;
										}
										// 判断当前的商品编号是否存在，存在则将数值加1，并重新放入Map中，否则直接放入Map 
										if (null != checkGoodsCodeMap.get(goodsCode) && !"".equals(checkGoodsCodeMap.get(goodsCode))
												&& checkGoodsCodeMap.get(goodsCode).split("@@@")[0].equals(goodsCode)) {
											checkGoodsCodeMap.put(goodsCode, goodsCode + "@@@" + checkGoodsCodeMap.get(goodsCode).split("@@@")[1] + "," + (i + 1) + "@@@" + "SAME");
											checkSameGoodsCode = true;
											status = true;
										} else {
											checkGoodsCodeMap.put(goodsCode, goodsCode + "@@@" + (i + 1) +"@@@" + "NOTSAME");
										}
									} else if (ExcelConstant.Goods.GOODS_NAME.equals(element.getTableColumn())) {
										goodsName = value;
										// 判断导入的商品名称是否为中文汉字
										Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
										char c[] = value.toCharArray();  
								        for(int n=0;n<c.length;n++){  
								            Matcher matcher = p.matcher(String.valueOf(c[n]));  
								            if(!matcher.matches()){  
								            	resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "包含非中文汉字，请修改！\n");
												status = true;
												break;
								            }  
								        }  
										// 判断商品名称是否在数据库中存在
										Goods goods = new Goods();
										goods.setGoodsName(value);
										Goods entity = goodsDao.findByGoodsName(goods);
										if (null != entity) {
											checkGoodsNameExistStr += (i + 1) + ",";
											status = true;
										}
										// 判断当前的商品名称是否存在，存在则将数值加1，并重新放入Map中，否则直接放入Map 
										if (null != checkGoodsNameMap.get(goodsName) && !"".equals(checkGoodsNameMap.get(goodsName))
												&& checkGoodsNameMap.get(goodsName).split("@@@")[0].equals(goodsName)) {
											checkGoodsNameMap.put(goodsName, goodsName + "@@@" + checkGoodsNameMap.get(goodsName).split("@@@")[1] + "," + (i + 1) + "@@@" + "SAME");
											checkSameGoodsName = true;
											status = true;
										} else {
											checkGoodsNameMap.put(goodsName, goodsName + "@@@" + (i + 1) +"@@@" + "NOTSAME");
										}
									}
								}
							} else {
								if (!StringUtils.isEmpty(value)) {
									// 判断导入的数据是否超长
									if (!StringUtils.isEmpty(element.getLength())) {
										int length = Integer.parseInt(element.getLength());
										if (null != value && value.length() > length) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "长度超出范围，请修改！\n");
											status = true;
										}
									}
									// 判断导入的数据是否是正整数
									if (element.getNumber()) {
										String rex = "^?[0-9]+";
										Pattern p = Pattern.compile(rex);
										Matcher m = p.matcher(value);
										if (!m.matches()) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "不是正整数，请修改！\n");
											status = true;
										}
									}
									// 判断商品性质和商品状态是否为0或1
									if (ExcelConstant.Goods.GOODS_NATURE.equals(element.getTableColumn()) || ExcelConstant.Goods.GOODS_STATUS.equals(element.getTableColumn())) {
										if (!CommonConstant.goodsNature.DICT_GOODS_NATURE_ZERO.equals(value) && !CommonConstant.goodsNature.DICT_GOODS_NATURE_ONE.equals(value)) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "录入的值不符合要求，请修改！\n");
											status = true;
										}
									}
									// 判断导入的商品副标题是否为中文汉字
									if (ExcelConstant.Goods.GOODS_TITLE.equals(element.getTableColumn())) {
										Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
										char c[] = value.toCharArray();  
								        for(int n=0;n<c.length;n++){  
								            Matcher matcher = p.matcher(String.valueOf(c[n]));  
								            if(!matcher.matches()){  
								            	resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "包含非中文汉字，请修改！\n");
												status = true;
												break;
								            }  
								        }  
									}
								}
								// 判断导入的是产品包，则不允许为空
								if (ExcelConstant.Goods.PACK_GOODS_CODE.equals(element.getTableColumn())) {
									if (CommonConstant.goodsType.DICT_GOODS_TYPE_TWO.equals(goodsType)) {
										if (StringUtils.isEmpty(value)) {
											resultBuffer.append("第" + (i + 1) + "行" + element.getColumnName() + "不允许为空，请修改！\n");
											status = true;
										} else {
											packageList.add(value + "@@" + goodsCode + "@@" + (i + 1));
										}
									}
								}
							}
						}
					}
				}
			}
			// 如果数据库中存在相同的商品编号
			if(checkGoodsCodeExistStr.length() > 0){
				checkGoodsCodeExistStr = checkGoodsCodeExistStr.substring(0, checkGoodsCodeExistStr.length() - 1);
				resultBuffer.append("第" + checkGoodsCodeExistStr + "行商品编号在数据库中已存在，请修改！\n");
			}
			// 如果数据库中存在相同的商品名称
			if(checkGoodsNameExistStr.length() > 0){
				checkGoodsNameExistStr = checkGoodsNameExistStr.substring(0, checkGoodsNameExistStr.length() - 1);
				resultBuffer.append("第" + checkGoodsNameExistStr + "行商品名称在数据库中已存在，请修改！\n");
			}
			// 如果有重复的商品编号，拼接返回前台
			if(checkSameGoodsCode){
				for(Entry<String, String> entry : checkGoodsCodeMap.entrySet()){
					if("SAME".equals(entry.getValue().split("@@@")[2])){
						resultBuffer.append("第" + entry.getValue().split("@@@")[1] + "行商品编码的值相同，请修改！\n");
					}
				}
			}
			// 如果有重复的商品名称，拼接返回前台
			if(checkSameGoodsName){
				for(Entry<String, String> entry : checkGoodsNameMap.entrySet()){
					if("SAME".equals(entry.getValue().split("@@@")[2])){
						resultBuffer.append("第" + entry.getValue().split("@@@")[1] + "行商品编码的名称相同，请修改！\n");
					}
				}
			}
			// 判断导入的产品包中的商品编号是否在此次导入的商品编号中存在，同时判断导入的产品包中的商品编号是否包含导入的产品包号
			for (String str : packageList) {
				String arrStr = str.split("@@")[0];
				// 判断导入的是否是多个商品
				if (arrStr.contains(",")) {
					String arr[] = arrStr.split(",");
					String sumValue = "";
					String sumPackageValue = "";
					// 判断导入的产品包中的商品编号是否在此次的导入商品编号中存在，默认为false
					boolean checkInfo = false;
					// 判断导入的产品包中的商品编号是否存在导入的产品包号，默认为false
					boolean checkPackageInfo = false;
					for (int o = 0; o < arr.length; o++) {
						if (StringUtils.isEmpty(goodsCodeMap.get(arr[o]))) {
							if(null != goodsCodePackageMap.get(arr[o]) && !"".equals(goodsCodePackageMap.get(arr[o]))){
								sumPackageValue += arr[o] + ",";
								checkPackageInfo = true;
								status = true;
							}else{
								sumValue += arr[o] + ",";
								checkInfo = true;
								status = true;
							}
						}
					}
					if (checkInfo) {
						resultBuffer.append(
								"第" + (str.split("@@")[2]) + "行产品包中被包含的产品编码" + sumValue.substring(0, sumValue.length() - 1) + "在此次导入的商品编码中不存在，请修改！\n");
					}
					if (checkPackageInfo) {
						resultBuffer.append(
								"第" + (str.split("@@")[2]) + "行被包含的产品编码" + sumPackageValue.substring(0, sumPackageValue.length() - 1) + "不能与产品包的商品编码相同，请修改！\n");
						status = true;
					}
				}else{
					if (StringUtils.isEmpty(goodsCodeMap.get(arrStr))) {
						if(null != goodsCodePackageMap.get(arrStr) && !"".equals(goodsCodePackageMap.get(arrStr))){
							resultBuffer.append(
									"第" + (str.split("@@")[2]) + "行被包含的产品编码" + arrStr + "不能与产品包的商品编码相同，请修改！\n");
							status = true;
						}else{
							resultBuffer.append(
									"第" + (str.split("@@")[2]) + "行产品包中被包含的产品编码" + arrStr + "在此次导入的商品编码中不存在，请修改！\n");
							status = true;
						}
					}
				}
			}
		}
		if (status) {
			obj.put("status", CommonConstant.RESULT_FAILURE);
			obj.put("msg", resultBuffer.toString());
		} else {
			obj.put("status", CommonConstant.RESULT_SUCCESS);
		}
		return obj;
	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 将当前单元格的值根据类型进行转换
	 * @param
	 * @author yuelingyun
	 * @return com.alibaba.fastjson.JSONObject
	 */
	private static String getCellValue(XSSFCell cell) {
		String cellValue = "";
		// DecimalFormat df = new DecimalFormat("#");
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
			// cellValue = df.format(cell.getNumericCellValue()).toString();
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
}