package com.ailk.ims.define;

/**
 * 枚举值编码定义 @Description:
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author xieqr
 * @Date 2012-07-09 <br>
 */
public class EnumCodeShDefine {
	// 同步短信表数据到crm时，batch_size的值，如1000
	public static final String SMS_SEND_BATCH_NUM = "SMS_SEND_BATCH_NUM";

	// 短信审核总表
	public static final long IM_SH_FREERES_MODE_CODE = 60000000L;
	// 短信模板编码
	// public static final long IM_SH_WLAN_CMCC_SEND_CODE = 60000001L;//
	// WLAN上网免费资源CMCC科目未使用提醒模板
	// public static final long IM_SH_WLAN_CMCC_EDU_SEND_CODE = 60000002L;//
	// WLAN上网免费资源CMCC_EDU未使用提醒模板
	public static final long IM_SH_WLAN_CMCC_AND_EDU_CODE = 60000003L;// WLAN上网免费资源CMCC、CMM_EDU均未使用提醒模板
	public static final long IM_SH_EWALK_SEND_CODE = 60000004L;// 随e行上网本免费流量使用情况
	public static final long IM_SH_USER_PACKAGE_CODE = 60000005L;// 用户套餐免费资源提醒服务
	public static final long IM_SH_MOBLIE_DATA_FLOW_CODE = 60000006L;// 移动数据流量未使用沉默用户提醒
	public static final long IM_SH_GPRS_FLOW_CODE = 60000007L;// 定期发送gprs流量提醒
	public static final long IM_SH_WLAN_FLOW_CODE = 60000008L;// 定期发送wlan流量提醒
	public static final long IM_SH_RES_VALIDITY_CODE = 60000010L;// 定期发送用户有效期到期提醒
	public static final long IM_SH_VALID_BILL_CYCLE_STOP_PHONE_CODE = 60000011L; // 有效期停机
																					// 短信提醒短信编号
	public static final long IM_SH_CYCLE_NOTIFY_LAST_RES_CODE = 60000012L;// GPRS流量周期提醒---提醒上帐期免费资源
	public static final long IM_SH_CYCLE_NOTIFY_LAST_CAL_CODE = 60000013L;// GPRS流量周期提醒---提醒上帐期累积量
	// public static final long IM_SH_CYCLE_NOTIFY_CUL_RES_CODE = 60000014L;//
	// GPRS流量周期提醒---提醒当前帐期免费资源
	public static final long IM_SH_CYCLE_NOTIFY_CUL_CAL_CODE = 60000015L;// GPRS流量周期提醒---提醒当前帐期累积量
	public static final long IM_SH_GPRS_LOW_FLOW_CODE = 60000016L; // GPRS低流量提醒
	public static final long IM_SH_WLAN_TIME_FLOW_CODE = 60000019L; // wlan时长提醒

	// 定义要提醒的免费资源科目
	public static final String IM_SH_WLAN_CMCC = "IM_SH_WLAN_CMCC"; // 用户wlan的CMCC科目
	public static final String IM_SH_WLAN_EDU = "IM_SH_WLAN_CMCC_EDU"; // 用户wlan的CMCC科目
	public static final String IM_SH_WLAN_AUTO = "IM_SH_WLAN_CMCC_AUTO"; // 用户WLAN
																			// auto自动认证科目
	public static final String IM_SH_EWALK_FLOW = "IM_SH_EWALK_FLOW";// 随e行上网本免费流量
	// public static final String IM_SH_USER_PACKAGE = "IM_SH_USER_PACKAGE";//
	// 用户套餐免费资源
	public static final String IM_SH_MOBLIE_DATA_FLOW = "IM_SH_MOBLIE_DATA_FLOW";// 移动数据流量
	public static final String IM_SH_GPRS_FLOW = "IM_SH_GPRS_FLOW";// GPRS流量
	public static final String IM_SH_GPRS_CUMULANT_FLOW = "IM_SH_GPRS_CUMULANT_FLOW";// GPRS累积量科目
	public static final String IM_SH_WLAN_FLOW = "IM_SH_WLAN_FLOW";// WLAN流量
	public static final String IM_SH_GPRS_LOW__DATA_FLOW = "IM_SH_GPRS_LOW_CM_DATA_FLOW";// GPRS
																							// 移动数据流量
																							// 低流量提醒
	public static final String IM_SH_GPRS_LOW_GPRS_FLOW = "IM_SH_GPRS_LOW_FLOW";// GPRS低流量提醒
	public static final String IM_SH_FREERES_HALF_USE_SMS_FLOW = "IM_SH_FREERES_HALF_USE_SMS_FLOW";// 免费资源落后进度提醒
	public static final String IM_SH_FREERES_HALF_USE_TIME_FLOW = "IM_SH_FREERES_HALF_USE_TIME_FLOW";// 免费资源落后进度提醒
	public static final String IM_SH_WLAN_TIME_FLOW = "IM_SH_WLAN_TIME_FLOW";// wlan时长提醒科目:4508096
	public static final String IM_SH_WLAN_TIME_CUMULANT_FLOW = "IM_SH_WLAN_TIME_CUMULANT_FLOW";// wlan时长提醒科目:4508096

