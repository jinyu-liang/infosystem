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
@XmlType(propOrder={"aNumber","msrn","scpId","mscId","vlrId","locationId","inTrunkid","outTrunkid","voipCdrFlag","oppMscId","oppVlrId","oppLocationId","translatedNumber","videoType","mocId","mtcId","gsmPbxInfo","gsmVcInfo","oppNoaccessNumber","roamNetType","bRoamEconomicallyRatingFlag","lfeeAdd","upRecnum","dnRecnum"})
@Sdl(module="MXdr")
public class SGsmSpecInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_A_NUMBER = "A_NUMBER";
	public final static String COL_MSRN = "MSRN";
	public final static String COL_SCP_ID = "SCP_ID";
	public final static String COL_MSC_ID = "MSC_ID";
	public final static String COL_VLR_ID = "VLR_ID";
	public final static String COL_LOCATION_ID = "LOCATION_ID";
	public final static String COL_IN_TRUNKID = "IN_TRUNKID";
	public final static String COL_OUT_TRUNKID = "OUT_TRUNKID";
	public final static String COL_VOIP_CDR_FLAG = "VOIP_CDR_FLAG";
	public final static String COL_OPP_MSC_ID = "OPP_MSC_ID";
	public final static String COL_OPP_VLR_ID = "OPP_VLR_ID";
	public final static String COL_OPP_LOCATION_ID = "OPP_LOCATION_ID";
	public final static String COL_TRANSLATED_NUMBER = "TRANSLATED_NUMBER";
	public final static String COL_VIDEO_TYPE = "VIDEO_TYPE";
	public final static String COL_MOC_ID = "MOC_ID";
	public final static String COL_MTC_ID = "MTC_ID";
	public final static String COL_GSM_PBX_INFO = "GSM_PBX_INFO";
	public final static String COL_GSM_VC_INFO = "GSM_VC_INFO";
	public final static String COL_OPP_NOACCESS_NUMBER = "OPP_NOACCESS_NUMBER";
	public final static String COL_ROAM_NET_TYPE = "ROAM_NET_TYPE";
	public final static String COL_B_ROAM_ECONOMICALLY_RATING_FLAG = "B_ROAM_ECONOMICALLY_RATING_FLAG";
	public final static String COL_LFEE_ADD = "LFEE_ADD";
	public final static String COL_UP_RECNUM = "UP_RECNUM";
	public final static String COL_DN_RECNUM = "DN_RECNUM";
	public final static int IDX_A_NUMBER = 0;
	public final static int IDX_MSRN = 1;
	public final static int IDX_SCP_ID = 2;
	public final static int IDX_MSC_ID = 3;
	public final static int IDX_VLR_ID = 4;
	public final static int IDX_LOCATION_ID = 5;
	public final static int IDX_IN_TRUNKID = 6;
	public final static int IDX_OUT_TRUNKID = 7;
	public final static int IDX_VOIP_CDR_FLAG = 8;
	public final static int IDX_OPP_MSC_ID = 9;
	public final static int IDX_OPP_VLR_ID = 10;
	public final static int IDX_OPP_LOCATION_ID = 11;
	public final static int IDX_TRANSLATED_NUMBER = 12;
	public final static int IDX_VIDEO_TYPE = 13;
	public final static int IDX_MOC_ID = 14;
	public final static int IDX_MTC_ID = 15;
	public final static int IDX_GSM_PBX_INFO = 16;
	public final static int IDX_GSM_VC_INFO = 17;
	public final static int IDX_OPP_NOACCESS_NUMBER = 18;
	public final static int IDX_ROAM_NET_TYPE = 19;
	public final static int IDX_B_ROAM_ECONOMICALLY_RATING_FLAG = 20;
	public final static int IDX_LFEE_ADD = 21;
	public final static int IDX_UP_RECNUM = 22;
	public final static int IDX_DN_RECNUM = 23;

	/**
	 * 
	 */
	@XmlElement(name="gsmPbxInfo")
	@Sdl
	private SPbxInfo gsmPbxInfo;

	/**
	 * 
	 */
	@XmlElement(name="gsmVcInfo")
	@Sdl
	private SVcInfo gsmVcInfo;

	/**
	 * 
	 */
	@XmlElement(name="aNumber")
	@Sdl
	private String aNumber;

	/**
	 * 
	 */
	@XmlElement(name="msrn")
	@Sdl
	private String msrn;

	/**
	 * 
	 */
	@XmlElement(name="scpId")
	@Sdl
	private String scpId;

	/**
	 * 
	 */
	@XmlElement(name="mscId")
	@Sdl
	private String mscId;

	/**
	 * 
	 */
	@XmlElement(name="vlrId")
	@Sdl
	private String vlrId;

	/**
	 * 
	 */
	@XmlElement(name="locationId")
	@Sdl
	private String locationId;

	/**
	 * 
	 */
	@XmlElement(name="inTrunkid")
	@Sdl
	private String inTrunkid;

	/**
	 * 
	 */
	@XmlElement(name="outTrunkid")
	@Sdl
	private String outTrunkid;

	/**
	 * 
	 */
	@XmlElement(name="voipCdrFlag")
	@Sdl
	private int voipCdrFlag;

	/**
	 * 
	 */
	@XmlElement(name="oppMscId")
	@Sdl
	private String oppMscId;

	/**
	 * 
	 */
	@XmlElement(name="oppVlrId")
	@Sdl
	private String oppVlrId;

	/**
	 * 
	 */
	@XmlElement(name="oppLocationId")
	@Sdl
	private String oppLocationId;

	/**
	 * 
	 */
	@XmlElement(name="translatedNumber")
	@Sdl
	private String translatedNumber;

	/**
	 * 
	 */
	@XmlElement(name="videoType")
	@Sdl
	private int videoType;

	/**
	 * 
	 */
	@XmlElement(name="mocId")
	@Sdl
	private String mocId;

	/**
	 * 
	 */
	@XmlElement(name="mtcId")
	@Sdl
	private String mtcId;

	/**
	 * 
	 */
	@XmlElement(name="oppNoaccessNumber")
	@Sdl
	private String oppNoaccessNumber;

	/**
	 * 
	 */
	@XmlElement(name="roamNetType")
	@Sdl
	private int roamNetType;

	/**
	 * 
	 */
	@XmlElement(name="bRoamEconomicallyRatingFlag")
	@Sdl
	private short bRoamEconomicallyRatingFlag;

	/**
	 * 
	 */
	@XmlElement(name="lfeeAdd")
	@Sdl
	private int lfeeAdd;

	/**
	 * 
	 */
	@XmlElement(name="upRecnum")
	@Sdl
	private int upRecnum;

	/**
	 * 
	 */
	@XmlElement(name="dnRecnum")
	@Sdl
	private int dnRecnum;

	public void setGsmPbxInfo(SPbxInfo obj){
		this.gsmPbxInfo = obj;
		onFieldSet(16, obj);
	}

	public SPbxInfo getGsmPbxInfo(){
		return gsmPbxInfo;
	}

	public void setGsmVcInfo(SVcInfo obj){
		this.gsmVcInfo = obj;
		onFieldSet(17, obj);
	}

	public SVcInfo getGsmVcInfo(){
		return gsmVcInfo;
	}

	public void setANumber(String obj){
		this.aNumber = obj;
		onFieldSet(0, obj);
	}

	public String getANumber(){
		return aNumber;
	}

	public void setMsrn(String obj){
		this.msrn = obj;
		onFieldSet(1, obj);
	}

	public String getMsrn(){
		return msrn;
	}

	public void setScpId(String obj){
		this.scpId = obj;
		onFieldSet(2, obj);
	}

	public String getScpId(){
		return scpId;
	}

	public void setMscId(String obj){
		this.mscId = obj;
		onFieldSet(3, obj);
	}

	public String getMscId(){
		return mscId;
	}

	public void setVlrId(String obj){
		this.vlrId = obj;
		onFieldSet(4, obj);
	}

	public String getVlrId(){
		return vlrId;
	}

	public void setLocationId(String obj){
		this.locationId = obj;
		onFieldSet(5, obj);
	}

	public String getLocationId(){
		return locationId;
	}

	public void setInTrunkid(String obj){
		this.inTrunkid = obj;
		onFieldSet(6, obj);
	}

	public String getInTrunkid(){
		return inTrunkid;
	}

	public void setOutTrunkid(String obj){
		this.outTrunkid = obj;
		onFieldSet(7, obj);
	}

	public String getOutTrunkid(){
		return outTrunkid;
	}

	public void setVoipCdrFlag(int obj){
		this.voipCdrFlag = obj;
		onFieldSet(8, obj);
	}

	public int getVoipCdrFlag(){
		return voipCdrFlag;
	}

	public void setOppMscId(String obj){
		this.oppMscId = obj;
		onFieldSet(9, obj);
	}

	public String getOppMscId(){
		return oppMscId;
	}

	public void setOppVlrId(String obj){
		this.oppVlrId = obj;
		onFieldSet(10, obj);
	}

	public String getOppVlrId(){
		return oppVlrId;
	}

	public void setOppLocationId(String obj){
		this.oppLocationId = obj;
		onFieldSet(11, obj);
	}

	public String getOppLocationId(){
		return oppLocationId;
	}

	public void setTranslatedNumber(String obj){
		this.translatedNumber = obj;
		onFieldSet(12, obj);
	}

	public String getTranslatedNumber(){
		return translatedNumber;
	}

	public void setVideoType(int obj){
		this.videoType = obj;
		onFieldSet(13, obj);
	}

	public int getVideoType(){
		return videoType;
	}

	public void setMocId(String obj){
		this.mocId = obj;
		onFieldSet(14, obj);
	}

	public String getMocId(){
		return mocId;
	}

	public void setMtcId(String obj){
		this.mtcId = obj;
		onFieldSet(15, obj);
	}

	public String getMtcId(){
		return mtcId;
	}

	public void setOppNoaccessNumber(String obj){
		this.oppNoaccessNumber = obj;
		onFieldSet(18, obj);
	}

	public String getOppNoaccessNumber(){
		return oppNoaccessNumber;
	}

	public void setRoamNetType(int obj){
		this.roamNetType = obj;
		onFieldSet(19, obj);
	}

	public int getRoamNetType(){
		return roamNetType;
	}

	public void setBRoamEconomicallyRatingFlag(short obj){
		this.bRoamEconomicallyRatingFlag = obj;
		onFieldSet(20, obj);
	}

	public short getBRoamEconomicallyRatingFlag(){
		return bRoamEconomicallyRatingFlag;
	}

	public void setLfeeAdd(int obj){
		this.lfeeAdd = obj;
		onFieldSet(21, obj);
	}

	public int getLfeeAdd(){
		return lfeeAdd;
	}

	public void setUpRecnum(int obj){
		this.upRecnum = obj;
		onFieldSet(22, obj);
	}

	public int getUpRecnum(){
		return upRecnum;
	}

	public void setDnRecnum(int obj){
		this.dnRecnum = obj;
		onFieldSet(23, obj);
	}

	public int getDnRecnum(){
		return dnRecnum;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGsmSpecInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 24; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGsmSpecInfo(SGsmSpecInfo arg0){
		copy(arg0);
	}

	public void copy(final SGsmSpecInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		aNumber = rhs.aNumber;
		msrn = rhs.msrn;
		scpId = rhs.scpId;
		mscId = rhs.mscId;
		vlrId = rhs.vlrId;
		locationId = rhs.locationId;
		inTrunkid = rhs.inTrunkid;
		outTrunkid = rhs.outTrunkid;
		voipCdrFlag = rhs.voipCdrFlag;
		oppMscId = rhs.oppMscId;
		oppVlrId = rhs.oppVlrId;
		oppLocationId = rhs.oppLocationId;
		translatedNumber = rhs.translatedNumber;
		videoType = rhs.videoType;
		mocId = rhs.mocId;
		mtcId = rhs.mtcId;
		gsmPbxInfo = rhs.gsmPbxInfo;
		gsmVcInfo = rhs.gsmVcInfo;
		oppNoaccessNumber = rhs.oppNoaccessNumber;
		roamNetType = rhs.roamNetType;
		bRoamEconomicallyRatingFlag = rhs.bRoamEconomicallyRatingFlag;
		lfeeAdd = rhs.lfeeAdd;
		upRecnum = rhs.upRecnum;
		dnRecnum = rhs.dnRecnum;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGsmSpecInfo rhs=(SGsmSpecInfo)rhs0;
		if(!ObjectUtils.equals(aNumber, rhs.aNumber)) return false;
		if(!ObjectUtils.equals(msrn, rhs.msrn)) return false;
		if(!ObjectUtils.equals(scpId, rhs.scpId)) return false;
		if(!ObjectUtils.equals(mscId, rhs.mscId)) return false;
		if(!ObjectUtils.equals(vlrId, rhs.vlrId)) return false;
		if(!ObjectUtils.equals(locationId, rhs.locationId)) return false;
		if(!ObjectUtils.equals(inTrunkid, rhs.inTrunkid)) return false;
		if(!ObjectUtils.equals(outTrunkid, rhs.outTrunkid)) return false;
		if(!ObjectUtils.equals(voipCdrFlag, rhs.voipCdrFlag)) return false;
		if(!ObjectUtils.equals(oppMscId, rhs.oppMscId)) return false;
		if(!ObjectUtils.equals(oppVlrId, rhs.oppVlrId)) return false;
		if(!ObjectUtils.equals(oppLocationId, rhs.oppLocationId)) return false;
		if(!ObjectUtils.equals(translatedNumber, rhs.translatedNumber)) return false;
		if(!ObjectUtils.equals(videoType, rhs.videoType)) return false;
		if(!ObjectUtils.equals(mocId, rhs.mocId)) return false;
		if(!ObjectUtils.equals(mtcId, rhs.mtcId)) return false;
		if(!ObjectUtils.equals(gsmPbxInfo, rhs.gsmPbxInfo)) return false;
		if(!ObjectUtils.equals(gsmVcInfo, rhs.gsmVcInfo)) return false;
		if(!ObjectUtils.equals(oppNoaccessNumber, rhs.oppNoaccessNumber)) return false;
		if(!ObjectUtils.equals(roamNetType, rhs.roamNetType)) return false;
		if(!ObjectUtils.equals(bRoamEconomicallyRatingFlag, rhs.bRoamEconomicallyRatingFlag)) return false;
		if(!ObjectUtils.equals(lfeeAdd, rhs.lfeeAdd)) return false;
		if(!ObjectUtils.equals(upRecnum, rhs.upRecnum)) return false;
		if(!ObjectUtils.equals(dnRecnum, rhs.dnRecnum)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(aNumber)
		.append(msrn)
		.append(scpId)
		.append(mscId)
		.append(vlrId)
		.append(locationId)
		.append(inTrunkid)
		.append(outTrunkid)
		.append(voipCdrFlag)
		.append(oppMscId)
		.append(oppVlrId)
		.append(oppLocationId)
		.append(translatedNumber)
		.append(videoType)
		.append(mocId)
		.append(mtcId)
		.append(gsmPbxInfo)
		.append(gsmVcInfo)
		.append(oppNoaccessNumber)
		.append(roamNetType)
		.append(bRoamEconomicallyRatingFlag)
		.append(lfeeAdd)
		.append(upRecnum)
		.append(dnRecnum)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(24);
public static final long BITS_ALL_MARKER = 0x800000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGsmSpecInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "A_NUMBER", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "MSRN", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "SCP_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "MSC_ID", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "VLR_ID", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "LOCATION_ID", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "IN_TRUNKID", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "OUT_TRUNKID", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "VOIP_CDR_FLAG", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "OPP_MSC_ID", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "OPP_VLR_ID", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "OPP_LOCATION_ID", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "TRANSLATED_NUMBER", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "VIDEO_TYPE", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "MOC_ID", 14, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "MTC_ID", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "GSM_PBX_INFO", 16, SPbxInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "GSM_VC_INFO", 17, SVcInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "OPP_NOACCESS_NUMBER", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "ROAM_NET_TYPE", 19, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "B_ROAM_ECONOMICALLY_RATING_FLAG", 20, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "LFEE_ADD", 21, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "UP_RECNUM", 22, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmSpecInfo.class, "DN_RECNUM", 23, int.class));
}

}