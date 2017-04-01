package com.ailk.ims.define;


/**
 * 枚举值编码定义 @Description: (这里用一句话描述这个类的作用)
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2 2011-7-27 lijc3 add USER_MANAGE_FN_MODIFY <br>
 *       2011-08-02 wuyujie : 新增CONTACT_TYPE_* <br>
 *       2011-08-04 zengxr card type for pay channel<br>
 *       2012-03-19 //预扣产品周期内退订是否退费 ljc public static final int
 *       PRODUCT_DEL_IS_REFUND_YES=1; public static final int
 *       PRODUCT_DEL_IS_REFUND_NO=0; 2012-04-02 zengxr ACTION_SPEC_ID_KEY get
 *       right busi spec of OneTimeFee for multi spec in one
 *       interface
 * @Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
 * @Date 2012-8-14 sunpf3 :新增ADJUST_SECOND_POCKET（调整赠送账本的类型）
 */
public class EnumCodeDefine {
	// *************************以下定义公用的枚举值*******************************************
	public static final int COMMON_MODULE_UNIVERSAL = 1;
	public static final int COMMON_MODULE_RATING = 2;
	public static final int COMMON_MODULE_PROD = 3;
	public static final int COMMON_MODULE_ACCT = 4;
	public static final int COMMON_MODULE_ACCTMGNT = 5;
	public static final int COMMON_MODULE_IMS = 6;

	public static final int STS_ACTIVE = 1;// 有效数据的状态值
	public static final int STS_INVALID = 0;// 无效数据的状态值

	public static final short OPER_TYPE_ADD = 0;
	public static final short OPER_TYPE_DELETE = 1;
	public static final short OPER_TYPE_MODIFY = 2;

	public static final short NOT_SEND_NOTIFICATION = 0;
	public static final short SEND_NOTIFICATION = 1;

	public static final short DEFAULT_NULL_VALUE = -999;

	// @Date 2012-09-28 yugb : 是否收取一次性费用标志
	public static final short CHARGE_ONE_TIME_FEE = 1;
	public static final short NO_CHARGE_ONE_TIME_FEE = 0;

	// ########################以上定义公用的枚举值###########################################

	// ########################以下定义业务接口bean枚举值,取值来源于bean.properties###########################################
	public static final int NEW_REGISTRATION = 1001;
	public static final int FIRST_ACTIVE_BY_CRM = 1009;
	public static final int MODIFY_CUST_INFO_BUSI_BEAN = 1010;
	public static final int MODIFY_ACCT_BASIC_INFO_BUSI_BEAN = 1012;
	public static final int CHANGE_OWNER = 1013;
	public static final int CREAT_GROUP = 1034;
	public static final int FIRST_ACTIVE_4_TS_BUSI_BEAN = 9006;
	public static final int FIRST_ACTIVE_4_XDR_BUSI_BEAN = 9002;
	public static final int FIRST_ACTIVE_BY_CRM_4_SH = 7023;
	public static final int MANAGE_SINGLE_BALANCE_BUSI_BEAN = 1063;
	public static final int MANAGE_SINGLE_BALANCEMBR_BUSI_BEAN = 1062;
	public static final int CHG_RESSER_CYCLE_SPEC_ID = 9027001;
	public static final int CHANGE_MAIN_PROM_BUSI_BEAN = 1022;
	public static final int CHANGE_PAYMODE_BUSI_BEAN = 1053;
	public static final int DEPOSIT_BY_CASH_BEAN = 7060;
	// ########################以上定义业务接口bean枚举值,取值来源于bean.properties###########################################

	// *************************以下定义Party相关的枚举值*******************************************
	public static final int PARTY_TYPE_INDIVIDUAL = 0;
	public static final int PARTY_TYPE_ORGANIZATION = 1;

	public static final int PARTY_STS_VALID = 1;// cm_party sts ： 有效

	public static final int PARTY_ROLETYPE_CUSTOMER = 0;
	public static final int PARTY_ROLETYPE_EMPLOYEE = 1;
	public static final int PARTY_ROLETYPE_PARTNER = 2;

	public static final int PARTY_ROLESTS_EFFECTIVE = 0;
	public static final int PARTY_ROLETYPE_EXPECTED = 1;
	public static final int PARTY_ROLETYPE_INVALID = 2;

	public static final int CONTACT_TYPE_CUSTADDRESS = 1;
	// public static final int CONTACT_TYPE_VATADDRESS = 2;
	public static final int CONTACT_TYPE_GROUPMAILADDRESS = 3;
	public static final int CONTACT_TYPE_ACCOUNTADDRESS = 4;
	public static final int CONTACT_TYPE_BILLINGADDRESS_SUMMARY = 5;
	public static final int CONTACT_TYPE_COPYBILLING_ADDRESS = 6;
	public static final int CONTACT_TYPE_BILLINGADDRESS_DETAIL = 7;
	public static final int CONTACT_TYPE_ESTATEMENT = 8;

	// ########################以上定义Party相关的枚举值###########################################

	// *************************以下定义Customer相关的枚举值*******************************************
	public static final int CUSTOMER_TITLE_MRS = 1;
	public static final int CUSTOMER_TITLE_MR = 2;

	// 客户类型
	public static final int CUSTOMER_CLASS_BUSINESS = 1;
	public static final int CUSTOMER_CLASS_GOVERNMENT = 2;
	public static final int CUSTOMER_CLASS_EXCLUSIVE = 3;
	public static final int CUSTOMER_CLASS_INHOUSE = 4;
	public static final int CUSTOMER_CLASS_RESIDENTIAL = 5;

	public static final int CUSTOMER_STS_UNKNOW = 0;
	public static final int CUSTOMER_STS_ACTIVE = 1;
	public static final int CUSTOMER_STS_NOTACTIVE = 2;
	public static final int CUSTOMER_STS_STOP = 3;
	public static final int CUSTOMER_STS_HANGUP = 4;

	// 强制账期表示
	public static final int CUSTOMER_FORCECYCLE_FALSE = 0;
	public static final int CUSTOMER_FORCECYCLE_TRUE = 1;
	// 取父帐户的账期编号的帐户强制账期标志
	public static final int FORCECYCLE_BY_PARENT_ACCOUNT = 2;
	// 取其管理的客户的账期编号的帐户强制账期标志
	public static final int FORCECYCLE_BY_CUSTOMER = 3;

	// modify customer basic infomation - contact mediun - oper_type
	public static final int MODIFY_CUSTOMER_CONTACT_MEDIUM_ADD = 0;
	public static final int MODIFY_CUSTOMER_CONTACT_MEDIUM_DELETE = 1;
	public static final int MODIFY_CUSTOMER_CONTACT_MEDIUM_MODIFY = 2;
	// 客户类型子类
	public static final int CUSTOMER_TYPE_KEY = 1;
	public static final int CUSTOMER_TYPE_SME = 2;
	public static final int CUSTOMER_TYPE_TOT = 3;
	public static final int CUSTOMER_TYPE_ROY = 4;
	public static final int CUSTOMER_TYPE_GOV = 5;
	public static final int CUSTOMER_TYPE_EMB = 6;
	public static final int CUSTOMER_TYPE_STA = 7;
	public static final int CUSTOMER_TYPE_NON = 8;
	public static final int CUSTOMER_TYPE_PRE = 9;
	public static final int CUSTOMER_TYPE_AIS = 10;
	public static final int CUSTOMER_TYPE_THA = 11;
	public static final int CUSTOMER_TYPE_FOR = 12;
	// 客户country_id
	public static final int CUSTOMER_DEFAULT_COUNTRY_ID = 86; // 0表示对所有的县区有效
	// 客户地市代码region_code
	public static final int CUSTOMER_DEFAULT_REGION_CODE = 0;
	// 客户省代码
	public static final int CUSTOMER_DEFAULT_PROV_CODE = 0;
	// 联系信息类型
	public static final int DEV_CONTACT_TYPE = 0; // 用户联系信息
	public static final int ACCT_CONTACT_TYPE = 1; // 帐户联系信息
	public static final int GROUP_CONTACT_TYPE = 2; // 群联系信息
	public static final int CUST_CONTACT_TYPE = 3; // 客户联系信息
	public static final int COMP_CONTACT_TYPE = 9; // 公司联系信息
	
	public static final int CONTACT_CUST = 3;
	public static final int CONTACT_FGPRELAPHONE = 4;
	public static final int CONTACT_GRUA = 2;
	public static final int CONTACT_GROUP = 1;
	

	// 个人客户性别
	// public static final int CUSTOMER_DEFAULT_GENDER = 0; //默认未知性别
	// ########################以上定义Customer相关的枚举值###########################################

	// *************************以下定义Account相关的枚举值*******************************************
	// relationship between account and resource
	public static final int ACCOUNT_RELATION_RES_BELONGING = 0;
	// public static final int ACCOUNT_RELATION_RES_BILLABLE = 2;
	public static final int ACCOUNT_RELATION_RES_MAINRESOURCE = 3;
	// 集团成员
	public static final int ACCOUNT_RELATION_RV_HIERARCHY = 0;
	// 自动群成员
	public static final int ACCOUNT_RELATION_RV_AUTOGROUP = 1;
	// 群成员
	public static final int ACCOUNT_RELATION_RV_GROUP = 2;
	// 特殊号码
	public static final int ACCOUNT_RELATION_RV_SPECIALNBR = 3;

	// account bill cyle
	public static final short ACCOUNT_BILLCYCLE_ADD = 0;
	public static final short ACCOUNT_BILLCYCLE_MODIFY = 1;

	// （short isBillRun 0-表示出账，1-表示不出账）
	public static final short ACCT_BILL_RUN = 0;
	public static final short ACCT_BILL_UNRUN = 1;

	// payment mode hybridRule
	public static final short PAYMODE_HYBRID_RULE_SELECTIVE = 1;
	public static final short PAYMODE_HYBRID_RULE_CREDIT_LIMIT = 2;
	public static final short PAYMODE_HYBRID_RULE_THRESHOLD = 3;
	// payment mode last_term_flag
	public static final short PAYMODE_LAST_TERM_N = 0;
	public static final short PAYMODE_LAST_TERM_Y = 1;
	// payment mode convert_flag
	public static final short PAYMODE_CONVERT_FLAG_BALANCE = 1;
	public static final short PAYMODE_CONVERT_FLAG_EXPRESS_PAYMENT = 2;
	public static final short PAYMODE_CONVERT_FLAG_ALL = 3;

	// type of account
	public static final short ACCOUNT_CLASS_CA = 1;
	public static final short ACCOUNT_CLASS_GCA = 2;
	// public static final short ACCOUNT_TYPE_INDIVIDUAL = 101;
	// public static final short ACCOUNT_TYPE_FAMILY = 102;
	// public static final short ACCOUNT_TYPE_GROUP = 103;
	public static final short ACCOUNT_TYPE_VPN = 201;
	// public static final short ACCOUNT_TYPE_CORPORATION = 202;
	public static final short ACCOUNT_TYPE_COMMUNITY = 203;
	public static final short ACCOUNT_TYPE_VIRTUAL_FAMILY = 204;
	public static final short ACCOUNT_TYPE_CUG = 205;
	public static final short ACCOUNT_TYPE_VPBX = 206;
	public static final short ACCOUNT_TYPE_SPECNBR_GCA = 207;
	public static final short ACCOUNT_TYPE_SURF_E_FAMILY = 208; // 冲浪e家亲活动群
	public static final short ACCOUNT_TYPE_SIX_SIX_FAMILY_PACKAGE = 209; // 66家庭包活动群
	public static final short ACCOUNT_TYPE_AUTO_GROUP = 222; // 自动群

	// relationship between CAs(GCA)
	public static final int ACCOUNT_BELONG = 0;
	public static final int ACCOUNT_GCA_RELATION = 1;
	public static final int ACCOUNT_CA_GCA_RELATION = 2;
	public static final int ACCOUNT_VPN_SPECNBR_GCA_RELATION = 3;

	// 帐户帐单类型0-IN : Normal Bill ,1-CC : Central Card,2-AM : Amex
	public static final int ACCOUNT_INVOICETYPE_NORMAL = 0;
	public static final int ACCOUNT_INVOICETYPE_CENTRALCARD = 1;
	public static final int ACCOUNT_INVOICETYPE_AMEX = 2;

	// ca relation oper_type
	public static final short ACCOUNT_RELATION_OPER_ADD = 0;
	public static final short ACCOUNT_RELATION_OPER_DEL = 1;
	public static final short ACCOUNT_RELATION_OPER_MOD = 2;

	// relation between cutomer and account
	public static final int ACCOUNT_RELATION_CUST_ATTRIBUTIVE = 0;

	// asset type
	public static final int ACCOUNT_ASSETTYPE_FUND = 0;
	public static final int ACCOUNT_ASSETTYPE_CREDITLIMIT = 1;
	public static final int ACCOUNT_ASSETTYPE_BONUS = 2;
	public static final int ACCOUNT_ASSETTYPE_BANKCAPITAL = 3;
	public static final int ACCOUNT_ASSETTYPE_FREERESOURCE = 4;

	public static final int ITEM_TYPE_CASH = 2;

	// Payment Type
	public static final int ACCOUNT_PAYTYPE_CREDIT = 8;
	public static final int ACCOUNT_PAYTYPE_CASH = 2;
	public static final int ACCOUNT_PAYTYPE_DEBIT = 3;
	public static final int ACCOUNT_PAYTYPE_EBPP = 81;

