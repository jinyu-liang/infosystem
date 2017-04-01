package com.ailk.ims.abmtransfer.commom;

import com.ailk.MessageTemplate;
/**
 * @Description: SDL错误代码 
 * 
 *               <pre>
 *               <dl>
 *               <tt>日期			操作者	操作		修改记录</dt>
 *               <dd>2011-07-16 	zhouqh 	添加		130001-130999</dd>
 *               </dl>
 * </pre>
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zhouqh
 * @Date 2011-7-16
 * 
 */
public abstract class ErrorCode {
    protected ErrorCode() {
    }
    
    @MessageTemplate(text = "Invalid account due charge.")
    public static final String CA_ACCOUNT_DEBT_NOT_RIGHT                            = "146029";
    
    @MessageTemplate(text = "Fail to find bill run information.")
    public static final String FIND_CYCLE_RUN_INFORMATION_FAIL                      = "146033";
    
    @MessageTemplate(text = "Can't find billing plan.")
    public static final String BILL_BILLCYCLE_BILLING_PLAN_IS_NULL                  = "146704";
    
    @MessageTemplate(text = "Invalid valid or expire date.")
    public static final String PLAN_VALID_EXPIRE_DATE_NOT_EXISTS                    = "146002";

    @MessageTemplate(text = "Invalid billing cycle specification ID.")
    public static final String CYCLE_SPEC_ID_NOT_RIGHT                              = "146020";

    @MessageTemplate(text = "Invalid period type.")
    public static final String PERIOD_TYPE_NOT_RIGHT                                = "146021";

    @MessageTemplate(text = "Invalid period unit.")
    public static final String PERIOD_UNIT_NOT_RIGHT                                = "146022";

    @MessageTemplate(text = "Invalid operation type.")
    public static final String OP_TYPE_NOT_RIGHT                                    = "146023";

    @MessageTemplate(text = "Invalid bill day or SO_out_date is null.")
    public static final String START_BILL_DAY_NOT_RIGHT                             = "146024";

    @MessageTemplate(text = "Invalid first bill type or is_delay.")
    public static final String FIRST_BILL_TYPE_NOT_RIGHT                            = "146025";

    @MessageTemplate(text = "Invalid billing period.")
    public static final String CA_BILLING_PERIOD_NOT_RIGHT                          = "146026";

    @MessageTemplate(text = "Invalid billing plan.")
    public static final String CA_BILLING_PLAN_NOT_RIGHT                            = "146027";

    @MessageTemplate(text = "Invalid billing cycle specification.")
    public static final String CA_BILLING_CYCLE_SPEC_NOT_RIGHT                      = "146028";

    @MessageTemplate(text = "Fail to create bill run information.")
    public static final String CREATE_CA_ACCOUNT_CYCLE_RUN_FAIL                     = "146030";

    @MessageTemplate(text = "Fail to create billing plan for short cycle.")
    public static final String CREATE_SHORT_CA_BILLING_PLAN_FAIL                    = "146032";

    @MessageTemplate(text = "Fail to create billing plan for long cycle.")
    public static final String CREATE_LONG_CA_BILLING_PLAN_FAIL                     = "146034";

    @MessageTemplate(text = "Fail to query bill cycle.")
    public static final String QUERY_BILL_CYCLE_FAIL                                = "146042";
    
    @MessageTemplate(text = "The account id is invalid, value:[{0}]")
    public static final String COMMON_ACCT_ID_INVALID                   = "130001";
    
    @MessageTemplate(text = "The resource id is invalid, value:[{0}]")
    public static final String COMMON_RESOURCE_ID_INVALID               = "130002";
    
    @MessageTemplate(text = "The original accout id is invalid, value:[{0}]")
    public static final String COMMON_ORG_ACCT_ID_INVALID               = "130003";
    
    @MessageTemplate(text = "The owner type is invalid, value:[{0}]")
    public static final String COMMON_OWNER_TYPE_INVALID                = "130004";
    
    @MessageTemplate(text = "The owner id is invalid, value:[{0}]")
    public static final String COMMON_OWNER_ID_INVALID                  = "130005";
    
    @MessageTemplate(text = "query mdbRoute fail.")
    public static final String QUERY_MDBROUTE_FAIL                      = "130006";
    
    @MessageTemplate(text = "no assets need to deal.")
    public static final String NO_ASSETS_TO_DEAL                      	= "130007";
    
    @MessageTemplate(text = "inList's size not equal outList's size.")
    public static final String INSIZE_NOT_EQUAL_OUTSIZE                	= "130008";
    
