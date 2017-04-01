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
@XmlType(propOrder={"transferLogList_Item"})
public class TransferLogList implements IComplexEntity{


	@XmlElement(name="transferLogList_Item")
	private TransferLog[] transferLogList_Item;

	public void setTransferLogList_Item(TransferLog[] obj){
		this.transferLogList_Item = obj;
	}

	public TransferLog[] getTransferLogList_Item(){
		return transferLogList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		TransferLogList rhs=(TransferLogList)rhs0;
		if(!ObjectUtils.equals(transferLogList_Item, rhs.transferLogList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(transferLogList_Item)
		.toHashCode();
	}


}