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
import com.ailk.openbilling.persistence.pm.entity.PmPriceParamDef;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"extendParam_list"})
public class SQueryPriceParamResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="extendParam_list")
	private List<PmPriceParamDef> extendParam_list;

	public void setExtendParam_list(List<PmPriceParamDef> obj){
		this.extendParam_list = obj;
	}

	public List<PmPriceParamDef> getExtendParam_list(){
		return extendParam_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryPriceParamResponse rhs=(SQueryPriceParamResponse)rhs0;
		if(!ObjectUtils.equals(extendParam_list, rhs.extendParam_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(extendParam_list)
		.toHashCode();
	}


}