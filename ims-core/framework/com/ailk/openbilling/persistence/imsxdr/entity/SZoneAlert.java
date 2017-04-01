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
@XmlType(propOrder={"productId","zoneAlert"})
@Sdl(module="MXdr")
public class SZoneAlert extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_ZONE_ALERT = "ZONE_ALERT";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_ZONE_ALERT = 1;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="zoneAlert")
	@Sdl
	private int zoneAlert;

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setZoneAlert(int obj){
		this.zoneAlert = obj;
		onFieldSet(1, obj);
	}

	public int getZoneAlert(){
		return zoneAlert;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SZoneAlert(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SZoneAlert(SZoneAlert arg0){
		copy(arg0);
	}

	public void copy(final SZoneAlert rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		productId = rhs.productId;
		zoneAlert = rhs.zoneAlert;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SZoneAlert rhs=(SZoneAlert)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(zoneAlert, rhs.zoneAlert)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(zoneAlert)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SZoneAlert";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SZoneAlert.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SZoneAlert.class, "ZONE_ALERT", 1, int.class));
}

}