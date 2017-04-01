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
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"cmCustomer"})
public class Do_queryBatchCustResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="cmCustomer")
	private List<CmCustomer> cmCustomer;

	public void setCmCustomer(List<CmCustomer> obj){
		this.cmCustomer = obj;
	}

	public List<CmCustomer> getCmCustomer(){
		return cmCustomer;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBatchCustResponse rhs=(Do_queryBatchCustResponse)rhs0;
		if(!ObjectUtils.equals(cmCustomer, rhs.cmCustomer)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cmCustomer)
		.toHashCode();
	}


}