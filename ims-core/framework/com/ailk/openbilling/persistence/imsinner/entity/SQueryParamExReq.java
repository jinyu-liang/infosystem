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
@XmlType(propOrder={"busi_flag"})
public class SQueryParamExReq implements IComplexEntity{


	@XmlElement(name="busi_flag")
	private Integer busi_flag;

	public void setBusi_flag(Integer obj){
		this.busi_flag = obj;
	}

	public Integer getBusi_flag(){
		return busi_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryParamExReq rhs=(SQueryParamExReq)rhs0;
		if(!ObjectUtils.equals(busi_flag, rhs.busi_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busi_flag)
		.toHashCode();
	}


}