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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"busiServiceList"})
public class Do_queryServicesByUserIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="busiServiceList")
	private SServiceReturnList busiServiceList;

	public void setBusiServiceList(SServiceReturnList obj){
		this.busiServiceList = obj;
	}

	public SServiceReturnList getBusiServiceList(){
		return busiServiceList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryServicesByUserIdResponse rhs=(Do_queryServicesByUserIdResponse)rhs0;
		if(!ObjectUtils.equals(busiServiceList, rhs.busiServiceList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiServiceList)
		.toHashCode();
	}


}