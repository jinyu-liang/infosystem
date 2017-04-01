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
@XmlType(propOrder={"phone_id","valid_date","expire_date","effect_time","expire_time","week_start","week_stop","route_number","ir_route_flag","routing_method","list_type"})
public class SCallScreenNo implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="effect_time")
	private String effect_time;

	@XmlElement(name="expire_time")
	private String expire_time;

	@XmlElement(name="week_start")
	private Short week_start;

	@XmlElement(name="week_stop")
	private Short week_stop;

	@XmlElement(name="route_number")
	private String route_number;

	@XmlElement(name="ir_route_flag")
	private Short ir_route_flag;

	@XmlElement(name="routing_method")
	private Short routing_method;

	@XmlElement(name="list_type")
	private Short list_type;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
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

	public void setEffect_time(String obj){
		this.effect_time = obj;
	}

	public String getEffect_time(){
		return effect_time;
	}

	public void setExpire_time(String obj){
		this.expire_time = obj;
	}

	public String getExpire_time(){
		return expire_time;
	}

	public void setWeek_start(Short obj){
		this.week_start = obj;
	}

	public Short getWeek_start(){
		return week_start;
	}

	public void setWeek_stop(Short obj){
		this.week_stop = obj;
	}

	public Short getWeek_stop(){
		return week_stop;
	}

	public void setRoute_number(String obj){
		this.route_number = obj;
	}

	public String getRoute_number(){
		return route_number;
	}

	public void setIr_route_flag(Short obj){
		this.ir_route_flag = obj;
	}

	public Short getIr_route_flag(){
		return ir_route_flag;
	}

	public void setRouting_method(Short obj){
		this.routing_method = obj;
	}

	public Short getRouting_method(){
		return routing_method;
	}

	public void setList_type(Short obj){
		this.list_type = obj;
	}

	public Short getList_type(){
		return list_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCallScreenNo rhs=(SCallScreenNo)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(effect_time, rhs.effect_time)) return false;
		if(!ObjectUtils.equals(expire_time, rhs.expire_time)) return false;
		if(!ObjectUtils.equals(week_start, rhs.week_start)) return false;
		if(!ObjectUtils.equals(week_stop, rhs.week_stop)) return false;
		if(!ObjectUtils.equals(route_number, rhs.route_number)) return false;
		if(!ObjectUtils.equals(ir_route_flag, rhs.ir_route_flag)) return false;
		if(!ObjectUtils.equals(routing_method, rhs.routing_method)) return false;
		if(!ObjectUtils.equals(list_type, rhs.list_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(valid_date)
		.append(expire_date)
		.append(effect_time)
		.append(expire_time)
		.append(week_start)
		.append(week_stop)
		.append(route_number)
		.append(ir_route_flag)
		.append(routing_method)
		.append(list_type)
		.toHashCode();
	}


}