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
@Table(schema="cd",name="IMS_ICS_SYNC_RST")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","userId","bosPhoneId","createDate","soNbr","sts","errorCode","errorMsg","dealDate","dealCount","phoneId","listType","effectTime","expireTime","weekStart","weekStop","routeNumber","irRouteFlag","routingMethod","validDate","expireDate","soDate"})
public class ImsIcsSyncRst extends DataObject{


	/**
	 * ID
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * RESOURCE_ID
	 */
	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="userId")
	private Long userId;

	/**
	 * BOS_PHONE_ID
	 */
	@Column(name="BOS_PHONE_ID",columnDefinition="Varchar",length=64,nullable=false)
	@XmlElement(name="bosPhoneId")
	private String bosPhoneId;

	/**
	 * CREATE_DATE
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	/**
	 * SO_NBR
	 */
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * STS
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="sts")
	private Integer sts;

	/**
	 * ???????
	 */
	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	/**
	 * ????
	 */
	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=512)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	/**
	 * DEAL_DATE
	 */
	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	/**
	 * DEAL_COUNT
	 */
	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="PHONE_ID",columnDefinition="Varchar",length=64,nullable=false)
	@XmlElement(name="phoneId")
	private String phoneId;

	/**
	 * 1: blacklist
2: whitelist
3:blacklist & whitelist(default)
	 */
	@Column(name="LIST_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="listType")
	private Integer listType;

	/**
	 * EFFECT_TIME
	 */
	@Column(name="EFFECT_TIME",columnDefinition="Varchar",length=64)
	@XmlElement(name="effectTime")
	private String effectTime;

	/**
	 * EXPIRE_TIME
	 */
	@Column(name="EXPIRE_TIME",columnDefinition="Varchar",length=64)
	@XmlElement(name="expireTime")
	private String expireTime;

	/**
	 * WEEK_START
	 */
	@Column(name="WEEK_START",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="weekStart")
	private Integer weekStart;

	/**
	 * WEEK_STOP
	 */
	@Column(name="WEEK_STOP",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="weekStop")
	private Integer weekStop;

	/**
	 * ROUTE_NUMBER
	 */
	@Column(name="ROUTE_NUMBER",columnDefinition="Varchar",length=64)
	@XmlElement(name="routeNumber")
	private String routeNumber;

	/**
	 * IR_ROUTE_METHOD
	 */
	@Column(name="IR_ROUTE_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="irRouteFlag")
	private Integer irRouteFlag;

	/**
	 * ROUTING_METHOD
	 */
	@Column(name="ROUTING_METHOD",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="routingMethod")
	private Integer routingMethod;

	/**
	 * VALID_DATE
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * EXPIRE_DATE
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * SO_DATE
	 */
	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setBosPhoneId(String obj){
		this.bosPhoneId = obj;
	}

	public String getBosPhoneId(){
		return bosPhoneId;
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

	public void setErrorCode(Integer obj){
		this.errorCode = obj;
	}

	public Integer getErrorCode(){
		return errorCode;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setDealCount(Integer obj){
		this.dealCount = obj;
	}

	public Integer getDealCount(){
		return dealCount;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setListType(Integer obj){
		this.listType = obj;
	}

	public Integer getListType(){
		return listType;
	}

	public void setEffectTime(String obj){
		this.effectTime = obj;
	}

	public String getEffectTime(){
		return effectTime;
	}

	public void setExpireTime(String obj){
		this.expireTime = obj;
	}

	public String getExpireTime(){
		return expireTime;
	}

	public void setWeekStart(Integer obj){
		this.weekStart = obj;
	}

	public Integer getWeekStart(){
		return weekStart;
	}

	public void setWeekStop(Integer obj){
		this.weekStop = obj;
	}

	public Integer getWeekStop(){
		return weekStop;
	}

	public void setRouteNumber(String obj){
		this.routeNumber = obj;
	}

	public String getRouteNumber(){
		return routeNumber;
	}

	public void setIrRouteFlag(Integer obj){
		this.irRouteFlag = obj;
	}

	public Integer getIrRouteFlag(){
		return irRouteFlag;
	}

	public void setRoutingMethod(Integer obj){
		this.routingMethod = obj;
	}

	public Integer getRoutingMethod(){
		return routingMethod;
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

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public ImsIcsSyncRst(){
	}

	public ImsIcsSyncRst(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsIcsSyncRst rhs=(ImsIcsSyncRst)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,userId,bosPhoneId,createDate,soNbr,sts,errorCode,errorMsg,dealDate,dealCount,phoneId,listType,effectTime,expireTime,weekStart,weekStop,routeNumber,irRouteFlag,routingMethod,validDate,expireDate,soDate}
}