package com.ailk.ims.component.billcycle.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlanHis;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.bill.entity.CaAccountDebt;
import com.ailk.openbilling.persistence.bill.entity.CaCycleRunHis;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;

public abstract class BillCycleHandler
{

    /**
     * 日志记录器
     */
    protected ImsLogger imsLogger = new ImsLogger(this.getClass());

    protected ChangeBillCycleContext billCycleContext;

    protected IMSContext context;

    /**
     * @description 用于计算首次出账日期
     */
    public abstract Date getFirstBillDate();

    /**
     * @description 用于创建账期
     */
    protected abstract void createCaBillingPlanDetail();

    /**
     * @description 不同类型的账期，各自的代码实现
     */
    protected abstract void modifyShortBillCycleImm();

    /**
     * @description 不同类型的账期，各自的代码实现
     */
    protected abstract void modifyShortBillCycleDelay();

    /**
     * @description 对于cycle_run为空的情况下，模拟cycle_run
     */
    protected abstract CaCycleRun calVirtualCycleRun();

    /**
     * @description 用于计算是否需要延迟生效
     * @return BOOLEAN
     */
    public boolean isNeedDelay()
    {
        Date firstBillDateDay = getFirstBillDate();
        billCycleContext.setFirstBillDay(firstBillDateDay);
        // 统一设置为不延迟生效。
        return false;
        /*
         * CaCycleRun oldCurrRun = billCycleContext.getOldCycleRun(); CaBillingPlan oldBillingPlan =
         * billCycleContext.getOldPlan(); Date cycleEnd =
         * DateUtil.getFormatDate(DateUtil.getFormateDate(oldCurrRun.getCurrCycleEnd()),
         * DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS); // 算出改成后的第一个出账日 begin // 请求日格式yyyymmdd String requestDateStr =
         * DateUtil.getFormatDate(billCycleContext.getSoDate(), DateUtil.DATE_FORMAT_YYYYMMDD); Date requestDateYMD =
         * DateUtil.getFormatDate(requestDateStr, DateUtil.DATE_FORMAT_YYYYMMDD); // 修改后的第一个出账日在请求日期和对应ca_cycle_run记录的end日期之内
         * 为立即生效 在这之外为延迟生效 //yanxl 在ca_cycle_run记录的end日期之后,老billing,org_end_date之前，允许立即生效
         * imsLogger.debug("wether next cycle:"+firstBillDateDay.before(requestDateYMD)+"`"+firstBillDateDay.after(cycleEnd)
         * +"`"+(oldBillingPlan.getOrgEndDate() != null && firstBillDateDay.after(oldBillingPlan.getOrgEndDate()))); //
         * 修改后的第一个出账日在请求日期和对应ca_cycle_run记录的end日期之内 为立即生效 在这之外为延迟生效 if (firstBillDateDay.before(requestDateYMD) ||
         * firstBillDateDay.after(cycleEnd)) { //yanxl 在ca_cycle_run记录的end日期之后,老billing,org_end_date之前，允许立即生效
         * if(oldBillingPlan.getOrgEndDate() == null || firstBillDateDay.after(oldBillingPlan.getOrgEndDate())){ return true; } }
         * return false;
         */
    }

    /**
     * @description 公共创建账期方法
     */
    public void createBillCycle()
    {
       BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
       CaBillingPlan caBillingPlan = convertBillingPlan();

       billingPlanQueryComponent.insertWithAllCache(caBillingPlan,new DBCondition(CaBillingPlan.Field.acctId,caBillingPlan.getAcctId()));
       createCycleRun(caBillingPlan);     
    }
    
