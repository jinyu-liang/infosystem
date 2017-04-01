package com.ailk.ims.define;

import com.ailk.ims.util.CommonUtil;

/**
 * 用于专门定义产品规格及特征值
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj 2011-7-28 wangjt：增加付费模式特征值 2011-08-01 ljc 增加黑白名单规格
 * @Date 2011-7-11
 *       Date 2012-04-17 lijc3 去掉不使用的特征值
 */
public class SpecCodeDefine {
	// normal product ord
	public static final int NORMAL = 101;

	// product budget
	public static final int BUDGET = 107;
	public static final int BUDGET_RULE = 10701;// busi_service_id
	public static final int BUDGET_AMOUNT = 10702;// budgetValue
	// public static final int BUDGET_VALIDDATE = 10703;
	// public static final int BUDGET_EXPIREDATE = 10704;
	// 在CA/GCA订购时，标识预算是作用到CA/GCA，还是作用到下面所有resource；
	// 0：作用到CA/GCA上，1：作用到下面的resource上
	public static final int BUDGET_OBJECT_TYPE = 10705;
	// public static final int BUDGET_CYCLE_UNIT = 10711;
	// public static final int BUDGET_CYCLE_TYPE = 10712;
	public static final int BUDGET_THRESHOLD_VALUE = 10713;// 提醒门限
	// public static final int BUDGET_IS_WARN_INCREASE_THRESHOLD = 10715;
	public static final int BUDGET_VALIDATE_CONDITION = 10717;

	public static final int BUDGET_ACTION = 10718;// 达到阀值后动作 1：nofication、 2
													// ：bar servcie 、3： do
													// nothing
	public static final int BUDGET_THRESHOLD_NOTIFY_TYPE = 10719;// 通知方式
																	// 1：sms、2：email、3：sms&
																	// email
	public static final int BUDGET_PAYMODE = 10720;// 适用计费方式 0：预付费 、1：后付费、2
													// 或空不关注
	public static final int BUDGET_THRESHOLD_NOTIFY_PHONE = 10721;// 联系号码
	public static final int BUDGET_THRESHOLD_NOTIFY_ADDR = 10722;// 联系地址

	public static final int BUDGET_NOTI_RULE_NOTI = 10714;// 提醒规则 nofication
	public static final int BUDGET_NOTI_RULE_BAR = 10723;// 提醒规则 bar service
	public static final int BUDGET_NOTI_RULE_CONTINUE = 10724;// 提醒规则 continue

	public static final int BUDGET_MEASURE_ID = 10706;// 货币单位
	// nofication

	// -------------付费模式(paymode)---------------------//
	public static final int PAYMODE = 116;
	public static final int PAYMODE_HYBRID_RULE = 11600;// 1: Hybrid Rule1; 2:
														// Hybrid Rule2 3:
														// Hybrid Rule3
	public static final int PAYMODE_RATING_SENARIO = 11601;// 计费场景标识 -spec char
															// for rule1
	public static final int PAYMODE_TIME_PERIOD = 11602;// 时段标识 -spec char for
														// rule1
	public static final int PAYMODE_PAYMODE = 11603; // 0:prepaid 1:post-paid
														// -spec char for rule1

	// 转预付费模式标识，信用度用完后，下通电话转预付费，枚举值--- 0: Don’t change；1:Need change
	// public static final int PAYMODE_CHANGE_FLAG = 11610;// --spec char for
	// rule2

	// 服务标识 –GPRS/VOICE/SMS/MMS等-spec char for rule3
	// public static final int PAYMODE_SERVICE_ID = 11621;

	// 阀值单位 -spec char for rule3
	// public static final int PAYMODE_THRESHOLD_UNIT = 11623;

	// 预付标识 --填默认值0—Prepaid -spec char for rule3
	// public static final int PAYMODE_PAYMODE3 = 11624;

	public static final int PAYMODE_THRESHOLD = 11622;// 阀值 -spec char for rule3

	public static final int PAYMODE_NOTI_RULE_NOTI = 11625; // 提醒规则 --spec char
															// for rule3
	// -------------付费模式(paymode) end---------------------//

	// product credit
	public static final int CREDIT = 108;
	public static final int CREDIT_AMOUNT = 10806;

	// 亲情号码规格
	public static final int FRIENDNUMBER = 102;
	public static final int FRIENDNUMBER_NUMBER = 10201;
	public static final int FRIENDNUMBER_VALIDDATE = 10202;
	public static final int FRIENDNUMBER_EXPIREDATE = 10203;
	public static final int FRIENDNUMBER_COUNT = 10204;

