package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.DateUtils;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.pd.entity.PmCurveSegments;
import com.ailk.openbilling.persistence.pd.entity.PmProrateDeductRuleDtl;
import com.ailk.openbilling.persistence.pd.entity.PmRates;
import com.ailk.openbilling.persistence.pd.entity.PmRecurringFeeDtl;
import com.ailk.openbilling.persistence.pm.entity.PmAssetItem;
import com.ailk.openbilling.persistence.pm.entity.PmComponentProdofferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeOfferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;

/**
 * @Description: 余额及免费资源相关操作的
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wukl
 * @Date 2011-9-26 2011-10-27 luojb:saccount结构中的company修改为Short类型 2011-11-22 liuyang8 将modifyProdStateBusiBean里的检查余额方法添加到balance主件
 * @Date 2012-4-17 tangjl5 suspend,disable 用户可以查询免费资源
 * @Date 2012-4-19 tangjl5 查询免费资源时传入生效时
 * @Date 2012-04-23 yangjh queryFreeResList返回时间格式修改
 * @Date 2012-05-21 wangdw5 [41732]免费资源查询分组 通过FreeResourceComplex组装MAP
 */
public class BalanceShComponent extends BaseComponent
{

    /**
     * 查询产品的月wangyh3 2012-7-9
     * 
     * @param prodId
     * @return
     */
    public List<SMonthlyFee> queryMonthlyFee(Integer pricePlanId, Date orderDate, double prorateRate)
    {
        imsLogger.debug("#####query product monthly fee begin...#####");

        DBJoinCondition join = new DBJoinCondition(PmCompositeOfferPrice.class);
        join.innerJoin(PmComponentProdofferPrice.class, new DBRelation(PmCompositeOfferPrice.Field.priceId,
                PmComponentProdofferPrice.Field.priceId));
        join.innerJoin(PmRecurringFeeDtl.class, new DBRelation(PmComponentProdofferPrice.Field.priceId,
                PmRecurringFeeDtl.Field.priceId));
        join.innerJoin(PmRates.class, new DBRelation(PmRecurringFeeDtl.Field.rateId, PmRates.Field.rateId));
        join.innerJoin(PmCurveSegments.class, new DBRelation(PmRates.Field.curveId, PmCurveSegments.Field.curveId));
        join.innerJoin(PmPriceEvent.class, new DBRelation(PmRecurringFeeDtl.Field.itemCode, PmPriceEvent.Field.itemId));
        List<Map> result = this.queryJoin(join, new DBCondition(PmCompositeOfferPrice.Field.pricingPlanId, pricePlanId),
                new DBCondition(PmCompositeOfferPrice.Field.offerSts, new Integer[] { -1, 0 }, Operator.IN), new DBCondition(
                        PmCompositeOfferPrice.Field.validDate, orderDate, Operator.LESS_EQUALS), new DBCondition(
                        PmCompositeOfferPrice.Field.expireDate, orderDate, Operator.GREAT), new DBCondition(
                        PmComponentProdofferPrice.Field.priceType, 7));

        imsLogger.debug("#####query product monthly fee finished.#####");

        if (!CommonUtil.isEmpty(result))
        {
            List<SMonthlyFee> monthlyFeeList = new ArrayList<SMonthlyFee>();
            Map<String, SMonthlyFee> tmpMap = new HashMap<String, SMonthlyFee>();
            for (Map item : result)
            {
                PmCompositeOfferPrice pm1 = (PmCompositeOfferPrice) item.get(PmCompositeOfferPrice.class);
                // PmComponentProdofferPrice pm2 = (PmComponentProdofferPrice)
                // item.get(PmComponentProdofferPrice.class);
                PmRecurringFeeDtl pm3 = (PmRecurringFeeDtl) item.get(PmRecurringFeeDtl.class);
                PmRates pm4 = (PmRates) item.get(PmRates.class);
                PmCurveSegments pm5 = (PmCurveSegments) item.get(PmCurveSegments.class);
                PmPriceEvent pm6 = (PmPriceEvent) item.get(PmPriceEvent.class);

                Integer itemCode = pm3.getItemCode();
                String key = CommonUtil.int2String(itemCode);
                SMonthlyFee cacheFee = tmpMap.get(key);

                // 对每个itemcode取最大的baseVal
                if (cacheFee == null || CommonUtil.Long2long(cacheFee.getAmount()) < CommonUtil.Long2long(pm5.getBaseVal()))
                {
                    SMonthlyFee mfee = new SMonthlyFee();
                    mfee.setPrice_id(pm1.getPriceId());
                    mfee.setItem_code(pm3.getItemCode());
                    mfee.setItem_name(pm6.getName());
                    Long amount = pm5.getBaseVal();
                    mfee.setAmount(amount);
                    if (prorateRate > 0)
                    {
                        mfee.setProrated_amount(CommonUtil.double2Long(Math.rint(amount * prorateRate)));
                    }
                    mfee.setMeasure_id(pm4.getMeasureId());

                    // 设置度量单位名称
                    SysMeasure measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                            SysMeasure.Field.measureId, mfee.getMeasure_id()));
                    mfee.setMeasure_name(measure != null ? measure.getDescription() : null);

                    tmpMap.put(key, mfee);
                }
            }

