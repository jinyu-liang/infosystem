package com.ailk.ims.define;

/**
 * @Description: 定义常量
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-21
 * @Date 2012-08-19 新增上发接口表模型字段定义
 * @Date 2012-05-21 zhangzj3 新增一次性费用延迟收费调用帐管bill_extDataService定义
 * @Date 2012-07-12 #51392 以下2个字段和BSS Broker的映射字段不一致
 * @Date 2012-08-08 #44886 新增押金管理服务 SPRING_BEAN_DEPOSIT_DEPOSITPOCKETSERVICE
 */
public class ConstantDefine
{
    public static final String SPRING_APP_CONTEXTFILE = "ims_applicationContext.xml";
    public static final String SPRING_APP_CONTEXTFILE_BUSINESS = "ims_applicationContext-business.xml";
    public static final String SPRING_BEAN_COMMONDAO = "commonDao";
    public static final String SPRING_BEAN_ENTITYMANAGERFACTORY = "entityManagerFactory";
    public static final String SPRING_BEAN_UNIQUEIDMANAGER = "uniqueIdManager";
    public static final String SPRING_BEAN_IMS_BUSINESS_PROXY = "ims_business_proxy";
    public static final String SPRING_BEAN_IMS_WS_PROXY = "ims_ws_proxy";
    public static final String SPRING_BEAN_IMS_SYNC_PROXY = "ims_sync_proxy";
    public static final String SPRING_BEAN_IMS_CMPLOG_INTERCEPTOR = "ims_cmplog_interceptor";
    public static final String SPRING_BEAN_ACCT_ACCUM_ACCUMSERVICE = "accumulation_accumulationService";
    public static final String SPRING_BEAN_ACCT_ASSET_ASSETSERVICE = "asset_assetService";
    public static final String SPRING_BEAN_ACCT_FREERESOURCESERVICE = "freeResource_freeResourceService";
    public static final String SPRING_BEAN_ACCT_ASSET_TOPUPSERVICE = "topup_topupService";
    public static final String SPRING_BEAN_ACCT_REWARD_REWARDSERVICE = "reward_rewardService";
    public static final String SPRING_BEAN_ACCT_BILL_BILLCYCLESERVICE = "bill_billCycleService";
    public static final String SPRING_BEAN_ACCT_BILL_PLANESERVICE = "bill_planService";
    public static final String SPRING_BEAN_BILL_REALACTUALUSAGERSERVICE = "bill_realActualUsageService";
    public static final String SPRING_BEAN_BILL_QUERYCDRSERVICESERVICE = "billdata_queryCDRServiceService";
    public static final String SPRING_BEAN_DOPAYFEESERVICE = "dopayfee_dopayfeeService";
    public static final String SPRING_BEAN_ACCT_BILL_STATUSSERVICE = "bill_billStatusService";
    public static final String SPRING_BEAN_BILL_BILLBUSISERVICE = "bill_billBusiService";
    public static final String SPRING_BEAN_ACCT_POINT_POINTSERVICE = "point_pointService";
    public static final String SPRING_BEAN_ACCT_REWARD_REWARDINFOSERVICE = "reward_rewardInfoService";
    public static final String SPRINF_BEAN_ACCT_BILL_EXTDATASERVICE = "bill_extDataService";
    public static final String SPRING_BEAN_ACCT_CONSUMEBUSISERVICE = "outInterface_consumeBusiService";
    public static final String SPRING_BEAN_IMS_DB_LISTENER = "ims_db_listener";
    public static final String SPRING_BEAN_EF_SALCLIENTFACTORY = "salClientFactory";
    public static final String SPRINF_BEAN_ACCT_BILLDATA_QUERYGRAPHSERVICE = "billdata_queryGraphServiceService";
    // NT AcctMgnt
    public static final String SPRING_BEAN_ACCT_SECURITYCASH_SERVICE = "securityCash_securityCashService";
    // SH AcctMgnt
    public static final String SPRING_BEAN_ACCT_OUTINTERFACE_BILLBUSISERVICE = "outInterface_billBusiService";
    public static final String SPRING_BEAN_ACCT_POCKETEXTBUSISERVICE = "outInterface_pocketExtBusiService";

