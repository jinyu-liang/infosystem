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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"splitList_Item"})
public class Do_querySplitListResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="splitList_Item")
	private SplitList[] splitList_Item;

	public void setSplitList_Item(SplitList[] obj){
		this.splitList_Item = obj;
	}

	public SplitList[] getSplitList_Item(){
		return splitList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_querySplitListResponse rhs=(Do_querySplitListResponse)rhs0;
		if(!ObjectUtils.equals(splitList_Item, rhs.splitList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(splitList_Item)
		.toHashCode();
	}


}