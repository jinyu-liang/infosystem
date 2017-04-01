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
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"prod_list"})
public class Do_queryProductsByUserIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prod_list")
	private List<CoProd> prod_list;

	public void setProd_list(List<CoProd> obj){
		this.prod_list = obj;
	}

	public List<CoProd> getProd_list(){
		return prod_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryProductsByUserIdResponse rhs=(Do_queryProductsByUserIdResponse)rhs0;
		if(!ObjectUtils.equals(prod_list, rhs.prod_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_list)
		.toHashCode();
	}


}