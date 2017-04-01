package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"num","size","count","orderFieldList"})
public class SPage implements IComplexEntity{


	@XmlElement(name="orderFieldList")
	private List<SOrderField> orderFieldList;

	@XmlElement(name="num")
	private Integer num;

	@XmlElement(name="size")
	private Integer size;

	@XmlElement(name="count")
	private Long count;

	public void setOrderFieldList(List<SOrderField> obj){
		this.orderFieldList = obj;
	}

	public List<SOrderField> getOrderFieldList(){
		return orderFieldList;
	}

	public void setNum(Integer obj){
		this.num = obj;
	}

	public Integer getNum(){
		return num;
	}

	public void setSize(Integer obj){
		this.size = obj;
	}

	public Integer getSize(){
		return size;
	}

	public void setCount(Long obj){
		this.count = obj;
	}

	public Long getCount(){
		return count;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPage rhs=(SPage)rhs0;
		if(!ObjectUtils.equals(orderFieldList, rhs.orderFieldList)) return false;
		if(!ObjectUtils.equals(num, rhs.num)) return false;
		if(!ObjectUtils.equals(size, rhs.size)) return false;
		if(!ObjectUtils.equals(count, rhs.count)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orderFieldList)
		.append(num)
		.append(size)
		.append(count)
		.toHashCode();
	}


}