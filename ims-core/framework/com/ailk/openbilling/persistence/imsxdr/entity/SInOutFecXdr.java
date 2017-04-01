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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"freeresMiddleInfo","freeresLimitMiddleInfo","creditMiddleInfo","bookMiddleInfo","capMaxMiddleInfo","budgetMiddleInfo","paymentMiddleInfo","abmSessionId","slaAlert"})
@Sdl(module="MXdr")
public class SInOutFecXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_FREERES_MIDDLE_INFO = "FREERES_MIDDLE_INFO";
	public final static String COL_FREERES_LIMIT_MIDDLE_INFO = "FREERES_LIMIT_MIDDLE_INFO";
	public final static String COL_CREDIT_MIDDLE_INFO = "CREDIT_MIDDLE_INFO";
	public final static String COL_BOOK_MIDDLE_INFO = "BOOK_MIDDLE_INFO";
	public final static String COL_CAP_MAX_MIDDLE_INFO = "CAP_MAX_MIDDLE_INFO";
	public final static String COL_BUDGET_MIDDLE_INFO = "BUDGET_MIDDLE_INFO";
	public final static String COL_PAYMENT_MIDDLE_INFO = "PAYMENT_MIDDLE_INFO";
	public final static String COL_ABM_SESSION_ID = "ABM_SESSION_ID";
	public final static String COL_SLA_ALERT = "SLA_ALERT";
	public final static int IDX_FREERES_MIDDLE_INFO = 0;
	public final static int IDX_FREERES_LIMIT_MIDDLE_INFO = 1;
	public final static int IDX_CREDIT_MIDDLE_INFO = 2;
	public final static int IDX_BOOK_MIDDLE_INFO = 3;
	public final static int IDX_CAP_MAX_MIDDLE_INFO = 4;
	public final static int IDX_BUDGET_MIDDLE_INFO = 5;
	public final static int IDX_PAYMENT_MIDDLE_INFO = 6;
	public final static int IDX_ABM_SESSION_ID = 7;
	public final static int IDX_SLA_ALERT = 8;

	/**
	 * 
	 */
	@XmlElement(name="freeresMiddleInfo")
	@Sdl
	private List<SFreeresMiddleInfo> freeresMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="freeresLimitMiddleInfo")
	@Sdl
	private List<SFreeresLimitMiddleInfo> freeresLimitMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="creditMiddleInfo")
	@Sdl
	private Map<Long,SCreditMiddleInfo> creditMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="bookMiddleInfo")
	@Sdl
	private Map<Long,SCreditMiddleInfo> bookMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="capMaxMiddleInfo")
	@Sdl
	private List<SBudgetMiddleInfo> capMaxMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="budgetMiddleInfo")
	@Sdl
	private List<SBudgetMiddleInfo> budgetMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="paymentMiddleInfo")
	@Sdl
	private List<SPaymentMiddleInfo> paymentMiddleInfo;

	/**
	 * 
	 */
	@XmlElement(name="abmSessionId")
	@Sdl
	private long abmSessionId;

	/**
	 * 
	 */
	@XmlElement(name="slaAlert")
	@Sdl
	private int slaAlert;

	public void setFreeresMiddleInfo(List<SFreeresMiddleInfo> obj){
		this.freeresMiddleInfo = obj;
		onFieldSet(0, obj);
	}

	public List<SFreeresMiddleInfo> getFreeresMiddleInfo(){
		return freeresMiddleInfo;
	}

	public void setFreeresLimitMiddleInfo(List<SFreeresLimitMiddleInfo> obj){
		this.freeresLimitMiddleInfo = obj;
		onFieldSet(1, obj);
	}

	public List<SFreeresLimitMiddleInfo> getFreeresLimitMiddleInfo(){
		return freeresLimitMiddleInfo;
	}

	public void setCreditMiddleInfo(Map<Long,SCreditMiddleInfo> obj){
		this.creditMiddleInfo = obj;
		onFieldSet(2, obj);
	}

	public Map<Long,SCreditMiddleInfo> getCreditMiddleInfo(){
		return creditMiddleInfo;
	}

	public void setBookMiddleInfo(Map<Long,SCreditMiddleInfo> obj){
		this.bookMiddleInfo = obj;
		onFieldSet(3, obj);
	}

	public Map<Long,SCreditMiddleInfo> getBookMiddleInfo(){
		return bookMiddleInfo;
	}

	public void setCapMaxMiddleInfo(List<SBudgetMiddleInfo> obj){
		this.capMaxMiddleInfo = obj;
		onFieldSet(4, obj);
	}

	public List<SBudgetMiddleInfo> getCapMaxMiddleInfo(){
		return capMaxMiddleInfo;
	}

	public void setBudgetMiddleInfo(List<SBudgetMiddleInfo> obj){
		this.budgetMiddleInfo = obj;
		onFieldSet(5, obj);
	}

	public List<SBudgetMiddleInfo> getBudgetMiddleInfo(){
		return budgetMiddleInfo;
	}

	public void setPaymentMiddleInfo(List<SPaymentMiddleInfo> obj){
		this.paymentMiddleInfo = obj;
		onFieldSet(6, obj);
	}

	public List<SPaymentMiddleInfo> getPaymentMiddleInfo(){
		return paymentMiddleInfo;
	}

	public void setAbmSessionId(long obj){
		this.abmSessionId = obj;
		onFieldSet(7, obj);
	}

	public long getAbmSessionId(){
		return abmSessionId;
	}

	public void setSlaAlert(int obj){
		this.slaAlert = obj;
		onFieldSet(8, obj);
	}

	public int getSlaAlert(){
		return slaAlert;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SInOutFecXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SInOutFecXdr(SInOutFecXdr arg0){
		copy(arg0);
	}

	public void copy(final SInOutFecXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		freeresMiddleInfo = rhs.freeresMiddleInfo;
		freeresLimitMiddleInfo = rhs.freeresLimitMiddleInfo;
		creditMiddleInfo = rhs.creditMiddleInfo;
		bookMiddleInfo = rhs.bookMiddleInfo;
		capMaxMiddleInfo = rhs.capMaxMiddleInfo;
		budgetMiddleInfo = rhs.budgetMiddleInfo;
		paymentMiddleInfo = rhs.paymentMiddleInfo;
		abmSessionId = rhs.abmSessionId;
		slaAlert = rhs.slaAlert;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SInOutFecXdr rhs=(SInOutFecXdr)rhs0;
		if(!ObjectUtils.equals(freeresMiddleInfo, rhs.freeresMiddleInfo)) return false;
		if(!ObjectUtils.equals(freeresLimitMiddleInfo, rhs.freeresLimitMiddleInfo)) return false;
		if(!ObjectUtils.equals(creditMiddleInfo, rhs.creditMiddleInfo)) return false;
		if(!ObjectUtils.equals(bookMiddleInfo, rhs.bookMiddleInfo)) return false;
		if(!ObjectUtils.equals(capMaxMiddleInfo, rhs.capMaxMiddleInfo)) return false;
		if(!ObjectUtils.equals(budgetMiddleInfo, rhs.budgetMiddleInfo)) return false;
		if(!ObjectUtils.equals(paymentMiddleInfo, rhs.paymentMiddleInfo)) return false;
		if(!ObjectUtils.equals(abmSessionId, rhs.abmSessionId)) return false;
		if(!ObjectUtils.equals(slaAlert, rhs.slaAlert)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeresMiddleInfo)
		.append(freeresLimitMiddleInfo)
		.append(creditMiddleInfo)
		.append(bookMiddleInfo)
		.append(capMaxMiddleInfo)
		.append(budgetMiddleInfo)
		.append(paymentMiddleInfo)
		.append(abmSessionId)
		.append(slaAlert)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SInOutFecXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "FREERES_MIDDLE_INFO", 0, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "FREERES_LIMIT_MIDDLE_INFO", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "CREDIT_MIDDLE_INFO", 2, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "BOOK_MIDDLE_INFO", 3, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "CAP_MAX_MIDDLE_INFO", 4, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "BUDGET_MIDDLE_INFO", 5, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "PAYMENT_MIDDLE_INFO", 6, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "ABM_SESSION_ID", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SInOutFecXdr.class, "SLA_ALERT", 8, int.class));
}

}