package com.ailk.ims.component;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.billcycle.DateUtils;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;

/**
 * @Description 账期组件
 * @author
 */
public class BillingPlanQueryComponent extends BaseComponent
{
    private Log log = LogFactory.getLog(BillingPlanQueryComponent.class);
    private SimpleDateFormat sdfOfDate = new SimpleDateFormat("yyyyMMdd");
//    private SimpleDateFormat sdfOfMonth = new SimpleDateFormat("yyyyMM");
    public SimpleDateFormat sdfOfTime = new SimpleDateFormat("yyyyMMddHHmmss");
    
    
    public CaBillingPlan queryBillingPlan(Long acctId, Date outDate)
    {
        List<CaBillingPlan> listCaBillPlan = this.query(CaBillingPlan.class, new DBCondition(CaBillingPlan.Field.acctId, acctId),
                new DBCondition(CaBillingPlan.Field.validDate, outDate, Operator.LESS_EQUALS), new DBCondition(
                        CaBillingPlan.Field.expireDate, outDate, Operator.GREAT));
        if (listCaBillPlan == null || listCaBillPlan.isEmpty() || listCaBillPlan.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPlan");
        }
        return listCaBillPlan.get(0);
    }
    
    
    public CaCycleRun queryExistBillCycle(Long acctId,Date soDate){
        Integer currcyclebegin = Integer.valueOf(sdfOfDate.format(soDate));
        List<CaCycleRun> listCaAccountCycleRun = this.query(CaCycleRun.class, new OrderCondition[] { new OrderCondition(false,
                CaCycleRun.Field.currCycleBegin) }, null, new DBCondition(CaCycleRun.Field.acctId, acctId));
        if (listCaAccountCycleRun == null || listCaAccountCycleRun.isEmpty())
        {
            return null;
        }
        for (CaCycleRun caAccountCycleRun : listCaAccountCycleRun)
        {
            if (caAccountCycleRun.getCurrCycleBegin() <= currcyclebegin && currcyclebegin < caAccountCycleRun.getCurrCycleEnd())
            {
                return caAccountCycleRun;
            }
        }
        
        return null;
    }
    
