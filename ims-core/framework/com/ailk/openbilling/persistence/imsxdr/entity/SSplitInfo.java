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
@XmlType(propOrder={"splitType","deleteFlag","splitIndex"})
@Sdl(module="MXdr")
public class SSplitInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SPLIT_TYPE = "SPLIT_TYPE";
	public final static String COL_DELETE_FLAG = "DELETE_FLAG";
	public final static String COL_SPLIT_INDEX = "SPLIT_INDEX";
	public final static int IDX_SPLIT_TYPE = 0;
	public final static int IDX_DELETE_FLAG = 1;
	public final static int IDX_SPLIT_INDEX = 2;

	/**
	 * 
	 */
	@XmlElement(name="splitType")
	@Sdl
	private int splitType;

	/**
	 * 
	 */
	@XmlElement(name="deleteFlag")
	@Sdl
	private short deleteFlag;

	/**
	 * 
	 */
	@XmlElement(name="splitIndex")
	@Sdl
	private short splitIndex;

	public void setSplitType(int obj){
		this.splitType = obj;
		onFieldSet(0, obj);
	}

	public int getSplitType(){
		return splitType;
	}

	public void setDeleteFlag(short obj){
		this.deleteFlag = obj;
		onFieldSet(1, obj);
	}

	public short getDeleteFlag(){
		return deleteFlag;
	}

	public void setSplitIndex(short obj){
		this.splitIndex = obj;
		onFieldSet(2, obj);
	}

	public short getSplitIndex(){
		return splitIndex;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSplitInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSplitInfo(SSplitInfo arg0){
		copy(arg0);
	}

	public void copy(final SSplitInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		splitType = rhs.splitType;
		deleteFlag = rhs.deleteFlag;
		splitIndex = rhs.splitIndex;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSplitInfo rhs=(SSplitInfo)rhs0;
		if(!ObjectUtils.equals(splitType, rhs.splitType)) return false;
		if(!ObjectUtils.equals(deleteFlag, rhs.deleteFlag)) return false;
		if(!ObjectUtils.equals(splitIndex, rhs.splitIndex)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(splitType)
		.append(deleteFlag)
		.append(splitIndex)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSplitInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSplitInfo.class, "SPLIT_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSplitInfo.class, "DELETE_FLAG", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSplitInfo.class, "SPLIT_INDEX", 2, short.class));
}

}