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
@XmlType(propOrder={"statisticRuleId","validDateTime","expireDateTime"})
@Sdl(module="MXdr")
public class SAccumulateRule extends CsdlStructObject implements IComplexEntity{

	public final static String COL_STATISTIC_RULE_ID = "STATISTIC_RULE_ID";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static int IDX_STATISTIC_RULE_ID = 0;
	public final static int IDX_VALID_DATE_TIME = 1;
	public final static int IDX_EXPIRE_DATE_TIME = 2;

	/**
	 * 
	 */
	@XmlElement(name="statisticRuleId")
	@Sdl
	private int statisticRuleId;

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

	public void setStatisticRuleId(int obj){
		this.statisticRuleId = obj;
		onFieldSet(0, obj);
	}

	public int getStatisticRuleId(){
		return statisticRuleId;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(1, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(2, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAccumulateRule(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAccumulateRule(SAccumulateRule arg0){
		copy(arg0);
	}

	public void copy(final SAccumulateRule rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		statisticRuleId = rhs.statisticRuleId;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAccumulateRule rhs=(SAccumulateRule)rhs0;
		if(!ObjectUtils.equals(statisticRuleId, rhs.statisticRuleId)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(statisticRuleId)
		.append(validDateTime)
		.append(expireDateTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAccumulateRule";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateRule.class, "STATISTIC_RULE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateRule.class, "VALID_DATE_TIME", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateRule.class, "EXPIRE_DATE_TIME", 2, long.class));
}

}