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
@XmlType(propOrder={"servId","acctId","assetId","changeItemId","doneCode","usedFee","overdraftFee","measureId","exchangeRate"})
@Sdl(module="MXdr")
public class SPrePayBillDtl extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_CHANGE_ITEM_ID = "CHANGE_ITEM_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_USED_FEE = "USED_FEE";
	public final static String COL_OVERDRAFT_FEE = "OVERDRAFT_FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_EXCHANGE_RATE = "EXCHANGE_RATE";
	public final static int IDX_SERV_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_ASSET_ID = 2;
	public final static int IDX_CHANGE_ITEM_ID = 3;
	public final static int IDX_DONE_CODE = 4;
	public final static int IDX_USED_FEE = 5;
	public final static int IDX_OVERDRAFT_FEE = 6;
	public final static int IDX_MEASURE_ID = 7;
	public final static int IDX_EXCHANGE_RATE = 8;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="assetId")
	@Sdl
	private long assetId;

	/**
	 * 
	 */
	@XmlElement(name="changeItemId")
	@Sdl
	private int changeItemId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="usedFee")
	@Sdl
	private long usedFee;

	/**
	 * 
	 */
	@XmlElement(name="overdraftFee")
	@Sdl
	private long overdraftFee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="exchangeRate")
	@Sdl
	private float exchangeRate;

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(0, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setAssetId(long obj){
		this.assetId = obj;
		onFieldSet(2, obj);
	}

	public long getAssetId(){
		return assetId;
	}

	public void setChangeItemId(int obj){
		this.changeItemId = obj;
		onFieldSet(3, obj);
	}

	public int getChangeItemId(){
		return changeItemId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(4, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setUsedFee(long obj){
		this.usedFee = obj;
		onFieldSet(5, obj);
	}

	public long getUsedFee(){
		return usedFee;
	}

	public void setOverdraftFee(long obj){
		this.overdraftFee = obj;
		onFieldSet(6, obj);
	}

	public long getOverdraftFee(){
		return overdraftFee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(7, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setExchangeRate(float obj){
		this.exchangeRate = obj;
		onFieldSet(8, obj);
	}

	public float getExchangeRate(){
		return exchangeRate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPrePayBillDtl(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPrePayBillDtl(SPrePayBillDtl arg0){
		copy(arg0);
	}

	public void copy(final SPrePayBillDtl rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		servId = rhs.servId;
		acctId = rhs.acctId;
		assetId = rhs.assetId;
		changeItemId = rhs.changeItemId;
		doneCode = rhs.doneCode;
		usedFee = rhs.usedFee;
		overdraftFee = rhs.overdraftFee;
		measureId = rhs.measureId;
		exchangeRate = rhs.exchangeRate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPrePayBillDtl rhs=(SPrePayBillDtl)rhs0;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(changeItemId, rhs.changeItemId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(usedFee, rhs.usedFee)) return false;
		if(!ObjectUtils.equals(overdraftFee, rhs.overdraftFee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(exchangeRate, rhs.exchangeRate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(servId)
		.append(acctId)
		.append(assetId)
		.append(changeItemId)
		.append(doneCode)
		.append(usedFee)
		.append(overdraftFee)
		.append(measureId)
		.append(exchangeRate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPrePayBillDtl";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "SERV_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "ASSET_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "CHANGE_ITEM_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "DONE_CODE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "USED_FEE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "OVERDRAFT_FEE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "MEASURE_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPrePayBillDtl.class, "EXCHANGE_RATE", 8, float.class));
}

}