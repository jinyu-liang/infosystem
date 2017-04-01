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
@XmlType(propOrder={"devId","userId"})
public class SQueryAcctByPhoneReq implements IComplexEntity{


	@XmlElement(name="devId")
	private String devId;

	@XmlElement(name="userId")
	private Long userId;

	public void setDevId(String obj){
		this.devId = obj;
	}

	public String getDevId(){
		return devId;
	}

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryAcctByPhoneReq rhs=(SQueryAcctByPhoneReq)rhs0;
		if(!ObjectUtils.equals(devId, rhs.devId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(devId)
		.append(userId)
		.toHashCode();
	}


}