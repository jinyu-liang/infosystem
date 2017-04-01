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
@XmlType(propOrder={"acctId","assetId","needFee","freezeFee"})
@Sdl(module="MXdr")
public class SAssetChargeVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_NEED_FEE = "NEED_FEE";
	public final static String COL_FREEZE_FEE = "FREEZE_FEE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_ASSET_ID = 1;
	public final static int IDX_NEED_FEE = 2;
	public final static int IDX_FREEZE_FEE = 3;

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
	@XmlElement(name="needFee")
	@Sdl
	private long needFee;

	/**
	 * 
	 */
	@XmlElement(name="freezeFee")
	@Sdl
	private long freezeFee;

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

	public void setNeedFee(long obj){
		this.needFee = obj;
		onFieldSet(2, obj);
	}

	public long getNeedFee(){
		return needFee;
	}

	public void setFreezeFee(long obj){
		this.freezeFee = obj;
		onFieldSet(3, obj);
	}

	public long getFreezeFee(){
		return freezeFee;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetChargeVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAssetChargeVal(SAssetChargeVal arg0){
		copy(arg0);
	}

	public void copy(final SAssetChargeVal rhs){
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
		needFee = rhs.needFee;
		freezeFee = rhs.freezeFee;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAssetChargeVal rhs=(SAssetChargeVal)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(needFee, rhs.needFee)) return false;
		if(!ObjectUtils.equals(freezeFee, rhs.freezeFee)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(assetId)
		.append(needFee)
		.append(freezeFee)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAssetChargeVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetChargeVal.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetChargeVal.class, "ASSET_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetChargeVal.class, "NEED_FEE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetChargeVal.class, "FREEZE_FEE", 3, long.class));
}

}