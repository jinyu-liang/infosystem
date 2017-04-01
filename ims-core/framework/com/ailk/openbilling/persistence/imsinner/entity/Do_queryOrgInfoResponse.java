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
@XmlType(propOrder={"orgInfoList"})
public class Do_queryOrgInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="orgInfoList")
	private List<SOrgInfoResp> orgInfoList;

	public void setOrgInfoList(List<SOrgInfoResp> obj){
		this.orgInfoList = obj;
	}

	public List<SOrgInfoResp> getOrgInfoList(){
		return orgInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryOrgInfoResponse rhs=(Do_queryOrgInfoResponse)rhs0;
		if(!ObjectUtils.equals(orgInfoList, rhs.orgInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orgInfoList)
		.toHashCode();
	}


}