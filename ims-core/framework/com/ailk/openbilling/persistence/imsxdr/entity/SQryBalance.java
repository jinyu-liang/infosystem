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
@XmlType(propOrder={"phoneId","calFeeFlag","confirmFlag","extraMap"})
@Sdl(module="MXdr")
public class SQryBalance extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_CAL_FEE_FLAG = "CAL_FEE_FLAG";
	public final static String COL_CONFIRM_FLAG = "CONFIRM_FLAG";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_PHONE_ID = 0;
	public final static int IDX_CAL_FEE_FLAG = 1;
	public final static int IDX_CONFIRM_FLAG = 2;
	public final static int IDX_EXTRA_MAP = 3;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="calFeeFlag")
	@Sdl
	private int calFeeFlag;

	/**
	 * 
	 */
	@XmlElement(name="confirmFlag")
	@Sdl
	private int confirmFlag;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(3, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(0, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setCalFeeFlag(int obj){
		this.calFeeFlag = obj;
		onFieldSet(1, obj);
	}

	public int getCalFeeFlag(){
		return calFeeFlag;
	}

	public void setConfirmFlag(int obj){
		this.confirmFlag = obj;
		onFieldSet(2, obj);
	}

	public int getConfirmFlag(){
		return confirmFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SQryBalance(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SQryBalance(SQryBalance arg0){
		copy(arg0);
	}

	public void copy(final SQryBalance rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		phoneId = rhs.phoneId;
		calFeeFlag = rhs.calFeeFlag;
		confirmFlag = rhs.confirmFlag;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQryBalance rhs=(SQryBalance)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(calFeeFlag, rhs.calFeeFlag)) return false;
		if(!ObjectUtils.equals(confirmFlag, rhs.confirmFlag)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.append(calFeeFlag)
		.append(confirmFlag)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SQryBalance";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SQryBalance.class, "PHONE_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQryBalance.class, "CAL_FEE_FLAG", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQryBalance.class, "CONFIRM_FLAG", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQryBalance.class, "EXTRA_MAP", 3, Map.class));
}

}