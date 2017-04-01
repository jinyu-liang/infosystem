package com.ailk.imssh.common.define;

/**
 * @Description：上海需要的常量(包括和原来不同的和原来没有的)
 * @author wangjt
 * @Date 2012-5-22
 */
public class ConstantExDefine {

	public static final String MDB_CLOUD_FLAG = "MDB_CLOUD_FLAG";
	public static final String MDB_SYNC_MANUAL = "MDB_SYNC_MANUAL";
	public static final String ROUTER_KEY_SUB_MOD = "SUB_MOD";
	public static final String CHANGE_PROD_OPERATE_PRODIDS = "all_product_ids";

	// 路由分表参数（用于ContextHolder中）的key定义[原则：session中都是大写,java对象取字段名]
	public static final String ROUTER_KEY_ACCT = "ACCT_ID";
	public static final String ROUTER_KEY_USER = "RESOURCE_ID";
	public static final String ROUTER_KEY_CUST = "CUST_ID";
	public static final String ROUTER_KEY_IDEN = "IDENTITY";
	public static final String ROUTER_KEY_COMMIT_DATE = "COMMIT_DATE";
	public static final String ROUTER_KEY_REGION_CODE = "REGION_CODE";
	public static final String ROUTER_KEY_GROUP_ID = "GROUP_ID";
	public static final String ROUTER_KEY_USER_ID = "USER_ID";
	public static final String ROUTER_KEY_BILL_MONTH = "BILL_MONTH";
	public static final String ROUTE_KEY_OBJECT_TYPE = "OBJECT_TYPE";
	public static final String ROUTE_KEY_DB_KEY = "DB_KEY";
	
	public static final String CHANGE_ACCT_MAP = "CHANGE_ACCT_MAP";//过户信息的map
	public static final String CHANGE_ACCT_FLAG = "CHANGE_ACCT_FLAG";// 过户标识
	public static final String ROUTER_MOVE_DATA_FLAG = "ROUTER_MOVE_DATA_FLAG";// 过户时是否迁移当前数据到新库新表的标识
	public static final String ROUTER_MOVE_USERID = "ROUTER_MOVE_USERID";// 过户时操作的用户编号
	public static final String ROUTER_MOVE_OLD_ACCTID = "ROUTER_MOVE_OLD_ACCTID";// 过户时老账户编号
	public static final String ROUTER_MOVE_NEW_ACCTID = "ROUTER_MOVE_NEW_ACCTID";// 过户时新账户编号
	public static final String CHANGE_ACCT_DATE = "CHANGE_ACCT_DATE";
	public static final String CHANGE_ACCT_EXPIRE_DATE = "CHANGE_ACCT_EXPIRE_DATE";
	public static final String CHANGE_ACCT_UPDATE_DATE = "CHANGE_ACCT_UPDATE_DATE";
	
	public static final int CHANGE_ACCT_TRADE_SUCC = 0;
	public static final int CHANGE_ACCT_TRADE_FAIL = 2;
	public static final int CHANGE_ACCT_DTRADE_DB_SUCC = 10;
	public static final int CHANGE_ACCT_DTRADE_DB_FAIL = 11;
	public static final int CHANGE_ACCT_DTRADE_MDB_SUCC = 20;
	public static final int CHANGE_ACCT_DTRADE_MDB_FAIL = 21;
	
	public static final String ROUTER_SERVICE_BEAN = "routerClient";
	public static final String CREDIT_SERVICE_BEAN = "outInterface_creditService";
	public static final String POCKET_SERVICE_BEAN = "outInterface_depositPocketBusiService";
	public static final String USER_STS_POOL = "USER_STS_POOL";// 销户标识
	public static final String POOL_PHONE_ID = "POOL_PHONE_ID";// 销户时的用户对应的手机号码

	public static final String POOL_IMSI = "POOL_IMSI";

	public static final String PCC_VALUE_KEY = "policycounterstatus";
	public static final String PCC_ORDER_FLAG = "orderflag";

	public static final String USER_MDB_VERTIVAL_VALUE = "user_info";


	public static final String XPATH = "/root/pipe_lines/pipe_line/nodes/node/functions/function/user_config";

	public static final int SQL_IN_MAX_NUMBER = 1000;

	public static final int HOME_PAY_RULE_ID = 99;

	public static final String ENTITY_FIELD_COUNTY_CODE = "countyCode";// 实体中字段county_code的名称

	public static final String IM_GX_HOME_PAY_RULE_ID = "IM_GX_HOME_PAY_RULE_ID";
	
	public static final String GX_MANUAL_MODIFY_PROD = "GX_MANUAL_MODIFY_PROD";
	
	public static final long MAIN_PROD_PREFIX = 100000000000000L;
	
	public static final long PROD_PREFIX = 100000000L;
	
	public static final int IMMEDIALETE_RC_CHARGE = 820005;
	
	public static final String CHANGE_REGION_IMMEDIATE = "CHANGE_REGION_IMMEDIATE";
	
	public static final int MDB_SYNC_INFO_ALL = 1;
	public static final int MDB_SYNC_INFO_OWN = 2;
	public static final int MDB_SYNC_INFO_IVR = 3;
	
	public static String USER_ACCT_SPLIT = ",";

}
