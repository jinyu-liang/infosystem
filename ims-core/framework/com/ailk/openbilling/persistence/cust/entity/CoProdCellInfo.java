package com.ailk.openbilling.persistence.cust.entity;

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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="CD",name="CO_PROD_CELL_INFO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","lacId","sacId","validDate","expireDate","modifyDate","remarks","soNbr","soDate","objectId","objectType"})
public class CoProdCellInfo extends DataObject{


	@Id
	@Column(name="PRODUCT_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	@Id
	@Column(name="LAC_ID",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="lacId")
	private Integer lacId;

	@Id
	@Column(name="SAC_ID",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sacId")
	private Integer sacId;

	@Id
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="MODIFY_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="modifyDate")
	private Date modifyDate;

	@Column(name="REMARKS",columnDefinition="Varchar",length=256)
	@XmlElement(name="remarks")
	private String remarks;

	@Id
	@Column(name="SO_NBR",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="SO_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="OBJECT_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectId")
	private Long objectId;

	@Column(name="OBJECT_TYPE",precision=4,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectType")
	private Integer objectType;

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setLacId(Integer obj){
		this.lacId = obj;
	}

	public Integer getLacId(){
		return lacId;
	}

	public void setSacId(Integer obj){
		this.sacId = obj;
	}

	public Integer getSacId(){
		return sacId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setModifyDate(Date obj){
		this.modifyDate = obj;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setRemarks(String obj){
		this.remarks = obj;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setObjectId(Long obj){
		this.objectId = obj;
	}

	public Long getObjectId(){
		return objectId;
	}

	public void setObjectType(Integer obj){
		this.objectType = obj;
	}

	public Integer getObjectType(){
		return objectType;
	}

	public CoProdCellInfo(){
	}

	public CoProdCellInfo(Long productId,Integer lacId,Integer sacId,Date validDate,Long soNbr){
		this.productId = productId;
		this.lacId = lacId;
		this.sacId = sacId;
		this.validDate = validDate;
		this.soNbr = soNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CoProdCellInfo rhs=(CoProdCellInfo)rhs0;
		if(!ObjectUtils.equals(sacId, rhs.sacId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(lacId, rhs.lacId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sacId)
		.append(validDate)
		.append(soNbr)
		.append(lacId)
		.append(productId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{productId,lacId,sacId,validDate,expireDate,modifyDate,remarks,soNbr,soDate,objectId,objectType}
}