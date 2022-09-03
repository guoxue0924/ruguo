package com.foundation.common.constant;

/**
 * 区划字典工具类
 *
 * @author liuqing
 * @version 2017-3-13
 */
public interface CommonConstant {
	// 返回结果key名称
	public static final String SUCCESS = "success"; // 请求操作结果状态
	public static final String ERROR_MSG = "errorMsg"; // 请求操作错误信息

	// 操作结果状态值常量
	public final static String RESULT_SUCCESS = "0"; // 成功
	public final static String RESULT_FAILURE = "1"; // 失败

	// 操作结果状态值常量
	public final static String COMMON_YES = "1"; // 是
	public final static String COMMON_NO = "0"; // 否

	// 区划级别
	interface DictZoneLevel {
		public final static String state = "1"; // 国家级
		public final static String provice = "2"; // 省级
		public final static String city = "3"; // 市级
		public final static String county = "4"; // 区级
		public final static String town = "5"; // 乡级
	}

	// 国家中心
	public final static String DICT_ZONE_STATECENTER = "000000000";
	public final static String DICT_STATECENTER_ID = "000000000";

	// 基础库对应编码
	public final static String SHARDDB_STATE = "00";

	// 国家中心
	public final static String DICT_ZONE_STATECENTERNAME = "国家孕前优生数据中心";

	// 启用、未启用状态
	interface DictStartStatus {
		public final static String notstart = "0"; // 未启用
		public final static String start = "1"; // 启用
		public final static String stop = "2"; // 停用
	}

	// parentIds分割符号
	public final static String DICT_PARENTIDS_SEPARATOR = ",";

	// 性别
	interface DictGender {
		public final static String unknow = "0"; // 未知的性别
		public final static String male = "1"; // 男性
		public final static String female = "2"; // 女性
		public final static String unstated = "9"; // 未说明的性别
	}

	// 胎婴儿性别
	interface DictBabyGender {
		public final static String male = "1"; // 男
		public final static String female = "2"; // 女
		public final static String unknow = "0"; // 不详
		public final static String unnormal = "3"; // 两性畸形
	}

	// 性别-项目用
	interface DictGenderItem {
		public final static String currency = "0"; // 通用
		public final static String male = "1"; // 男
		public final static String female = "2"; // 女
	}

	// 检验检查项目分类
	interface DictExamType {
		public final static String custom = "1"; // 自定义
		public final static String routine = "2"; // 常规
	}

	// 记录删除标识
	interface DelFlag {
		public final static String normal = "0"; // 正常
		public final static String delete = "1"; // 删除
	}

	// 机构类型
	interface DictOrgStyle {
		public final static String manager = "1"; // 管理机构
		public final static String service = "2"; // 服务机构
	}

	// 角色
	interface DictRole {
		public final static String city_service_mgr = "10"; // 市级服务机构管理员
		public final static String county_service_mgr = "11"; // 县级服务机构管理员
		public final static String statemanager = "1111"; // 国家级管理机构管理员（国家级管理员）
		public final static String town_service_mgr = "12"; // 乡级服务机构管理员
		public final static String pro_service_clinic_doc = "13"; // 省级服务机构临床医生
		public final static String pro_service_check_doc = "14"; // 省级服务机构检验医生
		public final static String pro_service_image_doc = "15"; // 省级服务机构影像医生
		public final static String pro_service_visit_doc = "16"; // 省级服务机构随访员
		public final static String pro_service_input_doc = "17"; // 省级服务机构录入员
		public final static String city_service_clinic_doc = "18"; // 市级服务机构临床医生
		public final static String city_service_check_doc = "19"; // 市级服务机构检验医生
		public final static String provincemanager = "2"; // 省级管理机构管理员（省级管理员）
		public final static String city_service_image_doc = "20"; // 市级服务机构影像医生
		public final static String city_service_visit_doc = "21"; // 市级服务机构随访员
		public final static String city_service_input_doc = "22"; // 市级服务机构录入员
		public final static String county_service_clinic_doc = "23"; // 县级服务机构临床医生
		public final static String county_service_check_doc = "24"; // 县级服务机构检验医生
		public final static String county_service_image_doc = "25"; // 县级服务机构影像医生
		public final static String county_service_visit_doc = "26"; // 县级服务机构随访员
		public final static String county_service_input_doc = "27"; // 县级服务机构录入员
		public final static String town_service_clinic_doc = "28"; // 乡级服务机构临床医生
		public final static String town_service_check_doc = "29"; // 乡级服务机构检验医生
		public final static String supermanager = "3"; // 国家级信息员
		public final static String town_service_image_doc = "30"; // 乡级服务机构影像医生
		public final static String town_service_visit_doc = "31"; // 乡级服务机构随访员
		public final static String town_service_input_doc = "32"; // 乡级服务机构录入员
		public final static String pro_info_doc = "4"; // 省级信息员
		public final static String city_info_doc = "5"; // 市级信息员
		public final static String county_info_doc = "6"; // 县级信息员
		public final static String state_check_center = "7"; // 国家级临检中心
		public final static String pro_check_center = "8"; // 省级临检中心
		public final static String pro_service_mgr = "9"; // 省级服务机构管理员
		public final static String super_mgr = "99999999999"; // 超级管理员
	}

