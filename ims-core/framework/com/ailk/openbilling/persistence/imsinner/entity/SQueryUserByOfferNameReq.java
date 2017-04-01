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
@XmlType(propOrder={"phone_id","offer_name","page"})
public class SQueryUserByOfferNameReq implements IComplexEntity{


	@XmlElement(name="page")
	private SPage page;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="offer_name")
	private String offer_name;

	public void setPage(SPage obj){
		this.page = obj;
	}

	public SPage getPage(){
		return page;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setOffer_name(String obj){
		this.offer_name = obj;
	}

	public String getOffer_name(){
		return offer_name;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryUserByOfferNameReq rhs=(SQueryUserByOfferNameReq)rhs0;
		if(!ObjectUtils.equals(page, rhs.page)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(offer_name, rhs.offer_name)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(page)
		.append(phone_id)
		.append(offer_name)
		.toHashCode();
	}


}