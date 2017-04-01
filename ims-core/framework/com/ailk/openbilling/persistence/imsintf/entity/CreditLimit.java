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
@XmlType(propOrder={"creditItemId","credit","remain_credit","temp_credit","advance_payment","usage","unpaid_payment","credit_type","expire_date","measure_id","temp_flag","unlimited_flag","last_bill_date","next_bill_date","recurringFee","onetimeFee","unbilled_amount","exempt_credit_flag","exempt_valid_date","exempt_expire_date"})
public class CreditLimit implements IComplexEntity{


	@XmlElement(name="creditItemId")
	private Integer creditItemId;

	@XmlElement(name="credit")
	private Double credit;

	@XmlElement(name="remain_credit")
	private Double remain_credit;

	@XmlElement(name="temp_credit")
	private Double temp_credit;

	@XmlElement(name="advance_payment")
	private Double advance_payment;

	@XmlElement(name="usage")
	private Double usage;

	@XmlElement(name="unpaid_payment")
	private Double unpaid_payment;

	@XmlElement(name="credit_type")
	private Short credit_type;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="temp_flag")
	private Short temp_flag;

	@XmlElement(name="unlimited_flag")
	private Short unlimited_flag;

	@XmlElement(name="last_bill_date")
	private String last_bill_date;

	@XmlElement(name="next_bill_date")
	private String next_bill_date;

	@XmlElement(name="recurringFee")
	private Double recurringFee;

	@XmlElement(name="onetimeFee")
	private Double onetimeFee;

	@XmlElement(name="unbilled_amount")
	private Double unbilled_amount;

	@XmlElement(name="exempt_credit_flag")
	private Short exempt_credit_flag;

	@XmlElement(name="exempt_valid_date")
	private String exempt_valid_date;

	@XmlElement(name="exempt_expire_date")
	private String exempt_expire_date;

	public void setCreditItemId(Integer obj){
		this.creditItemId = obj;
	}

	public Integer getCreditItemId(){
		return creditItemId;
	}

	public void setCredit(Double obj){
		this.credit = obj;
	}

	public Double getCredit(){
		return credit;
	}

	public void setRemain_credit(Double obj){
		this.remain_credit = obj;
	}

	public Double getRemain_credit(){
		return remain_credit;
	}

	public void setTemp_credit(Double obj){
		this.temp_credit = obj;
	}

	public Double getTemp_credit(){
		return temp_credit;
	}

	public void setAdvance_payment(Double obj){
		this.advance_payment = obj;
	}

	public Double getAdvance_payment(){
		return advance_payment;
	}

	public void setUsage(Double obj){
		this.usage = obj;
	}

	public Double getUsage(){
		return usage;
	}

	public void setUnpaid_payment(Double obj){
		this.unpaid_payment = obj;
	}

	public Double getUnpaid_payment(){
		return unpaid_payment;
	}

	public void setCredit_type(Short obj){
		this.credit_type = obj;
	}

	public Short getCredit_type(){
		return credit_type;
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

	public void setTemp_flag(Short obj){
		this.temp_flag = obj;
	}

	public Short getTemp_flag(){
		return temp_flag;
	}

	public void setUnlimited_flag(Short obj){
		this.unlimited_flag = obj;
	}

	public Short getUnlimited_flag(){
		return unlimited_flag;
	}

	public void setLast_bill_date(String obj){
		this.last_bill_date = obj;
	}

	public String getLast_bill_date(){
		return last_bill_date;
	}

	public void setNext_bill_date(String obj){
		this.next_bill_date = obj;
	}

	public String getNext_bill_date(){
		return next_bill_date;
	}

	public void setRecurringFee(Double obj){
		this.recurringFee = obj;
	}

	public Double getRecurringFee(){
		return recurringFee;
	}

	public void setOnetimeFee(Double obj){
		this.onetimeFee = obj;
	}

	public Double getOnetimeFee(){
		return onetimeFee;
	}

	public void setUnbilled_amount(Double obj){
		this.unbilled_amount = obj;
	}

	public Double getUnbilled_amount(){
		return unbilled_amount;
	}

	public void setExempt_credit_flag(Short obj){
		this.exempt_credit_flag = obj;
	}

	public Short getExempt_credit_flag(){
		return exempt_credit_flag;
	}

	public void setExempt_valid_date(String obj){
		this.exempt_valid_date = obj;
	}

	public String getExempt_valid_date(){
		return exempt_valid_date;
	}

	public void setExempt_expire_date(String obj){
		this.exempt_expire_date = obj;
	}

	public String getExempt_expire_date(){
		return exempt_expire_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CreditLimit rhs=(CreditLimit)rhs0;
		if(!ObjectUtils.equals(creditItemId, rhs.creditItemId)) return false;
		if(!ObjectUtils.equals(credit, rhs.credit)) return false;
		if(!ObjectUtils.equals(remain_credit, rhs.remain_credit)) return false;
		if(!ObjectUtils.equals(temp_credit, rhs.temp_credit)) return false;
		if(!ObjectUtils.equals(advance_payment, rhs.advance_payment)) return false;
		if(!ObjectUtils.equals(usage, rhs.usage)) return false;
		if(!ObjectUtils.equals(unpaid_payment, rhs.unpaid_payment)) return false;
		if(!ObjectUtils.equals(credit_type, rhs.credit_type)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(temp_flag, rhs.temp_flag)) return false;
		if(!ObjectUtils.equals(unlimited_flag, rhs.unlimited_flag)) return false;
		if(!ObjectUtils.equals(last_bill_date, rhs.last_bill_date)) return false;
		if(!ObjectUtils.equals(next_bill_date, rhs.next_bill_date)) return false;
		if(!ObjectUtils.equals(recurringFee, rhs.recurringFee)) return false;
		if(!ObjectUtils.equals(onetimeFee, rhs.onetimeFee)) return false;
		if(!ObjectUtils.equals(unbilled_amount, rhs.unbilled_amount)) return false;
		if(!ObjectUtils.equals(exempt_credit_flag, rhs.exempt_credit_flag)) return false;
		if(!ObjectUtils.equals(exempt_valid_date, rhs.exempt_valid_date)) return false;
		if(!ObjectUtils.equals(exempt_expire_date, rhs.exempt_expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(creditItemId)
		.append(credit)
		.append(remain_credit)
		.append(temp_credit)
		.append(advance_payment)
		.append(usage)
		.append(unpaid_payment)
		.append(credit_type)
		.append(expire_date)
		.append(measure_id)
		.append(temp_flag)
		.append(unlimited_flag)
		.append(last_bill_date)
		.append(next_bill_date)
		.append(recurringFee)
		.append(onetimeFee)
		.append(unbilled_amount)
		.append(exempt_credit_flag)
		.append(exempt_valid_date)
		.append(exempt_expire_date)
		.toHashCode();
	}


}