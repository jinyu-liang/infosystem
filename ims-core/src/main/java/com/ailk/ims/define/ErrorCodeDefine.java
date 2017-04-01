package com.ailk.ims.define;

/**
 * @Description: 错误码常量定义 ,错误码与错误信息的提示的对应配置在多语言文件中， ims-conf\message\error_thai.properties
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1
 * @Date 2012-04-19 lijc3 删除211937
 */
public class ErrorCodeDefine
{
    // ims error occurs : [{0}]
    public static final long UNKNOWN_ERROR = 259999;
    
    //MNP Prefix of phone[{0}] has no config
    public static final long MNPPREFIX_NOT_CONFIG = 240001;
    //Prefix of Phone Number[{0}] Not Match Business Requirements
    public static final long AIS_PREFIX_PHONEID_NOT_MATCH = 240002;
    
    // parameter[{0}] can not be empty
    public static final long COMMON_PARAM_ISNULL = 200001;
    // customer[{0}] doesn't exist
    public static final long COMMON_CUST_NOTEXIST = 200030;
    // account[{0}] doesn't exist
    public static final long COMMON_ACCT_NOTEXIST = 200031;
    // user[{0}] doesn't exist
    public static final long COMMON_USER_NOTEXIST = 200032;
    // phone[{0}] of user[{1}] doesn't exist
    public static final long COMMON_PHONE_NOTEXIST = 200033;
    // product[{0}] doesn't exist
    public static final long COMMON_PROD_NOTEXIST = 200034;
    // offering[{0}] doesn't exist
    public static final long COMMON_OFFER_NOTEXIST = 200035;
    // group[{0}] doesn't exist
    public static final long COMMON_GROUP_NOTEXIST = 200036;

    // can't find users of account[{0}]
    public static final long COMMON_USERS_OF_ACCT_NOTEXIST = 200037;
    // can't find accounts of customer[{0}]
    public static final long COMMON_ACCTS_OF_CUST_NOTEXIST = 200038;
    // can't find account for user[{0}]
    public static final long COMMON_ACCT_FOR_USER_NOTEXIST = 200039;
    // can't find customer for account[{0}]
    public static final long COMMON_CUST_FOR_ACCT_NOTEXIST = 200040;

    // parameters[{0}] can't be null at the same time
    public static final long COMMON_PARAMS_ALL_ISNULL = 200041;
    // can't find user for phone[{0}]
    public static final long COMMON_USER_FOR_PHONE_NOTEXIST = 200042;
    // parameter[{0}] must be in [{1}]
    public static final long COMMON_PARAM_INVALID = 200043;
    // all the charge_type of fee items in onetimefee should be same.
    public static final long COMMON_ONETIMEFEE_FEEITEMBESAME = 200044;
    // can't find billable account for user[{0}]
    public static final long COMMON_BILLACCT_FOR_USER_NOTEXIST = 200045;
    // the so_date in soper is null
    public static final long COMMON_SO_DATE_IS_NULL = 200046;

    // the so_nbr is null
    public static final long COMMON_SO_NBR_IS_NULL = 200054;

    // parameter[{0}] must be config in table<sys_parameter>
    public static final long NOT_CONFIG_IN_SYS_PARAMETER = 200048;

    // field [{0}] in struct [{1}] has exist in database.
    public static final long IDMAPPING_ID_EXIST_DB = 200049;
    // field [{0}] in struct [{1}] is duplicate in current request message.
    public static final long IDMAPPING_ID_EXIST_REQUEST = 200050;
    // can not mapping inner field [{0}] by outer field [{1}] in struct [{2}]
    public static final long IDMAPPING_INNERID_CANNOT_MAPPING_BYOUTERID = 200052;

    // access interface [{0}]is failed
    public static final long ACCESS_INTERFACE_FAILED = 200051;

    // can't find billable account for group[{0}]
    public static final long COMMON_BILLACCT_FOR_GROUP_NOTEXIST = 200055;
    // user[{0}] or phone[{1}] does not exist
    public static final long COMMON_USER_OR_PHONE_NOTEXIST = 200057;
    // spec_id is not configed in busi.xml for type[{0}]
    public static final long BUSI_SPEC_ID_NOT_CONFIG = 200058;
    // repeat request error.so_nbr[{0}],step_id[{1}],so_mode[{2}]
    public static final long BUSI_KEY_ERROR = 200059;
    // phone[{0}] doesn't exist
    public static final long COMMON_PHONE_NOT_EXIST = 200060;
    public static final long COMMON_CONFIG_DATA_INVALID = 200061;
    public static final long COMMON_PARENT_GROUP_NOTEXIST = 200062;
    // lacking configuration data of table[{0}] where the column [{1}] = [{2}]
    public static final long LACK_CONFIG_DATE = 200063;
    
    // lacking configuration data of table[{0}] where the column [{1}] = [{2}] and column [{3}] = [{4}]
    public static final long LACK_CONFIG_DATE2 = 200064;
    
    // the measure_type_id[{0}] of parameter measure_id[{1}] not the same as measure_type_id[{2}] of account[{3}]
    public static final long param_measure_not_equal_acct_measure = 200065;
    
    // offering[{0}]'s attribute is null
    public static final long COMMON_OFFER_ATTR_IS_NULL = 200066;
    // offering[{0}]'s billingType is null
    public static final long COMMON_OFFER_BILLTYPE_IS_NULL = 200067;
    // user[{0}]'s billingType does not match offering[{1}]'s billingType 
    public static final long COMMON_USER_OFFER_BILLTYPE_NOT_MATCH = 200068;
    // productOrder's billingType does not match offering[{0}]'s billingType 
    public static final long COMMON_ORDER_OFFER_BILLTYPE_NOT_MATCH = 200069;
    // productOrder(offer_id[{0}])'s billingType does not match user[{1}]'s billingType
    public static final long COMMON_ORDER_USER_BILLTYPE_NOT_MATCH = 200070;
    // offering[{0}] is not active
    public static final long OFFERING_NOT_ACTIVE = 200071;
    // account[{0}] can't order offer[{1}] with billing type[{2}]
    public static final long COMMON_ACCT_OFFER_BILLTYPE_NOT_MATCH = 200072;
    // not find original out so nbr[{0}] in cm_busi table
    public static final long COMMON_OR_SO_NBR_NOTEXIST = 200073;
    // service url can't be empty
    public static final long COMMON_SERVICE_URL_NULL = 200074;
    
    /* 用户账户状态检查相关错误码 */
    // cust_id can't be empty
    public static final long CHECK_CUSTID_EMPTY = 200900;
    // acct_id can't be empty
    public static final long CHECK_ACCTID_EMPTY = 200901;
    // user_id can't be empty
    public static final long CHECK_USERID_EMPTY = 200902;
    // phone_id can't be empty
    public static final long CHECK_PHONEID_EMPTY = 200903;
    // account[{0}] is not active
    public static final long CHECK_ACCOUNT_NOT_ACTIVE = 200904;
    // user[{0}] is not active
    public static final long CHECK_USER_NOT_ACTIVE = 200905;
    // the user of phone[{0}] is not active
    public static final long CHECK_PHONE_USER_NOT_ACTIVE = 200906;
    // the account of phone[{0}] is not active
    public static final long CHECK_PHONE_ACCOUNT_NOT_ACTIVE = 200907;
    // the user_id for phone_id:[{0}] does not match input user_id[{1}]
    public static final long CHECK_USERID_PHONEID_ERROR = 200908;
    // the acct_id for user_id:[{0}] does not match input acct_id[{1}]
    public static final long CHECK_ACCTID_USERID_ERROR = 200909;
    // the acct_id for phone_id:[{0}] does not match input acct_id[{1}]
    public static final long CHECK_ACCTID_PHONEID_ERROR = 200910;
    // can't use fax dummy number[{0}] in business service
    public static final long CHECK_FAX_NUMBER_ERROR = 200911;
    // the user_id for user_id:[{0}] does not match input phone_id[{1}]
    public static final long CHECK_PHONEID_USERID_ERROR = 200912;
    // user[{0}] state is not in the active, suspend, disable
    public static final long CHECK_USER_STS = 200913;
    /* 用户账户状态检查相关错误码 end */
    
    //notification_id config error in table bi_busi_spec_ext for busi_spec_id[{0}]
    public static final long CHECK_NOTIFICATION_ALARMID = 200914;
    
    // user[{0}] state[{1}] does not meet the requirements of business[{2}] 
    public static final long CHECK_USER_BUSISTS = 200915;
    
	// account[{0}] state[{1}] does not meet the requirements of business[{2}] 
    public static final long CHECK_ACCOUNT_BUSISTS = 200916;
    
    // customer[{0}] state[{1}] does not meet the requirements of business[{2}] 
    public static final long CHECK_CUSTOMER_BUSISTS = 200917;
    
    // the bill_acct_id[{0}] for phone_id:[{1}] does not match input acct_id[{2}]
    public static final long CHECK_BILL_ACCTID_PHONEID_ERROR = 200918;

    // customer can't be null
    public static final long CUST_IS_NULL = 200003;
    // account can't be null
    public static final long ACCOUNT_IS_NULL = 200004;
    // user can't be null
    public static final long USER_IS_NULL = 200005;
    // phone can't be null
    public static final long PHONE_IS_NULL = 200006;
    // title Role id can't be null
    public static final long TITLEROLEID_IS_NULL = 200007;
    // operate Type[{0}] is undefined
    public static final long OPERTYPE_UNDEFINED = 200008;
    // account[{0}] doesn't exist
    public static final long ACCOUNT_NOTEXIST = 200011;
    // customer[{0}] doesn't exist
    public static final long CUSTOMER_NOTEXIST = 200012;
    // group[{0}] doesn't exist
    public static final long GROUP_NOTEXIST = 200013;
    // product Order List can't be null
    public static final long PRODUCTORDER_IS_NULL = 200014;
    // product Offering[{0}] doesn't exist
    public static final long PRODUCT_OFFER_NOTEXIST = 200015;
    // group Information can't be null
    public static final long GROUPINFO_IS_NULL = 200017;
    // billable Account can't be null
    public static final long BILLACCOUNT_IS_NULL = 200018;
    // imsi can't be null
    public static final long IMSI_IS_NULL = 200019;
    // lack of base date:[{0}]
    public static final long LACK_OF_BASE_DATE = 200020;
    // product id can't be null
    public static final long PRODUCT_ID_IS_NULL = 200021;
    // operType can't be null
    public static final long OPERTYPE_IS_NULL = 200022;
    // one of user id or phone id must be filled
    public static final long INPUT_ONE_USERID_PHONEID = 200023;
    // parameter:[{0}] is empty or invalid
    public static final long PARAM_INVALID = 200024;
    // one of user_id or phone_id or acct_id must be filled
    public static final long INPUT_ONE_USERID_PHONEID_ACCTID = 200025;

    // offering id is null
    public static final long OFFERINGID_IS_NULL = 200026;
    // can't find productOfferingId for busi_flag:[{0}]
    public static final long PRODUCTOFFERINGID_ERROR = 200027;

    // user does not exist, user_id:[{0}]
    public static final long USER_NOT_EXIST = 200028;

