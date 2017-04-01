package com.ailk.imssh.common.cache;

import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;

/**
 * @Description 数据缓存类
 * @author lijc3
 * @Date 2012-10-17
 */
public class ItableCacheBean
{
    public List<PmProductPricingPlan> getPmPricingPlan(Integer offerId, Integer mainPromotion)
    {
        if(mainPromotion!=null){
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,offerId),
                    new DBOrCondition(new DBCondition(PmProductPricingPlan.Field.mainPromotion, mainPromotion),
                            new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1)));
        }else{
            return DBUtil.query(PmProductPricingPlan.class, new OrderCondition[] { (new OrderCondition(
                    PmProductPricingPlan.Field.priority)) }, null, new DBCondition(PmProductPricingPlan.Field.productOfferingId,offerId),
                    new DBCondition(PmProductPricingPlan.Field.mainPromotion, -1));
        }
    }

}
