package com.ailk.ims.abmtransfer.commom;

/**
 * 
 * AINBS
 * 
 * @Company: Asiainfo Technologies （China）,Inc. Hangzhou
 * @author Asiainfo R&D deparment(HangZhou)/lizl2
 * @version 1.0 Copyright (c) 2006
 * @date 2011-6-28
 * @description 
 * 
 * 
 * @Modify log 2011-06-28 lizl2 第一版本
 * @Modify log 2012-11-13 lanwj 
 */
public interface Constant {

	/**
	 * 函数调用正确返回
	 */
    public static final int RETURN_SUCCESS = 0;

	/**
	 * 函数调用返回失败
	 */
    public static final int RETURN_FAIL = -1;

	/**
	 * 函数调用返回失败
	 */
    public static final int RETURN_ONCE = -9939;

	/**
	 * 系统统一的状态，记录无效的
	 */
    public static final int STS_INVALID= 2;
	/**
	 * 系统统一的状态，记录撤单的
	 */
    public static final int STS_Unsubscription=0;
	/**
	 * 系统统一的状态, 记录有效的。
	 */
    public static final int STS_VALID = 1;

	/**
	 * 失败状态
	 */
    public static final int STS_FAILTURE = 2;

	/**
	 * 现金账本
	 */  
    public static final int ASSET_TYPE_POKCET=0;

	/**
	 * 信用度
	 */  
    public static final int ASSET_TYPE_CREDIT=1;

	/**
	 * 积分
	 */  
    public static final int ASSET_TYPE_COIN=2;
	/**
	 * 免费资源
	 */  
    public static final int ASSET_TYPE_FREERESOURCE=4;
    /**
	 * 周期性免费资源
	 */ 
    public static final int REMAINING_FREERESOURCE=0;
    /**
	 * 一次性免费资源
	 */ 
    public static final int ONETIME_FREERESOURCE=1;

	/**
	 * 银行资产
	 */  
    public static final int ASSET_TYPE_BANKASSET=3;

	/**
	 * 支票
	 */  
    public static final int ASSET_TYPE_CHECK=5;

	/**
	 * 有效期
	 */  
    public static final int ASSET_TYPE_VALIDITY=6;

	/**
	 * 周期性免费资源
	 */  
    public static final int ASSET_TYPE_OTFREERESOURCE=7;


    ////////////////////
	// 资产科目类型定义-开始
    ////////////////////
	/** 0：免费资源科目 */
    public static final int ASSET_ITEM_FRER_RES=0;

	/**
	 * #1：未定义
	 */
    public static final int ASSET_ITEM_UNDEFINE=1;
	/**
	 * 2：现金科目
	 */
    public static final int ASSET_ITEM_POCKET_COMMON=2;
	/**
	 * 3：专款科目
	 */
    public static final int ASSET_ITEM_POCKET_SPECIAL=3;
	/**
	 * 4：信用度科目
	 */
    public static final int ASSET_ITEM_CREDIT=4;
	/**
	 * 5：积分科目
	 */
    public static final int ASSET_ITEM_COIN=5;
	/**
	 * 6: 专用信用度科目
	 */
    public static final int ASSET_ITEM_CREDIT_SPECIAL=6;
	/**
	 * 7: 通用负余额账本科目
	 */
    public static final int ASSET_ITEM_NEGATIVE_POCKET_COMMON=7;
	/**
	 * 8: 专用负余额账本科目
	 */
    public static final int ASSET_ITEM_NEGATIVE_POCKET_SPECIAL=8;
	/**
	 * 9: 押金科目
	 */
    public static final int ASSET_ITEM_DEPOSIT_POCKET=9;
    
    /**
     * 预存款
     */
    public static final int ASSET_ITEM_ADVANCE_PAYMENT = 11;
    
    /**
     * 代理商保证金
     */
    public static final int ASSET_ITEM_AGENT_DEPOSIT = 12;
    
    ////////////////////
	// 资产科目类型定义-结束
    ////////////////////
     
