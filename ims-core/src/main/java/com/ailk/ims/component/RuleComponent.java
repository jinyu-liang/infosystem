package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.JavaInvokeLua;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.BiBusiAvaible;
import com.ailk.openbilling.persistence.acct.entity.BiBusiHybridRule;
import com.ailk.openbilling.persistence.acct.entity.BiBusiPlan;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeRulePrice;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveGprsUrl;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveRule;
import com.ailk.openbilling.persistence.pm.entity.PmProdOfferPriceRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;
import com.ailk.openbilling.persistence.sys.entity.SysTimeSegDef;
import com.ailk.openbilling.persistence.sys.entity.SysTimeSegDtl;

/**
 * @Description: 替换产品规则匹配组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wukl
 * @Date 2011-11-22 2012-02-14 wuyujie : changeproduct需要传入add的销售品id列表，在policy配置
 * @Date 2012-7-26 zhangzj3 : getPricePlandId方法增加入参busi_spec_id
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 * @Date 2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
 */
public class RuleComponent extends BaseComponent
{

    public RuleComponent()
    {
    }

    /**
     * 通过用户编号查询定价计划编号 2011-11-22
     * 
     * @param userId
     * @return
     */
    private Integer queryPricePlandIdByAcctId(Long acctId)
    {
        IMS3hBean ims3hBean = context.get3hTree().loadAcct3hBean(acctId);
        CmCustomer custInfo = ims3hBean.getCustomer();
        CaAccount acctInfo = ims3hBean.getAccount();

        // 直接传入当前时间
        Integer pricePlanId = null;
        BiBusiPlan plan = this.queryPricePlandId(null, custInfo, acctInfo, null, null, context.getRequestDate());
        if (plan != null)
            pricePlanId = plan.getPricingPlanId();
        return pricePlanId;
    }

    public Integer queryPricePlandIdByUserId(Long userId)
    {
        BiBusiPlan plan = this.getPricePlandIdByUserId(userId, null, null, context.getRequestDate());
        return plan == null ? null : plan.getPricingPlanId();
    }

    public Integer queryPricePlandIdByUserIdOrAcctId(Long userId, Long acctId, Short level)
    {
        if (level.shortValue() == EnumCodeDefine.ONETIMEFEE_LEVEL_DEV)
        {
            return this.queryPricePlandIdByUserId(userId);
        }
        else
        {
            return this.queryPricePlandIdByAcctId(acctId);
        }
    }

    /**
     * @Description: CRM流程的首次激活根据userId查询变更主产品
     */
    public BiBusiPlan getPricePlandIdByUserId(Long userId, String location, CoProd prodInfo, Date activeDate)
    {

        User3hBean ims3hBean = context.get3hTree().loadUser3hBean(userId);
        CmResource userInfo = ims3hBean.getUser();
        CmCustomer custInfo = ims3hBean.getCustomer();
        CaAccount acctInfo = ims3hBean.getAccount();
        if (CommonUtil.isEmpty(location))
            location = userInfo.getRegionCode().toString();
        Integer busiCode = context.getOper().getBusi_code();
        imsLogger.info("******************* need query plan price id interface busi code :" + busiCode);
        if ((busiCode == EnumCodeDefine.CHANGE_OWNER || busiCode == EnumCodeDefine.CHANGE_MAIN_PROM_BUSI_BEAN || busiCode == EnumCodeDefine.CHANGE_PAYMODE_BUSI_BEAN))
        {
            prodInfo = (CoProd) context.getExtendParam(ConstantDefine.OLD_MAIN_PROM);

            imsLogger.info("************** old main prom  " + prodInfo);

        }
        else if (prodInfo == null)
        {
            prodInfo = context.getComponent(BaseProductComponent.class).queryPrimaryProductByUserId(userId);
        }

        // 直接传入当前时间
        BiBusiPlan plan = this.queryPricePlandId(userInfo, custInfo, acctInfo, prodInfo, location, activeDate);
        return plan;
    }

    /**
     * @param activeDate
     * @param cacheFlag
     * @Description: DCC流程的首次激活根据客户、产品、location信息查询 变更主产品
     * @Date 2012-7-26 zhangzj3 : getPricePlandId方法增加入参busi_spec_id
     * @return
     */
    public BiBusiPlan getPricePlandId(CmResource userInfo, CmCustomer custInfo, CaAccount acctInfo, CoProd prodInfo,
            String location, Date activeDate, Integer busiSpecId)
    {

        return this.queryPricePlandId2Cache(userInfo, custInfo, acctInfo, prodInfo, location, activeDate, true, busiSpecId);
    }

    /**
     * @Description: 变更资费模式中的后付费转预付费，获取变更的主产品（用户原先没有主产品）
     * @param userId
     * @return
     */
    public Integer getChgOfferIdByUserId(Long userId)
    {
        Integer pricePlandId = this.queryPricePlandIdByUserId(userId);

        PmFirstActiveRule rule = this.queryFirstActiveRule(pricePlandId, EnumCodeDefine.CHANGE_MAIN_PRODUCT,
                context.getRequestDate());

        if (rule == null)
            return null;

        PmProductOffering prodOffering = context.getComponent(BaseProductComponent.class).queryProdOffering(rule.getDestOfferingId());
        if (prodOffering == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, rule.getDestOfferingId());

        if (prodOffering.getIsMain() != EnumCodeDefine.PRODUCT_MAIN)
            IMSUtil.throwBusiException(ErrorCodeDefine.OFFER_NOT_MAIN_OFFERING, rule.getDestOfferingId());

        return rule.getDestOfferingId();
    }

