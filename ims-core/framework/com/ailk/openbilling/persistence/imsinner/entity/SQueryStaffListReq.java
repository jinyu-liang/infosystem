package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
public class SQueryStaffListReq implements IComplexEntity{

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryStaffListReq rhs=(SQueryStaffListReq)rhs0;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.toHashCode();
	}


}