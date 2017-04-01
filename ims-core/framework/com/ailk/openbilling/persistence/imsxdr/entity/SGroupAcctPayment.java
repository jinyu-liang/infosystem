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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"payforServId","payforAcctId","specAcctType","segId","priority","policyId","subPayforRule"})
@Sdl(module="MXdr")
public class SGroupAcctPayment extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PAYFOR_SERV_ID = "PAYFOR_SERV_ID";
	public final static String COL_PAYFOR_ACCT_ID = "PAYFOR_ACCT_ID";
	public final static String COL_SPEC_ACCT_TYPE = "SPEC_ACCT_TYPE";
	public final static String COL_SEG_ID = "SEG_ID";
	public final static String COL_PRIORITY = "PRIORITY";
	public final static String COL_POLICY_ID = "POLICY_ID";
	public final static String COL_SUB_PAYFOR_RULE = "SUB_PAYFOR_RULE";
	public final static int IDX_PAYFOR_SERV_ID = 0;
	public final static int IDX_PAYFOR_ACCT_ID = 1;
	public final static int IDX_SPEC_ACCT_TYPE = 2;
	public final static int IDX_SEG_ID = 3;
	public final static int IDX_PRIORITY = 4;
	public final static int IDX_POLICY_ID = 5;
	public final static int IDX_SUB_PAYFOR_RULE = 6;

	/**
	 * 
	 */
	@XmlElement(name="subPayforRule")
	@Sdl
	private Map<Integer,SSubPayforRule> subPayforRule;

	/**
	 * 
	 */
	@XmlElement(name="payforServId")
	@Sdl
	private long payforServId;

	/**
	 * 
	 */
	@XmlElement(name="payforAcctId")
	@Sdl
	private long payforAcctId;

	/**
	 * 
	 */
	@XmlElement(name="specAcctType")
	@Sdl
	private int specAcctType;

	/**
	 * 
	 */
	@XmlElement(name="segId")
	@Sdl
	private int segId;

	/**
	 * 
	 */
	@XmlElement(name="priority")
	@Sdl
	private int priority;

	/**
	 * 
	 */
	@XmlElement(name="policyId")
	@Sdl
	private int policyId;

	public void setSubPayforRule(Map<Integer,SSubPayforRule> obj){
		this.subPayforRule = obj;
		onFieldSet(6, obj);
	}

	public Map<Integer,SSubPayforRule> getSubPayforRule(){
		return subPayforRule;
	}

	public void setPayforServId(long obj){
		this.payforServId = obj;
		onFieldSet(0, obj);
	}

	public long getPayforServId(){
		return payforServId;
	}

	public void setPayforAcctId(long obj){
		this.payforAcctId = obj;
		onFieldSet(1, obj);
	}

	public long getPayforAcctId(){
		return payforAcctId;
	}

	public void setSpecAcctType(int obj){
		this.specAcctType = obj;
		onFieldSet(2, obj);
	}

	public int getSpecAcctType(){
		return specAcctType;
	}

	public void setSegId(int obj){
		this.segId = obj;
		onFieldSet(3, obj);
	}

	public int getSegId(){
		return segId;
	}

	public void setPriority(int obj){
		this.priority = obj;
		onFieldSet(4, obj);
	}

	public int getPriority(){
		return priority;
	}

	public void setPolicyId(int obj){
		this.policyId = obj;
		onFieldSet(5, obj);
	}

	public int getPolicyId(){
		return policyId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGroupAcctPayment(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGroupAcctPayment(SGroupAcctPayment arg0){
		copy(arg0);
	}

	public void copy(final SGroupAcctPayment rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		payforServId = rhs.payforServId;
		payforAcctId = rhs.payforAcctId;
		specAcctType = rhs.specAcctType;
		segId = rhs.segId;
		priority = rhs.priority;
		policyId = rhs.policyId;
		subPayforRule = rhs.subPayforRule;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGroupAcctPayment rhs=(SGroupAcctPayment)rhs0;
		if(!ObjectUtils.equals(payforServId, rhs.payforServId)) return false;
		if(!ObjectUtils.equals(payforAcctId, rhs.payforAcctId)) return false;
		if(!ObjectUtils.equals(specAcctType, rhs.specAcctType)) return false;
		if(!ObjectUtils.equals(segId, rhs.segId)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(policyId, rhs.policyId)) return false;
		if(!ObjectUtils.equals(subPayforRule, rhs.subPayforRule)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payforServId)
		.append(payforAcctId)
		.append(specAcctType)
		.append(segId)
		.append(priority)
		.append(policyId)
		.append(subPayforRule)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGroupAcctPayment";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "PAYFOR_SERV_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "PAYFOR_ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "SPEC_ACCT_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "SEG_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "PRIORITY", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "POLICY_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupAcctPayment.class, "SUB_PAYFOR_RULE", 6, Map.class));
}

}