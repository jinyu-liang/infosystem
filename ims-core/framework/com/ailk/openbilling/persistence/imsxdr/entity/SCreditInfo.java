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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"unlimited","remainCredit","measureId","resourceId","acctId","validDate","expireDate","extraMap"})
@Sdl(module="MXdr")
public class SCreditInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_UNLIMITED = "UNLIMITED";
	public final static String COL_REMAIN_CREDIT = "REMAIN_CREDIT";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_UNLIMITED = 0;
	public final static int IDX_REMAIN_CREDIT = 1;
	public final static int IDX_MEASURE_ID = 2;
	public final static int IDX_RESOURCE_ID = 3;
	public final static int IDX_ACCT_ID = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_EXTRA_MAP = 7;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="unlimited")
	@Sdl
	private short unlimited;

	/**
	 * 
	 */
	@XmlElement(name="remainCredit")
	@Sdl
	private long remainCredit;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

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
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(7, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setUnlimited(short obj){
		this.unlimited = obj;
		onFieldSet(0, obj);
	}

	public short getUnlimited(){
		return unlimited;
	}

	public void setRemainCredit(long obj){
		this.remainCredit = obj;
		onFieldSet(1, obj);
	}

	public long getRemainCredit(){
		return remainCredit;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(2, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(3, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(4, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCreditInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCreditInfo(SCreditInfo arg0){
		copy(arg0);
	}

	public void copy(final SCreditInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		unlimited = rhs.unlimited;
		remainCredit = rhs.remainCredit;
		measureId = rhs.measureId;
		resourceId = rhs.resourceId;
		acctId = rhs.acctId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCreditInfo rhs=(SCreditInfo)rhs0;
		if(!ObjectUtils.equals(unlimited, rhs.unlimited)) return false;
		if(!ObjectUtils.equals(remainCredit, rhs.remainCredit)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(unlimited)
		.append(remainCredit)
		.append(measureId)
		.append(resourceId)
		.append(acctId)
		.append(validDate)
		.append(expireDate)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCreditInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "UNLIMITED", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "REMAIN_CREDIT", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "MEASURE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "RESOURCE_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "ACCT_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "VALID_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "EXPIRE_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditInfo.class, "EXTRA_MAP", 7, Map.class));
}

}