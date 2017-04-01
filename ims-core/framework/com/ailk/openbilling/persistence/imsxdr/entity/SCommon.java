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
@XmlType(propOrder={"companyCode","fileCreateDate","createTime","sequenceNo","sessionId","refundSessionId","originalFile","stopCause","chaegeupSeq","sendPriority","channelId","stopTime","messageSize","sendState","finalState","additionalInfo","serviceId","drType","deviceType","roamingOperatorId","roamingOperatorCode","roamingOperatorGroup","roamingOperatorNet","roamingCountryId","roamingCountryGroup","mnsType","imei","oriCharge","bakOriCharge","oriUserNumber","userNumber","connectCalledNumber","consumeUserNumber","actionType","imsi","oriImsi","bakReguideUserNumber","freeresQuery","cdrReceiveTime","numberClass","numberSubclass","otherInfo1","otherInfo2","otherInfo3","otherInfo4","otherInfo5","reserveFields","vplmn1","vplmn2","vplmn3","visitCountyCode","iddType","camelFlag","flhFlag","continueFlag","userOperatorId","oppOperatorId","numberIdentityAttr","oppHplmn1","oppHplmn2","oppHplmn3","oppVplmn1","oppVplmn2","oppVplmn3","originHost","originRealm","destinationRealm","authApplicationId","destinationHost","userName","originStateId","productName","hostIpAddress","vendorId1","gsmSpecInfo","gprsSpecInfo","smsSpecInfo","mmsSpecInfo","ismpSpecInfo","gsSpecInfo","uSeparateFlag","handset","switchFlag","switchTime","commercialStatus","flowId","deductAcctFlag","initThreshold","sequenceId","confirmTag","drTypeDesc","controlTagForModule"})
@Sdl(module="MXdr")
public class SCommon extends CsdlStructObject implements IComplexEntity{

