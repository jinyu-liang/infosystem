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
@XmlType(propOrder={"outer_mail_group_id","mail_group_id","post_code","country_id","receiver_name","address1","address2","address3","address4","address5"})
public class MailGroupPost implements IComplexEntity{


	@XmlElement(name="outer_mail_group_id")
	private String outer_mail_group_id;

	@XmlElement(name="mail_group_id")
	private Long mail_group_id;

	@XmlElement(name="post_code")
	private Integer post_code;

	@XmlElement(name="country_id")
	private Integer country_id;

	@XmlElement(name="receiver_name")
	private String receiver_name;

	@XmlElement(name="address1")
	private String address1;

	@XmlElement(name="address2")
	private String address2;

	@XmlElement(name="address3")
	private String address3;

	@XmlElement(name="address4")
	private String address4;

	@XmlElement(name="address5")
	private String address5;

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

	public void setPost_code(Integer obj){
		this.post_code = obj;
	}

	public Integer getPost_code(){
		return post_code;
	}

	public void setCountry_id(Integer obj){
		this.country_id = obj;
	}

	public Integer getCountry_id(){
		return country_id;
	}

	public void setReceiver_name(String obj){
		this.receiver_name = obj;
	}

	public String getReceiver_name(){
		return receiver_name;
	}

	public void setAddress1(String obj){
		this.address1 = obj;
	}

	public String getAddress1(){
		return address1;
	}

	public void setAddress2(String obj){
		this.address2 = obj;
	}

	public String getAddress2(){
		return address2;
	}

	public void setAddress3(String obj){
		this.address3 = obj;
	}

	public String getAddress3(){
		return address3;
	}

	public void setAddress4(String obj){
		this.address4 = obj;
	}

	public String getAddress4(){
		return address4;
	}

	public void setAddress5(String obj){
		this.address5 = obj;
	}

	public String getAddress5(){
		return address5;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MailGroupPost rhs=(MailGroupPost)rhs0;
		if(!ObjectUtils.equals(outer_mail_group_id, rhs.outer_mail_group_id)) return false;
		if(!ObjectUtils.equals(mail_group_id, rhs.mail_group_id)) return false;
		if(!ObjectUtils.equals(post_code, rhs.post_code)) return false;
		if(!ObjectUtils.equals(country_id, rhs.country_id)) return false;
		if(!ObjectUtils.equals(receiver_name, rhs.receiver_name)) return false;
		if(!ObjectUtils.equals(address1, rhs.address1)) return false;
		if(!ObjectUtils.equals(address2, rhs.address2)) return false;
		if(!ObjectUtils.equals(address3, rhs.address3)) return false;
		if(!ObjectUtils.equals(address4, rhs.address4)) return false;
		if(!ObjectUtils.equals(address5, rhs.address5)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_mail_group_id)
		.append(mail_group_id)
		.append(post_code)
		.append(country_id)
		.append(receiver_name)
		.append(address1)
		.append(address2)
		.append(address3)
		.append(address4)
		.append(address5)
		.toHashCode();
	}


}