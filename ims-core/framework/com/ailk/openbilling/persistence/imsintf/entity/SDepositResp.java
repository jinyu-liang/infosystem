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
@XmlType(propOrder={"book_item","oper_amount","remain_amount","valid_date","expire_date","measure_id"})
public class SDepositResp implements IComplexEntity{


	@XmlElement(name="book_item")
	private Integer book_item;

	@XmlElement(name="oper_amount")
	private Double oper_amount;

	@XmlElement(name="remain_amount")
	private Double remain_amount;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setBook_item(Integer obj){
		this.book_item = obj;
	}

	public Integer getBook_item(){
		return book_item;
	}

	public void setOper_amount(Double obj){
		this.oper_amount = obj;
	}

	public Double getOper_amount(){
		return oper_amount;
	}

	public void setRemain_amount(Double obj){
		this.remain_amount = obj;
	}

	public Double getRemain_amount(){
		return remain_amount;
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

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDepositResp rhs=(SDepositResp)rhs0;
		if(!ObjectUtils.equals(book_item, rhs.book_item)) return false;
		if(!ObjectUtils.equals(oper_amount, rhs.oper_amount)) return false;
		if(!ObjectUtils.equals(remain_amount, rhs.remain_amount)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(book_item)
		.append(oper_amount)
		.append(remain_amount)
		.append(valid_date)
		.append(expire_date)
		.append(measure_id)
		.toHashCode();
	}


}