    // busi_code[{0}] does not match with the expected busi_code[{1}];
    public static final long BUSI_CODE_NOT_MATCH = 200029;
    // busi_code[{0}] does not exist;
    public static final long BUSI_CODE_NOT_EXIST = 200047;

    // offering "[{0}]" does not exist
    public static final long NEWREG_OFFERINGNOTEXIST = 210101;
    // phone "[{0}]" has exist
    public static final long NEWREG_PHONE_EXIST = 210102;
    // life cycle of offering [{0}] does not exist
    public static final long NEWREG_PRODCYCLE_NOTEXIST = 210103;
    // not find account[{0}] for user[{1}]
    public static final long NEWREG_ACCT_FORUSER_NOTEXIST = 210105;
    // not find account[{0}] for contact[{1}]
    public static final long NEWREG_ACCT_FORCONTACT_NOTEXIST = 210108;
    // fax number[{0}] has exist.
    public static final long NEWREG_PROD_FAXNUMBEREXIST = 210109;
    // pricing plan for offering[{0}] does not exist
    public static final long NEWREG_PRICINGPLAN_PROD_NOTEXIST = 210112;
    // bill_cycle Attributes no value given
    public static final long NEWREG_BILL_CYCLE_NOT_FULL = 210113;
    // force_cycle can only for 0 or 1
    public static final long NEWREG_FORCE_CYCLE_NOTEXIST = 210114;
    // create bill cycle of account[{0}] error
    public static final long NEWREG_CREATE_BILL_CYCLE_ERROR = 210115;
    // cycle_Sepc_id dose not exist
    public static final long NEWREG_CYCLE_SEPC_ID_ERROR = 210116;
    // user[{0}] has no main promotion
    public static final long NEWREG_USER_HAS_NO_MAINPROD = 210117;
    // user[{0}] has more than one main promotion
    public static final long NEWREG_USER_HAS_MORE_MAINPROD = 210118;
    // offering[{0}] not related to user or account
    public static final long NEWREG_PROD_NO_USER_OR_ACCOUNT = 210120;
    // not find parentAccount[{0}] for account[{1}]
    public static final long NEWREG_PARENT_ACCOUNT_NOTEXIST = 210121;
    // paymode prepaid user cannot prospect and prematch
    public static final long NEWREG_USER_CANNOT_PROSPECT_AND_PREMATCH = 210122;
    // customer type [{0}] is invalid
    public static final long NEWREG_CUSTOMER_TYPE_IS_INVALID = 210123;
    // payment mode[{0}] is invalid
    public static final long NEWREG_PAYMENT_MODE_IS_INVALID = 210124;
    // user[{0}] of phone[{1}] has been prematched/prospected
    public static final long NEWREG_USER_HAS_BEEN_PREMATCH = 210126;
    // main promotion[{0}] of user[{1}] can't be prematch offering
    public static final long NEWREG_MAIN_PROMOTION_CANNOT_BE_PREMATCH_OFFER = 210127;
    // customer's contact[{0}] already exists
    public static final long NEWREG_CUSTOMER_CONTACT_IS_EXISTS = 210128;
    // account's contact[{0}] already exists
    public static final long NEWREG_ACCOUNT_CONTACT_IS_EXISTS = 210129;
    // paramter:[{0}]'s expire_date can't than the [{1}] early
    public static final long NEWREG_EXPIRE_DATE_INVALID = 210130;
    // account[{0}] topup faild
    public static final long NEWREG_TOPUP_FAILD = 210131;
    // offering[{0}] is not main product
    public static final long NEWREG_OFFERING_NOT_MAIN_PRODUCT = 210132;
    // single balance account[{0}]can't change bill cylce
    public static final long SINGLE_BALANCE_CHG_BILL_CYCLE = 210133;
    // bill Account[{0}] number of users can't satisfy offering [{1}] max subscriber limit
    public static final long USERS_NOT_SATISFY_SUBSCRIBER_LIMIT = 210134;
    // synchronization bill cycle of account[{0}] error
    public static final long NEWREG_SYN_BILL_CYCLE_ERROR = 210135;
    // phone[{0}] topup faild
    public static final long NEWREG_PHONE_TOPUP_FAILD = 210136;
    // account[{0}] already exists
    public static final long NEWREG_ACCOUNT_EXISTS = 210137;
    // user[{0}] already exists
    public static final long NEWREG_USER_EXISTS = 210138;
    // customer[{0}] already exists
    public static final long NEWREG_CUSTOMER_EXISTS = 210139;
    // credit value can't less than 0
    public static final long NEWREG_CREDIT_VALUE_ERROR = 210140;
    // account[{0}] of parent account[{1}]'s bill_sts is not active
    public static final long NEWREG_PARENT_ACCOUNT_IS_NOT_ACCTIVE= 210141;
    // the force account[{0}]'s bill_cycle can't be null
    public static final long NEWREG_BILL_CYCLE_IS_NULL = 210142;
    // the measure_id of account must be billing's measure_id
    public static final long NEWREG_ACCT_MEASURE_ERROR = 210143;
 // bill Account[{0}] number of users can't satisfy offering [{1}] max subscriber limit
    public static final long USERS_NOT_SATISFY_SUBSCRIBER_MAX_LIMIT = 210144;
    // bill Account[{0}] number of users can't satisfy offering [{1}] min subscriber limit
    public static final long USERS_NOT_SATISFY_SUBSCRIBER_MIN_LIMIT = 210145;
    
    
    // account[{0}] has been in the enterprise organization structure
    public static final long CARELATION_CA_HASBEENIN = 211400;
    // this two accounts have no relation:[{0}],[{1}]
    public static final long CARELATION_NORELATION = 211403;
    // user[{0}] has been in the enterprise organization structure
    public static final long CARELATION_USER_HASBEENIN = 211405;
    // user[{0}] does not in the enterprise organization structure
    public static final long CARELATION_USRE_NOTIN = 211407;
    // customer[{0}] have no cycle_spec_id
    public static final long CYCLE_SPEC_ID_NOTEXIST = 211412;
    // account[{0}] isn't a corporation account
    public static final long CARELATION_NOT_CORP_CA = 211413;
    // user[{0}] does not in relation
    public static final long CARELATION_USER_NOT_IN = 211415;
    // account[{0}] does not in relation
    public static final long CARELATION_CA_NOT_IN_RELATION = 211416;
    // parent account[{0}] and sub account[{1}] can not be the same account
    public static final long CARELATION_PARENT_SUB_SAME_ACCOUNT = 211417;
    // billSts of parent account[{0}] is not active
    public static final long CARELATION_PARENT_BILLSTS_NOTACTIVE = 211418;
    // parent account[{0}] is sub account of account[{1}]
    public static final long CARELATION_ADD_ERROR_FOR_PARENT_IS_SUB = 211419;

    // old IMSI[{0}] does not exist
    public static final long CHANGEMSISDNSIM_OLDSIM_NOTEXIST = 210504;
    // new phone number[{0}] has already been occupied
    public static final long CHANGEMSISDNSIM_NEWPHONE_EXIST = 210505;
    // new IMSI[{0}] has already been occupied
    public static final long CHANGEMSISDNSIM_NEWSIM_EXIST = 210506;
    // new phone number[{0}] is invalid
    public static final long CHANGEMSISDNSIM_PHONE_NOTVALID = 210507;
    // new IMSI[{0}] is invalid
    public static final long CHANGEMSISDNSIM_IMSI_NOTVALID = 210508;
    // Data is not legitimate, have more than one record
    public static final long DATE_NOT_LEGITIMATE = 210509;
    // new serial number[{0}] has already been accupied
    public static final long CHANGEMSISDNSIM_NEWSERIAL_EXIST = 210510;
    // serial number can't be empty
    public static final long CHANGEMSISDNSIM_SERIAL_EMPTY = 210511;
    //the serial number [{0}] does not match old serial number [{1}]
    public static final long CHANGEMSISDNSIM_SERIAL_NOT_MATCH = 210512;
    // parent Group can't be null
    public static final long CREATEGROUP_PARENTGROUP_NULL = 211209;
    //Group_type[{0}] not match config in Table SD.SYS_ENUM_DATA
    public static final long CREATEGROUP_GROUPTYPE_NOTCONFIG = 211210;

    // group[{0}] has owned max number[{1}] of close groups
    public static final long CREATEGROUP_TOUCH_MAX_CUG = 211213;
    // group type[{0}] is undefined
    public static final long CREATEGROUP_GROUP_TYPE_UNDEFINED = 211214;
    // parent group[{0}] is already a inner group
    public static final long CREATEGROUP_PARENTGROUP_IS_CUG = 211215;
    // offering product[{0}] is unknown type
    public static final long CREATEGROUP_UNKNOWNTYPE_OFFERPROD = 211216;
    // offering product[{0}] isn't a group product
    public static final long CREATEGROUP_NOT_GROUPPROD = 211217;
    // group must have one product at least
    public static final long CREATEGROUP_NEED_PROD = 211218;
    // the type of parent group[{0}] is unknown
    public static final long CREATEGROUP_PARENTGROUP_TYPE_UNKNOWN = 211219;

    // group id[{0}] does not exist
    public static final long CHANGEGROUPINFO_GROUPID_NOTEXIST = 214701;
    // nothing has been changed
    public static final long CHANGEGROUPINFO_NOTHING_CHANGED = 214705;
    // group[{0}] doesn't have special number[{1}]
    public static final long CHANGEGROUPINFO_SPEC_NBR_NOT_EXIST = 214707;

    // product[{0}] does not exist
    public static final long CHANGEGROUPPROD_PROD_NOTEXIST = 212001;
    // can't delete the only one group product[{0}] for group[{1}]
    public static final long CHANGEGROUPPROD_CANNOT_DEL_PROD = 212003;
    // billAccount of group[{0}] doesn't exist
    public static final long CHANGEGROUPPROD_BILLACCT_NOT_EXIST = 212004;
    // special number product[{0}] of group[{1}] exist
    public static final long CHANGEGROUPPROD_SPECNBRPROD_EXIST = 212005;
    // phone[{0}] is already special number of group[{1}]
    public static final long CHANGEGROUPPROD_SPECNBR_EXIST = 212006;
    // phone[{0}] is not special number of group[{1}]
    public static final long CHANGEGROUPPROD_SPECNBR_NOTEXIST = 212007;
    // can not add same special number[{0}]
    public static final long CHANGEGROUPPROD_SPECNBR_SAME = 212008;
    //group[{0}] don't order that productId[{1}] offerId[{2}] 
    public static final long GROUP_NOT_ORDER_PRODUCT=212009;

    // product[{0}] isn't an inter-group product
    public static final long VPNRELATION_NOT_INTERGROUP_PROD = 214800;
    // none inter group products in group[{0}]
    public static final long VPNRELATION_NO_INTERGROUP_PROD = 214801;
    // can't order inter product for group[{0}] to group[{1}]
    public static final long VPNRELATION_CANNOT_ORDER = 214804;
    // product[{0}] isn't group product between group[{1}] and group[{2}]
    public static final long VPNRELATION_NOT_GROUP_PROD = 214805;
    // expire date can't be null where extending product
    public static final long VPNRELATION_EXPIREDATE_NULL = 214806;
    // new expire date[{0}] is before original expire date[{1}]
    public static final long VPNRELATION_EXPIREDATE_ERROR = 214807;
    // group[{0}] and group[{1}] are different type of groups
    public static final long VPNRELATION_DIFFERENT_TYPE = 214809;
    // group[{0}] and group[{1}] are CUGs in the same group[{2}]
    public static final long VPNRELATION_CUGS_IN_SAME_GROUP = 214810;

