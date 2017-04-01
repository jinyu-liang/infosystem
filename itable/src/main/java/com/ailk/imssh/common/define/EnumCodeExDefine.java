package com.ailk.imssh.common.define;

/**
 * @Description:常量定义[原来的EnumCodeDefine中没有的，或者值不相同的，定义在此]
 * @author wangjt
 * @Date 2012-5-17
 */
public final class EnumCodeExDefine {

	public static final int DEFAULT_SPLIT_VALUE = -1;
	public static final int DEFAULT_SPLIT_DENOMINATOR = 100;
	public final static String[] USER_ID_BUSI_CODE={"160","180","10"};

	public static final int OPER_TYPE_INSERT = 1;
	public static final int OPER_TYPE_UPDATE = 2;
	public static final int OPER_TYPE_DELETE = 3;
	public static final int OPER_TYPE_DIFF = 8;

	public static final int DEAL_STS_SUCC = 1;// 处理成功状态
	public static final int DEAL_STS_FAIL = 2;// 处理失败状态

	public static final int ERROR_MSG_LENGTH = 255;// 索引错误表的错误信息长度

	/* I_GROUP相关的常量 wangyh3 2012-5-18 */
	public static final int ACCOUNT_OPERATOR_CMCC = 0; // 中国移动
	public static final int GROUP_DEFAULT_INGROUP_OUTGOINGCALL = 1;
	public static final int GROUP_DEFAULT_INGROUP_INCOMINGCALL = 1;
	public static final int GROUP_DEFAULT_CROSSGROUP_OUTGOINGCALL = 1;
	public static final int GROUP_DEFAULT_CROSSGROUP_INCOMINGCALL = 1;
	public static final int GROUP_DEFAULT_SPEC_OUTGOINGCALL = 1;
	public static final int GROUP_DEFAULT_SPEC_INCOMINGCALL = 1;
	public static final int GROUP_DEFAULT_OUTGROUP_OUTGOINGCALL = 1;
	public static final int GROUP_DEFAULT_OUTGROUP_INCOMINGCALL = 1;

	public static final int GROUP_CREDIT_SIGN_CONTROL_POST_PAID = 1;
	public static final int GROUP_DEFAULT_ACCOUNT_SEGMENT = 0;

	/* I_ACTIVITY相关的常量 wangyh3 2012-5-17 */
	public static final int ACTIVITY_NOCHANGE = 0; // 活动未变更
	public static final int ACTIVITY_CREATE = 1; // 活动受理
	public static final int ACTIVITY_CHANGE = 2; // 活动变更
	public static final int ACTIVITY_ROLLBACK = 3; // 活动回退
	public static final int ACTIVITY_CEASE = 4; // 活动中止
	public static final int ACTIVITY_PAUSE = 5; // 活动暂停
	public static final int ACTIVITY_RESUME = 6; // 活动恢复

	public static final short ACTIVITY_PAUSE_TYPE_NORMAL = 1; // 活动默认的暂停类型:
																// 1-普通暂停
	public static final short ACTIVITY_BOOK_TYPE_ACCT = 0; // 账户级账本
	public static final short ACTIVITY_BOOK_TYPE_USER = 1; // 用户级账本
	public static final int ACTIVITY_SCHEME_FEE_TRUE = 1; // 需要指定赠送额度
	public static final short ACTIVITY_NOT_BACK_FEE = 0; // 退费模式 - 非退现金
	public static final long ACTIVITY_DEFAULT_CEASE_COST = 0; // 默认的中止成本
	public static final short ACTIVITY_PREPAY_CHG_IGNORE = 0; // 忽略变更方式
	public static final short ACTIVITY_LAG_THIS_CYCLE = 0; // 迟延类型：0-本周期

	/* I_FEE相关的常量 wangyh3 2012-5-17 */
	public static final int FEE_TYPE_ONETIMEFEE = 1; // 一次性费用
	public static final int FEE_TYPE_POCKET = 2; // 押金
	public static final int FEE_TYPE_DEPOSIT = 3; // 预存款
	public static final int FEE_TYPE_FREERESOURCE = 4; // 一次性免费资源
	public static final int FEE_TYPE_TRANSFER = 5; // 预缴转普通预存

