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
@XmlType(propOrder={"prod_result_list","oldBalance_list"})
public class Do_changeOwnerReqResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prod_result_list")
	private SProductOrderResultList prod_result_list;

	@XmlElement(name="oldBalance_list")
	private SBalanceList oldBalance_list;

	public void setProd_result_list(SProductOrderResultList obj){
		this.prod_result_list = obj;
	}

	public SProductOrderResultList getProd_result_list(){
		return prod_result_list;
	}

	public void setOldBalance_list(SBalanceList obj){
		this.oldBalance_list = obj;
	}

	public SBalanceList getOldBalance_list(){
		return oldBalance_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_changeOwnerReqResponse rhs=(Do_changeOwnerReqResponse)rhs0;
		if(!ObjectUtils.equals(prod_result_list, rhs.prod_result_list)) return false;
		if(!ObjectUtils.equals(oldBalance_list, rhs.oldBalance_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_result_list)
		.append(oldBalance_list)
		.toHashCode();
	}


}