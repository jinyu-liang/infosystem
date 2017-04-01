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
@XmlType(propOrder={"iRResponse_Item"})
public class IRResponseList implements IComplexEntity{


	@XmlElement(name="iRResponse_Item")
	private IRResponse[] iRResponse_Item;

	public void setIRResponse_Item(IRResponse[] obj){
		this.iRResponse_Item = obj;
	}

	public IRResponse[] getIRResponse_Item(){
		return iRResponse_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IRResponseList rhs=(IRResponseList)rhs0;
		if(!ObjectUtils.equals(iRResponse_Item, rhs.iRResponse_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(iRResponse_Item)
		.toHashCode();
	}


}