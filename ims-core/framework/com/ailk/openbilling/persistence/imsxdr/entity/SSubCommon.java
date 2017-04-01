package com.ailk.openbilling.persistence.imsxdr.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.ailk.easyframe.web.common.annotation.Sdl;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.easyframe.sdl.sdlbuffer.MemberTypeInfo;
import javax.xml.bind.annotation.XmlTransient;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"notifyType","processType","treatFlag","uFlag","uCount","xdrOutPostfix","rsu","priorityLevel","qosClassId","paymentType","paymentMode","productValue","queryAccountType","lacId","oppLacId","cellId","oppCellId","sessionStartTime","startTime","visitStartTime","updateTime","timeSegment","serviceCode","chargeFlag","ccRequestNumber","offPeakInfo","timeZone","serviceKey","timeAddup","timeAddupSwitch","volumeAddup","volumeAddupSwitch","duration","octect","upVolume","upVolumeSwitch","downVolume","downVolumeSwitch","oppNumber","oriOppNumber","dialedNumber","userNature","oppNature","aNature","serviceType","messageType","businessCode","businessCodeGroup","contentCode","pointCost","billFlag","smscId","deductionFlag","recordType","stdErrCode","redoFlag","camelDestNum","camelServiceLevel","camelServiceKey","dftCallHandlingInd","tapCamelInvoFee","tapCamelVatAmt","cdrCamelFlag","ratingFlag","optimalChargeFlag","oriRatingFlag","freeChargeType","authType","rateStrategy","rateBudgetRes","blackListAlert","conditionId","productId","billIndicate","apartOppNumber","roamType","accessType","spRelaType","oppConditionId","cdrOnlineFlag","backupDate","cdrDealFlag","userProperty","oppProperty","spCode","groupType","netcallFlag","oppNumberType","oppCountryId","oppCountryGroup","userBaseInfo","rateProdId","oppRateMainProdId","rateProdBillCycle","rateProdRatingRes","sysRatingRes","rateProdQuotaRes","sysQuotaRes","ratingRes","rateProdZone","rateProdZoneAlertFlag","taxRule","accumulateRule","propertyValMin","rejectCallAlarmMap","accumulateAlarmList","afterMakeCallAlarmRule","beforeMakeCallAlarmRule","groupAcctPayment","groupFreebiePayment","capMax","groupBudget","groupRelationType","groupSpecShare","productExpireDate","productRedirectionDate","cellCode","oppCellCode","rateProdPricePlan","hybridRuleDef","serviceInfoMap","userGroup","maxTimePromSpecMap","processFlag","minimalValue","firstUpdateFlag","freebiePromotedRule","borderRoamInfoMap","borderRoamType","outRatingFec","inOutFec","outInFec","ratingOut","errorCode","errorMessage","errFile","errModifyFlag","errInputtime","errFileDate","reserve1","reserve2","reserve3","reserve4","reserveFields","billDate","revenueCodeDtl","billingOut","operatorRelaType","batchMessageNumber","hybridRule","slaRule","oriDeductTime","resCount","deductResCount","refundResCount","rgRule","analyseAlarm","tableName","databaseName","lateFlag","isFilter","fcitype","testCdrFlag","rgRuleList","historyErrorMap","offpeakSwitchDate","productValidDate","analyseStateInfo","nearestInfoChangeMap","outputFlag","chargeRollbackFlag","oppUserNumber","callRefnum","partialId","firstcdrStartTime","timeWindow","ocsMonitorCdr","serviceKeyVal","cdrQueryFlag","shareGruopIdForFreebie","newCdrFlag","batchNo"})
@Sdl(module="MXdr")
public class SSubCommon extends CsdlStructObject implements IComplexEntity{

