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
@XmlType(propOrder={"doneCode","freeresItemId","freeResType","validDateTime","expireDateTime","thisTimeUsed","thisTimeFreezed"})
@Sdl(module="MXdr")
public class SFreeResChargeVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_FREE_RES_TYPE = "FREE_RES_TYPE";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_THIS_TIME_USED = "THIS_TIME_USED";
	public final static String COL_THIS_TIME_FREEZED = "THIS_TIME_FREEZED";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_FREERES_ITEM_ID = 1;
	public final static int IDX_FREE_RES_TYPE = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_THIS_TIME_USED = 5;
	public final static int IDX_THIS_TIME_FREEZED = 6;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

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
	@XmlElement(name="thisTimeUsed")
	@Sdl
	private long thisTimeUsed;

	/**
	 * 
	 */
	@XmlElement(name="thisTimeFreezed")
	@Sdl
	private long thisTimeFreezed;

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(0, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setFreeresItemId(int obj){
		this.freeresItemId = obj;
		onFieldSet(1, obj);
	}

	public int getFreeresItemId(){
		return freeresItemId;
	}

	public void setFreeResType(int obj){
		this.freeResType = obj;
		onFieldSet(2, obj);
	}

	public int getFreeResType(){
		return freeResType;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(4, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setThisTimeUsed(long obj){
		this.thisTimeUsed = obj;
		onFieldSet(5, obj);
	}

	public long getThisTimeUsed(){
		return thisTimeUsed;
	}

	public void setThisTimeFreezed(long obj){
		this.thisTimeFreezed = obj;
		onFieldSet(6, obj);
	}

	public long getThisTimeFreezed(){
		return thisTimeFreezed;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreeResChargeVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreeResChargeVal(SFreeResChargeVal arg0){
		copy(arg0);
	}

	public void copy(final SFreeResChargeVal rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		doneCode = rhs.doneCode;
		freeresItemId = rhs.freeresItemId;
		freeResType = rhs.freeResType;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		thisTimeUsed = rhs.thisTimeUsed;
		thisTimeFreezed = rhs.thisTimeFreezed;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreeResChargeVal rhs=(SFreeResChargeVal)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(freeResType, rhs.freeResType)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(thisTimeUsed, rhs.thisTimeUsed)) return false;
		if(!ObjectUtils.equals(thisTimeFreezed, rhs.thisTimeFreezed)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(freeresItemId)
		.append(freeResType)
		.append(validDateTime)
		.append(expireDateTime)
		.append(thisTimeUsed)
		.append(thisTimeFreezed)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreeResChargeVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "FREERES_ITEM_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "FREE_RES_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "THIS_TIME_USED", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResChargeVal.class, "THIS_TIME_FREEZED", 6, long.class));
}

}