	public static final String IMS_SH_GPRS_THREAD_COUNT = "IMS_SH_GPRS_THREAD_COUNT";// gprs扫描线程个数

	public static final String IMS_SH_WLAN_THREAD_COUNT = "IMS_SH_WLAN_THREAD_COUNT";// wlan扫描线程个数

	// 空中充值类型（0：手机空中充值(默认值)；1：互联网空中充值）
	public static final short DO_TRANSFERAMOUNT_DO_TYPE_BY_PHONE = 0;
	public static final short DO_TRANSFERAMOUNT_DO_TYPE_BY_NET = 1;

	public static final short PRODUCT_OFFERING_IS_MAIN = 1; // 主销售品
	public static final short PRODUCT_OFFERING_IS_NOT_MAIN = 0; // 非主销售品

	// 操作类型0：新增1：删除2：修改
	public static final short OPERTYPE_IS_ADD = 0;
	public static final short OPERTYPE_IS_DELETE = 1;
	public static final short OPERTYPE_IS_MODIFY = 2;

	public static class busi {
		public static final String IM_SH_2010_IN_NET_PRE_PAY = "IM_SH_2010_IN_NET_PRE_PAY";// 2010新入网营销活动赠送预存"
		public static final String IM_SH_2010_IN_NET_VOICE_FEE = "IM_SH_2010_IN_NET_VOICE_FEE";// 2010新入网营销活动语音费专款"
		public static final String IM_SH_3G_SURF_FUND = "IM_SH_3G_SURF_FUND";// 剩余3G上网本上网费专用资金"
		public static final String IM_SH_09_SPRING_LEAVE_FUND = "IM_SH_09_SPRING_LEAVE_FUND";// 09春季入网送话费活动赠送资金的剩余金额"
		public static final String IM_SH_09_SPRING_CURR_USED_FUND = "IM_SH_09_SPRING_CURR_USED_FUND";// 09春季入网送话费活动本月可使用额度的赠送资金"
		public static final String IM_SH_WSYYT_ACCT_FUND = "IM_SH_WSYYT_ACCT_FUND";// 帐户资金费用查询(网上营业厅)"
		public static final String IM_SH_WEB_GPRS_AMT = "IM_SH_WEB_GPRS_AMT";// WAP_GPRS流量查询"
		public static final String IM_SH_DOMESTIC_GPRS_AMT = "IM_SH_DOMESTIC_GPRS_AMT";// 根据手机号码查询国内GPRS流量数据汇总"
		public static final String IM_SH_INTERNATIONAL_GPRS_AMT = "IM_SH_INTERNATIONAL_GPRS_AMT";// 根据手机号码查询国际GPRS流量数据汇总"
		public static final String IM_SH_WLAN_LOCAL = "IM_SH_WLAN_LOCAL";// 用户有本地wlan"
		public static final String IM_SH_WLAN_SCHOOL = "IM_SH_WLAN_SCHOOL";// 用户有上海校园WLAN"
		public static final String IM_SH_WLAN_FIMALY = "IM_SH_WLAN_FIMALY";// 用户有上海家庭WLAN"
		public static final String IM_SH_WLAN_PHONE = "IM_SH_WLAN_PHONE";// 用户有上海手机WLAN"
		public static final String IM_SH_WLAN_AUTO_CERTIFY = "IM_SH_WLAN_AUTO_CERTIFY";// 用户有WLAN自动认证专属资费"
		public static final String IM_SH_WLAN_BILLING_FLOW = "IM_SH_WLAN_BILLING_FLOW";// WLAN时长计费下流量汇总