	// fax
	public static final int FAX = 124;
	public static final int FAX_NUMBER = 12401;

	// 黑白名单规格
	public static final int MCS_NUMBER = 114;
	public static final int MCS_NUMBER_COUNT = 11400;
	public static final int MCS_NUMBER_NUMBER = 11401;
	public static final int MCS_NUMBER_TYPE = 11402;
	public static final int MCS_NUMBER_RULE = 11403;
	public static final int MCS_NUMBER_SEVICE = 11404;
	// @Date 2012-04-17 lijc3 去掉不使用的特征值
	// public static final int MCS_NUMBER_VALID_DATE = 11405;
	// public static final int MCS_NUMBER_EXPIRE_DATE = 11406;
	public static final int MCS_NUMBER_CALL_SCREEN_TYPE = 11407;
	public static final int MCS_NUMBER_CLIR = 11408;
	public static final int MCS_NUMBER_WHITELIST_LIMT = 11415;
	public static final int MCS_NUMBER_BLACK_LIMIT = 11416;
	public static final int MCS_NUMBER_WEEK_START = 11420;
	public static final int MCS_NUMBER_WEEK_END = 11421;
	public static final int MCS_NUMBER_ROUTE_FLAG = 11422;
	public static final int MCS_NUMBER_ROUTE_NUMBER = 11423;
	public static final int MCS_NUMBER_ROUTE_METHOD = 11424;
	public static final int MCS_NUMBER_NOTIFICATION_FLAG = 11425;
	public static final int MCS_NUMBER_NOTIFICATION_ID = 11426;
	public static final int MCS_NUMBER_EFFECT_TIME = 11427;
	public static final int MCS_NUMBER_EXPIRE_TIME = 11428;
	public static final int MCS_NUMBER_ROUTE_FLAG_DEFAULT_VALUE = 0;// 转移默认值0
	public static final int MCS_NUMBER_GROUP_DEFAULT_VALUE = 0;

	// 群资费产品
	public static final int GROUP_IN_PERSON = 130;
	public static final int GROUP_IN_PERSONAL_ID = 13001;
	public static final int GROUP_NO_PORT_PERSON = 131;
	public static final int GROUP_NO_PORT_PERSONAL_ID = 13101;
	public static final int GROUP_OUT_PERSON = 132;
	public static final int GROUP_OUT_PERSONAL_ID = 13201;

	public static final int GROUP_IN = 135;
	public static final int GROUP_NO_PORT = 136;
	public static final int GROUP_OUT = 137;

	// 群个性化产品 MDB需要的标识
	public static final int GROUP_PERSONAL_PRODUCT = 3;

	// 群间产品
	public static final int INTER_GROUP_PROMOTION = 104;
	public static final int INTER_GROUP_ID = 10401;
	public static final int INTER_GROUP_AUTH = 10402;

	// 普通账户订购的circle call 产品 会自动建群
	public static final int CIRCLE_CALL_SHARING_PROM = 147;

	// @Date 2012-06-06 wangdw5 : [47115]Community Promotion
	public static final int COMMUNITY_PROMOTION = 143;
	public static final int COMMUNITY_GROUP_ID = 14301;

	// 网外号码组
	public static final int OUTNET_PHONE = 103;
	public static final int OUTNET_PHONE_ID = 10301;
	public static final int OUTNET_PHONE_VALID_DATE = 10302;
	public static final int OUTNET_PHONE_EXPIRE_DATE = 10303;

	// 用户主动停机
	public static final int USER_SUSPEND_REQUEST = 123;
	public static final int USER_SUSPEND_REQEUST_FREE_DAYS = 12301;
	public static final int USER_SUSPEND_REQUER_VALIDITY_DAYS = 12302;

	// 收取周期性月费的产品
	public static final int PRODUCT_OFFERING_CYCLE_MONTH_FEE = 113;

	// AIS专有suspend_no_validity用户可以订购的一次性产品（赠送有效期）
	public static final int REWARD_VALIDITY_PROMOTION = 145;

	// Split
	public static final int SPLIT = 128;
	public static final int SPLIT_PRICE_RULE_ID = 12801;// price_rule_id
	public static final int SPLIT_AMOUNT = 12802;// 分账限额，单值
	public static final int SPLIT_NUMERATOR = 12803;// 百分比分子
	public static final int SPLIT_DENOMINATOR = 12804;// 百分比分母
	public static final int SPLIT_OBJECT_TYPE = 12805;// 被代付对象类型 :0:Dev1:Account
	public static final int SPLIT_OBJECT_ID = 12806;// 被代付对象标识(账户ID或者用户ID)
	public static final int SPLIT_PRIORITY = 12807;// 优先级

