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
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sUsers"})
public class Do_queryUserListResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sUsers")
	private List<SUser> sUsers;

	public void setSUsers(List<SUser> obj){
		this.sUsers = obj;
	}

	public List<SUser> getSUsers(){
		return sUsers;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUserListResponse rhs=(Do_queryUserListResponse)rhs0;
		if(!ObjectUtils.equals(sUsers, rhs.sUsers)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUsers)
		.toHashCode();
	}


}