package com.tuling.modules.goods.goods.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017年06月13日
 * Description
 * Copyright tuling (c) 2017 .
 *
 * @author yuelingyun
 */
public class ImportExcelConstants {

    // 商品导入类型
    public static final String GOODS = "goods";
    
    // 格式列表
    public static final Map<String, ImportExcelEntity> importExcelEntityMap = new HashMap<String, ImportExcelEntity>();

    static {
    	/**
         * 商品基本信息导入格式
         */ 
        ImportExcelEntity goodsEntity = new ImportExcelEntity();
        List<ImportExcelElement> goodsList = new ArrayList<ImportExcelElement>();
        ImportExcelElement element = null;

        element = new ImportExcelElement();
        element.setColumnName("主键"); // 列名称
        element.setColumnNum(-1);
        element.setTableColumn("id"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setIsAutoUUID(true); // 是否自动生成UUID填充该字段
        element.setLength("32");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);

        element = new ImportExcelElement();
        element.setColumnName("商品编码"); // 列名称
        element.setColumnNum(0);
        element.setTableColumn("goods_code"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("10");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);

        element = new ImportExcelElement();
        element.setColumnName("商品名称"); // 列名称
        element.setColumnNum(1);
        element.setTableColumn("goods_name"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("50");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品性质"); // 列名称
        element.setColumnNum(2);
        element.setTableColumn("goods_nature"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);

        element = new ImportExcelElement();
        element.setColumnName("商品类型"); // 列名称
        element.setColumnNum(3);
        element.setTableColumn("goods_type"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品副标题"); // 列名称
        element.setColumnNum(4);
        element.setTableColumn("goods_title"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("100");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品时效性数"); // 列名称
        element.setColumnNum(5);
        element.setTableColumn("goods_validity_num"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("2");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(true);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品时效性单位"); // 列名称
        element.setColumnNum(6);
        element.setTableColumn("goods_validity_unit"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品价格"); // 列名称
        element.setColumnNum(7);
        element.setTableColumn("goods_price"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("10");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品库存"); // 列名称
        element.setColumnNum(8);
        element.setTableColumn("goods_stock"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("8");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(true);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品状态"); // 列名称
        element.setColumnNum(9);
        element.setTableColumn("goods_status"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品详情"); // 列名称
        element.setColumnNum(10);
        element.setTableColumn("goods_details"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("65535");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("被包含商品编码"); // 列名称
        element.setColumnNum(11);
        element.setTableColumn("pack_goods_code"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("4000");
        element.setColumnType("String");
        element.setIsExcel(true); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);

        element = new ImportExcelElement();
        element.setColumnName("被包含商品顺序号"); // 列名称
        element.setColumnNum(12);
        element.setTableColumn("pack_goods_seq"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("2");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(true);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品拼音码"); // 列名称
        element.setColumnNum(13);
        element.setTableColumn("goods_phoneticize"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("50");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("商品分类编码"); // 列名称
        element.setColumnNum(14);
        element.setTableColumn("goods_class_code"); // 对应字段名
        element.setIsNotNull(false); // 是否不允许为空
        element.setLength("32");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("是否推荐"); // 列名称
        element.setColumnNum(15);
        element.setTableColumn("is_recommend"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setDefaultFlag("0"); // 默认值
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("是否删除"); // 列名称
        element.setColumnNum(16);
        element.setTableColumn("del_flag"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("1");
        element.setColumnType("String");
        element.setDefaultFlag("0"); // 默认值
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("创建人"); // 列名称
        element.setColumnNum(17);
        element.setTableColumn("create_by"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("32");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("创建时间"); // 列名称
        element.setColumnNum(18);
        element.setTableColumn("create_time"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("0");
        element.setColumnType("Date");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("修改人"); // 列名称
        element.setColumnNum(19);
        element.setTableColumn("update_by"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("32");
        element.setColumnType("String");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        element = new ImportExcelElement();
        element.setColumnName("修改时间"); // 列名称
        element.setColumnNum(20);
        element.setTableColumn("update_time"); // 对应字段名
        element.setIsNotNull(true); // 是否不允许为空
        element.setLength("0");
        element.setColumnType("Date");
        element.setIsExcel(false); // 是否来自excel
        element.setNumber(false);// 是否为数字
        goodsList.add(element);
        
        goodsEntity.setTableNameSheet("goods_package_list");
        goodsEntity.setListElement(goodsList);
        goodsEntity.setTableName("goods_basic_info");
        goodsEntity.setShardDBName("00");
        goodsEntity.setSheetNum("0");
        importExcelEntityMap.put(GOODS, goodsEntity);
        
    }

    /**
     * Created on 2017年06月13日 .
     * Description 获取导入配置类
     * @param type 类型
     * @author yuelingyun
     * @return ImportExcelEntity
     */
    public static ImportExcelEntity getImportExcelEntity(String type) {
        return importExcelEntityMap.get(type);
    }
}