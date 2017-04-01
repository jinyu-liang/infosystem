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
@XmlType(propOrder={"acctId","ownerId","ownerType","doneCode","priceId","freeresItemId","freeResType","measureId","validDateTime","expireDateTime","freeResLimit","freeResUsed","freeResFreeze"})
@Sdl(module="MXdr")
public class SFreeResQueryVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_FREE_RES_TYPE = "FREE_RES_TYPE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_FREE_RES_LIMIT = "FREE_RES_LIMIT";
	public final static String COL_FREE_RES_USED = "FREE_RES_USED";
	public final static String COL_FREE_RES_FREEZE = "FREE_RES_FREEZE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_OWNER_ID = 1;
	public final static int IDX_OWNER_TYPE = 2;
	public final static int IDX_DONE_CODE = 3;
	public final static int IDX_PRICE_ID = 4;
	public final static int IDX_FREERES_ITEM_ID = 5;
	public final static int IDX_FREE_RES_TYPE = 6;
	public final static int IDX_MEASURE_ID = 7;
	public final static int IDX_VALID_DATE_TIME = 8;
	public final static int IDX_EXPIRE_DATE_TIME = 9;
	public final static int IDX_FREE_RES_LIMIT = 10;
	public final static int IDX_FREE_RES_USED = 11;
	public final static int IDX_FREE_RES_FREEZE = 12;

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
	@XmlElement(name="ownerType")
	@Sdl
	private int ownerType;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="freeresItemId")
	@Sdl
	private int freeresItemId;

	/**
	 * 
	 */
	@XmlElement(name="freeResType")
	@Sdl
	private int freeResType;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="validDateTime")
	@Sdl
	private long validDateTime;

	/**
	 * 
	 */
	@XmlElement(name="expireDateTime")
	@Sdl
	private long expireDateTime;

	/**
	 * 
	 */
	@XmlElement(name="freeResLimit")
	@Sdl
	private long freeResLimit;

	/**
	 * 
	 */
	@XmlElement(name="freeResUsed")
	@Sdl
	private long freeResUsed;

	/**
	 * 
	 */
	@XmlElement(name="freeResFreeze")
	@Sdl
	private long freeResFreeze;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(1, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setOwnerType(int obj){
		this.ownerType = obj;
		onFieldSet(2, obj);
	}

	public int getOwnerType(){
		return ownerType;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(3, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(4, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setFreeresItemId(int obj){
		this.freeresItemId = obj;
		onFieldSet(5, obj);
	}

	public int getFreeresItemId(){
		return freeresItemId;
	}

	public void setFreeResType(int obj){
		this.freeResType = obj;
		onFieldSet(6, obj);
	}

	public int getFreeResType(){
		return freeResType;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(7, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(8, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(9, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setFreeResLimit(long obj){
		this.freeResLimit = obj;
		onFieldSet(10, obj);
	}

	public long getFreeResLimit(){
		return freeResLimit;
	}

	public void setFreeResUsed(long obj){
		this.freeResUsed = obj;
		onFieldSet(11, obj);
	}

	public long getFreeResUsed(){
		return freeResUsed;
	}

	public void setFreeResFreeze(long obj){
		this.freeResFreeze = obj;
		onFieldSet(12, obj);
	}

	public long getFreeResFreeze(){
		return freeResFreeze;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreeResQueryVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 13; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreeResQueryVal(SFreeResQueryVal arg0){
		copy(arg0);
	}

	public void copy(final SFreeResQueryVal rhs){
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
		ownerId = rhs.ownerId;
		ownerType = rhs.ownerType;
		doneCode = rhs.doneCode;
		priceId = rhs.priceId;
		freeresItemId = rhs.freeresItemId;
		freeResType = rhs.freeResType;
		measureId = rhs.measureId;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		freeResLimit = rhs.freeResLimit;
		freeResUsed = rhs.freeResUsed;
		freeResFreeze = rhs.freeResFreeze;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreeResQueryVal rhs=(SFreeResQueryVal)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(freeResType, rhs.freeResType)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(freeResLimit, rhs.freeResLimit)) return false;
		if(!ObjectUtils.equals(freeResUsed, rhs.freeResUsed)) return false;
		if(!ObjectUtils.equals(freeResFreeze, rhs.freeResFreeze)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(ownerId)
		.append(ownerType)
		.append(doneCode)
		.append(priceId)
		.append(freeresItemId)
		.append(freeResType)
		.append(measureId)
		.append(validDateTime)
		.append(expireDateTime)
		.append(freeResLimit)
		.append(freeResUsed)
		.append(freeResFreeze)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(13);
public static final long BITS_ALL_MARKER = 0x1000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreeResQueryVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "OWNER_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "OWNER_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "DONE_CODE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "PRICE_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "FREERES_ITEM_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "FREE_RES_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "MEASURE_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "VALID_DATE_TIME", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "EXPIRE_DATE_TIME", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "FREE_RES_LIMIT", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "FREE_RES_USED", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResQueryVal.class, "FREE_RES_FREEZE", 12, long.class));
}

}