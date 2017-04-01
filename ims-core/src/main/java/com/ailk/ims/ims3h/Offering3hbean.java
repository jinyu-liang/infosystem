package com.ailk.ims.ims3h;

import java.util.List;
import com.ailk.ims.common.ContextBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
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
 * 销售品的缓存bean，类似三户的缓存bean
 * 
 * @Description
 * @author wuyj
 * @Date 2012-6-12
 * @Date 2012-7-24 lijc3 增加List<PmProductOfferSpecChar>,getBusiFlag()方法
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 */
public class Offering3hbean extends ContextBean
{
    private PmProductOffering offering;// 主对象，laod的时候必须有
    private PmProductSpecTypeGroups specTypeGroup;
    private PmProductOfferLifecycle lifeCycle;
    private List<PmProductSpecCharUse> specCharUserList;
    private List<PmProductSpecCharValue> specCharValueList;
    private List<PmProductSpecServiceRel> serviceList;
    private PmProductOfferAttribute attribute;
    private List<PmProductOfferSpecChar> specCharList;
    private PmProdBusiLimit busiLimit;
    private List<PmProductPricingPlan> pricePlanList;
    private List<PmCompositeDeductRule> deductRuleList;
    //loadOffer3hbeanList的时候会将这个值设置为true，如果没有值，也不会再次查询数据库
    boolean flag=false;

    public Offering3hbean(PmProductOffering offering, IMSContext context)
    {
        this.offering = offering;
        this.setContext(context);
    }

    public boolean isMain()
    {
        return offering.getIsMain() == EnumCodeDefine.PRODUCT_MAIN;
    }

    public PmProductOffering getOffering()
    {
        return offering;
    }

    public Integer getBillingType()
    {
        Integer billingType = getAttribute().getBillingType();
        if (billingType == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_BILLTYPE_IS_NULL, offering.getProductOfferingId());
        return billingType;
    }

    public PmProductOfferAttribute getAttribute()
    {
        if (attribute == null)
        {
            attribute = SpringUtil.getOfferCacheBean().queryOfferingAttribute(offering.getProductOfferingId());
            if (attribute == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OFFER_ATTR_IS_NULL, offering.getProductOfferingId());
            }
        }