    // budgetControlList is empty
    public static final long SETBGT_BUDGETCONTROLLISTEMPTY = 212801;
    // budget_type must in (1, 2, 3)
    public static final long SETBGT_BUDGETTYPEERROR = 212802;
    // busi_service_id must input when budget_type=3
    public static final long SETBGT_BUSISERVID_INVALID = 212803;
    // account budget can't larger than credit limit, acctId:[{0}],credit limit:[{1}]
    public static final long SETBGT_ACCTBUDGET_ERROR = 212804;
    // user budget can't larger than credit limit, acctId:[{0}],userId:[{1}],credit limit:[{2}]
    public static final long SETBGT_USERBUDGET_ERROR = 212805;
    // service budget can't larger than credit limit, acctId:[{0}],userId:[{1}],serviceId:[{2}],credit limit:[{3}]
    public static final long SETBGT_SERVICEBUDGET_ERROR = 212806;
    // account[{0}] has no credit
    public static final long SETBGT_ACCOUNT_CREDIT_INVALID = 212807;
    // prepaid(postpaid) user can only set prepaid(postpaid) budget
    public static final long SETBGT_BUDGET_NOT_MATCH_USER = 212808;
    // threshold value can't larger than budget value
    public static final long SETBGT_THRESHOLD_VALUE_LARGE_THAN_BUDGET = 212809;
    // spec char value[{0}] do not have default value in pm_prod_char_value
    public static final long PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE = 212810;
    // account and phone can't be exist at the same time
    public static final long SETBUT_ACCOUNT_USER_EXIST = 212813;
    // account and service can't be exist at the same time
    public static final long SETBUT_ACCOUNT_SERCICE_EXIST = 212814;
    public static final long SETBUT_BUDGET_NOT_EXIST = 212815;
    // single balance can't set postpaid budget
    public static final long SETBGT_BUDGET_NOT_MATCH_ACCT = 212816;

    // phone[{0}] of User[{1}] has been in a group
    public static final long MANAGEGROUPMEMBER_MEMBER_EXIST = 211302;
    // user[{0}] is not in the group[{1}]
    public static final long MANAGEGROUPMEMBER_MEMBER_NOTEXIST = 211303;
    // member list can't be null
    public static final long MANAGEGROUPMEMBER_MEMBERLIST_IS_NULL = 211304;
    // short phone number[{0}] has been exist in group[{1}]
    public static final long MANAGEGROUPMEMBER_SHORTPHONE_EXIST = 211306;
    // main phone in group[{0}] has been exist
    public static final long MANAGEGROUPMEMBER_MAINPHONE_EXIST = 211307;
    // user[{0}] which is out of parent group[{1}] can't join inner group[{2}]
    public static final long MANAGEGROUPMEMBER_CANNOT_JOIN_CUG = 211309;
    // group max number of member[{0}] limit
    public static final long MANAGEGROUPMEMBER_FULL = 211310;
    // can't find userId of pbx number[{0}]
    public static final long MANAGEGROUPMEMBER_NOTFIND_USEROF_PBX = 211313;
    // group's status is active now
    public static final long MANAGEGROUPMEMBER_GROUP_STS_ACTIVE = 211314;
    // group's status is suspend now
    public static final long MANAGEGROUPMEMBER_GROUP_STS_SUSPEND = 211315;
    // member[{0}] is active now
    public static final long MANAGEGROUPMEMBER_MEM_STS_ACTIVE = 211316;
    // member[{0}] is suspend now
    public static final long MANAGEGROUPMEMBER_MEM_STS_SUSPEND = 211317;
    // user[{0}] of phone[{1}] have been in [{2}] [{3}] groups
    public static final long MANAGEGROUPMEMBER_CANNOT_JOIN = 211319;
    // the user[{0}] of product is not in group which member is changed
    public static final long MANAGEGROUPMEMBER_NOT_CHANGE_PRODUCT = 211320;
    // [{0}] has no config in SD.SYS_PARAMETER 
    public static final long CHECK_MAX_JOIN_GROUP = 211321;

    // payment mode must in (0,1,2)
    public static final long PAYMODE_INVALID = 211600;
    // add business service list couldn't be empty when hybrid_rule in (1,3)
    public static final long PAYMODE_BUSILIST_EMPTY = 211601;
    // hybrid rule must in (1,2,3)
    public static final long PAYMODE_HYBRID_RULE_INVALID = 211604;
    // payment mode of service must in (0,1)
    public static final long PAYMODE_4SERVICE_INVALID = 211605;
    // service id must input for SBusiService
    public static final long PAYMODE_SERVICEID_INVALID = 211606;
    // outer_acct_id of SAccount is invalid
    public static final long PAYMODE_SACCOUNT_ACCTID_INVALID = 211607;
    // oper_type must in (0,1,2)
    public static final long PAYMODE_OPERTYPE_INVALID = 211609;
    // busiService can't be empty
    public static final long PAYMODE_BUSISERVICE_EMPTY = 211610;
    // the service_id:[{0}] can't be operated more than twice in one request
    public static final long PAYMODE_SERVICE_ID_DEAL_AGAIN = 211611;
    // sBusiServiceOper list can't be empty;
    public static final long PAYMODE_SBUSISERVICEOPER_INVALID = 211612;
    // can't modify this service paymode because the paymode is not exist, user_id:[{0}],service_id:[{1}]
    public static final long PAYMODE_4_SERVICE_NOT_EXIST = 211614;
    // when prepaid to hybrid, hybrid rule must equals 1
    public static final long PAYMODE_HYBRID_RULE_ERROR = 211615;
    // convert balance to promotion error
    public static final long PAYMODE_BALANCE2PROM_ERROR = 211616;
    // convert advance payment to balance error
    public static final long PAYMODE_PAYMENT2BALANCE_ERROR = 211618;
    // when new paymode is [{0}], product's billing_type must equals [{1}]
    public static final long PAYMODE_PROD_BILLINGTYPE_ERROR = 211619;
    // this user has no config default product_offering_id,user_id:[{0}]
    public static final long PAYMODE_USER_HAS_NO_DEFAULT_OFFERID = 211620;
    // in change payment mode business, user can subscribe user level product only,offer_id:[{0}]
    public static final long PAYMODE_ORDER_USER_PROD_ONLY = 211621;
    // services with same id[{0}] must be one prepaid and one postpaid
    public static final long PAYMODE_ONLY_2SAMESERVICES = 211626;
    // the prepaid one and postpaid one of same service[{0}] must be in separated timePeriod
    public static final long PAYMODE_TIMEPERIOD_OVERLAP = 211627;
    // the prepaid one and postpaid one of same service[{0}] must be in same timeMode
    public static final long PAYMODE_TIMEMODE_NOTSAME = 211628;
    // timeMode config error in table sys_time_seg_def for timePeriod[{0}] or timePeriod[{1}]
    public static final long PAYMODE_TIMEPERIOD_ERROR_DEF = 211629;
    // timePeriod config error in table sys_time_seg_dtl for timePeriod[{0}] or timePeriod[{1}]
    public static final long PAYMODE_TIMEPERIOD_ERROR_DTL = 211630;
    public static final long PAYMODE_MAIN_OFFER_MORE_THAN_ONE = 211631;
    public static final long PAYMODE_CANNOT_CHANGE_MAIN_PROD = 211632;
    public static final long PAYMODE_POSTPAID_OR_HYBIRD_NO_MAIN_OFFER = 211633;
    // when oper Type is 0 or 2 payment mode can not be 1
    public static final long PAYMODE_POSTPAID_ERROR = 211634;

    /* split */
    // splitRelList_Item is empty
    public static final long SPLIT_INPUT_LIST_EMPTY = 212600;
    // split account cycle do not equals pay account cycle
    public static final long SPLIT_CYCLE_NOT_EQUALS_PAY_CYCLE = 212603;
    // oper_type of SPaySplitInfo must in (0,1,2)
    public static final long SPLIT_INFO_OPER_TYPE_ERROR = 212605;
    // pay_acct_id must input
    public static final long SPLIT_PAYACCTID_ERROR = 212606;
    // part_type must in (0,1)
    public static final long SPLIT_PART_TYPE_ERROR = 212607;
    // split relationship account[{0}] to account[{1}] already exist
    public static final long SPLIT_4_ACCOUNT_EXIST = 212608;
    // split relationship account[{0}] to user[{1}] already exist
    public static final long SPLIT_4_USER_EXIST = 212609;
    // split relationship account[{0}] to account[{1}] does not exist
    public static final long SPLIT_4_ACCOUNT_NOTEXIST = 212610;
    // split relationship account[{0}] to user[{1}] does not exist
    public static final long SPLIT_4_USER_NOTEXIST = 212611;
    // split_type must in (0,1)
    public static final long SPLIT_TYPE_ERROR = 212613;
    // account can't split for itself
    public static final long SPLIT_4_ITSELF_ERROR = 212614;
    // product_sequence_id[{0}] is not match object_id[{1}]
    public static final long SPLIT_PRODUCT_ID_NOT_MATCH_OBJECT = 212615;

    /* reguide */
    // reguideRelList_Item is empty
    public static final long REGUIDE_INPUT_LIST_EMPTY = 212700;
    // oper_type for SPayReguidePart must in (0,1) or empty
    public static final long REGUIDE_PART_OPER_TYPE_ERROR = 212701;
    // reguideInfoList_item is empty
    public static final long REGUIDE_INFO_LIST_EMPTY = 212702;
    // oper_type of SPayReguideInfo must input when oper_type of SPayReguidePart is empty
    public static final long REGUIDE_INFO_OPER_TYPE_EMPTY = 212704;
    // oper_type of SPayReguideInfo must in (0,1,2)
    public static final long REGUIDE_INFO_OPER_TYPE_ERROR = 212705;
    // pay_acct_id must input
    public static final long REGUIDE_PAYACCTID_ERROR = 212706;
    // part_type must in (0,1)
    public static final long REGUIDE_PART_TYPE_ERROR = 212707;
    // reguide type must in (0,1)
    public static final long REGUIDE_TYPE_ERROR = 212708;
    // pay_phone_id must input
    public static final long REGUIDE_PAYPHONEID_ERROR = 212709;
    // sPayReguidePart can't be null
    public static final long REGUIDE_SPAYREGUIDEPART_NULL = 212710;
    // reguide relationship account[{0}] to account[{1}] already exist
    public static final long REGUIDE_4_ACCOUNT_EXIST = 212711;
    // reguide relationship account[{0}] to user[{1}] already exist
    public static final long REGUIDE_4_USER_EXIST = 212712;
    // reguide relationship account[{0}] to account[{1}] does not exist
    public static final long REGUIDE_4_ACCOUNT_NOTEXIST = 212713;
    // reguide relationship account[{0}] to user[{1}] does not exist
    public static final long REGUIDE_4_USER_NOTEXIST = 212714;
    // reguide relationship user[{0}] to user[{1}] already exist
    public static final long REGUIDE_4_USAGE_EXIST = 212715;
    // when reguide_type=1,part_type must equals 1
    public static final long REGUIDE_PART_TYPE_INVALID = 212717;
    // reguide type and pay flag must in (0,1)
    public static final long TYPE_OR_FLAG_ERROR = 212719;
    // threshold value can't larger than reguide value
    public static final long REGUIDE_THRESHOLD_VALUE_LARGE_THAN_AMOUNT = 212720;
    // percentage value can't larger than 100
    public static final long PERCENTAGE_VALUE_LARGER_THAN_100 = 212721;

