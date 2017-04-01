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
@XmlType(propOrder={"outer_group_id","group_id","prod_order_list","specialNumberOperList"})
public class ChgGroupProduct implements IComplexEntity{


	@XmlElement(name="prod_order_list")
	private SProductOrderOperList prod_order_list;

	@XmlElement(name="specialNumberOperList")
	private SpecialNumberOperList specialNumberOperList;

	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	public void setProd_order_list(SProductOrderOperList obj){
		this.prod_order_list = obj;
	}

	public SProductOrderOperList getProd_order_list(){
		return prod_order_list;
	}

	public void setSpecialNumberOperList(SpecialNumberOperList obj){
		this.specialNumberOperList = obj;
	}

	public SpecialNumberOperList getSpecialNumberOperList(){
		return specialNumberOperList;
	}

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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ChgGroupProduct rhs=(ChgGroupProduct)rhs0;
		if(!ObjectUtils.equals(prod_order_list, rhs.prod_order_list)) return false;
		if(!ObjectUtils.equals(specialNumberOperList, rhs.specialNumberOperList)) return false;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_order_list)
		.append(specialNumberOperList)
		.append(outer_group_id)
		.append(group_id)
		.toHashCode();
	}


}