	// 调用账管接口doPayFee payment Type
	public static final short PAYMENT_TYPE_CASH = 2;
	public static final short PAYMENT_TYPE_CREDITCARDDEBIT = 8;
	public static final short PAYMENT_TYPE_BACKDEBIT = 3;
	public static final short PAYMENT_TYPE_EBPP = 81;

	// account status
	// 失效
	public static final int ACCOUNT_TERMINATE = 0;
	// 正常
	public static final int ACCOUNT_ACTIVE = 1;
	// 挂机
	public static final int ACCOUNT_SUSPEND = 2;
	public static final int ACCOUNT_RERATING = 3;

	public static final int ACCOUNT_RERATING_FLAG_TRUE = 1;
	public static final int ACCOUNT_RERATING_FLAG_FALSE = 0;

	// account TITLE_ROLE_ID
	public static final int ACCOUNT_ORDINARY_GROUP_ROLE = 1000;

	// adjust blance type
	public static final short ADJUST_BALANCE = 1;
	public static final short ADJUST_VALIDITY = 2;
	public static final short ADJUST_BALANCE_AND_VALIDITY = 3;
	public static final short ADJUST_FREE_RESOURCE = 4;
	public static final short ADJUST_FREE_RESOURCE_AND_VALIDITY = 5;
	public static final short ADJUST_SECOND_POCKET = 6;

	// adjust rtner
	public static final short ADJUST_DEDUCTING_ALL_FREERES = 0;
	public static final short ADJUST_NOT_DEDUCTING = 1;
	public static final short ADJUST_DEDUCT_TO_NEGATIVE = 2;

	// query work log
	public static final short QUERY_WORK_LOG_TYPE_ALL = 0;
	public static final short QUERY_WORK_LOG_TYPE_RECHAGE = 1;
	public static final short QUERY_WORK_LOG_TYPE_ADJUST = 2;
	public static final short QUERY_WORK_LOG_TYPE_TRANSFER = 3;
	public static final short QUERY_WORK_LOG_TYPE_PAYMENT = 4;
	public static final short QUERY_WORK_LOG_TYPE_NEGATIVE_BAL = 5;
	public static final short QUERY_WORK_LOG_TYPE_FREESOURCE = 6;

	// contact type
	public static final int ACCOUNT_CONTACT_TYPE_NORMALADDRESS = 0;

	// ca_account_type chg_flag
	public static final int ACCOUNT_CHG_FLAG_NORMAL = 0;
	public static final int ACCOUNT_CHG_FLAG_CHANGEACCOUNT = 1;
	public static final int ACCOUNT_CHG_FLAG_CHANGEOWNER = 2;

	// 转移累积量标志位
	public static final short TRANS_ACCUMLATION_FLAG_TRUE = 0;// 0或者null为直接删除累积量
	public static final short TRANS_ACCUMLATION_FLAG_FALSE = 1; // 1代表转移
	public static final short TRANS_USER_ACCUMLATION = 0;// 转移用户级累积量
	public static final short TRANS_ACCOUNT_ACCUMLATION = 1;// 转移账户级累积量
	public static final short TRANS_ACCUMLATION_COPY = 2;// 转移累积量
	public static final short TRANS_ACCUMLATION_NOT_COPY = 1;// 不转移累积量

	// 账户组织代码
	public static final int ACCOUNT_DEFAULT_ORG_ID = 0;// 默认为0
	// 帐户省份代码
	public static final int ACCOUNT_DEFAULT_PROVINCE_ID = 0;// 默认为0
	// 帐户地市代码
	public static final int ACCOUNT_DEFAULT_REGION_CODE = 0;// 默认为0
	// 帐户国家代码
	public static final int ACCOUNT_DEFAULT_COUNTY_CODE = 0;// 默认为0
	// 账户段
	public static final int ACCOUNT_DEFAULT_ACCOUNT_SEGMENT = 0;// 默认为0-Non
	// 账户操作员
	public static final int ACCOUNT_DEFAULT_OPERATOR_ID = 0;// 默认为0
	// 账户账本类型
	public static final int ACCOUNT_DEFAULT_BALANCE_TYPE = 0;
	// 账户出账标识
	public static final int BILL_STS_NOT_DEFINED = 0; // 默认不出账
	public static final int BILL_STS_ACTIVE = 1; // 出账
	public static final int BILL_STS_TERMINATE = 2;

	// 创建账期时帐管短信通知标识
	public static final short BILL_CYCLE_NOTIFY = 1; // 发送短信

	public static final short BILL_CYCLE_NOT_NOTIFY = 0;// 不发送短信

	// 是否变更账期标识
	public static final short BILL_CYCLE_MODIFY = 0;// 修改账期

	public static final short BILL_CYCLE_NOT_MODIFY = 1;// 不修改账期，沿用以前账期

	// 账期类型定义
	public static final int ACCT_BILL_CYCLE_DEFAULT = 0; // 默认账期
	public static final int ACCT_BILL_CYCLE_DAY = 1; // 按天出账
	public static final int ACCT_BILL_CYCLE_WEEK = 2; // 按周出账
	public static final int ACCT_BILL_CYCLE_MONTH = 5; // 按月出账
	public static final int ACCT_BILL_CYCLE_YEAR = 4; // 按年出账

	public static final short ACCOUNT_VALIDITY_TYPE_USER = 0;
	public static final short ACCOUNT_VALIDITY_TYPE_SINGLE_BALANCE = 1;
	public static final short ACCOUNT_VALIDITY_TYPE_BOTH = 2;
	// 表示查当前账期
	public static final int ACCT_CURRENT_BILL_CYCLE = 0;
	// 表示查询下个账期
	public static final int ACCT_NEXT_BILL_CYCLE = 1;
	// 调用帐管接口时查询账户下个账期开始日时，表示以最开始挂起那个月的账期作为当前账期
	public static final short SUSPEND_MONTH_START = 1;
	// 调用帐管接口时查询账户下个账期开始日时，表示以当前时间开始
	public static final short CURRENT_TIME_START = 0;

	// 是否查询预付费的账期规格标号
	public static final short QUERY_NOT_DEFINE_CYLCE = 1;

	// 默认短账期配置
	public static final long SHORT_CYCLE_SPECID = 99;
	// 长账期配置
	public static final long LONG_CYCLE_SPECID = 100;

	// email类型
	public static final int USER_EMAIL_ADDRESS = 0; // 帐户的email
	public static final int ACCT_EMAIL_ADDRESS = 1; // 用户的email

	// ########################以上定义Account相关的枚举值###########################################

	// *************************以下定义Resource相关的枚举值*******************************************
	// payment mode of credit
	public static final int USER_PAYMODE_PREPAID = 0;
	public static final int USER_PAYMODE_POSTPAID = 1;
	public static final int USER_PAYMODE_HYBRID = 2;// 输入参数中使用
	public static final int USER_PAYMODE_HYBRID_PREPAID = 10;// 预付费为主，对应参数中2
	public static final int USER_PAYMODE_HYBRID_POSTPAID = 11;// 后付费为主，对应参数中2

	// RESOURCE IDENTITY TYPE
	public static final int RESOURCE_IDENTITYTYPE_PHONE = 0;
	// public static final int RESOURCE_IDENTITYTYPE_IMSI = 1;
	public static final int RESOURCE_IDENTITYTYPE_FIXEDNO = 2;
	public static final int RESOURCE_IDENTITYTYPE_BROADBANDACCTID = 3;
	public static final int RESOURCE_IDENTITYTYPE_WLANACCTID = 4;
	public static final int RESOURCE_IDENTITYTYPE_FAXNUMBER = 5;
	public static final int RESOURCE_IDENTITYTYPE_MULTI_SIM = 6;

	// cm_res_identity里的identity_attr枚举
	public static final int RESOURCE_IDENTITYATTR_MAIN_NUMBER = 0; // 主号码
	public static final int RESOURCE_IDENTITYATTR_VICE_NUMBER = 1; // 副号码
	public static final int RESOURCE_IDENTITYATTR_NET_PARTNER_VICE_NUMBER = 2; // 上网伴侣副号码
	public static final int RESOURCE_IDENTITYATTR_EASY_CHANGE_VICE_NUMBER = 3; // 副号随意换副号码

	// changeMSISDSIM change type
	public static final short RESOURCE_CHANGE_MSISDN = 0;
	public static final short RESOURCE_CHANGE_IMSI = 1;
	public static final short RESOURCE_CHANGE_MSISDN_IMSI = 2;

	// lifecycle
	public static final int LIFECYCLE_IDLE_INITIAL = 1000;
	public static final int LIFECYCLE_ACTIVE = 1001;
	public static final int LIFECYCLE_SUSPEND_REQUEST = 1002;
	public static final int LIFECYCLE_SUSPEND_DEBT = 1003;
	public static final int LIFECYCLE_SUSPEND_FRAULD = 1004;
	public static final int LIFECYCLE_SUSPEND_CREDITLIMIT = 1005;
	public static final int LIFECYCLE_SUSPEND_2WAY = 1006;
	public static final int LIFECYCLE_DISABLE = 1007;
	public static final int LIFECYCLE_TERMINAL = 1008;
	public static final int LIFECYCLE_POOL = 1009;
	// 广西
	public static final int LIFECYCLE_POOL1 = 1013;
	public static final int LIFECYCLE_POOL2 = 1014;
	public static final int LIFECYCLE_POOL3 = 1015;
//	public static final int LIFECYCLE_PRE_TERMINAL = 1010;
//	public static final int LIFECYCLE_SUSPEND_1WAY = 1011;
	// 湖南
	public static final int LIFECYCLE_POOL4 = 1022;// 主动销户
	public static final int LIFECYCLE_POOL5 = 1024;// 欠费销号
	public static final int LIFECYCLE_PRE_TERMINAL = 1021;//主动预销号
	public static final int LIFECYCLE_SUSPEND_1WAY = 1023;//欠费预销号

	// 广西预销户
	public static final int LIFECYCLE_PRE_TERMINAL1 = 1008;
	public static final int LIFECYCLE_PRE_TERMINAL2 = 1010;
	public static final int LIFECYCLE_PRE_TERMINAL3 = 1011;
	public static final int LIFECYCLE_PRE_TERMINAL4 = 1012;
	// 湖南预销户
	public static final int LIFECYCLE_PRE_TERMINAL5 = 1021;
	public static final int LIFECYCLE_PRE_TERMINAL6 = 1023;

	public static final int LIFECYCLE_KEEP = 1017;
	public static final int LIFECYCLE_ZNW_KEEP = 1016;

	
	
	public static final int NOT_REDUCE_VALIDITY_FORCE = 0;
	public static final int REDUCE_VALIDITY_FORCE_TO_REQDATE = 1;
	public static final int REDUCE_VALIDITY_FORCE = 2;
	public static final int ADD_VALIDITY_NOT_FORCE = 3;

	public static final String LIFECYCLE_STS_ACTIVE = "00";
	public static final int MDB_LIFECYCLE_ACTIVE = 10000001;

	public static final int LIFECYCLE_OSSTS_ACTIVE = 10;
	public static final int LIFECYCLE_OSSTS_ONEWAY = 11;
	public static final int LIFECYCLE_OSSTS_TWOWAY = 12;

	public static final int LIFECYCLE_UNFRAULD_FLAG = 0;
	public static final int LIFECYCLE_FRAULD_FLAG = 1;
	public static final int LIFECYCLE_USERREQUEST_FLAG = 1;
	public static final int LIFECYCLE_USERREQUEST_NO_FLAG = 0;
	public static final int LIFECYCLE_UNBILLING_FLAG_PROSPECT = 2;
	public static final int LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY = 1;
	public static final int LIFECYCLE_UNBILLING_FLAG_NO = 0;
	public static final int LIFECYCLE_RERATING_FLAG = 1;
	public static final int LIFECYCLE_RERATING_FLAG_NO = 0;

	public static final int LIFECYCLE_SYNCTO_CRM_NO_BALANCE = 0;
	public static final int LIFECYCLE_SYNCTO_CRM_VALIDITY_EXPIRE = 1;
	public static final int LIFECYCLE_SYNCTO_CRM_CREDIT_LIMIT = 2;
	public static final int LIFECYCLE_SYNCTO_CRM_EXPIRE_FROM_SUSPEND_ONEWAY = 3;

	public static final int IS_HISTORY_DATA = 1;
	public static final int IS_VALID_DATA = 0;
	public static final int IN_DEALING = 2;

	public static final int RESOURCE_IMEI_BIND = 1;// resource和imei默认绑定

	// lifecycle event type
	public static final int LIFECYCLE_EVENT_PAYMENT_OPEN_REQ = 1;
	public static final int LIFECYCLE_EVENT_DEBTREQ = 2;
	public static final int LIFECYCLE_EVENT_FRAUDREQ = 3;
	public static final int LIFECYCLE_EVENT_CANCEL_FLAUD_REQ = 4;
	public static final int LIFECYCLE_EVENT_USER_REQ_SUSPEND = 5;
	public static final int LIFECYCLE_EVENT_CANCEL_USER_REQ_SUSPEND = 6;
	public static final int LIFECYCLE_EVENT_CRM_UNIDENTITY = 7;
	public static final int LIFECYCLE_EVENT_CANCEL_CRM_UNIDENTITY = 8;
	public static final int LIFECYCLE_EVENT_BALANCE_CHANGE = 9;
	public static final int LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_ACTIVE = 10;
	public static final int LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_ONEWAY = 11;
	public static final int LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_TWOWAY = 12;
	public static final int LIFECYCLE_EVENT_VALIDITYCHANGE_ACTIVE = 13;
	public static final int LIFECYCLE_EVENT_STATE_EXPIRE = 14;
	public static final int LIFECYCLE_EVENT_VALIDITYCHANGE = 15;

