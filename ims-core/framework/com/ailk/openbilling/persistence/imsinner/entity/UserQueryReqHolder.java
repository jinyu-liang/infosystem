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
@XmlType(propOrder={"userReq"})
public class UserQueryReqHolder extends AcctQueryReqHolder implements IComplexEntity{


	@XmlElement(name="userReq")
	private UserQueryReq userReq;

	public void setUserReq(UserQueryReq obj){
		this.userReq = obj;
	}

	public UserQueryReq getUserReq(){
		return userReq;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		UserQueryReqHolder rhs=(UserQueryReqHolder)rhs0;
		if(!ObjectUtils.equals(userReq, rhs.userReq)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userReq)
		.toHashCode();
	}


}