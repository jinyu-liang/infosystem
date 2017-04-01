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
@XmlType(propOrder={"phone_id","batchno","serialno","cardpin","charge_type"})
public class EtopupReq implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="batchno")
	private String batchno;

	@XmlElement(name="serialno")
	private String serialno;

	@XmlElement(name="cardpin")
	private String cardpin;

	@XmlElement(name="charge_type")
	private Short charge_type;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setBatchno(String obj){
		this.batchno = obj;
	}

	public String getBatchno(){
		return batchno;
	}

	public void setSerialno(String obj){
		this.serialno = obj;
	}

	public String getSerialno(){
		return serialno;
	}

	public void setCardpin(String obj){
		this.cardpin = obj;
	}

	public String getCardpin(){
		return cardpin;
	}

	public void setCharge_type(Short obj){
		this.charge_type = obj;
	}

	public Short getCharge_type(){
		return charge_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		EtopupReq rhs=(EtopupReq)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(batchno, rhs.batchno)) return false;
		if(!ObjectUtils.equals(serialno, rhs.serialno)) return false;
		if(!ObjectUtils.equals(cardpin, rhs.cardpin)) return false;
		if(!ObjectUtils.equals(charge_type, rhs.charge_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(batchno)
		.append(serialno)
		.append(cardpin)
		.append(charge_type)
		.toHashCode();
	}


}