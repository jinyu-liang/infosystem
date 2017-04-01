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
@XmlType(propOrder={"sModSubscriber"})
public class SModSubscriberList implements IComplexEntity{


	@XmlElement(name="sModSubscriber")
	private SModSubscriber[] sModSubscriber;

	public void setSModSubscriber(SModSubscriber[] obj){
		this.sModSubscriber = obj;
	}

	public SModSubscriber[] getSModSubscriber(){
		return sModSubscriber;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SModSubscriberList rhs=(SModSubscriberList)rhs0;
		if(!ObjectUtils.equals(sModSubscriber, rhs.sModSubscriber)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sModSubscriber)
		.toHashCode();
	}


}