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
@XmlType(propOrder={"outer_org_acct_id","org_acct_id","org_phone_id","outer_target_acct_id","target_acct_id","target_phone_id","rule_id","times","org_owner_type","target_owner_type"})
public class STransferReq implements IComplexEntity{


	@XmlElement(name="outer_org_acct_id")
	private String outer_org_acct_id;

	@XmlElement(name="org_acct_id")
	private Long org_acct_id;

	@XmlElement(name="org_phone_id")
	private String org_phone_id;

	@XmlElement(name="outer_target_acct_id")
	private String outer_target_acct_id;

	@XmlElement(name="target_acct_id")
	private Long target_acct_id;

	@XmlElement(name="target_phone_id")
	private String target_phone_id;

	@XmlElement(name="rule_id")
	private Long rule_id;

	@XmlElement(name="times")
	private Short times;

	@XmlElement(name="org_owner_type")
	private Integer org_owner_type;

	@XmlElement(name="target_owner_type")
	private Integer target_owner_type;

	public void setOuter_org_acct_id(String obj){
		this.outer_org_acct_id = obj;
	}

	public String getOuter_org_acct_id(){
		return outer_org_acct_id;
	}

	public void setOrg_acct_id(Long obj){
		this.org_acct_id = obj;
	}

	public Long getOrg_acct_id(){
		return org_acct_id;
	}

	public void setOrg_phone_id(String obj){
		this.org_phone_id = obj;
	}

	public String getOrg_phone_id(){
		return org_phone_id;
	}

	public void setOuter_target_acct_id(String obj){
		this.outer_target_acct_id = obj;
	}

	public String getOuter_target_acct_id(){
		return outer_target_acct_id;
	}

	public void setTarget_acct_id(Long obj){
		this.target_acct_id = obj;
	}

	public Long getTarget_acct_id(){
		return target_acct_id;
	}

	public void setTarget_phone_id(String obj){
		this.target_phone_id = obj;
	}

	public String getTarget_phone_id(){
		return target_phone_id;
	}

	public void setRule_id(Long obj){
		this.rule_id = obj;
	}

	public Long getRule_id(){
		return rule_id;
	}

	public void setTimes(Short obj){
		this.times = obj;
	}

	public Short getTimes(){
		return times;
	}

	public void setOrg_owner_type(Integer obj){
		this.org_owner_type = obj;
	}

	public Integer getOrg_owner_type(){
		return org_owner_type;
	}

	public void setTarget_owner_type(Integer obj){
		this.target_owner_type = obj;
	}

	public Integer getTarget_owner_type(){
		return target_owner_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STransferReq rhs=(STransferReq)rhs0;
		if(!ObjectUtils.equals(outer_org_acct_id, rhs.outer_org_acct_id)) return false;
		if(!ObjectUtils.equals(org_acct_id, rhs.org_acct_id)) return false;
		if(!ObjectUtils.equals(org_phone_id, rhs.org_phone_id)) return false;
		if(!ObjectUtils.equals(outer_target_acct_id, rhs.outer_target_acct_id)) return false;
		if(!ObjectUtils.equals(target_acct_id, rhs.target_acct_id)) return false;
		if(!ObjectUtils.equals(target_phone_id, rhs.target_phone_id)) return false;
		if(!ObjectUtils.equals(rule_id, rhs.rule_id)) return false;
		if(!ObjectUtils.equals(times, rhs.times)) return false;
		if(!ObjectUtils.equals(org_owner_type, rhs.org_owner_type)) return false;
		if(!ObjectUtils.equals(target_owner_type, rhs.target_owner_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_org_acct_id)
		.append(org_acct_id)
		.append(org_phone_id)
		.append(outer_target_acct_id)
		.append(target_acct_id)
		.append(target_phone_id)
		.append(rule_id)
		.append(times)
		.append(org_owner_type)
		.append(target_owner_type)
		.toHashCode();
	}


}