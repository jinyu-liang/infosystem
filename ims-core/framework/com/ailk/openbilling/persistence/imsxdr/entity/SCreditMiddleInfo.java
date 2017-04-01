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
@XmlType(propOrder={"acctId","assetId","freezeAmount","assetItemId"})
@Sdl(module="MXdr")
public class SCreditMiddleInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_FREEZE_AMOUNT = "FREEZE_AMOUNT";
	public final static String COL_ASSET_ITEM_ID = "ASSET_ITEM_ID";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_ASSET_ID = 1;
	public final static int IDX_FREEZE_AMOUNT = 2;
	public final static int IDX_ASSET_ITEM_ID = 3;

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
	@XmlElement(name="freezeAmount")
	@Sdl
	private long freezeAmount;

	/**
	 * 
	 */
	@XmlElement(name="assetItemId")
	@Sdl
	private int assetItemId;

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

	public void setFreezeAmount(long obj){
		this.freezeAmount = obj;
		onFieldSet(2, obj);
	}

	public long getFreezeAmount(){
		return freezeAmount;
	}

	public void setAssetItemId(int obj){
		this.assetItemId = obj;
		onFieldSet(3, obj);
	}

	public int getAssetItemId(){
		return assetItemId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCreditMiddleInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCreditMiddleInfo(SCreditMiddleInfo arg0){
		copy(arg0);
	}

	public void copy(final SCreditMiddleInfo rhs){
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
		freezeAmount = rhs.freezeAmount;
		assetItemId = rhs.assetItemId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCreditMiddleInfo rhs=(SCreditMiddleInfo)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(freezeAmount, rhs.freezeAmount)) return false;
		if(!ObjectUtils.equals(assetItemId, rhs.assetItemId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(assetId)
		.append(freezeAmount)
		.append(assetItemId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCreditMiddleInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditMiddleInfo.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditMiddleInfo.class, "ASSET_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditMiddleInfo.class, "FREEZE_AMOUNT", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditMiddleInfo.class, "ASSET_ITEM_ID", 3, int.class));
}

}