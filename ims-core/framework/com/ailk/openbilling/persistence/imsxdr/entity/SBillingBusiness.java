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
@XmlType(propOrder={"soNbr","outSoNbr","busiCode","serviceType","operationTime","topupNumber","pricingPlanId","productOfferId"})
@Sdl(module="MXdr")
public class SBillingBusiness extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_OUT_SO_NBR = "OUT_SO_NBR";
	public final static String COL_BUSI_CODE = "BUSI_CODE";
	public final static String COL_SERVICE_TYPE = "SERVICE_TYPE";
	public final static String COL_OPERATION_TIME = "OPERATION_TIME";
	public final static String COL_TOPUP_NUMBER = "TOPUP_NUMBER";
	public final static String COL_PRICING_PLAN_ID = "PRICING_PLAN_ID";
	public final static String COL_PRODUCT_OFFER_ID = "PRODUCT_OFFER_ID";
	public final static int IDX_SO_NBR = 0;
	public final static int IDX_OUT_SO_NBR = 1;
	public final static int IDX_BUSI_CODE = 2;
	public final static int IDX_SERVICE_TYPE = 3;
	public final static int IDX_OPERATION_TIME = 4;
	public final static int IDX_TOPUP_NUMBER = 5;
	public final static int IDX_PRICING_PLAN_ID = 6;
	public final static int IDX_PRODUCT_OFFER_ID = 7;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private String soNbr;

	/**
	 * 
	 */
	@XmlElement(name="outSoNbr")
	@Sdl
	private String outSoNbr;

	/**
	 * 
	 */
	@XmlElement(name="busiCode")
	@Sdl
	private int busiCode;

	/**
	 * 
	 */
	@XmlElement(name="serviceType")
	@Sdl
	private short serviceType;

	/**
	 * 
	 */
	@XmlElement(name="operationTime")
	@Sdl
	private long operationTime;

	/**
	 * 
	 */
	@XmlElement(name="topupNumber")
	@Sdl
	private String topupNumber;

	/**
	 * 
	 */
	@XmlElement(name="pricingPlanId")
	@Sdl
	private int pricingPlanId;

	/**
	 * 
	 */
	@XmlElement(name="productOfferId")
	@Sdl
	private int productOfferId;

	public void setSoNbr(String obj){
		this.soNbr = obj;
		onFieldSet(0, obj);
	}

	public String getSoNbr(){
		return soNbr;
	}

	public void setOutSoNbr(String obj){
		this.outSoNbr = obj;
		onFieldSet(1, obj);
	}

	public String getOutSoNbr(){
		return outSoNbr;
	}

	public void setBusiCode(int obj){
		this.busiCode = obj;
		onFieldSet(2, obj);
	}

	public int getBusiCode(){
		return busiCode;
	}

	public void setServiceType(short obj){
		this.serviceType = obj;
		onFieldSet(3, obj);
	}

	public short getServiceType(){
		return serviceType;
	}

	public void setOperationTime(long obj){
		this.operationTime = obj;
		onFieldSet(4, obj);
	}

	public long getOperationTime(){
		return operationTime;
	}

	public void setTopupNumber(String obj){
		this.topupNumber = obj;
		onFieldSet(5, obj);
	}

	public String getTopupNumber(){
		return topupNumber;
	}

	public void setPricingPlanId(int obj){
		this.pricingPlanId = obj;
		onFieldSet(6, obj);
	}

	public int getPricingPlanId(){
		return pricingPlanId;
	}

	public void setProductOfferId(int obj){
		this.productOfferId = obj;
		onFieldSet(7, obj);
	}

	public int getProductOfferId(){
		return productOfferId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingBusiness(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingBusiness(SBillingBusiness arg0){
		copy(arg0);
	}

	public void copy(final SBillingBusiness rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		soNbr = rhs.soNbr;
		outSoNbr = rhs.outSoNbr;
		busiCode = rhs.busiCode;
		serviceType = rhs.serviceType;
		operationTime = rhs.operationTime;
		topupNumber = rhs.topupNumber;
		pricingPlanId = rhs.pricingPlanId;
		productOfferId = rhs.productOfferId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingBusiness rhs=(SBillingBusiness)rhs0;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(outSoNbr, rhs.outSoNbr)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		if(!ObjectUtils.equals(serviceType, rhs.serviceType)) return false;
		if(!ObjectUtils.equals(operationTime, rhs.operationTime)) return false;
		if(!ObjectUtils.equals(topupNumber, rhs.topupNumber)) return false;
		if(!ObjectUtils.equals(pricingPlanId, rhs.pricingPlanId)) return false;
		if(!ObjectUtils.equals(productOfferId, rhs.productOfferId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soNbr)
		.append(outSoNbr)
		.append(busiCode)
		.append(serviceType)
		.append(operationTime)
		.append(topupNumber)
		.append(pricingPlanId)
		.append(productOfferId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingBusiness";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "SO_NBR", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "OUT_SO_NBR", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "BUSI_CODE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "SERVICE_TYPE", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "OPERATION_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "TOPUP_NUMBER", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "PRICING_PLAN_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBusiness.class, "PRODUCT_OFFER_ID", 7, int.class));
}

}