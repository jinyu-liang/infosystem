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
@XmlType(propOrder={"roamType","tollType","conditionId","vplmn2","oppHplmn2","oppVplmn2","rateProdId"})
@Sdl(module="MXdr")
public class SBorderRoamInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ROAM_TYPE = "ROAM_TYPE";
	public final static String COL_TOLL_TYPE = "TOLL_TYPE";
	public final static String COL_CONDITION_ID = "CONDITION_ID";
	public final static String COL_VPLMN2 = "VPLMN2";
	public final static String COL_OPP_HPLMN2 = "OPP_HPLMN2";
	public final static String COL_OPP_VPLMN2 = "OPP_VPLMN2";
	public final static String COL_RATE_PROD_ID = "RATE_PROD_ID";
	public final static int IDX_ROAM_TYPE = 0;
	public final static int IDX_TOLL_TYPE = 1;
	public final static int IDX_CONDITION_ID = 2;
	public final static int IDX_VPLMN2 = 3;
	public final static int IDX_OPP_HPLMN2 = 4;
	public final static int IDX_OPP_VPLMN2 = 5;
	public final static int IDX_RATE_PROD_ID = 6;

	/**
	 * 
	 */
	@XmlElement(name="rateProdId")
	@Sdl
	private Map<Long,SRateProdId> rateProdId;

	/**
	 * 
	 */
	@XmlElement(name="roamType")
	@Sdl
	private short roamType;

	/**
	 * 
	 */
	@XmlElement(name="tollType")
	@Sdl
	private short tollType;

	/**
	 * 
	 */
	@XmlElement(name="conditionId")
	@Sdl
	private int conditionId;

	/**
	 * 
	 */
	@XmlElement(name="vplmn2")
	@Sdl
	private String vplmn2;

	/**
	 * 
	 */
	@XmlElement(name="oppHplmn2")
	@Sdl
	private String oppHplmn2;

	/**
	 * 
	 */
	@XmlElement(name="oppVplmn2")
	@Sdl
	private String oppVplmn2;

	public void setRateProdId(Map<Long,SRateProdId> obj){
		this.rateProdId = obj;
		onFieldSet(6, obj);
	}

	public Map<Long,SRateProdId> getRateProdId(){
		return rateProdId;
	}

	public void setRoamType(short obj){
		this.roamType = obj;
		onFieldSet(0, obj);
	}

	public short getRoamType(){
		return roamType;
	}

	public void setTollType(short obj){
		this.tollType = obj;
		onFieldSet(1, obj);
	}

	public short getTollType(){
		return tollType;
	}

	public void setConditionId(int obj){
		this.conditionId = obj;
		onFieldSet(2, obj);
	}

	public int getConditionId(){
		return conditionId;
	}

	public void setVplmn2(String obj){
		this.vplmn2 = obj;
		onFieldSet(3, obj);
	}

	public String getVplmn2(){
		return vplmn2;
	}

	public void setOppHplmn2(String obj){
		this.oppHplmn2 = obj;
		onFieldSet(4, obj);
	}

	public String getOppHplmn2(){
		return oppHplmn2;
	}

	public void setOppVplmn2(String obj){
		this.oppVplmn2 = obj;
		onFieldSet(5, obj);
	}

	public String getOppVplmn2(){
		return oppVplmn2;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBorderRoamInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBorderRoamInfo(SBorderRoamInfo arg0){
		copy(arg0);
	}

	public void copy(final SBorderRoamInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		roamType = rhs.roamType;
		tollType = rhs.tollType;
		conditionId = rhs.conditionId;
		vplmn2 = rhs.vplmn2;
		oppHplmn2 = rhs.oppHplmn2;
		oppVplmn2 = rhs.oppVplmn2;
		rateProdId = rhs.rateProdId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBorderRoamInfo rhs=(SBorderRoamInfo)rhs0;
		if(!ObjectUtils.equals(roamType, rhs.roamType)) return false;
		if(!ObjectUtils.equals(tollType, rhs.tollType)) return false;
		if(!ObjectUtils.equals(conditionId, rhs.conditionId)) return false;
		if(!ObjectUtils.equals(vplmn2, rhs.vplmn2)) return false;
		if(!ObjectUtils.equals(oppHplmn2, rhs.oppHplmn2)) return false;
		if(!ObjectUtils.equals(oppVplmn2, rhs.oppVplmn2)) return false;
		if(!ObjectUtils.equals(rateProdId, rhs.rateProdId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(roamType)
		.append(tollType)
		.append(conditionId)
		.append(vplmn2)
		.append(oppHplmn2)
		.append(oppVplmn2)
		.append(rateProdId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBorderRoamInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "ROAM_TYPE", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "TOLL_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "CONDITION_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "VPLMN2", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "OPP_HPLMN2", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "OPP_VPLMN2", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBorderRoamInfo.class, "RATE_PROD_ID", 6, Map.class));
}

}