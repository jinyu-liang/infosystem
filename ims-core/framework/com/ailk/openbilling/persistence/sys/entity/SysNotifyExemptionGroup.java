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
@Table(schema="sd",name="SYS_NOTIFY_EXEMPTION_GROUP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"exemptionType","groupId"})
public class SysNotifyExemptionGroup extends DataObject{


	/**
	 * exemption type
1: no dunning and no baring service
	 */
	@Id
	@Column(name="EXEMPTION_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="exemptionType")
	private Integer exemptionType;

	/**
	 * group ID
	 */
	@Id
	@Column(name="GROUP_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="groupId")
	private Integer groupId;

	public void setExemptionType(Integer obj){
		this.exemptionType = obj;
	}

	public Integer getExemptionType(){
		return exemptionType;
	}

	public void setGroupId(Integer obj){
		this.groupId = obj;
	}

	public Integer getGroupId(){
		return groupId;
	}

	public SysNotifyExemptionGroup(){
	}

	public SysNotifyExemptionGroup(Integer exemptionType,Integer groupId){
		this.exemptionType = exemptionType;
		this.groupId = groupId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysNotifyExemptionGroup rhs=(SysNotifyExemptionGroup)rhs0;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(exemptionType, rhs.exemptionType)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groupId)
		.append(exemptionType)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{exemptionType,groupId}
}