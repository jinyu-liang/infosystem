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
@XmlType(propOrder={"acctId","groupId","productId","freeItemList"})
public class Do_queryShareAllocReq implements IComplexEntity{


	@XmlElement(name="freeItemList")
	private FreeItem4GUI[] freeItemList;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="groupId")
	private Long groupId;

	@XmlElement(name="productId")
	private Long productId;

	public void setFreeItemList(FreeItem4GUI[] obj){
		this.freeItemList = obj;
	}

	public FreeItem4GUI[] getFreeItemList(){
		return freeItemList;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setGroupId(Long obj){
		this.groupId = obj;
	}

	public Long getGroupId(){
		return groupId;
	}

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryShareAllocReq rhs=(Do_queryShareAllocReq)rhs0;
		if(!ObjectUtils.equals(freeItemList, rhs.freeItemList)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeItemList)
		.append(acctId)
		.append(groupId)
		.append(productId)
		.toHashCode();
	}


}