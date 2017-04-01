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
@XmlType(propOrder={"serviceCode","ruleName","ruleBaseName","ruleFlag"})
@Sdl(module="MXdr")
public class SRgRule extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SERVICE_CODE = "SERVICE_CODE";
	public final static String COL_RULE_NAME = "RULE_NAME";
	public final static String COL_RULE_BASE_NAME = "RULE_BASE_NAME";
	public final static String COL_RULE_FLAG = "RULE_FLAG";
	public final static int IDX_SERVICE_CODE = 0;
	public final static int IDX_RULE_NAME = 1;
	public final static int IDX_RULE_BASE_NAME = 2;
	public final static int IDX_RULE_FLAG = 3;

	/**
	 * 
	 */
	@XmlElement(name="serviceCode")
	@Sdl
	private String serviceCode;

	/**
	 * 
	 */
	@XmlElement(name="ruleName")
	@Sdl
	private String ruleName;

	/**
	 * 
	 */
	@XmlElement(name="ruleBaseName")
	@Sdl
	private String ruleBaseName;

	/**
	 * 
	 */
	@XmlElement(name="ruleFlag")
	@Sdl
	private int ruleFlag;

	public void setServiceCode(String obj){
		this.serviceCode = obj;
		onFieldSet(0, obj);
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setRuleName(String obj){
		this.ruleName = obj;
		onFieldSet(1, obj);
	}

	public String getRuleName(){
		return ruleName;
	}

	public void setRuleBaseName(String obj){
		this.ruleBaseName = obj;
		onFieldSet(2, obj);
	}

	public String getRuleBaseName(){
		return ruleBaseName;
	}

	public void setRuleFlag(int obj){
		this.ruleFlag = obj;
		onFieldSet(3, obj);
	}

	public int getRuleFlag(){
		return ruleFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRgRule(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRgRule(SRgRule arg0){
		copy(arg0);
	}

	public void copy(final SRgRule rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		serviceCode = rhs.serviceCode;
		ruleName = rhs.ruleName;
		ruleBaseName = rhs.ruleBaseName;
		ruleFlag = rhs.ruleFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRgRule rhs=(SRgRule)rhs0;
		if(!ObjectUtils.equals(serviceCode, rhs.serviceCode)) return false;
		if(!ObjectUtils.equals(ruleName, rhs.ruleName)) return false;
		if(!ObjectUtils.equals(ruleBaseName, rhs.ruleBaseName)) return false;
		if(!ObjectUtils.equals(ruleFlag, rhs.ruleFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(serviceCode)
		.append(ruleName)
		.append(ruleBaseName)
		.append(ruleFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRgRule";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRgRule.class, "SERVICE_CODE", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRgRule.class, "RULE_NAME", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRgRule.class, "RULE_BASE_NAME", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRgRule.class, "RULE_FLAG", 3, int.class));
}

}