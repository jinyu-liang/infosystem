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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"phoneId","modifyType","assetItemId","amount","validityAmount","measureId","deductType","transparentData1","transparentData2","transparentData3","extraMap"})
@Sdl(module="MXdr")
public class SAdjustAsset extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_MODIFY_TYPE = "MODIFY_TYPE";
	public final static String COL_ASSET_ITEM_ID = "ASSET_ITEM_ID";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_VALIDITY_AMOUNT = "VALIDITY_AMOUNT";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_DEDUCT_TYPE = "DEDUCT_TYPE";
	public final static String COL_TRANSPARENT_DATA1 = "TRANSPARENT_DATA1";
	public final static String COL_TRANSPARENT_DATA2 = "TRANSPARENT_DATA2";
	public final static String COL_TRANSPARENT_DATA3 = "TRANSPARENT_DATA3";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_PHONE_ID = 0;
	public final static int IDX_MODIFY_TYPE = 1;
	public final static int IDX_ASSET_ITEM_ID = 2;
	public final static int IDX_AMOUNT = 3;
	public final static int IDX_VALIDITY_AMOUNT = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_DEDUCT_TYPE = 6;
	public final static int IDX_TRANSPARENT_DATA1 = 7;
	public final static int IDX_TRANSPARENT_DATA2 = 8;
	public final static int IDX_TRANSPARENT_DATA3 = 9;
	public final static int IDX_EXTRA_MAP = 10;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="modifyType")
	@Sdl
	private short modifyType;

	/**
	 * 
	 */
	@XmlElement(name="assetItemId")
	@Sdl
	private long assetItemId;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="validityAmount")
	@Sdl
	private int validityAmount;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="deductType")
	@Sdl
	private int deductType;

	/**
	 * 
	 */
	@XmlElement(name="transparentData1")
	@Sdl
	private String transparentData1;

	/**
	 * 
	 */
	@XmlElement(name="transparentData2")
	@Sdl
	private String transparentData2;

	/**
	 * 
	 */
	@XmlElement(name="transparentData3")
	@Sdl
	private String transparentData3;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(10, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(0, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setModifyType(short obj){
		this.modifyType = obj;
		onFieldSet(1, obj);
	}

	public short getModifyType(){
		return modifyType;
	}

	public void setAssetItemId(long obj){
		this.assetItemId = obj;
		onFieldSet(2, obj);
	}

	public long getAssetItemId(){
		return assetItemId;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(3, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setValidityAmount(int obj){
		this.validityAmount = obj;
		onFieldSet(4, obj);
	}

	public int getValidityAmount(){
		return validityAmount;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setDeductType(int obj){
		this.deductType = obj;
		onFieldSet(6, obj);
	}

	public int getDeductType(){
		return deductType;
	}

	public void setTransparentData1(String obj){
		this.transparentData1 = obj;
		onFieldSet(7, obj);
	}

	public String getTransparentData1(){
		return transparentData1;
	}

	public void setTransparentData2(String obj){
		this.transparentData2 = obj;
		onFieldSet(8, obj);
	}

	public String getTransparentData2(){
		return transparentData2;
	}

	public void setTransparentData3(String obj){
		this.transparentData3 = obj;
		onFieldSet(9, obj);
	}

	public String getTransparentData3(){
		return transparentData3;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAdjustAsset(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAdjustAsset(SAdjustAsset arg0){
		copy(arg0);
	}

	public void copy(final SAdjustAsset rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		phoneId = rhs.phoneId;
		modifyType = rhs.modifyType;
		assetItemId = rhs.assetItemId;
		amount = rhs.amount;
		validityAmount = rhs.validityAmount;
		measureId = rhs.measureId;
		deductType = rhs.deductType;
		transparentData1 = rhs.transparentData1;
		transparentData2 = rhs.transparentData2;
		transparentData3 = rhs.transparentData3;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAdjustAsset rhs=(SAdjustAsset)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(modifyType, rhs.modifyType)) return false;
		if(!ObjectUtils.equals(assetItemId, rhs.assetItemId)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(validityAmount, rhs.validityAmount)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(deductType, rhs.deductType)) return false;
		if(!ObjectUtils.equals(transparentData1, rhs.transparentData1)) return false;
		if(!ObjectUtils.equals(transparentData2, rhs.transparentData2)) return false;
		if(!ObjectUtils.equals(transparentData3, rhs.transparentData3)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.append(modifyType)
		.append(assetItemId)
		.append(amount)
		.append(validityAmount)
		.append(measureId)
		.append(deductType)
		.append(transparentData1)
		.append(transparentData2)
		.append(transparentData3)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAdjustAsset";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "PHONE_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "MODIFY_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "ASSET_ITEM_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "AMOUNT", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "VALIDITY_AMOUNT", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "DEDUCT_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "TRANSPARENT_DATA1", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "TRANSPARENT_DATA2", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "TRANSPARENT_DATA3", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustAsset.class, "EXTRA_MAP", 10, Map.class));
}

}