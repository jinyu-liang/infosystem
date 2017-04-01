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
@XmlType(propOrder={"outer_acct_id","acct_id","phone_id","invoicing_no","begin_date","end_date","reguideFlag"})
public class SQueryReguideReq implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="invoicing_no")
	private String invoicing_no;

	@XmlElement(name="begin_date")
	private String begin_date;

	@XmlElement(name="end_date")
	private String end_date;

	@XmlElement(name="reguideFlag")
	private Integer reguideFlag;

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setInvoicing_no(String obj){
		this.invoicing_no = obj;
	}

	public String getInvoicing_no(){
		return invoicing_no;
	}

	public void setBegin_date(String obj){
		this.begin_date = obj;
	}

	public String getBegin_date(){
		return begin_date;
	}

	public void setEnd_date(String obj){
		this.end_date = obj;
	}

	public String getEnd_date(){
		return end_date;
	}

	public void setReguideFlag(Integer obj){
		this.reguideFlag = obj;
	}

	public Integer getReguideFlag(){
		return reguideFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryReguideReq rhs=(SQueryReguideReq)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(invoicing_no, rhs.invoicing_no)) return false;
		if(!ObjectUtils.equals(begin_date, rhs.begin_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(reguideFlag, rhs.reguideFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(invoicing_no)
		.append(begin_date)
		.append(end_date)
		.append(reguideFlag)
		.toHashCode();
	}


}