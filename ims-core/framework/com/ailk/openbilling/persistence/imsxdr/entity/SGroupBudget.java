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
@XmlType(propOrder={"productId","budgetId","billType","effectObjectType","policyId","measureId","priority","budgetInfoDtl"})
@Sdl(module="MXdr")
public class SGroupBudget extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_BUDGET_ID = "BUDGET_ID";
	public final static String COL_BILL_TYPE = "BILL_TYPE";
	public final static String COL_EFFECT_OBJECT_TYPE = "EFFECT_OBJECT_TYPE";
	public final static String COL_POLICY_ID = "POLICY_ID";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_PRIORITY = "PRIORITY";
	public final static String COL_BUDGET_INFO_DTL = "BUDGET_INFO_DTL";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_BUDGET_ID = 1;
	public final static int IDX_BILL_TYPE = 2;
	public final static int IDX_EFFECT_OBJECT_TYPE = 3;
	public final static int IDX_POLICY_ID = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_PRIORITY = 6;
	public final static int IDX_BUDGET_INFO_DTL = 7;

	/**
	 * 
	 */
	@XmlElement(name="budgetInfoDtl")
	@Sdl
	private List<SBudgetInfoDtl> budgetInfoDtl;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="budgetId")
	@Sdl
	private int budgetId;

	/**
	 * 
	 */
	@XmlElement(name="billType")
	@Sdl
	private int billType;

	/**
	 * 
	 */
	@XmlElement(name="effectObjectType")
	@Sdl
	private int effectObjectType;

	/**
	 * 
	 */
	@XmlElement(name="policyId")
	@Sdl
	private int policyId;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="priority")
	@Sdl
	private int priority;

	public void setBudgetInfoDtl(List<SBudgetInfoDtl> obj){
		this.budgetInfoDtl = obj;
		onFieldSet(7, obj);
	}

	public List<SBudgetInfoDtl> getBudgetInfoDtl(){
		return budgetInfoDtl;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setBudgetId(int obj){
		this.budgetId = obj;
		onFieldSet(1, obj);
	}

	public int getBudgetId(){
		return budgetId;
	}

	public void setBillType(int obj){
		this.billType = obj;
		onFieldSet(2, obj);
	}

	public int getBillType(){
		return billType;
	}

	public void setEffectObjectType(int obj){
		this.effectObjectType = obj;
		onFieldSet(3, obj);
	}

	public int getEffectObjectType(){
		return effectObjectType;
	}

	public void setPolicyId(int obj){
		this.policyId = obj;
		onFieldSet(4, obj);
	}

	public int getPolicyId(){
		return policyId;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setPriority(int obj){
		this.priority = obj;
		onFieldSet(6, obj);
	}

	public int getPriority(){
		return priority;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGroupBudget(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGroupBudget(SGroupBudget arg0){
		copy(arg0);
	}

	public void copy(final SGroupBudget rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		productId = rhs.productId;
		budgetId = rhs.budgetId;
		billType = rhs.billType;
		effectObjectType = rhs.effectObjectType;
		policyId = rhs.policyId;
		measureId = rhs.measureId;
		priority = rhs.priority;
		budgetInfoDtl = rhs.budgetInfoDtl;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGroupBudget rhs=(SGroupBudget)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(budgetId, rhs.budgetId)) return false;
		if(!ObjectUtils.equals(billType, rhs.billType)) return false;
		if(!ObjectUtils.equals(effectObjectType, rhs.effectObjectType)) return false;
		if(!ObjectUtils.equals(policyId, rhs.policyId)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(budgetInfoDtl, rhs.budgetInfoDtl)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(budgetId)
		.append(billType)
		.append(effectObjectType)
		.append(policyId)
		.append(measureId)
		.append(priority)
		.append(budgetInfoDtl)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGroupBudget";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "BUDGET_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "BILL_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "EFFECT_OBJECT_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "POLICY_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "PRIORITY", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupBudget.class, "BUDGET_INFO_DTL", 7, List.class));
}

}