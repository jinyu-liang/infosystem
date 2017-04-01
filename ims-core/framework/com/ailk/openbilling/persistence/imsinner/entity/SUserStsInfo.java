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
@XmlType(propOrder={"sts","stsName"})
public class SUserStsInfo implements IComplexEntity{


	@XmlElement(name="sts")
	private Integer sts;

	@XmlElement(name="stsName")
	private String stsName;

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setStsName(String obj){
		this.stsName = obj;
	}

	public String getStsName(){
		return stsName;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserStsInfo rhs=(SUserStsInfo)rhs0;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(stsName, rhs.stsName)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sts)
		.append(stsName)
		.toHashCode();
	}


}