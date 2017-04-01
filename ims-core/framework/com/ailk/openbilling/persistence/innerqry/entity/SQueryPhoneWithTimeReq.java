package com.ailk.openbilling.persistence.innerqry.entity;

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
@XmlType(propOrder={"resourceId","acctId","queryTime"})
public class SQueryPhoneWithTimeReq implements IComplexEntity{


	@XmlElement(name="resourceId")
	private Long resourceId;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="queryTime")
	private String queryTime;

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setQueryTime(String obj){
		this.queryTime = obj;
	}

	public String getQueryTime(){
		return queryTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryPhoneWithTimeReq rhs=(SQueryPhoneWithTimeReq)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(queryTime, rhs.queryTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(acctId)
		.append(queryTime)
		.toHashCode();
	}


}