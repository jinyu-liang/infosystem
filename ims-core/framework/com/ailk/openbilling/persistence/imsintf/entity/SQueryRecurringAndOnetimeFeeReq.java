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
@XmlType(propOrder={"phone_id","product_sequence_id","start_date","end_date","fee_item"})
public class SQueryRecurringAndOnetimeFeeReq implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="start_date")
	private Date start_date;

	@XmlElement(name="end_date")
	private Date end_date;

	@XmlElement(name="fee_item")
	private Integer fee_item;

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

	public void setStart_date(Date obj){
		this.start_date = obj;
	}

	public Date getStart_date(){
		return start_date;
	}

	public void setEnd_date(Date obj){
		this.end_date = obj;
	}

	public Date getEnd_date(){
		return end_date;
	}

	public void setFee_item(Integer obj){
		this.fee_item = obj;
	}

	public Integer getFee_item(){
		return fee_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryRecurringAndOnetimeFeeReq rhs=(SQueryRecurringAndOnetimeFeeReq)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(fee_item, rhs.fee_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(product_sequence_id)
		.append(start_date)
		.append(end_date)
		.append(fee_item)
		.toHashCode();
	}


}