    // reguided by self is error
    public static final long REGUIDE_FOR_ITSELF_ERROR = 212722;
    //new reguide value [{0}] can not less than old reguide [{1}]
    public static final long REGUIDE_CHARGE_VALUE_ERROR = 212723;
    //old max value is -1, new max value must be null
    public static final long REGUIDE_CHARGE_MAXVALUE_MUST_BE_NULL = 212724;
    //can't reguide charge amount, only can charge percentage
    public static final long REGUIDE_CHARGE_AMOUNT_ERROR = 212725;

    // customer not family customer
    public static final long CUS_NOT_FAMILY_CUS = 210415;
    // product with this product_seq [{0}]not user's product
    public static final long PRODUCT_NOT_USER_PRODUCT = 212148;
    // phone number[{0}] does not exist
    public static final long PHONE_NUMBER_NOT_EXIST = 212139;
    // phone number will add is null
    public static final long USER_ADD_FN_NULL = 212140;
    // phone number[{0}] isn't user's friend number
    public static final long PHONE_NOT_USER_FN = 212141;
    // phone number is user's friend number
    public static final long UESR_FN_EXIST_PHONE = 212142;
    // product with this product_seq [{0}]not friend number product
    public static final long PRODUCT_WITH_SEQ_NOT_EXIST = 212147;
    // product with product_id[{0}],number count not enough
    public static final long COUNT_NOT_ENOUGH = 212149;

    // product billing type didn't fit the billing type of subscriber
    public static final long PRODUCT_BILLTYPE_INVALIDE = 212150;

    // query condition must have one of product_id and offer_id
    public static final long PRODUCT_QUERY_COND_NOT_VALID = 212151;
    // fixed_expire_date {0} is null when the valid_type is fixed expiry 
    public static final long PRODUCT_FIXED_EXPIRE_DATE_NULL= 212152;
    //product_expire_date{0}_after_fixed_expire_date{1}
    public static final long PRODUCT_EXPIRE_DATE_AFTER_FIXED_EXPIRE_DATE= 212153;
    //product_expire_date{0}_before_billing_end_date{1}
    public static final long PRODUCT_EXPIRE_DATE_BEFORE_BILLING_END_DATE= 212154;
    

    // product_id and product_sequence cannot both be null
    public static final long QUERYFN_PROD_ID_AND_SEQ_ALL_NULL = 213504;
    // user hasn't friend number under this product_sequence
    public static final long QUERYFN_NO_FRIEND_NUMBERS = 213505;

    // new phone[{0}] is under the same user's call screen
    public static final long MCS_NEW_ACCT_IS_UNDER_CUS = 213404;
    // this parameter[{0}]is null
    public static final long MCS_PARAMETER_IS_NULL = 213405;
    // this parameter[{0}] is error
    public static final long MCS_PARAMETER_IS_ERROR = 213406;
    // weekStop must be larger than WeekStart
    public static final long MCS_WEEKSTOP_MUST_LARGER = 213407;
    // screen list info is null
    public static final long MCS_SCREEN_LIST_INFO_IS_NULL = 213408;
    // user hasn't black and white list product
    public static final long MCS_USER_NO_BLACK_AND_WHITE_LIST_PROD = 213410;
    // phone[{0}] isn't in user's call screen
    public static final long MCS_PHONE_NOT_USERS_CALLSCREEN = 213411;
    public static final long MCS_CANNOT_SET_BLACK_TYPE = 213412;
    public static final long MCS_CANNOT_SET_WHITE_TYPE = 213413;
    public static final long MCS_LIST_TYPE_NOT_FIX = 213414;
    // Pls check configration of PM_PRODUCT_OFFER_SPEC_CHAR[{0}]
    public static final long MCS_CONF_PMPRODUCTOFFERSPECCHAR = 213415;
    //2012-06-12 zhangzj3  黑白名单时间段重叠
    public static final long MCS_TIME_IS_OVERLAP = 213415;

    /************************* Modify account info ******************************/
    // acct_id can not be null
    public static final long MODIFY_ACCOUNT_ID_NOT_EXITS = 210303;
    // payment type can not be null
    public static final long MODIFY_PAYMENT_TYPE_NOT_EXITS = 210304;
    // oper type can not be null
    public static final long MODIFY_OPER_TYPE_NOT_EXITS = 210305;
    // contact type can not be null
    public static final long MODIFY_CONTACT_TYPE_NOT_EXITS = 210306;
    // force_cycle already exist
    public static final long FORCE_CYCLE_ALREADY_EXISTS = 210307;
    // account contact not exist
    public static final long DEL_ACCOUNT_CONTACT_NOT_EXITS = 210308;
    // contact type [{0}] is invalid
    public static final long MODIFY_CONTACT_TYPE_IS_INVALID = 210309;
    // account[{0}] don't allowed to change the record before next cycle effective
    public static final long NEXT_CYCLE_EFFECTIVE = 210310;
    /************************* Modify account info end ******************************/

    // main promotion should be added and deleted together.
    public static final long MAIN_PROMOTION_OP_ERROR = 211901;
    // account level can't order PrimaryProduct
    public static final long ACCT_CANOT_ORDER_MAIN_PROD = 211902;
    // user has order PrimaryProduct
    public static final long USER_HAS_ORDER_MAIN_PROD = 211903;
    // oper_type [{0}]is not in <0,1,2,3>
    public static final long CHANGE_PRODUCT_OPER_TYPE_NOEXSIT = 211909;
    // when validType = 4,validate must be filled
    public static final long CHANGE_PROD_VALIDATE_IS_NULL = 211912;
    // account or user can't order group product
    public static final long CHANGE_PROD_PERSONAL_CANOT_ORDER_GROUP_PROD = 211915;
    // iMSI [{0}] has exist
    public static final long CHANGE_PROD_IMSI_IS_EXIST = 211916;
    // this Param_id:[{0}] not found by offering:[{1}] and busi_flag: [{2}]
    public static final long CHANGE_PROD_SPEC_CHAR_NOT_FOUND_BY_OFFER = 211918;
    // account_id:[{0}] hasn't billcycle
    public static final long CHANGE_PROD_ACCONT_NO_BILLCYCLE = 211920;
    // when change main product,the target product offering [{0}] cannot pre_match offering
    public static final long TARGET_MAIN_OFFERING_IS_PRE_MATCH = 211921;
    // user or group has order black or white call screen product
    public static final long MCS_PRODUCT_IS_EXIST = 211922;
    // order product auth deduct fail:[{0}]
    public static final long AUTH_DEDUCT_FAILL = 211923;
    // this product [{0}] is suspend
    public static final long PROD_IS_SUSPEND = 211927;
    // valid time of will be add product must lager than an offering bill cycle
    public static final long VALIDTIME_LOWER_THAN_OFFERING_CYCLE = 211928;
    // account can not order personal group product
    public static final long CHANGE_PROD_ACCOUNT_CANOT_ORDER_PERSONAL_GROUP_PROD = 211929;
    // there is no record about in DB
    public static final long CHANGE_PROD_NO_REECORD = 211931;
    // account cycles are all expired when this product will be valid
    public static final long CHANGE_PROD_ACCOUNT_CYCLE_ALL_EXPIRED = 211932;
    // the configuration of expire_date and validDate is wrong
    public static final long CHANGE_PROD_CONFIG_OF_EXPIRE_AND_VALID_DATE = 211933;
    // configration of PM_PRODUCT_OFFER_SPEC_CHAR is wrong: value is not in COUNTRY_ID of SYS_COUNTRY
    public static final long CONFIG_OF_COUNTRY = 211934;
    // the UN_SUB_DELAY [{0}] of PM_PRODUCT_OFFER_LIFECYCLE is wrong
    public static final long CONFIG_OF_OFFER_LIFECYCLE_IS_WRONG = 211935;
    // can't order UP_SALE product offering
    public static final long CANOT_ORDER_UPSALE_PRODUCT = 211936;
    // cycle_type [{0}] is wrong
    public static final long CYCLE_TYPE_WRONG = 211938;
    // when add a main product,offer_id [{0}] must a main product offering
    public static final long OFFER_NOT_MAIN_OFFERING = 211939;
    // when replace main product,add product must a main product and prod_type must be: 2
    public static final long PRODUCT_TYPE_WRONG = 211940;
    // the product by id[{0}] is not a main product
    public static final long PRODUCT_BY_ID_IS_COMMON = 211941;
    // main product offer by offer_id: [{0}] not in a group
    public static final long OFFERING_NOT_IN_GROUP = 211944;
    // bill_type cannot be null where user's billing type is hybrid
    public static final long CHANGE_PROD_BILLING_TYPE_IS_NULL = 211945;
    // bill_type [{0}] not match user's billing_type
    public static final long BILL_TYPE_NOT_MACTH_USER = 211946;
    // product_id [{0}] has exist
    public static final long PRODUCT_ID_HAS_EXIST = 211947;
    // this offer [{0}] cannot order by user
    public static final long USER_LEVEL_ONLY_ORDER_USER_LEVEL = 211948;
    // this offer [{0}] cannot order by account
    public static final long ACCT_LEVEL_CANNOT_ORDER_USER_LEVEL = 211949;
    // this offer [{0}] cannot order by group
    public static final long GROUP_LEVEL_ONLY_ORDER_GROUP_LEVEL = 211950;
    // one time promotion cannot next cycle valid
    public static final long ONE_TIME_CANNOT_NEXT_CYCLE_VALID = 211953;
    // valid_date[{0}] of trial period is after expire_date[{1}] of product
    public static final long CHANGE_PROD_TRIAL_VALID_ERROR = 211954;
    // type of group by id [{0}] is not community
    public static final long CHANGE_PROD_GROUP_NOT_COMMUNITY = 211955;
    // when account cycle has cycle_type=0 and count of cycle only one
    public static final long CHANGE_PROD_ACCT_CYCLE_WRONG = 211956;
    // when order a main promtion<offer_id = [{0}]>,need data in PM_PRODUCT_OFFER_SPEC_CHAR,SPEC_CAHR_ID=[{1}]
    public static final long CHANGE_PROD_NEED_SPEC = 211957;
    // bill_type [{0}] not match of product_offering's bill_type " [{1}]
    public static final long CHANGE_PROD_BILL_TYPE_NOT_FIX = 211958;
    // main product can't modify or extend
    public static final long CHANGE_PROD_MAIN_PROD_CANNOT_EXTEND = 211959;
    public static final long PROD_HAD_UPSELLED = 211960;
    public static final long CANNOT_AMEND_PRIPAID_PROD = 211961;
    public static final long AMEND_VALID_DATE_MUST_BEFORE_OLD = 211962;
    public static final long AMEND_DATE_PROD_CYCLE_MUST_SAME_AS_ACCT = 211963;
    public static final long CHANGE_PROD_SPEC_CHAR_NOT_CONFIG=211964;
    // user<{0}> has order FPH products
    public static final long CHANGE_PROD_FPH_EXIST = 211965;
    // amend valid_date can not before account current cycle start [{0}] 
    public static final long AMEND_VALID_DATE_CAN_NOT_BEFORE_CYCLE_START = 211966;
    
