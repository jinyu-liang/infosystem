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
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"cmResource"})
public class Do_queryBatchUserResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="cmResource")
	private List<CmResource> cmResource;

	public void setCmResource(List<CmResource> obj){
		this.cmResource = obj;
	}

	public List<CmResource> getCmResource(){
		return cmResource;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBatchUserResponse rhs=(Do_queryBatchUserResponse)rhs0;
		if(!ObjectUtils.equals(cmResource, rhs.cmResource)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cmResource)
		.toHashCode();
	}


}