        return attribute;
    }

    public PmProductSpecTypeGroups getSpecTypeGroup()
    {
        if (specTypeGroup == null)
        {
            specTypeGroup=SpringUtil.getOfferCacheBean().queryOfferSpecTypeGroup(offering.getProdSpecId());
//            specTypeGroup = DBUtil.querySingle(PmProductSpecTypeGroups.class, new DBCondition(
//                    PmProductSpecTypeGroups.Field.prodSpecId, offering.getProdSpecId()));
        }
        return specTypeGroup;
    }

    public PmProductOfferLifecycle getOfferLifeCycle()
    {
        if (lifeCycle == null)
        {
            lifeCycle=SpringUtil.getOfferCacheBean().queryOfferLifecycle(offering.getProductOfferingId());
//            lifeCycle = DBUtil.querySingle(PmProductOfferLifecycle.class, new DBCondition(
//                    PmProductOfferLifecycle.Field.productOfferingId, offering.getProductOfferingId()));
        }

        return lifeCycle;
    }

    public List<PmProductSpecCharUse> getSpecCharUseList()
    {
        if (specCharUserList == null)
        {
            if(flag){
                return null;
            }
            specCharUserList=SpringUtil.getOfferCacheBean().queryOfferSpecCharUseList(offering.getProdSpecId());
//            specCharUserList = DBUtil.query(PmProductSpecCharUse.class, new DBCondition(PmProductSpecCharUse.Field.prodSpecId,
//                    offering.getProdSpecId()));
        }
        return specCharUserList;
    }

    public List<PmProductSpecCharValue> getSpecCharValueList()
    {
        
        if (specCharValueList == null)
        {
            if(flag){
                return null;
            }
            specCharValueList=SpringUtil.getOfferCacheBean().queryOfferSpecCharValueList(offering.getProdSpecId());
//            specCharValueList = DBUtil.query(PmProductSpecCharValue.class, new DBCondition(PmProductSpecCharUse.Field.prodSpecId,
//                    offering.getProdSpecId()));
        }
        return specCharValueList;
    }

    public List<PmProductSpecServiceRel> getServiceList()
    {
        if (serviceList == null)
        {
            if(flag){
                return null;
            }
            serviceList=SpringUtil.getOfferCacheBean().queryOfferServiceList(offering.getProdSpecId());
//            serviceList = DBUtil.query(PmProductSpecServiceRel.class, new DBCondition(PmProductSpecServiceRel.Field.prodSpecId,
//                    offering.getProdSpecId()));
        }
        return serviceList;
    }

    public boolean isMatch(long offeringId)
    {
        if (offeringId == offering.getProductOfferingId())
            return true;
        return false;
    }

    public Integer getBusiFlag()
    {
        if (getSpecTypeGroup() != null)
        {
            return getSpecTypeGroup().getSpecTypeId();
        }
        else
        {
            return SpecCodeDefine.NORMAL;
        }
    }

    public List<PmProductOfferSpecChar> getOfferSpecCharList()
    {
        if (specCharList == null)
        {
            if(flag){
                return null;
            }
            specCharList=SpringUtil.getOfferCacheBean().queryOfferSpecCharList(offering.getProductOfferingId());
//            specCharList = DBUtil.query(PmProductOfferSpecChar.class, new DBCondition(
//                    PmProductOfferSpecChar.Field.productOfferingId, offering.getProductOfferingId()));
        }
        return specCharList;
    }

    public PmProdBusiLimit getPmProdBusiLimit()
    {
        if (busiLimit == null)
        {
            busiLimit=SpringUtil.getOfferCacheBean().queryOfferBusiLimit(offering.getProductOfferingId());
//            busiLimit = DBUtil.querySingle(PmProdBusiLimit.class, new DBCondition(PmProdBusiLimit.Field.productOfferingId,
//                    offering.getProductOfferingId()));
        }
        return busiLimit;

    }

    public List<PmProductPricingPlan> getPricePlanList()
    {
        if (pricePlanList == null)
        {
            pricePlanList=SpringUtil.getOfferCacheBean().queryOfferPricePlanList(offering.getProductOfferingId());
//            pricePlanList = DBUtil.query(PmProductPricingPlan.class, new DBCondition(
//                    PmProductPricingPlan.Field.productOfferingId, offering.getProductOfferingId()));
        }
        return pricePlanList;
    }

    public void setOffering(PmProductOffering offering)
    {
        this.offering = offering;
    }

    public void setSpecTypeGroup(PmProductSpecTypeGroups specTypeGroup)
    {
        this.specTypeGroup = specTypeGroup;
    }

    public void setLifeCycle(PmProductOfferLifecycle lifeCycle)
    {
        this.lifeCycle = lifeCycle;
    }

    public void setSpecCharUserList(List<PmProductSpecCharUse> specCharUserList)
    {
        this.specCharUserList = specCharUserList;
    }

    public void setSpecCharValueList(List<PmProductSpecCharValue> specCharValueList)
    {
        this.specCharValueList = specCharValueList;
    }

    public void setServiceList(List<PmProductSpecServiceRel> serviceList)
    {
        this.serviceList = serviceList;
    }

    public void setAttribute(PmProductOfferAttribute attribute)
    {
        this.attribute = attribute;
    }

    public void setSpecCharList(List<PmProductOfferSpecChar> specCharList)
    {
        this.specCharList = specCharList;
    }

    public void setBusiLimit(PmProdBusiLimit busiLimit)
    {
        this.busiLimit = busiLimit;
    }

    public void setPricePlanList(List<PmProductPricingPlan> pricePlanList)
    {
        this.pricePlanList = pricePlanList;
    }
    public PmCompositeDeductRule getDeductRule(Integer billingType)
    {
        if(deductRuleList==null){
            if(flag){
                return null;
            }
            deductRuleList=SpringUtil.getOfferCacheBean().queryOfferCompositeDeductRuleList(offering.getProductOfferingId());
//            deductRuleList = DBUtil.query(PmCompositeDeductRule.class, new DBCondition(
//                    PmProductPricingPlan.Field.productOfferingId, offering.getProductOfferingId()));
        }
        if(CommonUtil.isEmpty(deductRuleList)){
            return null;
        }
        for(PmCompositeDeductRule rule:deductRuleList){
            if(rule.getBillingType()==billingType.intValue()){
                return rule;
            }
        }
        return null;
    }
    public void setDeductRule(List<PmCompositeDeductRule> deductRuleList)
    {
        this.deductRuleList = deductRuleList;
    }
    
    public PmProductOfferSpecChar getOfferSpecChar(int specCharId){
        List<PmProductOfferSpecChar> list= getOfferSpecCharList();
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        for(PmProductOfferSpecChar specChar:list){
            if(specChar.getSpecCharId()==specCharId){
                return specChar;
            }
        }
        return null;
    }
    
    public void setFlag(boolean flag){
        this.flag=flag;
    }
}
