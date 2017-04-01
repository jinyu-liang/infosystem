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
@XmlType(propOrder={"operInfoList"})
public class Do_queryOperInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="operInfoList")
	private List<SOperInfoResp> operInfoList;

	public void setOperInfoList(List<SOperInfoResp> obj){
		this.operInfoList = obj;
	}

	public List<SOperInfoResp> getOperInfoList(){
		return operInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryOperInfoResponse rhs=(Do_queryOperInfoResponse)rhs0;
		if(!ObjectUtils.equals(operInfoList, rhs.operInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(operInfoList)
		.toHashCode();
	}


}