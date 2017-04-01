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
@XmlType(propOrder={"book_item","amount_after","days_request","days_adjust","outer_acct_id","acct_id","user_id","phone_id","valid_date","expiredate_after","expiredate_before","amount_before","amount_request","amount_adjust"})
public class SBalanceAdjustResponse implements IComplexEntity{


	@XmlElement(name="book_item")
	private Integer book_item;

	@XmlElement(name="amount_after")
	private Long amount_after;

	@XmlElement(name="days_request")
	private Long days_request;

	@XmlElement(name="days_adjust")
	private Long days_adjust;

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

	@XmlElement(name="expiredate_after")
	private String expiredate_after;

	@XmlElement(name="expiredate_before")
	private String expiredate_before;

	@XmlElement(name="amount_before")
	private Long amount_before;

	@XmlElement(name="amount_request")
	private Long amount_request;

	@XmlElement(name="amount_adjust")
	private Long amount_adjust;

	public void setBook_item(Integer obj){
		this.book_item = obj;
	}

	public Integer getBook_item(){
		return book_item;
	}

	public void setAmount_after(Long obj){
		this.amount_after = obj;
	}

	public Long getAmount_after(){
		return amount_after;
	}

	public void setDays_request(Long obj){
		this.days_request = obj;
	}

	public Long getDays_request(){
		return days_request;
	}

	public void setDays_adjust(Long obj){
		this.days_adjust = obj;
	}

	public Long getDays_adjust(){
		return days_adjust;
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

	public void setExpiredate_after(String obj){
		this.expiredate_after = obj;
	}

	public String getExpiredate_after(){
		return expiredate_after;
	}

	public void setExpiredate_before(String obj){
		this.expiredate_before = obj;
	}

	public String getExpiredate_before(){
		return expiredate_before;
	}

	public void setAmount_before(Long obj){
		this.amount_before = obj;
	}

	public Long getAmount_before(){
		return amount_before;
	}

	public void setAmount_request(Long obj){
		this.amount_request = obj;
	}

	public Long getAmount_request(){
		return amount_request;
	}

	public void setAmount_adjust(Long obj){
		this.amount_adjust = obj;
	}

	public Long getAmount_adjust(){
		return amount_adjust;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBalanceAdjustResponse rhs=(SBalanceAdjustResponse)rhs0;
		if(!ObjectUtils.equals(book_item, rhs.book_item)) return false;
		if(!ObjectUtils.equals(amount_after, rhs.amount_after)) return false;
		if(!ObjectUtils.equals(days_request, rhs.days_request)) return false;
		if(!ObjectUtils.equals(days_adjust, rhs.days_adjust)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expiredate_after, rhs.expiredate_after)) return false;
		if(!ObjectUtils.equals(expiredate_before, rhs.expiredate_before)) return false;
		if(!ObjectUtils.equals(amount_before, rhs.amount_before)) return false;
		if(!ObjectUtils.equals(amount_request, rhs.amount_request)) return false;
		if(!ObjectUtils.equals(amount_adjust, rhs.amount_adjust)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(book_item)
		.append(amount_after)
		.append(days_request)
		.append(days_adjust)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(valid_date)
		.append(expiredate_after)
		.append(expiredate_before)
		.append(amount_before)
		.append(amount_request)
		.append(amount_adjust)
		.toHashCode();
	}


}