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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourceId","sts","lifecycleExpireDate","lifecycleValidDate","resValidExpireDate","resValidValidDate","resValidIshistory"})
@Sdl(module="MImsSyncDef")
public class SResLifeCycleSync extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_STS = "STS";
	public final static String COL_LIFECYCLE_EXPIRE_DATE = "LIFECYCLE_EXPIRE_DATE";
	public final static String COL_LIFECYCLE_VALID_DATE = "LIFECYCLE_VALID_DATE";
	public final static String COL_RES_VALID_EXPIRE_DATE = "RES_VALID_EXPIRE_DATE";
	public final static String COL_RES_VALID_VALID_DATE = "RES_VALID_VALID_DATE";
	public final static String COL_RES_VALID_ISHISTORY = "RES_VALID_ISHISTORY";
	public final static int IDX_RESOURCE_ID = 0;
	public final static int IDX_STS = 1;
	public final static int IDX_LIFECYCLE_EXPIRE_DATE = 2;
	public final static int IDX_LIFECYCLE_VALID_DATE = 3;
	public final static int IDX_RES_VALID_EXPIRE_DATE = 4;
	public final static int IDX_RES_VALID_VALID_DATE = 5;
	public final static int IDX_RES_VALID_ISHISTORY = 6;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="lifecycleExpireDate")
	@Sdl
	private Date lifecycleExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="lifecycleValidDate")
	@Sdl
	private Date lifecycleValidDate;

	/**
	 * 
	 */
	@XmlElement(name="resValidExpireDate")
	@Sdl
	private Date resValidExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="resValidValidDate")
	@Sdl
	private Date resValidValidDate;

	/**
	 * 
	 */
	@XmlElement(name="resValidIshistory")
	@Sdl
	private int resValidIshistory;

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(0, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(1, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setLifecycleExpireDate(Date obj){
		this.lifecycleExpireDate = obj;
		onFieldSet(2, obj);
	}

	public Date getLifecycleExpireDate(){
		return lifecycleExpireDate;
	}

	public void setLifecycleValidDate(Date obj){
		this.lifecycleValidDate = obj;
		onFieldSet(3, obj);
	}

	public Date getLifecycleValidDate(){
		return lifecycleValidDate;
	}

	public void setResValidExpireDate(Date obj){
		this.resValidExpireDate = obj;
		onFieldSet(4, obj);
	}

	public Date getResValidExpireDate(){
		return resValidExpireDate;
	}

	public void setResValidValidDate(Date obj){
		this.resValidValidDate = obj;
		onFieldSet(5, obj);
	}

	public Date getResValidValidDate(){
		return resValidValidDate;
	}

	public void setResValidIshistory(int obj){
		this.resValidIshistory = obj;
		onFieldSet(6, obj);
	}

	public int getResValidIshistory(){
		return resValidIshistory;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SResLifeCycleSync(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SResLifeCycleSync(SResLifeCycleSync arg0){
		copy(arg0);
	}

	public void copy(final SResLifeCycleSync rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		resourceId = rhs.resourceId;
		sts = rhs.sts;
		lifecycleExpireDate = rhs.lifecycleExpireDate;
		lifecycleValidDate = rhs.lifecycleValidDate;
		resValidExpireDate = rhs.resValidExpireDate;
		resValidValidDate = rhs.resValidValidDate;
		resValidIshistory = rhs.resValidIshistory;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SResLifeCycleSync rhs=(SResLifeCycleSync)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(lifecycleExpireDate, rhs.lifecycleExpireDate)) return false;
		if(!ObjectUtils.equals(lifecycleValidDate, rhs.lifecycleValidDate)) return false;
		if(!ObjectUtils.equals(resValidExpireDate, rhs.resValidExpireDate)) return false;
		if(!ObjectUtils.equals(resValidValidDate, rhs.resValidValidDate)) return false;
		if(!ObjectUtils.equals(resValidIshistory, rhs.resValidIshistory)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(sts)
		.append(lifecycleExpireDate)
		.append(lifecycleValidDate)
		.append(resValidExpireDate)
		.append(resValidValidDate)
		.append(resValidIshistory)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SResLifeCycleSync";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "RESOURCE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "STS", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "LIFECYCLE_EXPIRE_DATE", 2, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "LIFECYCLE_VALID_DATE", 3, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "RES_VALID_EXPIRE_DATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "RES_VALID_VALID_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResLifeCycleSync.class, "RES_VALID_ISHISTORY", 6, int.class));
}

}