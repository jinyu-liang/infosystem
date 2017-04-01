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
@XmlType(propOrder={"date_of_event","time_of_event","peak_offpeak","called_number","charging_number","operator","origination","destination","added_Service","unit","duration","country","grouping_name","prepaid_usage","postpaid_usage","pay_mode","roam_type","total_charge","pre_discount","measure_id"})
public class CallDetailRecord implements IComplexEntity{


	@XmlElement(name="date_of_event")
	private String date_of_event;

	@XmlElement(name="time_of_event")
	private String time_of_event;

	@XmlElement(name="peak_offpeak")
	private Short peak_offpeak;

	@XmlElement(name="called_number")
	private String called_number;

	@XmlElement(name="charging_number")
	private String charging_number;

	@XmlElement(name="operator")
	private String operator;

	@XmlElement(name="origination")
	private String origination;

	@XmlElement(name="destination")
	private String destination;

	@XmlElement(name="added_Service")
	private String added_Service;

	@XmlElement(name="unit")
	private String unit;

	@XmlElement(name="duration")
	private String duration;

	@XmlElement(name="country")
	private String country;

	@XmlElement(name="grouping_name")
	private String grouping_name;

	@XmlElement(name="prepaid_usage")
	private Long prepaid_usage;

	@XmlElement(name="postpaid_usage")
	private Long postpaid_usage;

	@XmlElement(name="pay_mode")
	private Short pay_mode;

	@XmlElement(name="roam_type")
	private Short roam_type;

	@XmlElement(name="total_charge")
	private Long total_charge;

	@XmlElement(name="pre_discount")
	private Long pre_discount;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setDate_of_event(String obj){
		this.date_of_event = obj;
	}

	public String getDate_of_event(){
		return date_of_event;
	}

	public void setTime_of_event(String obj){
		this.time_of_event = obj;
	}

	public String getTime_of_event(){
		return time_of_event;
	}

	public void setPeak_offpeak(Short obj){
		this.peak_offpeak = obj;
	}

	public Short getPeak_offpeak(){
		return peak_offpeak;
	}

	public void setCalled_number(String obj){
		this.called_number = obj;
	}

	public String getCalled_number(){
		return called_number;
	}

	public void setCharging_number(String obj){
		this.charging_number = obj;
	}

	public String getCharging_number(){
		return charging_number;
	}

	public void setOperator(String obj){
		this.operator = obj;
	}

	public String getOperator(){
		return operator;
	}

	public void setOrigination(String obj){
		this.origination = obj;
	}

	public String getOrigination(){
		return origination;
	}

	public void setDestination(String obj){
		this.destination = obj;
	}

	public String getDestination(){
		return destination;
	}

	public void setAdded_Service(String obj){
		this.added_Service = obj;
	}

	public String getAdded_Service(){
		return added_Service;
	}

	public void setUnit(String obj){
		this.unit = obj;
	}

	public String getUnit(){
		return unit;
	}

	public void setDuration(String obj){
		this.duration = obj;
	}

	public String getDuration(){
		return duration;
	}

	public void setCountry(String obj){
		this.country = obj;
	}

	public String getCountry(){
		return country;
	}

	public void setGrouping_name(String obj){
		this.grouping_name = obj;
	}

	public String getGrouping_name(){
		return grouping_name;
	}

	public void setPrepaid_usage(Long obj){
		this.prepaid_usage = obj;
	}

	public Long getPrepaid_usage(){
		return prepaid_usage;
	}

	public void setPostpaid_usage(Long obj){
		this.postpaid_usage = obj;
	}

	public Long getPostpaid_usage(){
		return postpaid_usage;
	}

	public void setPay_mode(Short obj){
		this.pay_mode = obj;
	}

	public Short getPay_mode(){
		return pay_mode;
	}

	public void setRoam_type(Short obj){
		this.roam_type = obj;
	}

	public Short getRoam_type(){
		return roam_type;
	}

	public void setTotal_charge(Long obj){
		this.total_charge = obj;
	}

	public Long getTotal_charge(){
		return total_charge;
	}

	public void setPre_discount(Long obj){
		this.pre_discount = obj;
	}

	public Long getPre_discount(){
		return pre_discount;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CallDetailRecord rhs=(CallDetailRecord)rhs0;
		if(!ObjectUtils.equals(date_of_event, rhs.date_of_event)) return false;
		if(!ObjectUtils.equals(time_of_event, rhs.time_of_event)) return false;
		if(!ObjectUtils.equals(peak_offpeak, rhs.peak_offpeak)) return false;
		if(!ObjectUtils.equals(called_number, rhs.called_number)) return false;
		if(!ObjectUtils.equals(charging_number, rhs.charging_number)) return false;
		if(!ObjectUtils.equals(operator, rhs.operator)) return false;
		if(!ObjectUtils.equals(origination, rhs.origination)) return false;
		if(!ObjectUtils.equals(destination, rhs.destination)) return false;
		if(!ObjectUtils.equals(added_Service, rhs.added_Service)) return false;
		if(!ObjectUtils.equals(unit, rhs.unit)) return false;
		if(!ObjectUtils.equals(duration, rhs.duration)) return false;
		if(!ObjectUtils.equals(country, rhs.country)) return false;
		if(!ObjectUtils.equals(grouping_name, rhs.grouping_name)) return false;
		if(!ObjectUtils.equals(prepaid_usage, rhs.prepaid_usage)) return false;
		if(!ObjectUtils.equals(postpaid_usage, rhs.postpaid_usage)) return false;
		if(!ObjectUtils.equals(pay_mode, rhs.pay_mode)) return false;
		if(!ObjectUtils.equals(roam_type, rhs.roam_type)) return false;
		if(!ObjectUtils.equals(total_charge, rhs.total_charge)) return false;
		if(!ObjectUtils.equals(pre_discount, rhs.pre_discount)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(date_of_event)
		.append(time_of_event)
		.append(peak_offpeak)
		.append(called_number)
		.append(charging_number)
		.append(operator)
		.append(origination)
		.append(destination)
		.append(added_Service)
		.append(unit)
		.append(duration)
		.append(country)
		.append(grouping_name)
		.append(prepaid_usage)
		.append(postpaid_usage)
		.append(pay_mode)
		.append(roam_type)
		.append(total_charge)
		.append(pre_discount)
		.append(measure_id)
		.toHashCode();
	}


}