    private CaBillingPlan convertBillingPlan(){
    	Long acctId = billCycleContext.getAcctId();

        Date outdate = billCycleContext.getValidDate();

        CaBillingPlan caBillingPlan = new CaBillingPlan();
        caBillingPlan.setBillingPlanId(DBUtil.getSequence(CaBillingPlan.class));
        caBillingPlan.setAcctId(acctId);
        caBillingPlan.setCycleSpecId(billCycleContext.getCycleSpecId());
        caBillingPlan.setBillFormatId(0);
        caBillingPlan.setPaymentPlanId(0l);
        caBillingPlan.setRangeSpecId(0);
        caBillingPlan.setCreateDate(outdate);
        caBillingPlan.setSoDate(outdate);
        caBillingPlan.setValidDate(outdate);
        Date firstBillDate = this.getFirstBillDate();
        caBillingPlan.setFirstBillDate(firstBillDate);
        caBillingPlan.setExpireDate(billCycleContext.getExpireDate());
        caBillingPlan.setCycleNum(0);
        caBillingPlan.setSoNbr(context.getSoNbr());
        return caBillingPlan;
    }
    
    public void modifyBillCycle(){
    	  prepareOldData();
    	  BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
          CaBillingPlan caBillingPlan = convertBillingPlan();
          billingPlanQueryComponent.updateMode1(caBillingPlan, new DBCondition(CaBillingPlan.Field.acctId,caBillingPlan.getAcctId()));
          changeCycleRun(caBillingPlan);
    }

