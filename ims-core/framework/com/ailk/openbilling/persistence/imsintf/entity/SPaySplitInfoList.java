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
@XmlType(propOrder={"splitInfoList_item"})
public class SPaySplitInfoList implements IComplexEntity{


	@XmlElement(name="splitInfoList_item")
	private SPaySplitInfo[] splitInfoList_item;

	public void setSplitInfoList_item(SPaySplitInfo[] obj){
		this.splitInfoList_item = obj;
	}

	public SPaySplitInfo[] getSplitInfoList_item(){
		return splitInfoList_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPaySplitInfoList rhs=(SPaySplitInfoList)rhs0;
		if(!ObjectUtils.equals(splitInfoList_item, rhs.splitInfoList_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(splitInfoList_item)
		.toHashCode();
	}


}