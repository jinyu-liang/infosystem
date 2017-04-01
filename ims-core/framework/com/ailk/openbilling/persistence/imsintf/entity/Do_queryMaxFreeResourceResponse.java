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
@XmlType(propOrder={"maxFreeResourceList","freeResourceList"})
public class Do_queryMaxFreeResourceResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="maxFreeResourceList")
	private MaxFreeResourceList maxFreeResourceList;

	@XmlElement(name="freeResourceList")
	private FreeResourceList freeResourceList;

	public void setMaxFreeResourceList(MaxFreeResourceList obj){
		this.maxFreeResourceList = obj;
	}

	public MaxFreeResourceList getMaxFreeResourceList(){
		return maxFreeResourceList;
	}

	public void setFreeResourceList(FreeResourceList obj){
		this.freeResourceList = obj;
	}

	public FreeResourceList getFreeResourceList(){
		return freeResourceList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryMaxFreeResourceResponse rhs=(Do_queryMaxFreeResourceResponse)rhs0;
		if(!ObjectUtils.equals(maxFreeResourceList, rhs.maxFreeResourceList)) return false;
		if(!ObjectUtils.equals(freeResourceList, rhs.freeResourceList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(maxFreeResourceList)
		.append(freeResourceList)
		.toHashCode();
	}


}