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
@XmlType(propOrder={"offerProdId","prodSpecType","prodType","suitableNet","groupAccId","oppGroupId","validDateTime","expireDateTime","timezoneOffset","groupPromSpecMap","billType","vt"})
@Sdl(module="MXdr")
public class SRateProdId extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OFFER_PROD_ID = "OFFER_PROD_ID";
	public final static String COL_PROD_SPEC_TYPE = "PROD_SPEC_TYPE";
	public final static String COL_PROD_TYPE = "PROD_TYPE";
	public final static String COL_SUITABLE_NET = "SUITABLE_NET";
	public final static String COL_GROUP_ACC_ID = "GROUP_ACC_ID";
	public final static String COL_OPP_GROUP_ID = "OPP_GROUP_ID";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_TIMEZONE_OFFSET = "TIMEZONE_OFFSET";
	public final static String COL_GROUP_PROM_SPEC_MAP = "GROUP_PROM_SPEC_MAP";
	public final static String COL_BILL_TYPE = "BILL_TYPE";
	public final static String COL_VT = "VT";
	public final static int IDX_OFFER_PROD_ID = 0;
	public final static int IDX_PROD_SPEC_TYPE = 1;
	public final static int IDX_PROD_TYPE = 2;
	public final static int IDX_SUITABLE_NET = 3;
	public final static int IDX_GROUP_ACC_ID = 4;
	public final static int IDX_OPP_GROUP_ID = 5;
	public final static int IDX_VALID_DATE_TIME = 6;
	public final static int IDX_EXPIRE_DATE_TIME = 7;
	public final static int IDX_TIMEZONE_OFFSET = 8;
	public final static int IDX_GROUP_PROM_SPEC_MAP = 9;
	public final static int IDX_BILL_TYPE = 10;
	public final static int IDX_VT = 11;

	/**
	 * 
	 */
	@XmlElement(name="groupPromSpecMap")
	@Sdl
	private Map<Long,Map<Integer,SPromParam>> groupPromSpecMap;

	/**
	 * 
	 */
	@XmlElement(name="offerProdId")
	@Sdl
	private int offerProdId;

	/**
	 * 
	 */
	@XmlElement(name="prodSpecType")
	@Sdl
	private int prodSpecType;

	/**
	 * 
	 */
	@XmlElement(name="prodType")
	@Sdl
	private int prodType;

	/**
	 * 
	 */
	@XmlElement(name="suitableNet")
	@Sdl
	private int suitableNet;

	/**
	 * 
	 */
	@XmlElement(name="groupAccId")
	@Sdl
	private long groupAccId;

	/**
	 * 
	 */
	@XmlElement(name="oppGroupId")
	@Sdl
	private int oppGroupId;

	/**
	 * 
	 */
	@XmlElement(name="validDateTime")
	@Sdl
	private long validDateTime;

	/**
	 * 
	 */
	@XmlElement(name="expireDateTime")
	@Sdl
	private long expireDateTime;

	/**
	 * 
	 */
	@XmlElement(name="timezoneOffset")
	@Sdl
	private int timezoneOffset;

	/**
	 * 
	 */
	@XmlElement(name="billType")
	@Sdl
	private int billType;

	/**
	 * 
	 */
	@XmlElement(name="vt")
	@Sdl
	private long vt;

	public void setGroupPromSpecMap(Map<Long,Map<Integer,SPromParam>> obj){
		this.groupPromSpecMap = obj;
		onFieldSet(9, obj);
	}

	public Map<Long,Map<Integer,SPromParam>> getGroupPromSpecMap(){
		return groupPromSpecMap;
	}

	public void setOfferProdId(int obj){
		this.offerProdId = obj;
		onFieldSet(0, obj);
	}

	public int getOfferProdId(){
		return offerProdId;
	}

	public void setProdSpecType(int obj){
		this.prodSpecType = obj;
		onFieldSet(1, obj);
	}

	public int getProdSpecType(){
		return prodSpecType;
	}

	public void setProdType(int obj){
		this.prodType = obj;
		onFieldSet(2, obj);
	}

	public int getProdType(){
		return prodType;
	}

	public void setSuitableNet(int obj){
		this.suitableNet = obj;
		onFieldSet(3, obj);
	}

	public int getSuitableNet(){
		return suitableNet;
	}

	public void setGroupAccId(long obj){
		this.groupAccId = obj;
		onFieldSet(4, obj);
	}

	public long getGroupAccId(){
		return groupAccId;
	}

	public void setOppGroupId(int obj){
		this.oppGroupId = obj;
		onFieldSet(5, obj);
	}

	public int getOppGroupId(){
		return oppGroupId;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(6, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(7, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setTimezoneOffset(int obj){
		this.timezoneOffset = obj;
		onFieldSet(8, obj);
	}

	public int getTimezoneOffset(){
		return timezoneOffset;
	}

	public void setBillType(int obj){
		this.billType = obj;
		onFieldSet(10, obj);
	}

	public int getBillType(){
		return billType;
	}

	public void setVt(long obj){
		this.vt = obj;
		onFieldSet(11, obj);
	}

	public long getVt(){
		return vt;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRateProdId(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 12; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRateProdId(SRateProdId arg0){
		copy(arg0);
	}

	public void copy(final SRateProdId rhs){
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
		prodSpecType = rhs.prodSpecType;
		prodType = rhs.prodType;
		suitableNet = rhs.suitableNet;
		groupAccId = rhs.groupAccId;
		oppGroupId = rhs.oppGroupId;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		timezoneOffset = rhs.timezoneOffset;
		groupPromSpecMap = rhs.groupPromSpecMap;
		billType = rhs.billType;
		vt = rhs.vt;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRateProdId rhs=(SRateProdId)rhs0;
		if(!ObjectUtils.equals(offerProdId, rhs.offerProdId)) return false;
		if(!ObjectUtils.equals(prodSpecType, rhs.prodSpecType)) return false;
		if(!ObjectUtils.equals(prodType, rhs.prodType)) return false;
		if(!ObjectUtils.equals(suitableNet, rhs.suitableNet)) return false;
		if(!ObjectUtils.equals(groupAccId, rhs.groupAccId)) return false;
		if(!ObjectUtils.equals(oppGroupId, rhs.oppGroupId)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(timezoneOffset, rhs.timezoneOffset)) return false;
		if(!ObjectUtils.equals(groupPromSpecMap, rhs.groupPromSpecMap)) return false;
		if(!ObjectUtils.equals(billType, rhs.billType)) return false;
		if(!ObjectUtils.equals(vt, rhs.vt)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offerProdId)
		.append(prodSpecType)
		.append(prodType)
		.append(suitableNet)
		.append(groupAccId)
		.append(oppGroupId)
		.append(validDateTime)
		.append(expireDateTime)
		.append(timezoneOffset)
		.append(groupPromSpecMap)
		.append(billType)
		.append(vt)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(12);
public static final long BITS_ALL_MARKER = 0x800L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRateProdId";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "OFFER_PROD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "PROD_SPEC_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "PROD_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "SUITABLE_NET", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "GROUP_ACC_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "OPP_GROUP_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "VALID_DATE_TIME", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "EXPIRE_DATE_TIME", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "TIMEZONE_OFFSET", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "GROUP_PROM_SPEC_MAP", 9, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "BILL_TYPE", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdId.class, "VT", 11, long.class));
}

}