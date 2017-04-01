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
@XmlType(propOrder={"object_id","object_type","valid_date"})
public class SQuerySplitRelListReq implements IComplexEntity{


	@XmlElement(name="object_id")
	private Long object_id;

	@XmlElement(name="object_type")
	private Long object_type;

	@XmlElement(name="valid_date")
	private String valid_date;

	public void setObject_id(Long obj){
		this.object_id = obj;
	}

	public Long getObject_id(){
		return object_id;
	}

	public void setObject_type(Long obj){
		this.object_type = obj;
	}

	public Long getObject_type(){
		return object_type;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQuerySplitRelListReq rhs=(SQuerySplitRelListReq)rhs0;
		if(!ObjectUtils.equals(object_id, rhs.object_id)) return false;
		if(!ObjectUtils.equals(object_type, rhs.object_type)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(object_id)
		.append(object_type)
		.append(valid_date)
		.toHashCode();
	}


}