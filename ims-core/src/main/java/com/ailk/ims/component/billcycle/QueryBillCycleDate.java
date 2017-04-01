package com.ailk.ims.component.billcycle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.OffsetUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanIn;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanOut;

/**
 * OpenBilling
 * 
 * @Company: Asiainfo Technologies （China）,Inc. Hangzhou
 * @author Asiainfo R&D deparment(HangZhou)/yinzh3
 * @version 1.0 Copyright (c) 2006
 * @date 2012-8-7 描述
 */
public class QueryBillCycleDate extends BaseBillCycleComponent
{
    public QryBillingPlanOut queryBillCycleDate(QryBillingPlanIn qryBillingPlanIn)
    {
        if (!checkParam(qryBillingPlanIn))
        {
            imsLogger.error("check para error!");
        }
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        Long billingPlanId = qryBillingPlanIn.getBillingPlanId();
        short isPrepaid = qryBillingPlanIn.getIsPrepaid();
        if (billingPlanId == null)
        {
            billingPlanId = -1l;
        }

        if (billingPlanId.longValue() == -1l)
        {
            try
            {
                if (isPrepaid == 1)
                {
                    qryBillingPlanOut = qryPrepaidBillCycle(qryBillingPlanIn);
                }
                else if (isPrepaid == 0)
                {
                    qryBillingPlanOut = qryBillCycleDate(qryBillingPlanIn);
                }
                else if (isPrepaid == 2)
                {
                    qryBillingPlanOut = qryUnknownBillCycle(qryBillingPlanIn);
                }
                return qryBillingPlanOut;
            }
            catch (Exception e)
            {
                //imsLogger.error(ErrorCode.QUERY_BILL_CYCLE_FAIL, e);
                // ExceptionUtil.rethrow(e);
                throw new IMSException(e);
            }
        }

        return null;
    }

