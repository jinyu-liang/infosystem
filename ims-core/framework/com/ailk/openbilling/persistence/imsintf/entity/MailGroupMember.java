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
@XmlType(propOrder={"oper_type","outer_mail_group_id","mail_group_id","outer_cust_id","cust_id","outer_acct_id","acct_id","valid_date","expire_date"})
public class MailGroupMember implements IComplexEntity{


	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="outer_mail_group_id")
	private String outer_mail_group_id;

	@XmlElement(name="mail_group_id")
	private Long mail_group_id;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setOuter_mail_group_id(String obj){
		this.outer_mail_group_id = obj;
	}

	public String getOuter_mail_group_id(){
		return outer_mail_group_id;
	}

	public void setMail_group_id(Long obj){
		this.mail_group_id = obj;
	}

	public Long getMail_group_id(){
		return mail_group_id;
	}

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

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

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MailGroupMember rhs=(MailGroupMember)rhs0;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(outer_mail_group_id, rhs.outer_mail_group_id)) return false;
		if(!ObjectUtils.equals(mail_group_id, rhs.mail_group_id)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oper_type)
		.append(outer_mail_group_id)
		.append(mail_group_id)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}