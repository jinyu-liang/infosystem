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
@XmlType(propOrder={"recurringOnetimeFeeList"})
public class Do_queryRecurringfeeOnetimefeeResp extends BaseResponse implements IComplexEntity{


	@XmlElement(name="recurringOnetimeFeeList")
	private RecurringOnetimeFeeList recurringOnetimeFeeList;

	public void setRecurringOnetimeFeeList(RecurringOnetimeFeeList obj){
		this.recurringOnetimeFeeList = obj;
	}

	public RecurringOnetimeFeeList getRecurringOnetimeFeeList(){
		return recurringOnetimeFeeList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryRecurringfeeOnetimefeeResp rhs=(Do_queryRecurringfeeOnetimefeeResp)rhs0;
		if(!ObjectUtils.equals(recurringOnetimeFeeList, rhs.recurringOnetimeFeeList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(recurringOnetimeFeeList)
		.toHashCode();
	}


}