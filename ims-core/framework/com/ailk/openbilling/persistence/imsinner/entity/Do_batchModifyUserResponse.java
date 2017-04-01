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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"thread_id"})
public class Do_batchModifyUserResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="thread_id")
	private Long thread_id;

	public void setThread_id(Long obj){
		this.thread_id = obj;
	}

	public Long getThread_id(){
		return thread_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_batchModifyUserResponse rhs=(Do_batchModifyUserResponse)rhs0;
		if(!ObjectUtils.equals(thread_id, rhs.thread_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(thread_id)
		.toHashCode();
	}


}