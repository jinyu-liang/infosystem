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
@XmlType(propOrder={"upSellConfirm","upFlag","upTime","prodId","orgOfferId","tarOfferId","useage","freeResourceAmount"})
@Sdl(module="MImsSyncDef")
public class SUpSellReq extends CsdlStructObject implements IComplexEntity{

	public final static String COL_UP_SELL_CONFIRM = "UP_SELL_CONFIRM";
	public final static String COL_UP_FLAG = "UP_FLAG";
	public final static String COL_UP_TIME = "UP_TIME";
	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_ORG_OFFER_ID = "ORG_OFFER_ID";
	public final static String COL_TAR_OFFER_ID = "TAR_OFFER_ID";
	public final static String COL_USEAGE = "USEAGE";
	public final static String COL_FREE_RESOURCE_AMOUNT = "FREE_RESOURCE_AMOUNT";
	public final static int IDX_UP_SELL_CONFIRM = 0;
	public final static int IDX_UP_FLAG = 1;
	public final static int IDX_UP_TIME = 2;
	public final static int IDX_PROD_ID = 3;
	public final static int IDX_ORG_OFFER_ID = 4;
	public final static int IDX_TAR_OFFER_ID = 5;
	public final static int IDX_USEAGE = 6;
	public final static int IDX_FREE_RESOURCE_AMOUNT = 7;

	/**
	 * 
	 */
	@XmlElement(name="upSellConfirm")
	@Sdl
	private short upSellConfirm;

	/**
	 * 
	 */
	@XmlElement(name="upFlag")
	@Sdl
	private short upFlag;

	/**
	 * 
	 */
	@XmlElement(name="upTime")
	@Sdl
	private Date upTime;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="orgOfferId")
	@Sdl
	private int orgOfferId;

	/**
	 * 
	 */
	@XmlElement(name="tarOfferId")
	@Sdl
	private int tarOfferId;

	/**
	 * 
	 */
	@XmlElement(name="useage")
	@Sdl
	private long useage;

	/**
	 * 
	 */
	@XmlElement(name="freeResourceAmount")
	@Sdl
	private long freeResourceAmount;

	public void setUpSellConfirm(short obj){
		this.upSellConfirm = obj;
		onFieldSet(0, obj);
	}

	public short getUpSellConfirm(){
		return upSellConfirm;
	}

	public void setUpFlag(short obj){
		this.upFlag = obj;
		onFieldSet(1, obj);
	}

	public short getUpFlag(){
		return upFlag;
	}

	public void setUpTime(Date obj){
		this.upTime = obj;
		onFieldSet(2, obj);
	}

	public Date getUpTime(){
		return upTime;
	}

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(3, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setOrgOfferId(int obj){
		this.orgOfferId = obj;
		onFieldSet(4, obj);
	}

	public int getOrgOfferId(){
		return orgOfferId;
	}

	public void setTarOfferId(int obj){
		this.tarOfferId = obj;
		onFieldSet(5, obj);
	}

	public int getTarOfferId(){
		return tarOfferId;
	}

	public void setUseage(long obj){
		this.useage = obj;
		onFieldSet(6, obj);
	}

	public long getUseage(){
		return useage;
	}

	public void setFreeResourceAmount(long obj){
		this.freeResourceAmount = obj;
		onFieldSet(7, obj);
	}

	public long getFreeResourceAmount(){
		return freeResourceAmount;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUpSellReq(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUpSellReq(SUpSellReq arg0){
		copy(arg0);
	}

	public void copy(final SUpSellReq rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		upSellConfirm = rhs.upSellConfirm;
		upFlag = rhs.upFlag;
		upTime = rhs.upTime;
		prodId = rhs.prodId;
		orgOfferId = rhs.orgOfferId;
		tarOfferId = rhs.tarOfferId;
		useage = rhs.useage;
		freeResourceAmount = rhs.freeResourceAmount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUpSellReq rhs=(SUpSellReq)rhs0;
		if(!ObjectUtils.equals(upSellConfirm, rhs.upSellConfirm)) return false;
		if(!ObjectUtils.equals(upFlag, rhs.upFlag)) return false;
		if(!ObjectUtils.equals(upTime, rhs.upTime)) return false;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(orgOfferId, rhs.orgOfferId)) return false;
		if(!ObjectUtils.equals(tarOfferId, rhs.tarOfferId)) return false;
		if(!ObjectUtils.equals(useage, rhs.useage)) return false;
		if(!ObjectUtils.equals(freeResourceAmount, rhs.freeResourceAmount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(upSellConfirm)
		.append(upFlag)
		.append(upTime)
		.append(prodId)
		.append(orgOfferId)
		.append(tarOfferId)
		.append(useage)
		.append(freeResourceAmount)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SUpSellReq";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "UP_SELL_CONFIRM", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "UP_FLAG", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "UP_TIME", 2, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "PROD_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "ORG_OFFER_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "TAR_OFFER_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "USEAGE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpSellReq.class, "FREE_RESOURCE_AMOUNT", 7, long.class));
}

}