	public static final int SPLIT_TYPE = 12812; // 0:科目级代付、1:产品级代付
	public static final int SPLIT_PRODUCT_ID = 12813; // 被代付产品编号
	public static final int SPLIT_METHOD = 12815; // 代付方式 0：一般代付（默认） 1：统付
	public static final int SPLIT_MEASURE_ID = 12814;

	public static final int SPLIT_PAY_SUCC_NOTI = 12808;// 代付者成功提醒规则
	public static final int SPLIT_BEPAID_SUCC_NOTI = 12809;// 被代付者成功提醒规则
	public static final int SPLIT_PAY_FAIL_BAR = 12810;// 代付者失败提醒规则
	public static final int SPLIT_BEPAID_FAIL_BAR = 12811;// 被代付者失败提醒规则
	// 用户级被代付对象的归属账户编号
	public static final int SPLIT_PAID_ACCT_ID = 12816;
	// 集团统付代付规则 add by songcc 2014-2-12
	public static final int SPLIT_ACCT_MAIN_USER_ID = 12817;
	// HN add 
	public static final int SPLIT_FILL_TYPE = 12818; //代付类型，0:作废  1:转入用户对应默认账户  2:用户账单强制补足 3:累计到下月使用
	public static final int SPLIT_BIND_TYPE = 12819; //虚科目代付规则：0账目优先级代付  1按贡献比
	public static final int PAY_ACCT_ID = 12820;//代付账户标识

	// REGUIDE_CHARGE
	public static final int REGUIDE_CHARGE = 106;
	// public static final int REGUIDE_CHARGE_SPEC_ID = 10601;//
	// 代付规格（PM_PAYFOR_SPEC.REGULATION_SPEC_ID）
	public static final int RC_OBJECT_TYPE = 10602;// 被代付对象类型 :0:Dev1:Account
	public static final int RC_OBJECT_ID = 10603;// 被代付对象标识(用户ID或者账户ID)
	public static final int RC_PRIORITY = 10604;// 优先级
	public static final int RC_BUSI_SERVICE_ID = 10605;// 代付规则（PM_PAYFOR_REGULATION.PRICE_RULE_ID），分为全业务代付、分业务代付（比如GPRS/VOICE等等）
	public static final int RC_AMOUNT = 10606;// 分账限额，单值
	public static final int RC_NUMERATOR = 10607;// 百分比分子
	public static final int RC_DENOMINATOR = 10608;// 百分比分母
	// public static final int REGUIDE_CHARGE_FREE_ITEM = 10609;// 科目ID,针对免费资源代付
	// public static final int REGUIDE_CHARGE_FREE_RESOUSE = 10610;//
	// 代付resource，限定免费资源从哪个上产生的。
	// public static final int REGUIDE_CHARGE_PERIOD_UNIT = 10611;// 周期单位
	// public static final int REGUIDE_CHARGE_PERIOD_TYPE = 10612;// 周期类型
	public static final int RC_WARN_MAXVALUE = 10613;// 代付者提醒阀值
	public static final int RC_TARGET_WARN_MAXVALUE = 10614;// 被代付者提醒阀值
	// 代付对象（被代付者）提醒阀值
	public static final int RC_TIME_PERIOD = 10615;// 时段条件（policy）

	public static final int RC_PAY_SUCC_NOTI = 10616;// 代付者成功提醒规则nofication
	public static final int RC_BEPAID_SUCC_NOTI = 10617;// 被代付者成功提醒规则nofication
	public static final int RC_PAY_FAIL_BAR = 10618;// 代付者失败提醒规则bar service
													// nofication
	public static final int RC_BEPAID_FAIL_BAR = 10619;// 被代付者失败提醒规则bar service
														// nofication
	public static final int RC_VALID_RULE = 10620; // 生效规则

	public static final int RC_MEASURE_ID = 10621;// 货币编号 lijc3

	public static final int RC_B_NUMBER = 10625;// 未定义
	// public static final int RC_THREHOLD = 10621;// 未定义
	// public static final int RC_NOTIFY_TYPE = 10622;// 未定义
	// public static final int RC_NOTIFY_ADDR = 10623;// 未定义
	// public static final int RC_NOTIFY_PHONE = 10624;// 未定义

	// 不可识别停机
	public static final int USER_SUSPEND_UNIDENTIFIED = 133;

