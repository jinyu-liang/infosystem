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
@XmlType(propOrder={"bill_Info"})
public class Do_adjustBillResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="bill_Info")
	private Billinfo bill_Info;

	public void setBill_Info(Billinfo obj){
		this.bill_Info = obj;
	}

	public Billinfo getBill_Info(){
		return bill_Info;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_adjustBillResponse rhs=(Do_adjustBillResponse)rhs0;
		if(!ObjectUtils.equals(bill_Info, rhs.bill_Info)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bill_Info)
		.toHashCode();
	}


}