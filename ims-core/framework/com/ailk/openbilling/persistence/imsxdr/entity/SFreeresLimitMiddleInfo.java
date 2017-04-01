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
@XmlType(propOrder={"ownerId","doneCode","freezeAmount","validDateTime","expireDateTime","ownerType","freeresItemId"})
@Sdl(module="MXdr")
public class SFreeresLimitMiddleInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FREEZE_AMOUNT = "FREEZE_AMOUNT";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static int IDX_OWNER_ID = 0;
	public final static int IDX_DONE_CODE = 1;
	public final static int IDX_FREEZE_AMOUNT = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_OWNER_TYPE = 5;
	public final static int IDX_FREERES_ITEM_ID = 6;

	/**
	 * 
	 */
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="freezeAmount")
	@Sdl
	private long freezeAmount;

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
	@XmlElement(name="ownerType")
	@Sdl
	private int ownerType;

	/**
	 * 
	 */
	@XmlElement(name="freeresItemId")
	@Sdl
	private int freeresItemId;

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(0, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(1, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setFreezeAmount(long obj){
		this.freezeAmount = obj;
		onFieldSet(2, obj);
	}

	public long getFreezeAmount(){
		return freezeAmount;
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

	public void setOwnerType(int obj){
		this.ownerType = obj;
		onFieldSet(5, obj);
	}

	public int getOwnerType(){
		return ownerType;
	}

	public void setFreeresItemId(int obj){
		this.freeresItemId = obj;
		onFieldSet(6, obj);
	}

	public int getFreeresItemId(){
		return freeresItemId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreeresLimitMiddleInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreeresLimitMiddleInfo(SFreeresLimitMiddleInfo arg0){
		copy(arg0);
	}

	public void copy(final SFreeresLimitMiddleInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		ownerId = rhs.ownerId;
		doneCode = rhs.doneCode;
		freezeAmount = rhs.freezeAmount;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		ownerType = rhs.ownerType;
		freeresItemId = rhs.freeresItemId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreeresLimitMiddleInfo rhs=(SFreeresLimitMiddleInfo)rhs0;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(freezeAmount, rhs.freezeAmount)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(ownerId)
		.append(doneCode)
		.append(freezeAmount)
		.append(validDateTime)
		.append(expireDateTime)
		.append(ownerType)
		.append(freeresItemId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreeresLimitMiddleInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "OWNER_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "DONE_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "FREEZE_AMOUNT", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "OWNER_TYPE", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeresLimitMiddleInfo.class, "FREERES_ITEM_ID", 6, int.class));
}

}