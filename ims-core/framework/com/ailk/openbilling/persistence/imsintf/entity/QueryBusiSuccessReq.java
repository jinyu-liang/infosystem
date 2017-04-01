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
@XmlType(propOrder={"so_nbr","so_mode"})
public class QueryBusiSuccessReq implements IComplexEntity{


	@XmlElement(name="so_nbr")
	private String so_nbr;

	@XmlElement(name="so_mode")
	private short so_mode;

	public void setSo_nbr(String obj){
		this.so_nbr = obj;
	}

	public String getSo_nbr(){
		return so_nbr;
	}

	public void setSo_mode(short obj){
		this.so_mode = obj;
	}

	public short getSo_mode(){
		return so_mode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryBusiSuccessReq rhs=(QueryBusiSuccessReq)rhs0;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(so_nbr)
		.append(so_mode)
		.toHashCode();
	}


}