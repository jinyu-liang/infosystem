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
@XmlType(propOrder={"reguideInfoList"})
public class Do_queryReguideInfoResp extends BaseResponse implements IComplexEntity{


	@XmlElement(name="reguideInfoList")
	private ReguideInfoList reguideInfoList;

	public void setReguideInfoList(ReguideInfoList obj){
		this.reguideInfoList = obj;
	}

	public ReguideInfoList getReguideInfoList(){
		return reguideInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryReguideInfoResp rhs=(Do_queryReguideInfoResp)rhs0;
		if(!ObjectUtils.equals(reguideInfoList, rhs.reguideInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideInfoList)
		.toHashCode();
	}


}