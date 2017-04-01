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
@XmlType(propOrder={"aoc","productId","zoneCode","zoneAlertFlag"})
@Sdl(module="MXdr")
public class SAocZone extends CsdlStructObject implements IComplexEntity{

	public final static String COL_AOC = "AOC";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_ZONE_CODE = "ZONE_CODE";
	public final static String COL_ZONE_ALERT_FLAG = "ZONE_ALERT_FLAG";
	public final static int IDX_AOC = 0;
	public final static int IDX_PRODUCT_ID = 1;
	public final static int IDX_ZONE_CODE = 2;
	public final static int IDX_ZONE_ALERT_FLAG = 3;

	/**
	 * 
	 */
	@XmlElement(name="aoc")
	@Sdl
	private SAocCode aoc;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="zoneCode")
	@Sdl
	private int zoneCode;

	/**
	 * 
	 */
	@XmlElement(name="zoneAlertFlag")
	@Sdl
	private int zoneAlertFlag;

	public void setAoc(SAocCode obj){
		this.aoc = obj;
		onFieldSet(0, obj);
	}

	public SAocCode getAoc(){
		return aoc;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(1, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setZoneCode(int obj){
		this.zoneCode = obj;
		onFieldSet(2, obj);
	}

	public int getZoneCode(){
		return zoneCode;
	}

	public void setZoneAlertFlag(int obj){
		this.zoneAlertFlag = obj;
		onFieldSet(3, obj);
	}

	public int getZoneAlertFlag(){
		return zoneAlertFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAocZone(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAocZone(SAocZone arg0){
		copy(arg0);
	}

	public void copy(final SAocZone rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		aoc = rhs.aoc;
		productId = rhs.productId;
		zoneCode = rhs.zoneCode;
		zoneAlertFlag = rhs.zoneAlertFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAocZone rhs=(SAocZone)rhs0;
		if(!ObjectUtils.equals(aoc, rhs.aoc)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(zoneCode, rhs.zoneCode)) return false;
		if(!ObjectUtils.equals(zoneAlertFlag, rhs.zoneAlertFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(aoc)
		.append(productId)
		.append(zoneCode)
		.append(zoneAlertFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAocZone";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAocZone.class, "AOC", 0, SAocCode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocZone.class, "PRODUCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocZone.class, "ZONE_CODE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocZone.class, "ZONE_ALERT_FLAG", 3, int.class));
}

}