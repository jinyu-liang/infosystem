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
@XmlType(propOrder={"rechargeLogList_Item"})
public class RechargeLogList implements IComplexEntity{


	@XmlElement(name="rechargeLogList_Item")
	private RechargeLog[] rechargeLogList_Item;

	public void setRechargeLogList_Item(RechargeLog[] obj){
		this.rechargeLogList_Item = obj;
	}

	public RechargeLog[] getRechargeLogList_Item(){
		return rechargeLogList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RechargeLogList rhs=(RechargeLogList)rhs0;
		if(!ObjectUtils.equals(rechargeLogList_Item, rhs.rechargeLogList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(rechargeLogList_Item)
		.toHashCode();
	}


}