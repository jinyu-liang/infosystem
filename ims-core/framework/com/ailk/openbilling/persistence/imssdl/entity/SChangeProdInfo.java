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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","offerId","operType","objectId","objectType","validDate","expireDate","paramList"})
@Sdl(module="MImsSyncDef")
public class SChangeProdInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_OPER_TYPE = "OPER_TYPE";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_PARAM_LIST = "PARAM_LIST";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_OFFER_ID = 1;
	public final static int IDX_OPER_TYPE = 2;
	public final static int IDX_OBJECT_ID = 3;
	public final static int IDX_OBJECT_TYPE = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_PARAM_LIST = 7;

	/**
	 * 
	 */
	@XmlElement(name="paramList")
	@Sdl
	private List<SParamInfo> paramList;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="offerId")
	@Sdl
	private int offerId;

	/**
	 * 
	 */
	@XmlElement(name="operType")
	@Sdl
	private int operType;

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
	@XmlElement(name="validDate")
	@Sdl
	private Date validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Date expireDate;

	public void setParamList(List<SParamInfo> obj){
		this.paramList = obj;
		onFieldSet(7, obj);
	}

	public List<SParamInfo> getParamList(){
		return paramList;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setOfferId(int obj){
		this.offerId = obj;
		onFieldSet(1, obj);
	}

	public int getOfferId(){
		return offerId;
	}

	public void setOperType(int obj){
		this.operType = obj;
		onFieldSet(2, obj);
	}

	public int getOperType(){
		return operType;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(3, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(int obj){
		this.objectType = obj;
		onFieldSet(4, obj);
	}

	public int getObjectType(){
		return objectType;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SChangeProdInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SChangeProdInfo(SChangeProdInfo arg0){
		copy(arg0);
	}

	public void copy(final SChangeProdInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		productId = rhs.productId;
		offerId = rhs.offerId;
		operType = rhs.operType;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		paramList = rhs.paramList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChangeProdInfo rhs=(SChangeProdInfo)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(paramList, rhs.paramList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(offerId)
		.append(operType)
		.append(objectId)
		.append(objectType)
		.append(validDate)
		.append(expireDate)
		.append(paramList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SChangeProdInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "OFFER_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "OPER_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "OBJECT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "OBJECT_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "VALID_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "EXPIRE_DATE", 6, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SChangeProdInfo.class, "PARAM_LIST", 7, List.class));
}

}