		public static final String IM_SH_MOBILE_DOMESTIC_AMOUNT = "IM_SH_MOBILE_DOMESTIC_AMOUNT";// 移动数据国内流量
		public static final String IM_SH_MOBILE_INTERNATIONAL_AMOUNT = "IM_SH_MOBILE_INTER_AMOUNT";// 国际及台港澳漫游流量
		public static final String IM_SH_3G_NOTE_BOOK_AMOUNT_AMOUNT = "IM_SH_3G_NOTE_BOOK_AMOUNT_AMOUNT";// 随e行3G上网本业务流量
		public static final String IM_SH_MP_MAIL_DOMESTIC_AMOUNT = "IM_SH_P_MAIL_DOMESTIC_AMOUNT";// 手机邮箱国内流量
		public static final String IM_SH_MP_MAIL_INTERNATIONAL_AMOUNT = "IM_SH_P_MAIL_INTER_AMOUNT";// 手机邮箱国际流量
		public static final String IM_SH_BLOC_APN_AMOUNT = "IM_SH_BLOC_APN_AMOUNT";// 集团APN流量
		public static final String IM_SH_BLACK_BERRY_DOMESTIC_AMOUNT = "IM_SH_BLB_DOMESTIC_AMOUNT";// BlackBerry国内流量
		public static final String IM_SH_BLACK_BERRY_INTERNATIONAL_AMOUNT = "IM_SH_BLB_INTER_AMOUNT";// BlackBerry国际及台港澳漫游流量
		public static final String IM_SH_FETION_MENBER_OWNER_FREE_AMOUNT = "IM_SH_FETION_FREE_AMOUNT";// 飞信会员专属免费流量
		public static final String IM_SH_DATA_AUTO_ADD_AMOUNT = "IM_SH_DATA_AUTO_ADD_AMOUNT";// 移动数据自动叠加包流量

		public static final String IM_SH_PREFERENTIAL_ITEM1 = "IM_SH_PREFERENTIAL_ITEM1";// 叠加优惠包科目1
																							// ：1923181
		public static final String IM_SH_PREFERENTIAL_ITEM2 = "IM_SH_PREFERENTIAL_ITEM2";// 叠加优惠包科目1
																							// ：
																							// 4529121

		public static final String IM_SH_VOICE_CALL_ITEM = "IM_SH_VOICE_CALL_ITEM";// 语音话费科目

		public static final String IM_SH_GPRS_TYPE_FLOW_UNIT = "IM_SH_GPRS_TYPE_FLOW_UNIT";// GPRS流量单位
		public static final String IM_SH_GPRS_TYPE_LENGTH_UNIT = "IM_SH_GPRS_TYPE_LENGTH_UNIT";// GPRS使用时长单位

