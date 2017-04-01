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
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custList"})
public class Do_custQueryResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="custList")
	private List<CustomerRet> custList;

	public void setCustList(List<CustomerRet> obj){
		this.custList = obj;
	}

	public List<CustomerRet> getCustList(){
		return custList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_custQueryResponse rhs=(Do_custQueryResponse)rhs0;
		if(!ObjectUtils.equals(custList, rhs.custList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custList)
		.toHashCode();
	}


}