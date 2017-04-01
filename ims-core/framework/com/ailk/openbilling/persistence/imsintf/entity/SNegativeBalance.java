package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"book_item","acct_id","user_id","phone_id","negative_balance","remain_negative_balance","negative_valid_date","negative_expire_date","book_item_type"})
public class SNegativeBalance implements IComplexEntity{


	@XmlElement(name="book_item")
	private Integer book_item;

	@XmlElement(name="acct_id")
	private String acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="negative_balance")
	private Long negative_balance;

	@XmlElement(name="remain_negative_balance")
	private Long remain_negative_balance;

	@XmlElement(name="negative_valid_date")
	private Date negative_valid_date;

	@XmlElement(name="negative_expire_date")
	private Date negative_expire_date;

	@XmlElement(name="book_item_type")
	private String book_item_type;

	public void setBook_item(Integer obj){
		this.book_item = obj;
	}

	public Integer getBook_item(){
		return book_item;
	}

	public void setAcct_id(String obj){
		this.acct_id = obj;
	}

	public String getAcct_id(){
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

	public void setNegative_balance(Long obj){
		this.negative_balance = obj;
	}

	public Long getNegative_balance(){
		return negative_balance;
	}

	public void setRemain_negative_balance(Long obj){
		this.remain_negative_balance = obj;
	}

	public Long getRemain_negative_balance(){
		return remain_negative_balance;
	}

	public void setNegative_valid_date(Date obj){
		this.negative_valid_date = obj;
	}

	public Date getNegative_valid_date(){
		return negative_valid_date;
	}

	public void setNegative_expire_date(Date obj){
		this.negative_expire_date = obj;
	}

	public Date getNegative_expire_date(){
		return negative_expire_date;
	}

	public void setBook_item_type(String obj){
		this.book_item_type = obj;
	}

	public String getBook_item_type(){
		return book_item_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNegativeBalance rhs=(SNegativeBalance)rhs0;
		if(!ObjectUtils.equals(book_item, rhs.book_item)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(negative_balance, rhs.negative_balance)) return false;
		if(!ObjectUtils.equals(remain_negative_balance, rhs.remain_negative_balance)) return false;
		if(!ObjectUtils.equals(negative_valid_date, rhs.negative_valid_date)) return false;
		if(!ObjectUtils.equals(negative_expire_date, rhs.negative_expire_date)) return false;
		if(!ObjectUtils.equals(book_item_type, rhs.book_item_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(book_item)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(negative_balance)
		.append(remain_negative_balance)
		.append(negative_valid_date)
		.append(negative_expire_date)
		.append(book_item_type)
		.toHashCode();
	}


}