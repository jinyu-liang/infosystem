package com.ailk.imssh.prod.cmp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.itable.entity.IProduct;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;

/**
 * @Description 产品账期组件
 * @author lijc3
 * @Date 2012-5-17
 * @Date 2012-10-15 wukl cycleNum上海不用，默认填0
 */
public class ProdCycleCmp extends ProdCmp
{

    /**
     * 解析账期 lijc3 2012-5-17
     */
    public CoProdBillingCycle parseProdBillingCycle(IProduct sProd)
    {
        PmProductOfferLifecycle dmLifeCycle = context.get3hTree().loadOffering3hBean(sProd.getOfferId()).getOfferLifeCycle();
        Date validDate = sProd.getValidDate();
        Date expireDate = sProd.getExpireDate();
        Date cycleFirstDay = createFirstBillDate(new Date(validDate.getTime()), null, dmLifeCycle.getCycleType(),
                dmLifeCycle.getCycleUnit());
        CoProdBillingCycle billCycle = new CoProdBillingCycle();
        billCycle.setProductId(sProd.getProductId());
        billCycle.setCycleType(dmLifeCycle.getCycleType());
        billCycle.setFirstBillDate(cycleFirstDay);
        billCycle.setValidDate(validDate);
        billCycle.setExpireDate(expireDate);
        billCycle.setCycleUnit(dmLifeCycle.getCycleUnit());
        Integer cycleFlag = dmLifeCycle.getCycleSyncFlag() == null ? EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT : dmLifeCycle
                .getCycleSyncFlag();
        billCycle.setCycleSyncFlag(cycleFlag);
        billCycle.setCycleNum(0);
        if(sProd.getUserId()!=null){
            billCycle.setObjectId(sProd.getUserId());
            billCycle.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        }else{
            billCycle.setObjectId(sProd.getAcctId());
            billCycle.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
        billCycle.setSoNbr(context.getSoNbr());
        billCycle.setSoDate(context.getCommitDate());
        billCycle.setCreateDate(context.getRequestDate());
        return billCycle;
    }

    /**
     * lijc3 2012-5-19 指定日期删除prodbilling
     */
    public void deleteProdBillingCycleByProdId(Long prodId, Date effectiveDate)
    {
        CoProdBillingCycle cycle = new CoProdBillingCycle();
        cycle.setExpireDate(effectiveDate);
        super.updateByCondition(cycle, effectiveDate, new DBCondition(CoProdBillingCycle.Field.productId, prodId));
    }

    /**
     * @Description:查询产品账期，可能有多个
     * @author caohm5
     * @Date 2012-05-14
     */
    public List<CoProdBillingCycle> queryProdBillingCycles(Long prodId, Long objectId)
    {
        return super.query(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, objectId));
    }

    /**
     * 根据开始时间和指定的账单日，推算正确账单日。<br>
     * 比如当前时间为2011-7-10,dueDay为20,则正确账单日为2011 -7-20,在同一个月; <br>
     * 如果dueDay为05，则正确账单日为2011-8-05,在下一个月
     */
    public Date createFirstBillDate(Date startDate, Integer dueDay, int cycleType, int cycleUnit)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_DAY)
        {// 自然天 下一天00:00
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH)
        {// 自然月,下个月1号00:00
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_WEEK)
        {// 自然周
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_YEAR)
        {// 自然年
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + cycleUnit);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH || cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET)
        {// 动态月
         // 修改动态月出账时间
            calendar.roll(Calendar.MONTH, cycleUnit);
            if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH)
            {
                return DateUtil.dayBegin(calendar.getTime());
            }
            else
            {
                return calendar.getTime();
            }
        }
        return IMSUtil.offsetDate(startDate, cycleType, cycleUnit);
    }
}
