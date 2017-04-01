package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"userId","phoneId","billingType","sts","newStsInfoList","unbillingFlag","userrequestFlag","frauldFlag","reratingFlag","osSts","validDate","expireDate","createDate"})
public class Do_queryUserTargetStsInfoRespons extends BaseResponse implements IComplexEntity{


	@XmlElement(name="newStsInfoList")
	private List<SUserStsInfo> newStsInfoList;

	@XmlElement(name="userId")
	private Long userId;

	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="billingType")
	private Integer billingType;

	@XmlElement(name="sts")
	private Integer sts;

	@XmlElement(name="unbillingFlag")
	private Integer unbillingFlag;

	@XmlElement(name="userrequestFlag")
	private Integer userrequestFlag;

	@XmlElement(name="frauldFlag")
	private Integer frauldFlag;

	@XmlElement(name="reratingFlag")
	private Integer reratingFlag;

	@XmlElement(name="osSts")
	private Integer osSts;

	@XmlElement(name="validDate")
	private String validDate;

	@XmlElement(name="expireDate")
	private String expireDate;

	@XmlElement(name="createDate")
	private String createDate;

	public void setNewStsInfoList(List<SUserStsInfo> obj){
		this.newStsInfoList = obj;
	}

	public List<SUserStsInfo> getNewStsInfoList(){
		return newStsInfoList;
	}

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setBillingType(Integer obj){
		this.billingType = obj;
	}

	public Integer getBillingType(){
		return billingType;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setUnbillingFlag(Integer obj){
		this.unbillingFlag = obj;
	}

	public Integer getUnbillingFlag(){
		return unbillingFlag;
	}

	public void setUserrequestFlag(Integer obj){
		this.userrequestFlag = obj;
	}

	public Integer getUserrequestFlag(){
		return userrequestFlag;
	}

	public void setFrauldFlag(Integer obj){
		this.frauldFlag = obj;
	}

	public Integer getFrauldFlag(){
		return frauldFlag;
	}

	public void setReratingFlag(Integer obj){
		this.reratingFlag = obj;
	}

	public Integer getReratingFlag(){
		return reratingFlag;
	}

	public void setOsSts(Integer obj){
		this.osSts = obj;
	}

	public Integer getOsSts(){
		return osSts;
	}

	public void setValidDate(String obj){
		this.validDate = obj;
	}

	public String getValidDate(){
		return validDate;
	}

	public void setExpireDate(String obj){
		this.expireDate = obj;
	}

	public String getExpireDate(){
		return expireDate;
	}

	public void setCreateDate(String obj){
		this.createDate = obj;
	}

	public String getCreateDate(){
		return createDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUserTargetStsInfoRespons rhs=(Do_queryUserTargetStsInfoRespons)rhs0;
		if(!ObjectUtils.equals(newStsInfoList, rhs.newStsInfoList)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(unbillingFlag, rhs.unbillingFlag)) return false;
		if(!ObjectUtils.equals(userrequestFlag, rhs.userrequestFlag)) return false;
		if(!ObjectUtils.equals(frauldFlag, rhs.frauldFlag)) return false;
		if(!ObjectUtils.equals(reratingFlag, rhs.reratingFlag)) return false;
		if(!ObjectUtils.equals(osSts, rhs.osSts)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(newStsInfoList)
		.append(userId)
		.append(phoneId)
		.append(billingType)
		.append(sts)
		.append(unbillingFlag)
		.append(userrequestFlag)
		.append(frauldFlag)
		.append(reratingFlag)
		.append(osSts)
		.append(validDate)
		.append(expireDate)
		.append(createDate)
		.toHashCode();
	}


}