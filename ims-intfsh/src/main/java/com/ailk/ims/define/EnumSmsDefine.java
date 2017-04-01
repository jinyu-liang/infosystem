package com.ailk.ims.define;

public class EnumSmsDefine 
{
	//短信提醒类的销售品id
	public static final int SMS_NOTIFY_WLAN_OFFERID = 10000012;//WLAN剩余时长提醒,            有订购才提醒
	public static final int SMS_NOTIFY_CYCLE_DATE_OFFERID = 10000004;//日周期提醒,                有订购才提醒
	public static final int SMS_NOTIFY_CYCLE_WEEK_OFFERID = 10000005;//周周期提醒,                有订购才提醒
	public static final int SMS_NOTIFY_CYCLE_MONTH_OFFERID = 10000006;//月周期提醒,            有订购才提醒
	public static final int SMS_NOTIFY_AUTO_ADD_OFFERID = 91190001;//月周期提醒,          移动数据套餐自动叠加包
	public static final int SMS_NOTIFY_USER_PACKAGE_OFFERID = 10000016;//套餐免费资源提醒,   有订购才提醒

	public static final int SMS_NOTIFY_GPRS_LOWFLOW_OFFERID = 10000008;//GPRS低流量提醒,                     有订购不提醒 
	public static final int SMS_NOTIFY_GPRS_OFFERID = 10000009;//GPRS定期流量提醒,                                     有订购不提醒 
	public static final int SMS_NOTIFY_MOBLIE_DATA_OFFERID = 10000013;//GPRS沉默提醒(移动数据流量提醒),  有订购不提醒
	public static final int SMS_NOTIFY_EWALK_OFFERID = 10000019;//随E行上网本提醒,                                       有订购不提醒
	
    // 短信审核总表
    public static final long IM_SH_FREERES_MODE_CODE = 60000000L;
    // 短信模板编码
//	public static final long IM_SH_WLAN_CMCC_SEND_CODE = 60000001L;// WLAN免费资源CMCC科目未使用提醒模板  ---已合并到600003
//	public static final long IM_SH_WLAN_CMCC_EDU_SEND_CODE = 60000002L;// WLAN上网免费资源CMCC_EDU未使用提醒模板    ---已合并到600003
	public static final long IM_SH_WLAN_CMCC_CODE = 60000003L;//  WLAN免费资源CMCC、CMCC-EDU、CMCC-AUTO科目沉默用户提醒
	public static final long IM_SH_EWALK_SEND_CODE = 60000004L;// 随e行上网本免费流量使用情况
	public static final long IM_SH_USER_PACKAGE_CODE = 60000005L;// 用户套餐免费资源提醒
	public static final long IM_SH_MOBLIE_DATA_FLOW_CODE = 60000006L;// 移动数据流量未使用沉默用户提醒
	public static final long IM_SH_GPRS_FLOW_CODE = 60000007L;// 定期发送gprs流量提醒
	public static final long IM_SH_WLAN_FLOW_CODE = 60000008L;// 定期发送wlan流量提醒
	public static final long IM_SH_RES_VALIDITY_ECARD_RETAIN_TIME__CODE2 = 60000009L;//定期发送用户有效期到期提醒-易通卡-保留期停
	public static final long IM_SH_RES_VALIDITY_ECARD_RETAIN_TIME__CODE = 60000010L;// 定期发送用户有效期到期提醒-易通卡-保留期停
	public static final long IM_SH_RES_VALIDITY_NOT_ECARD_RETAIN_TIME__CODE = 60000020L;// 定期发送用户有效期到期提醒-非易通卡-保留期停
	public static final long IM_SH_VALID_BILL_CYCLE_STOP_PHONE_CODE = 60000011L; // 有效期停机 短信提醒短信编号
	public static final long IM_SH_CYCLE_NOTIFY_LAST_RES_CODE = 60000012L;// GPRS流量周期提醒---提醒上帐期免费资源
	public static final long IM_SH_CYCLE_NOTIFY_SELECT_AUTO_ADD_PACKAGE_CODD= 60000012L;// 周期提醒模板_开通自动叠加包
	public static final long IM_SH_CYCLE_NOTIFY_N0_SELECT_AUTO_ADD_PACKAGE_CODD= 60000013L;//期提醒模板_未开通自动叠加包
//	public static final long IM_SH_CYCLE_NOTIFY_LAST_CAL_CODE = 60000013L;// GPRS流量周期提醒---提醒上帐期累积量  --只有60000012/60000013
//	public static final long IM_SH_CYCLE_NOTIFY_CUL_RES_CODE = 60000014L;// GPRS流量周期提醒---提醒当前帐期免费资源--只有60000012/60000013
//	public static final long IM_SH_CYCLE_NOTIFY_CUL_CAL_CODE = 60000015L;// GPRS流量周期提醒---提醒当前帐期累积量--只有60000012/60000013
	public static final long IM_SH_GPRS_LOW_FLOW__GPRS_CODE =60000017L; // GPRS低流量提醒--免费资源类型为6000031—其他免费资源类型
	public static final long IM_SH_GPRS_LOW_FLOW_DATA_CODE = 60000016L; // GPRS低流量提醒--移动数据流量
	public static final long IM_SH_FREERES_HALF_USE_CODE = 60000018L; // 免费资源落后进度提醒
}
