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
@XmlType(propOrder={"rule_id","name"})
public class RuleAndName implements IComplexEntity{


	@XmlElement(name="rule_id")
	private Integer rule_id;

	@XmlElement(name="name")
	private String name;

	public void setRule_id(Integer obj){
		this.rule_id = obj;
	}

	public Integer getRule_id(){
		return rule_id;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RuleAndName rhs=(RuleAndName)rhs0;
		if(!ObjectUtils.equals(rule_id, rhs.rule_id)) return false;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(rule_id)
		.append(name)
		.toHashCode();
	}


}