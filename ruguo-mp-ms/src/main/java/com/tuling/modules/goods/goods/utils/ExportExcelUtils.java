package com.tuling.modules.goods.goods.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.foundation.common.utils.upload.UploadException;
import com.foundation.modules.sys.utils.DictUtils;

/**
 * Created on 2017年06月12日
 * Description 导出Excel
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class ExportExcelUtils {

	private static Logger logger = LoggerFactory.getLogger(ExportExcelUtils.class);

	// 单元格宽度
	public static final int DEFAULT_CELL_WIDTH = 12;

	// 允许导出最大数据量
	public static final int DEFAULT_MAX = 10000;

	/**
	 * Created on 2017年06月12日 .
	 * Description 获取对应业务的excel导出格式
	 * @param type
	 * @author yuelingyun
	 * @return java.util.List<com.tuling.nfpc.common.ExportExcelElement>
	 */
	public static List<List<ExportExcelElement>> getExportExcelElementList(String type) {
		return ExportConstants.exportExcelElementMap.get(type);
	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 获取对应业务的excel导出格式
	 * @param type
	 * @author yuelingyun
	 * @return java.util.List<com.tuling.nfpc.common.ExportExcelElement>
	 */
	public static String getFileName(String type) {
		return ExportConstants.fileNameMap.get(type);
	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 调用Excel的单元格赋值和下载Excel的方法
	 * @param type
	 * @author yuelingyun
	 * @return java.util.List<com.tuling.nfpc.common.ExportExcelElement>
	 */
	public static void exportExcel(HttpServletResponse response, List<?> list, String type)
			throws UploadException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		XSSFWorkbook wb = ExportExcelUtils.exportExcel(list, ExportExcelUtils.getExportExcelElementList(type));
		ExportExcelUtils.downLoadExcel(response, wb, ExportExcelUtils.getFileName(type));
	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 对Excel的单元格进行赋值
	 * @param type
	 * @author yuelingyun
	 * @return java.util.List<com.tuling.nfpc.common.ExportExcelElement>
	 */
	@SuppressWarnings("rawtypes")
	public static XSSFWorkbook exportExcel(List entityList, List<List<ExportExcelElement>> elementExportList)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 创建XSSFWorkbook对象
		XSSFWorkbook wb = new XSSFWorkbook();
		int h = 0;
		for (List<ExportExcelElement> elementList : elementExportList) {
			String index = "";
			if (h == 0) {
				index = "商品信息";
			} else {
				index = "说明";
			}
			// 创建HSSFSheet对象
			XSSFSheet sheet = wb.createSheet(index);
			// 创建HSSFRow对象
			XSSFRow titleRow = sheet.createRow(0);

			// 说明样式
			CellStyle remarkCellStyle = wb.createCellStyle();

			// 设置说明字体
			XSSFFont remarkFont = wb.createFont();
			remarkFont.setBold(true);// 加粗
			remarkFont.setFontHeight(10);// 字体大小
//			remarkFont.setFontHeightInPoints((short) 10);// 字号
			remarkFont.setColor(HSSFColor.RED.index);// 颜色
			remarkCellStyle.setFont(remarkFont);
			remarkCellStyle.setWrapText(true);// 自动换行

			// 标题样式
			CellStyle titleCellStyle = wb.createCellStyle();
			// 设置标题字体加粗
			XSSFFont titleFont = wb.createFont();
			titleFont.setBold(true);// 加粗
			titleCellStyle.setFont(titleFont);
			
			// 金额格式
			CellStyle moneyCellStyle = wb.createCellStyle();
			DataFormat format = wb.createDataFormat();
			moneyCellStyle.setDataFormat(format.getFormat(",###,###.00"));

			int elementListSize = elementList.size();

			if (elementListSize > DEFAULT_MAX) {
				return null;
			}

			for (int i = 0; i < elementListSize; i++) {
				ExportExcelElement e = elementList.get(i);
				// 创建HSSFCell对象
				XSSFCell cell = titleRow.createCell(i);
				if (h == 1) {
					cell.setCellStyle(remarkCellStyle);
				} else {
					cell.setCellStyle(titleCellStyle);
				}
				// 设置单元格的值
				cell.setCellValue(e.getDisplayName());
				Integer width = e.getWidth();
				if (width == null) {
					width = DEFAULT_CELL_WIDTH;
				}
				sheet.setColumnWidth(i, width * 256);
			}
			int entityListSize = entityList.size();
			for (int i = 0; i < entityListSize; i++) {
				Object entity = entityList.get(i);
				// 创建HSSFRow对象
				XSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < elementListSize; j++) {
					ExportExcelElement e = elementList.get(j);
					Object obj = getEntityFieldValue(entity, e.getName());// 将对应单元格的值与实体对象进行互转
					XSSFCell cell = row.createCell(j);
					// cell.setCellStyle(cellStyle);
					String value = transformValue(obj);
					String sysDict = e.getSysDict();
					if (!StringUtils.isEmpty(e.getSysDict())) {
						value = DictUtils.getDictLabel(value, sysDict, "");
					}
					cell.setCellValue(value);
					if(ExcelConstant.Goods.GOODSPRICE.equals(e.getName())){
						cell.setCellStyle(moneyCellStyle);
					}
				}
			}
			h++;
		}

		return wb;

	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 下载导出文件
	 * @param response
	 * @param wb
	 * @param fileName
	 * @author yuelingyun
	 * @return void
	 */
	public static void downLoadExcel(HttpServletResponse response, XSSFWorkbook wb, String fileName) throws UploadException {

		OutputStream out = null;
		try {
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			// 创建输出流
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			logger.error("FileController.downFile download file is error.", e);
			throw new UploadException("文件下载失败");
		} finally {
			// 关闭输出流
			if (out != null) {
				try {
					out.close();
				} catch (IOException io) {
					logger.error("FileController.downFile close outputStream is error.", io);
				}
			}
		}
	}

	/**
	 * Created on 2017年06月13日 .
	 * Description entity特定字段取值
	 * @param obj entity对象
	 * @param fieldName 字段名称
	 * @author yuelingyun
	 * @return void
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static Object getEntityFieldValue(Object obj, String fieldName)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		if (obj == null) {
			return null;
		}
		Class<?> c = obj.getClass();
		StringBuffer method = new StringBuffer(32);
		method.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
		Method m = c.getMethod(method.toString());
		return m.invoke(obj);
	}

	/**
	 * Created on 2017年06月12日 .
	 * Description 根据值类型返回相应字符串
	 * @param value
	 * @author yuelingyun
	 * @return String
	 */
	private static String transformValue(Object value) {

		String result = "";

		if (value == null) {
			return result;
		}
		String type = value.getClass().getName();

		switch (type) {
		case "java.lang.String":
			result = String.valueOf(value);
			;
			break;
		case "java.lang.Character":
			result = String.valueOf(value);
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
		case "java.util.Date":
			Date d = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			result = sdf.format(d);
			break;
		default:
			result = String.valueOf(value);
		}
		return result;
	}

}