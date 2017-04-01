package com.ailk.ims.define;

/**
 * 系统参数常量名定义,使用SysUtil.getString(key)等方法来取值
 * 
 * @Author wuyj
 * @Date 2011-7-1 2012-02-10 wuyujie : 删除INNER_LANGUAGE
 */
public class SysCodeDefine
{
    // // 定义开发级系统参数名称
    // private static class inner
    // {
    // public static String INNER_COUNTRY = "country";
    // public static String INNER_CUST_CONTACT = "cust_contact";
    // public static String INNER_ACCT_CONTACT = "acct_contact";
    // public static String INNER_VALIDABLE = "validable";
    // // 是否启用组件方法追踪
    // public static final String INNER_COMPONENT_TRACE = "component_trace";
    //
    // // 生命周期一次获取数据条数
    // public static final String GET_LIFECYCLE_DATA_NUM = "get_lifecycle_data_num";
    // // public static final String GET_BARSERVICE_DATE_NUM="get_barservice_data_num";
    // // public static final String BAR_SERVICE_DEAL_AGAIN_AFTER_DATE="bar_service_deal_again_after_time";
    // // 生命周期中需要定时校验的状态
    // public static final String LIFECYCLE_NEED_CHECK_STATUS = "lifecycle_need_check_status";
    //
    // // 内部id是否和外部id保持一致
    // public static final String INNER_OUTER_ID_EQUAL = "inner_outer_id_equal";
    // // 内部系统so_mode，多个用逗号(,)分开
    // public static final String INNER_SO_MODE_STR = "inner_so_mode_str";
    //
    // public static final String LIFECYCLE_EXPIRE_AFTER_DAYS = "lifecycle_expire_after_days";
    //
    // public static final String FIRST_ACTIVE_LOACAL = "first_active_loacal";
    //
    // // 是否本地测试，不上发数据到云平台
    // public static final String LOCAL_TEST = "local_sdl_control";
    // // do reward控制
    // public static final String DO_REWARD = "do_reward";
    //
    // // 默认最大群成员数
    // public static final String GROUP_MEMBER_DEFAULT_MAX_NUMBER = "group_member_default_max_number";
    // // 默认最大内部群数
    // public static final String CUG_DEFAULT_MAX_NUMBER = "cug_default_max_number";
    // // 默认群主短号
    // public static final String GROUP_MANAGER_SHORT_PHONE = "group_manager_short_phone";
    //
    // //是否crm传入群成员数
    // public static final String IS_CRM_SEND_GROUP_MEM_NUM = "isCrmSendGroupMemNum";
    //
    // // 扫描快到期产品，分页查询
    // public static final String EXPIRE_PROD_PAGE_COUNT = "page_count";
    //
    // // 是否要检查外部id是存在
    // public static final String IS_CHECK = "is_check";
    //
    // // 订购产品reward/撤销rewaard specId
    // public static final String ORDER_PROD_REWARD_SPEC_ID = "orderprod_reward_spec_id";
    //
    // // 配置表明客户地址记录不能重复的类型
    // public static final String CUST_CONTACT_TYPE = "cust_contact_type";
    // // 配置表明帐户地址记录不能重复的类型
    // public static final String ACCT_CONTACT_TYPE = "acct_contact_type";
    //
    // // 首次激活，产品扣费开关
    // public static final String FIRST_ACTIVE_PREORDER_PROD = "first_active_preorder_prod";
    // }

    // 定义业务级系统参数名称
    public static class busi
    {
        // 定义业务参数名称
        public static String BUSI_DEFAULT_EXPIRE_DATE = "default_expire_date";// 默认失效时间
        public static String BUSI_MESSAGE_FILEPATH_PATTERN = "message_filepath_pattern";// 报文保存路径路径模式

        public static final String DEFAULT_LANGUAGE = "default_language";

        // 配置默认客户类型
        public static final String DEFAULT_CUSTOMER_TYPE = "default_customer_class";

        // 配置默认客户子类型
        public static final String DEFAULT_CUSTOMER_SUB_TYPE = "default_customer_type";

        // 配置默认客户等级
        public static final String DEFAULT_CUSTOMER_SEGMENT = "default_customer_segment";

