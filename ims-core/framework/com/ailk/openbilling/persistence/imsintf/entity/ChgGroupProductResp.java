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
@XmlType(propOrder={"sProductOrderResultList"})
public class ChgGroupProductResp extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sProductOrderResultList")
	private SProductOrderResultList sProductOrderResultList;

	public void setSProductOrderResultList(SProductOrderResultList obj){
		this.sProductOrderResultList = obj;
	}

	public SProductOrderResultList getSProductOrderResultList(){
		return sProductOrderResultList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ChgGroupProductResp rhs=(ChgGroupProductResp)rhs0;
		if(!ObjectUtils.equals(sProductOrderResultList, rhs.sProductOrderResultList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sProductOrderResultList)
		.toHashCode();
	}


}