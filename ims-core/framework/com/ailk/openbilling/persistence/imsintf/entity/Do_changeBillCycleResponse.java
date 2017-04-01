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
@XmlType(propOrder={"valid_date"})
public class Do_changeBillCycleResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="valid_date")
	private String valid_date;

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_changeBillCycleResponse rhs=(Do_changeBillCycleResponse)rhs0;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(valid_date)
		.toHashCode();
	}


}