        // 配置默认帐户表中的公司编号
        public static final String DEFAULT_ACCOUNT_COMPANY_ID = "default_account_company_id";

        // 配置默认帐户类型
        public static final String DEFAULT_ACCOUNT_TYPE = "default_account_type";

        // 配置默认帐户大类
        public static final String DEFAULT_ACCOUNT_CLASS = "default_account_class";

        // 配置默认帐户度量ID
        public static final String DEFAULT_ACCOUNT_PAYMENT_CURRENCY = "default_account_payment_currency";

        // 配置默认资源对应的规格编号
        public static final String DEFAULT_USER_SPEC_ID = "default_user_spec_id";
        // 配置默认资源用户等级
        public static final String DEFAULT_USER_SEGMENT = "default_user_segment";

        // 配置帐户用户关系表中资源角色类型
        public static final String DEFAULT_USER_TITLE_ROLEID = "default_title_roleid"; // 默认资源角色
        
        //配置员工标识
        public static final String  IM_STAFF_TYPE="IM_STAFF_TYPE";

        // ************************以下默认值配置给GUI使用*************************************
        // 界面显示客户信息默认值
        public static final String DEFAULT_CUST_TITLE = "default_cust_title"; // 默认客户称谓

        public static final String DEFAULT_COUNTRY_ID = "default_country_id"; // 默认国家编码

        public static final String DEFAULT_PROV_CODE = "default_prov_code";// 默认省代码

        public static final String DEFAULT_REGION_CODE = "default_region_code";// 默认地市代码

        public static final String DEFAULT_GENDER = "default_gender"; // 默认性别

        public static final String DEFAULT_REG_TYPE = "default_reg_type";// 默认身份证

        // 界面显示帐户信息默认值
        public static final String DEFAULT_FORCE_CYCLE = "default_force_cycle"; // 默认强制账期标识

        public static final String DEFAULT_INVOICE_TYPE = "default_invoice_type";// 扩展字段

        public static final String DEFAULT_BILL_STYLE_ID = "default_bill_style_id";// 扩展字段

        public static final String DEFAULT_PAYMENT_TYPE = "default_payment_type";// 支付类型

        // 界面显示用户信息默认值
        public static final String DEFAULT_PAYMENT_MODE = "default_payment_mode"; // 默认付费模式
        // ************************配置结束****************************

        // 现场需要实现的告警发送处理类，必须继承
        public static final String NOTIFY_SEND_HANDLER = "notify_send_handler";

        // BOS上发的服务url，不需要?wsdl
        public static final String SYNC_CHANGECUSTOMERSTATUS_SERVICE_URL = "sync_changecustomerstatus_url";
        public static final String SYNC_INFO2CSR_SERVICE_URL = "sync_info2csr_url";
        public static final String SYNC_LOWBALANCE2ETOPUP_SERVICE_URL = "sync_lowbalance2etopup_url";
        public static final String SYNC_ONETIMEPROMOTION_SERVICE_URL = "sync_onetimepromotion_url";
        public static final String SYNC_PRODUCTORDER_SERVICE_URL = "sync_productorder_url";
        public static final String SYNC_PRODUCTSTATUS2CRM_SERVICE_URL = "sync_productstatus2crm_url";
        public static final String FIRST_ACTIVE_URL = "sync_firstactive_url";
        public static final String SYNC_CUSTOMERINFO2CRM_SERVICE_URL = "sync_customerinfo2crm_url";
        public static final String SYNC_SYSTEMBARSERVICE_SERVICE_URL = "sync_systembarservice_url";
        public static final String SYNC_SHAREPRODTERMINATE_SERVICE_URL = "sync_shareprod_url";
        public static final String SYNC_ICS_SERVICE_URL = "sync_ics_url";
        public static final String SYNC_DELAYPROD_SERVICE_URL = "sync_delayprodservice_url";

        // 配置个人客户名称
        public static final String PERSON_CUST_NAME = "person_cust";