	// 签署情况
	interface DictInformedConsentSigned {
		public final static String female = "2"; // 女方签署
		public final static String male = "1"; // 男方签署
		public final static String all = "0"; // 双方签署
	}

	// 完成状态
	interface DictCompleteStatus {
		public final static String notfill = "0"; // 未填写
		public final static String notfinish = "1"; // 未完成
		public final static String finish = "2"; // 完成
	}

	// 是否
	interface DictIdentityFlag {
		public final static String yes = "1"; // 是
		public final static String no = "0"; // 否
	}

	// 有害因素
	interface DictHarmfulFactor {
		public final static String cat_dog = "1"; // 猫狗
		public final static String pesticides = "2"; // 农药
		public final static String radiation = "3"; // 放射线
		public final static String smoke = "4"; // 被动吸烟
		public final static String other = "5"; // 其他
	}

	// 停经后疾病类型
	interface DictPostmenopausalDisease {
		public final static String blood = "1"; // 阴道流血
		public final static String fever = "2"; // 发热38.5度以上
		public final static String diarrhea = "3"; // 腹泻
		public final static String abdominal_pain = "4"; // 腹痛
		public final static String cold = "5"; // 流行性感冒
		public final static String hepatitis = "6"; // 病毒性肝炎
		public final static String other = "7"; // 其他
	}

	// 妊娠服务机构类型
	interface DictPregnancyOrgStyle {
		public final static String county_health = "1"; // 县级以上医疗保健机构
		public final static String county_family_plan = "2"; // 县级以上计划生育服务机构
		public final static String town_health = "3"; // 乡镇卫生院
		public final static String town_family_plan = "4"; // 乡级计划生育服务机构
		public final static String other = "5"; // 其他机构
	}

	// 审核状态
	interface DictReportAuditStatus {
		public final static String unaudit = "0"; // 未审核
		public final static String pass = "1"; // 审核通过
		public final static String reject = "2"; // 驳回
		public final static String revoke = "3"; // 撤销审核
		public final static String abandon = "4"; // 放弃审核
	}

	// 审核状态
	interface DictAuditStatus {
		public final static String unaudit = "0"; // 未审核
		public final static String pass = "1"; // 通过
		public final static String reject = "2"; // 驳回
	}

	// 上报状态
	interface DictReportStatus {
		public final static String notfill = "1"; // 未上报
		public final static String notfinish = "2"; // 正常上报
		public final static String finish = "3"; // 逾期上报
	}

	// 项目值区间范围
	interface DictValueScope {
		public final static String containAll = "1"; // 不包括最小值和最大值
		public final static String containMin = "2"; // 包括最小值不包括最大值
		public final static String containMax = "3"; // 包括最大值不包括最小值
		public final static String notContainAll = "4"; // 包括最小值和最大值
	}

