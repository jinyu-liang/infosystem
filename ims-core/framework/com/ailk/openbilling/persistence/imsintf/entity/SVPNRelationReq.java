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
@XmlType(propOrder={"outer_orgin_group_id","orgin_group_id","subGroupList"})
public class SVPNRelationReq implements IComplexEntity{


	@XmlElement(name="subGroupList")
	private SubGroupList subGroupList;

	@XmlElement(name="outer_orgin_group_id")
	private String outer_orgin_group_id;

	@XmlElement(name="orgin_group_id")
	private Long orgin_group_id;

	public void setSubGroupList(SubGroupList obj){
		this.subGroupList = obj;
	}

	public SubGroupList getSubGroupList(){
		return subGroupList;
	}

	public void setOuter_orgin_group_id(String obj){
		this.outer_orgin_group_id = obj;
	}

	public String getOuter_orgin_group_id(){
		return outer_orgin_group_id;
	}

	public void setOrgin_group_id(Long obj){
		this.orgin_group_id = obj;
	}

	public Long getOrgin_group_id(){
		return orgin_group_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SVPNRelationReq rhs=(SVPNRelationReq)rhs0;
		if(!ObjectUtils.equals(subGroupList, rhs.subGroupList)) return false;
		if(!ObjectUtils.equals(outer_orgin_group_id, rhs.outer_orgin_group_id)) return false;
		if(!ObjectUtils.equals(orgin_group_id, rhs.orgin_group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subGroupList)
		.append(outer_orgin_group_id)
		.append(orgin_group_id)
		.toHashCode();
	}


}