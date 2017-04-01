package com.ailk.openbilling.persistence.innerqry.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"idMap","sAcct"})
public class Do_queryAcctInRouteResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="idMap")
	private IdMap idMap;

	@XmlElement(name="sAcct")
	private SAccount sAcct;

	public void setIdMap(IdMap obj){
		this.idMap = obj;
	}

	public IdMap getIdMap(){
		return idMap;
	}

	public void setSAcct(SAccount obj){
		this.sAcct = obj;
	}

	public SAccount getSAcct(){
		return sAcct;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctInRouteResponse rhs=(Do_queryAcctInRouteResponse)rhs0;
		if(!ObjectUtils.equals(idMap, rhs.idMap)) return false;
		if(!ObjectUtils.equals(sAcct, rhs.sAcct)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(idMap)
		.append(sAcct)
		.toHashCode();
	}


}