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
@XmlType(propOrder={"extendParamList_Item"})
public class ExtendParamList implements IComplexEntity{


	@XmlElement(name="extendParamList_Item")
	private ExtendParam[] extendParamList_Item;

	public void setExtendParamList_Item(ExtendParam[] obj){
		this.extendParamList_Item = obj;
	}

	public ExtendParam[] getExtendParamList_Item(){
		return extendParamList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ExtendParamList rhs=(ExtendParamList)rhs0;
		if(!ObjectUtils.equals(extendParamList_Item, rhs.extendParamList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(extendParamList_Item)
		.toHashCode();
	}


}