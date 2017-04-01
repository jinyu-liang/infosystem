package com.ailk.openbilling.persistence.imsts.entity;

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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","prodId","offerId","receivePhone","validDate","expireDate","notificationId","freeResFlag","createDate","sts","custId","acctId","userId"})
@Sdl(module="MImsTsDef")
public class SExpireProdNotify extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_RECEIVE_PHONE = "RECEIVE_PHONE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_NOTIFICATION_ID = "NOTIFICATION_ID";
	public final static String COL_FREE_RES_FLAG = "FREE_RES_FLAG";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_STS = "STS";
	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static int IDX_ID = 0;
	public final static int IDX_PROD_ID = 1;
	public final static int IDX_OFFER_ID = 2;
	public final static int IDX_RECEIVE_PHONE = 3;
	public final static int IDX_VALID_DATE = 4;
	public final static int IDX_EXPIRE_DATE = 5;
	public final static int IDX_NOTIFICATION_ID = 6;
	public final static int IDX_FREE_RES_FLAG = 7;
	public final static int IDX_CREATE_DATE = 8;
	public final static int IDX_STS = 9;
	public final static int IDX_CUST_ID = 10;
	public final static int IDX_ACCT_ID = 11;
	public final static int IDX_USER_ID = 12;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="offerId")
	@Sdl
	private long offerId;

	/**
	 * 
	 */
	@XmlElement(name="receivePhone")
	@Sdl
	private String receivePhone;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private Date validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Date expireDate;

	/**
	 * 
	 */
	@XmlElement(name="notificationId")
	@Sdl
	private int notificationId;

	/**
	 * 
	 */
	@XmlElement(name="freeResFlag")
	@Sdl
	private int freeResFlag;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="custId")
	@Sdl
	private long custId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="userId")
	@Sdl
	private long userId;

	public void setId(long obj){
		this.id = obj;
		onFieldSet(0, obj);
	}

	public long getId(){
		return id;
	}

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(1, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setOfferId(long obj){
		this.offerId = obj;
		onFieldSet(2, obj);
	}

	public long getOfferId(){
		return offerId;
	}

	public void setReceivePhone(String obj){
		this.receivePhone = obj;
		onFieldSet(3, obj);
	}

	public String getReceivePhone(){
		return receivePhone;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(4, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(5, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setNotificationId(int obj){
		this.notificationId = obj;
		onFieldSet(6, obj);
	}

	public int getNotificationId(){
		return notificationId;
	}

	public void setFreeResFlag(int obj){
		this.freeResFlag = obj;
		onFieldSet(7, obj);
	}

	public int getFreeResFlag(){
		return freeResFlag;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(8, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(9, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(10, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(11, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(12, obj);
	}

	public long getUserId(){
		return userId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SExpireProdNotify(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 13; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SExpireProdNotify(SExpireProdNotify arg0){
		copy(arg0);
	}

	public void copy(final SExpireProdNotify rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		id = rhs.id;
		prodId = rhs.prodId;
		offerId = rhs.offerId;
		receivePhone = rhs.receivePhone;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		notificationId = rhs.notificationId;
		freeResFlag = rhs.freeResFlag;
		createDate = rhs.createDate;
		sts = rhs.sts;
		custId = rhs.custId;
		acctId = rhs.acctId;
		userId = rhs.userId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SExpireProdNotify rhs=(SExpireProdNotify)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(receivePhone, rhs.receivePhone)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(notificationId, rhs.notificationId)) return false;
		if(!ObjectUtils.equals(freeResFlag, rhs.freeResFlag)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(prodId)
		.append(offerId)
		.append(receivePhone)
		.append(validDate)
		.append(expireDate)
		.append(notificationId)
		.append(freeResFlag)
		.append(createDate)
		.append(sts)
		.append(custId)
		.append(acctId)
		.append(userId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(13);
public static final long BITS_ALL_MARKER = 0x1000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SExpireProdNotify";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "PROD_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "OFFER_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "RECEIVE_PHONE", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "VALID_DATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "EXPIRE_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "NOTIFICATION_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "FREE_RES_FLAG", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "CREATE_DATE", 8, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "STS", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "CUST_ID", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "ACCT_ID", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SExpireProdNotify.class, "USER_ID", 12, long.class));
}

}