package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SBalanceList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resource_id","acct_id","extendDays","triggerEventType","reduceDays","capMaxFlag","nbalance","pbalance","sBalanceList","forceFlag","balanceType"})
public class SLifeCycleByAcctChgReq implements IComplexEntity{


	@XmlElement(name="sBalanceList")
	private SBalanceList sBalanceList;

	@XmlElement(name="resource_id")
	private Long resource_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="extendDays")
	private Integer extendDays;

	@XmlElement(name="triggerEventType")
	private Integer triggerEventType;

	@XmlElement(name="reduceDays")
	private Integer reduceDays;

	@XmlElement(name="capMaxFlag")
	private Long capMaxFlag;

	@XmlElement(name="nbalance")
	private Long nbalance;

	@XmlElement(name="pbalance")
	private Long pbalance;

	@XmlElement(name="forceFlag")
	private Short forceFlag;

	@XmlElement(name="balanceType")
	private Short balanceType;

	public void setSBalanceList(SBalanceList obj){
		this.sBalanceList = obj;
	}

	public SBalanceList getSBalanceList(){
		return sBalanceList;
	}

	public void setResource_id(Long obj){
		this.resource_id = obj;
	}

	public Long getResource_id(){
		return resource_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setExtendDays(Integer obj){
		this.extendDays = obj;
	}

	public Integer getExtendDays(){
		return extendDays;
	}

	public void setTriggerEventType(Integer obj){
		this.triggerEventType = obj;
	}

	public Integer getTriggerEventType(){
		return triggerEventType;
	}

	public void setReduceDays(Integer obj){
		this.reduceDays = obj;
	}

	public Integer getReduceDays(){
		return reduceDays;
	}

	public void setCapMaxFlag(Long obj){
		this.capMaxFlag = obj;
	}

	public Long getCapMaxFlag(){
		return capMaxFlag;
	}

	public void setNbalance(Long obj){
		this.nbalance = obj;
	}

	public Long getNbalance(){
		return nbalance;
	}

	public void setPbalance(Long obj){
		this.pbalance = obj;
	}

	public Long getPbalance(){
		return pbalance;
	}

	public void setForceFlag(Short obj){
		this.forceFlag = obj;
	}

	public Short getForceFlag(){
		return forceFlag;
	}

	public void setBalanceType(Short obj){
		this.balanceType = obj;
	}

	public Short getBalanceType(){
		return balanceType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLifeCycleByAcctChgReq rhs=(SLifeCycleByAcctChgReq)rhs0;
		if(!ObjectUtils.equals(sBalanceList, rhs.sBalanceList)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(extendDays, rhs.extendDays)) return false;
		if(!ObjectUtils.equals(triggerEventType, rhs.triggerEventType)) return false;
		if(!ObjectUtils.equals(reduceDays, rhs.reduceDays)) return false;
		if(!ObjectUtils.equals(capMaxFlag, rhs.capMaxFlag)) return false;
		if(!ObjectUtils.equals(nbalance, rhs.nbalance)) return false;
		if(!ObjectUtils.equals(pbalance, rhs.pbalance)) return false;
		if(!ObjectUtils.equals(forceFlag, rhs.forceFlag)) return false;
		if(!ObjectUtils.equals(balanceType, rhs.balanceType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalanceList)
		.append(resource_id)
		.append(acct_id)
		.append(extendDays)
		.append(triggerEventType)
		.append(reduceDays)
		.append(capMaxFlag)
		.append(nbalance)
		.append(pbalance)
		.append(forceFlag)
		.append(balanceType)
		.toHashCode();
	}


}