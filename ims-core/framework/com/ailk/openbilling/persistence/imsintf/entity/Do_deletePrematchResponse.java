package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(propOrder={"delete_phone_ids"})
public class Do_deletePrematchResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="delete_phone_ids")
	private List<String> delete_phone_ids;

	public void setDelete_phone_ids(List<String> obj){
		this.delete_phone_ids = obj;
	}

	public List<String> getDelete_phone_ids(){
		return delete_phone_ids;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_deletePrematchResponse rhs=(Do_deletePrematchResponse)rhs0;
		if(!ObjectUtils.equals(delete_phone_ids, rhs.delete_phone_ids)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(delete_phone_ids)
		.toHashCode();
	}


}