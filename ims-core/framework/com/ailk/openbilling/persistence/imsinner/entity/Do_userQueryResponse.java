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
@XmlType(propOrder={"userList"})
public class Do_userQueryResponse extends Do_acctQueryResponse implements IComplexEntity{


	@XmlElement(name="userList")
	private List<UserRet> userList;

	public void setUserList(List<UserRet> obj){
		this.userList = obj;
	}

	public List<UserRet> getUserList(){
		return userList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_userQueryResponse rhs=(Do_userQueryResponse)rhs0;
		if(!ObjectUtils.equals(userList, rhs.userList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userList)
		.toHashCode();
	}


}