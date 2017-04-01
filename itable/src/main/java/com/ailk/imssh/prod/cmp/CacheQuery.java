package com.ailk.imssh.prod.cmp;

import java.util.List;
import java.util.Map;
import javax.script.Bindings;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.init.LuaConfig;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;

/**
 * @Description 缓存组件
 * @author lijc3
 * @Date 2012-5-17
 */
public class CacheQuery extends BaseCmp
{
    
    
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
    

    public Integer queryPricePlanId(long offeringId, Map valueMap)
    {
        Object mainOfferId = valueMap.get(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID);
        imsLogger.info("#####begin query price plan offer_id =  ", offeringId , "  main_offer_id= " , mainOfferId);
        List<PmProductPricingPlan> plans =  queryOfferPricingPlan((int) offeringId, (Integer) mainOfferId);
        if (CommonUtil.isEmpty(plans))
        {
            imsLogger.error("****** not find PmProductPricingPlan where productOfferingId = ", offeringId);
            return null;
        }
        Bindings bind=LuaConfig.createBindings(valueMap);

        Integer default_planId = null;

        for (PmProductPricingPlan plan : plans)
        {
            if (plan.getPolicyId().intValue() == 0)
            {
                default_planId = plan.getPricingPlanId();
                continue;// policy=0是缺省价格计划
            }
            String exp_result = LuaConfig.executeLuaScript(plan.getPolicyId(), bind);
            if ("1".equals(exp_result) || "true".equals(exp_result))
            {
                default_planId = plan.getPricingPlanId();
                break;// 如果计算出一个结果为1，就使用这个加个计划，后面不再继续计算
            }

        }
        return default_planId;
    }

}
