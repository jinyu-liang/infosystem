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
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"list"})
public class Do_QueryCaAccountResResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="list")
	private List<CaAccountRes> list;

	public void setList(List<CaAccountRes> obj){
		this.list = obj;
	}

	public List<CaAccountRes> getList(){
		return list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryCaAccountResResponse rhs=(Do_QueryCaAccountResResponse)rhs0;
		if(!ObjectUtils.equals(list, rhs.list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(list)
		.toHashCode();
	}


}