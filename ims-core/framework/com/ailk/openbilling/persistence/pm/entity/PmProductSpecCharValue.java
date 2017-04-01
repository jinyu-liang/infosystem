package com.ailk.openbilling.persistence.pm.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="pd",name="PM_PRODUCT_SPEC_CHAR_VALUE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"charValueId","specCharId","valueTypeId","isDefault","value","valueFrom","valueTo","rangeInterval","measureId","validDate","expireDate"})
public class PmProductSpecCharValue extends DataObject{


	/**
	 * Characteristic ID
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="CHAR_VALUE_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="charValueId")
	private Integer charValueId;

	/**
	 * A unique identifier for the ProductSpecCharacteristic.
	 */
	@Column(name="SPEC_CHAR_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="specCharId")
	private Integer specCharId;

	/**
	 * A kind of value that the characteristic can take on, such as numeric, text, and so forth, from PM_ENUM_VALUE_TYPE_DEF
	 */
	@Column(name="VALUE_TYPE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="valueTypeId")
	private Integer valueTypeId;

	/**
	 * Whether or not it is default characteristic value: 0- N    1-Y
	 */
	@Column(name="IS_DEFAULT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="isDefault")
	private Integer isDefault;

	/**
	 * A discrete value that the characteristic can take on.
	 */
	@Column(name="VALUE",columnDefinition="Varchar",length=256)
	@XmlElement(name="value")
	private String value;

	/**
	 * The low range value that a characteristic can take on.
	 */
	@Column(name="VALUE_FROM",columnDefinition="Varchar",length=256)
	@XmlElement(name="valueFrom")
	private String valueFrom;

	/**
	 * End value of configuration
	 */
	@Column(name="VALUE_TO",columnDefinition="Varchar",length=256)
	@XmlElement(name="valueTo")
	private String valueTo;

	/**
	 * Step length
	 */
	@Column(name="RANGE_INTERVAL",columnDefinition="Varchar",length=256)
	@XmlElement(name="rangeInterval")
	private String rangeInterval;

	/**
	 * Measurement unit, unified definition of  value from/value_to/range_interval
	 */
	@Column(name="MEASURE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="measureId")
	private Integer measureId;

	/**
	 * Effective Date
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * Expired Date
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setCharValueId(Integer obj){
		this.charValueId = obj;
	}

	public Integer getCharValueId(){
		return charValueId;
	}

	public void setSpecCharId(Integer obj){
		this.specCharId = obj;
	}

	public Integer getSpecCharId(){
		return specCharId;
	}

	public void setValueTypeId(Integer obj){
		this.valueTypeId = obj;
	}

	public Integer getValueTypeId(){
		return valueTypeId;
	}

	public void setIsDefault(Integer obj){
		this.isDefault = obj;
	}

	public Integer getIsDefault(){
		return isDefault;
	}

	public void setValue(String obj){
		this.value = obj;
	}

	public String getValue(){
		return value;
	}

	public void setValueFrom(String obj){
		this.valueFrom = obj;
	}

	public String getValueFrom(){
		return valueFrom;
	}

	public void setValueTo(String obj){
		this.valueTo = obj;
	}

	public String getValueTo(){
		return valueTo;
	}

	public void setRangeInterval(String obj){
		this.rangeInterval = obj;
	}

	public String getRangeInterval(){
		return rangeInterval;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public PmProductSpecCharValue(){
	}

	public PmProductSpecCharValue(Integer charValueId){
		this.charValueId = charValueId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmProductSpecCharValue rhs=(PmProductSpecCharValue)rhs0;
		if(!ObjectUtils.equals(charValueId, rhs.charValueId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(charValueId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{charValueId,specCharId,valueTypeId,isDefault,value,valueFrom,valueTo,rangeInterval,measureId,validDate,expireDate}
}