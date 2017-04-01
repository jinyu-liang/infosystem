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
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"user_list"})
public class Do_queryUsersByConditionsResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="user_list")
	private List<SQueryUsersReturn> user_list;

	public void setUser_list(List<SQueryUsersReturn> obj){
		this.user_list = obj;
	}

	public List<SQueryUsersReturn> getUser_list(){
		return user_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUsersByConditionsResponse rhs=(Do_queryUsersByConditionsResponse)rhs0;
		if(!ObjectUtils.equals(user_list, rhs.user_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_list)
		.toHashCode();
	}


}