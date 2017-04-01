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
@XmlType(propOrder={"id","objectId","objectType","offeringId","validDate","expireDate","createDate","soNbr","sts","dealSts","dealDate","dealCount","errorCode","errorMsg"})
@Sdl(module="MImsTsDef")
public class SImsOrderProduct extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_OFFERING_ID = "OFFERING_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_STS = "STS";
	public final static String COL_DEAL_STS = "DEAL_STS";
	public final static String COL_DEAL_DATE = "DEAL_DATE";
	public final static String COL_DEAL_COUNT = "DEAL_COUNT";
	public final static String COL_ERROR_CODE = "ERROR_CODE";
	public final static String COL_ERROR_MSG = "ERROR_MSG";
	public final static int IDX_ID = 0;
	public final static int IDX_OBJECT_ID = 1;
	public final static int IDX_OBJECT_TYPE = 2;
	public final static int IDX_OFFERING_ID = 3;
	public final static int IDX_VALID_DATE = 4;
	public final static int IDX_EXPIRE_DATE = 5;
	public final static int IDX_CREATE_DATE = 6;
	public final static int IDX_SO_NBR = 7;
	public final static int IDX_STS = 8;
	public final static int IDX_DEAL_STS = 9;
	public final static int IDX_DEAL_DATE = 10;
	public final static int IDX_DEAL_COUNT = 11;
	public final static int IDX_ERROR_CODE = 12;
	public final static int IDX_ERROR_MSG = 13;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

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
	@XmlElement(name="offeringId")
	@Sdl
	private int offeringId;

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
	@XmlElement(name="soNbr")
	@Sdl
	private long soNbr;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="dealSts")
	@Sdl
	private int dealSts;

	/**
	 * 
	 */
	@XmlElement(name="dealDate")
	@Sdl
	private Date dealDate;

	/**
	 * 
	 */
	@XmlElement(name="dealCount")
	@Sdl
	private int dealCount;

	/**
	 * 
	 */
	@XmlElement(name="errorCode")
	@Sdl
	private int errorCode;

	/**
	 * 
	 */
	@XmlElement(name="errorMsg")
	@Sdl
	private String errorMsg;

	public void setId(long obj){
		this.id = obj;
		onFieldSet(0, obj);
	}

	public long getId(){
		return id;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(1, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(int obj){
		this.objectType = obj;
		onFieldSet(2, obj);
	}

	public int getObjectType(){
		return objectType;
	}

	public void setOfferingId(int obj){
		this.offeringId = obj;
		onFieldSet(3, obj);
	}

	public int getOfferingId(){
		return offeringId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(4, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(5, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(6, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoNbr(long obj){
		this.soNbr = obj;
		onFieldSet(7, obj);
	}

	public long getSoNbr(){
		return soNbr;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(8, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setDealSts(int obj){
		this.dealSts = obj;
		onFieldSet(9, obj);
	}

	public int getDealSts(){
		return dealSts;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
		onFieldSet(10, obj);
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setDealCount(int obj){
		this.dealCount = obj;
		onFieldSet(11, obj);
	}

	public int getDealCount(){
		return dealCount;
	}

	public void setErrorCode(int obj){
		this.errorCode = obj;
		onFieldSet(12, obj);
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
		onFieldSet(13, obj);
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SImsOrderProduct(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 14; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SImsOrderProduct(SImsOrderProduct arg0){
		copy(arg0);
	}

	public void copy(final SImsOrderProduct rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		id = rhs.id;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		offeringId = rhs.offeringId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		createDate = rhs.createDate;
		soNbr = rhs.soNbr;
		sts = rhs.sts;
		dealSts = rhs.dealSts;
		dealDate = rhs.dealDate;
		dealCount = rhs.dealCount;
		errorCode = rhs.errorCode;
		errorMsg = rhs.errorMsg;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SImsOrderProduct rhs=(SImsOrderProduct)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(offeringId, rhs.offeringId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(dealSts, rhs.dealSts)) return false;
		if(!ObjectUtils.equals(dealDate, rhs.dealDate)) return false;
		if(!ObjectUtils.equals(dealCount, rhs.dealCount)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(errorMsg, rhs.errorMsg)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(objectId)
		.append(objectType)
		.append(offeringId)
		.append(validDate)
		.append(expireDate)
		.append(createDate)
		.append(soNbr)
		.append(sts)
		.append(dealSts)
		.append(dealDate)
		.append(dealCount)
		.append(errorCode)
		.append(errorMsg)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(14);
public static final long BITS_ALL_MARKER = 0x2000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SImsOrderProduct";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "OBJECT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "OBJECT_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "OFFERING_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "VALID_DATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "EXPIRE_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "CREATE_DATE", 6, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "SO_NBR", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "STS", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "DEAL_STS", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "DEAL_DATE", 10, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "DEAL_COUNT", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "ERROR_CODE", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImsOrderProduct.class, "ERROR_MSG", 13, String.class));
}

}