		public static final String IM_SH_BOSS_HTTP_JSON_SERVER_ADDR = "IM_SH_BOSS_HTTP_JSON_SERVER_ADDR"; // 本地接口平台服务地址
		public static final String IM_SH_ABATE_BILL_ITEM_ID = "IM_SH_ABATE_BILL_ITEM_ID"; // 优惠科目编号
		public static final String IM_SH_CBOSS_HTTP_JSON_SERVER_ADDR = "IM_SH_CBOSS_SERVER_ADDR"; // CBOSS接口服务地址
		public static final String IM_SH_NTNCBOSS_HTTP_JSON_SERVER_ADDR = "IM_SH_NTNCBOSS_SERVER_ADDR"; // CBOSS接口服务地址
		public static final String IM_SH_JSON_SOAP_RECORD_SUCC = "IM_SH_JSON_SOAP_RECORD_SUCC";// 是否记录出入参报文到
																								// JD.IMS_JSON_SOAP
																								// 表中
		public static final String IM_SH_JSON_SOAP_RECORD_FAIL = "IM_SH_JSON_SOAP_RECORD_FAIL";// 是否记录出入参报文到
																								// JD.IMS_JSON_SOAP
																								// 表中
		public static final String IM_SH_JSON_SOAP_THREAD_SLEEP_TIME = "IM_SH_JSON_SOAP_THREAD_SLEEP_TE";// 批量插入JD.IMS_JSON_SOAP
																											// 表线程等待时间系统参数60000
		public static final String IM_SH_JSON_SOAP_BATCH_NUMBER = "IM_SH_JSON_SOAP_BATCH_NUMBER";// 批量插入JD.IMS_JSON_SOAP表数据

	}

	public static final Short GPRS_TYPE_FLOW = 1;// GPRS已使用流量
	public static final Short GPRS_TYPE_LENGTH = 2;// GRPS已使用时长
	// 空中充值类型
	// 0：手机空中充值；1：互联网空中充值;2:表示同帐户下不同科目资金转移
	public static final Short TRANSFER_TYPE_PHONE = 0;
	public static final Short TRANSFER_TYPE_NET = 1;
	public static final Short TRANSFER_TYPE_SELF = 2;
	public static final int TRANSFER_TYPE_SELF_SPEC_ID = 202008701;// 同帐户不同科目之间的资金转移：202008701
	public static final int TRANSFER_CHANGE_OWNER_SPEC_ID = 202200901;// 过户时资金转移：202200901

	public static final short CBOSS_QUERY_HISTORY_BILL = 0; // 只查询历史帐单
	public static final short CBOSS_QUERY_REAL_BILL = 2; // 只查询实时帐单

	public static final short CBOSS_QUERY_LAST_LAST_FEE = 1; // 查询滞纳金
	public static final Short CBOSS_QUERY_RET_TYPE = 1; // 查询帐单明细

	// 账本用户状态：0正常 1停机
	public static final int POCKET_USER_STATUS_NORMAL = 0;
	public static final int POCKET_USER_STATUS_NO_SERVICE = 1;

	// 用户停机位（16位）初始化位串
	public static String INIT_STS_DTL = "00000000000000000000";

	// 扣款类型 0-同款专款均扣 1-只扣专款 2-只扣通款
	public static final int RE_TYPE_NORMAL_AND_PROFATIONAL_FUND = 0;
	public static final int RE_TYPE_PROFATIONAL_FUND = 1;
	public static final int RE_TYPE_NORMAL_FUND = 2;

	// 移动数据流量提醒: 日、周、月提醒的枚举值
	public static final int ENUM_MOBILEFLOW_CYCLE_DATE = 0; // 按日提醒
	public static final int ENUM_MOBILEFLOW_CYCLE_WEEK = 1; // 按周提醒
	public static final int ENUM_MOBILEFLOW_CYCLE_MONTH = 2;// 按月提醒

