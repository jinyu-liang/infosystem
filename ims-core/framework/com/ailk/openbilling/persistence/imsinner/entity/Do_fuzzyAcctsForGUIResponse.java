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
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sAccountList"})
public class Do_fuzzyAcctsForGUIResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sAccountList")
	private SAccountList sAccountList;

	public void setSAccountList(SAccountList obj){
		this.sAccountList = obj;
	}

	public SAccountList getSAccountList(){
		return sAccountList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_fuzzyAcctsForGUIResponse rhs=(Do_fuzzyAcctsForGUIResponse)rhs0;
		if(!ObjectUtils.equals(sAccountList, rhs.sAccountList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sAccountList)
		.toHashCode();
	}


}