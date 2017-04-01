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
import com.ailk.openbilling.persistence.imsintf.entity.SChangeOwnerReq;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"initSChangeOwnerReq"})
public class Do_initSChangeOwnerResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="initSChangeOwnerReq")
	private SChangeOwnerReq initSChangeOwnerReq;

	public void setInitSChangeOwnerReq(SChangeOwnerReq obj){
		this.initSChangeOwnerReq = obj;
	}

	public SChangeOwnerReq getInitSChangeOwnerReq(){
		return initSChangeOwnerReq;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_initSChangeOwnerResponse rhs=(Do_initSChangeOwnerResponse)rhs0;
		if(!ObjectUtils.equals(initSChangeOwnerReq, rhs.initSChangeOwnerReq)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(initSChangeOwnerReq)
		.toHashCode();
	}


}