    public static final String SPRING_BEAN_ACCT_PAYMENTHISBUSISERVICE = "outInterface_paymentHisBusiService";
    public static final String SPRING_BEAN_ACCT_POSRECBUSISERVICE = "outInterface_posRecBusiService";
    public static final String SPRING_BEAN_ACCT_BADDEBTBILLBUSISERVICE = "outInterface_badDebtBillBusiService";
    public static final String SPRING_BEAN_ACCT_BILLITEMDEFBUSISERVICE = "outInterface_billItemDefBusiService";
    public static final String SPRING_BEAN_ACCT_DEPOSITBUSISERVICE = "outInterface_depositBusiService";
    public static final String SPRING_BEAN_ACCT_PAYFEEBUSISERVICE = "outInterface_payFeeBusiService";
    public static final String SPRING_BEAN_ACCT_EXTDATABUSISERVICE = "outInterface_extDataBusiService";
    public static final String SPRING_BEAN_ACCT_DERATERECORDBUSISERVICE = "outInterface_derateRecordBusiService";
    public static final String SPRING_BEAN_ACCT_RETURNRECORDBUSISERVICE = "outInterface_returnRecordBusiService";
    public static final String SPRING_BEAN_ACCT_BILLBUSISERVICE = "outInterface_accountBillBusiService";

    public static final String SPRING_BEAN_NOTIFICATION = "notification_notificationService";

    public static final String SPRING_BEAN_ACCT_BILL_EXTDATASERVICE = "bill_billStatusService";

    public static final String SPRING_BEAN_ACCT_BILL_SERVICE = "bill_billService";

    public static final String SPRING_BEAN_PAYMENT_DOPAYSERVICE = "payment_dopayService";

    public static final String SPRING_BEAN_TRACE_BUSISERVICE = "trace_traceBusiService";

    public static final String SPRING_BEAN_ACCT_BILL_MAILGROUPSERVICE = "bill_mailGroupService";

    public static final String SPRING_BEAN_PAYMENT_CONVERTSERVICE = "payment_convertService";

    public static final String SPRING_BEAN_DEPOSIT_DEPOSITPOCKETSERVICE = "deposit_depositPocketService";

    public static final String SPRING_BEAN_BILL_QUERYREGUIDESERVICE = "bill_queryReguideService";

    public static final String SPRING_BEAN_IMS_CONTEXT = "ims_context";
    public static final String SPRING_BEAN_IMS_INTERCEPTOR = "ims_interceptor";
    public static final String SPRING_BEAN_IMS_INFOMGNTSERVICE = "imsintf_iInfoMgntService";
    public static final String SPRING_BEAN_IMS_INNERSERVICE = "imsinner_innerService";
    public static final String SPRING_BEAN_IMS_INNERSDLSERVICE = "imsinner_innersdlService";
    public static final String SPRING_BEAN_IMS_INFOCNSHERVICE = "imsintf_cN_SHMgntService";
    
    public static final String SPRING_BEAN_INNERQRY_QUERY4ACCTRECVSERVICE = "innerqry_query4AcctRecvService";

    public static final String SUFFIX_BEAN_BUSI = "_busi";// 业务bean定义时的后缀
    public static final String SUFFIX_BEAN_MDB = "_mdb";// MdbBean定义时的后缀
    public static final String SUFFIX_BEAN_QRY = "_query";// QueryBean定义时的后缀
    public static final String SUFFIX_BEAN_SYNC = "_sync";// BaseSyncBean定义时的后缀

