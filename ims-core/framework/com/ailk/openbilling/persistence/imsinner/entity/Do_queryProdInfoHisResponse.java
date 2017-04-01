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
@XmlType(propOrder={"prodinfo_list"})
public class Do_queryProdInfoHisResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prodinfo_list")
	private List<ProdRespComplex> prodinfo_list;

	public void setProdinfo_list(List<ProdRespComplex> obj){
		this.prodinfo_list = obj;
	}

	public List<ProdRespComplex> getProdinfo_list(){
		return prodinfo_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryProdInfoHisResponse rhs=(Do_queryProdInfoHisResponse)rhs0;
		if(!ObjectUtils.equals(prodinfo_list, rhs.prodinfo_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodinfo_list)
		.toHashCode();
	}


}