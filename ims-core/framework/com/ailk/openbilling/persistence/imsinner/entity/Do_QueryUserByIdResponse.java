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
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"user"})
public class Do_QueryUserByIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="user")
	private CmResource user;

	public void setUser(CmResource obj){
		this.user = obj;
	}

	public CmResource getUser(){
		return user;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryUserByIdResponse rhs=(Do_QueryUserByIdResponse)rhs0;
		if(!ObjectUtils.equals(user, rhs.user)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user)
		.toHashCode();
	}


}