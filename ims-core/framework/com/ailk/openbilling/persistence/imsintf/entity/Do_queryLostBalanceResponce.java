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
@XmlType(propOrder={"balanceList"})
public class Do_queryLostBalanceResponce extends BaseResponse implements IComplexEntity{


	@XmlElement(name="balanceList")
	private SBalanceList balanceList;

	public void setBalanceList(SBalanceList obj){
		this.balanceList = obj;
	}

	public SBalanceList getBalanceList(){
		return balanceList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryLostBalanceResponce rhs=(Do_queryLostBalanceResponce)rhs0;
		if(!ObjectUtils.equals(balanceList, rhs.balanceList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balanceList)
		.toHashCode();
	}


}