package com.ailk.openbilling.persistence.imssdl.entity;

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
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"action","userId","phoneId","busiServiceId","reasonCode","acctId"})
@Sdl(module="MImsSyncDef")
public class SBarService extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACTION = "ACTION";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_BUSI_SERVICE_ID = "BUSI_SERVICE_ID";
	public final static String COL_REASON_CODE = "REASON_CODE";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static int IDX_ACTION = 0;
	public final static int IDX_USER_ID = 1;
	public final static int IDX_PHONE_ID = 2;
	public final static int IDX_BUSI_SERVICE_ID = 3;
	public final static int IDX_REASON_CODE = 4;
	public final static int IDX_ACCT_ID = 5;

	@XmlElement(name="action")
	@Sdl
	private short action;

	@XmlElement(name="userId")
	@Sdl
	private long userId;

	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	@XmlElement(name="busiServiceId")
	@Sdl
	private int busiServiceId;

	@XmlElement(name="reasonCode")
	@Sdl
	private short reasonCode;

	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	public void setAction(short obj){
		this.action = obj;
		onFieldSet(0, obj);
	}

	public short getAction(){
		return action;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(1, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(2, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setBusiServiceId(int obj){
		this.busiServiceId = obj;
		onFieldSet(3, obj);
	}

	public int getBusiServiceId(){
		return busiServiceId;
	}

	public void setReasonCode(short obj){
		this.reasonCode = obj;
		onFieldSet(4, obj);
	}

	public short getReasonCode(){
		return reasonCode;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(5, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBarService(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBarService(SBarService arg0){
		copy(arg0);
	}

	public void copy(final SBarService rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		action = rhs.action;
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		busiServiceId = rhs.busiServiceId;
		reasonCode = rhs.reasonCode;
		acctId = rhs.acctId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBarService rhs=(SBarService)rhs0;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(busiServiceId, rhs.busiServiceId)) return false;
		if(!ObjectUtils.equals(reasonCode, rhs.reasonCode)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(action)
		.append(userId)
		.append(phoneId)
		.append(busiServiceId)
		.append(reasonCode)
		.append(acctId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SBarService";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "ACTION", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "USER_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "PHONE_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "BUSI_SERVICE_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "REASON_CODE", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarService.class, "ACCT_ID", 5, long.class));
}

}