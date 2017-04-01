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
@XmlType(propOrder={"payment_type","bank_id","bank_acct_nbr","card_no","card_issuing_bank","outer_cust_id","cust_id","outer_acct_id","acct_id"})
public class SPayChannel implements IComplexEntity{


	@XmlElement(name="payment_type")
	private Short payment_type;

	@XmlElement(name="bank_id")
	private String bank_id;

	@XmlElement(name="bank_acct_nbr")
	private String bank_acct_nbr;

	@XmlElement(name="card_no")
	private String card_no;

	@XmlElement(name="card_issuing_bank")
	private String card_issuing_bank;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	public void setPayment_type(Short obj){
		this.payment_type = obj;
	}

	public Short getPayment_type(){
		return payment_type;
	}

	public void setBank_id(String obj){
		this.bank_id = obj;
	}

	public String getBank_id(){
		return bank_id;
	}

	public void setBank_acct_nbr(String obj){
		this.bank_acct_nbr = obj;
	}

	public String getBank_acct_nbr(){
		return bank_acct_nbr;
	}

	public void setCard_no(String obj){
		this.card_no = obj;
	}

	public String getCard_no(){
		return card_no;
	}

	public void setCard_issuing_bank(String obj){
		this.card_issuing_bank = obj;
	}

	public String getCard_issuing_bank(){
		return card_issuing_bank;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPayChannel rhs=(SPayChannel)rhs0;
		if(!ObjectUtils.equals(payment_type, rhs.payment_type)) return false;
		if(!ObjectUtils.equals(bank_id, rhs.bank_id)) return false;
		if(!ObjectUtils.equals(bank_acct_nbr, rhs.bank_acct_nbr)) return false;
		if(!ObjectUtils.equals(card_no, rhs.card_no)) return false;
		if(!ObjectUtils.equals(card_issuing_bank, rhs.card_issuing_bank)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payment_type)
		.append(bank_id)
		.append(bank_acct_nbr)
		.append(card_no)
		.append(card_issuing_bank)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.toHashCode();
	}


}