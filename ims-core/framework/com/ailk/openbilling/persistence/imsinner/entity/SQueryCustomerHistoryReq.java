package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(propOrder={"gui_cust_id","gui_acct_id","gui_user_id","gui_phone_id","gui_his_date"})
public class SQueryCustomerHistoryReq implements IComplexEntity{


	@XmlElement(name="gui_cust_id")
	private Long gui_cust_id;

	@XmlElement(name="gui_acct_id")
	private Long gui_acct_id;

	@XmlElement(name="gui_user_id")
	private Long gui_user_id;

	@XmlElement(name="gui_phone_id")
	private String gui_phone_id;

	@XmlElement(name="gui_his_date")
	private Date gui_his_date;

	public void setGui_cust_id(Long obj){
		this.gui_cust_id = obj;
	}

	public Long getGui_cust_id(){
		return gui_cust_id;
	}

	public void setGui_acct_id(Long obj){
		this.gui_acct_id = obj;
	}

	public Long getGui_acct_id(){
		return gui_acct_id;
	}

	public void setGui_user_id(Long obj){
		this.gui_user_id = obj;
	}

	public Long getGui_user_id(){
		return gui_user_id;
	}

	public void setGui_phone_id(String obj){
		this.gui_phone_id = obj;
	}

	public String getGui_phone_id(){
		return gui_phone_id;
	}

	public void setGui_his_date(Date obj){
		this.gui_his_date = obj;
	}

	public Date getGui_his_date(){
		return gui_his_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryCustomerHistoryReq rhs=(SQueryCustomerHistoryReq)rhs0;
		if(!ObjectUtils.equals(gui_cust_id, rhs.gui_cust_id)) return false;
		if(!ObjectUtils.equals(gui_acct_id, rhs.gui_acct_id)) return false;
		if(!ObjectUtils.equals(gui_user_id, rhs.gui_user_id)) return false;
		if(!ObjectUtils.equals(gui_phone_id, rhs.gui_phone_id)) return false;
		if(!ObjectUtils.equals(gui_his_date, rhs.gui_his_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(gui_cust_id)
		.append(gui_acct_id)
		.append(gui_user_id)
		.append(gui_phone_id)
		.append(gui_his_date)
		.toHashCode();
	}


}