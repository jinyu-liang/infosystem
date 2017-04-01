package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"cycleUnit"})
public class CycleUnits implements IComplexEntity{


	@XmlElement(name="cycleUnit")
	private List<Integer> cycleUnit;

	public void setCycleUnit(List<Integer> obj){
		this.cycleUnit = obj;
	}

	public List<Integer> getCycleUnit(){
		return cycleUnit;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CycleUnits rhs=(CycleUnits)rhs0;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cycleUnit)
		.toHashCode();
	}


}