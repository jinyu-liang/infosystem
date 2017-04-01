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
@XmlType(propOrder={"scustomrList","sContactList","totalCount","sAccountList","sUserList"})
public class Do_queryCustBaseInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="scustomrList")
	private SCustomerList scustomrList;

	@XmlElement(name="sContactList")
	private SContactList sContactList;

	@XmlElement(name="sAccountList")
	private SAccountList sAccountList;

	@XmlElement(name="sUserList")
	private SUserList sUserList;

	@XmlElement(name="totalCount")
	private Integer totalCount;

	public void setScustomrList(SCustomerList obj){
		this.scustomrList = obj;
	}

	public SCustomerList getScustomrList(){
		return scustomrList;
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

	public void setTotalCount(Integer obj){
		this.totalCount = obj;
	}

	public Integer getTotalCount(){
		return totalCount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryCustBaseInfoResponse rhs=(Do_queryCustBaseInfoResponse)rhs0;
		if(!ObjectUtils.equals(scustomrList, rhs.scustomrList)) return false;
		if(!ObjectUtils.equals(sContactList, rhs.sContactList)) return false;
		if(!ObjectUtils.equals(sAccountList, rhs.sAccountList)) return false;
		if(!ObjectUtils.equals(sUserList, rhs.sUserList)) return false;
		if(!ObjectUtils.equals(totalCount, rhs.totalCount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(scustomrList)
		.append(sContactList)
		.append(sAccountList)
		.append(sUserList)
		.append(totalCount)
		.toHashCode();
	}


}