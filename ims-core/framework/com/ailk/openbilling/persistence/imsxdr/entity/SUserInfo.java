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
@XmlType(propOrder={"activePeriod","suspendPeriod","disablePeriod","brandId","maximunBalance","fraudLock","userLifeCycle","billingType","extraMap"})
@Sdl(module="MXdr")
public class SUserInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACTIVE_PERIOD = "ACTIVE_PERIOD";
	public final static String COL_SUSPEND_PERIOD = "SUSPEND_PERIOD";
	public final static String COL_DISABLE_PERIOD = "DISABLE_PERIOD";
	public final static String COL_BRAND_ID = "BRAND_ID";
	public final static String COL_MAXIMUN_BALANCE = "MAXIMUN_BALANCE";
	public final static String COL_FRAUD_LOCK = "FRAUD_LOCK";
	public final static String COL_USER_LIFE_CYCLE = "USER_LIFE_CYCLE";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ACTIVE_PERIOD = 0;
	public final static int IDX_SUSPEND_PERIOD = 1;
	public final static int IDX_DISABLE_PERIOD = 2;
	public final static int IDX_BRAND_ID = 3;
	public final static int IDX_MAXIMUN_BALANCE = 4;
	public final static int IDX_FRAUD_LOCK = 5;
	public final static int IDX_USER_LIFE_CYCLE = 6;
	public final static int IDX_BILLING_TYPE = 7;
	public final static int IDX_EXTRA_MAP = 8;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="activePeriod")
	@Sdl
	private long activePeriod;

	/**
	 * 
	 */
	@XmlElement(name="suspendPeriod")
	@Sdl
	private long suspendPeriod;

	/**
	 * 
	 */
	@XmlElement(name="disablePeriod")
	@Sdl
	private long disablePeriod;

	/**
	 * 
	 */
	@XmlElement(name="brandId")
	@Sdl
	private int brandId;

	/**
	 * 
	 */
	@XmlElement(name="maximunBalance")
	@Sdl
	private long maximunBalance;

	/**
	 * 
	 */
	@XmlElement(name="fraudLock")
	@Sdl
	private int fraudLock;

	/**
	 * 
	 */
	@XmlElement(name="userLifeCycle")
	@Sdl
	private int userLifeCycle;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(8, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setActivePeriod(long obj){
		this.activePeriod = obj;
		onFieldSet(0, obj);
	}

	public long getActivePeriod(){
		return activePeriod;
	}

	public void setSuspendPeriod(long obj){
		this.suspendPeriod = obj;
		onFieldSet(1, obj);
	}

	public long getSuspendPeriod(){
		return suspendPeriod;
	}

	public void setDisablePeriod(long obj){
		this.disablePeriod = obj;
		onFieldSet(2, obj);
	}

	public long getDisablePeriod(){
		return disablePeriod;
	}

	public void setBrandId(int obj){
		this.brandId = obj;
		onFieldSet(3, obj);
	}

	public int getBrandId(){
		return brandId;
	}

	public void setMaximunBalance(long obj){
		this.maximunBalance = obj;
		onFieldSet(4, obj);
	}

	public long getMaximunBalance(){
		return maximunBalance;
	}

	public void setFraudLock(int obj){
		this.fraudLock = obj;
		onFieldSet(5, obj);
	}

	public int getFraudLock(){
		return fraudLock;
	}

	public void setUserLifeCycle(int obj){
		this.userLifeCycle = obj;
		onFieldSet(6, obj);
	}

	public int getUserLifeCycle(){
		return userLifeCycle;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(7, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserInfo(SUserInfo arg0){
		copy(arg0);
	}

	public void copy(final SUserInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		activePeriod = rhs.activePeriod;
		suspendPeriod = rhs.suspendPeriod;
		disablePeriod = rhs.disablePeriod;
		brandId = rhs.brandId;
		maximunBalance = rhs.maximunBalance;
		fraudLock = rhs.fraudLock;
		userLifeCycle = rhs.userLifeCycle;
		billingType = rhs.billingType;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserInfo rhs=(SUserInfo)rhs0;
		if(!ObjectUtils.equals(activePeriod, rhs.activePeriod)) return false;
		if(!ObjectUtils.equals(suspendPeriod, rhs.suspendPeriod)) return false;
		if(!ObjectUtils.equals(disablePeriod, rhs.disablePeriod)) return false;
		if(!ObjectUtils.equals(brandId, rhs.brandId)) return false;
		if(!ObjectUtils.equals(maximunBalance, rhs.maximunBalance)) return false;
		if(!ObjectUtils.equals(fraudLock, rhs.fraudLock)) return false;
		if(!ObjectUtils.equals(userLifeCycle, rhs.userLifeCycle)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(activePeriod)
		.append(suspendPeriod)
		.append(disablePeriod)
		.append(brandId)
		.append(maximunBalance)
		.append(fraudLock)
		.append(userLifeCycle)
		.append(billingType)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SUserInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "ACTIVE_PERIOD", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "SUSPEND_PERIOD", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "DISABLE_PERIOD", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "BRAND_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "MAXIMUN_BALANCE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "FRAUD_LOCK", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "USER_LIFE_CYCLE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "BILLING_TYPE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "EXTRA_MAP", 8, Map.class));
}

}