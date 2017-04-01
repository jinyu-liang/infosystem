package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"out_acct_id","acctId","phoneId","beginDate","endDate"})
public class SQueryCapMaxReq implements IComplexEntity{


	@XmlElement(name="out_acct_id")
	private String out_acct_id;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="beginDate")
	private String beginDate;

	@XmlElement(name="endDate")
	private String endDate;

	public void setOut_acct_id(String obj){
		this.out_acct_id = obj;
	}

	public String getOut_acct_id(){
		return out_acct_id;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setBeginDate(String obj){
		this.beginDate = obj;
	}

	public String getBeginDate(){
		return beginDate;
	}

	public void setEndDate(String obj){
		this.endDate = obj;
	}

	public String getEndDate(){
		return endDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryCapMaxReq rhs=(SQueryCapMaxReq)rhs0;
		if(!ObjectUtils.equals(out_acct_id, rhs.out_acct_id)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(beginDate, rhs.beginDate)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(out_acct_id)
		.append(acctId)
		.append(phoneId)
		.append(beginDate)
		.append(endDate)
		.toHashCode();
	}


}