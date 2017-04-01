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
@XmlType(propOrder={"vcNumber","tradeType","tradeState","cardId","vcType","cardCharge","vcLocation","cardHplmn1","cardHplmn2"})
@Sdl(module="MXdr")
public class SVcInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_VC_NUMBER = "VC_NUMBER";
	public final static String COL_TRADE_TYPE = "TRADE_TYPE";
	public final static String COL_TRADE_STATE = "TRADE_STATE";
	public final static String COL_CARD_ID = "CARD_ID";
	public final static String COL_VC_TYPE = "VC_TYPE";
	public final static String COL_CARD_CHARGE = "CARD_CHARGE";
	public final static String COL_VC_LOCATION = "VC_LOCATION";
	public final static String COL_CARD_HPLMN1 = "CARD_HPLMN1";
	public final static String COL_CARD_HPLMN2 = "CARD_HPLMN2";
	public final static int IDX_VC_NUMBER = 0;
	public final static int IDX_TRADE_TYPE = 1;
	public final static int IDX_TRADE_STATE = 2;
	public final static int IDX_CARD_ID = 3;
	public final static int IDX_VC_TYPE = 4;
	public final static int IDX_CARD_CHARGE = 5;
	public final static int IDX_VC_LOCATION = 6;
	public final static int IDX_CARD_HPLMN1 = 7;
	public final static int IDX_CARD_HPLMN2 = 8;

	/**
	 * 
	 */
	@XmlElement(name="vcNumber")
	@Sdl
	private String vcNumber;

	/**
	 * 
	 */
	@XmlElement(name="tradeType")
	@Sdl
	private short tradeType;

	/**
	 * 
	 */
	@XmlElement(name="tradeState")
	@Sdl
	private short tradeState;

	/**
	 * 
	 */
	@XmlElement(name="cardId")
	@Sdl
	private String cardId;

	/**
	 * 
	 */
	@XmlElement(name="vcType")
	@Sdl
	private short vcType;

	/**
	 * 
	 */
	@XmlElement(name="cardCharge")
	@Sdl
	private long cardCharge;

	/**
	 * 
	 */
	@XmlElement(name="vcLocation")
	@Sdl
	private short vcLocation;

	/**
	 * 
	 */
	@XmlElement(name="cardHplmn1")
	@Sdl
	private String cardHplmn1;

	/**
	 * 
	 */
	@XmlElement(name="cardHplmn2")
	@Sdl
	private String cardHplmn2;

	public void setVcNumber(String obj){
		this.vcNumber = obj;
		onFieldSet(0, obj);
	}

	public String getVcNumber(){
		return vcNumber;
	}

	public void setTradeType(short obj){
		this.tradeType = obj;
		onFieldSet(1, obj);
	}

	public short getTradeType(){
		return tradeType;
	}

	public void setTradeState(short obj){
		this.tradeState = obj;
		onFieldSet(2, obj);
	}

	public short getTradeState(){
		return tradeState;
	}

	public void setCardId(String obj){
		this.cardId = obj;
		onFieldSet(3, obj);
	}

	public String getCardId(){
		return cardId;
	}

	public void setVcType(short obj){
		this.vcType = obj;
		onFieldSet(4, obj);
	}

	public short getVcType(){
		return vcType;
	}

	public void setCardCharge(long obj){
		this.cardCharge = obj;
		onFieldSet(5, obj);
	}

	public long getCardCharge(){
		return cardCharge;
	}

	public void setVcLocation(short obj){
		this.vcLocation = obj;
		onFieldSet(6, obj);
	}

	public short getVcLocation(){
		return vcLocation;
	}

	public void setCardHplmn1(String obj){
		this.cardHplmn1 = obj;
		onFieldSet(7, obj);
	}

	public String getCardHplmn1(){
		return cardHplmn1;
	}

	public void setCardHplmn2(String obj){
		this.cardHplmn2 = obj;
		onFieldSet(8, obj);
	}

	public String getCardHplmn2(){
		return cardHplmn2;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SVcInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SVcInfo(SVcInfo arg0){
		copy(arg0);
	}

	public void copy(final SVcInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		vcNumber = rhs.vcNumber;
		tradeType = rhs.tradeType;
		tradeState = rhs.tradeState;
		cardId = rhs.cardId;
		vcType = rhs.vcType;
		cardCharge = rhs.cardCharge;
		vcLocation = rhs.vcLocation;
		cardHplmn1 = rhs.cardHplmn1;
		cardHplmn2 = rhs.cardHplmn2;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SVcInfo rhs=(SVcInfo)rhs0;
		if(!ObjectUtils.equals(vcNumber, rhs.vcNumber)) return false;
		if(!ObjectUtils.equals(tradeType, rhs.tradeType)) return false;
		if(!ObjectUtils.equals(tradeState, rhs.tradeState)) return false;
		if(!ObjectUtils.equals(cardId, rhs.cardId)) return false;
		if(!ObjectUtils.equals(vcType, rhs.vcType)) return false;
		if(!ObjectUtils.equals(cardCharge, rhs.cardCharge)) return false;
		if(!ObjectUtils.equals(vcLocation, rhs.vcLocation)) return false;
		if(!ObjectUtils.equals(cardHplmn1, rhs.cardHplmn1)) return false;
		if(!ObjectUtils.equals(cardHplmn2, rhs.cardHplmn2)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(vcNumber)
		.append(tradeType)
		.append(tradeState)
		.append(cardId)
		.append(vcType)
		.append(cardCharge)
		.append(vcLocation)
		.append(cardHplmn1)
		.append(cardHplmn2)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SVcInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "VC_NUMBER", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "TRADE_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "TRADE_STATE", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "CARD_ID", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "VC_TYPE", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "CARD_CHARGE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "VC_LOCATION", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "CARD_HPLMN1", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SVcInfo.class, "CARD_HPLMN2", 8, String.class));
}

}