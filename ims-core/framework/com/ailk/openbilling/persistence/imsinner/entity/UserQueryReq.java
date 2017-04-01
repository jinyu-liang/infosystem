package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(propOrder={"userIds","phoneIds","isCmResLifeCycle","isCmResIdentity","isCmIdentityVsImei","isCmResExt","isBillAcctId","isUserBudget","isMainPromotion","isCmResource","isValidtity"})
public class UserQueryReq extends BaseQueryReq implements IComplexEntity{


	@XmlElement(name="userIds")
	private List<Long> userIds;

	@XmlElement(name="phoneIds")
	private List<String> phoneIds;

	@XmlElement(name="isCmResLifeCycle")
	private Boolean isCmResLifeCycle;

	@XmlElement(name="isCmResIdentity")
	private Boolean isCmResIdentity;

	@XmlElement(name="isCmIdentityVsImei")
	private Boolean isCmIdentityVsImei;

	@XmlElement(name="isCmResExt")
	private Boolean isCmResExt;

	@XmlElement(name="isBillAcctId")
	private Boolean isBillAcctId;

	@XmlElement(name="isUserBudget")
	private Boolean isUserBudget;

	@XmlElement(name="isMainPromotion")
	private Boolean isMainPromotion;

	@XmlElement(name="isCmResource")
	private Boolean isCmResource;

	@XmlElement(name="isValidtity")
	private Boolean isValidtity;

	public void setUserIds(List<Long> obj){
		this.userIds = obj;
	}

	public List<Long> getUserIds(){
		return userIds;
	}

	public void setPhoneIds(List<String> obj){
		this.phoneIds = obj;
	}

	public List<String> getPhoneIds(){
		return phoneIds;
	}

	public void setIsCmResLifeCycle(Boolean obj){
		this.isCmResLifeCycle = obj;
	}

	public Boolean getIsCmResLifeCycle(){
		return isCmResLifeCycle;
	}

	public void setIsCmResIdentity(Boolean obj){
		this.isCmResIdentity = obj;
	}

	public Boolean getIsCmResIdentity(){
		return isCmResIdentity;
	}

	public void setIsCmIdentityVsImei(Boolean obj){
		this.isCmIdentityVsImei = obj;
	}

	public Boolean getIsCmIdentityVsImei(){
		return isCmIdentityVsImei;
	}

	public void setIsCmResExt(Boolean obj){
		this.isCmResExt = obj;
	}

	public Boolean getIsCmResExt(){
		return isCmResExt;
	}

	public void setIsBillAcctId(Boolean obj){
		this.isBillAcctId = obj;
	}

	public Boolean getIsBillAcctId(){
		return isBillAcctId;
	}

	public void setIsUserBudget(Boolean obj){
		this.isUserBudget = obj;
	}

	public Boolean getIsUserBudget(){
		return isUserBudget;
	}

	public void setIsMainPromotion(Boolean obj){
		this.isMainPromotion = obj;
	}

	public Boolean getIsMainPromotion(){
		return isMainPromotion;
	}

	public void setIsCmResource(Boolean obj){
		this.isCmResource = obj;
	}

	public Boolean getIsCmResource(){
		return isCmResource;
	}

	public void setIsValidtity(Boolean obj){
		this.isValidtity = obj;
	}

	public Boolean getIsValidtity(){
		return isValidtity;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		UserQueryReq rhs=(UserQueryReq)rhs0;
		if(!ObjectUtils.equals(userIds, rhs.userIds)) return false;
		if(!ObjectUtils.equals(phoneIds, rhs.phoneIds)) return false;
		if(!ObjectUtils.equals(isCmResLifeCycle, rhs.isCmResLifeCycle)) return false;
		if(!ObjectUtils.equals(isCmResIdentity, rhs.isCmResIdentity)) return false;
		if(!ObjectUtils.equals(isCmIdentityVsImei, rhs.isCmIdentityVsImei)) return false;
		if(!ObjectUtils.equals(isCmResExt, rhs.isCmResExt)) return false;
		if(!ObjectUtils.equals(isBillAcctId, rhs.isBillAcctId)) return false;
		if(!ObjectUtils.equals(isUserBudget, rhs.isUserBudget)) return false;
		if(!ObjectUtils.equals(isMainPromotion, rhs.isMainPromotion)) return false;
		if(!ObjectUtils.equals(isCmResource, rhs.isCmResource)) return false;
		if(!ObjectUtils.equals(isValidtity, rhs.isValidtity)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userIds)
		.append(phoneIds)
		.append(isCmResLifeCycle)
		.append(isCmResIdentity)
		.append(isCmIdentityVsImei)
		.append(isCmResExt)
		.append(isBillAcctId)
		.append(isUserBudget)
		.append(isMainPromotion)
		.append(isCmResource)
		.append(isValidtity)
		.toHashCode();
	}


}