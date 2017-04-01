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
@XmlType(propOrder={"callType","tollType","tollRate","transferCapability","layerProtocol","callingPartysCategory","ucbFlag"})
@Sdl(module="MXdr")
public class SGsmInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CALL_TYPE = "CALL_TYPE";
	public final static String COL_TOLL_TYPE = "TOLL_TYPE";
	public final static String COL_TOLL_RATE = "TOLL_RATE";
	public final static String COL_TRANSFER_CAPABILITY = "TRANSFER_CAPABILITY";
	public final static String COL_LAYER_PROTOCOL = "LAYER_PROTOCOL";
	public final static String COL_CALLING_PARTYS_CATEGORY = "CALLING_PARTYS_CATEGORY";
	public final static String COL_UCB_FLAG = "UCB_FLAG";
	public final static int IDX_CALL_TYPE = 0;
	public final static int IDX_TOLL_TYPE = 1;
	public final static int IDX_TOLL_RATE = 2;
	public final static int IDX_TRANSFER_CAPABILITY = 3;
	public final static int IDX_LAYER_PROTOCOL = 4;
	public final static int IDX_CALLING_PARTYS_CATEGORY = 5;
	public final static int IDX_UCB_FLAG = 6;

	/**
	 * 
	 */
	@XmlElement(name="callType")
	@Sdl
	private int callType;

	/**
	 * 
	 */
	@XmlElement(name="tollType")
	@Sdl
	private short tollType;

	/**
	 * 
	 */
	@XmlElement(name="tollRate")
	@Sdl
	private short tollRate;

	/**
	 * 
	 */
	@XmlElement(name="transferCapability")
	@Sdl
	private int transferCapability;

	/**
	 * 
	 */
	@XmlElement(name="layerProtocol")
	@Sdl
	private int layerProtocol;

	/**
	 * 
	 */
	@XmlElement(name="callingPartysCategory")
	@Sdl
	private int callingPartysCategory;

	/**
	 * 
	 */
	@XmlElement(name="ucbFlag")
	@Sdl
	private short ucbFlag;

	public void setCallType(int obj){
		this.callType = obj;
		onFieldSet(0, obj);
	}

	public int getCallType(){
		return callType;
	}

	public void setTollType(short obj){
		this.tollType = obj;
		onFieldSet(1, obj);
	}

	public short getTollType(){
		return tollType;
	}

	public void setTollRate(short obj){
		this.tollRate = obj;
		onFieldSet(2, obj);
	}

	public short getTollRate(){
		return tollRate;
	}

	public void setTransferCapability(int obj){
		this.transferCapability = obj;
		onFieldSet(3, obj);
	}

	public int getTransferCapability(){
		return transferCapability;
	}

	public void setLayerProtocol(int obj){
		this.layerProtocol = obj;
		onFieldSet(4, obj);
	}

	public int getLayerProtocol(){
		return layerProtocol;
	}

	public void setCallingPartysCategory(int obj){
		this.callingPartysCategory = obj;
		onFieldSet(5, obj);
	}

	public int getCallingPartysCategory(){
		return callingPartysCategory;
	}

	public void setUcbFlag(short obj){
		this.ucbFlag = obj;
		onFieldSet(6, obj);
	}

	public short getUcbFlag(){
		return ucbFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGsmInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGsmInfo(SGsmInfo arg0){
		copy(arg0);
	}

	public void copy(final SGsmInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		callType = rhs.callType;
		tollType = rhs.tollType;
		tollRate = rhs.tollRate;
		transferCapability = rhs.transferCapability;
		layerProtocol = rhs.layerProtocol;
		callingPartysCategory = rhs.callingPartysCategory;
		ucbFlag = rhs.ucbFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGsmInfo rhs=(SGsmInfo)rhs0;
		if(!ObjectUtils.equals(callType, rhs.callType)) return false;
		if(!ObjectUtils.equals(tollType, rhs.tollType)) return false;
		if(!ObjectUtils.equals(tollRate, rhs.tollRate)) return false;
		if(!ObjectUtils.equals(transferCapability, rhs.transferCapability)) return false;
		if(!ObjectUtils.equals(layerProtocol, rhs.layerProtocol)) return false;
		if(!ObjectUtils.equals(callingPartysCategory, rhs.callingPartysCategory)) return false;
		if(!ObjectUtils.equals(ucbFlag, rhs.ucbFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(callType)
		.append(tollType)
		.append(tollRate)
		.append(transferCapability)
		.append(layerProtocol)
		.append(callingPartysCategory)
		.append(ucbFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGsmInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "CALL_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "TOLL_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "TOLL_RATE", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "TRANSFER_CAPABILITY", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "LAYER_PROTOCOL", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "CALLING_PARTYS_CATEGORY", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGsmInfo.class, "UCB_FLAG", 6, short.class));
}

}