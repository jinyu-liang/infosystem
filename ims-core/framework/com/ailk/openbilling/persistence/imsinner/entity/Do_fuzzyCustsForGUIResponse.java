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
import com.ailk.openbilling.persistence.imsintf.entity.SCustomerList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sCustomerList","sContactList","sAccountList","sUserList"})
public class Do_fuzzyCustsForGUIResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sCustomerList")
	private SCustomerList sCustomerList;

	@XmlElement(name="sContactList")
	private SContactList sContactList;

	@XmlElement(name="sAccountList")
	private SAccountList sAccountList;

	@XmlElement(name="sUserList")
	private SUserList sUserList;

	public void setSCustomerList(SCustomerList obj){
		this.sCustomerList = obj;
	}

	public SCustomerList getSCustomerList(){
		return sCustomerList;
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

	public void setSUserList(SUserList obj){
		this.sUserList = obj;
	}

	public SUserList getSUserList(){
		return sUserList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_fuzzyCustsForGUIResponse rhs=(Do_fuzzyCustsForGUIResponse)rhs0;
		if(!ObjectUtils.equals(sCustomerList, rhs.sCustomerList)) return false;
		if(!ObjectUtils.equals(sContactList, rhs.sContactList)) return false;
		if(!ObjectUtils.equals(sAccountList, rhs.sAccountList)) return false;
		if(!ObjectUtils.equals(sUserList, rhs.sUserList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sCustomerList)
		.append(sContactList)
		.append(sAccountList)
		.append(sUserList)
		.toHashCode();
	}


}