	public static final int POCKET_BILLING_TYPE_POSTPAY = 1; // 押金后付费
	public static final int POCKET_STS_VALID = 1; // 押金状态有效

	public static final int DEPOSIT_CHANNEL_ID = 3; // 充值的channelId

	public static final int CALL_INTERFACE_SUCCESS = 0; // 调用接口成功

	/* I_ACCOUNT相关的常量caohm5 2012-5-17 */
	// 账单语言,默认为英语
	public static final int BILL_DEFAULT_LANGUAGE = 2;
	// 打印账单
	public static final int BILL_PRINT_NO = 0;
	public static final int BILL_PRINT_YES = 1;
	// 帐户帐单类型0-IN : Normal Bill ,1-CC : Central Card,2-AM : Amex
	public static final int ACCOUNT_INVOICETYPE_NORMAL = 0;
	public static final int ACCOUNT_INVOICETYPE_CENTRALCARD = 1;
	public static final int ACCOUNT_INVOICETYPE_AMEX = 2;
	public static final int ACCOUNT_ORG_ID = 0;
	public static final int ACCOUNT_OPERATOR_ID = 0;
	// type of product object_type
	public static final int PROD_OBJECTTYPE_DEV = 0;
	public static final int PROD_OBJECTTYPE_ACCOUNT = 1;
	public static final int PROD_OBJECTTYPE_GCA = 2;
	// 是否设置免催免停
	public static final int DUNNING_AND_SUSPENDING = 0;// 非免催非免停
	public static final int IMMUNE_DUNNING_AND_SUSPENDING = 1;// 免催
	public static final int DUNNING_AND_IMMUNE_SUSPENDING = 2;// 免停
	public static final int IMMUNE_DUNNING_AND_IMMUNE_SUSPENDING = 3;// 免催免停
	// relation between cutomer and account
	public static final int ACCOUNT_RELATION_CUST_ATTRIBUTIVE = 0;
	// 强制账期表示
	public static final int CUSTOMER_FORCECYCLE_FALSE = 0;
	public static final int CUSTOMER_FORCECYCLE_TRUE = 1;
	// 账户出账标识
	public static final int BILL_STS_NOT_DEFINED = 0; // 默认不出账
	public static final int BILL_STS_ACTIVE = 1; // 出账
	public static final int BILL_STS_TERMINATE = 2;
	// 设置是免催免停业务还是通知设置
	public static final short EXEMPTCREDITLIMIT_BUSINESS = 0; // 免催免停业务
	public static final short SETNOTIFICATIONFLAG_BUSINESS = 1; // 通知设置业务

	/* I_ACCT_PAY_CHANNEL相关的常量caohm5 2012-5-17 */
	public static final int CA_BANK_FUN_STS_INACTIVE = 0;
	public static final int CA_BANK_FUN_STS_ACTIVE = 1;
	// asset type
	public static final long ACCOUNT_ASSETTYPE_FUND = 0;
	public static final long ACCOUNT_ASSETTYPE_CREDITLIMIT = 1;
	public static final long ACCOUNT_ASSETTYPE_BONUS = 2;
	public static final long ACCOUNT_ASSETTYPE_BANKCAPITAL = 3;
	public static final long ACCOUNT_ASSETTYPE_FREERESOURCE = 4;
	// card type for pay channel
	// 0- Common account
	// 1- Debit card
	public static final short CARD_TYPE_CREDIT = 0;
	public static final short CARD_TYPE_DEBIT = 1;
	// Payment Type:1、现金2、信用卡代扣3、银行托收5、银行卡绑定充值6、手机支付自动交话费7、代缴
	public static final int ACCOUNT_PAYTYPE_PHONE_AUTO = 6;
	public static final int ACCOUNT_PAYTYPE_CASH = 1;
	public static final int ACCOUNT_PAYTYPE_CREDIT_WITHHODING = 2;
	public static final int ACCOUNT_PAYTYPE_BANK_BINDGING = 5;
	public static final int ACCOUNT_PAYTYPE_BANK_COLLECTION = 3;
	public static final int ACCOUNT_PAYTYPE_PAYMENT = 7;

