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
@XmlType(propOrder={"orign_so_nbr","remark","cancel_date"})
public class CancelOrderInfo implements IComplexEntity{


	@XmlElement(name="orign_so_nbr")
	private Long orign_so_nbr;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="cancel_date")
	private String cancel_date;

	public void setOrign_so_nbr(Long obj){
		this.orign_so_nbr = obj;
	}

	public Long getOrign_so_nbr(){
		return orign_so_nbr;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setCancel_date(String obj){
		this.cancel_date = obj;
	}

	public String getCancel_date(){
		return cancel_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CancelOrderInfo rhs=(CancelOrderInfo)rhs0;
		if(!ObjectUtils.equals(orign_so_nbr, rhs.orign_so_nbr)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(cancel_date, rhs.cancel_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orign_so_nbr)
		.append(remark)
		.append(cancel_date)
		.toHashCode();
	}


}