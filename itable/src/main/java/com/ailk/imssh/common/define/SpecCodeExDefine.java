package com.ailk.imssh.common.define;

import com.ailk.ims.define.SpecCodeDefine;

/**
 * @Description 个性化产品规格特征值定义
 * @author lijc3
 * @Date 2012-7-30
 */
public class SpecCodeExDefine {
	public static final int USER_SWITCH = 204;
	public static final int USER_SWITCH_TJ = 370;
	public static final int USER_SWITCH_SERV_CODE = 20401;
	public static final int USER_SWITCH_OPEN_FLAG = 20402;

	public static final int RETAIN_PROD = 213;// 保留期停产品
	public static final int DAY_PROD = 212;// 日保号停产品
	public static final int NO_PLAN_REWARD = 214;// 无套餐赠送首条话单赠送产品
	/**
	 * 产品特征值
	 */
	public static final int SPEC_CHAR_ID = 10805;
	/**
	 * 产品特征值10808功能type组合获销售品编号
	 */
	public static final int OFFER_SPEC_CHAR_ID = 10808;
	public static final int[] NO_DELETE_PRODUCT = new int[] { NO_PLAN_REWARD, RETAIN_PROD, DAY_PROD, SpecCodeDefine.SPLIT, USER_SWITCH,
			SpecCodeDefine.USER_SUSPEND_REQUEST };

	/**
	 * 多渠道提醒BUSI_FLAG值
	 */
	public static final int MULTI_NOTICE_BUSI_FLAG = 303;

	/**
	 * 多渠道提醒内容范围特征值
	 */
	public static final int MULTI_NOTICE_CONTENT_TYPE_SPEC_CHAR_ID = 30302;
	/**
	 * 多渠道提醒发送方式特征值
	 */
	public static final int MULTI_NOTICE_SEND_METHOD_SPEC_CHAR_ID = 30303;

	// 个性化提醒参数编码
	public static final int REMIND_NOTIFY_30320 = 30320;
	public static final int REMIND_NOTIFY_30321 = 30321;
	
	//信用服务销售品
	public static final int USER_CREDIT_SERV = 370;
	public static final int CREDIT_SERV_ID = 37001;
	public static final int CREDIT_SERV_VALUE = 37002;
	public static final int CREDIT_LIMIT_TIME = 37003;
	
	public static final int PROD_BUSI_FLAG_108 = 108;
	
	public static final int SPEC_10801 = 10801;
	public static final int SPEC_10802 = 10802;
	public static final int SPEC_10803 = 10803;
}