	// 血压范围
	interface DictBloodPressScope {
		public final static Integer systolic_blood_press_normal_max = 120; // 收缩压正常范围最大值
		public final static Integer systolic_blood_press_normal_min = 90; // 收缩压正常范围最小值
		public final static Integer diastolic_blood_press_normal_max = 80; // 舒张压正常范围最大值
		public final static Integer diastolic_blood_press_normal_min = 60; // 舒张压正常范围最小值
		public final static Integer systolic_blood_press_max = 1000; // 收缩压允许范围最大值
		public final static Integer systolic_blood_press_min = 0; // 收缩压允许范围最小值
		public final static Integer diastolic_blood_press_max = 1000; // 舒张压允许范围最大值
		public final static Integer diastolic_blood_press_min = 0; // 舒张压允许范围最小值
	}

	// servicr url
	interface ServiceUrl {
		public final static String dict_exam_range = "DictExamRangeService"; // 参数区间设置
		public final static String busi_plan_pregnan = "BusiPlanPregnanService"; // 计划怀孕夫妇
		public final static String archive_service = "ArchiveService"; // 档案
		public final static String stat_pregnancy_month_report_report = "StatPregnancyMonthReportReportService"; // 统计-妊娠月报相关统计（上报）
		public final static String stat_service_month_report_report = "StatServiceMonthReportReportService"; // 技术服务月报相关统计（上报）
	}

	// 早孕随访结果
	interface DictEarlyFollowUp {
		public final static String pregnant = "1"; // 已孕
		public final static String notpregnant = "2"; // 未孕
		public final static String lost = "3"; // 失访
	}

	// 胸透或者B超检查
	interface DictChestOrB {
		public final static String normal = "1"; // 正常
		public final static String notnormal = "0"; // 异常
		public final static String notsure = "2"; // 不能确定
		public final static String reject = "3"; // 拒绝检查
	}

	// 确诊早孕机构
	interface DictEarlyPregnancyDiagOrg {
		public final static String org = "1"; // 本机构
		public final static String other_org = "2"; // 转录其他机构确诊结果
		public final static String ohter = "3"; // 其他情况
	}

	// 档案类型
	interface DictArchiveStyle {
		public final static String exam = "1"; // 临床评估
		public final static String early_visit = "2"; // 早孕随访
		public final static String preg_visit = "3"; // 妊娠结局随访
	}

	// 逾期和即将逾期类型
	interface SetType {
		public final static String overdue = "overdue"; // 逾期时限
		public final static String overdue_remind = "overdueRemind"; // 逾期提醒时限
		public final static String overdue_report = "overdueReport"; // 报表时限
	}

	// 阴性阳性可疑
	interface DictNegativePositiveSuspicious {
		public final static String positive = "1"; // 阳性
		public final static String negative = "2"; // 阴性
		public final static String suspicious = "3"; // 可疑
	}

	// 三分类五分类标识
	interface DictThreeFiveFlag {
		public final static String all = "0"; // 通用
		public final static String three = "1"; // 三分类
		public final static String five = "2"; // 五分类
	}

	// 基因类型
	interface DictGeneType {
		public final static String no_deletion = "0"; // 无缺失
		public final static String heterozygous_mutation = "1"; // 杂合型突变
		public final static String homozygous_mutation = "2"; // 纯合型突变
	}

	// 正常异常状态
	interface DictNormalAbnormal {
		public final static String normal = "1"; // 正常
		public final static String abnormal = "0"; // 异常
	}

	// 异常未见异常
	interface DictAbnormalStatus {
		public final static String normal = "1"; // 未见异常
		public final static String abnormal = "0"; // 异常
	}

	// RH血型
	interface DictRhBloodGroup {
		public final static String positive = "1"; // 阳性
		public final static String negative = "2"; // 阴性
	}

	// 清洁度
	interface DictDegreeCleanliness {
		public final static String one = "0"; // Ⅰ
		public final static String two = "1"; // II
		public final static String three = "2"; // III
		public final static String four = "3"; // IV
	}

