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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resoureId","phoneId","currentBalanceAmount"})
@Sdl(module="MImsSyncDef")
public class SLifeCycleChgByPayment extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RESOURE_ID = "RESOURE_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_CURRENT_BALANCE_AMOUNT = "CURRENT_BALANCE_AMOUNT";
	public final static int IDX_RESOURE_ID = 0;
	public final static int IDX_PHONE_ID = 1;
	public final static int IDX_CURRENT_BALANCE_AMOUNT = 2;

	/**
	 * 
	 */
	@XmlElement(name="resoureId")
	@Sdl
	private long resoureId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="currentBalanceAmount")
	@Sdl
	private long currentBalanceAmount;

	public void setResoureId(long obj){
		this.resoureId = obj;
		onFieldSet(0, obj);
	}

	public long getResoureId(){
		return resoureId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(1, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setCurrentBalanceAmount(long obj){
		this.currentBalanceAmount = obj;
		onFieldSet(2, obj);
	}

	public long getCurrentBalanceAmount(){
		return currentBalanceAmount;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SLifeCycleChgByPayment(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SLifeCycleChgByPayment(SLifeCycleChgByPayment arg0){
		copy(arg0);
	}

	public void copy(final SLifeCycleChgByPayment rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		resoureId = rhs.resoureId;
		phoneId = rhs.phoneId;
		currentBalanceAmount = rhs.currentBalanceAmount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLifeCycleChgByPayment rhs=(SLifeCycleChgByPayment)rhs0;
		if(!ObjectUtils.equals(resoureId, rhs.resoureId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(currentBalanceAmount, rhs.currentBalanceAmount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resoureId)
		.append(phoneId)
		.append(currentBalanceAmount)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChgByPayment";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChgByPayment.class, "RESOURE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChgByPayment.class, "PHONE_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleChgByPayment.class, "CURRENT_BALANCE_AMOUNT", 2, long.class));
}

}