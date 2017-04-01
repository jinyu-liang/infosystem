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
@XmlType(propOrder={"user_id","phone_id","outer_cust_id","cust_id","outer_acct_id","acct_id","reguide_type","busi_service_id","time_segment","sPayReguideInfoList","oper_type"})
public class SUserPayReguideRel implements IComplexEntity{


	@XmlElement(name="sPayReguideInfoList")
	private SPayReguideInfoList sPayReguideInfoList;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="reguide_type")
	private Short reguide_type;

	@XmlElement(name="busi_service_id")
	private Long busi_service_id;

	@XmlElement(name="time_segment")
	private String time_segment;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setSPayReguideInfoList(SPayReguideInfoList obj){
		this.sPayReguideInfoList = obj;
	}

	public SPayReguideInfoList getSPayReguideInfoList(){
		return sPayReguideInfoList;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setReguide_type(Short obj){
		this.reguide_type = obj;
	}

	public Short getReguide_type(){
		return reguide_type;
	}

	public void setBusi_service_id(Long obj){
		this.busi_service_id = obj;
	}

	public Long getBusi_service_id(){
		return busi_service_id;
	}

	public void setTime_segment(String obj){
		this.time_segment = obj;
	}

	public String getTime_segment(){
		return time_segment;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserPayReguideRel rhs=(SUserPayReguideRel)rhs0;
		if(!ObjectUtils.equals(sPayReguideInfoList, rhs.sPayReguideInfoList)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(reguide_type, rhs.reguide_type)) return false;
		if(!ObjectUtils.equals(busi_service_id, rhs.busi_service_id)) return false;
		if(!ObjectUtils.equals(time_segment, rhs.time_segment)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sPayReguideInfoList)
		.append(user_id)
		.append(phone_id)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(reguide_type)
		.append(busi_service_id)
		.append(time_segment)
		.append(oper_type)
		.toHashCode();
	}


}