	// PH值
	interface DictPhValue {
		public final static String high = "1"; // >4.5
		public final static String low = "0"; // ≤4.5
	}

	// 无效有效
	interface DictInvalidValid {
		public final static String effective = "1"; // 有效
		public final static String invalid = "0"; // 无效
	}

	// 是否被动吸烟/喝酒
	interface DictDrinkFre {
		public final static String none = "0"; // 否
		public final static String occasionally = "1"; // 偶尔
		public final static String often = "2"; // 经常
	}

	// 服用叶酸及开始时间
	interface DictFolviteTime {
		public final static String none = "0"; // 未服用
		public final static String three = "1"; // 停经前至少3个月
		public final static String one = "2"; // 停经前1-2个月
		public final static String stop = "3"; // 停经后
	}

	// 妊娠结局随访结果
	interface DictPregnancyFollowupResults {
		public final static String brith = "1"; // 已分娩
		public final static String lost = "2"; // 失访
		public final static String not = "3"; // 未分娩
	}

	// 城镇农村标识
	interface DictTownVillage {
		public final static String town = "1"; // 城镇
		public final static String village = "2"; // 农村
	}

	// 申请状态
	interface DictApplyStatus {
		public final static String unaudit = "0"; // 未审核
		public final static String pass = "1"; // 审核通过
		public final static String reject = "2"; // 驳回
		public final static String notapply = "3"; // 未申请
	}

	// 证件类型
	interface DictCardType {
		public final static String resident_identity_card = "1"; // 居民身份证
		public final static String identity_card = "2"; // 港澳居民身份证
		public final static String residence_booklet = "3"; // 居民户口薄
		public final static String passport = "4"; // 护照
		public final static String officers_certificate = "5"; // 军官证
		public final static String driver_license = "6"; // 驾驶证
		public final static String other = "99"; // 其他证件
	}

	// 职业分类
	interface DictOccupationType {
		public final static String farmer = "1"; // 农民
		public final static String worker = "2"; // 工人
		public final static String service_industry = "3"; // 服务业
		public final static String business = "4"; // 经商
		public final static String housework = "5"; // 家务
		public final static String teacher = "6"; // 教师/公务员/职员等
		public final static String other = "7"; // 其他
	}

	// 时限类型
	interface DictTableType {
		public final static String busi_archive = "1"; // 基本信息 1
		public final static String busi_wife_general_situation = "2"; // 妻子一般情况表 2
		public final static String busi_husband_general_situation = "3"; // 丈夫一般情况表  3
		public final static String busi_wife_physical_exam = "4"; // 妻子一般体格检查表 4
		public final static String busi_husband_physical_exam = "5"; // 丈夫一般体格检查表 5
		public final static String busi_wife_genital_exam = "6"; // 妻子生殖系统表 6
		public final static String busi_husband_genital_exam = "7"; // 丈夫生殖系统检查表 7
		public final static String busi_exam_value_w = "8"; // 妻子临床检验表 8
		public final static String busi_exam_value_b = "9"; // 丈夫临床检验表 89
		public final static String busi_hemoglobin_wife = "10"; // 妻子血红蛋白分析表 10
		public final static String busi_hemoglobin_husband = "11"; // 丈夫血红蛋白分析表   11
		public final static String busi_gene_wife = "12"; // 妻子基因检测表 12
		public final static String busi_gene_husband = "13"; // 丈夫基因检测表 13
		public final static String busi_wife_b_value = "14"; // 妻子妇科B超检查表 14
		public final static String busi_evaluation_advice = "15"; // 评估建议告知书 15
		public final static String busi_early_visit = "16"; // 早孕随访 16
		public final static String busi_pregnancy_visit = "17"; // 妊娠结局随访 17
	}

	public final static String[] aDeletionArray = { "D010025", "D010026", "D010027" };
	public final static String[] aTransiliencArray = { "D010028", "D010029", "D010030" };
	public final static String[] aOtherArray = { "D010031" };

