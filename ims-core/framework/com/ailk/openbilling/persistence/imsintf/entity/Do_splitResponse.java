package com.ailk.openbilling.persistence.imsintf.entity;

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
public class Do_splitResponse extends BaseResponse implements IComplexEntity{

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_splitResponse rhs=(Do_splitResponse)rhs0;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.toHashCode();
	}


}