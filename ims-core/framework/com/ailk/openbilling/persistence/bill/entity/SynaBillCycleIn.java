package com.ailk.openbilling.persistence.bill.entity;

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
@XmlType(propOrder={"fatherAcctId","acctId","outDate","opId","custId","countyCode","regionCode","measureId","channelId","isNotify","outSoNbr"})
public class SynaBillCycleIn implements IComplexEntity{


	@XmlElement(name="fatherAcctId")
	private Long fatherAcctId;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="outDate")
	private Date outDate;

	@XmlElement(name="opId")
	private Long opId;

	@XmlElement(name="custId")
	private Long custId;

	@XmlElement(name="countyCode")
	private Integer countyCode;

	@XmlElement(name="regionCode")
	private Integer regionCode;

	@XmlElement(name="measureId")
	private Integer measureId;

	@XmlElement(name="channelId")
	private Integer channelId;

	@XmlElement(name="isNotify")
	private Short isNotify;

	@XmlElement(name="outSoNbr")
	private Long outSoNbr;

	public void setFatherAcctId(Long obj){
		this.fatherAcctId = obj;
	}

	public Long getFatherAcctId(){
		return fatherAcctId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setOutDate(Date obj){
		this.outDate = obj;
	}

	public Date getOutDate(){
		return outDate;
	}

	public void setOpId(Long obj){
		this.opId = obj;
	}

	public Long getOpId(){
		return opId;
	}

	public void setCustId(Long obj){
		this.custId = obj;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setChannelId(Integer obj){
		this.channelId = obj;
	}

	public Integer getChannelId(){
		return channelId;
	}

	public void setIsNotify(Short obj){
		this.isNotify = obj;
	}

	public Short getIsNotify(){
		return isNotify;
	}

	public void setOutSoNbr(Long obj){
		this.outSoNbr = obj;
	}

	public Long getOutSoNbr(){
		return outSoNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SynaBillCycleIn rhs=(SynaBillCycleIn)rhs0;
		if(!ObjectUtils.equals(fatherAcctId, rhs.fatherAcctId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(outDate, rhs.outDate)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(isNotify, rhs.isNotify)) return false;
		if(!ObjectUtils.equals(outSoNbr, rhs.outSoNbr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fatherAcctId)
		.append(acctId)
		.append(outDate)
		.append(opId)
		.append(custId)
		.append(countyCode)
		.append(regionCode)
		.append(measureId)
		.append(channelId)
		.append(isNotify)
		.append(outSoNbr)
		.toHashCode();
	}


}