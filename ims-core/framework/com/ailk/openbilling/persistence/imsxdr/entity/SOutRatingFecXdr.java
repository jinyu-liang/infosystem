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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"accPriceAlarmCode","accPriceUrlAddr","assetAlarmCode","assetUrlAddr","sla","gsu","slaAlarm","lastQuotaAlarm","vt","vqt","tqt","qht","qct","inReleaseDeal","zoneAlarmAoc","zoneAlarmUrl","ocsRejectCause","beforeMakecallAoc","beforeMakecallUrl","rgName","urlAccumulateAlarm","aocAccumulateAlarm"})
@Sdl(module="MXdr")
public class SOutRatingFecXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACC_PRICE_ALARM_CODE = "ACC_PRICE_ALARM_CODE";
	public final static String COL_ACC_PRICE_URL_ADDR = "ACC_PRICE_URL_ADDR";
	public final static String COL_ASSET_ALARM_CODE = "ASSET_ALARM_CODE";
	public final static String COL_ASSET_URL_ADDR = "ASSET_URL_ADDR";
	public final static String COL_SLA = "SLA";
	public final static String COL_GSU = "GSU";
	public final static String COL_SLA_ALARM = "SLA_ALARM";
	public final static String COL_LAST_QUOTA_ALARM = "LAST_QUOTA_ALARM";
	public final static String COL_VT = "VT";
	public final static String COL_VQT = "VQT";
	public final static String COL_TQT = "TQT";
	public final static String COL_QHT = "QHT";
	public final static String COL_QCT = "QCT";
	public final static String COL_IN_RELEASE_DEAL = "IN_RELEASE_DEAL";
	public final static String COL_ZONE_ALARM_AOC = "ZONE_ALARM_AOC";
	public final static String COL_ZONE_ALARM_URL = "ZONE_ALARM_URL";
	public final static String COL_OCS_REJECT_CAUSE = "OCS_REJECT_CAUSE";
	public final static String COL_BEFORE_MAKECALL_AOC = "BEFORE_MAKECALL_AOC";
	public final static String COL_BEFORE_MAKECALL_URL = "BEFORE_MAKECALL_URL";
	public final static String COL_RG_NAME = "RG_NAME";
	public final static String COL_URL_ACCUMULATE_ALARM = "URL_ACCUMULATE_ALARM";
	public final static String COL_AOC_ACCUMULATE_ALARM = "AOC_ACCUMULATE_ALARM";
	public final static int IDX_ACC_PRICE_ALARM_CODE = 0;
	public final static int IDX_ACC_PRICE_URL_ADDR = 1;
	public final static int IDX_ASSET_ALARM_CODE = 2;
	public final static int IDX_ASSET_URL_ADDR = 3;
	public final static int IDX_SLA = 4;
	public final static int IDX_GSU = 5;
	public final static int IDX_SLA_ALARM = 6;
	public final static int IDX_LAST_QUOTA_ALARM = 7;
	public final static int IDX_VT = 8;
	public final static int IDX_VQT = 9;
	public final static int IDX_TQT = 10;
	public final static int IDX_QHT = 11;
	public final static int IDX_QCT = 12;
	public final static int IDX_IN_RELEASE_DEAL = 13;
	public final static int IDX_ZONE_ALARM_AOC = 14;
	public final static int IDX_ZONE_ALARM_URL = 15;
	public final static int IDX_OCS_REJECT_CAUSE = 16;
	public final static int IDX_BEFORE_MAKECALL_AOC = 17;
	public final static int IDX_BEFORE_MAKECALL_URL = 18;
	public final static int IDX_RG_NAME = 19;
	public final static int IDX_URL_ACCUMULATE_ALARM = 20;
	public final static int IDX_AOC_ACCUMULATE_ALARM = 21;

	/**
	 * 
	 */
	@XmlElement(name="accPriceAlarmCode")
	@Sdl
	private List<SAccumulatePriceAocCode> accPriceAlarmCode;

	/**
	 * 
	 */
	@XmlElement(name="accPriceUrlAddr")
	@Sdl
	private List<SAccumulatePriceUrlAddr> accPriceUrlAddr;

	/**
	 * 
	 */
	@XmlElement(name="assetAlarmCode")
	@Sdl
	private List<SAocAsset> assetAlarmCode;

	/**
	 * 
	 */
	@XmlElement(name="assetUrlAddr")
	@Sdl
	private List<SUrlAsset> assetUrlAddr;

	/**
	 * 
	 */
	@XmlElement(name="sla")
	@Sdl
	private List<SSlaValue> sla;

	/**
	 * 
	 */
	@XmlElement(name="gsu")
	@Sdl
	private SGsuValue gsu;

	/**
	 * 
	 */
	@XmlElement(name="slaAlarm")
	@Sdl
	private SSlaAlert slaAlarm;

	/**
	 * 
	 */
	@XmlElement(name="lastQuotaAlarm")
	@Sdl
	private SLastQuotaAlert lastQuotaAlarm;

	/**
	 * 
	 */
	@XmlElement(name="zoneAlarmAoc")
	@Sdl
	private List<SAocZone> zoneAlarmAoc;

	/**
	 * 
	 */
	@XmlElement(name="zoneAlarmUrl")
	@Sdl
	private List<SUrlZone> zoneAlarmUrl;

	/**
	 * 
	 */
	@XmlElement(name="ocsRejectCause")
	@Sdl
	private SOcsRejectCause ocsRejectCause;

	/**
	 * 
	 */
	@XmlElement(name="beforeMakecallAoc")
	@Sdl
	private SBeforeMakeCallAoc beforeMakecallAoc;

	/**
	 * 
	 */
	@XmlElement(name="beforeMakecallUrl")
	@Sdl
	private SBeforeMakeCallUrl beforeMakecallUrl;

	/**
	 * 
	 */
	@XmlElement(name="rgName")
	@Sdl
	private Map<String,SRgName> rgName;

	/**
	 * 
	 */
	@XmlElement(name="urlAccumulateAlarm")
	@Sdl
	private List<SAccumulatePriceUrlAddr> urlAccumulateAlarm;

	/**
	 * 
	 */
	@XmlElement(name="aocAccumulateAlarm")
	@Sdl
	private List<SAccumulatePriceAocCode> aocAccumulateAlarm;

	/**
	 * 
	 */
	@XmlElement(name="vt")
	@Sdl
	private long vt;

	/**
	 * 
	 */
	@XmlElement(name="vqt")
	@Sdl
	private long vqt;

	/**
	 * 
	 */
	@XmlElement(name="tqt")
	@Sdl
	private long tqt;

	/**
	 * 
	 */
	@XmlElement(name="qht")
	@Sdl
	private long qht;

	/**
	 * 
	 */
	@XmlElement(name="qct")
	@Sdl
	private long qct;

	/**
	 * 
	 */
	@XmlElement(name="inReleaseDeal")
	@Sdl
	private int inReleaseDeal;

	public void setAccPriceAlarmCode(List<SAccumulatePriceAocCode> obj){
		this.accPriceAlarmCode = obj;
		onFieldSet(0, obj);
	}

	public List<SAccumulatePriceAocCode> getAccPriceAlarmCode(){
		return accPriceAlarmCode;
	}

	public void setAccPriceUrlAddr(List<SAccumulatePriceUrlAddr> obj){
		this.accPriceUrlAddr = obj;
		onFieldSet(1, obj);
	}

	public List<SAccumulatePriceUrlAddr> getAccPriceUrlAddr(){
		return accPriceUrlAddr;
	}

	public void setAssetAlarmCode(List<SAocAsset> obj){
		this.assetAlarmCode = obj;
		onFieldSet(2, obj);
	}

	public List<SAocAsset> getAssetAlarmCode(){
		return assetAlarmCode;
	}

	public void setAssetUrlAddr(List<SUrlAsset> obj){
		this.assetUrlAddr = obj;
		onFieldSet(3, obj);
	}

	public List<SUrlAsset> getAssetUrlAddr(){
		return assetUrlAddr;
	}

	public void setSla(List<SSlaValue> obj){
		this.sla = obj;
		onFieldSet(4, obj);
	}

	public List<SSlaValue> getSla(){
		return sla;
	}

	public void setGsu(SGsuValue obj){
		this.gsu = obj;
		onFieldSet(5, obj);
	}

	public SGsuValue getGsu(){
		return gsu;
	}

	public void setSlaAlarm(SSlaAlert obj){
		this.slaAlarm = obj;
		onFieldSet(6, obj);
	}

	public SSlaAlert getSlaAlarm(){
		return slaAlarm;
	}

	public void setLastQuotaAlarm(SLastQuotaAlert obj){
		this.lastQuotaAlarm = obj;
		onFieldSet(7, obj);
	}

	public SLastQuotaAlert getLastQuotaAlarm(){
		return lastQuotaAlarm;
	}

	public void setZoneAlarmAoc(List<SAocZone> obj){
		this.zoneAlarmAoc = obj;
		onFieldSet(14, obj);
	}

	public List<SAocZone> getZoneAlarmAoc(){
		return zoneAlarmAoc;
	}

	public void setZoneAlarmUrl(List<SUrlZone> obj){
		this.zoneAlarmUrl = obj;
		onFieldSet(15, obj);
	}

	public List<SUrlZone> getZoneAlarmUrl(){
		return zoneAlarmUrl;
	}

	public void setOcsRejectCause(SOcsRejectCause obj){
		this.ocsRejectCause = obj;
		onFieldSet(16, obj);
	}

	public SOcsRejectCause getOcsRejectCause(){
		return ocsRejectCause;
	}

	public void setBeforeMakecallAoc(SBeforeMakeCallAoc obj){
		this.beforeMakecallAoc = obj;
		onFieldSet(17, obj);
	}

	public SBeforeMakeCallAoc getBeforeMakecallAoc(){
		return beforeMakecallAoc;
	}

	public void setBeforeMakecallUrl(SBeforeMakeCallUrl obj){
		this.beforeMakecallUrl = obj;
		onFieldSet(18, obj);
	}

	public SBeforeMakeCallUrl getBeforeMakecallUrl(){
		return beforeMakecallUrl;
	}

	public void setRgName(Map<String,SRgName> obj){
		this.rgName = obj;
		onFieldSet(19, obj);
	}

	public Map<String,SRgName> getRgName(){
		return rgName;
	}

	public void setUrlAccumulateAlarm(List<SAccumulatePriceUrlAddr> obj){
		this.urlAccumulateAlarm = obj;
		onFieldSet(20, obj);
	}

	public List<SAccumulatePriceUrlAddr> getUrlAccumulateAlarm(){
		return urlAccumulateAlarm;
	}

	public void setAocAccumulateAlarm(List<SAccumulatePriceAocCode> obj){
		this.aocAccumulateAlarm = obj;
		onFieldSet(21, obj);
	}

	public List<SAccumulatePriceAocCode> getAocAccumulateAlarm(){
		return aocAccumulateAlarm;
	}

	public void setVt(long obj){
		this.vt = obj;
		onFieldSet(8, obj);
	}

	public long getVt(){
		return vt;
	}

	public void setVqt(long obj){
		this.vqt = obj;
		onFieldSet(9, obj);
	}

	public long getVqt(){
		return vqt;
	}

	public void setTqt(long obj){
		this.tqt = obj;
		onFieldSet(10, obj);
	}

	public long getTqt(){
		return tqt;
	}

	public void setQht(long obj){
		this.qht = obj;
		onFieldSet(11, obj);
	}

	public long getQht(){
		return qht;
	}

	public void setQct(long obj){
		this.qct = obj;
		onFieldSet(12, obj);
	}

	public long getQct(){
		return qct;
	}

	public void setInReleaseDeal(int obj){
		this.inReleaseDeal = obj;
		onFieldSet(13, obj);
	}

	public int getInReleaseDeal(){
		return inReleaseDeal;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOutRatingFecXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 22; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOutRatingFecXdr(SOutRatingFecXdr arg0){
		copy(arg0);
	}

	public void copy(final SOutRatingFecXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		accPriceAlarmCode = rhs.accPriceAlarmCode;
		accPriceUrlAddr = rhs.accPriceUrlAddr;
		assetAlarmCode = rhs.assetAlarmCode;
		assetUrlAddr = rhs.assetUrlAddr;
		sla = rhs.sla;
		gsu = rhs.gsu;
		slaAlarm = rhs.slaAlarm;
		lastQuotaAlarm = rhs.lastQuotaAlarm;
		vt = rhs.vt;
		vqt = rhs.vqt;
		tqt = rhs.tqt;
		qht = rhs.qht;
		qct = rhs.qct;
		inReleaseDeal = rhs.inReleaseDeal;
		zoneAlarmAoc = rhs.zoneAlarmAoc;
		zoneAlarmUrl = rhs.zoneAlarmUrl;
		ocsRejectCause = rhs.ocsRejectCause;
		beforeMakecallAoc = rhs.beforeMakecallAoc;
		beforeMakecallUrl = rhs.beforeMakecallUrl;
		rgName = rhs.rgName;
		urlAccumulateAlarm = rhs.urlAccumulateAlarm;
		aocAccumulateAlarm = rhs.aocAccumulateAlarm;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOutRatingFecXdr rhs=(SOutRatingFecXdr)rhs0;
		if(!ObjectUtils.equals(accPriceAlarmCode, rhs.accPriceAlarmCode)) return false;
		if(!ObjectUtils.equals(accPriceUrlAddr, rhs.accPriceUrlAddr)) return false;
		if(!ObjectUtils.equals(assetAlarmCode, rhs.assetAlarmCode)) return false;
		if(!ObjectUtils.equals(assetUrlAddr, rhs.assetUrlAddr)) return false;
		if(!ObjectUtils.equals(sla, rhs.sla)) return false;
		if(!ObjectUtils.equals(gsu, rhs.gsu)) return false;
		if(!ObjectUtils.equals(slaAlarm, rhs.slaAlarm)) return false;
		if(!ObjectUtils.equals(lastQuotaAlarm, rhs.lastQuotaAlarm)) return false;
		if(!ObjectUtils.equals(vt, rhs.vt)) return false;
		if(!ObjectUtils.equals(vqt, rhs.vqt)) return false;
		if(!ObjectUtils.equals(tqt, rhs.tqt)) return false;
		if(!ObjectUtils.equals(qht, rhs.qht)) return false;
		if(!ObjectUtils.equals(qct, rhs.qct)) return false;
		if(!ObjectUtils.equals(inReleaseDeal, rhs.inReleaseDeal)) return false;
		if(!ObjectUtils.equals(zoneAlarmAoc, rhs.zoneAlarmAoc)) return false;
		if(!ObjectUtils.equals(zoneAlarmUrl, rhs.zoneAlarmUrl)) return false;
		if(!ObjectUtils.equals(ocsRejectCause, rhs.ocsRejectCause)) return false;
		if(!ObjectUtils.equals(beforeMakecallAoc, rhs.beforeMakecallAoc)) return false;
		if(!ObjectUtils.equals(beforeMakecallUrl, rhs.beforeMakecallUrl)) return false;
		if(!ObjectUtils.equals(rgName, rhs.rgName)) return false;
		if(!ObjectUtils.equals(urlAccumulateAlarm, rhs.urlAccumulateAlarm)) return false;
		if(!ObjectUtils.equals(aocAccumulateAlarm, rhs.aocAccumulateAlarm)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accPriceAlarmCode)
		.append(accPriceUrlAddr)
		.append(assetAlarmCode)
		.append(assetUrlAddr)
		.append(sla)
		.append(gsu)
		.append(slaAlarm)
		.append(lastQuotaAlarm)
		.append(vt)
		.append(vqt)
		.append(tqt)
		.append(qht)
		.append(qct)
		.append(inReleaseDeal)
		.append(zoneAlarmAoc)
		.append(zoneAlarmUrl)
		.append(ocsRejectCause)
		.append(beforeMakecallAoc)
		.append(beforeMakecallUrl)
		.append(rgName)
		.append(urlAccumulateAlarm)
		.append(aocAccumulateAlarm)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(22);
public static final long BITS_ALL_MARKER = 0x200000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOutRatingFecXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ACC_PRICE_ALARM_CODE", 0, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ACC_PRICE_URL_ADDR", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ASSET_ALARM_CODE", 2, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ASSET_URL_ADDR", 3, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "SLA", 4, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "GSU", 5, SGsuValue.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "SLA_ALARM", 6, SSlaAlert.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "LAST_QUOTA_ALARM", 7, SLastQuotaAlert.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "VT", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "VQT", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "TQT", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "QHT", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "QCT", 12, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "IN_RELEASE_DEAL", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ZONE_ALARM_AOC", 14, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "ZONE_ALARM_URL", 15, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "OCS_REJECT_CAUSE", 16, SOcsRejectCause.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "BEFORE_MAKECALL_AOC", 17, SBeforeMakeCallAoc.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "BEFORE_MAKECALL_URL", 18, SBeforeMakeCallUrl.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "RG_NAME", 19, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "URL_ACCUMULATE_ALARM", 20, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingFecXdr.class, "AOC_ACCUMULATE_ALARM", 21, List.class));
}

}