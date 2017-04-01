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
@XmlType(propOrder={"firstBillDay"})
public class FirstBillDays implements IComplexEntity{


	@XmlElement(name="firstBillDay")
	private List<Integer> firstBillDay;

	public void setFirstBillDay(List<Integer> obj){
		this.firstBillDay = obj;
	}

	public List<Integer> getFirstBillDay(){
		return firstBillDay;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FirstBillDays rhs=(FirstBillDays)rhs0;
		if(!ObjectUtils.equals(firstBillDay, rhs.firstBillDay)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(firstBillDay)
		.toHashCode();
	}


}