    public static final String ENTITY_FIELD_SO_NBR = "soNbr";// 实体中字段so_nbr的名称
    public static final String ENTITY_FIELD_SO_DATE = "soDate";// 实体中字段so_date的名称
    public static final String ENTITY_FIELD_EXPIRE_DATE = "expireDate";// 实体中字段expire_date的名称
    public static final String ENTITY_FIELD_VALID_DATE = "validDate";// 实体中字段valid_date的名称
    public static final String ENTITY_FIELD_CREATE_DATE = "createDate";// 实体中字段create_date的名称
    public static final String ENTITY_FIELD_REGION_CODE = "regionCode";// 实体中字段so_date的名称

    //0crm更新，1账管更新，2，信控更新
    public static final String CRM_UPDATE = "0";
    public static final String ACCT_UPDATE = "1";
    public static final String CC_UPDATE = "2";

    // 上发接口表模型字段定义
    public static final String ENTITY_FIELD_SYNC_ID = "id";
    public static final String ENTITY_FIELD_SYNC_HIS_ID = "hisId";
    public static final String ENTITY_FIELD_SYNC_STS = "sts";
    public static final String ENTITY_FIELD_SYNC_DEAL_DATE = "dealDate";
    public static final String ENTITY_FIELD_SYNC_DEAL_COUNT = "dealCount";
    public static final String ENTITY_FIELD_SYNC_ERROR_MSG = "errorMsg";
    public static final String ENTITY_FIELD_SYNC_ERROR_CODE = "errorCode";
    public static final String ENTITY_FIELD_SYNC_EFFECTIVE_TIME = "effectiveTime";

    public static final String ENTITY_TABLE_SUFFIX_HIS = "_HIS";// 实体对应的历史表后缀

    public static final String RESET_NEGATIVE_VALUE_ENTITY_FIELD_SYNC_ID = "his_id";

    public static final String TYPE_CUST = "cust";
    public static final String TYPE_ACCT = "acct";
    public static final String TYPE_USER = "user";
    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_PROD = "prod";

    public static final String PREFIX_MAPPER_ID = "mapper_id_";
    public static final String PREFIX_MAPPER_DM = "mapper_dm_";

    public static final String REQCONTEXT_KEY_SOAP = "request_soap";
    public static final String REQCONTEXT_KEY_CONTEXT = "ims_context";

    public static final String LIFECYCLE_CHECK_DEAL_BUSICODE = "9015";

    /**************************************** 生命周期特征值 *********************************************/
    // user expire date
    public static final String LIFECYCLE_SUBSCRIBER_EXPIRE_DATE = "SUBSCRIBER_EXPIRE_DATE";
    // system date
    public static final String LIFECYCLE_SYSDATE = "SYSDATE";

    public static final String LUA_RUN_OK = "1";

    // 价格计划输入参数
    public static final String LUA_PRRICINGPLAN_CUST_SEGMENT = "CM_CUSTOMER_CUSTOMER_SEGMENT";
    public static final String LUA_PRRICINGPLAN_CUST_TYPE = "CM_CUSTOMER_CUSOMTER_TYPE";
    public static final String LUA_PRRICINGPLAN_CUST_GENDER = "CM_INDIVIDUAL_GENDER";
    public static final String LUA_PRRICINGPLAN_ACCT_SEGMENT = "CA_ACCOUNT_ACCOUNT_SEGMENT";
    public static final String LUA_PRRICINGPLAN_USER_SEGMENT = "CM_RESOURCE_SEGMENT";
    public static final String LUA_PRRICINGPLAN_PROD_ISMAIN = "CM_CUSTOMER_CUSTOMER_SEGMENT";
    public static final String LUA_PRRICINGPLAN_PROD_OVERRIDE_OFFER_ID = "PM_PRODUCT_OFFER_OVERRIDE_OFFER_ID";
    public static final String LUA_PRICEINGPLAN_PROD_UP_SELL = "PM_PRODUCT_OFFER_UPSELL_FLAG";
    public static final String LUA_PRICEINGPLAN_PROD_FIRST_ORDER = "PM_PRODUCT_OFFER_FIRST_ORDER";
    public static final String LUA_PRICEINGPLAN_PROD_VALID_DATE = "CO_PROD_VALID_DATE";
    public static final String LUA_PRICEINGPLAN_USER_REGION_CODE = "CM_CUSTOMER_REGION_CODE";
    public static final String LUA_PRICEINGPLAN_MAIN_OFFER_ID = "PM_PRODUCT_OFFER_OFFERING_ID";
    public static final String LUA_PRICEINGPLAN_COUNT_OF_PACKAGE = "PM_PRODUCT_COUNT_OF_PACKAGE";
    public static final String LUA_PRICINGPLAN_GROUP_TYPE = "GROUP_TYPE";
    // FirstActivation rule parameter
    public static final String LUA_FIRSTACT_CUST_SEGMENT = "CUSTOMER_SEGMENT";
    public static final String LUA_FIRSTACT_CUST_TYPE = "CUSOMTER_TYPE";
    public static final String LUA_FIRSTACT_ACT_CHANNEL = "ACTIVATION_CHANNEL";
    public static final String LUA_FIRSTACT_USER_SEGMENT = "RESOURCE_SEGMENT";
    public static final String LUA_FIRSTACT_ACT_LOCATION = "ACTIVATION_LOCATION";
    public static final String LUA_FIRSTACT_ACT_MAINPROM = "ACTUSER_MAIN_PROMOTION";
    public static final String LUA_FIRSTACT_ACT_DATE = "ACTIVATION_DATE";

