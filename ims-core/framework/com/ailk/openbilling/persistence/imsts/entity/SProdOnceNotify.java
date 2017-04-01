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
@XmlType(propOrder={"notifyType","productId","offerId","objectId","objectType","validDate","expireDate","createDate","sts","freeResFlag","notifyDate","retryTimes","freeUseDays","freeRestDays"})
@Sdl(module="MImsTsDef")
public class SProdOnceNotify extends CsdlStructObject implements IComplexEntity{

	public final static String COL_NOTIFY_TYPE = "NOTIFY_TYPE";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_STS = "STS";
	public final static String COL_FREE_RES_FLAG = "FREE_RES_FLAG";
	public final static String COL_NOTIFY_DATE = "NOTIFY_DATE";
	public final static String COL_RETRY_TIMES = "RETRY_TIMES";
	public final static String COL_FREE_USE_DAYS = "FREE_USE_DAYS";
	public final static String COL_FREE_REST_DAYS = "FREE_REST_DAYS";
	public final static int IDX_NOTIFY_TYPE = 0;
	public final static int IDX_PRODUCT_ID = 1;
	public final static int IDX_OFFER_ID = 2;
	public final static int IDX_OBJECT_ID = 3;
	public final static int IDX_OBJECT_TYPE = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_CREATE_DATE = 7;
	public final static int IDX_STS = 8;
	public final static int IDX_FREE_RES_FLAG = 9;
	public final static int IDX_NOTIFY_DATE = 10;
	public final static int IDX_RETRY_TIMES = 11;
	public final static int IDX_FREE_USE_DAYS = 12;
	public final static int IDX_FREE_REST_DAYS = 13;

	/**
	 * 
	 */
	@XmlElement(name="notifyType")
	@Sdl
	private int notifyType;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

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
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="freeResFlag")
	@Sdl
	private int freeResFlag;

	/**
	 * 
	 */
	@XmlElement(name="notifyDate")
	@Sdl
	private Date notifyDate;

	/**
	 * 
	 */
	@XmlElement(name="retryTimes")
	@Sdl
	private int retryTimes;

	/**
	 * 
	 */
	@XmlElement(name="freeUseDays")
	@Sdl
	private int freeUseDays;

	/**
	 * 
	 */
	@XmlElement(name="freeRestDays")
	@Sdl
	private int freeRestDays;

	public void setNotifyType(int obj){
		this.notifyType = obj;
		onFieldSet(0, obj);
	}

	public int getNotifyType(){
		return notifyType;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(1, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setOfferId(int obj){
		this.offerId = obj;
		onFieldSet(2, obj);
	}

	public int getOfferId(){
		return offerId;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(3, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(int obj){
		this.objectType = obj;
		onFieldSet(4, obj);
	}

	public int getObjectType(){
		return objectType;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(7, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(8, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setFreeResFlag(int obj){
		this.freeResFlag = obj;
		onFieldSet(9, obj);
	}

	public int getFreeResFlag(){
		return freeResFlag;
	}

	public void setNotifyDate(Date obj){
		this.notifyDate = obj;
		onFieldSet(10, obj);
	}

	public Date getNotifyDate(){
		return notifyDate;
	}

	public void setRetryTimes(int obj){
		this.retryTimes = obj;
		onFieldSet(11, obj);
	}

	public int getRetryTimes(){
		return retryTimes;
	}

	public void setFreeUseDays(int obj){
		this.freeUseDays = obj;
		onFieldSet(12, obj);
	}

	public int getFreeUseDays(){
		return freeUseDays;
	}

	public void setFreeRestDays(int obj){
		this.freeRestDays = obj;
		onFieldSet(13, obj);
	}

	public int getFreeRestDays(){
		return freeRestDays;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProdOnceNotify(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 14; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProdOnceNotify(SProdOnceNotify arg0){
		copy(arg0);
	}

	public void copy(final SProdOnceNotify rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		notifyType = rhs.notifyType;
		productId = rhs.productId;
		offerId = rhs.offerId;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		createDate = rhs.createDate;
		sts = rhs.sts;
		freeResFlag = rhs.freeResFlag;
		notifyDate = rhs.notifyDate;
		retryTimes = rhs.retryTimes;
		freeUseDays = rhs.freeUseDays;
		freeRestDays = rhs.freeRestDays;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdOnceNotify rhs=(SProdOnceNotify)rhs0;
		if(!ObjectUtils.equals(notifyType, rhs.notifyType)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(freeResFlag, rhs.freeResFlag)) return false;
		if(!ObjectUtils.equals(notifyDate, rhs.notifyDate)) return false;
		if(!ObjectUtils.equals(retryTimes, rhs.retryTimes)) return false;
		if(!ObjectUtils.equals(freeUseDays, rhs.freeUseDays)) return false;
		if(!ObjectUtils.equals(freeRestDays, rhs.freeRestDays)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyType)
		.append(productId)
		.append(offerId)
		.append(objectId)
		.append(objectType)
		.append(validDate)
		.append(expireDate)
		.append(createDate)
		.append(sts)
		.append(freeResFlag)
		.append(notifyDate)
		.append(retryTimes)
		.append(freeUseDays)
		.append(freeRestDays)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(14);
public static final long BITS_ALL_MARKER = 0x2000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SProdOnceNotify";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "NOTIFY_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "PRODUCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "OFFER_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "OBJECT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "OBJECT_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "VALID_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "EXPIRE_DATE", 6, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "CREATE_DATE", 7, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "STS", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "FREE_RES_FLAG", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "NOTIFY_DATE", 10, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "RETRY_TIMES", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "FREE_USE_DAYS", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOnceNotify.class, "FREE_REST_DAYS", 13, int.class));
}

}