package com.ailk.ims.define;
/**
 * @Description:告警相关的编码定义            
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-13
 */
public class AlarmCodeDefine {
	//******************************告警场景
	//三户新装
	//首次激活
	//换卡换号
	//更改账户
	//更改产品
	//更改产品 订购IR产品
	public static final int ALARM_CHANGE_SERVICE_BY_CRM_IR=3010502;
	//设置信用度
	public static final int ALARM_SET_CREDIT_LIMIT = 3010601;
	//设置预算
	public static final int ALARM_SET_BUDGET = 3010701;
	//设置最大资源值
	public static final int ALARM_SET_MAXIMUN_FREE_RESOURCE = 3010801;
	//设置信用度
	public static final int ALARM_SET_EXEMPT_CREDIT_LIMIT = 3010901;
	//生命周期状态即将失效
	public static final int ALARM_LIFECYLE_STATUS_WILL_EXPIRE = 3011001;
	// 用户有效期即将失效
	public static final int ALARM_USER_VALIDITY_WILL_EXPIRE = 3011003;
	
	//产品即将到期
	public static final int ALARM_EXPIRE_PROD_NOTIFY = 3011101;
	//产品生效前提醒
	public static final int ALARM_VALID_PROD_NOTIFY = 3011102;
	//产品周期提醒
	public static final int ALARM_PROD_CYCLE_NOTIFY = 3011103;
	//产品周期提醒包含免费资源
	public static final int ALARM_PROD_CYCLE_NOTIFY_WITH_FREE_RES = 3011104;
	
	//现金充值
	public static final int ALARM_TOPUP_BY_CASH = 1020701;
	//充值卡充值
	public static final int ALARM_TOPUP_BY_VOUCHER_CARDS = 1020704;
	
	// 生命周期状态变更
	public static final int ALARM_LIFECYCLE_STS_CHANGE = 3011002;
	
	//扣费失败 产品状态变更suspend
	public static final int ALARM_DEDUCT_FAIL = 3011401;
	//扣费成功 产品状态变更active
	public static final int ALARM_DEDUCT_SUCCESS = 3011402;
	//扣费成功 产品状态变更active 修改首次出账日
	public static final int ALARM_DEDUCT_SUCCESS_MODIFY_CYCLE = 3011403;
	
	//changePayMode
	public static final int ALARM_CHGPAYMODE_PPSPOS_TO_HYBRID = 3011501;
	public static final int ALARM_CHGPAYMODE_HYBRID_TO_PPSPOS = 3011502;
	public static final int ALARM_CHGPAYMODE_CHG_HYBRIDRULE = 3011503;
	public static final int ALARM_CHGPAYMODE_CHGPAYMODE = 3011504;
	public static final int ALARM_CHGPAYMODE_PPSPOS_SHIFT = 3011508;
	public static final int ALARM_CHGPAYMODE_threshold = 3011006;
	
	//******************告警参数值
	//客户称谓，如Mr/Ms
	public static final int PARAM_CUST_TITLE = 9001;
	//客户名称
	public static final int PARAM_FIRST_NAME = 9002;
	//客户姓氏
	public static final int PARAM_FAMILY_NAME = 9003;
	//手机号码
	public static final int PARAM_PHONE_ID = 3004;
	//生效期
	public static final int PARAM_VALID_DATE = 8001;
	//失效期
	public static final int PARAM_EXPIRE_DATE = 8002;
	//更改前的手机号
	public static final int PARAM_OLD_PHONE = 3007;
	//更改后的手机号
	public static final int PARAM_NEW_PHONE = 3008;
	//更改前的SIM号
	public static final int PARAM_OLD_SIM = 3009;
	//更改后的SIM号
	public static final int PARAM_NEW_SIM = 3010;
	//余额
	public static final int PARAM_BALANCE = 3011;
	//信用度
	public static final int PARAM_CREDIT_LIMIT = 3012;
	//余额失效期
	public static final int PARAM_VALIDITY = 3013;
	//账户编号
	public static final int PARAM_ACCT_ID = 3014;
	//更改后的新账户
	public static final int PARAM_NEW_ACCT_ID = 3015;
	//对象编号
	public static final int PARAM_OBJECT_ID = 3016;
	//操作类型
	public static final int PARAM_OPER_TYPE = 3017;
	//销售品id
	public static final int PARAM_OFFERING_ID = 3018;
    //销售品id（用于一个模板中有两个销售品id的情况，如更换主产品中旧主产品）暂时使用其他模板中的id
    public static final int PARAM_OFFERING_ID_2 = 2030;
	//共享最大值
	public static final int PARAM_AMOUNT  = 3019;
	//预算等级
	public static final int PARAM_LEVEL  = 3020;
	//预算值
	public static final int PARAM_BUDGET  = 3021;
	//信用度类型
	public static final int PARAM_CREDIT_TYPE  = 3022;
	// 生命周期当前状态
	public static final int PARAM_LIFECYCLE_STATUS = 3023;
	// 生命周当前状态失效时间
	public static final int PARAM_LIFECYCLE_STATUS_EXPIREDATE = 3024;
	// 状态在几天后失效
	public static final int PARAM_LIFECYCLE_STATUS_EXPIRE_AFTER_DAYS = 3025;
	
	public static final int EXEMPT_CREDIT_OPER_TYPE = 3026;
	
	// 生命周期状态变更的旧状态
	public static final int PARAM_LIFECYCLE_OLD_STS = 3028;
	
	// 生命周期状态变更的新状态
	public static final int PARAM_LIFECYCLE_NEW_STS = 3029;
	
	// 生命周期状态变更的新状态失效时间
	public static final int PARAM_LIFECYCLE_STS_CHANGE_EXPIREDATE = 3024;
	