	/* I_USER_MONITOR相关的常量 zenglu 2012-5-30 */
	// region_code
	public static final int RESOURCE_REGION_CODE = 0;

	/* I_USER_RELATION相关的常量 zenglu 2012-08-01 */
	public static final int USER_RELATION_FRIEND_NUMBER = 1; // 亲情号码
	public static final int USER_RELATION_RAILCOM_NET_ACCTID = 2; // 铁通上网帐号
	public static final int USER_RELATION_RAILCOM_FIXED_NO = 3; // 铁通固定电话
	public static final int USER_RELATION_NET_PARTNER_VICE_NUMBER = 4; // 上网伴侣副号码
	public static final int USER_RELATION_EASY_CHANGE_VICE_NUMBER = 5; // 副号随意换副号码
	public static final int USER_RELATION_ONE_PAY_NUMBER = 13; // 一卡付费
	public static final int USER_RELATION_MANY_TERM = 15; // 多终端
	public static final int USER_RELATION_4G_SHARE = 16; // 4G共享
	public static final int USER_RELATION_MASO = 14; //MASO
	public static final int USER_RELATION_ONE_MANY_NUMBER = 29; //MASO

	// AD.ca_notification_exemption里的billing_type
	public static final int NOTIFY_EXEMPTION_BILLING_TYPE_PREPAID = 0;  // 预付费 
	public static final int NOTIFY_EXEMPTION_BILLING_TYPE_POSTPAID = 1; // 后付费
	public static final int NOTIFY_EXEMPTION_REC_REDUSER = 1; // 红名单用户
	public static final int NOTIFY_EXEMPTION_REC_NODEALUSER = 2; // 免处理用户
	
	// CD.IMS_USER_CTT里的identity_type枚举
	public static final int RESOURCE_IDENTITYTYPE_RAILCOM_NET_ACCTID = 1; // 1-铁通上网账号;
	public static final int RESOURCE_IDENTITYTYPE_RAILCOM_FIXED_NO = 2; // 2-铁通固话号码;
	// cm_res_identity里的identity_attr枚举
	public static final int RESOURCE_IDENTITYATTR_MAIN_NUMBER = 0; // 主号码
	public static final int RESOURCE_IDENTITYATTR_VICE_NUMBER = 1; // 副号码
	public static final int RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER = 2; // 上网伴侣副号码
	public static final int RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER = 3; // 副号随意换副号码

	/* I_ACCT_BILL_CYCLE相关的常量caohm5 2012-5-17 */
	// account bill cyle
	public static final short ACCOUNT_BILLCYCLE_ADD = 0;
	public static final short ACCOUNT_BILLCYCLE_MODIFY = 1;
	// 修改账期的类型
	public static final short SHORT_BILL_TYPE = 1;
	public static final short LONG_BILL_TYPE = 2;
	// 修改后账期是否延时
	public static final short EFFECTIVE_BY_CURRENT_CYCLE = 0;
	public static final short EFFECTIVE_BY_NEXT_CYCLE = 1;
	// 不发送短信
	public static final short BILL_CYCLE_NOT_NOTIFY = 0;
	/* I_CREDIT相关的常量caohm5 2012-5-17 */
	public static final short CREDIT_TYPE_ACCOUNT = 1;
	public static final short CREDIT_TYPE_USER = 2;
	public static final short CREDIT_TYPE_SERVICE = 3;
		
	public static final short USER_CONTACT = 0;// 用户联系人类型
	public static final short ACCT_CONTACT = 1;// 账户联系人类型
	public static final short CUST_CONTACT = 3;// 客户联系人类型

	// 操作员默认值
	public static final long OP_ID_DEFUALT = 100000;

	// 账户联系人信息表CM_CONTACT_MEDIUM中party_role_id值
	public static final short CM_CONTACT_MEDIUM_PARTY_ROLE_ID = 0;
	// split 分账类型
	public static final int SPLIT_TYPE_ITEM = 0;// 科目级代付
	public static final int SPLIT_TYPE_PRODUCT = 1; // 产品级代付

