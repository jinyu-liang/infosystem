package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"outer_group_id","group_id","group_type","contact_phone","member_phone","status"})
public class SQueryGroup4GUIReq implements IComplexEntity{


	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="group_type")
	private Short group_type;

	@XmlElement(name="contact_phone")
	private String contact_phone;

	@XmlElement(name="member_phone")
	private String member_phone;

	@XmlElement(name="status")
	private Short status;

	public void setOuter_group_id(String obj){
		this.outer_group_id = obj;
	}

	public String getOuter_group_id(){
		return outer_group_id;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
	}

	public void setGroup_type(Short obj){
		this.group_type = obj;
	}

	public Short getGroup_type(){
		return group_type;
	}

	public void setContact_phone(String obj){
		this.contact_phone = obj;
	}

	public String getContact_phone(){
		return contact_phone;
	}

	public void setMember_phone(String obj){
		this.member_phone = obj;
	}

	public String getMember_phone(){
		return member_phone;
	}

	public void setStatus(Short obj){
		this.status = obj;
	}

	public Short getStatus(){
		return status;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryGroup4GUIReq rhs=(SQueryGroup4GUIReq)rhs0;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(group_type, rhs.group_type)) return false;
		if(!ObjectUtils.equals(contact_phone, rhs.contact_phone)) return false;
		if(!ObjectUtils.equals(member_phone, rhs.member_phone)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_group_id)
		.append(group_id)
		.append(group_type)
		.append(contact_phone)
		.append(member_phone)
		.append(status)
		.toHashCode();
	}


}