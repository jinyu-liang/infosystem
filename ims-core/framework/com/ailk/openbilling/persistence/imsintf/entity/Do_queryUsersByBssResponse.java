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
@XmlType(propOrder={"userList"})
public class Do_queryUsersByBssResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="userList")
	private UserList userList;

	public void setUserList(UserList obj){
		this.userList = obj;
	}

	public UserList getUserList(){
		return userList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUsersByBssResponse rhs=(Do_queryUsersByBssResponse)rhs0;
		if(!ObjectUtils.equals(userList, rhs.userList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userList)
		.toHashCode();
	}


}