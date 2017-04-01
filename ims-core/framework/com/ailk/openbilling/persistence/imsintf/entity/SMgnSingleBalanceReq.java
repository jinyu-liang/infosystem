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
@XmlType(propOrder={"outer_new_acct_id","new_acct_id","oper_type","master_phone_id","outer_dummy_account_id","dummy_account_id","accumulation_reset_flag"})
public class SMgnSingleBalanceReq implements IComplexEntity{


	@XmlElement(name="outer_new_acct_id")
	private String outer_new_acct_id;

	@XmlElement(name="new_acct_id")
	private Long new_acct_id;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="master_phone_id")
	private String master_phone_id;

	@XmlElement(name="outer_dummy_account_id")
	private String outer_dummy_account_id;

	@XmlElement(name="dummy_account_id")
	private Long dummy_account_id;

	@XmlElement(name="accumulation_reset_flag")
	private Short accumulation_reset_flag;

	public void setOuter_new_acct_id(String obj){
		this.outer_new_acct_id = obj;
	}

	public String getOuter_new_acct_id(){
		return outer_new_acct_id;
	}

	public void setNew_acct_id(Long obj){
		this.new_acct_id = obj;
	}

	public Long getNew_acct_id(){
		return new_acct_id;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setMaster_phone_id(String obj){
		this.master_phone_id = obj;
	}

	public String getMaster_phone_id(){
		return master_phone_id;
	}

	public void setOuter_dummy_account_id(String obj){
		this.outer_dummy_account_id = obj;
	}

	public String getOuter_dummy_account_id(){
		return outer_dummy_account_id;
	}

	public void setDummy_account_id(Long obj){
		this.dummy_account_id = obj;
	}

	public Long getDummy_account_id(){
		return dummy_account_id;
	}

	public void setAccumulation_reset_flag(Short obj){
		this.accumulation_reset_flag = obj;
	}

	public Short getAccumulation_reset_flag(){
		return accumulation_reset_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMgnSingleBalanceReq rhs=(SMgnSingleBalanceReq)rhs0;
		if(!ObjectUtils.equals(outer_new_acct_id, rhs.outer_new_acct_id)) return false;
		if(!ObjectUtils.equals(new_acct_id, rhs.new_acct_id)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(master_phone_id, rhs.master_phone_id)) return false;
		if(!ObjectUtils.equals(outer_dummy_account_id, rhs.outer_dummy_account_id)) return false;
		if(!ObjectUtils.equals(dummy_account_id, rhs.dummy_account_id)) return false;
		if(!ObjectUtils.equals(accumulation_reset_flag, rhs.accumulation_reset_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_new_acct_id)
		.append(new_acct_id)
		.append(oper_type)
		.append(master_phone_id)
		.append(outer_dummy_account_id)
		.append(dummy_account_id)
		.append(accumulation_reset_flag)
		.toHashCode();
	}


}