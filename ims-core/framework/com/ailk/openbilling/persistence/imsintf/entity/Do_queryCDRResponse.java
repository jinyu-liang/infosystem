package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"callDetailRecordList","sContact","file_path"})
public class Do_queryCDRResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="callDetailRecordList")
	private CallDetailRecordList callDetailRecordList;

	@XmlElement(name="sContact")
	private SContact sContact;

	@XmlElement(name="file_path")
	private String file_path;

	public void setCallDetailRecordList(CallDetailRecordList obj){
		this.callDetailRecordList = obj;
	}

	public CallDetailRecordList getCallDetailRecordList(){
		return callDetailRecordList;
	}

	public void setSContact(SContact obj){
		this.sContact = obj;
	}

	public SContact getSContact(){
		return sContact;
	}

	public void setFile_path(String obj){
		this.file_path = obj;
	}

	public String getFile_path(){
		return file_path;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryCDRResponse rhs=(Do_queryCDRResponse)rhs0;
		if(!ObjectUtils.equals(callDetailRecordList, rhs.callDetailRecordList)) return false;
		if(!ObjectUtils.equals(sContact, rhs.sContact)) return false;
		if(!ObjectUtils.equals(file_path, rhs.file_path)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(callDetailRecordList)
		.append(sContact)
		.append(file_path)
		.toHashCode();
	}


}