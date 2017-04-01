package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sUser"})
public class Do_queryMainUserResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sUser")
	private SUser sUser;

	public void setSUser(SUser obj){
		this.sUser = obj;
	}

	public SUser getSUser(){
		return sUser;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryMainUserResponse rhs=(Do_queryMainUserResponse)rhs0;
		if(!ObjectUtils.equals(sUser, rhs.sUser)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUser)
		.toHashCode();
	}


}