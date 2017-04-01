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
@XmlType(propOrder={"doneCode","hybridRuleType","hybridRuleId","threshold","monitorCdr","segId"})
@Sdl(module="MXdr")
public class SHybirdRuleDefine extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_HYBRID_RULE_TYPE = "HYBRID_RULE_TYPE";
	public final static String COL_HYBRID_RULE_ID = "HYBRID_RULE_ID";
	public final static String COL_THRESHOLD = "THRESHOLD";
	public final static String COL_MONITOR_CDR = "MONITOR_CDR";
	public final static String COL_SEG_ID = "SEG_ID";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_HYBRID_RULE_TYPE = 1;
	public final static int IDX_HYBRID_RULE_ID = 2;
	public final static int IDX_THRESHOLD = 3;
	public final static int IDX_MONITOR_CDR = 4;
	public final static int IDX_SEG_ID = 5;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="hybridRuleType")
	@Sdl
	private int hybridRuleType;

	/**
	 * 
	 */
	@XmlElement(name="hybridRuleId")
	@Sdl
	private int hybridRuleId;

	/**
	 * 
	 */
	@XmlElement(name="threshold")
	@Sdl
	private long threshold;

	/**
	 * 
	 */
	@XmlElement(name="monitorCdr")
	@Sdl
	private int monitorCdr;

	/**
	 * 
	 */
	@XmlElement(name="segId")
	@Sdl
	private int segId;

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(0, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setHybridRuleType(int obj){
		this.hybridRuleType = obj;
		onFieldSet(1, obj);
	}

	public int getHybridRuleType(){
		return hybridRuleType;
	}

	public void setHybridRuleId(int obj){
		this.hybridRuleId = obj;
		onFieldSet(2, obj);
	}

	public int getHybridRuleId(){
		return hybridRuleId;
	}

	public void setThreshold(long obj){
		this.threshold = obj;
		onFieldSet(3, obj);
	}

	public long getThreshold(){
		return threshold;
	}

	public void setMonitorCdr(int obj){
		this.monitorCdr = obj;
		onFieldSet(4, obj);
	}

	public int getMonitorCdr(){
		return monitorCdr;
	}

	public void setSegId(int obj){
		this.segId = obj;
		onFieldSet(5, obj);
	}

	public int getSegId(){
		return segId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SHybirdRuleDefine(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SHybirdRuleDefine(SHybirdRuleDefine arg0){
		copy(arg0);
	}

	public void copy(final SHybirdRuleDefine rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		doneCode = rhs.doneCode;
		hybridRuleType = rhs.hybridRuleType;
		hybridRuleId = rhs.hybridRuleId;
		threshold = rhs.threshold;
		monitorCdr = rhs.monitorCdr;
		segId = rhs.segId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SHybirdRuleDefine rhs=(SHybirdRuleDefine)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(hybridRuleType, rhs.hybridRuleType)) return false;
		if(!ObjectUtils.equals(hybridRuleId, rhs.hybridRuleId)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(monitorCdr, rhs.monitorCdr)) return false;
		if(!ObjectUtils.equals(segId, rhs.segId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(hybridRuleType)
		.append(hybridRuleId)
		.append(threshold)
		.append(monitorCdr)
		.append(segId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SHybirdRuleDefine";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "HYBRID_RULE_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "HYBRID_RULE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "THRESHOLD", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "MONITOR_CDR", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SHybirdRuleDefine.class, "SEG_ID", 5, int.class));
}

}