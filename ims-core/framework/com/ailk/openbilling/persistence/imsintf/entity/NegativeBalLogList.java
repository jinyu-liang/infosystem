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
@XmlType(propOrder={"negativeBalLog_item"})
public class NegativeBalLogList implements IComplexEntity{


	@XmlElement(name="negativeBalLog_item")
	private NegativeBalanceLog[] negativeBalLog_item;

	public void setNegativeBalLog_item(NegativeBalanceLog[] obj){
		this.negativeBalLog_item = obj;
	}

	public NegativeBalanceLog[] getNegativeBalLog_item(){
		return negativeBalLog_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NegativeBalLogList rhs=(NegativeBalLogList)rhs0;
		if(!ObjectUtils.equals(negativeBalLog_item, rhs.negativeBalLog_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(negativeBalLog_item)
		.toHashCode();
	}


}