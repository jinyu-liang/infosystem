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
@XmlType(propOrder={"so_nbr"})
public class SCancelTopUpReq implements IComplexEntity{


	@XmlElement(name="so_nbr")
	private Long so_nbr;

	public void setSo_nbr(Long obj){
		this.so_nbr = obj;
	}

	public Long getSo_nbr(){
		return so_nbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCancelTopUpReq rhs=(SCancelTopUpReq)rhs0;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(so_nbr)
		.toHashCode();
	}


}