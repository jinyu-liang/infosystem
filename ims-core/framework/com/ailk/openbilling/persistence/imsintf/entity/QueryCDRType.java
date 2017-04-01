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
@XmlType(propOrder={"query_cdr_type"})
public class QueryCDRType implements IComplexEntity{


	@XmlElement(name="query_cdr_type")
	private Short query_cdr_type;

	public void setQuery_cdr_type(Short obj){
		this.query_cdr_type = obj;
	}

	public Short getQuery_cdr_type(){
		return query_cdr_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryCDRType rhs=(QueryCDRType)rhs0;
		if(!ObjectUtils.equals(query_cdr_type, rhs.query_cdr_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(query_cdr_type)
		.toHashCode();
	}


}