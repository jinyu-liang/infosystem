package com.ailk.ims.component.billcycle.entity;

import java.util.Date;
import java.util.List;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.billcycle.handler.BillCycleHandler;
import com.ailk.ims.component.billcycle.handler.DayBillCycleHandler;
import com.ailk.ims.component.billcycle.handler.MonthBillCycleHandler;
import com.ailk.ims.component.billcycle.handler.WeekBillCycleHandler;
import com.ailk.ims.component.billcycle.handler.YearBillCycleHandler;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;

public class ChangeBillCycleContext{
	
	private Short firstBillType; 
	
	private Short effectiveCycle;
	
	private SBillCycle billCycle;
	
	private Long acctId;
	
	private Date soDate;
	
	private CaCycleRun oldCycleRun;  
	
	private CaBillingPlan oldPlan; 
	
	private Long cycleSpecId;
	
    // 找出该账户下的账期规格表
    private CaBillingCycleSpec oldCycleSpec; 
    // 找出该账户下的账期类型表
    private CaBillingPeriod oldPeriod;
    
    private List<CaBillingPlan> newPlanList;
    
    private CaCycleRun newCycleRun;
    
    private Date firstBillDay;
    
    private Date validDate;
    
    private Date expireDate;

    
    
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Short getFirstBillType() {
		if(firstBillType == null){
			return EnumCodeDefine.SHORT_BILL_TYPE;
		}
		
		return firstBillType;
	}

	public void setFirstBillType(Short firstBillType) {
		if (firstBillType != null
                && (firstBillType.shortValue() == EnumCodeDefine.SHORT_BILL_TYPE || firstBillType.shortValue() == EnumCodeDefine.LONG_BILL_TYPE))
		{
			this.firstBillType = firstBillType;
		}else
		{
			this.firstBillType = EnumCodeDefine.SHORT_BILL_TYPE;
		}
	}

	public Short getEffectiveCycle() {
		if(effectiveCycle == null){
			return EnumCodeDefine.EFFECTIVE_BY_CURRENT_CYCLE;
		}
		
		return effectiveCycle;
	}

	public void setEffectiveCycle(Short effectiveCycle) {
		if (effectiveCycle != null
                && (effectiveCycle.shortValue() == EnumCodeDefine.EFFECTIVE_BY_CURRENT_CYCLE || effectiveCycle.shortValue() == EnumCodeDefine.EFFECTIVE_BY_NEXT_CYCLE)){
			this.effectiveCycle = effectiveCycle;
		}else{
			this.effectiveCycle = EnumCodeDefine.EFFECTIVE_BY_CURRENT_CYCLE;
		}
		
	}
	
	public SBillCycle getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(SBillCycle billCycle) {
		this.billCycle = billCycle;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Date getSoDate() {
		return soDate;
	}

	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}

	public void setCycleSpecId(Long cycleSpecId) {
		this.cycleSpecId = cycleSpecId;
	}

	public CaCycleRun getOldCycleRun() {
		return oldCycleRun;
	}

	public void setOldCycleRun(CaCycleRun oldCycleRun) {
		this.oldCycleRun = oldCycleRun;
	}

	public CaBillingPlan getOldPlan() {
		return oldPlan;
	}

	public void setOldPlan(CaBillingPlan oldPlan) {
		this.oldPlan = oldPlan;
	}

	public CaBillingCycleSpec getOldCycleSpec() {
		return oldCycleSpec;
	}

	public void setOldCycleSpec(CaBillingCycleSpec oldCycleSpec) {
		this.oldCycleSpec = oldCycleSpec;
	}

	public CaBillingPeriod getOldPeriod() {
		return oldPeriod;
	}

	public void setOldPeriod(CaBillingPeriod oldPeriod) {
		this.oldPeriod = oldPeriod;
	}

	public Long getCycleSpecId() {
		return cycleSpecId;
	}

	public List<CaBillingPlan> getNewPlanList() {
		return newPlanList;
	}

	public void setNewPlanList(List<CaBillingPlan> newPlanList) {
		this.newPlanList = newPlanList;
	}
	
	public CaCycleRun getNewCycleRun() {
		return newCycleRun;
	}

	public void setNewCycleRun(CaCycleRun newCycleRun) {
		this.newCycleRun = newCycleRun;
	}

	public Date getFirstBillDay() {
		return firstBillDay;
	}

	public void setFirstBillDay(Date firstBillDay) {
		this.firstBillDay = firstBillDay;
	}
	
