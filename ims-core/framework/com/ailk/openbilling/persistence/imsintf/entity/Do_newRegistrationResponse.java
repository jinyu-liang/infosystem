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
@XmlType(propOrder={"outer_cust_id","cust_id","acct_ids","user_result_list","prod_result_list"})
public class Do_newRegistrationResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acct_ids")
	private LongList acct_ids;

	@XmlElement(name="user_result_list")
	private SUserResultList user_result_list;

	@XmlElement(name="prod_result_list")
	private SProductOrderResultList prod_result_list;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private long cust_id;

	public void setAcct_ids(LongList obj){
		this.acct_ids = obj;
	}

	public LongList getAcct_ids(){
		return acct_ids;
	}

	public void setUser_result_list(SUserResultList obj){
		this.user_result_list = obj;
	}

	public SUserResultList getUser_result_list(){
		return user_result_list;
	}

	public void setProd_result_list(SProductOrderResultList obj){
		this.prod_result_list = obj;
	}

	public SProductOrderResultList getProd_result_list(){
		return prod_result_list;
	}

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(long obj){
		this.cust_id = obj;
	}

	public long getCust_id(){
		return cust_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_newRegistrationResponse rhs=(Do_newRegistrationResponse)rhs0;
		if(!ObjectUtils.equals(acct_ids, rhs.acct_ids)) return false;
		if(!ObjectUtils.equals(user_result_list, rhs.user_result_list)) return false;
		if(!ObjectUtils.equals(prod_result_list, rhs.prod_result_list)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_ids)
		.append(user_result_list)
		.append(prod_result_list)
		.append(outer_cust_id)
		.append(cust_id)
		.toHashCode();
	}


}