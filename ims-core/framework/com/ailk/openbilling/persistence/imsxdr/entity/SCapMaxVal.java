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
@XmlType(propOrder={"ownerId","budgetAmount","validDateTime","expireDateTime","doneCode","acctId","ownerType","budgetId","measureId"})
@Sdl(module="MXdr")
public class SCapMaxVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_BUDGET_AMOUNT = "BUDGET_AMOUNT";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_BUDGET_ID = "BUDGET_ID";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_OWNER_ID = 0;
	public final static int IDX_BUDGET_AMOUNT = 1;
	public final static int IDX_VALID_DATE_TIME = 2;
	public final static int IDX_EXPIRE_DATE_TIME = 3;
	public final static int IDX_DONE_CODE = 4;
	public final static int IDX_ACCT_ID = 5;
	public final static int IDX_OWNER_TYPE = 6;
	public final static int IDX_BUDGET_ID = 7;
	public final static int IDX_MEASURE_ID = 8;

	/**
	 * 
	 */
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="budgetAmount")
	@Sdl
	private long budgetAmount;

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
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="ownerType")
	@Sdl
	private int ownerType;

	/**
	 * 
	 */
	@XmlElement(name="budgetId")
	@Sdl
	private int budgetId;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(0, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setBudgetAmount(long obj){
		this.budgetAmount = obj;
		onFieldSet(1, obj);
	}

	public long getBudgetAmount(){
		return budgetAmount;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(2, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(4, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(5, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setOwnerType(int obj){
		this.ownerType = obj;
		onFieldSet(6, obj);
	}

	public int getOwnerType(){
		return ownerType;
	}

	public void setBudgetId(int obj){
		this.budgetId = obj;
		onFieldSet(7, obj);
	}

	public int getBudgetId(){
		return budgetId;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(8, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCapMaxVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCapMaxVal(SCapMaxVal arg0){
		copy(arg0);
	}

	public void copy(final SCapMaxVal rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		ownerId = rhs.ownerId;
		budgetAmount = rhs.budgetAmount;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		doneCode = rhs.doneCode;
		acctId = rhs.acctId;
		ownerType = rhs.ownerType;
		budgetId = rhs.budgetId;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCapMaxVal rhs=(SCapMaxVal)rhs0;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(budgetAmount, rhs.budgetAmount)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(budgetId, rhs.budgetId)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(ownerId)
		.append(budgetAmount)
		.append(validDateTime)
		.append(expireDateTime)
		.append(doneCode)
		.append(acctId)
		.append(ownerType)
		.append(budgetId)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCapMaxVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "OWNER_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "BUDGET_AMOUNT", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "VALID_DATE_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "EXPIRE_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "DONE_CODE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "ACCT_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "OWNER_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "BUDGET_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCapMaxVal.class, "MEASURE_ID", 8, int.class));
}

}