    /**
     * @Description: 根据userid查询充值送有效期的priceRuleId
     * @param userId
     * @return
     */
    public Integer getPriceRuleId4ChgValidDateByUserId(Long userId)
    {
        Integer pricePlandId = this.queryPricePlandIdByUserId(userId);

        Integer priceRuleId = this.getPriceRuleId(pricePlandId, EnumCodeDefine.GIVE_MORE_VALID_DATE_WHEN_TOP_UP,
                context.getRequestDate());

        return priceRuleId;
    }

    /**
     * @Description: 查询默认最大的充值送有效期对应的priceRuleId
     * @return
     */
    public Integer getDefaultPriceRuleId4ChgValidDate()
    {
        Integer defaultValue = CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION);
        CmResource userInfo = new CmResource();
        userInfo.setRegionCode(defaultValue);
        userInfo.setBillingType(defaultValue);
        userInfo.setResourceSpecId(defaultValue);

        CmCustomer custInfo = new CmCustomer();
        custInfo.setCustomerClass(defaultValue);
        custInfo.setCustomerSegment(defaultValue);
        custInfo.setCustomerType(defaultValue);

        CaAccount acctInfo = new CaAccount();
        acctInfo.setAccountType(defaultValue);
        acctInfo.setAccountSegment(defaultValue);
        acctInfo.setRegionCode(defaultValue);

        CoProd prodInfo = new CoProd();
        prodInfo.setProductOfferingId(defaultValue);

        String location = ConstantDefine.STRING_UNIFIED_CONFIGURATION;
        Integer pricePlandId = null;
        BiBusiPlan plan = this.queryPricePlandId(userInfo, custInfo, acctInfo, prodInfo, location, context.getRequestDate());
        if (plan != null)
            pricePlandId = plan.getPricingPlanId();
        Integer priceRuleId = this.getPriceRuleId(pricePlandId, EnumCodeDefine.GIVE_MORE_VALID_DATE_WHEN_TOP_UP,
                context.getRequestDate());

