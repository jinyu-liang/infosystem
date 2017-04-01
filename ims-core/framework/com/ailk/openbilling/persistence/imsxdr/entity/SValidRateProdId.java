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
@XmlType(propOrder={"changeItemId","doneCode","priceId","rateId"})
@Sdl(module="MXdr")
public class SValidRateProdId extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHANGE_ITEM_ID = "CHANGE_ITEM_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_RATE_ID = "RATE_ID";
	public final static int IDX_CHANGE_ITEM_ID = 0;
	public final static int IDX_DONE_CODE = 1;
	public final static int IDX_PRICE_ID = 2;
	public final static int IDX_RATE_ID = 3;

	/**
	 * 
	 */
	@XmlElement(name="changeItemId")
	@Sdl
	private int changeItemId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="rateId")
	@Sdl
	private int rateId;

	public void setChangeItemId(int obj){
		this.changeItemId = obj;
		onFieldSet(0, obj);
	}

	public int getChangeItemId(){
		return changeItemId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(1, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(2, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setRateId(int obj){
		this.rateId = obj;
		onFieldSet(3, obj);
	}

	public int getRateId(){
		return rateId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SValidRateProdId(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SValidRateProdId(SValidRateProdId arg0){
		copy(arg0);
	}

	public void copy(final SValidRateProdId rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		changeItemId = rhs.changeItemId;
		doneCode = rhs.doneCode;
		priceId = rhs.priceId;
		rateId = rhs.rateId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SValidRateProdId rhs=(SValidRateProdId)rhs0;
		if(!ObjectUtils.equals(changeItemId, rhs.changeItemId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(rateId, rhs.rateId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(changeItemId)
		.append(doneCode)
		.append(priceId)
		.append(rateId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SValidRateProdId";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SValidRateProdId.class, "CHANGE_ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SValidRateProdId.class, "DONE_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SValidRateProdId.class, "PRICE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SValidRateProdId.class, "RATE_ID", 3, int.class));
}

}