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
@XmlType(propOrder={"bill_item","unpay_fee","discount_fee","adjust_fee","prim_fee","billDetaillist"})
public class BillItem implements IComplexEntity{


	@XmlElement(name="billDetaillist")
	private BillDetailList billDetaillist;

	@XmlElement(name="bill_item")
	private Integer bill_item;

	@XmlElement(name="unpay_fee")
	private long unpay_fee;

	@XmlElement(name="discount_fee")
	private Long discount_fee;

	@XmlElement(name="adjust_fee")
	private Long adjust_fee;

	@XmlElement(name="prim_fee")
	private Long prim_fee;

	public void setBillDetaillist(BillDetailList obj){
		this.billDetaillist = obj;
	}

	public BillDetailList getBillDetaillist(){
		return billDetaillist;
	}

	public void setBill_item(Integer obj){
		this.bill_item = obj;
	}

	public Integer getBill_item(){
		return bill_item;
	}

	public void setUnpay_fee(long obj){
		this.unpay_fee = obj;
	}

	public long getUnpay_fee(){
		return unpay_fee;
	}

	public void setDiscount_fee(Long obj){
		this.discount_fee = obj;
	}

	public Long getDiscount_fee(){
		return discount_fee;
	}

	public void setAdjust_fee(Long obj){
		this.adjust_fee = obj;
	}

	public Long getAdjust_fee(){
		return adjust_fee;
	}

	public void setPrim_fee(Long obj){
		this.prim_fee = obj;
	}

	public Long getPrim_fee(){
		return prim_fee;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillItem rhs=(BillItem)rhs0;
		if(!ObjectUtils.equals(billDetaillist, rhs.billDetaillist)) return false;
		if(!ObjectUtils.equals(bill_item, rhs.bill_item)) return false;
		if(!ObjectUtils.equals(unpay_fee, rhs.unpay_fee)) return false;
		if(!ObjectUtils.equals(discount_fee, rhs.discount_fee)) return false;
		if(!ObjectUtils.equals(adjust_fee, rhs.adjust_fee)) return false;
		if(!ObjectUtils.equals(prim_fee, rhs.prim_fee)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billDetaillist)
		.append(bill_item)
		.append(unpay_fee)
		.append(discount_fee)
		.append(adjust_fee)
		.append(prim_fee)
		.toHashCode();
	}


}