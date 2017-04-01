package com.ailk.openbilling.persistence.imssdl.entity;

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
@XmlType(propOrder={"busiServiceId","budgetMode","budgetType","budget","remainBudget","tempBudget","action","thresholdItem"})
@Sdl(module="MImsSyncDef")
public class SBudgetControl extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BUSI_SERVICE_ID = "BUSI_SERVICE_ID";
	public final static String COL_BUDGET_MODE = "BUDGET_MODE";
	public final static String COL_BUDGET_TYPE = "BUDGET_TYPE";
	public final static String COL_BUDGET = "BUDGET";
	public final static String COL_REMAIN_BUDGET = "REMAIN_BUDGET";
	public final static String COL_TEMP_BUDGET = "TEMP_BUDGET";
	public final static String COL_ACTION = "ACTION";
	public final static String COL_THRESHOLD_ITEM = "THRESHOLD_ITEM";
	public final static int IDX_BUSI_SERVICE_ID = 0;
	public final static int IDX_BUDGET_MODE = 1;
	public final static int IDX_BUDGET_TYPE = 2;
	public final static int IDX_BUDGET = 3;
	public final static int IDX_REMAIN_BUDGET = 4;
	public final static int IDX_TEMP_BUDGET = 5;
	public final static int IDX_ACTION = 6;
	public final static int IDX_THRESHOLD_ITEM = 7;

	/**
	 * 
	 */
	@XmlElement(name="thresholdItem")
	@Sdl
	private List<SThreshold> thresholdItem;

	/**
	 * 
	 */
	@XmlElement(name="busiServiceId")
	@Sdl
	private int busiServiceId;

	/**
	 * 
	 */
	@XmlElement(name="budgetMode")
	@Sdl
	private String budgetMode;

	/**
	 * 
	 */
	@XmlElement(name="budgetType")
	@Sdl
	private short budgetType;

	/**
	 * 
	 */
	@XmlElement(name="budget")
	@Sdl
	private long budget;

	/**
	 * 
	 */
	@XmlElement(name="remainBudget")
	@Sdl
	private long remainBudget;

	/**
	 * 
	 */
	@XmlElement(name="tempBudget")
	@Sdl
	private long tempBudget;

	/**
	 * 
	 */
	@XmlElement(name="action")
	@Sdl
	private short action;

	public void setThresholdItem(List<SThreshold> obj){
		this.thresholdItem = obj;
		onFieldSet(7, obj);
	}

	public List<SThreshold> getThresholdItem(){
		return thresholdItem;
	}

	public void setBusiServiceId(int obj){
		this.busiServiceId = obj;
		onFieldSet(0, obj);
	}

	public int getBusiServiceId(){
		return busiServiceId;
	}

	public void setBudgetMode(String obj){
		this.budgetMode = obj;
		onFieldSet(1, obj);
	}

	public String getBudgetMode(){
		return budgetMode;
	}

	public void setBudgetType(short obj){
		this.budgetType = obj;
		onFieldSet(2, obj);
	}

	public short getBudgetType(){
		return budgetType;
	}

	public void setBudget(long obj){
		this.budget = obj;
		onFieldSet(3, obj);
	}

	public long getBudget(){
		return budget;
	}

	public void setRemainBudget(long obj){
		this.remainBudget = obj;
		onFieldSet(4, obj);
	}

	public long getRemainBudget(){
		return remainBudget;
	}

	public void setTempBudget(long obj){
		this.tempBudget = obj;
		onFieldSet(5, obj);
	}

	public long getTempBudget(){
		return tempBudget;
	}

	public void setAction(short obj){
		this.action = obj;
		onFieldSet(6, obj);
	}

	public short getAction(){
		return action;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBudgetControl(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBudgetControl(SBudgetControl arg0){
		copy(arg0);
	}

	public void copy(final SBudgetControl rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		busiServiceId = rhs.busiServiceId;
		budgetMode = rhs.budgetMode;
		budgetType = rhs.budgetType;
		budget = rhs.budget;
		remainBudget = rhs.remainBudget;
		tempBudget = rhs.tempBudget;
		action = rhs.action;
		thresholdItem = rhs.thresholdItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBudgetControl rhs=(SBudgetControl)rhs0;
		if(!ObjectUtils.equals(busiServiceId, rhs.busiServiceId)) return false;
		if(!ObjectUtils.equals(budgetMode, rhs.budgetMode)) return false;
		if(!ObjectUtils.equals(budgetType, rhs.budgetType)) return false;
		if(!ObjectUtils.equals(budget, rhs.budget)) return false;
		if(!ObjectUtils.equals(remainBudget, rhs.remainBudget)) return false;
		if(!ObjectUtils.equals(tempBudget, rhs.tempBudget)) return false;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(thresholdItem, rhs.thresholdItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiServiceId)
		.append(budgetMode)
		.append(budgetType)
		.append(budget)
		.append(remainBudget)
		.append(tempBudget)
		.append(action)
		.append(thresholdItem)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SBudgetControl";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "BUSI_SERVICE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "BUDGET_MODE", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "BUDGET_TYPE", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "BUDGET", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "REMAIN_BUDGET", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "TEMP_BUDGET", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "ACTION", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBudgetControl.class, "THRESHOLD_ITEM", 7, List.class));
}

}