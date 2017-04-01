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
@XmlType(propOrder={"specialNumberOperList"})
public class SpecialNumberOperList implements IComplexEntity{


	@XmlElement(name="specialNumberOperList")
	private SpecialNumberOper[] specialNumberOperList;

	public void setSpecialNumberOperList(SpecialNumberOper[] obj){
		this.specialNumberOperList = obj;
	}

	public SpecialNumberOper[] getSpecialNumberOperList(){
		return specialNumberOperList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SpecialNumberOperList rhs=(SpecialNumberOperList)rhs0;
		if(!ObjectUtils.equals(specialNumberOperList, rhs.specialNumberOperList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(specialNumberOperList)
		.toHashCode();
	}


}