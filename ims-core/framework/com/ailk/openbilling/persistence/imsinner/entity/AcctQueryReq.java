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
@XmlType(propOrder={"acctIds","isCaCustInvoice","isCaAcctAttribute","isCaBuget","isCaAccount","isCaParentAcctId","isCmContactMedium","isQueryUsers","userReq","isCaPaymentChannel"})
public class AcctQueryReq extends BaseQueryReq implements IComplexEntity{


	@XmlElement(name="acctIds")
	private List<Long> acctIds;

	@XmlElement(name="userReq")
	private UserQueryReq userReq;

	@XmlElement(name="isCaCustInvoice")
	private Boolean isCaCustInvoice;

	@XmlElement(name="isCaAcctAttribute")
	private Boolean isCaAcctAttribute;

	@XmlElement(name="isCaBuget")
	private Boolean isCaBuget;

	@XmlElement(name="isCaAccount")
	private Boolean isCaAccount;

	@XmlElement(name="isCaParentAcctId")
	private Boolean isCaParentAcctId;

	@XmlElement(name="isCmContactMedium")
	private Boolean isCmContactMedium;

	@XmlElement(name="isQueryUsers")
	private Boolean isQueryUsers;

	@XmlElement(name="isCaPaymentChannel")
	private Boolean isCaPaymentChannel;

	public void setAcctIds(List<Long> obj){
		this.acctIds = obj;
	}

	public List<Long> getAcctIds(){
		return acctIds;
	}

	public void setUserReq(UserQueryReq obj){
		this.userReq = obj;
	}

	public UserQueryReq getUserReq(){
		return userReq;
	}

	public void setIsCaCustInvoice(Boolean obj){
		this.isCaCustInvoice = obj;
	}

	public Boolean getIsCaCustInvoice(){
		return isCaCustInvoice;
	}

	public void setIsCaAcctAttribute(Boolean obj){
		this.isCaAcctAttribute = obj;
	}

	public Boolean getIsCaAcctAttribute(){
		return isCaAcctAttribute;
	}

	public void setIsCaBuget(Boolean obj){
		this.isCaBuget = obj;
	}

	public Boolean getIsCaBuget(){
		return isCaBuget;
	}

	public void setIsCaAccount(Boolean obj){
		this.isCaAccount = obj;
	}

	public Boolean getIsCaAccount(){
		return isCaAccount;
	}

	public void setIsCaParentAcctId(Boolean obj){
		this.isCaParentAcctId = obj;
	}

	public Boolean getIsCaParentAcctId(){
		return isCaParentAcctId;
	}

	public void setIsCmContactMedium(Boolean obj){
		this.isCmContactMedium = obj;
	}

	public Boolean getIsCmContactMedium(){
		return isCmContactMedium;
	}

	public void setIsQueryUsers(Boolean obj){
		this.isQueryUsers = obj;
	}

	public Boolean getIsQueryUsers(){
		return isQueryUsers;
	}

	public void setIsCaPaymentChannel(Boolean obj){
		this.isCaPaymentChannel = obj;
	}

	public Boolean getIsCaPaymentChannel(){
		return isCaPaymentChannel;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AcctQueryReq rhs=(AcctQueryReq)rhs0;
		if(!ObjectUtils.equals(acctIds, rhs.acctIds)) return false;
		if(!ObjectUtils.equals(userReq, rhs.userReq)) return false;
		if(!ObjectUtils.equals(isCaCustInvoice, rhs.isCaCustInvoice)) return false;
		if(!ObjectUtils.equals(isCaAcctAttribute, rhs.isCaAcctAttribute)) return false;
		if(!ObjectUtils.equals(isCaBuget, rhs.isCaBuget)) return false;
		if(!ObjectUtils.equals(isCaAccount, rhs.isCaAccount)) return false;
		if(!ObjectUtils.equals(isCaParentAcctId, rhs.isCaParentAcctId)) return false;
		if(!ObjectUtils.equals(isCmContactMedium, rhs.isCmContactMedium)) return false;
		if(!ObjectUtils.equals(isQueryUsers, rhs.isQueryUsers)) return false;
		if(!ObjectUtils.equals(isCaPaymentChannel, rhs.isCaPaymentChannel)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctIds)
		.append(userReq)
		.append(isCaCustInvoice)
		.append(isCaAcctAttribute)
		.append(isCaBuget)
		.append(isCaAccount)
		.append(isCaParentAcctId)
		.append(isCmContactMedium)
		.append(isQueryUsers)
		.append(isCaPaymentChannel)
		.toHashCode();
	}


}