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
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;
import com.ailk.openbilling.persistence.imsintf.entity.SubGroupList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"number_of_sub","number_of_cug","productOrderList","groupInfoList","subGroupList"})
public class Do_queryGroupInfo4GUIResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="productOrderList")
	private SProductOrderList productOrderList;

	@XmlElement(name="groupInfoList")
	private SGroupList groupInfoList;

	@XmlElement(name="subGroupList")
	private SubGroupList subGroupList;

	@XmlElement(name="number_of_sub")
	private Integer number_of_sub;

	@XmlElement(name="number_of_cug")
	private Integer number_of_cug;

	public void setProductOrderList(SProductOrderList obj){
		this.productOrderList = obj;
	}

	public SProductOrderList getProductOrderList(){
		return productOrderList;
	}

	public void setGroupInfoList(SGroupList obj){
		this.groupInfoList = obj;
	}

	public SGroupList getGroupInfoList(){
		return groupInfoList;
	}

	public void setSubGroupList(SubGroupList obj){
		this.subGroupList = obj;
	}

	public SubGroupList getSubGroupList(){
		return subGroupList;
	}

	public void setNumber_of_sub(Integer obj){
		this.number_of_sub = obj;
	}

	public Integer getNumber_of_sub(){
		return number_of_sub;
	}

	public void setNumber_of_cug(Integer obj){
		this.number_of_cug = obj;
	}

	public Integer getNumber_of_cug(){
		return number_of_cug;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryGroupInfo4GUIResponse rhs=(Do_queryGroupInfo4GUIResponse)rhs0;
		if(!ObjectUtils.equals(productOrderList, rhs.productOrderList)) return false;
		if(!ObjectUtils.equals(groupInfoList, rhs.groupInfoList)) return false;
		if(!ObjectUtils.equals(subGroupList, rhs.subGroupList)) return false;
		if(!ObjectUtils.equals(number_of_sub, rhs.number_of_sub)) return false;
		if(!ObjectUtils.equals(number_of_cug, rhs.number_of_cug)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productOrderList)
		.append(groupInfoList)
		.append(subGroupList)
		.append(number_of_sub)
		.append(number_of_cug)
		.toHashCode();
	}


}