    /*************************************** 扩展参数 **********************************/
    // 客户SCustomer
    public static final String EXTPARAM_CUST_HIERARCHYBILLINGFLAG = "HIERARCHYBILLINGFLAG";
    public static final String EXTPARAM_CUST_CYCLE_TYPE = "CYCLE_TYPE";
    public static final String EXTPARAM_CUST_CYCLE_UNIT = "CYCLE_UNIT";
    public static final String EXTPARAM_CUST_START_BILL_DAY = "START_BILL_DAY";
    public static final String EXTPARAM_CUST_BILL_CYCLE = "BILL_CYCLE";
    public static final String EXTPARAM_CUST_BILLHANDINGCODE = "BILLHANDINGCODE";
    public static final String EXTPARAM_CUST_CUSTBMCALLDETAIL = "CUSTBMCALLDETAIL";
    public static final String EXTPARAM_CUST_CUSTBMSUMMARY = "CUSTBMSUMMARY";
    // @Date 2012-07-12 #51392 以下2个字段和BSS Broker的映射字段不一致
    public static final String EXTPARAM_CUST_EMAILBMCALL = "EMAILCUSTBMCALL";
    public static final String EXTPARAM_CUST_EMAILBMSUMMARY = "EMAILCUSTBMSUMMARY";

    public static final String EXTPARAM_CUST_ATTRCUSTEMAIL = "ATTRCUSTEMAIL";
    public static final String EXTPARAM_CUST_FAXBMCALL = "FAXBMCALL";
    public static final String EXTPARAM_CUST_FAXBMSUMMARY = "FAXBMSUMMARY";

