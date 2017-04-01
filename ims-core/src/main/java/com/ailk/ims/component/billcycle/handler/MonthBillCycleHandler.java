package com.ailk.ims.component.billcycle.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.billcycle.DateUtils;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;

public class MonthBillCycleHandler extends BillCycleHandler{

	public MonthBillCycleHandler(ChangeBillCycleContext billCycleContext,IMSContext context) {
		this.billCycleContext = billCycleContext;
		this.context = context;
	}

	@Override
	public Date getFirstBillDate() {
		Date firstBillDateDay =  null;
    	Date  requestDate = billCycleContext.getSoDate();
    	String yyyyMM = DateUtil.getFormatDate(requestDate, DateUtil.DATE_FORMAT_YYYYMM);
    	String yyyyMMdd = DateUtil.getFormatDate(requestDate, DateUtil.DATE_FORMAT_YYYYMMDD);
    	int firstBillDateDayIn = billCycleContext.getBillCycle().getFirst_bill_day();
    	String firstBillDateTemp = yyyyMM;
    	if (firstBillDateDayIn == 0) {
    		firstBillDateDayIn = 1;
    	}
    	if (firstBillDateDayIn >= 10) {
    		firstBillDateTemp = yyyyMM + CommonUtil.int2String(firstBillDateDayIn);
    	} else {
    		firstBillDateTemp = yyyyMM + "0" + CommonUtil.int2String(firstBillDateDayIn);
    	}
    	// 是否大于请求日期
    	if (CommonUtil.string2Integer(yyyyMMdd) >= CommonUtil.string2Integer(firstBillDateTemp)) {
    		firstBillDateDay = DateUtil.offsetDate(DateUtil.getFormatDate(firstBillDateTemp, 
    				DateUtil.DATE_FORMAT_YYYYMMDD), DateUtil.MONTH, billCycleContext.getBillCycle().getCycle_unit());
    	} else {
    		firstBillDateDay =  DateUtil.getFormatDate(firstBillDateTemp, DateUtil.DATE_FORMAT_YYYYMMDD);
    	}
    	
    	imsLogger.debug("MonthHandler getFirstBillDate:"+firstBillDateTemp);
		return firstBillDateDay;
	}

	@Override
	public void createCaBillingPlanDetail() {
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
        //int newFirstBillDay = billCycleContext.getBillCycle().getFirst_bill_day();
        //int newUnit = billCycleContext.getBillCycle().getCycle_unit();
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
        
        //计算expire_Date
//        int dayOfMonth =DateUtil.getDayOfMonth(oldCycleEndDate);
//        Date shortPlanEndDate = DateUtil.offsetDate(oldCycleEndDate, Calendar.DATE, -dayOfMonth);
//    	shortPlanEndDate = DateUtil.offsetDate(shortPlanEndDate, Calendar.DATE, newFirstBillDay);
        
//    	if(dayOfMonth > newFirstBillDay){
//        	shortPlanEndDate = DateUtil.offsetDate(shortPlanEndDate, Calendar.MONTH, newUnit);
//        }
        
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
        
        String newCycleEnd= String.valueOf(oldCycleRun.getCurrCycleEnd());
        Date oldCycleEndDate = DateUtil.getFormatDate(newCycleEnd, DateUtil.DATE_FORMAT_YYYYMMDD);
        
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
        int dayOfMonth =DateUtil.getDayOfMonth(oldCycleEndDate);
        Date shortPlanEndDate = DateUtil.offsetDate(oldCycleEndDate, Calendar.DATE, -dayOfMonth);
    	shortPlanEndDate = DateUtil.offsetDate(shortPlanEndDate, Calendar.DATE, newFirstBillDay);
        if(dayOfMonth >= newFirstBillDay){
        	shortPlanEndDate = DateUtil.offsetDate(shortPlanEndDate, Calendar.MONTH, newUnit);
        }
        
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
    	Date cycleBegin = DateUtils.addMonths(cycleEnd,-billCycleContext.getBillCycle().getCycle_unit());;
    	
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
    	
    	imsLogger.dump("MonthHandler calVirtualCycleRun:",cycleRun);
		return cycleRun;
	}
}
