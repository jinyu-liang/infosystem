package com.ailk.ims.component.billcycle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.bill.entity.CaAccountDebt;

/**
 * @Description:提出帐管账期所包含的一些公共方法
 * @author wangjt
 * @Date 2013-2-22
 */
public class BaseBillCycleComponent extends BaseComponent
{
    protected SimpleDateFormat sdfOfDate = new SimpleDateFormat("yyyyMMdd");

    protected String formatDate(Date date)
    {
        return DateUtil.formatDate(date, "yyyyMMdd");
    }

    protected String formatMonth(Date date)
    {
        return DateUtil.formatDate(date, "yyyyMM");
    }

    public static Date getDateByString(String strDate)
    {
        if (strDate == null || strDate.trim().equals(""))
        {
            return DateUtil.currentDate();
        }
        return DateUtil.getFormattedDate(strDate);
    }

    protected CaBillingPeriod getCaBillingPeriod(Integer periodId)
    {
//        return SpringUtil.getOfferCacheBean().query
//        return DBConfigInitBean.getCacheHouse().getCacheParser(CaBillingPeriod.class).getSingle(new CacheCondition(CaBillingPeriod.Field.periodId, periodId));
//        return querySingle(CaBillingPeriod.class, new DBCondition(CaBillingPeriod.Field.periodId, periodId));
        List<CaBillingPeriod> periodList=getCaBillingPeriodList(periodId);
        if(CommonUtil.isNotEmpty(periodList)){
            return periodList.get(0);
        }else{
            return null;
        }
    }

    protected List<CaBillingPeriod> getCaBillingPeriodList(Integer periodId)
    {
        return SpringUtil.getOfferCacheBean().queryAcctBillPeriodList(periodId);
//        return DBConfigInitBean.getCacheHouse().getCacheParser(CaBillingPeriod.class).getList(new CacheCondition(CaBillingPeriod.Field.periodId, periodId));
//        return query(CaBillingPeriod.class, new DBCondition(CaBillingPeriod.Field.periodId, periodId));
    }

    protected CaBillingCycleSpec getCaBillingCycleSpec(Long cycleSpecId)
    {
//        return 
//        return DBConfigInitBean.getCacheHouse().getCacheParser(CaBillingCycleSpec.class).getSingle(new CacheCondition(CaBillingCycleSpec.Field.cycleSpecId, cycleSpecId));
//        return querySingle(CaBillingCycleSpec.class, new DBCondition(CaBillingCycleSpec.Field.cycleSpecId, cycleSpecId));
        List<CaBillingCycleSpec> specList=getCaBillingCycleSpecList(cycleSpecId);
        if(CommonUtil.isNotEmpty(specList)){
            return specList.get(0);
        }else{
            return null;
        }
    }

    protected List<CaBillingCycleSpec> getCaBillingCycleSpecList(Long cycleSpecId)
    {
        return SpringUtil.getOfferCacheBean().queryAcctCycleSpec(cycleSpecId);
//        return DBConfigInitBean.getCacheHouse().getCacheParser(CaBillingCycleSpec.class).getList(new CacheCondition(CaBillingCycleSpec.Field.cycleSpecId, cycleSpecId));
//        return query(CaBillingCycleSpec.class, new DBCondition(CaBillingCycleSpec.Field.cycleSpecId, cycleSpecId));
    }

    protected CaAccountDebt getCaAccountDebt(Long acctId)
    {
        return querySingle(CaAccountDebt.class, new DBCondition(CaAccountDebt.Field.acctId, acctId));
    }

    protected CaBillingPlan getCaBillingPlan(Long acctId)
    {
        return DBUtil.querySingle(CaBillingPlan.class, new DBCondition(CaBillingPlan.Field.acctId, acctId));
    }

    protected CaBillingPlan getCaBillingPlan(Long acctId, Date date)
    {
        return DBUtil.querySingle(CaBillingPlan.class, new DBCondition(CaBillingPlan.Field.acctId, acctId), new DBCondition(
                CaBillingPlan.Field.validDate, date, Operator.LESS_EQUALS), new DBCondition(CaBillingPlan.Field.expireDate, date,
                Operator.GREAT));
    }

    /**
     * 查询当前有效的记录+排序字段
     */
    protected List<CaBillingPlan> getCaBillingPlanList(Long acctId, Date date, jef.database.Field field, boolean isAsc)
    {
        OrderCondition[] order = { new OrderCondition(isAsc, field) };

        return DBUtil.query(CaBillingPlan.class, order, null, new DBCondition(CaBillingPlan.Field.acctId, acctId),
                new DBCondition(CaBillingPlan.Field.expireDate, date, Operator.GREAT_EQUALS));
    }

    /**
     * 查询当前有效的记录
     */
    protected List<CaBillingPlan> getCaBillingPlanList(Long acctId, Date date)
    {
        return DBUtil.query(CaBillingPlan.class, new DBCondition(CaBillingPlan.Field.acctId, acctId), new DBCondition(
                CaBillingPlan.Field.expireDate, date, Operator.GREAT), new DBCondition(CaBillingPlan.Field.validDate, date,
                Operator.LESS_EQUALS));
    }

    /**
     * 按照 currCycleBegin 降序排
     */
    protected List<CaCycleRun> getCaCycleRunList(Long acctId)
    {
        OrderCondition[] order = { new OrderCondition(false, CaCycleRun.Field.currCycleBegin) };
        return query(CaCycleRun.class, order, null, new DBCondition(CaCycleRun.Field.acctId, acctId));
    }

    /**
     * currCycleBegin <= 输入时间 ，同时按照 currCycleBegin 降序排
     */
    protected List<CaCycleRun> getCaCycleRunList(Long acctId, Date date)
    {
        OrderCondition[] order = { new OrderCondition(false, CaCycleRun.Field.currCycleBegin) };

        return query(CaCycleRun.class, order, null, new DBCondition(CaCycleRun.Field.acctId, acctId), new DBCondition(
                CaCycleRun.Field.currCycleBegin, formatDate(date), Operator.LESS_EQUALS));
    }

    public CaCycleRun getCaCycleRun(Long acctId, Date date)
    {
        return querySingle(CaCycleRun.class, new DBCondition(CaCycleRun.Field.acctId, acctId), new DBCondition(
                CaCycleRun.Field.currCycleBegin, formatDate(date), Operator.LESS_EQUALS), new DBCondition(
                CaCycleRun.Field.currCycleEnd, formatDate(date), Operator.GREAT));
    }
}