    // 账户SAccount
    public static final String EXTPARAM_ACCT_PRINTBILL = "PRINTBILL";
    public static final String EXTPARAM_ACCT_SMSCONTACTNUM = "SMSCONTACTNUM";
    public static final String EXTPARAM_ACCT_BILLHANDLINGCODE = "BILLHANDLINGCODE";
    public static final String EXTPARAM_ACCT_ACCTBMCALLDETAIL = "ACCTBMCALLDETAIL";
    public static final String EXTPARAM_ACCT_ACCTBMSUMMARY = "ACCTBMSUMMARY";
    public static final String EXTPARAM_ACCT_EMAILACCTBMCALL = "EMAILACCTBMCALL";
    public static final String EXTPARAM_ACCT_EMAILACCTBMSUMMARY = "EMAILACCTBMSUMMARY";
    public static final String EXTPARAM_ACCT_ATTRACCTEMAIL = "ATTRACCTEMAIL";
    public static final String EXTPARAM_ACCT_DEFAULTCPSID = "DEFAULTCPSID";
    public static final String EXTPARAM_ACCT_INVOICETYPE = "INVOICETYPE";
    public static final String EXTPARAM_ACCT_BILLSTYLEID = "BILLSTYLEID";
    public static final String EXTPARAM_ACCT_FAXBILLTO = "FAXBILLTO";
    public static final String EXTPARAM_ACCT_FAXACCBMCALL = "FAXACCBMCALL";
    public static final String EXTPARAM_ACCT_FAXACCBMSUM = "FAXACCBMSUM";
    public static final String EXTPARAM_ACCT_SPECIALACCNT = "SPECIALACCNT";
    public static final String EXTPARAM_ACCT_CHARGETYPE = "CHARGETYPE";
    public static final String EXTPARAM_ACCT_SMSBILLTO = "SMSBILLTO";
    public static final String EXTPARAM_ACCT_BILLPAYMENTCURRENCY = "BILLPAYMENTCURRENCY";
    public static final String EXTPARAM_ACCT_EMAILBILLTO = "EMAILBILLTO";
    public static final String EXTPARAM_ACCT_BILLNAME = "BILLNAME";
    public static final String EXTPARAM_ACCT_VATNAME = "VATNAME";
    public static final String EXTPARAM_ACCT_ORIGINAL_DEFAULTCPSID = "ORIGINALDEFAULTCPSID";
    public static final String EXTPARAM_ACCT_COPYBILLBUDGET = "COPYBILLBUDGET";

    /*************************************** 扩展参数 **********************************/

    // 用户扩展参数
    public static final String EXTPARAM_USER_PHFLAG = "PH_FLAG";
    public static final String EXTPARAM_USER_VOICEMAILFLAG = "VOICE_MAIL_FLAG";
    public static final String EXTPARAM_USER_USSDCALLBACKFLAG = "USSD_CALLBACK_FLAG";
    public static final String EXTPARAM_USER_ICFFLAG = "ICF_FLAG";
    public static final String EXTPARAM_USER_ICFNUMBER = "ICF_NUMBER";

    // 组定义表的策略参数
    public static final String LUA_CUST_SEGMENT = "CUSTOMER_SEGMENT";
    public static final String LUA_CUST_TYPE = "CUSTOMER_TYPE";
    public static final String LUA_RES_TYPE = "RES_TYPE";
    public static final String LUA_RES_SEGMENT = "RES_SEGMENT";

    public static final String SERVICE_PROXY_BEAN_ID = "proxyBeanId";
    public static final String BUSI_CODE = "busi_code";

    // 定义获取域的参数
    public static final String DOMAIN_NAME_INFOSYSTEM = "infosystem";

    // 替换主产品，
    public static final String CHANGE_MAIN_PROMOTION = "change_main_promotion";

    // 统配值定义
    public static final String INT_UNIFIED_CONFIGURATION = "-1";
    public static final String STRING_UNIFIED_CONFIGURATION = "-";

    // 是否是特别标注业务接口
    public static final String IS_REWARD_PROD_ORDER = "isRewardProdOrder";// 赠送产品订购

    // 一次性标志
    public static final String IS_ONE_TIME_FEE = "isOneTimeFee";
    public static final String IS_TIMER = "isTimer";

    public static final String TOP_UP_ACTIVE_VALIDDATE = "ACTIVE_VALIDDATE";
    public static final String TOP_UP_ACTIVE_EXPIREDATE = "ACTIVE_EXPIREDATE";
    public static final String TOP_UP_SUSPEND_EXPIREDATE = "SUSPEND_EXPIREDATE";
    public static final String TOP_UP_DISABLE_EXPIREDATE = "DISABLE_EXPIREDATE";

    // 保存IR产品目的国家的生失效时间
    public static final String IR_PROD_EXPIRE_DATE = "IR_PROD_EXPIRE_DATE";
    public static final String IR_PROD_VALID_DATE = "IR_PROD_VALID_DATE";
    public static final String IR_PROD_NAME = "IR_PROD_NAME";
    public static final String IR_TIME_ZONE_DETAIL = "IR_TIME_ZONE_DETAIL";

