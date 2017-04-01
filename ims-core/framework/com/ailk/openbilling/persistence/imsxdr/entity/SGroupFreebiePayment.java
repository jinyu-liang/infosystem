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
@XmlType(propOrder={"payforAcctId","payforRuleId","priority","validDateTime","expireDateTime","ownerId","freeresItemId","threshold"})
@Sdl(module="MXdr")
public class SGroupFreebiePayment extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PAYFOR_ACCT_ID = "PAYFOR_ACCT_ID";
	public final static String COL_PAYFOR_RULE_ID = "PAYFOR_RULE_ID";
	public final static String COL_PRIORITY = "PRIORITY";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_THRESHOLD = "THRESHOLD";
	public final static int IDX_PAYFOR_ACCT_ID = 0;
	public final static int IDX_PAYFOR_RULE_ID = 1;
	public final static int IDX_PRIORITY = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_OWNER_ID = 5;
	public final static int IDX_FREERES_ITEM_ID = 6;
	public final static int IDX_THRESHOLD = 7;

	/**
	 * 
	 */
	@XmlElement(name="payforAcctId")
	@Sdl
	private long payforAcctId;

	/**
	 * 
	 */
	@XmlElement(name="payforRuleId")
	@Sdl
	private int payforRuleId;

	/**
	 * 
	 */
	@XmlElement(name="priority")
	@Sdl
	private int priority;

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
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="freeresItemId")
	@Sdl
	private int freeresItemId;

	/**
	 * 
	 */
	@XmlElement(name="threshold")
	@Sdl
	private long threshold;

	public void setPayforAcctId(long obj){
		this.payforAcctId = obj;
		onFieldSet(0, obj);
	}

	public long getPayforAcctId(){
		return payforAcctId;
	}

	public void setPayforRuleId(int obj){
		this.payforRuleId = obj;
		onFieldSet(1, obj);
	}

	public int getPayforRuleId(){
		return payforRuleId;
	}

	public void setPriority(int obj){
		this.priority = obj;
		onFieldSet(2, obj);
	}

	public int getPriority(){
		return priority;
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

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(5, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setFreeresItemId(int obj){
		this.freeresItemId = obj;
		onFieldSet(6, obj);
	}

	public int getFreeresItemId(){
		return freeresItemId;
	}

	public void setThreshold(long obj){
		this.threshold = obj;
		onFieldSet(7, obj);
	}

	public long getThreshold(){
		return threshold;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGroupFreebiePayment(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGroupFreebiePayment(SGroupFreebiePayment arg0){
		copy(arg0);
	}

	public void copy(final SGroupFreebiePayment rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		payforAcctId = rhs.payforAcctId;
		payforRuleId = rhs.payforRuleId;
		priority = rhs.priority;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		ownerId = rhs.ownerId;
		freeresItemId = rhs.freeresItemId;
		threshold = rhs.threshold;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGroupFreebiePayment rhs=(SGroupFreebiePayment)rhs0;
		if(!ObjectUtils.equals(payforAcctId, rhs.payforAcctId)) return false;
		if(!ObjectUtils.equals(payforRuleId, rhs.payforRuleId)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payforAcctId)
		.append(payforRuleId)
		.append(priority)
		.append(validDateTime)
		.append(expireDateTime)
		.append(ownerId)
		.append(freeresItemId)
		.append(threshold)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGroupFreebiePayment";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "PAYFOR_ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "PAYFOR_RULE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "PRIORITY", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "OWNER_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "FREERES_ITEM_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupFreebiePayment.class, "THRESHOLD", 7, long.class));
}

}