	// LJC ADD 2011-10-23
	public static final int LIFECYCLE_UNBILLING_NORMAL = 0;
	public static final int LIFECYCLE_UNBILLING_UN_INDENTIFY = 1;
	public static final int LIFECYCLE_UNBILLING_PROSPECT = 2;

	// resource service cycle
	public static final int SERVICE_CYCLESTS_CLOSE = 0;
	public static final int SERVICE_CYCLESTS_ACTIVE = 1;
	public static final int SERVICE_CYCLESTS_SUSPEND = 2;

	// manage user FN
	public static final int USER_MANAGE_FN_ADD = 0;
	public static final int USER_MANAGE_FN_DELETE = 1;
	// 2011-07-27 lijc3 add modify
	public static final int USER_MANAGE_FN_MODIFY = 2;

	// VPN member
	public static final short MANAGEGROUPMEMBER_OPER_ADD = 0;
	public static final short MANAGEGROUPMEMBER_OPER_DEL = 1;
	public static final short MANAGEGROUPMEMBER_COMMON = 0;
	public static final short MANAGEGROUPMEMBER_MANAGER = 1;
	// vpn number type
	public static final short VPN_NUMBER_TYPE_BOS = 0;
	public static final short VPN_NUMBER_TYPE_PBX = 2;

	// manage call screen
	public static final short MCS_OPERTYPE_ADD = 1;
	public static final short MCS_OPERTYPE_DELETE = 2;
	public static final short MCS_OPERTYPE_ROUT = 3;
	public static final short MCS_OPERTYPE_SET_CS_TYPE = 4;
	public static final short MCS_OPERTYPE_OPEN_CLIR = 5;
	public static final short MCS_OPERTYPE_CLOSE_CLIR = 6;
	public static final short MCS_OPERTYPE_OPEN_NOTIFICATION = 7;
	public static final short MCS_OPERTYPE_CLOSE_NOTIFICATION = 8;
	public static final short MCS_CS_NOTYPE_BLACKLIST = 0;
	public static final short MCS_CS_NOTYPE_WHITEIST = 1;
	public static final short MCS_CS_NOTYPE_BLACK_AND_WHITELIST = 2;
	public static final short MCS_WEEK_SUNDAY = 0;
	public static final short MCS_WEEK_SATURDAY = 6;
	public static final short MCS_CILR_OPEN = 0;
	public static final short MCS_CILR_CLOSE = 1;
	public static final short MCS_NOTIFY_CLOSE = 0;
	public static final short MCS_NOTIFY_OPEN = 1;

	// terminal user
	public static final short TERMINAL_USER_DROP_EXIST_PROMOTION_SERVICE = 1;
	public static final short TERMINAL_USER_NOUT_DROP_EXIST_PROMOTION_SERVICE = 0;
	public static final short TERMINAL_USER_RESET_BUDGET = 1;
	public static final short TERMINAL_USER_NOT_RESET_BUDGET = 0;
	public static final short TERMINAL_USER_LAST_TERM_FLAG_TRUE = 1;
	public static final short TERMINAL_USER_LAST_TERM_FLAG_FALSE = 0;

	// 用户信息默认值
	public static final int DEFAULT_USER_COUNTY_CODE = 0; // 默认地市代码
	public static final int DEFAULT_USER_REGION_CODE = 0; // 默认县级代码
	// 是否测试卡用户
	public static final int IS_TEST_NUMBER_TRUE = 1;
	public static final int IS_TEST_NUMBER_FALSE = 0;

	// MgntIRParty OPERATOR_TYPE： 1=AIS子公司 2=默认值
	public static final int OPERATOR_TYPE_DEFAULT = 2;
	public static final int OPERATOR_TYPE_AIS = 1;
	// InvoicingCoID 字段默认值 0
	public static final String INVOICINGCOID_DEFAULT = "1";

	// IMS_BUSI_STS_CONTROL表level字段 0 ：user
	public static final int IMSBUSISTSCONTROL_LEVEL_USER = 0;
	// IMS_BUSI_STS_CONTROL表level字段 1 ：account
	public static final int IMSBUSISTSCONTROL_LEVEL_ACCOUNT = 1;
	// IMS_BUSI_STS_CONTROL表level字段 2 ：customer
	public static final int IMSBUSISTSCONTROL_LEVEL_CUSTOMER = 2;

	// ########################以上定义Resource相关的枚举值###########################################

	// *************************以下定义Product相关的枚举值*******************************************
	// type of billtype
	public static final int PROD_BILLTYPE_PREPAID = 0;
	public static final int PROD_BILLTYPE_POSTPAID = 1;
	public static final int PROD_BILLTYPE_ALL = -1;

	// keep ics list
	public static final int PROD_KEEP_ICS_LIST = 0;
	public static final int PROD_NOT_KEEP_ICS_LIST = 1;

	// CoProdCharValue
	public static final int PROD_CHAR_VALUE_BUDGET = 0;
	public static final long PROD_CHAR_VALUE_NOT_GROUP = 0;

	// type of product
	public static final int PROD_TYPE_COMPONENT = 0;
	// public static final int PROD_TYPE_PACKAGE = 1;
	public static final int PROD_TYPE_MAIN = 2;
	// public static final int PROD_TYPE_PROMOTION = 3;
	// public static final int PROD_TYPE_SERVICE = 4;
	// public static final int PROD_TYPE_GROUP = 5;

	// active
	public static final int PROD_LIFECYCLE_ACTIVE = 1;
	// Suspend
	public static final int PROD_LIFECYCLE_SUSPEND = 2;
	// TERMINATE
	public static final int PROD_LIFECYCLE_TERMINATE = 3;
	public static final int PROD_LIFECYCLE_MAIN_PROD_SUSPEND = 3;
	// PAUSE
	public static final int PROD_LIFECYCLE_PAUSE = 4;
	// 扣费成功后是否变更产品账期
	public static final int PROD_DEDUCT_CHANGE_BILLCYCLE_YES = 1;
	public static final int PROD_DEDUCT_CHANGE_BILLCYCLE_NO = 0;

	// type of product involve object
	public static final int PROD_INVOLVETYPE_COMPOUND = 0;// include 1 and 2
	public static final int PROD_INVOLVETYPE_PAYMENT = 1;
	public static final int PROD_INVOLVETYPE_PURCHASE = 2;

	// type of product object_type
	public static final int PROD_OBJECTTYPE_DEV = 0;
	public static final int PROD_OBJECTTYPE_ACCOUNT = 1;
	public static final int PROD_OBJECTTYPE_GCA = 2;
	
	public static final int PROD_TYPE_USER = 0;
	public static final int PROD_TYPE_GROUP_1 = 1;
	public static final int PROD_TYPE_GROUP_2 = 2;
	public static final int PROD_TYPE_ACCT = 3;

	// type of account which associated with product
	public static final int PROD_RELATION_ACCOUNT_ALL = 0;
	public static final int PROD_RELATION_ACCOUNT_PAYMENT = 1;
	public static final int PROD_RELATION_ACCOUNT_SUBSCRIBING = 2;

	public static final short PROD_OPERTYPE_ADD = 0;
	public static final short PROD_OPERTYPE_DELETE = 1;
	public static final short PROD_OPERTYPE_MODIFY = 2;
	public static final short PROD_OPERTYPE_EXTEND = 3;

	// 产品生效模式effect mode by PmProductOfferLifecycle
	public static final int PROD_EFFECT_MODE_IMMEDIATELY = 0;
	public static final int PROD_EFFECT_MODE_DELAY = 1;
	public static final int PROD_EFFECT_MODE_USERDEFINED = 99;

	// 产品生效模式传入参数
	public static final int PROD_VALID_IMMEDIATELY = 0;
	public static final int PROD_VALID_NEXT_CYCLE = 1;
	public static final int PROD_VALID_NEXT_DAY = 2;
	public static final int PROD_VALID_NEXT_MONTH = 3;
	public static final int PROD_VALID_SPECIFIC_DATE = 4;

	// 产品生效延迟单位
	public static final int PROD_DELAY_CYCLE_DAY = 1;
	public static final int PROD_DELAY_CYCLE_MONTH = 2;
	public static final int PROD_DELAY_CYCLE_BILL = 3;
	public static final int PROD_DELAY_CYCLE_HOUR = 4;

	// 产品账单周期
	public static final int PROD_CYCLETYPE_DAY = 1;
	public static final int PROD_CYCLETYPE_WEEK = 2;
	public static final int PROD_CYCLETYPE_MONTH = 3;
	public static final int PROD_CYCLETYPE_YEAR = 4;
	public static final int PROD_CYCLETYPE_RUN_MONTH = 5;
	public static final int PROD_CYCLETYPE_HOUR = 6;
	public static final int PROD_CYCLETYPE_MONTH_OFFSET = 7;
	public static final int PROD_CYCLETYPE_RUN_DAY = 8;
	public static final int PROD_CYCLETYPE_RUN_WEEK = 9;// 供转换用 其他没用
	public static final int PROD_CYCLETYPE_RUN_YEAR = 10;// 供转换用 其他没用
	// 产品生效类型
	public static final int PROD_VALID_CYCLETYPE_RUN_DAY = 1;
	public static final int PROD_VALID_CYCLETYPE_RUN_WEEK = 2;
	public static final int PROD_VALID_CYCLETYPE_RUN_MONTH = 3;
	public static final int PROD_VALID_CYCLETYPE_RUN_YEAR = 4;
	public static final int PROD_VALID_CYCLETYPE_HOUR = 5;
	public static final int PROD_VALID_CYCLETYPE_DAY = 6;
	public static final int PROD_VALID_CYCLETYPE_WEEK = 7;
	public static final int PROD_VALID_CYCLETYPE_MONTH = 8;
	public static final int PROD_VALID_CYCLETYPE_YEAR = 9;
	public static final int PROD_VALID_CYCLETYPE_BILL = 10;
	public static final int PROD_VALID_FIXED_EXPIRE_TYPE = 11;

	// pay part type
	public static final int PAY_PART_TYPE_PERCENTAGE = 0;
	public static final int PAY_PART_TYPE_AMOUNT = 1;

	// main product specification or not
	public static final int PRODUCT_COMMON = 0;
	public static final int PRODUCT_MAIN = 1;
	// 标识映射产品，不需要上发MDB
	public static final int PRODUCT_MAPPING = 2;

	/** newreg 接口 main_promotion_id优先 */
	public static final int NEWREG_PRIRIOTY_MAIN_PROMOTION_ID = 1;
	/** newreg 接口 sProductOrder优先 */
	public static final int NEWREG_PRIRIOTY_SPRODUCTORDER = 2;
	/** newreg 接口 两者都输入了则报错 */
	public static final int NEWREG_PRIRIOTY_EQUAL = 3;

	// Deducting method of billing charges
	public static final int BILLING_CYCLE_DEDUCT_FLAG_PREPAID = 0;
	public static final int BILLING_CYCLE_DEDUCT_FLAG_POSTPAID = 1;
	public static final int BILLING_CYCLE_DEDUCT_FLAG_NO_DEDUCT = 2;
	// IR_OUT
	public static final int IR_OUT_VALUE = 0;
	public static final int IR_OUT_VALUE_DEFAULT = 1;
	// 产品账期根据那种方式生效 0：系统默认方式(按照预付费和后付费分别处理)
	// 1：与账期一致（不管预付费还是后付费都按账期处理）
	// 2：以产品周期为准（不管预付后付都按产品周期处理）
	// TODO 3:无周期
	public static final short PROD_OFFER_CYCLE_FLAG_DEFAULT = 0;
	public static final short PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT = 1;
	public static final short PROD_OFFER_CYCLE_FLAG_BY_PROD = 2;
	public static final short PROD_OFFER_CYCLE_FLAG_NO_PIROD = 3;
	// 产品升级后，0表示保留原产品，1表示使用升级后产品
	public static final short PROD_OFFER_UPSELL_FLAG_NO = 0;
	public static final short PROD_OFFER_UPSELL_FLAG_YES = 1;

	// 实例化账期是否需要包括短账期 0：产品周期不包括短账期 1：产品周期包括短账期
	public static final short PROD_HALF_CYCLE_NO = 0;
	public static final short PROD_HALF_CYCLE_YES = 1;
	// 销售品的预后付费属性
	public static final int PROD_OFFERING_BILLINGTYPE_ALL = -1;
	public static final int PROD_OFFERING_BILLINGTYPE_PREPAY = 0;
	public static final int PROD_OFFERING_BILLINGTYPE_POSTPAY = 1;

	// 销售品的状态（1-已激活，2-已废弃，3-计划中）
	public static final int PROD_OFFERING_STATUS_ACTIVE = 1;
	public static final int PROD_OFFERING_STATUS_SCRAP = 2;
	public static final int PROD_OFFERING_STATUS_PLANING = 3;
	// 产品扣费结果
	public static final short PROD_DEDUCT_FLAG_SUCCESS = 0; // 正常扣费成功
	public static final short PROD_DEDUCT_FLAG_SUCCESS_RE = 1;// 重新扣费成功
	public static final short PROD_DEDUCT_FLAG_FAIL = 2;// 扣费失败
	// public static final short PROD_DEDUCT_FLAG_HALF=3;
	public static final short PROD_DEDUCT_FLAG_PRORATE = 3;
	public static final short PROD_DEDUCT_FLAG_DELAY = 4;

