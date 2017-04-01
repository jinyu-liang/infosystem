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
@XmlType(propOrder={"id","offerid","productid","validdate","createdate","sonbr","sts"})
@Sdl(module="MImsTsDef")
public class SDelayValidProdSyncScpaip extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_OFFERID = "OFFERID";
	public final static String COL_PRODUCTID = "PRODUCTID";
	public final static String COL_VALIDDATE = "VALIDDATE";
	public final static String COL_CREATEDATE = "CREATEDATE";
	public final static String COL_SONBR = "SONBR";
	public final static String COL_STS = "STS";
	public final static int IDX_ID = 0;
	public final static int IDX_OFFERID = 1;
	public final static int IDX_PRODUCTID = 2;
	public final static int IDX_VALIDDATE = 3;
	public final static int IDX_CREATEDATE = 4;
	public final static int IDX_SONBR = 5;
	public final static int IDX_STS = 6;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

	/**
	 * 
	 */
	@XmlElement(name="offerid")
	@Sdl
	private long offerid;

	/**
	 * 
	 */
	@XmlElement(name="productid")
	@Sdl
	private long productid;

	/**
	 * 
	 */
	@XmlElement(name="validdate")
	@Sdl
	private Date validdate;

	/**
	 * 
	 */
	@XmlElement(name="createdate")
	@Sdl
	private Date createdate;

	/**
	 * 
	 */
	@XmlElement(name="sonbr")
	@Sdl
	private long sonbr;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	public void setId(long obj){
		this.id = obj;
		onFieldSet(0, obj);
	}

	public long getId(){
		return id;
	}

	public void setOfferid(long obj){
		this.offerid = obj;
		onFieldSet(1, obj);
	}

	public long getOfferid(){
		return offerid;
	}

	public void setProductid(long obj){
		this.productid = obj;
		onFieldSet(2, obj);
	}

	public long getProductid(){
		return productid;
	}

	public void setValiddate(Date obj){
		this.validdate = obj;
		onFieldSet(3, obj);
	}

	public Date getValiddate(){
		return validdate;
	}

	public void setCreatedate(Date obj){
		this.createdate = obj;
		onFieldSet(4, obj);
	}

	public Date getCreatedate(){
		return createdate;
	}

	public void setSonbr(long obj){
		this.sonbr = obj;
		onFieldSet(5, obj);
	}

	public long getSonbr(){
		return sonbr;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(6, obj);
	}

	public int getSts(){
		return sts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SDelayValidProdSyncScpaip(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SDelayValidProdSyncScpaip(SDelayValidProdSyncScpaip arg0){
		copy(arg0);
	}

	public void copy(final SDelayValidProdSyncScpaip rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		id = rhs.id;
		offerid = rhs.offerid;
		productid = rhs.productid;
		validdate = rhs.validdate;
		createdate = rhs.createdate;
		sonbr = rhs.sonbr;
		sts = rhs.sts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDelayValidProdSyncScpaip rhs=(SDelayValidProdSyncScpaip)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(offerid, rhs.offerid)) return false;
		if(!ObjectUtils.equals(productid, rhs.productid)) return false;
		if(!ObjectUtils.equals(validdate, rhs.validdate)) return false;
		if(!ObjectUtils.equals(createdate, rhs.createdate)) return false;
		if(!ObjectUtils.equals(sonbr, rhs.sonbr)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(offerid)
		.append(productid)
		.append(validdate)
		.append(createdate)
		.append(sonbr)
		.append(sts)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SDelayValidProdSyncScpaip";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "OFFERID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "PRODUCTID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "VALIDDATE", 3, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "CREATEDATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "SONBR", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDelayValidProdSyncScpaip.class, "STS", 6, int.class));
}

}