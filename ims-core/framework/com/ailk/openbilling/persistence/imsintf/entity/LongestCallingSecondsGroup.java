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
@XmlType(propOrder={"destinationNumberList","seconds"})
public class LongestCallingSecondsGroup implements IComplexEntity{


	@XmlElement(name="destinationNumberList")
	private List<String> destinationNumberList;

	@XmlElement(name="seconds")
	private Long seconds;

	public void setDestinationNumberList(List<String> obj){
		this.destinationNumberList = obj;
	}

	public List<String> getDestinationNumberList(){
		return destinationNumberList;
	}

	public void setSeconds(Long obj){
		this.seconds = obj;
	}

	public Long getSeconds(){
		return seconds;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		LongestCallingSecondsGroup rhs=(LongestCallingSecondsGroup)rhs0;
		if(!ObjectUtils.equals(destinationNumberList, rhs.destinationNumberList)) return false;
		if(!ObjectUtils.equals(seconds, rhs.seconds)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(destinationNumberList)
		.append(seconds)
		.toHashCode();
	}


}