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
@XmlType(propOrder={"event_type","real_total_event_type","total_event_type","real_duration","total_charge_domestic","total_charge_domestic_vat","total_charge_international"})
public class CallDetail implements IComplexEntity{


	@XmlElement(name="event_type")
	private int event_type;

	@XmlElement(name="real_total_event_type")
	private int real_total_event_type;

	@XmlElement(name="total_event_type")
	private int total_event_type;

	@XmlElement(name="real_duration")
	private int real_duration;

	@XmlElement(name="total_charge_domestic")
	private Long total_charge_domestic;

	@XmlElement(name="total_charge_domestic_vat")
	private Long total_charge_domestic_vat;

	@XmlElement(name="total_charge_international")
	private Long total_charge_international;

	public void setEvent_type(int obj){
		this.event_type = obj;
	}

	public int getEvent_type(){
		return event_type;
	}

	public void setReal_total_event_type(int obj){
		this.real_total_event_type = obj;
	}

	public int getReal_total_event_type(){
		return real_total_event_type;
	}

	public void setTotal_event_type(int obj){
		this.total_event_type = obj;
	}

	public int getTotal_event_type(){
		return total_event_type;
	}

	public void setReal_duration(int obj){
		this.real_duration = obj;
	}

	public int getReal_duration(){
		return real_duration;
	}

	public void setTotal_charge_domestic(Long obj){
		this.total_charge_domestic = obj;
	}

	public Long getTotal_charge_domestic(){
		return total_charge_domestic;
	}

	public void setTotal_charge_domestic_vat(Long obj){
		this.total_charge_domestic_vat = obj;
	}

	public Long getTotal_charge_domestic_vat(){
		return total_charge_domestic_vat;
	}

	public void setTotal_charge_international(Long obj){
		this.total_charge_international = obj;
	}

	public Long getTotal_charge_international(){
		return total_charge_international;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CallDetail rhs=(CallDetail)rhs0;
		if(!ObjectUtils.equals(event_type, rhs.event_type)) return false;
		if(!ObjectUtils.equals(real_total_event_type, rhs.real_total_event_type)) return false;
		if(!ObjectUtils.equals(total_event_type, rhs.total_event_type)) return false;
		if(!ObjectUtils.equals(real_duration, rhs.real_duration)) return false;
		if(!ObjectUtils.equals(total_charge_domestic, rhs.total_charge_domestic)) return false;
		if(!ObjectUtils.equals(total_charge_domestic_vat, rhs.total_charge_domestic_vat)) return false;
		if(!ObjectUtils.equals(total_charge_international, rhs.total_charge_international)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(event_type)
		.append(real_total_event_type)
		.append(total_event_type)
		.append(real_duration)
		.append(total_charge_domestic)
		.append(total_charge_domestic_vat)
		.append(total_charge_international)
		.toHashCode();
	}


}