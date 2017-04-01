package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"balance_type","title_role_id","acct_id"})
public class BalanceInfo implements IComplexEntity{


	@XmlElement(name="balance_type")
	private Integer balance_type;

	@XmlElement(name="title_role_id")
	private Integer title_role_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	public void setBalance_type(Integer obj){
		this.balance_type = obj;
	}

	public Integer getBalance_type(){
		return balance_type;
	}

	public void setTitle_role_id(Integer obj){
		this.title_role_id = obj;
	}

	public Integer getTitle_role_id(){
		return title_role_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BalanceInfo rhs=(BalanceInfo)rhs0;
		if(!ObjectUtils.equals(balance_type, rhs.balance_type)) return false;
		if(!ObjectUtils.equals(title_role_id, rhs.title_role_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balance_type)
		.append(title_role_id)
		.append(acct_id)
		.toHashCode();
	}


}