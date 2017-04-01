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
@XmlType(propOrder={"doneCode","billFlag","itemId","monopolyValue","amount","validDate","expireDate","groupId","servId"})
@Sdl(module="MXdr")
public class SGroupSpecShare extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_BILL_FLAG = "BILL_FLAG";
	public final static String COL_ITEM_ID = "ITEM_ID";
	public final static String COL_MONOPOLY_VALUE = "MONOPOLY_VALUE";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_GROUP_ID = "GROUP_ID";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_BILL_FLAG = 1;
	public final static int IDX_ITEM_ID = 2;
	public final static int IDX_MONOPOLY_VALUE = 3;
	public final static int IDX_AMOUNT = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_GROUP_ID = 7;
	public final static int IDX_SERV_ID = 8;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="billFlag")
	@Sdl
	private int billFlag;

	/**
	 * 
	 */
	@XmlElement(name="itemId")
	@Sdl
	private int itemId;

	/**
	 * 
	 */
	@XmlElement(name="monopolyValue")
	@Sdl
	private long monopolyValue;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

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

	/**
	 * 
	 */
	@XmlElement(name="groupId")
	@Sdl
	private long groupId;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(0, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setBillFlag(int obj){
		this.billFlag = obj;
		onFieldSet(1, obj);
	}

	public int getBillFlag(){
		return billFlag;
	}

	public void setItemId(int obj){
		this.itemId = obj;
		onFieldSet(2, obj);
	}

	public int getItemId(){
		return itemId;
	}

	public void setMonopolyValue(long obj){
		this.monopolyValue = obj;
		onFieldSet(3, obj);
	}

	public long getMonopolyValue(){
		return monopolyValue;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(4, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setGroupId(long obj){
		this.groupId = obj;
		onFieldSet(7, obj);
	}

	public long getGroupId(){
		return groupId;
	}

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(8, obj);
	}

	public long getServId(){
		return servId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGroupSpecShare(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGroupSpecShare(SGroupSpecShare arg0){
		copy(arg0);
	}

	public void copy(final SGroupSpecShare rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		doneCode = rhs.doneCode;
		billFlag = rhs.billFlag;
		itemId = rhs.itemId;
		monopolyValue = rhs.monopolyValue;
		amount = rhs.amount;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		groupId = rhs.groupId;
		servId = rhs.servId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGroupSpecShare rhs=(SGroupSpecShare)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(billFlag, rhs.billFlag)) return false;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(monopolyValue, rhs.monopolyValue)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(billFlag)
		.append(itemId)
		.append(monopolyValue)
		.append(amount)
		.append(validDate)
		.append(expireDate)
		.append(groupId)
		.append(servId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGroupSpecShare";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "BILL_FLAG", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "ITEM_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "MONOPOLY_VALUE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "AMOUNT", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "VALID_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "EXPIRE_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "GROUP_ID", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGroupSpecShare.class, "SERV_ID", 8, long.class));
}

}