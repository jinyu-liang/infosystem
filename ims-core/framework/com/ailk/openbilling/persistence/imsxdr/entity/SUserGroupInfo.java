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
@XmlType(propOrder={"groupId","payAcctId","servId"})
@Sdl(module="MXdr")
public class SUserGroupInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_GROUP_ID = "GROUP_ID";
	public final static String COL_PAY_ACCT_ID = "PAY_ACCT_ID";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static int IDX_GROUP_ID = 0;
	public final static int IDX_PAY_ACCT_ID = 1;
	public final static int IDX_SERV_ID = 2;

	/**
	 * 
	 */
	@XmlElement(name="groupId")
	@Sdl
	private long groupId;

	/**
	 * 
	 */
	@XmlElement(name="payAcctId")
	@Sdl
	private long payAcctId;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	public void setGroupId(long obj){
		this.groupId = obj;
		onFieldSet(0, obj);
	}

	public long getGroupId(){
		return groupId;
	}

	public void setPayAcctId(long obj){
		this.payAcctId = obj;
		onFieldSet(1, obj);
	}

	public long getPayAcctId(){
		return payAcctId;
	}

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(2, obj);
	}

	public long getServId(){
		return servId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserGroupInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserGroupInfo(SUserGroupInfo arg0){
		copy(arg0);
	}

	public void copy(final SUserGroupInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		groupId = rhs.groupId;
		payAcctId = rhs.payAcctId;
		servId = rhs.servId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserGroupInfo rhs=(SUserGroupInfo)rhs0;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(payAcctId, rhs.payAcctId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groupId)
		.append(payAcctId)
		.append(servId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SUserGroupInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserGroupInfo.class, "GROUP_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserGroupInfo.class, "PAY_ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserGroupInfo.class, "SERV_ID", 2, long.class));
}

}