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
@XmlType(propOrder={"groupInfo","product_order_list"})
public class SNewGroupReq implements IComplexEntity{


	@XmlElement(name="groupInfo")
	private SGroup groupInfo;

	@XmlElement(name="product_order_list")
	private SProductOrderList product_order_list;

	public void setGroupInfo(SGroup obj){
		this.groupInfo = obj;
	}

	public SGroup getGroupInfo(){
		return groupInfo;
	}

	public void setProduct_order_list(SProductOrderList obj){
		this.product_order_list = obj;
	}

	public SProductOrderList getProduct_order_list(){
		return product_order_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNewGroupReq rhs=(SNewGroupReq)rhs0;
		if(!ObjectUtils.equals(groupInfo, rhs.groupInfo)) return false;
		if(!ObjectUtils.equals(product_order_list, rhs.product_order_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groupInfo)
		.append(product_order_list)
		.toHashCode();
	}


}