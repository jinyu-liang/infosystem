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
@XmlType(propOrder={"acctReq"})
public class AcctQueryReqHolder extends CustQueryReqHolder implements IComplexEntity{


	@XmlElement(name="acctReq")
	private AcctQueryReq acctReq;

	public void setAcctReq(AcctQueryReq obj){
		this.acctReq = obj;
	}

	public AcctQueryReq getAcctReq(){
		return acctReq;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AcctQueryReqHolder rhs=(AcctQueryReqHolder)rhs0;
		if(!ObjectUtils.equals(acctReq, rhs.acctReq)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctReq)
		.toHashCode();
	}


}