package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"coProd","coProdCharValueList","coProdBillingCycleList","coProdPriceParamList","sProdSpecCharList"})
public class Do_queryProductInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="coProd")
	private CoProd coProd;

	@XmlElement(name="coProdCharValueList")
	private List<CoProdCharValue> coProdCharValueList;

	@XmlElement(name="coProdBillingCycleList")
	private List<CoProdBillingCycle> coProdBillingCycleList;

	@XmlElement(name="coProdPriceParamList")
	private List<CoProdPriceParam> coProdPriceParamList;

	@XmlElement(name="sProdSpecCharList")
	private List<SProdSpecChar> sProdSpecCharList;

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

	public void setCoProdBillingCycleList(List<CoProdBillingCycle> obj){
		this.coProdBillingCycleList = obj;
	}

	public List<CoProdBillingCycle> getCoProdBillingCycleList(){
		return coProdBillingCycleList;
	}

	public void setCoProdPriceParamList(List<CoProdPriceParam> obj){
		this.coProdPriceParamList = obj;
	}

	public List<CoProdPriceParam> getCoProdPriceParamList(){
		return coProdPriceParamList;
	}

	public void setSProdSpecCharList(List<SProdSpecChar> obj){
		this.sProdSpecCharList = obj;
	}

	public List<SProdSpecChar> getSProdSpecCharList(){
		return sProdSpecCharList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryProductInfoResponse rhs=(Do_queryProductInfoResponse)rhs0;
		if(!ObjectUtils.equals(coProd, rhs.coProd)) return false;
		if(!ObjectUtils.equals(coProdCharValueList, rhs.coProdCharValueList)) return false;
		if(!ObjectUtils.equals(coProdBillingCycleList, rhs.coProdBillingCycleList)) return false;
		if(!ObjectUtils.equals(coProdPriceParamList, rhs.coProdPriceParamList)) return false;
		if(!ObjectUtils.equals(sProdSpecCharList, rhs.sProdSpecCharList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(coProd)
		.append(coProdCharValueList)
		.append(coProdBillingCycleList)
		.append(coProdPriceParamList)
		.append(sProdSpecCharList)
		.toHashCode();
	}


}