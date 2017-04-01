package com.ailk.ims.cache;

import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProdBusiLimit;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecServiceRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;

/**
 * @Description 缓存销售品相关信息,使用的时候缓存
 * @Description 也缓存了账期配置表
 * @author lijc3
 * @Date 2012-10-22
 */
public class OfferCacheBean
{
    /**
     * lijc3 2012-10-22 获取销售品信息
     * 
     * @param offerId
     * @return
     */
    public PmProductOffering queryOfferingById(Long offerId)
    {
    	/*
        PmProductOffering offering = DBUtil.querySingle(PmProductOffering.class, new DBCondition(
                PmProductOffering.Field.productOfferingId, offerId),
                new DBCondition(PmProductOffering.Field.expireDate, DateUtil.currentDate(), Operator.GREAT));
                */
    	PmProductOffering offering = DBUtil.querySingle(PmProductOffering.class, new DBCondition(
                PmProductOffering.Field.productOfferingId, offerId));
    	if(offering == null ){
    		int tempOfferId = SysUtil.getInt("TEST_EXIST_OFFERING_ID",0);
    		if(tempOfferId != 0 ){
    			offering = DBUtil.querySingle(PmProductOffering.class, new DBCondition(
    	                PmProductOffering.Field.productOfferingId, tempOfferId));
    		}
    	}
        if (offering == null){
        	IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_NOTEXIST, offerId);
        }
        return offering;
    }

    public PmProductOfferLifecycle queryOfferLifecycle(Integer offerId)
    {
        PmProductOfferLifecycle lifeCycle = DBUtil.querySingle(PmProductOfferLifecycle.class, new DBCondition(
                PmProductOfferLifecycle.Field.productOfferingId, offerId));
        
        if(lifeCycle == null ){
    		int tempOfferId = SysUtil.getInt("TEST_EXIST_OFFERING_ID",0);
    		if(tempOfferId != 0 ){
    			lifeCycle = DBUtil.querySingle(PmProductOfferLifecycle.class, new DBCondition(
    	                PmProductOfferLifecycle.Field.productOfferingId, tempOfferId));
    		}
    	}
        
        if (lifeCycle == null)
        {
            throw IMSUtil.throwBusiException("offer lifecycle of offer_id = " + offerId + " not exist");
        }
        return lifeCycle;
    }

    public PmProductOfferAttribute queryOfferingAttribute(Integer offerId)
    {
        PmProductOfferAttribute attribute = DBUtil.querySingle(PmProductOfferAttribute.class, new DBCondition(
                PmProductOfferAttribute.Field.productOfferingId, offerId));
        
        if(attribute == null ){
    		int tempOfferId = SysUtil.getInt("TEST_EXIST_OFFERING_ID",0);
    		if(tempOfferId != 0 ){
    			attribute = DBUtil.querySingle(PmProductOfferAttribute.class, new DBCondition(
    	                PmProductOfferAttribute.Field.productOfferingId, tempOfferId));
    		}
    	}
        
        if (attribute == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_OFFERING_ATTRIBUTE_IS_NULL, offerId);
        }
        return attribute;
    }

    public PmProductSpecTypeGroups queryOfferSpecTypeGroup(Integer prodSpecId)
    {
        PmProductSpecTypeGroups specTypeGroup = DBUtil.querySingle(PmProductSpecTypeGroups.class, new DBCondition(
                PmProductSpecTypeGroups.Field.prodSpecId, prodSpecId));

        return specTypeGroup;
    }
    
    public PmProductSpecTypeGroups queryOfferSpecTypeGroupBySpecType(Integer specType){
    	PmProductSpecTypeGroups specTypeGroup = DBUtil.querySingle(PmProductSpecTypeGroups.class, new DBCondition(
                PmProductSpecTypeGroups.Field.specTypeId, specType));
    	return specTypeGroup;
    }
    
    /**
     * 
     * lijc3 2012-11-27 主动停机产品 日保号产品专用
     * @param prodSpecId
     * @return
     */
    public PmProductOffering queryOfferByProdSpecId(Integer prodSpecId){
        PmProductOffering offering = DBUtil.querySingle(PmProductOffering.class, new DBCondition(
                PmProductOffering.Field.prodSpecId, prodSpecId));
        return offering;
    }

    public List<PmProductSpecCharUse> queryOfferSpecCharUseList(Integer prodSpecId)
    {
        List<PmProductSpecCharUse> specCharUserList = DBUtil.query(PmProductSpecCharUse.class, new DBCondition(
                PmProductSpecCharUse.Field.prodSpecId, prodSpecId));

        return specCharUserList;
    }

    public List<PmProductSpecCharValue> queryOfferSpecCharValueList(Integer prodSpecId)
    {

        List<PmProductSpecCharValue> specCharValueList = DBUtil.query(PmProductSpecCharValue.class, new DBCondition(
                PmProductSpecCharUse.Field.prodSpecId, prodSpecId));

        return specCharValueList;
    }

    public List<PmProductSpecServiceRel> queryOfferServiceList(Integer prodSpecId)
    {
        List<PmProductSpecServiceRel> serviceList = DBUtil.query(PmProductSpecServiceRel.class, new DBCondition(
                PmProductSpecServiceRel.Field.prodSpecId, prodSpecId));
        return serviceList;
    }

    /**
     * lijc3 2012-10-22 查询PmProductOfferSpecChar
     * 
     * @param offerId
     * @return
     */
    public List<PmProductOfferSpecChar> queryOfferSpecCharList(Integer offerId)
    {
        List<PmProductOfferSpecChar> specCharList = DBUtil.query(PmProductOfferSpecChar.class, new DBCondition(
                PmProductOfferSpecChar.Field.productOfferingId, offerId));

        return specCharList;
    }

    /**
     * lijc3 2012-10-22 查询PmProdBusiLimit
     * 
     * @param offerId
     * @return
     */
    public PmProdBusiLimit queryOfferBusiLimit(Integer offerId)
    {
        PmProdBusiLimit busiLimit = DBUtil.querySingle(PmProdBusiLimit.class, new DBCondition(
                PmProdBusiLimit.Field.productOfferingId, offerId));
        return busiLimit;
    }

    /**
     * lijc3 2012-10-22 查询PmProductPricingPlan
     * 
     * @param offerId
     * @return
     */
    public List<PmProductPricingPlan> queryOfferPricePlanList(Integer offerId)
    {
        List<PmProductPricingPlan> pricePlanList = DBUtil.query(PmProductPricingPlan.class, new DBCondition(
                PmProductPricingPlan.Field.productOfferingId, offerId));

        return pricePlanList;
    }

    /**
     * lijc3 2012-10-22 查询PmCompositeDeductRule
     * 
     * @param offerId
     * @return
     */
    public List<PmCompositeDeductRule> queryOfferCompositeDeductRuleList(Integer offerId)
    {
        List<PmCompositeDeductRule> deductRuleList = DBUtil.query(PmCompositeDeductRule.class, new DBCondition(
                PmProductPricingPlan.Field.productOfferingId, offerId));
        return deductRuleList;
    }

    /**
     * lijc3 2012-10-22 根据主产品查询offerId对应的PM_PRODUCT_PRICING_PLAN
     * 
     * @param offerId
     * @param mainPromotion
     * @return
     */
    public List<PmProductPricingPlan> queryOfferPricingPlan(List<Integer> offerIds, Integer mainPromotion)
    {
        if (mainPromotion != null)
        {
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,
                    offerIds,Operator.IN), new DBOrCondition(new DBCondition(PmProductPricingPlan.Field.mainPromotion, mainPromotion),
                    new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1)));
        }
        else
        {
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,
                    offerIds,Operator.IN), new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1));
        }
    }
    
    /**
     * lijc3 2012-10-22 根据主产品查询offerId对应的PM_PRODUCT_PRICING_PLAN
     * 
     * @param offerId
     * @param mainPromotion
     * @return
     */
    public List<PmProductPricingPlan> queryOfferPricingPlan(Integer offerId, Integer mainPromotion)
    {
        if (mainPromotion != null)
        {
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(false,
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,
                    offerId), new DBOrCondition(new DBCondition(PmProductPricingPlan.Field.mainPromotion, mainPromotion),
                    new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1)));
        }
        else
        {
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(false,
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,
                    offerId), new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1));
        }
    }
    

    /**
     * lijc3 2012-10-23 缓存账期配置表CA_BILLING_PERIOD
     * 
     * @param cycleType
     * @param cycleUnit
     * @return
     */
    public List<CaBillingPeriod> queryAcctCyclePeriod(int cycleType, int cycleUnit)
    {
        return DBUtil.query(CaBillingPeriod.class, new DBCondition(CaBillingPeriod.Field.periodType, cycleType), new DBCondition(
                CaBillingPeriod.Field.periodUnit, cycleUnit));
    }

    /**
     * lijc3 2012-10-23 缓存账期配置表CA_BILLING_CYCLE_SPEC
     * 
     * @param periodId
     * @param startBillday
     * @return
     */
    public List<CaBillingCycleSpec> queryAcctCycleSpecList(int periodId, int startBillday)
    {
        return DBUtil.query(CaBillingCycleSpec.class, new DBCondition(CaBillingCycleSpec.Field.periodId, periodId),
                new DBCondition(CaBillingCycleSpec.Field.billingDateShift, startBillday));
    }

    public List<CaBillingCycleSpec> queryAcctCycleSpec(Long cycleSpecId)
    {
        return DBUtil.query(CaBillingCycleSpec.class, new DBCondition(CaBillingCycleSpec.Field.cycleSpecId, cycleSpecId));
    }

    public List<CaBillingPeriod> queryAcctBillPeriodList(Integer periodId)
    {
        return DBUtil.query(CaBillingPeriod.class, new DBCondition(CaBillingPeriod.Field.periodId, periodId));
    }
    
}
