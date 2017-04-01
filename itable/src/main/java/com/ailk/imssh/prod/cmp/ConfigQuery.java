package com.ailk.imssh.prod.cmp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.ims.cache.OfferCacheBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.openbilling.persistence.pm.entity.PmBillingParamRela;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferComposite;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecServiceRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;
/**
 * 
 * @Description 销售品相关查询组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijc3
 * @Date 2012-5-14
 */
public class ConfigQuery extends CacheQuery
{
    /**
     * 根据busiFlag获取销售品
     */
    public PmProductOffering queryOfferingByBusiFlag(int busiFlag)
    {
        OfferCacheBean cacheBean=SpringUtil.getOfferCacheBean();
        PmProductSpecTypeGroups dmSpecType = cacheBean.queryOfferSpecTypeGroupBySpecType(busiFlag);
        if (dmSpecType == null)
            return null;
        PmProductOffering dmProdOffer = querySingle(PmProductOffering.class, new DBCondition(PmProductOffering.Field.prodSpecId,
                dmSpecType.getProdSpecId()));
        return dmProdOffer;
    }
    /**
     * 根据busiFlag获取销售品id
     */
    public Integer queryOfferingId(int busiFlag)
    {
        PmProductOffering dmProdOffer = queryOfferingByBusiFlag(busiFlag);
        if (dmProdOffer == null)
            return null;
        return dmProdOffer.getProductOfferingId();
    }
    
