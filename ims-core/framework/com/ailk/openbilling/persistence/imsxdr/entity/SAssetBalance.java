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
@XmlType(propOrder={"balanceInfoList","creditInfoList"})
@Sdl(module="MXdr")
public class SAssetBalance extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BALANCE_INFO_LIST = "BALANCE_INFO_LIST";
	public final static String COL_CREDIT_INFO_LIST = "CREDIT_INFO_LIST";
	public final static int IDX_BALANCE_INFO_LIST = 0;
	public final static int IDX_CREDIT_INFO_LIST = 1;

	/**
	 * 
	 */
	@XmlElement(name="balanceInfoList")
	@Sdl
	private List<SBalanceInfo> balanceInfoList;

	/**
	 * 
	 */
	@XmlElement(name="creditInfoList")
	@Sdl
	private List<SCreditInfo> creditInfoList;

	public void setBalanceInfoList(List<SBalanceInfo> obj){
		this.balanceInfoList = obj;
		onFieldSet(0, obj);
	}

	public List<SBalanceInfo> getBalanceInfoList(){
		return balanceInfoList;
	}

	public void setCreditInfoList(List<SCreditInfo> obj){
		this.creditInfoList = obj;
		onFieldSet(1, obj);
	}

	public List<SCreditInfo> getCreditInfoList(){
		return creditInfoList;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetBalance(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAssetBalance(SAssetBalance arg0){
		copy(arg0);
	}

	public void copy(final SAssetBalance rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		balanceInfoList = rhs.balanceInfoList;
		creditInfoList = rhs.creditInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAssetBalance rhs=(SAssetBalance)rhs0;
		if(!ObjectUtils.equals(balanceInfoList, rhs.balanceInfoList)) return false;
		if(!ObjectUtils.equals(creditInfoList, rhs.creditInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balanceInfoList)
		.append(creditInfoList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAssetBalance";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetBalance.class, "BALANCE_INFO_LIST", 0, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetBalance.class, "CREDIT_INFO_LIST", 1, List.class));
}

}