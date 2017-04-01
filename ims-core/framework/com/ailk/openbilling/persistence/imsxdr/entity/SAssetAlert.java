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
@XmlType(propOrder={"acctId","itemCode","alarmRuleDtlId"})
@Sdl(module="MXdr")
public class SAssetAlert extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_ALARM_RULE_DTL_ID = "ALARM_RULE_DTL_ID";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_ITEM_CODE = 1;
	public final static int IDX_ALARM_RULE_DTL_ID = 2;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="alarmRuleDtlId")
	@Sdl
	private int alarmRuleDtlId;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(1, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setAlarmRuleDtlId(int obj){
		this.alarmRuleDtlId = obj;
		onFieldSet(2, obj);
	}

	public int getAlarmRuleDtlId(){
		return alarmRuleDtlId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetAlert(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAssetAlert(SAssetAlert arg0){
		copy(arg0);
	}

	public void copy(final SAssetAlert rhs){
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
		itemCode = rhs.itemCode;
		alarmRuleDtlId = rhs.alarmRuleDtlId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAssetAlert rhs=(SAssetAlert)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(alarmRuleDtlId, rhs.alarmRuleDtlId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(itemCode)
		.append(alarmRuleDtlId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAssetAlert";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetAlert.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetAlert.class, "ITEM_CODE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetAlert.class, "ALARM_RULE_DTL_ID", 2, int.class));
}

}