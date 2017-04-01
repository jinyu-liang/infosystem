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
@XmlType(propOrder={"aoc","rateInfo","bookInfo"})
@Sdl(module="MXdr")
public class SBeforeMakeCallAoc extends CsdlStructObject implements IComplexEntity{

	public final static String COL_AOC = "AOC";
	public final static String COL_RATE_INFO = "RATE_INFO";
	public final static String COL_BOOK_INFO = "BOOK_INFO";
	public final static int IDX_AOC = 0;
	public final static int IDX_RATE_INFO = 1;
	public final static int IDX_BOOK_INFO = 2;

	/**
	 * 
	 */
	@XmlElement(name="aoc")
	@Sdl
	private SAocCode aoc;

	/**
	 * 
	 */
	@XmlElement(name="rateInfo")
	@Sdl
	private List<SRateValue> rateInfo;

	/**
	 * 
	 */
	@XmlElement(name="bookInfo")
	@Sdl
	private List<SBookInfo> bookInfo;

	public void setAoc(SAocCode obj){
		this.aoc = obj;
		onFieldSet(0, obj);
	}

	public SAocCode getAoc(){
		return aoc;
	}

	public void setRateInfo(List<SRateValue> obj){
		this.rateInfo = obj;
		onFieldSet(1, obj);
	}

	public List<SRateValue> getRateInfo(){
		return rateInfo;
	}

	public void setBookInfo(List<SBookInfo> obj){
		this.bookInfo = obj;
		onFieldSet(2, obj);
	}

	public List<SBookInfo> getBookInfo(){
		return bookInfo;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBeforeMakeCallAoc(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBeforeMakeCallAoc(SBeforeMakeCallAoc arg0){
		copy(arg0);
	}

	public void copy(final SBeforeMakeCallAoc rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		aoc = rhs.aoc;
		rateInfo = rhs.rateInfo;
		bookInfo = rhs.bookInfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBeforeMakeCallAoc rhs=(SBeforeMakeCallAoc)rhs0;
		if(!ObjectUtils.equals(aoc, rhs.aoc)) return false;
		if(!ObjectUtils.equals(rateInfo, rhs.rateInfo)) return false;
		if(!ObjectUtils.equals(bookInfo, rhs.bookInfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(aoc)
		.append(rateInfo)
		.append(bookInfo)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBeforeMakeCallAoc";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBeforeMakeCallAoc.class, "AOC", 0, SAocCode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBeforeMakeCallAoc.class, "RATE_INFO", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBeforeMakeCallAoc.class, "BOOK_INFO", 2, List.class));
}

}