	public static BillCycleHandler instanceHandler(Long acctId,Long cycleSpecId,SBillCycle billCycle,Short firstBillType,Short effectiveCycle,IMSContext context,Date validDate,Date expireDate){
		if(cycleSpecId == null && billCycle == null){
			billCycle = new SBillCycle();
            billCycle.setFirst_bill_day(1);
            billCycle.setCycle_type(EnumCodeDefine.ACCT_BILL_CYCLE_MONTH);
            billCycle.setCycle_unit(1);
		}
		
        if (cycleSpecId == null || cycleSpecId <= 0)
        {
        	BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
            CaBillingCycleSpec newCaBillCycleSpec = billingPlanQueryComponent.queryCaBillCycleSpec(billCycle);
            cycleSpecId = newCaBillCycleSpec.getCycleSpecId();
        }
        
        if(billCycle == null){
        	BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
            CaBillingCycleSpec cycleSpec = billingPlanQueryComponent.queryCycleSpec(cycleSpecId);
            CaBillingPeriod period = billingPlanQueryComponent.queryBillPeriod(cycleSpec.getPeriodId());
        	
            billCycle = new SBillCycle();
            billCycle.setFirst_bill_day(cycleSpec.getBillingDateShift());
            billCycle.setCycle_type(period.getPeriodType());
            billCycle.setCycle_unit(period.getPeriodUnit());
        }
        
        //构建ChangeBillCycleContext
        ChangeBillCycleContext billCycleContext = new ChangeBillCycleContext();
        billCycleContext.setAcctId(acctId);
        billCycleContext.setBillCycle(billCycle);
        billCycleContext.setCycleSpecId(cycleSpecId);
        billCycleContext.setEffectiveCycle(effectiveCycle);
        billCycleContext.setFirstBillType(firstBillType);
        billCycleContext.setSoDate(context.getRequestDate());
        billCycleContext.setValidDate(validDate);
        billCycleContext.setExpireDate(expireDate);
      //在这里就可以构造handler
		switch (billCycleContext.getBillCycle().getCycle_type()){
			case EnumCodeDefine.ACCT_BILL_CYCLE_DAY:
				return new DayBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_WEEK:
				return new WeekBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_MONTH:
				return new MonthBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_YEAR:
				return new YearBillCycleHandler(billCycleContext,context);
			default:
				return new MonthBillCycleHandler(billCycleContext,context);
		}
	}
	

	public static BillCycleHandler instanceHandler(Long acctId,Long cycleSpecId,SBillCycle billCycle,Short firstBillType,Short effectiveCycle,IMSContext context)
	{
		if(cycleSpecId == null && billCycle == null){
			billCycle = new SBillCycle();
            billCycle.setFirst_bill_day(1);
            billCycle.setCycle_type(EnumCodeDefine.ACCT_BILL_CYCLE_MONTH);
            billCycle.setCycle_unit(1);
		}
		
        if (cycleSpecId == null || cycleSpecId <= 0)
        {
        	BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
            CaBillingCycleSpec newCaBillCycleSpec = billingPlanQueryComponent.queryCaBillCycleSpec(billCycle);
            cycleSpecId = newCaBillCycleSpec.getCycleSpecId();
        }
        
        if(billCycle == null){
        	BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
            CaBillingCycleSpec cycleSpec = billingPlanQueryComponent.queryCycleSpec(cycleSpecId);
            CaBillingPeriod period = billingPlanQueryComponent.queryBillPeriod(cycleSpec.getPeriodId());
        	
            billCycle = new SBillCycle();
            billCycle.setFirst_bill_day(cycleSpec.getBillingDateShift());
            billCycle.setCycle_type(period.getPeriodType());
            billCycle.setCycle_unit(period.getPeriodUnit());
        }
        
        //构建ChangeBillCycleContext
        ChangeBillCycleContext billCycleContext = new ChangeBillCycleContext();
        billCycleContext.setAcctId(acctId);
        billCycleContext.setBillCycle(billCycle);
        billCycleContext.setCycleSpecId(cycleSpecId);
        billCycleContext.setEffectiveCycle(effectiveCycle);
        billCycleContext.setFirstBillType(firstBillType);
        billCycleContext.setSoDate(context.getRequestDate());
        
      //在这里就可以构造handler
		switch (billCycleContext.getBillCycle().getCycle_type()){
			case EnumCodeDefine.ACCT_BILL_CYCLE_DAY:
				return new DayBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_WEEK:
				return new WeekBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_MONTH:
				return new MonthBillCycleHandler(billCycleContext,context);
			case EnumCodeDefine.ACCT_BILL_CYCLE_YEAR:
				return new YearBillCycleHandler(billCycleContext,context);
			default:
				return new MonthBillCycleHandler(billCycleContext,context);
		}
	}
}