	// 产品扣费失败挂起时机 第一次失败挂起还是最后一次挂起
	public static final short PROD_SUSPEND_FLAG_FIRST_TIME = 0;
	public static final short PROD_SUSPEND_FLAG_LAST_TIME = 1;
	// 普通产品扣费失败时，第一次失败退订还是最后一次退订
	public static final int PROD_TERMINATE_FLAG_FIRST_TIME = 0;
	public static final int PROD_TERMINATE_FLAG_LAST_TIME = 1;
	public static final int PROD_TERMINATE_FLAG_FIRST_SUSP_LAST_TERM = 2;// 第一次挂起，最后一次退订

	// 产品退订失效方式
	public static final short UNSUB_TYPE_BY_DAY = 1;
	public static final short UNSUB_TYPE_BY_MONTH = 2;
	public static final short UNSUB_TYPE_BY_BILL = 3;
	public static final short UNSUB_TYPE_BY_HOUR = 4;

	// 销售品适用于那种使用者订购
	public static final int OFFER_CLASS_ALL = 111;
	// 是否使用动态小区
	public static final int HOME_ZONE_STATIC = 0;
	public static final int HOME_ZONE_DYNAMIC = 1;

	public static final int EXEMPT_TYPE_NO_PUSH = 1;
	public static final int EXEMPT_TYPE_NO_STOP = 2;
	public static final int EXEMPT_TYPE_NO_PUSH_AND_STOP = 3;
	public static final int EXEMPT_TYPE_PUSH_AND_STOP = 0;

	// 预算产品的定价计划标识
	public static final int BUDGET_PRICING_PLAN_ID = 0;
	// 预算产品的付费模式
	public static final int BUDGET_PAYMODE_PREPAID = 0;
	public static final int BUDGET_PAYMODE_POSTPAID = 1;
	public static final int BUDGET_PAYMODE_HYBIRD = -1;

	// reguid的定价计划表示
	public static final int REGUID_PRICING_PLAN_ID = -999;
	// 预扣产品周期内退订是否退费
	public static final int PRODUCT_DEL_IS_REFUND_YES = 1;
	public static final int PRODUCT_DEL_IS_REFUND_NO = 0;
	// 销售品关系 0:A=B+C+D,1:A+B=C+D
	public static final int PRODUCT_REL_ONE_2_MORE = 0;
	public static final int PRODUCT_REL_ENVLOPROD = 1;
	// 免费资源充值的specId
	public static final int FREE_SPEC_ID = 8121;
	// ########################以上定义Product相关的枚举值###########################################

	// set budget - budget_type
	public static final short BUDGET_BUDGETTYPE_ACCOUNT = 1;
	public static final short BUDGET_BUDGETTYPE_USER = 2;
	public static final short BUDGET_BUDGETTYPE_SERVICE = 3;

	public static final short BUDGET_OPER_TYPE_ADD = 0;
	public static final short BUDGET_OPER_TYPE_DELETE = 1;
	public static final short BUDGET_OPER_TYPE_MODIFY = 2;

	// action
	public static final short BUDGET_ACTION_NOTIFY = 3;
	public static final short BUDGET_ACTION_BARSERVICE = 2;

	// notify_type (1：sms、2：email、3：sms& email)
	public static final short BUDGET_NOTIFY_TYPE_SMS = 1;
	public static final short BUDGET_NOTIFY_TYPE_EMAIL = 2;
	public static final short BUDGET_NOTIFY_TYPE_BOTH = 3;

	// notify_action_type (0: Both level(default),1 account level,2: subscriber
	// level)
	public static final short NOTIFY_ACTION_TYPE_DEFAULT = 0;
	public static final short NOTIFY_ACTION_TYPE_ACCT = 1;
	public static final short NOTIFY_ACTION_TYPE_USER = 2;

	// last term flag:1-yes;0-no
	public static final short CHANGEOWNER_LAST_TERM_FLAG_NO = 0;
	public static final short CHANGEOWNER_LAST_TERM_FLAG_YES = 1;

	// convert_flag:0-no need to transfer;1-need to convert
	public static final short CHANGEOWNER_CONVERT_FLAG_NO = 0;
	public static final short CHANGEOWNER_CONVERT_FLAG_YES = 1;

	// reset_flag:1-reset;0-not reset
	public static final short CHANGEOWNER_RESET_FLAG_NO = 0;
	public static final short CHANGEOWNER_RESET_FLAG_YES = 1;

	// change_promotion_flag:0-keep existing;1-change
	public static final short CHANGEOWNER_CHG_PROMOTION_FLAG_NO = 0;
	public static final short CHANGEOWNER_CHG_PROMOTION_FLAG_YES = 1;

	// change_type:0-change owner; 1- change account
	public static final short CHANGEOWNER_CHANGE_TYPE_OWNER = 0;
	public static final short CHANGEOWNER_CHANGE_TYPE_ACCOUNT = 1;
	public static final short CHANGEOWNER_CHANGE_TYPE_BILLACCTOUNT = 2;
	public static final short CHANGEOWNER_CHANGE_TYPE_ALL = 9;// 用于singlebalance内部调用，不将该枚举提供给crm

	// change product - oper_type
	public static final short CHANGE_PROD_ADD = 0;
	public static final short CHANGE_PROD_DELETE = 1;
	public static final short CHANGE_PROD_MODIFY = 2;// modify param list
	public static final short CHANGE_PROD_EXTEND = 3;// extend expire date
	public static final short CHANGE_PROD_CHANGE_PAYMODE = 4;
	public static final short CHANGE_PROD_CPME = 5;// changepaymode,modifyparam,extend
	public static final short CHNAGE_PROD_EXTEND_CMP = 6; // extend and
															// changePaymentMode

	// title_role_id
	public static final int TITLEROLEID_INDIVIDUAL_ORDINARITY = 3000;
	public static final int TITLEROLEID_INDIVIDUAL_GENERALMANAGER = 3001;
	public static final int TITLEROLEID_INDIVIDUAL_SUPERVISIOR = 3002;
	public static final int TITLEROLEID_INDIVIDUAL_FATHER = 3003;
	public static final int TITLEROLEID_INDIVIDUAL_MOTHER = 3004;
	public static final int TITLEROLEID_INDIVIDUAL_CHILD = 3005;
	public static final int TITLEROLEID_UNDEFINE = 0;
	public static final int TITLEROLEID_MASTER_NUMBER = 8000;
	public static final int TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER = 8010;

	public static final int TITLEROLEID_VPN_MAINPHONE = 41;
	public static final int TITLEROLEID_VPN_NORMAL = 42;
	public static final int TITLEROLEID_VPN_MANAGER = 43;
	// @Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
	public static final int BSSBROKER_ROLE_VPN_NORMAL = 0;
	public static final int BSSBROKER_ROLE_VPN_MANAGER = 1;

	// query member info :0-not return group_info ;1-return group_info
	public static final int QUERYMEMBER_WITHOUT_GROUPINFO = 0;
	public static final int QUERYMEMBER_WITH_GROUPINFO = 1;

	public static final short REGUID_POLICY_ID_ALL = 0;
	public static final short REGUID_SEG_ID_ALL = 0;
	public static final short GROUP_ID_IS_ZERO = 0;
	public static final short PERCENT_DENOMINATOR_HUNDRED = 100;

	public static final short REGUIDE_TYPE_CHARGE = 0;
	public static final short REGUIDE_TYPE_USAGE = 1;

	public static final int REGUIDE_BE_PAYED = 0;
	public static final int REGUIDE_TO_PAY = 1;

	// card type for pay channel
	// 0- Common account
	// 1- Debit card
	public static final short CARD_TYPE_CREDIT = 0;
	public static final short CARD_TYPE_DEBIT = 1;

	// reCreate account or not for renewRegister
	// 0- use old account (default)
	// 1- use new account
	public static final short ISCHANGE_ACCOUNT = 1;
	public static final short NOTCHANGE_ACCOUNT = 0;

	// public static final short GROUP_TYPE_VPN = 1;
	// public static final short GROUP_TYPE_CUG = 2;
	// public static final short GROUP_TYPE_COMMUNITY = 3;
	// public static final short GROUP_TYPE_VPBX = 4;
	// public static final short GROUP_TYPE_SURF_E_FAMILY = 5; // 冲浪e家亲活动群
	// public static final short GROUP_TYPE_SIX_SIX_FAMILY_PACKAGE = 6;//
	// 66家庭包活动群

	public static final int MDB_GROUP_TYPE_VPN = 1;
	public static final int MDB_GROUP_TYPE_CUG = 2;
	public static final int MDB_GROUP_TYPE_COMMUNITY = 3;
	public static final int MDB_GROUP_TYPE_SPEC_GCA = 4;
	// @Date 2012-06-13 wangdw5:User Story #48305
	public static final int MDB_GROUP_TYPE_VPBX = 206;

	// Fee deduction methods in billing module 是否需要鉴权 0 ：不需要 ,1 ： 需要
	public static final short FEE_DEDUCTION_AUTH_FALSE = 0;
	public static final short FEE_DEDUCTION_AUTH_TRUE = 1;
	// @Date 2012-08-03 yugb 固费 鉴权操作类型,0:鉴权,1:费用查询
	public static final short FEE_DEDUCTION_AUTH = 0;
	public static final short FEE_DEDUCTION_QUERY = 1;

	public static final int FEE_PREPAID_DEDUCT = 0;// 预扣
	public static final int FEE_POSTPAID_DEDUCT = 1;// 后扣

	/******************************** Terminal user *********************************/
	// Last term flag can not be null
	public static final long TERMINAL_USER_LAST_TERM_FLAG_ILLEGEL = 3303601;
	// The subscriber has already terminate
	public static final long USER_ALREADY_TERMINAL = 3303602;
	// The drop promtion flag is illegel
	public static final long TERMINAL_USER_DROP_PROMOTION_FLAG_ILLEGEL = 3303603;

	// 同步产品状态：action取值
	public static final short SYNC_PROD_STS_ACTION_RESUME = 1;
	public static final short SYNC_PROD_STS_ACTION_SUSPEND = 2;
	public static final short SYNC_PROD_STS_ACTION_TERMINATE = 3;
	public static final short SYNC_PROD_STS_ACTION_PRORATE = 4;
	public static final short SYNC_PROD_STS_ACTION_CHANGEBILLINGCYCLE = 5;

	/**
	 * 1：普通销售品扣费失败挂起 2：主销售品扣费失败后转特殊状态 3：切换主销售品 （N天后换主销售品） 4: 普通产品terminate
	 */
	public static final int SYNC_PROD_ACT_TYPE_COMMON_PROD_SUSPEND = 1;
	public static final int SYNC_PROD_ACT_TYPE_MAIN_PROD_SUSPEND = 2;
	public static final int SYNC_PROD_ACT_TYPE_CHANGE_MAIN_PROD = 3;
	public static final int SYNC_PROD_ACT_TYPE_COMMON_PROD_TERMINATE = 4;
	public static final int SYNC_PROD_ACT_TYPE_USER_ACTIVE = 5;
	public static final int SYNC_PROD_ACT_TYPE_USER_DISABLE = 6;
	public static final int SYNC_PROD_ACT_TYPE_USER_TERMINAL = 7;

	// 同步产品状态 ：扣费结果
	public static final short SYNC_PROD_STS_DEDUCT_FLAG_SUC = 1;
	public static final short SYNC_PROD_STS_DEDUCT_FLAG_FAILED = 2;
	public static final short SYNC_PROD_STS_DEDUCT_FLAG_DEDUCT_HALF = 3;
	public static final short SYNC_PROD_STS_DEDUCT_FLAG_DEDUCT_PRORATE = 4;

	// 1:初始状态 2: 处理中
	public static final int SYNC_CRM_RECORD_STS_INITIAL = 1;
	public static final int SYNC_CRM_RECORD_STS_HANDLE = 2;

	// 首次激活firstActiveReward接口调用方式枚举
	public static final short REWARD_HANDLETYPE_MDB_DB = 0;
	public static final short REWARD_HANDLETYPE_MDB = 1;
	public static final short REWARD_HANDLETYPE_DB = 2;

	// 首次激活的渠道编号
	public static final short VOICE_ACTIVE = 16;// 语音首次激活
	public static final short DATA_ACTIVE = 17;// 数字首次激活

	/******************* set exempt credit limit *********************/
	// 免催也免停
	public static final int SET_EXP_CREDIT_COMMON = 0;
	public static final short SET_EXP_CREDIT_UNLIMIT = 1;

	// temp flag
	public static final short SET_EXP_CREDIT_PROMPT = 0;
	public static final short SET_EXP_CREDIT_NEXT_WEEK = 1;
	public static final short SET_EXP_CREDIT_TEMPORARY = 2;

	// credit type
	public static final short SET_EXP_CREDIT_TYPE_ACCOUNT = 1;
	public static final short SET_EXP_CREDIT_TYPE_USER = 2;
	public static final short SET_EXP_CREDIT_TYPE_SERVICE = 3;

	/************************ Auto Topup ****************************/
	// 信息管理定义auto_type
	public static final short AUTOTYPE_EVERY_WEEK = 0;
	public static final short AUTOTYPE_EVERY_MONTH = 1;
	public static final short AUTOTYPE_LOW_BALANCE = 2; // e-topup 银行自动充值时使用
	public static final short AUTOTYPE_ON_DEMAND = 3;