	/**
	 * ------------------------------------------------------------------------
	 * -----------
	 **/
	// 操作员默认值
	public static final long OP_ID_DEFUALT = 100000;
	// 组织默认值
	public static final int ORG_ID_DEFAULT = 10000;
	/**
	 * ------------------------------------有效期----------------------------------
	 * -----------------
	 **/
	// 用户有效期是否有效标识0：有效 1：无效
	public static final int CM_VALIDITY_EFFECTIVE = 0;
	public static final int CM_VALIDITY_IN_VALID = 1;
	// 取消预付费用户有效期枚举值 0：可以取消 2：没有设置有效期
	// 3：欠费停机 4:参加了"家庭活动" 5:尚未完成实名制 6:全球通 7家庭副号码8预付费随e 行品牌的号码带有月租费的产品或功能
	public static final int CM_VALIDITY_YES_CANCLE = 0;
	public static final int CM_VALIDITY_NULL = 2;
	public static final int CM_VALIDITY_OWE_BILL_STOP = 3;
	public static final int CM_VALIDITY_PARTY_FIMILY_ACTIVITY = 4;
	public static final int CM_VALIDITY_NO_REAL_NAME_STS = 5;
	public static final int CM_VALIDITY_BRAND_IS_GLOBLE = 6;
	public static final int CM_VALIDITY_FIMILY_NOT_MAIN = 7;
	public static final int CM_VALIDITY_E_PRICE = 8;
	// 0恢复有效期 1 取消有效期
	public static final int RE_COVER_CM_VALIDITY = 0;
	public static final int CANCLE_CM_VALIDITY = 1;
	// 取消有效期，参加家庭活动
	public static final int FIMILY_ACTIVITY = 204;
	// 取消有效期，品牌是全球通
	public static final int BRAND_ID_GLOBLE = 7;
	// 取消有效期，副号码
	public static final int TITLE_ROLE_ID_42 = 42;
	// 取消有效期；随e 行品牌销售品编号
	public static final int E_PRODUCT_OFFERING_ID = 20000710;
	// 易通卡品牌的销售品
	public static final int E_CARD_OFFERING_ID = 20000200;

	// 有用户有效期的用户品牌对应的KEY值，在SYS_PARAMETER表中配置
	public static final String IM_SH_E_CARD_KEY = "IM_SH_E_CARD_KEY";
	/**
	 * ----------------------------------------有效期------------------------------
	 * -----------------
	 **/

	// type of product object_type
	public static final int PROD_OBJECTTYPE_DEV = 0;
	public static final int PROD_OBJECTTYPE_ACCOUNT = 1;
	public static final int PROD_OBJECTTYPE_GCA = 2;

	// 上海一卡通停机状态 0停机 1 正常 2 预开户 3 预销户
	public static final int ECARD_USER_STOP_SERVICE = 0;
	public static final int ECARD_USER_NORMAL = 1;
	public static final int ECARD_USER_PRE_REGISTER = 2;
	public static final int ECARD_USER_PRE_CANCLE = 3;

	// 集团V网 产品busi_flag
	public static final int GROUP_V_NET_BUSI_FLAG = 130;
	// 资产科目类型
	public static final int ITEM_TYPE_FREERESOURCE = 0;
	// 是否有v网产品：0没有 1有
	public static final int IS_HAS_VNET_PROD = 1;
	public static final int IS_NO_VNET_PROD = 0;

	// 计费规则类型 0：代付规则
	public static final int PRICE_RULE_SPLIT = 0;

	// 上海免催免停枚举值
	public static final int EXEMPTION_NO_CALL = 101;
	public static final int EXEMPTION_NO_STOP = 102;
	public static final int EXEMPTION_NO_CALL_AND_NO_STOP = 103;
	public static final int EXEMPTION_HOLIDAYS_NO_CALL = 104;
	public static final int EXEMPTION_HOLIDAYS_NO_STOP = 105;
	public static final int EXEMPTION_HOLIDAYS_NO_CALL_AND_NO_STOP = 106;

	// 上海CM_RES_LIFECYCLE的osStsDtl位串枚举值
	public static final short STS_DTL_ONE = 1;// 第1位，其他停
	public static final short STS_DTL_ELEVEN = 11;// 第11位，日保号停
	public static final short STS_DTL_TWELVE = 12;// 第12位，特殊停
	public static final short STS_DTL_THIRTEEN = 13;// 第13位，同证件停
	public static final short STS_DTL_FOURTEEN = 14;// 第14位，新入网额度停
	public static final short STS_DTL_FIFTEEN = 15;// 第15位，有效期停
	public static final short STS_DTL_SIXTEEN = 16;// 第16位，保留期停
	public static final short STS_DTL_SEVENTEEN = 17;// 第17位，用户停
	public static final short STS_DTL_EIGHTEEN = 18;// 第18位，呼出限制停
	public static final short STS_DTL_NINETEEN = 19;// 第19位，欠费预销户停
	public static final short STS_DTL_TWENTY = 20;// 第20位，欠费停

