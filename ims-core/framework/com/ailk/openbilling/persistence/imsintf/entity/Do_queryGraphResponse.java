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
@XmlType(propOrder={"highestTransactionGroupList","longestCallingSecondsGroupList"})
public class Do_queryGraphResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="highestTransactionGroupList")
	private List<HighestTransactionGroup> highestTransactionGroupList;

	@XmlElement(name="longestCallingSecondsGroupList")
	private List<LongestCallingSecondsGroup> longestCallingSecondsGroupList;

	public void setHighestTransactionGroupList(List<HighestTransactionGroup> obj){
		this.highestTransactionGroupList = obj;
	}

	public List<HighestTransactionGroup> getHighestTransactionGroupList(){
		return highestTransactionGroupList;
	}

	public void setLongestCallingSecondsGroupList(List<LongestCallingSecondsGroup> obj){
		this.longestCallingSecondsGroupList = obj;
	}

	public List<LongestCallingSecondsGroup> getLongestCallingSecondsGroupList(){
		return longestCallingSecondsGroupList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryGraphResponse rhs=(Do_queryGraphResponse)rhs0;
		if(!ObjectUtils.equals(highestTransactionGroupList, rhs.highestTransactionGroupList)) return false;
		if(!ObjectUtils.equals(longestCallingSecondsGroupList, rhs.longestCallingSecondsGroupList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(highestTransactionGroupList)
		.append(longestCallingSecondsGroupList)
		.toHashCode();
	}


}