    /*************************************** 缓存数据key值定义 **********************************/
    // 三户新装缓存
    public static final String NEWREG_PRICE_PLAN = "price_plan";
    public static final String NEWREG_SPEC_CHAR_BUSI_FLAG = "specChar_busiFlag_"; // 产品规格特征值缓存

    public static final String RECURRING_OBJECT = "common_recurring_object";// 鉴权缓存
    public static final String ONETIMEFEE_OBJECT = "common_onetimefee_object";// onetimefee缓存
    public static final String REWARD_OBJECT = "common_reward_object";// reward缓存
    public static final String METHODNODE_OBJECT = "common_methodnode_object";// methodNode缓存
    public static final String ONETIMEFEE_AMOUNT = "common_onetimefee_amount";// 一次性费用总和
    // 账期结构数据缓存
    public static final String BILL_CYCLE_KEY_NAME = "bill_cycle";
    // 是否已经有billingPlan
    public static final String HAS_CREATE_BILLCYCLE = "HAS_CREATE_BILLCYCLE";
    // 客户扩展信息数据缓存
    public static final String CMCUSTEXT_OBJECT = "cmCustExt";

    // 缓存要删除的产品ids
    public static final String DELETE_PRODIDS = "del_prod";
    // 缓存替换普通产品的SproductOrderList
    public static final String REPLACE_COMMON_PROD = "repalce_common_prod";
    public static final String REPLACE_MAIN_PROD = "REPLACE_MAIN_PROD";
    // 缓存替换普通产品的CO_PROD_BILL_CYCLE
    public static final String REPLACE_PROD_CYCLE = "add_prod_bill_cycle";
    public static final String REPLACE_MAIN_PROD_CYCLE = "add_main_prod_bill_cycle";
    public static final String CACHE_GROUP_PERSON_PROD_CYCLE = "CACHE_GROUP_PERSON_PROD_CYCLE";
    // 缓存替换普通产品新增的产品的生效时间
    public static final String REPLACE_PROD_ADD_VALID_DATE = "ADD_VALID_DATE";
    public static final String REPLACE_MAIN_PROD_ADD_VALID_DATE = "ADD_MAIN_VALID_DATE";

    public static final String ORDER_PROD_CANCEL_REWARD_SPEC_ID = "orderprod_cancel_reward_spec_id";

    // sys_parameter缓存bean
    public static final String IMS_DB_CACHE_BEAN = "ims_db_cache_bean";

    // 首次激活的相关缓存
    public static final String ACTIVE_DATE = "active_date";
    public static final String ACTIVE_LOCATION = "active_location";
    public static final String ACTIVE_SDL_SUSEREX = "active_sdl_suserex";
    public static final String ACTIVE_SDL_SCUSTOMER = "active_sdl_scustomer";
    public static final String ACTIVE_SDL_SUSERCYCLE = "active_sdl_susercycle";
    public static final String ACTIVE_SDL_SACCOUNTEX = "active_sdl_saccountex";
    public static final String ACTIVE_SDL_SPROMBILLCYCLE_LIST = "active_sdl_sprombillcycle_list";
    public static final String ACTIVE_SDL_SPRODINFO_LIST = "active_sdl_sprodinfo_list";
    public static final String ACTIVE_SDL_SPROMPRICE_LIST = "active_sdl_spromprice_list";
    public static final String ACTIVE_SDL_MAIN_PROMOTION = "active_sdl_main_promotion";
    public static final String ACTIVE_JAVA_CMRESOURCE = "active_java_cmresource";
    public static final String ACTIVE_JAVA_CMCUSTOMER = "active_java_cmcustomer";
    public static final String ACTIVE_JAVA_CAACCOUNT = "active_java_caaccount";
    public static final String ACTIVE_JAVA_MAIN_PROMOTION = "active_java_main_promotion";
    public static final String ACTIVE_PATTERN_ID = "active_pattern_id";
    public static final String ACTIVE_GPRS_URL = "active_gprs_url";

