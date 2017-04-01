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
import com.ailk.openbilling.persistence.imsintf.entity.SBusiServiceList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"phone_id","user_id","old_payment_mode","sBusiServiceList","sProductOrderList"})
public class Do_queryUserPaymemtModeResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sBusiServiceList")
	private SBusiServiceList sBusiServiceList;

	@XmlElement(name="sProductOrderList")
	private SProductOrderList sProductOrderList;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="old_payment_mode")
	private Short old_payment_mode;

	public void setSBusiServiceList(SBusiServiceList obj){
		this.sBusiServiceList = obj;
	}

	public SBusiServiceList getSBusiServiceList(){
		return sBusiServiceList;
	}

	public void setSProductOrderList(SProductOrderList obj){
		this.sProductOrderList = obj;
	}

	public SProductOrderList getSProductOrderList(){
		return sProductOrderList;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setOld_payment_mode(Short obj){
		this.old_payment_mode = obj;
	}

	public Short getOld_payment_mode(){
		return old_payment_mode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUserPaymemtModeResponse rhs=(Do_queryUserPaymemtModeResponse)rhs0;
		if(!ObjectUtils.equals(sBusiServiceList, rhs.sBusiServiceList)) return false;
		if(!ObjectUtils.equals(sProductOrderList, rhs.sProductOrderList)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(old_payment_mode, rhs.old_payment_mode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBusiServiceList)
		.append(sProductOrderList)
		.append(phone_id)
		.append(user_id)
		.append(old_payment_mode)
		.toHashCode();
	}


}