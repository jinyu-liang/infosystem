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
@XmlType(propOrder={"queryType"})
public class SQueryGraphQueryTypeList implements IComplexEntity{


	@XmlElement(name="queryType")
	private Short queryType;

	public void setQueryType(Short obj){
		this.queryType = obj;
	}

	public Short getQueryType(){
		return queryType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryGraphQueryTypeList rhs=(SQueryGraphQueryTypeList)rhs0;
		if(!ObjectUtils.equals(queryType, rhs.queryType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(queryType)
		.toHashCode();
	}


}