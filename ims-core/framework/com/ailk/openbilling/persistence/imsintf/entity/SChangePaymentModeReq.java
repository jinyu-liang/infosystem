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
@XmlType(propOrder={"outer_new_acct_id","new_acct_id","user_id","phone_id","convert_flag","reset_flag","last_term_flag","hybrid_rule","old_payment_mode","new_payment_mode","drop_flag","sCustomer","sAccount","productOrderOperList","busiServiceOperList","billalbe_acct_id","outer_billalbe_acct_id","accumulation_reset_flag"})
public class SChangePaymentModeReq implements IComplexEntity{


	@XmlElement(name="sCustomer")
	private SCustomer sCustomer;

	@XmlElement(name="sAccount")
	private SAccount sAccount;

	@XmlElement(name="productOrderOperList")
	private SProductOrderOperList productOrderOperList;

	@XmlElement(name="busiServiceOperList")
	private SBusiServiceOperList busiServiceOperList;

	@XmlElement(name="outer_new_acct_id")
	private String outer_new_acct_id;

	@XmlElement(name="new_acct_id")
	private Long new_acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="convert_flag")
	private Short convert_flag;

	@XmlElement(name="reset_flag")
	private Short reset_flag;

	@XmlElement(name="last_term_flag")
	private Short last_term_flag;

	@XmlElement(name="hybrid_rule")
	private Short hybrid_rule;

	@XmlElement(name="old_payment_mode")
	private Short old_payment_mode;

	@XmlElement(name="new_payment_mode")
	private Short new_payment_mode;

	@XmlElement(name="drop_flag")
	private Short drop_flag;

	@XmlElement(name="billalbe_acct_id")
	private Long billalbe_acct_id;

	@XmlElement(name="outer_billalbe_acct_id")
	private String outer_billalbe_acct_id;

	@XmlElement(name="accumulation_reset_flag")
	private Short accumulation_reset_flag;

	public void setSCustomer(SCustomer obj){
		this.sCustomer = obj;
	}

	public SCustomer getSCustomer(){
		return sCustomer;
	}

	public void setSAccount(SAccount obj){
		this.sAccount = obj;
	}

	public SAccount getSAccount(){
		return sAccount;
	}

	public void setProductOrderOperList(SProductOrderOperList obj){
		this.productOrderOperList = obj;
	}

	public SProductOrderOperList getProductOrderOperList(){
		return productOrderOperList;
	}

	public void setBusiServiceOperList(SBusiServiceOperList obj){
		this.busiServiceOperList = obj;
	}

	public SBusiServiceOperList getBusiServiceOperList(){
		return busiServiceOperList;
	}

	public void setOuter_new_acct_id(String obj){
		this.outer_new_acct_id = obj;
	}

	public String getOuter_new_acct_id(){
		return outer_new_acct_id;
	}

	public void setNew_acct_id(Long obj){
		this.new_acct_id = obj;
	}

	public Long getNew_acct_id(){
		return new_acct_id;
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

	public void setConvert_flag(Short obj){
		this.convert_flag = obj;
	}

	public Short getConvert_flag(){
		return convert_flag;
	}

	public void setReset_flag(Short obj){
		this.reset_flag = obj;
	}

	public Short getReset_flag(){
		return reset_flag;
	}

	public void setLast_term_flag(Short obj){
		this.last_term_flag = obj;
	}

	public Short getLast_term_flag(){
		return last_term_flag;
	}

	public void setHybrid_rule(Short obj){
		this.hybrid_rule = obj;
	}

	public Short getHybrid_rule(){
		return hybrid_rule;
	}

	public void setOld_payment_mode(Short obj){
		this.old_payment_mode = obj;
	}

	public Short getOld_payment_mode(){
		return old_payment_mode;
	}

	public void setNew_payment_mode(Short obj){
		this.new_payment_mode = obj;
	}

	public Short getNew_payment_mode(){
		return new_payment_mode;
	}

	public void setDrop_flag(Short obj){
		this.drop_flag = obj;
	}

	public Short getDrop_flag(){
		return drop_flag;
	}

	public void setBillalbe_acct_id(Long obj){
		this.billalbe_acct_id = obj;
	}

	public Long getBillalbe_acct_id(){
		return billalbe_acct_id;
	}

	public void setOuter_billalbe_acct_id(String obj){
		this.outer_billalbe_acct_id = obj;
	}

	public String getOuter_billalbe_acct_id(){
		return outer_billalbe_acct_id;
	}

	public void setAccumulation_reset_flag(Short obj){
		this.accumulation_reset_flag = obj;
	}

	public Short getAccumulation_reset_flag(){
		return accumulation_reset_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChangePaymentModeReq rhs=(SChangePaymentModeReq)rhs0;
		if(!ObjectUtils.equals(sCustomer, rhs.sCustomer)) return false;
		if(!ObjectUtils.equals(sAccount, rhs.sAccount)) return false;
		if(!ObjectUtils.equals(productOrderOperList, rhs.productOrderOperList)) return false;
		if(!ObjectUtils.equals(busiServiceOperList, rhs.busiServiceOperList)) return false;
		if(!ObjectUtils.equals(outer_new_acct_id, rhs.outer_new_acct_id)) return false;
		if(!ObjectUtils.equals(new_acct_id, rhs.new_acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(convert_flag, rhs.convert_flag)) return false;
		if(!ObjectUtils.equals(reset_flag, rhs.reset_flag)) return false;
		if(!ObjectUtils.equals(last_term_flag, rhs.last_term_flag)) return false;
		if(!ObjectUtils.equals(hybrid_rule, rhs.hybrid_rule)) return false;
		if(!ObjectUtils.equals(old_payment_mode, rhs.old_payment_mode)) return false;
		if(!ObjectUtils.equals(new_payment_mode, rhs.new_payment_mode)) return false;
		if(!ObjectUtils.equals(drop_flag, rhs.drop_flag)) return false;
		if(!ObjectUtils.equals(billalbe_acct_id, rhs.billalbe_acct_id)) return false;
		if(!ObjectUtils.equals(outer_billalbe_acct_id, rhs.outer_billalbe_acct_id)) return false;
		if(!ObjectUtils.equals(accumulation_reset_flag, rhs.accumulation_reset_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sCustomer)
		.append(sAccount)
		.append(productOrderOperList)
		.append(busiServiceOperList)
		.append(outer_new_acct_id)
		.append(new_acct_id)
		.append(user_id)
		.append(phone_id)
		.append(convert_flag)
		.append(reset_flag)
		.append(last_term_flag)
		.append(hybrid_rule)
		.append(old_payment_mode)
		.append(new_payment_mode)
		.append(drop_flag)
		.append(billalbe_acct_id)
		.append(outer_billalbe_acct_id)
		.append(accumulation_reset_flag)
		.toHashCode();
	}


}