package com.ailk.ims.business.crminterface.bo;

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
@XmlType(propOrder={"OfficeTel","ContactAddress","EMail","ContactName","Duty","Remarks","GroupId","CustName","PostAddress","Hobby","PostCode","Gender","ContactId","ContactType","ContactMobile"})
public class ContactInfo implements IComplexEntity{


	@XmlElement(name="OfficeTel")
	private String OfficeTel;

	@XmlElement(name="ContactAddress")
	private String ContactAddress;

	@XmlElement(name="EMail")
	private String EMail;

	@XmlElement(name="ContactName")
	private String ContactName;

	@XmlElement(name="Duty")
	private String Duty;

	@XmlElement(name="Remarks")
	private String Remarks;

	@XmlElement(name="GroupId")
	private String GroupId;

	@XmlElement(name="CustName")
	private String CustName;

	@XmlElement(name="PostAddress")
	private String PostAddress;

	@XmlElement(name="Hobby")
	private String Hobby;

	@XmlElement(name="PostCode")
	private String PostCode;

	@XmlElement(name="Gender")
	private String Gender;

	@XmlElement(name="ContactId")
	private String ContactId;

	@XmlElement(name="ContactType")
	private String ContactType;

	@XmlElement(name="ContactMobile")
	private String ContactMobile;

	public void setOfficeTel(String obj){
		this.OfficeTel = obj;
	}

	public String getOfficeTel(){
		return OfficeTel;
	}

	public void setContactAddress(String obj){
		this.ContactAddress = obj;
	}

	public String getContactAddress(){
		return ContactAddress;
	}

	public void setEMail(String obj){
		this.EMail = obj;
	}

	public String getEMail(){
		return EMail;
	}

	public void setContactName(String obj){
		this.ContactName = obj;
	}

	public String getContactName(){
		return ContactName;
	}

	public void setDuty(String obj){
		this.Duty = obj;
	}

	public String getDuty(){
		return Duty;
	}

	public void setRemarks(String obj){
		this.Remarks = obj;
	}

	public String getRemarks(){
		return Remarks;
	}

	public void setGroupId(String obj){
		this.GroupId = obj;
	}

	public String getGroupId(){
		return GroupId;
	}

	public void setCustName(String obj){
		this.CustName = obj;
	}

	public String getCustName(){
		return CustName;
	}

	public void setPostAddress(String obj){
		this.PostAddress = obj;
	}

	public String getPostAddress(){
		return PostAddress;
	}

	public void setHobby(String obj){
		this.Hobby = obj;
	}

	public String getHobby(){
		return Hobby;
	}

	public void setPostCode(String obj){
		this.PostCode = obj;
	}

	public String getPostCode(){
		return PostCode;
	}

	public void setGender(String obj){
		this.Gender = obj;
	}

	public String getGender(){
		return Gender;
	}

	public void setContactId(String obj){
		this.ContactId = obj;
	}

	public String getContactId(){
		return ContactId;
	}

	public void setContactType(String obj){
		this.ContactType = obj;
	}

	public String getContactType(){
		return ContactType;
	}

	public void setContactMobile(String obj){
		this.ContactMobile = obj;
	}

	public String getContactMobile(){
		return ContactMobile;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ContactInfo rhs=(ContactInfo)rhs0;
		if(!ObjectUtils.equals(OfficeTel, rhs.OfficeTel)) return false;
		if(!ObjectUtils.equals(ContactAddress, rhs.ContactAddress)) return false;
		if(!ObjectUtils.equals(EMail, rhs.EMail)) return false;
		if(!ObjectUtils.equals(ContactName, rhs.ContactName)) return false;
		if(!ObjectUtils.equals(Duty, rhs.Duty)) return false;
		if(!ObjectUtils.equals(Remarks, rhs.Remarks)) return false;
		if(!ObjectUtils.equals(GroupId, rhs.GroupId)) return false;
		if(!ObjectUtils.equals(CustName, rhs.CustName)) return false;
		if(!ObjectUtils.equals(PostAddress, rhs.PostAddress)) return false;
		if(!ObjectUtils.equals(Hobby, rhs.Hobby)) return false;
		if(!ObjectUtils.equals(PostCode, rhs.PostCode)) return false;
		if(!ObjectUtils.equals(Gender, rhs.Gender)) return false;
		if(!ObjectUtils.equals(ContactId, rhs.ContactId)) return false;
		if(!ObjectUtils.equals(ContactType, rhs.ContactType)) return false;
		if(!ObjectUtils.equals(ContactMobile, rhs.ContactMobile)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(OfficeTel)
		.append(ContactAddress)
		.append(EMail)
		.append(ContactName)
		.append(Duty)
		.append(Remarks)
		.append(GroupId)
		.append(CustName)
		.append(PostAddress)
		.append(Hobby)
		.append(PostCode)
		.append(Gender)
		.append(ContactId)
		.append(ContactType)
		.append(ContactMobile)
		.toHashCode();
	}


}