    /*
     * 
     */
    public CaCycleRun queryCurrenBillCycle(Long acctId,Date soDate)
    {
        // 修改ca_account_cycle_run记录
        Integer currcyclebegin = Integer.valueOf(sdfOfDate.format(soDate));
        List<CaCycleRun> listCaAccountCycleRun = this.query(CaCycleRun.class, new OrderCondition[] { new OrderCondition(false,
                CaCycleRun.Field.currCycleBegin) }, null, new DBCondition(CaCycleRun.Field.acctId, acctId));
        if (listCaAccountCycleRun == null || listCaAccountCycleRun.isEmpty())
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.FIND_CYCLE_RUN_INFORMATION_FAIL);
        }
        CaCycleRun returnRun = listCaAccountCycleRun.get(0);
        for (CaCycleRun caAccountCycleRun : listCaAccountCycleRun)
        {
            if (caAccountCycleRun.getCurrCycleBegin() <= currcyclebegin && currcyclebegin < caAccountCycleRun.getCurrCycleEnd())
            {
                return caAccountCycleRun;
            }
        }
        CaCycleRun cacyclerun = queryBillCycle(acctId,soDate, returnRun);
        return cacyclerun;
    }

    /*
     * 查询当前时间所属账期---待优化yanxl
     */
    public CaCycleRun queryBillCycle(Long acctId,Date soDate, CaCycleRun cacyrun)
    {
        CaCycleRun CaCycleRun = new CaCycleRun();
        List<CaBillingPlan> listCaBillingPlan = new ArrayList<CaBillingPlan>();
        // 外部传入的时间
        int outdate = Integer.valueOf(sdfOfDate.format(soDate));
        int cycleStart = 0;
        Date cycleStartDate = DateUtils.getCurrentDate();
        Date cycleEndDate = DateUtils.getCurrentDate();
        int cycleEnd = 0;
        int num = 100000;
        listCaBillingPlan = this.query(
                CaBillingPlan.class,
                new OrderCondition[] { new OrderCondition(true, CaBillingPlan.Field.expireDate) },
                null,
                new DBCondition(CaBillingPlan.Field.acctId,acctId),
                new DBCondition(CaBillingPlan.Field.expireDate, DateUtils.getDateByString(String.valueOf(cacyrun
                        .getCurrCycleEnd())), Operator.GREAT));
        if (listCaBillingPlan == null || listCaBillingPlan.isEmpty())
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CA_BILLING_PLAN_NOT_RIGHT);
        }
        for (CaBillingPlan caBillPlan : listCaBillingPlan)
        {
            if (caBillPlan.getCycleSpecId() == 99l || caBillPlan.getCycleSpecId() == 100l)
            {
                cycleStart = Integer.valueOf(sdfOfDate.format(caBillPlan.getValidDate()));
                cycleEnd = Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate()));
                if (cycleStart <= outdate && outdate < cycleEnd)
                {
                    if (cycleStart == cacyrun.getCurrCycleEnd())
                    {
                        CaCycleRun.setLastCycleBegin(cacyrun.getCurrCycleBegin());
                        CaCycleRun.setLastCycleEnd(cacyrun.getCurrCycleEnd());
                        CaCycleRun.setAcctId(cacyrun.getAcctId());
                        CaCycleRun.setCurrCycleBegin(cycleStart);
                        CaCycleRun.setCurrCycleEnd(cycleEnd);
                        CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                        CaCycleRun.setCycleSpecId(cacyrun.getRealCycleSpecId());
                        CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                    }
                    else
                    {
                        CaCycleRun.setCurrCycleBegin(cycleStart);
                        CaCycleRun.setCurrCycleEnd(cycleEnd);
                        CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                    }
                    return CaCycleRun;
                }
                else
                {
                    CaCycleRun.setAcctId(cacyrun.getAcctId());
                    CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                    CaCycleRun.setLastCycleBegin(cycleStart);
                    CaCycleRun.setLastCycleEnd(cycleEnd);
                    CaCycleRun.setCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                    continue;
                }
            }
            else
            {
                CaBillingCycleSpec caBillCycleSpec = new CaBillingCycleSpec();
                List<CaBillingCycleSpec> listCaBillCycleSpec = this.query(CaBillingCycleSpec.class, new DBCondition(CaBillingCycleSpec.Field.cycleSpecId,
                        caBillPlan.getCycleSpecId()));
                if (listCaBillCycleSpec == null || listCaBillCycleSpec.isEmpty() || listCaBillCycleSpec.size() > 1)
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingCycleSpec");
                }

                caBillCycleSpec = listCaBillCycleSpec.get(0);
                // 找出ca_billing_period
                List<CaBillingPeriod> listCaBillPeriod;
                listCaBillPeriod = this.queryBillPeriodList(caBillCycleSpec.getPeriodId());
                if (listCaBillPeriod == null || listCaBillPeriod.isEmpty() || listCaBillPeriod.size() > 1)
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPeriod");
                }
                CaBillingPeriod caBillPeriod  = listCaBillPeriod.get(0);
                int periodType = caBillPeriod.getPeriodType();
                int periodUnit = caBillPeriod.getPeriodUnit();
                // 循环查询
                for (int i = 0; i <= num; i++)
                {
                    if (periodType == EnumCodeDefine.ACCT_BILL_CYCLE_YEAR)
                    {
                        cycleStartDate = DateUtils.addYears(DateUtils.getDateByString(String.valueOf(cacyrun.getCurrCycleEnd())),
                                i * periodUnit);
                        cycleEndDate = DateUtils.addYears(cycleStartDate, periodUnit);
                    }
                    else if (periodType == EnumCodeDefine.ACCT_BILL_CYCLE_MONTH)
                    {
                        cycleStartDate = DateUtils.addMonths(
                                DateUtils.getDateByString(String.valueOf(cacyrun.getCurrCycleEnd())), i * periodUnit);
                        cycleEndDate = DateUtils.addMonths(cycleStartDate, periodUnit);
                    }
                    else if (periodType == EnumCodeDefine.ACCT_BILL_CYCLE_WEEK)
                    {
                        cycleStartDate = DateUtils.addDays(DateUtils.getDateByString(String.valueOf(cacyrun.getCurrCycleEnd())),
                                (long) i * periodUnit * 7);
                        cycleEndDate = DateUtils.addDays(cycleStartDate, periodUnit * 7);
                    }
                    else if (periodType == EnumCodeDefine.ACCT_BILL_CYCLE_DAY)
                    {
                        cycleStartDate = DateUtils.addDays(DateUtils.getDateByString(String.valueOf(cacyrun.getCurrCycleEnd())),
                                (long) i * periodUnit);
                        cycleEndDate = DateUtils.addDays(cycleStartDate, periodUnit);
                    }
                    cycleStart = Integer.valueOf(sdfOfDate.format(cycleStartDate));
                    cycleEnd = Integer.valueOf(sdfOfDate.format(cycleEndDate));
                    // 判断账期是否包含查询时间
                    if (cycleStart <= outdate && cycleEnd > outdate)
                    {
                        if (cycleEnd <= Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())))
                        {
                            if (cycleStart == cacyrun.getCurrCycleEnd())
                            {
                                CaCycleRun.setAcctId(cacyrun.getAcctId());
                                CaCycleRun.setCurrCycleBegin(cycleStart);
                                CaCycleRun.setCurrCycleEnd(cycleEnd);
                                CaCycleRun.setLastCycleBegin(cacyrun.getCurrCycleBegin());
                                CaCycleRun.setLastCycleEnd(cacyrun.getCurrCycleEnd());
                                CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                                CaCycleRun.setCycleSpecId(cacyrun.getRealCycleSpecId());
                                CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            }
                            else
                            {
                                CaCycleRun.setAcctId(cacyrun.getAcctId());
                                CaCycleRun.setCurrCycleBegin(cycleStart);
                                CaCycleRun.setCurrCycleEnd(cycleEnd);
                                CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                                CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            }
                        }
                        if (cycleStart < Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate()))
                                && cycleEnd > Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())))
                        {
                            if (cycleStart == cacyrun.getCurrCycleEnd())
                            {
                                CaCycleRun.setAcctId(cacyrun.getAcctId());
                                CaCycleRun.setCurrCycleBegin(cycleStart);
                                CaCycleRun.setCurrCycleEnd(Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())));
                                CaCycleRun.setLastCycleBegin(cacyrun.getCurrCycleBegin());
                                CaCycleRun.setLastCycleEnd(cacyrun.getCurrCycleEnd());
                                CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                                CaCycleRun.setCycleSpecId(cacyrun.getRealCycleSpecId());
                                CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            }
                            else
                            {
                                CaCycleRun.setAcctId(cacyrun.getAcctId());
                                CaCycleRun.setCurrCycleBegin(cycleStart);
                                CaCycleRun.setCurrCycleEnd(Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())));
                                CaCycleRun.setRealCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                                CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            }
                        }
                        return CaCycleRun;
                    }
                    else
                    {
                        if (cycleEnd < Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())))
                        {
                            CaCycleRun.setAcctId(cacyrun.getAcctId());
                            CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            CaCycleRun.setLastCycleBegin(cycleStart);
                            CaCycleRun.setLastCycleEnd(cycleEnd);
                            CaCycleRun.setCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                            continue;
                        }
                        else if (cycleEnd >= Integer.valueOf(sdfOfDate.format(caBillPlan.getExpireDate())))
                        {
                            CaCycleRun.setAcctId(cacyrun.getAcctId());
                            CaCycleRun.setCycleSts(cacyrun.getCycleSts());
                            CaCycleRun.setLastCycleBegin(cycleStart);
                            CaCycleRun.setLastCycleEnd(cycleEnd);
                            CaCycleRun.setCycleSpecId(CommonUtil.long2Int(caBillPlan.getCycleSpecId()));
                            break;
                        }

                    }
                }
            }
        }
        return CaCycleRun;
    }
    
    public List<CaBillingPeriod> queryBillPeriodList(Integer periodId)
    {
        return SpringUtil.getOfferCacheBean().queryAcctBillPeriodList(periodId);
    }
    
    
    /**
     * TODO
     * 
     * @description 查询账期规格表
     * @param CaBillCycleIn
     * @return CaBillingCycleSpec
     * @throws SQLException
     */
    public CaBillingCycleSpec queryCaBillCycleSpec(SBillCycle sBillCycle)
    {
        // 出账偏移量
        int startBillday = sBillCycle.getFirst_bill_day();

        // 创建账期类别表
        CaBillingPeriod caBillPeriod = queryCaBillingPeriod(sBillCycle);

        List<CaBillingCycleSpec> listCaBillingCycleSpec =SpringUtil.getOfferCacheBean().queryAcctCycleSpecList(caBillPeriod.getPeriodId(), startBillday);
//            DBConfigInitBean.getCacheHouse().getCacheParser(CaBillingCycleSpec.class).getList(new CacheCondition(CaBillingCycleSpec.Field.periodId, caBillPeriod.getPeriodId()),
//                new CacheCondition(CaBillingCycleSpec.Field.billingDateShift, startBillday));
        if (listCaBillingCycleSpec == null || listCaBillingCycleSpec.isEmpty() || listCaBillingCycleSpec.size() > 1)
        {
            // 此处需要修改为信息管理的 异常
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingCycleSpec");
        }
        return listCaBillingCycleSpec.get(0);
    }
    
    /**
     * TODO
     * 
     * @description 创建账期类别表
     * @param CaBillCycleIn
     * @return CaBillingPeriod
     */
    public CaBillingPeriod queryCaBillingPeriod(SBillCycle sBillCycle)
    {
        // 周期类型 1-Day,2-Week,3-Month,4-Year
        int cycleType = sBillCycle.getCycle_type();
        // 周期单位
        int cycleUnit = sBillCycle.getCycle_unit();
        List<CaBillingPeriod> listCaBillingPeriod = SpringUtil.getOfferCacheBean().queryAcctCyclePeriod(cycleType, cycleUnit);;
        
        if (listCaBillingPeriod == null || listCaBillingPeriod.isEmpty() || listCaBillingPeriod.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPeriod");
        }
        CaBillingPeriod billingPeriod = listCaBillingPeriod.get(0);
//        context.addExtendParam(ConstantDefine.BILLING_PERIOD + billingPeriod.getPeriodId(), billingPeriod);
        return billingPeriod;
    }
    
    /**
     * @author yangjh 2012-06-19
     * @date yanchuan 2012-07-26 将查询出来的账期规格定义表缓存起来
     * @param cycleSpecId
     */
    public CaBillingCycleSpec queryCycleSpec(Long cycleSpecId)
    {
        List<CaBillingCycleSpec> cycleSpec = SpringUtil.getOfferCacheBean().queryAcctCycleSpec(cycleSpecId);
        if (CommonUtil.isEmpty(cycleSpec))
        {
            log.error("last ca_billing_cycle_Spec is null");
            IMSUtil.throwBusiException(ErrorCodeDefine.CYCLE_SPEC_NOT_EXIST, cycleSpecId);
        }
        if (cycleSpec.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPeriod");
        }
        return cycleSpec.get(0);
    }
    
    /**
     * @date yanchuan 2012-07-26 将查询出来的账期类型定义表缓存起来
     * @param periodId
     */
    public CaBillingPeriod queryBillPeriod(Integer periodId)
    {
        List<CaBillingPeriod> billingPeriod = queryBillPeriodList(periodId);
        if (CommonUtil.isEmpty(billingPeriod))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.PERIOD_ID_NOT_EXIST, periodId);
        }
        if (billingPeriod.size() > 1)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPeriod");
        }
        return billingPeriod.get(0);
    }
    
    
    public List<CaBillingPlan> queryBillingPlans(Long acctId, Date outDate)
    {
        List<CaBillingPlan> listCaBillPlan = this.query(CaBillingPlan.class, 
                new OrderCondition[]{new OrderCondition(true, CaBillingPlan.Field.validDate)},
                null,
                new DBCondition(CaBillingPlan.Field.acctId, acctId),
                new DBCondition(CaBillingPlan.Field.expireDate, outDate, Operator.GREAT));
        if (CommonUtil.isEmpty(listCaBillPlan))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.SIZE_OF_TABLE_ERROR, "CaBillingPlan");
        }
        return listCaBillPlan;
    }
}
