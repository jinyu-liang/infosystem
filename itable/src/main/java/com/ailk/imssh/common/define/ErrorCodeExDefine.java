package com.ailk.imssh.common.define;

/**
 * @Description 错误码定义
 * @author lijc3
 * @Date 2012-6-14
 */
public class ErrorCodeExDefine
{
    public static final long PRODUCTOFFERINGID_ERROR = 230001;
    public static final long ACCESS_INTERFACE_FAILED = 230006;
    public static final long COMMON_OFFERCYCLE_NOTEXIST = 230003;
    public static final long COMMON_PROD_NOTEXIST = 230002;
    public static final long COMMON_PARAMS_ALL_ISNULL = 230008;
    //public static final long COMMON_OFFER_NOTEXIST = 200001;

    public static final long LIFECYCLE_LACK_UNIDENTITY_OFFERING = 210001;
    public static final long CHANGE_PROD_NEED_SPEC = 210005;
    public static final long PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE = 210006;
    public static final long SPLIT_4_ACCOUNT_NOTEXIST = 220001;
    public static final long SPLIT_4_USER_NOTEXIST = 220002;
    public static final long CHANGE_PROD_TRIAL_VALID_ERROR = 220003;
    public static final long ACCOUNT_CYCLE_ALL_EXPIRED = 220004;
    public static final long COMMON_PARAM_INVALID = 230004;
    public static final long PARAM_INVALID = 230007;

    public static final long IDENTIT_BY_USER_NOT_EXIST = 220005;
    public static final long LIFE_CYCLE_IS_NULL = 210004;

    public static final long LIFECYCLE_PROD_CONFIG_ERROR = 210002;
    public static final long PRODUCT_BILL_CYCLE_NOT_EXIST = 210003;
    /**
     * 不存在个性化门限TYPE对应的销售品编号.
     */
    public static final long TYPE_TO_OFFERING_ID_NOT_EXIST = 230001;
    /**
     * 不存在销售品对应的BUSI_FLAG.
     */
    public static final long OFFERING_BUSI_FLAG_NOT_EXIST = 240003;

}
