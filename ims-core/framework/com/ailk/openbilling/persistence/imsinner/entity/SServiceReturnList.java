package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"serviceReturnList"})
public class SServiceReturnList implements IComplexEntity{


	@XmlElement(name="serviceReturnList")
	private SServicesReturn[] serviceReturnList;

	public void setServiceReturnList(SServicesReturn[] obj){
		this.serviceReturnList = obj;
	}

	public SServicesReturn[] getServiceReturnList(){
		return serviceReturnList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SServiceReturnList rhs=(SServiceReturnList)rhs0;
		if(!ObjectUtils.equals(serviceReturnList, rhs.serviceReturnList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(serviceReturnList)
		.toHashCode();
	}


}