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
@XmlType(propOrder={"sAddSubscriber"})
public class SAddSubscriberList implements IComplexEntity{


	@XmlElement(name="sAddSubscriber")
	private SAddSubscriber[] sAddSubscriber;

	public void setSAddSubscriber(SAddSubscriber[] obj){
		this.sAddSubscriber = obj;
	}

	public SAddSubscriber[] getSAddSubscriber(){
		return sAddSubscriber;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAddSubscriberList rhs=(SAddSubscriberList)rhs0;
		if(!ObjectUtils.equals(sAddSubscriber, rhs.sAddSubscriber)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sAddSubscriber)
		.toHashCode();
	}


}