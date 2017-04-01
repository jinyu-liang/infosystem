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
@XmlType(propOrder={"primaryKey","acctId","ownerId","pricePlanId","specId","channelId","itemCode","beginDate","endDate","value","ownerType"})
@Sdl(module="MXdr")
public class SBillingAccumulate extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRIMARY_KEY = "PRIMARY_KEY";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_PRICE_PLAN_ID = "PRICE_PLAN_ID";
	public final static String COL_SPEC_ID = "SPEC_ID";
	public final static String COL_CHANNEL_ID = "CHANNEL_ID";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_BEGIN_DATE = "BEGIN_DATE";
	public final static String COL_END_DATE = "END_DATE";
	public final static String COL_VALUE = "VALUE";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static int IDX_PRIMARY_KEY = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_OWNER_ID = 2;
	public final static int IDX_PRICE_PLAN_ID = 3;
	public final static int IDX_SPEC_ID = 4;
	public final static int IDX_CHANNEL_ID = 5;
	public final static int IDX_ITEM_CODE = 6;
	public final static int IDX_BEGIN_DATE = 7;
	public final static int IDX_END_DATE = 8;
	public final static int IDX_VALUE = 9;
	public final static int IDX_OWNER_TYPE = 10;

	/**
	 * 
	 */
	@XmlElement(name="primaryKey")
	@Sdl
	private long primaryKey;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="pricePlanId")
	@Sdl
	private int pricePlanId;

	/**
	 * 
	 */
	@XmlElement(name="specId")
	@Sdl
	private int specId;

	/**
	 * 
	 */
	@XmlElement(name="channelId")
	@Sdl
	private int channelId;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="beginDate")
	@Sdl
	private long beginDate;

	/**
	 * 
	 */
	@XmlElement(name="endDate")
	@Sdl
	private long endDate;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private long value;

	/**
	 * 
	 */
	@XmlElement(name="ownerType")
	@Sdl
	private short ownerType;

	public void setPrimaryKey(long obj){
		this.primaryKey = obj;
		onFieldSet(0, obj);
	}

	public long getPrimaryKey(){
		return primaryKey;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(2, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setPricePlanId(int obj){
		this.pricePlanId = obj;
		onFieldSet(3, obj);
	}

	public int getPricePlanId(){
		return pricePlanId;
	}

	public void setSpecId(int obj){
		this.specId = obj;
		onFieldSet(4, obj);
	}

	public int getSpecId(){
		return specId;
	}

	public void setChannelId(int obj){
		this.channelId = obj;
		onFieldSet(5, obj);
	}

	public int getChannelId(){
		return channelId;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(6, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setBeginDate(long obj){
		this.beginDate = obj;
		onFieldSet(7, obj);
	}

	public long getBeginDate(){
		return beginDate;
	}

	public void setEndDate(long obj){
		this.endDate = obj;
		onFieldSet(8, obj);
	}

	public long getEndDate(){
		return endDate;
	}

	public void setValue(long obj){
		this.value = obj;
		onFieldSet(9, obj);
	}

	public long getValue(){
		return value;
	}

	public void setOwnerType(short obj){
		this.ownerType = obj;
		onFieldSet(10, obj);
	}

	public short getOwnerType(){
		return ownerType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingAccumulate(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingAccumulate(SBillingAccumulate arg0){
		copy(arg0);
	}

	public void copy(final SBillingAccumulate rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		primaryKey = rhs.primaryKey;
		acctId = rhs.acctId;
		ownerId = rhs.ownerId;
		pricePlanId = rhs.pricePlanId;
		specId = rhs.specId;
		channelId = rhs.channelId;
		itemCode = rhs.itemCode;
		beginDate = rhs.beginDate;
		endDate = rhs.endDate;
		value = rhs.value;
		ownerType = rhs.ownerType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingAccumulate rhs=(SBillingAccumulate)rhs0;
		if(!ObjectUtils.equals(primaryKey, rhs.primaryKey)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(pricePlanId, rhs.pricePlanId)) return false;
		if(!ObjectUtils.equals(specId, rhs.specId)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(beginDate, rhs.beginDate)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(primaryKey)
		.append(acctId)
		.append(ownerId)
		.append(pricePlanId)
		.append(specId)
		.append(channelId)
		.append(itemCode)
		.append(beginDate)
		.append(endDate)
		.append(value)
		.append(ownerType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingAccumulate";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "PRIMARY_KEY", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "OWNER_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "PRICE_PLAN_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "SPEC_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "CHANNEL_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "ITEM_CODE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "BEGIN_DATE", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "END_DATE", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "VALUE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingAccumulate.class, "OWNER_TYPE", 10, short.class));
}

}