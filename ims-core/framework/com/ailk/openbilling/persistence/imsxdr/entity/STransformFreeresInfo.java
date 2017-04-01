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
@XmlType(propOrder={"doneCode","freeresItemId","itemId","assetValue","resourceValue","validDate","expireDate","freeresType"})
@Sdl(module="MXdr")
public class STransformFreeresInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_ITEM_ID = "ITEM_ID";
	public final static String COL_ASSET_VALUE = "ASSET_VALUE";
	public final static String COL_RESOURCE_VALUE = "RESOURCE_VALUE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_FREERES_TYPE = "FREERES_TYPE";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_FREERES_ITEM_ID = 1;
	public final static int IDX_ITEM_ID = 2;
	public final static int IDX_ASSET_VALUE = 3;
	public final static int IDX_RESOURCE_VALUE = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_FREERES_TYPE = 7;

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
	@XmlElement(name="itemId")
	@Sdl
	private int itemId;

	/**
	 * 
	 */
	@XmlElement(name="assetValue")
	@Sdl
	private long assetValue;

	/**
	 * 
	 */
	@XmlElement(name="resourceValue")
	@Sdl
	private long resourceValue;

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
	@XmlElement(name="freeresType")
	@Sdl
	private int freeresType;

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

	public void setItemId(int obj){
		this.itemId = obj;
		onFieldSet(2, obj);
	}

	public int getItemId(){
		return itemId;
	}

	public void setAssetValue(long obj){
		this.assetValue = obj;
		onFieldSet(3, obj);
	}

	public long getAssetValue(){
		return assetValue;
	}

	public void setResourceValue(long obj){
		this.resourceValue = obj;
		onFieldSet(4, obj);
	}

	public long getResourceValue(){
		return resourceValue;
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

	public void setFreeresType(int obj){
		this.freeresType = obj;
		onFieldSet(7, obj);
	}

	public int getFreeresType(){
		return freeresType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public STransformFreeresInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public STransformFreeresInfo(STransformFreeresInfo arg0){
		copy(arg0);
	}

	public void copy(final STransformFreeresInfo rhs){
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
		itemId = rhs.itemId;
		assetValue = rhs.assetValue;
		resourceValue = rhs.resourceValue;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		freeresType = rhs.freeresType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STransformFreeresInfo rhs=(STransformFreeresInfo)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(assetValue, rhs.assetValue)) return false;
		if(!ObjectUtils.equals(resourceValue, rhs.resourceValue)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(freeresType, rhs.freeresType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(freeresItemId)
		.append(itemId)
		.append(assetValue)
		.append(resourceValue)
		.append(validDate)
		.append(expireDate)
		.append(freeresType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.STransformFreeresInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "FREERES_ITEM_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "ITEM_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "ASSET_VALUE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "RESOURCE_VALUE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "VALID_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "EXPIRE_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STransformFreeresInfo.class, "FREERES_TYPE", 7, int.class));
}

}