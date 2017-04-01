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
@XmlType(propOrder={"sim","product_id","oper_type","serial_no"})
public class SimItem implements IComplexEntity{


	@XmlElement(name="sim")
	private String sim;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="serial_no")
	private String serial_no;

	public void setSim(String obj){
		this.sim = obj;
	}

	public String getSim(){
		return sim;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setSerial_no(String obj){
		this.serial_no = obj;
	}

	public String getSerial_no(){
		return serial_no;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SimItem rhs=(SimItem)rhs0;
		if(!ObjectUtils.equals(sim, rhs.sim)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(serial_no, rhs.serial_no)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sim)
		.append(product_id)
		.append(oper_type)
		.append(serial_no)
		.toHashCode();
	}


}