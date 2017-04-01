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
@XmlType(propOrder={"user_id","is_valid","expire_date"})
public class SChgUserValidityReq implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="is_valid")
	private Integer is_valid;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setIs_valid(Integer obj){
		this.is_valid = obj;
	}

	public Integer getIs_valid(){
		return is_valid;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChgUserValidityReq rhs=(SChgUserValidityReq)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(is_valid, rhs.is_valid)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(is_valid)
		.append(expire_date)
		.toHashCode();
	}


}