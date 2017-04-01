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
@XmlType(propOrder={"acctId","servId","freezeAmount","validDateTime","expireDateTime","payRuleId","belongDate"})
@Sdl(module="MXdr")
public class SPaymentMiddleInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_FREEZE_AMOUNT = "FREEZE_AMOUNT";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_PAY_RULE_ID = "PAY_RULE_ID";
	public final static String COL_BELONG_DATE = "BELONG_DATE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_SERV_ID = 1;
	public final static int IDX_FREEZE_AMOUNT = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_PAY_RULE_ID = 5;
	public final static int IDX_BELONG_DATE = 6;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="freezeAmount")
	@Sdl
	private long freezeAmount;

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
	@XmlElement(name="payRuleId")
	@Sdl
	private int payRuleId;

	/**
	 * 
	 */
	@XmlElement(name="belongDate")
	@Sdl
	private int belongDate;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(1, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setFreezeAmount(long obj){
		this.freezeAmount = obj;
		onFieldSet(2, obj);
	}

	public long getFreezeAmount(){
		return freezeAmount;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(4, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setPayRuleId(int obj){
		this.payRuleId = obj;
		onFieldSet(5, obj);
	}

	public int getPayRuleId(){
		return payRuleId;
	}

	public void setBelongDate(int obj){
		this.belongDate = obj;
		onFieldSet(6, obj);
	}

	public int getBelongDate(){
		return belongDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPaymentMiddleInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPaymentMiddleInfo(SPaymentMiddleInfo arg0){
		copy(arg0);
	}

	public void copy(final SPaymentMiddleInfo rhs){
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
		servId = rhs.servId;
		freezeAmount = rhs.freezeAmount;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		payRuleId = rhs.payRuleId;
		belongDate = rhs.belongDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPaymentMiddleInfo rhs=(SPaymentMiddleInfo)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(freezeAmount, rhs.freezeAmount)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(payRuleId, rhs.payRuleId)) return false;
		if(!ObjectUtils.equals(belongDate, rhs.belongDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(servId)
		.append(freezeAmount)
		.append(validDateTime)
		.append(expireDateTime)
		.append(payRuleId)
		.append(belongDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPaymentMiddleInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "SERV_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "FREEZE_AMOUNT", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "PAY_RULE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPaymentMiddleInfo.class, "BELONG_DATE", 6, int.class));
}

}