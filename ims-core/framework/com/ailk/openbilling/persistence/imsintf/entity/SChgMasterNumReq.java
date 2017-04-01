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
@XmlType(propOrder={"old_master_number","new_master_number"})
public class SChgMasterNumReq implements IComplexEntity{


	@XmlElement(name="old_master_number")
	private String old_master_number;

	@XmlElement(name="new_master_number")
	private String new_master_number;

	public void setOld_master_number(String obj){
		this.old_master_number = obj;
	}

	public String getOld_master_number(){
		return old_master_number;
	}

	public void setNew_master_number(String obj){
		this.new_master_number = obj;
	}

	public String getNew_master_number(){
		return new_master_number;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChgMasterNumReq rhs=(SChgMasterNumReq)rhs0;
		if(!ObjectUtils.equals(old_master_number, rhs.old_master_number)) return false;
		if(!ObjectUtils.equals(new_master_number, rhs.new_master_number)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(old_master_number)
		.append(new_master_number)
		.toHashCode();
	}


}