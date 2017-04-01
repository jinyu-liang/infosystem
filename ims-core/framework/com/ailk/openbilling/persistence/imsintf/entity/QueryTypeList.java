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
@XmlType(propOrder={"queryType_Item"})
public class QueryTypeList implements IComplexEntity{


	@XmlElement(name="queryType_Item")
	private QueryType[] queryType_Item;

	public void setQueryType_Item(QueryType[] obj){
		this.queryType_Item = obj;
	}

	public QueryType[] getQueryType_Item(){
		return queryType_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryTypeList rhs=(QueryTypeList)rhs0;
		if(!ObjectUtils.equals(queryType_Item, rhs.queryType_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(queryType_Item)
		.toHashCode();
	}


}