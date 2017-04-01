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
@XmlType(propOrder={"totalCount","prodOfferInfoList"})
public class Do_queryProdOfferingInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prodOfferInfoList")
	private List<ProdOfferInfo> prodOfferInfoList;

	@XmlElement(name="totalCount")
	private Integer totalCount;

	public void setProdOfferInfoList(List<ProdOfferInfo> obj){
		this.prodOfferInfoList = obj;
	}

	public List<ProdOfferInfo> getProdOfferInfoList(){
		return prodOfferInfoList;
	}

	public void setTotalCount(Integer obj){
		this.totalCount = obj;
	}

	public Integer getTotalCount(){
		return totalCount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryProdOfferingInfoResponse rhs=(Do_queryProdOfferingInfoResponse)rhs0;
		if(!ObjectUtils.equals(prodOfferInfoList, rhs.prodOfferInfoList)) return false;
		if(!ObjectUtils.equals(totalCount, rhs.totalCount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodOfferInfoList)
		.append(totalCount)
		.toHashCode();
	}


}