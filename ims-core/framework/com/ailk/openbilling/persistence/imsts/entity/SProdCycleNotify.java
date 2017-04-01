package com.ailk.openbilling.persistence.imsts.entity;

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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","firstNotifyDate","notifyCount","nextNotifyDate","modifyDate","offerId","objectId","objectType","sts","validDate","expireDate","freeResFlag"})
@Sdl(module="MImsTsDef")
public class SProdCycleNotify extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_FIRST_NOTIFY_DATE = "FIRST_NOTIFY_DATE";
	public final static String COL_NOTIFY_COUNT = "NOTIFY_COUNT";
	public final static String COL_NEXT_NOTIFY_DATE = "NEXT_NOTIFY_DATE";
	public final static String COL_MODIFY_DATE = "MODIFY_DATE";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_STS = "STS";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_FREE_RES_FLAG = "FREE_RES_FLAG";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_FIRST_NOTIFY_DATE = 1;
	public final static int IDX_NOTIFY_COUNT = 2;
	public final static int IDX_NEXT_NOTIFY_DATE = 3;
	public final static int IDX_MODIFY_DATE = 4;
	public final static int IDX_OFFER_ID = 5;
	public final static int IDX_OBJECT_ID = 6;
	public final static int IDX_OBJECT_TYPE = 7;
	public final static int IDX_STS = 8;
	public final static int IDX_VALID_DATE = 9;
	public final static int IDX_EXPIRE_DATE = 10;
	public final static int IDX_FREE_RES_FLAG = 11;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="firstNotifyDate")
	@Sdl
	private Date firstNotifyDate;

	/**
	 * 
	 */
	@XmlElement(name="notifyCount")
	@Sdl
	private int notifyCount;

	/**
	 * 
	 */
	@XmlElement(name="nextNotifyDate")
	@Sdl
	private Date nextNotifyDate;

	/**
	 * 
	 */
	@XmlElement(name="modifyDate")
	@Sdl
	private Date modifyDate;

	/**
	 * 
	 */
	@XmlElement(name="offerId")
	@Sdl
	private int offerId;

	/**
	 * 
	 */
	@XmlElement(name="objectId")
	@Sdl
	private long objectId;

	/**
	 * 
	 */
	@XmlElement(name="objectType")
	@Sdl
	private int objectType;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private Date validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Date expireDate;

	/**
	 * 
	 */
	@XmlElement(name="freeResFlag")
	@Sdl
	private int freeResFlag;

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setFirstNotifyDate(Date obj){
		this.firstNotifyDate = obj;
		onFieldSet(1, obj);
	}

	public Date getFirstNotifyDate(){
		return firstNotifyDate;
	}

	public void setNotifyCount(int obj){
		this.notifyCount = obj;
		onFieldSet(2, obj);
	}

	public int getNotifyCount(){
		return notifyCount;
	}

	public void setNextNotifyDate(Date obj){
		this.nextNotifyDate = obj;
		onFieldSet(3, obj);
	}

	public Date getNextNotifyDate(){
		return nextNotifyDate;
	}

	public void setModifyDate(Date obj){
		this.modifyDate = obj;
		onFieldSet(4, obj);
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setOfferId(int obj){
		this.offerId = obj;
		onFieldSet(5, obj);
	}

	public int getOfferId(){
		return offerId;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(6, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(int obj){
		this.objectType = obj;
		onFieldSet(7, obj);
	}

	public int getObjectType(){
		return objectType;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(8, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(9, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(10, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setFreeResFlag(int obj){
		this.freeResFlag = obj;
		onFieldSet(11, obj);
	}

	public int getFreeResFlag(){
		return freeResFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProdCycleNotify(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 12; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProdCycleNotify(SProdCycleNotify arg0){
		copy(arg0);
	}

	public void copy(final SProdCycleNotify rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		productId = rhs.productId;
		firstNotifyDate = rhs.firstNotifyDate;
		notifyCount = rhs.notifyCount;
		nextNotifyDate = rhs.nextNotifyDate;
		modifyDate = rhs.modifyDate;
		offerId = rhs.offerId;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		sts = rhs.sts;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		freeResFlag = rhs.freeResFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdCycleNotify rhs=(SProdCycleNotify)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(firstNotifyDate, rhs.firstNotifyDate)) return false;
		if(!ObjectUtils.equals(notifyCount, rhs.notifyCount)) return false;
		if(!ObjectUtils.equals(nextNotifyDate, rhs.nextNotifyDate)) return false;
		if(!ObjectUtils.equals(modifyDate, rhs.modifyDate)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(freeResFlag, rhs.freeResFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(firstNotifyDate)
		.append(notifyCount)
		.append(nextNotifyDate)
		.append(modifyDate)
		.append(offerId)
		.append(objectId)
		.append(objectType)
		.append(sts)
		.append(validDate)
		.append(expireDate)
		.append(freeResFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(12);
public static final long BITS_ALL_MARKER = 0x800L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SProdCycleNotify";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "FIRST_NOTIFY_DATE", 1, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "NOTIFY_COUNT", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "NEXT_NOTIFY_DATE", 3, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "MODIFY_DATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "OFFER_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "OBJECT_ID", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "OBJECT_TYPE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "STS", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "VALID_DATE", 9, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "EXPIRE_DATE", 10, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdCycleNotify.class, "FREE_RES_FLAG", 11, int.class));
}

}