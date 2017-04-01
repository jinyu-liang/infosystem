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
@XmlType(propOrder={"phone_id","freeResourceList"})
public class MaxFreeResource implements IComplexEntity{


	@XmlElement(name="freeResourceList")
	private FreeResourceList freeResourceList;

	@XmlElement(name="phone_id")
	private String phone_id;

	public void setFreeResourceList(FreeResourceList obj){
		this.freeResourceList = obj;
	}

	public FreeResourceList getFreeResourceList(){
		return freeResourceList;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MaxFreeResource rhs=(MaxFreeResource)rhs0;
		if(!ObjectUtils.equals(freeResourceList, rhs.freeResourceList)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceList)
		.append(phone_id)
		.toHashCode();
	}


}