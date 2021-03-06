package com.ailk.openbilling.persistence.sys.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
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
 */
@NotModified
@Entity
@Table(schema="SD",name="SYS_TIME_SEG_DEF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"segId","timeMode","segName","description"})
public class SysTimeSegDef extends DataObject{


	/**
	 * time segment definition ID
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="SEG_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="segId")
	private Integer segId;

	/**
	 * time segment time mode 0-Not time mode if the referential subject is time amount, then the premium tier should stand for YYYYddmmHHMMSS, which is designated by the use of time_mode:  1-week-type day time segment, date should be 1-7；E.g. Mon-Fri 8.00-20.00 means being effective between 8:00 and 20:00 from Monday to Friday, and is configured as 1080000 and 5200000. 2-YYYYMMDD-type appoints date and day time segment；E.g. 20100102-20101114 8.00-20.00, to show being effective between 8:00 and 20:00 from 2010/1/2 to 2010/11/14, configure is as 20100102080000 and 20101114200000. 3-MMDD-type means day time segment；E.g. 8:00-20:00 from 1/2 to 11/14 of every year is effective; configure it as 0102080000 and 1114200000. 4-DD-type refers to day time segment;E.g. to show that time between 8:00 and 20:00 from 2sec to 14th of every month is effective, then configure it as 02080000 and 14200000. 5-week-type time which is from start date to ending date, e.g. Mon 8.00 - Fri 20.00 means it is effective from 8:00 Monday to 20:00 Friday, then configure it as 1080000 and 5200000. 6-YYYYMMDD-type time which is from start date to ending date, e.g. 2010/1/2 8:00 – 2010/11/14 20:00 means it is effective within this time area, then configure it as 20100102080000 and 20101114200000. 7-MMDD-type time which is from start date to ending date, e.g. 1/2 8:00 – 11/14 20:00 of every year means it is effective within this period, then configure it as 0102080000 and 1114200000 8-DD-type time which is from start date to ending date, e.g. 2ed 8:00 – 14th 20:00 of every month is effective, then configure it as 02080000 and 14200000.
	 */
	@Column(name="TIME_MODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="timeMode")
	private Integer timeMode;

	/**
	 * time segment definition name
	 */
	@Column(name="SEG_NAME",columnDefinition="Varchar",length=256)
	@XmlElement(name="segName")
	private String segName;

	/**
	 * description
	 */
	@Column(name="DESCRIPTION",columnDefinition="Varchar",length=1024,nullable=false)
	@XmlElement(name="description")
	private String description;

	public void setSegId(Integer obj){
		this.segId = obj;
	}

	public Integer getSegId(){
		return segId;
	}

	public void setTimeMode(Integer obj){
		this.timeMode = obj;
	}

	public Integer getTimeMode(){
		return timeMode;
	}

	public void setSegName(String obj){
		this.segName = obj;
	}

	public String getSegName(){
		return segName;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public SysTimeSegDef(){
	}

	public SysTimeSegDef(Integer segId){
		this.segId = segId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysTimeSegDef rhs=(SysTimeSegDef)rhs0;
		if(!ObjectUtils.equals(segId, rhs.segId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(segId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{segId,timeMode,segName,description}
}