package com.ailk.openbilling.persistence.cust.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
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
 * 
 */
@NotModified
@Entity
@Table(schema="cd",name="IMS_DELAY_PROD_SYNC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","offerId","productId","validDate","createDate","soNbr","sts"})
public class ImsDelayProdSync extends DataObject{


	/**
	 * id
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * ฯ๚สฦทid
	 */
	@Column(name="OFFER_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="offerId")
	private Long offerId;

	/**
	 * สตภปฏฒ๚ฦทid
	 */
	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	/**
	 * ฒ๚ฦทษ๚ะงสฑผไ
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * ผวยผษ๚ณษสฑผไ
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	/**
	 * ม๗หฎบล
	 */
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * สพืดฬฌ

1-ณ๕สผปฏ
2-ีิฺดฆภํ
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setOfferId(Long obj){
		this.offerId = obj;
	}

	public Long getOfferId(){
		return offerId;
	}

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public ImsDelayProdSync(){
	}

	public ImsDelayProdSync(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsDelayProdSync rhs=(ImsDelayProdSync)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,offerId,productId,validDate,createDate,soNbr,sts}
}