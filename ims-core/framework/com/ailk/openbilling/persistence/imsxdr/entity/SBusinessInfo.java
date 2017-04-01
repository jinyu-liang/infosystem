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
@XmlType(propOrder={"sbusinessinteraction","listRewardInfo","adjustFreeResresult","listRechargeLog","assetBalance","calFeeConfirm"})
@Sdl(module="MXdr")
public class SBusinessInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SBUSINESSINTERACTION = "SBUSINESSINTERACTION";
	public final static String COL_LIST_REWARD_INFO = "LIST_REWARD_INFO";
	public final static String COL_ADJUST_FREE_RESRESULT = "ADJUST_FREE_RESRESULT";
	public final static String COL_LIST_RECHARGE_LOG = "LIST_RECHARGE_LOG";
	public final static String COL_ASSET_BALANCE = "ASSET_BALANCE";
	public final static String COL_CAL_FEE_CONFIRM = "CAL_FEE_CONFIRM";
	public final static int IDX_SBUSINESSINTERACTION = 0;
	public final static int IDX_LIST_REWARD_INFO = 1;
	public final static int IDX_ADJUST_FREE_RESRESULT = 2;
	public final static int IDX_LIST_RECHARGE_LOG = 3;
	public final static int IDX_ASSET_BALANCE = 4;
	public final static int IDX_CAL_FEE_CONFIRM = 5;

	/**
	 * 
	 */
	@XmlElement(name="sbusinessinteraction")
	@Sdl
	private SBusinessInteraction sbusinessinteraction;

	/**
	 * 
	 */
	@XmlElement(name="listRewardInfo")
	@Sdl
	private List<SRewardInfo> listRewardInfo;

	/**
	 * 
	 */
	@XmlElement(name="adjustFreeResresult")
	@Sdl
	private SAdjustFreeResResult adjustFreeResresult;

	/**
	 * 
	 */
	@XmlElement(name="listRechargeLog")
	@Sdl
	private List<SRechargeLog> listRechargeLog;

	/**
	 * 
	 */
	@XmlElement(name="assetBalance")
	@Sdl
	private SAssetBalance assetBalance;

	/**
	 * 
	 */
	@XmlElement(name="calFeeConfirm")
	@Sdl
	private SCalFeeConfirm calFeeConfirm;

	public void setSbusinessinteraction(SBusinessInteraction obj){
		this.sbusinessinteraction = obj;
		onFieldSet(0, obj);
	}

	public SBusinessInteraction getSbusinessinteraction(){
		return sbusinessinteraction;
	}

	public void setListRewardInfo(List<SRewardInfo> obj){
		this.listRewardInfo = obj;
		onFieldSet(1, obj);
	}

	public List<SRewardInfo> getListRewardInfo(){
		return listRewardInfo;
	}

	public void setAdjustFreeResresult(SAdjustFreeResResult obj){
		this.adjustFreeResresult = obj;
		onFieldSet(2, obj);
	}

	public SAdjustFreeResResult getAdjustFreeResresult(){
		return adjustFreeResresult;
	}

	public void setListRechargeLog(List<SRechargeLog> obj){
		this.listRechargeLog = obj;
		onFieldSet(3, obj);
	}

	public List<SRechargeLog> getListRechargeLog(){
		return listRechargeLog;
	}

	public void setAssetBalance(SAssetBalance obj){
		this.assetBalance = obj;
		onFieldSet(4, obj);
	}

	public SAssetBalance getAssetBalance(){
		return assetBalance;
	}

	public void setCalFeeConfirm(SCalFeeConfirm obj){
		this.calFeeConfirm = obj;
		onFieldSet(5, obj);
	}

	public SCalFeeConfirm getCalFeeConfirm(){
		return calFeeConfirm;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBusinessInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBusinessInfo(SBusinessInfo arg0){
		copy(arg0);
	}

	public void copy(final SBusinessInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		sbusinessinteraction = rhs.sbusinessinteraction;
		listRewardInfo = rhs.listRewardInfo;
		adjustFreeResresult = rhs.adjustFreeResresult;
		listRechargeLog = rhs.listRechargeLog;
		assetBalance = rhs.assetBalance;
		calFeeConfirm = rhs.calFeeConfirm;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBusinessInfo rhs=(SBusinessInfo)rhs0;
		if(!ObjectUtils.equals(sbusinessinteraction, rhs.sbusinessinteraction)) return false;
		if(!ObjectUtils.equals(listRewardInfo, rhs.listRewardInfo)) return false;
		if(!ObjectUtils.equals(adjustFreeResresult, rhs.adjustFreeResresult)) return false;
		if(!ObjectUtils.equals(listRechargeLog, rhs.listRechargeLog)) return false;
		if(!ObjectUtils.equals(assetBalance, rhs.assetBalance)) return false;
		if(!ObjectUtils.equals(calFeeConfirm, rhs.calFeeConfirm)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sbusinessinteraction)
		.append(listRewardInfo)
		.append(adjustFreeResresult)
		.append(listRechargeLog)
		.append(assetBalance)
		.append(calFeeConfirm)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBusinessInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "SBUSINESSINTERACTION", 0, SBusinessInteraction.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "LIST_REWARD_INFO", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "ADJUST_FREE_RESRESULT", 2, SAdjustFreeResResult.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "LIST_RECHARGE_LOG", 3, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "ASSET_BALANCE", 4, SAssetBalance.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInfo.class, "CAL_FEE_CONFIRM", 5, SCalFeeConfirm.class));
}

}