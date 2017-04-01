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
@XmlType(propOrder={"query_id","acct_name","cust_name"})
public class SQueryUserListForGuiReq implements IComplexEntity{


	@XmlElement(name="query_id")
	private String query_id;

	@XmlElement(name="acct_name")
	private String acct_name;

	@XmlElement(name="cust_name")
	private String cust_name;

	public void setQuery_id(String obj){
		this.query_id = obj;
	}

	public String getQuery_id(){
		return query_id;
	}

	public void setAcct_name(String obj){
		this.acct_name = obj;
	}

	public String getAcct_name(){
		return acct_name;
	}

	public void setCust_name(String obj){
		this.cust_name = obj;
	}

	public String getCust_name(){
		return cust_name;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryUserListForGuiReq rhs=(SQueryUserListForGuiReq)rhs0;
		if(!ObjectUtils.equals(query_id, rhs.query_id)) return false;
		if(!ObjectUtils.equals(acct_name, rhs.acct_name)) return false;
		if(!ObjectUtils.equals(cust_name, rhs.cust_name)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(query_id)
		.append(acct_name)
		.append(cust_name)
		.toHashCode();
	}


}