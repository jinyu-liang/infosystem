package com.ailk.openbilling.persistence.imsts.entity;

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
@XmlType(propOrder={"resourceId","notifyDate","notifyCount","modifyDate","retryTimes","sts","nsts","psts"})
@Sdl(module="MImsTsDef")
public class SLifeCycleOncePreNotify extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_NOTIFY_DATE = "NOTIFY_DATE";
	public final static String COL_NOTIFY_COUNT = "NOTIFY_COUNT";
	public final static String COL_MODIFY_DATE = "MODIFY_DATE";
	public final static String COL_RETRY_TIMES = "RETRY_TIMES";
	public final static String COL_STS = "STS";
	public final static String COL_NSTS = "NSTS";
	public final static String COL_PSTS = "PSTS";
	public final static int IDX_RESOURCE_ID = 0;
	public final static int IDX_NOTIFY_DATE = 1;
	public final static int IDX_NOTIFY_COUNT = 2;
	public final static int IDX_MODIFY_DATE = 3;
	public final static int IDX_RETRY_TIMES = 4;
	public final static int IDX_STS = 5;
	public final static int IDX_NSTS = 6;
	public final static int IDX_PSTS = 7;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="notifyDate")
	@Sdl
	private Date notifyDate;

	/**
	 * 
	 */
	@XmlElement(name="notifyCount")
	@Sdl
	private int notifyCount;

	/**
	 * 
	 */
	@XmlElement(name="modifyDate")
	@Sdl
	private Date modifyDate;

	/**
	 * 
	 */
	@XmlElement(name="retryTimes")
	@Sdl
	private int retryTimes;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="nsts")
	@Sdl
	private int nsts;

	/**
	 * 
	 */
	@XmlElement(name="psts")
	@Sdl
	private int psts;

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(0, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setNotifyDate(Date obj){
		this.notifyDate = obj;
		onFieldSet(1, obj);
	}

	public Date getNotifyDate(){
		return notifyDate;
	}

	public void setNotifyCount(int obj){
		this.notifyCount = obj;
		onFieldSet(2, obj);
	}

	public int getNotifyCount(){
		return notifyCount;
	}

	public void setModifyDate(Date obj){
		this.modifyDate = obj;
		onFieldSet(3, obj);
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setRetryTimes(int obj){
		this.retryTimes = obj;
		onFieldSet(4, obj);
	}

	public int getRetryTimes(){
		return retryTimes;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(5, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setNsts(int obj){
		this.nsts = obj;
		onFieldSet(6, obj);
	}

	public int getNsts(){
		return nsts;
	}

	public void setPsts(int obj){
		this.psts = obj;
		onFieldSet(7, obj);
	}

	public int getPsts(){
		return psts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SLifeCycleOncePreNotify(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SLifeCycleOncePreNotify(SLifeCycleOncePreNotify arg0){
		copy(arg0);
	}

	public void copy(final SLifeCycleOncePreNotify rhs){
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
		notifyDate = rhs.notifyDate;
		notifyCount = rhs.notifyCount;
		modifyDate = rhs.modifyDate;
		retryTimes = rhs.retryTimes;
		sts = rhs.sts;
		nsts = rhs.nsts;
		psts = rhs.psts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLifeCycleOncePreNotify rhs=(SLifeCycleOncePreNotify)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(notifyDate, rhs.notifyDate)) return false;
		if(!ObjectUtils.equals(notifyCount, rhs.notifyCount)) return false;
		if(!ObjectUtils.equals(modifyDate, rhs.modifyDate)) return false;
		if(!ObjectUtils.equals(retryTimes, rhs.retryTimes)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(nsts, rhs.nsts)) return false;
		if(!ObjectUtils.equals(psts, rhs.psts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(notifyDate)
		.append(notifyCount)
		.append(modifyDate)
		.append(retryTimes)
		.append(sts)
		.append(nsts)
		.append(psts)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SLifeCycleOncePreNotify";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "RESOURCE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "NOTIFY_DATE", 1, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "NOTIFY_COUNT", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "MODIFY_DATE", 3, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "RETRY_TIMES", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "STS", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "NSTS", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLifeCycleOncePreNotify.class, "PSTS", 7, int.class));
}

}