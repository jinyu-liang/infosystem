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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"objectId","objectType","acctId","validDate","expireDate"})
public class SQry3BeanHisRet extends BaseResponse implements IComplexEntity{


	@XmlElement(name="objectId")
	private Long objectId;

	@XmlElement(name="objectType")
	private Integer objectType;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="validDate")
	private String validDate;

	@XmlElement(name="expireDate")
	private String expireDate;

	public void setObjectId(Long obj){
		this.objectId = obj;
	}

	public Long getObjectId(){
		return objectId;
	}

	public void setObjectType(Integer obj){
		this.objectType = obj;
	}

	public Integer getObjectType(){
		return objectType;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setValidDate(String obj){
		this.validDate = obj;
	}

	public String getValidDate(){
		return validDate;
	}

	public void setExpireDate(String obj){
		this.expireDate = obj;
	}

	public String getExpireDate(){
		return expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQry3BeanHisRet rhs=(SQry3BeanHisRet)rhs0;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(objectId)
		.append(objectType)
		.append(acctId)
		.append(validDate)
		.append(expireDate)
		.toHashCode();
	}


}