    /////////////////////
    // 账本信息
    /////////////////////
    public static final String REQ_KEY_OF_ASSET = "REQ_KEY_OF_ASSET/ASSET_CHANGE";
    //更新接口参数 ：CsdlArrayList<SAbmArAsset>
    public static final String REQ_KEY_OF_DEAL = "REQ_KEY_OF_DEAL/ASSET_DEAL";
    //更新接口参数 ：冻结后返回的sessionId
    public static final String REQ_KEY_OF_SESSIONID = "REQ_KEY_OF_SESSIONID/ASSET_SESSIONID";
    //是否提交做mdb端操作，置'1'表示不做mdb操作
    public static final String REQ_KEY_MDB_ACTION = "REQ_KEY_MDB_ACTION/MDB_ACTION";
    //重扣费标识,"1"标识需要做重扣费
    public static final String REQ_KEY_RE_DEDUCT = "REQ_KEY_DEDUCT_STS/RE_DEDUCT";
    //是否是充值 0不是 1是
    public static final String REQ_KEY_TOPUP_FLAG = "REQ_KEY_TOPUP_FLAG/TOPUP_FLAG";
    //是否有资产变更 0或者null表示没有资产变更，不需要上发mdb，1表示有资产变更，需要上发mdb
    public static final String REQ_KEY_HAS_ASSET_CHG = "REQ_KEY_HAS_ASSET_CHG/MDB_ACTION";    
    
    
    /////
	// / billing type 定义开始
    
    /////
	/**
	 * 预付费
	 */
    public static final int BILLING_TYPE_PREPAY  = 0;

	/**
	 * 后付费
	 */
    public static final int BILLING_TYPE_POSTPAY  = 1;
    
    //账本类型
    
	/**
	 * 预后融合类型
	 */
    
    /**
	 * 主账本
	 */
    public static final int MAIN_ASSET  = 0;

	/**
	 * 赠送账本
	 */
    public static final int REWARD_AEEST  = 4;
    
    public static final int BILLING_TYPE_HYBRID  = 2;
    
    public static final int BILLING_TYPE_PRE_HYBRID  = 10;
    
    public static final int BILLING_TYPE_POST_HYBRID  = 11;

	/**
	 * 生命周期状态码用户为ACTIVE
	 */
    public static final int IIFECYCLE_STS_RESOURCE_ACTIVE = 1001;
	/**
	 * 用户为欺诈状态
	 */
    public static final int USER_STS_FRAUD = 1;

	/**
	 * 一次性费用EVENT_TYPE 0=授权算费(只计算费用然后返回结果）；1=正式算费（包括扣费和记录帐单，生成话单）
	 */
    public static final short ONETIMEFEE_EVENT_TYPE_FORMAL = 1;
	/**
	 * 一次性费用 CHARGE_TYPE 外系统计算
	 */
    public static final short ONETIMEFEE_CHARGE_TYPE_INTERFACE = 2;
	/**
	 * 一次性费用类型 账户
	 */
    public static final short ONETIMEFEE_OBJECTTYPE_ACCOUNT = 1;
	/**
	 * 一次性费用立即扣费
	 */
    public static final short  DEDUCT_TYPE_PROMPTLY = 1;

	/**
	 * 一次性费用XC未配置
	 */
    public static final int  no_one_time_fee = 2;

	/**
	 * 内部系统OP_ID
	 */
    public static final long OP_ID_ADMIN = 9999L;

