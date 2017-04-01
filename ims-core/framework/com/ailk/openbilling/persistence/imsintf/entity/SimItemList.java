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
@XmlType(propOrder={"list"})
public class SimItemList implements IComplexEntity{


	@XmlElement(name="list")
	private SimItem[] list;

	public void setList(SimItem[] obj){
		this.list = obj;
	}

	public SimItem[] getList(){
		return list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SimItemList rhs=(SimItemList)rhs0;
		if(!ObjectUtils.equals(list, rhs.list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(list)
		.toHashCode();
	}


}