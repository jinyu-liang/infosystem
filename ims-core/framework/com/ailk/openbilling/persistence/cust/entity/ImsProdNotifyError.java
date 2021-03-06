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
 * 
 */
@NotModified
@Entity
@Table(schema="cd",name="IMS_PROD_NOTIFY_ERROR")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"notificationId","offerId","objectId","objectType","phoneId","validDate","expireDate","createDate"})
public class ImsProdNotifyError extends DataObject{


	/**
	 * 告警id
	 */
	@Column(name="notification_id",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="notificationId")
	private Integer notificationId;

	/**
	 * 销售品id
	 */
	@Column(name="offer_id",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="offerId")
	private Integer offerId;

	/**
	 * 产品使用对象id
	 */
	@Column(name="object_id",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectId")
	private Long objectId;

	/**
	 * 使用对象类型
	 */
	@Column(name="object_type",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectType")
	private Integer objectType;

	/**
	 * 通知号码
	 */
	@Column(name="phone_id",columnDefinition="Varchar",length=30,nullable=false)
	@XmlElement(name="phoneId")
	private String phoneId;

	/**
	 * 产品生效时间
	 */
	@Column(name="valid_date",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * 产品失效时间
	 */
	@Column(name="expire_date",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * 记录创建时间
	 */
	@Column(name="create_date",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	public void setNotificationId(Integer obj){
		this.notificationId = obj;
	}

	public Integer getNotificationId(){
		return notificationId;
	}

	public void setOfferId(Integer obj){
		this.offerId = obj;
	}

	public Integer getOfferId(){
		return offerId;
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

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
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

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public ImsProdNotifyError(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsProdNotifyError rhs=(ImsProdNotifyError)rhs0;
		if(!ObjectUtils.equals(notificationId, rhs.notificationId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notificationId)
		.append(offerId)
		.append(objectId)
		.append(objectType)
		.append(phoneId)
		.append(validDate)
		.append(expireDate)
		.append(createDate)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{notificationId,offerId,objectId,objectType,phoneId,validDate,expireDate,createDate}
}