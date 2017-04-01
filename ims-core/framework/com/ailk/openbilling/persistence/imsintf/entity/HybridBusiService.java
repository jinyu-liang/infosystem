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
@XmlType(propOrder={"hybrid_rule","status","sBusiServiceList"})
public class HybridBusiService implements IComplexEntity{


	@XmlElement(name="sBusiServiceList")
	private SBusiServiceList sBusiServiceList;

	@XmlElement(name="hybrid_rule")
	private String hybrid_rule;

	@XmlElement(name="status")
	private String status;

	public void setSBusiServiceList(SBusiServiceList obj){
		this.sBusiServiceList = obj;
	}

	public SBusiServiceList getSBusiServiceList(){
		return sBusiServiceList;
	}

	public void setHybrid_rule(String obj){
		this.hybrid_rule = obj;
	}

	public String getHybrid_rule(){
		return hybrid_rule;
	}

	public void setStatus(String obj){
		this.status = obj;
	}

	public String getStatus(){
		return status;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		HybridBusiService rhs=(HybridBusiService)rhs0;
		if(!ObjectUtils.equals(sBusiServiceList, rhs.sBusiServiceList)) return false;
		if(!ObjectUtils.equals(hybrid_rule, rhs.hybrid_rule)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBusiServiceList)
		.append(hybrid_rule)
		.append(status)
		.toHashCode();
	}


}