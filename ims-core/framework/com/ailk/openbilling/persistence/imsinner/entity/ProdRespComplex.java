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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"co_prod","co_prod_char_value_list","co_prod_bill_cycle_list","co_prod_param_list"})
public class ProdRespComplex implements IComplexEntity{


	@XmlElement(name="co_prod")
	private CoProd co_prod;

	@XmlElement(name="co_prod_char_value_list")
	private List<CoProdCharValue> co_prod_char_value_list;

	@XmlElement(name="co_prod_bill_cycle_list")
	private List<CoProdBillingCycle> co_prod_bill_cycle_list;

	@XmlElement(name="co_prod_param_list")
	private List<CoProdPriceParam> co_prod_param_list;

	public void setCo_prod(CoProd obj){
		this.co_prod = obj;
	}

	public CoProd getCo_prod(){
		return co_prod;
	}

	public void setCo_prod_char_value_list(List<CoProdCharValue> obj){
		this.co_prod_char_value_list = obj;
	}

	public List<CoProdCharValue> getCo_prod_char_value_list(){
		return co_prod_char_value_list;
	}

	public void setCo_prod_bill_cycle_list(List<CoProdBillingCycle> obj){
		this.co_prod_bill_cycle_list = obj;
	}

	public List<CoProdBillingCycle> getCo_prod_bill_cycle_list(){
		return co_prod_bill_cycle_list;
	}

	public void setCo_prod_param_list(List<CoProdPriceParam> obj){
		this.co_prod_param_list = obj;
	}

	public List<CoProdPriceParam> getCo_prod_param_list(){
		return co_prod_param_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProdRespComplex rhs=(ProdRespComplex)rhs0;
		if(!ObjectUtils.equals(co_prod, rhs.co_prod)) return false;
		if(!ObjectUtils.equals(co_prod_char_value_list, rhs.co_prod_char_value_list)) return false;
		if(!ObjectUtils.equals(co_prod_bill_cycle_list, rhs.co_prod_bill_cycle_list)) return false;
		if(!ObjectUtils.equals(co_prod_param_list, rhs.co_prod_param_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(co_prod)
		.append(co_prod_char_value_list)
		.append(co_prod_bill_cycle_list)
		.append(co_prod_param_list)
		.toHashCode();
	}


}