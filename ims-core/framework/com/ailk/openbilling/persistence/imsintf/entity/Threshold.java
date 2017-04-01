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
@XmlType(propOrder={"threshold","notify_type","notify_addr","notify_phone_id"})
public class Threshold implements IComplexEntity{


	@XmlElement(name="threshold")
	private Double threshold;

	@XmlElement(name="notify_type")
	private Short notify_type;

	@XmlElement(name="notify_addr")
	private String notify_addr;

	@XmlElement(name="notify_phone_id")
	private String notify_phone_id;

	public void setThreshold(Double obj){
		this.threshold = obj;
	}

	public Double getThreshold(){
		return threshold;
	}

	public void setNotify_type(Short obj){
		this.notify_type = obj;
	}

	public Short getNotify_type(){
		return notify_type;
	}

	public void setNotify_addr(String obj){
		this.notify_addr = obj;
	}

	public String getNotify_addr(){
		return notify_addr;
	}

	public void setNotify_phone_id(String obj){
		this.notify_phone_id = obj;
	}

	public String getNotify_phone_id(){
		return notify_phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Threshold rhs=(Threshold)rhs0;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(notify_type, rhs.notify_type)) return false;
		if(!ObjectUtils.equals(notify_addr, rhs.notify_addr)) return false;
		if(!ObjectUtils.equals(notify_phone_id, rhs.notify_phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(threshold)
		.append(notify_type)
		.append(notify_addr)
		.append(notify_phone_id)
		.toHashCode();
	}


}