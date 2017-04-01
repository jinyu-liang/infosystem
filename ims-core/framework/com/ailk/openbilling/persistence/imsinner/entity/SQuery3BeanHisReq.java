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
@XmlType(propOrder={"objectId","objecctType","acctId","beginDate","endDate"})
public class SQuery3BeanHisReq implements IComplexEntity{


	@XmlElement(name="objectId")
	private Long objectId;

	@XmlElement(name="objecctType")
	private Integer objecctType;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="beginDate")
	private String beginDate;

	@XmlElement(name="endDate")
	private String endDate;

	public void setObjectId(Long obj){
		this.objectId = obj;
	}

	public Long getObjectId(){
		return objectId;
	}

	public void setObjecctType(Integer obj){
		this.objecctType = obj;
	}

	public Integer getObjecctType(){
		return objecctType;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setBeginDate(String obj){
		this.beginDate = obj;
	}

	public String getBeginDate(){
		return beginDate;
	}

	public void setEndDate(String obj){
		this.endDate = obj;
	}

	public String getEndDate(){
		return endDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQuery3BeanHisReq rhs=(SQuery3BeanHisReq)rhs0;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objecctType, rhs.objecctType)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(beginDate, rhs.beginDate)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(objectId)
		.append(objecctType)
		.append(acctId)
		.append(beginDate)
		.append(endDate)
		.toHashCode();
	}


}