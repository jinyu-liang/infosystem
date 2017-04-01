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
@XmlType(propOrder={"prodId","productOfferId","isMain","packageType","prodValidDate","prodExpireDate"})
@Sdl(module="MXdr")
public class SBillingProd extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_PRODUCT_OFFER_ID = "PRODUCT_OFFER_ID";
	public final static String COL_IS_MAIN = "IS_MAIN";
	public final static String COL_PACKAGE_TYPE = "PACKAGE_TYPE";
	public final static String COL_PROD_VALID_DATE = "PROD_VALID_DATE";
	public final static String COL_PROD_EXPIRE_DATE = "PROD_EXPIRE_DATE";
	public final static int IDX_PROD_ID = 0;
	public final static int IDX_PRODUCT_OFFER_ID = 1;
	public final static int IDX_IS_MAIN = 2;
	public final static int IDX_PACKAGE_TYPE = 3;
	public final static int IDX_PROD_VALID_DATE = 4;
	public final static int IDX_PROD_EXPIRE_DATE = 5;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="productOfferId")
	@Sdl
	private int productOfferId;

	/**
	 * 
	 */
	@XmlElement(name="isMain")
	@Sdl
	private short isMain;

	/**
	 * 
	 */
	@XmlElement(name="packageType")
	@Sdl
	private short packageType;

	/**
	 * 
	 */
	@XmlElement(name="prodValidDate")
	@Sdl
	private long prodValidDate;

	/**
	 * 
	 */
	@XmlElement(name="prodExpireDate")
	@Sdl
	private long prodExpireDate;

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(0, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setProductOfferId(int obj){
		this.productOfferId = obj;
		onFieldSet(1, obj);
	}

	public int getProductOfferId(){
		return productOfferId;
	}

	public void setIsMain(short obj){
		this.isMain = obj;
		onFieldSet(2, obj);
	}

	public short getIsMain(){
		return isMain;
	}

	public void setPackageType(short obj){
		this.packageType = obj;
		onFieldSet(3, obj);
	}

	public short getPackageType(){
		return packageType;
	}

	public void setProdValidDate(long obj){
		this.prodValidDate = obj;
		onFieldSet(4, obj);
	}

	public long getProdValidDate(){
		return prodValidDate;
	}

	public void setProdExpireDate(long obj){
		this.prodExpireDate = obj;
		onFieldSet(5, obj);
	}

	public long getProdExpireDate(){
		return prodExpireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingProd(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingProd(SBillingProd arg0){
		copy(arg0);
	}

	public void copy(final SBillingProd rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		prodId = rhs.prodId;
		productOfferId = rhs.productOfferId;
		isMain = rhs.isMain;
		packageType = rhs.packageType;
		prodValidDate = rhs.prodValidDate;
		prodExpireDate = rhs.prodExpireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingProd rhs=(SBillingProd)rhs0;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(productOfferId, rhs.productOfferId)) return false;
		if(!ObjectUtils.equals(isMain, rhs.isMain)) return false;
		if(!ObjectUtils.equals(packageType, rhs.packageType)) return false;
		if(!ObjectUtils.equals(prodValidDate, rhs.prodValidDate)) return false;
		if(!ObjectUtils.equals(prodExpireDate, rhs.prodExpireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodId)
		.append(productOfferId)
		.append(isMain)
		.append(packageType)
		.append(prodValidDate)
		.append(prodExpireDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingProd";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "PROD_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "PRODUCT_OFFER_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "IS_MAIN", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "PACKAGE_TYPE", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "PROD_VALID_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProd.class, "PROD_EXPIRE_DATE", 5, long.class));
}

}