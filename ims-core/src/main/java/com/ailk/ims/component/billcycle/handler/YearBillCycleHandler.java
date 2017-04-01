package com.ailk.ims.component.billcycle.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;

public class YearBillCycleHandler extends BillCycleHandler {

	public YearBillCycleHandler(ChangeBillCycleContext billCycleContext,IMSContext context) {
		this.billCycleContext = billCycleContext;
		this.context = context;
	}
	
	@Override
	public Date getFirstBillDate() {
		//按周操作最好还是使用java的api
    	Date  requestDate = billCycleContext.getSoDate();
    	int firstBillDateDayIn = billCycleContext.getBillCycle().getFirst_bill_day();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(requestDate);
    	int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
    	cal.add(Calendar.DATE, -dayOfYear);
    	cal.add(Calendar.DATE, firstBillDateDayIn);
    	// 是否大于请求日期
    	if (dayOfYear >= firstBillDateDayIn) {
    		// 往后推一个礼拜
    		cal.add(Calendar.YEAR, billCycleContext.getBillCycle().getCycle_unit());
    	}
    	imsLogger.debug("WeekHandler getFirstBillDate:"+cal.getTime());
		return cal.getTime();
	}

	@Override
	public void createCaBillingPlanDetail() {
		// TODO Auto-generated method stub

	}

	
    /**
	 * 
	 * @description 修改账期立即生效
	 */
	public void modifyShortBillCycleImm() {
        // 账户编号
        long acctId = billCycleContext.getAcctId();
        // 外部传入的操作时间
        Date outDate = billCycleContext.getSoDate();
        
        //取出所有后面计算需要用到的对象
        BaseComponent component = context.getComponent(BaseComponent.class);
        CaCycleRun oldCycleRun = billCycleContext.getOldCycleRun();
        CaBillingPlan oldPlan = billCycleContext.getOldPlan();
        long newCycleSpecId = billCycleContext.getCycleSpecId();
        
        List<CaBillingPlan> newPlanList = new ArrayList<CaBillingPlan>();
        
        //step 1 查询出所有有效的cabillingplan并且值置失效
        //deleteOldPlan();
		
        //step 2 
        String oldCycleStart = String.valueOf(oldCycleRun.getCurrCycleBegin());
        Date oldCycleStartDate = DateUtil.getFormatDate(oldCycleStart, DateUtil.DATE_FORMAT_YYYYMMDD);
        
//        String oldCycleEnd= String.valueOf(oldCycleRun.getCurrCycleEnd());
//        Date oldCycleEndDate = DateUtil.getFormatDate(oldCycleEnd, DateUtil.DATE_FORMAT_YYYYMMDD);
        
        CaBillingPlan orgBillplan = new CaBillingPlan();
        orgBillplan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        orgBillplan.setAcctId(acctId);
        orgBillplan.setCycleSpecId(newCycleSpecId);
        orgBillplan.setBillFormatId(oldPlan.getBillFormatId());
        orgBillplan.setPaymentPlanId(oldPlan.getPaymentPlanId());
        orgBillplan.setRangeSpecId(oldPlan.getRangeSpecId());
        orgBillplan.setValidDate(oldCycleStartDate);
        orgBillplan.setExpireDate(billCycleContext.getFirstBillDay());
        orgBillplan.setSoNbr(context.getSoNbr());
        orgBillplan.setCreateDate(outDate);
        orgBillplan.setSoDate(outDate);
        orgBillplan.setFirstBillDate(billCycleContext.getFirstBillDay());
        orgBillplan.setCycleNum(0);
        /*
        if(oldPlan.getOrgEndDate() == null){
        	orgBillplan.setOrgEndDate(oldCycleEndDate);
        }else{
        	orgBillplan.setOrgEndDate(oldPlan.getOrgEndDate());
        }
        */
        component.insert(orgBillplan);
        newPlanList.add(orgBillplan);
        imsLogger.dump("first ca_billing_plan", orgBillplan);
        
        //setp 3
        CaBillingPlan lastBillplan = new CaBillingPlan();
        lastBillplan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        lastBillplan.setAcctId(acctId);
        lastBillplan.setCycleSpecId(newCycleSpecId);
        lastBillplan.setBillFormatId(oldPlan.getBillFormatId());
        lastBillplan.setBillFormatId(oldPlan.getBillFormatId());
        lastBillplan.setPaymentPlanId(oldPlan.getPaymentPlanId());
        lastBillplan.setRangeSpecId(oldPlan.getRangeSpecId());
        lastBillplan.setValidDate(billCycleContext.getFirstBillDay());
        lastBillplan.setExpireDate(IMSUtil.getDefaultExpireDate());
        lastBillplan.setSoNbr(context.getSoNbr());
        lastBillplan.setCreateDate(outDate);
        lastBillplan.setSoDate(outDate);
        lastBillplan.setFirstBillDate(billCycleContext.getFirstBillDay());
        lastBillplan.setCycleNum(0);
        component.insert(lastBillplan);
        newPlanList.add(lastBillplan);
        imsLogger.dump("second ca_billing_plan", lastBillplan);
        
        billCycleContext.setNewPlanList(newPlanList);
	}

