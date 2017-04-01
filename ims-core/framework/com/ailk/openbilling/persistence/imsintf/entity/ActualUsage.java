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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"group_name","subActualUsageList","start_date","end_date"})
public class ActualUsage implements IComplexEntity{


	@XmlElement(name="subActualUsageList")
	private List<SubActualUsage> subActualUsageList;

	@XmlElement(name="group_name")
	private String group_name;

	@XmlElement(name="start_date")
	private Date start_date;

	@XmlElement(name="end_date")
	private Date end_date;

	public void setSubActualUsageList(List<SubActualUsage> obj){
		this.subActualUsageList = obj;
	}

	public List<SubActualUsage> getSubActualUsageList(){
		return subActualUsageList;
	}

	public void setGroup_name(String obj){
		this.group_name = obj;
	}

	public String getGroup_name(){
		return group_name;
	}

	public void setStart_date(Date obj){
		this.start_date = obj;
	}

	public Date getStart_date(){
		return start_date;
	}

	public void setEnd_date(Date obj){
		this.end_date = obj;
	}

	public Date getEnd_date(){
		return end_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ActualUsage rhs=(ActualUsage)rhs0;
		if(!ObjectUtils.equals(subActualUsageList, rhs.subActualUsageList)) return false;
		if(!ObjectUtils.equals(group_name, rhs.group_name)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subActualUsageList)
		.append(group_name)
		.append(start_date)
		.append(end_date)
		.toHashCode();
	}


}