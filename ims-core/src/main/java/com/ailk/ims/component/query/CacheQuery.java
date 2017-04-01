package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.Bindings;
import jef.database.Condition.Operator;
import com.ailk.ims.cache.OfferCacheBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.JavaInvokeLua;
import com.ailk.ims.common.LuaConfig;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.GroupComponent;
import com.ailk.ims.component.helper.ProdCycleHelper;
import com.ailk.ims.component.helper.ProdHelper;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecServiceRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternDef;
import com.ailk.openbilling.persistence.sys.entity.SysElementsVsPolicy;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;
import com.ailk.openbilling.persistence.sys.entity.SysGroupDef;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;
import com.ailk.openbilling.persistence.sys.entity.SysPolicyElements;

/**
 * @Description:缓存相关的信息查询的方法类
 * @author wukl
 * @Date 2012-1-10
 * @Date 2012-5-4 tangjl5 首次激活中账户的balance_type为null
 * @Date 2012-6-11 wuyj 新增queryBusiflag(PmProductOffering offer)
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 */
public class CacheQuery extends BaseComponent
{
    public CacheQuery()
    {

    }
    /**
     * 
     * @author zhangzhj 2012-11-24 上海专用
     * @param offeringId
     * @param valueMap
     * @return
     */
//    public Integer queryPricePlanId4SH(long offeringId, Map valueMap)
//    {
//        OfferCacheBean cacheBean= SpringUtil.getOfferCacheBean();
//        Object mainOfferId = valueMap.get(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID);
//        List<PmProductPricingPlan> plans =  cacheBean.queryOfferPricingPlan((int) offeringId, (Integer) mainOfferId);
//        if (CommonUtil.isEmpty(plans))
//        {
//            imsLogger.error("****** not find PmProductPricingPlan where productOfferingId = ", offeringId);
//            return null;
//        }
//        Bindings bind=LuaConfig.createBindings(valueMap);
//
//        Integer default_planId = null;
//
//        for (PmProductPricingPlan plan : plans)
//        {
//            if (plan.getPolicyId().intValue() == 0)
//            {
//                default_planId = plan.getPricingPlanId();
//                continue;// policy=0是缺省价格计划
//            }
//            String exp_result = LuaConfig.executeLuaScript(plan.getPolicyId(), bind);
//            if ("1".equals(exp_result))
//            {
//                default_planId = plan.getPricingPlanId();
//                break;// 如果计算出一个结果为1，就使用这个加个计划，后面不再继续计算
//            }
//        }
//        return default_planId;
//    }

    public Integer queryPricePlanId(long offeringId, CmCustomer cust, CaAccount account, CmResource user)
    {
        Map<Class, Object> inputMap = new HashMap<Class, Object>();
        inputMap.put(CmCustomer.class, cust);
        inputMap.put(CaAccount.class, account);
	        
        // when an account order an promotion, maybe there are no subscriber.
        if (user != null)
        {
            inputMap.put(CmResource.class, user);
        }
        Map map = ProdHelper.buildPricingPlanLuaMap(inputMap);

        return queryPricePlanId(offeringId, map);
    }

