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
@XmlType(propOrder={"sBalanceList"})
public class Do_removeUserResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sBalanceList")
	private SBalanceList sBalanceList;

	public void setSBalanceList(SBalanceList obj){
		this.sBalanceList = obj;
	}

	public SBalanceList getSBalanceList(){
		return sBalanceList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_removeUserResponse rhs=(Do_removeUserResponse)rhs0;
		if(!ObjectUtils.equals(sBalanceList, rhs.sBalanceList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalanceList)
		.toHashCode();
	}


}