	public final static String COL_NOTIFY_TYPE = "NOTIFY_TYPE";
	public final static String COL_PROCESS_TYPE = "PROCESS_TYPE";
	public final static String COL_TREAT_FLAG = "TREAT_FLAG";
	public final static String COL_U_FLAG = "U_FLAG";
	public final static String COL_U_COUNT = "U_COUNT";
	public final static String COL_XDR_OUT_POSTFIX = "XDR_OUT_POSTFIX";
	public final static String COL_RSU = "RSU";
	public final static String COL_PRIORITY_LEVEL = "PRIORITY_LEVEL";
	public final static String COL_QOS_CLASS_ID = "QOS_CLASS_ID";
	public final static String COL_PAYMENT_TYPE = "PAYMENT_TYPE";
	public final static String COL_PAYMENT_MODE = "PAYMENT_MODE";
	public final static String COL_PRODUCT_VALUE = "PRODUCT_VALUE";
	public final static String COL_QUERY_ACCOUNT_TYPE = "QUERY_ACCOUNT_TYPE";
	public final static String COL_LAC_ID = "LAC_ID";
	public final static String COL_OPP_LAC_ID = "OPP_LAC_ID";
	public final static String COL_CELL_ID = "CELL_ID";
	public final static String COL_OPP_CELL_ID = "OPP_CELL_ID";
	public final static String COL_SESSION_START_TIME = "SESSION_START_TIME";
	public final static String COL_START_TIME = "START_TIME";
	public final static String COL_VISIT_START_TIME = "VISIT_START_TIME";
	public final static String COL_UPDATE_TIME = "UPDATE_TIME";
	public final static String COL_TIME_SEGMENT = "TIME_SEGMENT";
	public final static String COL_SERVICE_CODE = "SERVICE_CODE";
	public final static String COL_CHARGE_FLAG = "CHARGE_FLAG";
	public final static String COL_CC_REQUEST_NUMBER = "CC_REQUEST_NUMBER";
	public final static String COL_OFF_PEAK_INFO = "OFF_PEAK_INFO";
	public final static String COL_TIME_ZONE = "TIME_ZONE";
	public final static String COL_SERVICE_KEY = "SERVICE_KEY";
	public final static String COL_TIME_ADDUP = "TIME_ADDUP";
	public final static String COL_TIME_ADDUP_SWITCH = "TIME_ADDUP_SWITCH";
	public final static String COL_VOLUME_ADDUP = "VOLUME_ADDUP";
	public final static String COL_VOLUME_ADDUP_SWITCH = "VOLUME_ADDUP_SWITCH";
	public final static String COL_DURATION = "DURATION";
	public final static String COL_OCTECT = "OCTECT";
	public final static String COL_UP_VOLUME = "UP_VOLUME";
	public final static String COL_UP_VOLUME_SWITCH = "UP_VOLUME_SWITCH";
	public final static String COL_DOWN_VOLUME = "DOWN_VOLUME";
	public final static String COL_DOWN_VOLUME_SWITCH = "DOWN_VOLUME_SWITCH";
	public final static String COL_OPP_NUMBER = "OPP_NUMBER";
	public final static String COL_ORI_OPP_NUMBER = "ORI_OPP_NUMBER";
	public final static String COL_DIALED_NUMBER = "DIALED_NUMBER";
	public final static String COL_USER_NATURE = "USER_NATURE";
	public final static String COL_OPP_NATURE = "OPP_NATURE";
	public final static String COL_A_NATURE = "A_NATURE";
	public final static String COL_SERVICE_TYPE = "SERVICE_TYPE";
	public final static String COL_MESSAGE_TYPE = "MESSAGE_TYPE";
	public final static String COL_BUSINESS_CODE = "BUSINESS_CODE";
	public final static String COL_BUSINESS_CODE_GROUP = "BUSINESS_CODE_GROUP";
	public final static String COL_CONTENT_CODE = "CONTENT_CODE";
	public final static String COL_POINT_COST = "POINT_COST";
	public final static String COL_BILL_FLAG = "BILL_FLAG";
	public final static String COL_SMSC_ID = "SMSC_ID";
	public final static String COL_DEDUCTION_FLAG = "DEDUCTION_FLAG";
	public final static String COL_RECORD_TYPE = "RECORD_TYPE";
	public final static String COL_STD_ERR_CODE = "STD_ERR_CODE";
	public final static String COL_REDO_FLAG = "REDO_FLAG";
	public final static String COL_CAMEL_DEST_NUM = "CAMEL_DEST_NUM";
	public final static String COL_CAMEL_SERVICE_LEVEL = "CAMEL_SERVICE_LEVEL";
	public final static String COL_CAMEL_SERVICE_KEY = "CAMEL_SERVICE_KEY";
	public final static String COL_DFT_CALL_HANDLING_IND = "DFT_CALL_HANDLING_IND";
	public final static String COL_TAP_CAMEL_INVO_FEE = "TAP_CAMEL_INVO_FEE";
	public final static String COL_TAP_CAMEL_VAT_AMT = "TAP_CAMEL_VAT_AMT";
	public final static String COL_CDR_CAMEL_FLAG = "CDR_CAMEL_FLAG";
	public final static String COL_RATING_FLAG = "RATING_FLAG";
	public final static String COL_OPTIMAL_CHARGE_FLAG = "OPTIMAL_CHARGE_FLAG";
	public final static String COL_ORI_RATING_FLAG = "ORI_RATING_FLAG";
	public final static String COL_FREE_CHARGE_TYPE = "FREE_CHARGE_TYPE";
	public final static String COL_AUTH_TYPE = "AUTH_TYPE";
	public final static String COL_RATE_STRATEGY = "RATE_STRATEGY";
	public final static String COL_RATE_BUDGET_RES = "RATE_BUDGET_RES";
	public final static String COL_BLACK_LIST_ALERT = "BLACK_LIST_ALERT";
	public final static String COL_CONDITION_ID = "CONDITION_ID";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_BILL_INDICATE = "BILL_INDICATE";
	public final static String COL_APART_OPP_NUMBER = "APART_OPP_NUMBER";
	public final static String COL_ROAM_TYPE = "ROAM_TYPE";
	public final static String COL_ACCESS_TYPE = "ACCESS_TYPE";
	public final static String COL_SP_RELA_TYPE = "SP_RELA_TYPE";
	public final static String COL_OPP_CONDITION_ID = "OPP_CONDITION_ID";
	public final static String COL_CDR_ONLINE_FLAG = "CDR_ONLINE_FLAG";
	public final static String COL_BACKUP_DATE = "BACKUP_DATE";
	public final static String COL_CDR_DEAL_FLAG = "CDR_DEAL_FLAG";
	public final static String COL_USER_PROPERTY = "USER_PROPERTY";
	public final static String COL_OPP_PROPERTY = "OPP_PROPERTY";
	public final static String COL_SP_CODE = "SP_CODE";
	public final static String COL_GROUP_TYPE = "GROUP_TYPE";
	public final static String COL_NETCALL_FLAG = "NETCALL_FLAG";
	public final static String COL_OPP_NUMBER_TYPE = "OPP_NUMBER_TYPE";
	public final static String COL_OPP_COUNTRY_ID = "OPP_COUNTRY_ID";
	public final static String COL_OPP_COUNTRY_GROUP = "OPP_COUNTRY_GROUP";
	public final static String COL_USER_BASE_INFO = "USER_BASE_INFO";
	public final static String COL_RATE_PROD_ID = "RATE_PROD_ID";
	public final static String COL_OPP_RATE_MAIN_PROD_ID = "OPP_RATE_MAIN_PROD_ID";
	public final static String COL_RATE_PROD_BILL_CYCLE = "RATE_PROD_BILL_CYCLE";
	public final static String COL_RATE_PROD_RATING_RES = "RATE_PROD_RATING_RES";
	public final static String COL_SYS_RATING_RES = "SYS_RATING_RES";
	public final static String COL_RATE_PROD_QUOTA_RES = "RATE_PROD_QUOTA_RES";
	public final static String COL_SYS_QUOTA_RES = "SYS_QUOTA_RES";
	public final static String COL_RATING_RES = "RATING_RES";
	public final static String COL_RATE_PROD_ZONE = "RATE_PROD_ZONE";
	public final static String COL_RATE_PROD_ZONE_ALERT_FLAG = "RATE_PROD_ZONE_ALERT_FLAG";
	public final static String COL_TAX_RULE = "TAX_RULE";
	public final static String COL_ACCUMULATE_RULE = "ACCUMULATE_RULE";
	public final static String COL_PROPERTY_VAL_MIN = "PROPERTY_VAL_MIN";
	public final static String COL_REJECT_CALL_ALARM_MAP = "REJECT_CALL_ALARM_MAP";
	public final static String COL_ACCUMULATE_ALARM_LIST = "ACCUMULATE_ALARM_LIST";
	public final static String COL_AFTER_MAKE_CALL_ALARM_RULE = "AFTER_MAKE_CALL_ALARM_RULE";
	public final static String COL_BEFORE_MAKE_CALL_ALARM_RULE = "BEFORE_MAKE_CALL_ALARM_RULE";
	public final static String COL_GROUP_ACCT_PAYMENT = "GROUP_ACCT_PAYMENT";
	public final static String COL_GROUP_FREEBIE_PAYMENT = "GROUP_FREEBIE_PAYMENT";
	public final static String COL_CAP_MAX = "CAP_MAX";
	public final static String COL_GROUP_BUDGET = "GROUP_BUDGET";
	public final static String COL_GROUP_RELATION_TYPE = "GROUP_RELATION_TYPE";
	public final static String COL_GROUP_SPEC_SHARE = "GROUP_SPEC_SHARE";
	public final static String COL_PRODUCT_EXPIRE_DATE = "PRODUCT_EXPIRE_DATE";
	public final static String COL_PRODUCT_REDIRECTION_DATE = "PRODUCT_REDIRECTION_DATE";
	public final static String COL_CELL_CODE = "CELL_CODE";
	public final static String COL_OPP_CELL_CODE = "OPP_CELL_CODE";
	public final static String COL_RATE_PROD_PRICE_PLAN = "RATE_PROD_PRICE_PLAN";
	public final static String COL_HYBRID_RULE_DEF = "HYBRID_RULE_DEF";
	public final static String COL_SERVICE_INFO_MAP = "SERVICE_INFO_MAP";
	public final static String COL_USER_GROUP = "USER_GROUP";
	public final static String COL_MAX_TIME_PROM_SPEC_MAP = "MAX_TIME_PROM_SPEC_MAP";
	public final static String COL_PROCESS_FLAG = "PROCESS_FLAG";
	public final static String COL_MINIMAL_VALUE = "MINIMAL_VALUE";
	public final static String COL_FIRST_UPDATE_FLAG = "FIRST_UPDATE_FLAG";
	public final static String COL_FREEBIE_PROMOTED_RULE = "FREEBIE_PROMOTED_RULE";
	public final static String COL_BORDER_ROAM_INFO_MAP = "BORDER_ROAM_INFO_MAP";
	public final static String COL_BORDER_ROAM_TYPE = "BORDER_ROAM_TYPE";
	public final static String COL_OUT_RATING_FEC = "OUT_RATING_FEC";
	public final static String COL_IN_OUT_FEC = "IN_OUT_FEC";
	public final static String COL_OUT_IN_FEC = "OUT_IN_FEC";
	public final static String COL_RATING_OUT = "RATING_OUT";
	public final static String COL_ERROR_CODE = "ERROR_CODE";
	public final static String COL_ERROR_MESSAGE = "ERROR_MESSAGE";
	public final static String COL_ERR_FILE = "ERR_FILE";
	public final static String COL_ERR_MODIFY_FLAG = "ERR_MODIFY_FLAG";
	public final static String COL_ERR_INPUTTIME = "ERR_INPUTTIME";
	public final static String COL_ERR_FILE_DATE = "ERR_FILE_DATE";
	public final static String COL_RESERVE1 = "RESERVE1";
	public final static String COL_RESERVE2 = "RESERVE2";
	public final static String COL_RESERVE3 = "RESERVE3";
	public final static String COL_RESERVE4 = "RESERVE4";
	public final static String COL_RESERVE_FIELDS = "RESERVE_FIELDS";
	public final static String COL_BILL_DATE = "BILL_DATE";
	public final static String COL_REVENUE_CODE_DTL = "REVENUE_CODE_DTL";
	public final static String COL_BILLING_OUT = "BILLING_OUT";
	public final static String COL_OPERATOR_RELA_TYPE = "OPERATOR_RELA_TYPE";
	public final static String COL_BATCH_MESSAGE_NUMBER = "BATCH_MESSAGE_NUMBER";
	public final static String COL_HYBRID_RULE = "HYBRID_RULE";
	public final static String COL_SLA_RULE = "SLA_RULE";
	public final static String COL_ORI_DEDUCT_TIME = "ORI_DEDUCT_TIME";
	public final static String COL_RES_COUNT = "RES_COUNT";
	public final static String COL_DEDUCT_RES_COUNT = "DEDUCT_RES_COUNT";
	public final static String COL_REFUND_RES_COUNT = "REFUND_RES_COUNT";
	public final static String COL_RG_RULE = "RG_RULE";
	public final static String COL_ANALYSE_ALARM = "ANALYSE_ALARM";
	public final static String COL_TABLE_NAME = "TABLE_NAME";
	public final static String COL_DATABASE_NAME = "DATABASE_NAME";
	public final static String COL_LATE_FLAG = "LATE_FLAG";
	public final static String COL_IS_FILTER = "IS_FILTER";
	public final static String COL_FCITYPE = "FCITYPE";
	public final static String COL_TEST_CDR_FLAG = "TEST_CDR_FLAG";
	public final static String COL_RG_RULE_LIST = "RG_RULE_LIST";
	public final static String COL_HISTORY_ERROR_MAP = "HISTORY_ERROR_MAP";
	public final static String COL_OFFPEAK_SWITCH_DATE = "OFFPEAK_SWITCH_DATE";
	public final static String COL_PRODUCT_VALID_DATE = "PRODUCT_VALID_DATE";
	public final static String COL_ANALYSE_STATE_INFO = "ANALYSE_STATE_INFO";
	public final static String COL_NEAREST_INFO_CHANGE_MAP = "NEAREST_INFO_CHANGE_MAP";
	public final static String COL_OUTPUT_FLAG = "OUTPUT_FLAG";
	public final static String COL_CHARGE_ROLLBACK_FLAG = "CHARGE_ROLLBACK_FLAG";
	public final static String COL_OPP_USER_NUMBER = "OPP_USER_NUMBER";
	public final static String COL_CALL_REFNUM = "CALL_REFNUM";
	public final static String COL_PARTIAL_ID = "PARTIAL_ID";
	public final static String COL_FIRSTCDR_START_TIME = "FIRSTCDR_START_TIME";
	public final static String COL_TIME_WINDOW = "TIME_WINDOW";
	public final static String COL_OCS_MONITOR_CDR = "OCS_MONITOR_CDR";
	public final static String COL_SERVICE_KEY_VAL = "SERVICE_KEY_VAL";
	public final static String COL_CDR_QUERY_FLAG = "CDR_QUERY_FLAG";
	public final static String COL_SHARE_GRUOP_ID_FOR_FREEBIE = "SHARE_GRUOP_ID_FOR_FREEBIE";
	public final static String COL_NEW_CDR_FLAG = "NEW_CDR_FLAG";
	public final static String COL_BATCH_NO = "BATCH_NO";
	public final static int IDX_NOTIFY_TYPE = 0;
	public final static int IDX_PROCESS_TYPE = 1;
	public final static int IDX_TREAT_FLAG = 2;
	public final static int IDX_U_FLAG = 3;
	public final static int IDX_U_COUNT = 4;
	public final static int IDX_XDR_OUT_POSTFIX = 5;
	public final static int IDX_RSU = 6;
	public final static int IDX_PRIORITY_LEVEL = 7;
	public final static int IDX_QOS_CLASS_ID = 8;
	public final static int IDX_PAYMENT_TYPE = 9;
	public final static int IDX_PAYMENT_MODE = 10;
	public final static int IDX_PRODUCT_VALUE = 11;
	public final static int IDX_QUERY_ACCOUNT_TYPE = 12;
	public final static int IDX_LAC_ID = 13;
	public final static int IDX_OPP_LAC_ID = 14;
	public final static int IDX_CELL_ID = 15;
	public final static int IDX_OPP_CELL_ID = 16;
	public final static int IDX_SESSION_START_TIME = 17;
	public final static int IDX_START_TIME = 18;
	public final static int IDX_VISIT_START_TIME = 19;
	public final static int IDX_UPDATE_TIME = 20;
	public final static int IDX_TIME_SEGMENT = 21;
	public final static int IDX_SERVICE_CODE = 22;
	public final static int IDX_CHARGE_FLAG = 23;
	public final static int IDX_CC_REQUEST_NUMBER = 24;
	public final static int IDX_OFF_PEAK_INFO = 25;
	public final static int IDX_TIME_ZONE = 26;
	public final static int IDX_SERVICE_KEY = 27;
	public final static int IDX_TIME_ADDUP = 28;
	public final static int IDX_TIME_ADDUP_SWITCH = 29;
	public final static int IDX_VOLUME_ADDUP = 30;
	public final static int IDX_VOLUME_ADDUP_SWITCH = 31;
	public final static int IDX_DURATION = 32;
	public final static int IDX_OCTECT = 33;
	public final static int IDX_UP_VOLUME = 34;
	public final static int IDX_UP_VOLUME_SWITCH = 35;
	public final static int IDX_DOWN_VOLUME = 36;
	public final static int IDX_DOWN_VOLUME_SWITCH = 37;
	public final static int IDX_OPP_NUMBER = 38;
	public final static int IDX_ORI_OPP_NUMBER = 39;
	public final static int IDX_DIALED_NUMBER = 40;
	public final static int IDX_USER_NATURE = 41;
	public final static int IDX_OPP_NATURE = 42;
	public final static int IDX_A_NATURE = 43;
	public final static int IDX_SERVICE_TYPE = 44;
	public final static int IDX_MESSAGE_TYPE = 45;
	public final static int IDX_BUSINESS_CODE = 46;
	public final static int IDX_BUSINESS_CODE_GROUP = 47;
	public final static int IDX_CONTENT_CODE = 48;
	public final static int IDX_POINT_COST = 49;
	public final static int IDX_BILL_FLAG = 50;
	public final static int IDX_SMSC_ID = 51;
	public final static int IDX_DEDUCTION_FLAG = 52;
	public final static int IDX_RECORD_TYPE = 53;
	public final static int IDX_STD_ERR_CODE = 54;
	public final static int IDX_REDO_FLAG = 55;
	public final static int IDX_CAMEL_DEST_NUM = 56;
	public final static int IDX_CAMEL_SERVICE_LEVEL = 57;
	public final static int IDX_CAMEL_SERVICE_KEY = 58;
	public final static int IDX_DFT_CALL_HANDLING_IND = 59;
	public final static int IDX_TAP_CAMEL_INVO_FEE = 60;
	public final static int IDX_TAP_CAMEL_VAT_AMT = 61;
	public final static int IDX_CDR_CAMEL_FLAG = 62;
	public final static int IDX_RATING_FLAG = 63;
	public final static int IDX_OPTIMAL_CHARGE_FLAG = 64;
	public final static int IDX_ORI_RATING_FLAG = 65;
	public final static int IDX_FREE_CHARGE_TYPE = 66;
	public final static int IDX_AUTH_TYPE = 67;
	public final static int IDX_RATE_STRATEGY = 68;
	public final static int IDX_RATE_BUDGET_RES = 69;
	public final static int IDX_BLACK_LIST_ALERT = 70;
	public final static int IDX_CONDITION_ID = 71;
	public final static int IDX_PRODUCT_ID = 72;
	public final static int IDX_BILL_INDICATE = 73;
	public final static int IDX_APART_OPP_NUMBER = 74;
	public final static int IDX_ROAM_TYPE = 75;
	public final static int IDX_ACCESS_TYPE = 76;
	public final static int IDX_SP_RELA_TYPE = 77;
	public final static int IDX_OPP_CONDITION_ID = 78;
	public final static int IDX_CDR_ONLINE_FLAG = 79;
	public final static int IDX_BACKUP_DATE = 80;
	public final static int IDX_CDR_DEAL_FLAG = 81;
	public final static int IDX_USER_PROPERTY = 82;
	public final static int IDX_OPP_PROPERTY = 83;
	public final static int IDX_SP_CODE = 84;
	public final static int IDX_GROUP_TYPE = 85;
	public final static int IDX_NETCALL_FLAG = 86;
	public final static int IDX_OPP_NUMBER_TYPE = 87;
	public final static int IDX_OPP_COUNTRY_ID = 88;
	public final static int IDX_OPP_COUNTRY_GROUP = 89;
	public final static int IDX_USER_BASE_INFO = 90;
	public final static int IDX_RATE_PROD_ID = 91;
	public final static int IDX_OPP_RATE_MAIN_PROD_ID = 92;
	public final static int IDX_RATE_PROD_BILL_CYCLE = 93;
	public final static int IDX_RATE_PROD_RATING_RES = 94;
	public final static int IDX_SYS_RATING_RES = 95;
	public final static int IDX_RATE_PROD_QUOTA_RES = 96;
	public final static int IDX_SYS_QUOTA_RES = 97;
	public final static int IDX_RATING_RES = 98;
	public final static int IDX_RATE_PROD_ZONE = 99;
	public final static int IDX_RATE_PROD_ZONE_ALERT_FLAG = 100;
	public final static int IDX_TAX_RULE = 101;
	public final static int IDX_ACCUMULATE_RULE = 102;
	public final static int IDX_PROPERTY_VAL_MIN = 103;
	public final static int IDX_REJECT_CALL_ALARM_MAP = 104;
	public final static int IDX_ACCUMULATE_ALARM_LIST = 105;
	public final static int IDX_AFTER_MAKE_CALL_ALARM_RULE = 106;
	public final static int IDX_BEFORE_MAKE_CALL_ALARM_RULE = 107;
	public final static int IDX_GROUP_ACCT_PAYMENT = 108;
	public final static int IDX_GROUP_FREEBIE_PAYMENT = 109;
	public final static int IDX_CAP_MAX = 110;
	public final static int IDX_GROUP_BUDGET = 111;
	public final static int IDX_GROUP_RELATION_TYPE = 112;
	public final static int IDX_GROUP_SPEC_SHARE = 113;
	public final static int IDX_PRODUCT_EXPIRE_DATE = 114;
	public final static int IDX_PRODUCT_REDIRECTION_DATE = 115;
	public final static int IDX_CELL_CODE = 116;
	public final static int IDX_OPP_CELL_CODE = 117;
	public final static int IDX_RATE_PROD_PRICE_PLAN = 118;
	public final static int IDX_HYBRID_RULE_DEF = 119;
	public final static int IDX_SERVICE_INFO_MAP = 120;
	public final static int IDX_USER_GROUP = 121;
	public final static int IDX_MAX_TIME_PROM_SPEC_MAP = 122;
	public final static int IDX_PROCESS_FLAG = 123;
	public final static int IDX_MINIMAL_VALUE = 124;
	public final static int IDX_FIRST_UPDATE_FLAG = 125;
	public final static int IDX_FREEBIE_PROMOTED_RULE = 126;
	public final static int IDX_BORDER_ROAM_INFO_MAP = 127;
	public final static int IDX_BORDER_ROAM_TYPE = 128;
	public final static int IDX_OUT_RATING_FEC = 129;
	public final static int IDX_IN_OUT_FEC = 130;
	public final static int IDX_OUT_IN_FEC = 131;
	public final static int IDX_RATING_OUT = 132;
	public final static int IDX_ERROR_CODE = 133;
	public final static int IDX_ERROR_MESSAGE = 134;
	public final static int IDX_ERR_FILE = 135;
	public final static int IDX_ERR_MODIFY_FLAG = 136;
	public final static int IDX_ERR_INPUTTIME = 137;
	public final static int IDX_ERR_FILE_DATE = 138;
	public final static int IDX_RESERVE1 = 139;
	public final static int IDX_RESERVE2 = 140;
	public final static int IDX_RESERVE3 = 141;
	public final static int IDX_RESERVE4 = 142;
	public final static int IDX_RESERVE_FIELDS = 143;
	public final static int IDX_BILL_DATE = 144;
	public final static int IDX_REVENUE_CODE_DTL = 145;
	public final static int IDX_BILLING_OUT = 146;
	public final static int IDX_OPERATOR_RELA_TYPE = 147;
	public final static int IDX_BATCH_MESSAGE_NUMBER = 148;
	public final static int IDX_HYBRID_RULE = 149;
	public final static int IDX_SLA_RULE = 150;
	public final static int IDX_ORI_DEDUCT_TIME = 151;
	public final static int IDX_RES_COUNT = 152;
	public final static int IDX_DEDUCT_RES_COUNT = 153;
	public final static int IDX_REFUND_RES_COUNT = 154;
	public final static int IDX_RG_RULE = 155;
	public final static int IDX_ANALYSE_ALARM = 156;
	public final static int IDX_TABLE_NAME = 157;
	public final static int IDX_DATABASE_NAME = 158;
	public final static int IDX_LATE_FLAG = 159;
	public final static int IDX_IS_FILTER = 160;
	public final static int IDX_FCITYPE = 161;
	public final static int IDX_TEST_CDR_FLAG = 162;
	public final static int IDX_RG_RULE_LIST = 163;
	public final static int IDX_HISTORY_ERROR_MAP = 164;
	public final static int IDX_OFFPEAK_SWITCH_DATE = 165;
	public final static int IDX_PRODUCT_VALID_DATE = 166;
	public final static int IDX_ANALYSE_STATE_INFO = 167;
	public final static int IDX_NEAREST_INFO_CHANGE_MAP = 168;
	public final static int IDX_OUTPUT_FLAG = 169;
	public final static int IDX_CHARGE_ROLLBACK_FLAG = 170;
	public final static int IDX_OPP_USER_NUMBER = 171;
	public final static int IDX_CALL_REFNUM = 172;
	public final static int IDX_PARTIAL_ID = 173;
	public final static int IDX_FIRSTCDR_START_TIME = 174;
	public final static int IDX_TIME_WINDOW = 175;
	public final static int IDX_OCS_MONITOR_CDR = 176;
	public final static int IDX_SERVICE_KEY_VAL = 177;
	public final static int IDX_CDR_QUERY_FLAG = 178;
	public final static int IDX_SHARE_GRUOP_ID_FOR_FREEBIE = 179;
	public final static int IDX_NEW_CDR_FLAG = 180;
	public final static int IDX_BATCH_NO = 181;

	/**
	 * 
	 */
	@XmlElement(name="rsu")
	@Sdl
	private List<SRsu> rsu;

	/**
	 * 
	 */
	@XmlElement(name="timeSegment")
	@Sdl
	private SOffPeak timeSegment;

	/**
	 * 
	 */
	@XmlElement(name="offPeakInfo")
	@Sdl
	private List<SOffPeakCharge> offPeakInfo;

	/**
	 * 
	 */
	@XmlElement(name="tapCamelInvoFee")
	@Sdl
	private STapCamelInvoFee tapCamelInvoFee;

	/**
	 * 
	 */
	@XmlElement(name="tapCamelVatAmt")
	@Sdl
	private STapCamelVatAmt tapCamelVatAmt;

	/**
	 * 
	 */
	@XmlElement(name="apartOppNumber")
	@Sdl
	private SApartOppNumber apartOppNumber;

	/**
	 * 
	 */
	@XmlElement(name="oppCountryGroup")
	@Sdl
	private List<SCountryGroup> oppCountryGroup;

	/**
	 * 
	 */
	@XmlElement(name="userBaseInfo")
	@Sdl
	private SUserInfoBase userBaseInfo;

	/**
	 * 
	 */
	@XmlElement(name="rateProdId")
	@Sdl
	private Map<Long,SRateProdId> rateProdId;

	/**
	 * 
	 */
	@XmlElement(name="oppRateMainProdId")
	@Sdl
	private SRateProdId oppRateMainProdId;

