package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"user"})
public class UserList implements IComplexEntity{


	@XmlElement(name="user")
	private List<SUser> user;

	public void setUser(List<SUser> obj){
		this.user = obj;
	}

	public List<SUser> getUser(){
		return user;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		UserList rhs=(UserList)rhs0;
		if(!ObjectUtils.equals(user, rhs.user)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user)
		.toHashCode();
	}


}