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
@XmlType(propOrder={"itemId","cycleUnit","resMeasureId","stdBeginRes","stdRes","stdUnit","standardProductId"})
@Sdl(module="MXdr")
public class SStdRatingUnit extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ITEM_ID = "ITEM_ID";
	public final static String COL_CYCLE_UNIT = "CYCLE_UNIT";
	public final static String COL_RES_MEASURE_ID = "RES_MEASURE_ID";
	public final static String COL_STD_BEGIN_RES = "STD_BEGIN_RES";
	public final static String COL_STD_RES = "STD_RES";
	public final static String COL_STD_UNIT = "STD_UNIT";
	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static int IDX_ITEM_ID = 0;
	public final static int IDX_CYCLE_UNIT = 1;
	public final static int IDX_RES_MEASURE_ID = 2;
	public final static int IDX_STD_BEGIN_RES = 3;
	public final static int IDX_STD_RES = 4;
	public final static int IDX_STD_UNIT = 5;
	public final static int IDX_STANDARD_PRODUCT_ID = 6;

	/**
	 * 
	 */
	@XmlElement(name="itemId")
	@Sdl
	private int itemId;

	/**
	 * 
	 */
	@XmlElement(name="cycleUnit")
	@Sdl
	private int cycleUnit;

	/**
	 * 
	 */
	@XmlElement(name="resMeasureId")
	@Sdl
	private int resMeasureId;

	/**
	 * 
	 */
	@XmlElement(name="stdBeginRes")
	@Sdl
	private long stdBeginRes;

	/**
	 * 
	 */
	@XmlElement(name="stdRes")
	@Sdl
	private long stdRes;

	/**
	 * 
	 */
	@XmlElement(name="stdUnit")
	@Sdl
	private long stdUnit;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	public void setItemId(int obj){
		this.itemId = obj;
		onFieldSet(0, obj);
	}

	public int getItemId(){
		return itemId;
	}

	public void setCycleUnit(int obj){
		this.cycleUnit = obj;
		onFieldSet(1, obj);
	}

	public int getCycleUnit(){
		return cycleUnit;
	}

	public void setResMeasureId(int obj){
		this.resMeasureId = obj;
		onFieldSet(2, obj);
	}

	public int getResMeasureId(){
		return resMeasureId;
	}

	public void setStdBeginRes(long obj){
		this.stdBeginRes = obj;
		onFieldSet(3, obj);
	}

	public long getStdBeginRes(){
		return stdBeginRes;
	}

	public void setStdRes(long obj){
		this.stdRes = obj;
		onFieldSet(4, obj);
	}

	public long getStdRes(){
		return stdRes;
	}

	public void setStdUnit(long obj){
		this.stdUnit = obj;
		onFieldSet(5, obj);
	}

	public long getStdUnit(){
		return stdUnit;
	}

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(6, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SStdRatingUnit(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SStdRatingUnit(SStdRatingUnit arg0){
		copy(arg0);
	}

	public void copy(final SStdRatingUnit rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		itemId = rhs.itemId;
		cycleUnit = rhs.cycleUnit;
		resMeasureId = rhs.resMeasureId;
		stdBeginRes = rhs.stdBeginRes;
		stdRes = rhs.stdRes;
		stdUnit = rhs.stdUnit;
		standardProductId = rhs.standardProductId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SStdRatingUnit rhs=(SStdRatingUnit)rhs0;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(resMeasureId, rhs.resMeasureId)) return false;
		if(!ObjectUtils.equals(stdBeginRes, rhs.stdBeginRes)) return false;
		if(!ObjectUtils.equals(stdRes, rhs.stdRes)) return false;
		if(!ObjectUtils.equals(stdUnit, rhs.stdUnit)) return false;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(itemId)
		.append(cycleUnit)
		.append(resMeasureId)
		.append(stdBeginRes)
		.append(stdRes)
		.append(stdUnit)
		.append(standardProductId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SStdRatingUnit";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "CYCLE_UNIT", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "RES_MEASURE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "STD_BEGIN_RES", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "STD_RES", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "STD_UNIT", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SStdRatingUnit.class, "STANDARD_PRODUCT_ID", 6, long.class));
}

}