        // 上发CRM接口表中，同时处理的最大记录条数
        public static final String ONE_TIME_DEAL_RECORD_COUNT = "one_time_deal_record_count";
        // 上发CRM接口表中，每次上传的记录条数
        public static final String ONE_TIME_UPLOAD_RECORD_COUNT = "one_time_upload_record_count";
        // 上发CRM接口表中，记录超时时间（小时为单位）
        public static final String TIMEOUT_HOUR_VALUE = "timeout_hour_value";
        // 上发CRM接口表中，记录超时时间（小时为单位）
        public static final String TIMEOUT_SECOND_VALUE = "timeout_second_value";
        // 上发CRM接口表中，记录处理次数限制
        public static final String RECORD_DEAL_COUNT_VALUE = "record_deal_count_value";
        
        // 默认操作员ID
        public static final String INFOSYSTEM_OPID = "infosystem_opid";

        // 产品通知最大天数参数
        public static final String PROD_VALID_NOTIFY_MAX_DAY = "prod_valid_notify_max_day";
        public static final String PROD_EXPIRE_NOTIFY_MAX_DAY = "prod_expire_notify_max_day";
        public static final String PROD_NOTIFY_MAX_RETRY_TIMES = "prod_notify_max_retry_times";

        // 一个成员允许加入每个类型群的个数
//        public static final String JOIN_GROUP_AMOUNT_VPN = "join_group_amount_vpn";
//        public static final String JOIN_GROUP_AMOUNT_COMMUNITY = "join_group_amount_community";
//        public static final String JOIN_GROUP_AMOUNT_VPBX = "join_group_amount_vpbx";
        
        // changeowner绑定的一次性费用收取，是针对变更前、还是变更后账户的控制，1-变更前；2-变更后；默认为1-变更前
        public static final String ONETIMEFEE_ACCOUNT_CONTROL = "onetimefee_account_control";

        // 项目使用版本
        public static final String PROJECT = "project";
        // 免催免停类型 EXEMPT_TYPE_101 免催 EXEMPT_TYPE_102 免停 EXEMPT_TYPE_103 免催免停
        public static final String EXEMPT_TYPE_NO_PUSH = "exempt_type_101";
        public static final String EXEMPT_TYPE_NO_STOP = "exempt_type_102";
        public static final String EXEMPT_TYPE_NO_STOP_AND_PUSH = "exempt_type_103";

        // 需要发送告警的用户生命周期状态
        public static final String LIFECYCLE_NOTIFY_STS_LIST = "lifecycle_notify_sts_list";

        // 是否收取一次性费用 OneTimeFee
        public static final String GUI_CHARGE_FLAG = "gui_charge_flag";
        
        public static final String IS_PRINT_CACHE="is_print_cache";
        
        // ***************************以下为系统级配置*********************************//

        public static String INNER_COUNTRY = "country";
        public static String INNER_CUST_CONTACT = "cust_contact";
        public static String INNER_ACCT_CONTACT = "acct_contact";
        public static String INNER_VALIDABLE = "validable";
        // 是否启用组件方法追踪
        public static final String INNER_COMPONENT_TRACE = "component_trace";

        // 生命周期一次获取数据条数
        public static final String GET_LIFECYCLE_DATA_NUM = "get_lifecycle_data_num";
        // public static final String GET_BARSERVICE_DATE_NUM="get_barservice_data_num";
        // public static final String BAR_SERVICE_DEAL_AGAIN_AFTER_DATE="bar_service_deal_again_after_time";
        // 生命周期中需要定时校验的状态
        public static final String LIFECYCLE_NEED_CHECK_STATUS = "lifecycle_need_check_status";

        // 内部id是否和外部id保持一致
        public static final String INNER_OUTER_ID_EQUAL = "inner_outer_id_equal";
        // 内部系统so_mode，多个用逗号(,)分开
        public static final String INNER_SO_MODE_STR = "inner_so_mode_str";

        public static final String LIFECYCLE_EXPIRE_AFTER_DAYS = "lifecycle_expire_after_days";

        public static final String FIRST_ACTIVE_LOACAL = "first_active_loacal";

        // 是否本地测试，不上发数据到云平台
        public static final String LOCAL_TEST = "local_sdl_control";
        // do reward控制
        public static final String DO_REWARD = "do_reward";

        // 默认最大群成员数
        public static final String GROUP_MEMBER_DEFAULT_MAX_NUMBER = "group_member_default_max_number";
        // 默认最大内部群数
        public static final String CUG_DEFAULT_MAX_NUMBER = "cug_default_max_number";
        // 默认群主短号
        public static final String GROUP_MANAGER_SHORT_PHONE = "group_manager_short_phone";

