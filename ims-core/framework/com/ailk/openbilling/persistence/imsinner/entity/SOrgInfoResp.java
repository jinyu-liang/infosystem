package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"organize_id","organize_name","county_id","district_id"})
public class SOrgInfoResp implements IComplexEntity{


	@XmlElement(name="organize_id")
	private Long organize_id;

	@XmlElement(name="organize_name")
	private String organize_name;

	@XmlElement(name="county_id")
	private Long county_id;

	@XmlElement(name="district_id")
	private String district_id;

	public void setOrganize_id(Long obj){
		this.organize_id = obj;
	}

	public Long getOrganize_id(){
		return organize_id;
	}

	public void setOrganize_name(String obj){
		this.organize_name = obj;
	}

	public String getOrganize_name(){
		return organize_name;
	}

	public void setCounty_id(Long obj){
		this.county_id = obj;
	}

	public Long getCounty_id(){
		return county_id;
	}

	public void setDistrict_id(String obj){
		this.district_id = obj;
	}

	public String getDistrict_id(){
		return district_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOrgInfoResp rhs=(SOrgInfoResp)rhs0;
		if(!ObjectUtils.equals(organize_id, rhs.organize_id)) return false;
		if(!ObjectUtils.equals(organize_name, rhs.organize_name)) return false;
		if(!ObjectUtils.equals(county_id, rhs.county_id)) return false;
		if(!ObjectUtils.equals(district_id, rhs.district_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(organize_id)
		.append(organize_name)
		.append(county_id)
		.append(district_id)
		.toHashCode();
	}


}