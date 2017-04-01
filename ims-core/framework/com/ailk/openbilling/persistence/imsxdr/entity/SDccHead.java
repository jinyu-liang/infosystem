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
@XmlType(propOrder={"version","commandFlag","commandCode","applicationId","hopId","endId"})
@Sdl(module="MXdr")
public class SDccHead extends CsdlStructObject implements IComplexEntity{

	public final static String COL_VERSION = "VERSION";
	public final static String COL_COMMAND_FLAG = "COMMAND_FLAG";
	public final static String COL_COMMAND_CODE = "COMMAND_CODE";
	public final static String COL_APPLICATION_ID = "APPLICATION_ID";
	public final static String COL_HOP_ID = "HOP_ID";
	public final static String COL_END_ID = "END_ID";
	public final static int IDX_VERSION = 0;
	public final static int IDX_COMMAND_FLAG = 1;
	public final static int IDX_COMMAND_CODE = 2;
	public final static int IDX_APPLICATION_ID = 3;
	public final static int IDX_HOP_ID = 4;
	public final static int IDX_END_ID = 5;

	/**
	 * 
	 */
	@XmlElement(name="version")
	@Sdl
	private int version;

	/**
	 * 
	 */
	@XmlElement(name="commandFlag")
	@Sdl
	private int commandFlag;

	/**
	 * 
	 */
	@XmlElement(name="commandCode")
	@Sdl
	private int commandCode;

	/**
	 * 
	 */
	@XmlElement(name="applicationId")
	@Sdl
	private int applicationId;

	/**
	 * 
	 */
	@XmlElement(name="hopId")
	@Sdl
	private int hopId;

	/**
	 * 
	 */
	@XmlElement(name="endId")
	@Sdl
	private int endId;

	public void setVersion(int obj){
		this.version = obj;
		onFieldSet(0, obj);
	}

	public int getVersion(){
		return version;
	}

	public void setCommandFlag(int obj){
		this.commandFlag = obj;
		onFieldSet(1, obj);
	}

	public int getCommandFlag(){
		return commandFlag;
	}

	public void setCommandCode(int obj){
		this.commandCode = obj;
		onFieldSet(2, obj);
	}

	public int getCommandCode(){
		return commandCode;
	}

	public void setApplicationId(int obj){
		this.applicationId = obj;
		onFieldSet(3, obj);
	}

	public int getApplicationId(){
		return applicationId;
	}

	public void setHopId(int obj){
		this.hopId = obj;
		onFieldSet(4, obj);
	}

	public int getHopId(){
		return hopId;
	}

	public void setEndId(int obj){
		this.endId = obj;
		onFieldSet(5, obj);
	}

	public int getEndId(){
		return endId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SDccHead(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SDccHead(SDccHead arg0){
		copy(arg0);
	}

	public void copy(final SDccHead rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		version = rhs.version;
		commandFlag = rhs.commandFlag;
		commandCode = rhs.commandCode;
		applicationId = rhs.applicationId;
		hopId = rhs.hopId;
		endId = rhs.endId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDccHead rhs=(SDccHead)rhs0;
		if(!ObjectUtils.equals(version, rhs.version)) return false;
		if(!ObjectUtils.equals(commandFlag, rhs.commandFlag)) return false;
		if(!ObjectUtils.equals(commandCode, rhs.commandCode)) return false;
		if(!ObjectUtils.equals(applicationId, rhs.applicationId)) return false;
		if(!ObjectUtils.equals(hopId, rhs.hopId)) return false;
		if(!ObjectUtils.equals(endId, rhs.endId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(version)
		.append(commandFlag)
		.append(commandCode)
		.append(applicationId)
		.append(hopId)
		.append(endId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SDccHead";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "VERSION", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "COMMAND_FLAG", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "COMMAND_CODE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "APPLICATION_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "HOP_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDccHead.class, "END_ID", 5, int.class));
}

}