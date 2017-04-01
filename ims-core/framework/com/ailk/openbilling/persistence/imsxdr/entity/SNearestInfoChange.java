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
@XmlType(propOrder={"offerProdId","doneCode","changeType","changeDateTime"})
@Sdl(module="MXdr")
public class SNearestInfoChange extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OFFER_PROD_ID = "OFFER_PROD_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_CHANGE_TYPE = "CHANGE_TYPE";
	public final static String COL_CHANGE_DATE_TIME = "CHANGE_DATE_TIME";
	public final static int IDX_OFFER_PROD_ID = 0;
	public final static int IDX_DONE_CODE = 1;
	public final static int IDX_CHANGE_TYPE = 2;
	public final static int IDX_CHANGE_DATE_TIME = 3;

	/**
	 * 
	 */
	@XmlElement(name="offerProdId")
	@Sdl
	private int offerProdId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="changeType")
	@Sdl
	private int changeType;

	/**
	 * 
	 */
	@XmlElement(name="changeDateTime")
	@Sdl
	private long changeDateTime;

	public void setOfferProdId(int obj){
		this.offerProdId = obj;
		onFieldSet(0, obj);
	}

	public int getOfferProdId(){
		return offerProdId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(1, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setChangeType(int obj){
		this.changeType = obj;
		onFieldSet(2, obj);
	}

	public int getChangeType(){
		return changeType;
	}

	public void setChangeDateTime(long obj){
		this.changeDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getChangeDateTime(){
		return changeDateTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SNearestInfoChange(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SNearestInfoChange(SNearestInfoChange arg0){
		copy(arg0);
	}

	public void copy(final SNearestInfoChange rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		offerProdId = rhs.offerProdId;
		doneCode = rhs.doneCode;
		changeType = rhs.changeType;
		changeDateTime = rhs.changeDateTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNearestInfoChange rhs=(SNearestInfoChange)rhs0;
		if(!ObjectUtils.equals(offerProdId, rhs.offerProdId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(changeType, rhs.changeType)) return false;
		if(!ObjectUtils.equals(changeDateTime, rhs.changeDateTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offerProdId)
		.append(doneCode)
		.append(changeType)
		.append(changeDateTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SNearestInfoChange";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SNearestInfoChange.class, "OFFER_PROD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNearestInfoChange.class, "DONE_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNearestInfoChange.class, "CHANGE_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNearestInfoChange.class, "CHANGE_DATE_TIME", 3, long.class));
}

}