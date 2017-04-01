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
@XmlType(propOrder={"creditLimitListItem"})
public class CreditLimitList implements IComplexEntity{


	@XmlElement(name="creditLimitListItem")
	private CreditLimit[] creditLimitListItem;

	public void setCreditLimitListItem(CreditLimit[] obj){
		this.creditLimitListItem = obj;
	}

	public CreditLimit[] getCreditLimitListItem(){
		return creditLimitListItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CreditLimitList rhs=(CreditLimitList)rhs0;
		if(!ObjectUtils.equals(creditLimitListItem, rhs.creditLimitListItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(creditLimitListItem)
		.toHashCode();
	}


}