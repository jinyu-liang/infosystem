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
@XmlType(propOrder={"queryCDRType_Item"})
public class QueryCDRTypeList implements IComplexEntity{


	@XmlElement(name="queryCDRType_Item")
	private QueryCDRType[] queryCDRType_Item;

	public void setQueryCDRType_Item(QueryCDRType[] obj){
		this.queryCDRType_Item = obj;
	}

	public QueryCDRType[] getQueryCDRType_Item(){
		return queryCDRType_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryCDRTypeList rhs=(QueryCDRTypeList)rhs0;
		if(!ObjectUtils.equals(queryCDRType_Item, rhs.queryCDRType_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(queryCDRType_Item)
		.toHashCode();
	}


}