    /**
     * @description 创建账期是，新建cacyclerun
     */
    private void createCycleRun(CaBillingPlan nowPlan)
    {

        // 如果是single balance 帐户则需要特殊处理，默认不出账
        // 都要出账
        /*
         * CaAccount dmAccount = context.get3hTree().loadAcct3hBean(billCycleContext.getAcctId()).getAccount(); if
         * (dmAccount.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE || dmAccount.getBillSts() !=
         * EnumCodeDefine.BILL_STS_ACTIVE) { return; }
         */
        /*
         * 广西不创建呆坏账信息 createCaAccountDebt();
         */
        //CaBillingPlan nowPlan = billCycleContext.getNewPlanList().get(0);

        // int currCycleBegin = Integer.valueOf(DateUtil.formatDate(nowPlan.getValidDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

        int currCycleBegin = Integer.valueOf(DateUtil.formatDate(getCurrCycleBegin(billCycleContext.getBillCycle()
                .getCycle_type()), DateUtil.DATE_FORMAT_YYYYMMDD));
        int currCycleEnd = Integer.valueOf(DateUtil.formatDate(nowPlan.getFirstBillDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

        CaCycleRun caAccountCycleRun = new CaCycleRun();
        caAccountCycleRun.setAcctId(billCycleContext.getAcctId());
        caAccountCycleRun.setCreateDate(context.getRequestDate());
        caAccountCycleRun.setCurrCycleBegin(currCycleBegin);
        caAccountCycleRun.setCurrCycleEnd(currCycleEnd);
        caAccountCycleRun.setCycleSpecId(CommonUtil.long2Int(nowPlan.getCycleSpecId()));
        caAccountCycleRun.setRealCycleSpecId(CommonUtil.long2Int(nowPlan.getCycleSpecId()));
        caAccountCycleRun.setModDate(context.getRequestDate());
        caAccountCycleRun.setLastCycleBegin(0);
        caAccountCycleRun.setLastCycleEnd(0);
        caAccountCycleRun.setBillDataSts(0);
        caAccountCycleRun.setBillDate(0);
        // caAccountCycleRun.setBillMonth(DateUtil.parserBillMonth(caAccountCycleRun.getCurrCycleEnd()));
        caAccountCycleRun.setBillRunSts(0);
        caAccountCycleRun.setCycleSts(1);
        caAccountCycleRun.setConfirmBillSts(0);
        context.getComponent(BaseComponent.class).insert(caAccountCycleRun);
        billCycleContext.setNewCycleRun(caAccountCycleRun);
    }

    private Date getCurrCycleBegin(int type)
    {

        Date firstBillDate = this.getFirstBillDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstBillDate);
        int unit = billCycleContext.getBillCycle().getCycle_unit();

        switch (type)
        {
        case EnumCodeDefine.ACCT_BILL_CYCLE_DAY:
            calendar.add(Calendar.DATE, -unit);
            return calendar.getTime();
        case EnumCodeDefine.ACCT_BILL_CYCLE_WEEK:
            calendar.add(Calendar.DATE, -7 * unit);
            return calendar.getTime();
        case EnumCodeDefine.ACCT_BILL_CYCLE_MONTH:
            calendar.add(DateUtil.MONTH, -unit);
            return calendar.getTime();
        case EnumCodeDefine.ACCT_BILL_CYCLE_YEAR:
            calendar.add(DateUtil.YEAR, -unit);
            return calendar.getTime();
        default:
            calendar.add(DateUtil.MONTH, -unit);
            return calendar.getTime();
        }
    }

    private void createCaAccountDebt()
    {
        List<CaAccountDebt> debts = DBUtil.query(CaAccountDebt.class, new DBCondition(CaAccountDebt.Field.acctId,
                billCycleContext.getAcctId()));

        if (CommonUtil.isEmpty(debts))
        {
            CaAccountDebt caAccountDebt = new CaAccountDebt();
            caAccountDebt.setAcctId(billCycleContext.getAcctId());
            Date outDate = billCycleContext.getSoDate();
            caAccountDebt.setModDate(outDate);
            // 创建的月份为上次缴费月份
            caAccountDebt.setLastUnpayMonth(DateUtil.formatDate(outDate, DateUtil.DATE_FORMAT_YYYYMM));
            String BillCycleStart = DateUtil.formatDate(outDate, DateUtil.DATE_FORMAT_YYYYMMDD);
            // 当前账期的开始时间为创建时间
            caAccountDebt.setBillCycleStart(BillCycleStart);
            // getDao(CaAccountDebtDao.class).
            context.getComponent(BaseComponent.class).insert(caAccountDebt);
        }
    }

    /**
     * @description 账期处理的公共代码部分
     */
    public void changeBillCycle()
    {
        prepareOldData();

        // 如果是立即生效，需要判断是否需要延迟生效
        if (billCycleContext.getEffectiveCycle() == EnumCodeDefine.EFFECTIVE_BY_CURRENT_CYCLE && isNeedDelay())
        {
            imsLogger.debug("need delay");
            billCycleContext.setEffectiveCycle(EnumCodeDefine.EFFECTIVE_BY_NEXT_CYCLE);
        }

        deleteOldPlan();
        // firstbilltype=1表示短账期，2表示长账期，默认是1
        short firstBillType = billCycleContext.getFirstBillType();
        // isDelay=1表示延时变更，0表示立即变更
        short effectiveCycle = billCycleContext.getEffectiveCycle();

        if (firstBillType == EnumCodeDefine.SHORT_BILL_TYPE)
        {
            // 创建短账期
            if (effectiveCycle == EnumCodeDefine.EFFECTIVE_BY_CURRENT_CYCLE)
            {
                // 立即变更
                modifyShortBillCycleImm();
            }
            if (effectiveCycle == EnumCodeDefine.EFFECTIVE_BY_NEXT_CYCLE)
            {
                // 延时变更
                modifyShortBillCycleDelay();
            }

        }
        // todo:未实现
        else if (firstBillType == EnumCodeDefine.LONG_BILL_TYPE)
        {
            // 创建长账期
            // createLongBillingCycle(caBillCycleIn);
        }

        // todo:cycle_run
        //changeCycleRun();
    }

    /**
     * @description 修改账期时，处理ca_cycle_run
     */
    private void changeCycleRun(CaBillingPlan plan)
    {

        // 如果是single balance 帐户则需要特殊处理，默认不出账
        /*
         * CaAccount dmAccount = context.get3hTree().loadAcct3hBean(billCycleContext.getAcctId()).getAccount(); if
         * (dmAccount.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE || dmAccount.getBillSts() !=
         * EnumCodeDefine.BILL_STS_ACTIVE) { return; }
         */
        // 获取需要的数据
        CaCycleRun oldCycleRun = billCycleContext.getOldCycleRun();
        //CaBillingPlan plan = billCycleContext.getNewPlanList().get(0);

        // 只有立即生效的情况下，才需要去修改cycle_run
    
            BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
            // 查询一把使用的oldcyclerun是构造出来的，还是存在的，存在的话，需要放到his表里
            CaCycleRun oldCurrRun = billingPlanQueryComponent.queryExistBillCycle(billCycleContext.getAcctId(),
                    billCycleContext.getSoDate());

            if (oldCurrRun != null)
            {
//                CaCycleRunHis caAccountCycleRunhis = new CaCycleRunHis();
//                caAccountCycleRunhis.setAcctId(oldCurrRun.getAcctId());
//                caAccountCycleRunhis.setCreateDate(billCycleContext.getSoDate());
//                caAccountCycleRunhis.setCurrCycleBegin(oldCurrRun.getCurrCycleBegin());
//                caAccountCycleRunhis.setCurrCycleEnd(oldCurrRun.getCurrCycleEnd());
//                caAccountCycleRunhis.setCycleSpecId(oldCurrRun.getCycleSpecId());
//                caAccountCycleRunhis.setRealCycleSpecId(oldCurrRun.getRealCycleSpecId());
//                caAccountCycleRunhis.setLastCycleBegin(oldCurrRun.getLastCycleBegin());
//                caAccountCycleRunhis.setLastCycleEnd(oldCurrRun.getLastCycleEnd());
//                caAccountCycleRunhis.setModDate(billCycleContext.getSoDate());
//                caAccountCycleRunhis.setBillDataSts(oldCurrRun.getBillDataSts());
//                caAccountCycleRunhis.setBillDate(oldCurrRun.getBillDate());
//                caAccountCycleRunhis.setBillRunSts(oldCurrRun.getBillRunSts());
//                caAccountCycleRunhis.setCycleSts(oldCurrRun.getCycleSts());
                // caAccountCycleRunhis.setBillMonth(oldCurrRun.getBillMonth());
                // billingPlanQueryComponent.insert(caAccountCycleRunhis);

                DBUtil.deleteByCondition(CaCycleRun.class, new DBCondition(CaCycleRun.Field.acctId, oldCurrRun.getAcctId()),
                        new DBCondition(CaCycleRun.Field.currCycleBegin, oldCurrRun.getCurrCycleBegin()));
            }

            int currCycleBegin = Integer.valueOf(DateUtil.formatDate(plan.getValidDate(), DateUtil.DATE_FORMAT_YYYYMMDD));
            int currCycleEnd = Integer.valueOf(DateUtil.formatDate(plan.getFirstBillDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

            CaCycleRun caCycleRun = new CaCycleRun();
            caCycleRun.setAcctId(oldCycleRun.getAcctId());
            caCycleRun.setCreateDate(billCycleContext.getSoDate());
            caCycleRun.setCurrCycleBegin(currCycleBegin);
            caCycleRun.setCurrCycleEnd(currCycleEnd);
            caCycleRun.setRealCycleSpecId(oldCycleRun.getCycleSpecId());
            caCycleRun.setCycleSpecId(CommonUtil.long2Int(plan.getCycleSpecId()));
            caCycleRun.setLastCycleBegin(oldCycleRun.getLastCycleBegin());
            caCycleRun.setLastCycleEnd(oldCycleRun.getLastCycleEnd());
            caCycleRun.setModDate(billCycleContext.getSoDate());
            caCycleRun.setBillDataSts(oldCycleRun.getBillDataSts());
            caCycleRun.setBillDate(oldCycleRun.getBillDate());
            caCycleRun.setBillRunSts(oldCycleRun.getBillRunSts());
            caCycleRun.setCycleSts(oldCycleRun.getCycleSts());
            // caCycleRun.setBillMonth(DateUtil.parserBillMonth(caCycleRun.getCurrCycleEnd()));
            billingPlanQueryComponent.insert(caCycleRun);
            billCycleContext.setNewCycleRun(caCycleRun);
        

        // 发送告警
    }

    /**
     * @description 修改账期时，准备计算数据
     */
    private void prepareOldData()
    {
        BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);

        CaCycleRun oldCurrRun = billingPlanQueryComponent.queryExistBillCycle(billCycleContext.getAcctId(),
                billCycleContext.getSoDate());
        CaBillingPlan oldBillingPlan = billingPlanQueryComponent.queryBillingPlan(billCycleContext.getAcctId(),
                billCycleContext.getSoDate());
        CaBillingCycleSpec oldCaBillCycleSpec = billingPlanQueryComponent.queryCycleSpec(oldBillingPlan.getCycleSpecId());
        CaBillingPeriod oldCaBillPeriod = billingPlanQueryComponent.queryBillPeriod(oldCaBillCycleSpec.getPeriodId());

        billCycleContext.setOldPlan(oldBillingPlan);
        billCycleContext.setOldCycleSpec(oldCaBillCycleSpec);
        billCycleContext.setOldPeriod(oldCaBillPeriod);

        if (oldCurrRun == null)
        {
            // 需要构建一个老数据的handler
            SBillCycle billCycle = new SBillCycle();
            billCycle.setCycle_type(oldCaBillPeriod.getPeriodType());
            billCycle.setCycle_unit(oldCaBillPeriod.getPeriodUnit());
            billCycle.setFirst_bill_day(oldCaBillCycleSpec.getBillingDateShift());

            BillCycleHandler oldHandler = ChangeBillCycleContext.instanceHandler(billCycleContext.getAcctId(), null, billCycle,
                    null, null, context);
            oldCurrRun = oldHandler.calVirtualCycleRun();
        }

        billCycleContext.setOldCycleRun(oldCurrRun);
    }

    /**
     * @description 统一方法，删除老的ca_billing_plan
     */
    private void deleteOldPlan()
    {
        Long acctId = billCycleContext.getAcctId();
        Date outdate = billCycleContext.getSoDate();

        List<DBCondition> conditions = new ArrayList<DBCondition>();
        conditions.add(new DBCondition(CaBillingPlan.Field.acctId, acctId));
        conditions.add(new DBCondition(CaBillingPlan.Field.expireDate, outdate, Operator.GREAT_EQUALS));
        List<CaBillingPlan> listCaBillPlan = DBUtil.query(CaBillingPlan.class,
                conditions.toArray(new DBCondition[conditions.size()]));
        if (listCaBillPlan != null && !listCaBillPlan.isEmpty())
        {
            for (CaBillingPlan cabillplan : listCaBillPlan)
            {
                // 首先记录历史
                CaBillingPlanHis caBillplanhis = new CaBillingPlanHis();
                caBillplanhis.setAcctId(acctId);
                caBillplanhis.setBillFormatId(cabillplan.getBillFormatId());
                caBillplanhis.setBillingPlanId(cabillplan.getBillingPlanId());
                caBillplanhis.setCycleSpecId(cabillplan.getCycleSpecId());
                caBillplanhis.setPaymentPlanId(cabillplan.getPaymentPlanId());
                caBillplanhis.setExpireDate(cabillplan.getExpireDate());
                caBillplanhis.setRangeSpecId(cabillplan.getRangeSpecId());
                caBillplanhis.setValidDate(cabillplan.getValidDate());
                caBillplanhis.setSoDate(outdate);
                caBillplanhis.setSoNbr(context.getSoNbr());
                caBillplanhis.setCreateDate(outdate);
                DBUtil.insert(caBillplanhis);

                // 再变更时间
                cabillplan.setExpireDate(context.getRequestDate());
                context.cacheSingle(cabillplan);
                DBUtil.deleteByCondition(CaBillingPlan.class,
                        new DBCondition(CaBillingPlan.Field.billingPlanId, cabillplan.getBillingPlanId()));
            }
        }
    }

    public ChangeBillCycleContext getBillCycleContext()
    {
        return billCycleContext;
    }
}
