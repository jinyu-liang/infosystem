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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"doneCode","pricePlanId","offerSts","promParamMap"})
@Sdl(module="MXdr")
public class SRateProdPricePlan extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_PRICE_PLAN_ID = "PRICE_PLAN_ID";
	public final static String COL_OFFER_STS = "OFFER_STS";
	public final static String COL_PROM_PARAM_MAP = "PROM_PARAM_MAP";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_PRICE_PLAN_ID = 1;
	public final static int IDX_OFFER_STS = 2;
	public final static int IDX_PROM_PARAM_MAP = 3;

	/**
	 * 
	 */
	@XmlElement(name="promParamMap")
	@Sdl
	private Map<Integer,SPromParam> promParamMap;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="pricePlanId")
	@Sdl
	private int pricePlanId;

	/**
	 * 
	 */
	@XmlElement(name="offerSts")
	@Sdl
	private int offerSts;

	public void setPromParamMap(Map<Integer,SPromParam> obj){
		this.promParamMap = obj;
		onFieldSet(3, obj);
	}

	public Map<Integer,SPromParam> getPromParamMap(){
		return promParamMap;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(0, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setPricePlanId(int obj){
		this.pricePlanId = obj;
		onFieldSet(1, obj);
	}

	public int getPricePlanId(){
		return pricePlanId;
	}

	public void setOfferSts(int obj){
		this.offerSts = obj;
		onFieldSet(2, obj);
	}

	public int getOfferSts(){
		return offerSts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRateProdPricePlan(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRateProdPricePlan(SRateProdPricePlan arg0){
		copy(arg0);
	}

	public void copy(final SRateProdPricePlan rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		doneCode = rhs.doneCode;
		pricePlanId = rhs.pricePlanId;
		offerSts = rhs.offerSts;
		promParamMap = rhs.promParamMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRateProdPricePlan rhs=(SRateProdPricePlan)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(pricePlanId, rhs.pricePlanId)) return false;
		if(!ObjectUtils.equals(offerSts, rhs.offerSts)) return false;
		if(!ObjectUtils.equals(promParamMap, rhs.promParamMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(pricePlanId)
		.append(offerSts)
		.append(promParamMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRateProdPricePlan";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdPricePlan.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdPricePlan.class, "PRICE_PLAN_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdPricePlan.class, "OFFER_STS", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdPricePlan.class, "PROM_PARAM_MAP", 3, Map.class));
}

}