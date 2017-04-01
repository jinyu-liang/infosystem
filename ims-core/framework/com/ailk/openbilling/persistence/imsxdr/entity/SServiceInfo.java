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
@XmlType(propOrder={"age","usedFlag"})
@Sdl(module="MXdr")
public class SServiceInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_AGE = "AGE";
	public final static String COL_USED_FLAG = "USED_FLAG";
	public final static int IDX_AGE = 0;
	public final static int IDX_USED_FLAG = 1;

	/**
	 * 
	 */
	@XmlElement(name="age")
	@Sdl
	private int age;

	/**
	 * 
	 */
	@XmlElement(name="usedFlag")
	@Sdl
	private int usedFlag;

	public void setAge(int obj){
		this.age = obj;
		onFieldSet(0, obj);
	}

	public int getAge(){
		return age;
	}

	public void setUsedFlag(int obj){
		this.usedFlag = obj;
		onFieldSet(1, obj);
	}

	public int getUsedFlag(){
		return usedFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SServiceInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SServiceInfo(SServiceInfo arg0){
		copy(arg0);
	}

	public void copy(final SServiceInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		age = rhs.age;
		usedFlag = rhs.usedFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SServiceInfo rhs=(SServiceInfo)rhs0;
		if(!ObjectUtils.equals(age, rhs.age)) return false;
		if(!ObjectUtils.equals(usedFlag, rhs.usedFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(age)
		.append(usedFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SServiceInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SServiceInfo.class, "AGE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SServiceInfo.class, "USED_FLAG", 1, int.class));
}

}