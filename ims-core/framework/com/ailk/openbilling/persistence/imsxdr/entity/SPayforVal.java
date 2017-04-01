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
@XmlType(propOrder={"acctId","payRuleId","servId","productId","payforAmount","validDateTime","expireDateTime","measureId"})
@Sdl(module="MXdr")
public class SPayforVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_PAY_RULE_ID = "PAY_RULE_ID";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PAYFOR_AMOUNT = "PAYFOR_AMOUNT";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_PAY_RULE_ID = 1;
	public final static int IDX_SERV_ID = 2;
	public final static int IDX_PRODUCT_ID = 3;
	public final static int IDX_PAYFOR_AMOUNT = 4;
	public final static int IDX_VALID_DATE_TIME = 5;
	public final static int IDX_EXPIRE_DATE_TIME = 6;
	public final static int IDX_MEASURE_ID = 7;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="payRuleId")
	@Sdl
	private int payRuleId;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="payforAmount")
	@Sdl
	private long payforAmount;

	/**
	 * 
	 */
	@XmlElement(name="validDateTime")
	@Sdl
	private long validDateTime;

	/**
	 * 
	 */
	@XmlElement(name="expireDateTime")
	@Sdl
	private long expireDateTime;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setPayRuleId(int obj){
		this.payRuleId = obj;
		onFieldSet(1, obj);
	}

	public int getPayRuleId(){
		return payRuleId;
	}

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(2, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(3, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setPayforAmount(long obj){
		this.payforAmount = obj;
		onFieldSet(4, obj);
	}

	public long getPayforAmount(){
		return payforAmount;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(5, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(7, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPayforVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPayforVal(SPayforVal arg0){
		copy(arg0);
	}

	public void copy(final SPayforVal rhs){
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
		payRuleId = rhs.payRuleId;
		servId = rhs.servId;
		productId = rhs.productId;
		payforAmount = rhs.payforAmount;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPayforVal rhs=(SPayforVal)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(payRuleId, rhs.payRuleId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(payforAmount, rhs.payforAmount)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(payRuleId)
		.append(servId)
		.append(productId)
		.append(payforAmount)
		.append(validDateTime)
		.append(expireDateTime)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPayforVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "PAY_RULE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "SERV_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "PRODUCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "PAYFOR_AMOUNT", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "VALID_DATE_TIME", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "EXPIRE_DATE_TIME", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayforVal.class, "MEASURE_ID", 7, int.class));
}

}