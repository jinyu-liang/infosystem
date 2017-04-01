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
@XmlType(propOrder={"parent_acct_id","acct_id"})
public class AcctTree implements IComplexEntity{


	@XmlElement(name="parent_acct_id")
	private Long parent_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	public void setParent_acct_id(Long obj){
		this.parent_acct_id = obj;
	}

	public Long getParent_acct_id(){
		return parent_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AcctTree rhs=(AcctTree)rhs0;
		if(!ObjectUtils.equals(parent_acct_id, rhs.parent_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(parent_acct_id)
		.append(acct_id)
		.toHashCode();
	}


}