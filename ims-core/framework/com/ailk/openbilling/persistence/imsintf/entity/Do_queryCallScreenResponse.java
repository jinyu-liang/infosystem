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
@XmlType(propOrder={"cLIR_flag","call_screen_no_type","product_id","routing_method","iCSSendSMSFlag","callscreen_list"})
public class Do_queryCallScreenResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="callscreen_list")
	private SCallScreenNoList callscreen_list;

	@XmlElement(name="cLIR_flag")
	private Short cLIR_flag;

	@XmlElement(name="call_screen_no_type")
	private Short call_screen_no_type;

	/**
	 * call screen product id
	 */
	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="routing_method")
	private Short routing_method;

	@XmlElement(name="iCSSendSMSFlag")
	private String iCSSendSMSFlag;

	public void setCallscreen_list(SCallScreenNoList obj){
		this.callscreen_list = obj;
	}

	public SCallScreenNoList getCallscreen_list(){
		return callscreen_list;
	}

	public void setCLIR_flag(Short obj){
		this.cLIR_flag = obj;
	}

	public Short getCLIR_flag(){
		return cLIR_flag;
	}

	public void setCall_screen_no_type(Short obj){
		this.call_screen_no_type = obj;
	}

	public Short getCall_screen_no_type(){
		return call_screen_no_type;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setRouting_method(Short obj){
		this.routing_method = obj;
	}

	public Short getRouting_method(){
		return routing_method;
	}

	public void setICSSendSMSFlag(String obj){
		this.iCSSendSMSFlag = obj;
	}

	public String getICSSendSMSFlag(){
		return iCSSendSMSFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryCallScreenResponse rhs=(Do_queryCallScreenResponse)rhs0;
		if(!ObjectUtils.equals(callscreen_list, rhs.callscreen_list)) return false;
		if(!ObjectUtils.equals(cLIR_flag, rhs.cLIR_flag)) return false;
		if(!ObjectUtils.equals(call_screen_no_type, rhs.call_screen_no_type)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(routing_method, rhs.routing_method)) return false;
		if(!ObjectUtils.equals(iCSSendSMSFlag, rhs.iCSSendSMSFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(callscreen_list)
		.append(cLIR_flag)
		.append(call_screen_no_type)
		.append(product_id)
		.append(routing_method)
		.append(iCSSendSMSFlag)
		.toHashCode();
	}


}