	// 广西停机状态为位
	public static final short GX_STS_DTL_EIGHTEEN = 18;// 第18位，用户停
	public static final short GX_STS_DTL_SEVENTEEN = 17;// 第17位，账务连带停
	public static final short GX_STS_DTL_SIXTEEN = 16;// 第16位，信誉度停
	public static final short GX_STS_DTL_FIFTEEN = 15;// 第15位，欠费停

	public static final int SENG_NOTIFICATION_YES = 1;
	public static final int SEND_NOTIFICATION_NO = 0;

	// 帐管套餐类型（ A:全球通; B:M-ZONE; C:神州行;H:其它品牌）
	public static final String AMS_PLAN_TYPE_A = "A";
	public static final String AMS_PLAN_TYPE_B = "B";
	public static final String AMS_PLAN_TYPE_C = "C";
	public static final String AMS_PLAN_TYPE_H = "H";

	// 信息管理套餐类型( A:全球通; B:M-ZONE; C:神州行;H:其它品牌)
	public static final String IMS_PLAN_TYPE_A = "全球通";
	public static final String IMS_PLAN_TYPE_B = "M-ZONE";
	public static final String IMS_PLAN_TYPE_C = "神州行";
	public static final String IMS_PLAN_TYPE_H = "其它品牌";

	// 上海免费资源赠送；busi_spec_id所传值！
	public static final int SH_FREERESOURCE_DEPOSIT_SPEC_ID = 3;
	// 银行卡签约代扣及绑定充值业务的操作类型：1.绑定，2.变更卡号，3.解绑，4.变更额度，5.重置支付密码
	public static final int BANK_BIND_OPER_TYPE_BIND = 1;
	public static final int BANK_BIND_OPER_TYPE_CAND_CHARGE = 2;
	public static final int BANK_BIND_OPER_TYPE_CANCLE_BIND = 3;
	public static final int BANK_BIND_OPER_TYPE_AMOUNT_CHARGE = 4;
	public static final int BANK_BIND_OPER_TYPE_RESET_PASSWORD = 5;

	// 银行卡签约代扣及绑定充值业务的业务类型：1.预付费充值绑定，2.后付费绑定代扣
	public static final String BANK_BIND_BUSI_TYPE_BEFORE_FEE = "1";
	public static final String BANK_BIND_BUSI_TYPE_AFTER_FEE = "2";

	// 银行卡签约代扣及绑定充值业务的绑定类型 1：预付费的充值绑定 2：后付费的付账单绑定
	public static final int BANK_BIND_BIND_TYPE_BEFORE_FEE = 1;
	public static final int BANK_BIND_BIND_TYPE_TYPE_AFTER_FEE = 2;

	// 银行卡签约代扣及绑定充值业务的卡类型： 1:借记卡 2:信用卡
	public static final int BANK_BIND_CARD_TYPE_DEPOSIT_CARD = 1;
	public static final int BANK_BIND_CARD_TYPE_CREDIT_CARD = 2;

	// 上海是否有一卡通信息0、没有1、有
	public static final int SUBSCRIBER_HAS_ECARD_INFO = 1;
	public static final int SUBSCRIBER_HSA_NOT_ECARD_INFO = 0;

	// 上海查询免费资源科目级别1、账户级2、用户级3、集团
	public static final short QUERY_FREERESOURCE_ITEM_TYPE_ACCOUNT = 1;
	public static final short QUERY_FREERESOURCE_ITEM_TYPE_SUBSCRIBER = 2;
	public static final short QUERY_FREERESOURCE_ITEM_TYPE_GROUP = 3;

	// 农行进行签约解约 ,渠道来源0：农行侧1：移动侧
	public static final int ABC_BIND_CHANENEL_TYPE_ABC = 0;
	public static final int ABC_BIND_CHANENEL_TYPE_MOBILE = 1;