        return priceRuleId;
    }

    /**
     * @Description: 根据pricePlanId获取变更主产品
     * @param pricePlanId
     * @param queryDate
     * @return
     */
    public PmFirstActiveRule getChgPromRule2Cache(Integer priceRuleId, Date queryDate)
    {
        PmFirstActiveRule rule = this.queryFirstActiveRule2Cache(priceRuleId, queryDate);

        if (rule == null)
            return null;

        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(rule.getDestOfferingId().longValue());
        PmProductOffering prodOffering = offerBean.getOffering();
        if (prodOffering == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, rule.getDestOfferingId());

        if (prodOffering.getIsMain() != EnumCodeDefine.PRODUCT_MAIN)
            IMSUtil.throwBusiException(ErrorCodeDefine.OFFER_NOT_MAIN_OFFERING, rule.getDestOfferingId());

        // 2012-02-10 变更的主产品不允许为pre_match产品
        short busiFlag = (short) context.getComponent(CacheQuery.class).queryBusiflag(rule.getDestOfferingId().intValue());
        if (busiFlag == SpecCodeDefine.PREMATCH)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.TARGET_MAIN_OFFERING_IS_PRE_MATCH, rule.getDestOfferingId());
        }

        return rule;
    }

    public PmFirstActiveRule getChgPromRule(Integer pricePlanId, Date queryDate)
    {
        PmFirstActiveRule rule = this.queryFirstActiveRule(pricePlanId, EnumCodeDefine.CHANGE_MAIN_PRODUCT, queryDate);

        if (rule == null)
            return null;

        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(rule.getDestOfferingId().longValue());
        PmProductOffering prodOffering = offerBean.getOffering();
        if (prodOffering == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, rule.getDestOfferingId());

        if (prodOffering.getIsMain() != EnumCodeDefine.PRODUCT_MAIN)
            IMSUtil.throwBusiException(ErrorCodeDefine.OFFER_NOT_MAIN_OFFERING, rule.getDestOfferingId());

        // 2012-02-10 变更的主产品不允许为pre_match产品
        int busiFlag = context.getComponent(ProductQuery.class).queryBusiflag(rule.getDestOfferingId());
        if (busiFlag == SpecCodeDefine.PREMATCH)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.TARGET_MAIN_OFFERING_IS_PRE_MATCH, rule.getDestOfferingId());
        }

        return rule;
    }

    private BiBusiPlan queryPricePlandId(CmResource userInfo, CmCustomer custInfo, CaAccount acctInfo, CoProd prodInfo,
            String location, Date queryDate)
    {
        return queryPricePlandId2Cache(userInfo, custInfo, acctInfo, prodInfo, location, queryDate, false, null);
    }

    private BiBusiPlan queryPricePlandId2Cache(CmResource userInfo, CmCustomer custInfo, CaAccount acctInfo, CoProd prodInfo,
            String location, Date queryDate, boolean cacheFlag, Integer busiSpecId)
    {
        // activeChannel == so_mode
        Integer activeChannel = context.getOper().getSo_mode().intValue();
        Map ruleParam = this.buildChangePromRuleMap(userInfo, custInfo, prodInfo, activeChannel, location, queryDate);

        // 2012-02-14 wuyujie : changeproduct需要传入add的销售品id列表，在policy配置
        List<Long> offerIdList = (List<Long>) context.getExtendParam(ConstantDefine.CHGPROD_FOROTF_OFFERIDLIST);
        if (CommonUtil.isNotEmpty(offerIdList))
        {
            ruleParam.put(ConstantDefine.CHGPROD_FOROTF_PLOCY_OFFERIDS, offerIdList.toArray(new Long[offerIdList.size()]));
        }

        Integer pricingPlanId = null;

        // 对于 账户级匹配时，brandID取通配
        int brandId = -1;
        if (userInfo != null)
        {
            brandId = userInfo.getBrandId();
        }
        BiBusiPlan plan = null;
        /**
         * @Date 2012-05-28 wangdw5 [46173]首次激活根据company发送不同的告警 BiBusiAvaible查询增加operator条件
         */
        if (cacheFlag)
        {
            plan = this.getPricingPlanId2Cache(userInfo, custInfo, acctInfo, brandId, prodInfo, queryDate, busiSpecId);
        }
        else
        {
            plan = this.getPricingPlanId(userInfo, custInfo, acctInfo, brandId, prodInfo, queryDate, ruleParam);

        }
        return plan;
    }

    /**
     * @Description: 根据传入的 pricingPlanId及priceRuleType 查询对应的rule
     * @param pricingPlanId
     * @param priceRuleType
     * @return
     */
    private PmFirstActiveRule queryFirstActiveRule2Cache(Integer priceRuleId, Date queryDate)
    {
        PmFirstActiveRule rule = DBConfigInitBean.getSingleCached(PmFirstActiveRule.class, new CacheCondition(
                PmFirstActiveRule.Field.priceRuleId, priceRuleId));

        if (rule == null)
        {
            imsLogger.info("****** not find change main promotion in table [ PM_FIRST_ACTIVE_RULE ] with price_rule_id = [ "
                    + priceRuleId + " ]");
            return null;
        }

        // 2011-12-31 wukl 增加时间策略判断
        boolean timeFlag = checkTimeSeg(rule.getSegId(), queryDate, true);
        if (!timeFlag)
            return null;

        return rule;
    }

    /**
     * 表PmProdOfferPriceRule缓存时，已经加了priceRuleType=4的条件，除首次激活外其它业务采用缓存，则需要去掉该条件 wukl 2012-3-9
     * 
     * @param pricingPlanId
     * @param priceRuleType
     * @param queryDate
     * @return
     */
    public Integer getPriceRuleId2Cache(Integer pricingPlanId, Integer priceRuleType, Date queryDate)
    {
        if (pricingPlanId == null)
            return null;

        List<PmCompositeRulePrice> tempPmCompositeRulePrices = DBConfigInitBean.getCached(PmCompositeRulePrice.class,
                new CacheCondition(PmCompositeRulePrice.Field.pricingPlanId, pricingPlanId), new CacheCondition(
                        PmCompositeRulePrice.Field.validDate, queryDate, Operator.LESS_EQUALS), new CacheCondition(
                        PmCompositeRulePrice.Field.expireDate, queryDate, Operator.GREAT_EQUALS));
        if (CommonUtil.isEmpty(tempPmCompositeRulePrices))
            return null;

        PmCompositeRulePrice rulePrice = null;
        for (PmCompositeRulePrice price : tempPmCompositeRulePrices)
        {
            PmProdOfferPriceRule priceRule = DBConfigInitBean.getSingleCached(PmProdOfferPriceRule.class, new CacheCondition(
                    PmCompositeRulePrice.Field.priceRuleId, price.getPriceRuleId()));
            if (priceRule != null && priceRule.getPriceRuleType().intValue() == priceRuleType.intValue())
            {
                rulePrice = price;
                break;
            }
        }

        /*
         * PmCompositeRulePrice rulePrice = null; for (PmCompositeRulePrice price : tempPmCompositeRulePrices) { for
         * (PmProdOfferPriceRule rule: pmProdOfferPriceRules){ if (price.getPriceRuleId().intValue() ==
         * rule.getPriceRuleId().intValue()){ rulePrice = price; break; } } if (rulePrice != null) break; }
         */

        if (rulePrice == null)
        {
            imsLogger.info("****** not find price_rule_id in table [ PM_PROD_OFFER_PRICE_RULE ] with price_rule_type = [ "
                    + priceRuleType + " ] and price_plan_id = [ " + pricingPlanId + " ]");
            return null;
        }

        return rulePrice.getPriceRuleId();
    }

    private PmFirstActiveRule queryFirstActiveRule(Integer pricingPlanId, Integer priceRuleType, Date queryDate)
    {
        if (pricingPlanId == null)
            return null;

        // 匹配变更主产品的PriceRuleId
        Integer priceRuleId = this.getPriceRuleId(pricingPlanId, priceRuleType, queryDate);

        if (priceRuleId == null)
            return null;

        PmFirstActiveRule rule = querySingle(PmFirstActiveRule.class, new DBCondition(PmFirstActiveRule.Field.priceRuleId,
                priceRuleId));

        if (rule == null)
            return null;

        // 2011-12-31 wukl 增加时间策略判断
        boolean timeFlag = checkTimeSeg(rule.getSegId(), queryDate, false);
        if (!timeFlag)
            return null;

        return rule;
    }

    private Integer getPriceRuleId(Integer pricingPlanId, Integer priceRuleType, Date queryDate)
    {
        DBJoinCondition leftJoin = new DBJoinCondition(PmCompositeRulePrice.class);
        leftJoin.leftJoin(PmProdOfferPriceRule.class, new DBRelation(PmCompositeRulePrice.Field.priceRuleId,
                PmProdOfferPriceRule.Field.priceRuleId));

        List<Map> resultMap = queryJoin(leftJoin, new OrderCondition[] { new OrderCondition(false,
                PmCompositeRulePrice.Field.validDate) }, null, new DBCondition(PmCompositeRulePrice.Field.pricingPlanId,
                pricingPlanId), new DBCondition(PmProdOfferPriceRule.Field.priceRuleType, priceRuleType), new DBCondition(
                PmCompositeRulePrice.Field.validDate, queryDate, Operator.LESS_EQUALS), new DBCondition(
                PmCompositeRulePrice.Field.expireDate, queryDate, Operator.GREAT_EQUALS));

        PmCompositeRulePrice rulePrice = null;
        if (!CommonUtil.isEmpty(resultMap))
        {
            for (Map itemMap : resultMap)
            {
                PmCompositeRulePrice item = (PmCompositeRulePrice) itemMap.get(PmCompositeRulePrice.class);
                if (null == item)
                    continue;

                rulePrice = item;
                break;
            }
        }

        if (rulePrice == null)
        {
            imsLogger.info("****** not find price_rule_id in table [ PM_PROD_OFFER_PRICE_RULE ] with price_rule_type = [ "
                    + priceRuleType + " ]");
            return null;
        }

        return rulePrice.getPriceRuleId();
    }

    private BiBusiPlan getPricingPlanId2Cache(CmResource userInfo, CmCustomer custInfo, CaAccount acctInfo, Integer brandId,
            CoProd prodInfo, Date activeDate, Integer busiSpecId)
    {
        // 此处设置通配符为默认值，是因为后付费转预付费时，用户原来没有主产品
        Integer mainProdOfferId = CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION);
        if (prodInfo != null)
            mainProdOfferId = prodInfo.getProductOfferingId();

        if (ConstantDefine.INT_UNIFIED_CONFIGURATION.equals(mainProdOfferId.toString()))
            imsLogger.debug("****** match change main promotion for change paymode");

        // @Date 2012-7-26 zhangzj3 : getPricePlandId方法增加入参busi_spec_id
        if (busiSpecId == null)
        {
            busiSpecId = context.getBusiSpecId(true);
        }
