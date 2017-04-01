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
@XmlType(propOrder={"param_name","param_value","valid_date","expire_date"})
public class ExtendParam implements IComplexEntity{


	@XmlElement(name="param_name")
	private String param_name;

	@XmlElement(name="param_value")
	private String param_value;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

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
		ExtendParam rhs=(ExtendParam)rhs0;
		if(!ObjectUtils.equals(param_name, rhs.param_name)) return false;
		if(!ObjectUtils.equals(param_value, rhs.param_value)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(param_name)
		.append(param_value)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}