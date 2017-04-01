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
@XmlType(propOrder={"reguideInfoList_item"})
public class SPayReguideInfoList implements IComplexEntity{


	@XmlElement(name="reguideInfoList_item")
	private SPayReguideInfo[] reguideInfoList_item;

	public void setReguideInfoList_item(SPayReguideInfo[] obj){
		this.reguideInfoList_item = obj;
	}

	public SPayReguideInfo[] getReguideInfoList_item(){
		return reguideInfoList_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPayReguideInfoList rhs=(SPayReguideInfoList)rhs0;
		if(!ObjectUtils.equals(reguideInfoList_item, rhs.reguideInfoList_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideInfoList_item)
		.toHashCode();
	}


}