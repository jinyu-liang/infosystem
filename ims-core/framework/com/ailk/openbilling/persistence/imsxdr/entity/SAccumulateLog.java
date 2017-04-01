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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"itemCode","channelId","value","totalFreeTimes","extraMap"})
@Sdl(module="MXdr")
public class SAccumulateLog extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_CHANNEL_ID = "CHANNEL_ID";
	public final static String COL_VALUE = "VALUE";
	public final static String COL_TOTAL_FREE_TIMES = "TOTAL_FREE_TIMES";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ITEM_CODE = 0;
	public final static int IDX_CHANNEL_ID = 1;
	public final static int IDX_VALUE = 2;
	public final static int IDX_TOTAL_FREE_TIMES = 3;
	public final static int IDX_EXTRA_MAP = 4;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="channelId")
	@Sdl
	private short channelId;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private int value;

	/**
	 * 
	 */
	@XmlElement(name="totalFreeTimes")
	@Sdl
	private int totalFreeTimes;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(4, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(0, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setChannelId(short obj){
		this.channelId = obj;
		onFieldSet(1, obj);
	}

	public short getChannelId(){
		return channelId;
	}

	public void setValue(int obj){
		this.value = obj;
		onFieldSet(2, obj);
	}

	public int getValue(){
		return value;
	}

	public void setTotalFreeTimes(int obj){
		this.totalFreeTimes = obj;
		onFieldSet(3, obj);
	}

	public int getTotalFreeTimes(){
		return totalFreeTimes;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAccumulateLog(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAccumulateLog(SAccumulateLog arg0){
		copy(arg0);
	}

	public void copy(final SAccumulateLog rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		itemCode = rhs.itemCode;
		channelId = rhs.channelId;
		value = rhs.value;
		totalFreeTimes = rhs.totalFreeTimes;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAccumulateLog rhs=(SAccumulateLog)rhs0;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		if(!ObjectUtils.equals(totalFreeTimes, rhs.totalFreeTimes)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(itemCode)
		.append(channelId)
		.append(value)
		.append(totalFreeTimes)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAccumulateLog";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateLog.class, "ITEM_CODE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateLog.class, "CHANNEL_ID", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateLog.class, "VALUE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateLog.class, "TOTAL_FREE_TIMES", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateLog.class, "EXTRA_MAP", 4, Map.class));
}

}