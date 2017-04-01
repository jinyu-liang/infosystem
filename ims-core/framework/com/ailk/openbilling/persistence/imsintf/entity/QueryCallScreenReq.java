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
@XmlType(propOrder={"user_id","phone_id","outer_group_id","group_id","oneTimeFee"})
public class QueryCallScreenReq implements IComplexEntity{


	@XmlElement(name="oneTimeFee")
	private OneTimeFee oneTimeFee;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	public void setOneTimeFee(OneTimeFee obj){
		this.oneTimeFee = obj;
	}

	public OneTimeFee getOneTimeFee(){
		return oneTimeFee;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryCallScreenReq rhs=(QueryCallScreenReq)rhs0;
		if(!ObjectUtils.equals(oneTimeFee, rhs.oneTimeFee)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oneTimeFee)
		.append(user_id)
		.append(phone_id)
		.append(outer_group_id)
		.append(group_id)
		.toHashCode();
	}


}