	// REGUIDE_USAGE
	public static final int REGUIDE_USAGE = 120;
	public static final int REGUIDE_USAGE_POLICY_ID = 12001;// 业务场景(可选，如果没有指定，默认填0，全业务场景)
	public static final int RU_TIME_PERIOD = 12002;// 时段(可选，如果没有指定，默认填0，全时段)
	public static final int RU_USER_ID = 12003;// 被re-guide usage
												// 的号码(填resource_id),必填
	public static final int RU_B_NUMBER = 12004;// guide usage
												// 允许的对端号码(可选，如果没有则为全部)
	public static final int RU_BUSI_SERVICE_ID = 12005;// 代付的服务标识(比如gprs、ir、voice)

	public static final int RU_PAY_SUCC_NOTI = 12006;// 代付者成功提醒规则nofication
	public static final int RU_BEPAID_SUCC_NOTI = 12007;// 被代付者成功提醒规则nofication
	public static final int RU_PAY_FAIL_BAR = 12008;// 代付者失败提醒规则bar service
													// nofication
	public static final int RU_BEPAID_FAIL_BAR = 12009;// 被代付者失败提醒规则bar service
														// nofication

	public static final int RU_PRIORITY = 12015;// 优先级 // 未定义
	public static final int RU_MEASURE_ID = 12016;// 货币编号
	// public static final int RU_MAX_VALUE = 12016; // 未定义
	// public static final int RU_AMOUNT = 12017;// 分账限额，单值 // 未定义
	// public static final int RU_THREHOLD = 12018; // 未定义
	// public static final int RU_NOTIFY_TYPE = 12019;// 未定义
	// public static final int RU_NOTIFY_ADDR = 12020;// 未定义
	// public static final int RU_NOTIFY_PHONE = 12021;// 未定义

	// 固费二次计价参数
	public static final int PRICE_PARAM_ID_RC_BASE_FEE = 820001;

	public static final int MAIN_PROD_SPEC_CHAR_11628 = 11628;
	public static final int MAIN_PROD_SPEC_CHAR_12000 = 12000;

	// 国际漫游规格
	public static final int IR_OUT = 122;
	public static final int IR_OUT_FLAG = 12201;
	public static final int IR_OUT_COUNTRY = 12202;
	public static final int IR_OUT_TIME_DIFFERENCE = 12203;

	public static final int GPRS = 105;
	public static final int SLA = 117;
	public static final int SLA_SPEED = 11701;
	public static final int MULTI_SIM = 129;
	public static final int MULTI_SIM_NUMBER = 12901;
	public static final int MULTI_SIM_SERIAL_NO = 12902;// 临时定义，提了DB变更单了，等完成后修改

	// E-TOPUP产品
	public static final int E_TOP_UP = 134;
	public static final int E_TOP_UP_TO_PHONE = 13401;
	public static final int E_TOP_UP_AMOUNT_LIMIT = 13402; // 0 表示crm没有输入
	// 0： bank 1: credit card   2: other post-paid account  3: other pre-paid
	// account
	public static final int E_TOP_UP_PAY_MODE = 13403; // 使用0 和 1
	public static final int E_TOP_UP_BANK_CODE = 13404; // 不使用
	public static final int E_TOP_UP_BANK_ACCOUNT = 13405; // 不使用
	public static final int E_TOP_UP_BANK_ACCOUNT_NAME = 13406; // 不使用
	public static final int E_TOP_UP_CREDIT_CARD = 13407; // 不使用
	public static final int E_TOP_UP_CREDIT_CARD_EXPIRE_DATE = 13408; // 不使用
	public static final int E_TOP_UP_PAY_ACCOUNT = 13409; // 付费方式 IN( 2,3) 不使用
	public static final int E_TOP_UP_NOTIFY = 13410; // 充值时是否需要提醒  0: 否， 1: 是
	public static final int E_TOP_UP_NOTIFY_IDENTIFY = 13411; // 不使用
	// 0: every week   1: every month  2: low balance (银行自动充值使用)
	public static final int E_TOP_UP_TIGGER_TYPE = 13412; // 使用2
	public static final int E_TOP_UP_DATE = 13413; // 充值日期, 按周时, 0: Sunday 1:
													// Monday … 6: Saturday;
													// 按月时，1..28
	public static final int E_TOP_UP_LOW_BALANCE = 13414;

	// 存储每次自动充值的总额
	public static final int E_TOP_UP_AMOUNT = 13415;
	// pre_match
	public static final int PREMATCH = 121;