	// 农行进行签约解约 ,银行卡类型， 0贷记卡 1准贷记卡 2借记卡
	public static final int ABC_BIND_CARD_TYPE_DEPOSIT_CARD = 0;
	public static final int ABC_BIND_CARD_TYPE_PRE_DEPOSIT_CARD = 1;
	public static final int ABC_BIND_CARD_TYPE_CREDIT_CARD = 2;

	// 银行类型：03表示农行，05表示建行，83表示联动优势
	public static final String BANK_CODE_ABC = "03";
	public static final String BANK_CODE_CCB = "05";
	public static final String BANK_CODE_MOVE = "83";

	// 农行进行签约解约,操作类型1：签约，2：解约
	public static final int ABC_BIND_OPER_TYPE_SIGNING = 1;
	public static final int ABC_BIND_OPER_TYPE_NO_SIGNING = 2;

	// cm_resource brand 随E行用户
	public static final int CM_RESOURCE_BRAND_ID_EWALK = 12;

	// 家庭类统付活动副号码余额支持转出功能
	// 设置自动转移功能：1取消自动转移功能：-1

	public static final int AMOUNT_AUTO_TRANSFER_SETTING = 1;
	public static final int AMOUNT_AUTO_TRANSFER_CANCLE = -1;

	// 手机支付，标示类型IDTYPE：01 被缴手机号
	public static final String IDTYPE_IS_PAYMENT_PHONE_ID = "01";
	// 手机支付；boss的单位是分；CBoss单位是厘
	public static final int BOSS_CHARGE_TO_CBOSS = 10;
	// 手机支付折扣率
	public static final String PAY_MENT_DISCOUNT = "100";
	// 手机支付方式：0：手机支付账户缴费方式；1：手机支付网银缴费方式。
	public static final String PAY_MENT_TYPE_ACCOUNT = "0";

	// measure
	// 时长
	public static final int MEASURE_ID_MILLISECOND = 101;// 毫秒单位
	public static final int MEASURE_ID_SEC = 102;// 秒单位
	public static final int MEASURE_ID_MINUTE = 106;// 分钟单位
	public static final int MEASURE_ID_HOUR = 107;// 小时单位
	// 流量
	public static final short MEASURE_ID_KB = 104;

	// 现金充值；支付手机外围有可能传入不规则的手机号
	public static final int PAY_PHONE_ID_SUBSTRING = 2;

	public static final int MONITOR_SOURCE_ID = 13;

	// 查询活动返还金额: 6代表的是活动终止的记录
	public static final int RETURN_FEE_STS_END = 6;
	// http+json成功与否0:成功1：失败
	public static final int HTTP_AND_JSON_SUCC = 0;
	public static final int HTTP_AND_JSON_FAIL = 1;
	// CBOSS超时
	// 连接超时
	public static final int CONNECTION_TIME_OUT = 1200000;
	// 读取超时
	public static final int SO_TIME_OUT = 60000;
	// 批量插入JD.IMS_JSON_SOAP 表线程等待时间系统默认参数60000
	public static final int INSERT_IMS_JSON_SOAP_SLEEP_TIME_DEFAULT = 60000;
	public static final int IM_SH_JSON_SOAP_BATCH_NUMBER_DEFAULT = 250;
	// 充值类型(1：用户 2 帐户)
	public static final int PAY_TYPE_SUB = 1;
	public static final int PAY_TYPE_ACCT = 2;
	// 调用接口超时时间
	public static final int CALL_INTERFACE_TIME_OUT_TIME = 15000;

	public static final int FIRST_ACTIVE_NOTIFY_ID_GX = 60000000;
	public static final int FIRST_ACTIVE_ACTION_ID_GX = 60000000;
	public static final int FIRST_ACTIVE_STATE_ID_GX = 0;

	public static final String PM_WISDOM_LUA_PARAM_RES_AMOUNT = "res_count";
	public static final String PM_WISDOM_LUA_FUNCTION = "return getresfee(res_count)";
	public static final int PM_RENT_FEE_TYPE_MONTH = 1;
	public static final int PM_RENT_FEE_TYPE_daily = 2;
}
