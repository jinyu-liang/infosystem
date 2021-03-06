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
@XmlType(propOrder={"userId","phoneId"})
@Sdl(module="MImsSyncDef")
public class SUserInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static int IDX_USER_ID = 0;
	public final static int IDX_PHONE_ID = 1;

	/**
	 * 
	 */
	@XmlElement(name="userId")
	@Sdl
	private long userId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(0, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(1, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserInfo(SUserInfo arg0){
		copy(arg0);
	}

	public void copy(final SUserInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		userId = rhs.userId;
		phoneId = rhs.phoneId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserInfo rhs=(SUserInfo)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userId)
		.append(phoneId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SUserInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "USER_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserInfo.class, "PHONE_ID", 1, String.class));
}

}