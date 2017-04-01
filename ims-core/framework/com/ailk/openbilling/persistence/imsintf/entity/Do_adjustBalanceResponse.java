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
@XmlType(propOrder={"sBalanceAdjustResponse","freeResourceListAdjustResponse"})
public class Do_adjustBalanceResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sBalanceAdjustResponse")
	private SBalanceAdjustResponse sBalanceAdjustResponse;

	@XmlElement(name="freeResourceListAdjustResponse")
	private FreeResourceList freeResourceListAdjustResponse;

	public void setSBalanceAdjustResponse(SBalanceAdjustResponse obj){
		this.sBalanceAdjustResponse = obj;
	}

	public SBalanceAdjustResponse getSBalanceAdjustResponse(){
		return sBalanceAdjustResponse;
	}

	public void setFreeResourceListAdjustResponse(FreeResourceList obj){
		this.freeResourceListAdjustResponse = obj;
	}

	public FreeResourceList getFreeResourceListAdjustResponse(){
		return freeResourceListAdjustResponse;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_adjustBalanceResponse rhs=(Do_adjustBalanceResponse)rhs0;
		if(!ObjectUtils.equals(sBalanceAdjustResponse, rhs.sBalanceAdjustResponse)) return false;
		if(!ObjectUtils.equals(freeResourceListAdjustResponse, rhs.freeResourceListAdjustResponse)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalanceAdjustResponse)
		.append(freeResourceListAdjustResponse)
		.toHashCode();
	}


}