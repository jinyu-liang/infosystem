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
@XmlType(propOrder={"phone_id","product_sequence_id","resource_id","resource_value","resource_name","effective_date","acct_id","valid_date","operation_date","source_system","transaction_id"})
public class MaxFreeResourceLog implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="resource_id")
	private Integer resource_id;

	@XmlElement(name="resource_value")
	private Long resource_value;

	@XmlElement(name="resource_name")
	private String resource_name;

	@XmlElement(name="effective_date")
	private Date effective_date;

	@XmlElement(name="acct_id")
	private String acct_id;

	@XmlElement(name="valid_date")
	private Date valid_date;

	@XmlElement(name="operation_date")
	private Date operation_date;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setProduct_sequence_id(Long obj){
		this.product_sequence_id = obj;
	}

	public Long getProduct_sequence_id(){
		return product_sequence_id;
	}

	public void setResource_id(Integer obj){
		this.resource_id = obj;
	}

	public Integer getResource_id(){
		return resource_id;
	}

	public void setResource_value(Long obj){
		this.resource_value = obj;
	}

	public Long getResource_value(){
		return resource_value;
	}

	public void setResource_name(String obj){
		this.resource_name = obj;
	}

	public String getResource_name(){
		return resource_name;
	}

	public void setEffective_date(Date obj){
		this.effective_date = obj;
	}

	public Date getEffective_date(){
		return effective_date;
	}

	public void setAcct_id(String obj){
		this.acct_id = obj;
	}

	public String getAcct_id(){
		return acct_id;
	}

	public void setValid_date(Date obj){
		this.valid_date = obj;
	}

	public Date getValid_date(){
		return valid_date;
	}

	public void setOperation_date(Date obj){
		this.operation_date = obj;
	}

	public Date getOperation_date(){
		return operation_date;
	}

	public void setSource_system(String obj){
		this.source_system = obj;
	}

	public String getSource_system(){
		return source_system;
	}

	public void setTransaction_id(String obj){
		this.transaction_id = obj;
	}

	public String getTransaction_id(){
		return transaction_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MaxFreeResourceLog rhs=(MaxFreeResourceLog)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(resource_value, rhs.resource_value)) return false;
		if(!ObjectUtils.equals(resource_name, rhs.resource_name)) return false;
		if(!ObjectUtils.equals(effective_date, rhs.effective_date)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(operation_date, rhs.operation_date)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(product_sequence_id)
		.append(resource_id)
		.append(resource_value)
		.append(resource_name)
		.append(effective_date)
		.append(acct_id)
		.append(valid_date)
		.append(operation_date)
		.append(source_system)
		.append(transaction_id)
		.toHashCode();
	}


}