	// / 帐管定义的auto_type
	public static final int AUTOTYPE_BILLING_CYCLE = 0; // 按周期
	public static final int AUTOTYPE_BILLING_ON_DEMAND = 1; // 1: on demand (one
															// time)
	public static final int AUTOTYPE_BILLING_CYCLICITY = 2; // 周期性

	// 帐管定义的period_type
	public static final int AUTOTYPE_BILLING_YEAR = 1;
	public static final int AUTOTYPE_BILLING_MONTH = 2;
	public static final int AUTOTYPE_BILLING_WEEK = 3;
	public static final int AUTOTYPE_BILLING_DAY = 4;

	// 帐管定义的owner_type
	public static final int ORG_OWNER_TYPE_USER = 0;
	public static final int ORG_OWNER_TYPE_ACCOUNT = 1;

	// bss broker定义的sunday
	public static final short AUTOTYPE_SUNDAY = 0;
	// 帐管定义的sunday
	public static final short AUTOTYPE_BILLING_SUNDAY = 7;

	// pay_channel
	public static final short PAY_CHANNEL_BANK = 0;
	public static final short PAY_CHANNEL_CREDIT = 1;
	public static final short PAY_CHANNEL_OTHER_POSTPAID_ACCOUNT = 2;
	public static final short PAY_CHANNEL_OTHER_PREPAID_ACCOUNT = 3;

	/*********************** Query group menber info **********************************/
	// 查询某个群成员信息时是否返回归属群信息
	public static final short QUERY_MEMBER_NO_RETURN_GROUP = 0;
	public static final short QUERY_MEMBER_RETURN_GROUP = 1;

	/*********************** Query group menber info end *******************************/
	// PAY_TYPE
	public static final short PAY_TYPE_ADVANCE_FEE = 0;
	public static final short PAY_TYPE_BILL_FEE = 1;

	// 货币种类
	/********************* Top up by cash ***************************/
	// 账本类型
	public static final int IS_COMMON_POCKET = 1;
	public static final int IS_DEDICATED_POCKET = 2;

	/*********************** One Time Fee *******************************************/
	public static final short ONETIMEFEE_EVENTTYPE_NOTPAY = 0;
	public static final short ONETIMEFEE_EVENTTYPE_PAY = 1;

	public static final short ONETIMEFEE_LEVEL_DEV = 0;// 用户级的一次性费用
	public static final short ONETIMEFEE_LEVEL_ACCT = 1;// 账户级的一次性费用

	public static final short ONETIMEFEE_AUTH_SUCCESS = 0;// 一次性鉴权成功
	public static final short ONETIMEFEE_AUTH_FAIL = -1;// 一次性鉴权失败
	public static final short ONETIMEFEE_AUTH_NOT_HAVE_PRICE = 2;// 无资费

	public static final short ONETIMEFEE_BOS = 1;// bos 算费
	public static final short ONETIMEFEE_SFF = 2;// 外部算费

	/** 0-收取一次性费用 */
	public static final short ONETIMEFEE_CHARGE_FLAG_ON = 0;
	/** 1-不收取一次性费用 */
	public static final short ONETIMEFEE_CHARGE_FLAG_OFF = 1;//

	// 同步数据表中：初始状态
	public static final short SYNC_DEAL_STS_INITIAL = 1;
	// 同步数据表中：处理中状态
	public static final short SYNC_DEAL_STS_HANDLING = 2;
	// 同步数据表中：处理失败
	public static final short SYNC_DEAL_STS_FAIL = 3;
	// 同步数据表中：处理过程中产生无法预期的错误，需要人工干预
	public static final short SYNC_DEAL_STS_UNEXPECTED = 4;
	// 扣费失败后是否存在延迟操作
	public static final int PMRENTDEDUCTFAILURE_HAVEDELAYACTION = 1;

	/***************** MAIL GROUP MANAGEMENT *********************/
	// whether to notify the customer
	public static final short NOTIFY_NO = 0;
	public static final short NOTIFY_YES = 1;

	// // 操作mailgroup信息的oper_type类型
	// public static final short MAIL_GROUP_OPER_TYPE_ADD = 0;
	// public static final short MAIL_GROUP_OPER_TYPE_MODIFY = 1;
	// public static final short MAIL_GROUP_OPER_TYPE_DELETE = 2;

	// // 操作mail group members信息的oper_type类型
	// public static final short MAIL_GROUP_MEMBERS_OPER_TYPE_ADD = 0;
	// public static final short MAIL_GROUP_MEMBERS_OPER_TYPE_MODIFY = 2;
	// public static final short MAIL_GROUP_MEMBERS_OPER_TYPE_DELETE =1;

	// file_type类型
	public static final short MAIL_GROUP_FILE_TYPE_PDF = 1;
	public static final short MAIL_GROUP_FILE_TYPE_TXT = 2;

	/****************** query group info *********************************/
	// query_type
	// query_type = 0 : query group and group relation and group CUG
	public static final short GROUP_QUERY_TYPE_QUERY_GROUP_ALL = 0;
	public static final short GROUP_QUERY_TYPE_QUERY_GROUP = 1;
	public static final short GROUP_QUERY_TYPE_QUERY_GROUP_CUG = 2;
	public static final short GROUP_QUERY_TYPE_QUERY_GROUP_REL = 3;

	/****************** query group info end **************************/

	// 修改账期的类型
	public static final short SHORT_BILL_TYPE = 1;
	public static final short LONG_BILL_TYPE = 2;

	// 修改后账期是否延时
	public static final short EFFECTIVE_BY_CURRENT_CYCLE = 0;
	public static final short EFFECTIVE_BY_NEXT_CYCLE = 1;

	/***************** 以下定义渠道编号 **************************/
	public static final short CHANNEL_CRM = 01;

	// 三户新装flag标识
	public static final short POST_PAID_PREMATCH = 1;
	public static final short POST_PAID_PROSPECT = 2;

	// method name
	public static final String METHOD_NAME_ORDER_PRODUCT = "do_orderProduct";

	/****************** set CreditLimit *********************/

	public static final short CREDIT_TYPE_ACCOUNT = 1;
	public static final short CREDIT_TYPE_USER = 2;
	public static final short CREDIT_TYPE_SERVICE = 3;

	public static final short DEFAULT_CREDIT_VALUE = -999;
	public static final short INFINITY_CREDIT_VALUE = -1;

	// 0：普通信用度，1：临时信用度，2：后付费账本，3：无穷大普通信用度，4：无穷大临时信用度
	public static final short NORMAL_CREDIT = 0;
	public static final short TEMP_CREDIT = 1;
	public static final short AFTER_PAY_BOOKS = 2;
	public static final short UNLIMIT_CREDIT = 3;
	public static final short UNLIMIT_TEMP_CREDIT = 4;

	public static final short UNLIMIT_CREDIT_FLAG = 1;
	public static final short TEMP_CREDIT_FLAG = 1;

	// 1:帐户级信用度 2:用户级信用度
	public static final short ACCT_CREDIT_TYPE = 1;
	public static final short USER_CREDIT_TYPE = 2;

	/************ query real time call charge **************/
	// real type
	public static final short BILLING_REAL_TYPE_HISBILL = 0;
	public static final short BILLING_REAL_TYPE_HISANDREALTIME = 1;
	public static final short BILLING_REAL_TYPE_REALTIME = 2;
	public static final short BILLING_PROD_TYPE = 1; // 标识查产品级费用

	// ret type
	public static final short BILLING_RET_TYPE_USERBILL = 0;
	public static final short BILLING_RET_TYPE_BILLANDITEM = 1;

	// default bill no
	public static final short BILLING_DEFAULT_NO = 0;

	// set max free resource:flag(bill_flag)
	public static final short MAXFREERES_BILL_FLAG_FIX = 0;
	public static final short MAXFREERES_BILL_FLAG_MAX = 1;

	/**************** query CreditLimit ***********************/
	// 2012-10-16 gaoqc5 最大信用度为十个9
	public static final long CREDIT_LIMIT_VALUE = 9999999999l;

	public static final short CREDIT_LIMIT_FLAG = 0;
	public static final short CREDIT_UNLIMIT_FLAG = 1;

	// queryCommonItem 0：全查 1：通用科目 2：专用科目
	public static final short QUERY_CREDIT_ITEM_ALL = 0;
	public static final short QUERY_CREDIT_ITEM_GENERAL = 1;
	public static final short QUERY_CREDIT_ITEM_SPECIAL = 2;

	public static final short ONETIMEFEE_CHARGETYPE_SFF = 0;
	public static final short ONETIMEFEE_CHARGETYPE_BOS = 1;

	// isCommonItem 0:本信用度不是通用信用度 1：本信用度是通用信用度
	public static final short CREDIT_IS_NOT_COMMONITEM = 0;
	public static final short CREDIT_IS_COMMONITEM = 1;

	// cm_id_mapping表中的map_type
	public static final int ID_MAP_TYPE_CUSTID = 1;
	public static final int ID_MAP_TYPE_ACCTID = 2;
	public static final int ID_MAP_TYPE_GROUPID = 3;
	public static final int ID_MAP_TYPE_USERID = 4;// 表中暂不使用

	// 帐管通用账本的标志
	public static final int ACCT_COMMONPOCKET_NO = 0;
	public static final int ACCT_COMMONPOCKET_YES = 1;

	// 缴费和调账操作
	public static final int ORDER_TYPE_PAYMENT = 0;
	public static final int ORDER_TYPE_ADJUST_BILL = 1;

	// 告警工单
	public static final short FOR_ALL_TYPE = 0;
	public static final short NOTIFICATION_FOR_DUNNING = 1;
	public static final short NOTIFICATION_FOR_BILL = 2;
	public static final short NOTIFICATION_FOR_TRANSACTION = 3;

	// 用户服务使用状态
	public static final int RESOURCE_SERVICE_FLAG_NO_USED = 0;
	public static final int RESOURCE_SERVICE_FLAG_USED = 1;

	// 鉴权操作类型
	public static final int RECURRING_ACTION_FREEZE = 1;// 冻结
	public static final int RECURRING_ACTION_UNFREEZE = 3;// 解冻
	public static final int RECURRING_ACTION_DEDUCT = 4;// 扣费
	public static final int AUTH_ITEM_FLAG_SUCCESS = 1;// 科目鉴权成功
	public static final int AUTH_ITEM_FLAG_FALSE = 2;// 科目鉴权失败

	// 设置通知是用户级别还是账户级别
	public static final short NOTIFICATION_BOTH_LEVEL = 0; // 账户级别和用户级别
	public static final short NOTIFICATION_ACCOUNT_LEVEL = 1; // 账户级别
	public static final short NOTIFICATION_SUBSCRIBER_LEVEL = 2;// 用户级别

	// 设置是免催免停业务还是通知设置
	public static final short EXEMPTCREDITLIMIT_BUSINESS = 0; // 免催免停业务
	public static final short SETNOTIFICATIONFLAG_BUSINESS = 1; // 通知设置业务

	public static final int CAP_LIMITE_BUSINESS_AMOUNT = 1001;// 业务限额
	public static final int CAP_LIMITE_TOPUP_USER_MAX_BALANCE = 1002;// 充值用户最大余额(max
																		// balance)
	public static final int CAP_LIMITE_USER_SINGLE_BUSINESS = 1003; // 用户单笔业务最大操作金额
	public static final int CAP_LIMITE_TANSFER_AMOUNT = 1004;// 转移转换的限额
	public static final int CAP_LIMITE_MAX_VALIDITY = 1005;// 最大有效期限制

	// 用户资源的等级的枚举值
	public static final int RES_SEGMENT_CLASSIC = 1;
	public static final int RES_SEGMENT_GOLD = 2;
	public static final int RES_SEGMENT_PLATINUM = 3;
	public static final int RES_SEGMENT_PLATINUM_PLUS = 4;
	public static final int RES_SEGMENT_SERENADE_CEO = 5;
	public static final int RES_SEGMENT_SERENADE_PRESTIGE = 6;
	public static final int RES_SEGMENT_STANDARD = 7;

	/************************** 事件触发reward *****************/
	// 事件大类
	public static final int EVENT_CLASS_CYCLE = 1;
	public static final int EVENT_CLASS_ONETIME = 2;
	// 事件类型
	public static final int EVENT_TYPE_BIRTHDAY = 100;
	public static final int EVENT_TYPE_ONNET_YEAR = 200;
	// 规则类型
	public static final int LIMIT_TYPE_BIRTHDAY = 1;
	public static final int LIMIT_TYPE_ONNET_YEAR = 2;

	/******************* 业务记录 start *********************/
	public static final int BUSIREC_RECTYPE_NORMAL = 0;// 业务类型，正常业务
	public static final int BUSIREC_RECTYPE_CANCEL = 1;// 业务类型，撤单业务

	public static final int BUSIREC_DIREC_CRM2BOS = 1;// 业务方向，crm同步到bos
	public static final int BUSIREC_DIREC_BOS2CRM = 2;

	public static final int BUSIREC_STS_NORMAL = 0;// 业务状态，正常状态
	public static final int BUSIREC_STS_CANCEL = 1;// 业务状态，已撤单过的

	public static final int BUSIREC_PRODTYPE_NORMAL = 0;// 产品类型，普通产品
	public static final int BUSIREC_PRODTYPE_REWARD = 1;// 产品类型，赠送产品