	/**
     * 
     * @description 修改账期延迟生效
     */
	public void modifyShortBillCycleDelay() {
        // 账户编号
        long acctId = billCycleContext.getAcctId();
        // 外部传入的操作时间
        Date outDate = billCycleContext.getSoDate();
        
        //取出所有后面计算需要用到的对象
        BaseComponent component = context.getComponent(BaseComponent.class);
        CaCycleRun oldCycleRun = billCycleContext.getOldCycleRun();
        CaBillingPlan oldPlan = billCycleContext.getOldPlan();
        int newFirstBillDay = billCycleContext.getBillCycle().getFirst_bill_day();
        int newUnit = billCycleContext.getBillCycle().getCycle_unit();
        long newCycleSpecId = billCycleContext.getCycleSpecId();
        
        List<CaBillingPlan> newPlanList = new ArrayList<CaBillingPlan>();
        
        //step 1 查询出所有有效的cabillingplan并且值置失效
        //deleteOldPlan();
        //step 2 利用老cyclerun的valid_date,expire_date构建第一条plan
        String oldCycleStart = String.valueOf(oldCycleRun.getCurrCycleBegin());
        Date oldCycleStartDate = DateUtil.getFormatDate(oldCycleStart, DateUtil.DATE_FORMAT_YYYYMMDD);
        
        String oldCycleEnd= String.valueOf(oldCycleRun.getCurrCycleEnd());
        Date oldCycleEndDate = DateUtil.getFormatDate(oldCycleEnd, DateUtil.DATE_FORMAT_YYYYMMDD);
        
        CaBillingPlan orgBillplan = new CaBillingPlan();
        orgBillplan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        orgBillplan.setAcctId(acctId);
        orgBillplan.setCycleSpecId(oldPlan.getCycleSpecId());
        orgBillplan.setBillFormatId(oldPlan.getBillFormatId());
        orgBillplan.setPaymentPlanId(oldPlan.getPaymentPlanId());
        orgBillplan.setRangeSpecId(oldPlan.getRangeSpecId());
        orgBillplan.setValidDate(oldCycleStartDate);
        orgBillplan.setExpireDate(oldCycleEndDate);
        orgBillplan.setSoNbr(context.getSoNbr());
        orgBillplan.setCreateDate(outDate);
        orgBillplan.setSoDate(outDate);
        orgBillplan.setFirstBillDate(oldCycleEndDate);
        orgBillplan.setCycleNum(0);
        
        /*
        if(oldPlan.getOrgEndDate() != null){
        	orgBillplan.setOrgEndDate(oldPlan.getOrgEndDate());
        }
        */
        component.insert(orgBillplan);
        newPlanList.add(orgBillplan);
        imsLogger.dump("first ca_billing_plan", orgBillplan);
        
        //step 3 利用老cyclerun的expire_date，新的first bill date构建第二条数据
        CaBillingPlan shortBillplan = new CaBillingPlan();
        shortBillplan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        shortBillplan.setAcctId(acctId);
        shortBillplan.setCycleSpecId(EnumCodeDefine.SHORT_CYCLE_SPECID);
        shortBillplan.setBillFormatId(oldPlan.getBillFormatId());
        shortBillplan.setBillFormatId(oldPlan.getBillFormatId());
        shortBillplan.setPaymentPlanId(oldPlan.getPaymentPlanId());
        shortBillplan.setRangeSpecId(oldPlan.getRangeSpecId());
        shortBillplan.setValidDate(oldCycleEndDate);
        
        //计算expire_Date
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(oldCycleEndDate);
    	int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
    	cal.add(Calendar.DATE, -dayOfYear);
    	cal.add(Calendar.DATE, newFirstBillDay);
    	
    	if(dayOfYear >= newFirstBillDay){
    		cal.add(Calendar.YEAR, newUnit);
    	}
    	
        Date shortPlanEndDate = cal.getTime();
        
        shortBillplan.setExpireDate(shortPlanEndDate);
        shortBillplan.setSoNbr(context.getSoNbr());
        shortBillplan.setCreateDate(outDate);
        shortBillplan.setSoDate(outDate);
        shortBillplan.setFirstBillDate(shortPlanEndDate);
        shortBillplan.setCycleNum(0);
        component.insert(shortBillplan);
        newPlanList.add(shortBillplan);
        imsLogger.dump("second ca_billing_plan", shortBillplan);
        
        //step 4 利用上一条billingplan构建第三条数据
        CaBillingPlan lastBillplan = new CaBillingPlan();
        lastBillplan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        lastBillplan.setAcctId(acctId);
        lastBillplan.setCycleSpecId(newCycleSpecId);
        lastBillplan.setBillFormatId(oldPlan.getBillFormatId());
        lastBillplan.setBillFormatId(oldPlan.getBillFormatId());
        lastBillplan.setPaymentPlanId(oldPlan.getPaymentPlanId());
        lastBillplan.setRangeSpecId(oldPlan.getRangeSpecId());
        lastBillplan.setValidDate(shortPlanEndDate);
        lastBillplan.setExpireDate(IMSUtil.getDefaultExpireDate());
        lastBillplan.setSoNbr(context.getSoNbr());
        lastBillplan.setCreateDate(outDate);
        lastBillplan.setSoDate(outDate);
        lastBillplan.setFirstBillDate(shortPlanEndDate);
        lastBillplan.setCycleNum(0);
        component.insert(lastBillplan);
        newPlanList.add(lastBillplan);
        imsLogger.dump("third ca_billing_plan", lastBillplan);
        
        billCycleContext.setNewPlanList(newPlanList);
	}

