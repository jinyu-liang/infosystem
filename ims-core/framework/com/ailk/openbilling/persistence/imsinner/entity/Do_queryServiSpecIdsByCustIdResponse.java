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
@XmlType(propOrder={"servieSpecId_list"})
public class Do_queryServiSpecIdsByCustIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="servieSpecId_list")
	private List<Integer> servieSpecId_list;

	public void setServieSpecId_list(List<Integer> obj){
		this.servieSpecId_list = obj;
	}

	public List<Integer> getServieSpecId_list(){
		return servieSpecId_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryServiSpecIdsByCustIdResponse rhs=(Do_queryServiSpecIdsByCustIdResponse)rhs0;
		if(!ObjectUtils.equals(servieSpecId_list, rhs.servieSpecId_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(servieSpecId_list)
		.toHashCode();
	}


}