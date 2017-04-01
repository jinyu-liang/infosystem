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
@XmlType(propOrder={"main_promotion","start_date_time","end_date_time","total_charge","duration","callDetailList"})
public class RealTimeCallCharge implements IComplexEntity{


	@XmlElement(name="callDetailList")
	private CallDetailList callDetailList;

	@XmlElement(name="main_promotion")
	private Integer main_promotion;

	@XmlElement(name="start_date_time")
	private String start_date_time;

	@XmlElement(name="end_date_time")
	private String end_date_time;

	@XmlElement(name="total_charge")
	private Long total_charge;

	@XmlElement(name="duration")
	private Integer duration;

	public void setCallDetailList(CallDetailList obj){
		this.callDetailList = obj;
	}

	public CallDetailList getCallDetailList(){
		return callDetailList;
	}

	public void setMain_promotion(Integer obj){
		this.main_promotion = obj;
	}

	public Integer getMain_promotion(){
		return main_promotion;
	}

	public void setStart_date_time(String obj){
		this.start_date_time = obj;
	}

	public String getStart_date_time(){
		return start_date_time;
	}

	public void setEnd_date_time(String obj){
		this.end_date_time = obj;
	}

	public String getEnd_date_time(){
		return end_date_time;
	}

	public void setTotal_charge(Long obj){
		this.total_charge = obj;
	}

	public Long getTotal_charge(){
		return total_charge;
	}

	public void setDuration(Integer obj){
		this.duration = obj;
	}

	public Integer getDuration(){
		return duration;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RealTimeCallCharge rhs=(RealTimeCallCharge)rhs0;
		if(!ObjectUtils.equals(callDetailList, rhs.callDetailList)) return false;
		if(!ObjectUtils.equals(main_promotion, rhs.main_promotion)) return false;
		if(!ObjectUtils.equals(start_date_time, rhs.start_date_time)) return false;
		if(!ObjectUtils.equals(end_date_time, rhs.end_date_time)) return false;
		if(!ObjectUtils.equals(total_charge, rhs.total_charge)) return false;
		if(!ObjectUtils.equals(duration, rhs.duration)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(callDetailList)
		.append(main_promotion)
		.append(start_date_time)
		.append(end_date_time)
		.append(total_charge)
		.append(duration)
		.toHashCode();
	}


}