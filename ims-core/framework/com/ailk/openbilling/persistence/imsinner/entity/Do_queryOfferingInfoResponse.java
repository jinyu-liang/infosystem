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
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpec;
import java.util.List;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"offering","pricePlan","productSpec","prodSpecCharList","prodLifecycle"})
public class Do_queryOfferingInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="offering")
	private PmProductOffering offering;

	@XmlElement(name="pricePlan")
	private PmProductPricingPlan pricePlan;

	@XmlElement(name="productSpec")
	private PmProductSpec productSpec;

	@XmlElement(name="prodSpecCharList")
	private List<SProdSpecChar> prodSpecCharList;

	@XmlElement(name="prodLifecycle")
	private PmProductOfferLifecycle prodLifecycle;

	public void setOffering(PmProductOffering obj){
		this.offering = obj;
	}

	public PmProductOffering getOffering(){
		return offering;
	}

	public void setPricePlan(PmProductPricingPlan obj){
		this.pricePlan = obj;
	}

	public PmProductPricingPlan getPricePlan(){
		return pricePlan;
	}

	public void setProductSpec(PmProductSpec obj){
		this.productSpec = obj;
	}

	public PmProductSpec getProductSpec(){
		return productSpec;
	}

	public void setProdSpecCharList(List<SProdSpecChar> obj){
		this.prodSpecCharList = obj;
	}

	public List<SProdSpecChar> getProdSpecCharList(){
		return prodSpecCharList;
	}

	public void setProdLifecycle(PmProductOfferLifecycle obj){
		this.prodLifecycle = obj;
	}

	public PmProductOfferLifecycle getProdLifecycle(){
		return prodLifecycle;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryOfferingInfoResponse rhs=(Do_queryOfferingInfoResponse)rhs0;
		if(!ObjectUtils.equals(offering, rhs.offering)) return false;
		if(!ObjectUtils.equals(pricePlan, rhs.pricePlan)) return false;
		if(!ObjectUtils.equals(productSpec, rhs.productSpec)) return false;
		if(!ObjectUtils.equals(prodSpecCharList, rhs.prodSpecCharList)) return false;
		if(!ObjectUtils.equals(prodLifecycle, rhs.prodLifecycle)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offering)
		.append(pricePlan)
		.append(productSpec)
		.append(prodSpecCharList)
		.append(prodLifecycle)
		.toHashCode();
	}


}