        // 是否crm传入群成员数
        // public static final String IS_CRM_SEND_GROUP_MEM_NUM = "isCrmSendGroupMemNum"; //目前暂不使用 yanchuan 2012-03-03

        // 扫描快到期产品，分页查询
        // public static final String EXPIRE_PROD_PAGE_COUNT = "page_count"; //目前暂不使用 yanchuan 2012-03-03

        // 是否要检查外部id是存在
        // public static final String IS_CHECK = "is_check"; //目前暂不使用 yanchuan 2012-03-03

        // 订购产品reward/撤销rewaard specId
        public static final String ORDER_PROD_REWARD_SPEC_ID = "orderprod_reward_spec_id";

        // 配置表明客户地址记录不能重复的类型
        public static final String CUST_CONTACT_TYPE = "cust_contact_type";
        // 配置表明帐户地址记录不能重复的类型
        public static final String ACCT_CONTACT_TYPE = "acct_contact_type";

        // 首次激活，产品扣费开关
        public static final String FIRST_ACTIVE_PREORDER_PROD = "first_active_preorder_prod";

        // 配置默认的balance_type
        public static final String DEFAULT_BALANCE_TYPE = "balance_type";
        // 配置默认的品牌id
        public static final String DEFAULT_BRAND_ID = "brand_id";

        // 数据激活，未获取到用户信息，失败统一跳转的URL
        public static final String FIRST_ACTIVE_FAILURE_URL = "first_active_failure_url";
        //创建产品通知信息是否在ts中进行
        public static final String CREATE_PROD_NOTIFY_UPLOAD_TS = "create_prod_notify_upload_ts";
        
        //Pririoty of MainPromotion in New Registration for Subscriber
        public static final String NEWREG_MAINPRODID_PRIRIOTY = "newreg_mainprodid_pririoty";
        
        //配置默认的Feel like home和continue flag
        public static final String DEFAULT_FEEL_LIKE_HOME_FLAG = "feel_like_home";
        public static final String DEFAULT_CONTINUE_FLAG = "continue_flag";
        
        //配置默认的prospect销售品
        public static final String DEFAULT_PROSPECT_OFFERING = "prospect_offering";
        
        public static final String DEFAULT_FNS = "default_friend_numbers";
        //是否打印参数结构
        public static final String IS_DUMP = "OBJECT_DUMP";
        //是否打印JSON 字符串对象
        public static final String IS_JSON_DUMP = "JSON_DUMP";
        public static final String IS_INTF_DUMP = "INTF_OBJECT_DUMP";
        
        // ****************************系统级配置结束*********************************//
        
        //上海CRM http+json 服务器地址
        public static final String SH_CRM_HTTP_JSON_SERVER_ADDR = "sh_crm_http_json_server_addr";
        //统一平台wsdl地址
        public static final String SH_UNIVER_PLAT_WEB_WSDL_ADDR="sh_univer_plat_web_wsdl_addr";
        
        //@Date 2012-06-14 wangdw5 : User Story #42479 号码头问题,后加project_name
        //@Date 2012-06-25 wangdw5 : Base Data Modify #49292
        public static final String IMS_PHONEID_HEAD_HANDLER = "IM_PHONEID_HEAD_HANDLER_";
        
        public static final String MAX_JOIN_GROUP = "IM_MAX_JOIN_GROUP_";
        
        public static final String RATEING_AHEAD_DAYS = "IM_RATEING_AHEAD_DAYS";
        //@Date 2012-06-28 tangkun : Base Data Modify #49589 
        public static final String IM_ADD_ABM_BUDGET_WAY_FLAG = "IM_ADD_ABM_BUDGET_WAY_FLAG";
        public static final String IM_MAX_FREERESOURCE_AMOUNT = "IM_MAX_FREERESOURCE_AMOUNT";
        //上海e_card用户默认有效期天数
        public static final String IM_SH_E_CARD_VALID_DAYS="IM_SH_E_CARD_VALID_DAYS";
        //@Date 2012-10-08 yugb :60590 三户信息查询是否需要返回账户的账单寄送地址
        public static final String IM_SCONTACT_ADDRESS_FLAG = "IM_SCONTACT_ADDRESS_FLAG";
    }

}
