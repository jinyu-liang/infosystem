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
@XmlType(propOrder={"destinationNumberList","times"})
public class HighestTransactionGroup implements IComplexEntity{


	@XmlElement(name="destinationNumberList")
	private List<String> destinationNumberList;

	@XmlElement(name="times")
	private Long times;

	public void setDestinationNumberList(List<String> obj){
		this.destinationNumberList = obj;
	}

	public List<String> getDestinationNumberList(){
		return destinationNumberList;
	}

	public void setTimes(Long obj){
		this.times = obj;
	}

	public Long getTimes(){
		return times;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		HighestTransactionGroup rhs=(HighestTransactionGroup)rhs0;
		if(!ObjectUtils.equals(destinationNumberList, rhs.destinationNumberList)) return false;
		if(!ObjectUtils.equals(times, rhs.times)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(destinationNumberList)
		.append(times)
		.toHashCode();
	}


}