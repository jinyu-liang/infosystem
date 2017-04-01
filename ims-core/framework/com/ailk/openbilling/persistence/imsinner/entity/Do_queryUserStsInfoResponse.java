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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"brand_id","active_period","suspend_period","disable_period","maximun_balance","avtive_valid_date","sts","os_sts","frauld_flag","user_request_flag","unbilling_flag","rerating_flag","full_sts"})
public class Do_queryUserStsInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="brand_id")
	private Integer brand_id;

	@XmlElement(name="active_period")
	private Date active_period;

	@XmlElement(name="suspend_period")
	private Date suspend_period;

	@XmlElement(name="disable_period")
	private Date disable_period;

	@XmlElement(name="maximun_balance")
	private Long maximun_balance;

	@XmlElement(name="avtive_valid_date")
	private Date avtive_valid_date;

	@XmlElement(name="sts")
	private Integer sts;

	@XmlElement(name="os_sts")
	private Integer os_sts;

	@XmlElement(name="frauld_flag")
	private Integer frauld_flag;

	@XmlElement(name="user_request_flag")
	private Integer user_request_flag;

	@XmlElement(name="unbilling_flag")
	private Integer unbilling_flag;

	@XmlElement(name="rerating_flag")
	private Integer rerating_flag;

	@XmlElement(name="full_sts")
	private String full_sts;

	public void setBrand_id(Integer obj){
		this.brand_id = obj;
	}

	public Integer getBrand_id(){
		return brand_id;
	}

	public void setActive_period(Date obj){
		this.active_period = obj;
	}

	public Date getActive_period(){
		return active_period;
	}

	public void setSuspend_period(Date obj){
		this.suspend_period = obj;
	}

	public Date getSuspend_period(){
		return suspend_period;
	}

	public void setDisable_period(Date obj){
		this.disable_period = obj;
	}

	public Date getDisable_period(){
		return disable_period;
	}

	public void setMaximun_balance(Long obj){
		this.maximun_balance = obj;
	}

	public Long getMaximun_balance(){
		return maximun_balance;
	}

	public void setAvtive_valid_date(Date obj){
		this.avtive_valid_date = obj;
	}

	public Date getAvtive_valid_date(){
		return avtive_valid_date;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setOs_sts(Integer obj){
		this.os_sts = obj;
	}

	public Integer getOs_sts(){
		return os_sts;
	}

	public void setFrauld_flag(Integer obj){
		this.frauld_flag = obj;
	}

	public Integer getFrauld_flag(){
		return frauld_flag;
	}

	public void setUser_request_flag(Integer obj){
		this.user_request_flag = obj;
	}

	public Integer getUser_request_flag(){
		return user_request_flag;
	}

	public void setUnbilling_flag(Integer obj){
		this.unbilling_flag = obj;
	}

	public Integer getUnbilling_flag(){
		return unbilling_flag;
	}

	public void setRerating_flag(Integer obj){
		this.rerating_flag = obj;
	}

	public Integer getRerating_flag(){
		return rerating_flag;
	}

	public void setFull_sts(String obj){
		this.full_sts = obj;
	}

	public String getFull_sts(){
		return full_sts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUserStsInfoResponse rhs=(Do_queryUserStsInfoResponse)rhs0;
		if(!ObjectUtils.equals(brand_id, rhs.brand_id)) return false;
		if(!ObjectUtils.equals(active_period, rhs.active_period)) return false;
		if(!ObjectUtils.equals(suspend_period, rhs.suspend_period)) return false;
		if(!ObjectUtils.equals(disable_period, rhs.disable_period)) return false;
		if(!ObjectUtils.equals(maximun_balance, rhs.maximun_balance)) return false;
		if(!ObjectUtils.equals(avtive_valid_date, rhs.avtive_valid_date)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(os_sts, rhs.os_sts)) return false;
		if(!ObjectUtils.equals(frauld_flag, rhs.frauld_flag)) return false;
		if(!ObjectUtils.equals(user_request_flag, rhs.user_request_flag)) return false;
		if(!ObjectUtils.equals(unbilling_flag, rhs.unbilling_flag)) return false;
		if(!ObjectUtils.equals(rerating_flag, rhs.rerating_flag)) return false;
		if(!ObjectUtils.equals(full_sts, rhs.full_sts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(brand_id)
		.append(active_period)
		.append(suspend_period)
		.append(disable_period)
		.append(maximun_balance)
		.append(avtive_valid_date)
		.append(sts)
		.append(os_sts)
		.append(frauld_flag)
		.append(user_request_flag)
		.append(unbilling_flag)
		.append(rerating_flag)
		.append(full_sts)
		.toHashCode();
	}


}