	public static final int SPLIT_TYPE_AMOUNT = 1;// 按金额分账
	public static final int SPLIT_TYPE_NUMERATOR = 2;// 按比例分账

	public static final int SPLIT_PAY_METHOD_HOME = 1;

	// 用户账户关系表的chg_flag字段定义

	/**
	 * 新增用户账户关系时，chg_flag为0
	 */
	public static final int CHG_FLAG_NORMAL = 0;
	/**
	 * 修改时，老的用户账户关系标识为1
	 */
	public static final int CHG_FLAG_OLD = 1;
	/**
	 * 修改时，新的用户账户关系标识为10
	 */
	public static final int CHG_FLAG_NEW = 10;

	// 调账管接口的默认参数
	public static final int ORG_ID_DEFAULT = 10000;
	public static final int REGION_CODE_DEFAULT = 22;
	public static final int BUSI_CODE_DEFAULT = 0;
	public static final int CHANNEL_ID_DEFAULT = 0;
	public static final int PROV_CODE_DEFAULT = 210;
	public static final int COUNTRY_CODE_DEFAULT = 2100;
	public static final short COMMON_NO_NOTIFY = 0;
	public static final int OPERATOR_ID = 0;

	// 终端imei绑定的枚举
	public static final int IDENTITY_IMEI_UNBINDING = 0;
	public static final int IDENTITY_IMEI_BINDING = 1;

	// 用户停机位（20位）初始化位串
	public static String INIT_STS_DTL = "00000000000000000000";

	// 货币单位
	public static final int MEASURE_ID_LI = 10402;// 厘
	public static final int MEASURE_ID_FEN = 10403;// 分

	// 工单映射外围系统类型
	public static String SN_MAPPING_MODE_CRM_DEPOSIT = "001"; // 营业过来的充值
	public static String SN_MAPPING_MODE_CRM_ONETIMEFEE = "002"; // 营业过来的一次性费用
	public static String SN_MAPPING_MODE_CRM_TRANSFER = "003"; // 营业过来的资金转移

	public static final short ENVOPROD_EXPIRE_IMMEDIATELY = 0;
	public static final short ENVOPROD_EXPIRE_DELAY = 1;

	// 信息服务开关相关
	// 全量服务 为0，表示有多个销售品
	public static final int USER_SWITCH_COMMON_SERV_CODE = 0;
	public static final short USER_SWITCH_OPEN = 1;
	public static final short USER_SWITCH_CLOSE = 2;

	// 用户有效期，有效无效
	public static final int USER_VALIDITY_FLAG_VALID = 1;
	public static final int USER_VALIDITY_FLAG_UNVALID = 0;

	// 用户有效期天数
	public static final int USER_VALIDITY_LENTH = 730;

	public static final int USER_VALIDITY_IS_ECARD = 1;

	public static final long ACCT_PROD_ID = -1L;
	// 信用等级默认值
	public static final int CREDIT_LEVLE_DEFAULT_VALUE = 100;

	// 产品状态-机卡分离暂停
	public static final int PRODUCT_STS_SUSPEND = 2;

	// 新增二次议价参数：机卡分离暂停标示
	public static final int PRICE_PARAM_ID_RC_SUSPEND_PRODUCT = 810401;
	// 免费资源赠送method
	public static final int FREE_RESOURCE_METHOD = 2;
	// 时间 分的度量单位
	public static final short MEASURE_ID_MINUTE = 106;

	// 账户表中credit_sign_control字段枚举
	public static final short PREPAID = 0;
	public static final short POSTPAID = 1;
	public static final short BROADBAND = 9;

	public static final long MDB_COMMIT_FAIL = 1048593;
	public static final long SYNC_MDB_ERR = 28888;
	public static final int PROD_PARAM_COUNT = 800332;

	// 账户类型
	public static final int ACCOUNT_TYPE_103 = 103;

