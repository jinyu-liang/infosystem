package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.JavaInvokeLua;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.GroupComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.pm.entity.PmChangeOfferRule;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferComposite;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferTypeGroups;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecServiceRel;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;
import com.ailk.openbilling.persistence.pm.entity.PmRentDeductFailure;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCredit;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;
import com.ailk.openbilling.persistence.sys.entity.SysStateDef;

/**
 * @Description:配置相关的信息查询的方法类
 * @author wangjt
 * @Date 2011-12-21
 * @Date 2012-08-01 tangjl5 :Bug # 54183 查询条件修改为同帐管一致
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 */
public class ConfigQuery extends CacheQuery
{
    /**
     * 获取特征值对应的配置值<br>
     * wangjt 2011-12-8
     * 
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
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE, specCharValue);
        }
        return pmProductSpecCharValue.getValue();
    }

    /**
     * ljc 2011-10-1 获取PmProductSpecCharValue 根据specId
     */
    public PmProductSpecCharValue queryPmSpecChar(int specId)
    {
        // PmProductSpecCharValue pscv = new PmProductSpecCharValue();
        // pscv.setSpecCharId(specId);
        // pscv.setIsDefault(1);// 固定值
        PmProductSpecCharValue pscv = this.querySingle(PmProductSpecCharValue.class, new DBCondition(
                PmProductSpecCharValue.Field.specCharId, specId), new DBCondition(PmProductSpecCharValue.Field.isDefault, 1));
        if (pscv == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_NO_REECORD);
        }
        return pscv;
    }

    /**
     * ljc 2011-10-1 2011-03-06 修改查询销售品对应的规格特征的方法和逻辑
     */
    public List<Integer> querySpecCharIdsByOfferId(Integer offerId)
    {
        // 增加对List<PmProductSpecChar>的缓存 yanchuan 2012-02-20
        if (context.getExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId) != null)
        {
            return (List<Integer>) context.getExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId);
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
            // List<PmProductSpecChar> chars = this.query(PmProductSpecChar.class, new
            // DBCondition(PmProductSpecChar.Field.specCharId,
            // busiFlag, Operator.MATCH_START));
            // if (busiFlag.intValue() == SpecCodeDefine.GPRS)
            // {// 如果是GPRS，则需要查询出SLA的特征值
            // List<PmProductSpecChar> list = query(PmProductSpecChar.class, new DBCondition(PmProductSpecChar.Field.specCharId,
            // SpecCodeDefine.SLA, Operator.MATCH_START));
            // chars.addAll(list);
            // }
            imsLogger.info("*****************size of spec_char_id list by offer_id = " + offerId + "is " + specCharIds.size());
            context.addExtendParam(ConstantDefine.NEWREG_SPEC_CHAR_BUSI_FLAG + offerId, specCharIds);
            return specCharIds;
        }
    }

    public boolean isOfferIdExist(int offerId) throws IMSException
    {
        PmProductOffering offer = context.getComponent(CacheQuery.class).queryPmProductOfferingByOfferId(offerId);
        // offer.setProductOfferingId(offerId);
        // offer = querySingle(offer);
        return offer != null;
    }

    public PmProductOffering queryOfferingByOfferId(Long offerId)
    {
        // PmProductOffering value = super.querySingle(PmProductOffering.class, new DBCondition(
        // PmProductOffering.Field.productOfferingId, offerId));
        return context.getComponent(CacheQuery.class).queryPmProductOfferingByOfferId(offerId.intValue());
    }

    public PmProductOfferLifecycle queryProdOfferLifeCycle(long offeringId) throws IMSException
    {
        return context.getComponent(CacheQuery.class).queryProdOfferLifeCycle((int) offeringId);
    }

    public PmProductOffering queryOfferingByBusiFlag(int busiFlag)
    {
        PmProductSpecTypeGroups dmSpecType = querySingle(PmProductSpecTypeGroups.class, new DBCondition(
                PmProductSpecTypeGroups.Field.specTypeId, busiFlag));

        if (dmSpecType == null)
            return null;
        PmProductOffering dmProdOffer = querySingle(PmProductOffering.class, new DBCondition(PmProductOffering.Field.prodSpecId,
                dmSpecType.getProdSpecId()));
        return dmProdOffer;
    }

    public Integer queryOfferingId(int busiFlag) throws IMSException
    {
        PmProductOffering dmProdOffer = queryOfferingByBusiFlag(busiFlag);
        if (dmProdOffer == null)
            return null;
        return dmProdOffer.getProductOfferingId();
    }

    public List<Integer> queryServiceSpecIdListByOfferId(Long offeringId) throws IMSException
    {
        if (context.getExtendParam("servList_" + offeringId) != null)
        {
            return (List<Integer>) context.getExtendParam("servList_" + offeringId);
        }
         List<Integer> servList = context.getComponent(CacheQuery.class).queryServiceSpecIdListByOfferId(offeringId.intValue());

        if (CommonUtil.isNotEmpty(servList))
        {
            context.addExtendParam("servList_" + offeringId, servList);
        }
        return servList;
    }

    /**
     * 获取销售品扣减规则
     */
    public PmCompositeDeductRule queryCompositeDeductRule(Integer offeringId)
    {
        // PmCompositeDeductRule deductRule = new PmCompositeDeductRule();
        // deductRule.setProductOfferingId(offeringId);
        return querySingle(PmCompositeDeductRule.class,
                new DBCondition(PmCompositeDeductRule.Field.productOfferingId, offeringId));
    }

    /**
     * 获取替换主产品扣减规则定义的扣减天数
     */
    public Integer queryChangeMainDeductDaysByProductRule(Integer priceRuleId, int changeFlag)
    {
        // PmChangeOfferRule rule = new PmChangeOfferRule();
        // rule.setChangeFlag(changeFlag);
        // rule.setPriceRuleId(priceRuleId);
        PmChangeOfferRule rule = this.querySingle(PmChangeOfferRule.class, new DBCondition(PmChangeOfferRule.Field.changeFlag,
                changeFlag), new DBCondition(PmChangeOfferRule.Field.priceRuleId, priceRuleId));
        return rule == null ? null : rule.getChargeValidity();
    }

    /**
     * 获取主产品所在的组
     */
    public Integer queryOfferTypeByMainOfferId(Integer offerId)
    {
        // PmProductOfferTypeGroups group = new PmProductOfferTypeGroups();
        // group.setProductOfferingId(offerId);
        PmProductOfferTypeGroups group = this.querySingle(PmProductOfferTypeGroups.class, new DBCondition(
                PmProductOfferTypeGroups.Field.productOfferingId, offerId));
        if (group == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.OFFERING_NOT_IN_GROUP, offerId);
        }
        return group.getOfferType();
    }

    /**
     * 根据源销售品ID查询销售品之间的关系 ljc 2011-9-29
     */
    public PmProductOfferRel queryOfferRelByOrgOfferId(Integer offerId)
    {
        // PmProductOfferRel offerRel = new PmProductOfferRel();
        // offerRel.setProductOfferingId(offerId);
        PmProductOfferRel offerRel = this.querySingle(PmProductOfferRel.class, new DBCondition(
                PmProductOfferRel.Field.productOfferingId, offerId));
        return offerRel;
    }

    /**
     * 根据产品扣费失败处理规则id获取处理规则 ljc 2011-11-11
     */
    public PmRentDeductFailure queryFailRuleByRuleId(short ruleId) throws IMSException
    {
        return this.querySingle(PmRentDeductFailure.class, new DBCondition(PmRentDeductFailure.Field.failureRuleId, ruleId));
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
     * 查询销售品Id列表对应的服务
     * 
     * @Author lijingcheng
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

    /**
     * 根据groupID查询帐户信用度初始化表 yanchuan 2011-08-26
     */
    public SysGroupCredit queryGroupCredit(CmCustomer cust, CaAccount acct, CmResource res, Long mainProdOfferingId)
            throws IMSException
    {
        Long mainProductOfferingId = null;
        if (CommonUtil.isValid(mainProdOfferingId))
        {
            mainProductOfferingId = mainProdOfferingId;
        }
        else if (res != null)
        {
            mainProductOfferingId = (long) context.getComponent(BaseProductComponent.class).getUserMainProdOfferingId(
                    res.getResourceId());
        }

        List<SysGroupCredit> result = null;
        if (res != null)
        {
            result = DBUtil.query(SysGroupCredit.class, new OrderCondition[] { new OrderCondition(false,
                    SysGroupCredit.Field.priority) }, null, new DBOrCondition(new DBCondition(SysGroupCredit.Field.mainPromotion,
                    ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(SysGroupCredit.Field.mainPromotion,
                    mainProductOfferingId)), new DBCondition(SysGroupCredit.Field.resType,
                    ConstantDefine.INT_UNIFIED_CONFIGURATION),
                    new DBOrCondition(new DBCondition(SysGroupCredit.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.regionCode, acct.getRegionCode())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.billingType, res.getBillingType())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.accountType, acct.getAccountType())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.resSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.resSegment, res.getResSegment())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerType, cust.getCustomerType())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerClass, cust.getCustomerClass())),
                    // @Date 2012-08-01 tangjl5 :Bug # 54183 查询条件修改为同帐管一致
                    new DBOrCondition(new DBCondition(SysGroupCredit.Field.brandId, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.brandId, res.getBrandId())),
                    // 2012-06-08 zhangzj3 [47119]查询信用度时新增一个字段balance_type表示普通账户还是singbalance
                    new DBCondition(SysGroupCredit.Field.balanceType, acct.getBalanceType()));
        }
        else
        {
            result = DBUtil.query(
                    SysGroupCredit.class,
                    new OrderCondition[] { new OrderCondition(false, SysGroupCredit.Field.priority) },
                    null,
                    // 账户级产品不查询主产品
                    // new DBOrCondition(new DBCondition(SysGroupCredit.Field.mainPromotion,
                    // ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(SysGroupCredit.Field.mainPromotion,
                    // mainProductOfferingId)),
                    new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerType, cust.getCustomerType())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.regionCode, acct.getRegionCode()), new DBCondition(
                                    SysGroupCyclePattern.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION)),
                    new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.accountType, acct.getAccountType())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
                            new DBCondition(SysGroupCredit.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCredit.Field.customerClass, cust.getCustomerClass())),
                    // 2012-06-08 zhangzj3 [47119]查询信用度时新增一个字段balance_type表示普通账户还是singbalance
                    new DBCondition(SysGroupCredit.Field.balanceType, acct.getBalanceType()));
        }

        if (CommonUtil.isEmpty(result))
        {
            return null;
        }
        else
        {
            List<SysGroupCredit> groupCredits = new ArrayList<SysGroupCredit>();
            for (SysGroupCredit groupRedit : result)
            {
                SysPolicy sysPolicy = querySingle(SysPolicy.class,
                        new DBCondition(SysPolicy.Field.policyId, groupRedit.getPolicyId()));
                if (null != sysPolicy)
                {
                    String policyExpr = sysPolicy.getPolicyExpr();

                    // 执行lua脚本，若计算结果为1则，流转成功
                    String luaResult = JavaInvokeLua.executeLuaScript(policyExpr, context.getComponent(GroupComponent.class)
                            .packageLuaParam(cust, res));
                    if (ConstantDefine.LUA_RUN_OK.equals(luaResult))
                    {
                        groupCredits.add(groupRedit);
                    }
                    else
                    {
                        continue;
                    }
                }
                else
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.POLICY_IS_NULL, groupRedit.getPolicyId());
                }
            }

            if (groupCredits.size() > 0)
            {
                Map<Integer, SysGroupCredit> creditMap = new HashMap<Integer, SysGroupCredit>();
                for (SysGroupCredit groupRedit : groupCredits)
                {
                    creditMap.put(groupRedit.getPriority(), groupRedit);
                }

                if (creditMap.size() > 0)
                {
                    Integer maxPriorty = Collections.max(creditMap.keySet());
                    return creditMap.get(maxPriorty);
                }
            }

            return null;
        }
    }

    /**
     * 根据offername查询销售品 xieqr 2012-3-3
     * 
     * @param offerName
     * @return
     */
    public PmProductOffering queryOfferingByOfferName(String offerName)
    {
        PmProductOffering value = super.querySingle(PmProductOffering.class, new DBCondition(PmProductOffering.Field.name,
                offerName));
        return value;
    }

    /**
     * @Description: 获取状态名称
     * @author: tangjl5
     * @Date: 2011-12-9
     */
    public String querySysStateDefName(Integer sts)
    {
        SysStateDef stateDef = querySingle(SysStateDef.class, new DBCondition(SysStateDef.Field.sts, sts));
        if (null != stateDef)
        {
            return stateDef.getName();
        }
        return null;
    }
}
