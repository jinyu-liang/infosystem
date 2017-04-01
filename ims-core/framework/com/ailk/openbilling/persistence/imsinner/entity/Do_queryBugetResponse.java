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
import com.ailk.openbilling.persistence.imsintf.entity.BudgetList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"budget_list"})
public class Do_queryBugetResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="budget_list")
	private BudgetList budget_list;

	public void setBudget_list(BudgetList obj){
		this.budget_list = obj;
	}

	public BudgetList getBudget_list(){
		return budget_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBugetResponse rhs=(Do_queryBugetResponse)rhs0;
		if(!ObjectUtils.equals(budget_list, rhs.budget_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(budget_list)
		.toHashCode();
	}


}