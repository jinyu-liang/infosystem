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
@XmlType(propOrder={"req"})
public class SOperateMultiSimReq implements IComplexEntity{


	@XmlElement(name="req")
	private SimItemList req;

	public void setReq(SimItemList obj){
		this.req = obj;
	}

	public SimItemList getReq(){
		return req;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOperateMultiSimReq rhs=(SOperateMultiSimReq)rhs0;
		if(!ObjectUtils.equals(req, rhs.req)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(req)
		.toHashCode();
	}


}