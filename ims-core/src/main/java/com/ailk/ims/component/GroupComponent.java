package com.ailk.ims.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.JavaInvokeLua;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;
import com.ailk.openbilling.persistence.sys.entity.SysStateDef;

/**
 * @Description: (这里用一句话描述这个类的作用)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-8-25
 * @Date 2011-8-25 修改获取组方式
 * @Date 2012-4-19 wangjt 注释不使用的方法
 */
public class GroupComponent extends BaseComponent
{

    private static ImsLogger logger = new ImsLogger(GroupComponent.class);

    public GroupComponent()
    {
    }

    /**
     * 根据表中的region_code,biilingType,mainPromotion字段筛选出组列表，<BR>
     * 在将customer_type,customer_segment,res_type,res_segment 作为入参传入lua表达式 ，<BR>
     * 获取计算结果为1的组列表 ， 再根据组列表中的优先级字段 （ Priority ） ， 获取优先级最高的组
     */
    // private SysGroupDef queryGroupIDByConditions(CmCustomer cust, CmResource res, CaAccount acct) throws IMSException
    // {
    // List<SysGroupDef> result = null;
    //
    // // @Date tangjl5 如果没有用户，则直接使用客户信息获取组信息。如果有用户，则也增加用户信息一起来获取组信息
    // if (res == null)
    // {
    // if (cust == null)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_NOTEXIST);
    // }
    //
    // result = query(SysGroupDef.class, new OrderCondition[] { new OrderCondition(false, SysGroupDef.Field.priority) },
    // null,
    // new DBOrCondition(new DBCondition(SysGroupDef.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerType, cust.getCustomerType())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.regionCode, acct.getRegionCode()), new DBCondition(
    // SysGroupDef.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION)), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.accountType, acct.getAccountType())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerClass, cust.getCustomerClass())));
    // }
    // else
    // {
    // result = query(SysGroupDef.class, new OrderCondition[] { new OrderCondition(false, SysGroupDef.Field.priority) },
    // null, new DBCondition(SysGroupDef.Field.resType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBOrCondition(new DBCondition(SysGroupDef.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.regionCode, acct.getRegionCode())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.billingType, res.getBillingType())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.accountType, acct.getAccountType())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.resSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.resSegment, res.getResSegment())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerType, cust.getCustomerType())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
    // new DBCondition(SysGroupDef.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
    // new DBCondition(SysGroupDef.Field.customerClass, cust.getCustomerClass())));
    // }
    //
    // if (CommonUtil.isEmpty(result))
    // {
    // return null;
    // }
    // else
    // {
    // return result.get(0);
    // }
    // }

    /**
     * @Description: 构造传递给lua表达式所需参数
     */
    public Map<String, Object> packageLuaParam(CmCustomer cust, CmResource res)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (null != cust)
        {
            paramMap.put(ConstantDefine.LUA_CUST_TYPE, cust.getCustomerType());
            paramMap.put(ConstantDefine.LUA_CUST_SEGMENT, cust.getCustomerSegment());
        }

        if (res != null)
        {
            // 暂时先去掉该参数 wukl 2011-12-26
            /*
             * CmResIdentity resIdentity = context.getComponent(UserComponent.class).queryResIdentityByUserId(
             * res.getResourceId()); if (null != resIdentity) { paramMap.put(ConstantDefine.LUA_RES_TYPE,
             * resIdentity.getIdentityType()); }
             */

            paramMap.put(ConstantDefine.LUA_RES_SEGMENT, res.getResSegment());
        }

