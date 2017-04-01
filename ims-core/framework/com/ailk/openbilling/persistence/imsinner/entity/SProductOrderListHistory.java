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
@XmlType(propOrder={"item"})
public class SProductOrderListHistory implements IComplexEntity{


	@XmlElement(name="item")
	private SProductOrderHistory[] item;

	public void setItem(SProductOrderHistory[] obj){
		this.item = obj;
	}

	public SProductOrderHistory[] getItem(){
		return item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrderListHistory rhs=(SProductOrderListHistory)rhs0;
		if(!ObjectUtils.equals(item, rhs.item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(item)
		.toHashCode();
	}


}