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
@XmlType(propOrder={"oper_type","outer_parent_cust_id","parent_cust_id","outer_parent_acct_id","parent_acct_id","outer_cust_id","cust_id","outer_acct_id","acct_id","title_role_id","user_list"})
public class SubCA implements IComplexEntity{


	@XmlElement(name="user_list")
	private SAddSubscriberList user_list;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="outer_parent_cust_id")
	private String outer_parent_cust_id;

	@XmlElement(name="parent_cust_id")
	private Long parent_cust_id;

	@XmlElement(name="outer_parent_acct_id")
	private String outer_parent_acct_id;

	@XmlElement(name="parent_acct_id")
	private Long parent_acct_id;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="title_role_id")
	private Integer title_role_id;

	public void setUser_list(SAddSubscriberList obj){
		this.user_list = obj;
	}

	public SAddSubscriberList getUser_list(){
		return user_list;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setOuter_parent_cust_id(String obj){
		this.outer_parent_cust_id = obj;
	}

	public String getOuter_parent_cust_id(){
		return outer_parent_cust_id;
	}

	public void setParent_cust_id(Long obj){
		this.parent_cust_id = obj;
	}

	public Long getParent_cust_id(){
		return parent_cust_id;
	}

	public void setOuter_parent_acct_id(String obj){
		this.outer_parent_acct_id = obj;
	}

	public String getOuter_parent_acct_id(){
		return outer_parent_acct_id;
	}

	public void setParent_acct_id(Long obj){
		this.parent_acct_id = obj;
	}

	public Long getParent_acct_id(){
		return parent_acct_id;
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

	public void setTitle_role_id(Integer obj){
		this.title_role_id = obj;
	}

	public Integer getTitle_role_id(){
		return title_role_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SubCA rhs=(SubCA)rhs0;
		if(!ObjectUtils.equals(user_list, rhs.user_list)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(outer_parent_cust_id, rhs.outer_parent_cust_id)) return false;
		if(!ObjectUtils.equals(parent_cust_id, rhs.parent_cust_id)) return false;
		if(!ObjectUtils.equals(outer_parent_acct_id, rhs.outer_parent_acct_id)) return false;
		if(!ObjectUtils.equals(parent_acct_id, rhs.parent_acct_id)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(title_role_id, rhs.title_role_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_list)
		.append(oper_type)
		.append(outer_parent_cust_id)
		.append(parent_cust_id)
		.append(outer_parent_acct_id)
		.append(parent_acct_id)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(title_role_id)
		.toHashCode();
	}


}