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
@XmlType(propOrder={"outer_acct_id","acct_id","outer_mail_group_id","mail_group_id"})
public class AccountMail implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="outer_mail_group_id")
	private String outer_mail_group_id;

	@XmlElement(name="mail_group_id")
	private Long mail_group_id;

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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AccountMail rhs=(AccountMail)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(outer_mail_group_id, rhs.outer_mail_group_id)) return false;
		if(!ObjectUtils.equals(mail_group_id, rhs.mail_group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(outer_mail_group_id)
		.append(mail_group_id)
		.toHashCode();
	}


}