	public static final long ALL_DELETE_BUSI_CODE = 11111111111L;
	
	//异常接口处理常量 busi_code = 9,oper_method=8
	public static final long DELETE_BUSI_CODE_8 = 999L;
	
	public static final long ACTIVE_BUSI_CODE1 = 191000000109L;
	public static final long ACTIVE_BUSI_CODE2 = 191000000139L;
	public static final long ACTIVE_BUSI_CODE_IVR = 191000000923L;
	public static final long ACTIVE_BUSI_CODE_TAOOKA = 191000000109L;

	public static final int GX_MANUAL_MODIFY_PROD = 1152;

	public static final long USER_SEG_BUSI_CODE = -9999L;
	public static final long CUST_SEG_BUSI_CODE = -8888L;
	
	public static final int FORMAT_ID_GROUP = 101;
	public static final int FORMAT_ID_NORMAL = 100;

	public static final int PCC_SLA = 221;
	public static final int PCC_SLA_VALUE = 22101;
	// PCC
	public static final int PCC_ORDER_FLAG_ADD = 1;
	public static final int PCC_ORDER_FLAG_DELETE = 2;
	public static final int PCC_ORDER_FLAG_CHANGE = 3;

	// split route type
	public static final short PAY_RELATION_ROUTE_ACCT_USER = 2; // 账户代付用户
	public static final short PAY_RELATION_ROUTE_USER_USER = 1;// 用户代付用户
	public static final short PAY_RELATION_ROUTE_ACCT_ACCT = 3;// 账户代付账户
	public static final short PAY_RELATION_ROUTE_USER_GROUP = 4;// 用户代付群
	public static final short PAY_RELATION_ROUTE_USER_RRUSER = 5;// 用户代付主用户

	public static final int PRICE_PARAM_KEY_820205 = 820205;

	public static final int USER_ORDER_STS_0 = 0;
	public static final int USER_ORDER_STS_1 = 1;
	public static final int USER_ORDER_STS_2 = 2;
	public static final int USER_ORDER_STS_3 = 3;
	public static final int USER_ORDER_STS_4 = 4;
	public static final int USER_ORDER_STS_5 = 5;

	// 路由手工上发相应枚举位
	public static final int SYS_RT_ACCOUNT = 62;
	public static final int SYS_RT_CUSTOMER = 63;
	public static final int SYS_RT_IDENTITY = 61;
	public static final int SYS_RT_RESOURCE = 60;
	public static final int SYS_RT_INDUSTRY = 59;

	// 过户资产迁移
	public static final int DEAL_CHANGE_OWN_ASSET = 58;
	public static final int SYS_RT_PHONE = 57;
	public static final int SYS_RT_IMSI = 56;

	public static final int SYNC_MDB_BY_ACCT = 55;
	public static final int SYNC_ROUTE_INDUSTRY = 54;

	public static final long ERROR_THREAD_BUSI_VALUE = -9999L;

	public static final int ACCT_OWE_DONE_STS = 1;

	public static final int DEPOSIT_PROM_TYPE_PRINCIPAL_PROM = 1;  // 本金优惠促销
	public static final int DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL = 3; // 特殊本金产品
	public static final int DEPOSIT_PROM_TYPE_COMMON_PRINCIPAL = 4; // 普通本金
	public static final int DEPOSIT_PROM_TYPE_REWARD_PROM = 2; // 赠金优惠促销
	public static final int DEPOSIT_PROM_TYPE_REWARD_MONEY = 5; //普通赠金
	
	public static final int PRICE_PARAM_CUR_AMOUNT = 820006;
	public static final int PRICE_PARAM_REMAIN_ACCOUNT = 820007;
	public static final int PRICE_PARAM_BIND_DATE = 820008;
	
	public static final int DEPOSIT_DEV_STS_0 = 0;
	public static final int DEPOSIT_DEV_STS_1 = 1;
	
	public static final int GROUP_TYPE_SHARE_PROD = 222;
	
	// i_product.product_type 程控产品 399 (江西)
	public static final int PRODUCT_TYPE_PROGRAMCONTROL = 399;
	
