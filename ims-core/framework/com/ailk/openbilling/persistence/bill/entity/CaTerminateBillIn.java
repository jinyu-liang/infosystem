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
@XmlType(propOrder={"acctId","outDate","soNbr","resourceId","opId","channelId"})
public class CaTerminateBillIn implements IComplexEntity{


	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="outDate")
	private Date outDate;

	@XmlElement(name="soNbr")
	private Long soNbr;

	@XmlElement(name="resourceId")
	private Long resourceId;

	@XmlElement(name="opId")
	private Long opId;

	@XmlElement(name="channelId")
	private Integer channelId;

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

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setOpId(Long obj){
		this.opId = obj;
	}

	public Long getOpId(){
		return opId;
	}

	public void setChannelId(Integer obj){
		this.channelId = obj;
	}

	public Integer getChannelId(){
		return channelId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaTerminateBillIn rhs=(CaTerminateBillIn)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(outDate, rhs.outDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(outDate)
		.append(soNbr)
		.append(resourceId)
		.append(opId)
		.append(channelId)
		.toHashCode();
	}


}