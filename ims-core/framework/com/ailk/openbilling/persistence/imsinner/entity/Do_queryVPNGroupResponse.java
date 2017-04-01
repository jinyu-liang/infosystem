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
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"groups"})
public class Do_queryVPNGroupResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="groups")
	private SGroupList groups;

	public void setGroups(SGroupList obj){
		this.groups = obj;
	}

	public SGroupList getGroups(){
		return groups;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryVPNGroupResponse rhs=(Do_queryVPNGroupResponse)rhs0;
		if(!ObjectUtils.equals(groups, rhs.groups)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groups)
		.toHashCode();
	}


}