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
@XmlType(propOrder={"soNbr","cycleSpecId","caMdbBillCycleList"})
public class CaBillCycleOut implements IComplexEntity{


	@XmlElement(name="caMdbBillCycleList")
	private List<CaMdbBillCycle> caMdbBillCycleList;

	@XmlElement(name="soNbr")
	private Long soNbr;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	public void setCaMdbBillCycleList(List<CaMdbBillCycle> obj){
		this.caMdbBillCycleList = obj;
	}

	public List<CaMdbBillCycle> getCaMdbBillCycleList(){
		return caMdbBillCycleList;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaBillCycleOut rhs=(CaBillCycleOut)rhs0;
		if(!ObjectUtils.equals(caMdbBillCycleList, rhs.caMdbBillCycleList)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(caMdbBillCycleList)
		.append(soNbr)
		.append(cycleSpecId)
		.toHashCode();
	}


}