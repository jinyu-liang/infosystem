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
@XmlType(propOrder={"outer_group_id","group_id","phone_id","region_code","valid_date"})
public class SDismissReq implements IComplexEntity{


	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="valid_date")
	private String valid_date;

	public void setOuter_group_id(String obj){
		this.outer_group_id = obj;
	}

	public String getOuter_group_id(){
		return outer_group_id;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setRegion_code(Short obj){
		this.region_code = obj;
	}

	public Short getRegion_code(){
		return region_code;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDismissReq rhs=(SDismissReq)rhs0;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_group_id)
		.append(group_id)
		.append(phone_id)
		.append(region_code)
		.append(valid_date)
		.toHashCode();
	}


}