	/**
	 * 通配符
	 */
    public static final int SYS_GROUP_DEF_CODE = -1; 
	// 修改生命枚举
    public static final int LIFECYCLE_EVENT_VALIDITYCHANGE = 15;
    public static final String SPRING_BEAN_IMS_INNERSERVICE = "imsinner_innerService";
    
    
    ///////////////////////////////////////////////////////////
	// 以下定义是充值方式 start //
    ///////////////////////////////////////////////////////////
    public static final int RECHARGE_METHOD_ALL =-1  ;//:ALL
    public static final int RECHARGE_METHOD_VC =1   ;//: VC recharge
    public static final int RECHARGE_METHOD_MANUAL =3   ;//: manual recharge
    public static final int RECHARGE_METHOD_ATM =4   ;//: ATM recharge
    public static final int RECHARGE_METHOD_STK =5   ;//: STK recharge 
    public static final int RECHARGE_METHOD_TELE_BANK =6   ;//: Tele-banking recharge
    public static final int RECHARGE_METHOD_SPARE =7   ;//: Spare
	public static final int RECHARGE_METHOD_CASH = 8;// ：cash
    public static final int RECHARGE_METHOD_CHECK =9   ;//: check
    public static final int RECHARGE_METHOD_PERSONAL =10   ;//: GSM TOP UP
    public static final int RECHARGE_METHOD_CORPORATE =11   ;//: Corporate Top-up
    public static final int RECHARGE_METHOD_AUTO =13   ;//: Corporate Top-up
    ///////////////////////////////////////////////////////////
	// 以上定义是充值方式 end //
    ///////////////////////////////////////////////////////////
    
	/**
	 * create trace;
	 * 
	 */
		
//	0-father and son work order relationship
    public static final int relationship_father = 0; 
//	1-order cancel relationship
    public static final int relationship_cancel = 1; 
    
    
    /////////////
	// 货币转换枚举值 START
    /////////////

	/**
	 * 0:后台处理资金时，使用的度量精确
	 */
    public static final short MEASURE_MAX_ACCURACY = 0;
	/**
	 * 1：主要用于页面展示
	 */
    public static final short MEASURE_IS_DISPLAY = 1; 
    /////////////
	// 货币转换枚举值 END
    /////////////
	/**
	 * 货币类型（指美元，人民而不是元、角、分）
	 */
    public static final short MeasureClass_0 = 0;
	/**
	 * 本国货币标示
	 */
    public static final short LocalCurrency_1 = 1;

	/**
	 * 有效期页面展示
	 */
    public static final String ITEM_NAME_VALIDDATE_NAME = "ValidDate";
	/**
	 * sys_paramter 里 收不收一次性费用的标识
	 */
    public static final String CAL_FEE_FLAG = "CAL_FEE_FLAG";

	/**
	 * sys_paramter里查询信用度是否计算冻结费用
	 */
    public static final String IS_CREDIT_FREEZE_FEE = "IS_CREDIT_FREEZE_FEE";

	/**
	 * 批量操作acctId默认值
	 */
    public static final long BATHCH_ACCT_ID = 9999L;

	// ///////////
	// source_type的枚举值
	// ///////////

	/**
	 * 账户级(默认)
	 */
    public static final int OWNER_TYPE_ACCOUNT = 1;
	/**
	 * 用户级
	 */
    public static final int OWNER_TYPE_RESOURCE = 0;
	/**
	 * 当前用户和账户
	 */
    public static final int OWNER_TYPE_BOTH = 2;

	/**
	 * 所有用户和账户
	 */
	public static final int OWNER_TYPE_ALL = 3;
	
    
    /////////////
	// payment_type的枚举值
    /////////////
    
    public static final int PAY_METHOD_CASH =2  ;//Cash
    public static final int PAY_METHOD_CREDIT_CARD_DEBIT =8  ;//Credit Card Debit
    public static final int PAY_METHOD_BANK_DEBIT =3  ;//Bank Debit
    public static final int PAY_METHOD_EBPP =81  ;//eBPP
    
    /////////////
	// payType的枚举值
    /////////////
    public static final short PAYTYPE_PAYMENT =1  ;//PAYMENT
    public static final short PAYTYPE_ADVANCE_PAYMENT =0  ;//ADVANCE_PAYMENT
    
