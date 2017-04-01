package com.ailk.ims.component.billcycle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.common.wrapper.Holder;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpec;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecIn;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecOut;

/**
 * OpenBilling
 * 
 * @Company: Asiainfo Technologies （China）,Inc. Hangzhou
 * @author Asiainfo R&D deparment(HangZhou)/yinzh3
 * @version 1.0 Copyright (c) 2006
 * @date 2012-8-7 描述
 */
public class BillCycleSpecBusiness extends BaseBillCycleComponent
{
    /**
     * @description 入口函数
     * @param CACycleSpecIn
     * @return Holder<CaCycleSpecOut>
     * @throws SQLException
     */
    public Integer qryBillCycleSpec(CaCycleSpecIn caCycleSpecIn, Holder<CaCycleSpecOut> caCycleSpecOut)
    {
        if (!checkParam(caCycleSpecIn))
        {
            return -1;
        }
        CaCycleSpecOut caCycleSpecout = new CaCycleSpecOut();
        Long acctId = caCycleSpecIn.getAcctId();
        int cycleUnit = caCycleSpecIn.getCycleUnit();
        int startBillDay = caCycleSpecIn.getStartBillDay();
        short cycleType = caCycleSpecIn.getCycleType();
        // 账户为空,查询cycle_spec_id
        if (acctId == null)
        {
            CaBillingPeriod caBillPeriod = querySingle(CaBillingPeriod.class, new DBCondition(CaBillingPeriod.Field.periodType,
                    cycleType), new DBCondition(CaBillingPeriod.Field.periodUnit, cycleUnit));

            // CaBillingPeriod caBillPeriod = new CaBillingPeriod();
            // caBillPeriod.getQuery().addCondition(CaBillingPeriod.Field.periodType, Operator.EQUALS, cycleType);
            // caBillPeriod.getQuery().addCondition(CaBillingPeriod.Field.periodUnit, Operator.EQUALS, cycleUnit);
            // caBillPeriod = getDao(CaBillingPeriodDao.class).load(caBillPeriod);

            if (caBillPeriod == null)
            {
                imsLogger.error("have not found ca_billing_period");
                IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_BILLING_PERIOD_INVALID);
            }

            CaBillingCycleSpec caBillCycleSpec = querySingle(CaBillingCycleSpec.class, new DBCondition(
                    CaBillingCycleSpec.Field.periodId, caBillPeriod.getPeriodId()), new DBCondition(
                    CaBillingCycleSpec.Field.billingDateShift, startBillDay));

            // CaBillingCycleSpec caBillCycleSpec = new CaBillingCycleSpec();
            // caBillCycleSpec.getQuery().addCondition(CaBillingCycleSpec.Field.periodId, Operator.EQUALS,
            // caBillPeriod.getPeriodId());
            // caBillCycleSpec.getQuery().addCondition(CaBillingCycleSpec.Field.billingDateShift, Operator.EQUALS, startBillDay);
            // caBillCycleSpec = getDao(CaBillingCycleSpecDao.class).load(caBillCycleSpec);

            if (caBillCycleSpec == null)
            {
                imsLogger.error("have not found ca_billing_cycle_specification");
                IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_BILLING_CYCLE_INVALID);
            }
            caCycleSpecout.setCycleSpecId(caBillCycleSpec.getCycleSpecId().intValue());
        }
        // 查询出账计划
        else
        {

            List<CaCycleSpec> listCaCycleSpec = new ArrayList<CaCycleSpec>();

            Date date = DateUtil.currentDate();

            List<CaBillingPlan> listCaBillingPlan = query(CaBillingPlan.class,
                    new DBCondition(CaBillingPlan.Field.acctId, acctId), new DBCondition(CaBillingPlan.Field.expireDate, date,
                            Operator.GREAT_EQUALS));

            // CaBillingPlan caBillingPlan = new CaBillingPlan();
            // caBillingPlan.getQuery().addCondition(CaBillingPlan.Field.acctId, Operator.EQUALS, acctId);
            // caBillingPlan.getQuery().addCondition(CaBillingPlan.Field.expireDate, Operator.GREAT_EQUALS,
            // DateUtils.getCurrentDate());
            // List<CaBillingPlan> listCaBillingPlan = getDao(CaBillingPlanDao.class).find(caBillingPlan);
            if (listCaBillingPlan != null && !listCaBillingPlan.isEmpty())
            {
                for (CaBillingPlan caBillPlan : listCaBillingPlan)
                {
                    CaCycleSpec caCycleSpec = new CaCycleSpec();

                    CaBillingCycleSpec caBillCycleSpec = super.getCaBillingCycleSpec(caBillPlan.getCycleSpecId());
                    // CaBillingCycleSpec caBillCycleSpec = new CaBillingCycleSpec();
                    // caBillCycleSpec.getQuery().addCondition(CaBillingCycleSpec.Field.cycleSpecId, Operator.EQUALS,
                    // caBillPlan.getCycleSpecId());
                    // caBillCycleSpec = getDao(CaBillingCycleSpecDao.class).load(caBillCycleSpec);

                    if (caBillCycleSpec == null)
                    {
                        imsLogger.error("have not found ca_billing_cycle_specification");
                        IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_BILLING_CYCLE_INVALID);
                    }
                    caCycleSpec.setFirstBillDate(caBillCycleSpec.getBillingDateShift());
                    caCycleSpec.setValidate(caBillPlan.getValidDate());
                    caCycleSpec.setExpriedate(caBillPlan.getExpireDate());

                    CaBillingPeriod caBillingPeriod = super.getCaBillingPeriod(caBillCycleSpec.getPeriodId());

                    // CaBillingPeriod caBillingPeriod = new CaBillingPeriod();
                    // caBillingPeriod.getQuery().addCondition(CaBillingPeriod.Field.periodId, Operator.EQUALS,
                    // caBillCycleSpec.getPeriodId());
                    // caBillingPeriod = getDao(CaBillingPeriodDao.class).load(caBillingPeriod);

                    if (caBillingPeriod == null)
                    {
                        imsLogger.error("have not found ca_billing_period");
                        IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_BILLING_PERIOD_INVALID);
                    }
                    caCycleSpec.setCycleUnit(caBillingPeriod.getPeriodUnit());
                    caCycleSpec.setCycleType(caBillingPeriod.getPeriodType());
                    listCaCycleSpec.add(caCycleSpec);
                }
            }
            caCycleSpecout.setCaCycleSpecList(listCaCycleSpec);
        }
        caCycleSpecOut.set(caCycleSpecout);

        return 0;
    }

    /*
     * 检查参数
     */
    private boolean checkParam(CaCycleSpecIn cyclespecin)
    {
        Integer cycleUnit = cyclespecin.getCycleUnit();
        Integer startBillDay = cyclespecin.getStartBillDay();
        Short cycleType = cyclespecin.getCycleType();
        if (cycleUnit == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_CYCLE_UNIT_INVALID);
        }
        if (startBillDay == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_STRAT_BILL_DAY_INVALID);
        }
        if (cycleType == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.BILL_BILLCYCLE_CYCLE_TYPE_INVALID);
        }
        return true;
    }

}