package com.ailk.openbilling.persistence.sys.entity;

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
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="SD",name="SYS_BANK_INFO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"bankId","bankType","bankNo","bankName","bankAddr","linkMan","linkPhone","postCode","email","parentBankId","sts","bankDesc","bankDescExt","isNeedPasswd","topBankId"})
public class SysBankInfo extends DataObject{


	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="BANK_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="bankId")
	private Integer bankId;

	@Column(name="BANK_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="bankType")
	private Integer bankType;

	@Column(name="BANK_NO",columnDefinition="Varchar",length=12)
	@XmlElement(name="bankNo")
	private String bankNo;

	@Column(name="BANK_NAME",columnDefinition="Varchar",length=256)
	@XmlElement(name="bankName")
	private String bankName;

	@Column(name="BANK_ADDR",columnDefinition="Varchar",length=256)
	@XmlElement(name="bankAddr")
	private String bankAddr;

	@Column(name="LINK_MAN",columnDefinition="Varchar",length=256)
	@XmlElement(name="linkMan")
	private String linkMan;

	@Column(name="LINK_PHONE",columnDefinition="Varchar",length=64)
	@XmlElement(name="linkPhone")
	private String linkPhone;

	@Column(name="POST_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="postCode")
	private Integer postCode;

	@Column(name="EMAIL",columnDefinition="Varchar",length=32)
	@XmlElement(name="email")
	private String email;

	@Column(name="PARENT_BANK_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="parentBankId")
	private Integer parentBankId;

	@Column(name="STS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="BANK_DESC",columnDefinition="Varchar",length=1024)
	@XmlElement(name="bankDesc")
	private String bankDesc;

	@Column(name="BANK_DESC_EXT",columnDefinition="Varchar",length=1024)
	@XmlElement(name="bankDescExt")
	private String bankDescExt;

	/**
	 * ???(?????)????????????? 0 - ??? 1 - ??
	 */
	@Column(name="IS_NEED_PASSWD",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="isNeedPasswd")
	private Integer isNeedPasswd;

	@Column(name="TOP_BANK_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="topBankId")
	private Integer topBankId;

	public void setBankId(Integer obj){
		this.bankId = obj;
	}

	public Integer getBankId(){
		return bankId;
	}

	public void setBankType(Integer obj){
		this.bankType = obj;
	}

	public Integer getBankType(){
		return bankType;
	}

	public void setBankNo(String obj){
		this.bankNo = obj;
	}

	public String getBankNo(){
		return bankNo;
	}

	public void setBankName(String obj){
		this.bankName = obj;
	}

	public String getBankName(){
		return bankName;
	}

	public void setBankAddr(String obj){
		this.bankAddr = obj;
	}

	public String getBankAddr(){
		return bankAddr;
	}

	public void setLinkMan(String obj){
		this.linkMan = obj;
	}

	public String getLinkMan(){
		return linkMan;
	}

	public void setLinkPhone(String obj){
		this.linkPhone = obj;
	}

	public String getLinkPhone(){
		return linkPhone;
	}

	public void setPostCode(Integer obj){
		this.postCode = obj;
	}

	public Integer getPostCode(){
		return postCode;
	}

	public void setEmail(String obj){
		this.email = obj;
	}

	public String getEmail(){
		return email;
	}

	public void setParentBankId(Integer obj){
		this.parentBankId = obj;
	}

	public Integer getParentBankId(){
		return parentBankId;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setBankDesc(String obj){
		this.bankDesc = obj;
	}

	public String getBankDesc(){
		return bankDesc;
	}

	public void setBankDescExt(String obj){
		this.bankDescExt = obj;
	}

	public String getBankDescExt(){
		return bankDescExt;
	}

	public void setIsNeedPasswd(Integer obj){
		this.isNeedPasswd = obj;
	}

	public Integer getIsNeedPasswd(){
		return isNeedPasswd;
	}

	public void setTopBankId(Integer obj){
		this.topBankId = obj;
	}

	public Integer getTopBankId(){
		return topBankId;
	}

	public SysBankInfo(){
	}

	public SysBankInfo(Integer bankId){
		this.bankId = bankId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysBankInfo rhs=(SysBankInfo)rhs0;
		if(!ObjectUtils.equals(bankId, rhs.bankId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bankId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{bankId,bankType,bankNo,bankName,bankAddr,linkMan,linkPhone,postCode,email,parentBankId,sts,bankDesc,bankDescExt,isNeedPasswd,topBankId}
}