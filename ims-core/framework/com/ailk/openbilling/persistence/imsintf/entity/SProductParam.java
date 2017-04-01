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
@XmlType(propOrder={"param_id","param_name","param_value","group_id","valid_date","expire_date"})
public class SProductParam implements IComplexEntity{


	@XmlElement(name="param_id")
	private Integer param_id;

	@XmlElement(name="param_name")
	private String param_name;

	@XmlElement(name="param_value")
	private String param_value;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setParam_id(Integer obj){
		this.param_id = obj;
	}

	public Integer getParam_id(){
		return param_id;
	}

	public void setParam_name(String obj){
		this.param_name = obj;
	}

	public String getParam_name(){
		return param_name;
	}

	public void setParam_value(String obj){
		this.param_value = obj;
	}

	public String getParam_value(){
		return param_value;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
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
		SProductParam rhs=(SProductParam)rhs0;
		if(!ObjectUtils.equals(param_id, rhs.param_id)) return false;
		if(!ObjectUtils.equals(param_name, rhs.param_name)) return false;
		if(!ObjectUtils.equals(param_value, rhs.param_value)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(param_id)
		.append(param_name)
		.append(param_value)
		.append(group_id)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}