    // 首次修改服务时间接口缓存
    public static final String SERVICE_SPEC_ID = "service_spec_id";

    public static final short INNSER_SO_MODE = 99;

    // 线程变量ims3hTree
    public static final String THREAD_LOCAL_IMS3HTREE = "ims3hTree";
    public static final String THREAD_QUERY_IMS3HBEAN = "query3hbean";// 用于首次激活时缓存，减少账管调用时查询数据库

    public static final String NEED_CHANGE_PAY_MODE = "need_change";

    // call screen send notification
    public static final String SEND_NOTIFY = "Send";
    public static final String NOT_SEND_NOTIFY = "Not Send";
    public static final String CHANGE_PAYMODE_NEW_OFFER_ID = "newOfferId";

    public static final String MAX_SEND_TIME = "max send time";

    // 系统使用版本号
    public static final String PROJECT_CN_SH = "CN_SH";
    public static final String PROJECT_CN_XZ = "CN_XZ";
    public static final String PROJECT_CN_NM = "CN_NM";
    public static final String PROJECT_CN_JX = "CN_JX";
    public static final String PROJECT_CN_GX = "CN_GX";
    public static final String PROJECT_TH_AIS = "TH_AIS";

    // 修改帐户信息时的缓存下个账期开始日
    public static final String NEXT_CYCLE_VALID_DATE = "next_cycle_valid_date"; // 帐户下个账期生效日

    public static final String OLD_MAIN_PROM = "old_main_prom";// 用于更改主产品时，记录原主产品的对应销售品的编号

    public static final String CUST_ID_FOR_ROUTER = "cust_id_for_router";
    // 账期规格表缓存
    public static final String BILL_CYCLE_SPEC = "bill_cycle_spec";
    // 账期类型缓存
    public static final String BILLING_PERIOD = "billing_period";
    /*************************************** QueryCDR **********************************/
    public static final int QUERYCDR_TYPE_VOICE = 50001;
    public static final int QUERYCDR_TYPE_SMS = 50005;
    public static final int QUERYCDR_TYPE_MMS = 50014;
    public static final int QUERYCDR_TYPE_GPRS = 53001;
    public static final int QUERYCDR_TYPE_ONETIMEFEE = 70002;

    // 免催免停标志
    public static final String EXEMPT_CREDIT_LIMIT = "default_exempt_type";

    // 2012-02-14 wuyujie : changeproduct接口为了后续收取一次性费用，需要把add的销售品列表都记录下来
    public static final String CHGPROD_FOROTF_OFFERIDLIST = "chgprod_offer_ids";
    public static final String CHGPROD_FOROTF_PLOCY_OFFERIDS = "offer_ids";

    // 2012-02-17 caohm5:：产品包计费；需要把add的产品包列表记录下来
    // 作为包；传输给产品管理
    public static final String PACKET_PRODUCT_OFFERING_ID = "packet_product_offering_id";
    // 用来临时存储自销售品列表
    public static final String SON_OFFERING_ID_LIST = "son_offering_id_list";
    // 作为子产品；传输给产品管理
    public static final String SON_PRODUCT_IN_PARCKET = "son_product_in_parcket";
    // 用来临时存储作为子产品列表
    public static final String SON_PRODUCT_IN_PARCKET_LIST = "son_product_in_parcket_list";
    // --caohm5 2012-02-21

    // TB session 定时任务 时间戳
    public static final String TB_SESSION_TIMESTAMP = "QUEUE_TIMESTAMP";

    // 修改亲情号码次数
    public static final String MANAGE_FN_TIMES = "manager_fn_times";

    public static final String CLEAN_BALANCE_FREERESOURCE_CONFIG = "clean_balance_freeres_config";
    // 更换主产品，旧的主产品
    public static final String REPLACE_MAIN_PROD_OLD = "replace_main_prod_old";
    // preorder product list
    public static final String PREORDER_PRODUCT_LIST_MAP = "preorder_product_list_map";
    public static final String PREORDER_PRODUCT_OBJECT_TYPE_MAP = "preorder_product_object_type_map";
    public static final String PREORDER_PRODUCT_PAY_ACCT_MAP = "preorder_product_pay_acct_map";

