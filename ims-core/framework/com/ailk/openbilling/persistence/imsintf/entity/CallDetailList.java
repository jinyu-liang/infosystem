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
@XmlType(propOrder={"callDetailItem"})
public class CallDetailList implements IComplexEntity{


	@XmlElement(name="callDetailItem")
	private CallDetail[] callDetailItem;

	public void setCallDetailItem(CallDetail[] obj){
		this.callDetailItem = obj;
	}

	public CallDetail[] getCallDetailItem(){
		return callDetailItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CallDetailList rhs=(CallDetailList)rhs0;
		if(!ObjectUtils.equals(callDetailItem, rhs.callDetailItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(callDetailItem)
		.toHashCode();
	}


}