    /**
     * 获取特征值对应的配置值<br>
     * @param specCharValue:特征值
     * @return:配置值
     */
    public String getSpecCharValueById(Integer specCharValue)
    {
        PmProductSpecCharValue pmProductSpecCharValue = querySingle(PmProductSpecCharValue.class, new DBCondition(
                PmProductSpecCharValue.Field.specCharId, specCharValue));
        if (pmProductSpecCharValue == null)
        {
            // 如果未配置，直接抛异常
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE, specCharValue);
        }
        return pmProductSpecCharValue.getValue();
    }
    
    /**
     * 查询销售品服务
     * lijc3 2012-5-17
     * @param offeringId
     * @return
     * @throws IMSException
     */
    /*
    public List<Integer> queryServiceSpecIdListByOfferId(Long offeringId) throws IMSException
    {
        if (getContext().getExtendParam("servList_" + offeringId) != null)
        {
            return (List<Integer>) getContext().getExtendParam("servList_" + offeringId);
        }
        List<Integer> servList = new ArrayList<Integer>();
        servList = super.queryServiceSpecIdListByOfferId(offeringId.intValue());

        if (CommonUtil.isNotEmpty(servList))
        {
        	getContext().addExtendParam("servList_" + offeringId, servList);
        }
        return servList;
    }
    */
    /**
     * ljc 2011-10-1 2011-03-06 修改查询销售品对应的规格特征的方法和逻辑
     */
    public List<Integer> querySpecCharIdsByOfferId(Integer offerId)
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId.longValue());
        List<PmProductSpecCharUse> useList = offerBean.getSpecCharUseList();
        if (CommonUtil.isEmpty(useList))
        {
            return null;
        }
        List<Integer> specCharIds = new ArrayList<Integer>();
        for (PmProductSpecCharUse use : useList)
        {
            specCharIds.add(use.getSpecCharId());
        }
        return specCharIds;
        // 增加对List<PmProductSpecChar>的缓存 yanchuan 2012-02-20
        /*
        if (getContext().getExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId) != null)
        {
            return (List<Integer>) getContext().getExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId);
        }
        else
        {
            List<Integer> specCharIds = new ArrayList<Integer>();
            DBJoinCondition join = new DBJoinCondition(PmProductOffering.class);

            join.innerJoin(PmProductSpecCharUse.class, new DBRelation(PmProductSpecCharUse.Field.prodSpecId,
                    PmProductOffering.Field.prodSpecId));
            List<Map> result = this.queryJoin(join, new DBCondition(PmProductOffering.Field.productOfferingId, offerId));
            if (CommonUtil.isEmpty(result))
            {
                return null;
            }
            for (Map map : result)
            {
                PmProductSpecCharUse use = (PmProductSpecCharUse) map.get(PmProductSpecCharUse.class);
                specCharIds.add(use.getSpecCharId());
            }
            logger.info("*****************size of spec_char_id list by offer_id = " + offerId + "is " + specCharIds.size());
            getContext().addExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId, specCharIds);
            return specCharIds;
        }
        */
    }
    
    /**
     * 根据传入的销售品ID获取可以override的销售品id ljc 2011-11-17
     */
    public Integer queryOverrideOfferIdByOrgOfferId(Integer offerId)
    {
        List<PmProductOfferComposite> compositeList = null;
        PmProductOfferComposite composite = null;
        DBJoinCondition join = new DBJoinCondition(PmProductOfferComposite.class);
        join.innerJoin(PmProductOffering.class, new DBRelation(PmProductOfferComposite.Field.parentProductOfferingId,
                PmProductOffering.Field.productOfferingId));
        join.innerJoin(PmProductSpecTypeGroups.class, new DBRelation(PmProductSpecTypeGroups.Field.prodSpecId,
                PmProductOffering.Field.prodSpecId));
        List<Map> resultList = this.queryJoin(join, new DBCondition(PmProductSpecTypeGroups.Field.specTypeId,
                SpecCodeDefine.OVERRIDE), new DBCondition(PmProductOfferComposite.Field.productOfferingId, offerId));
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }
        for (Map map : resultList)
        {
            composite = (PmProductOfferComposite) map.get(PmProductOfferComposite.class);
            break;
        }
        if(composite == null){
        	return null;
        }
        compositeList = this.query(PmProductOfferComposite.class, new DBCondition(
                PmProductOfferComposite.Field.parentProductOfferingId, composite.getParentProductOfferingId()));
        if (compositeList.size() == 2)
        {
            if (compositeList.get(0).getProductOfferingId() == offerId.intValue())
            {
                return compositeList.get(1).getProductOfferingId();
            }
            else
            {
                return compositeList.get(0).getProductOfferingId();
            }
        }
        else
        {// 不是两个 配置错误 不返回
            return null;
        }
    }
    
    /**
     * 查询销售品特征值
     */
    public PmProductOfferSpecChar getOfferSpecCharBySpecCharIdAndOfferId(int specCharId, Integer offerId) throws IMSException
    {
        Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId.longValue());
        List<PmProductOfferSpecChar> specCharList = offerBean.getOfferSpecCharList();
        if (CommonUtil.isNotEmpty(specCharList))
        {
            for (PmProductOfferSpecChar specChar : specCharList)
            {
                if (specChar.getSpecCharId() == specCharId)
                {
                    return specChar;
                }
            }
            return null;
        }
        else
        {
            return null;
        }
        // return this.querySingle(PmProductOfferSpecChar.class,new DBCondition(PmProductOfferSpecChar.Field.productOfferingId,
        // offerId),
        // new DBCondition(PmProductOfferSpecChar.Field.specCharId, specCharId));
    }

    /**
     * 根据销售品id查询扣费规则 ljc 2011-11-14
     * @param offerId
     * @return
     */
    public PmCompositeDeductRule queryPmCompsiteDeductRuleByOfferId(Integer offerId, Integer billType)
    {
        if (context.getExtendParam("Deduct_rule" + offerId) != null)
        {
            return (PmCompositeDeductRule) context.getExtendParam("Deduct_rule" + offerId);
        }
        PmCompositeDeductRule rule = this.querySingle(PmCompositeDeductRule.class, new DBCondition(
                PmCompositeDeductRule.Field.productOfferingId, offerId), new DBCondition(PmCompositeDeductRule.Field.billingType,
                billType));
        if (rule != null)
        {
            context.addExtendParam("Deduct_rule" + offerId, rule);
            return rule;
        }
        return null;
    }
    
    /**
     * 查询销售品Id列表对应的服务
     * @Author lijc3
     */
    public List<Integer> queryServiceSpecIdListByOfferIds(Set offerIdList)
    {
        DBJoinCondition joinCond = new DBJoinCondition(PmProductOffering.class);
        joinCond.innerJoin(PmProductSpecServiceRel.class, new DBRelation(PmProductSpecServiceRel.Field.prodSpecId,
                PmProductOffering.Field.prodSpecId));
        List<Map> resultList = this.queryJoin(joinCond, new DBCondition(PmProductOffering.Field.productOfferingId, offerIdList,
                Operator.IN));
        List<Integer> servList = new ArrayList<Integer>();
        if (!CommonUtil.isEmpty(resultList))
        {
            for (Map itemMap : resultList)
            {
                PmProductSpecServiceRel rel = (PmProductSpecServiceRel) itemMap.get(PmProductSpecServiceRel.class);
                if (null != rel)
                {
                    servList.add(rel.getServiceSpecId());
                }
            }
        }
        return servList;
    }
    
    public Set<Integer> queryOfferListByServiceIds(Integer servcieId){
    	List<PmBillingParamRela> relaList = this.query(PmBillingParamRela.class, 
    			new DBCondition(PmBillingParamRela.Field.paramCode, servcieId),
    			new DBCondition(PmBillingParamRela.Field.paramType, 3),//CRM订购服务映射
    			new DBCondition(PmBillingParamRela.Field.billingParamType, 31),//服务对应销售品
    			new DBCondition(PmBillingParamRela.Field.paramClass, 1));//CRM系统
    	if(CommonUtil.isEmpty(relaList)){
    		return null;
    	}
    	Set<Integer> result = new HashSet<Integer>();
    	for(PmBillingParamRela rela : relaList){
    		result.add(Integer.parseInt(rela.getBillingParam()));
    	}
    	return result;
    }
}
