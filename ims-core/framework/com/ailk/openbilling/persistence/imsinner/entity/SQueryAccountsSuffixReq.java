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
@XmlType(propOrder={"table_suffix","acctIdList"})
public class SQueryAccountsSuffixReq implements IComplexEntity{


	@XmlElement(name="acctIdList")
	private List<Long> acctIdList;

	@XmlElement(name="table_suffix")
	private String table_suffix;

	public void setAcctIdList(List<Long> obj){
		this.acctIdList = obj;
	}

	public List<Long> getAcctIdList(){
		return acctIdList;
	}

	public void setTable_suffix(String obj){
		this.table_suffix = obj;
	}

	public String getTable_suffix(){
		return table_suffix;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryAccountsSuffixReq rhs=(SQueryAccountsSuffixReq)rhs0;
		if(!ObjectUtils.equals(acctIdList, rhs.acctIdList)) return false;
		if(!ObjectUtils.equals(table_suffix, rhs.table_suffix)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctIdList)
		.append(table_suffix)
		.toHashCode();
	}


}