    public Integer queryPricePlanId(long offeringId, Map valueMap)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offeringId);
        List<PmProductPricingPlan> plans = offerBean.getPricePlanList();
        /*
         * List<PmProductPricingPlan> plans = DBConfigInitBean.getCached(PmProductPricingPlan.class, new CacheCondition(
         * PmProductPricingPlan.Field.productOfferingId, offeringId));
         */
        if (CommonUtil.isEmpty(plans))
        {
            imsLogger.error("****** not find PmProductPricingPlan where productOfferingId = " + offeringId);
            return null;
        }

        Integer default_planId = null;
        //@Date 2012-11-17 zhangzj3 判断主产品是否一致
        Integer mainOfferId=(Integer) valueMap.get(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID);
        if(mainOfferId!=null){
            for (PmProductPricingPlan plan : plans)
            {
                if(plan.getMainPromotion().equals(mainOfferId)){
                    return plan.getPricingPlanId();
                }
            }
        }
        for (PmProductPricingPlan plan : plans)
        {
            if (plan.getPolicyId().intValue() == 0)
            {
                default_planId = plan.getPricingPlanId();
                continue;// policy=0是缺省价格计划
            }

            SysPolicy sysPolicy = querySingle(SysPolicy.class, new DBCondition(SysPolicy.Field.policyId, plan.getPolicyId()));
            /*
             * SysPolicy sysPolicy = DBConfigInitBean.getSingleCached(SysPolicy.class, new
             * CacheCondition(SysPolicy.Field.policyId, plan.getPolicyId()));
             */
            if (sysPolicy == null)
            {
                // 这里需要抛出异常 lijc3 2012-08-15
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE,
                        new Object[] { "SYS_POLICY", "policy_id", plan.getPolicyId() });
            }

            List<SysElementsVsPolicy> elementList = DBConfigInitBean.getCached(SysElementsVsPolicy.class, new CacheCondition(
                    SysElementsVsPolicy.Field.policyId, sysPolicy.getPolicyId()));

            boolean flag = false;
            if (CommonUtil.isNotEmpty(elementList))
            {
                for (SysElementsVsPolicy element : elementList)
                {
                    SysPolicyElements sysPolicyElements = DBConfigInitBean.getSingleCached(SysPolicyElements.class,
                            new CacheCondition(SysPolicyElements.Field.elementId, element.getElementId()));

                    if (CommonUtil.isEmpty(sysPolicyElements.getElementKey()))
                        continue;

                    if (sysPolicyElements.getElementKey().equals(ConstantDefine.LUA_PRICEINGPLAN_PROD_FIRST_ORDER))
                    {
                        flag = true;
                        break;
                    }
                }
            }

            // 需要判断首次订购
            if (flag)
            {
                context.getComponent(ProductQuery.class).doFirstOrder(offeringId, valueMap);
            }

            String policyExp = sysPolicy.getPolicyExpr();
            if (CommonUtil.isEmpty(policyExp))
                continue;
            String exp_result = JavaInvokeLua.executeLuaScript(policyExp, valueMap);
            if ("1".equals(exp_result))
            {
                default_planId = plan.getPricingPlanId();
                break;// 如果计算出一个结果为1，就使用这个加个计划，后面不再继续计算
            }

        }
        return default_planId;
    }

    /**
     * 根据销售品Id 查询产品账期 wukl 2012-1-10
     * 
     * @param offeringId
     * @return
     */
    public PmProductOfferLifecycle queryProdOfferLifeCycle(Integer offeringId)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offeringId.longValue());
        return offerBean.getOfferLifeCycle();
    }

    /**
     * 根据销售品ID查询服务 wukl 2012-1-10
     * 
     * @param offeringId
     * @return
     */
    public List<Integer> queryServiceSpecIdListByOfferId(Integer offeringId)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offeringId.longValue());
        List<PmProductSpecServiceRel> serviceList = offerBean.getServiceList();

        if (CommonUtil.isEmpty(serviceList))
            return null;

        List<Integer> resultList = new ArrayList<Integer>();

        for (PmProductSpecServiceRel rel : serviceList)
        {
            resultList.add(rel.getServiceSpecId());
        }
        return resultList;
    }

    public PmProductOfferAttribute queryPmProductOfferAttribute(int offerId)
    {
        return DBConfigInitBean.getSingleCached(PmProductOfferAttribute.class, new CacheCondition(
                PmProductOfferAttribute.Field.productOfferingId, offerId));
    }

    public Integer queryProdOfferingBillingType(Long prodOfferingId)
    {
        PmProductOfferAttribute result = queryPmProductOfferAttribute(prodOfferingId.intValue());
        if (null != result && result.getBillingType() != null)
        {
            return result.getBillingType();
        }
        return null;
    }

    /**
     * 根据销售品ID查询信息 wukl 2012-1-10
     * 
     * @param offeringId
     * @return
     */
    public PmProductOffering queryPmProductOfferingByOfferId(int offeringId)
    {
        PmProductOffering offering = querySingle(PmProductOffering.class, new DBCondition(
                PmProductOffering.Field.productOfferingId, offeringId));
        return offering;
    }

    /**
     * 根据销售品ID，推算出firstBillDate wukl 2012-1-11
     * 
     * @param offeringId
     * @param startDate
     * @return
     */
    public Date getFirstBillDate(int offeringId, Date startDate)
    {
        PmProductOfferLifecycle lifeCycle = this.queryProdOfferLifeCycle(offeringId);
        Date firstBillDate = ProdCycleHelper.createFirstBillDate(startDate, null, lifeCycle.getCycleType(),
                lifeCycle.getCycleUnit());
        return firstBillDate;
    }

    /**
     * DCC流程根据lacId 获取regionCode 用户规则匹配 wukl 2012-1-11
     * 
     * @param lacId
     * @param activeDate
     * @return
     */
    /*
     * public String getRegionCode(String lacId, Date activeDate) { String regionCode = (String)
     * context.getExtendParam(ConstantDefine.ACTIVE_LOCATION); if (CommonUtil.isEmpty(regionCode)) { if (null == activeDate)
     * activeDate = context.getRequestDate(); List<RsSysCellInfo> list = null; if (!CommonUtil.isEmpty(lacId)) { list =
     * DBConfigInitBean.getCached(RsSysCellInfo.class, new CacheCondition(RsSysCellInfo.Field.lacId, lacId), new
     * CacheCondition(RsSysCellInfo.Field.validDate, activeDate, Operator.LESS_EQUALS), new CacheCondition(
     * RsSysCellInfo.Field.expireDate, activeDate, Operator.GREAT_EQUALS)); } if (CommonUtil.isEmpty(list)) return null;
     * context.addExtendParam(ConstantDefine.ACTIVE_LOCATION, list.get(0).getRegionCode().toString()); return
     * list.get(0).getRegionCode().toString(); } return regionCode; }
     */

    /**
     * 根据销售品查询busi_flag wukl wuyj 2012-6-11
     * 
     * @param offeringId
     * @return
     * @throws IMSException
     */
    public int queryBusiflag(PmProductOffering offer)
    {
        int busiFlag = 0;
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offer.getProductOfferingId().longValue());
        PmProductSpecTypeGroups type = offerBean.getSpecTypeGroup();

        if (type == null)
        {
            busiFlag = SpecCodeDefine.NORMAL;
        }
        else
        {
            busiFlag = type.getSpecTypeId();
        }

        return busiFlag;
    }

    /**
     * 根据销售品ID查询busi_flag wukl 2012-1-12
     */
    public int queryBusiflag(int offeringId)
    {
        PmProductOffering offer = this.queryPmProductOfferingByOfferId(offeringId);
        return queryBusiflag(offer);
    }

    /**
     * 获取组GROUP_ID wukl 2012-1-12
     */
    public SysGroupDef queryGroupIDByConditions(CmCustomer cust, CmResource res, CaAccount acct) throws IMSException
    {
        SysGroupDef result = null;

        List<SysGroupDef> tempDef = DBConfigInitBean.getCached(SysGroupDef.class);

        if (CommonUtil.isEmpty(tempDef))
            return null;

        // @Date tangjl5 如果没有用户，则直接使用客户信息获取组信息。如果有用户，则也增加用户信息一起来获取组信息
        if (res == null)
        {
            if (cust == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_NOTEXIST);
            }

            for (SysGroupDef groupDef : tempDef)
            {
                if ((groupDef.getCustomerClass().intValue() == cust.getCustomerClass().intValue() || groupDef.getCustomerClass()
                        .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getRegionCode().intValue() == acct.getRegionCode().intValue() || groupDef.getRegionCode()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getCustomerSegment().intValue() == cust.getCustomerSegment().intValue() || groupDef
                                .getCustomerSegment().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getCustomerType().intValue() == cust.getCustomerType().intValue() || groupDef
                                .getCustomerType().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getAccountType().intValue() == acct.getAccountType().intValue() || groupDef.getAccountType()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                {
                    result = groupDef;
                    break;
                }
            }

        }
        else
        {
            for (SysGroupDef groupDef : tempDef)
            {
                if (groupDef.getResType() == null)
                    continue;

                if (groupDef.getResType().intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                        && (groupDef.getCustomerClass().intValue() == cust.getCustomerClass().intValue() || groupDef
                                .getCustomerClass().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getCustomerSegment().intValue() == cust.getCustomerSegment().intValue() || groupDef
                                .getCustomerSegment().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getCustomerType().intValue() == cust.getCustomerType().intValue() || groupDef
                                .getCustomerType().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getRegionCode().intValue() == acct.getRegionCode().intValue() || groupDef.getRegionCode()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getBillingType().intValue() == res.getBillingType().intValue() || groupDef.getBillingType()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getResSegment().intValue() == res.getResSegment().intValue() || groupDef.getResSegment()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                        && (groupDef.getAccountType().intValue() == acct.getAccountType().intValue() || groupDef.getAccountType()
                                .intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                {
                    result = groupDef;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 获取pattern_id wukl 2012-1-12
     * 
     * @param groupID
     * @param cust
     * @param res
     * @param mainProdOfferingId
     * @return
     */
    public SysGroupCyclePattern queryGrpCycPatternByConditions(CmCustomer cust, CmResource res, CaAccount acct,
            Long mainProdOfferingId)
    {
        boolean isSingleBalance = (res == null || acct != null && acct.getBalanceType() != null
                && acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE) ? true : false;
        GroupComponent group = context.getComponent(GroupComponent.class);
        Long mainProductOfferingId = CommonUtil.string2Long(ConstantDefine.INT_UNIFIED_CONFIGURATION);
        if (CommonUtil.isValid(mainProdOfferingId))
        {
            mainProductOfferingId = mainProdOfferingId;
        }
        else
        {
            // @Date 2012-4-16 On_Site Defect #43689 若是已销户的用户则，则将用户的主产品设置为默认值-1
            if (context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle().getSts() == EnumCodeDefine.LIFECYCLE_TERMINAL)
            {
                mainProductOfferingId = CommonUtil.StringToLong(ConstantDefine.INT_UNIFIED_CONFIGURATION);
            }
            else
            {
                mainProductOfferingId = (long) context.getComponent(BaseProductComponent.class).getUserMainProdOfferingId(
                        res.getResourceId());
            }
        }

        List<SysGroupCyclePattern> result = DBConfigInitBean.getCached(SysGroupCyclePattern.class);
        List<SysGroupCyclePattern> filterRresult = new ArrayList<SysGroupCyclePattern>();
        if (CommonUtil.isNotEmpty(result))
        {
            // @Date 2012-5-4 tangjl5 首次激活中账户的balance_type为null
            // @Date 2014-4-16 tangjl5 销户后的用户没有对应的CmCustomer和CaAccount,则在查询组时使用默认值
            if (cust == null && acct == null)
            {
                for (SysGroupCyclePattern groupPattern : result)
                {
                    if (groupPattern.getResType().intValue() == CommonUtil
                            .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                            && (groupPattern.getCustomerClass().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerSegment().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getRegionCode().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getBillingType().intValue() == res.getBillingType().intValue() || groupPattern
                                    .getBillingType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getResSegment().intValue() == res.getResSegment().intValue() || groupPattern
                                    .getResSegment().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getAccountType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                    {
                        filterRresult.add(groupPattern);
                    }
                }
            }
            // @Date 2012-4-24 tangjl5 若账户是single balance，则账户下的用户的状态需保持一致，查找pattern_id时不使用用户信息
            else if (res != null && acct != null && !isSingleBalance)
            {
                for (SysGroupCyclePattern groupPattern : result)
                {
                    if (groupPattern.getResType().intValue() == CommonUtil
                            .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                            && (groupPattern.getCustomerClass().intValue() == cust.getCustomerClass().intValue() || groupPattern
                                    .getCustomerClass().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerSegment().intValue() == cust.getCustomerSegment().intValue() || groupPattern
                                    .getCustomerSegment().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerType().intValue() == cust.getCustomerType().intValue() || groupPattern
                                    .getCustomerType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getRegionCode().intValue() == acct.getRegionCode().intValue() || groupPattern
                                    .getRegionCode().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getBillingType().intValue() == res.getBillingType().intValue() || groupPattern
                                    .getBillingType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getResSegment().intValue() == res.getResSegment().intValue() || groupPattern
                                    .getResSegment().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getAccountType().intValue() == acct.getAccountType().intValue() || groupPattern
                                    .getAccountType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                    {
                        filterRresult.add(groupPattern);
                    }
                }
            }
            else
            {
                for (SysGroupCyclePattern groupPattern : result)
                {
                    if (groupPattern.getResType().intValue() == CommonUtil
                            .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                            && (groupPattern.getCustomerClass().intValue() == cust.getCustomerClass().intValue() || groupPattern
                                    .getCustomerClass().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerSegment().intValue() == cust.getCustomerSegment().intValue() || groupPattern
                                    .getCustomerSegment().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getCustomerType().intValue() == cust.getCustomerType().intValue() || groupPattern
                                    .getCustomerType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getRegionCode().intValue() == acct.getRegionCode().intValue() || groupPattern
                                    .getRegionCode().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                            && (groupPattern.getAccountType().intValue() == acct.getAccountType().intValue() || groupPattern
                                    .getAccountType().intValue() == CommonUtil
                                    .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                    {
                        filterRresult.add(groupPattern);
                    }
                }
            }
        }

        List<SysGroupCyclePattern> tempPatterns = new ArrayList<SysGroupCyclePattern>();
        if (CommonUtil.isEmpty(filterRresult))
            return null;

        for (Object object : filterRresult)
        {
            SysGroupCyclePattern pattern = (SysGroupCyclePattern) object;
            if (pattern.getMainPromotion().intValue() == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                    || pattern.getMainPromotion().intValue() == mainProductOfferingId.intValue())
            {
                tempPatterns.add(pattern);
            }
        }
        if (CommonUtil.isEmpty(tempPatterns))
        {
            return null;
        }
        else
        {
            List<SysGroupCyclePattern> cyclePatterns = new ArrayList<SysGroupCyclePattern>();
            for (SysGroupCyclePattern cyclePattern : tempPatterns)
            {
                SysPolicy sysPolicy = querySingle(SysPolicy.class,
                        new DBCondition(SysPolicy.Field.policyId, cyclePattern.getPolicyId()));
                /*
                 * SysPolicy sysPolicy = DBConfigInitBean.getSingleCached(SysPolicy.class, new CacheCondition(
                 * SysPolicy.Field.policyId, cyclePattern.getPolicyId()));
                 */

                if (null != sysPolicy)
                {
                    String policyExpr = sysPolicy.getPolicyExpr();

                    // 执行lua脚本，若计算结果为1则，流转成功
                    String luaResult = JavaInvokeLua.executeLuaScript(policyExpr, group.packageLuaParam(cust, res));
                    if (ConstantDefine.LUA_RUN_OK.equals(luaResult))
                    {
                        cyclePatterns.add(cyclePattern);
                    }
                    else
                    {
                        imsLogger.debug("****** patternId[" + cyclePattern.getPatternId() + "],plocyId=: "
                                + cyclePattern.getPolicyId() + ";result=" + luaResult);
                        continue;
                    }
                }
                else
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.POLICY_IS_NULL, cyclePattern.getPolicyId());
                }
            }

            if (cyclePatterns.size() > 0)
            {
                List<Integer> patternIds = new ArrayList<Integer>();
                for (SysGroupCyclePattern sysCyclePattern : cyclePatterns)
                {
                    patternIds.add(sysCyclePattern.getPatternId());
                }

                List<SysCyclePatternDef> patternDefList = query(SysCyclePatternDef.class, new DBCondition(
                        SysCyclePatternDef.Field.patternId, patternIds, Operator.IN));

                if (CommonUtil.isNotEmpty(patternDefList))
                {
                    Map<Integer, Integer> patternIdMap = new HashMap<Integer, Integer>();
                    for (SysCyclePatternDef def : patternDefList)
                    {
                        // @Date 2012-6-14 tangjl5 On_Site Defect #48398 pattern_id的 匹配到使用级
                        if ((isSingleBalance && def.getPatternType() == EnumCodeDefine.CYCLE_PATTERN_DEF_PATTERN_TYPE_ACCOUNT)
                                || (!isSingleBalance && def.getPatternType() == EnumCodeDefine.CYCLE_PATTERN_DEF_PATTERN_TYPE_DEVICE))
                        {
                            patternIdMap.put(def.getPriority(), def.getPatternId());
                        }
                    }

                    if (patternIdMap.size() == 0)
                        return null;

                    Integer maxPriorty = Collections.max(patternIdMap.keySet());
                    Integer maxPriortyPatterId = patternIdMap.get(maxPriorty);

                    for (SysGroupCyclePattern sysCyclePattern : cyclePatterns)
                    {
                        if (sysCyclePattern.getPatternId() == maxPriortyPatterId.intValue())
                        {
                            return sysCyclePattern;
                        }
                    }
                }
                else
                {
                    return null;
                }
            }

            return null;
        }
    }

    /**
     * 获取主产品对应的特征值（11628） wukl 2012-3-1
     * 
     * @param offerId
     * @return
     */
    public String getMainPromoCharValue(Integer offerId)
    {
        Offering3hbean bean = context.get3hTree().loadOffering3hBean(offerId.longValue());
        List<PmProductOfferSpecChar> listSpecChar = bean.getOfferSpecCharList();
        if (CommonUtil.isEmpty(listSpecChar))
            IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NEED_SPEC, offerId, SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628);

        String result = "";
        for (PmProductOfferSpecChar specChar : listSpecChar)
        {
            if (specChar.getSpecCharId().intValue() == SpecCodeDefine.MAIN_PROD_SPEC_CHAR_11628)
            {
                result = specChar.getValue();
                break;
            }
        }

        return result;
    }
}