	public final static String COL_COMPANY_CODE = "COMPANY_CODE";
	public final static String COL_FILE_CREATE_DATE = "FILE_CREATE_DATE";
	public final static String COL_CREATE_TIME = "CREATE_TIME";
	public final static String COL_SEQUENCE_NO = "SEQUENCE_NO";
	public final static String COL_SESSION_ID = "SESSION_ID";
	public final static String COL_REFUND_SESSION_ID = "REFUND_SESSION_ID";
	public final static String COL_ORIGINAL_FILE = "ORIGINAL_FILE";
	public final static String COL_STOP_CAUSE = "STOP_CAUSE";
	public final static String COL_CHAEGEUP_SEQ = "CHAEGEUP_SEQ";
	public final static String COL_SEND_PRIORITY = "SEND_PRIORITY";
	public final static String COL_CHANNEL_ID = "CHANNEL_ID";
	public final static String COL_STOP_TIME = "STOP_TIME";
	public final static String COL_MESSAGE_SIZE = "MESSAGE_SIZE";
	public final static String COL_SEND_STATE = "SEND_STATE";
	public final static String COL_FINAL_STATE = "FINAL_STATE";
	public final static String COL_ADDITIONAL_INFO = "ADDITIONAL_INFO";
	public final static String COL_SERVICE_ID = "SERVICE_ID";
	public final static String COL_DR_TYPE = "DR_TYPE";
	public final static String COL_DEVICE_TYPE = "DEVICE_TYPE";
	public final static String COL_ROAMING_OPERATOR_ID = "ROAMING_OPERATOR_ID";
	public final static String COL_ROAMING_OPERATOR_CODE = "ROAMING_OPERATOR_CODE";
	public final static String COL_ROAMING_OPERATOR_GROUP = "ROAMING_OPERATOR_GROUP";
	public final static String COL_ROAMING_OPERATOR_NET = "ROAMING_OPERATOR_NET";
	public final static String COL_ROAMING_COUNTRY_ID = "ROAMING_COUNTRY_ID";
	public final static String COL_ROAMING_COUNTRY_GROUP = "ROAMING_COUNTRY_GROUP";
	public final static String COL_MNS_TYPE = "MNS_TYPE";
	public final static String COL_IMEI = "IMEI";
	public final static String COL_ORI_CHARGE = "ORI_CHARGE";
	public final static String COL_BAK_ORI_CHARGE = "BAK_ORI_CHARGE";
	public final static String COL_ORI_USER_NUMBER = "ORI_USER_NUMBER";
	public final static String COL_USER_NUMBER = "USER_NUMBER";
	public final static String COL_CONNECT_CALLED_NUMBER = "CONNECT_CALLED_NUMBER";
	public final static String COL_CONSUME_USER_NUMBER = "CONSUME_USER_NUMBER";
	public final static String COL_ACTION_TYPE = "ACTION_TYPE";
	public final static String COL_IMSI = "IMSI";
	public final static String COL_ORI_IMSI = "ORI_IMSI";
	public final static String COL_BAK_REGUIDE_USER_NUMBER = "BAK_REGUIDE_USER_NUMBER";
	public final static String COL_FREERES_QUERY = "FREERES_QUERY";
	public final static String COL_CDR_RECEIVE_TIME = "CDR_RECEIVE_TIME";
	public final static String COL_NUMBER_CLASS = "NUMBER_CLASS";
	public final static String COL_NUMBER_SUBCLASS = "NUMBER_SUBCLASS";
	public final static String COL_OTHER_INFO1 = "OTHER_INFO1";
	public final static String COL_OTHER_INFO2 = "OTHER_INFO2";
	public final static String COL_OTHER_INFO3 = "OTHER_INFO3";
	public final static String COL_OTHER_INFO4 = "OTHER_INFO4";
	public final static String COL_OTHER_INFO5 = "OTHER_INFO5";
	public final static String COL_RESERVE_FIELDS = "RESERVE_FIELDS";
	public final static String COL_VPLMN1 = "VPLMN1";
	public final static String COL_VPLMN2 = "VPLMN2";
	public final static String COL_VPLMN3 = "VPLMN3";
	public final static String COL_VISIT_COUNTY_CODE = "VISIT_COUNTY_CODE";
	public final static String COL_IDD_TYPE = "IDD_TYPE";
	public final static String COL_CAMEL_FLAG = "CAMEL_FLAG";
	public final static String COL_FLH_FLAG = "FLH_FLAG";
	public final static String COL_CONTINUE_FLAG = "CONTINUE_FLAG";
	public final static String COL_USER_OPERATOR_ID = "USER_OPERATOR_ID";
	public final static String COL_OPP_OPERATOR_ID = "OPP_OPERATOR_ID";
	public final static String COL_NUMBER_IDENTITY_ATTR = "NUMBER_IDENTITY_ATTR";
	public final static String COL_OPP_HPLMN1 = "OPP_HPLMN1";
	public final static String COL_OPP_HPLMN2 = "OPP_HPLMN2";
	public final static String COL_OPP_HPLMN3 = "OPP_HPLMN3";
	public final static String COL_OPP_VPLMN1 = "OPP_VPLMN1";
	public final static String COL_OPP_VPLMN2 = "OPP_VPLMN2";
	public final static String COL_OPP_VPLMN3 = "OPP_VPLMN3";
	public final static String COL_ORIGIN_HOST = "ORIGIN_HOST";
	public final static String COL_ORIGIN_REALM = "ORIGIN_REALM";
	public final static String COL_DESTINATION_REALM = "DESTINATION_REALM";
	public final static String COL_AUTH_APPLICATION_ID = "AUTH_APPLICATION_ID";
	public final static String COL_DESTINATION_HOST = "DESTINATION_HOST";
	public final static String COL_USER_NAME = "USER_NAME";
	public final static String COL_ORIGIN_STATE_ID = "ORIGIN_STATE_ID";
	public final static String COL_PRODUCT_NAME = "PRODUCT_NAME";
	public final static String COL_HOST_IP_ADDRESS = "HOST_IP_ADDRESS";
	public final static String COL_VENDOR_ID1 = "VENDOR_ID1";
	public final static String COL_GSM_SPEC_INFO = "GSM_SPEC_INFO";
	public final static String COL_GPRS_SPEC_INFO = "GPRS_SPEC_INFO";
	public final static String COL_SMS_SPEC_INFO = "SMS_SPEC_INFO";
	public final static String COL_MMS_SPEC_INFO = "MMS_SPEC_INFO";
	public final static String COL_ISMP_SPEC_INFO = "ISMP_SPEC_INFO";
	public final static String COL_GS_SPEC_INFO = "GS_SPEC_INFO";
	public final static String COL_U_SEPARATE_FLAG = "U_SEPARATE_FLAG";
	public final static String COL_HANDSET = "HANDSET";
	public final static String COL_SWITCH_FLAG = "SWITCH_FLAG";
	public final static String COL_SWITCH_TIME = "SWITCH_TIME";
	public final static String COL_COMMERCIAL_STATUS = "COMMERCIAL_STATUS";
	public final static String COL_FLOW_ID = "FLOW_ID";
	public final static String COL_DEDUCT_ACCT_FLAG = "DEDUCT_ACCT_FLAG";
	public final static String COL_INIT_THRESHOLD = "INIT_THRESHOLD";
	public final static String COL_SEQUENCE_ID = "SEQUENCE_ID";
	public final static String COL_CONFIRM_TAG = "CONFIRM_TAG";
	public final static String COL_DR_TYPE_DESC = "DR_TYPE_DESC";
	public final static String COL_CONTROL_TAG_FOR_MODULE = "CONTROL_TAG_FOR_MODULE";
	public final static int IDX_COMPANY_CODE = 0;
	public final static int IDX_FILE_CREATE_DATE = 1;
	public final static int IDX_CREATE_TIME = 2;
	public final static int IDX_SEQUENCE_NO = 3;
	public final static int IDX_SESSION_ID = 4;
	public final static int IDX_REFUND_SESSION_ID = 5;
	public final static int IDX_ORIGINAL_FILE = 6;
	public final static int IDX_STOP_CAUSE = 7;
	public final static int IDX_CHAEGEUP_SEQ = 8;
	public final static int IDX_SEND_PRIORITY = 9;
	public final static int IDX_CHANNEL_ID = 10;
	public final static int IDX_STOP_TIME = 11;
	public final static int IDX_MESSAGE_SIZE = 12;
	public final static int IDX_SEND_STATE = 13;
	public final static int IDX_FINAL_STATE = 14;
	public final static int IDX_ADDITIONAL_INFO = 15;
	public final static int IDX_SERVICE_ID = 16;
	public final static int IDX_DR_TYPE = 17;
	public final static int IDX_DEVICE_TYPE = 18;
	public final static int IDX_ROAMING_OPERATOR_ID = 19;
	public final static int IDX_ROAMING_OPERATOR_CODE = 20;
	public final static int IDX_ROAMING_OPERATOR_GROUP = 21;
	public final static int IDX_ROAMING_OPERATOR_NET = 22;
	public final static int IDX_ROAMING_COUNTRY_ID = 23;
	public final static int IDX_ROAMING_COUNTRY_GROUP = 24;
	public final static int IDX_MNS_TYPE = 25;
	public final static int IDX_IMEI = 26;
	public final static int IDX_ORI_CHARGE = 27;
	public final static int IDX_BAK_ORI_CHARGE = 28;
	public final static int IDX_ORI_USER_NUMBER = 29;
	public final static int IDX_USER_NUMBER = 30;
	public final static int IDX_CONNECT_CALLED_NUMBER = 31;
	public final static int IDX_CONSUME_USER_NUMBER = 32;
	public final static int IDX_ACTION_TYPE = 33;
	public final static int IDX_IMSI = 34;
	public final static int IDX_ORI_IMSI = 35;
	public final static int IDX_BAK_REGUIDE_USER_NUMBER = 36;
	public final static int IDX_FREERES_QUERY = 37;
	public final static int IDX_CDR_RECEIVE_TIME = 38;
	public final static int IDX_NUMBER_CLASS = 39;
	public final static int IDX_NUMBER_SUBCLASS = 40;
	public final static int IDX_OTHER_INFO1 = 41;
	public final static int IDX_OTHER_INFO2 = 42;
	public final static int IDX_OTHER_INFO3 = 43;
	public final static int IDX_OTHER_INFO4 = 44;
	public final static int IDX_OTHER_INFO5 = 45;
	public final static int IDX_RESERVE_FIELDS = 46;
	public final static int IDX_VPLMN1 = 47;
	public final static int IDX_VPLMN2 = 48;
	public final static int IDX_VPLMN3 = 49;
	public final static int IDX_VISIT_COUNTY_CODE = 50;
	public final static int IDX_IDD_TYPE = 51;
	public final static int IDX_CAMEL_FLAG = 52;
	public final static int IDX_FLH_FLAG = 53;
	public final static int IDX_CONTINUE_FLAG = 54;
	public final static int IDX_USER_OPERATOR_ID = 55;
	public final static int IDX_OPP_OPERATOR_ID = 56;
	public final static int IDX_NUMBER_IDENTITY_ATTR = 57;
	public final static int IDX_OPP_HPLMN1 = 58;
	public final static int IDX_OPP_HPLMN2 = 59;
	public final static int IDX_OPP_HPLMN3 = 60;
	public final static int IDX_OPP_VPLMN1 = 61;
	public final static int IDX_OPP_VPLMN2 = 62;
	public final static int IDX_OPP_VPLMN3 = 63;
	public final static int IDX_ORIGIN_HOST = 64;
	public final static int IDX_ORIGIN_REALM = 65;
	public final static int IDX_DESTINATION_REALM = 66;
	public final static int IDX_AUTH_APPLICATION_ID = 67;
	public final static int IDX_DESTINATION_HOST = 68;
	public final static int IDX_USER_NAME = 69;
	public final static int IDX_ORIGIN_STATE_ID = 70;
	public final static int IDX_PRODUCT_NAME = 71;
	public final static int IDX_HOST_IP_ADDRESS = 72;
	public final static int IDX_VENDOR_ID1 = 73;
	public final static int IDX_GSM_SPEC_INFO = 74;
	public final static int IDX_GPRS_SPEC_INFO = 75;
	public final static int IDX_SMS_SPEC_INFO = 76;
	public final static int IDX_MMS_SPEC_INFO = 77;
	public final static int IDX_ISMP_SPEC_INFO = 78;
	public final static int IDX_GS_SPEC_INFO = 79;
	public final static int IDX_U_SEPARATE_FLAG = 80;
	public final static int IDX_HANDSET = 81;
	public final static int IDX_SWITCH_FLAG = 82;
	public final static int IDX_SWITCH_TIME = 83;
	public final static int IDX_COMMERCIAL_STATUS = 84;
	public final static int IDX_FLOW_ID = 85;
	public final static int IDX_DEDUCT_ACCT_FLAG = 86;
	public final static int IDX_INIT_THRESHOLD = 87;
	public final static int IDX_SEQUENCE_ID = 88;
	public final static int IDX_CONFIRM_TAG = 89;
	public final static int IDX_DR_TYPE_DESC = 90;
	public final static int IDX_CONTROL_TAG_FOR_MODULE = 91;

	/**
	 * 
	 */
	@XmlElement(name="roamingOperatorGroup")
	@Sdl
	private List<SOperatorGroup> roamingOperatorGroup;

	/**
	 * 
	 */
	@XmlElement(name="roamingCountryGroup")
	@Sdl
	private List<SCountryGroup> roamingCountryGroup;

	/**
	 * 
	 */
	@XmlElement(name="oriCharge")
	@Sdl
	private List<SOriCharge> oriCharge;

	/**
	 * 
	 */
	@XmlElement(name="bakOriCharge")
	@Sdl
	private List<SOriCharge> bakOriCharge;