	/**
	 * 
	 */
	@XmlElement(name="rateProdBillCycle")
	@Sdl
	private Map<Long,SRateProdBillCycle> rateProdBillCycle;

	/**
	 * 
	 */
	@XmlElement(name="rateProdRatingRes")
	@Sdl
	private Map<Long,SRateProdRatingRes> rateProdRatingRes;

	/**
	 * 
	 */
	@XmlElement(name="rateProdQuotaRes")
	@Sdl
	private List<SRateProdQuotaRes> rateProdQuotaRes;

	/**
	 * 
	 */
	@XmlElement(name="ratingRes")
	@Sdl
	private List<SRatingRes> ratingRes;

	/**
	 * 
	 */
	@XmlElement(name="rateProdZone")
	@Sdl
	private Map<Long,SRateProdZone> rateProdZone;

	/**
	 * 
	 */
	@XmlElement(name="taxRule")
	@Sdl
	private List<STaxRule> taxRule;

	/**
	 * 
	 */
	@XmlElement(name="accumulateRule")
	@Sdl
	private Map<Long,SAccumulateRule> accumulateRule;

	/**
	 * 
	 */
	@XmlElement(name="propertyValMin")
	@Sdl
	private Map<Long,List<SPropertyValMin>> propertyValMin;

	/**
	 * 
	 */
	@XmlElement(name="rejectCallAlarmMap")
	@Sdl
	private Map<Integer,SRejectCallAlarm> rejectCallAlarmMap;

	/**
	 * 
	 */
	@XmlElement(name="accumulateAlarmList")
	@Sdl
	private List<SAccumulateAlarm> accumulateAlarmList;

	/**
	 * 
	 */
	@XmlElement(name="groupAcctPayment")
	@Sdl
	private List<SGroupAcctPayment> groupAcctPayment;

	/**
	 * 
	 */
	@XmlElement(name="groupFreebiePayment")
	@Sdl
	private List<SGroupFreebiePayment> groupFreebiePayment;

	/**
	 * 
	 */
	@XmlElement(name="capMax")
	@Sdl
	private List<SGroupBudget> capMax;

	/**
	 * 
	 */
	@XmlElement(name="groupBudget")
	@Sdl
	private List<SGroupBudget> groupBudget;

	/**
	 * 
	 */
	@XmlElement(name="groupRelationType")
	@Sdl
	private List<Integer> groupRelationType;

	/**
	 * 
	 */
	@XmlElement(name="groupSpecShare")
	@Sdl
	private List<SGroupSpecShare> groupSpecShare;

	/**
	 * 
	 */
	@XmlElement(name="cellCode")
	@Sdl
	private List<Integer> cellCode;

	/**
	 * 
	 */
	@XmlElement(name="oppCellCode")
	@Sdl
	private List<Integer> oppCellCode;

	/**
	 * 
	 */
	@XmlElement(name="rateProdPricePlan")
	@Sdl
	private List<SRateProdPricePlan> rateProdPricePlan;

	/**
	 * 
	 */
	@XmlElement(name="hybridRuleDef")
	@Sdl
	private SHybirdRuleDefine hybridRuleDef;

	/**
	 * 
	 */
	@XmlElement(name="serviceInfoMap")
	@Sdl
	private Map<Integer,SServiceInfo> serviceInfoMap;

	/**
	 * 
	 */
	@XmlElement(name="userGroup")
	@Sdl
	private Map<Long,SUserGroupInfo> userGroup;

	/**
	 * 
	 */
	@XmlElement(name="maxTimePromSpecMap")
	@Sdl
	private Map<String,SMaxTimeProd> maxTimePromSpecMap;

	/**
	 * 
	 */
	@XmlElement(name="minimalValue")
	@Sdl
	private List<SMinimalValue> minimalValue;

	/**
	 * 
	 */
	@XmlElement(name="freebiePromotedRule")
	@Sdl
	private Map<Long,SFreebiePromote> freebiePromotedRule;

	/**
	 * 
	 */
	@XmlElement(name="borderRoamInfoMap")
	@Sdl
	private Map<String,SBorderRoamInfo> borderRoamInfoMap;

	/**
	 * 
	 */
	@XmlElement(name="outRatingFec")
	@Sdl
	private SOutRatingFecXdr outRatingFec;

	/**
	 * 
	 */
	@XmlElement(name="inOutFec")
	@Sdl
	private SInOutFecXdr inOutFec;

	/**
	 * 
	 */
	@XmlElement(name="outInFec")
	@Sdl
	private SOutInFecXdr outInFec;

	/**
	 * 
	 */
	@XmlElement(name="ratingOut")
	@Sdl
	private SOutRatingXdr ratingOut;

	/**
	 * 
	 */
	@XmlElement(name="reserveFields")
	@Sdl
	private Map<String,String> reserveFields;

	/**
	 * 
	 */
	@XmlElement(name="revenueCodeDtl")
	@Sdl
	private List<SRevenueCode> revenueCodeDtl;

	/**
	 * 
	 */
	@XmlElement(name="billingOut")
	@Sdl
	private SOutBillingXdr billingOut;

	/**
	 * 
	 */
	@XmlElement(name="slaRule")
	@Sdl
	private List<SSlaRule> slaRule;

	/**
	 * 
	 */
	@XmlElement(name="rgRule")
	@Sdl
	private List<SRgRule> rgRule;

	/**
	 * 
	 */
	@XmlElement(name="analyseAlarm")
	@Sdl
	private List<SAnalyseAlarm> analyseAlarm;

	/**
	 * 
	 */
	@XmlElement(name="rgRuleList")
	@Sdl
	private List<SSlaRule> rgRuleList;

	/**
	 * 
	 */
	@XmlElement(name="historyErrorMap")
	@Sdl
	private Map<Long,Integer> historyErrorMap;

	/**
	 * 
	 */
	@XmlElement(name="analyseStateInfo")
	@Sdl
	private SAnalyseStateInfo analyseStateInfo;

	/**
	 * 
	 */
	@XmlElement(name="nearestInfoChangeMap")
	@Sdl
	private Map<Integer,SNearestInfoChange> nearestInfoChangeMap;

	/**
	 * 
	 */
	@XmlElement(name="notifyType")
	@Sdl
	private int notifyType;

	/**
	 * 
	 */
	@XmlElement(name="processType")
	@Sdl
	private int processType;

	/**
	 * 
	 */
	@XmlElement(name="treatFlag")
	@Sdl
	private int treatFlag;

	/**
	 * 
	 */
	@XmlElement(name="uFlag")
	@Sdl
	private int uFlag;

	/**
	 * 
	 */
	@XmlElement(name="uCount")
	@Sdl
	private int uCount;

	/**
	 * 
	 */
	@XmlElement(name="xdrOutPostfix")
	@Sdl
	private String xdrOutPostfix;

	/**
	 * 
	 */
	@XmlElement(name="priorityLevel")
	@Sdl
	private int priorityLevel;

	/**
	 * 
	 */
	@XmlElement(name="qosClassId")
	@Sdl
	private int qosClassId;

	/**
	 * 
	 */
	@XmlElement(name="paymentType")
	@Sdl
	private String paymentType;

	/**
	 * 
	 */
	@XmlElement(name="paymentMode")
	@Sdl
	private String paymentMode;

	/**
	 * 
	 */
	@XmlElement(name="productValue")
	@Sdl
	private long productValue;

	/**
	 * 
	 */
	@XmlElement(name="queryAccountType")
	@Sdl
	private int queryAccountType;

	/**
	 * 
	 */
	@XmlElement(name="lacId")
	@Sdl
	private String lacId;

	/**
	 * 
	 */
	@XmlElement(name="oppLacId")
	@Sdl
	private String oppLacId;

	/**
	 * 
	 */
	@XmlElement(name="cellId")
	@Sdl
	private String cellId;

	/**
	 * 
	 */
	@XmlElement(name="oppCellId")
	@Sdl
	private String oppCellId;

	/**
	 * 
	 */
	@XmlElement(name="sessionStartTime")
	@Sdl
	private long sessionStartTime;

	/**
	 * 
	 */
	@XmlElement(name="startTime")
	@Sdl
	private long startTime;

	/**
	 * 
	 */
	@XmlElement(name="visitStartTime")
	@Sdl
	private long visitStartTime;

	/**
	 * 
	 */
	@XmlElement(name="updateTime")
	@Sdl
	private long updateTime;

	/**
	 * 
	 */
	@XmlElement(name="serviceCode")
	@Sdl
	private String serviceCode;

	/**
	 * 
	 */
	@XmlElement(name="chargeFlag")
	@Sdl
	private int chargeFlag;

	/**
	 * 
	 */
	@XmlElement(name="ccRequestNumber")
	@Sdl
	private int ccRequestNumber;

	/**
	 * 
	 */
	@XmlElement(name="timeZone")
	@Sdl
	private short timeZone;

	/**
	 * 
	 */
	@XmlElement(name="serviceKey")
	@Sdl
	private long serviceKey;

	/**
	 * 
	 */
	@XmlElement(name="timeAddup")
	@Sdl
	private long timeAddup;

	/**
	 * 
	 */
	@XmlElement(name="timeAddupSwitch")
	@Sdl
	private long timeAddupSwitch;

	/**
	 * 
	 */
	@XmlElement(name="volumeAddup")
	@Sdl
	private long volumeAddup;

	/**
	 * 
	 */
	@XmlElement(name="volumeAddupSwitch")
	@Sdl
	private long volumeAddupSwitch;

	/**
	 * 
	 */
	@XmlElement(name="duration")
	@Sdl
	private long duration;

	/**
	 * 
	 */
	@XmlElement(name="octect")
	@Sdl
	private long octect;

	/**
	 * 
	 */
	@XmlElement(name="upVolume")
	@Sdl
	private long upVolume;

	/**
	 * 
	 */
	@XmlElement(name="upVolumeSwitch")
	@Sdl
	private long upVolumeSwitch;

	/**
	 * 
	 */
	@XmlElement(name="downVolume")
	@Sdl
	private long downVolume;

	/**
	 * 
	 */
	@XmlElement(name="downVolumeSwitch")
	@Sdl
	private long downVolumeSwitch;

	/**
	 * 
	 */
	@XmlElement(name="oppNumber")
	@Sdl
	private String oppNumber;

	/**
	 * 
	 */
	@XmlElement(name="oriOppNumber")
	@Sdl
	private String oriOppNumber;

	/**
	 * 
	 */
	@XmlElement(name="dialedNumber")
	@Sdl
	private String dialedNumber;

	/**
	 * 
	 */
	@XmlElement(name="userNature")
	@Sdl
	private short userNature;

	/**
	 * 
	 */
	@XmlElement(name="oppNature")
	@Sdl
	private short oppNature;

	/**
	 * 
	 */
	@XmlElement(name="aNature")
	@Sdl
	private short aNature;

	/**
	 * 
	 */
	@XmlElement(name="serviceType")
	@Sdl
	private String serviceType;

	/**
	 * 
	 */
	@XmlElement(name="messageType")
	@Sdl
	private int messageType;

	/**
	 * 
	 */
	@XmlElement(name="businessCode")
	@Sdl
	private String businessCode;

	/**
	 * 
	 */
	@XmlElement(name="businessCodeGroup")
	@Sdl
	private int businessCodeGroup;

	/**
	 * 
	 */
	@XmlElement(name="contentCode")
	@Sdl
	private String contentCode;

	/**
	 * 
	 */
	@XmlElement(name="pointCost")
	@Sdl
	private int pointCost;

	/**
	 * 
	 */
	@XmlElement(name="billFlag")
	@Sdl
	private int billFlag;

	/**
	 * 
	 */
	@XmlElement(name="smscId")
	@Sdl
	private String smscId;

	/**
	 * 
	 */
	@XmlElement(name="deductionFlag")
	@Sdl
	private int deductionFlag;

	/**
	 * 
	 */
	@XmlElement(name="recordType")
	@Sdl
	private int recordType;

	/**
	 * 
	 */
	@XmlElement(name="stdErrCode")
	@Sdl
	private int stdErrCode;

	/**
	 * 
	 */
	@XmlElement(name="redoFlag")
	@Sdl
	private int redoFlag;

	/**
	 * 
	 */
	@XmlElement(name="camelDestNum")
	@Sdl
	private String camelDestNum;

	/**
	 * 
	 */
	@XmlElement(name="camelServiceLevel")
	@Sdl
	private short camelServiceLevel;

	/**
	 * 
	 */
	@XmlElement(name="camelServiceKey")
	@Sdl
	private int camelServiceKey;

	/**
	 * 
	 */
	@XmlElement(name="dftCallHandlingInd")
	@Sdl
	private short dftCallHandlingInd;

	/**
	 * 
	 */
	@XmlElement(name="cdrCamelFlag")
	@Sdl
	private short cdrCamelFlag;

	/**
	 * 
	 */
	@XmlElement(name="ratingFlag")
	@Sdl
	private int ratingFlag;

	/**
	 * 
	 */
	@XmlElement(name="optimalChargeFlag")
	@Sdl
	private int optimalChargeFlag;

	/**
	 * 
	 */
	@XmlElement(name="oriRatingFlag")
	@Sdl
	private int oriRatingFlag;

	/**
	 * 
	 */
	@XmlElement(name="freeChargeType")
	@Sdl
	private short freeChargeType;

	/**
	 * 
	 */
	@XmlElement(name="authType")
	@Sdl
	private int authType;

	/**
	 * 
	 */
	@XmlElement(name="rateStrategy")
	@Sdl
	private int rateStrategy;

	/**
	 * 
	 */
	@XmlElement(name="rateBudgetRes")
	@Sdl
	private int rateBudgetRes;

	/**
	 * 
	 */
	@XmlElement(name="blackListAlert")
	@Sdl
	private int blackListAlert;

