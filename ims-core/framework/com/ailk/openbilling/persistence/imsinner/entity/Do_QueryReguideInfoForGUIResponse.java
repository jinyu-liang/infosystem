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
@XmlType(propOrder={"reguideInfo_list"})
public class Do_QueryReguideInfoForGUIResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="reguideInfo_list")
	private List<ReguideInfo> reguideInfo_list;

	public void setReguideInfo_list(List<ReguideInfo> obj){
		this.reguideInfo_list = obj;
	}

	public List<ReguideInfo> getReguideInfo_list(){
		return reguideInfo_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryReguideInfoForGUIResponse rhs=(Do_QueryReguideInfoForGUIResponse)rhs0;
		if(!ObjectUtils.equals(reguideInfo_list, rhs.reguideInfo_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideInfo_list)
		.toHashCode();
	}


}