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
@XmlType(propOrder={"payAcctId","objectId","value","productId","validDate","expireDate","itemCode","measureId","objectType"})
@Sdl(module="MImsSyncDef")
public class SRewardOtFreeres extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PAY_ACCT_ID = "PAY_ACCT_ID";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_VALUE = "VALUE";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static int IDX_PAY_ACCT_ID = 0;
	public final static int IDX_OBJECT_ID = 1;
	public final static int IDX_VALUE = 2;
	public final static int IDX_PRODUCT_ID = 3;
	public final static int IDX_VALID_DATE = 4;
	public final static int IDX_EXPIRE_DATE = 5;
	public final static int IDX_ITEM_CODE = 6;
	public final static int IDX_MEASURE_ID = 7;
	public final static int IDX_OBJECT_TYPE = 8;

	/**
	 * 
	 */
	@XmlElement(name="payAcctId")
	@Sdl
	private long payAcctId;

	/**
	 * 
	 */
	@XmlElement(name="objectId")
	@Sdl
	private long objectId;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private long value;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

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
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="objectType")
	@Sdl
	private short objectType;

	public void setPayAcctId(long obj){
		this.payAcctId = obj;
		onFieldSet(0, obj);
	}

	public long getPayAcctId(){
		return payAcctId;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(1, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setValue(long obj){
		this.value = obj;
		onFieldSet(2, obj);
	}

	public long getValue(){
		return value;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(3, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(4, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(5, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(6, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(7, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setObjectType(short obj){
		this.objectType = obj;
		onFieldSet(8, obj);
	}

	public short getObjectType(){
		return objectType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRewardOtFreeres(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRewardOtFreeres(SRewardOtFreeres arg0){
		copy(arg0);
	}

	public void copy(final SRewardOtFreeres rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		payAcctId = rhs.payAcctId;
		objectId = rhs.objectId;
		value = rhs.value;
		productId = rhs.productId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		itemCode = rhs.itemCode;
		measureId = rhs.measureId;
		objectType = rhs.objectType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRewardOtFreeres rhs=(SRewardOtFreeres)rhs0;
		if(!ObjectUtils.equals(payAcctId, rhs.payAcctId)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payAcctId)
		.append(objectId)
		.append(value)
		.append(productId)
		.append(validDate)
		.append(expireDate)
		.append(itemCode)
		.append(measureId)
		.append(objectType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SRewardOtFreeres";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "PAY_ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "OBJECT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "VALUE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "PRODUCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "VALID_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "EXPIRE_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "ITEM_CODE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "MEASURE_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardOtFreeres.class, "OBJECT_TYPE", 8, short.class));
}

}