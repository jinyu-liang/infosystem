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
@XmlType(propOrder={"transformFreeresInfo","afterMakecallInfo","zoneAlertList","packageAlertList","assetAlertList","aocAssetList","urlAssetList","urlAccumulateAlarm","aocAccumulateAlarm","authType","accumulatePriceAdjustInfo","preStdChargingInfo"})
@Sdl(module="MXdr")
public class SOutInFecXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_TRANSFORM_FREERES_INFO = "TRANSFORM_FREERES_INFO";
	public final static String COL_AFTER_MAKECALL_INFO = "AFTER_MAKECALL_INFO";
	public final static String COL_ZONE_ALERT_LIST = "ZONE_ALERT_LIST";
	public final static String COL_PACKAGE_ALERT_LIST = "PACKAGE_ALERT_LIST";
	public final static String COL_ASSET_ALERT_LIST = "ASSET_ALERT_LIST";
	public final static String COL_AOC_ASSET_LIST = "AOC_ASSET_LIST";
	public final static String COL_URL_ASSET_LIST = "URL_ASSET_LIST";
	public final static String COL_URL_ACCUMULATE_ALARM = "URL_ACCUMULATE_ALARM";
	public final static String COL_AOC_ACCUMULATE_ALARM = "AOC_ACCUMULATE_ALARM";
	public final static String COL_AUTH_TYPE = "AUTH_TYPE";
	public final static String COL_ACCUMULATE_PRICE_ADJUST_INFO = "ACCUMULATE_PRICE_ADJUST_INFO";
	public final static String COL_PRE_STD_CHARGING_INFO = "PRE_STD_CHARGING_INFO";
	public final static int IDX_TRANSFORM_FREERES_INFO = 0;
	public final static int IDX_AFTER_MAKECALL_INFO = 1;
	public final static int IDX_ZONE_ALERT_LIST = 2;
	public final static int IDX_PACKAGE_ALERT_LIST = 3;
	public final static int IDX_ASSET_ALERT_LIST = 4;
	public final static int IDX_AOC_ASSET_LIST = 5;
	public final static int IDX_URL_ASSET_LIST = 6;
	public final static int IDX_URL_ACCUMULATE_ALARM = 7;
	public final static int IDX_AOC_ACCUMULATE_ALARM = 8;
	public final static int IDX_AUTH_TYPE = 9;
	public final static int IDX_ACCUMULATE_PRICE_ADJUST_INFO = 10;
	public final static int IDX_PRE_STD_CHARGING_INFO = 11;

	/**
	 * 
	 */
	@XmlElement(name="transformFreeresInfo")
	@Sdl
	private List<STransformFreeresInfo> transformFreeresInfo;

	/**
	 * 
	 */
	@XmlElement(name="afterMakecallInfo")
	@Sdl
	private List<SAfterMakeCall> afterMakecallInfo;

	/**
	 * 
	 */
	@XmlElement(name="zoneAlertList")
	@Sdl
	private List<SZoneAlert> zoneAlertList;

	/**
	 * 
	 */
	@XmlElement(name="packageAlertList")
	@Sdl
	private List<SPackageAlert> packageAlertList;

	/**
	 * 
	 */
	@XmlElement(name="assetAlertList")
	@Sdl
	private List<SAssetAlert> assetAlertList;

	/**
	 * 
	 */
	@XmlElement(name="aocAssetList")
	@Sdl
	private List<SAocAsset> aocAssetList;

	/**
	 * 
	 */
	@XmlElement(name="urlAssetList")
	@Sdl
	private List<SUrlAsset> urlAssetList;

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
	@XmlElement(name="accumulatePriceAdjustInfo")
	@Sdl
	private List<SAccumulateAdjustMiddleInfo> accumulatePriceAdjustInfo;

	/**
	 * 
	 */
	@XmlElement(name="preStdChargingInfo")
	@Sdl
	private Map<Integer,SPreStdChargingInfo> preStdChargingInfo;

	/**
	 * 
	 */
	@XmlElement(name="authType")
	@Sdl
	private int authType;

	public void setTransformFreeresInfo(List<STransformFreeresInfo> obj){
		this.transformFreeresInfo = obj;
		onFieldSet(0, obj);
	}

	public List<STransformFreeresInfo> getTransformFreeresInfo(){
		return transformFreeresInfo;
	}

	public void setAfterMakecallInfo(List<SAfterMakeCall> obj){
		this.afterMakecallInfo = obj;
		onFieldSet(1, obj);
	}

	public List<SAfterMakeCall> getAfterMakecallInfo(){
		return afterMakecallInfo;
	}

	public void setZoneAlertList(List<SZoneAlert> obj){
		this.zoneAlertList = obj;
		onFieldSet(2, obj);
	}

	public List<SZoneAlert> getZoneAlertList(){
		return zoneAlertList;
	}

	public void setPackageAlertList(List<SPackageAlert> obj){
		this.packageAlertList = obj;
		onFieldSet(3, obj);
	}

	public List<SPackageAlert> getPackageAlertList(){
		return packageAlertList;
	}

	public void setAssetAlertList(List<SAssetAlert> obj){
		this.assetAlertList = obj;
		onFieldSet(4, obj);
	}

	public List<SAssetAlert> getAssetAlertList(){
		return assetAlertList;
	}

	public void setAocAssetList(List<SAocAsset> obj){
		this.aocAssetList = obj;
		onFieldSet(5, obj);
	}

	public List<SAocAsset> getAocAssetList(){
		return aocAssetList;
	}

	public void setUrlAssetList(List<SUrlAsset> obj){
		this.urlAssetList = obj;
		onFieldSet(6, obj);
	}

	public List<SUrlAsset> getUrlAssetList(){
		return urlAssetList;
	}

	public void setUrlAccumulateAlarm(List<SAccumulatePriceUrlAddr> obj){
		this.urlAccumulateAlarm = obj;
		onFieldSet(7, obj);
	}

	public List<SAccumulatePriceUrlAddr> getUrlAccumulateAlarm(){
		return urlAccumulateAlarm;
	}

	public void setAocAccumulateAlarm(List<SAccumulatePriceAocCode> obj){
		this.aocAccumulateAlarm = obj;
		onFieldSet(8, obj);
	}

	public List<SAccumulatePriceAocCode> getAocAccumulateAlarm(){
		return aocAccumulateAlarm;
	}

	public void setAccumulatePriceAdjustInfo(List<SAccumulateAdjustMiddleInfo> obj){
		this.accumulatePriceAdjustInfo = obj;
		onFieldSet(10, obj);
	}

	public List<SAccumulateAdjustMiddleInfo> getAccumulatePriceAdjustInfo(){
		return accumulatePriceAdjustInfo;
	}

	public void setPreStdChargingInfo(Map<Integer,SPreStdChargingInfo> obj){
		this.preStdChargingInfo = obj;
		onFieldSet(11, obj);
	}

	public Map<Integer,SPreStdChargingInfo> getPreStdChargingInfo(){
		return preStdChargingInfo;
	}

	public void setAuthType(int obj){
		this.authType = obj;
		onFieldSet(9, obj);
	}

	public int getAuthType(){
		return authType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOutInFecXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 12; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOutInFecXdr(SOutInFecXdr arg0){
		copy(arg0);
	}

	public void copy(final SOutInFecXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		transformFreeresInfo = rhs.transformFreeresInfo;
		afterMakecallInfo = rhs.afterMakecallInfo;
		zoneAlertList = rhs.zoneAlertList;
		packageAlertList = rhs.packageAlertList;
		assetAlertList = rhs.assetAlertList;
		aocAssetList = rhs.aocAssetList;
		urlAssetList = rhs.urlAssetList;
		urlAccumulateAlarm = rhs.urlAccumulateAlarm;
		aocAccumulateAlarm = rhs.aocAccumulateAlarm;
		authType = rhs.authType;
		accumulatePriceAdjustInfo = rhs.accumulatePriceAdjustInfo;
		preStdChargingInfo = rhs.preStdChargingInfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOutInFecXdr rhs=(SOutInFecXdr)rhs0;
		if(!ObjectUtils.equals(transformFreeresInfo, rhs.transformFreeresInfo)) return false;
		if(!ObjectUtils.equals(afterMakecallInfo, rhs.afterMakecallInfo)) return false;
		if(!ObjectUtils.equals(zoneAlertList, rhs.zoneAlertList)) return false;
		if(!ObjectUtils.equals(packageAlertList, rhs.packageAlertList)) return false;
		if(!ObjectUtils.equals(assetAlertList, rhs.assetAlertList)) return false;
		if(!ObjectUtils.equals(aocAssetList, rhs.aocAssetList)) return false;
		if(!ObjectUtils.equals(urlAssetList, rhs.urlAssetList)) return false;
		if(!ObjectUtils.equals(urlAccumulateAlarm, rhs.urlAccumulateAlarm)) return false;
		if(!ObjectUtils.equals(aocAccumulateAlarm, rhs.aocAccumulateAlarm)) return false;
		if(!ObjectUtils.equals(authType, rhs.authType)) return false;
		if(!ObjectUtils.equals(accumulatePriceAdjustInfo, rhs.accumulatePriceAdjustInfo)) return false;
		if(!ObjectUtils.equals(preStdChargingInfo, rhs.preStdChargingInfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(transformFreeresInfo)
		.append(afterMakecallInfo)
		.append(zoneAlertList)
		.append(packageAlertList)
		.append(assetAlertList)
		.append(aocAssetList)
		.append(urlAssetList)
		.append(urlAccumulateAlarm)
		.append(aocAccumulateAlarm)
		.append(authType)
		.append(accumulatePriceAdjustInfo)
		.append(preStdChargingInfo)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(12);
public static final long BITS_ALL_MARKER = 0x800L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOutInFecXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "TRANSFORM_FREERES_INFO", 0, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "AFTER_MAKECALL_INFO", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "ZONE_ALERT_LIST", 2, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "PACKAGE_ALERT_LIST", 3, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "ASSET_ALERT_LIST", 4, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "AOC_ASSET_LIST", 5, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "URL_ASSET_LIST", 6, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "URL_ACCUMULATE_ALARM", 7, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "AOC_ACCUMULATE_ALARM", 8, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "AUTH_TYPE", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "ACCUMULATE_PRICE_ADJUST_INFO", 10, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutInFecXdr.class, "PRE_STD_CHARGING_INFO", 11, Map.class));
}

}