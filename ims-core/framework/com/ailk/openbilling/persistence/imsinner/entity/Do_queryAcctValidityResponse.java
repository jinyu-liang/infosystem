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
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctValidity"})
public class Do_queryAcctValidityResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acctValidity")
	private CmResValidity acctValidity;

	public void setAcctValidity(CmResValidity obj){
		this.acctValidity = obj;
	}

	public CmResValidity getAcctValidity(){
		return acctValidity;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctValidityResponse rhs=(Do_queryAcctValidityResponse)rhs0;
		if(!ObjectUtils.equals(acctValidity, rhs.acctValidity)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctValidity)
		.toHashCode();
	}


}