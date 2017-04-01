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
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"prodList","userList"})
public class Do_queryUserByOfferNmaeResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="prodList")
	private List<ProdList> prodList;

	@XmlElement(name="userList")
	private SUserList userList;

	public void setProdList(List<ProdList> obj){
		this.prodList = obj;
	}

	public List<ProdList> getProdList(){
		return prodList;
	}

	public void setUserList(SUserList obj){
		this.userList = obj;
	}

	public SUserList getUserList(){
		return userList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryUserByOfferNmaeResponse rhs=(Do_queryUserByOfferNmaeResponse)rhs0;
		if(!ObjectUtils.equals(prodList, rhs.prodList)) return false;
		if(!ObjectUtils.equals(userList, rhs.userList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodList)
		.append(userList)
		.toHashCode();
	}


}