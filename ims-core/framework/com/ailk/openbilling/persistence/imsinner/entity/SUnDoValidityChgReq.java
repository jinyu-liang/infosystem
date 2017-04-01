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
@XmlType(propOrder={"unDealDate","acctId","unDealsoNbr","force_flag"})
public class SUnDoValidityChgReq implements IComplexEntity{


	@XmlElement(name="unDealsoNbr")
	private Long unDealsoNbr;

	@XmlElement(name="unDealDate")
	private Date unDealDate;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="force_flag")
	private Short force_flag;

	public void setUnDealsoNbr(Long obj){
		this.unDealsoNbr = obj;
	}

	public Long getUnDealsoNbr(){
		return unDealsoNbr;
	}

	public void setUnDealDate(Date obj){
		this.unDealDate = obj;
	}

	public Date getUnDealDate(){
		return unDealDate;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setForce_flag(Short obj){
		this.force_flag = obj;
	}

	public Short getForce_flag(){
		return force_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUnDoValidityChgReq rhs=(SUnDoValidityChgReq)rhs0;
		if(!ObjectUtils.equals(unDealsoNbr, rhs.unDealsoNbr)) return false;
		if(!ObjectUtils.equals(unDealDate, rhs.unDealDate)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(force_flag, rhs.force_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(unDealsoNbr)
		.append(unDealDate)
		.append(acctId)
		.append(force_flag)
		.toHashCode();
	}


}