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
@XmlType(propOrder={"resourceId","acctId","extendDays","reduceDays","triggerEventType","capMaxFlag","nbalance","pbalance","listbalance"})
@Sdl(module="MImsSyncDef")
public class SUserValidityChg extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_EXTEND_DAYS = "EXTEND_DAYS";
	public final static String COL_REDUCE_DAYS = "REDUCE_DAYS";
	public final static String COL_TRIGGER_EVENT_TYPE = "TRIGGER_EVENT_TYPE";
	public final static String COL_CAP_MAX_FLAG = "CAP_MAX_FLAG";
	public final static String COL_NBALANCE = "NBALANCE";
	public final static String COL_PBALANCE = "PBALANCE";
	public final static String COL_LISTBALANCE = "LISTBALANCE";
	public final static int IDX_RESOURCE_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_EXTEND_DAYS = 2;
	public final static int IDX_REDUCE_DAYS = 3;
	public final static int IDX_TRIGGER_EVENT_TYPE = 4;
	public final static int IDX_CAP_MAX_FLAG = 5;
	public final static int IDX_NBALANCE = 6;
	public final static int IDX_PBALANCE = 7;
	public final static int IDX_LISTBALANCE = 8;

	/**
	 * 
	 */
	@XmlElement(name="listbalance")
	@Sdl
	private List<SBalance> listbalance;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="extendDays")
	@Sdl
	private int extendDays;

	/**
	 * 
	 */
	@XmlElement(name="reduceDays")
	@Sdl
	private int reduceDays;

	/**
	 * 
	 */
	@XmlElement(name="triggerEventType")
	@Sdl
	private int triggerEventType;

	/**
	 * 
	 */
	@XmlElement(name="capMaxFlag")
	@Sdl
	private long capMaxFlag;

	/**
	 * 
	 */
	@XmlElement(name="nbalance")
	@Sdl
	private long nbalance;

	/**
	 * 
	 */
	@XmlElement(name="pbalance")
	@Sdl
	private long pbalance;

	public void setListbalance(List<SBalance> obj){
		this.listbalance = obj;
		onFieldSet(8, obj);
	}

	public List<SBalance> getListbalance(){
		return listbalance;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(0, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setExtendDays(int obj){
		this.extendDays = obj;
		onFieldSet(2, obj);
	}

	public int getExtendDays(){
		return extendDays;
	}

	public void setReduceDays(int obj){
		this.reduceDays = obj;
		onFieldSet(3, obj);
	}

	public int getReduceDays(){
		return reduceDays;
	}

	public void setTriggerEventType(int obj){
		this.triggerEventType = obj;
		onFieldSet(4, obj);
	}

	public int getTriggerEventType(){
		return triggerEventType;
	}

	public void setCapMaxFlag(long obj){
		this.capMaxFlag = obj;
		onFieldSet(5, obj);
	}

	public long getCapMaxFlag(){
		return capMaxFlag;
	}

	public void setNbalance(long obj){
		this.nbalance = obj;
		onFieldSet(6, obj);
	}

	public long getNbalance(){
		return nbalance;
	}

	public void setPbalance(long obj){
		this.pbalance = obj;
		onFieldSet(7, obj);
	}

	public long getPbalance(){
		return pbalance;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserValidityChg(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserValidityChg(SUserValidityChg arg0){
		copy(arg0);
	}

	public void copy(final SUserValidityChg rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		resourceId = rhs.resourceId;
		acctId = rhs.acctId;
		extendDays = rhs.extendDays;
		reduceDays = rhs.reduceDays;
		triggerEventType = rhs.triggerEventType;
		capMaxFlag = rhs.capMaxFlag;
		nbalance = rhs.nbalance;
		pbalance = rhs.pbalance;
		listbalance = rhs.listbalance;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserValidityChg rhs=(SUserValidityChg)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(extendDays, rhs.extendDays)) return false;
		if(!ObjectUtils.equals(reduceDays, rhs.reduceDays)) return false;
		if(!ObjectUtils.equals(triggerEventType, rhs.triggerEventType)) return false;
		if(!ObjectUtils.equals(capMaxFlag, rhs.capMaxFlag)) return false;
		if(!ObjectUtils.equals(nbalance, rhs.nbalance)) return false;
		if(!ObjectUtils.equals(pbalance, rhs.pbalance)) return false;
		if(!ObjectUtils.equals(listbalance, rhs.listbalance)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(acctId)
		.append(extendDays)
		.append(reduceDays)
		.append(triggerEventType)
		.append(capMaxFlag)
		.append(nbalance)
		.append(pbalance)
		.append(listbalance)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SUserValidityChg";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "RESOURCE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "EXTEND_DAYS", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "REDUCE_DAYS", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "TRIGGER_EVENT_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "CAP_MAX_FLAG", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "NBALANCE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "PBALANCE", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserValidityChg.class, "LISTBALANCE", 8, List.class));
}

}