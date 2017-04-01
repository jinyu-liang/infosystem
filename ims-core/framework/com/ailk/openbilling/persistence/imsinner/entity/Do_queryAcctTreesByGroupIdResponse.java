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
@XmlType(propOrder={"acct_tree_list"})
public class Do_queryAcctTreesByGroupIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acct_tree_list")
	private List<AcctTree> acct_tree_list;

	public void setAcct_tree_list(List<AcctTree> obj){
		this.acct_tree_list = obj;
	}

	public List<AcctTree> getAcct_tree_list(){
		return acct_tree_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctTreesByGroupIdResponse rhs=(Do_queryAcctTreesByGroupIdResponse)rhs0;
		if(!ObjectUtils.equals(acct_tree_list, rhs.acct_tree_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_tree_list)
		.toHashCode();
	}


}