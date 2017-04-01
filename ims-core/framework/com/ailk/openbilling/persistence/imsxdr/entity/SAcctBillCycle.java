package com.ailk.openbilling.persistence.imsxdr.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.ailk.easyframe.web.common.annotation.Sdl;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.easyframe.sdl.sdlbuffer.MemberTypeInfo;
import javax.xml.bind.annotation.XmlTransient;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"cycleUnit","cycleType","firstBillDate","billValidDate","billExpireDate","cycleCount"})
@Sdl(module="MXdr")
public class SAcctBillCycle extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CYCLE_UNIT = "CYCLE_UNIT";
	public final static String COL_CYCLE_TYPE = "CYCLE_TYPE";
	public final static String COL_FIRST_BILL_DATE = "FIRST_BILL_DATE";
	public final static String COL_BILL_VALID_DATE = "BILL_VALID_DATE";
	public final static String COL_BILL_EXPIRE_DATE = "BILL_EXPIRE_DATE";
	public final static String COL_CYCLE_COUNT = "CYCLE_COUNT";
	public final static int IDX_CYCLE_UNIT = 0;
	public final static int IDX_CYCLE_TYPE = 1;
	public final static int IDX_FIRST_BILL_DATE = 2;
	public final static int IDX_BILL_VALID_DATE = 3;
	public final static int IDX_BILL_EXPIRE_DATE = 4;
	public final static int IDX_CYCLE_COUNT = 5;

	/**
	 * 
	 */
	@XmlElement(name="cycleUnit")
	@Sdl
	private short cycleUnit;

	/**
	 * 
	 */
	@XmlElement(name="cycleType")
	@Sdl
	private short cycleType;

	/**
	 * 
	 */
	@XmlElement(name="firstBillDate")
	@Sdl
	private long firstBillDate;

	/**
	 * 
	 */
	@XmlElement(name="billValidDate")
	@Sdl
	private long billValidDate;

	/**
	 * 
	 */
	@XmlElement(name="billExpireDate")
	@Sdl
	private long billExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="cycleCount")
	@Sdl
	private int cycleCount;

	public void setCycleUnit(short obj){
		this.cycleUnit = obj;
		onFieldSet(0, obj);
	}

	public short getCycleUnit(){
		return cycleUnit;
	}

	public void setCycleType(short obj){
		this.cycleType = obj;
		onFieldSet(1, obj);
	}

	public short getCycleType(){
		return cycleType;
	}

	public void setFirstBillDate(long obj){
		this.firstBillDate = obj;
		onFieldSet(2, obj);
	}

	public long getFirstBillDate(){
		return firstBillDate;
	}

	public void setBillValidDate(long obj){
		this.billValidDate = obj;
		onFieldSet(3, obj);
	}

	public long getBillValidDate(){
		return billValidDate;
	}

	public void setBillExpireDate(long obj){
		this.billExpireDate = obj;
		onFieldSet(4, obj);
	}

	public long getBillExpireDate(){
		return billExpireDate;
	}

	public void setCycleCount(int obj){
		this.cycleCount = obj;
		onFieldSet(5, obj);
	}

	public int getCycleCount(){
		return cycleCount;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAcctBillCycle(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAcctBillCycle(SAcctBillCycle arg0){
		copy(arg0);
	}

	public void copy(final SAcctBillCycle rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		cycleUnit = rhs.cycleUnit;
		cycleType = rhs.cycleType;
		firstBillDate = rhs.firstBillDate;
		billValidDate = rhs.billValidDate;
		billExpireDate = rhs.billExpireDate;
		cycleCount = rhs.cycleCount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAcctBillCycle rhs=(SAcctBillCycle)rhs0;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(firstBillDate, rhs.firstBillDate)) return false;
		if(!ObjectUtils.equals(billValidDate, rhs.billValidDate)) return false;
		if(!ObjectUtils.equals(billExpireDate, rhs.billExpireDate)) return false;
		if(!ObjectUtils.equals(cycleCount, rhs.cycleCount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cycleUnit)
		.append(cycleType)
		.append(firstBillDate)
		.append(billValidDate)
		.append(billExpireDate)
		.append(cycleCount)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAcctBillCycle";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "CYCLE_UNIT", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "CYCLE_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "FIRST_BILL_DATE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "BILL_VALID_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "BILL_EXPIRE_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAcctBillCycle.class, "CYCLE_COUNT", 5, int.class));
}

}