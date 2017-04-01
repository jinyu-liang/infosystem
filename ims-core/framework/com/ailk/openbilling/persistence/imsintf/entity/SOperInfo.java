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
@XmlType(propOrder={"so_nbr","busi_code","so_mode","so_date","charge_flag","isnormal","outer_cust_id","cust_id","outer_acct_id","acct_id","user_id","phone_id","op_id","prov_code","region_code","county_code","org_id","rso_nbr","is_monitor","remark","step_id","source_system","notify_flag"})
public class SOperInfo implements IComplexEntity{


	@XmlElement(name="so_nbr")
	private String so_nbr;

	@XmlElement(name="busi_code")
	private Integer busi_code;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="so_date")
	private String so_date;

	@XmlElement(name="charge_flag")
	private Short charge_flag;

	@XmlElement(name="isnormal")
	private Short isnormal;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="op_id")
	private Long op_id;

	@XmlElement(name="prov_code")
	private Short prov_code;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="county_code")
	private Short county_code;

	@XmlElement(name="org_id")
	private Integer org_id;

	@XmlElement(name="rso_nbr")
	private String rso_nbr;

	@XmlElement(name="is_monitor")
	private Short is_monitor;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="step_id")
	private Short step_id;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="notify_flag")
	private Short notify_flag;

	public void setSo_nbr(String obj){
		this.so_nbr = obj;
	}

	public String getSo_nbr(){
		return so_nbr;
	}

	public void setBusi_code(Integer obj){
		this.busi_code = obj;
	}

	public Integer getBusi_code(){
		return busi_code;
	}

	public void setSo_mode(Short obj){
		this.so_mode = obj;
	}

	public Short getSo_mode(){
		return so_mode;
	}

	public void setSo_date(String obj){
		this.so_date = obj;
	}

	public String getSo_date(){
		return so_date;
	}

	public void setCharge_flag(Short obj){
		this.charge_flag = obj;
	}

	public Short getCharge_flag(){
		return charge_flag;
	}

	public void setIsnormal(Short obj){
		this.isnormal = obj;
	}

	public Short getIsnormal(){
		return isnormal;
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

	public void setOp_id(Long obj){
		this.op_id = obj;
	}

	public Long getOp_id(){
		return op_id;
	}

	public void setProv_code(Short obj){
		this.prov_code = obj;
	}

	public Short getProv_code(){
		return prov_code;
	}

	public void setRegion_code(Short obj){
		this.region_code = obj;
	}

	public Short getRegion_code(){
		return region_code;
	}

	public void setCounty_code(Short obj){
		this.county_code = obj;
	}

	public Short getCounty_code(){
		return county_code;
	}

	public void setOrg_id(Integer obj){
		this.org_id = obj;
	}

	public Integer getOrg_id(){
		return org_id;
	}

	public void setRso_nbr(String obj){
		this.rso_nbr = obj;
	}

	public String getRso_nbr(){
		return rso_nbr;
	}

	public void setIs_monitor(Short obj){
		this.is_monitor = obj;
	}

	public Short getIs_monitor(){
		return is_monitor;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setStep_id(Short obj){
		this.step_id = obj;
	}

	public Short getStep_id(){
		return step_id;
	}

	public void setSource_system(String obj){
		this.source_system = obj;
	}

	public String getSource_system(){
		return source_system;
	}

	public void setNotify_flag(Short obj){
		this.notify_flag = obj;
	}

	public Short getNotify_flag(){
		return notify_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOperInfo rhs=(SOperInfo)rhs0;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		if(!ObjectUtils.equals(busi_code, rhs.busi_code)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(so_date, rhs.so_date)) return false;
		if(!ObjectUtils.equals(charge_flag, rhs.charge_flag)) return false;
		if(!ObjectUtils.equals(isnormal, rhs.isnormal)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(prov_code, rhs.prov_code)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(county_code, rhs.county_code)) return false;
		if(!ObjectUtils.equals(org_id, rhs.org_id)) return false;
		if(!ObjectUtils.equals(rso_nbr, rhs.rso_nbr)) return false;
		if(!ObjectUtils.equals(is_monitor, rhs.is_monitor)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(step_id, rhs.step_id)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(notify_flag, rhs.notify_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(so_nbr)
		.append(busi_code)
		.append(so_mode)
		.append(so_date)
		.append(charge_flag)
		.append(isnormal)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(op_id)
		.append(prov_code)
		.append(region_code)
		.append(county_code)
		.append(org_id)
		.append(rso_nbr)
		.append(is_monitor)
		.append(remark)
		.append(step_id)
		.append(source_system)
		.append(notify_flag)
		.toHashCode();
	}


}