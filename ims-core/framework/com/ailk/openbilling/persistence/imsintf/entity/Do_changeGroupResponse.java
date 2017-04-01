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
@XmlType(propOrder={"outer_group_id","group_id","prod_result_list"})
public class Do_changeGroupResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prod_result_list")
	private SProductOrderResultList prod_result_list;

	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	public void setProd_result_list(SProductOrderResultList obj){
		this.prod_result_list = obj;
	}

	public SProductOrderResultList getProd_result_list(){
		return prod_result_list;
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
		Do_changeGroupResponse rhs=(Do_changeGroupResponse)rhs0;
		if(!ObjectUtils.equals(prod_result_list, rhs.prod_result_list)) return false;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_result_list)
		.append(outer_group_id)
		.append(group_id)
		.toHashCode();
	}


}