            monthlyFeeList.addAll(tmpMap.values());
            return monthlyFeeList;
        }
        return null;
    }

    /*   *//**
     * 查询免费资源 wangyh3 2012-7-13
     * 
     * @param prorateRate 当月折算
     */
    /*
     * public List<SFreeResource> queryUnOrderFreeResList(Long offerId, Integer pricePlanId, Date orderDate, double prorateRate) {
     * List<SFreeResource> freeResourceList = new ArrayList<SFreeResource>();
     * imsLogger.debug("#####query free resource begin...#####"); DBJoinCondition join = new
     * DBJoinCondition(PmCompositeOfferPrice.class); join.innerJoin(PmComponentProdofferPrice.class, new
     * DBRelation(PmCompositeOfferPrice.Field.priceId, PmComponentProdofferPrice.Field.priceId));
     * join.innerJoin(PmAllowanceFreeresDetails.class, new DBRelation(PmComponentProdofferPrice.Field.priceId,
     * PmAllowanceFreeresDetails.Field.priceId)); join.innerJoin(PmAllowanceFreeresSegment.class, new
     * DBRelation(PmAllowanceFreeresDetails.Field.priceId, PmAllowanceFreeresSegment.Field.priceId));
     * join.innerJoin(PmAssetItem.class, new DBRelation(PmAllowanceFreeresDetails.Field.freeresItem,
     * PmAssetItem.Field.assetItemId)); join.innerJoin(PmFreeUsageProperty.class, new DBRelation(PmAssetItem.Field.assetItemId,
     * PmFreeUsageProperty.Field.assetItemId)); List<Map> result = this.queryJoin(join, new
     * DBCondition(PmCompositeOfferPrice.Field.pricingPlanId, pricePlanId), new DBCondition(PmCompositeOfferPrice.Field.offerSts,
     * new Integer[] { -1, 0 }, Operator.IN), new DBCondition( PmCompositeOfferPrice.Field.validDate, orderDate,
     * Operator.LESS_EQUALS), new DBCondition( PmCompositeOfferPrice.Field.expireDate, orderDate, Operator.GREAT), new
     * DBCondition( PmComponentProdofferPrice.Field.priceType, 3), new DBCondition( PmAllowanceFreeresSegment.Field.startValue,
     * 1)); imsLogger.debug("#####query free resource finished.#####"); if (!CommonUtil.isEmpty(result)) {
     * imsLogger.debug("#####begin to query prorateType 半月这算率#####"); Integer prorateType = queryProrateTypeByOfferId(offerId);
     * imsLogger.debug("#####end to query prorateType 半月这算率.#####"); imsLogger.debug("#####prorateType .#####",prorateType); for
     * (Map item : result) { PmCompositeOfferPrice pm1 = (PmCompositeOfferPrice) item.get(PmCompositeOfferPrice.class); //
     * PmComponentProdofferPrice pm2 = (PmComponentProdofferPrice) // item.get(PmComponentProdofferPrice.class);
     * PmAllowanceFreeresDetails pm3 = (PmAllowanceFreeresDetails) item.get(PmAllowanceFreeresDetails.class);
     * PmAllowanceFreeresSegment pm4 = (PmAllowanceFreeresSegment) item.get(PmAllowanceFreeresSegment.class); PmAssetItem pm5 =
     * (PmAssetItem) item.get(PmAssetItem.class); PmFreeUsageProperty pm6 = (PmFreeUsageProperty)
     * item.get(PmFreeUsageProperty.class); SFreeResource resource = new SFreeResource(); resource.setPrice_id(pm1.getPriceId());
     * resource.setFreeres_item(pm3.getFreeresItem()); resource.setFreeres_name(pm5.getName());
     * resource.setAmount(pm4.getAmount()); resource.setMeasure_id(pm6.getMeasureId()); // 设置度量单位名称 SysMeasure measure =
     * DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition( SysMeasure.Field.measureId,
     * resource.getMeasure_id())); resource.setMeasure_name(measure != null ? measure.getDescription() : null); // 计算订购月折算后资源 Long
     * amount = pm4.getAmount(); resource.setProrated_amount(amount); if (CommonUtil.int2Short(prorateType) == 1) //
     * 1：表示半周期折扣（上半月订购送全部免费资源、下半月订购送一半） { if (prorateRate >= 0.5) resource.setProrated_amount(amount); else
     * resource.setProrated_amount(amount / 2); } else if (CommonUtil.int2Short(prorateType) == 3) // 3:用于中国移动规范的按天折 国内使用) {
     * resource.setProrated_amount(CommonUtil.double2Long(amount * prorateRate)); } freeResourceList.add(resource); } } return
     * freeResourceList; }
     */

    /**
     * 查询免费资源折算规则 wangyh3 2012-7-13
     */
    public Map<Integer, Integer> queryProrateTypeByOfferId(List<Integer> offerIds, Integer mainOfferId)
    {
        DBJoinCondition join = new DBJoinCondition(PmCompositeDeductRule.class);
        join.innerJoin(PmProrateDeductRuleDtl.class, new DBRelation(PmCompositeDeductRule.Field.prorateDeductRuleId,
                PmProrateDeductRuleDtl.Field.prorateDeductRuleId));
        List<Map> result = this.queryJoin(join, new DBCondition(PmCompositeDeductRule.Field.productOfferingId, offerIds,
                Operator.IN), new DBCondition(PmProrateDeductRuleDtl.Field.ruleClass, new Integer[] { 0, 1 }, Operator.IN),
                new DBCondition(PmProrateDeductRuleDtl.Field.effectType, 0));

        if (!CommonUtil.isEmpty(result))
        {
            Map<Integer, Integer> offerProrateMap = new HashMap<Integer, Integer>();
            for (Map item : result)
            {
                PmCompositeDeductRule rule = (PmCompositeDeductRule) item.get(PmCompositeDeductRule.class);
                PmProrateDeductRuleDtl prorate = (PmProrateDeductRuleDtl) item.get(PmProrateDeductRuleDtl.class);
                // 如果有跟主产品匹配的就用匹配的，如果没有用-1匹配。
                if (offerProrateMap.get(rule.getProductOfferingId()) == null)
                {
                    if ((mainOfferId != null && rule.getMainPromotion().equals(mainOfferId)) || rule.getMainPromotion() == -1)
                    {
                        offerProrateMap.put(rule.getProductOfferingId(), prorate.getProrateType());
                    }
                }
                else
                {
                    if ((mainOfferId != null && rule.getMainPromotion().equals(mainOfferId)))
                    {
                        offerProrateMap.put(rule.getProductOfferingId(), prorate.getProrateType());
                    }
                }
            }
            return offerProrateMap;
        }
        return null;
    }

    /**
     * 计算折算 wangyh3 2012-7-13
     */
    public double caculateProrateRate(SBillCycle billCycle, Date orderDate)
    {
        // Billing cycle types:
        // 0-Specified
        // 1-Year
        // 2-Month
        // 3-Week
        // 4-Day
        // 目前只考虑 cycle_type=Month, cycle_unit=1 的情
        int firstDay = billCycle.getFirst_bill_day();
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderDate);
        int dt = cal.get(Calendar.DATE);
        if (firstDay == dt) // 首账日就是订购日
        {
            return 1D;
        }
        else if (firstDay > dt) // 首账日在订购日之
        {
            return ((double) (firstDay - dt)) * 12 / 365;
        }
        else
        // 首账日在订购日之
        {
            int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            return ((double) (max - (dt - firstDay))) * 12 / 365;
        }
    }

    /**
     * 计算折算 wangyh3 2012-7-13
     */
    public int getOrderSumDay(SBillCycle billCycle, Date orderDate)
    {
        int firstDay = billCycle.getFirst_bill_day();
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderDate);
        int dt = cal.get(Calendar.DATE);
        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (firstDay == dt)
        {// 首账日就是订购日
            return 0;
        }
        else if (firstDay > dt)
        {// 首账日在订购日之 后
            return firstDay - dt;
        }
        else
        {// 首账日在订购日之 前
            return max - (dt - firstDay);
        }
    }


    /**
     * 查询账期内的免费资源 wangyh3 2012-7-19
     */
    /*
    private List<FreeResource> queryFreeResource(Long acctId, Long userId, Date cycleBeginDate, Date cycleEndDate,
            Map<Integer, String> nameMap)
    {
        // if (iAbmInterfaceCommonInt == null)
        // iAbmInterfaceCommonInt = new IAbmInterfaceCommonInt();

        List<FreeResource> listFreeRes = new ArrayList<FreeResource>();

        SAbmBalanceQueryUp queryUp = new SAbmBalanceQueryUp();
        queryUp.set_acctId(acctId);
        // queryUp.set_servId(userId);
        
        queryUp.set_ownerId(userId);
        queryUp.set_ownerType((short) EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        
        queryUp.set_tableFlag(24);
        queryUp.set_cycleBegin(Long.parseLong(DateUtil.formatDate(cycleBeginDate, DateUtils.DATE_ZEROTIME_FORMAT2)));
        queryUp.set_cycleEnd(Long.parseLong(DateUtil.formatDate(cycleEndDate, DateUtils.DATE_ZEROTIME_FORMAT2)));
        // CsdlStructObjectHolder<SAbmCommonQueryRet> sAbmCommonQueryRetHolder = new CsdlStructObjectHolder<SAbmCommonQueryRet>(
        // SAbmCommonQueryRet.class);
        SAbmBalanceQueryRet queryRet = new SAbmBalanceQueryRet();
        // CBSErrorMsg cBSErrorMsg = new CBSErrorMsg();
        imsLogger.dumpJsonObject("#####帐处入参", queryUp);
        // 接口调用成功与否标识
        // int ret = -1;
        SpringUtil.getSalClient().get(MdbKVDefine.QUERY_ABM_TABLES, queryUp, queryRet, new QueryAbmTableMapReduce());
        imsLogger.debug("#####帐处abm query_abmTable返回的日志：#####");
        // if (queryRet == null ||queryRet.get_succFlag()!=0)
        // {
        // throw new IMSException(queryRet.get_);
        // }
        // 日志输出
        // imsLogger.debug("query_abmTable -ret is ", ret);
        // 如果接口调用成功
        if (queryRet != null && queryRet.get_succFlag() == 0)
        {
            imsLogger.dumpJsonObject("#####帐处出参", queryRet);
            // SAbmCommonQueryRet sAbmCommonQueryRet = sAbmCommonQueryRetHolder.get();
            imsLogger.debug("#####sFreeResList:周期性免费资 ####");
            CsdlArrayList<SFreeRes> sFreeResList = queryRet.get_freeresList();
            imsLogger.dumpJsonObject("FreeResourceList: ", sFreeResList);

            imsLogger.debug("#####sOtFreeResList:一次性免费资 ####");
            CsdlArrayList<SOtFreeRes> sOtFreeResList = queryRet.get_otFreeresList();
            imsLogger.dumpJsonObject("FreeResourceList: ", sOtFreeResList);

            for (SFreeRes list : sFreeResList)
            {
                FreeResource resource = new FreeResource();
                resource.setAcct_id(list.get_acctId());
                resource.setObject_id(list.get_objectId());
                resource.setObject_type(list.get_objectType());
                resource.setItem_code(list.get_itemCode());
                resource.setMeasure_id(list.get_measureId());
                SysMeasure measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                        SysMeasure.Field.measureId, list.get_measureId()));
                resource.setMeasure_name(measure != null ? measure.getDescription() : null);
                resource.setAmount(list.get_amount());
                resource.setUsed_value(list.get_usedValue());
                resource.setReduce_fee(list.get_amount() - list.get_usedValue());
                resource.setProduct_id(list.get_productId());
                resource.setFreeze_fee(list.get_freezeFee());
                resource.setFreeze_time(long2DateStr(list.get_freezeTime(), DateUtils.DATETIME_FORMAT));
                resource.setValid_date(long2DateStr(list.get_validDate(), DateUtils.DATETIME_FORMAT));
                resource.setExpire_date(long2DateStr(list.get_expireDate(), DateUtils.DATETIME_FORMAT));
                if (nameMap != null && nameMap.containsKey(list.get_itemCode()))
                {
                    resource.setResource_name(nameMap.get(list.get_itemCode()));
                }
                else
                {
                    resource.setResource_name("具体信息到营业厅或拨 0086查询");
                }

                listFreeRes.add(resource);
            }

            for (SOtFreeRes otlist : sOtFreeResList)
            {
                FreeResource resource = new FreeResource();
                resource.setAcct_id(otlist.get_acctId());
                resource.setObject_id(otlist.get_objectId());
                resource.setObject_type(otlist.get_objectType());
                resource.setItem_code(otlist.get_itemCode());
                resource.setMeasure_id(otlist.get_measureId());
                SysMeasure measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                        SysMeasure.Field.measureId, otlist.get_measureId()));
                resource.setMeasure_name(measure != null ? measure.getDescription() : null);
                resource.setAmount(otlist.get_amount());
                resource.setUsed_value(otlist.get_usedValue());
                resource.setReduce_fee(otlist.get_amount() - otlist.get_usedValue());
                resource.setProduct_id(otlist.get_productId());
                resource.setFreeze_fee(otlist.get_freezeFee());
                resource.setFreeze_time(long2DateStr(otlist.get_freezeTime(), DateUtils.DATETIME_FORMAT));
                resource.setValid_date(long2DateStr(otlist.get_validDate(), DateUtils.DATETIME_FORMAT));
                resource.setExpire_date(long2DateStr(otlist.get_expireDate(), DateUtils.DATETIME_FORMAT));
                if (nameMap != null && nameMap.containsKey(otlist.get_itemCode()))
                {
                    resource.setResource_name(nameMap.get(otlist.get_itemCode()));
                }
                else
                {
                    resource.setResource_name("具体信息到营业厅或拨 0086查询");
                }

                listFreeRes.add(resource);
            }
        }
        imsLogger.dumpJsonObject("Return FreeResourceList: ", listFreeRes);

        return listFreeRes;
    }

*/
    /**
     * 查询科目的编 名称对应关系 wangyh3 2012-7-19
     */
    public Map<Integer, String> queryAssetNameMap()
    {
        Map<Integer, String> nameMap = new HashMap<Integer, String>();
        List<PmAssetItem> listPmAssetItem = DBUtil.query(PmAssetItem.class, new DBCondition(PmAssetItem.Field.assetType,
                EnumCodeDefine.ACCOUNT_ASSETTYPE_FREERESOURCE), new DBCondition(PmAssetItem.Field.itemType,
                EnumCodeShDefine.ITEM_TYPE_FREERESOURCE));
        for (PmAssetItem pmItem : listPmAssetItem)
        {
            nameMap.put(pmItem.getAssetItemId(), pmItem.getName());
        }

        return nameMap;
    }


    /**
     * 查询账户下的第一个用
     */
    public Long queryFirstUserIdByAcctId(Long acctId)
    {
        if (null == acctId)
        {
            return null;
        }
        List<CaAccountRes> resList = context.getComponent(BaseComponent.class).query(CaAccountRes.class,
                new OrderCondition[] { new OrderCondition(CaAccountRes.Field.resourceId) }, null,
                new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        if (resList == null || CommonUtil.isEmpty(resList))
        {
            return null;
        }
        CaAccountRes cr = resList.get(0);
        return cr.getResourceId();
    }

 
}