    public static final long AMEND_EXPIRE_DATE_MUST_AFTER_OLD = 211967;
    // this offer [{0}] hasn't price plan
    public static final long PRICE_PLAN_NOT_EXIST = 211968;
    // account can not change main product.product_id[{0}],offer_id[{1}]
    public static final long CHANGE_PROD_ACCT_CANNOT_CHGMAINPROD = 211969;
    // order product offer_id[{0}] which busi_flag=145 must be authentication
    public static final long CHANGE_PROD_145_MUST_AUTH = 211970;
    // configration of sys_country is wrong : country_name[{0}]'s time_zone is null
    public static final long TIME_ZONE_NULL = 211971;
    // the configuration of expire_date[{0}] can't than the sysdate[{1}] early
    public static final long CONFIG_EXPIRE_DATE_INVALID = 211972;
    // the offering[{0}] can't be order for limit in pm_prod_busi_limit
    public static final long ORDER_PROD_LIMIT = 211973; 
    
    /************************* Modify product state ******************************/
    // product does not exist
    public static final long MODIFY_PROD_NOT_EXITS = 212200;
    // new state can not be null
    public static final long MODIFY_PROD_STS_NOT_EXITS = 212201;
    // product do not be terminate
    public static final long PRODUCT_CAN_NOT_BE_TERMINATE = 212204;
    // modify product state info must be active or pause
    public static final long PROD_STS_INFO_NOT_EXITS = 212205;
    // product id can not be null
    public static final long PRODUCTID_IS_NULL = 212208;
    // main promotion can't be modified by this interface
    public static final long MAINPROM_CANT_MODIFY_STATE = 212209;
    // product order [{0}] is already in state: [{1}]
    public static final long PRODUCT_STS_HAS_EXITS = 212210;
    // user [{0}]'s balance not enough
    public static final long RESOURCE_BALANCE_NOT_ENOUGH = 212211;
    //product status can not change to  active  or pause when the old status is suspend
    public static final long  PROD_STS_CHANGE_FORBIDDEN = 212212;

    /************************* Modify customer info *******************************/
    // customer id can not be null
    public static final long MODIFY_CUST_INFO_CUST_ID_NOT_NULL = 210200;
    // customer [{0}]not exist
    public static final long MODIFY_CUST_INFO_CUST_NOEXSIT = 210201;
    // party [{0}] not exist
    public static final long MODIFY_CUST_INFO_PARTY_NOEXSIT = 210202;
    // oper_type for modify cust must in (0-add, 1-del, 2-mod)
    public static final long MODIFY_CUST_CONTACTOPER_OPER_TYPE = 210220;
    // customer contact not exist
    public static final long DEL_CUST_CONTACT_NOT_EXITS = 210203;

    // user's service not exist
    public static final long USER_SERVICE_NOT_EXIST = 230400;

    /******************************* LifeCycle **************************/

    // user[{0}] lifeCycle transfer where patternId is [{1}], sts is [{2}], os_sts is [{3}] and trgger_enent_type[{4}] is illegal
    public static final long LIFE_CYCYLE_TRANSFER_ILLEGAL = 212302;

    // user[{0}] lifeCycle is null
    public static final long LIFE_CYCLE_IS_NULL = 212303;

    // user[{0}] not matching group
    public static final long USER_NOT_MATCHING_GROUP = 212304;

    // user lifecycle sts[{0}],patternID [{1}] lifecycle pattern detail doesn't exist
    public static final long USER_CYCLE_PATTERN_DETAIL_NOTEXIST = 212305;

    // user[{0}] don't have main promotion
    public static final long USER_NOT_HAVE_MAINPROMOTION = 212306;

    // policy[{0}] is null
    public static final long POLICY_IS_NULL = 212307;

    // group[{0}] not have normal init sts
    public static final long GROUP_NOT_HAVE_INITSTS = 212308;

    // user lifeCycle trigger event type can not be null
    public static final long USER_LIFECYCLE_TRIGGER_EVENT_TYPE_IS_NULL = 212311;

    // user[{0}] lifecycle must be idle or pool or termianl or active
    public static final long LIFECYCLE_STS_ILLEGAL = 212313;

    // user[{0}]'s flag[{1}] type [{2}] is exist
    public static final long LIFECYCLE_FLAG_IS_ALREADY_EXIST = 212315;

    // user[{0}]'s sts[{1}] is already exist
    public static final long LIFECYCLE_STS_IS_ALREADY_EXIST = 212316;

    // user[{0}] not matched pattern
    public static final long LIFECYCLE_GROUP_NOT_MATCHING_PATTERN = 212318;

    // soNbr[{0}] is not matched
    public static final long LIFECYCLE_UNDO_VALIDITY_INNER_SONBR_NOT_EXIST = 212320;

    // product offering[{0}] configuration error
    public static final long LIFECYCLE_PROD_CONFIG_ERROR = 212322;

    // sonbr[{0}] validity has not be changed
    public static final long LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED = 212324;

    // account[{0}] max validity is null
    public static final long LIFECYCLE_ACCOUNT_MAX_VALIDITY_IS_NULL = 212325;

    // extending the days of validity exceeded the configured maximum of account[{0}]
    public static final long LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH = 212326;

    // updated singleBalance's validity, acctId can't for empty
    public static final long LIFECYCLE_UPDATE_SINGLEBALANCE_ACCT_IS_NULL = 212330;

    // updated user's validity, userId can't for empty
    public static final long LIFECYCLE_UPDATE_SINGLEBALANCE_USER_IS_NULL = 212331;

    // according to patternId[{0}],old sts[{1}], old os_sts[{2}], new sts[{3}], trigger_event_type[{4}] cann't find target os_sts
    public static final long LIFECYCLE_TARGET_OSSTS_IS_NULL = 212332;

    // lack date of offering which busi_flag is [{0}]
    public static final long LIFECYCLE_LACK_UNIDENTITY_OFFERING = 212334;

    // the validity of the user[{0}] can only deduct to terminal state
    public static final long LIFECYCLE_CAN_NOT_DEDUCT_AFTER_TERMINAL = 212335;

    // the validity of the terminal user[{0}] can not be changed
    public static final long LIFECYCLE_TERMINAL_USER_CAN_NOT_ADJUST_VALIDITY = 212336;

    // reason_days can't be null due to user[{0}] request suspend
    public static final long LIFECYCLE_USER_REQ_SUSPEND_REASON_DAYS_IS_NULL = 212337;

    // user[{0}] in the fraud state cannot adjust the validity
    public static final long LIFECYCLE_IN_FRAUD_CAN_NOT_ADJUST_VALIDITY = 212338;
    
    // the status of target user must be active, suspend or disable
    public static final long TRANSFER_TARGET_USER_STS_ILLEGAL = 212339;
    
    // Because the prepaid user[{0}] state[{1}] is not active so can't be set to user_request_flag
    public static final long LIFECYCLE_STS_ISNOT_ACTIVE_CANNOT_SUSPEND = 212340;
    
    // can not adjust the validity of secondary balance
    public static final long ADJUST_SECONDARY_BALANCE_FORBIDDEN = 212341;
    
    // the billingType of target user must be prepaid when adjust validity
    public static final long TRANSFER_TARGET_USER_BILLINGTYPE_ILLEGAL = 212342;

    /******************************** Change owner *********************************/
    // offer_id[{0}]'s billingType not matching user's billingType
    public static final long CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER = 211701;

    // offer_id[{0}]'s billingType not matching user[{1}]'s billingType
    public static final long CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_USER = 211702;

    // offer_id[{0}]'s attribute is null
    public static final long CHANGE_OWNER_OFFERING_ATTRIBUTE_IS_NULL = 211703;
    
    // the request parameter of bill_type not match of offerig's bill_type
    public static final long CHANGE_OWNER_OFFER_BILLINGTYPE_NOT_MATCHING_PROD = 211704;
    
    // the master user of single balance or the master number of family can not be change owner
    public static final long MASTER_USER_CAN_NOT_BE_CHANGE_OWNER = 211705;
    
    //the main promotion oper_type not match change_promotion_flag
    public static final long MAIN_PROMOTION_OPER_TYPE_NOT_MATCH_CHANGE_PROMOTION_FLAG = 211706;

    /******************************** Terminal user *********************************/
    // last term flag can not be null
    public static final long TERMINAL_USER_LAST_TERM_FLAG_ILLEGEL = 212401;
    // the drop promtion flag is illegel
    public static final long TERMINAL_USER_DROP_PROMOTION_FLAG_ILLEGEL = 212403;

    // user[{0}]'s lifeCycle not existence
    public static final long TERMINAL_USER_LIFECYCLE_NOT_EXIST = 212404;

    // user[{0}] already terminated
    public static final long TERMINAL_USER_ALREADY_TERMINATE = 212405;
    
    // the master user of single balance or the master number of family can not be terminal
    public static final long MASTER_USER_CAN_NOT_BE_TERMINATE = 212406;
    
    // group_id cannot be null
    public static final long GROUP_ID_IS_NULL = 212901;
    // group not exist
    public static final long GROUP_NOT_EXIST = 212902;
    // parameter is illegal
    public static final long PARAM_IS_ILLEGAL = 217100;
    // sProductStatusList is empty
    public static final long SYNC_PROD_STS_PRODLIST_EMPTY = 220100;
    // group can not be deleted because of existing members.
    public static final long DELGROUP_EXIST_MEMBERS = 213300;

    /******************************** One Time Fee *********************************/
    // charge item does not exist
    public static final long CHARGE_ITEM_NOT_EXIST = 216400;
    // price plan id must be config
    public static final long NOT_CONFIG_PRICE_PLAN_ID = 216401;
    // one time fee is error
    public static final long ONETIMEFEE_ERROR = 216402;
    // sff one time fee charge need to config pirce id
    public static final long ONETIMEFEE_NOT_CONFIG_PRICE = 216403;
    //the out so nbr do not have  bos so nbr to match
    public static final long BOS_SONBR_NOT_EXIST = 216405;
    //feeitem[{0}] authentication failed
    public static final long ONETIMEFEE_AUTH_FAILED = 216406;
    /*************************** Auto topup **********************************/
    // phone[{0}] belong acct[{1}] has order e top-up product
    public static final long PHONE_BELONG_ACCT_ORDER_E_TOP_UP_EXIST = 218102;
    // phone[{0}] not order e top-up product
    public static final long PHONE_BELONG_ACCT_NOT_ORDER_E_TOP_UP = 218103;
    // prod [{0}]has no char values
    public static final long PROD_HAS_NO_CHAR_VALUES = 218104;
    // pay_mode not exist
    public static final long PAY_MODE_NOT_EXIST = 218105;
    // auto top-up undo the on demand
    public static final long AUTO_TOPUP_UNDO_ON_DEMAND = 218106;
    // every week date can't larger than 7 (1:Sunday, 2:Monday, 3-Tuesday, 4-Wednesday, 5-Thursday, 6-Friday, 7-Saturday)
    public static final long AUTO_DATE_LARGER_THAN_7 = 218108;
    // every month date can't larger than 28
    public static final long AUTO_DATE_LARGER_THAN_28 = 218109;