	/**
	 * 
	 */
	@XmlElement(name="conditionId")
	@Sdl
	private int conditionId;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="billIndicate")
	@Sdl
	private short billIndicate;

	/**
	 * 
	 */
	@XmlElement(name="roamType")
	@Sdl
	private short roamType;

	/**
	 * 
	 */
	@XmlElement(name="accessType")
	@Sdl
	private int accessType;

	/**
	 * 
	 */
	@XmlElement(name="spRelaType")
	@Sdl
	private short spRelaType;

	/**
	 * 
	 */
	@XmlElement(name="oppConditionId")
	@Sdl
	private int oppConditionId;

	/**
	 * 
	 */
	@XmlElement(name="cdrOnlineFlag")
	@Sdl
	private int cdrOnlineFlag;

	/**
	 * 
	 */
	@XmlElement(name="backupDate")
	@Sdl
	private String backupDate;

	/**
	 * 
	 */
	@XmlElement(name="cdrDealFlag")
	@Sdl
	private short cdrDealFlag;

	/**
	 * 
	 */
	@XmlElement(name="userProperty")
	@Sdl
	private short userProperty;

	/**
	 * 
	 */
	@XmlElement(name="oppProperty")
	@Sdl
	private short oppProperty;

	/**
	 * 
	 */
	@XmlElement(name="spCode")
	@Sdl
	private String spCode;

	/**
	 * 
	 */
	@XmlElement(name="groupType")
	@Sdl
	private int groupType;

	/**
	 * 
	 */
	@XmlElement(name="netcallFlag")
	@Sdl
	private short netcallFlag;

	/**
	 * 
	 */
	@XmlElement(name="oppNumberType")
	@Sdl
	private int oppNumberType;

	/**
	 * 
	 */
	@XmlElement(name="oppCountryId")
	@Sdl
	private int oppCountryId;

	/**
	 * 
	 */
	@XmlElement(name="sysRatingRes")
	@Sdl
	private int sysRatingRes;

	/**
	 * 
	 */
	@XmlElement(name="sysQuotaRes")
	@Sdl
	private int sysQuotaRes;

	/**
	 * 
	 */
	@XmlElement(name="rateProdZoneAlertFlag")
	@Sdl
	private int rateProdZoneAlertFlag;

	/**
	 * 
	 */
	@XmlElement(name="afterMakeCallAlarmRule")
	@Sdl
	private int afterMakeCallAlarmRule;

	/**
	 * 
	 */
	@XmlElement(name="beforeMakeCallAlarmRule")
	@Sdl
	private int beforeMakeCallAlarmRule;

	/**
	 * 
	 */
	@XmlElement(name="productExpireDate")
	@Sdl
	private long productExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="productRedirectionDate")
	@Sdl
	private long productRedirectionDate;

	/**
	 * 
	 */
	@XmlElement(name="processFlag")
	@Sdl
	private int processFlag;

	/**
	 * 
	 */
	@XmlElement(name="firstUpdateFlag")
	@Sdl
	private int firstUpdateFlag;

	/**
	 * 
	 */
	@XmlElement(name="borderRoamType")
	@Sdl
	private short borderRoamType;

	/**
	 * 
	 */
	@XmlElement(name="errorCode")
	@Sdl
	private String errorCode;

	/**
	 * 
	 */
	@XmlElement(name="errorMessage")
	@Sdl
	private String errorMessage;

	/**
	 * 
	 */
	@XmlElement(name="errFile")
	@Sdl
	private String errFile;

	/**
	 * 
	 */
	@XmlElement(name="errModifyFlag")
	@Sdl
	private short errModifyFlag;

	/**
	 * 
	 */
	@XmlElement(name="errInputtime")
	@Sdl
	private long errInputtime;

	/**
	 * 
	 */
	@XmlElement(name="errFileDate")
	@Sdl
	private String errFileDate;

	/**
	 * 
	 */
	@XmlElement(name="reserve1")
	@Sdl
	private String reserve1;

	/**
	 * 
	 */
	@XmlElement(name="reserve2")
	@Sdl
	private String reserve2;

	/**
	 * 
	 */
	@XmlElement(name="reserve3")
	@Sdl
	private String reserve3;

	/**
	 * 
	 */
	@XmlElement(name="reserve4")
	@Sdl
	private String reserve4;

	/**
	 * 
	 */
	@XmlElement(name="billDate")
	@Sdl
	private long billDate;

	/**
	 * 
	 */
	@XmlElement(name="operatorRelaType")
	@Sdl
	private int operatorRelaType;

	/**
	 * 
	 */
	@XmlElement(name="batchMessageNumber")
	@Sdl
	private String batchMessageNumber;

	/**
	 * 
	 */
	@XmlElement(name="hybridRule")
	@Sdl
	private int hybridRule;

	/**
	 * 
	 */
	@XmlElement(name="oriDeductTime")
	@Sdl
	private long oriDeductTime;

	/**
	 * 
	 */
	@XmlElement(name="resCount")
	@Sdl
	private int resCount;

	/**
	 * 
	 */
	@XmlElement(name="deductResCount")
	@Sdl
	private int deductResCount;

	/**
	 * 
	 */
	@XmlElement(name="refundResCount")
	@Sdl
	private int refundResCount;

	/**
	 * 
	 */
	@XmlElement(name="tableName")
	@Sdl
	private String tableName;

	/**
	 * 
	 */
	@XmlElement(name="databaseName")
	@Sdl
	private String databaseName;

	/**
	 * 
	 */
	@XmlElement(name="lateFlag")
	@Sdl
	private short lateFlag;

	/**
	 * 
	 */
	@XmlElement(name="isFilter")
	@Sdl
	private short isFilter;

	/**
	 * 
	 */
	@XmlElement(name="fcitype")
	@Sdl
	private String fcitype;

	/**
	 * 
	 */
	@XmlElement(name="testCdrFlag")
	@Sdl
	private short testCdrFlag;

	/**
	 * 
	 */
	@XmlElement(name="offpeakSwitchDate")
	@Sdl
	private long offpeakSwitchDate;

	/**
	 * 
	 */
	@XmlElement(name="productValidDate")
	@Sdl
	private long productValidDate;

	/**
	 * 
	 */
	@XmlElement(name="outputFlag")
	@Sdl
	private int outputFlag;

	/**
	 * 
	 */
	@XmlElement(name="chargeRollbackFlag")
	@Sdl
	private int chargeRollbackFlag;

	/**
	 * 
	 */
	@XmlElement(name="oppUserNumber")
	@Sdl
	private String oppUserNumber;

	/**
	 * 
	 */
	@XmlElement(name="callRefnum")
	@Sdl
	private String callRefnum;

	/**
	 * 
	 */
	@XmlElement(name="partialId")
	@Sdl
	private String partialId;

	/**
	 * 
	 */
	@XmlElement(name="firstcdrStartTime")
	@Sdl
	private long firstcdrStartTime;

	/**
	 * 
	 */
	@XmlElement(name="timeWindow")
	@Sdl
	private long timeWindow;

	/**
	 * 
	 */
	@XmlElement(name="ocsMonitorCdr")
	@Sdl
	private long ocsMonitorCdr;

	/**
	 * 
	 */
	@XmlElement(name="serviceKeyVal")
	@Sdl
	private String serviceKeyVal;

	/**
	 * 
	 */
	@XmlElement(name="cdrQueryFlag")
	@Sdl
	private short cdrQueryFlag;

	/**
	 * 
	 */
	@XmlElement(name="shareGruopIdForFreebie")
	@Sdl
	private long shareGruopIdForFreebie;

	/**
	 * 
	 */
	@XmlElement(name="newCdrFlag")
	@Sdl
	private short newCdrFlag;

	/**
	 * 
	 */
	@XmlElement(name="batchNo")
	@Sdl
	private long batchNo;

	public void setRsu(List<SRsu> obj){
		this.rsu = obj;
		onFieldSet(6, obj);
	}

	public List<SRsu> getRsu(){
		return rsu;
	}

	public void setTimeSegment(SOffPeak obj){
		this.timeSegment = obj;
		onFieldSet(21, obj);
	}

	public SOffPeak getTimeSegment(){
		return timeSegment;
	}

	public void setOffPeakInfo(List<SOffPeakCharge> obj){
		this.offPeakInfo = obj;
		onFieldSet(25, obj);
	}

	public List<SOffPeakCharge> getOffPeakInfo(){
		return offPeakInfo;
	}

	public void setTapCamelInvoFee(STapCamelInvoFee obj){
		this.tapCamelInvoFee = obj;
		onFieldSet(60, obj);
	}

	public STapCamelInvoFee getTapCamelInvoFee(){
		return tapCamelInvoFee;
	}

	public void setTapCamelVatAmt(STapCamelVatAmt obj){
		this.tapCamelVatAmt = obj;
		onFieldSet(61, obj);
	}

	public STapCamelVatAmt getTapCamelVatAmt(){
		return tapCamelVatAmt;
	}

	public void setApartOppNumber(SApartOppNumber obj){
		this.apartOppNumber = obj;
		onFieldSet(74, obj);
	}

	public SApartOppNumber getApartOppNumber(){
		return apartOppNumber;
	}

	public void setOppCountryGroup(List<SCountryGroup> obj){
		this.oppCountryGroup = obj;
		onFieldSet(89, obj);
	}

	public List<SCountryGroup> getOppCountryGroup(){
		return oppCountryGroup;
	}

	public void setUserBaseInfo(SUserInfoBase obj){
		this.userBaseInfo = obj;
		onFieldSet(90, obj);
	}

	public SUserInfoBase getUserBaseInfo(){
		return userBaseInfo;
	}

	public void setRateProdId(Map<Long,SRateProdId> obj){
		this.rateProdId = obj;
		onFieldSet(91, obj);
	}

	public Map<Long,SRateProdId> getRateProdId(){
		return rateProdId;
	}

	public void setOppRateMainProdId(SRateProdId obj){
		this.oppRateMainProdId = obj;
		onFieldSet(92, obj);
	}

	public SRateProdId getOppRateMainProdId(){
		return oppRateMainProdId;
	}

	public void setRateProdBillCycle(Map<Long,SRateProdBillCycle> obj){
		this.rateProdBillCycle = obj;
		onFieldSet(93, obj);
	}

	public Map<Long,SRateProdBillCycle> getRateProdBillCycle(){
		return rateProdBillCycle;
	}

	public void setRateProdRatingRes(Map<Long,SRateProdRatingRes> obj){
		this.rateProdRatingRes = obj;
		onFieldSet(94, obj);
	}

	public Map<Long,SRateProdRatingRes> getRateProdRatingRes(){
		return rateProdRatingRes;
	}

	public void setRateProdQuotaRes(List<SRateProdQuotaRes> obj){
		this.rateProdQuotaRes = obj;
		onFieldSet(96, obj);
	}

	public List<SRateProdQuotaRes> getRateProdQuotaRes(){
		return rateProdQuotaRes;
	}

	public void setRatingRes(List<SRatingRes> obj){
		this.ratingRes = obj;
		onFieldSet(98, obj);
	}

	public List<SRatingRes> getRatingRes(){
		return ratingRes;
	}

	public void setRateProdZone(Map<Long,SRateProdZone> obj){
		this.rateProdZone = obj;
		onFieldSet(99, obj);
	}

	public Map<Long,SRateProdZone> getRateProdZone(){
		return rateProdZone;
	}

	public void setTaxRule(List<STaxRule> obj){
		this.taxRule = obj;
		onFieldSet(101, obj);
	}

	public List<STaxRule> getTaxRule(){
		return taxRule;
	}

	public void setAccumulateRule(Map<Long,SAccumulateRule> obj){
		this.accumulateRule = obj;
		onFieldSet(102, obj);
	}

	public Map<Long,SAccumulateRule> getAccumulateRule(){
		return accumulateRule;
	}

	public void setPropertyValMin(Map<Long,List<SPropertyValMin>> obj){
		this.propertyValMin = obj;
		onFieldSet(103, obj);
	}

	public Map<Long,List<SPropertyValMin>> getPropertyValMin(){
		return propertyValMin;
	}

	public void setRejectCallAlarmMap(Map<Integer,SRejectCallAlarm> obj){
		this.rejectCallAlarmMap = obj;
		onFieldSet(104, obj);
	}

	public Map<Integer,SRejectCallAlarm> getRejectCallAlarmMap(){
		return rejectCallAlarmMap;
	}

	public void setAccumulateAlarmList(List<SAccumulateAlarm> obj){
		this.accumulateAlarmList = obj;
		onFieldSet(105, obj);
	}

	public List<SAccumulateAlarm> getAccumulateAlarmList(){
		return accumulateAlarmList;
	}

	public void setGroupAcctPayment(List<SGroupAcctPayment> obj){
		this.groupAcctPayment = obj;
		onFieldSet(108, obj);
	}

	public List<SGroupAcctPayment> getGroupAcctPayment(){
		return groupAcctPayment;
	}

	public void setGroupFreebiePayment(List<SGroupFreebiePayment> obj){
		this.groupFreebiePayment = obj;
		onFieldSet(109, obj);
	}

	public List<SGroupFreebiePayment> getGroupFreebiePayment(){
		return groupFreebiePayment;
	}

	public void setCapMax(List<SGroupBudget> obj){
		this.capMax = obj;
		onFieldSet(110, obj);
	}

	public List<SGroupBudget> getCapMax(){
		return capMax;
	}

	public void setGroupBudget(List<SGroupBudget> obj){
		this.groupBudget = obj;
		onFieldSet(111, obj);
	}

	public List<SGroupBudget> getGroupBudget(){
		return groupBudget;
	}

	public void setGroupRelationType(List<Integer> obj){
		this.groupRelationType = obj;
		onFieldSet(112, obj);
	}

	public List<Integer> getGroupRelationType(){
		return groupRelationType;
	}

	public void setGroupSpecShare(List<SGroupSpecShare> obj){
		this.groupSpecShare = obj;
		onFieldSet(113, obj);
	}

	public List<SGroupSpecShare> getGroupSpecShare(){
		return groupSpecShare;
	}

	public void setCellCode(List<Integer> obj){
		this.cellCode = obj;
		onFieldSet(116, obj);
	}

	public List<Integer> getCellCode(){
		return cellCode;
	}

	public void setOppCellCode(List<Integer> obj){
		this.oppCellCode = obj;
		onFieldSet(117, obj);
	}

	public List<Integer> getOppCellCode(){
		return oppCellCode;
	}

	public void setRateProdPricePlan(List<SRateProdPricePlan> obj){
		this.rateProdPricePlan = obj;
		onFieldSet(118, obj);
	}

	public List<SRateProdPricePlan> getRateProdPricePlan(){
		return rateProdPricePlan;
	}

	public void setHybridRuleDef(SHybirdRuleDefine obj){
		this.hybridRuleDef = obj;
		onFieldSet(119, obj);
	}

	public SHybirdRuleDefine getHybridRuleDef(){
		return hybridRuleDef;
	}

	public void setServiceInfoMap(Map<Integer,SServiceInfo> obj){
		this.serviceInfoMap = obj;
		onFieldSet(120, obj);
	}

	public Map<Integer,SServiceInfo> getServiceInfoMap(){
		return serviceInfoMap;
	}

	public void setUserGroup(Map<Long,SUserGroupInfo> obj){
		this.userGroup = obj;
		onFieldSet(121, obj);
	}

	public Map<Long,SUserGroupInfo> getUserGroup(){
		return userGroup;
	}

	public void setMaxTimePromSpecMap(Map<String,SMaxTimeProd> obj){
		this.maxTimePromSpecMap = obj;
		onFieldSet(122, obj);
	}

	public Map<String,SMaxTimeProd> getMaxTimePromSpecMap(){
		return maxTimePromSpecMap;
	}

	public void setMinimalValue(List<SMinimalValue> obj){
		this.minimalValue = obj;
		onFieldSet(124, obj);
	}

	public List<SMinimalValue> getMinimalValue(){
		return minimalValue;
	}

	public void setFreebiePromotedRule(Map<Long,SFreebiePromote> obj){
		this.freebiePromotedRule = obj;
		onFieldSet(126, obj);
	}

	public Map<Long,SFreebiePromote> getFreebiePromotedRule(){
		return freebiePromotedRule;
	}

	public void setBorderRoamInfoMap(Map<String,SBorderRoamInfo> obj){
		this.borderRoamInfoMap = obj;
		onFieldSet(127, obj);
	}

	public Map<String,SBorderRoamInfo> getBorderRoamInfoMap(){
		return borderRoamInfoMap;
	}

	public void setOutRatingFec(SOutRatingFecXdr obj){
		this.outRatingFec = obj;
		onFieldSet(129, obj);
	}

	public SOutRatingFecXdr getOutRatingFec(){
		return outRatingFec;
	}

	public void setInOutFec(SInOutFecXdr obj){
		this.inOutFec = obj;
		onFieldSet(130, obj);
	}

	public SInOutFecXdr getInOutFec(){
		return inOutFec;
	}

	public void setOutInFec(SOutInFecXdr obj){
		this.outInFec = obj;
		onFieldSet(131, obj);
	}

	public SOutInFecXdr getOutInFec(){
		return outInFec;
	}

	public void setRatingOut(SOutRatingXdr obj){
		this.ratingOut = obj;
		onFieldSet(132, obj);
	}

	public SOutRatingXdr getRatingOut(){
		return ratingOut;
	}

	public void setReserveFields(Map<String,String> obj){
		this.reserveFields = obj;
		onFieldSet(143, obj);
	}

	public Map<String,String> getReserveFields(){
		return reserveFields;
	}

	public void setRevenueCodeDtl(List<SRevenueCode> obj){
		this.revenueCodeDtl = obj;
		onFieldSet(145, obj);
	}

	public List<SRevenueCode> getRevenueCodeDtl(){
		return revenueCodeDtl;
	}

	public void setBillingOut(SOutBillingXdr obj){
		this.billingOut = obj;
		onFieldSet(146, obj);
	}

	public SOutBillingXdr getBillingOut(){
		return billingOut;
	}

	public void setSlaRule(List<SSlaRule> obj){
		this.slaRule = obj;
		onFieldSet(150, obj);
	}

	public List<SSlaRule> getSlaRule(){
		return slaRule;
	}

	public void setRgRule(List<SRgRule> obj){
		this.rgRule = obj;
		onFieldSet(155, obj);
	}

	public List<SRgRule> getRgRule(){
		return rgRule;
	}

	public void setAnalyseAlarm(List<SAnalyseAlarm> obj){
		this.analyseAlarm = obj;
		onFieldSet(156, obj);
	}

	public List<SAnalyseAlarm> getAnalyseAlarm(){
		return analyseAlarm;
	}

	public void setRgRuleList(List<SSlaRule> obj){
		this.rgRuleList = obj;
		onFieldSet(163, obj);
	}

	public List<SSlaRule> getRgRuleList(){
		return rgRuleList;
	}

	public void setHistoryErrorMap(Map<Long,Integer> obj){
		this.historyErrorMap = obj;
		onFieldSet(164, obj);
	}

	public Map<Long,Integer> getHistoryErrorMap(){
		return historyErrorMap;
	}

	public void setAnalyseStateInfo(SAnalyseStateInfo obj){
		this.analyseStateInfo = obj;
		onFieldSet(167, obj);
	}

	public SAnalyseStateInfo getAnalyseStateInfo(){
		return analyseStateInfo;
	}

	public void setNearestInfoChangeMap(Map<Integer,SNearestInfoChange> obj){
		this.nearestInfoChangeMap = obj;
		onFieldSet(168, obj);
	}

	public Map<Integer,SNearestInfoChange> getNearestInfoChangeMap(){
		return nearestInfoChangeMap;
	}

	public void setNotifyType(int obj){
		this.notifyType = obj;
		onFieldSet(0, obj);
	}

	public int getNotifyType(){
		return notifyType;
	}

	public void setProcessType(int obj){
		this.processType = obj;
		onFieldSet(1, obj);
	}

	public int getProcessType(){
		return processType;
	}

	public void setTreatFlag(int obj){
		this.treatFlag = obj;
		onFieldSet(2, obj);
	}

	public int getTreatFlag(){
		return treatFlag;
	}

	public void setUFlag(int obj){
		this.uFlag = obj;
		onFieldSet(3, obj);
	}

	public int getUFlag(){
		return uFlag;
	}

	public void setUCount(int obj){
		this.uCount = obj;
		onFieldSet(4, obj);
	}

	public int getUCount(){
		return uCount;
	}

	public void setXdrOutPostfix(String obj){
		this.xdrOutPostfix = obj;
		onFieldSet(5, obj);
	}

	public String getXdrOutPostfix(){
		return xdrOutPostfix;
	}

	public void setPriorityLevel(int obj){
		this.priorityLevel = obj;
		onFieldSet(7, obj);
	}

	public int getPriorityLevel(){
		return priorityLevel;
	}

	public void setQosClassId(int obj){
		this.qosClassId = obj;
		onFieldSet(8, obj);
	}

	public int getQosClassId(){
		return qosClassId;
	}

	public void setPaymentType(String obj){
		this.paymentType = obj;
		onFieldSet(9, obj);
	}

	public String getPaymentType(){
		return paymentType;
	}

	public void setPaymentMode(String obj){
		this.paymentMode = obj;
		onFieldSet(10, obj);
	}

	public String getPaymentMode(){
		return paymentMode;
	}

	public void setProductValue(long obj){
		this.productValue = obj;
		onFieldSet(11, obj);
	}

	public long getProductValue(){
		return productValue;
	}

	public void setQueryAccountType(int obj){
		this.queryAccountType = obj;
		onFieldSet(12, obj);
	}

	public int getQueryAccountType(){
		return queryAccountType;
	}

	public void setLacId(String obj){
		this.lacId = obj;
		onFieldSet(13, obj);
	}

	public String getLacId(){
		return lacId;
	}

	public void setOppLacId(String obj){
		this.oppLacId = obj;
		onFieldSet(14, obj);
	}

	public String getOppLacId(){
		return oppLacId;
	}

	public void setCellId(String obj){
		this.cellId = obj;
		onFieldSet(15, obj);
	}

	public String getCellId(){
		return cellId;
	}

	public void setOppCellId(String obj){
		this.oppCellId = obj;
		onFieldSet(16, obj);
	}

	public String getOppCellId(){
		return oppCellId;
	}

	public void setSessionStartTime(long obj){
		this.sessionStartTime = obj;
		onFieldSet(17, obj);
	}

	public long getSessionStartTime(){
		return sessionStartTime;
	}

	public void setStartTime(long obj){
		this.startTime = obj;
		onFieldSet(18, obj);
	}

	public long getStartTime(){
		return startTime;
	}

	public void setVisitStartTime(long obj){
		this.visitStartTime = obj;
		onFieldSet(19, obj);
	}

	public long getVisitStartTime(){
		return visitStartTime;
	}

	public void setUpdateTime(long obj){
		this.updateTime = obj;
		onFieldSet(20, obj);
	}

	public long getUpdateTime(){
		return updateTime;
	}

	public void setServiceCode(String obj){
		this.serviceCode = obj;
		onFieldSet(22, obj);
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setChargeFlag(int obj){
		this.chargeFlag = obj;
		onFieldSet(23, obj);
	}

	public int getChargeFlag(){
		return chargeFlag;
	}

	public void setCcRequestNumber(int obj){
		this.ccRequestNumber = obj;
		onFieldSet(24, obj);
	}

	public int getCcRequestNumber(){
		return ccRequestNumber;
	}

	public void setTimeZone(short obj){
		this.timeZone = obj;
		onFieldSet(26, obj);
	}

	public short getTimeZone(){
		return timeZone;
	}

	public void setServiceKey(long obj){
		this.serviceKey = obj;
		onFieldSet(27, obj);
	}

	public long getServiceKey(){
		return serviceKey;
	}

	public void setTimeAddup(long obj){
		this.timeAddup = obj;
		onFieldSet(28, obj);
	}

	public long getTimeAddup(){
		return timeAddup;
	}

	public void setTimeAddupSwitch(long obj){
		this.timeAddupSwitch = obj;
		onFieldSet(29, obj);
	}

	public long getTimeAddupSwitch(){
		return timeAddupSwitch;
	}

	public void setVolumeAddup(long obj){
		this.volumeAddup = obj;
		onFieldSet(30, obj);
	}

	public long getVolumeAddup(){
		return volumeAddup;
	}

	public void setVolumeAddupSwitch(long obj){
		this.volumeAddupSwitch = obj;
		onFieldSet(31, obj);
	}

	public long getVolumeAddupSwitch(){
		return volumeAddupSwitch;
	}

	public void setDuration(long obj){
		this.duration = obj;
		onFieldSet(32, obj);
	}

	public long getDuration(){
		return duration;
	}

	public void setOctect(long obj){
		this.octect = obj;
		onFieldSet(33, obj);
	}

	public long getOctect(){
		return octect;
	}

	public void setUpVolume(long obj){
		this.upVolume = obj;
		onFieldSet(34, obj);
	}

	public long getUpVolume(){
		return upVolume;
	}

	public void setUpVolumeSwitch(long obj){
		this.upVolumeSwitch = obj;
		onFieldSet(35, obj);
	}

	public long getUpVolumeSwitch(){
		return upVolumeSwitch;
	}

	public void setDownVolume(long obj){
		this.downVolume = obj;
		onFieldSet(36, obj);
	}

	public long getDownVolume(){
		return downVolume;
	}

	public void setDownVolumeSwitch(long obj){
		this.downVolumeSwitch = obj;
		onFieldSet(37, obj);
	}

	public long getDownVolumeSwitch(){
		return downVolumeSwitch;
	}

	public void setOppNumber(String obj){
		this.oppNumber = obj;
		onFieldSet(38, obj);
	}

	public String getOppNumber(){
		return oppNumber;
	}

	public void setOriOppNumber(String obj){
		this.oriOppNumber = obj;
		onFieldSet(39, obj);
	}

	public String getOriOppNumber(){
		return oriOppNumber;
	}

	public void setDialedNumber(String obj){
		this.dialedNumber = obj;
		onFieldSet(40, obj);
	}

	public String getDialedNumber(){
		return dialedNumber;
	}

	public void setUserNature(short obj){
		this.userNature = obj;
		onFieldSet(41, obj);
	}

	public short getUserNature(){
		return userNature;
	}

	public void setOppNature(short obj){
		this.oppNature = obj;
		onFieldSet(42, obj);
	}

	public short getOppNature(){
		return oppNature;
	}

	public void setANature(short obj){
		this.aNature = obj;
		onFieldSet(43, obj);
	}

	public short getANature(){
		return aNature;
	}

	public void setServiceType(String obj){
		this.serviceType = obj;
		onFieldSet(44, obj);
	}

	public String getServiceType(){
		return serviceType;
	}

	public void setMessageType(int obj){
		this.messageType = obj;
		onFieldSet(45, obj);
	}

	public int getMessageType(){
		return messageType;
	}

	public void setBusinessCode(String obj){
		this.businessCode = obj;
		onFieldSet(46, obj);
	}

	public String getBusinessCode(){
		return businessCode;
	}

	public void setBusinessCodeGroup(int obj){
		this.businessCodeGroup = obj;
		onFieldSet(47, obj);
	}

	public int getBusinessCodeGroup(){
		return businessCodeGroup;
	}

	public void setContentCode(String obj){
		this.contentCode = obj;
		onFieldSet(48, obj);
	}

	public String getContentCode(){
		return contentCode;
	}

	public void setPointCost(int obj){
		this.pointCost = obj;
		onFieldSet(49, obj);
	}

	public int getPointCost(){
		return pointCost;
	}

	public void setBillFlag(int obj){
		this.billFlag = obj;
		onFieldSet(50, obj);
	}

	public int getBillFlag(){
		return billFlag;
	}

	public void setSmscId(String obj){
		this.smscId = obj;
		onFieldSet(51, obj);
	}

	public String getSmscId(){
		return smscId;
	}

	public void setDeductionFlag(int obj){
		this.deductionFlag = obj;
		onFieldSet(52, obj);
	}

	public int getDeductionFlag(){
		return deductionFlag;
	}

	public void setRecordType(int obj){
		this.recordType = obj;
		onFieldSet(53, obj);
	}

	public int getRecordType(){
		return recordType;
	}

	public void setStdErrCode(int obj){
		this.stdErrCode = obj;
		onFieldSet(54, obj);
	}

	public int getStdErrCode(){
		return stdErrCode;
	}

	public void setRedoFlag(int obj){
		this.redoFlag = obj;
		onFieldSet(55, obj);
	}

	public int getRedoFlag(){
		return redoFlag;
	}

	public void setCamelDestNum(String obj){
		this.camelDestNum = obj;
		onFieldSet(56, obj);
	}

	public String getCamelDestNum(){
		return camelDestNum;
	}

	public void setCamelServiceLevel(short obj){
		this.camelServiceLevel = obj;
		onFieldSet(57, obj);
	}

	public short getCamelServiceLevel(){
		return camelServiceLevel;
	}

	public void setCamelServiceKey(int obj){
		this.camelServiceKey = obj;
		onFieldSet(58, obj);
	}

	public int getCamelServiceKey(){
		return camelServiceKey;
	}

	public void setDftCallHandlingInd(short obj){
		this.dftCallHandlingInd = obj;
		onFieldSet(59, obj);
	}

	public short getDftCallHandlingInd(){
		return dftCallHandlingInd;
	}

	public void setCdrCamelFlag(short obj){
		this.cdrCamelFlag = obj;
		onFieldSet(62, obj);
	}

	public short getCdrCamelFlag(){
		return cdrCamelFlag;
	}

	public void setRatingFlag(int obj){
		this.ratingFlag = obj;
		onFieldSet(63, obj);
	}

	public int getRatingFlag(){
		return ratingFlag;
	}

	public void setOptimalChargeFlag(int obj){
		this.optimalChargeFlag = obj;
		onFieldSet(64, obj);
	}

	public int getOptimalChargeFlag(){
		return optimalChargeFlag;
	}

	public void setOriRatingFlag(int obj){
		this.oriRatingFlag = obj;
		onFieldSet(65, obj);
	}

	public int getOriRatingFlag(){
		return oriRatingFlag;
	}

	public void setFreeChargeType(short obj){
		this.freeChargeType = obj;
		onFieldSet(66, obj);
	}

	public short getFreeChargeType(){
		return freeChargeType;
	}

	public void setAuthType(int obj){
		this.authType = obj;
		onFieldSet(67, obj);
	}

	public int getAuthType(){
		return authType;
	}

	public void setRateStrategy(int obj){
		this.rateStrategy = obj;
		onFieldSet(68, obj);
	}

	public int getRateStrategy(){
		return rateStrategy;
	}

	public void setRateBudgetRes(int obj){
		this.rateBudgetRes = obj;
		onFieldSet(69, obj);
	}

	public int getRateBudgetRes(){
		return rateBudgetRes;
	}

	public void setBlackListAlert(int obj){
		this.blackListAlert = obj;
		onFieldSet(70, obj);
	}

	public int getBlackListAlert(){
		return blackListAlert;
	}

	public void setConditionId(int obj){
		this.conditionId = obj;
		onFieldSet(71, obj);
	}

	public int getConditionId(){
		return conditionId;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(72, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setBillIndicate(short obj){
		this.billIndicate = obj;
		onFieldSet(73, obj);
	}

	public short getBillIndicate(){
		return billIndicate;
	}

	public void setRoamType(short obj){
		this.roamType = obj;
		onFieldSet(75, obj);
	}

	public short getRoamType(){
		return roamType;
	}

	public void setAccessType(int obj){
		this.accessType = obj;
		onFieldSet(76, obj);
	}

	public int getAccessType(){
		return accessType;
	}

	public void setSpRelaType(short obj){
		this.spRelaType = obj;
		onFieldSet(77, obj);
	}

	public short getSpRelaType(){
		return spRelaType;
	}

	public void setOppConditionId(int obj){
		this.oppConditionId = obj;
		onFieldSet(78, obj);
	}

	public int getOppConditionId(){
		return oppConditionId;
	}

	public void setCdrOnlineFlag(int obj){
		this.cdrOnlineFlag = obj;
		onFieldSet(79, obj);
	}

	public int getCdrOnlineFlag(){
		return cdrOnlineFlag;
	}

	public void setBackupDate(String obj){
		this.backupDate = obj;
		onFieldSet(80, obj);
	}

	public String getBackupDate(){
		return backupDate;
	}

	public void setCdrDealFlag(short obj){
		this.cdrDealFlag = obj;
		onFieldSet(81, obj);
	}

	public short getCdrDealFlag(){
		return cdrDealFlag;
	}

	public void setUserProperty(short obj){
		this.userProperty = obj;
		onFieldSet(82, obj);
	}

	public short getUserProperty(){
		return userProperty;
	}

	public void setOppProperty(short obj){
		this.oppProperty = obj;
		onFieldSet(83, obj);
	}

	public short getOppProperty(){
		return oppProperty;
	}

	public void setSpCode(String obj){
		this.spCode = obj;
		onFieldSet(84, obj);
	}

	public String getSpCode(){
		return spCode;
	}

	public void setGroupType(int obj){
		this.groupType = obj;
		onFieldSet(85, obj);
	}

	public int getGroupType(){
		return groupType;
	}

	public void setNetcallFlag(short obj){
		this.netcallFlag = obj;
		onFieldSet(86, obj);
	}

	public short getNetcallFlag(){
		return netcallFlag;
	}

	public void setOppNumberType(int obj){
		this.oppNumberType = obj;
		onFieldSet(87, obj);
	}

	public int getOppNumberType(){
		return oppNumberType;
	}

	public void setOppCountryId(int obj){
		this.oppCountryId = obj;
		onFieldSet(88, obj);
	}

	public int getOppCountryId(){
		return oppCountryId;
	}

	public void setSysRatingRes(int obj){
		this.sysRatingRes = obj;
		onFieldSet(95, obj);
	}

	public int getSysRatingRes(){
		return sysRatingRes;
	}

	public void setSysQuotaRes(int obj){
		this.sysQuotaRes = obj;
		onFieldSet(97, obj);
	}

	public int getSysQuotaRes(){
		return sysQuotaRes;
	}

	public void setRateProdZoneAlertFlag(int obj){
		this.rateProdZoneAlertFlag = obj;
		onFieldSet(100, obj);
	}

	public int getRateProdZoneAlertFlag(){
		return rateProdZoneAlertFlag;
	}

	public void setAfterMakeCallAlarmRule(int obj){
		this.afterMakeCallAlarmRule = obj;
		onFieldSet(106, obj);
	}

	public int getAfterMakeCallAlarmRule(){
		return afterMakeCallAlarmRule;
	}

	public void setBeforeMakeCallAlarmRule(int obj){
		this.beforeMakeCallAlarmRule = obj;
		onFieldSet(107, obj);
	}

	public int getBeforeMakeCallAlarmRule(){
		return beforeMakeCallAlarmRule;
	}

	public void setProductExpireDate(long obj){
		this.productExpireDate = obj;
		onFieldSet(114, obj);
	}

	public long getProductExpireDate(){
		return productExpireDate;
	}

	public void setProductRedirectionDate(long obj){
		this.productRedirectionDate = obj;
		onFieldSet(115, obj);
	}

	public long getProductRedirectionDate(){
		return productRedirectionDate;
	}

	public void setProcessFlag(int obj){
		this.processFlag = obj;
		onFieldSet(123, obj);
	}

	public int getProcessFlag(){
		return processFlag;
	}

	public void setFirstUpdateFlag(int obj){
		this.firstUpdateFlag = obj;
		onFieldSet(125, obj);
	}

	public int getFirstUpdateFlag(){
		return firstUpdateFlag;
	}

	public void setBorderRoamType(short obj){
		this.borderRoamType = obj;
		onFieldSet(128, obj);
	}

	public short getBorderRoamType(){
		return borderRoamType;
	}

	public void setErrorCode(String obj){
		this.errorCode = obj;
		onFieldSet(133, obj);
	}

	public String getErrorCode(){
		return errorCode;
	}

	public void setErrorMessage(String obj){
		this.errorMessage = obj;
		onFieldSet(134, obj);
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setErrFile(String obj){
		this.errFile = obj;
		onFieldSet(135, obj);
	}

	public String getErrFile(){
		return errFile;
	}

	public void setErrModifyFlag(short obj){
		this.errModifyFlag = obj;
		onFieldSet(136, obj);
	}

	public short getErrModifyFlag(){
		return errModifyFlag;
	}

	public void setErrInputtime(long obj){
		this.errInputtime = obj;
		onFieldSet(137, obj);
	}

	public long getErrInputtime(){
		return errInputtime;
	}

	public void setErrFileDate(String obj){
		this.errFileDate = obj;
		onFieldSet(138, obj);
	}

	public String getErrFileDate(){
		return errFileDate;
	}

	public void setReserve1(String obj){
		this.reserve1 = obj;
		onFieldSet(139, obj);
	}

	public String getReserve1(){
		return reserve1;
	}

	public void setReserve2(String obj){
		this.reserve2 = obj;
		onFieldSet(140, obj);
	}

	public String getReserve2(){
		return reserve2;
	}

	public void setReserve3(String obj){
		this.reserve3 = obj;
		onFieldSet(141, obj);
	}

	public String getReserve3(){
		return reserve3;
	}

	public void setReserve4(String obj){
		this.reserve4 = obj;
		onFieldSet(142, obj);
	}

	public String getReserve4(){
		return reserve4;
	}

	public void setBillDate(long obj){
		this.billDate = obj;
		onFieldSet(144, obj);
	}

	public long getBillDate(){
		return billDate;
	}

	public void setOperatorRelaType(int obj){
		this.operatorRelaType = obj;
		onFieldSet(147, obj);
	}

	public int getOperatorRelaType(){
		return operatorRelaType;
	}

	public void setBatchMessageNumber(String obj){
		this.batchMessageNumber = obj;
		onFieldSet(148, obj);
	}

	public String getBatchMessageNumber(){
		return batchMessageNumber;
	}

	public void setHybridRule(int obj){
		this.hybridRule = obj;
		onFieldSet(149, obj);
	}

	public int getHybridRule(){
		return hybridRule;
	}

	public void setOriDeductTime(long obj){
		this.oriDeductTime = obj;
		onFieldSet(151, obj);
	}

	public long getOriDeductTime(){
		return oriDeductTime;
	}

	public void setResCount(int obj){
		this.resCount = obj;
		onFieldSet(152, obj);
	}

	public int getResCount(){
		return resCount;
	}

	public void setDeductResCount(int obj){
		this.deductResCount = obj;
		onFieldSet(153, obj);
	}

	public int getDeductResCount(){
		return deductResCount;
	}

	public void setRefundResCount(int obj){
		this.refundResCount = obj;
		onFieldSet(154, obj);
	}

	public int getRefundResCount(){
		return refundResCount;
	}

	public void setTableName(String obj){
		this.tableName = obj;
		onFieldSet(157, obj);
	}

	public String getTableName(){
		return tableName;
	}

	public void setDatabaseName(String obj){
		this.databaseName = obj;
		onFieldSet(158, obj);
	}

	public String getDatabaseName(){
		return databaseName;
	}

	public void setLateFlag(short obj){
		this.lateFlag = obj;
		onFieldSet(159, obj);
	}

	public short getLateFlag(){
		return lateFlag;
	}

	public void setIsFilter(short obj){
		this.isFilter = obj;
		onFieldSet(160, obj);
	}

	public short getIsFilter(){
		return isFilter;
	}

	public void setFcitype(String obj){
		this.fcitype = obj;
		onFieldSet(161, obj);
	}

	public String getFcitype(){
		return fcitype;
	}

	public void setTestCdrFlag(short obj){
		this.testCdrFlag = obj;
		onFieldSet(162, obj);
	}

	public short getTestCdrFlag(){
		return testCdrFlag;
	}

	public void setOffpeakSwitchDate(long obj){
		this.offpeakSwitchDate = obj;
		onFieldSet(165, obj);
	}

	public long getOffpeakSwitchDate(){
		return offpeakSwitchDate;
	}

	public void setProductValidDate(long obj){
		this.productValidDate = obj;
		onFieldSet(166, obj);
	}

	public long getProductValidDate(){
		return productValidDate;
	}

	public void setOutputFlag(int obj){
		this.outputFlag = obj;
		onFieldSet(169, obj);
	}

	public int getOutputFlag(){
		return outputFlag;
	}

	public void setChargeRollbackFlag(int obj){
		this.chargeRollbackFlag = obj;
		onFieldSet(170, obj);
	}

	public int getChargeRollbackFlag(){
		return chargeRollbackFlag;
	}

	public void setOppUserNumber(String obj){
		this.oppUserNumber = obj;
		onFieldSet(171, obj);
	}

	public String getOppUserNumber(){
		return oppUserNumber;
	}

	public void setCallRefnum(String obj){
		this.callRefnum = obj;
		onFieldSet(172, obj);
	}

	public String getCallRefnum(){
		return callRefnum;
	}

	public void setPartialId(String obj){
		this.partialId = obj;
		onFieldSet(173, obj);
	}

	public String getPartialId(){
		return partialId;
	}

	public void setFirstcdrStartTime(long obj){
		this.firstcdrStartTime = obj;
		onFieldSet(174, obj);
	}

	public long getFirstcdrStartTime(){
		return firstcdrStartTime;
	}

	public void setTimeWindow(long obj){
		this.timeWindow = obj;
		onFieldSet(175, obj);
	}

	public long getTimeWindow(){
		return timeWindow;
	}

	public void setOcsMonitorCdr(long obj){
		this.ocsMonitorCdr = obj;
		onFieldSet(176, obj);
	}

	public long getOcsMonitorCdr(){
		return ocsMonitorCdr;
	}

	public void setServiceKeyVal(String obj){
		this.serviceKeyVal = obj;
		onFieldSet(177, obj);
	}

	public String getServiceKeyVal(){
		return serviceKeyVal;
	}

	public void setCdrQueryFlag(short obj){
		this.cdrQueryFlag = obj;
		onFieldSet(178, obj);
	}

	public short getCdrQueryFlag(){
		return cdrQueryFlag;
	}

	public void setShareGruopIdForFreebie(long obj){
		this.shareGruopIdForFreebie = obj;
		onFieldSet(179, obj);
	}

	public long getShareGruopIdForFreebie(){
		return shareGruopIdForFreebie;
	}

	public void setNewCdrFlag(short obj){
		this.newCdrFlag = obj;
		onFieldSet(180, obj);
	}

	public short getNewCdrFlag(){
		return newCdrFlag;
	}

	public void setBatchNo(long obj){
		this.batchNo = obj;
		onFieldSet(181, obj);
	}

	public long getBatchNo(){
		return batchNo;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSubCommon(){
		m_llMarkers = new long[3]; // used marker
		m_llUsedMarkers = new long[3]; // used marker
		fieldNum = 54; 
		markerNum = 3; 
	}

	/**
	 * 创建copy方法
	 */
	public SSubCommon(SSubCommon arg0){
		copy(arg0);
	}

	public void copy(final SSubCommon rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		notifyType = rhs.notifyType;
		processType = rhs.processType;
		treatFlag = rhs.treatFlag;
		uFlag = rhs.uFlag;
		uCount = rhs.uCount;
		xdrOutPostfix = rhs.xdrOutPostfix;
		rsu = rhs.rsu;
		priorityLevel = rhs.priorityLevel;
		qosClassId = rhs.qosClassId;
		paymentType = rhs.paymentType;
		paymentMode = rhs.paymentMode;
		productValue = rhs.productValue;
		queryAccountType = rhs.queryAccountType;
		lacId = rhs.lacId;
		oppLacId = rhs.oppLacId;
		cellId = rhs.cellId;
		oppCellId = rhs.oppCellId;
		sessionStartTime = rhs.sessionStartTime;
		startTime = rhs.startTime;
		visitStartTime = rhs.visitStartTime;
		updateTime = rhs.updateTime;
		timeSegment = rhs.timeSegment;
		serviceCode = rhs.serviceCode;
		chargeFlag = rhs.chargeFlag;
		ccRequestNumber = rhs.ccRequestNumber;
		offPeakInfo = rhs.offPeakInfo;
		timeZone = rhs.timeZone;
		serviceKey = rhs.serviceKey;
		timeAddup = rhs.timeAddup;
		timeAddupSwitch = rhs.timeAddupSwitch;
		volumeAddup = rhs.volumeAddup;
		volumeAddupSwitch = rhs.volumeAddupSwitch;
		duration = rhs.duration;
		octect = rhs.octect;
		upVolume = rhs.upVolume;
		upVolumeSwitch = rhs.upVolumeSwitch;
		downVolume = rhs.downVolume;
		downVolumeSwitch = rhs.downVolumeSwitch;
		oppNumber = rhs.oppNumber;
		oriOppNumber = rhs.oriOppNumber;
		dialedNumber = rhs.dialedNumber;
		userNature = rhs.userNature;
		oppNature = rhs.oppNature;
		aNature = rhs.aNature;
		serviceType = rhs.serviceType;
		messageType = rhs.messageType;
		businessCode = rhs.businessCode;
		businessCodeGroup = rhs.businessCodeGroup;
		contentCode = rhs.contentCode;
		pointCost = rhs.pointCost;
		billFlag = rhs.billFlag;
		smscId = rhs.smscId;
		deductionFlag = rhs.deductionFlag;
		recordType = rhs.recordType;
		stdErrCode = rhs.stdErrCode;
		redoFlag = rhs.redoFlag;
		camelDestNum = rhs.camelDestNum;
		camelServiceLevel = rhs.camelServiceLevel;
		camelServiceKey = rhs.camelServiceKey;
		dftCallHandlingInd = rhs.dftCallHandlingInd;
		tapCamelInvoFee = rhs.tapCamelInvoFee;
		tapCamelVatAmt = rhs.tapCamelVatAmt;
		cdrCamelFlag = rhs.cdrCamelFlag;
		ratingFlag = rhs.ratingFlag;
		optimalChargeFlag = rhs.optimalChargeFlag;
		oriRatingFlag = rhs.oriRatingFlag;
		freeChargeType = rhs.freeChargeType;
		authType = rhs.authType;
		rateStrategy = rhs.rateStrategy;
		rateBudgetRes = rhs.rateBudgetRes;
		blackListAlert = rhs.blackListAlert;
		conditionId = rhs.conditionId;
		productId = rhs.productId;
		billIndicate = rhs.billIndicate;
		apartOppNumber = rhs.apartOppNumber;
		roamType = rhs.roamType;
		accessType = rhs.accessType;
		spRelaType = rhs.spRelaType;
		oppConditionId = rhs.oppConditionId;
		cdrOnlineFlag = rhs.cdrOnlineFlag;
		backupDate = rhs.backupDate;
		cdrDealFlag = rhs.cdrDealFlag;
		userProperty = rhs.userProperty;
		oppProperty = rhs.oppProperty;
		spCode = rhs.spCode;
		groupType = rhs.groupType;
		netcallFlag = rhs.netcallFlag;
		oppNumberType = rhs.oppNumberType;
		oppCountryId = rhs.oppCountryId;
		oppCountryGroup = rhs.oppCountryGroup;
		userBaseInfo = rhs.userBaseInfo;
		rateProdId = rhs.rateProdId;
		oppRateMainProdId = rhs.oppRateMainProdId;
		rateProdBillCycle = rhs.rateProdBillCycle;
		rateProdRatingRes = rhs.rateProdRatingRes;
		sysRatingRes = rhs.sysRatingRes;
		rateProdQuotaRes = rhs.rateProdQuotaRes;
		sysQuotaRes = rhs.sysQuotaRes;
		ratingRes = rhs.ratingRes;
		rateProdZone = rhs.rateProdZone;
		rateProdZoneAlertFlag = rhs.rateProdZoneAlertFlag;
		taxRule = rhs.taxRule;
		accumulateRule = rhs.accumulateRule;
		propertyValMin = rhs.propertyValMin;
		rejectCallAlarmMap = rhs.rejectCallAlarmMap;
		accumulateAlarmList = rhs.accumulateAlarmList;
		afterMakeCallAlarmRule = rhs.afterMakeCallAlarmRule;
		beforeMakeCallAlarmRule = rhs.beforeMakeCallAlarmRule;
		groupAcctPayment = rhs.groupAcctPayment;
		groupFreebiePayment = rhs.groupFreebiePayment;
		capMax = rhs.capMax;
		groupBudget = rhs.groupBudget;
		groupRelationType = rhs.groupRelationType;
		groupSpecShare = rhs.groupSpecShare;
		productExpireDate = rhs.productExpireDate;
		productRedirectionDate = rhs.productRedirectionDate;
		cellCode = rhs.cellCode;
		oppCellCode = rhs.oppCellCode;
		rateProdPricePlan = rhs.rateProdPricePlan;
		hybridRuleDef = rhs.hybridRuleDef;
		serviceInfoMap = rhs.serviceInfoMap;
		userGroup = rhs.userGroup;
		maxTimePromSpecMap = rhs.maxTimePromSpecMap;
		processFlag = rhs.processFlag;
		minimalValue = rhs.minimalValue;
		firstUpdateFlag = rhs.firstUpdateFlag;
		freebiePromotedRule = rhs.freebiePromotedRule;
		borderRoamInfoMap = rhs.borderRoamInfoMap;
		borderRoamType = rhs.borderRoamType;
		outRatingFec = rhs.outRatingFec;
		inOutFec = rhs.inOutFec;
		outInFec = rhs.outInFec;
		ratingOut = rhs.ratingOut;
		errorCode = rhs.errorCode;
		errorMessage = rhs.errorMessage;
		errFile = rhs.errFile;
		errModifyFlag = rhs.errModifyFlag;
		errInputtime = rhs.errInputtime;
		errFileDate = rhs.errFileDate;
		reserve1 = rhs.reserve1;
		reserve2 = rhs.reserve2;
		reserve3 = rhs.reserve3;
		reserve4 = rhs.reserve4;
		reserveFields = rhs.reserveFields;
		billDate = rhs.billDate;
		revenueCodeDtl = rhs.revenueCodeDtl;
		billingOut = rhs.billingOut;
		operatorRelaType = rhs.operatorRelaType;
		batchMessageNumber = rhs.batchMessageNumber;
		hybridRule = rhs.hybridRule;
		slaRule = rhs.slaRule;
		oriDeductTime = rhs.oriDeductTime;
		resCount = rhs.resCount;
		deductResCount = rhs.deductResCount;
		refundResCount = rhs.refundResCount;
		rgRule = rhs.rgRule;
		analyseAlarm = rhs.analyseAlarm;
		tableName = rhs.tableName;
		databaseName = rhs.databaseName;
		lateFlag = rhs.lateFlag;
		isFilter = rhs.isFilter;
		fcitype = rhs.fcitype;
		testCdrFlag = rhs.testCdrFlag;
		rgRuleList = rhs.rgRuleList;
		historyErrorMap = rhs.historyErrorMap;
		offpeakSwitchDate = rhs.offpeakSwitchDate;
		productValidDate = rhs.productValidDate;
		analyseStateInfo = rhs.analyseStateInfo;
		nearestInfoChangeMap = rhs.nearestInfoChangeMap;
		outputFlag = rhs.outputFlag;
		chargeRollbackFlag = rhs.chargeRollbackFlag;
		oppUserNumber = rhs.oppUserNumber;
		callRefnum = rhs.callRefnum;
		partialId = rhs.partialId;
		firstcdrStartTime = rhs.firstcdrStartTime;
		timeWindow = rhs.timeWindow;
		ocsMonitorCdr = rhs.ocsMonitorCdr;
		serviceKeyVal = rhs.serviceKeyVal;
		cdrQueryFlag = rhs.cdrQueryFlag;
		shareGruopIdForFreebie = rhs.shareGruopIdForFreebie;
		newCdrFlag = rhs.newCdrFlag;
		batchNo = rhs.batchNo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSubCommon rhs=(SSubCommon)rhs0;
		if(!ObjectUtils.equals(notifyType, rhs.notifyType)) return false;
		if(!ObjectUtils.equals(processType, rhs.processType)) return false;
		if(!ObjectUtils.equals(treatFlag, rhs.treatFlag)) return false;
		if(!ObjectUtils.equals(uFlag, rhs.uFlag)) return false;
		if(!ObjectUtils.equals(uCount, rhs.uCount)) return false;
		if(!ObjectUtils.equals(xdrOutPostfix, rhs.xdrOutPostfix)) return false;
		if(!ObjectUtils.equals(rsu, rhs.rsu)) return false;
		if(!ObjectUtils.equals(priorityLevel, rhs.priorityLevel)) return false;
		if(!ObjectUtils.equals(qosClassId, rhs.qosClassId)) return false;
		if(!ObjectUtils.equals(paymentType, rhs.paymentType)) return false;
		if(!ObjectUtils.equals(paymentMode, rhs.paymentMode)) return false;
		if(!ObjectUtils.equals(productValue, rhs.productValue)) return false;
		if(!ObjectUtils.equals(queryAccountType, rhs.queryAccountType)) return false;
		if(!ObjectUtils.equals(lacId, rhs.lacId)) return false;
		if(!ObjectUtils.equals(oppLacId, rhs.oppLacId)) return false;
		if(!ObjectUtils.equals(cellId, rhs.cellId)) return false;
		if(!ObjectUtils.equals(oppCellId, rhs.oppCellId)) return false;
		if(!ObjectUtils.equals(sessionStartTime, rhs.sessionStartTime)) return false;
		if(!ObjectUtils.equals(startTime, rhs.startTime)) return false;
		if(!ObjectUtils.equals(visitStartTime, rhs.visitStartTime)) return false;
		if(!ObjectUtils.equals(updateTime, rhs.updateTime)) return false;
		if(!ObjectUtils.equals(timeSegment, rhs.timeSegment)) return false;
		if(!ObjectUtils.equals(serviceCode, rhs.serviceCode)) return false;
		if(!ObjectUtils.equals(chargeFlag, rhs.chargeFlag)) return false;
		if(!ObjectUtils.equals(ccRequestNumber, rhs.ccRequestNumber)) return false;
		if(!ObjectUtils.equals(offPeakInfo, rhs.offPeakInfo)) return false;
		if(!ObjectUtils.equals(timeZone, rhs.timeZone)) return false;
		if(!ObjectUtils.equals(serviceKey, rhs.serviceKey)) return false;
		if(!ObjectUtils.equals(timeAddup, rhs.timeAddup)) return false;
		if(!ObjectUtils.equals(timeAddupSwitch, rhs.timeAddupSwitch)) return false;
		if(!ObjectUtils.equals(volumeAddup, rhs.volumeAddup)) return false;
		if(!ObjectUtils.equals(volumeAddupSwitch, rhs.volumeAddupSwitch)) return false;
		if(!ObjectUtils.equals(duration, rhs.duration)) return false;
		if(!ObjectUtils.equals(octect, rhs.octect)) return false;
		if(!ObjectUtils.equals(upVolume, rhs.upVolume)) return false;
		if(!ObjectUtils.equals(upVolumeSwitch, rhs.upVolumeSwitch)) return false;
		if(!ObjectUtils.equals(downVolume, rhs.downVolume)) return false;
		if(!ObjectUtils.equals(downVolumeSwitch, rhs.downVolumeSwitch)) return false;
		if(!ObjectUtils.equals(oppNumber, rhs.oppNumber)) return false;
		if(!ObjectUtils.equals(oriOppNumber, rhs.oriOppNumber)) return false;
		if(!ObjectUtils.equals(dialedNumber, rhs.dialedNumber)) return false;
		if(!ObjectUtils.equals(userNature, rhs.userNature)) return false;
		if(!ObjectUtils.equals(oppNature, rhs.oppNature)) return false;
		if(!ObjectUtils.equals(aNature, rhs.aNature)) return false;
		if(!ObjectUtils.equals(serviceType, rhs.serviceType)) return false;
		if(!ObjectUtils.equals(messageType, rhs.messageType)) return false;
		if(!ObjectUtils.equals(businessCode, rhs.businessCode)) return false;
		if(!ObjectUtils.equals(businessCodeGroup, rhs.businessCodeGroup)) return false;
		if(!ObjectUtils.equals(contentCode, rhs.contentCode)) return false;
		if(!ObjectUtils.equals(pointCost, rhs.pointCost)) return false;
		if(!ObjectUtils.equals(billFlag, rhs.billFlag)) return false;
		if(!ObjectUtils.equals(smscId, rhs.smscId)) return false;
		if(!ObjectUtils.equals(deductionFlag, rhs.deductionFlag)) return false;
		if(!ObjectUtils.equals(recordType, rhs.recordType)) return false;
		if(!ObjectUtils.equals(stdErrCode, rhs.stdErrCode)) return false;
		if(!ObjectUtils.equals(redoFlag, rhs.redoFlag)) return false;
		if(!ObjectUtils.equals(camelDestNum, rhs.camelDestNum)) return false;
		if(!ObjectUtils.equals(camelServiceLevel, rhs.camelServiceLevel)) return false;
		if(!ObjectUtils.equals(camelServiceKey, rhs.camelServiceKey)) return false;
		if(!ObjectUtils.equals(dftCallHandlingInd, rhs.dftCallHandlingInd)) return false;
		if(!ObjectUtils.equals(tapCamelInvoFee, rhs.tapCamelInvoFee)) return false;
		if(!ObjectUtils.equals(tapCamelVatAmt, rhs.tapCamelVatAmt)) return false;
		if(!ObjectUtils.equals(cdrCamelFlag, rhs.cdrCamelFlag)) return false;
		if(!ObjectUtils.equals(ratingFlag, rhs.ratingFlag)) return false;
		if(!ObjectUtils.equals(optimalChargeFlag, rhs.optimalChargeFlag)) return false;
		if(!ObjectUtils.equals(oriRatingFlag, rhs.oriRatingFlag)) return false;
		if(!ObjectUtils.equals(freeChargeType, rhs.freeChargeType)) return false;
		if(!ObjectUtils.equals(authType, rhs.authType)) return false;
		if(!ObjectUtils.equals(rateStrategy, rhs.rateStrategy)) return false;
		if(!ObjectUtils.equals(rateBudgetRes, rhs.rateBudgetRes)) return false;
		if(!ObjectUtils.equals(blackListAlert, rhs.blackListAlert)) return false;
		if(!ObjectUtils.equals(conditionId, rhs.conditionId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(billIndicate, rhs.billIndicate)) return false;
		if(!ObjectUtils.equals(apartOppNumber, rhs.apartOppNumber)) return false;
		if(!ObjectUtils.equals(roamType, rhs.roamType)) return false;
		if(!ObjectUtils.equals(accessType, rhs.accessType)) return false;
		if(!ObjectUtils.equals(spRelaType, rhs.spRelaType)) return false;
		if(!ObjectUtils.equals(oppConditionId, rhs.oppConditionId)) return false;
		if(!ObjectUtils.equals(cdrOnlineFlag, rhs.cdrOnlineFlag)) return false;
		if(!ObjectUtils.equals(backupDate, rhs.backupDate)) return false;
		if(!ObjectUtils.equals(cdrDealFlag, rhs.cdrDealFlag)) return false;
		if(!ObjectUtils.equals(userProperty, rhs.userProperty)) return false;
		if(!ObjectUtils.equals(oppProperty, rhs.oppProperty)) return false;
		if(!ObjectUtils.equals(spCode, rhs.spCode)) return false;
		if(!ObjectUtils.equals(groupType, rhs.groupType)) return false;
		if(!ObjectUtils.equals(netcallFlag, rhs.netcallFlag)) return false;
		if(!ObjectUtils.equals(oppNumberType, rhs.oppNumberType)) return false;
		if(!ObjectUtils.equals(oppCountryId, rhs.oppCountryId)) return false;
		if(!ObjectUtils.equals(oppCountryGroup, rhs.oppCountryGroup)) return false;
		if(!ObjectUtils.equals(userBaseInfo, rhs.userBaseInfo)) return false;
		if(!ObjectUtils.equals(rateProdId, rhs.rateProdId)) return false;
		if(!ObjectUtils.equals(oppRateMainProdId, rhs.oppRateMainProdId)) return false;
		if(!ObjectUtils.equals(rateProdBillCycle, rhs.rateProdBillCycle)) return false;
		if(!ObjectUtils.equals(rateProdRatingRes, rhs.rateProdRatingRes)) return false;
		if(!ObjectUtils.equals(sysRatingRes, rhs.sysRatingRes)) return false;
		if(!ObjectUtils.equals(rateProdQuotaRes, rhs.rateProdQuotaRes)) return false;
		if(!ObjectUtils.equals(sysQuotaRes, rhs.sysQuotaRes)) return false;
		if(!ObjectUtils.equals(ratingRes, rhs.ratingRes)) return false;
		if(!ObjectUtils.equals(rateProdZone, rhs.rateProdZone)) return false;
		if(!ObjectUtils.equals(rateProdZoneAlertFlag, rhs.rateProdZoneAlertFlag)) return false;
		if(!ObjectUtils.equals(taxRule, rhs.taxRule)) return false;
		if(!ObjectUtils.equals(accumulateRule, rhs.accumulateRule)) return false;
		if(!ObjectUtils.equals(propertyValMin, rhs.propertyValMin)) return false;
		if(!ObjectUtils.equals(rejectCallAlarmMap, rhs.rejectCallAlarmMap)) return false;
		if(!ObjectUtils.equals(accumulateAlarmList, rhs.accumulateAlarmList)) return false;
		if(!ObjectUtils.equals(afterMakeCallAlarmRule, rhs.afterMakeCallAlarmRule)) return false;
		if(!ObjectUtils.equals(beforeMakeCallAlarmRule, rhs.beforeMakeCallAlarmRule)) return false;
		if(!ObjectUtils.equals(groupAcctPayment, rhs.groupAcctPayment)) return false;
		if(!ObjectUtils.equals(groupFreebiePayment, rhs.groupFreebiePayment)) return false;
		if(!ObjectUtils.equals(capMax, rhs.capMax)) return false;
		if(!ObjectUtils.equals(groupBudget, rhs.groupBudget)) return false;
		if(!ObjectUtils.equals(groupRelationType, rhs.groupRelationType)) return false;
		if(!ObjectUtils.equals(groupSpecShare, rhs.groupSpecShare)) return false;
		if(!ObjectUtils.equals(productExpireDate, rhs.productExpireDate)) return false;
		if(!ObjectUtils.equals(productRedirectionDate, rhs.productRedirectionDate)) return false;
		if(!ObjectUtils.equals(cellCode, rhs.cellCode)) return false;
		if(!ObjectUtils.equals(oppCellCode, rhs.oppCellCode)) return false;
		if(!ObjectUtils.equals(rateProdPricePlan, rhs.rateProdPricePlan)) return false;
		if(!ObjectUtils.equals(hybridRuleDef, rhs.hybridRuleDef)) return false;
		if(!ObjectUtils.equals(serviceInfoMap, rhs.serviceInfoMap)) return false;
		if(!ObjectUtils.equals(userGroup, rhs.userGroup)) return false;
		if(!ObjectUtils.equals(maxTimePromSpecMap, rhs.maxTimePromSpecMap)) return false;
		if(!ObjectUtils.equals(processFlag, rhs.processFlag)) return false;
		if(!ObjectUtils.equals(minimalValue, rhs.minimalValue)) return false;
		if(!ObjectUtils.equals(firstUpdateFlag, rhs.firstUpdateFlag)) return false;
		if(!ObjectUtils.equals(freebiePromotedRule, rhs.freebiePromotedRule)) return false;
		if(!ObjectUtils.equals(borderRoamInfoMap, rhs.borderRoamInfoMap)) return false;
		if(!ObjectUtils.equals(borderRoamType, rhs.borderRoamType)) return false;
		if(!ObjectUtils.equals(outRatingFec, rhs.outRatingFec)) return false;
		if(!ObjectUtils.equals(inOutFec, rhs.inOutFec)) return false;
		if(!ObjectUtils.equals(outInFec, rhs.outInFec)) return false;
		if(!ObjectUtils.equals(ratingOut, rhs.ratingOut)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(errorMessage, rhs.errorMessage)) return false;
		if(!ObjectUtils.equals(errFile, rhs.errFile)) return false;
		if(!ObjectUtils.equals(errModifyFlag, rhs.errModifyFlag)) return false;
		if(!ObjectUtils.equals(errInputtime, rhs.errInputtime)) return false;
		if(!ObjectUtils.equals(errFileDate, rhs.errFileDate)) return false;
		if(!ObjectUtils.equals(reserve1, rhs.reserve1)) return false;
		if(!ObjectUtils.equals(reserve2, rhs.reserve2)) return false;
		if(!ObjectUtils.equals(reserve3, rhs.reserve3)) return false;
		if(!ObjectUtils.equals(reserve4, rhs.reserve4)) return false;
		if(!ObjectUtils.equals(reserveFields, rhs.reserveFields)) return false;
		if(!ObjectUtils.equals(billDate, rhs.billDate)) return false;
		if(!ObjectUtils.equals(revenueCodeDtl, rhs.revenueCodeDtl)) return false;
		if(!ObjectUtils.equals(billingOut, rhs.billingOut)) return false;
		if(!ObjectUtils.equals(operatorRelaType, rhs.operatorRelaType)) return false;
		if(!ObjectUtils.equals(batchMessageNumber, rhs.batchMessageNumber)) return false;
		if(!ObjectUtils.equals(hybridRule, rhs.hybridRule)) return false;
		if(!ObjectUtils.equals(slaRule, rhs.slaRule)) return false;
		if(!ObjectUtils.equals(oriDeductTime, rhs.oriDeductTime)) return false;
		if(!ObjectUtils.equals(resCount, rhs.resCount)) return false;
		if(!ObjectUtils.equals(deductResCount, rhs.deductResCount)) return false;
		if(!ObjectUtils.equals(refundResCount, rhs.refundResCount)) return false;
		if(!ObjectUtils.equals(rgRule, rhs.rgRule)) return false;
		if(!ObjectUtils.equals(analyseAlarm, rhs.analyseAlarm)) return false;
		if(!ObjectUtils.equals(tableName, rhs.tableName)) return false;
		if(!ObjectUtils.equals(databaseName, rhs.databaseName)) return false;
		if(!ObjectUtils.equals(lateFlag, rhs.lateFlag)) return false;
		if(!ObjectUtils.equals(isFilter, rhs.isFilter)) return false;
		if(!ObjectUtils.equals(fcitype, rhs.fcitype)) return false;
		if(!ObjectUtils.equals(testCdrFlag, rhs.testCdrFlag)) return false;
		if(!ObjectUtils.equals(rgRuleList, rhs.rgRuleList)) return false;
		if(!ObjectUtils.equals(historyErrorMap, rhs.historyErrorMap)) return false;
		if(!ObjectUtils.equals(offpeakSwitchDate, rhs.offpeakSwitchDate)) return false;
		if(!ObjectUtils.equals(productValidDate, rhs.productValidDate)) return false;
		if(!ObjectUtils.equals(analyseStateInfo, rhs.analyseStateInfo)) return false;
		if(!ObjectUtils.equals(nearestInfoChangeMap, rhs.nearestInfoChangeMap)) return false;
		if(!ObjectUtils.equals(outputFlag, rhs.outputFlag)) return false;
		if(!ObjectUtils.equals(chargeRollbackFlag, rhs.chargeRollbackFlag)) return false;
		if(!ObjectUtils.equals(oppUserNumber, rhs.oppUserNumber)) return false;
		if(!ObjectUtils.equals(callRefnum, rhs.callRefnum)) return false;
		if(!ObjectUtils.equals(partialId, rhs.partialId)) return false;
		if(!ObjectUtils.equals(firstcdrStartTime, rhs.firstcdrStartTime)) return false;
		if(!ObjectUtils.equals(timeWindow, rhs.timeWindow)) return false;
		if(!ObjectUtils.equals(ocsMonitorCdr, rhs.ocsMonitorCdr)) return false;
		if(!ObjectUtils.equals(serviceKeyVal, rhs.serviceKeyVal)) return false;
		if(!ObjectUtils.equals(cdrQueryFlag, rhs.cdrQueryFlag)) return false;
		if(!ObjectUtils.equals(shareGruopIdForFreebie, rhs.shareGruopIdForFreebie)) return false;
		if(!ObjectUtils.equals(newCdrFlag, rhs.newCdrFlag)) return false;
		if(!ObjectUtils.equals(batchNo, rhs.batchNo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyType)
		.append(processType)
		.append(treatFlag)
		.append(uFlag)
		.append(uCount)
		.append(xdrOutPostfix)
		.append(rsu)
		.append(priorityLevel)
		.append(qosClassId)
		.append(paymentType)
		.append(paymentMode)
		.append(productValue)
		.append(queryAccountType)
		.append(lacId)
		.append(oppLacId)
		.append(cellId)
		.append(oppCellId)
		.append(sessionStartTime)
		.append(startTime)
		.append(visitStartTime)
		.append(updateTime)
		.append(timeSegment)
		.append(serviceCode)
		.append(chargeFlag)
		.append(ccRequestNumber)
		.append(offPeakInfo)
		.append(timeZone)
		.append(serviceKey)
		.append(timeAddup)
		.append(timeAddupSwitch)
		.append(volumeAddup)
		.append(volumeAddupSwitch)
		.append(duration)
		.append(octect)
		.append(upVolume)
		.append(upVolumeSwitch)
		.append(downVolume)
		.append(downVolumeSwitch)
		.append(oppNumber)
		.append(oriOppNumber)
		.append(dialedNumber)
		.append(userNature)
		.append(oppNature)
		.append(aNature)
		.append(serviceType)
		.append(messageType)
		.append(businessCode)
		.append(businessCodeGroup)
		.append(contentCode)
		.append(pointCost)
		.append(billFlag)
		.append(smscId)
		.append(deductionFlag)
		.append(recordType)
		.append(stdErrCode)
		.append(redoFlag)
		.append(camelDestNum)
		.append(camelServiceLevel)
		.append(camelServiceKey)
		.append(dftCallHandlingInd)
		.append(tapCamelInvoFee)
		.append(tapCamelVatAmt)
		.append(cdrCamelFlag)
		.append(ratingFlag)
		.append(optimalChargeFlag)
		.append(oriRatingFlag)
		.append(freeChargeType)
		.append(authType)
		.append(rateStrategy)
		.append(rateBudgetRes)
		.append(blackListAlert)
		.append(conditionId)
		.append(productId)
		.append(billIndicate)
		.append(apartOppNumber)
		.append(roamType)
		.append(accessType)
		.append(spRelaType)
		.append(oppConditionId)
		.append(cdrOnlineFlag)
		.append(backupDate)
		.append(cdrDealFlag)
		.append(userProperty)
		.append(oppProperty)
		.append(spCode)
		.append(groupType)
		.append(netcallFlag)
		.append(oppNumberType)
		.append(oppCountryId)
		.append(oppCountryGroup)
		.append(userBaseInfo)
		.append(rateProdId)
		.append(oppRateMainProdId)
		.append(rateProdBillCycle)
		.append(rateProdRatingRes)
		.append(sysRatingRes)
		.append(rateProdQuotaRes)
		.append(sysQuotaRes)
		.append(ratingRes)
		.append(rateProdZone)
		.append(rateProdZoneAlertFlag)
		.append(taxRule)
		.append(accumulateRule)
		.append(propertyValMin)
		.append(rejectCallAlarmMap)
		.append(accumulateAlarmList)
		.append(afterMakeCallAlarmRule)
		.append(beforeMakeCallAlarmRule)
		.append(groupAcctPayment)
		.append(groupFreebiePayment)
		.append(capMax)
		.append(groupBudget)
		.append(groupRelationType)
		.append(groupSpecShare)
		.append(productExpireDate)
		.append(productRedirectionDate)
		.append(cellCode)
		.append(oppCellCode)
		.append(rateProdPricePlan)
		.append(hybridRuleDef)
		.append(serviceInfoMap)
		.append(userGroup)
		.append(maxTimePromSpecMap)
		.append(processFlag)
		.append(minimalValue)
		.append(firstUpdateFlag)
		.append(freebiePromotedRule)
		.append(borderRoamInfoMap)
		.append(borderRoamType)
		.append(outRatingFec)
		.append(inOutFec)
		.append(outInFec)
		.append(ratingOut)
		.append(errorCode)
		.append(errorMessage)
		.append(errFile)
		.append(errModifyFlag)
		.append(errInputtime)
		.append(errFileDate)
		.append(reserve1)
		.append(reserve2)
		.append(reserve3)
		.append(reserve4)
		.append(reserveFields)
		.append(billDate)
		.append(revenueCodeDtl)
		.append(billingOut)
		.append(operatorRelaType)
		.append(batchMessageNumber)
		.append(hybridRule)
		.append(slaRule)
		.append(oriDeductTime)
		.append(resCount)
		.append(deductResCount)
		.append(refundResCount)
		.append(rgRule)
		.append(analyseAlarm)
		.append(tableName)
		.append(databaseName)
		.append(lateFlag)
		.append(isFilter)
		.append(fcitype)
		.append(testCdrFlag)
		.append(rgRuleList)
		.append(historyErrorMap)
		.append(offpeakSwitchDate)
		.append(productValidDate)
		.append(analyseStateInfo)
		.append(nearestInfoChangeMap)
		.append(outputFlag)
		.append(chargeRollbackFlag)
		.append(oppUserNumber)
		.append(callRefnum)
		.append(partialId)
		.append(firstcdrStartTime)
		.append(timeWindow)
		.append(ocsMonitorCdr)
		.append(serviceKeyVal)
		.append(cdrQueryFlag)
		.append(shareGruopIdForFreebie)
		.append(newCdrFlag)
		.append(batchNo)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(182);
public static final long BITS_ALL_MARKER = 0x20000000000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSubCommon";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "NOTIFY_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PROCESS_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TREAT_FLAG", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "U_FLAG", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "U_COUNT", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "XDR_OUT_POSTFIX", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RSU", 6, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRIORITY_LEVEL", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "QOS_CLASS_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PAYMENT_TYPE", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PAYMENT_MODE", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRODUCT_VALUE", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "QUERY_ACCOUNT_TYPE", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "LAC_ID", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_LAC_ID", 14, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CELL_ID", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_CELL_ID", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SESSION_START_TIME", 17, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "START_TIME", 18, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "VISIT_START_TIME", 19, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "UPDATE_TIME", 20, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TIME_SEGMENT", 21, SOffPeak.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SERVICE_CODE", 22, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CHARGE_FLAG", 23, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CC_REQUEST_NUMBER", 24, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OFF_PEAK_INFO", 25, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TIME_ZONE", 26, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SERVICE_KEY", 27, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TIME_ADDUP", 28, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TIME_ADDUP_SWITCH", 29, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "VOLUME_ADDUP", 30, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "VOLUME_ADDUP_SWITCH", 31, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DURATION", 32, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OCTECT", 33, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "UP_VOLUME", 34, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "UP_VOLUME_SWITCH", 35, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DOWN_VOLUME", 36, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DOWN_VOLUME_SWITCH", 37, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_NUMBER", 38, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ORI_OPP_NUMBER", 39, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DIALED_NUMBER", 40, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "USER_NATURE", 41, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_NATURE", 42, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "A_NATURE", 43, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SERVICE_TYPE", 44, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "MESSAGE_TYPE", 45, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BUSINESS_CODE", 46, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BUSINESS_CODE_GROUP", 47, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CONTENT_CODE", 48, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "POINT_COST", 49, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BILL_FLAG", 50, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SMSC_ID", 51, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DEDUCTION_FLAG", 52, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RECORD_TYPE", 53, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "STD_ERR_CODE", 54, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "REDO_FLAG", 55, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CAMEL_DEST_NUM", 56, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CAMEL_SERVICE_LEVEL", 57, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CAMEL_SERVICE_KEY", 58, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DFT_CALL_HANDLING_IND", 59, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TAP_CAMEL_INVO_FEE", 60, STapCamelInvoFee.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TAP_CAMEL_VAT_AMT", 61, STapCamelVatAmt.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CDR_CAMEL_FLAG", 62, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATING_FLAG", 63, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPTIMAL_CHARGE_FLAG", 64, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ORI_RATING_FLAG", 65, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "FREE_CHARGE_TYPE", 66, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "AUTH_TYPE", 67, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_STRATEGY", 68, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_BUDGET_RES", 69, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BLACK_LIST_ALERT", 70, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CONDITION_ID", 71, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRODUCT_ID", 72, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BILL_INDICATE", 73, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "APART_OPP_NUMBER", 74, SApartOppNumber.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ROAM_TYPE", 75, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ACCESS_TYPE", 76, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SP_RELA_TYPE", 77, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_CONDITION_ID", 78, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CDR_ONLINE_FLAG", 79, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BACKUP_DATE", 80, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CDR_DEAL_FLAG", 81, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "USER_PROPERTY", 82, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_PROPERTY", 83, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SP_CODE", 84, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_TYPE", 85, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "NETCALL_FLAG", 86, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_NUMBER_TYPE", 87, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_COUNTRY_ID", 88, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_COUNTRY_GROUP", 89, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "USER_BASE_INFO", 90, SUserInfoBase.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_ID", 91, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_RATE_MAIN_PROD_ID", 92, SRateProdId.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_BILL_CYCLE", 93, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_RATING_RES", 94, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SYS_RATING_RES", 95, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_QUOTA_RES", 96, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SYS_QUOTA_RES", 97, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATING_RES", 98, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_ZONE", 99, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_ZONE_ALERT_FLAG", 100, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TAX_RULE", 101, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ACCUMULATE_RULE", 102, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PROPERTY_VAL_MIN", 103, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "REJECT_CALL_ALARM_MAP", 104, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ACCUMULATE_ALARM_LIST", 105, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "AFTER_MAKE_CALL_ALARM_RULE", 106, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BEFORE_MAKE_CALL_ALARM_RULE", 107, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_ACCT_PAYMENT", 108, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_FREEBIE_PAYMENT", 109, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CAP_MAX", 110, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_BUDGET", 111, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_RELATION_TYPE", 112, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "GROUP_SPEC_SHARE", 113, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRODUCT_EXPIRE_DATE", 114, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRODUCT_REDIRECTION_DATE", 115, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CELL_CODE", 116, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_CELL_CODE", 117, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATE_PROD_PRICE_PLAN", 118, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "HYBRID_RULE_DEF", 119, SHybirdRuleDefine.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SERVICE_INFO_MAP", 120, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "USER_GROUP", 121, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "MAX_TIME_PROM_SPEC_MAP", 122, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PROCESS_FLAG", 123, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "MINIMAL_VALUE", 124, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "FIRST_UPDATE_FLAG", 125, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "FREEBIE_PROMOTED_RULE", 126, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BORDER_ROAM_INFO_MAP", 127, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BORDER_ROAM_TYPE", 128, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OUT_RATING_FEC", 129, SOutRatingFecXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "IN_OUT_FEC", 130, SInOutFecXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OUT_IN_FEC", 131, SOutInFecXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RATING_OUT", 132, SOutRatingXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERROR_CODE", 133, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERROR_MESSAGE", 134, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERR_FILE", 135, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERR_MODIFY_FLAG", 136, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERR_INPUTTIME", 137, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ERR_FILE_DATE", 138, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RESERVE1", 139, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RESERVE2", 140, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RESERVE3", 141, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RESERVE4", 142, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RESERVE_FIELDS", 143, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BILL_DATE", 144, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "REVENUE_CODE_DTL", 145, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BILLING_OUT", 146, SOutBillingXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPERATOR_RELA_TYPE", 147, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BATCH_MESSAGE_NUMBER", 148, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "HYBRID_RULE", 149, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SLA_RULE", 150, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ORI_DEDUCT_TIME", 151, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RES_COUNT", 152, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DEDUCT_RES_COUNT", 153, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "REFUND_RES_COUNT", 154, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RG_RULE", 155, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ANALYSE_ALARM", 156, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TABLE_NAME", 157, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "DATABASE_NAME", 158, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "LATE_FLAG", 159, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "IS_FILTER", 160, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "FCITYPE", 161, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TEST_CDR_FLAG", 162, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "RG_RULE_LIST", 163, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "HISTORY_ERROR_MAP", 164, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OFFPEAK_SWITCH_DATE", 165, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PRODUCT_VALID_DATE", 166, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "ANALYSE_STATE_INFO", 167, SAnalyseStateInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "NEAREST_INFO_CHANGE_MAP", 168, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OUTPUT_FLAG", 169, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CHARGE_ROLLBACK_FLAG", 170, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OPP_USER_NUMBER", 171, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CALL_REFNUM", 172, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "PARTIAL_ID", 173, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "FIRSTCDR_START_TIME", 174, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "TIME_WINDOW", 175, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "OCS_MONITOR_CDR", 176, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SERVICE_KEY_VAL", 177, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "CDR_QUERY_FLAG", 178, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "SHARE_GRUOP_ID_FOR_FREEBIE", 179, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "NEW_CDR_FLAG", 180, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubCommon.class, "BATCH_NO", 181, long.class));
}

}