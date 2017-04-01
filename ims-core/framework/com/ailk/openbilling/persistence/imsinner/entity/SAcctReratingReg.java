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
@XmlType(propOrder={"acct_id","rerating_flag"})
public class SAcctReratingReg implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="rerating_flag")
	private Integer rerating_flag;

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setRerating_flag(Integer obj){
		this.rerating_flag = obj;
	}

	public Integer getRerating_flag(){
		return rerating_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAcctReratingReg rhs=(SAcctReratingReg)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(rerating_flag, rhs.rerating_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(rerating_flag)
		.toHashCode();
	}


}