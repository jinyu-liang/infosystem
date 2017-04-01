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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custType","custId","accId","userId","defaultAccId","billableAccId","userType","listenLanguage","readLanguage","brand","custBirthdayFlag","custGender","custJobcode","userLifeCycle","custAging","accAging","userAging","firstActivateFlag","createDate","hplmn1","hplmn2","hplmn3","customerSegment","userCycleExpireDate","exemptType","nextUserCycle","nextUserCycleExpireDate","custClass","billBeginDate","billEndDate","acctBillCycle","reguideUsageAcctId","reguideUsageUserId","userNumber","imsi","bakReguideUserNumber","userOperatorId","specAcctType","reguideUsageSpecAcctType","userSpecType"})
@Sdl(module="MXdr")
public class SUserInfoBase extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CUST_TYPE = "CUST_TYPE";
	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACC_ID = "ACC_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_DEFAULT_ACC_ID = "DEFAULT_ACC_ID";
	public final static String COL_BILLABLE_ACC_ID = "BILLABLE_ACC_ID";
	public final static String COL_USER_TYPE = "USER_TYPE";
	public final static String COL_LISTEN_LANGUAGE = "LISTEN_LANGUAGE";
	public final static String COL_READ_LANGUAGE = "READ_LANGUAGE";
	public final static String COL_BRAND = "BRAND";
	public final static String COL_CUST_BIRTHDAY_FLAG = "CUST_BIRTHDAY_FLAG";
	public final static String COL_CUST_GENDER = "CUST_GENDER";
	public final static String COL_CUST_JOBCODE = "CUST_JOBCODE";
	public final static String COL_USER_LIFE_CYCLE = "USER_LIFE_CYCLE";
	public final static String COL_CUST_AGING = "CUST_AGING";
	public final static String COL_ACC_AGING = "ACC_AGING";
	public final static String COL_USER_AGING = "USER_AGING";
	public final static String COL_FIRST_ACTIVATE_FLAG = "FIRST_ACTIVATE_FLAG";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_HPLMN1 = "HPLMN1";
	public final static String COL_HPLMN2 = "HPLMN2";
	public final static String COL_HPLMN3 = "HPLMN3";
	public final static String COL_CUSTOMER_SEGMENT = "CUSTOMER_SEGMENT";
	public final static String COL_USER_CYCLE_EXPIRE_DATE = "USER_CYCLE_EXPIRE_DATE";
	public final static String COL_EXEMPT_TYPE = "EXEMPT_TYPE";
	public final static String COL_NEXT_USER_CYCLE = "NEXT_USER_CYCLE";
	public final static String COL_NEXT_USER_CYCLE_EXPIRE_DATE = "NEXT_USER_CYCLE_EXPIRE_DATE";
	public final static String COL_CUST_CLASS = "CUST_CLASS";
	public final static String COL_BILL_BEGIN_DATE = "BILL_BEGIN_DATE";
	public final static String COL_BILL_END_DATE = "BILL_END_DATE";
	public final static String COL_ACCT_BILL_CYCLE = "ACCT_BILL_CYCLE";
	public final static String COL_REGUIDE_USAGE_ACCT_ID = "REGUIDE_USAGE_ACCT_ID";
	public final static String COL_REGUIDE_USAGE_USER_ID = "REGUIDE_USAGE_USER_ID";
	public final static String COL_USER_NUMBER = "USER_NUMBER";
	public final static String COL_IMSI = "IMSI";
	public final static String COL_BAK_REGUIDE_USER_NUMBER = "BAK_REGUIDE_USER_NUMBER";
	public final static String COL_USER_OPERATOR_ID = "USER_OPERATOR_ID";
	public final static String COL_SPEC_ACCT_TYPE = "SPEC_ACCT_TYPE";
	public final static String COL_REGUIDE_USAGE_SPEC_ACCT_TYPE = "REGUIDE_USAGE_SPEC_ACCT_TYPE";
	public final static String COL_USER_SPEC_TYPE = "USER_SPEC_TYPE";
	public final static int IDX_CUST_TYPE = 0;
	public final static int IDX_CUST_ID = 1;
	public final static int IDX_ACC_ID = 2;
	public final static int IDX_USER_ID = 3;
	public final static int IDX_DEFAULT_ACC_ID = 4;
	public final static int IDX_BILLABLE_ACC_ID = 5;
	public final static int IDX_USER_TYPE = 6;
	public final static int IDX_LISTEN_LANGUAGE = 7;
	public final static int IDX_READ_LANGUAGE = 8;
	public final static int IDX_BRAND = 9;
	public final static int IDX_CUST_BIRTHDAY_FLAG = 10;
	public final static int IDX_CUST_GENDER = 11;
	public final static int IDX_CUST_JOBCODE = 12;
	public final static int IDX_USER_LIFE_CYCLE = 13;
	public final static int IDX_CUST_AGING = 14;
	public final static int IDX_ACC_AGING = 15;
	public final static int IDX_USER_AGING = 16;
	public final static int IDX_FIRST_ACTIVATE_FLAG = 17;
	public final static int IDX_CREATE_DATE = 18;
	public final static int IDX_HPLMN1 = 19;
	public final static int IDX_HPLMN2 = 20;
	public final static int IDX_HPLMN3 = 21;
	public final static int IDX_CUSTOMER_SEGMENT = 22;
	public final static int IDX_USER_CYCLE_EXPIRE_DATE = 23;
	public final static int IDX_EXEMPT_TYPE = 24;
	public final static int IDX_NEXT_USER_CYCLE = 25;
	public final static int IDX_NEXT_USER_CYCLE_EXPIRE_DATE = 26;
	public final static int IDX_CUST_CLASS = 27;
	public final static int IDX_BILL_BEGIN_DATE = 28;
	public final static int IDX_BILL_END_DATE = 29;
	public final static int IDX_ACCT_BILL_CYCLE = 30;
	public final static int IDX_REGUIDE_USAGE_ACCT_ID = 31;
	public final static int IDX_REGUIDE_USAGE_USER_ID = 32;
	public final static int IDX_USER_NUMBER = 33;
	public final static int IDX_IMSI = 34;
	public final static int IDX_BAK_REGUIDE_USER_NUMBER = 35;
	public final static int IDX_USER_OPERATOR_ID = 36;
	public final static int IDX_SPEC_ACCT_TYPE = 37;
	public final static int IDX_REGUIDE_USAGE_SPEC_ACCT_TYPE = 38;
	public final static int IDX_USER_SPEC_TYPE = 39;

	/**
	 * 
	 */
	@XmlElement(name="exemptType")
	@Sdl
	private SExemptType exemptType;

	/**
	 * 
	 */
	@XmlElement(name="acctBillCycle")
	@Sdl
	private SAcctBillCycle acctBillCycle;

	/**
	 * 
	 */
	@XmlElement(name="custType")
	@Sdl
	private int custType;

	/**
	 * 
	 */
	@XmlElement(name="custId")
	@Sdl
	private long custId;

	/**
	 * 
	 */
	@XmlElement(name="accId")
	@Sdl
	private long accId;

	/**
	 * 
	 */
	@XmlElement(name="userId")
	@Sdl
	private long userId;

	/**
	 * 
	 */
	@XmlElement(name="defaultAccId")
	@Sdl
	private long defaultAccId;

	/**
	 * 
	 */
	@XmlElement(name="billableAccId")
	@Sdl
	private long billableAccId;

	/**
	 * 
	 */
	@XmlElement(name="userType")
	@Sdl
	private int userType;

	/**
	 * 
	 */
	@XmlElement(name="listenLanguage")
	@Sdl
	private int listenLanguage;

	/**
	 * 
	 */
	@XmlElement(name="readLanguage")
	@Sdl
	private int readLanguage;

	/**
	 * 
	 */
	@XmlElement(name="brand")
	@Sdl
	private int brand;

	/**
	 * 
	 */
	@XmlElement(name="custBirthdayFlag")
	@Sdl
	private int custBirthdayFlag;

	/**
	 * 
	 */
	@XmlElement(name="custGender")
	@Sdl
	private int custGender;

	/**
	 * 
	 */
	@XmlElement(name="custJobcode")
	@Sdl
	private int custJobcode;

	/**
	 * 
	 */
	@XmlElement(name="userLifeCycle")
	@Sdl
	private int userLifeCycle;

	/**
	 * 
	 */
	@XmlElement(name="custAging")
	@Sdl
	private int custAging;

	/**
	 * 
	 */
	@XmlElement(name="accAging")
	@Sdl
	private int accAging;

	/**
	 * 
	 */
	@XmlElement(name="userAging")
	@Sdl
	private int userAging;

	/**
	 * 
	 */
	@XmlElement(name="firstActivateFlag")
	@Sdl
	private String firstActivateFlag;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private long createDate;

	/**
	 * 
	 */
	@XmlElement(name="hplmn1")
	@Sdl
	private String hplmn1;

	/**
	 * 
	 */
	@XmlElement(name="hplmn2")
	@Sdl
	private String hplmn2;

	/**
	 * 
	 */
	@XmlElement(name="hplmn3")
	@Sdl
	private String hplmn3;

	/**
	 * 
	 */
	@XmlElement(name="customerSegment")
	@Sdl
	private int customerSegment;

	/**
	 * 
	 */
	@XmlElement(name="userCycleExpireDate")
	@Sdl
	private int userCycleExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="nextUserCycle")
	@Sdl
	private long nextUserCycle;

	/**
	 * 
	 */
	@XmlElement(name="nextUserCycleExpireDate")
	@Sdl
	private long nextUserCycleExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="custClass")
	@Sdl
	private int custClass;

	/**
	 * 
	 */
	@XmlElement(name="billBeginDate")
	@Sdl
	private long billBeginDate;

	/**
	 * 
	 */
	@XmlElement(name="billEndDate")
	@Sdl
	private long billEndDate;

	/**
	 * 
	 */
	@XmlElement(name="reguideUsageAcctId")
	@Sdl
	private long reguideUsageAcctId;

	/**
	 * 
	 */
	@XmlElement(name="reguideUsageUserId")
	@Sdl
	private long reguideUsageUserId;

	/**
	 * 
	 */
	@XmlElement(name="userNumber")
	@Sdl
	private String userNumber;

	/**
	 * 
	 */
	@XmlElement(name="imsi")
	@Sdl
	private String imsi;

	/**
	 * 
	 */
	@XmlElement(name="bakReguideUserNumber")
	@Sdl
	private String bakReguideUserNumber;

	/**
	 * 
	 */
	@XmlElement(name="userOperatorId")
	@Sdl
	private short userOperatorId;

	/**
	 * 
	 */
	@XmlElement(name="specAcctType")
	@Sdl
	private int specAcctType;

	/**
	 * 
	 */
	@XmlElement(name="reguideUsageSpecAcctType")
	@Sdl
	private int reguideUsageSpecAcctType;

	/**
	 * 
	 */
	@XmlElement(name="userSpecType")
	@Sdl
	private int userSpecType;

	public void setExemptType(SExemptType obj){
		this.exemptType = obj;
		onFieldSet(24, obj);
	}

	public SExemptType getExemptType(){
		return exemptType;
	}

	public void setAcctBillCycle(SAcctBillCycle obj){
		this.acctBillCycle = obj;
		onFieldSet(30, obj);
	}

	public SAcctBillCycle getAcctBillCycle(){
		return acctBillCycle;
	}

	public void setCustType(int obj){
		this.custType = obj;
		onFieldSet(0, obj);
	}

	public int getCustType(){
		return custType;
	}

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(1, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAccId(long obj){
		this.accId = obj;
		onFieldSet(2, obj);
	}

	public long getAccId(){
		return accId;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(3, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setDefaultAccId(long obj){
		this.defaultAccId = obj;
		onFieldSet(4, obj);
	}

	public long getDefaultAccId(){
		return defaultAccId;
	}

	public void setBillableAccId(long obj){
		this.billableAccId = obj;
		onFieldSet(5, obj);
	}

	public long getBillableAccId(){
		return billableAccId;
	}

	public void setUserType(int obj){
		this.userType = obj;
		onFieldSet(6, obj);
	}

	public int getUserType(){
		return userType;
	}

	public void setListenLanguage(int obj){
		this.listenLanguage = obj;
		onFieldSet(7, obj);
	}

	public int getListenLanguage(){
		return listenLanguage;
	}

	public void setReadLanguage(int obj){
		this.readLanguage = obj;
		onFieldSet(8, obj);
	}

	public int getReadLanguage(){
		return readLanguage;
	}

	public void setBrand(int obj){
		this.brand = obj;
		onFieldSet(9, obj);
	}

	public int getBrand(){
		return brand;
	}

	public void setCustBirthdayFlag(int obj){
		this.custBirthdayFlag = obj;
		onFieldSet(10, obj);
	}

	public int getCustBirthdayFlag(){
		return custBirthdayFlag;
	}

	public void setCustGender(int obj){
		this.custGender = obj;
		onFieldSet(11, obj);
	}

	public int getCustGender(){
		return custGender;
	}

	public void setCustJobcode(int obj){
		this.custJobcode = obj;
		onFieldSet(12, obj);
	}

	public int getCustJobcode(){
		return custJobcode;
	}

	public void setUserLifeCycle(int obj){
		this.userLifeCycle = obj;
		onFieldSet(13, obj);
	}

	public int getUserLifeCycle(){
		return userLifeCycle;
	}

	public void setCustAging(int obj){
		this.custAging = obj;
		onFieldSet(14, obj);
	}

	public int getCustAging(){
		return custAging;
	}

	public void setAccAging(int obj){
		this.accAging = obj;
		onFieldSet(15, obj);
	}

	public int getAccAging(){
		return accAging;
	}

	public void setUserAging(int obj){
		this.userAging = obj;
		onFieldSet(16, obj);
	}

	public int getUserAging(){
		return userAging;
	}

	public void setFirstActivateFlag(String obj){
		this.firstActivateFlag = obj;
		onFieldSet(17, obj);
	}

	public String getFirstActivateFlag(){
		return firstActivateFlag;
	}

	public void setCreateDate(long obj){
		this.createDate = obj;
		onFieldSet(18, obj);
	}

	public long getCreateDate(){
		return createDate;
	}

	public void setHplmn1(String obj){
		this.hplmn1 = obj;
		onFieldSet(19, obj);
	}

	public String getHplmn1(){
		return hplmn1;
	}

	public void setHplmn2(String obj){
		this.hplmn2 = obj;
		onFieldSet(20, obj);
	}

	public String getHplmn2(){
		return hplmn2;
	}

	public void setHplmn3(String obj){
		this.hplmn3 = obj;
		onFieldSet(21, obj);
	}

	public String getHplmn3(){
		return hplmn3;
	}

	public void setCustomerSegment(int obj){
		this.customerSegment = obj;
		onFieldSet(22, obj);
	}

	public int getCustomerSegment(){
		return customerSegment;
	}

	public void setUserCycleExpireDate(int obj){
		this.userCycleExpireDate = obj;
		onFieldSet(23, obj);
	}

	public int getUserCycleExpireDate(){
		return userCycleExpireDate;
	}

	public void setNextUserCycle(long obj){
		this.nextUserCycle = obj;
		onFieldSet(25, obj);
	}

	public long getNextUserCycle(){
		return nextUserCycle;
	}

	public void setNextUserCycleExpireDate(long obj){
		this.nextUserCycleExpireDate = obj;
		onFieldSet(26, obj);
	}

	public long getNextUserCycleExpireDate(){
		return nextUserCycleExpireDate;
	}

	public void setCustClass(int obj){
		this.custClass = obj;
		onFieldSet(27, obj);
	}

	public int getCustClass(){
		return custClass;
	}

	public void setBillBeginDate(long obj){
		this.billBeginDate = obj;
		onFieldSet(28, obj);
	}

	public long getBillBeginDate(){
		return billBeginDate;
	}

	public void setBillEndDate(long obj){
		this.billEndDate = obj;
		onFieldSet(29, obj);
	}

	public long getBillEndDate(){
		return billEndDate;
	}

	public void setReguideUsageAcctId(long obj){
		this.reguideUsageAcctId = obj;
		onFieldSet(31, obj);
	}

	public long getReguideUsageAcctId(){
		return reguideUsageAcctId;
	}

	public void setReguideUsageUserId(long obj){
		this.reguideUsageUserId = obj;
		onFieldSet(32, obj);
	}

	public long getReguideUsageUserId(){
		return reguideUsageUserId;
	}

	public void setUserNumber(String obj){
		this.userNumber = obj;
		onFieldSet(33, obj);
	}

	public String getUserNumber(){
		return userNumber;
	}

	public void setImsi(String obj){
		this.imsi = obj;
		onFieldSet(34, obj);
	}

	public String getImsi(){
		return imsi;
	}

	public void setBakReguideUserNumber(String obj){
		this.bakReguideUserNumber = obj;
		onFieldSet(35, obj);
	}

	public String getBakReguideUserNumber(){
		return bakReguideUserNumber;
	}

	public void setUserOperatorId(short obj){
		this.userOperatorId = obj;
		onFieldSet(36, obj);
	}

	public short getUserOperatorId(){
		return userOperatorId;
	}

	public void setSpecAcctType(int obj){
		this.specAcctType = obj;
		onFieldSet(37, obj);
	}

	public int getSpecAcctType(){
		return specAcctType;
	}

	public void setReguideUsageSpecAcctType(int obj){
		this.reguideUsageSpecAcctType = obj;
		onFieldSet(38, obj);
	}

	public int getReguideUsageSpecAcctType(){
		return reguideUsageSpecAcctType;
	}

	public void setUserSpecType(int obj){
		this.userSpecType = obj;
		onFieldSet(39, obj);
	}

	public int getUserSpecType(){
		return userSpecType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserInfoBase(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 40; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserInfoBase(SUserInfoBase arg0){
		copy(arg0);
	}

	public void copy(final SUserInfoBase rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		custType = rhs.custType;
		custId = rhs.custId;
		accId = rhs.accId;
		userId = rhs.userId;
		defaultAccId = rhs.defaultAccId;
		billableAccId = rhs.billableAccId;
		userType = rhs.userType;
		listenLanguage = rhs.listenLanguage;
		readLanguage = rhs.readLanguage;
		brand = rhs.brand;
		custBirthdayFlag = rhs.custBirthdayFlag;
		custGender = rhs.custGender;
		custJobcode = rhs.custJobcode;
		userLifeCycle = rhs.userLifeCycle;
		custAging = rhs.custAging;
		accAging = rhs.accAging;
		userAging = rhs.userAging;
		firstActivateFlag = rhs.firstActivateFlag;
		createDate = rhs.createDate;
		hplmn1 = rhs.hplmn1;
		hplmn2 = rhs.hplmn2;
		hplmn3 = rhs.hplmn3;
		customerSegment = rhs.customerSegment;
		userCycleExpireDate = rhs.userCycleExpireDate;
		exemptType = rhs.exemptType;
		nextUserCycle = rhs.nextUserCycle;
		nextUserCycleExpireDate = rhs.nextUserCycleExpireDate;
		custClass = rhs.custClass;
		billBeginDate = rhs.billBeginDate;
		billEndDate = rhs.billEndDate;
		acctBillCycle = rhs.acctBillCycle;
		reguideUsageAcctId = rhs.reguideUsageAcctId;
		reguideUsageUserId = rhs.reguideUsageUserId;
		userNumber = rhs.userNumber;
		imsi = rhs.imsi;
		bakReguideUserNumber = rhs.bakReguideUserNumber;
		userOperatorId = rhs.userOperatorId;
		specAcctType = rhs.specAcctType;
		reguideUsageSpecAcctType = rhs.reguideUsageSpecAcctType;
		userSpecType = rhs.userSpecType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserInfoBase rhs=(SUserInfoBase)rhs0;
		if(!ObjectUtils.equals(custType, rhs.custType)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(accId, rhs.accId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(defaultAccId, rhs.defaultAccId)) return false;
		if(!ObjectUtils.equals(billableAccId, rhs.billableAccId)) return false;
		if(!ObjectUtils.equals(userType, rhs.userType)) return false;
		if(!ObjectUtils.equals(listenLanguage, rhs.listenLanguage)) return false;
		if(!ObjectUtils.equals(readLanguage, rhs.readLanguage)) return false;
		if(!ObjectUtils.equals(brand, rhs.brand)) return false;
		if(!ObjectUtils.equals(custBirthdayFlag, rhs.custBirthdayFlag)) return false;
		if(!ObjectUtils.equals(custGender, rhs.custGender)) return false;
		if(!ObjectUtils.equals(custJobcode, rhs.custJobcode)) return false;
		if(!ObjectUtils.equals(userLifeCycle, rhs.userLifeCycle)) return false;
		if(!ObjectUtils.equals(custAging, rhs.custAging)) return false;
		if(!ObjectUtils.equals(accAging, rhs.accAging)) return false;
		if(!ObjectUtils.equals(userAging, rhs.userAging)) return false;
		if(!ObjectUtils.equals(firstActivateFlag, rhs.firstActivateFlag)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(hplmn1, rhs.hplmn1)) return false;
		if(!ObjectUtils.equals(hplmn2, rhs.hplmn2)) return false;
		if(!ObjectUtils.equals(hplmn3, rhs.hplmn3)) return false;
		if(!ObjectUtils.equals(customerSegment, rhs.customerSegment)) return false;
		if(!ObjectUtils.equals(userCycleExpireDate, rhs.userCycleExpireDate)) return false;
		if(!ObjectUtils.equals(exemptType, rhs.exemptType)) return false;
		if(!ObjectUtils.equals(nextUserCycle, rhs.nextUserCycle)) return false;
		if(!ObjectUtils.equals(nextUserCycleExpireDate, rhs.nextUserCycleExpireDate)) return false;
		if(!ObjectUtils.equals(custClass, rhs.custClass)) return false;
		if(!ObjectUtils.equals(billBeginDate, rhs.billBeginDate)) return false;
		if(!ObjectUtils.equals(billEndDate, rhs.billEndDate)) return false;
		if(!ObjectUtils.equals(acctBillCycle, rhs.acctBillCycle)) return false;
		if(!ObjectUtils.equals(reguideUsageAcctId, rhs.reguideUsageAcctId)) return false;
		if(!ObjectUtils.equals(reguideUsageUserId, rhs.reguideUsageUserId)) return false;
		if(!ObjectUtils.equals(userNumber, rhs.userNumber)) return false;
		if(!ObjectUtils.equals(imsi, rhs.imsi)) return false;
		if(!ObjectUtils.equals(bakReguideUserNumber, rhs.bakReguideUserNumber)) return false;
		if(!ObjectUtils.equals(userOperatorId, rhs.userOperatorId)) return false;
		if(!ObjectUtils.equals(specAcctType, rhs.specAcctType)) return false;
		if(!ObjectUtils.equals(reguideUsageSpecAcctType, rhs.reguideUsageSpecAcctType)) return false;
		if(!ObjectUtils.equals(userSpecType, rhs.userSpecType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custType)
		.append(custId)
		.append(accId)
		.append(userId)
		.append(defaultAccId)
		.append(billableAccId)
		.append(userType)
		.append(listenLanguage)
		.append(readLanguage)
		.append(brand)
		.append(custBirthdayFlag)
		.append(custGender)
		.append(custJobcode)
		.append(userLifeCycle)
		.append(custAging)
		.append(accAging)
		.append(userAging)
		.append(firstActivateFlag)
		.append(createDate)
		.append(hplmn1)
		.append(hplmn2)
		.append(hplmn3)
		.append(customerSegment)
		.append(userCycleExpireDate)
		.append(exemptType)
		.append(nextUserCycle)
		.append(nextUserCycleExpireDate)
		.append(custClass)
		.append(billBeginDate)
		.append(billEndDate)
		.append(acctBillCycle)
		.append(reguideUsageAcctId)
		.append(reguideUsageUserId)
		.append(userNumber)
		.append(imsi)
		.append(bakReguideUserNumber)
		.append(userOperatorId)
		.append(specAcctType)
		.append(reguideUsageSpecAcctType)
		.append(userSpecType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(40);
public static final long BITS_ALL_MARKER = 0x8000000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SUserInfoBase";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "ACC_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "DEFAULT_ACC_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "BILLABLE_ACC_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "LISTEN_LANGUAGE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "READ_LANGUAGE", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "BRAND", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_BIRTHDAY_FLAG", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_GENDER", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_JOBCODE", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_LIFE_CYCLE", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_AGING", 14, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "ACC_AGING", 15, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_AGING", 16, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "FIRST_ACTIVATE_FLAG", 17, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CREATE_DATE", 18, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "HPLMN1", 19, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "HPLMN2", 20, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "HPLMN3", 21, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUSTOMER_SEGMENT", 22, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_CYCLE_EXPIRE_DATE", 23, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "EXEMPT_TYPE", 24, SExemptType.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "NEXT_USER_CYCLE", 25, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "NEXT_USER_CYCLE_EXPIRE_DATE", 26, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "CUST_CLASS", 27, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "BILL_BEGIN_DATE", 28, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "BILL_END_DATE", 29, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "ACCT_BILL_CYCLE", 30, SAcctBillCycle.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "REGUIDE_USAGE_ACCT_ID", 31, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "REGUIDE_USAGE_USER_ID", 32, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_NUMBER", 33, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "IMSI", 34, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "BAK_REGUIDE_USER_NUMBER", 35, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_OPERATOR_ID", 36, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "SPEC_ACCT_TYPE", 37, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "REGUIDE_USAGE_SPEC_ACCT_TYPE", 38, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfoBase.class, "USER_SPEC_TYPE", 39, int.class));
}

}