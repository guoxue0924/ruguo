package com.tuling.modules.goods.goods.utils;

/**
 * 常量工具类
 *
 * @author yuelingyun
 * @version 2017-6-21
 */
public interface ExcelConstant {
	// 商品常量
	interface Goods {
		public final static String ID = "id"; 										// 商品主键
		public final static String GOODS_TYPE = "goods_type"; 						// 商品类型，1为商品；2为产品包
		public final static String GOODS_VALIDITY_UNIT = "goods_validity_unit"; 	// 商品时效性单位，0次；1月
		public final static String GOODS_PRICE = "goods_price"; 					// 商品价格
		public final static String GOODS_CODE = "goods_code"; 						// 商品编码
		public final static String GOODS_NAME = "goods_name"; 						// 商品名称
		public final static String GOODS_TITLE = "goods_title";						// 商品副标题
		public final static String GOODS_STATUS = "goods_status"; 					// 商品状态，0下架；1上架
		public final static String GOODS_NATURE = "goods_nature"; 					// 商品性质，标识商品是服务还是实体商品，0服务；1实体商品
		public final static String PACK_GOODS_CODE = "pack_goods_code"; 			// 被包含商品编码
		public final static String DEL_FLAG = "del_flag"; 							// 标识记录是否删除 0：否1：是
		public final static String CREATE_BY = "create_by"; 						// 创建此条记录的用户编码
		public final static String UPDATE_BY = "update_by"; 						// 修改此条记录的用户编码
		public final static String GOODS_PHONETICIZE = "goods_phoneticize"; 		// 商品拼音码
		public final static String IS_RECOMMEND = "is_recommend"; 					// 是否推荐，0不推荐，1推荐
		public final static String PACK_GOODS_SEQ = "pack_goods_seq"; 				// 被包含商品顺序号
		public final static String CREATE_TIME = "create_time"; 					// 此条数据产生的时间
		public final static String UPDATE_TIME = "update_time"; 					// 此条数据更新的时间
		public final static String GOODSPRICE = "goodsPrice"; 					// 商品价格
	}
}