	@Override
	protected CaCycleRun calVirtualCycleRun() {
		Date cycleEnd = this.getFirstBillDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cycleEnd);
		cal.add(Calendar.YEAR, -billCycleContext.getBillCycle().getCycle_unit());
    	Date cycleBegin = cal.getTime();
    	//考虑首账期的情况
    	BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
    	CaBillingPlan oldPlan = billingPlanQueryComponent.queryBillingPlan(billCycleContext.getAcctId(),billCycleContext.getSoDate());
    	if(cycleBegin.before(oldPlan.getValidDate())){
    		cycleBegin = oldPlan.getValidDate();
    	}
    	
    	CaCycleRun cycleRun = new CaCycleRun();
    	cycleRun.setAcctId(billCycleContext.getAcctId());
    	cycleRun.setCreateDate(context.getRequestDate());
    	cycleRun.setCurrCycleBegin(Integer.valueOf(DateUtil.formatDate(cycleBegin, DateUtil.DATE_FORMAT_YYYYMMDD)));
    	cycleRun.setCurrCycleEnd(Integer.valueOf(DateUtil.formatDate(cycleEnd, DateUtil.DATE_FORMAT_YYYYMMDD)));
    	cycleRun.setCycleSpecId(0);
    	cycleRun.setRealCycleSpecId(CommonUtil.long2Int(billCycleContext.getCycleSpecId()));
    	cycleRun.setModDate(context.getRequestDate());
    	cycleRun.setLastCycleBegin(0);
    	cycleRun.setLastCycleEnd(0);
    	cycleRun.setBillDataSts(0);
    	cycleRun.setBillDate(0);
//    	cycleRun.setBillMonth(DateUtil.parserBillMonth(cycleRun.getCurrCycleEnd()));
    	cycleRun.setBillRunSts(0);
    	cycleRun.setCycleSts(1);
    	cycleRun.setConfirmBillSts(0);
    	
    	imsLogger.dump("YearHandler calVirtualCycleRun:",cycleRun);		
    	
		return cycleRun;
	}

}
