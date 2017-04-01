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
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"bookId","bookItem","amount","days","acctId","userId","phoneId","validDate","expireDate"})
@Sdl(module="MImsSyncDef")
public class SBalance extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BOOK_ID = "BOOK_ID";
	public final static String COL_BOOK_ITEM = "BOOK_ITEM";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_DAYS = "DAYS";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static int IDX_BOOK_ID = 0;
	public final static int IDX_BOOK_ITEM = 1;
	public final static int IDX_AMOUNT = 2;
	public final static int IDX_DAYS = 3;
	public final static int IDX_ACCT_ID = 4;
	public final static int IDX_USER_ID = 5;
	public final static int IDX_PHONE_ID = 6;
	public final static int IDX_VALID_DATE = 7;
	public final static int IDX_EXPIRE_DATE = 8;

	/**
	 * 
	 */
	@XmlElement(name="bookId")
	@Sdl
	private long bookId;

	/**
	 * 
	 */
	@XmlElement(name="bookItem")
	@Sdl
	private long bookItem;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="days")
	@Sdl
	private int days;

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

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	public void setBookId(long obj){
		this.bookId = obj;
		onFieldSet(0, obj);
	}

	public long getBookId(){
		return bookId;
	}

	public void setBookItem(long obj){
		this.bookItem = obj;
		onFieldSet(1, obj);
	}

	public long getBookItem(){
		return bookItem;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(2, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setDays(int obj){
		this.days = obj;
		onFieldSet(3, obj);
	}

	public int getDays(){
		return days;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(4, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(5, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(6, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(7, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(8, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBalance(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBalance(SBalance arg0){
		copy(arg0);
	}

	public void copy(final SBalance rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		bookId = rhs.bookId;
		bookItem = rhs.bookItem;
		amount = rhs.amount;
		days = rhs.days;
		acctId = rhs.acctId;
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBalance rhs=(SBalance)rhs0;
		if(!ObjectUtils.equals(bookId, rhs.bookId)) return false;
		if(!ObjectUtils.equals(bookItem, rhs.bookItem)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(days, rhs.days)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bookId)
		.append(bookItem)
		.append(amount)
		.append(days)
		.append(acctId)
		.append(userId)
		.append(phoneId)
		.append(validDate)
		.append(expireDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SBalance";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "BOOK_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "BOOK_ITEM", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "AMOUNT", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "DAYS", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "ACCT_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "USER_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "PHONE_ID", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "VALID_DATE", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBalance.class, "EXPIRE_DATE", 8, long.class));
}

}