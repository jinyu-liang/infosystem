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
@XmlType(propOrder={"user_id","phone_id","outer_cust_id","cust_id","outer_acct_id","acct_id","sPaySplitInfoList","product_sequence_id","oper_type","split_type","price_rule_id"})
public class SUserPaySplitRel implements IComplexEntity{


	@XmlElement(name="sPaySplitInfoList")
	private SPaySplitInfoList sPaySplitInfoList;

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

	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="split_type")
	private Short split_type;

	@XmlElement(name="price_rule_id")
	private Long price_rule_id;

	public void setSPaySplitInfoList(SPaySplitInfoList obj){
		this.sPaySplitInfoList = obj;
	}

	public SPaySplitInfoList getSPaySplitInfoList(){
		return sPaySplitInfoList;
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

	public void setProduct_sequence_id(Long obj){
		this.product_sequence_id = obj;
	}

	public Long getProduct_sequence_id(){
		return product_sequence_id;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setSplit_type(Short obj){
		this.split_type = obj;
	}

	public Short getSplit_type(){
		return split_type;
	}

	public void setPrice_rule_id(Long obj){
		this.price_rule_id = obj;
	}

	public Long getPrice_rule_id(){
		return price_rule_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserPaySplitRel rhs=(SUserPaySplitRel)rhs0;
		if(!ObjectUtils.equals(sPaySplitInfoList, rhs.sPaySplitInfoList)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(split_type, rhs.split_type)) return false;
		if(!ObjectUtils.equals(price_rule_id, rhs.price_rule_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sPaySplitInfoList)
		.append(user_id)
		.append(phone_id)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(product_sequence_id)
		.append(oper_type)
		.append(split_type)
		.append(price_rule_id)
		.toHashCode();
	}


}