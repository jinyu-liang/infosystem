package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourcr_id","extendDays","expireDate","validDate","sts","activeValidDate","activeExpireDate","suspendExpireDate","disableExprieDate"})
public class SLifeCycleChg implements IComplexEntity{


	@XmlElement(name="resourcr_id")
	private Long resourcr_id;

	@XmlElement(name="extendDays")
	private Integer extendDays;

	@XmlElement(name="expireDate")
	private Date expireDate;

	@XmlElement(name="validDate")
	private Date validDate;

	@XmlElement(name="sts")
	private Integer sts;

	@XmlElement(name="activeValidDate")
	private Date activeValidDate;

	@XmlElement(name="activeExpireDate")
	private Date activeExpireDate;

	@XmlElement(name="suspendExpireDate")
	private Date suspendExpireDate;

	@XmlElement(name="disableExprieDate")
	private Date disableExprieDate;

	public void setResourcr_id(Long obj){
		this.resourcr_id = obj;
	}

	public Long getResourcr_id(){
		return resourcr_id;
	}

	public void setExtendDays(Integer obj){
		this.extendDays = obj;
	}

	public Integer getExtendDays(){
		return extendDays;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setActiveValidDate(Date obj){
		this.activeValidDate = obj;
	}

	public Date getActiveValidDate(){
		return activeValidDate;
	}

	public void setActiveExpireDate(Date obj){
		this.activeExpireDate = obj;
	}

	public Date getActiveExpireDate(){
		return activeExpireDate;
	}

	public void setSuspendExpireDate(Date obj){
		this.suspendExpireDate = obj;
	}

	public Date getSuspendExpireDate(){
		return suspendExpireDate;
	}

	public void setDisableExprieDate(Date obj){
		this.disableExprieDate = obj;
	}

	public Date getDisableExprieDate(){
		return disableExprieDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLifeCycleChg rhs=(SLifeCycleChg)rhs0;
		if(!ObjectUtils.equals(resourcr_id, rhs.resourcr_id)) return false;
		if(!ObjectUtils.equals(extendDays, rhs.extendDays)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(activeValidDate, rhs.activeValidDate)) return false;
		if(!ObjectUtils.equals(activeExpireDate, rhs.activeExpireDate)) return false;
		if(!ObjectUtils.equals(suspendExpireDate, rhs.suspendExpireDate)) return false;
		if(!ObjectUtils.equals(disableExprieDate, rhs.disableExprieDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourcr_id)
		.append(extendDays)
		.append(expireDate)
		.append(validDate)
		.append(sts)
		.append(activeValidDate)
		.append(activeExpireDate)
		.append(suspendExpireDate)
		.append(disableExprieDate)
		.toHashCode();
	}


}