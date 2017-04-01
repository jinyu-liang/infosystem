package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import java.util.List;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"coProd","coProdCharValueList","coProdBillCycleList","coProdPriceParamList","pmProductOffering"})
public class ProdList implements IComplexEntity{


	@XmlElement(name="coProd")
	private CoProd coProd;

	@XmlElement(name="coProdCharValueList")
	private List<CoProdCharValue> coProdCharValueList;

	@XmlElement(name="coProdBillCycleList")
	private List<CoProdBillingCycle> coProdBillCycleList;

	@XmlElement(name="coProdPriceParamList")
	private List<CoProdPriceParam> coProdPriceParamList;

	@XmlElement(name="pmProductOffering")
	private PmProductOffering pmProductOffering;

	public void setCoProd(CoProd obj){
		this.coProd = obj;
	}

	public CoProd getCoProd(){
		return coProd;
	}

	public void setCoProdCharValueList(List<CoProdCharValue> obj){
		this.coProdCharValueList = obj;
	}

	public List<CoProdCharValue> getCoProdCharValueList(){
		return coProdCharValueList;
	}

	public void setCoProdBillCycleList(List<CoProdBillingCycle> obj){
		this.coProdBillCycleList = obj;
	}

	public List<CoProdBillingCycle> getCoProdBillCycleList(){
		return coProdBillCycleList;
	}

	public void setCoProdPriceParamList(List<CoProdPriceParam> obj){
		this.coProdPriceParamList = obj;
	}

	public List<CoProdPriceParam> getCoProdPriceParamList(){
		return coProdPriceParamList;
	}

	public void setPmProductOffering(PmProductOffering obj){
		this.pmProductOffering = obj;
	}

	public PmProductOffering getPmProductOffering(){
		return pmProductOffering;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProdList rhs=(ProdList)rhs0;
		if(!ObjectUtils.equals(coProd, rhs.coProd)) return false;
		if(!ObjectUtils.equals(coProdCharValueList, rhs.coProdCharValueList)) return false;
		if(!ObjectUtils.equals(coProdBillCycleList, rhs.coProdBillCycleList)) return false;
		if(!ObjectUtils.equals(coProdPriceParamList, rhs.coProdPriceParamList)) return false;
		if(!ObjectUtils.equals(pmProductOffering, rhs.pmProductOffering)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(coProd)
		.append(coProdCharValueList)
		.append(coProdBillCycleList)
		.append(coProdPriceParamList)
		.append(pmProductOffering)
		.toHashCode();
	}


}