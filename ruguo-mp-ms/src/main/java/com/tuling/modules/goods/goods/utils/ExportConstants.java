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
public class ExportConstants {

    // 商品导出类型
    public static final String GOODS = "goods";
    
    // 格式列表MemberBasicInfoResult
    public static final Map<String, List<List<ExportExcelElement>>> exportExcelElementMap = new HashMap<String, List<List<ExportExcelElement>>>();

    // 导出文件名称的Map
    public static final Map<String, String> fileNameMap = new HashMap<String, String>();

    static {
    	List <List<ExportExcelElement>> exportList = new ArrayList<List<ExportExcelElement>>();
    	// 导出的商品字段列表
        List<ExportExcelElement> goodsList = new ArrayList<ExportExcelElement>();
        ExportExcelElement element = null;
        element = new ExportExcelElement("goodsCode", "商品编码", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsName", "商品名称", 20);
        goodsList.add(element);
        element = new ExportExcelElement("goodsNature", "商品性质", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsType", "商品类型", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsTitle", "商品副标题", 20);
        goodsList.add(element);
        element = new ExportExcelElement("goodsValidityNum", "商品时效性数", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsValidityUnit", "商品时效性单位", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsPrice", "商品价格", 15);
        goodsList.add(element);
        element = new ExportExcelElement("goodsStock", "商品库存", 15);
        goodsList.add(element);
        element = new ExportExcelElement("goodsStatus", "商品状态", 10);
        goodsList.add(element);
        element = new ExportExcelElement("goodsDetails", "商品详情", 50);
        goodsList.add(element);
        element = new ExportExcelElement("packGoodsCode", "被包含商品编码(多个商品用英文逗号分隔)", 50);
        goodsList.add(element);
        exportList.add(goodsList);
        // 导出的说明信息
        ExportExcelElement elements = null;
        List<ExportExcelElement> remarkList = new ArrayList<ExportExcelElement>();
        elements = new ExportExcelElement("remark", "1、第一行商品标题列不能随意换行，并且不能换列、删列或增列；从第二行开始录入本次导入的商品数据。\n"
        										  + "2、第一列商品编码、第二列商品名称、第四列商品类型、第六列商品时效性数、第七列商品时效性单位、第八列商品价格、第十一列商品详情都不允许为空，如果当前行第四列填写的是产品包（即填入的值为‘2’），则对应的第十二列也不允许为空。\n"
        										  + "3、第一列商品编码不能超过10个字符，第二列商品名称不能超过50个字符，第三列商品性质不能超过1个字符，第四列商品类型不能超过1个字符，第五列商品副标题不能超过100个字符，第六列商品时效性数不能超过2个字符，第七列商品时效性单位不能超过1个字符，第八列商品价格不能超过10个字符，第九列商品库存不能超过8个字符，第十列商品状态不能超过1个字符，第十一列商品详情不能超过65535个字符。\n"
        										  + "4、第三列商品性质如果需要填写，只能写入0或1，其中0代表服务；1代表实体商品；第四列商品类型只能写入1或2，其中1为商品；2为产品包；第七列商品时效性单位只能写入0或1，0代表次，1代表月；第十列商品状态如果需要填写，只能写入0或1，其中0代表下架 ，1代表上架；\n"
        										  + "5、商品名称和商品副标题都必须填入中文汉字；\n"
        										  + "6、第六列商品时效性数和第九列商品库存只允许填正整数；第八列商品价格只允许填最多两位小数的正实数；\n"
        										  + "7、如果当前行第四列填入的是产品包，则第十二列中填写的多个商品编码之间用英文的逗号“,”进行分隔；并且第十二列所有的商品编码值都必须在此次导入的第一列商品编码内；同时不能存在产品包的商品编码。\n", 100);
        remarkList.add(elements);
        exportList.add(remarkList);
        
        exportExcelElementMap.put(GOODS, exportList);
        fileNameMap.put(GOODS, "导出商品信息.xlsx");
        
       
    }
    
 // 商品导出类型
    public static final String MemberBasicInfoResultResult = "MemberBasicInfoResultResult";
    
    // 格式列表
//    public static final Map<String, List<List<ExportExcelElement>>> exportExcelElementMap = new HashMap<String, List<List<ExportExcelElement>>>();

//    // 导出文件名称的Map
//    public static final Map<String, String> fileNameMap = new HashMap<String, String>();

    static {
    	List <List<ExportExcelElement>> exportList = new ArrayList<List<ExportExcelElement>>();
    	// 导出的商品字段列表
        List<ExportExcelElement> MemberBasicInfoResultResultList = new ArrayList<ExportExcelElement>();
        ExportExcelElement element = null;
        element = new ExportExcelElement("name", "会员姓名", 10);
        MemberBasicInfoResultResultList.add(element);
        element = new ExportExcelElement("genderName", "会员性别", 10);
        MemberBasicInfoResultResultList.add(element);
        element = new ExportExcelElement("birthday", "出生日期", 10);
        MemberBasicInfoResultResultList.add(element);
        element = new ExportExcelElement("certificateName", "证件名称", 10);
        MemberBasicInfoResultResultList.add(element);
        element = new ExportExcelElement("certificateNo", "证件号码", 20);
        MemberBasicInfoResultResultList.add(element);
        element = new ExportExcelElement("mobilePhone", "手机号码", 10);
        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("email", "电子邮箱", 10);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("hight", "会员身高(cm)", 15);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("weight", "会员体重(kg)", 15);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("waistline", "会员腰围(cm)", 10);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("isChronicDisease", "是否有慢病", 10);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("chronicDiseaseContent", "慢病内容", 50);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("orgName", "所属机构名称", 20);
//        MemberBasicInfoResultResultList.add(element);
//        element = new ExportExcelElement("memberLevelName", "会员名称", 20);
//        MemberBasicInfoResultResultList.add(element);
        exportList.add(MemberBasicInfoResultResultList);
    
        
        exportExcelElementMap.put(MemberBasicInfoResultResult, exportList);
        fileNameMap.put(MemberBasicInfoResultResult, "导出会员信息.xlsx");
        
       
    }
}