	/**
	 * 
	 */
	@XmlElement(name="freeresQuery")
	@Sdl
	private SFreeResQuery freeresQuery;

	/**
	 * 
	 */
	@XmlElement(name="reserveFields")
	@Sdl
	private Map<String,String> reserveFields;

	/**
	 * 
	 */
	@XmlElement(name="gsmSpecInfo")
	@Sdl
	private SGsmSpecInfo gsmSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="gprsSpecInfo")
	@Sdl
	private SGprsSpecInfo gprsSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="smsSpecInfo")
	@Sdl
	private SSmsSpecInfo smsSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="mmsSpecInfo")
	@Sdl
	private SMmsSpecInfo mmsSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="ismpSpecInfo")
	@Sdl
	private SIsmpSpecInfo ismpSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="gsSpecInfo")
	@Sdl
	private SGsSpecInfo gsSpecInfo;

	/**
	 * 
	 */
	@XmlElement(name="initThreshold")
	@Sdl
	private SInitThreshold initThreshold;

	/**
	 * 
	 */
	@XmlElement(name="companyCode")
	@Sdl
	private String companyCode;

	/**
	 * 
	 */
	@XmlElement(name="fileCreateDate")
	@Sdl
	private long fileCreateDate;

	/**
	 * 
	 */
	@XmlElement(name="createTime")
	@Sdl
	private long createTime;

	/**
	 * 
	 */
	@XmlElement(name="sequenceNo")
	@Sdl
	private long sequenceNo;

	/**
	 * 
	 */
	@XmlElement(name="sessionId")
	@Sdl
	private String sessionId;

	/**
	 * 
	 */
	@XmlElement(name="refundSessionId")
	@Sdl
	private String refundSessionId;

	/**
	 * 
	 */
	@XmlElement(name="originalFile")
	@Sdl
	private String originalFile;

	/**
	 * 
	 */
	@XmlElement(name="stopCause")
	@Sdl
	private int stopCause;

	/**
	 * 
	 */
	@XmlElement(name="chaegeupSeq")
	@Sdl
	private String chaegeupSeq;

	/**
	 * 
	 */
	@XmlElement(name="sendPriority")
	@Sdl
	private int sendPriority;

	/**
	 * 
	 */
	@XmlElement(name="channelId")
	@Sdl
	private String channelId;

	/**
	 * 
	 */
	@XmlElement(name="stopTime")
	@Sdl
	private long stopTime;

	/**
	 * 
	 */
	@XmlElement(name="messageSize")
	@Sdl
	private int messageSize;

	/**
	 * 
	 */
	@XmlElement(name="sendState")
	@Sdl
	private int sendState;

	/**
	 * 
	 */
	@XmlElement(name="finalState")
	@Sdl
	private int finalState;

	/**
	 * 
	 */
	@XmlElement(name="additionalInfo")
	@Sdl
	private String additionalInfo;

	/**
	 * 
	 */
	@XmlElement(name="serviceId")
	@Sdl
	private long serviceId;

	/**
	 * 
	 */
	@XmlElement(name="drType")
	@Sdl
	private long drType;

	/**
	 * 
	 */
	@XmlElement(name="deviceType")
	@Sdl
	private int deviceType;

	/**
	 * 
	 */
	@XmlElement(name="roamingOperatorId")
	@Sdl
	private int roamingOperatorId;

	/**
	 * 
	 */
	@XmlElement(name="roamingOperatorCode")
	@Sdl
	private String roamingOperatorCode;

	/**
	 * 
	 */
	@XmlElement(name="roamingOperatorNet")
	@Sdl
	private String roamingOperatorNet;

	/**
	 * 
	 */
	@XmlElement(name="roamingCountryId")
	@Sdl
	private int roamingCountryId;

	/**
	 * 
	 */
	@XmlElement(name="mnsType")
	@Sdl
	private int mnsType;

	/**
	 * 
	 */
	@XmlElement(name="imei")
	@Sdl
	private String imei;

	/**
	 * 
	 */
	@XmlElement(name="oriUserNumber")
	@Sdl
	private String oriUserNumber;

	/**
	 * 
	 */
	@XmlElement(name="userNumber")
	@Sdl
	private String userNumber;

	/**
	 * 
	 */
	@XmlElement(name="connectCalledNumber")
	@Sdl
	private String connectCalledNumber;

	/**
	 * 
	 */
	@XmlElement(name="consumeUserNumber")
	@Sdl
	private String consumeUserNumber;

	/**
	 * 
	 */
	@XmlElement(name="actionType")
	@Sdl
	private int actionType;

	/**
	 * 
	 */
	@XmlElement(name="imsi")
	@Sdl
	private String imsi;

	/**
	 * 
	 */
	@XmlElement(name="oriImsi")
	@Sdl
	private String oriImsi;

	/**
	 * 
	 */
	@XmlElement(name="bakReguideUserNumber")
	@Sdl
	private String bakReguideUserNumber;

	/**
	 * 
	 */
	@XmlElement(name="cdrReceiveTime")
	@Sdl
	private long cdrReceiveTime;

	/**
	 * 
	 */
	@XmlElement(name="numberClass")
	@Sdl
	private int numberClass;

	/**
	 * 
	 */
	@XmlElement(name="numberSubclass")
	@Sdl
	private int numberSubclass;

	/**
	 * 
	 */
	@XmlElement(name="otherInfo1")
	@Sdl
	private String otherInfo1;

	/**
	 * 
	 */
	@XmlElement(name="otherInfo2")
	@Sdl
	private String otherInfo2;

	/**
	 * 
	 */
	@XmlElement(name="otherInfo3")
	@Sdl
	private String otherInfo3;

	/**
	 * 
	 */
	@XmlElement(name="otherInfo4")
	@Sdl
	private String otherInfo4;

	/**
	 * 
	 */
	@XmlElement(name="otherInfo5")
	@Sdl
	private String otherInfo5;

	/**
	 * 
	 */
	@XmlElement(name="vplmn1")
	@Sdl
	private String vplmn1;

	/**
	 * 
	 */
	@XmlElement(name="vplmn2")
	@Sdl
	private String vplmn2;

	/**
	 * 
	 */
	@XmlElement(name="vplmn3")
	@Sdl
	private String vplmn3;

	/**
	 * 
	 */
	@XmlElement(name="visitCountyCode")
	@Sdl
	private short visitCountyCode;

	/**
	 * 
	 */
	@XmlElement(name="iddType")
	@Sdl
	private short iddType;

	/**
	 * 
	 */
	@XmlElement(name="camelFlag")
	@Sdl
	private short camelFlag;

	/**
	 * 
	 */
	@XmlElement(name="flhFlag")
	@Sdl
	private short flhFlag;

	/**
	 * 
	 */
	@XmlElement(name="continueFlag")
	@Sdl
	private short continueFlag;

	/**
	 * 
	 */
	@XmlElement(name="userOperatorId")
	@Sdl
	private short userOperatorId;

	/**
	 * 
	 */
	@XmlElement(name="oppOperatorId")
	@Sdl
	private short oppOperatorId;

	/**
	 * 
	 */
	@XmlElement(name="numberIdentityAttr")
	@Sdl
	private int numberIdentityAttr;

	/**
	 * 
	 */
	@XmlElement(name="oppHplmn1")
	@Sdl
	private String oppHplmn1;

	/**
	 * 
	 */
	@XmlElement(name="oppHplmn2")
	@Sdl
	private String oppHplmn2;

	/**
	 * 
	 */
	@XmlElement(name="oppHplmn3")
	@Sdl
	private String oppHplmn3;

	/**
	 * 
	 */
	@XmlElement(name="oppVplmn1")
	@Sdl
	private String oppVplmn1;

	/**
	 * 
	 */
	@XmlElement(name="oppVplmn2")
	@Sdl
	private String oppVplmn2;

	/**
	 * 
	 */
	@XmlElement(name="oppVplmn3")
	@Sdl
	private String oppVplmn3;

	/**
	 * 
	 */
	@XmlElement(name="originHost")
	@Sdl
	private String originHost;

	/**
	 * 
	 */
	@XmlElement(name="originRealm")
	@Sdl
	private String originRealm;

	/**
	 * 
	 */
	@XmlElement(name="destinationRealm")
	@Sdl
	private String destinationRealm;

	/**
	 * 
	 */
	@XmlElement(name="authApplicationId")
	@Sdl
	private int authApplicationId;

	/**
	 * 
	 */
	@XmlElement(name="destinationHost")
	@Sdl
	private String destinationHost;

	/**
	 * 
	 */
	@XmlElement(name="userName")
	@Sdl
	private String userName;

	/**
	 * 
	 */
	@XmlElement(name="originStateId")
	@Sdl
	private int originStateId;

	/**
	 * 
	 */
	@XmlElement(name="productName")
	@Sdl
	private String productName;

	/**
	 * 
	 */
	@XmlElement(name="hostIpAddress")
	@Sdl
	private String hostIpAddress;

	/**
	 * 
	 */
	@XmlElement(name="vendorId1")
	@Sdl
	private int vendorId1;

	/**
	 * 
	 */
	@XmlElement(name="uSeparateFlag")
	@Sdl
	private int uSeparateFlag;

	/**
	 * 
	 */
	@XmlElement(name="handset")
	@Sdl
	private int handset;

	/**
	 * 
	 */
	@XmlElement(name="switchFlag")
	@Sdl
	private int switchFlag;

	/**
	 * 
	 */
	@XmlElement(name="switchTime")
	@Sdl
	private long switchTime;

	/**
	 * 
	 */
	@XmlElement(name="commercialStatus")
	@Sdl
	private short commercialStatus;

	/**
	 * 
	 */
	@XmlElement(name="flowId")
	@Sdl
	private String flowId;

	/**
	 * 
	 */
	@XmlElement(name="deductAcctFlag")
	@Sdl
	private int deductAcctFlag;

	/**
	 * 
	 */
	@XmlElement(name="sequenceId")
	@Sdl
	private String sequenceId;

	/**
	 * 
	 */
	@XmlElement(name="confirmTag")
	@Sdl
	private short confirmTag;

	/**
	 * 
	 */
	@XmlElement(name="drTypeDesc")
	@Sdl
	private String drTypeDesc;

	/**
	 * 
	 */
	@XmlElement(name="controlTagForModule")
	@Sdl
	private long controlTagForModule;

	public void setRoamingOperatorGroup(List<SOperatorGroup> obj){
		this.roamingOperatorGroup = obj;
		onFieldSet(21, obj);
	}

	public List<SOperatorGroup> getRoamingOperatorGroup(){
		return roamingOperatorGroup;
	}

	public void setRoamingCountryGroup(List<SCountryGroup> obj){
		this.roamingCountryGroup = obj;
		onFieldSet(24, obj);
	}

	public List<SCountryGroup> getRoamingCountryGroup(){
		return roamingCountryGroup;
	}

	public void setOriCharge(List<SOriCharge> obj){
		this.oriCharge = obj;
		onFieldSet(27, obj);
	}

	public List<SOriCharge> getOriCharge(){
		return oriCharge;
	}

	public void setBakOriCharge(List<SOriCharge> obj){
		this.bakOriCharge = obj;
		onFieldSet(28, obj);
	}

	public List<SOriCharge> getBakOriCharge(){
		return bakOriCharge;
	}

	public void setFreeresQuery(SFreeResQuery obj){
		this.freeresQuery = obj;
		onFieldSet(37, obj);
	}

	public SFreeResQuery getFreeresQuery(){
		return freeresQuery;
	}

	public void setReserveFields(Map<String,String> obj){
		this.reserveFields = obj;
		onFieldSet(46, obj);
	}

	public Map<String,String> getReserveFields(){
		return reserveFields;
	}

	public void setGsmSpecInfo(SGsmSpecInfo obj){
		this.gsmSpecInfo = obj;
		onFieldSet(74, obj);
	}

	public SGsmSpecInfo getGsmSpecInfo(){
		return gsmSpecInfo;
	}

	public void setGprsSpecInfo(SGprsSpecInfo obj){
		this.gprsSpecInfo = obj;
		onFieldSet(75, obj);
	}

	public SGprsSpecInfo getGprsSpecInfo(){
		return gprsSpecInfo;
	}

	public void setSmsSpecInfo(SSmsSpecInfo obj){
		this.smsSpecInfo = obj;
		onFieldSet(76, obj);
	}

	public SSmsSpecInfo getSmsSpecInfo(){
		return smsSpecInfo;
	}

	public void setMmsSpecInfo(SMmsSpecInfo obj){
		this.mmsSpecInfo = obj;
		onFieldSet(77, obj);
	}

	public SMmsSpecInfo getMmsSpecInfo(){
		return mmsSpecInfo;
	}

	public void setIsmpSpecInfo(SIsmpSpecInfo obj){
		this.ismpSpecInfo = obj;
		onFieldSet(78, obj);
	}

	public SIsmpSpecInfo getIsmpSpecInfo(){
		return ismpSpecInfo;
	}

	public void setGsSpecInfo(SGsSpecInfo obj){
		this.gsSpecInfo = obj;
		onFieldSet(79, obj);
	}

	public SGsSpecInfo getGsSpecInfo(){
		return gsSpecInfo;
	}

	public void setInitThreshold(SInitThreshold obj){
		this.initThreshold = obj;
		onFieldSet(87, obj);
	}

	public SInitThreshold getInitThreshold(){
		return initThreshold;
	}

	public void setCompanyCode(String obj){
		this.companyCode = obj;
		onFieldSet(0, obj);
	}

	public String getCompanyCode(){
		return companyCode;
	}

	public void setFileCreateDate(long obj){
		this.fileCreateDate = obj;
		onFieldSet(1, obj);
	}

	public long getFileCreateDate(){
		return fileCreateDate;
	}

	public void setCreateTime(long obj){
		this.createTime = obj;
		onFieldSet(2, obj);
	}

	public long getCreateTime(){
		return createTime;
	}

	public void setSequenceNo(long obj){
		this.sequenceNo = obj;
		onFieldSet(3, obj);
	}

	public long getSequenceNo(){
		return sequenceNo;
	}

	public void setSessionId(String obj){
		this.sessionId = obj;
		onFieldSet(4, obj);
	}

	public String getSessionId(){
		return sessionId;
	}

	public void setRefundSessionId(String obj){
		this.refundSessionId = obj;
		onFieldSet(5, obj);
	}

	public String getRefundSessionId(){
		return refundSessionId;
	}

	public void setOriginalFile(String obj){
		this.originalFile = obj;
		onFieldSet(6, obj);
	}

	public String getOriginalFile(){
		return originalFile;
	}

	public void setStopCause(int obj){
		this.stopCause = obj;
		onFieldSet(7, obj);
	}

	public int getStopCause(){
		return stopCause;
	}

	public void setChaegeupSeq(String obj){
		this.chaegeupSeq = obj;
		onFieldSet(8, obj);
	}

	public String getChaegeupSeq(){
		return chaegeupSeq;
	}

	public void setSendPriority(int obj){
		this.sendPriority = obj;
		onFieldSet(9, obj);
	}

	public int getSendPriority(){
		return sendPriority;
	}

	public void setChannelId(String obj){
		this.channelId = obj;
		onFieldSet(10, obj);
	}

	public String getChannelId(){
		return channelId;
	}

	public void setStopTime(long obj){
		this.stopTime = obj;
		onFieldSet(11, obj);
	}

	public long getStopTime(){
		return stopTime;
	}

	public void setMessageSize(int obj){
		this.messageSize = obj;
		onFieldSet(12, obj);
	}

	public int getMessageSize(){
		return messageSize;
	}

	public void setSendState(int obj){
		this.sendState = obj;
		onFieldSet(13, obj);
	}

	public int getSendState(){
		return sendState;
	}

	public void setFinalState(int obj){
		this.finalState = obj;
		onFieldSet(14, obj);
	}

	public int getFinalState(){
		return finalState;
	}

	public void setAdditionalInfo(String obj){
		this.additionalInfo = obj;
		onFieldSet(15, obj);
	}

	public String getAdditionalInfo(){
		return additionalInfo;
	}

	public void setServiceId(long obj){
		this.serviceId = obj;
		onFieldSet(16, obj);
	}

	public long getServiceId(){
		return serviceId;
	}

	public void setDrType(long obj){
		this.drType = obj;
		onFieldSet(17, obj);
	}

	public long getDrType(){
		return drType;
	}

	public void setDeviceType(int obj){
		this.deviceType = obj;
		onFieldSet(18, obj);
	}

	public int getDeviceType(){
		return deviceType;
	}

	public void setRoamingOperatorId(int obj){
		this.roamingOperatorId = obj;
		onFieldSet(19, obj);
	}

	public int getRoamingOperatorId(){
		return roamingOperatorId;
	}

	public void setRoamingOperatorCode(String obj){
		this.roamingOperatorCode = obj;
		onFieldSet(20, obj);
	}

	public String getRoamingOperatorCode(){
		return roamingOperatorCode;
	}

	public void setRoamingOperatorNet(String obj){
		this.roamingOperatorNet = obj;
		onFieldSet(22, obj);
	}

	public String getRoamingOperatorNet(){
		return roamingOperatorNet;
	}

	public void setRoamingCountryId(int obj){
		this.roamingCountryId = obj;
		onFieldSet(23, obj);
	}

	public int getRoamingCountryId(){
		return roamingCountryId;
	}

	public void setMnsType(int obj){
		this.mnsType = obj;
		onFieldSet(25, obj);
	}

	public int getMnsType(){
		return mnsType;
	}

	public void setImei(String obj){
		this.imei = obj;
		onFieldSet(26, obj);
	}

	public String getImei(){
		return imei;
	}

	public void setOriUserNumber(String obj){
		this.oriUserNumber = obj;
		onFieldSet(29, obj);
	}

	public String getOriUserNumber(){
		return oriUserNumber;
	}

	public void setUserNumber(String obj){
		this.userNumber = obj;
		onFieldSet(30, obj);
	}

	public String getUserNumber(){
		return userNumber;
	}

	public void setConnectCalledNumber(String obj){
		this.connectCalledNumber = obj;
		onFieldSet(31, obj);
	}

	public String getConnectCalledNumber(){
		return connectCalledNumber;
	}

	public void setConsumeUserNumber(String obj){
		this.consumeUserNumber = obj;
		onFieldSet(32, obj);
	}

	public String getConsumeUserNumber(){
		return consumeUserNumber;
	}

	public void setActionType(int obj){
		this.actionType = obj;
		onFieldSet(33, obj);
	}

	public int getActionType(){
		return actionType;
	}

	public void setImsi(String obj){
		this.imsi = obj;
		onFieldSet(34, obj);
	}

	public String getImsi(){
		return imsi;
	}

	public void setOriImsi(String obj){
		this.oriImsi = obj;
		onFieldSet(35, obj);
	}

	public String getOriImsi(){
		return oriImsi;
	}

	public void setBakReguideUserNumber(String obj){
		this.bakReguideUserNumber = obj;
		onFieldSet(36, obj);
	}

	public String getBakReguideUserNumber(){
		return bakReguideUserNumber;
	}

	public void setCdrReceiveTime(long obj){
		this.cdrReceiveTime = obj;
		onFieldSet(38, obj);
	}

	public long getCdrReceiveTime(){
		return cdrReceiveTime;
	}

	public void setNumberClass(int obj){
		this.numberClass = obj;
		onFieldSet(39, obj);
	}

	public int getNumberClass(){
		return numberClass;
	}

	public void setNumberSubclass(int obj){
		this.numberSubclass = obj;
		onFieldSet(40, obj);
	}

	public int getNumberSubclass(){
		return numberSubclass;
	}

	public void setOtherInfo1(String obj){
		this.otherInfo1 = obj;
		onFieldSet(41, obj);
	}

	public String getOtherInfo1(){
		return otherInfo1;
	}

	public void setOtherInfo2(String obj){
		this.otherInfo2 = obj;
		onFieldSet(42, obj);
	}

	public String getOtherInfo2(){
		return otherInfo2;
	}

	public void setOtherInfo3(String obj){
		this.otherInfo3 = obj;
		onFieldSet(43, obj);
	}

	public String getOtherInfo3(){
		return otherInfo3;
	}

	public void setOtherInfo4(String obj){
		this.otherInfo4 = obj;
		onFieldSet(44, obj);
	}

	public String getOtherInfo4(){
		return otherInfo4;
	}

	public void setOtherInfo5(String obj){
		this.otherInfo5 = obj;
		onFieldSet(45, obj);
	}

	public String getOtherInfo5(){
		return otherInfo5;
	}

	public void setVplmn1(String obj){
		this.vplmn1 = obj;
		onFieldSet(47, obj);
	}

	public String getVplmn1(){
		return vplmn1;
	}

	public void setVplmn2(String obj){
		this.vplmn2 = obj;
		onFieldSet(48, obj);
	}

	public String getVplmn2(){
		return vplmn2;
	}

	public void setVplmn3(String obj){
		this.vplmn3 = obj;
		onFieldSet(49, obj);
	}

	public String getVplmn3(){
		return vplmn3;
	}

	public void setVisitCountyCode(short obj){
		this.visitCountyCode = obj;
		onFieldSet(50, obj);
	}

	public short getVisitCountyCode(){
		return visitCountyCode;
	}

	public void setIddType(short obj){
		this.iddType = obj;
		onFieldSet(51, obj);
	}

	public short getIddType(){
		return iddType;
	}

	public void setCamelFlag(short obj){
		this.camelFlag = obj;
		onFieldSet(52, obj);
	}

	public short getCamelFlag(){
		return camelFlag;
	}

	public void setFlhFlag(short obj){
		this.flhFlag = obj;
		onFieldSet(53, obj);
	}

	public short getFlhFlag(){
		return flhFlag;
	}

	public void setContinueFlag(short obj){
		this.continueFlag = obj;
		onFieldSet(54, obj);
	}

	public short getContinueFlag(){
		return continueFlag;
	}

	public void setUserOperatorId(short obj){
		this.userOperatorId = obj;
		onFieldSet(55, obj);
	}

	public short getUserOperatorId(){
		return userOperatorId;
	}

	public void setOppOperatorId(short obj){
		this.oppOperatorId = obj;
		onFieldSet(56, obj);
	}

	public short getOppOperatorId(){
		return oppOperatorId;
	}

	public void setNumberIdentityAttr(int obj){
		this.numberIdentityAttr = obj;
		onFieldSet(57, obj);
	}

	public int getNumberIdentityAttr(){
		return numberIdentityAttr;
	}

	public void setOppHplmn1(String obj){
		this.oppHplmn1 = obj;
		onFieldSet(58, obj);
	}

	public String getOppHplmn1(){
		return oppHplmn1;
	}

	public void setOppHplmn2(String obj){
		this.oppHplmn2 = obj;
		onFieldSet(59, obj);
	}

	public String getOppHplmn2(){
		return oppHplmn2;
	}

	public void setOppHplmn3(String obj){
		this.oppHplmn3 = obj;
		onFieldSet(60, obj);
	}

	public String getOppHplmn3(){
		return oppHplmn3;
	}

	public void setOppVplmn1(String obj){
		this.oppVplmn1 = obj;
		onFieldSet(61, obj);
	}

	public String getOppVplmn1(){
		return oppVplmn1;
	}

	public void setOppVplmn2(String obj){
		this.oppVplmn2 = obj;
		onFieldSet(62, obj);
	}

	public String getOppVplmn2(){
		return oppVplmn2;
	}

	public void setOppVplmn3(String obj){
		this.oppVplmn3 = obj;
		onFieldSet(63, obj);
	}

	public String getOppVplmn3(){
		return oppVplmn3;
	}

	public void setOriginHost(String obj){
		this.originHost = obj;
		onFieldSet(64, obj);
	}

	public String getOriginHost(){
		return originHost;
	}

	public void setOriginRealm(String obj){
		this.originRealm = obj;
		onFieldSet(65, obj);
	}

	public String getOriginRealm(){
		return originRealm;
	}

	public void setDestinationRealm(String obj){
		this.destinationRealm = obj;
		onFieldSet(66, obj);
	}

	public String getDestinationRealm(){
		return destinationRealm;
	}

	public void setAuthApplicationId(int obj){
		this.authApplicationId = obj;
		onFieldSet(67, obj);
	}

	public int getAuthApplicationId(){
		return authApplicationId;
	}

	public void setDestinationHost(String obj){
		this.destinationHost = obj;
		onFieldSet(68, obj);
	}

	public String getDestinationHost(){
		return destinationHost;
	}

	public void setUserName(String obj){
		this.userName = obj;
		onFieldSet(69, obj);
	}

	public String getUserName(){
		return userName;
	}

	public void setOriginStateId(int obj){
		this.originStateId = obj;
		onFieldSet(70, obj);
	}

	public int getOriginStateId(){
		return originStateId;
	}

	public void setProductName(String obj){
		this.productName = obj;
		onFieldSet(71, obj);
	}

	public String getProductName(){
		return productName;
	}

	public void setHostIpAddress(String obj){
		this.hostIpAddress = obj;
		onFieldSet(72, obj);
	}

	public String getHostIpAddress(){
		return hostIpAddress;
	}

	public void setVendorId1(int obj){
		this.vendorId1 = obj;
		onFieldSet(73, obj);
	}

	public int getVendorId1(){
		return vendorId1;
	}

	public void setUSeparateFlag(int obj){
		this.uSeparateFlag = obj;
		onFieldSet(80, obj);
	}

	public int getUSeparateFlag(){
		return uSeparateFlag;
	}

	public void setHandset(int obj){
		this.handset = obj;
		onFieldSet(81, obj);
	}

	public int getHandset(){
		return handset;
	}

	public void setSwitchFlag(int obj){
		this.switchFlag = obj;
		onFieldSet(82, obj);
	}

	public int getSwitchFlag(){
		return switchFlag;
	}

	public void setSwitchTime(long obj){
		this.switchTime = obj;
		onFieldSet(83, obj);
	}

	public long getSwitchTime(){
		return switchTime;
	}

	public void setCommercialStatus(short obj){
		this.commercialStatus = obj;
		onFieldSet(84, obj);
	}

	public short getCommercialStatus(){
		return commercialStatus;
	}

	public void setFlowId(String obj){
		this.flowId = obj;
		onFieldSet(85, obj);
	}

	public String getFlowId(){
		return flowId;
	}

	public void setDeductAcctFlag(int obj){
		this.deductAcctFlag = obj;
		onFieldSet(86, obj);
	}

	public int getDeductAcctFlag(){
		return deductAcctFlag;
	}

	public void setSequenceId(String obj){
		this.sequenceId = obj;
		onFieldSet(88, obj);
	}

	public String getSequenceId(){
		return sequenceId;
	}

	public void setConfirmTag(short obj){
		this.confirmTag = obj;
		onFieldSet(89, obj);
	}

	public short getConfirmTag(){
		return confirmTag;
	}

	public void setDrTypeDesc(String obj){
		this.drTypeDesc = obj;
		onFieldSet(90, obj);
	}

	public String getDrTypeDesc(){
		return drTypeDesc;
	}

	public void setControlTagForModule(long obj){
		this.controlTagForModule = obj;
		onFieldSet(91, obj);
	}

	public long getControlTagForModule(){
		return controlTagForModule;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCommon(){
		m_llMarkers = new long[2]; // used marker
		m_llUsedMarkers = new long[2]; // used marker
		fieldNum = 28; 
		markerNum = 2; 
	}

	/**
	 * 创建copy方法
	 */
	public SCommon(SCommon arg0){
		copy(arg0);
	}

	public void copy(final SCommon rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		companyCode = rhs.companyCode;
		fileCreateDate = rhs.fileCreateDate;
		createTime = rhs.createTime;
		sequenceNo = rhs.sequenceNo;
		sessionId = rhs.sessionId;
		refundSessionId = rhs.refundSessionId;
		originalFile = rhs.originalFile;
		stopCause = rhs.stopCause;
		chaegeupSeq = rhs.chaegeupSeq;
		sendPriority = rhs.sendPriority;
		channelId = rhs.channelId;
		stopTime = rhs.stopTime;
		messageSize = rhs.messageSize;
		sendState = rhs.sendState;
		finalState = rhs.finalState;
		additionalInfo = rhs.additionalInfo;
		serviceId = rhs.serviceId;
		drType = rhs.drType;
		deviceType = rhs.deviceType;
		roamingOperatorId = rhs.roamingOperatorId;
		roamingOperatorCode = rhs.roamingOperatorCode;
		roamingOperatorGroup = rhs.roamingOperatorGroup;
		roamingOperatorNet = rhs.roamingOperatorNet;
		roamingCountryId = rhs.roamingCountryId;
		roamingCountryGroup = rhs.roamingCountryGroup;
		mnsType = rhs.mnsType;
		imei = rhs.imei;
		oriCharge = rhs.oriCharge;
		bakOriCharge = rhs.bakOriCharge;
		oriUserNumber = rhs.oriUserNumber;
		userNumber = rhs.userNumber;
		connectCalledNumber = rhs.connectCalledNumber;
		consumeUserNumber = rhs.consumeUserNumber;
		actionType = rhs.actionType;
		imsi = rhs.imsi;
		oriImsi = rhs.oriImsi;
		bakReguideUserNumber = rhs.bakReguideUserNumber;
		freeresQuery = rhs.freeresQuery;
		cdrReceiveTime = rhs.cdrReceiveTime;
		numberClass = rhs.numberClass;
		numberSubclass = rhs.numberSubclass;
		otherInfo1 = rhs.otherInfo1;
		otherInfo2 = rhs.otherInfo2;
		otherInfo3 = rhs.otherInfo3;
		otherInfo4 = rhs.otherInfo4;
		otherInfo5 = rhs.otherInfo5;
		reserveFields = rhs.reserveFields;
		vplmn1 = rhs.vplmn1;
		vplmn2 = rhs.vplmn2;
		vplmn3 = rhs.vplmn3;
		visitCountyCode = rhs.visitCountyCode;
		iddType = rhs.iddType;
		camelFlag = rhs.camelFlag;
		flhFlag = rhs.flhFlag;
		continueFlag = rhs.continueFlag;
		userOperatorId = rhs.userOperatorId;
		oppOperatorId = rhs.oppOperatorId;
		numberIdentityAttr = rhs.numberIdentityAttr;
		oppHplmn1 = rhs.oppHplmn1;
		oppHplmn2 = rhs.oppHplmn2;
		oppHplmn3 = rhs.oppHplmn3;
		oppVplmn1 = rhs.oppVplmn1;
		oppVplmn2 = rhs.oppVplmn2;
		oppVplmn3 = rhs.oppVplmn3;
		originHost = rhs.originHost;
		originRealm = rhs.originRealm;
		destinationRealm = rhs.destinationRealm;
		authApplicationId = rhs.authApplicationId;
		destinationHost = rhs.destinationHost;
		userName = rhs.userName;
		originStateId = rhs.originStateId;
		productName = rhs.productName;
		hostIpAddress = rhs.hostIpAddress;
		vendorId1 = rhs.vendorId1;
		gsmSpecInfo = rhs.gsmSpecInfo;
		gprsSpecInfo = rhs.gprsSpecInfo;
		smsSpecInfo = rhs.smsSpecInfo;
		mmsSpecInfo = rhs.mmsSpecInfo;
		ismpSpecInfo = rhs.ismpSpecInfo;
		gsSpecInfo = rhs.gsSpecInfo;
		uSeparateFlag = rhs.uSeparateFlag;
		handset = rhs.handset;
		switchFlag = rhs.switchFlag;
		switchTime = rhs.switchTime;
		commercialStatus = rhs.commercialStatus;
		flowId = rhs.flowId;
		deductAcctFlag = rhs.deductAcctFlag;
		initThreshold = rhs.initThreshold;
		sequenceId = rhs.sequenceId;
		confirmTag = rhs.confirmTag;
		drTypeDesc = rhs.drTypeDesc;
		controlTagForModule = rhs.controlTagForModule;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCommon rhs=(SCommon)rhs0;
		if(!ObjectUtils.equals(companyCode, rhs.companyCode)) return false;
		if(!ObjectUtils.equals(fileCreateDate, rhs.fileCreateDate)) return false;
		if(!ObjectUtils.equals(createTime, rhs.createTime)) return false;
		if(!ObjectUtils.equals(sequenceNo, rhs.sequenceNo)) return false;
		if(!ObjectUtils.equals(sessionId, rhs.sessionId)) return false;
		if(!ObjectUtils.equals(refundSessionId, rhs.refundSessionId)) return false;
		if(!ObjectUtils.equals(originalFile, rhs.originalFile)) return false;
		if(!ObjectUtils.equals(stopCause, rhs.stopCause)) return false;
		if(!ObjectUtils.equals(chaegeupSeq, rhs.chaegeupSeq)) return false;
		if(!ObjectUtils.equals(sendPriority, rhs.sendPriority)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(stopTime, rhs.stopTime)) return false;
		if(!ObjectUtils.equals(messageSize, rhs.messageSize)) return false;
		if(!ObjectUtils.equals(sendState, rhs.sendState)) return false;
		if(!ObjectUtils.equals(finalState, rhs.finalState)) return false;
		if(!ObjectUtils.equals(additionalInfo, rhs.additionalInfo)) return false;
		if(!ObjectUtils.equals(serviceId, rhs.serviceId)) return false;
		if(!ObjectUtils.equals(drType, rhs.drType)) return false;
		if(!ObjectUtils.equals(deviceType, rhs.deviceType)) return false;
		if(!ObjectUtils.equals(roamingOperatorId, rhs.roamingOperatorId)) return false;
		if(!ObjectUtils.equals(roamingOperatorCode, rhs.roamingOperatorCode)) return false;
		if(!ObjectUtils.equals(roamingOperatorGroup, rhs.roamingOperatorGroup)) return false;
		if(!ObjectUtils.equals(roamingOperatorNet, rhs.roamingOperatorNet)) return false;
		if(!ObjectUtils.equals(roamingCountryId, rhs.roamingCountryId)) return false;
		if(!ObjectUtils.equals(roamingCountryGroup, rhs.roamingCountryGroup)) return false;
		if(!ObjectUtils.equals(mnsType, rhs.mnsType)) return false;
		if(!ObjectUtils.equals(imei, rhs.imei)) return false;
		if(!ObjectUtils.equals(oriCharge, rhs.oriCharge)) return false;
		if(!ObjectUtils.equals(bakOriCharge, rhs.bakOriCharge)) return false;
		if(!ObjectUtils.equals(oriUserNumber, rhs.oriUserNumber)) return false;
		if(!ObjectUtils.equals(userNumber, rhs.userNumber)) return false;
		if(!ObjectUtils.equals(connectCalledNumber, rhs.connectCalledNumber)) return false;
		if(!ObjectUtils.equals(consumeUserNumber, rhs.consumeUserNumber)) return false;
		if(!ObjectUtils.equals(actionType, rhs.actionType)) return false;
		if(!ObjectUtils.equals(imsi, rhs.imsi)) return false;
		if(!ObjectUtils.equals(oriImsi, rhs.oriImsi)) return false;
		if(!ObjectUtils.equals(bakReguideUserNumber, rhs.bakReguideUserNumber)) return false;
		if(!ObjectUtils.equals(freeresQuery, rhs.freeresQuery)) return false;
		if(!ObjectUtils.equals(cdrReceiveTime, rhs.cdrReceiveTime)) return false;
		if(!ObjectUtils.equals(numberClass, rhs.numberClass)) return false;
		if(!ObjectUtils.equals(numberSubclass, rhs.numberSubclass)) return false;
		if(!ObjectUtils.equals(otherInfo1, rhs.otherInfo1)) return false;
		if(!ObjectUtils.equals(otherInfo2, rhs.otherInfo2)) return false;
		if(!ObjectUtils.equals(otherInfo3, rhs.otherInfo3)) return false;
		if(!ObjectUtils.equals(otherInfo4, rhs.otherInfo4)) return false;
		if(!ObjectUtils.equals(otherInfo5, rhs.otherInfo5)) return false;
		if(!ObjectUtils.equals(reserveFields, rhs.reserveFields)) return false;
		if(!ObjectUtils.equals(vplmn1, rhs.vplmn1)) return false;
		if(!ObjectUtils.equals(vplmn2, rhs.vplmn2)) return false;
		if(!ObjectUtils.equals(vplmn3, rhs.vplmn3)) return false;
		if(!ObjectUtils.equals(visitCountyCode, rhs.visitCountyCode)) return false;
		if(!ObjectUtils.equals(iddType, rhs.iddType)) return false;
		if(!ObjectUtils.equals(camelFlag, rhs.camelFlag)) return false;
		if(!ObjectUtils.equals(flhFlag, rhs.flhFlag)) return false;
		if(!ObjectUtils.equals(continueFlag, rhs.continueFlag)) return false;
		if(!ObjectUtils.equals(userOperatorId, rhs.userOperatorId)) return false;
		if(!ObjectUtils.equals(oppOperatorId, rhs.oppOperatorId)) return false;
		if(!ObjectUtils.equals(numberIdentityAttr, rhs.numberIdentityAttr)) return false;
		if(!ObjectUtils.equals(oppHplmn1, rhs.oppHplmn1)) return false;
		if(!ObjectUtils.equals(oppHplmn2, rhs.oppHplmn2)) return false;
		if(!ObjectUtils.equals(oppHplmn3, rhs.oppHplmn3)) return false;
		if(!ObjectUtils.equals(oppVplmn1, rhs.oppVplmn1)) return false;
		if(!ObjectUtils.equals(oppVplmn2, rhs.oppVplmn2)) return false;
		if(!ObjectUtils.equals(oppVplmn3, rhs.oppVplmn3)) return false;
		if(!ObjectUtils.equals(originHost, rhs.originHost)) return false;
		if(!ObjectUtils.equals(originRealm, rhs.originRealm)) return false;
		if(!ObjectUtils.equals(destinationRealm, rhs.destinationRealm)) return false;
		if(!ObjectUtils.equals(authApplicationId, rhs.authApplicationId)) return false;
		if(!ObjectUtils.equals(destinationHost, rhs.destinationHost)) return false;
		if(!ObjectUtils.equals(userName, rhs.userName)) return false;
		if(!ObjectUtils.equals(originStateId, rhs.originStateId)) return false;
		if(!ObjectUtils.equals(productName, rhs.productName)) return false;
		if(!ObjectUtils.equals(hostIpAddress, rhs.hostIpAddress)) return false;
		if(!ObjectUtils.equals(vendorId1, rhs.vendorId1)) return false;
		if(!ObjectUtils.equals(gsmSpecInfo, rhs.gsmSpecInfo)) return false;
		if(!ObjectUtils.equals(gprsSpecInfo, rhs.gprsSpecInfo)) return false;
		if(!ObjectUtils.equals(smsSpecInfo, rhs.smsSpecInfo)) return false;
		if(!ObjectUtils.equals(mmsSpecInfo, rhs.mmsSpecInfo)) return false;
		if(!ObjectUtils.equals(ismpSpecInfo, rhs.ismpSpecInfo)) return false;
		if(!ObjectUtils.equals(gsSpecInfo, rhs.gsSpecInfo)) return false;
		if(!ObjectUtils.equals(uSeparateFlag, rhs.uSeparateFlag)) return false;
		if(!ObjectUtils.equals(handset, rhs.handset)) return false;
		if(!ObjectUtils.equals(switchFlag, rhs.switchFlag)) return false;
		if(!ObjectUtils.equals(switchTime, rhs.switchTime)) return false;
		if(!ObjectUtils.equals(commercialStatus, rhs.commercialStatus)) return false;
		if(!ObjectUtils.equals(flowId, rhs.flowId)) return false;
		if(!ObjectUtils.equals(deductAcctFlag, rhs.deductAcctFlag)) return false;
		if(!ObjectUtils.equals(initThreshold, rhs.initThreshold)) return false;
		if(!ObjectUtils.equals(sequenceId, rhs.sequenceId)) return false;
		if(!ObjectUtils.equals(confirmTag, rhs.confirmTag)) return false;
		if(!ObjectUtils.equals(drTypeDesc, rhs.drTypeDesc)) return false;
		if(!ObjectUtils.equals(controlTagForModule, rhs.controlTagForModule)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(companyCode)
		.append(fileCreateDate)
		.append(createTime)
		.append(sequenceNo)
		.append(sessionId)
		.append(refundSessionId)
		.append(originalFile)
		.append(stopCause)
		.append(chaegeupSeq)
		.append(sendPriority)
		.append(channelId)
		.append(stopTime)
		.append(messageSize)
		.append(sendState)
		.append(finalState)
		.append(additionalInfo)
		.append(serviceId)
		.append(drType)
		.append(deviceType)
		.append(roamingOperatorId)
		.append(roamingOperatorCode)
		.append(roamingOperatorGroup)
		.append(roamingOperatorNet)
		.append(roamingCountryId)
		.append(roamingCountryGroup)
		.append(mnsType)
		.append(imei)
		.append(oriCharge)
		.append(bakOriCharge)
		.append(oriUserNumber)
		.append(userNumber)
		.append(connectCalledNumber)
		.append(consumeUserNumber)
		.append(actionType)
		.append(imsi)
		.append(oriImsi)
		.append(bakReguideUserNumber)
		.append(freeresQuery)
		.append(cdrReceiveTime)
		.append(numberClass)
		.append(numberSubclass)
		.append(otherInfo1)
		.append(otherInfo2)
		.append(otherInfo3)
		.append(otherInfo4)
		.append(otherInfo5)
		.append(reserveFields)
		.append(vplmn1)
		.append(vplmn2)
		.append(vplmn3)
		.append(visitCountyCode)
		.append(iddType)
		.append(camelFlag)
		.append(flhFlag)
		.append(continueFlag)
		.append(userOperatorId)
		.append(oppOperatorId)
		.append(numberIdentityAttr)
		.append(oppHplmn1)
		.append(oppHplmn2)
		.append(oppHplmn3)
		.append(oppVplmn1)
		.append(oppVplmn2)
		.append(oppVplmn3)
		.append(originHost)
		.append(originRealm)
		.append(destinationRealm)
		.append(authApplicationId)
		.append(destinationHost)
		.append(userName)
		.append(originStateId)
		.append(productName)
		.append(hostIpAddress)
		.append(vendorId1)
		.append(gsmSpecInfo)
		.append(gprsSpecInfo)
		.append(smsSpecInfo)
		.append(mmsSpecInfo)
		.append(ismpSpecInfo)
		.append(gsSpecInfo)
		.append(uSeparateFlag)
		.append(handset)
		.append(switchFlag)
		.append(switchTime)
		.append(commercialStatus)
		.append(flowId)
		.append(deductAcctFlag)
		.append(initThreshold)
		.append(sequenceId)
		.append(confirmTag)
		.append(drTypeDesc)
		.append(controlTagForModule)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(92);
public static final long BITS_ALL_MARKER = 0x8000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCommon";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "COMPANY_CODE", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "FILE_CREATE_DATE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CREATE_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SEQUENCE_NO", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SESSION_ID", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "REFUND_SESSION_ID", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORIGINAL_FILE", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "STOP_CAUSE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CHAEGEUP_SEQ", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SEND_PRIORITY", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CHANNEL_ID", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "STOP_TIME", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "MESSAGE_SIZE", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SEND_STATE", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "FINAL_STATE", 14, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ADDITIONAL_INFO", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SERVICE_ID", 16, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DR_TYPE", 17, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DEVICE_TYPE", 18, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_OPERATOR_ID", 19, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_OPERATOR_CODE", 20, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_OPERATOR_GROUP", 21, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_OPERATOR_NET", 22, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_COUNTRY_ID", 23, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ROAMING_COUNTRY_GROUP", 24, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "MNS_TYPE", 25, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "IMEI", 26, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORI_CHARGE", 27, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "BAK_ORI_CHARGE", 28, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORI_USER_NUMBER", 29, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "USER_NUMBER", 30, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CONNECT_CALLED_NUMBER", 31, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CONSUME_USER_NUMBER", 32, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ACTION_TYPE", 33, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "IMSI", 34, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORI_IMSI", 35, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "BAK_REGUIDE_USER_NUMBER", 36, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "FREERES_QUERY", 37, SFreeResQuery.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CDR_RECEIVE_TIME", 38, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "NUMBER_CLASS", 39, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "NUMBER_SUBCLASS", 40, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OTHER_INFO1", 41, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OTHER_INFO2", 42, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OTHER_INFO3", 43, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OTHER_INFO4", 44, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OTHER_INFO5", 45, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "RESERVE_FIELDS", 46, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "VPLMN1", 47, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "VPLMN2", 48, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "VPLMN3", 49, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "VISIT_COUNTY_CODE", 50, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "IDD_TYPE", 51, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CAMEL_FLAG", 52, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "FLH_FLAG", 53, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CONTINUE_FLAG", 54, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "USER_OPERATOR_ID", 55, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_OPERATOR_ID", 56, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "NUMBER_IDENTITY_ATTR", 57, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_HPLMN1", 58, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_HPLMN2", 59, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_HPLMN3", 60, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_VPLMN1", 61, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_VPLMN2", 62, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "OPP_VPLMN3", 63, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORIGIN_HOST", 64, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORIGIN_REALM", 65, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DESTINATION_REALM", 66, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "AUTH_APPLICATION_ID", 67, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DESTINATION_HOST", 68, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "USER_NAME", 69, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ORIGIN_STATE_ID", 70, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "PRODUCT_NAME", 71, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "HOST_IP_ADDRESS", 72, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "VENDOR_ID1", 73, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "GSM_SPEC_INFO", 74, SGsmSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "GPRS_SPEC_INFO", 75, SGprsSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SMS_SPEC_INFO", 76, SSmsSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "MMS_SPEC_INFO", 77, SMmsSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "ISMP_SPEC_INFO", 78, SIsmpSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "GS_SPEC_INFO", 79, SGsSpecInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "U_SEPARATE_FLAG", 80, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "HANDSET", 81, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SWITCH_FLAG", 82, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SWITCH_TIME", 83, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "COMMERCIAL_STATUS", 84, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "FLOW_ID", 85, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DEDUCT_ACCT_FLAG", 86, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "INIT_THRESHOLD", 87, SInitThreshold.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "SEQUENCE_ID", 88, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CONFIRM_TAG", 89, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "DR_TYPE_DESC", 90, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCommon.class, "CONTROL_TAG_FOR_MODULE", 91, long.class));
}

}