	public static final int REMIND_OFFERING_XZ_90004091 = 90004091;
	public static final int REMIND_OFFERING_XZ_90004092 = 90004092;
	
	public static final int ABM_TRANSFER_TYPE_USER = 0;
	public static final int ABM_TRANSFER_TYPE_ACCT = 1;
	
	public static final int APS_TRANSFER_TYPE_IMME = 1;
	public static final int APS_TRANSFER_TYPE_NEXT = 0;
	
	public static final int REOPEN_TYPE_GUOMAN = 0; //国漫主动重开类型
	public static final int REOPEN_TYPE_15G = 1; //15G封顶主动重开类型
	public static final int REOPEN_OFFER_ID_GUOMAN_HN = 487002108; //国漫主动重开产品
	public static final int REOPEN_OFFER_ID_15G_HN = 487002150; //15G封顶主动重开产品
	
	// 信用服务类型
	public static final int CREDIT_SERV_7901 = 7901; //7901:欠费额度             
	public static final int CREDIT_SERV_7902 = 7902; //7902:单停额度             
	public static final int CREDIT_SERV_7903 = 7903; //7903:专属额度             
	public static final int CREDIT_SERV_7904 = 7904; //7904:节假日临时额度       
	public static final int CREDIT_SERV_7905 = 7905; //7905:国际漫游临时额度     
	public static final int CREDIT_SERV_7906 = 7906; //7906:紧急开机额度         
	public static final int CREDIT_SERV_7907 = 7907; //7907:担保开机额度         
	public static final int CREDIT_SERV_7908 = 7908; //7908:特殊透支额度 (不同步)
	public static final int CREDIT_SERV_7909 = 7909; //7909:低额提醒阀值         
	public static final int CREDIT_SERV_7912 = 7912; //7912:固定额度             
	public static final int CREDIT_SERV_7914 = 7914; //7914:春节服务         
	
	//ENTERPRISE SERVICE_CODE取值判断
	public static final String SERVICE_CODE_COND_119 = "119";
	public static final String SERVICE_CODE_COND_MHN = "mhn";
	
	//ENTERPRISE OPERATOR_CODE长度判断
	public static final int OPERATOR_CODE_LENGETH_3 = 3;
	public static final int OPERATOR_CODE_LENGETH_10 = 10;
	
	//ENTERPRISE SERVER_CODE长度判断
	public static final int SERVER_CODE_LENGTH_12 = 12;
	public static final int SERVER_CODE_LENGTH_24 = 24;
	
	public static final int TJ_REGION_CODE = 22;
	/**2016-09-06  id_type=1*/
	public static final int CM_ROUTE_IDENTITY_ID_TYPE = 1;
	
	public static final int USER_SERVICE_201306 = 201306;
	public static final int USER_SERVICE_2011 = 2011;
	public static final int USER_SERVICE_2010 = 2010;
	public static final int PARAM_ID_30321 = 30321;
	public static final int PARAM_ID_30320 = 30320;
	
	public static final String PARAM_VALUE_30321_10 = "ARID30321_HAVE_10Y:1:10000";
	public static final String PARAM_VALUE_30321_100 = "ARID30321_HAVE_100Y:1:100000";
	public static final String PARAM_VALUE_30321_30= "ARID30321_HAVE_30Y:1:30000";
	public static final String PARAM_VALUE_30321_5= "ARID30321_HAVE_5Y:1:5000";
	public static final String PARAM_VALUE_30321_0= "ARID30321_NO_0Y:1:0";
	
	public static final String PARAM_VALUE_30320_10 = "ARID30320_RP_10per:2:10";
	public static final String PARAM_VALUE_30320_25 = "ARID30320_RP_25per:2:25";
	public static final String PARAM_VALUE_30320_50= " ARID30320_RP_50per:2:50";
	public static final String PARAM_VALUE_30320_0= "ARID30320_RP_0per:2:0";
	public static final int ACCT_PARAM_ID=630000;
	public static final int C_USER_VERSION = 33;
	public static final int USER_SWITCH_ERROR_OFFERID=99999;

	
}