    /******************************** payment *******************************/
    // paymentInfo can't be null
    public static final long PAYMENT_INFO_NULL = 215600;
    // payment list can't be null
    public static final long PAYMENT_PAYLIST_NULL = 215605;
    // payment item list can't be null
    public static final long PAYMENT_ITEM_NULL = 215606;
    // payment item code can't be null
    public static final long PAYMENT_ITEMCODE_NULL = 215607;
    // pay type is undefined
    public static final long PAYMENT_PAYTYPE_UNDEFINED = 215609;
    // invoice number can't be null
    public static final long PAYMENT_INVOICENO_NULL = 215610;
    // can't find original payment so_nbr for outer so_nbr[{0}]
    public static final long PAYMENT_CANOT_FIND_ORSONBR = 215611;

    /********* set exempt credit limit ********/
    // when modify notification exemption,the so_nbr do not be null
    public static final long USER_DE_OR_ADD_IS_NULL = 218600;
    // notification exemption not exist
    public static final long ACCOUNT_NOTI_NOT_EXIST = 218605;
    // the set exemption credit limit time can not be overlap
    public static final long SET_CREDIT_LIMIT_TIME_OVERLAP = 218606;
    // each postpaid user has not been actived
    public static final long POSTPAID_HAS_SUSPEND = 218607;
    // under the account only has prepaid users
    public static final long ONLY_HAVE_PREPAID = 218608;
    // not config exemption type
    public static final long NOT_CONFIG_FLAG = 218609;
    // account [{0}] has exemption type stats
    public static final long ACCT_HAS_EXEMPT = 218610;
    // input billing type[{0}] not match user[{1}]'s billing type
    public static final long BILLINT_TYPE_NOT_MATCH = 218611;
    /*************************** setCreditLimit ****************/
    // credit_type [{0}] is null or is not in (1,2,3)
    public static final long CREDIT_TYPE_NULL_OR_INVALID = 215101;
    // creditLimitList can not be null
    public static final long CREDIT_LIMIT_ITEM_NULL = 215104;
    // lack of SSetCreditLimit information
    public static final long SSETCREDITLIMIT_CAN_NOT_BE_NULL = 215105;

    /**************************** setNegativeBalance **********************/
    // negative balance is null
    public static final long NEGATIVE_BALANCE_ERROR = 215200;
    // lack of SSetNegativeBalance information
    public static final long SSETNEGATIVEBALANCE_CAN_NOT_BE_NULL = 215201;
    // the parameter ownerType and account's balanceType not consistent, the ownerType of parameter is [{0}],the balanceType of account is [{1}]
    public static final long SSETNEGATIVEBALANCE_PARAM_WRONG = 215202;

    /***************************** Qeury group memeber info ******************/
    // the user[{0}] is not a group member
    public static final long USER_IS_NOT_GROUP_MEMBER = 213001;
    // group member does not exist
    public static final long GROUP_MEMBER_NOT_EXIST = 213002;
    // can not find user by phoneId[{0}]
    public static final long USER_NOT_EXIST_FOR_PHONEID = 213004;
    // group is not VPN group
    public static final long GROUP_NOT_VPN = 213005;

    /************************ query group info *****************************/
    // query_type is wrong
    public static final long QUERY_TYPE_IS_WRONG = 212900;

    /****************************** modify account status *********************/

    /***************************** Auth Parent **********************************/
    // phone [{0}] belong to account not exist
    public static final long PHONE_BELONG_ACCOUNT_NOT_EXIST = 214404;
    // two object have no parent relation
    public static final long TWO_OBJECTS_HAVE_NO_PARNET_RELATION = 214406;
    // [{0}] can not be same
    public static final long AUTH_PARENT_TWO_OBJS_ARE_SAME = 214407;

    /****************************** change bill cycle *****************************/
    // bill cycle not exist
    public static final long BILL_CYCLE_NOT_EXIST = 218000;
    // account use customer's billcycle, don't modify account's billcycle
    public static final long BILL_CYCLE_NOT_MODIFY = 218001;
    // account [{0}] 'bill_sts not define or terminate
    public static final long BILL_STS_NOT_DEFINE = 218002;
    // account[{0}] has split or be split
    public static final long BILL_CYCLE_ACCOUNT_HAS_SPLIT = 218003;
    // parameter[{0}]'s value[{1}] is invalid
    public static final long PARAMETER_IS_NOT_RIGHT = 218004;
    // cycle_spec_id[{0}] not exist
    public static final long CYCLE_SPEC_NOT_EXIST = 218005;
    // period_id[{0}] not exist
    public static final long PERIOD_ID_NOT_EXIST = 218006;
    // table[{0}] is null or has more than one record
    public static final long SIZE_OF_TABLE_ERROR = 218007;
    // create short ca_billing_plan fail 
    public static final long CREATE_SHORT_CA_BILLING_PLAN_FAIL = 218008;
    // create long ca_billing_plan fail 
    public static final long CREATE_LONG_CA_BILLING_PLAN_FAIL = 218009;
    // bill_cycle_month is null
    public static final long CA_ACCOUNT_DEBT_NOT_RIGHT = 218010;
    // next bill cycle date should great than old bill cycle date
    public static final long NEXT_BILL_CYCLE_ERROR = 218011;
    // ca_billing_period is null
    public static final long CA_BILLING_PERIOD_NOT_RIGHT = 218012;
 
    
    /********************************* query work log ***********************************/
    // start_date can not be null
    public static final long START_DATE_IS_NULL = 214200;
    // target phone_id can not be null
    public static final long TAG_PHONE_IS_NULL = 214201;
    // query_type vaule is invaild
    public static final long QUERY_TYPE_IS_INVAILD = 214202;
    // recharge_channel value can not be null
    public static final long RECHARGE_CHANNEL_IS_NULL = 214203;
    // target phone hasn't belong account
    public static final long TARGET_PHONE_BELONG_ACCOUNT_NOT_EXIST = 214204;

    // phone_item_list can't be empty
    public static final long MAXFREE_PHONE_ITEM_LIST_EMPTY = 218401;
    // phone_id can not be empty
    public static final long MAXFREE_PHONE_EMPTY = 218402;
    // productSeqItem is empty
    public static final long MAXFREE_PRODUCTSEQITEM_EMPTY = 218403;
    // flag must in (0,1)
    public static final long MAXFREE_FLAG_INVALID = 218404;
    // product_seq_id can't be null or 0
    public static final long MAXFREE_PRODUCTSEQID_INVALID = 218405;
    // resource_id can't be null or 0
    public static final long MAXFREE_RESOURCEID_INVALID = 218406;
    // this account can't share product to this user, acctId:[{0}],userId:[{1}]
    public static final long MAXFREE_USERID_INVALID = 218408;
    // product_seq_id not exists or product_seq_id not belongs to this account,product_seq_id:[{0}],acct_id:[{1}]
    public static final long MAXFREE_PRODSEQID_USERID_ERROR = 218409;
    // this resource_value [{0}] cann't greater than product's maxFreeResource [{1}]
    public static final long MAXFREE_MAXFREERESOURCE_NOT_ENOUGH = 218410;
    // this product[{0}] is not belong any account or user
    public static final long MAXFREE_MAXFREERESOURCE_PROD_BELONG_OBJECT = 218411;
    // can not find the product by id:[{0}]
    public static final long CAN_NOT_FIND_PROD_BY_ID = 231000;

    /********************************* first active by dcc ***********************************/
    // query data from mdb is failed
    public static final long QUERY_DATA_FROM_MDB_ERROR = 230300;
    // query userinfo from mdb is failed
    public static final long QUERY_USERINFO_ERROR = 230301;
    // query custinfo from mdb is failed
    public static final long QUERY_CUSTINFO_ERROR = 230302;
    // query usercycle from mdb is failed
    public static final long QUERY_USERCYCLE_MDB_ERROR = 230303;
    // query prodinfo from mdb is failed, serverId:[{0}]
    public static final long QUERY_PRODINFO_ERROR = 230304;
    // query billcycle from mdb is failed, serverId:[{0}]
    public static final long QUERY_BILLCYCLE_ERROR = 230305;
    // query product price from mdb is failed, serverId:[{0}]
    public static final long QUERY_PRODUCT_PRICE_ERROR = 230306;
    // user original lifecycle isn't idle
    public static final long USER_DO_NOT_ACTIVE = 230307;
    // sync data to mdb is failed
    public static final long SYNC_DATA_TO_MDB_FAILED = 230308;
    // user doesn't have main promotion
    public static final long USER_HAVE_NO_MAIN_PROM = 230309;
    // not find data [{0}] in context
    public static final long NOT_DATA_IN_CONTEXT = 230310;
    // not find data [{0}] by segId: [{1}]
    public static final long NOT_DATA_IN_SYSTIMESEGDEF = 230311;
    // time mode:[{0}] not define
    public static final long TIME_MODE_NOT_DEFINE = 230312;
    // query accountinfo from mdb is failed
    public static final long QUERY_ACCOUNTINFO_ERROR = 230313;
    // active gprs url not define, price_rule_id:[{0}]
    public static final long ACTIVE_GPRS_URL_NOT_DEFINE = 230315;

    // price_plan_id not defined
    public static final long PRICE_PLAN_ID_NOT_DEFINE = 230316;

    // price_rule_id not defined
    public static final long PRICE_RULE_ID_NOT_DEFINE = 230317;

    /********************************* first active by dcc ***********************************/

    /************************* manager IR party **********************/
    // oper info has exist
    public static final long RS_SYS_OPER_INFO_HAS_EXIST = 218500;
    // oper info not exist
    public static final long RS_SYS_OPER_INFO_NOT_EXIST = 218501;
    // oper service has exist
    public static final long RS_SYS_OPER_SERVICE_HAS_EXIST = 218502;
    // oper service not exist
    public static final long RS_SYS_OPER_SERVICE_NOT_EXIST = 218503;
    // when adding operator profile ,the service_oper _type must be add
    public static final long OPER_TYPE_IS_ADD = 218504;
    // when modify operator profile or delete operator profile ,the param ServiceRequestOper should be null
    public static final long OPER_TYPE_IS_MODIFY_OR_DELETE = 218505;
    // the operator:[{0}] not define
    public static final long OPERATOR_MAP_ERROR = 218506;
    // table PM_SERVICE_SPEC not config service id
    public static final long RS_SYS_OPER_SERVICE_NOT_CONFIG = 218507;
    // table RS_SYS_ELEMENT_CALL_TYPE not config call type id [{0}];
    public static final long CALL_TYPE_NOT_CONFIG = 218508;
    // Can not delete operator info when have services
    public static final long NOT_DEL_INFO_HAS_SERVICE = 218509;
    // the operator_ID should be null when adding oper info
    public static final long NULL_OPERID_ADDING_OPER = 218510;
    // there will be overlap period between two statuses
    public static final long PERIOD_OVERLAP = 218511;
    // input period is wrong
    public static final long PERIOD_ERROR = 218512;
    /******************************** Query **************************************/

