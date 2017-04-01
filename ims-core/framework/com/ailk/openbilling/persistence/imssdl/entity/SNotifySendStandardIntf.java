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
@XmlType(propOrder={"listsnotifysendintf","count"})
@Sdl(module="MImsSyncDef")
public class SNotifySendStandardIntf extends CsdlStructObject implements IComplexEntity{

	public final static String COL_LISTSNOTIFYSENDINTF = "LISTSNOTIFYSENDINTF";
	public final static String COL_COUNT = "COUNT";
	public final static int IDX_LISTSNOTIFYSENDINTF = 0;
	public final static int IDX_COUNT = 1;

	/**
	 * 
	 */
	@XmlElement(name="listsnotifysendintf")
	@Sdl
	private List<SNotifySendIntf> listsnotifysendintf;

	/**
	 * 
	 */
	@XmlElement(name="count")
	@Sdl
	private int count;

	public void setListsnotifysendintf(List<SNotifySendIntf> obj){
		this.listsnotifysendintf = obj;
		onFieldSet(0, obj);
	}

	public List<SNotifySendIntf> getListsnotifysendintf(){
		return listsnotifysendintf;
	}

	public void setCount(int obj){
		this.count = obj;
		onFieldSet(1, obj);
	}

	public int getCount(){
		return count;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SNotifySendStandardIntf(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SNotifySendStandardIntf(SNotifySendStandardIntf arg0){
		copy(arg0);
	}

	public void copy(final SNotifySendStandardIntf rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		listsnotifysendintf = rhs.listsnotifysendintf;
		count = rhs.count;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNotifySendStandardIntf rhs=(SNotifySendStandardIntf)rhs0;
		if(!ObjectUtils.equals(listsnotifysendintf, rhs.listsnotifysendintf)) return false;
		if(!ObjectUtils.equals(count, rhs.count)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(listsnotifysendintf)
		.append(count)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SNotifySendStandardIntf";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendStandardIntf.class, "LISTSNOTIFYSENDINTF", 0, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendStandardIntf.class, "COUNT", 1, int.class));
}

}