    /**
     * @description 账期查询(根据acctId,num来查)
     * @param QryBillingPlanIn
     * @param Holder <QryBillingPlanOut>
     * @return
     */
    private QryBillingPlanOut qryBillCycleDate(QryBillingPlanIn qryBillingPlanIn) throws Exception
    {
        // 返回值
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 账户编号
        long acctId = qryBillingPlanIn.getAcctId();
        // 是否根据挂起月查询标志，1表示根据挂起月查询，0表示当前月查询
        short isSupress = qryBillingPlanIn.getIsSupress();
        // num=0表示查询当前账期，num=1表示查询下个账期
        int num = qryBillingPlanIn.getNum();
        // 账期开始时间
        Date startDate = null;
        // 查询时间
        Date queryDate = qryBillingPlanIn.getOutDate();
        // 账期结束时间
        Date endDate = null;
        // 是否返回短账期的标志,IsExportShortBillCycle=1表示不输出短账期的specid,0表示输出短账期的specid,默认等于1
        short isExportShortBill = qryBillingPlanIn.getIsExportShortBillCycle();

        List<CaCycleRun> listCaCycleRun = super.getCaCycleRunList(acctId, queryDate);

        // 如果查询出来的结果为空，则报错
        if (listCaCycleRun == null || listCaCycleRun.isEmpty())
        {
            imsLogger.error("ca_account_cycle_run have no record");
            return qryBillingPlanOut;
        }

        CaCycleRun caCycleRun = listCaCycleRun.get(0);
        // 如果查找出的数据是当前日期
        if (Integer.valueOf(formatDate(queryDate)) < caCycleRun.getCurrCycleBegin()
                || Integer.valueOf(formatDate(queryDate)) >= caCycleRun.getCurrCycleEnd())
        {
            caCycleRun = queryCurrenBillCycle(qryBillingPlanIn, caCycleRun);

        }

        // 以最开始挂起那个月的账期作为当前账期
        if (isSupress == 1)
        {
            if (caCycleRun.getCycleSts() == 2)
            {

                OrderCondition[] order2 = { new OrderCondition(true, CaCycleRun.Field.currCycleBegin) };
                listCaCycleRun = query(CaCycleRun.class, order2, null, new DBCondition(CaCycleRun.Field.acctId, acctId),
                        new DBCondition(CaCycleRun.Field.cycleSts, 2, Operator.LESS_EQUALS));

                caCycleRun = listCaCycleRun.get(0);
                // 查询当前账期
                if (num == 0)
                {
                    if (isExportShortBill == 1)
                    {
                        if (caCycleRun.getRealCycleSpecId() == 99 || caCycleRun.getRealCycleSpecId() == 100)
                        {
                            qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                        }
                        else
                        {
                            qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getRealCycleSpecId());
                        }
                    }
                    else if (isExportShortBill == 0)
                    {
                        qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                    }
                    startDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleBegin()));
                    endDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd()));
                    qryBillingPlanOut.setStartDate(startDate);
                    qryBillingPlanOut.setEndDate(endDate);
                }
                else
                {
                    qryBillingPlanOut = queryNextBillCycle(acctId, sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd())),
                            num - 1, caCycleRun.getCycleSpecId(), isExportShortBill);
                }
            }
            else
            {
                // 查询当前账期
                if (num == 0)
                {
                    if (isExportShortBill == 1)
                    {
                        if (caCycleRun.getRealCycleSpecId() == 99 || caCycleRun.getRealCycleSpecId() == 100)
                        {
                            qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                        }
                        else
                        {
                            qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getRealCycleSpecId());
                        }
                    }
                    else if (isExportShortBill == 0)
                    {
                        qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                    }
                    startDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleBegin()));
                    endDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd()));
                    qryBillingPlanOut.setStartDate(startDate);
                    qryBillingPlanOut.setEndDate(endDate);
                }
                else
                {
                    qryBillingPlanOut = queryNextBillCycle(acctId, sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd())),
                            num - 1, caCycleRun.getCycleSpecId(), isExportShortBill);
                }
            }
        }
        // 查询当前那个月的账期
        else
        {
            // 查询当前账期
            if (num == 0)
            {
                if (isExportShortBill == 1)
                {
                    if (caCycleRun.getRealCycleSpecId() == 99 || caCycleRun.getRealCycleSpecId() == 100)
                    {
                        qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                    }
                    else
                    {
                        qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getRealCycleSpecId());
                    }
                }
                else if (isExportShortBill == 0)
                {
                    qryBillingPlanOut.setCycleSpecId((long) caCycleRun.getCycleSpecId());
                }
                startDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleBegin()));
                endDate = sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd()));
                qryBillingPlanOut.setStartDate(startDate);
                qryBillingPlanOut.setEndDate(endDate);
            }
            else
            {
                qryBillingPlanOut = queryNextBillCycle(acctId, sdfOfDate.parse(String.valueOf(caCycleRun.getCurrCycleEnd())),
                        num - 1, caCycleRun.getCycleSpecId(), isExportShortBill);
            }
        }

        return qryBillingPlanOut;

    }

    /*
     * 查询出当前账期的开始时间和结束时间
     */
    public CaCycleRun queryCurrenBillCycle(QryBillingPlanIn qryBillingPlanIn, CaCycleRun caAccountCycleRun)
    {
        // 外部传入的时间
        int outDate = Integer.valueOf(formatDate(qryBillingPlanIn.getOutDate()));
        int cycleStart = 0;
        Date cycleStartDate = DateUtils.getCurrentDate();
        Date cycleEndDate = DateUtils.getCurrentDate();
        int cycleEnd = 0;
        int num = 100000;

        Date dateByString = DateUtils.getDateByString(String.valueOf(caAccountCycleRun.getCurrCycleEnd()));
        List<CaBillingPlan> listCaBillingPlan = super.getCaBillingPlanList(qryBillingPlanIn.getAcctId(), dateByString,
                CaBillingPlan.Field.expireDate, true);

        if (listCaBillingPlan == null || listCaBillingPlan.isEmpty())
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        for (CaBillingPlan caBillPlan : listCaBillingPlan)
        {
            if (caBillPlan.getCycleSpecId() == 99l || caBillPlan.getCycleSpecId() == 100l)
            {
                cycleStart = Integer.valueOf(formatDate(caBillPlan.getValidDate()));
                cycleEnd = Integer.valueOf(formatDate(caBillPlan.getExpireDate()));
                if (cycleStart <= outDate && outDate < cycleEnd)
                {
                    caAccountCycleRun.setAcctId(caAccountCycleRun.getAcctId());
                    caAccountCycleRun.setCurrCycleBegin(cycleStart);
                    caAccountCycleRun.setCurrCycleEnd(cycleEnd);
                    caAccountCycleRun.setRealCycleSpecId(caBillPlan.getCycleSpecId().intValue());
                    caAccountCycleRun.setCycleSpecId(caAccountCycleRun.getCycleSpecId());
                    caAccountCycleRun.setCycleSts(caAccountCycleRun.getCycleSts());
                    return caAccountCycleRun;
                }
                else
                {
                    caAccountCycleRun.setCycleSpecId(caBillPlan.getCycleSpecId().intValue());
                    caAccountCycleRun.setCurrCycleEnd(cycleEnd);
                    continue;
                }

            }
            else
            {
                List<CaBillingCycleSpec> listCaBillCycleSpec = super.getCaBillingCycleSpecList(caBillPlan.getCycleSpecId());

                if (listCaBillCycleSpec == null || listCaBillCycleSpec.isEmpty() || listCaBillCycleSpec.size() > 1)
                {
                    imsLogger.error("ca_billing_cycle_spec is null or have more than one records of this cycle_spec_id");
                    IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_CYCLE_SPEC_NOT_RIGHT);
                }

                CaBillingCycleSpec caBillingCycleSpec = listCaBillCycleSpec.get(0);

                // 找出ca_billing_period
                List<CaBillingPeriod> listCaBillPeriod = super.getCaBillingPeriodList(caBillingCycleSpec.getPeriodId());

                if (listCaBillPeriod == null || listCaBillPeriod.isEmpty() || listCaBillPeriod.size() > 1)
                {
                    imsLogger.error("ca_billing_period is null or have more than one records of this period_id");
                    IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PERIOD_NOT_RIGHT);
                }
                CaBillingPeriod caBillPeriod = listCaBillPeriod.get(0);
                int periodType = caBillPeriod.getPeriodType();
                int periodUnit = caBillPeriod.getPeriodUnit();
                // 循环查询
                for (int i = 0; i <= num; i++)
                {
                    if (periodType == 4)
                    {
                        cycleStartDate = DateUtils.addYears(dateByString, i * periodUnit);
                        cycleEndDate = DateUtils.addYears(cycleStartDate, periodUnit);
                    }
                    else if (periodType == 3 || periodType == 5)
                    {
                        cycleStartDate = DateUtils.addMonths(dateByString, i * periodUnit);
                        cycleEndDate = DateUtils.addMonths(cycleStartDate, periodUnit);
                    }
                    else if (periodType == 2)
                    {
                        cycleStartDate = DateUtils.addDays(dateByString, (long) i * periodUnit * 7);
                        cycleEndDate = DateUtils.addDays(cycleStartDate, periodUnit * 7);
                    }
                    else if (periodType == 1)
                    {
                        cycleStartDate = DateUtils.addDays(dateByString, (long) i * periodUnit);
                        cycleEndDate = DateUtils.addDays(cycleStartDate, periodUnit);
                    }
                    cycleStart = Integer.valueOf(formatDate(cycleStartDate));
                    cycleEnd = Integer.valueOf(formatDate(cycleEndDate));
                    // 判断账期是否包含查询时间
                    if (cycleStart <= outDate && cycleEnd > outDate)
                    {
                        if (cycleEnd <= Integer.valueOf(formatDate(caBillPlan.getExpireDate())))
                        {
                            caAccountCycleRun.setAcctId(caAccountCycleRun.getAcctId());
                            caAccountCycleRun.setCurrCycleBegin(cycleStart);
                            caAccountCycleRun.setCurrCycleEnd(cycleEnd);
                            caAccountCycleRun.setRealCycleSpecId(caBillPlan.getCycleSpecId().intValue());
                            caAccountCycleRun.setCycleSpecId(caAccountCycleRun.getCycleSpecId());
                            caAccountCycleRun.setCycleSts(caAccountCycleRun.getCycleSts());
                        }
                        if (cycleStart < Integer.valueOf(formatDate(caBillPlan.getExpireDate()))
                                && cycleEnd > Integer.valueOf(formatDate(caBillPlan.getExpireDate())))
                        {
                            caAccountCycleRun.setAcctId(caAccountCycleRun.getAcctId());
                            caAccountCycleRun.setCurrCycleBegin(cycleStart);
                            caAccountCycleRun.setCurrCycleEnd(Integer.valueOf(formatDate(caBillPlan.getExpireDate())));
                            caAccountCycleRun.setRealCycleSpecId(caBillPlan.getCycleSpecId().intValue());
                            caAccountCycleRun.setCycleSpecId(caAccountCycleRun.getCycleSpecId());
                            caAccountCycleRun.setCycleSts(caAccountCycleRun.getCycleSts());
                        }
                        return caAccountCycleRun;
                    }
                    else
                    {
                        if (cycleEnd < Integer.valueOf(formatDate(caBillPlan.getExpireDate())))
                        {
                            continue;
                        }
                        else if (cycleEnd >= Integer.valueOf(formatDate(caBillPlan.getExpireDate())))
                        {
                            caAccountCycleRun.setCycleSpecId(caBillPlan.getCycleSpecId().intValue());
                            caAccountCycleRun.setCurrCycleEnd(cycleEnd);
                            break;
                        }

                    }
                }
            }
        }
        return caAccountCycleRun;
    }

    /*
     * 查询下一个账期
     */
    public QryBillingPlanOut queryNextBillCycle(long acctId, Date endate, int num, long cycleSpecId, short isExportShortBillCycle)
    {
        // 返回值
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 账期开始时间
        Date startDate = null;
        //
        boolean shortCycle = false;
        // 账期结束时间
        Date endDate = null;
        // 根据acctid查出当前出账计划

        CaBillingPlan cabillplan = super.getCaBillingPlan(acctId, endate);

        if (cabillplan == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        // 查出CaBillingCycleSpec
        CaBillingCycleSpec caBillingCycleSpec = super.getCaBillingCycleSpec(cabillplan.getCycleSpecId());

        if (caBillingCycleSpec == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_CYCLE_SPEC_NOT_RIGHT);
        }
        // 查出CaBillingPeriod
        CaBillingPeriod caBillperiod = super.getCaBillingPeriod(caBillingCycleSpec.getPeriodId());

        if (caBillperiod == null)
        {
            imsLogger.error("ca_billing_period have no record");
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PERIOD_NOT_RIGHT);
        }
        int periodUnit = caBillperiod.getPeriodUnit();
        int periodType = caBillperiod.getPeriodType();

        // 已用账期的个数
        int billCycleNum = 0;
        // 根据查询当天和失效时间求间隔共有多少天
        long days = DateUtils.daysBetween(endate, cabillplan.getExpireDate());
        // 根据查询当天和失效时间求间隔共有多少月
        int months = DateUtils.monthsBetween(endate, cabillplan.getExpireDate());
        // 根据查询当天和失效时间求间隔共有多少年
        int years = DateUtils.yearBetween(endate, cabillplan.getExpireDate());

        if (periodType == 4)
        {
            startDate = DateUtils.addYears(endate, num * periodUnit);
            endDate = DateUtils.addYears(startDate, periodUnit);
            billCycleNum = ((int) years) / periodUnit;
        }
        if (periodType == 3 || periodType == 5)
        {
            startDate = DateUtils.addMonths(endate, num * periodUnit);
            endDate = DateUtils.addMonths(startDate, periodUnit);
            billCycleNum = ((int) months) / periodUnit;
        }
        if (periodType == 2)
        {
            startDate = DateUtils.addDays(endate, (long) num * periodUnit * 7);
            endDate = DateUtils.addDays(startDate, periodUnit * 7);
            billCycleNum = ((int) days) / periodUnit * 7;
        }
        if (periodType == 1)
        {
            startDate = DateUtils.addDays(endate, (long) num * periodUnit);
            endDate = DateUtils.addDays(startDate, periodUnit);
            billCycleNum = ((int) days) / periodUnit;
        }
        if (cabillplan.getCycleSpecId() == 99 || cabillplan.getCycleSpecId() == 100)
        {
            billCycleNum = 1;
            startDate = cabillplan.getValidDate();
            endDate = cabillplan.getExpireDate();
            shortCycle = true;
        }
        if (shortCycle && num == 0)
        {
            qryBillingPlanOut.setStartDate(cabillplan.getValidDate());
            qryBillingPlanOut.setEndDate(cabillplan.getExpireDate());
            if (isExportShortBillCycle == 1)
            {
                qryBillingPlanOut.setCycleSpecId(cycleSpecId);
            }
            else if (isExportShortBillCycle == 0)
            {
                qryBillingPlanOut.setCycleSpecId(cabillplan.getCycleSpecId());
            }

        }
        else
        {
            if (billCycleNum >= num + 1)
            {
                qryBillingPlanOut.setStartDate(startDate);
                qryBillingPlanOut.setEndDate(endDate);
                qryBillingPlanOut.setCycleSpecId(cabillplan.getCycleSpecId());

            }
            else
            {
                qryBillingPlanOut = queryNextBillCycle(acctId, cabillplan.getExpireDate(), num - billCycleNum,
                        cabillplan.getCycleSpecId(), isExportShortBillCycle);
            }
        }

        return qryBillingPlanOut;

    }

    /*
     * 查询预付费的账期类型
     */
    public QryBillingPlanOut qryPrepaidBillCycle(QryBillingPlanIn qryBillingPlanIn)
    {
    	QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 账户编号
        long acctId = qryBillingPlanIn.getAcctId();
        // 外部操作时间
        Date outDate = qryBillingPlanIn.getOutDate();

        List<CaBillingPlan> listCaBillingPlan = super.getCaBillingPlanList(acctId, outDate);
        if (listCaBillingPlan == null || listCaBillingPlan.isEmpty() || listCaBillingPlan.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        CaBillingPlan caBillingPlan = listCaBillingPlan.get(0);
        qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
        BillingPlanQueryComponent billingPlanQueryComponent = context.getComponent(BillingPlanQueryComponent.class);
        CaBillingCycleSpec cycleSpec = billingPlanQueryComponent.queryCycleSpec(caBillingPlan.getCycleSpecId());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.dayBegin(context.getRequestDate()));
        calendar.set(Calendar.DAY_OF_MONTH, cycleSpec.getBillingDateShift());
        Date firstBillDate = calendar.getTime();
        if(firstBillDate.after(outDate))
        {
            firstBillDate = OffsetUtil.offsetMonth(firstBillDate, -1);
        }
        qryBillingPlanOut.setStartDate(firstBillDate);
        qryBillingPlanOut.setEndDate(OffsetUtil.offsetMonth(firstBillDate, 1));
        return qryBillingPlanOut;

    }

    /*
     * 查询预后属性未知的账期类型
     */
    private QryBillingPlanOut qryUnknownBillCycle(QryBillingPlanIn qryBillingPlanIn) throws Exception
    {
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 账户编号
        long acctId = qryBillingPlanIn.getAcctId();
        Acct3hBean acct3hBean = super.context.get3hTree().loadAcct3hBean(acctId);
        CaAccount caAccount = acct3hBean.getAccount();

        if (caAccount.getBillSts().intValue() == 1 || caAccount.getBillSts().intValue() == 2)
        {
            qryBillingPlanOut = qryPrepaidBillCycleDate(qryBillingPlanIn);
        }
        else
        {
            qryBillingPlanOut = qryPrepaidBillCycle(qryBillingPlanIn);
        }
        return qryBillingPlanOut;
    }

    /*
     * 查询预付费的账期开始日和账期结束日
     */
    private QryBillingPlanOut qryPrepaidBillCycleDate(QryBillingPlanIn qryBillingPlanIn) throws Exception
    {

        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 账户编号
        long acctId = qryBillingPlanIn.getAcctId();
        // 外部操作时间
        Date outDate = qryBillingPlanIn.getOutDate();
        // 第几个账期
        int billCycleNum = qryBillingPlanIn.getNum();

        List<CaBillingPlan> listCaBillingPlan = super.getCaBillingPlanList(acctId, outDate);

        if (listCaBillingPlan == null || listCaBillingPlan.isEmpty() || listCaBillingPlan.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        CaBillingPlan caBillingPlan = listCaBillingPlan.get(0);

        // 查出CaBillingCycleSpec
        CaBillingCycleSpec caBillingCycleSpec = super.getCaBillingCycleSpec(caBillingPlan.getCycleSpecId());

        if (caBillingCycleSpec == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_CYCLE_SPEC_NOT_RIGHT);
        }

        // 查出CaBillingPeriod
        CaBillingPeriod caBillPeriod = super.getCaBillingPeriod(caBillingCycleSpec.getPeriodId());
        if (caBillPeriod == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PERIOD_NOT_RIGHT);
        }
        qryBillingPlanOut = queryCurrentPrepaidBillCycle(caBillPeriod, caBillingPlan, caBillingCycleSpec.getBillingDateShift(),
                outDate, billCycleNum);

        return qryBillingPlanOut;

    }

    /*
     * 查询预付费当前的账期
     */
    private QryBillingPlanOut queryCurrentPrepaidBillCycle(CaBillingPeriod caBillingPeriod, CaBillingPlan caBillingPlan,
            int billDate, Date outDate, int billCycleNum) throws Exception
    {
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();
        // 当前账期开始日的字符串形式
        String cycleBeginDate = "";
        // 当前账期结束日的字符串形式
        String cycleEndDate = "";
        // 外部操作时间所属第几日
        @SuppressWarnings("deprecation")
        int date = outDate.getDate();
        int PeriodType = caBillingPeriod.getPeriodType();
        int PeriodUnit = caBillingPeriod.getPeriodUnit();
        Date billCycleBeginDate = caBillingPlan.getValidDate();
        Date billCycleEndDate = DateUtils.getCurrentDate();
        int usedBillCycleNum = 0;

        if (PeriodType == 4)
        {
            for (int i = 0; i < 100; i++)
            {
                billCycleEndDate = calculateYear(billCycleBeginDate, PeriodUnit, 1);
                if (billCycleEndDate.after(outDate))
                {
                    usedBillCycleNum = DateUtils.yearBetween(billCycleEndDate, caBillingPlan.getExpireDate()) / PeriodUnit;
                    if (billCycleNum == 0)
                    {
                        if (billCycleBeginDate.before(caBillingPlan.getValidDate()))
                        {
                            qryBillingPlanOut.setStartDate(caBillingPlan.getValidDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setStartDate(billCycleBeginDate);
                        }

                        if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                        {
                            qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setEndDate(billCycleEndDate);
                        }

                        qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                        return qryBillingPlanOut;
                    }
                    else if (usedBillCycleNum >= billCycleNum)
                    {
                        billCycleEndDate = calculateYear(billCycleEndDate, PeriodUnit, billCycleNum);
                        billCycleBeginDate = calculateYear(billCycleEndDate, PeriodUnit, -1);
                        qryBillingPlanOut.setStartDate(billCycleBeginDate);
                        if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                        {
                            qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setEndDate(billCycleEndDate);
                        }
                        qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                        return qryBillingPlanOut;
                    }
                    else
                    {
                        qryBillingPlanOut = queryNextPrepaidBillCycle(caBillingPlan, billCycleNum - usedBillCycleNum);
                    }
                }
                billCycleBeginDate = billCycleEndDate;
            }
        }
        else if (PeriodType == 3 || PeriodType == 5)
        {
            if (date >= billDate)
            {
                cycleEndDate = formatMonth(DateUtils.addMonths(outDate, PeriodUnit));
                cycleBeginDate = formatMonth(outDate);
            }
            else
            {
                cycleBeginDate = formatMonth(DateUtils.addMonths(outDate, (-1) * PeriodUnit));
                cycleEndDate = formatMonth(outDate);
            }
            if (billDate >= 10)
            {
                cycleEndDate += billDate;
                cycleBeginDate += billDate;
            }
            else
            {
                cycleEndDate += "0" + billDate;
                cycleBeginDate += "0" + billDate;
            }
            usedBillCycleNum = DateUtils.monthsBetween(sdfOfDate.parse(cycleEndDate), caBillingPlan.getExpireDate()) / PeriodUnit;

            if (billCycleNum == 0)
            {
                if (sdfOfDate.parse(cycleBeginDate).before(caBillingPlan.getValidDate()))
                {
                    qryBillingPlanOut.setStartDate(caBillingPlan.getValidDate());
                }
                else
                {
                    qryBillingPlanOut.setStartDate(sdfOfDate.parse(cycleBeginDate));
                }
                if (sdfOfDate.parse(cycleEndDate).after(caBillingPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(sdfOfDate.parse(cycleEndDate));
                }
                qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());

            }
            else if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = DateUtils.addMonths(sdfOfDate.parse(cycleEndDate), billCycleNum * PeriodUnit);
                billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, (-1) * PeriodUnit);
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillingPlan, billCycleNum - usedBillCycleNum);
            }

        }
        else if (PeriodType == 2)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(outDate);
            // 外部操作时间所处的是星期几
            int weekDay = 0;
            weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay == 1)
            {
                weekDay = 7;
            }
            else
            {
                weekDay = weekDay - 1;
            }
            // 临时存放账期结束日期的变量
            Date billEndDate = DateUtils.getCurrentDate();
            if (weekDay > billDate)
            {
                billEndDate = DateUtils.addDays(outDate, 7 - weekDay + billDate);
            }
            else
            {
                billEndDate = DateUtils.addDays(outDate, billDate - weekDay);
            }
            cycleBeginDate = formatDate(DateUtils.addDays(billEndDate, -7));
            cycleEndDate = formatDate(billEndDate);

            usedBillCycleNum = (int) (DateUtils.daysBetween(billCycleEndDate, caBillingPlan.getExpireDate()) / PeriodUnit * 7);
            if (billCycleNum == 0)
            {
                if (sdfOfDate.parse(cycleBeginDate).before(caBillingPlan.getValidDate()))
                {
                    qryBillingPlanOut.setStartDate(caBillingPlan.getValidDate());
                }
                else
                {
                    qryBillingPlanOut.setStartDate(sdfOfDate.parse(cycleBeginDate));
                }
                if (sdfOfDate.parse(cycleEndDate).after(caBillingPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(sdfOfDate.parse(cycleEndDate));
                }
                qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());

            }
            else if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = DateUtils.addMonths(sdfOfDate.parse(cycleEndDate), billCycleNum * PeriodUnit);
                billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, (-1) * PeriodUnit);
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillingPlan, billCycleNum - usedBillCycleNum);
            }

        }
        else if (PeriodType == 1)
        {
            billCycleBeginDate = caBillingPlan.getValidDate();
            billCycleEndDate = DateUtils.getCurrentDate();
            for (int i = 0; i < 10000; i++)
            {
                billCycleEndDate = calculateDate(billCycleBeginDate, PeriodUnit, 1);
                if (billCycleEndDate.after(outDate))
                {
                    usedBillCycleNum = (int) (DateUtils.daysBetween(billCycleEndDate, caBillingPlan.getExpireDate()) / PeriodUnit);
                    if (billCycleNum == 0)
                    {
                        if (billCycleBeginDate.before(caBillingPlan.getValidDate()))
                        {
                            qryBillingPlanOut.setStartDate(caBillingPlan.getValidDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setStartDate(billCycleBeginDate);
                        }

                        if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                        {
                            qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setEndDate(billCycleEndDate);
                        }

                        qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                        return qryBillingPlanOut;
                    }
                    else if (usedBillCycleNum >= billCycleNum)
                    {
                        billCycleEndDate = DateUtils.addMonths(billCycleEndDate, billCycleNum * PeriodUnit);
                        billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, PeriodUnit * (-1));
                        qryBillingPlanOut.setStartDate(billCycleBeginDate);
                        if (billCycleEndDate.after(caBillingPlan.getExpireDate()))
                        {
                            qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                        }
                        else
                        {
                            qryBillingPlanOut.setEndDate(billCycleEndDate);
                        }
                        qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                        return qryBillingPlanOut;
                    }
                    else
                    {
                        qryBillingPlanOut = queryNextPrepaidBillCycle(caBillingPlan, billCycleNum - usedBillCycleNum);
                    }
                }
                billCycleBeginDate = billCycleEndDate;
            }
        }
        else if (PeriodType == 0)
        {
            if (billCycleNum == 0)
            {
                qryBillingPlanOut.setStartDate(caBillingPlan.getValidDate());
                qryBillingPlanOut.setEndDate(caBillingPlan.getExpireDate());
                qryBillingPlanOut.setCycleSpecId(caBillingPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillingPlan, billCycleNum);
            }
        }
        return qryBillingPlanOut;
    }

    /*
     * 计算出预付费的下个账期开始日 和账期结束日
     */
    private QryBillingPlanOut queryNextPrepaidBillCycle(CaBillingPlan caBillingPlan, int billCycleNum) throws Exception
    {
        QryBillingPlanOut qryBillingPlanOut = new QryBillingPlanOut();

        CaBillingPlan caBillPlan = super.getCaBillingPlan(caBillingPlan.getAcctId(), caBillingPlan.getExpireDate());

        if (caBillPlan == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        // 查出CaBillingCycleSpec
        CaBillingCycleSpec caBillingCycleSpec = super.getCaBillingCycleSpec(caBillPlan.getCycleSpecId());
        if (caBillingCycleSpec == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_CYCLE_SPEC_NOT_RIGHT);
        }
        // 查出CaBillingPeriod
        CaBillingPeriod caBillPeriod = super.getCaBillingPeriod(caBillingCycleSpec.getPeriodId());
        if (caBillPeriod == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PERIOD_NOT_RIGHT);
        }
        int PeriodType = caBillPeriod.getPeriodType();
        int PeriodUnit = caBillPeriod.getPeriodUnit();

        Date billCycleBeginDate = caBillingPlan.getValidDate();
        Date billCycleEndDate = DateUtils.getCurrentDate();
        int usedBillCycleNum = 0;

        if (PeriodType == 4)
        {
            billCycleEndDate = calculateYear(billCycleBeginDate, PeriodUnit, 1);
            usedBillCycleNum = DateUtils.yearBetween(caBillPlan.getValidDate(), caBillPlan.getExpireDate()) / PeriodUnit;
            if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = calculateYear(billCycleEndDate, PeriodUnit, billCycleNum);
                billCycleBeginDate = calculateYear(billCycleEndDate, PeriodUnit, -1);
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillPlan, billCycleNum - usedBillCycleNum);
            }

        }
        else if (PeriodType == 3 || PeriodType == 5)
        {
            usedBillCycleNum = DateUtils.monthsBetween(caBillPlan.getValidDate(), caBillPlan.getExpireDate()) / PeriodUnit;
            if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = DateUtils.addMonths(caBillPlan.getValidDate(), billCycleNum * PeriodUnit);
                billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, (-1) * PeriodUnit);
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillPlan, billCycleNum - usedBillCycleNum);
            }

        }
        else if (PeriodType == 2)
        {
            usedBillCycleNum = (int) (DateUtils.daysBetween(caBillPlan.getValidDate(), caBillPlan.getExpireDate()) / PeriodUnit * 7);
            if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = DateUtils.addMonths(caBillPlan.getValidDate(), billCycleNum * PeriodUnit);
                billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, (-1) * PeriodUnit);
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillPlan, billCycleNum - usedBillCycleNum);
            }

        }
        else if (PeriodType == 1)
        {
            usedBillCycleNum = (int) (DateUtils.daysBetween(caBillPlan.getValidDate(), caBillPlan.getExpireDate()) / PeriodUnit);
            if (usedBillCycleNum >= billCycleNum)
            {
                billCycleEndDate = DateUtils.addMonths(billCycleEndDate, billCycleNum * PeriodUnit);
                billCycleBeginDate = DateUtils.addMonths(billCycleEndDate, PeriodUnit * (-1));
                qryBillingPlanOut.setStartDate(billCycleBeginDate);
                if (billCycleEndDate.after(caBillPlan.getExpireDate()))
                {
                    qryBillingPlanOut.setEndDate(caBillPlan.getExpireDate());
                }
                else
                {
                    qryBillingPlanOut.setEndDate(billCycleEndDate);
                }
                qryBillingPlanOut.setCycleSpecId(caBillPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillPlan, billCycleNum - usedBillCycleNum);
            }
        }
        else if (PeriodType == 0)
        {
            if (billCycleNum == 0)
            {
                qryBillingPlanOut.setStartDate(caBillPlan.getValidDate());
                qryBillingPlanOut.setEndDate(caBillPlan.getExpireDate());
                qryBillingPlanOut.setCycleSpecId(caBillPlan.getCycleSpecId());
                return qryBillingPlanOut;
            }
            else
            {
                qryBillingPlanOut = queryNextPrepaidBillCycle(caBillPlan, billCycleNum - 1);
            }
        }
        return qryBillingPlanOut;
    }

    /*
     * 计算出当前账期结束日期（按年的账期类型）
     */
    private Date calculateYear(Date cycleEndDate, int PeriodUnit, int num)
    {
        Date billCycleEndDate = DateUtils.getCurrentDate();
        billCycleEndDate = DateUtils.addYears(cycleEndDate, PeriodUnit * num);
        return billCycleEndDate;
    }

    /*
     * 计算出当前账期结束日期（按日的账期类型）
     */
    private Date calculateDate(Date cycleEndDate, int PeriodUnit, int num)
    {
        Date billCycleEndDate = DateUtils.getCurrentDate();
        billCycleEndDate = DateUtils.addDays(cycleEndDate, PeriodUnit * num);
        return billCycleEndDate;
    }

    /**
     * @description 检查参数
     * @param QryBillingPlanIn
     * @param
     * @return boolean
     * @throws Exception
     */
    private boolean checkParam(QryBillingPlanIn qryBillingPlanIn)
    {
        if (qryBillingPlanIn == null)
        {
            imsLogger.error("BillingPlanQueryBusiness_qryBillingPlanIn is null");
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_CHECK_PARAM_ERROR);
        }
        if (qryBillingPlanIn.getAcctId() == null || qryBillingPlanIn.getAcctId() == 0)
        {
            imsLogger.error("BillingPlanQueryBusiness_qryBillingPlanIn.acct_id is null or equal 0");
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_CHECK_PARAM_ERROR);
        }
        if (qryBillingPlanIn.getIsExportShortBillCycle() == null)
        {
            // IsExportShortBillCycle=1表示不输出短账期的specid,0表示输出短账期的specid
            qryBillingPlanIn.setIsExportShortBillCycle((short) 1);
        }
        if (qryBillingPlanIn.getIsSupress() == null)
        {
            // IsSupress=1表示以最开始挂起那个月的账期作为当前账期,0表示以当前时间开始，默认为0
            qryBillingPlanIn.setIsSupress((short) 0);
        }
        if (qryBillingPlanIn.getOutDate() == null)
        {
            imsLogger.error("BillingPlanQueryBusiness_qryBillingPlanIn.out_Date is null");
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_CHECK_PARAM_ERROR);
        }
        if (qryBillingPlanIn.getIsPrepaid() == null)
        {
            // IsPrepaid=1表示是预付费账户,0表示后付费账户
            qryBillingPlanIn.setIsPrepaid((short) 2);
        }
        return true;
    }
}
