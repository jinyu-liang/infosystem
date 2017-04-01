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
@XmlType(propOrder={"phoneId","startDate","endDate","topX","queryTypeList"})
public class SQueryGraphReq implements IComplexEntity{


	@XmlElement(name="queryTypeList")
	private List<SQueryGraphQueryTypeList> queryTypeList;

	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="startDate")
	private String startDate;

	@XmlElement(name="endDate")
	private String endDate;

	@XmlElement(name="topX")
	private Integer topX = 1;

	public void setQueryTypeList(List<SQueryGraphQueryTypeList> obj){
		this.queryTypeList = obj;
	}

	public List<SQueryGraphQueryTypeList> getQueryTypeList(){
		return queryTypeList;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setStartDate(String obj){
		this.startDate = obj;
	}

	public String getStartDate(){
		return startDate;
	}

	public void setEndDate(String obj){
		this.endDate = obj;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setTopX(Integer obj){
		this.topX = obj;
	}

	public Integer getTopX(){
		return topX;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryGraphReq rhs=(SQueryGraphReq)rhs0;
		if(!ObjectUtils.equals(queryTypeList, rhs.queryTypeList)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(startDate, rhs.startDate)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		if(!ObjectUtils.equals(topX, rhs.topX)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(queryTypeList)
		.append(phoneId)
		.append(startDate)
		.append(endDate)
		.append(topX)
		.toHashCode();
	}


}