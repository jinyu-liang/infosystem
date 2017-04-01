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
@XmlType(propOrder={"sProdResultList"})
public class Do_VPNGroupRelationResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sProdResultList")
	private SProductOrderResultList sProdResultList;

	public void setSProdResultList(SProductOrderResultList obj){
		this.sProdResultList = obj;
	}

	public SProductOrderResultList getSProdResultList(){
		return sProdResultList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_VPNGroupRelationResponse rhs=(Do_VPNGroupRelationResponse)rhs0;
		if(!ObjectUtils.equals(sProdResultList, rhs.sProdResultList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sProdResultList)
		.toHashCode();
	}


}