    // acctId and userId is both null
    public static final long ACCT_ID_AND_USER_ID_BOTH_NULL = 231200;
    // can not find CmIndividual by this partyid:[{0}]
    public static final long CM_INDIVIDUAL_IS_NOT_EXIST = 231202;
    // can not find primary product by resourceid:[{0}]
    public static final long CAN_NOT_FIND_PRIMARY_PRODUCT_BY_USER_ID = 231203;
    // can not find CmIndividualName by this cust id:[{0}]
    public static final long CM_INDIVIDUAL_NAME_IS_NOT_EXIST = 231204;
    // can not find ca_account_rel by acct id:[{0}]
    public static final long CAN_NOT_FIND_CA_ACCOUNT_REL = 231205;
    // rel account id is null
    public static final long REL_ACCCOUNT_ID_IS_NULL = 231206;
    // none order this product :[{0}]
    public static final long NONE_ORDER_THIS_PRODUCT = 231400;
    // acct is not exist by phone id: [{0}]
    public static final long ACCT_IS_NOT_EXIST_BY_DEV = 232100;
    // acct is not exist by user id: [{0}]
    public static final long ACCT_IS_NOT_EXIST_BY_USER_ID = 232101;
    // can't find list of cm_res_identity by dev: devid[{0}]
    public static final long CAN_NOT_FIND_LIST_OF_CM_RES_IDENTITY_BY_DEV = 232200;
    // can't find list of cm_res_identity by user id: user id[{0}]
    public static final long CAN_NOT_FIND_LIST_OF_CM_RES_IDENTITY_BY_USER_ID = 232201;
    // can't find cust by acct : acctid[{0}]
    public static final long CAN_NOT_FIND_CUST_BY_ACCT = 232300;

    // product offering relation not exist
    public static final long PRODUCT_OFFERING_REL_NOT_EXIST = 231100;
    // product [{0}] billcycle nont exist
    public static final long PRODUCT_BILL_CYCLE_NOT_EXIST = 231101;
    // product [{0}] not under an account or user
    public static final long PRODUCT_NOT_UNDER_ACCOUNT_OR_USER = 231102;

    /*************************************** BankInfo Configuration *********************/
    // sysBankInfo is not exist
    public static final long SYS_BANK_INFO_NOT_FOUND = 218900;
    /***************************** GSM Top Up *******************/
    // service type is null
    public static final long SERVICE_TYPE_IS_NULL = 219100;
    // top up amount is null
    public static final long TOP_UP_AMOUNT_IS_NULL = 219101;
    // prepaid phone is null
    public static final long PREPAID_PHONE_IS_NULL = 219102;
    // mapping not exist
    public static final long MAPPING_NOT_EXIST = 219103;
    /*************************************** chgResSerCycle ************************/
    // service specId[{0}] of user[{1}] is invalid
    public static final long CHGRESSERCYCLE_SERVICE_SPECID_INVALID = 231500;
    // service cycle[{0}] of user[{1}] doesn't exist
    public static final long CHGRESSERCYCLE_SERVCYCLE_NOTEXIST = 231501;
    // service cycle[{0}] of user[{1}] is used
    public static final long CHGRESSERCYCLE_SERVCYCLE_USED = 231502;

    /***************************** setNotificationFlag ***************************/
    // an illegel oper_type
    public static final long NOTIFICATION_FLAG_OPERTYPE_ILLEGEL = 219001;
    // has not canceled this notification flag
    public static final long NOT_SETNOTIFICATION_FLAG = 219002;
    // has added this notification flag
    public static final long HAS_CANCEL_NOTIFLAG = 219003;

    /********************************** 事件触发reward *******************************************/
    // limit type of limitId[{0}] is not for birthday event
    public static final long EVENT_REWARD_LIMITTYPE_NOT_BIRTHDAY = 231600;
    // limit type of limitId[{0}] is not for onnet year event
    public static final long EVENT_REWARD_LIMITTYPE_NOT_ONNET_YEAR = 231601;

    // party for customer[{0}] not exist
    public static final long PARTY_FOR_CUSTOMER_NOT_EXIST = 231703;

    // handler for notify send not exist
    public static final long NOTIFY_SEND_HANDLER_NOT_EXIST = 231900;
    // handler for notify send invalid
    public static final long NOTIFY_SEND_HANDLER_INVALID = 231901;

    /*********************** advance payment allocation ***********/
    // not find original out so nbr[{0}] in ims_sonbr_mapping table
    public static final long ADVANCEPAYMENT_OLDSONBR_NOT_FIND = 219200;
    /*************** top up using cash **********/

    /*********************** QueryCDR(214100 ~ 214199) ***********/
    // query type can't be bull
    public static final long QUERYCRD_QUERY_TYPE_IS_NULL = 214100;

    /*********************** QueryFirstBillDays(232500 ~ 223599) ***********/
    // cycle_type and cycle_unit cann't be empty
    public static final long CYCLETYPE_CYCLEUNIT_EMPTY = 232500;

    /************************ sync product sts to crm ************************/
    // configuration of PM_RENT_DEDUCT_FAILURE is wrong:actType is null
    public static final long ACTTYPE_IS_NULL = 230701;
    // if will suspend commom product,product[{0}] must be commom product
    public static final long PRODUCT_IS_MAIN_PRODUCT = 230702;
    // if will suspend main product,product[{0}] must be main product
    public static final long PRODUCT_ID_COMMOM_PRODUCT = 230703;
    // if will change main product,product[{0}] must be main product
    public static final long PRODUCT_ID_COMMOM_PRODUCT_CHANGE = 230704;
    // configuration of PM_RENT_DEDUCT_FAILURE is wrong:tarOfferId is null
    public static final long TAR_OFFER_ID_IS_NULL = 230705;
    // configuration of PM_RENT_DEDUCT_FAILURE is wrong:target offering [{0}] is not a main product offering
    public static final long TAR_OFFER_ID_IS_NOT_MAIN_PRODUCT = 230706;
    // if will terminate common product,product[{0}] must be commom product
    public static final long PRODUCT_IS_MAIN_PRODUCT_CANNOT_TERMINATE = 230707;
    public static final long SYNC_PRODSTS_FAIL_RULE_WRONG = 230708;
    public static final long SYNC_PRODSTS_DEDUCT_RULE_NOT_EXIST = 230709;
    //delayCycle of PM_RENT_DEDUCT_FAILURE is wrong 
    public static final long DUCTFAILURE_DELAYCYCLE_WRONG = 230710;
    //[{0}]Have no config intPM_RENT_DEDUCT_ACTION
    public static final long DUCTFAILUREACTION_NULL = 230711;
    // can not find user by prodId[{0}]
    public static final long SYNC_PRODSTS_CANNOT_FIND_USER_BY_PRODID = 230712;
    /******************************* 批量修改3h ***************************************/
    // modify acctInfo is null
    public static final long MODIFY_ACCT_INFO_IS_NULL = 232704;
    // the system is busy.
    public static final long SYSTEM_IS_BUSY_NOW = 232707;
    // the new condition must be sent
    public static final long MODIFY_USER_INFO_IS_NOT_SENT = 232708;
    // the needed modified content is to larger
    public static final long MODIFY_INFO_IS_TOO_LARGER = 232709;
    // the data is modifying
    public static final long MODIFY_INFO_IS_MODIFYING = 232710;
    // the data has been modified
    public static final long MODIFY_USER_INFO_IS_MODIFIED = 232711;

    /**************************** Modify Account State ******************************/
    // account State must be changed from terminate to active or suspend to active
    public static final long NEW_STATE_ERROR = 214300;
    // this Account is active now
    public static final long ACCOUNT_IS_ACTIVE = 214301;
    // this status[{0}] of account already exists
    public static final long ACCOUNT_STATUS_ALREADY_EXISTS = 214302;

    // sim is null
    public static final long SIM_IS_NULL = 219400;
    // can not operate multi sim for this prod_id:[{0}]
    public static final long CAN_NOT_OPERATE_MULTI_SIM_FOR_THIS_PROD_ID = 219401;
    // sim is exist :[{0}]
    public static final long IMSI_IS_EXIST = 219402;
    // can not find co_prod_inv_obj by product id:[{0}]
    public static final long CO_PROD_INV_OBJ_IS_NULL = 219403;
    // if terminate account ,cannot contain users
    public static final long account_have_user = 219404;
    /**************************** Change master number ******************************/
    // the phone_id's account[{0}] is not single balance
    public static final long ACCOUNT_IS_NOT_SINGLE_BALANCE = 219500;
    // ths new_master_number[{0}]'s account and the old_master_number[{1}]'s account are accordant
    public static final long ACCOUNTS_NOT_ACCORDANT = 219501;
    // old number[{0}] is not master number
    public static final long ACCOUNTS_NOT_MUSTER_NUM = 219502;

    /**************************** Manage single balance ******************************/
    // when oper_type=0 then dummy_acct_id can not be null
    public static final long OPER_ADD_DUMMY_ACCT_ID_IS_NULL = 219600;
    // the phoneId[{0}]'s account is not a single balance
    public static final long ACCOUNT_NOT_SINGLE_BALANCE = 219601;
    // the phoneId[{0}]'s single balance is not existence
    public static final long SINGLE_BALANCE_NOT_EXIST = 219602;
    // the phoneId[{0}]'s not the master number of the account[{1}]
    public static final long PHONE_IS_NOT_MASTER_NUMBER = 219603;
    // the member of the account more than one
    public static final long ACCOUNT_HAVA_ANOTHER_MEMBER = 219604;
    // the balance of the account[{0}] is not enough
    public static final long ACCOUNT_BALANCE_NOT_ENOUGH = 219605;
    // the phoneId[{0}]'s account is already be single balance
    public static final long ACCOUNT_IS_SINGLE_BALANCE = 219606;
    // the balance of the user[{0}] is not enough
    public static final long USER_BALANCE_NOT_ENOUGH = 219607;
    // only the prepaid user can not join single balance
    public static final long NOT_PREPAID_CAN_NOT_JOIN_SINGLE_BALANCE = 219608;
    // only the prepaid user can create single balance
    public static final long NOT_PREPAID_CAN_NOT_CREATE_SINGLE_BALANCE = 219609;

    /**************************** Manage single balance Member ******************************/
    // the account[{0}] dosn't have parent account
    public static final long DUMMY_ACCOUNT_NOT_HAVE_PARENT = 221000;

    // sUserMonitorOper List is empty
    public static final long USER_MONITOR_LIST_EMPTY = 220000;
    // oper_type of SUserMonitorOper must in (0,1,2)
    public static final long USER_MONITO_OPER_TYPE_ERROR = 220001;
    // service id [{0}] of user [{1}] is exist
    public static final long USER_MONITOR_EXIST = 220003;
    // user id and service id can't empty
    public static final long USER_MONITOR_USERID_SERVICEID_EMPTY = 220002;

