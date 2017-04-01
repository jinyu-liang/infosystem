package com.ailk.openbilling.persistence.imssdl.entity;

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
@XmlType(propOrder={"acctId","triggerEventType","oldAmount","newAmount","billType","capMaxFlag","unbillingFlag","suspendReqFlag","fraudFlag","notifyFlag","opId","orgId","userInfoList"})
@Sdl(module="MImsSyncDef")
public class SLifeCycleChg4Abm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_TRIGGER_EVENT_TYPE = "TRIGGER_EVENT_TYPE";
	public final static String COL_OLD_AMOUNT = "OLD_AMOUNT";
	public final static String COL_NEW_AMOUNT = "NEW_AMOUNT";
	public final static String COL_BILL_TYPE = "BILL_TYPE";
	public final static String COL_CAP_MAX_FLAG = "CAP_MAX_FLAG";
	public final static String COL_UNBILLING_FLAG = "UNBILLING_FLAG";
	public final static String COL_SUSPEND_REQ_FLAG = "SUSPEND_REQ_FLAG";
	public final static String COL_FRAUD_FLAG = "FRAUD_FLAG";
	public final static String COL_NOTIFY_FLAG = "NOTIFY_FLAG";
	public final static String COL_OP_ID = "OP_ID";
	public final static String COL_ORG_ID = "ORG_ID";
	public final static String COL_USER_INFO_LIST = "USER_INFO_LIST";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_TRIGGER_EVENT_TYPE = 1;
	public final static int IDX_OLD_AMOUNT = 2;
	public final static int IDX_NEW_AMOUNT = 3;
	public final static int IDX_BILL_TYPE = 4;
	public final static int IDX_CAP_MAX_FLAG = 5;
	public final static int IDX_UNBILLING_FLAG = 6;
	public final static int IDX_SUSPEND_REQ_FLAG = 7;
	public final static int IDX_FRAUD_FLAG = 8;
	public final static int IDX_NOTIFY_FLAG = 9;
	public final static int IDX_OP_ID = 10;
	public final static int IDX_ORG_ID = 11;
	public final static int IDX_USER_INFO_LIST = 12;

	/**
	 * 
	 */
	@XmlElement(name="userInfoList")
	@Sdl
	private List<SUserInfo> userInfoList;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="triggerEventType")
	@Sdl
	private int triggerEventType;

	/**
	 * 
	 */
	@XmlElement(name="oldAmount")
	@Sdl
	private long oldAmount;

	/**
	 * 
	 */
	@XmlElement(name="newAmount")
	@Sdl
	private long newAmount;

	/**
	 * 
	 */
	@XmlElement(name="billType")
	@Sdl
	private int billType;

	/**
	 * 
	 */
	@XmlElement(name="capMaxFlag")
	@Sdl
	private int capMaxFlag;

	/**
	 * 
	 */
	@XmlElement(name="unbillingFlag")
	@Sdl
	private int unbillingFlag;

	/**
	 * 
	 */
	@XmlElement(name="suspendReqFlag")
	@Sdl
	private int suspendReqFlag;

	/**
	 * 
	 */
	@XmlElement(name="fraudFlag")
	@Sdl
	private int fraudFlag;

	/**
	 * 
	 */
	@XmlElement(name="notifyFlag")
	@Sdl
	private short notifyFlag;

	/**
	 * 
	 */
	@XmlElement(name="opId")
	@Sdl
	private long opId;

	/**
	 * 
	 */
	@XmlElement(name="orgId")
	@Sdl
	private int orgId;

	public void setUserInfoList(List<SUserInfo> obj){
		this.userInfoList = obj;
		onFieldSet(12, obj);
	}

	public List<SUserInfo> getUserInfoList(){
		return userInfoList;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setTriggerEventType(int obj){
		this.triggerEventType = obj;
		onFieldSet(1, obj);
	}

	public int getTriggerEventType(){
		return triggerEventType;
	}

	public void setOldAmount(long obj){
		this.oldAmount = obj;
		onFieldSet(2, obj);
	}

	public long getOldAmount(){
		return oldAmount;
	}

	public void setNewAmount(long obj){
		this.newAmount = obj;
		onFieldSet(3, obj);
	}

	public long getNewAmount(){
		return newAmount;
	}

	public void setBillType(int obj){
		this.billType = obj;
		onFieldSet(4, obj);
	}

	public int getBillType(){
		return billType;
	}

	public void setCapMaxFlag(int obj){
		this.capMaxFlag = obj;
		onFieldSet(5, obj);
	}

	public int getCapMaxFlag(){
		return capMaxFlag;
	}

	public void setUnbillingFlag(int obj){
		this.unbillingFlag = obj;
		onFieldSet(6, obj);
	}

	public int getUnbillingFlag(){
		return unbillingFlag;
	}

	public void setSuspendReqFlag(int obj){
		this.suspendReqFlag = obj;
		onFieldSet(7, obj);
	}

	public int getSuspendReqFlag(){
		return suspendReqFlag;
	}

	public void setFraudFlag(int obj){
		this.fraudFlag = obj;
		onFieldSet(8, obj);
	}

	public int getFraudFlag(){
		return fraudFlag;
	}

	public void setNotifyFlag(short obj){
		this.notifyFlag = obj;
		onFieldSet(9, obj);
	}

	public short getNotifyFlag(){
		return notifyFlag;
	}

	public void setOpId(long obj){
		this.opId = obj;
		onFieldSet(10, obj);
	}

	public long getOpId(){
		return opId;
	}

	public void setOrgId(int obj){
		this.orgId = obj;
		onFieldSet(11, obj);
	}

	public int getOrgId(){
		return orgId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SLifeCycleChg4Abm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 13; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SLifeCycleChg4Abm(SLifeCycleChg4Abm arg0){
		copy(arg0);
	}

	public void copy(final SLifeCycleChg4Abm rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		acctId = rhs.acctId;
		triggerEventType = rhs.triggerEventType;
		oldAmount = rhs.oldAmount;
		newAmount = rhs.newAmount;
		billType = rhs.billType;
		capMaxFlag = rhs.capMaxFlag;
		unbillingFlag = rhs.unbillingFlag;
		suspendReqFlag = rhs.suspendReqFlag;
		fraudFlag = rhs.fraudFlag;
		notifyFlag = rhs.notifyFlag;
		opId = rhs.opId;
		orgId = rhs.orgId;
		userInfoList = rhs.userInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLifeCycleChg4Abm rhs=(SLifeCycleChg4Abm)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(triggerEventType, rhs.triggerEventType)) return false;
		if(!ObjectUtils.equals(oldAmount, rhs.oldAmount)) return false;
		if(!ObjectUtils.equals(newAmount, rhs.newAmount)) return false;
		if(!ObjectUtils.equals(billType, rhs.billType)) return false;
		if(!ObjectUtils.equals(capMaxFlag, rhs.capMaxFlag)) return false;
		if(!ObjectUtils.equals(unbillingFlag, rhs.unbillingFlag)) return false;
		if(!ObjectUtils.equals(suspendReqFlag, rhs.suspendReqFlag)) return false;
		if(!ObjectUtils.equals(fraudFlag, rhs.fraudFlag)) return false;
		if(!ObjectUtils.equals(notifyFlag, rhs.notifyFlag)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(orgId, rhs.orgId)) return false;
		if(!ObjectUtils.equals(userInfoList, rhs.userInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(triggerEventType)
		.append(oldAmount)
		.append(newAmount)
		.append(billType)
		.append(capMaxFlag)
		.append(unbillingFlag)
		.append(suspendReqFlag)
		.append(fraudFlag)
		.append(notifyFlag)
		.append(opId)
		.append(orgId)
		.append(userInfoList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(13);
public static final long BITS_ALL_MARKER = 0x1000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChg4Abm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "TRIGGER_EVENT_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "OLD_AMOUNT", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "NEW_AMOUNT", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "BILL_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "CAP_MAX_FLAG", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "UNBILLING_FLAG", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "SUSPEND_REQ_FLAG", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "FRAUD_FLAG", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "NOTIFY_FLAG", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "OP_ID", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "ORG_ID", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChg4Abm.class, "USER_INFO_LIST", 12, List.class));
}

}