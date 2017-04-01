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
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sUser","sAccount","sCustomer"})
public class Do_qryUserAcctCustResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sUser")
	private SUser sUser;

	@XmlElement(name="sAccount")
	private SAccount sAccount;

	@XmlElement(name="sCustomer")
	private SCustomer sCustomer;

	public void setSUser(SUser obj){
		this.sUser = obj;
	}

	public SUser getSUser(){
		return sUser;
	}

	public void setSAccount(SAccount obj){
		this.sAccount = obj;
	}

	public SAccount getSAccount(){
		return sAccount;
	}

	public void setSCustomer(SCustomer obj){
		this.sCustomer = obj;
	}

	public SCustomer getSCustomer(){
		return sCustomer;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_qryUserAcctCustResponse rhs=(Do_qryUserAcctCustResponse)rhs0;
		if(!ObjectUtils.equals(sUser, rhs.sUser)) return false;
		if(!ObjectUtils.equals(sAccount, rhs.sAccount)) return false;
		if(!ObjectUtils.equals(sCustomer, rhs.sCustomer)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUser)
		.append(sAccount)
		.append(sCustomer)
		.toHashCode();
	}


}