    public static final String CHANGE_MAIN_PROD_NOT_VALID = "change_main_prod_not_valid";
    // 缓存退订产品的时候同时退订的子产品信息sproductOrder
    public static final String CACHE_DEL_PACK_PRODLIST = "cache_del_pack_prodlist";
    // 缓存订购的时候订购的主产品id
    public static final String CHANGE_PROD_ADD_MAIN_PRODUCT_ID = "change_prod_add_main_product_id";
    public static final String CHANGE_PROD_ADD_OFFERIDS = "change_prod_add_offerIs";
    public static final String CHANGE_PROD_DEL_OFFERIDS = "change_prod_del_offerIds";
    public static final int EVENT_TYPE_MODIFY_PROFILE = 1;

    /** 默认号码头处理,未配置的情况下使用 */
    public static final String DEFAULTPHONEIDHEADHANDLER = "com.ailk.ims.phoneidhead.DefaultPhoneIdHeadHandler";
    // 主动退订产品标志
    public static final String TERMINATE_FLAG = "terminate_flag";

    // 是否需要回复黑白名单
    public static final String WHETHER_NEED_KEEP_ICS_LIST = "whether_need_keep_ics_list";

    // Manage single balance Member，操作前后的balance,_amount_before_amount_after
    public static final String SINGLE_BALANCE_BEFORE_AMOUNT = "single_balance_before_amount";
    public static final String SINGLE_BALANCE_AFTER_AMOUNT = "single_balance_after_amount";
    // changeowner 需要返回的oldBalance,暂只返回main balance
    public static final String CHANGEOWNER_OLD_BALANCE = "changeowner_old_balance";
    public static final String SINGLE_BALANCE_MAX_VALIDITY = "single_balance_max_validity";

    // [56230]writeCap优化
    // uri定义
    public static final String WRITE_CAP_URI = "/ims/custmgnt";
    // key 定义
    public static final String WRITE_CAP_MAP_KEY_NEW_REG = "active_user_count";
    public static final String WRITE_CAP_MAP_KEY_CHG_SIM = "change_sim_count";
    public static final String WRITE_CAP_MAP_KEY_CHG_BILL_CYCLE = "bill_cycle_change_count";
    //46336 cap max action 定义
    public static final String CAP_MAX_ACTION_CONTINUE_FREECHARGE = "continue-freecharge";
    public static final String CAP_MAX_ACTION_CONTINUE_THROTTING = "continue-throtting";
    public static final String CAP_MAX_ACTION_BAR = "Bar";
    //增加默认账本 2012-09-05 
    public static final String DEFAULT_GROUP_BILLABLE_ACCT_ID="DEFAULT_GROUP_BILLABLE_ACCT_ID";
    //路由服务类
    public static final String ROUTER_SERVICE_BEAN="routerClient";
    public static final String OFFER_CACHE_BEAN="offer_ims_db_cache_bean";
    
    
    /*************************路由维度的名称**************************/
    public static final String ROUTER_KEY_ACCT = "ACCT_ID";
    public static final String ROUTER_KEY_USER = "RESOURCE_ID";
    public static final String ROUTER_KEY_CUST = "CUST_ID";
    public static final String ROUTER_KEY_IDEN = "IDENTITY";
    public static final String ROUTER_KEY_SO_DATE = "SO_DATE";
    
    
    //数据库配置参数BOSS_REPAIRING,用于系统修复时，外围调用报错。
    public static final String IM_SHBOSS_REPAIRING = "IM_SHBOSS_REPAIRING";
    public static final String MATCHBUILDER_COMMON_MATCH = "-1";
    public static final String MATCHBUILDER_SEPERATOR = "_";
    
    public static final String USER_SEGMENT = "resSegment";
    public static final String CUST_SEGMENT = "customerSegment";
}
