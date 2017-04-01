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
@XmlType(propOrder={"fn_list"})
public class Do_queryFNResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="fn_list")
	private ProductFNList fn_list;

	public void setFn_list(ProductFNList obj){
		this.fn_list = obj;
	}

	public ProductFNList getFn_list(){
		return fn_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryFNResponse rhs=(Do_queryFNResponse)rhs0;
		if(!ObjectUtils.equals(fn_list, rhs.fn_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fn_list)
		.toHashCode();
	}


}