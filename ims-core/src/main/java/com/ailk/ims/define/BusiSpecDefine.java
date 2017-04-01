package com.ailk.ims.define;
/**
 * @Description: 业务规格类型定义         
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-25
 */
public class BusiSpecDefine {


    /*************************** 触发告警的业务操作类型 **********************/
    // 产品
    public static final int NOTIFY_PROD_ADD = 1;
    public static final int NOTIFY_PROD_DEL = 2;
    public static final int NOTIFY_PROD_MOD = 3;
    public static final int NOTIFY_PROD_EXT = 4;
    public static final int NOTIFY_PROD_CHGPAYMODE = 5;
    public static final int NOTIFY_PROD_ADD_IR = 6;
    public static final int NOTIFY_VALID = 7;
    public static final int NOTIFY_EXPIRE = 8;
    public static final int NOTIFY_CYCLE = 9;
    public static final int NOTIFY_CYCLE_WITH_FREERES = 10;
    public static final int NOTIFY_TRIAL = 11;
    
    //产品状态变更
    public static final int NOTIFY_DEDUCT_SUCCESS = 1;                  //1: 扣费成功，产品状态变更active
    public static final int NOTIFY_DEDUCT_SUCCESS_MODIFY_CYCLE = 2;               //2: 扣费成功，修改产品首次出账日，状态变更active
    public static final int NOTIFY_DEDUCT_FAIL = 3;  //3：扣费失败，产品状态变更suspend

    // budget
    public static final int NOTIFY_BUDGET_SET = 1;
    public static final int NOTIFY_BUDGET_DEL = 2;

    // lifecycle
    public static final int NOTIFY_LIFECYCLE_STS_EXPIRE = 1;
    public static final int NOTIFY_LIFECYCLE_STS_CHANGE = 2;
    public static final int NOTIFY_LIFECYCLE_VALIDITY_EXPIRE = 3;
    public static final int NOTIFY_LIFECYCLE_FRAULD_FLAG_CHANGE = 4;
    public static final int NOTIFY_LIFECYCLE_USERREQ_FLAG_CHANGE = 5;

    // 换卡换号
    public static final int NOTIFY_CHANGE_MSISDN = 0;
    public static final int NOTIFY_CHANGE_SIM = 1;
    public static final int NOTIFY_CHANGE_MSISDNANDSIM = 2;

    // 免催免停
    public static final int NOTIFY_SETEXEMPTCREDIT_ADD = 1;
    public static final int NOTIFY_SETEXEMPTCREDIT_DEL = 2;
    public static final int NOTIFY_SETEXEMPTCREDIT_MOD = 3;

    // 周期性产品通知免费资源
    public static final int NOTIFY_CYCLEPROD_WITHOUT_FREERES = 1;
    public static final int NOTIFY_CYCLEPROD_WITH_FREERES = 2;
    
    
    
    //订购产品触发reward
    public static final int REWARD_FOR_ORDERPROD = 20;
    
    //changePayMode
    public static final int CHGPAYMODE_PPS_POS_TO_HYBRID = 11;
    public static final int CHGPAYMODE_HYBRID_TO_PPS_POS = 12;
    public static final int CHGPAYMODE_CHG_HYBRIDRULE = 13;
    public static final int CHGPAYMODE_CHG_SERV_PAYMODE = 14;
    public static final int CHGPAYMODE_INCREASE_THRESHOLD = 15;
    public static final int CHGPAYMODE_PPS_POS_SHIFT = 18;
    
    
    //split告警
    public static final int NOTIFY_SPLIT_ADD = 1;
    public static final int NOTIFY_SPLIT_DELETE = 2;
    public static final int NOTIFY_SPLIT_MODIFY = 3;
    
    //reguide告警
    public static final int NOTIFY_REGUIDE_ADD = 1;
    public static final int NOTIFY_REGUIDE_DELETE = 2;
    public static final int NOTIFY_REGUIDE_MODIFY = 3;
}
