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
@XmlType(propOrder={"numerator","denominator","productId"})
@Sdl(module="MXdr")
public class SFreebiePromote extends CsdlStructObject implements IComplexEntity{

	public final static String COL_NUMERATOR = "NUMERATOR";
	public final static String COL_DENOMINATOR = "DENOMINATOR";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static int IDX_NUMERATOR = 0;
	public final static int IDX_DENOMINATOR = 1;
	public final static int IDX_PRODUCT_ID = 2;

	/**
	 * 
	 */
	@XmlElement(name="numerator")
	@Sdl
	private int numerator;

	/**
	 * 
	 */
	@XmlElement(name="denominator")
	@Sdl
	private int denominator;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	public void setNumerator(int obj){
		this.numerator = obj;
		onFieldSet(0, obj);
	}

	public int getNumerator(){
		return numerator;
	}

	public void setDenominator(int obj){
		this.denominator = obj;
		onFieldSet(1, obj);
	}

	public int getDenominator(){
		return denominator;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(2, obj);
	}

	public long getProductId(){
		return productId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreebiePromote(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreebiePromote(SFreebiePromote arg0){
		copy(arg0);
	}

	public void copy(final SFreebiePromote rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		numerator = rhs.numerator;
		denominator = rhs.denominator;
		productId = rhs.productId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreebiePromote rhs=(SFreebiePromote)rhs0;
		if(!ObjectUtils.equals(numerator, rhs.numerator)) return false;
		if(!ObjectUtils.equals(denominator, rhs.denominator)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(numerator)
		.append(denominator)
		.append(productId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreebiePromote";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreebiePromote.class, "NUMERATOR", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreebiePromote.class, "DENOMINATOR", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreebiePromote.class, "PRODUCT_ID", 2, long.class));
}

}