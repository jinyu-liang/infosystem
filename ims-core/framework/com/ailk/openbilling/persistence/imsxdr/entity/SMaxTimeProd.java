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
@XmlType(propOrder={"validFlag","segId","rejectThreshold","accumulateTime"})
@Sdl(module="MXdr")
public class SMaxTimeProd extends CsdlStructObject implements IComplexEntity{

	public final static String COL_VALID_FLAG = "VALID_FLAG";
	public final static String COL_SEG_ID = "SEG_ID";
	public final static String COL_REJECT_THRESHOLD = "REJECT_THRESHOLD";
	public final static String COL_ACCUMULATE_TIME = "ACCUMULATE_TIME";
	public final static int IDX_VALID_FLAG = 0;
	public final static int IDX_SEG_ID = 1;
	public final static int IDX_REJECT_THRESHOLD = 2;
	public final static int IDX_ACCUMULATE_TIME = 3;

	/**
	 * 
	 */
	@XmlElement(name="validFlag")
	@Sdl
	private int validFlag;

	/**
	 * 
	 */
	@XmlElement(name="segId")
	@Sdl
	private int segId;

	/**
	 * 
	 */
	@XmlElement(name="rejectThreshold")
	@Sdl
	private int rejectThreshold;

	/**
	 * 
	 */
	@XmlElement(name="accumulateTime")
	@Sdl
	private int accumulateTime;

	public void setValidFlag(int obj){
		this.validFlag = obj;
		onFieldSet(0, obj);
	}

	public int getValidFlag(){
		return validFlag;
	}

	public void setSegId(int obj){
		this.segId = obj;
		onFieldSet(1, obj);
	}

	public int getSegId(){
		return segId;
	}

	public void setRejectThreshold(int obj){
		this.rejectThreshold = obj;
		onFieldSet(2, obj);
	}

	public int getRejectThreshold(){
		return rejectThreshold;
	}

	public void setAccumulateTime(int obj){
		this.accumulateTime = obj;
		onFieldSet(3, obj);
	}

	public int getAccumulateTime(){
		return accumulateTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SMaxTimeProd(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SMaxTimeProd(SMaxTimeProd arg0){
		copy(arg0);
	}

	public void copy(final SMaxTimeProd rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		validFlag = rhs.validFlag;
		segId = rhs.segId;
		rejectThreshold = rhs.rejectThreshold;
		accumulateTime = rhs.accumulateTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMaxTimeProd rhs=(SMaxTimeProd)rhs0;
		if(!ObjectUtils.equals(validFlag, rhs.validFlag)) return false;
		if(!ObjectUtils.equals(segId, rhs.segId)) return false;
		if(!ObjectUtils.equals(rejectThreshold, rhs.rejectThreshold)) return false;
		if(!ObjectUtils.equals(accumulateTime, rhs.accumulateTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(validFlag)
		.append(segId)
		.append(rejectThreshold)
		.append(accumulateTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SMaxTimeProd";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SMaxTimeProd.class, "VALID_FLAG", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMaxTimeProd.class, "SEG_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMaxTimeProd.class, "REJECT_THRESHOLD", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMaxTimeProd.class, "ACCUMULATE_TIME", 3, int.class));
}

}