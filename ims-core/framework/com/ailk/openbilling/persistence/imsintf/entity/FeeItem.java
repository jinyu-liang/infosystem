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
@XmlType(propOrder={"order_type","item_code","cost_code","charge_type","amount","bill_type","so_onetime_id","onetime_seq","remark","measure_id"})
public class FeeItem implements IComplexEntity{


	@XmlElement(name="order_type")
	private Short order_type;

	@XmlElement(name="item_code")
	private Long item_code;

	@XmlElement(name="cost_code")
	private Integer cost_code;

	@XmlElement(name="charge_type")
	private Short charge_type;

	@XmlElement(name="amount")
	private Double amount;

	@XmlElement(name="bill_type")
	private Integer bill_type;

	@XmlElement(name="so_onetime_id")
	private Long so_onetime_id;

	@XmlElement(name="onetime_seq")
	private Long onetime_seq;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setOrder_type(Short obj){
		this.order_type = obj;
	}

	public Short getOrder_type(){
		return order_type;
	}

	public void setItem_code(Long obj){
		this.item_code = obj;
	}

	public Long getItem_code(){
		return item_code;
	}

	public void setCost_code(Integer obj){
		this.cost_code = obj;
	}

	public Integer getCost_code(){
		return cost_code;
	}

	public void setCharge_type(Short obj){
		this.charge_type = obj;
	}

	public Short getCharge_type(){
		return charge_type;
	}

	public void setAmount(Double obj){
		this.amount = obj;
	}

	public Double getAmount(){
		return amount;
	}

	public void setBill_type(Integer obj){
		this.bill_type = obj;
	}

	public Integer getBill_type(){
		return bill_type;
	}

	public void setSo_onetime_id(Long obj){
		this.so_onetime_id = obj;
	}

	public Long getSo_onetime_id(){
		return so_onetime_id;
	}

	public void setOnetime_seq(Long obj){
		this.onetime_seq = obj;
	}

	public Long getOnetime_seq(){
		return onetime_seq;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FeeItem rhs=(FeeItem)rhs0;
		if(!ObjectUtils.equals(order_type, rhs.order_type)) return false;
		if(!ObjectUtils.equals(item_code, rhs.item_code)) return false;
		if(!ObjectUtils.equals(cost_code, rhs.cost_code)) return false;
		if(!ObjectUtils.equals(charge_type, rhs.charge_type)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(bill_type, rhs.bill_type)) return false;
		if(!ObjectUtils.equals(so_onetime_id, rhs.so_onetime_id)) return false;
		if(!ObjectUtils.equals(onetime_seq, rhs.onetime_seq)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(order_type)
		.append(item_code)
		.append(cost_code)
		.append(charge_type)
		.append(amount)
		.append(bill_type)
		.append(so_onetime_id)
		.append(onetime_seq)
		.append(remark)
		.append(measure_id)
		.toHashCode();
	}


}