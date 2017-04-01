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
@XmlType(propOrder={"phoneId","userId"})
public class SQueryServicesReq implements IComplexEntity{


	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="userId")
	private Long userId;

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryServicesReq rhs=(SQueryServicesReq)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.append(userId)
		.toHashCode();
	}


}