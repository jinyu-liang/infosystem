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
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="CD",name="CO_PROD_BUNDLE_COMPOSITE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"parentProductNo","productId","objectId","objectType"})
public class CoProdBundleComposite extends DataObject{


	/**
	 * Bundled Product id
The id of the instance of Bundled Product
	 */
	@Id
	@Column(name="PARENT_PRODUCT_NO",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="parentProductNo")
	private Long parentProductNo;

	/**
	 * Component Product Id
The id of the instance of Component Product
	 */
	@Id
	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	@Column(name="OBJECT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectId")
	private Long objectId;

	@Column(name="OBJECT_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectType")
	private Integer objectType;

	public void setParentProductNo(Long obj){
		this.parentProductNo = obj;
	}

	public Long getParentProductNo(){
		return parentProductNo;
	}

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
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

	public CoProdBundleComposite(){
	}

	public CoProdBundleComposite(Long parentProductNo,Long productId){
		this.parentProductNo = parentProductNo;
		this.productId = productId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CoProdBundleComposite rhs=(CoProdBundleComposite)rhs0;
		if(!ObjectUtils.equals(parentProductNo, rhs.parentProductNo)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(parentProductNo)
		.append(productId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{parentProductNo,productId,objectId,objectType}
}