	// ///////////
	// 信用度枚举
	// ///////////
	/** 无限大信用度默认额度: 999999999000 */
	public static final double CREDIT_DEFAULT_UNLIMITED_VALUE = 999999999000L;
	/** 信用度默认失效期年限 */
	public static final int CREDIT_DEFAULT_EXPIRE_DATE = 30;
	/** credit type枚举：1-账户级信用度 */
	public static final short CREDIT_TYPE_ACCOUNT = 1;
	/** credit type枚举：2-用户级信用度 */
	public static final short CREDIT_TYPE_RESOURCE = 2;
	/** credit type枚举：3-服务级信用度 */
	public static final short CREDIT_TYPE_SERVICE = 3;
	/** credit flag枚举：0-普通信用度 */
	public static final short CREDIT_FLAG_COMMON = 0;
	/** credit flag枚举：1-临时信用度 */
	public static final short CREDIT_FLAG_TEMP = 1;
	/** credit flag枚举：2-后付费账本 */
	public static final short CREDIT_FLAG_POSTPAID = 2;
	/** credit flag枚举：3-无限大普通信用度 */
	public static final short CREDIT_FLAG_UNLIMITED_COMMON = 3;
	/** credit flag枚举：4-无限大临时信用度 */
	public static final short CREDIT_FLAG_UNLIMITED_TEMP = 4;

	/**
	 * 共享免费资源
	 */
	public static final short FREERESOURCE_IS_SHARE = 1;

	// ///////////
	// 页面来访者身份
	// ///////////
	/**
	 * 页面来访者身份枚举：1-客户
	 */
	public static final short PAGE_BUSINESS_TYPE_CUSTOMER = 1;
	/**
	 * 页面来访者身份枚举：2-账户
	 */
	public static final short PAGE_BUSINESS_TYPE_ACCOUNT = 2;
	/**
	 * 页面来访者身份枚举：3-用户
	 */
	public static final short PAGE_BUSINESS_TYPE_RESOURCE = 3;
	/**
	 * 页面来访者身份枚举：4-手机号
	 */
	public static final short PAGE_BUSINESS_TYPE_PHONE = 4;
	/**
	 * 需要发送短信
	 */
	public static final short NEED_NOTIFICATION = 1;
	
	//REV_CODE 类型
	public static final int REV_TYPE_TOPUP = 0;//充值
	public static final int REV_TYPE_ADJUSTMAIN = 1;//调整主账本
	public static final int REV_TYPE_ADJUSTSECOND = 2;//调整赠送账本
	public static final int REV_TYPE_REWARDSECOND = 3;//赠送账本
	
	
    /**
     * 未定义
     */
    public static final int CHANNEL_UNDEFINE = 0;
    
    /**
     * crm 
     */
    public static final int CHANNEL_CRM = 1;
    
    /**
     * 客服中心
     */
    public static final int CHANNEL_CALL_CENTER = 2;
    
    /**
     * 支付中心
     */
    public static final int CHANNEL_PAY_CENTER = 3;
    
    /**
     * USSD
     */
    public static final int CHANNEL_USSD = 4;
    
    /**
     * IVR
     */
    public static final int CHANNEL_IVR = 5;
    
    /**
     * 内部系统
     */
    public static final int CHANNEL_INNER_SYSTEM = 99;	
    
    /**
     * 用户生命周期状态
     */
    public static final int LIFECYCLE_IDLE_INITIAL = 1000;
    public static final int LIFECYCLE_ACTIVE = 1001;
    public static final int LIFECYCLE_SUSPEND_REQUEST = 1002;
    public static final int LIFECYCLE_SUSPEND_DEBT = 1003;
    public static final int LIFECYCLE_SUSPEND_FRAULD = 1004;
    public static final int LIFECYCLE_SUSPEND_CREDITLIMIT = 1005;
    public static final int LIFECYCLE_SUSPEND_NO_BALANCE_VALIDITY = 1006;
    public static final int LIFECYCLE_DISABLE = 1007;
    public static final int LIFECYCLE_TERMINAL = 1008;
    public static final int LIFECYCLE_POOL = 1009;    
    /**
     * 累积量类型0 rating累积量 1 sla累积量
     */
    public static final short ACCUM_TYPE_RATING=0;
    public static final short ACCUM_TYPE_SLA=1;

}