	public final static String[] bTransiliencArray = { "D010001", "D01003", "D010003", "D010004", "D010005", "D010006", "D010007", "D010012", "D010013",
			"D010018", "D010019", "D010024", "D010008", "D010011", "D010014", "D010017", "D010020", "D010023", "D010009", "D010010", "D010015", "D010016",
			"D010021", "D010022" };

	interface orgUnit {
		public final static String orgTown = "01"; // 乡级服务机构
		public final static String orgCounty = "02"; // 县级服务机构
	}
	
	interface platformType {
		public final static String channel = "1"; // 渠道
		public final static String agent = "2"; // 代理商
		public final static String facilitator = "3"; // 服务商
	}
	
	// 订单状态
	interface orderStatus {
		public final static String DICT_ORDER_STATUS_ZERO = "0"; // 未付款
		public final static String DICT_ORDER_STATUS_ONE = "1"; // 已付款
		public final static String DICT_ORDER_STATUS_TWO = "2"; // 申请退款
		public final static String DICT_ORDER_STATUS_THREE = "3"; // 退款中
		public final static String DICT_ORDER_STATUS_FOUR = "4"; // 已退款
		public final static String DICT_ORDER_STATUS_FIVE = "5"; // 已取消
		public final static String DICT_ORDER_STATUS_SIX = "6"; // 已关闭
		public final static String DICT_ORDER_STATUS_ALL = "9"; // 全部
	}
	// 商品性质
	interface goodsNature {
		public final static String DICT_GOODS_NATURE_ZERO = "0"; // 服务
		public final static String DICT_GOODS_NATURE_ONE = "1"; // 实体商品
	}
	// 商品类型
	interface goodsType {
		public final static String DICT_GOODS_TYPE_ZERO = "0"; // 医生服务
		public final static String DICT_GOODS_TYPE_ONE = "1"; // 基因类
		public final static String DICT_GOODS_TYPE_TWO = "2"; // 硬件类
	}
	// 商品时效性单位
	interface goodsValidityUnit {
		public final static String DICT_GOODS_VALIDITY_UNIT_ZERO = "0"; // 次
		public final static String DICT_GOODS_VALIDITY_UNIT_ONE = "1"; // 月
	}
	// 商品状态
	interface goodsStatus {
		public final static String DICT_GOODS_STATUS_ZERO = "0"; // 下架
		public final static String DICT_GOODS_STATUS_ONE = "1"; // 上架
	}
	//是否推荐
	interface isRecommend {
		public final static String DICT_ISRECOMMEND_ZERO = "0"; // 不推荐
		public final static String DICT_ISRECOMMEND_ONE = "1"; // 推荐
	}
	//是否最小分类
	interface isMinClass {
		public final static String DICT_ISMINCLASS_ZERO = "0"; // 不是
		public final static String DICT_ISMINCLASS_ONE = "1"; // 是
	}
	// 同意不同意
	interface agreeOrNot {
		public final static String DICT_AGREE_OR_NOT_ZERO = "0"; // 不同意
		public final static String DICT_AGREE_OR_NOT_ONE = "1"; // 同意
	}
	
	// 成功失败
	interface successOrFail {
		public final static String DICT_SUCCESS_OR_FAILURE_ZERO = "0"; // 失败
		public final static String DICT_SUCCESS_OR_FAILURE_ONE = "1"; // 成功
	}
	
	interface orgPlatformType {
		public final static String DICT_ORG_PLATFORM_TYPE_ONE = "1"; // 失败
		public final static String DICT_ORG_PLATFORM_TYPE_TWO = "2"; // 成功
	}
	
	interface serviceDistanceDate {
		public final static String DICT_DISTANCE_DATE_ONE = "1"; // 一个月内
		public final static String DICT_DISTANCE_DATE_TWO = "2"; // 二个月内
		public final static String DICT_DISTANCE_DATE_THREE = "3"; // 二个月内
		public final static String DICT_DISTANCE_DATE_FOUR = "4"; // 二个月内
	}
	
	
}