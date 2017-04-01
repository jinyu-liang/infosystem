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
@XmlType(propOrder={"user_id","sts","os_sts","fraud_flag","rerating_flag","unbilling_flag","user_request_flag","valid_date","expire_date","create_date"})
public class SUserLifecycle implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="sts")
	private Integer sts;

	@XmlElement(name="os_sts")
	private Integer os_sts;

	@XmlElement(name="fraud_flag")
	private Integer fraud_flag;

	@XmlElement(name="rerating_flag")
	private Integer rerating_flag;

	@XmlElement(name="unbilling_flag")
	private Integer unbilling_flag;

	@XmlElement(name="user_request_flag")
	private Integer user_request_flag;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="create_date")
	private String create_date;

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
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

	public void setFraud_flag(Integer obj){
		this.fraud_flag = obj;
	}

	public Integer getFraud_flag(){
		return fraud_flag;
	}

	public void setRerating_flag(Integer obj){
		this.rerating_flag = obj;
	}

	public Integer getRerating_flag(){
		return rerating_flag;
	}

	public void setUnbilling_flag(Integer obj){
		this.unbilling_flag = obj;
	}

	public Integer getUnbilling_flag(){
		return unbilling_flag;
	}

	public void setUser_request_flag(Integer obj){
		this.user_request_flag = obj;
	}

	public Integer getUser_request_flag(){
		return user_request_flag;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserLifecycle rhs=(SUserLifecycle)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(os_sts, rhs.os_sts)) return false;
		if(!ObjectUtils.equals(fraud_flag, rhs.fraud_flag)) return false;
		if(!ObjectUtils.equals(rerating_flag, rhs.rerating_flag)) return false;
		if(!ObjectUtils.equals(unbilling_flag, rhs.unbilling_flag)) return false;
		if(!ObjectUtils.equals(user_request_flag, rhs.user_request_flag)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(sts)
		.append(os_sts)
		.append(fraud_flag)
		.append(rerating_flag)
		.append(unbilling_flag)
		.append(user_request_flag)
		.append(valid_date)
		.append(expire_date)
		.append(create_date)
		.toHashCode();
	}


}