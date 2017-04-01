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
@XmlType(propOrder={"doneCode","freeresItemId","limitType","validDateTime","expireDateTime","freeResFreeze","freeResUsed","amount","servId"})
@Sdl(module="MXdr")
public class SFreeResLimitQueryVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_LIMIT_TYPE = "LIMIT_TYPE";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_FREE_RES_FREEZE = "FREE_RES_FREEZE";
	public final static String COL_FREE_RES_USED = "FREE_RES_USED";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_FREERES_ITEM_ID = 1;
	public final static int IDX_LIMIT_TYPE = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_FREE_RES_FREEZE = 5;
	public final static int IDX_FREE_RES_USED = 6;
	public final static int IDX_AMOUNT = 7;
	public final static int IDX_SERV_ID = 8;

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
	@XmlElement(name="limitType")
	@Sdl
	private int limitType;

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
	@XmlElement(name="freeResFreeze")
	@Sdl
	private long freeResFreeze;

	/**
	 * 
	 */
	@XmlElement(name="freeResUsed")
	@Sdl
	private long freeResUsed;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

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

	public void setLimitType(int obj){
		this.limitType = obj;
		onFieldSet(2, obj);
	}

	public int getLimitType(){
		return limitType;
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

	public void setFreeResFreeze(long obj){
		this.freeResFreeze = obj;
		onFieldSet(5, obj);
	}

	public long getFreeResFreeze(){
		return freeResFreeze;
	}

	public void setFreeResUsed(long obj){
		this.freeResUsed = obj;
		onFieldSet(6, obj);
	}

	public long getFreeResUsed(){
		return freeResUsed;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(7, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(8, obj);
	}

	public long getServId(){
		return servId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreeResLimitQueryVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreeResLimitQueryVal(SFreeResLimitQueryVal arg0){
		copy(arg0);
	}

	public void copy(final SFreeResLimitQueryVal rhs){
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
		limitType = rhs.limitType;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		freeResFreeze = rhs.freeResFreeze;
		freeResUsed = rhs.freeResUsed;
		amount = rhs.amount;
		servId = rhs.servId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreeResLimitQueryVal rhs=(SFreeResLimitQueryVal)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(limitType, rhs.limitType)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(freeResFreeze, rhs.freeResFreeze)) return false;
		if(!ObjectUtils.equals(freeResUsed, rhs.freeResUsed)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(freeresItemId)
		.append(limitType)
		.append(validDateTime)
		.append(expireDateTime)
		.append(freeResFreeze)
		.append(freeResUsed)
		.append(amount)
		.append(servId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreeResLimitQueryVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "FREERES_ITEM_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "LIMIT_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "FREE_RES_FREEZE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "FREE_RES_USED", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "AMOUNT", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResLimitQueryVal.class, "SERV_ID", 8, long.class));
}

}