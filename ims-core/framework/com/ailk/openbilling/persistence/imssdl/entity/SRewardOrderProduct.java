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
@XmlType(propOrder={"offeringId","objectType","objectId","validDate","expireDate","count"})
@Sdl(module="MImsSyncDef")
public class SRewardOrderProduct extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OFFERING_ID = "OFFERING_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_COUNT = "COUNT";
	public final static int IDX_OFFERING_ID = 0;
	public final static int IDX_OBJECT_TYPE = 1;
	public final static int IDX_OBJECT_ID = 2;
	public final static int IDX_VALID_DATE = 3;
	public final static int IDX_EXPIRE_DATE = 4;
	public final static int IDX_COUNT = 5;

	/**
	 * 
	 */
	@XmlElement(name="offeringId")
	@Sdl
	private int offeringId;

	/**
	 * 
	 */
	@XmlElement(name="objectType")
	@Sdl
	private short objectType;

	/**
	 * 
	 */
	@XmlElement(name="objectId")
	@Sdl
	private long objectId;

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

	/**
	 * 
	 */
	@XmlElement(name="count")
	@Sdl
	private short count;

	public void setOfferingId(int obj){
		this.offeringId = obj;
		onFieldSet(0, obj);
	}

	public int getOfferingId(){
		return offeringId;
	}

	public void setObjectType(short obj){
		this.objectType = obj;
		onFieldSet(1, obj);
	}

	public short getObjectType(){
		return objectType;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(2, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(3, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(4, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setCount(short obj){
		this.count = obj;
		onFieldSet(5, obj);
	}

	public short getCount(){
		return count;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRewardOrderProduct(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRewardOrderProduct(SRewardOrderProduct arg0){
		copy(arg0);
	}

	public void copy(final SRewardOrderProduct rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		offeringId = rhs.offeringId;
		objectType = rhs.objectType;
		objectId = rhs.objectId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		count = rhs.count;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRewardOrderProduct rhs=(SRewardOrderProduct)rhs0;
		if(!ObjectUtils.equals(offeringId, rhs.offeringId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(count, rhs.count)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offeringId)
		.append(objectType)
		.append(objectId)
		.append(validDate)
		.append(expireDate)
		.append(count)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SRewardOrderProduct";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "OFFERING_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "OBJECT_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "OBJECT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "VALID_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "EXPIRE_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOrderProduct.class, "COUNT", 5, short.class));
}

}