	//产品订购国家名称简写
	public static final int PARAM_PROD_NAME_ABBREVIATION=3030;
	
	//免费资源赠送值
	public static final int PARAM_FREE_RESOURCE_TOTAL = 3032;
	//免费资源剩余值
	public static final int PARAM_FREE_RESOURCE_REMAIN = 3033;
	//产品免费试用天数
	public static final int PARAM_FREE_DAYS = 3040;
	//产品免费试用剩余天数
    public static final int PARAM_FREE_REST_DAYS = 3041;
    //扣费时间
    public static final int PARAM_DEDUCT_TIME = 3043;
    //扣费金额
    public static final int PARAM_DEDUCT_AMOUNT = 3042;
    //产品新状态
    public static final int PARAM_PROD_NEW_STS = 3044;
    //出帐日
    public static final int PARAM_BILL_DATA = 3045;
    
    //hybrid_rule
    public static final int PARAM_HYBRID_RULE = 3046;
    
	//对象类型
	public static final int PARAM_OBJECT_TYPE = 3034;
	// 低余额 阀值
	public static final int PARAM_LOW_BALANCE_THRESHOD  = 3047;
	//充值金额
	public static final int PARAM_TOPUP_AMOUNT = 1022;
	//充值剩余金额
	public static final int PARAM_TOPUP_BALANCE = 1007;
	//充值时间
	public static final int PARAM_TOPUP_DATE = 1003;
	//资产类型
	public static final int PARAM_ITEM_TYPE = 1001;
	//充值卡卡号
	public static final int PARAM_VOUCHER_CARDS = 1042;
	//生效时间
	public static final int PARAM_VALIDDATE = 1004;
	//失效时间
	public static final int PARAM_EXPIREDATE = 1005;
	//可用余额
	public static final int PARAM_AVAILABLE_BALANCE = 4001;
	// 余额临界值 
	public static final int PARAM_BALANCE_THRESHOLD = 4002;
	//旧余额
	public static final int PARAM_OLD_AMOUNT = 4005;
	//新余额
	public static final int PARAM_NEW_AMOUNT = 4006;
	//支付方式
	public static final int PARAM_BILL_TYPE = 4007;
	//brand ID
	public static final int PARAM_BRAND_ID = 1073;
	//account type
	public static final int PARAM_ACCOUNT_TYPE = 1089;
	
	// change master number
	public static final int PARAM_CHG_MASTER_NUM_ACCTID = 1036;
	public static final int PARAM_CHG_MASTER_NUM_NEW_MASTER_NUM = 3035;
	public static final int PARAM_CHG_MASTER_NUM_old_MASTER_NUM = 3036;
	
	// manage single balance
	public static final int PARAM_MANAGE_SINGLE_NEW_ACCT_ID = 3038;
	public static final int PARAM_MANAGE_SINGLE_old_ACCT_ID = 3039;
	
	public static final int PARAM_LIFECYCLE_OLD_FRAULD_FLAG = 3048;
	public static final int PARAM_LIFECYCLE_NEW_FRAULD_FLAG = 3049; 
	
	public static final int PARAM_LIFECYCLE_OLD_USERREQ_FLAG = 3050;
    public static final int PARAM_LIFECYCLE_NEW_USERREQ_FLAG = 3051;
    
    //操作时间
    public static final int PARAM_CHGPAYMODE_OPERTIME = 3052;
    //主销售品ID
    public static final int PARAM_CHGPAYMODE_MAINOFFERID = 8010;
    //Change PaymentMode(Postpaid to prepaid or prepaid to postpaid) 旧模式
    public static final int PARAM_CHGPAYMODE_OLDPAYMODE = 7021;
    //Change PaymentMode(Postpaid to prepaid or prepaid to postpaid) 新模式
    public static final int PARAM_CHGPAYMODE_NEWPAYMODE = 7022;
    
    //changePayMode
    //阀值
    public static final int PARAM_THRESHOLD_VALUE= 3051;
    
    //产品订购失败时间
    public static final int PARAM_PROD_FAIL_DATE = 3056;
    //产品名称
    public static final int PARAM_PROD_NAME = 3057;
    //失败原因
    public static final int PARAM_PROD_FAIL_REASON = 3058;
  
    
    //split
    //产品id
    public static final int PARAM_PRODUCT_OFFERING_ID = 8010;
    //被分账对象
    public static final int PARAM_SPLIT_OBJECT_ID = 3055;
    //被分账类型
    public static final int PARAM_SPLIT_OBJECT_TYPE = 7006;  
    //分账模式
    public static final int PARAM_SPLIT_PART = 7026;
    //分账额度
    public static final int PARAM_SPLIT_VALUE = 3053;
    //支付账户
    public static final int PARAM_PAY_ACCOUNT = 3054;
    
    //reguide
    //被代付对象
    public static final int PARAM_REGUIDE_OBJECT_ID = 3057;
    //被代付对象类型
    public static final int PARAM_REGUIDE_OBJECT_TYPE = 7031;
    //代付类型
    public static final int PARAM_REGUIDE_TYPE = 7028;
    //代付模式
    public static final int PARAM_REGUIDE_PART = 7029;
    //代付额度
    public static final int PARAM_REGUIDE_VALUE = 3056;
    //代付对象id
    public static final int PARAM_PAY_OBJECT_ID = 3060;
    //代付对象类型
    public static final int PARAM_PAY_OBJECT_TYPE = 7030;
    //原代付金额
    public static final int PARAM_OLD_REGUIDE_VALUE = 3061;
    //已代付金额
    public static final int PARAM_PAYED_REGUIDE_VALUE = 3062;
}
