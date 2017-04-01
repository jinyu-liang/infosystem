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
@XmlType(propOrder={"user_id","phone_id","last_term_flag","drop_promotion_flag","reset_flag"})
public class SRemoveReq implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="last_term_flag")
	private Short last_term_flag;

	@XmlElement(name="drop_promotion_flag")
	private Short drop_promotion_flag;

	@XmlElement(name="reset_flag")
	private Short reset_flag;

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

	public void setLast_term_flag(Short obj){
		this.last_term_flag = obj;
	}

	public Short getLast_term_flag(){
		return last_term_flag;
	}

	public void setDrop_promotion_flag(Short obj){
		this.drop_promotion_flag = obj;
	}

	public Short getDrop_promotion_flag(){
		return drop_promotion_flag;
	}

	public void setReset_flag(Short obj){
		this.reset_flag = obj;
	}

	public Short getReset_flag(){
		return reset_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRemoveReq rhs=(SRemoveReq)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(last_term_flag, rhs.last_term_flag)) return false;
		if(!ObjectUtils.equals(drop_promotion_flag, rhs.drop_promotion_flag)) return false;
		if(!ObjectUtils.equals(reset_flag, rhs.reset_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(phone_id)
		.append(last_term_flag)
		.append(drop_promotion_flag)
		.append(reset_flag)
		.toHashCode();
	}


}