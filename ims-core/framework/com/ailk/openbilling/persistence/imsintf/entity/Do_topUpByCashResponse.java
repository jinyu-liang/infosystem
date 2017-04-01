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
@XmlType(propOrder={"top_Up_Resp"})
public class Do_topUpByCashResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="top_Up_Resp")
	private STopUpResponse top_Up_Resp;

	public void setTop_Up_Resp(STopUpResponse obj){
		this.top_Up_Resp = obj;
	}

	public STopUpResponse getTop_Up_Resp(){
		return top_Up_Resp;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_topUpByCashResponse rhs=(Do_topUpByCashResponse)rhs0;
		if(!ObjectUtils.equals(top_Up_Resp, rhs.top_Up_Resp)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(top_Up_Resp)
		.toHashCode();
	}


}