    /**************************** Batch Modify product State ******************************/
    // productStateList is empty
    public static final long PROD_STATE_LIST = 219700;
    // productId is empty
    public static final long PROD_ID = 219701;
    // coProdInvObj is empty
    public static final long CO_PORD_INV_OBJ_NULL = 219702;
    // coProdInvObj is not right
    public static final long CO_PORD_INV_OBJ_NOT_RIGHT = 219703;

    // cmResAuth not exist for user_id[{0}]
    public static final long CMRESAUTH_NOT_EXIST = 1043001;
    /******************************** query cap max ***************************************/
    // accumulate not config threshold
    public static final long NOT_CONFIG_THRESHOLD = 219800;
    // accumulate not config action
    public static final long NOT_CONFIG_ACTION = 219801;
    // accumulate not config service
    public static final long NOT_CONFIG_SERVICE = 219802;
    // accumulate not config service sts
    public static final long NOT_CONFIG_SERVICE_STS = 219803;

    /**
     * 管理集团和客户经理关系错误码
     */
    // oper_type of custManagerRelReq must in (0,1,2)
    public static final long MNGT_CUST_MANAGER_REL_OPER_TYPE_ERROR = 219900;
    // check type must in[{0}]
    public static final long CHECK_VICE_NUM_CHECK_TYPE = 219901;
    /**
     * 免催免停表为空
     */
    // operator_id is null
    public static final long OPERATOR_ID_IS_NULL = 219903;
    // secoperator is null
    public static final long SECOPERATOR_IS_NULL = 219904;
    // custmanagerrelinfo'cust_id is null
    public static final long CUST_MANAGER_REL_CUST_ID = 219905;
    // custmanageroperlist is null
    public static final long CUST_MANAGER_REL_OPER_LIST_IS_EMPTY = 219907;
    // custmanagerrelinfo'cust_manager_id is null
    public static final long CUST_MANAGER_REL_CUST_MANAGER_ID = 219908;
    // custmanagerrel customer[{0}] and manager[{1}]is exist
    public static final long CUST_MANAGER_REL_IS_EXIST = 219909;
    // custmanagerrel customer[{0}] and manager[{1}] is not exist
    public static final long CUST_NANAGER_REL_IS_NOT_EXIST = 219910;
    // the cust_id and group_id rel isnot right
    public static final long CUST_ID_AND_GROUOP_ID_REL_IS_NOT_RIGHT = 219912;
    // customer[{0}] is not group customer
    public static final long CUST_ID_IS_NOT_GROUP_CUSTOMER = 219913;
    // party[{0}] is null
    public static final long PARTY_IS_NULL = 219914;
    // cmOrgName or CmOrganization is not legal
    public static final long ORG_NAME_OR_ORGNIZATION_IS_NOT_LEGAL = 219915;
    /**
     * 高危系统需求
     */
    // sUserMonitorReqList is null
    public static final long USER_MONITOR_REQ_LIST_EMPTY = 219906;
    /**
     * 二次扣费提醒需求
     */
    // sCmUserOrderConfirmList is null
    public static final long USER_ORDER_CONFIRM_REQ_LIST_EMPTY = 219911;
    
    /**
     * 查询最大免费资源 zhangzj3
     */
    //product[{0}] is not match accout[{1}]
    public static final long ACCOUNT_AND_PRODUCT_NOT_MATCH = 220101;
    //product[{0}]is not account product
    public static final long PRODUCT_IS_NOT_ACCOUNT_TYPE = 220102;
    //account[{0}]is not order product
    public static final long ACCOUNT_IS_NOT_ORDER_PRODUCT = 220103;
    /**
     * 上海接口封装的错误码定义(7004-7022)
     */
    // can not find account for phone[{0}]
    public static final long PHONE_DOES_NOT_EXIST = 220900;
    // the paramter [{0}] must be in[{1}]
    public static final long PARAMTER_MUST_BE_IN = 220901;
    // can not find cust id by group id[{0}]
    public static final long CAN_NOT_FIND_CUST_ID_BY_GROUP_ID = 220902;
    
    //CRM service error code[{0}] error message[{1}]
    public static final long CRM_SERVICE_ERROR_CODE=235001;
    
    //can not find boss so_nbr by out_so_nbr[{0}] and out_so_date[{1}]
    public static final long CAN_NOT_FIND_BOSS_SO_NBR = 235002;
    
    //can not find sh_crm_http_json_server_addr in sys_parameter 
    public static final long  CAN_NOT_FIND_CRM_HTTP_JSON_ADDR= 235003;
    
    //monthType of qryPaymentHisByMonthIn must in (1,2,3)
    public static final long MONTH_TYPE_INPUT_ERROR=235004;
    //{0} of {1} must in ({2})
    public static final long PARAM_VALUE_MUST_IN_RANGE=235005;
    //acct_id[{0}] is already in table[cd.IMS_FUND_DEPOSIT_AGENT] 
    public static final long RECORD_IS_ALREADY_EXIST=235006;
    //acct_id[{0}] is not in table[cd.IMS_FUND_DEPOSIT_AGENT]
    public static final long RECORD_IS_NOT_ALREADY_EXIST=235007;
    //billing plan is not right
    public static final long CA_BILLING_PLAN_NOT_RIGHT=235008;
    //find cycle run information fail
    public static final long FIND_CYCLE_RUN_INFORMATION_FAIL=235009;
    //can not find the two billcycle before current billcycle
    public static final long THE_TWO_BILL_MONTH_BEFORE_CUR=235010;
    //password[{0}]of acct_id[{1}] is not right
    public static final long PASSWORD_IS_NOT_RIGHT=235011;
    //effectFlag of user is not[{0}]
    public static final long EFFECT_FLAG_OF_USER_IS_NOT_RIGHT=235012;
    //CmResValidity is null
    public static final long CM_RES_VALIDITY_IS_NULL=235013;
    //Customer Id or Account Id or Resource Id or Phone Id not in Route Table
    public static final long ROUTE_ERROR_CODE_DEF = 236000;
    //so_mode[{0}] and out_so_nbr[{1}] is duplicate
    public static final long SO_MODE_AND_OUT_SO_NBR_EXIST = 235014;
    //out_so_date of database is not equals out_so_date[{0}]
    public static final long CAN_NOT_FIND_OUT_SO_DATE=235015;
    //CBOSS service error code[{0}] error message[{1}]
    public static final long CBOSS_SERVICE_ERROR_CODE=235016;
    //invoke CBOSS method error[{0}]
    public static final long INVOKE_CBOSS_METHOD_ERROR=235017;
    //parsedata error!!!
    public static final long PARSE_DATA_ERROR=235018;
    //invoke universe plat form method error[{0}]
    public static final long INVOKE_UNIVERSE_PLAT_METHOD_CODE=235019;
    //get_universe_url_error_by_wsdl[{0}]
    public static final long GET_UNIVERSE_URL_BY_WSDL_ERROR=235020;
    //call interface time out[{0}]
    public static final long CALL_INTERFACE_TIME_OUT = 235021;
    // not found acct_id by user_id [{0}]  in  router
    public static final long NOT_FOUND_ACCT_ID_BY_USER_ID_ROUTER = 236001;
    // not found acct_id by phone_id [{0}]  in  router
    public static final long NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER = 236002;
    

    //this product [{0}] is not valid
    public static final long THE_PRODUCT_NOT_VALID = 236003;
    
    //SHBOSS  REPAIRING
    public static final long SHBOSS_REPAIR = 250000;
    //SHBOSS ABM REPAIRING
    public static final long SHBOSS_ABM_REPAIR = 250001;
    //in_net_work_days of subscriber is less 120
    public static final long IN_NET_WORK_DAYS_IS_LESS_120=236004;
    //status of subscriber is not active 
    public static final long USER_STATUT_EXCEPTION=236005;
    //subscriber is not real_name
    public static final long USER_IS_NOT_REAL_NAME=236006;
    //customer[{0}] is not match accout[{1}]
    public static final long CUSTOMER_AND_ACCOUNT_NOT_MATCH = 236007;
    //in_net_work_days of subscriber is less [{1}]
    public static final long IN_NET_WORK_DAYS_IS_LESS=236008;
    //resource's brand is {0}, {1}. 
    public static final long RESOURCE_IS_NOT_3BRAND=236009;
    //resource is ativate at {0},less than 1 year.
    public static final long IN_NET_WORK_DAYS_IS_LESS_365=236010;
    //订单[{0}]重复！
    public static final long ZDZ_OUT_SO_NBR_EXITS=37;
    //帐户不存在
    public static final long ZDZ_ACCT_NOT_EXITS=2;
    //到账超时[{0}]
    public static final long ZDZ_INTERFACE_TIME_OUT = 61;
    
    //从帐管copy
    public static final long BILL_BILLCYCLE_BILLING_PERIOD_INVALID = 246708;
    
    //billing cycle invalid
    public static final long BILL_BILLCYCLE_BILLING_CYCLE_INVALID = 246707;
    
    //billing cycle unit invalid
    public static final long BILL_BILLCYCLE_CYCLE_UNIT_INVALID = 246712;
    
    //billing start bill day invalid
    public static final long BILL_BILLCYCLE_STRAT_BILL_DAY_INVALID = 246713;
    
    // billing cycle type invalid
    public static final long BILL_BILLCYCLE_CYCLE_TYPE_INVALID = 246714;
    
    //public static final long CREATE_SHORT_CA_BILLING_PLAN_FAIL = 146032;
    //public static final long CREATE_LONG_CA_BILLING_PLAN_FAIL = 146034;
    //public static final long CA_BILLING_PLAN_NOT_RIGHT = 146027;
    //public static final long CA_BILLING_PERIOD_NOT_RIGHT = 146026;
    //public static final long FIND_CYCLE_RUN_INFORMATION_FAIL = "146033";;
    
    //billing cycle spec not right
    public static final long CA_BILLING_CYCLE_SPEC_NOT_RIGHT = 246028;
    
    //notify id not correct
    public static final long  COMMON_NOTIFYID_NOT_CORRECT = 241023;
    
    //db operating errors
    public static final long  COMMON_DB_OPERATING_ERRORS = 240010;
    
    // account not exists
    public static final long COMMON_ACCOUNT_NOT_EXISTS = 240001;
    
    //cycle spec id not right
    public static final long CYCLE_SPEC_ID_NOT_RIGHT = 246020;
    
    //period type not right
    public static final long PERIOD_TYPE_NOT_RIGHT = 246021;
    
    //period unit not right
    public static final long PERIOD_UNIT_NOT_RIGHT = 246022;
    
    //resource not correct
    public static final long COMMON_RESOURCEID_NOT_CORRECT = 240014;
    
    //start bill day not right
    public static final long START_BILL_DAY_NOT_RIGHT = 246024;
    
    //op type not right
    public static final long OP_TYPE_NOT_RIGHT = 246023;
    
    //first bill type not right
    public static final long FIRST_BILL_TYPE_NOT_RIGHT = 246025;
    
  //bill cycle checkk param error
    public static final long  BILL_BILLCYCLE_CHECK_PARAM_ERROR = 246700;
    
    
    
    /*********************产品管理错误码*******************************/
    public static final long RESCOUNT_ISNEGATIVE= 298001;
    public static final long CALCULATE_WISDOM_FEE_ERR=298000;
    public static final long NO_RENT_FEE_FOUND=298002;
    
}