	public static final int BUSIREC_OPERTYPE_ADDPROD = 0;// 操作类型，订购产品
	public static final int BUSIREC_OPERTYPE_DELPROD = 1;// 操作类型，删除产品
	public static final int BUSIREC_OPERTYPE_MODIFYPROD = 2;// 操作类型，修改产品
	public static final int BUSIREC_OPERTYPE_EXTENDPROD = 3;// 操作类型，延期产品
	public static final int BUSIREC_OPERTYPE_CHGMAINPROD = 4;// 操作类型，更改主产品

	/******************* 业务记录 end *********************/

	/********************* 低余额通知自动缴费平台 ********************************/
	public static final int ETOPUP_PAY_CHANNEL_CREDIT = 1;
	public static final int ETOPUP_PAY_CHANNEL_BANK_ACCOUNT = 0;
	public static final int ETOPUP_AUTO_TYPE_LOW_BALANCE_AUTO = 2;

	/******************** manageScpProFile接口相关枚举值 ***********************/
	// ICF_flag枚举值
	public static final short SCP_ICF_FLAG_OPEN = 0;
	public static final short SCP_ICF_FLAG_CLOSE = 1;
	public static final short SCP_ICF_FLAG_ADD = 2;
	public static final short SCP_ICF_FLAG_DELETE = 3;
	public static final short SCP_ICF_FLAG_ACTIVE = 4;
	public static final short SCP_ICF_FLAG_DEACTIVE = 5;
	// number statue 枚举值
	public static final short SCP_NUMBER_STATUE_INVALID = 0;
	public static final short SCP_NUMBER_STATUE_ACTIVE = 1;

	/****************************** query cap max ****************/
	public static final int CAP_MAX_ITEM = 1;
	/************************* advance payment allocation ********************/
	public static final short ADVANCEPAYMENT_ACTIONTYPE_MOD = 0;
	public static final short ADVANCEPAYMENT_ACTIONTYPE_ALLOCATION = 1;

	// 帐户账期类型
	// public static final int ACCT_BILLCYCLETYPE_YEAR = 1;
	// public static final int ACCT_BILLCYCLETYPE_MONTH = 2;
	// public static final int ACCT_BILLCYCLETYPE_WEEK = 3;
	// public static final int ACCT_BILLCYCLETYPE_DAY = 4;
	public static final int ACCT_BILLCYCLETYPE_UNDEFINE = 0;

	// 帐管充值缴费接口标志位
	public static final int COMMONPARAM_SPECIAL_BUSI_FLAG = 1;

	// 清除免费资源 clearOtFreeRes 操作类型:0--不清理被冻结的免费资源 1--强制清理所有符合条件的免费资源
	public static final int CLEAR_OTFREERES_ACTFLAG_PART = 0;
	public static final int CLEAR_OTFREERES_ACTFLAG_ALL = 1;

	// PM_PROD_OFFER_PRICE_RULE表的PRICE_RULE_TYPE枚举
	public static final int PAY_ON_BEHALF_RULE = 0;// 代付规则
	public static final int PREPAID_RULE = 1;// 预算规则
	public static final int ASSET_TRANSFORM = 2;// 资产转换
	public static final int GIVE_MORE_VALID_DATE_WHEN_TOP_UP = 3;// 充值延有效期
	public static final int CHANGE_MAIN_PRODUCT = 4;// 首次激活换主产品
	public static final int CAP_MAX = 5;// cap max
	public static final int ASSET_TRANSFER = 6;// 资产转移
	public static final int BALANCE_TRANSFORM_FREERESOURSE = 7;// 预付费转后付费的余额转换为免费资源规则
	public static final int REPLACE_MAIN_PRODUCT = 8;// 替换主产品扣有效期
	public static final int HYBRID = 9;// HYBRID规则
	public static final int SLA_RULE = 10;// SLA规则
	public static final int DATA_FIRSTACTIVE_GPRS_URL = 11;// 数据业务首次激活规则

	// reward type
	public static final int REWARD_TYPE_POCKET = 0; // 赠送类型-资金
	public static final int REWARD_TYPE_COIN = 2; // 赠送类型-积分
	public static final int REWARD_TYPE_FREEUSAGE = 4; // 赠送类型-免费资源
	// public static final int REWARD_TYPE_CREDIT = 4; //赠送类型-信用度
	public static final int REWARD_TYPE_PRODUCTOFFERRING = 5; // 赠送类型-销售品
	public static final int REWARD_TYPE_VALIDDATE = 6; // 赠送类型-有效期

	// reward allotLevel 赠送级别 0-用户级 1-账户级
	public static final int REWARD_ALLOTLEVEL_ACCOUNT = 1;
	public static final int REWARD_ALLOTLEVEL_USER = 0;

	// 客户，帐户联系人信息类型
	public static final int CONTACT_TYPE_CUSTOMER_ADDRESS = 1;
	public static final int CONTACT_TYPE_VAT_ADDRESS = 2;
	public static final int CONTACT_TYPE_BILLING_ADDRESS = 4;
	public static final int CONTACT_TYPE_COPYING_BILL_ADDRESS = 6;

	// 设置负余额账本flag标志
	// @Date 2012-08-06 tangjl5 :Bug # 53005 由于帐管接口枚举值修改
	public static final int SET_NAGIVE_PERSONALIZED = 2; // 个性化负账本
	public static final int SET_NAGIVE_SYSTEM = 1; // 系统级负账本

	// queryPocketBalance 的query_type
	public static final short BALANCE_QUERY_TYPE = 9;// 只进行账本信息查询，不进行一次性费用收取
	public static final short BALANCE_QUERY_VALID = 1;// 查询有效的余额数据。
	// 换产品 是换入还是换出
	public static final int CHANGE_PRODUCT_IN = 0;
	public static final int CHANGE_PRODUCT_OUT = 1;

	// 受理工单类型
	public static final int INNER_SOURCETYPE = 1; // 表示账务管理内部
	public static final int OUTER_SOURCETYPE = 0; // 表示信息管理

	/************** 产品通知类型 ********************/
	public static final int PROD_NOTIFY_TYPE_VALID = 1;
	public static final int PROD_NOTIFY_TYPE_EXPIRE = 2;
	public static final int PROD_NOTIFY_TYPE_CYCLE = 3;
	public static final int PROD_NOTIFY_TYPE_TRIAL = 7;
	public static final int PROD_NOTIFY_TYPE_ADD = 8;
	public static final int PROD_NOTIFY_TYPE_DELETE = 9;
	public static final int PROD_NOTIFY_TYPE_MODITY = 10;
	public static final int PROD_NOTIFY_TYPE_EXTEND = 11;
	public static final int PROD_NOTIFY_TYPE_CHANGEPAYMODE = 12;
	public static final int PROD_NOTIFY_TYPE_CHANGEMAINPROD = 13;

	public static final int PROD_NOTIFY_MODE_IMMEDIATELY = 1;
	public static final int PROD_NOTIFY_MODE_BEFORE = 2;
	public static final int PROD_NOTIFY_MODE_AFTER = 3;

	public static final int PROD_NOTIFY_TRIAL_MODE_PRODVALID = 0;
	public static final int PROD_NOTIFY_TRIAL_MODE_OFFSET = 1;

	public static final int PROD_NOTIFY_OFF_TYPE_NATURE_DAY = 1;
	public static final int PROD_NOTIFY_OFF_TYPE_NATURE_WEEK = 2;
	public static final int PROD_NOTIFY_OFF_TYPE_NATURE_MONTH = 3;
	public static final int PROD_NOTIFY_OFF_TYPE_NATURE_YEAR = 4;
	public static final int PROD_NOTIFY_OFF_TYPE_MONTH = 5;
	public static final int PROD_NOTIFY_OFF_TYPE_HOUR = 6;
	public static final int PROD_NOTIFY_OFF_TYPE_NATURE_OFF_MONTH = 7;
	public static final int PROD_NOTIFY_OFF_TYPE_DAY = 8;

	// 是否通知产品的免费资源
	public static final int PROD_NOTIFY_FREE_RES_NO = 0;
	public static final int PROD_NOTIFY_FREE_RES_YES = 1;

	/************** 产品通知类型END ********************/

	// FreeResoure level type
	public static final short FREERESOURCE_TYPE_ACCT = 1;
	public static final short FREERESOURCE_TYPE_SUBSCRIBER = 2;
	public static final int FREERESOURCE_RECHARGE_METHOD = 20;

	public static final short MDB_DEFAULT_VALUE = -999;
	public static final String MDB_DEFAULT_VALU_STRING = "-999";

	/************** measureId ********************/
	public static final short MEASURE_ID_MILSECOND = 101;
	public static final short MEASURE_ID_SECOND = 102;
	public static final short MEASURE_ID_BYTE = 103;
	public static final short MEASURE_ID_KB = 104;
	public static final short MEASURE_ID_MB = 105;
	// bar service reason code
	public static final int BAR_SERVICE_REASON_CODE_HIT_CREDIT = 1;
	public static final int BAR_SERVICE_REASON_CODE_HIT_BUDGET = 2;
	public static final int BAR_SERVICE_REASON_CODE_HIT_CAPMAX = 3;
	// unbar service reason code
	public static final int BAR_UN_SERVICE_REASON_CODE_HIT_CREDIT = 1;
	public static final int BAR_UN_SERVICE_REASON_CODE_HIT_BUDGET = 2;
	public static final int BAR_UN_SERVICE_REASON_CODE_HIT_CAPMAX = 3;

	// 0：barservice(停服务) 1：unbarservice(开通服务)
	public static final int BAR_SERVICE = 0;
	public static final int UN_BAR_SERVICE = 1;

	/************** 表SYS_TIME_SEG_DEF中time_mode枚举 ********************/
	public static final short TIME_MODE_NOT_TIME = 0;// 非时间模式
	public static final short TIME_MODE_WEEK = 1;// 星期型的当日时间段
	public static final short TIME_MODE_YYYYMMDD = 2;// YYYYMMDD型指定日期的当日时间段
	public static final short TIME_MODE_MMDD = 3;// MMDD型当天的时间段
	public static final short TIME_MODE_DD = 4;// DD型当天的时间段
	public static final short TIME_MODE_WEEK_PERIOD = 5;// 星期型的从其实日期到终止日期的时间
	public static final short TIME_MODE_YYYYMMDD_PERIOD = 6;// YYYYMMDD型从起始日期时间到终止日期时间
	public static final short TIME_MODE_MMDD_PERIOD = 7;// MMDD型从起始日期时间到终止日期时间
	public static final short TIME_MODE_DD_PERIOD = 8;// DD型从起始日期时间到终止日期时间

	/************** changeowner绑定的一次性费用收取时是传变更前还是变更后账号的开关控制（默认为1） ********************/
	public static final int CHANGEOWNER_ONETIMEFEE_OLD = 1;// 1: 收取变更前账户
	public static final int CHANGEOWNER_ONETIMEFEE_NEW = 2;// 2：收取变更后账户

	// 免费资源级别类型
	public static final int ONE_TIME_PROM_USER = 0;// 用户
	public static final int ONE_TIME_PROM_ACCOUNT = 1;// 账户
	public static final int ONE_TIME_PROM_GCA = 2;// 集团
	public static final int ONE_TIME_PROM_CUST = 3;// 客户

	// 充值
	public static final int NOTIFY_TOPUP_BY_CASH = 0;// 充值无负账本
	public static final int NOTIFY_TOUPP_BY_VOUCHER_CARDS = 4;// 充值卡充值

	// split 分账类型
	public static final int SPLIT_TYPE_ITEM = 0;// 科目级代付
	public static final int SPLIT_TYPE_PRODUCT = 1; // 产品级代付

	// split代付方式 SPLIT_METHOD = 12815; //代付方式 0：一般代付（默认） 1：统付
	public static final int SPLIT_METHOD_NOMAL = 0;
	public static final int SPLIT_METHOD_TONGFU = 1;

	// balance_type
	public static final int BALANCE_TYPE_USER = 0;// 用户级账本
	public static final int BALANCE_TYPE_SINGLE_BALANCE = 1;// 账户级账本
	public static final int BALANCE_TYPE_USER_AND_SINGLE_BALANCE = 2;

	// _negative_balance_flag 0: system level 1: individual level 2: account
	// level
	public static final short NAGATIVE_BALANCE_FLAG_SYSTEM_LEVEL = 0;
	public static final short NAGATIVE_BALANCE_FLAG_INDIVIDUAL_LEVEL = 1;
	public static final short NAGATIVE_BALANCE_FLAG_ACCOUNT_LEVEL = 2;

	// 免费资源类型
	public static final int ONE_TIME_FEE_RES = 4;
	public static final int CYCLE_RESOURCE = 3;
	// 低余额通知
	public static final int LOW_BALANCE_NOTI = 1;

	// manage single balance
	public static final int SINGLE_BALANCE_OPER_TYPE_ADD = 0;
	public static final int SINGLE_BALANCE_OPER_TYPE_DEL = 1;

	// clean balance type
	public static final int CLEAN_BALANCE_TYPE_USER = 0;
	public static final int CLEAN_BALANCE_TYPE_ACCOUNT = 1;
	public static final int CLEAN_BALANCE_TYPE_ACCOUNT_USER = 1;

	// 通知扣费
	// 0:下次扣费时间取valid_date(首次激活/新装)
	public static final int CALL_DEDUCT_FEE_FOR_OTHERS = 0;
	// 1:下次扣费时间取deduct_date(预付费用户暂停后重新激活)
	public static final int CALL_DEDUCT_FEE_FOR_REATIVE = 1;
	// 4:下次扣费时间取valid_date(产品延期)
	public static final int CALL_DEDUCT_FEE_EXTEND_EXPIRE_DATE = 4;

