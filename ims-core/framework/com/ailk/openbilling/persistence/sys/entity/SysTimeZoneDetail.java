package com.ailk.openbilling.persistence.sys.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="sd",name="SYS_TIME_ZONE_DETAIL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"timeZoneId","zoneType","timeZoneOffset","startVal","endVal"})
public class SysTimeZoneDetail extends DataObject{


	/**
	 * time zone
	 */
	@Id
	@Column(name="TIME_ZONE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="timeZoneId")
	private Integer timeZoneId;

	/**
	 * 1:???
2:???
	 */
	@Column(name="ZONE_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="zoneType")
	private Integer zoneType;

	@Column(name="TIME_ZONE_OFFSET",columnDefinition="Varchar",length=5)
	@XmlElement(name="timeZoneOffset")
	private String timeZoneOffset;

	@Id
	@Column(name="START_VAL",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="startVal")
	private Long startVal;

	@Column(name="END_VAL",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="endVal")
	private Long endVal;

	public void setTimeZoneId(Integer obj){
		this.timeZoneId = obj;
	}

	public Integer getTimeZoneId(){
		return timeZoneId;
	}

	public void setZoneType(Integer obj){
		this.zoneType = obj;
	}

	public Integer getZoneType(){
		return zoneType;
	}

	public void setTimeZoneOffset(String obj){
		this.timeZoneOffset = obj;
	}

	public String getTimeZoneOffset(){
		return timeZoneOffset;
	}

	public void setStartVal(Long obj){
		this.startVal = obj;
	}

	public Long getStartVal(){
		return startVal;
	}

	public void setEndVal(Long obj){
		this.endVal = obj;
	}

	public Long getEndVal(){
		return endVal;
	}

	public SysTimeZoneDetail(){
	}

	public SysTimeZoneDetail(Integer timeZoneId,Long startVal){
		this.timeZoneId = timeZoneId;
		this.startVal = startVal;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysTimeZoneDetail rhs=(SysTimeZoneDetail)rhs0;
		if(!ObjectUtils.equals(startVal, rhs.startVal)) return false;
		if(!ObjectUtils.equals(timeZoneId, rhs.timeZoneId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(startVal)
		.append(timeZoneId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{timeZoneId,zoneType,timeZoneOffset,startVal,endVal}
}