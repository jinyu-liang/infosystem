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
@XmlType(propOrder={"budgetList_Item"})
public class BudgetList implements IComplexEntity{


	@XmlElement(name="budgetList_Item")
	private BudgetControl[] budgetList_Item;

	public void setBudgetList_Item(BudgetControl[] obj){
		this.budgetList_Item = obj;
	}

	public BudgetControl[] getBudgetList_Item(){
		return budgetList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BudgetList rhs=(BudgetList)rhs0;
		if(!ObjectUtils.equals(budgetList_Item, rhs.budgetList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(budgetList_Item)
		.toHashCode();
	}


}