	// FIRE_EVENT_TYPE
	public static final int FIRE_EVENT_TYPE_CLEAN_BALANCE = 0;

	// 查询余额类型
	public static final int QUERY_USER = 0;// 用户级余额
	public static final int QUERY_ACCOUNT = 1;// 账户级余额
	public static final int QUERY_VPN = 2;
	public static final int QUERY_CURRENT_ACCT_USER = 2;// 当前用户和账户
	public static final int QUERY_ALL_ACCT_USER = 3;// 所有用户和账户

	public static final int VALIDITY_QUERY_ACCOUNT = 1; // 查询账户级有效期
	public static final int VALIDITY_QUERY_USER = 0; // 查询用户级有效期

	// SYS_TIME_SEG_DEF TIME_MODE枚举值
	public static final int NO_TIME_MODE = 0;
	public static final int MODE_1 = 1;
	public static final int MODE_2 = 2;
	public static final int MODE_3 = 3;
	public static final int MODE_4 = 4;
	public static final int MODE_5 = 5;
	public static final int MODE_6 = 6;
	public static final int MODE_7 = 7;
	public static final int MODE_8 = 8;

	// ims_sonbr_mapping.busi_type
	public static final int SONBR_MAPPING_BUSITYPE_ONETIMEFEE = 1;
	public static final int SONBR_MAPPING_BUSITYPE_REWARD = 2;
	public static final int SONBR_MAPPING_BUSITYPE_AUTH = 3;
	public static final int SONBR_MAPPING_BUSITYPE_PAYMENT = 4;

	public static final int CREATE_PROD_NOTIFY_BUSI_CODE = 9094;

	// 0:Validity will start from current expire date
	public static final int VALIDITY_START_FROM_CUR_EXEPIRE_DATE = 0;
	// 1:Validity will start from current system date
	public static final int VALIDITY_START_FROM_CUR_SYSTEM_DATE = 1;
	// 2:If subscriber has negative validity,validity will start from current
	// system date
	public static final int NEGATIVE_VALIDITY_START_FROM_CUR_SYSTEM_DATE = 2;
	/**
	 * caohm5 SH免催免停标识 免催免停类型 101 免催 102 免停 103 免催免停
	 */
	public static final int EXEMPTION_NO_CALL = 101;
	public static final int EXEMPTION_NO_STOP = 102;
	public static final int EXEMPTION_NO_CALL_AND_NO_STOP = 103;
	public static final int EXEMPTION_CALL_AND_STOP = 0;

	public static final short CHECK_SURF_E_VICE_NUM = 1; // 判断是否是冲浪e家亲活动的副号码
	public static final short CHECK_SIX_SIX_FAMILY_PACKAGE_VICE_NUM = 2; // 判断是否是66家庭包活动的副号码
	public static final short CHECK_ALL_PAY_VICE_NUM = 3; // 判断是否统付类活动的副号码
	public static final short CHECK_CHANGXIANG_VICR_NUM = 4;// 判断是否畅想计划

	public static final String QUERY_DEPOSIT = "0";// 查询押金
	public static final String QUERY_POCKET = "1";// 查询帐本
	public static final String QUERY_POCKET_AND_DEPOSIT = "2";// 查询押金和帐本

	public static final short BILL_EXT_IN_QUERY_TYPE_PERSON = 0;// 查询账单个人
	public static final short BILL_EXT_IN_QUERY_TYPE_GROUP = 1;// 查询集团账单
	// 是否查询实时帐单
	public static final short BILL_EXT_IN_HISTORY_BILL = 0;// 只查询历史帐单
	public static final short BILL_EXT_IN_HISTORY_AND_REAL_BILL = 1;// 查询历史帐单和实时帐单
	public static final short BILL_EXT_IN_REAL_BILL = 2;// 只查询实时帐单
	// 是否查询滞纳金
	public static final short BILL_EXT_IN_LATE_FEE_NO = 0;
	public static final short BILL_EXT_IN_LATE_FEE_YES = 1;
	// 是否格式化帐单
	public static final Boolean BILL_EXT_IN_FORMAT_NO = false;
	public static final Boolean BILL_EXT_IN_FORMAT_YES = true;
	// 是否查询帐单明细
	public static final short BILL_EXT_IN_BILL_DETAIL_NO = 0;
	public static final short BILL_EXT_IN_BILL_DETAIL_YES = 1;
	// 判断欠费状态
	public static final short BILL_EXT_OUT_SATUS_NO_BILL = 3;
	public static final short BILL_EXT_OUT_SATUS_UNPAY = 2;
	public static final short BILL_EXT_OUT_SATUS_NO_SETTLING = 1;
	public static final short BILL_EXT_OUT_SATUS_SETTLING = 0;
	// 是否欠费
	public static final short BILL_EXT_IS_UNPAY = 0; // 欠费
	public static final short BILL_EXT_IS_PAY = 1; // 未欠费

	// 2012-04-02 zengxr get right busi spec of OneTimeFee for multi spec in one
	// interface
	// The value type of this key is Integer, saved in IMSContext
	public static final String ACTION_SPEC_ID_KEY = "IMS_ACTION_SPEC_ID_KEY";

	public static final String COLLECT_CALL_FLAG_CLOSE = "0";
	public static final String COLLECT_CALL_FLAG_OPERN = "1";

	public static final int TAX_FLAG_TRUE = 0;
	public static final int TAX_FLAG_FLASE = 1;

	public static final int CHANGE_GROUP_INFO_NOT_SUPPORT_CALL_SCREAN = 0;
	public static final int CHANGE_GROUP_INFO_SUPPORT_CALL_SCREAN = 1;

	public static final short PARAM_TYPE_ACCT = 0; // (0:帐户编号，1：用户编号)
	public static final short PARAM_TYPE_DEV = 1; // (0:帐户编号，1：用户编号)
	public static final short MONITOR_SIGN_IN = 1; // 迁入
	public static final short MONITOR_SIGN_OUT = 2; // 迁出
	public static final short MONITOR_COND_ID = 0; // 监控业务条件表达式ID， 目前高危系统默认为0

	/**
	 * 2012-5-29 zhangzj3 增加reward触发类型枚举值
	 */
	public static final int SRC_ACTION_OTHER = 0;// 其他
	public static final int SRC_ACTION_ACCOUNT_REWARD = 1;// 帐管调用IMS接口订购Reward产品
	public static final int SRC_ACTION_BOS_VALIDATE = 2;// BOS触发的延长有效期
	public static final int SRC_ACTION_RATEING_REWARD_PRODUCT = 3;// 计费触发Reward
																	// product
	public static final int SRC_ACTION_RATEING_UPSELLING = 4;// 计费触发Upselling
	public static final int SRC_ACTION_RATEING_REWARD_FREE = 5;// 计费触发Reward
																// freeresource

	public static final String CRM_SERVICE_RIGHT_CODE = "0000"; // 调用CRM
																// http+json返回的正确代码

	public static final short DO_UNDEPOSIT_SO_NBR_IN_FLAG = 1; // 充值接口帐管返回的工单号
	public static final short DO_UNDEPOSIT_SO_NBR_OUT_FLAG = 2; // 充值接口 外部传入的工单号

	public static final int CYCLE_PATTERN_DEF_PATTERN_TYPE_DEVICE = 0;
	public static final int CYCLE_PATTERN_DEF_PATTERN_TYPE_ACCOUNT = 1;

	// 用户有效停机位枚举（反向同步CRM时使用）
	/*
	 * public static final short OS_STS_DTL_FIRST = 1;//欠费停
	 * public static final short OS_STS_DTL_SECOND = 2;//呼出限制停
	 * public static final short OS_STS_DTL_THIRD = 3;//新入网额度停
	 * public static final short OS_STS_DTL_FOUR = 4;//欠费预销户停
	 * public static final short OS_STS_DTL_FIVE = 5;//有效期停
	 * public static final short OS_STS_DTL_SIX = 6;//保留期停
	 * public static final short OS_STS_DTL_SEVEN = 7;//同证件停
	 * public static final short OS_STS_DTL_TEN =
	 * 10;//欠费复机（复机类别：欠费停、呼出限制停、欠费预销、保留停、有效期停）
	 * public static final short OS_STS_DTL_ELEVEN = 11;//新入网额度复机
	 */
	/**
	 * 停机位枚举值定义
	 * 
	 * @author songcc
	 * @Date 2014-1-14
	 */
	public static final short OS_STS_DTL_FRIST = 1;// 骚扰彩信停机
	public static final short OS_STS_DTL_SECOND = 2;// 骚扰电话停
	public static final short OS_STS_DTL_THIRD = 3;// 垃圾短息停
	public static final short OS_STS_DTL_FOUR = 4;// 短信热线停机
	public static final short OS_STS_DTL_FIVE = 5;// 集团数据业务(集团业务类)
	public static final short OS_STS_DTL_SIX = 6;// IPBUS帐户封锁状态(管理类)
	public static final short OS_STS_DTL_SEVEN = 7;// IPBUS帐户封锁状态(帐务管理)
	public static final short OS_STS_DTL_EIGHT = 8;// IPBUS帐户封锁状态(业务受理)
	public static final short OS_STS_DTL_NINE = 9;// 高额联带停
	public static final short OS_STS_DTL_TEN = 10;// 高额停
	public static final short OS_STS_DTL_ELEVEN = 11;// 管理(用户违章停) 联带停
	public static final short OS_STS_DTL_TWELVE = 12;// 管理停（用户违章停）
	public static final short OS_STS_DTL_THIRTEEN = 13;// 欠费停
	public static final short OS_STS_DTL_FOURTEEN = 14;// 信誉度停
	public static final short OS_STS_DTL_FIFTEEN = 15;// 账务连带停
	public static final short OS_STS_DTL_SIXTEEN = 16;// 营业报停
	public static final short OS_STS_DTL_SEVENTEEN = 17;// 挂失停
	public static final short OS_STS_DTL_EIGHTEEN = 18;// 连带停

	public static final String QUERY_POCKET_YAJIN = "0";// 查询类型
														// 0：查询押金，1：查询帐本，2：查询押金和帐本
	public static final String QUERY_POCKET_ZHANGBEN = "1"; // 查询类型
															// 0：查询押金，1：查询帐本，2：查询押金和帐本
	public static final String QUERY_POCKET_YAJIN_AND_ZHANGBEN = "2"; // 查询类型
																		// 0：查询押金，1：查询帐本，2：查询押金和帐本
	public static final Integer POCKET_ITEM_5030011 = 5030011; // 预存额,科目号为5030011
	public static final String QUERY_GPRS_AMT_TYPE_GUONEI = "0"; // 根据手机号码查询国内GPRS流量数据汇总
																	// 查询类型:
																	// 0(国内),1(国际)
	public static final String QUERY_GRRS_AMT_TYPE_GUOJI = "1"; // 根据手机号码查询国内GPRS流量数据汇总
																// 查询类型:
																// 0(国内),1(国际)
	// 2010新入网营销活动赠送预存 5501891
	public static final Integer SEND_BALANCE_ITEM = 5501891;
	// 2010新入网营销活动语音费专款 5901871
	public static final Integer VOICE_BALANCE_ITEM = 5901871;
	// 3g上网本活动;5521001
	public static final Integer BOOK_NOTE_3G_ITEM = 5521001;
	// 是否参加3g上网本活动；0,没有参加 1,参加了活动
	public static final short BOOK_NOTE_3G_YES = 1;
	public static final short BOOK_NOTE_3G_NO = 0;
	// 国内GPRS科目编码
	// TODO
	public static final Integer ITEM_CODE_GPRS_GUONEI = 11111;
	// 国际GPRS科目编码
	// TODO
	public static final Integer ITEM_CODE_GPRS_GUOJI = 22222;
	// 09春季参加活动赠送资金的剩余金额(5501801,5901661,5501891,5901871,5501771)
	public static final Integer POCKET_ITEM_5501801 = 5501801;
	public static final Integer POCKET_ITEM_5901661 = 5901661;
	public static final Integer POCKET_ITEM_5501891 = 5501891;
	public static final Integer POCKET_ITEM_5901871 = 5901871;
	public static final Integer POCKET_ITEM_5501771 = 5501771;

	public static final Integer CM_RES_LIFECYCLE_OS_STS_DTL_NORMAL = 0; // 上海停机位状态
																		// 0-正常
	// 上海免费资源科目
	public static final Integer FREE_RESOURCE_ITEM_CODE_1 = 12345;
	// 0表示无单缴费，1表示凭单缴费
	public static final Integer NO_BILL_PAY_FEE = 0;
	public static final Integer BILL_PAY_FEE = 1;

	// 查询账单，类型0:查历史账单，1:历史+实时 2:查询实时账单
	public static final short QUERY_BILL_REAL_TYPE_HIS = 0;
	public static final short QUERY_BILL_REAL_TYPE_BOTH = 1;
	public static final short QUERY_BILL_REAL_TYPE_REAL = 2;

	// 查询账单，是否需要明细，0：不需要 1：需要
	public static final short QUERY_BILL_RET_TYPE_NO_NEED = 0;
	public static final short QUERY_BILL_RET_TYPE_NEED = 1;

