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
import com.ailk.openbilling.persistence.imsintf.entity.SimItemList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sim_Item"})
public class Do_queryMultiSim4GuiResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sim_Item")
	private SimItemList sim_Item;

	public void setSim_Item(SimItemList obj){
		this.sim_Item = obj;
	}

	public SimItemList getSim_Item(){
		return sim_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryMultiSim4GuiResponse rhs=(Do_queryMultiSim4GuiResponse)rhs0;
		if(!ObjectUtils.equals(sim_Item, rhs.sim_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sim_Item)
		.toHashCode();
	}


}