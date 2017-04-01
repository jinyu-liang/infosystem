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
@XmlType(propOrder={"maxFreeResourceLog_item"})
public class MaxFreeResourceLogList implements IComplexEntity{


	@XmlElement(name="maxFreeResourceLog_item")
	private MaxFreeResourceLog[] maxFreeResourceLog_item;

	public void setMaxFreeResourceLog_item(MaxFreeResourceLog[] obj){
		this.maxFreeResourceLog_item = obj;
	}

	public MaxFreeResourceLog[] getMaxFreeResourceLog_item(){
		return maxFreeResourceLog_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MaxFreeResourceLogList rhs=(MaxFreeResourceLogList)rhs0;
		if(!ObjectUtils.equals(maxFreeResourceLog_item, rhs.maxFreeResourceLog_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(maxFreeResourceLog_item)
		.toHashCode();
	}


}