	public static final short SET_NEGATIVE_VALUE_TOTAL = 0;
	public static final short SET_NEGATIVE_VALUE_INCREMENT = 1;
	// 1：计费模块
	// 2：账务模块
	// 3: IMS
	public static final Integer MODULE_RATING = 1;
	public static final Integer MODULE_BILLING = 2;
	public static final Integer MODULE_IMS = 3;
	// 0：用户主动请求Terminate产品
	public static final Integer FREERES_BUSI_LIMIT_TRIGGER_TYPE_TERMINATEPROD = 0;
	// 1：Change BA
	public static final Integer FREERES_BUSI_LIMIT_TRIGGER_TYPE_CHANGEBA = 1;
	// 2：Change Owner
	public static final Integer FREERES_BUSI_LIMIT_TRIGGER_TYPE_CHANGEOWNER = 2;
	// 3：Change pay mode
	public static final Integer FREERES_BUSI_LIMIT_TRIGGER_TYPE_CHANGEPAYMODE = 3;
	// -1：表示所有事件
	public static final Integer FREERES_BUSI_LIMIT_TRIGGER_TYPE_ALL = -1;

	// 0：不保留产品
	public static final Integer FREERES_BUSI_LIMIT_PERSIST_FLAG_NOTKEEPPROD = 0;
	// 1：保留产品
	public static final Integer FREERES_BUSI_LIMIT_PERSIST_FLAG_KEEPPROD = 1;
	// -1：统配
	public static final Integer FREERES_BUSI_LIMIT_PERSIST_FLAG_ALL = -1;

	// 0：周期性免费资源不能使用（修改周期性免费资源的失效时间，置为事件发生时间）
	public static final Integer FREERES_BUSI_LIMIT_FREE_RES_FLAG_RESET = 0;
	// 1：周期性免费资源继续使用 （需要修改周期性免费资源为一次性免费资源）
	public static final Integer FREERES_BUSI_LIMIT_FREE_RES_FLAG_TRANSFER = 1;

	// 0或null ：删除一次性or周期性免费资源
	public static final Integer CHANGEBALANCEOWNER_FRS_OTFRS_RESET = 0;
	// 1 ：过户一次性or周期性免费资源
	public static final Integer CHANGEBALANCEOWNER_FRS_OTFRS_TRANSFER = 1;

	// 0或null ：删除MainBalance/SecBalance/SnBalance/InBalance
	public static final int CHANGEBALANCEOWNER_BALANCE_RESET = 0;
	// 1 ：过户MainBalance/SecBalance/SnBalance/InBalance
	public static final int CHANGEBALANCEOWNER_BALANCE_TRANSFER = 1;
	// 2 ：过户MainBalance/SecBalance/SnBalance/InBalance,但是欠费报错
	public static final int CHANGEBALANCEOWNER_BALANCE_TRANSFER_VALID = 2;
	// 3 ：删除MainBalance/SecBalance/SnBalance/InBalance,但是欠费报错
	public static final int CHANGEBALANCEOWNER_BALANCE_RESET_VALID = 3;
	// 4 系统级负余额额度会生成，deduct_value会重置
	public static final int CHANGEBALANCEOWNER_SYS_NEGATIVE_BALANCE_RESET = 4;

	// cleanTarget参数枚举 0 ：直接删除账本
	public static final Integer BALANCE_CLEANTARGET_DELETE = 0;
	// cleanTarget参数枚举 1 ：账本置失效
	public static final Integer BALANCE_CLEANTARGET_EXPIRE = 1;
	// cleanTarget参数枚举 2 ：账本清0
	public static final Integer BALANCE_CLEANTARGET_RESET = 2;

	// 1 表示清理
	public static final char CLEANBALANCE_CLEAN_YES = 1;
	// 0 表示不清理
	public static final char CLEANBALANCE_CLEAN_NO = 0;

	// 1 一次性
	public static final Integer CLEAROTFREERES_FREERESFLAG_OT = 1;
	// 0 周期性
	public static final Integer CLEAROTFREERES_FREERESFLAG_RECURR = 0;

	// 查询lostbalance
	public static final Integer QUERY_LOST_BALANCE_USER = 2;

	// 过户前,过户后
	public static final Integer CHANGEOWER_BEFORE = 0;
	public static final Integer CHANGEOWER_AFTER = 1;

	// 修改付费模式
	public static final short CHANGEPAYMODE_DROPFLAG_KEEP = 0;
	public static final short CHANGEPAYMODE_DROPFLAG_CHANGE = 1;

	// 账本类型
	public static final short MAIN_BALANCE = 0;
	public static final short SYSTEM_LEVEL_NEGATIVE_BALANCE = 1;
	public static final short INDIVIDUAL_LEVEL_NEGATIVE_BALANCE = 2;
	public static final short SECONDARY_BALANCE = 3;

	// 上发CRM接口 work_order_type值
	public static final short FIRST_ACTIVE_WORK_ORDER_TYPE = 1;
	public static final short CREDIT_STATUS_WORK_ORDER_TYPE = 2;
	public static final short PRODUCT_STS_WORK_ORDER_TYPE = 3;
	public static final short USER_STS_WORK_ORDER_TYPE = 4;
	public static final short BAR_SERVICE_WORK_ORDER_TYPE = 5;
	public static final short PRODUCT_REWARD_WORK_ORDER_TYPE = 6;
	public static final short PROM_USER_WORK_ORDER_TYPE = 7;
	public static final short PRODUCT_SHARE_WORK_ORDER_TYPE = 8;
	public static final short LOW_BALANCE_WORK_ORDER_TYPE = 9;

	// 业务结果
	public static final short BUSI_RESULT_SUCCESS = 1;
	public static final short BUSI_RESULT_FAILED = 2;

	// 清理balance，reward balance，free resource 标志
	public static final String CLEAR_BALANCE_FREE_RESOURE_REWARD = "11110";

	/*************** query free times ****************/
	// 1 由资费确认收费（即不匹配费用列表，计算bos资费上所有科目费用）；< =2 匹配费用列表（即按传入科目计算费用）；
	public static final short CALC_TYPE = 1;
	// 仅计算(资费引擎)当前一次性费用及累计量信息
	public static final short EVENT_TYPE = 6;
	// 扣费模式 0:非强制扣费; 1:强制扣费
	public static final short DEDUCT_TYPE = 0;
	// 免费标志Y-free of charge, N-Not free
	public static final String FREE_FLAG_YES = "Y";
	public static final String FREE_FLAG_NO = "N";

	public static final int QUERY_BALANCE_ACCOUNT = 1;
	public static final int QUERY_BALANCE_UER = 0;
	/*************** query free times ****************/

	public static final int USER_VALIDITY_EFFECT = 0;// 用户有效期表记录有效
	public static final int USER_VALIDITY_NOT_EFFECT = 1;// 用户有效期表记录无效

	// @Date 2012-08-14 yugb :增加产品的定价类型 priceType
	public static final int PROD_ACCUMULATE_PRICE = 1;
	public static final int PROD_ACCUMULATE_ALLOWANCE = 2;
	public static final int PROD_SERVICE_ACCUMULATION = 11;

	// 多渠道告警定义类型
	public static final int NODIFY_CHANNEL_TYPE_SMS = 0;
	public static final int NODIFY_CHANNEL_TYPE_EMAIL = 1;
	// 多渠道告警定义类型(BosBorke)
	public static final int BOSBORKE_NODIFY_CHANNEL_TYPE_SMS = 1;
	public static final int BOSBORKE_NODIFY_CHANNEL_TYPE_EMAIL = 2;

	/************** query actual usage ****************/
	public static final int CYCLE_FLAG_CURRENT = 0; // 查询当前账单
	public static final int CYCLE_FLAG_PREVIOUS = 1; // 查询上个账期的账单

	// 表示产品管理删除sys_group_credit数据
	public static final int RESET_NEGATIVE_VALUE_OPERATOR_FLAG_DEL = 3;

	/************** @Date 2012-08-17 wangdw5 : queryCDR ****************/
	public static final short QUERYCDR_TYPE_VOICE = 1;
	public static final short QUERYCDR_TYPE_SMS = 2;
	public static final short QUERYCDR_TYPE_MMS = 3;
	public static final short QUERYCDR_TYPE_GPRS = 4;
	public static final short QUERYCDR_TYPE_3GAIS = 5;
	public static final short QUERYCDR_TYPE_VIDEOCALL = 6;
	public static final short QUERYCDR_TYPE_MENTERTAIN = 7;
	public static final short QUERYCDR_TYPE_VIA1900 = 8;
	public static final short QUERYCDR_TYPE_WLAN = 9;
	public static final short QUERYCDR_TYPE_GOODSANDSERVICE = 10;
	public static final short QUERYCDR_TYPE_TRANSACTION = 11;
	public static final short QUERYCDR_TYPE_3GTOT = 12;
	public static final short QUERYCDR_TYPE_OTHERS = 13;
	public static final short QUERYCDR_TYPE_VASSERVICE = 14;

	public static final short QUERYCDR_DEFAULT_DOMANDINT = 0;
	public static final short QUERYCDR_DOMESTIC = 1;
	public static final short QUERYCDR_INTERNATIONAL = 2;

	public static final short QUERYCDR_RATEING_DOMESTIC = 0;
	public static final short QUERYCDR_RATEING_INTERNATIONALIN = 3;
	public static final short QUERYCDR_RATEING_INTERNATIONALOUT = 5;

	public static final short QUERYCDR_OFFLINEFLAG_NO = 0;
	public static final short QUERYCDR_OFFLINEFLAG_YES = 1;

	public static final short QUERYCDR_SHOWHEADING = 1;

	public static final short QUERYCDR_BSS_SORTBY_DATETIME = 1;
	public static final short QUERYCDR_BSS_SORTBY_EVENTDATETIME = 2;
	public static final short QUERYCDR_BSS_SORTBY_BNUMBER = 3;
	public static final short QUERYCDR_BSS_SORTBY_DESTINATION = 4;

	public static final short QUERYCDR_BILL_SORTBY_DATETIME = 1;
	// public static final short QUERYCDR_BILL_SORTBY_EVENTDATETIME = 2;
	public static final short QUERYCDR_BILL_SORTBY_BNUMBER = 2;
	public static final short QUERYCDR_BILL_SORTBY_DESTINATION = 3;
	// 最大免费资源调是否失效
	public static final int MAX_FREE_RESOURCE_INVALID = 1;
	public static final int MAX_FREE_RESOURCE_VALID = 0;
	// 框架路由找不到数据异常code
	public static final int ROUTE_ERROR_CODE = 2097152;

	// Gsm topUp
	public static final int GSM_BILL_TYPE_REALTIME = 1;
	public static final int GSM_BILL_TYPE_BILLED = 2;

	// 押金接口 账管 用户电话类型：1固话 2移话
	public static final int RES_TYPE_TELPHONE = 1;
	public static final int RES_TYPE_MOBILE = 2;

	// 话单中号码类型：0手机
	public static final short XDR_DEVICE_TYPE_MOBILE = 0;

	// 一次性免费资源生效类型
	public static final int FR_VALID_DELAY = 0;
	public static final int FR_VALID_IMMEDIATE = 1;

	// 一次性免费资源周期参考类型
	public static final int FR_CYCLE_REF_MODE_ACCT = 0;
	public static final int FR_CYCLE_REF_MODE_PROD = 1;
	public static final int FR_CYCLE_REF_MODE_OWN_CONF = 2;

	// productClass 定义
	public static final int PRODUCT_CLASS_MAIN_PRODUCT = 2;
	public static final int PRODUCT_CLASS_CALL_SCREEN = 40;
	public static final int PRODUCT_CLASS_HOME_ZONE = 43;
	public static final int PRODUCT_CLASS_FAX_ADDIONAL = 42;
	public static final int PRODUCT_CLASS_IR_REAL_TIME = 44;
	// product 相关定义
	public static final int PRODUCT_CALL_SCREEN = 114;
	public static final int PRODUCT_HOME_ZONE = 112;
	public static final int PRODUCT_FAX_ADDIONAL = 124;
	public static final int PRODUCT_IR_REAL_TIME = 122;

	// SHBOSS维护相关定义0:正常 1：系统级维护 2:ABM维护
	public static final String SHBOSS_NORMAL = "0";
	public static final String SHBOSS_REPAIR = "1";
	public static final String SHBOSS_ABM_REPAIR = "2";
	// 现金充值超时时间
	public static final int CALL_DEPOSIT_BY_CASH_TIME_OUT_TIME = 15000;

	// 计费调用的产品自动升档的busi_code定义
	public static final int BUSI_CODE_UP_PRICE_PARAM = 9144;
	public static final int BUSI_CODE_DISCOUNT_PRICE_PARAM = 9148;
	
	public static final int MDB_ROUTE_VERSION_TYPE_USER = 0;
	public static final int MDB_ROUTE_VERSION_TYPE_ACCT = 1;
	
	public static final short USER_CONTACT = 0;// 用户联系人类型
	public static final short ACCT_CONTACT = 1;// 账户联系人类型
	public static final short CUST_CONTACT = 3;// 客户联系人类型
	
	// 免催免停表  rec_type 业务类型
	public static final int NOTIFY_EXEMPTION_REC_REDUSER = 1; // 红名单用户
	public static final int NOTIFY_EXEMPTION_REC_NODEALUSER = 2; // 免处理用户
	public static final int NOTIFY_EXEMPTION_REC_SPECUSER = 3; // 小区信控停机红名单
	//冲销类型
	public static final int BILLING_TYPE_REAL_TIME = 0; // 实时销账
	public static final int BILLING_TYPE_END_MONTH = 1; // 月底批销
	
	public static final int BILLING_TYPE_PREPAY = 1;
	
}
