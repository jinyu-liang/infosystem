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
@XmlType(propOrder={"firstActivation","operInfo","rechargeInfo","queryBalance","businessInfo","userInfo","queryRechargeLog","adjustFreeres","adjustAsset"})
@Sdl(module="MXdr")
public class SManagerInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_FIRST_ACTIVATION = "FIRST_ACTIVATION";
	public final static String COL_OPER_INFO = "OPER_INFO";
	public final static String COL_RECHARGE_INFO = "RECHARGE_INFO";
	public final static String COL_QUERY_BALANCE = "QUERY_BALANCE";
	public final static String COL_BUSINESS_INFO = "BUSINESS_INFO";
	public final static String COL_USER_INFO = "USER_INFO";
	public final static String COL_QUERY_RECHARGE_LOG = "QUERY_RECHARGE_LOG";
	public final static String COL_ADJUST_FREERES = "ADJUST_FREERES";
	public final static String COL_ADJUST_ASSET = "ADJUST_ASSET";
	public final static int IDX_FIRST_ACTIVATION = 0;
	public final static int IDX_OPER_INFO = 1;
	public final static int IDX_RECHARGE_INFO = 2;
	public final static int IDX_QUERY_BALANCE = 3;
	public final static int IDX_BUSINESS_INFO = 4;
	public final static int IDX_USER_INFO = 5;
	public final static int IDX_QUERY_RECHARGE_LOG = 6;
	public final static int IDX_ADJUST_FREERES = 7;
	public final static int IDX_ADJUST_ASSET = 8;

	/**
	 * 
	 */
	@XmlElement(name="firstActivation")
	@Sdl
	private SFirstAct firstActivation;

	/**
	 * 
	 */
	@XmlElement(name="operInfo")
	@Sdl
	private SOperInfo operInfo;

	/**
	 * 
	 */
	@XmlElement(name="rechargeInfo")
	@Sdl
	private SRecharge rechargeInfo;

	/**
	 * 
	 */
	@XmlElement(name="queryBalance")
	@Sdl
	private SQryBalance queryBalance;

	/**
	 * 
	 */
	@XmlElement(name="businessInfo")
	@Sdl
	private SBusinessInfo businessInfo;

	/**
	 * 
	 */
	@XmlElement(name="userInfo")
	@Sdl
	private SUserInfo userInfo;

	/**
	 * 
	 */
	@XmlElement(name="queryRechargeLog")
	@Sdl
	private SQueryRechargeLog queryRechargeLog;

	/**
	 * 
	 */
	@XmlElement(name="adjustFreeres")
	@Sdl
	private SAdjustFreeRes adjustFreeres;

	/**
	 * 
	 */
	@XmlElement(name="adjustAsset")
	@Sdl
	private SAdjustAsset adjustAsset;

	public void setFirstActivation(SFirstAct obj){
		this.firstActivation = obj;
		onFieldSet(0, obj);
	}

	public SFirstAct getFirstActivation(){
		return firstActivation;
	}

	public void setOperInfo(SOperInfo obj){
		this.operInfo = obj;
		onFieldSet(1, obj);
	}

	public SOperInfo getOperInfo(){
		return operInfo;
	}

	public void setRechargeInfo(SRecharge obj){
		this.rechargeInfo = obj;
		onFieldSet(2, obj);
	}

	public SRecharge getRechargeInfo(){
		return rechargeInfo;
	}

	public void setQueryBalance(SQryBalance obj){
		this.queryBalance = obj;
		onFieldSet(3, obj);
	}

	public SQryBalance getQueryBalance(){
		return queryBalance;
	}

	public void setBusinessInfo(SBusinessInfo obj){
		this.businessInfo = obj;
		onFieldSet(4, obj);
	}

	public SBusinessInfo getBusinessInfo(){
		return businessInfo;
	}

	public void setUserInfo(SUserInfo obj){
		this.userInfo = obj;
		onFieldSet(5, obj);
	}

	public SUserInfo getUserInfo(){
		return userInfo;
	}

	public void setQueryRechargeLog(SQueryRechargeLog obj){
		this.queryRechargeLog = obj;
		onFieldSet(6, obj);
	}

	public SQueryRechargeLog getQueryRechargeLog(){
		return queryRechargeLog;
	}

	public void setAdjustFreeres(SAdjustFreeRes obj){
		this.adjustFreeres = obj;
		onFieldSet(7, obj);
	}

	public SAdjustFreeRes getAdjustFreeres(){
		return adjustFreeres;
	}

	public void setAdjustAsset(SAdjustAsset obj){
		this.adjustAsset = obj;
		onFieldSet(8, obj);
	}

	public SAdjustAsset getAdjustAsset(){
		return adjustAsset;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SManagerInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SManagerInfo(SManagerInfo arg0){
		copy(arg0);
	}

	public void copy(final SManagerInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		firstActivation = rhs.firstActivation;
		operInfo = rhs.operInfo;
		rechargeInfo = rhs.rechargeInfo;
		queryBalance = rhs.queryBalance;
		businessInfo = rhs.businessInfo;
		userInfo = rhs.userInfo;
		queryRechargeLog = rhs.queryRechargeLog;
		adjustFreeres = rhs.adjustFreeres;
		adjustAsset = rhs.adjustAsset;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SManagerInfo rhs=(SManagerInfo)rhs0;
		if(!ObjectUtils.equals(firstActivation, rhs.firstActivation)) return false;
		if(!ObjectUtils.equals(operInfo, rhs.operInfo)) return false;
		if(!ObjectUtils.equals(rechargeInfo, rhs.rechargeInfo)) return false;
		if(!ObjectUtils.equals(queryBalance, rhs.queryBalance)) return false;
		if(!ObjectUtils.equals(businessInfo, rhs.businessInfo)) return false;
		if(!ObjectUtils.equals(userInfo, rhs.userInfo)) return false;
		if(!ObjectUtils.equals(queryRechargeLog, rhs.queryRechargeLog)) return false;
		if(!ObjectUtils.equals(adjustFreeres, rhs.adjustFreeres)) return false;
		if(!ObjectUtils.equals(adjustAsset, rhs.adjustAsset)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(firstActivation)
		.append(operInfo)
		.append(rechargeInfo)
		.append(queryBalance)
		.append(businessInfo)
		.append(userInfo)
		.append(queryRechargeLog)
		.append(adjustFreeres)
		.append(adjustAsset)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SManagerInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "FIRST_ACTIVATION", 0, SFirstAct.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "OPER_INFO", 1, SOperInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "RECHARGE_INFO", 2, SRecharge.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "QUERY_BALANCE", 3, SQryBalance.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "BUSINESS_INFO", 4, SBusinessInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "USER_INFO", 5, SUserInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "QUERY_RECHARGE_LOG", 6, SQueryRechargeLog.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "ADJUST_FREERES", 7, SAdjustFreeRes.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SManagerInfo.class, "ADJUST_ASSET", 8, SAdjustAsset.class));
}

}