	public static final int SPECIAL_NUMBER = 115;
	public static final int SPECIAL_NUMBER_NUMBER = 11501;
	public static final int HOME_ZONE = 112;
	public static final int HOMEZ_ZONE_FLAG = 11200;
	public static final int HOME_ZONE_CELL = 11202;
	public static final int HOME_ZONE_CELL_OPPOSITE = 11204;
	// 2012-09-27 gaoqc5 #60007 两城一家
	public static final int TWO_CITY_ONE_HOME = 210;
	public static final int TWO_CITY_ONE_HOME_CELL = 21001;

	// Override
	public static final int OVERRIDE = 138;
	// override对方的产品id
	public static final int OVERRIDE_PRODUCT_ID = 13801;
	// 原来的定价计划，
	public static final int OLD_PRICE_PLAN_ID = 13802;
	// UPSELL产品
	public static final int UP_SELL = 139;

	// @Date 2012-7-27 二次议价参数枚举值修改，huangyl邮件
	public static final int GROUP_MEMBER_NUMBER_PROD_PARAM_ID = 800332;
	// 群类型进行计价计算，不入库
	public static final int GROUP_TYPE_PROD_PARAM_ID = 95555;

	public static final int CALC_PARA_RC_BASE_FEE = 820001;
	public static final int CALC_PARA_RC_BASE_FEE_MEASURE = 820002;

	public static final int CALC_YEAR_PROM_FEE = 820101;
	public static final int CALC_YEAR_PROM_FEE_MEASURE = 820102;
	public static final int FREE_RESOURCE_REWARD = 810301;
	public static final int FREE_RESOURCE_REWARD_MEASURE = 810302;
	public static final int FREE_RESOURCE_REWARD_DISCOUNT = 810321;
	public static final int BASE_RATING_FEE_DISCOUNT = 810001;

	// 押金划拨二次议价值 
	public static final int DEPOSIT_CUR_AMOUNT = 820006;
	public static final int DEPOSIT_REMAIN_ACCOUNT = 820007;
	public static final int DEPOSIT_BIND_DATE = 820008;
	
	// upselling 计价计算，不入库
	public static final int UPSELLING_FLAG = 9021;

	// 主动退订产品计价参数
	public static final int TERMINATE_PROD_FLAG = 820501;

	public static final int CANCEL_DELAY_STOP = 306;

	public static final int USER_REMIND = 305;
	public static final int USER_REMIND_TYPE = 30501;
	public static final int USER_REMIND_CHANNEL = 30502;
	public static final int USER_REMIND_FLAG = 30503;
	public static final int USER_REMIND_BEGIN = 30504;
	public static final int USER_REMIND_END = 30505;

	// SH需求
	// 产品订购分数，规格特征值TODO
	public static final int PROD_COUNT = 810002;

	
	/**
	 * 判断是否为特殊产品，即非CRM产品
	 */
	public static boolean isSpecialProd(int busiFlag) {
		int[] specialBusiFlag = ColCodeDefine.SPECIAL_BUSI_FLAG;// , PM_HR2,
																// PM_HR3
		return CommonUtil.isIn(busiFlag, specialBusiFlag);
	}

	/**
	 * 判断产品是否需要变更账期
	 */
	public static boolean needChangeBillCycle(int busiFlag) {
		// BUDGET,
		int[] specialBusiFlag = { SPLIT, REGUIDE_CHARGE, REGUIDE_USAGE, PAYMODE };// ,
																					// PM_HR2,
																					// PM_HR3
		return !CommonUtil.isIn(busiFlag, specialBusiFlag);
	}

	/** -------------------上海需求中的产品规格----------------------------------- **/
	public static final int HOMEGATE = 201;
	public static final int HOMEGATE_LAC_ID = 20101;
	public static final int HOMEGATE_SAC_ID = 20102;
	public static final int SMSNOTIFY_EWALKFLOW_ID = 30101; // 随e行上网本免费流量
	public static final int SMSNOTIFY_USERPACKAGE_ID = 30102;// 用户套餐免费资源
	// public static final int SMSNOTIFY_MOBILEDATA_FLOW_ID=30103;//移动数据流量
	// public static final int SMSNOTIFY_GPRS_ID=30104;//定期发送gprs流量
	// public static final int SMSNOTIFY_WLAN_ID=30105;//定期发送wlan流量
	
	// 提醒特征规格值
	public static final int REMIND_NOTIFY_30320 = 30320;
	public static final int REMIND_NOTIFY_30321 = 30321;
	
	// 信控服务特征
	public static final int CREDIT_SERV_ID = 37001; //信用服务类型
	public static final int CREDIT_VALUE = 37002; //信用服务额度
	public static final int CREDIT_LIMIT_TIME = 37003; //信用服务截至时间
	
	

}
