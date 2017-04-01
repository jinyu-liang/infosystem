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
@XmlType(propOrder={"auto_id","outer_acct_id","acct_id","user_id","phone_id","_low_balance_threshold","valid_date","expire_date","pay_channel","bank_acct_id","bank_acct_name","credit_card_no","bank_code","additional_info","card_serial_no","card_batch_no","card_type","auto_type","topup_date","notification_flag","topup_amount"})
public class AutoTopUpRelation implements IComplexEntity{


	@XmlElement(name="auto_id")
	private String auto_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="_low_balance_threshold")
	private Long _low_balance_threshold;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="pay_channel")
	private Short pay_channel;

	@XmlElement(name="bank_acct_id")
	private String bank_acct_id;

	@XmlElement(name="bank_acct_name")
	private String bank_acct_name;

	@XmlElement(name="credit_card_no")
	private String credit_card_no;

	@XmlElement(name="bank_code")
	private Integer bank_code;

	@XmlElement(name="additional_info")
	private String additional_info;

	@XmlElement(name="card_serial_no")
	private String card_serial_no;

	@XmlElement(name="card_batch_no")
	private String card_batch_no;

	@XmlElement(name="card_type")
	private Short card_type;

	@XmlElement(name="auto_type")
	private Short auto_type;

	@XmlElement(name="topup_date")
	private Short topup_date;

	@XmlElement(name="notification_flag")
	private Short notification_flag;

	@XmlElement(name="topup_amount")
	private Long topup_amount;

	public void setAuto_id(String obj){
		this.auto_id = obj;
	}

	public String getAuto_id(){
		return auto_id;
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

	public void setLow_balance_threshold(Long obj){
		this._low_balance_threshold = obj;
	}

	public Long getLow_balance_threshold(){
		return _low_balance_threshold;
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

	public void setPay_channel(Short obj){
		this.pay_channel = obj;
	}

	public Short getPay_channel(){
		return pay_channel;
	}

	public void setBank_acct_id(String obj){
		this.bank_acct_id = obj;
	}

	public String getBank_acct_id(){
		return bank_acct_id;
	}

	public void setBank_acct_name(String obj){
		this.bank_acct_name = obj;
	}

	public String getBank_acct_name(){
		return bank_acct_name;
	}

	public void setCredit_card_no(String obj){
		this.credit_card_no = obj;
	}

	public String getCredit_card_no(){
		return credit_card_no;
	}

	public void setBank_code(Integer obj){
		this.bank_code = obj;
	}

	public Integer getBank_code(){
		return bank_code;
	}

	public void setAdditional_info(String obj){
		this.additional_info = obj;
	}

	public String getAdditional_info(){
		return additional_info;
	}

	public void setCard_serial_no(String obj){
		this.card_serial_no = obj;
	}

	public String getCard_serial_no(){
		return card_serial_no;
	}

	public void setCard_batch_no(String obj){
		this.card_batch_no = obj;
	}

	public String getCard_batch_no(){
		return card_batch_no;
	}

	public void setCard_type(Short obj){
		this.card_type = obj;
	}

	public Short getCard_type(){
		return card_type;
	}

	public void setAuto_type(Short obj){
		this.auto_type = obj;
	}

	public Short getAuto_type(){
		return auto_type;
	}

	public void setTopup_date(Short obj){
		this.topup_date = obj;
	}

	public Short getTopup_date(){
		return topup_date;
	}

	public void setNotification_flag(Short obj){
		this.notification_flag = obj;
	}

	public Short getNotification_flag(){
		return notification_flag;
	}

	public void setTopup_amount(Long obj){
		this.topup_amount = obj;
	}

	public Long getTopup_amount(){
		return topup_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AutoTopUpRelation rhs=(AutoTopUpRelation)rhs0;
		if(!ObjectUtils.equals(auto_id, rhs.auto_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(_low_balance_threshold, rhs._low_balance_threshold)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(pay_channel, rhs.pay_channel)) return false;
		if(!ObjectUtils.equals(bank_acct_id, rhs.bank_acct_id)) return false;
		if(!ObjectUtils.equals(bank_acct_name, rhs.bank_acct_name)) return false;
		if(!ObjectUtils.equals(credit_card_no, rhs.credit_card_no)) return false;
		if(!ObjectUtils.equals(bank_code, rhs.bank_code)) return false;
		if(!ObjectUtils.equals(additional_info, rhs.additional_info)) return false;
		if(!ObjectUtils.equals(card_serial_no, rhs.card_serial_no)) return false;
		if(!ObjectUtils.equals(card_batch_no, rhs.card_batch_no)) return false;
		if(!ObjectUtils.equals(card_type, rhs.card_type)) return false;
		if(!ObjectUtils.equals(auto_type, rhs.auto_type)) return false;
		if(!ObjectUtils.equals(topup_date, rhs.topup_date)) return false;
		if(!ObjectUtils.equals(notification_flag, rhs.notification_flag)) return false;
		if(!ObjectUtils.equals(topup_amount, rhs.topup_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(auto_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(_low_balance_threshold)
		.append(valid_date)
		.append(expire_date)
		.append(pay_channel)
		.append(bank_acct_id)
		.append(bank_acct_name)
		.append(credit_card_no)
		.append(bank_code)
		.append(additional_info)
		.append(card_serial_no)
		.append(card_batch_no)
		.append(card_type)
		.append(auto_type)
		.append(topup_date)
		.append(notification_flag)
		.append(topup_amount)
		.toHashCode();
	}


}