//        Integer pricingPlanId = null;
        List<BiBusiPlan> tempPlanList = DBConfigInitBean.getCached(BiBusiPlan.class, new CacheCondition(
                BiBusiPlan.Field.busiSpecId, busiSpecId));
        // yanchuan 首次使用服务时，增加对triggerServiceId字段的匹配 2012-02-23
        BiBusiPlan plan = null;
        if (CommonUtil.isNotEmpty(tempPlanList))
        {
            List<Integer> busiPlanIdList = new ArrayList<Integer>();
            for (BiBusiPlan busiPlan : tempPlanList)
            {
                if (!busiPlan.getExpireDate().after(activeDate))
                {// 判断该记录是否还有效
                    continue;
                }
                List<BiBusiAvaible> tempAvaibles = DBConfigInitBean.getCached(BiBusiAvaible.class, 
                        new CacheCondition(BiBusiAvaible.Field.busiPlanId, busiPlan.getBusiPlanId()),
                        new CacheCondition(BiBusiAvaible.Field.balanceType, acctInfo.getBalanceType()));
                
                List<BiBusiAvaible> tempAvaibles2 = DBConfigInitBean.getCached(BiBusiAvaible.class, 
                        new CacheCondition(BiBusiAvaible.Field.busiPlanId, busiPlan.getBusiPlanId()),
                        new CacheCondition(BiBusiAvaible.Field.balanceType, ConstantDefine.INT_UNIFIED_CONFIGURATION));
                
                tempAvaibles = IMSUtil.mergeList(tempAvaibles,tempAvaibles2);
                
                if (tempAvaibles == null)
                {
                    continue;
                }
                
                for (BiBusiAvaible tempAvaible : tempAvaibles)
                {
                    boolean flag = false;
                    if (userInfo != null)
                    {
                        if (tempAvaible.getResType().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                                && (tempAvaible.getCustomerClass().intValue() == custInfo.getCustomerClass() || tempAvaible
                                        .getCustomerClass().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getCustomerSegment().intValue() == custInfo.getCustomerSegment() || tempAvaible
                                        .getCustomerSegment().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getCustomerType().intValue() == custInfo.getCustomerType() || tempAvaible
                                        .getCustomerType().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getRegionCode().intValue() == acctInfo.getRegionCode() || tempAvaible
                                        .getRegionCode().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getBillingType().intValue() == userInfo.getBillingType() || tempAvaible
                                        .getBillingType().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getResSegment().intValue() == userInfo.getResSegment() || tempAvaible
                                        .getResSegment().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getAccountType().intValue() == acctInfo.getAccountType() || tempAvaible
                                        .getAccountType().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getBrandId().intValue() == brandId || tempAvaible.getBrandId().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getMainPromotion().intValue() == mainProdOfferId.intValue() || tempAvaible
                                        .getMainPromotion().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getOperatorId().intValue() == acctInfo.getOperatorId() || tempAvaible
                                        .getOperatorId().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                        {
                            flag = true;
                        }
                    }
                    else
                    {
                        if (tempAvaible.getResType().intValue() == CommonUtil
                                .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)
                                && (tempAvaible.getCustomerClass().intValue() == custInfo.getCustomerClass() || tempAvaible
                                        .getCustomerClass().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getCustomerSegment().intValue() == custInfo.getCustomerSegment() || tempAvaible
                                        .getCustomerSegment().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getCustomerType().intValue() == custInfo.getCustomerType() || tempAvaible
                                        .getCustomerType().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getRegionCode().intValue() == acctInfo.getRegionCode() || tempAvaible
                                        .getRegionCode().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getAccountType().intValue() == acctInfo.getAccountType() || tempAvaible
                                        .getAccountType().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getBrandId().intValue() == brandId || tempAvaible.getBrandId().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getMainPromotion().intValue() == mainProdOfferId.intValue() || tempAvaible
                                        .getMainPromotion().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
                                && (tempAvaible.getOperatorId().intValue() == acctInfo.getOperatorId() || tempAvaible
                                        .getOperatorId().intValue() == CommonUtil
                                        .string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION)))
                        {
                            flag = true;
                        }
                    }
                    if (flag == true)
                    {
                        // yanchuan 首次使用服务时，增加对triggerServiceId字段的匹配 2012-02-23
                        if (busiSpecId == EnumCodeDefine.CHG_RESSER_CYCLE_SPEC_ID)
                        {
                            if (context.getExtendParam(ConstantDefine.SERVICE_SPEC_ID) != null
                                    && tempAvaible.getTriggerServiceId() != null
                                    && (Integer) context.getExtendParam(ConstantDefine.SERVICE_SPEC_ID) == tempAvaible
                                            .getTriggerServiceId().intValue())
                            {
                                busiPlanIdList.add(tempAvaible.getBusiPlanId());
                            }
                            else
                                continue;
                        }
                        else
                            busiPlanIdList.add(tempAvaible.getBusiPlanId());
                    }
                }
            }

            Map<Integer, BiBusiPlan> pricingIdMap = new HashMap<Integer, BiBusiPlan>();
            if (CommonUtil.isNotEmpty(busiPlanIdList))
            {
                for (Integer busiPlanId : busiPlanIdList)
                {
                    for (BiBusiPlan busiPlan : tempPlanList)
                    {
                        if (busiPlan.getBusiPlanId().intValue() == busiPlanId)
                        {
                            pricingIdMap.put(busiPlan.getPriority(), busiPlan);
                        }
                    }
                }
            }

            if (CommonUtil.isNotEmpty(pricingIdMap))
            {
                Set<Integer> priortySet = pricingIdMap.keySet();
                plan = pricingIdMap.get(Collections.max(priortySet));
//                pricingPlanId = plan.getPricingPlanId();
                imsLogger.info("****** RULE: [BUSI_PLAN_ID = " + plan.getBusiPlanId() + " ] [PRICING_PLAN_ID = "
                        + plan.getPricingPlanId() + " ] [BUSI_SPEC_ID = " + plan.getBusiSpecId() + "] [PRIORITY = "
                        + plan.getPriority() + " ]");
            }
        }

        return plan;
    }

    private BiBusiPlan getPricingPlanId(CmResource res, CmCustomer cust, CaAccount acct, Integer brandId, CoProd prodInfo,
            Date activeDate, Map ruleParam)
    {
        // 此处设置通配符为默认值，是因为后付费转预付费时，用户原来没有主产品
        Integer mainProdOfferId = CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION);
        if (prodInfo != null)
            mainProdOfferId = prodInfo.getProductOfferingId();

        if (ConstantDefine.INT_UNIFIED_CONFIGURATION.equals(mainProdOfferId.toString()))
            imsLogger.debug("****** match change main promotion for change paymode");

        Integer busiSpecId = context.getBusiSpecId(true);

        DBJoinCondition innerJoin = new DBJoinCondition(BiBusiAvaible.class);
        innerJoin.innerJoin(BiBusiPlan.class, new DBRelation(BiBusiAvaible.Field.busiPlanId, BiBusiPlan.Field.busiPlanId));
        List<Map> resultMap = null;
        if (res != null)
        {
            resultMap = queryJoin(innerJoin, new OrderCondition[] { new OrderCondition(false, BiBusiPlan.Field.priority) }, null,
                    new DBCondition(BiBusiPlan.Field.busiSpecId, busiSpecId), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.brandId, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.brandId, brandId)), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.mainPromotion, mainProdOfferId)), new DBCondition(BiBusiPlan.Field.validDate,
                            activeDate, Operator.LESS_EQUALS), new DBCondition(BiBusiPlan.Field.expireDate, activeDate,
                            Operator.GREAT_EQUALS), new DBCondition(BiBusiAvaible.Field.resType,
                            ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.regionCode, acct.getRegionCode())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.billingType, res.getBillingType())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.accountType, acct.getAccountType())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.resSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.resSegment, res.getResSegment())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerType, cust.getCustomerType())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerClass, cust.getCustomerClass())),new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.balanceType, acct.getBalanceType()),new DBCondition(
                            BiBusiAvaible.Field.balanceType, ConstantDefine.INT_UNIFIED_CONFIGURATION)), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.operatorId, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.operatorId, acct.getOperatorId())));
        }
        else
        {
            resultMap = queryJoin(innerJoin, new OrderCondition[] { new OrderCondition(false, BiBusiPlan.Field.priority) }, null,
                    new DBCondition(BiBusiPlan.Field.busiSpecId, busiSpecId), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.brandId, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.brandId, brandId)), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.mainPromotion, mainProdOfferId)), new DBCondition(BiBusiPlan.Field.validDate,
                            activeDate, Operator.LESS_EQUALS), new DBCondition(BiBusiPlan.Field.expireDate, activeDate,
                            Operator.GREAT_EQUALS), new DBCondition(BiBusiAvaible.Field.resType,
                            ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.regionCode, acct.getRegionCode())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.accountType, acct.getAccountType())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerType, cust.getCustomerType())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.customerClass, cust.getCustomerClass())), new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.balanceType, acct.getBalanceType()),new DBCondition(
                            BiBusiAvaible.Field.balanceType, ConstantDefine.INT_UNIFIED_CONFIGURATION)),new DBOrCondition(new DBCondition(
                            BiBusiAvaible.Field.operatorId, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                            BiBusiAvaible.Field.operatorId, acct.getOperatorId())));
        }

        BiBusiPlan biBusiPlan = null;

        if (!CommonUtil.isEmpty(resultMap))
        {
            for (Map itemMap : resultMap)
            {
                BiBusiAvaible item = (BiBusiAvaible) itemMap.get(BiBusiAvaible.class);
                BiBusiPlan itemPlan = (BiBusiPlan) itemMap.get(BiBusiPlan.class);
                if (null == item)
                    continue;
                //2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
                //@Date 2012-10-22 yugb :61088 皇室成员不收取一次性费用
                if(item.getPolicyId() != 0){
                	//input special account into the ruleParam
                	if(acct != null && acct.getSpecialAccount() != null){
                		ruleParam.put("SPECIAL_ACCOUNT", acct.getSpecialAccount());
                	}
                }

                if (ConstantDefine.LUA_RUN_OK.equals(getPolicyResult(item.getPolicyId(), ruleParam)))
                {
                    // yanchuan 首次使用服务时，增加对triggerServiceId字段的匹配 2012-02-23
                    if (busiSpecId == EnumCodeDefine.CHG_RESSER_CYCLE_SPEC_ID)
                    {
                        if (context.getExtendParam(ConstantDefine.SERVICE_SPEC_ID) != null
                                && item.getTriggerServiceId() != null
                                && (Integer) context.getExtendParam(ConstantDefine.SERVICE_SPEC_ID) == item.getTriggerServiceId()
                                        .intValue())
                        {
                            biBusiPlan = itemPlan;
                            break;
                        }
                        else
                            continue;
                    }
                    biBusiPlan = itemPlan;
                    break;
                }

            }
        }

        if (biBusiPlan == null)
            return null;

        imsLogger.info("****** RULE: [BUSI_PLAN_ID = " + biBusiPlan.getBusiPlanId() + " ] [PRICING_PLAN_ID = "
                + biBusiPlan.getPricingPlanId() + " ] [BUSI_SPEC_ID = " + biBusiPlan.getBusiSpecId() + "] [PRIORITY = "
                + biBusiPlan.getPriority() + " ]");

        return biBusiPlan;
    }

    /**
     * @Description: 根据表中的region_code,customerSegment,customerType字段筛选出组列表，再根据组列表中的优先级字段（Priority），获取优先级最高的groupId
     * @param sUser
     * @param sCustomer
     * @return
     */
    // private SysGroupDef querySysGroupDef(CmResource userInfo, CmCustomer custInfo, CaAccount acctInfo)
    // {
    // // 按Priority 倒序查出记录
    // List<SysGroupDef> groups = query(SysGroupDef.class, new OrderCondition[] { new OrderCondition(false,
    // SysGroupDef.Field.priority) }, null, new DBOrCondition(new DBCondition(SysGroupDef.Field.regionCode,
    // ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.regionCode, userInfo.getRegionCode())),
    // /*
    // * new DBOrCondition( new DBCondition(SysGroupDef.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION), new
    // * DBCondition(SysGroupDef.Field.customerClass, custInfo.getCustomerClass())),
    // */
    // new DBOrCondition(new DBCondition(SysGroupDef.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerSegment, custInfo.getCustomerSegment())),
    // /*
    // * new DBOrCondition( new DBCondition(SysGroupDef.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new
    // * DBCondition(SysGroupDef.Field.accountType, acctInfo.getAccountType())), new DBOrCondition( new
    // * DBCondition(SysGroupDef.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new
    // * DBCondition(SysGroupDef.Field.billingType, userInfo.getBillingType())), new DBOrCondition( new
    // * DBCondition(SysGroupDef.Field.resType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new
    // * DBCondition(SysGroupDef.Field.resType, userInfo.getResourceSpecId())),
    // */
    // new DBOrCondition(new DBCondition(SysGroupDef.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerType, custInfo.getCustomerType())));
    //
    // if (CommonUtil.isEmpty(groups))
    // return null;
    //
    // // 返回Priority最高的一条记录
    // return groups.get(0);
    // }

    private Map buildChangePromRuleMap(CmResource userInfo, CmCustomer custInfo, CoProd prodInfo, Integer activeChannel,
            String location, Date date)
    {
        Map map = new HashMap();

        // map.put(ConstantDefine.LUA_FIRSTACT_ACT_DATE, ConvertUtil.javaDate2SdlLong(date));
        if (location != null)
            map.put(ConstantDefine.LUA_FIRSTACT_ACT_LOCATION, location);
        // if (userInfo != null)
        // map.put(ConstantDefine.LUA_FIRSTACT_USER_SEGMENT, userInfo.getResSegment());
        if (activeChannel != null)
            map.put(ConstantDefine.LUA_FIRSTACT_ACT_CHANNEL, activeChannel);
        return map;
    }

    // private BiBusiAvaible queryBiBusiAvaible(Integer groupId, SProdInfo prodInfo, Map ruleParam)
    // {
    // List<BiBusiAvaible> list = null;
    //
    // // 变更产品资费模式后付费用户没有主产品
    // if (prodInfo != null)
    // {
    // Integer mainProdOfferId = prodInfo.get_promOfferId();
    //
    // list = query(BiBusiAvaible.class, new DBCondition(BiBusiAvaible.Field.groupId, groupId), new DBOrCondition(
    // new DBCondition(BiBusiAvaible.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(BiBusiAvaible.Field.mainPromotion, mainProdOfferId)));
    // }
    // else
    // {
    //
    // list = query(BiBusiAvaible.class, new DBCondition(BiBusiAvaible.Field.groupId, groupId), new DBCondition(
    // BiBusiAvaible.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION));
    // }
    //
    // if (CommonUtil.isEmpty(list))
    // return null;
    //
    // // 按优先级从高到低进行匹配
    // BiBusiAvaible busiAvaible = null;
    // for (BiBusiAvaible value : list)
    // {
    // if (ConstantDefine.LUA_RUN_OK.equals(getPolicyResult(value.getPolicyId(), ruleParam)))
    // {
    // busiAvaible = value;
    // break;
    // }
    // }
    //
    // return busiAvaible;
    // }

    // private String getPolicyResult2Cache(Integer policyId, Map ruleParam)
    // {
    // SysPolicyParser sysPolicyParser = (SysPolicyParser) DBConfigInitBean.getDBCacheParser(SysPolicy.class);
    // SysPolicy sysPolicy = sysPolicyParser.getSingleSysPolicy(policyId);
    // // SysPolicy sysPolicy = DBConfigInitBean.getSingleCached(SysPolicy.class, new CacheCondition(SysPolicy.Field.policyId,
    // // policyId));
    //
    // if (sysPolicy == null)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.POLICY_IS_NULL, policyId);
    // }
    //
    // String policyExp = sysPolicy.getPolicyExpr();
    // String exp_result = JavaInvokeLua.executeLuaScript(policyExp, ruleParam);
    //
    // return exp_result;
    // }

    private String getPolicyResult(Integer policyId, Map ruleParam)
    {
        SysPolicy sysPolicy = (SysPolicy) querySingle(SysPolicy.class, new DBCondition(SysPolicy.Field.policyId, policyId));

        if (sysPolicy == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.POLICY_IS_NULL, policyId);
        }

        String policyExp = sysPolicy.getPolicyExpr();
        String exp_result = JavaInvokeLua.executeLuaScript(policyExp, ruleParam);

        return exp_result;
    }

    /**
     * @Description: 根据userid查询替换主产品的priceRuleId
     * @param userId
     * @return lijc
     */
    public Integer getPriceRuleId4ByReplaceMainProduct(Long userId, CoProd mainProd)
    {
        BiBusiPlan plan = this.getPricePlandIdByUserId(userId, null, mainProd, context.getRequestDate());
        if (plan == null)
        {
            imsLogger.info("************PRICE_PLAN_ID is null");
            return null;
        }
        // 该值可能为空
        Integer priceRuleId = this.getPriceRuleId(plan.getPricingPlanId(), EnumCodeDefine.REPLACE_MAIN_PRODUCT,
                context.getRequestDate());

        return priceRuleId;
    }

    /**
     * 当传入的月、日、时、分、秒为一位数时，前面补0 wukl 2011-12-31
     * 
     * @param obj
     * @return
     */
    private String getTwoDigits(int obj)
    {
        String str = CommonUtil.int2String(obj);
        return obj < 10 ? "0" + str : str;
    }

    /**
     * 判断时间策略 wukl 2011-12-31
     * 
     * @param segId
     * @param queryDate
     * @param cacheFlag
     * @return
     */
    private boolean checkTimeSeg(Integer segId, Date queryDate, boolean cacheFlag)
    {
        SysTimeSegDef sysTimeSegDef = null;
        if (cacheFlag)
        {
            sysTimeSegDef = DBConfigInitBean.getSingleCached(SysTimeSegDef.class, new CacheCondition(SysTimeSegDef.Field.segId,
                    segId));
        }
        else
        {
            sysTimeSegDef = querySingle(SysTimeSegDef.class, new DBCondition(SysTimeSegDef.Field.segId, segId));

        }
        if (sysTimeSegDef == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_SYSTIMESEGDEF, "SYS_TIME_SEG_DEF", segId);
        }

        int timeMode = sysTimeSegDef.getTimeMode().intValue();
        if (timeMode == EnumCodeDefine.TIME_MODE_NOT_TIME)
        {
            imsLogger.info("****** segId =" + segId + " is not time mode");
            return true;
        }

        List<SysTimeSegDtl> listSysTimeSegDtl = null;
        if (cacheFlag)
        {
            listSysTimeSegDtl = DBConfigInitBean.getCached(SysTimeSegDtl.class, new CacheCondition(SysTimeSegDtl.Field.segId,
                    segId));
        }
        else
        {
            listSysTimeSegDtl = query(SysTimeSegDtl.class, new DBCondition(SysTimeSegDtl.Field.segId, segId));
        }

        if (CommonUtil.isEmpty(listSysTimeSegDtl))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_SYSTIMESEGDEF, "SYS_TIME_SEG_DTL", segId);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(queryDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekday == 0)
            weekday = 7;
        int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        long year_month_day = CommonUtil.string2Long(year + getTwoDigits(month) + getTwoDigits(day));// 年月日
        long hour_minute_second = CommonUtil.string2Long(getTwoDigits(hour24) + getTwoDigits(minute) + getTwoDigits(second));// 时分秒
        long month_day = CommonUtil.string2Long(getTwoDigits(month) + getTwoDigits(day));// 月日

        long tempVal = 0;
        switch (timeMode)
        {
        case EnumCodeDefine.TIME_MODE_NOT_TIME:
            break;
        case EnumCodeDefine.TIME_MODE_WEEK:
            /* 星期型的当日时间段，日期为1-7。举例：Mon-Fri 8.00-20.00，表示星期一至星期五的每天8点到20点生效，则配置成：1080000 和 5200000 */
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                long startWeekDay = sysTimeSegDtl.getStartVal() / 1000000;
                long endWeekDay = sysTimeSegDtl.getEndVal() / 1000000;
                long startTime = sysTimeSegDtl.getStartVal() - startWeekDay * 1000000;
                long endTime = sysTimeSegDtl.getEndVal() - endWeekDay * 1000000;

                if ((weekday >= startWeekDay && weekday <= endWeekDay)
                        && (hour_minute_second >= startTime && hour_minute_second <= endTime))
                {
                    return true;
                }
            }
            break;
        case EnumCodeDefine.TIME_MODE_YYYYMMDD:
            /*
             * YYYYMMDD型指定日期的当日时间段。举例：20100102-20101114 8.00-20.00，表示2010年1月2日到2010年11月14日的每天8点到20点生效，则配置成：20100102080000 和
             * 20101114200000
             */
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                long startDate = sysTimeSegDtl.getStartVal() / 1000000;
                long endDate = sysTimeSegDtl.getEndVal() / 1000000;
                long startTime = sysTimeSegDtl.getStartVal() - startDate * 1000000;
                long endTime = sysTimeSegDtl.getEndVal() - endDate * 1000000;

                if ((year_month_day >= startDate && year_month_day <= endDate)
                        && (hour_minute_second >= startTime && hour_minute_second <= endTime))
                {
                    return true;
                }
            }

            break;
        case EnumCodeDefine.TIME_MODE_MMDD:
            /* MMDD型当天的时间段。举例：每年1月2日-11月14日 8.00-20.00，表示每年的1月2日到11月14日的每天8点到20点生效，则配置成：0102080000 和 1114200000 */
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                long startMonthDay = sysTimeSegDtl.getStartVal() / 1000000;
                long endMonthDay = sysTimeSegDtl.getEndVal() / 1000000;
                long startTime = sysTimeSegDtl.getStartVal() - startMonthDay * 1000000;
                long endTime = sysTimeSegDtl.getEndVal() - endMonthDay * 1000000;

                if ((month_day >= startMonthDay && month_day <= endMonthDay)
                        && (hour_minute_second >= startTime && hour_minute_second <= endTime))
                {
                    return true;
                }
            }

            break;
        case EnumCodeDefine.TIME_MODE_DD:
            /* DD型当天的时间段。举例：每月2日-11日 8.00-20.00，表示每月的2日到14日的每天8点到20点生效，则配置成：02080000 和 14200000 */
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                long startDay = sysTimeSegDtl.getStartVal() / 1000000;
                long endDay = sysTimeSegDtl.getEndVal() / 1000000;
                long startTime = sysTimeSegDtl.getStartVal() - startDay * 1000000;
                long endTime = sysTimeSegDtl.getEndVal() - endDay * 1000000;

                if ((day >= startDay && day < endDay) && (hour_minute_second >= startTime && hour_minute_second < endTime))
                {
                    return true;
                }
            }
            break;
        case EnumCodeDefine.TIME_MODE_WEEK_PERIOD:
            /* 星期型的从其实日期到终止日期的时间。举例：Mon 8.00 - Fri 20.00，表示星期一的8点到星期五的20点 之间生效，则配置成：1080000 和 5200000 */
            tempVal = CommonUtil.string2Long("" + weekday + hour24 + minute + second);
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                if (tempVal >= sysTimeSegDtl.getStartVal() && tempVal <= sysTimeSegDtl.getEndVal())
                {
                    return true;
                }
            }
            break;
        case EnumCodeDefine.TIME_MODE_YYYYMMDD_PERIOD:
            /*
             * YYYYMMDD型从起始日期时间到终止日期时间。举例：2010年1月2日 8.00 - 2010年11月14日 20.00，表示 2010年1月2日的8点 到 2010年11月14日的20点
             * 之间生效，则配置成：20100102080000 和 20101114200000
             */
            tempVal = CommonUtil.string2Long("" + year + month + day + hour24 + minute + second);
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                if (tempVal >= sysTimeSegDtl.getStartVal() && tempVal <= sysTimeSegDtl.getEndVal())
                {
                    return true;
                }
            }
            break;
        case EnumCodeDefine.TIME_MODE_MMDD_PERIOD:
            /* MMDD型从起始日期时间到终止日期时间。举例：每年1月2日 8.00 - 每年11月14日 20.00，表示 每年的1月2日的8点 到 每年的11月14日的20点 之间生效，则配置成：0102080000 和 1114200000 */
            tempVal = CommonUtil.string2Long("" + month + day + hour24 + minute + second);
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                if (tempVal >= sysTimeSegDtl.getStartVal() && tempVal <= sysTimeSegDtl.getEndVal())
                {
                    return true;
                }
            }
            break;
        case EnumCodeDefine.TIME_MODE_DD_PERIOD:
            /* DD型从起始日期时间到终止日期时间。举例：每月2日 8.00 - 每月14日 20.00，表示 每月的2日的8点 到 每月的14日的20点 之间生效，则配置成：02080000 和 14200000 */
            tempVal = CommonUtil.string2Long("" + day + hour24 + minute + second);
            for (SysTimeSegDtl sysTimeSegDtl : listSysTimeSegDtl)
            {
                if (tempVal >= sysTimeSegDtl.getStartVal() && tempVal <= sysTimeSegDtl.getEndVal())
                {
                    return true;
                }
            }
            break;
        default:
            IMSUtil.throwBusiException(ErrorCodeDefine.TIME_MODE_NOT_DEFINE, timeMode);
            break;
        }

        return false;
    }

    /**
     * 不同业务OneTimeFee针对hybrid用户的收费类型获取 wukl 2012-3-8
     * 
     * @param busiSpecId
     * @return
     */
    public BiBusiHybridRule getBiBusiHybridRule(Integer busiSpecId, boolean cacheFlag)
    {
        BiBusiHybridRule hybridRule = null;
        if (cacheFlag)
        {
            hybridRule = DBConfigInitBean.getSingleCached(BiBusiHybridRule.class, new CacheCondition(
                    BiBusiHybridRule.Field.busiSpecId, busiSpecId));

        }
        else
        {
            hybridRule = querySingle(BiBusiHybridRule.class, new DBCondition(BiBusiHybridRule.Field.busiSpecId, busiSpecId));
        }

        if (hybridRule == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE, 
                    DBUtil.buildTableName(BiBusiHybridRule.class),BiBusiHybridRule.Field.busiSpecId.toString(),busiSpecId);
        }

        return hybridRule;
    }

    /**
     * 数据首次激活成功失败跳转的URL wukl 2012-3-8
     * 
     * @param priceRuleId
     * @param cacheFlag
     * @return
     */
    public PmFirstActiveGprsUrl getPmFirstActiveGprsUrl(Integer priceRuleId, boolean cacheFlag)
    {
        PmFirstActiveGprsUrl gprsUrl = null;
        if (cacheFlag)
        {
            gprsUrl = DBConfigInitBean.getSingleCached(PmFirstActiveGprsUrl.class, new CacheCondition(
                    PmFirstActiveGprsUrl.Field.priceRuleId, priceRuleId));
        }
        else
        {
            gprsUrl = querySingle(PmFirstActiveGprsUrl.class,
                    new DBCondition(PmFirstActiveGprsUrl.Field.priceRuleId, priceRuleId));
        }

        if (gprsUrl == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.ACTIVE_GPRS_URL_NOT_DEFINE, priceRuleId);
        }

        return gprsUrl;
    }

}