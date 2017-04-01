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
@XmlType(propOrder={"book_id","book_item","amount","days","outer_acct_id","acct_id","user_id","phone_id","valid_date","expire_date","negative_valid_date","negative_expire_date","max_balance","max_validity","negative_balance","remain_negative_balance","book_item_type","measure_id","used_main_amount","used_negative_amount","total_negative_amount","total_negative_remaining","asset_id"})
public class SBalance implements IComplexEntity{


	@XmlElement(name="book_id")
	private Long book_id;

	@XmlElement(name="book_item")
	private Long book_item;

	@XmlElement(name="amount")
	private Double amount;

	@XmlElement(name="days")
	private Integer days;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="negative_valid_date")
	private String negative_valid_date;

	@XmlElement(name="negative_expire_date")
	private String negative_expire_date;

	@XmlElement(name="max_balance")
	private Double max_balance;

	@XmlElement(name="max_validity")
	private Long max_validity;

	@XmlElement(name="negative_balance")
	private Double negative_balance;

	@XmlElement(name="remain_negative_balance")
	private Double remain_negative_balance;

	@XmlElement(name="book_item_type")
	private Short book_item_type;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="used_main_amount")
	private Long used_main_amount;

	@XmlElement(name="used_negative_amount")
	private Long used_negative_amount;

	@XmlElement(name="total_negative_amount")
	private Long total_negative_amount;

	@XmlElement(name="total_negative_remaining")
	private Long total_negative_remaining;

	@XmlElement(name="asset_id")
	private Long asset_id;

	public void setBook_id(Long obj){
		this.book_id = obj;
	}

	public Long getBook_id(){
		return book_id;
	}

	public void setBook_item(Long obj){
		this.book_item = obj;
	}

	public Long getBook_item(){
		return book_item;
	}

	public void setAmount(Double obj){
		this.amount = obj;
	}

	public Double getAmount(){
		return amount;
	}

	public void setDays(Integer obj){
		this.days = obj;
	}

	public Integer getDays(){
		return days;
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

	public void setNegative_valid_date(String obj){
		this.negative_valid_date = obj;
	}

	public String getNegative_valid_date(){
		return negative_valid_date;
	}

	public void setNegative_expire_date(String obj){
		this.negative_expire_date = obj;
	}

	public String getNegative_expire_date(){
		return negative_expire_date;
	}

	public void setMax_balance(Double obj){
		this.max_balance = obj;
	}

	public Double getMax_balance(){
		return max_balance;
	}

	public void setMax_validity(Long obj){
		this.max_validity = obj;
	}

	public Long getMax_validity(){
		return max_validity;
	}

	public void setNegative_balance(Double obj){
		this.negative_balance = obj;
	}

	public Double getNegative_balance(){
		return negative_balance;
	}

	public void setRemain_negative_balance(Double obj){
		this.remain_negative_balance = obj;
	}

	public Double getRemain_negative_balance(){
		return remain_negative_balance;
	}

	public void setBook_item_type(Short obj){
		this.book_item_type = obj;
	}

	public Short getBook_item_type(){
		return book_item_type;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public void setUsed_main_amount(Long obj){
		this.used_main_amount = obj;
	}

	public Long getUsed_main_amount(){
		return used_main_amount;
	}

	public void setUsed_negative_amount(Long obj){
		this.used_negative_amount = obj;
	}

	public Long getUsed_negative_amount(){
		return used_negative_amount;
	}

	public void setTotal_negative_amount(Long obj){
		this.total_negative_amount = obj;
	}

	public Long getTotal_negative_amount(){
		return total_negative_amount;
	}

	public void setTotal_negative_remaining(Long obj){
		this.total_negative_remaining = obj;
	}

	public Long getTotal_negative_remaining(){
		return total_negative_remaining;
	}

	public void setAsset_id(Long obj){
		this.asset_id = obj;
	}

	public Long getAsset_id(){
		return asset_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBalance rhs=(SBalance)rhs0;
		if(!ObjectUtils.equals(book_id, rhs.book_id)) return false;
		if(!ObjectUtils.equals(book_item, rhs.book_item)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(days, rhs.days)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(negative_valid_date, rhs.negative_valid_date)) return false;
		if(!ObjectUtils.equals(negative_expire_date, rhs.negative_expire_date)) return false;
		if(!ObjectUtils.equals(max_balance, rhs.max_balance)) return false;
		if(!ObjectUtils.equals(max_validity, rhs.max_validity)) return false;
		if(!ObjectUtils.equals(negative_balance, rhs.negative_balance)) return false;
		if(!ObjectUtils.equals(remain_negative_balance, rhs.remain_negative_balance)) return false;
		if(!ObjectUtils.equals(book_item_type, rhs.book_item_type)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(used_main_amount, rhs.used_main_amount)) return false;
		if(!ObjectUtils.equals(used_negative_amount, rhs.used_negative_amount)) return false;
		if(!ObjectUtils.equals(total_negative_amount, rhs.total_negative_amount)) return false;
		if(!ObjectUtils.equals(total_negative_remaining, rhs.total_negative_remaining)) return false;
		if(!ObjectUtils.equals(asset_id, rhs.asset_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(book_id)
		.append(book_item)
		.append(amount)
		.append(days)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(valid_date)
		.append(expire_date)
		.append(negative_valid_date)
		.append(negative_expire_date)
		.append(max_balance)
		.append(max_validity)
		.append(negative_balance)
		.append(remain_negative_balance)
		.append(book_item_type)
		.append(measure_id)
		.append(used_main_amount)
		.append(used_negative_amount)
		.append(total_negative_amount)
		.append(total_negative_remaining)
		.append(asset_id)
		.toHashCode();
	}


}