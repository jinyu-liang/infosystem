package com.ailk.ims.define;

/**
 * 定义一些常用的枚举集合
 * @Description
 * @author luojb
 * @Date 2012-8-3
 */
public class ColCodeDefine
{
    /*********群相关**********/
    // 原生的群产品集合
    public static final int[] GROUP_PROD_BUSIFLAGS = {
        SpecCodeDefine.GROUP_IN,
        SpecCodeDefine.GROUP_OUT,
        SpecCodeDefine.GROUP_NO_PORT,
        SpecCodeDefine.OUTNET_PHONE,
        SpecCodeDefine.INTER_GROUP_PROMOTION
    };
    
    // 订购群产品操作的所有类型产品
    public static final int[] CHG_GROUP_PROD_BUSIFLAGS = {
        SpecCodeDefine.GROUP_IN,
        SpecCodeDefine.GROUP_OUT,
        SpecCodeDefine.GROUP_NO_PORT,
        SpecCodeDefine.OUTNET_PHONE,
        SpecCodeDefine.MCS_NUMBER
    };
    
    // objectType=2 的所有产品
    public static final int[] GROUP_PROD_ALL_BUSIFLAGS = {
        SpecCodeDefine.GROUP_IN,
        SpecCodeDefine.GROUP_OUT,
        SpecCodeDefine.GROUP_NO_PORT,
        SpecCodeDefine.OUTNET_PHONE,
        SpecCodeDefine.INTER_GROUP_PROMOTION,
        SpecCodeDefine.MCS_NUMBER
    };
    
    // 群成员个性化产品
    public static final int[] GROUP_MEM_PROD_BUSIFLAGS = {
        SpecCodeDefine.GROUP_IN_PERSON,
        SpecCodeDefine.GROUP_OUT_PERSON,
        SpecCodeDefine.GROUP_NO_PORT_PERSON,
        SpecCodeDefine.COMMUNITY_PROMOTION
    };
    
    
    /*********产品相关**********/
    //产品操作类型
    public static final Short[] CHG_PROD_OPERTYPES = {
        EnumCodeDefine.CHANGE_PROD_ADD,
        EnumCodeDefine.CHANGE_PROD_DELETE,
        EnumCodeDefine.CHANGE_PROD_MODIFY,
        EnumCodeDefine.CHANGE_PROD_EXTEND,
        EnumCodeDefine.CHANGE_PROD_CHANGE_PAYMODE,
        EnumCodeDefine.CHANGE_PROD_CPME,
        EnumCodeDefine.CHNAGE_PROD_EXTEND_CMP
    };
    
    // 特殊产品，即非crm受理的产品
    public static final int[] SPECIAL_BUSI_FLAG = { 
        SpecCodeDefine.BUDGET, 
        SpecCodeDefine.SPLIT, 
        SpecCodeDefine.REGUIDE_CHARGE, 
        SpecCodeDefine.REGUIDE_USAGE, 
        SpecCodeDefine.PAYMODE, 
        SpecCodeDefine.USER_SUSPEND_UNIDENTIFIED, 
        SpecCodeDefine.E_TOP_UP,
        SpecCodeDefine.USER_SUSPEND_REQUEST,
    };
    
    
    /*********产品二次议价参数*************/
    //不需要入库的参数
    public static final int[] PRICE_PARAM_NO_INSERT = {
        SpecCodeDefine.GROUP_TYPE_PROD_PARAM_ID,
        SpecCodeDefine.UPSELLING_FLAG
    };
    
    //上发mdb需要特殊处理的产品二次议价参数
    public static final int[] SPEC_CODE_PRICE_PARAM = {
        SpecCodeDefine.FREE_RESOURCE_REWARD_DISCOUNT,
        SpecCodeDefine.BASE_RATING_FEE_DISCOUNT
    };
    
    //江西押金划拨 820006 820007 820008 需要直接更新 不新增
    public static final int[] DEPOSIT_PRICE_PARAM = {
        SpecCodeDefine.DEPOSIT_CUR_AMOUNT,
        SpecCodeDefine.DEPOSIT_REMAIN_ACCOUNT,
        SpecCodeDefine.DEPOSIT_BIND_DATE
    };
    
    // 广西销户  湖南销户456
    public static final int[] LIFECYCLE_POOL_CANCEL = {
//        EnumCodeDefine.LIFECYCLE_POOL,
//        EnumCodeDefine.LIFECYCLE_POOL1,
//        EnumCodeDefine.LIFECYCLE_POOL2,
//        EnumCodeDefine.LIFECYCLE_POOL3,
        EnumCodeDefine.LIFECYCLE_POOL4,
        EnumCodeDefine.LIFECYCLE_POOL5
    };
    
    public static final int[] LIFECYCLE_PRE_TERMINAL = {
//        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL1,
//        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL2,
//        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL3,
//        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL4,
        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL5,
        EnumCodeDefine.LIFECYCLE_PRE_TERMINAL6
    };
    
    public static final int[] LIFECYCLE_KEEP = {
        EnumCodeDefine.LIFECYCLE_ZNW_KEEP,
        EnumCodeDefine.LIFECYCLE_KEEP
    };
    
    public static final int[] MDB_NOT_IN_CHAR_VALUE = {
    	SpecCodeDefine.INTER_GROUP_ID,
    	SpecCodeDefine.HOME_ZONE_CELL,
    	SpecCodeDefine.GROUP_IN_PERSONAL_ID,
    	SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID,
    	SpecCodeDefine.GROUP_OUT_PERSONAL_ID,
    	SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628,
    	SpecCodeDefine.OUTNET_PHONE_ID
    };
    
    public static final int[] REMIND_NOTIFY_SPEC_CHAR_ID = {
    	SpecCodeDefine.REMIND_NOTIFY_30320,
    	SpecCodeDefine.REMIND_NOTIFY_30321
    };
}
