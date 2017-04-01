package com.ailk.openbilling.persistence.bill.entity;

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
@XmlType(propOrder={"cycleSpecId","caCycleSpecList"})
public class CaCycleSpecOut implements IComplexEntity{


	@XmlElement(name="caCycleSpecList")
	private List<CaCycleSpec> caCycleSpecList;

	@XmlElement(name="cycleSpecId")
	private Integer cycleSpecId;

	public void setCaCycleSpecList(List<CaCycleSpec> obj){
		this.caCycleSpecList = obj;
	}

	public List<CaCycleSpec> getCaCycleSpecList(){
		return caCycleSpecList;
	}

	public void setCycleSpecId(Integer obj){
		this.cycleSpecId = obj;
	}

	public Integer getCycleSpecId(){
		return cycleSpecId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaCycleSpecOut rhs=(CaCycleSpecOut)rhs0;
		if(!ObjectUtils.equals(caCycleSpecList, rhs.caCycleSpecList)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(caCycleSpecList)
		.append(cycleSpecId)
		.toHashCode();
	}


}