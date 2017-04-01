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
@XmlType(propOrder={"max_free_time","used_free_time","remaining_free_time","free_flag","fee_amount","start_date","end_date","busi_spec_id"})
public class SQueryFreeTimes implements IComplexEntity{


	@XmlElement(name="max_free_time")
	private Integer max_free_time;

	@XmlElement(name="used_free_time")
	private Integer used_free_time;

	@XmlElement(name="remaining_free_time")
	private Integer remaining_free_time;

	@XmlElement(name="free_flag")
	private String free_flag;

	@XmlElement(name="fee_amount")
	private Integer fee_amount;

	@XmlElement(name="start_date")
	private Date start_date;

	@XmlElement(name="end_date")
	private Date end_date;

	@XmlElement(name="busi_spec_id")
	private Long busi_spec_id;

	public void setMax_free_time(Integer obj){
		this.max_free_time = obj;
	}

	public Integer getMax_free_time(){
		return max_free_time;
	}

	public void setUsed_free_time(Integer obj){
		this.used_free_time = obj;
	}

	public Integer getUsed_free_time(){
		return used_free_time;
	}

	public void setRemaining_free_time(Integer obj){
		this.remaining_free_time = obj;
	}

	public Integer getRemaining_free_time(){
		return remaining_free_time;
	}

	public void setFree_flag(String obj){
		this.free_flag = obj;
	}

	public String getFree_flag(){
		return free_flag;
	}

	public void setFee_amount(Integer obj){
		this.fee_amount = obj;
	}

	public Integer getFee_amount(){
		return fee_amount;
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

	public void setBusi_spec_id(Long obj){
		this.busi_spec_id = obj;
	}

	public Long getBusi_spec_id(){
		return busi_spec_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryFreeTimes rhs=(SQueryFreeTimes)rhs0;
		if(!ObjectUtils.equals(max_free_time, rhs.max_free_time)) return false;
		if(!ObjectUtils.equals(used_free_time, rhs.used_free_time)) return false;
		if(!ObjectUtils.equals(remaining_free_time, rhs.remaining_free_time)) return false;
		if(!ObjectUtils.equals(free_flag, rhs.free_flag)) return false;
		if(!ObjectUtils.equals(fee_amount, rhs.fee_amount)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(busi_spec_id, rhs.busi_spec_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(max_free_time)
		.append(used_free_time)
		.append(remaining_free_time)
		.append(free_flag)
		.append(fee_amount)
		.append(start_date)
		.append(end_date)
		.append(busi_spec_id)
		.toHashCode();
	}


}