        return paramMap;
    }

    /**
     * @Description: 根据组Id，MAIN_PROMOTION,POLICY_ID匹配组对应得模式
     * @param groupID
     * @param cust
     * @param res
     * @param mainProdOfferingId
     * @return
     * @author: tangjl5
     * @Date: 2011-10-11
     */
    @SuppressWarnings("unused")
    public SysGroupCyclePattern queryGrpCycPatternByConditions(CmCustomer cust, CmResource res, CaAccount acct,
            Long mainProdOfferingId)
    {
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
        
        List<SysGroupCyclePattern> result = null;
        // @Date 2012-4-24 tangjl5 若账户是single balance，则账户下的用户的状态需保持一致，查找pattern_id时不使用用户信息
        if (res != null && acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
        {
         // @Date 2012-4-16 tangjl5 terminal状态的用户若没有对应的账户、客户时，则查询所在组时不匹配对应的客户，账户信息
            if (cust == null && acct == null)
            {
                result = query(SysGroupCyclePattern.class, 
                        new DBOrCondition(
                                new DBCondition(SysGroupCyclePattern.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION), 
                                new DBCondition(SysGroupCyclePattern.Field.mainPromotion, mainProductOfferingId)
                        ),
                        new DBCondition(SysGroupCyclePattern.Field.resType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION), 
                        new DBOrCondition(
                            new DBCondition(SysGroupCyclePattern.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCyclePattern.Field.billingType, res.getBillingType())
                        ), 
                        new DBCondition(SysGroupCyclePattern.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION), 
                        new DBOrCondition(
                            new DBCondition(SysGroupCyclePattern.Field.resSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                            new DBCondition(SysGroupCyclePattern.Field.resSegment, res.getResSegment())
                        ), 
                        new DBCondition(SysGroupCyclePattern.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION), 
                        new DBCondition(SysGroupCyclePattern.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION));
            }
            else
            {
                result = query(SysGroupCyclePattern.class, new DBOrCondition(new DBCondition(SysGroupCyclePattern.Field.mainPromotion,
                        ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(SysGroupCyclePattern.Field.mainPromotion,
                        mainProductOfferingId)),
                        new DBCondition(SysGroupCyclePattern.Field.resType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBOrCondition(new DBCondition(SysGroupCyclePattern.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.regionCode, acct.getRegionCode())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.billingType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.billingType, res.getBillingType())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.accountType, acct.getAccountType())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.resSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.resSegment, res.getResSegment())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.customerType, cust.getCustomerType())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
                        new DBCondition(SysGroupCyclePattern.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                        new DBCondition(SysGroupCyclePattern.Field.customerClass, cust.getCustomerClass())));
            }
        }
        else
        {
            result = query(SysGroupCyclePattern.class, new DBOrCondition(new DBCondition(
                    SysGroupCyclePattern.Field.mainPromotion, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                    SysGroupCyclePattern.Field.mainPromotion, mainProductOfferingId)), new DBOrCondition(new DBCondition(
                    SysGroupCyclePattern.Field.customerType, ConstantDefine.INT_UNIFIED_CONFIGURATION), new DBCondition(
                    SysGroupCyclePattern.Field.customerType, cust.getCustomerType())), new DBOrCondition(new DBCondition(
                    SysGroupCyclePattern.Field.regionCode, acct.getRegionCode()), new DBCondition(
                    SysGroupCyclePattern.Field.regionCode, ConstantDefine.INT_UNIFIED_CONFIGURATION)), new DBOrCondition(
                    new DBCondition(SysGroupCyclePattern.Field.accountType, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                    new DBCondition(SysGroupCyclePattern.Field.accountType, acct.getAccountType())), new DBOrCondition(
                    new DBCondition(SysGroupCyclePattern.Field.customerSegment, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                    new DBCondition(SysGroupCyclePattern.Field.customerSegment, cust.getCustomerSegment())), new DBOrCondition(
                    new DBCondition(SysGroupCyclePattern.Field.customerClass, ConstantDefine.INT_UNIFIED_CONFIGURATION),
                    new DBCondition(SysGroupCyclePattern.Field.customerClass, cust.getCustomerClass())));
        }

        if (CommonUtil.isEmpty(result))
        {
            return null;
        }
        else
        {
            for (SysGroupCyclePattern cyclePattern : result)
            {
                SysPolicy sysPolicy = context.getComponent(BaseLifeCycleComponent.class).querySysPolicyByPolicy(
                        cyclePattern.getPolicyId());
                if (null != sysPolicy)
                {
                    String policyExpr = sysPolicy.getPolicyExpr();

                    // 执行lua脚本，若计算结果为1则，流转成功
                    String luaResult = JavaInvokeLua.executeLuaScript(policyExpr, packageLuaParam(cust, res));
                    if (ConstantDefine.LUA_RUN_OK.equals(luaResult))
                    {
                        return cyclePattern;
                    }
                    else
                    {
                        logger.debug("****** patternId[" + cyclePattern.getPatternId() + "], plocyId=: "
                                + cyclePattern.getPolicyId() + ";result=" + luaResult);
                        continue;
                    }
                }
                else
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.POLICY_IS_NULL, cyclePattern.getPolicyId());
                }
            }

            return null;
        }
    }

    /**
     * @Description: 根据组Id，MAIN_PROMOTION,POLICY_ID匹配组对应得模式
     * @param groupID
     * @param cust
     * @param res
     * @return
     * @throws IMSException
     * @author: tangjl5
     * @Date: 2011-10-11
     */
    public SysGroupCyclePattern queryGroupCyclePatternByGroupID(Integer groupID, CmCustomer cust, CmResource res, CaAccount acct)
            throws IMSException
    {
        return queryGrpCycPatternByConditions(cust, res, acct, null);
    }

    public List<SysStateDef> queryPostPaidStsList()
    {
        Integer[] stsArr = new Integer[] { EnumCodeDefine.LIFECYCLE_IDLE_INITIAL, EnumCodeDefine.LIFECYCLE_ACTIVE,
                EnumCodeDefine.LIFECYCLE_SUSPEND_REQUEST, EnumCodeDefine.LIFECYCLE_SUSPEND_DEBT,
                EnumCodeDefine.LIFECYCLE_SUSPEND_FRAULD, EnumCodeDefine.LIFECYCLE_SUSPEND_CREDITLIMIT,EnumCodeDefine.LIFECYCLE_PRE_TERMINAL,
                EnumCodeDefine.LIFECYCLE_TERMINAL, EnumCodeDefine.LIFECYCLE_POOL };
        List<SysStateDef> stateDefList = query(SysStateDef.class, new DBCondition(SysStateDef.Field.sts, stsArr, Operator.IN),
                new DBCondition(SysStateDef.Field.stsType, 1));
        return stateDefList;
    }

    // public SysNotifyExemptionGroup queryExemptionGroupByConditons(CmCustomer cust, CaAccount acct)
    // {
    // SysGroupDef groupDef = this.queryGroupIDByConditions(cust, null, acct);
    // if (groupDef == null)
    // {
    // return null;
    // }
    //
    // return querySingle(SysNotifyExemptionGroup.class,
    // new DBCondition(SysNotifyExemptionGroup.Field.groupId, groupDef.getGroupId()));
    // }
}
