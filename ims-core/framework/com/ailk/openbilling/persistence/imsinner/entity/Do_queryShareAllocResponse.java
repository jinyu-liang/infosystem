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
@XmlType(propOrder={"freeItem_list","shareAlloc_list"})
public class Do_queryShareAllocResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="freeItem_list")
	private List<FreeItem4GUI> freeItem_list;

	@XmlElement(name="shareAlloc_list")
	private List<CoProdShareAlloc4GUI> shareAlloc_list;

	public void setFreeItem_list(List<FreeItem4GUI> obj){
		this.freeItem_list = obj;
	}

	public List<FreeItem4GUI> getFreeItem_list(){
		return freeItem_list;
	}

	public void setShareAlloc_list(List<CoProdShareAlloc4GUI> obj){
		this.shareAlloc_list = obj;
	}

	public List<CoProdShareAlloc4GUI> getShareAlloc_list(){
		return shareAlloc_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryShareAllocResponse rhs=(Do_queryShareAllocResponse)rhs0;
		if(!ObjectUtils.equals(freeItem_list, rhs.freeItem_list)) return false;
		if(!ObjectUtils.equals(shareAlloc_list, rhs.shareAlloc_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeItem_list)
		.append(shareAlloc_list)
		.toHashCode();
	}


}