package com.ailk.openbilling.persistence.cust.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="CD",name="IMS_UPSELL_REQ")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","upFlag","upTime","prodId","orgOfferId","tarOfferId","objType","objId"})
public class ImsUpsellReq extends DataObject{


	@Column(name="ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	@Column(name="UP_FLAG",precision=4,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="upFlag")
	private Integer upFlag;

	@Column(name="UP_TIME",columnDefinition="TimeStamp")
	@XmlElement(name="upTime")
	private Date upTime;

	@Column(name="PROD_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="prodId")
	private Long prodId;

	@Column(name="ORG_OFFER_ID",precision=9,columnDefinition="NUMBER")
	@XmlElement(name="orgOfferId")
	private Integer orgOfferId;

	@Column(name="TAR_OFFER_ID",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="tarOfferId")
	private Integer tarOfferId;

	@Column(name="OBJ_TYPE",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objType")
	private Integer objType;

	@Column(name="OBJ_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objId")
	private Long objId;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setUpFlag(Integer obj){
		this.upFlag = obj;
	}

	public Integer getUpFlag(){
		return upFlag;
	}

	public void setUpTime(Date obj){
		this.upTime = obj;
	}

	public Date getUpTime(){
		return upTime;
	}

	public void setProdId(Long obj){
		this.prodId = obj;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setOrgOfferId(Integer obj){
		this.orgOfferId = obj;
	}

	public Integer getOrgOfferId(){
		return orgOfferId;
	}

	public void setTarOfferId(Integer obj){
		this.tarOfferId = obj;
	}

	public Integer getTarOfferId(){
		return tarOfferId;
	}

	public void setObjType(Integer obj){
		this.objType = obj;
	}

	public Integer getObjType(){
		return objType;
	}

	public void setObjId(Long obj){
		this.objId = obj;
	}

	public Long getObjId(){
		return objId;
	}

	public ImsUpsellReq(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsUpsellReq rhs=(ImsUpsellReq)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(upFlag, rhs.upFlag)) return false;
		if(!ObjectUtils.equals(upTime, rhs.upTime)) return false;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(orgOfferId, rhs.orgOfferId)) return false;
		if(!ObjectUtils.equals(tarOfferId, rhs.tarOfferId)) return false;
		if(!ObjectUtils.equals(objType, rhs.objType)) return false;
		if(!ObjectUtils.equals(objId, rhs.objId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(upFlag)
		.append(upTime)
		.append(prodId)
		.append(orgOfferId)
		.append(tarOfferId)
		.append(objType)
		.append(objId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,upFlag,upTime,prodId,orgOfferId,tarOfferId,objType,objId}
}