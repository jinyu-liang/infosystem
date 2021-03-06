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
@XmlType(propOrder={"custReq"})
public class CustQueryReqHolder extends BaseQueryReqHolder implements IComplexEntity{


	@XmlElement(name="custReq")
	private CustQueryReq custReq;

	public void setCustReq(CustQueryReq obj){
		this.custReq = obj;
	}

	public CustQueryReq getCustReq(){
		return custReq;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CustQueryReqHolder rhs=(CustQueryReqHolder)rhs0;
		if(!ObjectUtils.equals(custReq, rhs.custReq)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custReq)
		.toHashCode();
	}


}