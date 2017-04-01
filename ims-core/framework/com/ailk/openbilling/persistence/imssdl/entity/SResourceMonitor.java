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
@XmlType(propOrder={"servId","serviceId","moniCondId","moniType","operType","validDate","expireDate","notes"})
@Sdl(module="MImsSyncDef")
public class SResourceMonitor extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_SERVICE_ID = "SERVICE_ID";
	public final static String COL_MONI_COND_ID = "MONI_COND_ID";
	public final static String COL_MONI_TYPE = "MONI_TYPE";
	public final static String COL_OPER_TYPE = "OPER_TYPE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_NOTES = "NOTES";
	public final static int IDX_SERV_ID = 0;
	public final static int IDX_SERVICE_ID = 1;
	public final static int IDX_MONI_COND_ID = 2;
	public final static int IDX_MONI_TYPE = 3;
	public final static int IDX_OPER_TYPE = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_NOTES = 7;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="serviceId")
	@Sdl
	private int serviceId;

	/**
	 * 
	 */
	@XmlElement(name="moniCondId")
	@Sdl
	private int moniCondId;

	/**
	 * 
	 */
	@XmlElement(name="moniType")
	@Sdl
	private int moniType;

	/**
	 * 
	 */
	@XmlElement(name="operType")
	@Sdl
	private int operType;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	/**
	 * 
	 */
	@XmlElement(name="notes")
	@Sdl
	private String notes;

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(0, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setServiceId(int obj){
		this.serviceId = obj;
		onFieldSet(1, obj);
	}

	public int getServiceId(){
		return serviceId;
	}

	public void setMoniCondId(int obj){
		this.moniCondId = obj;
		onFieldSet(2, obj);
	}

	public int getMoniCondId(){
		return moniCondId;
	}

	public void setMoniType(int obj){
		this.moniType = obj;
		onFieldSet(3, obj);
	}

	public int getMoniType(){
		return moniType;
	}

	public void setOperType(int obj){
		this.operType = obj;
		onFieldSet(4, obj);
	}

	public int getOperType(){
		return operType;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setNotes(String obj){
		this.notes = obj;
		onFieldSet(7, obj);
	}

	public String getNotes(){
		return notes;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SResourceMonitor(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SResourceMonitor(SResourceMonitor arg0){
		copy(arg0);
	}

	public void copy(final SResourceMonitor rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		servId = rhs.servId;
		serviceId = rhs.serviceId;
		moniCondId = rhs.moniCondId;
		moniType = rhs.moniType;
		operType = rhs.operType;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		notes = rhs.notes;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SResourceMonitor rhs=(SResourceMonitor)rhs0;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(serviceId, rhs.serviceId)) return false;
		if(!ObjectUtils.equals(moniCondId, rhs.moniCondId)) return false;
		if(!ObjectUtils.equals(moniType, rhs.moniType)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(notes, rhs.notes)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(servId)
		.append(serviceId)
		.append(moniCondId)
		.append(moniType)
		.append(operType)
		.append(validDate)
		.append(expireDate)
		.append(notes)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SResourceMonitor";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "SERV_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "SERVICE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "MONI_COND_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "MONI_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "OPER_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "VALID_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "EXPIRE_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResourceMonitor.class, "NOTES", 7, String.class));
}

}