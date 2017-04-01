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
@XmlType(propOrder={"acctId","assetId","assetItemId","assetMeasureId","assetFreezeFee","assetUsedFee","assetAmount","assetType"})
@Sdl(module="MXdr")
public class SAssetQueryVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_ASSET_ITEM_ID = "ASSET_ITEM_ID";
	public final static String COL_ASSET_MEASURE_ID = "ASSET_MEASURE_ID";
	public final static String COL_ASSET_FREEZE_FEE = "ASSET_FREEZE_FEE";
	public final static String COL_ASSET_USED_FEE = "ASSET_USED_FEE";
	public final static String COL_ASSET_AMOUNT = "ASSET_AMOUNT";
	public final static String COL_ASSET_TYPE = "ASSET_TYPE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_ASSET_ID = 1;
	public final static int IDX_ASSET_ITEM_ID = 2;
	public final static int IDX_ASSET_MEASURE_ID = 3;
	public final static int IDX_ASSET_FREEZE_FEE = 4;
	public final static int IDX_ASSET_USED_FEE = 5;
	public final static int IDX_ASSET_AMOUNT = 6;
	public final static int IDX_ASSET_TYPE = 7;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="assetId")
	@Sdl
	private long assetId;

	/**
	 * 
	 */
	@XmlElement(name="assetItemId")
	@Sdl
	private int assetItemId;

	/**
	 * 
	 */
	@XmlElement(name="assetMeasureId")
	@Sdl
	private int assetMeasureId;

	/**
	 * 
	 */
	@XmlElement(name="assetFreezeFee")
	@Sdl
	private long assetFreezeFee;

	/**
	 * 
	 */
	@XmlElement(name="assetUsedFee")
	@Sdl
	private long assetUsedFee;

	/**
	 * 
	 */
	@XmlElement(name="assetAmount")
	@Sdl
	private long assetAmount;

	/**
	 * 
	 */
	@XmlElement(name="assetType")
	@Sdl
	private int assetType;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setAssetId(long obj){
		this.assetId = obj;
		onFieldSet(1, obj);
	}

	public long getAssetId(){
		return assetId;
	}

	public void setAssetItemId(int obj){
		this.assetItemId = obj;
		onFieldSet(2, obj);
	}

	public int getAssetItemId(){
		return assetItemId;
	}

	public void setAssetMeasureId(int obj){
		this.assetMeasureId = obj;
		onFieldSet(3, obj);
	}

	public int getAssetMeasureId(){
		return assetMeasureId;
	}

	public void setAssetFreezeFee(long obj){
		this.assetFreezeFee = obj;
		onFieldSet(4, obj);
	}

	public long getAssetFreezeFee(){
		return assetFreezeFee;
	}

	public void setAssetUsedFee(long obj){
		this.assetUsedFee = obj;
		onFieldSet(5, obj);
	}

	public long getAssetUsedFee(){
		return assetUsedFee;
	}

	public void setAssetAmount(long obj){
		this.assetAmount = obj;
		onFieldSet(6, obj);
	}

	public long getAssetAmount(){
		return assetAmount;
	}

	public void setAssetType(int obj){
		this.assetType = obj;
		onFieldSet(7, obj);
	}

	public int getAssetType(){
		return assetType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetQueryVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAssetQueryVal(SAssetQueryVal arg0){
		copy(arg0);
	}

	public void copy(final SAssetQueryVal rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		acctId = rhs.acctId;
		assetId = rhs.assetId;
		assetItemId = rhs.assetItemId;
		assetMeasureId = rhs.assetMeasureId;
		assetFreezeFee = rhs.assetFreezeFee;
		assetUsedFee = rhs.assetUsedFee;
		assetAmount = rhs.assetAmount;
		assetType = rhs.assetType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAssetQueryVal rhs=(SAssetQueryVal)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(assetItemId, rhs.assetItemId)) return false;
		if(!ObjectUtils.equals(assetMeasureId, rhs.assetMeasureId)) return false;
		if(!ObjectUtils.equals(assetFreezeFee, rhs.assetFreezeFee)) return false;
		if(!ObjectUtils.equals(assetUsedFee, rhs.assetUsedFee)) return false;
		if(!ObjectUtils.equals(assetAmount, rhs.assetAmount)) return false;
		if(!ObjectUtils.equals(assetType, rhs.assetType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(assetId)
		.append(assetItemId)
		.append(assetMeasureId)
		.append(assetFreezeFee)
		.append(assetUsedFee)
		.append(assetAmount)
		.append(assetType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAssetQueryVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_ITEM_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_MEASURE_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_FREEZE_FEE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_USED_FEE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_AMOUNT", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetQueryVal.class, "ASSET_TYPE", 7, int.class));
}

}