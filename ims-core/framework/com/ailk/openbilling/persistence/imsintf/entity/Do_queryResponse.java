package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
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
@XmlType(propOrder={"user","userAuth","account","customer","balance","budget","credit","freeResourceGroupList","accumulation","billInfo","hybridRule","oneTimeCharge","point","prodOrderList","busiServiceList","realtimeCharge","hybridBusiServiceList","sNegativeBalanceList","capMaxControlList","queryFreeTimes","billUsage","actualUsageList","sContactList"})
public class Do_queryResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="user")
	private SUser user;

	@XmlElement(name="userAuth")
	private SUserAuth userAuth;

	@XmlElement(name="account")
	private SAccount account;

	@XmlElement(name="customer")
	private SCustomer customer;

	@XmlElement(name="balance")
	private SBalanceList balance;

	@XmlElement(name="budget")
	private BudgetList budget;

	@XmlElement(name="credit")
	private CreditLimitList credit;

	@XmlElement(name="freeResourceGroupList")
	private FreeResourceGroupList freeResourceGroupList;

	@XmlElement(name="accumulation")
	private AccumulateList accumulation;

	@XmlElement(name="billInfo")
	private BillinfoList billInfo;

	@XmlElement(name="hybridRule")
	private SBusiServiceList hybridRule;

	@XmlElement(name="oneTimeCharge")
	private OneTimeFeeList oneTimeCharge;

	@XmlElement(name="point")
	private Spoints point;

	@XmlElement(name="prodOrderList")
	private SProductOrderList prodOrderList;

	@XmlElement(name="busiServiceList")
	private SBusiServiceList busiServiceList;

	@XmlElement(name="realtimeCharge")
	private RealTimeCallChargeList realtimeCharge;

	@XmlElement(name="hybridBusiServiceList")
	private HybridBusiServiceList hybridBusiServiceList;

	@XmlElement(name="sNegativeBalanceList")
	private SNegativeBalanceList sNegativeBalanceList;

	@XmlElement(name="capMaxControlList")
	private CapMaxControlList capMaxControlList;

	@XmlElement(name="queryFreeTimes")
	private SQueryFreeTimes queryFreeTimes;

	@XmlElement(name="billUsage")
	private BillUsage billUsage;

	@XmlElement(name="actualUsageList")
	private ActualUsageList actualUsageList;

	@XmlElement(name="sContactList")
	private List<SContact> sContactList;

	public void setUser(SUser obj){
		this.user = obj;
	}

	public SUser getUser(){
		return user;
	}

	public void setUserAuth(SUserAuth obj){
		this.userAuth = obj;
	}

	public SUserAuth getUserAuth(){
		return userAuth;
	}

	public void setAccount(SAccount obj){
		this.account = obj;
	}

	public SAccount getAccount(){
		return account;
	}

	public void setCustomer(SCustomer obj){
		this.customer = obj;
	}

	public SCustomer getCustomer(){
		return customer;
	}

	public void setBalance(SBalanceList obj){
		this.balance = obj;
	}

	public SBalanceList getBalance(){
		return balance;
	}

	public void setBudget(BudgetList obj){
		this.budget = obj;
	}

	public BudgetList getBudget(){
		return budget;
	}

	public void setCredit(CreditLimitList obj){
		this.credit = obj;
	}

	public CreditLimitList getCredit(){
		return credit;
	}

	public void setFreeResourceGroupList(FreeResourceGroupList obj){
		this.freeResourceGroupList = obj;
	}

	public FreeResourceGroupList getFreeResourceGroupList(){
		return freeResourceGroupList;
	}

	public void setAccumulation(AccumulateList obj){
		this.accumulation = obj;
	}

	public AccumulateList getAccumulation(){
		return accumulation;
	}

	public void setBillInfo(BillinfoList obj){
		this.billInfo = obj;
	}

	public BillinfoList getBillInfo(){
		return billInfo;
	}

	public void setHybridRule(SBusiServiceList obj){
		this.hybridRule = obj;
	}

	public SBusiServiceList getHybridRule(){
		return hybridRule;
	}

	public void setOneTimeCharge(OneTimeFeeList obj){
		this.oneTimeCharge = obj;
	}

	public OneTimeFeeList getOneTimeCharge(){
		return oneTimeCharge;
	}

	public void setPoint(Spoints obj){
		this.point = obj;
	}

	public Spoints getPoint(){
		return point;
	}

	public void setProdOrderList(SProductOrderList obj){
		this.prodOrderList = obj;
	}

	public SProductOrderList getProdOrderList(){
		return prodOrderList;
	}

	public void setBusiServiceList(SBusiServiceList obj){
		this.busiServiceList = obj;
	}

	public SBusiServiceList getBusiServiceList(){
		return busiServiceList;
	}

	public void setRealtimeCharge(RealTimeCallChargeList obj){
		this.realtimeCharge = obj;
	}

	public RealTimeCallChargeList getRealtimeCharge(){
		return realtimeCharge;
	}

	public void setHybridBusiServiceList(HybridBusiServiceList obj){
		this.hybridBusiServiceList = obj;
	}

	public HybridBusiServiceList getHybridBusiServiceList(){
		return hybridBusiServiceList;
	}

	public void setSNegativeBalanceList(SNegativeBalanceList obj){
		this.sNegativeBalanceList = obj;
	}

	public SNegativeBalanceList getSNegativeBalanceList(){
		return sNegativeBalanceList;
	}

	public void setCapMaxControlList(CapMaxControlList obj){
		this.capMaxControlList = obj;
	}

	public CapMaxControlList getCapMaxControlList(){
		return capMaxControlList;
	}

	public void setQueryFreeTimes(SQueryFreeTimes obj){
		this.queryFreeTimes = obj;
	}

	public SQueryFreeTimes getQueryFreeTimes(){
		return queryFreeTimes;
	}

	public void setBillUsage(BillUsage obj){
		this.billUsage = obj;
	}

	public BillUsage getBillUsage(){
		return billUsage;
	}

	public void setActualUsageList(ActualUsageList obj){
		this.actualUsageList = obj;
	}

	public ActualUsageList getActualUsageList(){
		return actualUsageList;
	}

	public void setSContactList(List<SContact> obj){
		this.sContactList = obj;
	}

	public List<SContact> getSContactList(){
		return sContactList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryResponse rhs=(Do_queryResponse)rhs0;
		if(!ObjectUtils.equals(user, rhs.user)) return false;
		if(!ObjectUtils.equals(userAuth, rhs.userAuth)) return false;
		if(!ObjectUtils.equals(account, rhs.account)) return false;
		if(!ObjectUtils.equals(customer, rhs.customer)) return false;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		if(!ObjectUtils.equals(budget, rhs.budget)) return false;
		if(!ObjectUtils.equals(credit, rhs.credit)) return false;
		if(!ObjectUtils.equals(freeResourceGroupList, rhs.freeResourceGroupList)) return false;
		if(!ObjectUtils.equals(accumulation, rhs.accumulation)) return false;
		if(!ObjectUtils.equals(billInfo, rhs.billInfo)) return false;
		if(!ObjectUtils.equals(hybridRule, rhs.hybridRule)) return false;
		if(!ObjectUtils.equals(oneTimeCharge, rhs.oneTimeCharge)) return false;
		if(!ObjectUtils.equals(point, rhs.point)) return false;
		if(!ObjectUtils.equals(prodOrderList, rhs.prodOrderList)) return false;
		if(!ObjectUtils.equals(busiServiceList, rhs.busiServiceList)) return false;
		if(!ObjectUtils.equals(realtimeCharge, rhs.realtimeCharge)) return false;
		if(!ObjectUtils.equals(hybridBusiServiceList, rhs.hybridBusiServiceList)) return false;
		if(!ObjectUtils.equals(sNegativeBalanceList, rhs.sNegativeBalanceList)) return false;
		if(!ObjectUtils.equals(capMaxControlList, rhs.capMaxControlList)) return false;
		if(!ObjectUtils.equals(queryFreeTimes, rhs.queryFreeTimes)) return false;
		if(!ObjectUtils.equals(billUsage, rhs.billUsage)) return false;
		if(!ObjectUtils.equals(actualUsageList, rhs.actualUsageList)) return false;
		if(!ObjectUtils.equals(sContactList, rhs.sContactList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user)
		.append(userAuth)
		.append(account)
		.append(customer)
		.append(balance)
		.append(budget)
		.append(credit)
		.append(freeResourceGroupList)
		.append(accumulation)
		.append(billInfo)
		.append(hybridRule)
		.append(oneTimeCharge)
		.append(point)
		.append(prodOrderList)
		.append(busiServiceList)
		.append(realtimeCharge)
		.append(hybridBusiServiceList)
		.append(sNegativeBalanceList)
		.append(capMaxControlList)
		.append(queryFreeTimes)
		.append(billUsage)
		.append(actualUsageList)
		.append(sContactList)
		.toHashCode();
	}


}