    @MessageTemplate(text = "route map not contains the mdbKey[{0}].")
    public static final String ROUTE_MAP_NOT_MATCH                      = "130009";
    
    @MessageTemplate(text = "both acctId and ownerId invalid.")
    public static final String ACCTID_OWNERID_INVALID                	= "130010";
    
    @MessageTemplate(text = "there ara two or Greater than two MDB to deal with freeRes or otFreeRes.")
    public static final String MORE_THAN_ONE_MDB                		= "130011";
    
    @MessageTemplate(text = "Invalid input resource ID.")
    public static final String COMMON_RESOURCEID_NOT_CORRECT                        = "140014";
    
    @MessageTemplate(text = "The account does not exist.")
    public static final String COMMON_ACCOUNT_NOT_EXISTS                            = "140001";
    
    @MessageTemplate(text = "Invalid input start or end date.")
    public static final String COMMON_SDATE_OR_EDATE_NOT_CORRECT                    = "141002";
    
    @MessageTemplate(text = "Cannot locate customer information as per customer ID.")
    public static final String COMMON_CUSTOMER_NOT_INFORMATION                      = "140020";
    
    @MessageTemplate(text = "The operator does not exist.")
    public static final String COMMON_OPERATOR_NOT_EXISTS                           = "140004";
    
    @MessageTemplate(text = "Input area does not exist.")
    public static final String COMMON_AREA_NOT_CORRECT                              = "141004";
    
    @MessageTemplate(text = "None out SO number passed in.")
    public static final String COMMON_NO_OUT_SONBR_PASSED                           = "140024";
    
    @MessageTemplate(text = "Database error occurs.")
    public static final String COMMON_DB_OPERATING_ERRORS                           = "140010";
    
    @MessageTemplate(text = "Check parameter error")
    public static final String BILL_BILLCYCLE_CHECK_PARAM_ERROR                     = "146700";
    
    @MessageTemplate(text = "Can't find the account.")
    public static final String BILL_BILLCYCLE_ACCOUNT_IS_NULL                       = "146705";
    
    @MessageTemplate(text = "Invalid billing cycle specification.")
    public static final String BILL_BILLCYCLE_BILLING_CYCLE_INVALID                 = "146707";

    @MessageTemplate(text = "Invalid billing period definition.")
    public static final String BILL_BILLCYCLE_BILLING_PERIOD_INVALID                = "146708";
    
    @MessageTemplate(text = "Start bill day required.")
    public static final String BILL_BILLCYCLE_STRAT_BILL_DAY_INVALID                = "146713";

    @MessageTemplate(text = "Cycle unit required.")
    public static final String BILL_BILLCYCLE_CYCLE_UNIT_INVALID                    = "146712";

    @MessageTemplate(text = "Cycle type required.")
    public static final String BILL_BILLCYCLE_CYCLE_TYPE_INVALID                    = "146714";
    
    /*******************************信管资产迁移专用错误错误编号*******************************/
    @MessageTemplate(text = "Invalid account ID.")
    public static final String INVALID_ACCOUNT_ID                		= "130012";
    
    @MessageTemplate(text = "Invalid original account ID.")
    public static final String INVALID_ORIG_ACCOUNT_ID               	= "130013";
    
    @MessageTemplate(text = "Invalid resource ID.")
    public static final String INVALID_RESOURCE_ID                		= "130014";
    
    @MessageTemplate(text = "the route acctId of servId is not equal input acctId, transferMdbAsset return fail.")
    public static final String ROUTE_ACCTID_WRONG                		= "130015";
    
    @MessageTemplate(text = "the route version of servId is equal zero, so transferMdbAsset can't continue.")
    public static final String ROUTE_VERSION_EMPTY                      = "130016";  
    @MessageTemplate(text = "transferMdbAsset fail.")
    public static final String TRANSFER_MDB_ASSET_ERR                   = "130017";
    /*******************************信管资产迁移专用错误错误编号*******************************/
    
    @MessageTemplate(text = "The resource id or accout id invalid")
    public static final String COMMON_RESOURCE_ACCT_ID_INVALID          = "130018";
    
    @MessageTemplate(text = "query real bill query type error")
    public static final String REAL_BILL_QUERY_TYPE_ERROR               = "130019";
    
    @MessageTemplate(text = "mdb service return value hava one null ")
    public static final String MDB_SERVICE_RETURN_ERROR               	= "130020";//mdb服务端获取信息为空

}
