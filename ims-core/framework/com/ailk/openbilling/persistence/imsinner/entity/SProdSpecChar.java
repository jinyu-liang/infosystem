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
@XmlType(propOrder={"spec_char_id","spec_char_name","is_default","value_type","char_value","char_value_list"})
public class SProdSpecChar implements IComplexEntity{


	@XmlElement(name="char_value_list")
	private List<String> char_value_list;

	@XmlElement(name="spec_char_id")
	private Integer spec_char_id;

	@XmlElement(name="spec_char_name")
	private String spec_char_name;

	@XmlElement(name="is_default")
	private Integer is_default;

	@XmlElement(name="value_type")
	private Integer value_type;

	@XmlElement(name="char_value")
	private String char_value;

	public void setChar_value_list(List<String> obj){
		this.char_value_list = obj;
	}

	public List<String> getChar_value_list(){
		return char_value_list;
	}

	public void setSpec_char_id(Integer obj){
		this.spec_char_id = obj;
	}

	public Integer getSpec_char_id(){
		return spec_char_id;
	}

	public void setSpec_char_name(String obj){
		this.spec_char_name = obj;
	}

	public String getSpec_char_name(){
		return spec_char_name;
	}

	public void setIs_default(Integer obj){
		this.is_default = obj;
	}

	public Integer getIs_default(){
		return is_default;
	}

	public void setValue_type(Integer obj){
		this.value_type = obj;
	}

	public Integer getValue_type(){
		return value_type;
	}

	public void setChar_value(String obj){
		this.char_value = obj;
	}

	public String getChar_value(){
		return char_value;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdSpecChar rhs=(SProdSpecChar)rhs0;
		if(!ObjectUtils.equals(char_value_list, rhs.char_value_list)) return false;
		if(!ObjectUtils.equals(spec_char_id, rhs.spec_char_id)) return false;
		if(!ObjectUtils.equals(spec_char_name, rhs.spec_char_name)) return false;
		if(!ObjectUtils.equals(is_default, rhs.is_default)) return false;
		if(!ObjectUtils.equals(value_type, rhs.value_type)) return false;
		if(!ObjectUtils.equals(char_value, rhs.char_value)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(char_value_list)
		.append(spec_char_id)
		.append(spec_char_name)
		.append(is_default)
		.append(value_type)
		.append(char_value)
		.toHashCode();
	}


}