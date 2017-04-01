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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"cust_id","cust_name","cust_segment","cust_sts","user_sts","acct_id","acct_name","acct_sts","paymode","promotion","phoneId","offer_name","balance","limit_credit"})
public class Do_QueryCommonUserInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="cust_name")
	private String cust_name;

	@XmlElement(name="cust_segment")
	private Integer cust_segment;

	@XmlElement(name="cust_sts")
	private Integer cust_sts;

	@XmlElement(name="user_sts")
	private Integer user_sts;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="acct_name")
	private String acct_name;

	@XmlElement(name="acct_sts")
	private Integer acct_sts;

	@XmlElement(name="paymode")
	private Integer paymode;

	@XmlElement(name="promotion")
	private Long promotion;

	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="offer_name")
	private String offer_name;

	@XmlElement(name="balance")
	private Double balance;

	@XmlElement(name="limit_credit")
	private Double limit_credit;

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setCust_name(String obj){
		this.cust_name = obj;
	}

	public String getCust_name(){
		return cust_name;
	}

	public void setCust_segment(Integer obj){
		this.cust_segment = obj;
	}

	public Integer getCust_segment(){
		return cust_segment;
	}

	public void setCust_sts(Integer obj){
		this.cust_sts = obj;
	}

	public Integer getCust_sts(){
		return cust_sts;
	}

	public void setUser_sts(Integer obj){
		this.user_sts = obj;
	}

	public Integer getUser_sts(){
		return user_sts;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setAcct_name(String obj){
		this.acct_name = obj;
	}

	public String getAcct_name(){
		return acct_name;
	}

	public void setAcct_sts(Integer obj){
		this.acct_sts = obj;
	}

	public Integer getAcct_sts(){
		return acct_sts;
	}

	public void setPaymode(Integer obj){
		this.paymode = obj;
	}

	public Integer getPaymode(){
		return paymode;
	}

	public void setPromotion(Long obj){
		this.promotion = obj;
	}

	public Long getPromotion(){
		return promotion;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setOffer_name(String obj){
		this.offer_name = obj;
	}

	public String getOffer_name(){
		return offer_name;
	}

	public void setBalance(Double obj){
		this.balance = obj;
	}

	public Double getBalance(){
		return balance;
	}

	public void setLimit_credit(Double obj){
		this.limit_credit = obj;
	}

	public Double getLimit_credit(){
		return limit_credit;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryCommonUserInfoResponse rhs=(Do_QueryCommonUserInfoResponse)rhs0;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(cust_name, rhs.cust_name)) return false;
		if(!ObjectUtils.equals(cust_segment, rhs.cust_segment)) return false;
		if(!ObjectUtils.equals(cust_sts, rhs.cust_sts)) return false;
		if(!ObjectUtils.equals(user_sts, rhs.user_sts)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(acct_name, rhs.acct_name)) return false;
		if(!ObjectUtils.equals(acct_sts, rhs.acct_sts)) return false;
		if(!ObjectUtils.equals(paymode, rhs.paymode)) return false;
		if(!ObjectUtils.equals(promotion, rhs.promotion)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(offer_name, rhs.offer_name)) return false;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		if(!ObjectUtils.equals(limit_credit, rhs.limit_credit)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cust_id)
		.append(cust_name)
		.append(cust_segment)
		.append(cust_sts)
		.append(user_sts)
		.append(acct_id)
		.append(acct_name)
		.append(acct_sts)
		.append(paymode)
		.append(promotion)
		.append(phoneId)
		.append(offer_name)
		.append(balance)
		.append(limit_credit)
		.toHashCode();
	}


}