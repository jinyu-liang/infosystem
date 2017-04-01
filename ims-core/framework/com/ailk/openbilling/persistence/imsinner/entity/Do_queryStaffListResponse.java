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
@XmlType(propOrder={"staffInfoList"})
public class Do_queryStaffListResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="staffInfoList")
	private List<StaffInfo> staffInfoList;

	public void setStaffInfoList(List<StaffInfo> obj){
		this.staffInfoList = obj;
	}

	public List<StaffInfo> getStaffInfoList(){
		return staffInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryStaffListResponse rhs=(Do_queryStaffListResponse)rhs0;
		if(!ObjectUtils.equals(staffInfoList, rhs.staffInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(staffInfoList)
		.toHashCode();
	}


}