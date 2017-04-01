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
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sCustomer","sContactList","sAccountList"})
public class Do_queryCustProfileResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sCustomer")
	private SCustomer sCustomer;

	@XmlElement(name="sContactList")
	private SContactList sContactList;

	@XmlElement(name="sAccountList")
	private SAccountList sAccountList;

	public void setSCustomer(SCustomer obj){
		this.sCustomer = obj;
	}

	public SCustomer getSCustomer(){
		return sCustomer;
	}

	public void setSContactList(SContactList obj){
		this.sContactList = obj;
	}

	public SContactList getSContactList(){
		return sContactList;
	}

	public void setSAccountList(SAccountList obj){
		this.sAccountList = obj;
	}

	public SAccountList getSAccountList(){
		return sAccountList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryCustProfileResponse rhs=(Do_queryCustProfileResponse)rhs0;
		if(!ObjectUtils.equals(sCustomer, rhs.sCustomer)) return false;
		if(!ObjectUtils.equals(sContactList, rhs.sContactList)) return false;
		if(!ObjectUtils.equals(sAccountList, rhs.sAccountList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sCustomer)
		.append(sContactList)
		.append(sAccountList)
		.toHashCode();
	}


}