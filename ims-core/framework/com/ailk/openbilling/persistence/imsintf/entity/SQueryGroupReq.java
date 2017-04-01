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
@XmlType(propOrder={"outer_group_id","group_id","query_type","contact_phone","main_phone","status"})
public class SQueryGroupReq implements IComplexEntity{


	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="query_type")
	private Short query_type;

	@XmlElement(name="contact_phone")
	private String contact_phone;

	@XmlElement(name="main_phone")
	private String main_phone;

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

	public void setQuery_type(Short obj){
		this.query_type = obj;
	}

	public Short getQuery_type(){
		return query_type;
	}

	public void setContact_phone(String obj){
		this.contact_phone = obj;
	}

	public String getContact_phone(){
		return contact_phone;
	}

	public void setMain_phone(String obj){
		this.main_phone = obj;
	}

	public String getMain_phone(){
		return main_phone;
	}

	public void setStatus(Short obj){
		this.status = obj;
	}

	public Short getStatus(){
		return status;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryGroupReq rhs=(SQueryGroupReq)rhs0;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(query_type, rhs.query_type)) return false;
		if(!ObjectUtils.equals(contact_phone, rhs.contact_phone)) return false;
		if(!ObjectUtils.equals(main_phone, rhs.main_phone)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_group_id)
		.append(group_id)
		.append(query_type)
		.append(contact_phone)
		.append(main_phone)
		.append(status)
		.toHashCode();
	}


}