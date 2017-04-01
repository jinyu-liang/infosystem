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
@XmlType(propOrder={"assetId","validDate","expireDate","amount","deductFee","measureId","itemCode"})
@Sdl(module="MXdr")
public class SBookInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_DEDUCT_FEE = "DEDUCT_FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static int IDX_ASSET_ID = 0;
	public final static int IDX_VALID_DATE = 1;
	public final static int IDX_EXPIRE_DATE = 2;
	public final static int IDX_AMOUNT = 3;
	public final static int IDX_DEDUCT_FEE = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_ITEM_CODE = 6;

	/**
	 * 
	 */
	@XmlElement(name="assetId")
	@Sdl
	private long assetId;

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
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="deductFee")
	@Sdl
	private long deductFee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	public void setAssetId(long obj){
		this.assetId = obj;
		onFieldSet(0, obj);
	}

	public long getAssetId(){
		return assetId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(1, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(2, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(3, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setDeductFee(long obj){
		this.deductFee = obj;
		onFieldSet(4, obj);
	}

	public long getDeductFee(){
		return deductFee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(6, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBookInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBookInfo(SBookInfo arg0){
		copy(arg0);
	}

	public void copy(final SBookInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		assetId = rhs.assetId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		amount = rhs.amount;
		deductFee = rhs.deductFee;
		measureId = rhs.measureId;
		itemCode = rhs.itemCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBookInfo rhs=(SBookInfo)rhs0;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(deductFee, rhs.deductFee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(assetId)
		.append(validDate)
		.append(expireDate)
		.append(amount)
		.append(deductFee)
		.append(measureId)
		.append(itemCode)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBookInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "ASSET_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "VALID_DATE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "EXPIRE_DATE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "AMOUNT", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "DEDUCT_FEE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBookInfo.class, "ITEM_CODE", 6, int.class));
}

}