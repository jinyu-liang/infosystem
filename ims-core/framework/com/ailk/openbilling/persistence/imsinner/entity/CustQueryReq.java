package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custIds","isCmIndividual","isCmOrganization","isCmPartyIdentity","isCmCustExt","isCmIndividualName","isCmOrganizationName","isCmCustomer","isCmContactMedium","isQueryAccts","acctReq"})
public class CustQueryReq extends BaseQueryReq implements IComplexEntity{


	@XmlElement(name="custIds")
	private List<Long> custIds;

	@XmlElement(name="acctReq")
	private AcctQueryReq acctReq;

	@XmlElement(name="isCmIndividual")
	private Boolean isCmIndividual;

	@XmlElement(name="isCmOrganization")
	private Boolean isCmOrganization;

	@XmlElement(name="isCmPartyIdentity")
	private Boolean isCmPartyIdentity;

	@XmlElement(name="isCmCustExt")
	private Boolean isCmCustExt;

	@XmlElement(name="isCmIndividualName")
	private Boolean isCmIndividualName;

	@XmlElement(name="isCmOrganizationName")
	private Boolean isCmOrganizationName;

	@XmlElement(name="isCmCustomer")
	private Boolean isCmCustomer;

	@XmlElement(name="isCmContactMedium")
	private Boolean isCmContactMedium;

	@XmlElement(name="isQueryAccts")
	private Boolean isQueryAccts;

	public void setCustIds(List<Long> obj){
		this.custIds = obj;
	}

	public List<Long> getCustIds(){
		return custIds;
	}

	public void setAcctReq(AcctQueryReq obj){
		this.acctReq = obj;
	}

	public AcctQueryReq getAcctReq(){
		return acctReq;
	}

	public void setIsCmIndividual(Boolean obj){
		this.isCmIndividual = obj;
	}

	public Boolean getIsCmIndividual(){
		return isCmIndividual;
	}

	public void setIsCmOrganization(Boolean obj){
		this.isCmOrganization = obj;
	}

	public Boolean getIsCmOrganization(){
		return isCmOrganization;
	}

	public void setIsCmPartyIdentity(Boolean obj){
		this.isCmPartyIdentity = obj;
	}

	public Boolean getIsCmPartyIdentity(){
		return isCmPartyIdentity;
	}

	public void setIsCmCustExt(Boolean obj){
		this.isCmCustExt = obj;
	}

	public Boolean getIsCmCustExt(){
		return isCmCustExt;
	}

	public void setIsCmIndividualName(Boolean obj){
		this.isCmIndividualName = obj;
	}

	public Boolean getIsCmIndividualName(){
		return isCmIndividualName;
	}

	public void setIsCmOrganizationName(Boolean obj){
		this.isCmOrganizationName = obj;
	}

	public Boolean getIsCmOrganizationName(){
		return isCmOrganizationName;
	}

	public void setIsCmCustomer(Boolean obj){
		this.isCmCustomer = obj;
	}

	public Boolean getIsCmCustomer(){
		return isCmCustomer;
	}

	public void setIsCmContactMedium(Boolean obj){
		this.isCmContactMedium = obj;
	}

	public Boolean getIsCmContactMedium(){
		return isCmContactMedium;
	}

	public void setIsQueryAccts(Boolean obj){
		this.isQueryAccts = obj;
	}

	public Boolean getIsQueryAccts(){
		return isQueryAccts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CustQueryReq rhs=(CustQueryReq)rhs0;
		if(!ObjectUtils.equals(custIds, rhs.custIds)) return false;
		if(!ObjectUtils.equals(acctReq, rhs.acctReq)) return false;
		if(!ObjectUtils.equals(isCmIndividual, rhs.isCmIndividual)) return false;
		if(!ObjectUtils.equals(isCmOrganization, rhs.isCmOrganization)) return false;
		if(!ObjectUtils.equals(isCmPartyIdentity, rhs.isCmPartyIdentity)) return false;
		if(!ObjectUtils.equals(isCmCustExt, rhs.isCmCustExt)) return false;
		if(!ObjectUtils.equals(isCmIndividualName, rhs.isCmIndividualName)) return false;
		if(!ObjectUtils.equals(isCmOrganizationName, rhs.isCmOrganizationName)) return false;
		if(!ObjectUtils.equals(isCmCustomer, rhs.isCmCustomer)) return false;
		if(!ObjectUtils.equals(isCmContactMedium, rhs.isCmContactMedium)) return false;
		if(!ObjectUtils.equals(isQueryAccts, rhs.isQueryAccts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custIds)
		.append(acctReq)
		.append(isCmIndividual)
		.append(isCmOrganization)
		.append(isCmPartyIdentity)
		.append(isCmCustExt)
		.append(isCmIndividualName)
		.append(isCmOrganizationName)
		.append(isCmCustomer)
		.append(isCmContactMedium)
		.append(isQueryAccts)
		.toHashCode();
	}


}