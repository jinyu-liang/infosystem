package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(propOrder={"capMaxAccumulateList"})
public class CapMaxAccumulateList implements IComplexEntity{


	@XmlElement(name="capMaxAccumulateList")
	private List<CapMaxAccumulate> capMaxAccumulateList;

	public void setCapMaxAccumulateList(List<CapMaxAccumulate> obj){
		this.capMaxAccumulateList = obj;
	}

	public List<CapMaxAccumulate> getCapMaxAccumulateList(){
		return capMaxAccumulateList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CapMaxAccumulateList rhs=(CapMaxAccumulateList)rhs0;
		if(!ObjectUtils.equals(capMaxAccumulateList, rhs.capMaxAccumulateList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(capMaxAccumulateList)
		.toHashCode();
	}


}