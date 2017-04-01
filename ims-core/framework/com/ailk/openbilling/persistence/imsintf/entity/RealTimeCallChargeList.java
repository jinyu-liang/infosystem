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
@XmlType(propOrder={"realTimeCallChargeList_Item"})
public class RealTimeCallChargeList implements IComplexEntity{


	@XmlElement(name="realTimeCallChargeList_Item")
	private RealTimeCallCharge[] realTimeCallChargeList_Item;

	public void setRealTimeCallChargeList_Item(RealTimeCallCharge[] obj){
		this.realTimeCallChargeList_Item = obj;
	}

	public RealTimeCallCharge[] getRealTimeCallChargeList_Item(){
		return realTimeCallChargeList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RealTimeCallChargeList rhs=(RealTimeCallChargeList)rhs0;
		if(!ObjectUtils.equals(realTimeCallChargeList_Item, rhs.realTimeCallChargeList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(realTimeCallChargeList_Item)
		.toHashCode();
	}


}