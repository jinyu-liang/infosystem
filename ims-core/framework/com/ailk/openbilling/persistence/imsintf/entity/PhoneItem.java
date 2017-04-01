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
@XmlType(propOrder={"user_id","phone_id","flag","productSeqItemList","oper_type"})
public class PhoneItem implements IComplexEntity{


	@XmlElement(name="productSeqItemList")
	private ProductSeqItemList productSeqItemList;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="flag")
	private Short flag;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setProductSeqItemList(ProductSeqItemList obj){
		this.productSeqItemList = obj;
	}

	public ProductSeqItemList getProductSeqItemList(){
		return productSeqItemList;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setFlag(Short obj){
		this.flag = obj;
	}

	public Short getFlag(){
		return flag;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PhoneItem rhs=(PhoneItem)rhs0;
		if(!ObjectUtils.equals(productSeqItemList, rhs.productSeqItemList)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productSeqItemList)
		.append(user_id)
		.append(phone_id)
		.append(flag)
		.append(oper_type)
		.toHashCode();
	}


}