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
@XmlType(propOrder={"query_type"})
public class QueryType implements IComplexEntity{


	@XmlElement(name="query_type")
	private Short query_type;

	public void setQuery_type(Short obj){
		this.query_type = obj;
	}

	public Short getQuery_type(){
		return query_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryType rhs=(QueryType)rhs0;
		if(!ObjectUtils.equals(query_type, rhs.query_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(query_type)
		.toHashCode();
	}


}