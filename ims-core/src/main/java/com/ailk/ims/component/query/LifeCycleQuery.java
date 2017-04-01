package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.JavaInvokeLua;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.GroupComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaRescycReg;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternDef;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternDetail;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternTransfer;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCredit;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;
import com.ailk.openbilling.persistence.sys.entity.SysPolicy;

/**
 * 生命周期相关查询方法
 * 
 * @author wangjt
 * @Date 2012-4-30 删除： getImsResStsSyncReasonCode 方法
 * @Date 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
 * @Date 2012-07-03 wukl 上海的rec_sts字段没使用，所以不调用DBUtil的方法
 * @Ddate 2012-11-16 wukl 上海有效期表中不使用rec_sts字段 且表中的expire_date始终为最大值，所以查询方法独立开
 */
public class LifeCycleQuery extends ConfigQuery
{
    /**
     * 构造传递给lua表达式所需参数
     */
    private Map<String, Object> paramMap = null;

    public Map<String, Object> packageLuaParam(CmResLifecycle cmResLifecycle)
    {
        if (CommonUtil.isEmpty(paramMap))
        {
            paramMap = new HashMap<String, Object>();
            paramMap.put(ConstantDefine.LIFECYCLE_SUBSCRIBER_EXPIRE_DATE, cmResLifecycle.getExpireDate());
            paramMap.put(ConstantDefine.LIFECYCLE_SYSDATE, context.getRequestDate());
        }

        return paramMap;
    }


    /**
     * 根据用户ID获取有效期有效的用户
     */
    public CmResLifecycle queryResLifeCycForTimer(Long resourceID) throws IMSException
    {
        CmResLifecycle result = querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, resourceID),
                new DBOrCondition(new DBCondition(CmResLifecycle.Field.expireDate, context.getRequestDate(), Operator.GREAT),
                        new DBCondition(new DBCondition(CmResLifecycle.Field.expireDate, context.getRequestDate(),
                                Operator.LESS_EQUALS), new DBCondition(CmResLifecycle.Field.recSts, EnumCodeDefine.IN_DEALING))));
        return result;
    }

    /**
     * 上海有效期表中不使用rec_sts字段 且表中的expire_date始终为最大值，所以查询方法独立开
     * wukl 2012-11-16
     * @param resId
     * @return
     */
    public CmResValidity queryUserValidity4Sh(Long resId)
    {
        CmResValidity curValidity = querySingle(CmResValidity.class, new DBCondition(CmResValidity.Field.resourceId, resId));
        return curValidity;
    }
    /**
     * 查询用户有效期
     * 
     * @author: tangjl5
     * @Date: 2011-10-10
     */
    public CmResValidity queryUserValidity(Long resId)
    {
        CmResValidity result = querySingle(CmResValidity.class, new DBCondition(CmResValidity.Field.resourceId, resId),
                new DBCondition(CmResValidity.Field.recSts, EnumCodeDefine.IS_VALID_DATA, Operator.EQUALS), new DBOrCondition(
                        new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS),
                        new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT)));
        return result;
    }

    public CmResValidity queryAcctValidity(Long acctId)
    {
        CmResValidity result = querySingle(CmResValidity.class, new DBCondition(CmResValidity.Field.accountId, acctId),
                new DBCondition(CmResValidity.Field.resourceId, 0), new DBCondition(CmResValidity.Field.recSts,
                        EnumCodeDefine.IS_VALID_DATA, Operator.EQUALS), new DBOrCondition(new DBCondition(
                        CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS), new DBCondition(
                        CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT)));
        return result;
    }
    //@Date 2012-10-26 yugb :User Story #62519 pool或terminal,idle时允许查询用户状态
    public CmResValidity queryUserTerminalValidity(Long resId)
    {
    	List<CmResValidity> resultList =  DBUtil.query(CmResValidity.class, new OrderCondition[]{new OrderCondition(false,CmResValidity.Field.soNbr)},
				null,new DBCondition(CmResValidity.Field.resourceId,resId),new DBCondition(CmResValidity.Field.recSts,EnumCodeDefine.IS_HISTORY_DATA));
    	if(CommonUtil.isNotEmpty(resultList))
    	{
    		for(CmResValidity validity:resultList)
    		{
    			if(!validity.getExpireDate().equals(validity.getValidDate()))
    			{
    				return validity;
    			}
    		}
    	}
    	return null;
    }
    public List<CmResValidity> queryUserValidityByAcctId(Long acctId)
    {
        List<CmResValidity> result = query(CmResValidity.class, new DBCondition(CmResValidity.Field.accountId, acctId),
                new DBCondition(CmResValidity.Field.resourceId, 0, Operator.IS_NOT_NULL), new DBCondition(
                        CmResValidity.Field.recSts, EnumCodeDefine.IS_VALID_DATA, Operator.EQUALS), new DBOrCondition(
                        new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS),
                        new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT)));
        return result;
    }

    /**
     * @author: tangjl5
     * @Date: 2012-2-2
     */
    public List<CmResValidity> queryValidityBySoNbrUserId(Long soNbr)
    {
        return DBUtil.query(CmResValidity.class, new DBCondition(CmResValidity.Field.soNbr, soNbr));
    }

    /**
     * 根据模式ID获取组和模式对应关系实体
     */
    public SysGroupCyclePattern queryGroupCyclePartten(int parttenID) throws IMSException
    {
        SysGroupCyclePattern result = querySingle(SysGroupCyclePattern.class, new DBCondition(
                SysGroupCyclePattern.Field.patternId, parttenID));
        return result;
    }

    /**
     * 根据模式ID、起始状态、目标状态、促发时间获取状态流转实体
     */
    public SysCyclePatternTransfer queryLifeCyclePatternTransfer(int parttenID, Integer startAction, Integer osStsFrom,
            Long triggerEvevt) throws IMSException
    {
        SysCyclePatternTransfer result = querySingle(SysCyclePatternTransfer.class, new DBCondition(
                SysCyclePatternTransfer.Field.patternId, parttenID), new DBCondition(SysCyclePatternTransfer.Field.stsFrom,
                startAction), new DBCondition(SysCyclePatternTransfer.Field.osStsFrom, osStsFrom), new DBCondition(
                SysCyclePatternTransfer.Field.triggerEventType, triggerEvevt));
        return result;
    }

    public CmResLifecycle queryDealingResLifeCycleForTimer(Long resId)
    {
        return DBUtil.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, resId), new DBCondition(
                CmResLifecycle.Field.recSts, EnumCodeDefine.IN_DEALING));
    }

    /**
     * @author: tangjl5
     * @Date: 2012-1-5
     */
    public CmResLifecycle queryNewResLifeCycle(Long resId)
    {
        CmResLifecycle query = new CmResLifecycle();
        query.setResourceId(resId);

        return querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, resId), new DBCondition(
                CmResLifecycle.Field.validDate, context.getRequestDate(), Operator.LESS), new DBCondition(
                CmResLifecycle.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
    }

    /**
     * 根据传入的缓存标识，是从DB中获取，还是从缓存中获取 wukl 2011-12-26
     */
    public List<SysCyclePatternDetail> queryPatternDetails(List<Integer> stsList, Integer patternId, boolean cacheFlag)
    {
        List<SysCyclePatternDetail> details = null;
        if (cacheFlag)
        {
            details = new ArrayList<SysCyclePatternDetail>();
            for (Integer sts : stsList)
            {
                SysCyclePatternDetail detail = DBConfigInitBean.getSingleCached(SysCyclePatternDetail.class, new CacheCondition(
                        SysCyclePatternDetail.Field.sts, sts), new CacheCondition(SysCyclePatternDetail.Field.patternId,
                        patternId));
                details.add(detail);
            }
        }
        else
        {
            details = query(SysCyclePatternDetail.class, new DBCondition(SysCyclePatternDetail.Field.sts, stsList, Operator.IN),
                    new DBCondition(SysCyclePatternDetail.Field.patternId, patternId));
        }

        return details;
    }

    /**
     * 根据生命周期状态，模式ID查询模式详情实体
     * 
     * @param sts
     * @param patternID
     * @return
     */
    public SysCyclePatternDetail querySysCyclePatternDetailByStsID2Cache(Short sts, Integer patternID)
    {
        SysCyclePatternDetail detail = DBConfigInitBean.getSingleCached(SysCyclePatternDetail.class, new CacheCondition(
                SysCyclePatternDetail.Field.sts, sts.intValue()), new CacheCondition(SysCyclePatternDetail.Field.patternId,
                patternID));
        if (detail == null)
        {
            // 用户在sys_cycle_pattern_detail中未找到对应的模式
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, sts, patternID);
        }
        return detail;
    }

    public SysCyclePatternDetail querySysCyclePatternDetailByStsID(Short sts, Integer patternID)
    {
        return querySingle(SysCyclePatternDetail.class, new DBCondition(SysCyclePatternDetail.Field.sts, sts), new DBCondition(
                SysCyclePatternDetail.Field.patternId, patternID));
    }

    /**
     * 根据策略ID查询策略
     */
    public SysPolicy querySysPolicyByPolicy(Integer policyID)
    {
        return DBConfigInitBean.getSingleCached(SysPolicy.class, new CacheCondition(SysPolicy.Field.policyId, policyID));
    }

    /**
     * 查找用户的所属模式信息
     */
    public Integer queryLifeCycleNorInitSts(CmResource res, CmCustomer cust, CaAccount acct, Long mainProdOfferId)
    {
        SysGroupCyclePattern groupCyclePattern = this.queryCaGroupCyclePattern(res, cust, acct, mainProdOfferId);
//        if (groupCyclePattern == null)
//        {
//            return null;
//        }

        List<SysCyclePatternDef> sysCyclePatternDefs = query(SysCyclePatternDef.class, new OrderCondition[] { new OrderCondition(
                false, SysCyclePatternDef.Field.priority) }, null, new DBCondition(SysCyclePatternDef.Field.patternId,
                groupCyclePattern.getPatternId()));
        if (!CommonUtil.isEmpty(sysCyclePatternDefs))
        {
            Integer initSts = sysCyclePatternDefs.get(0).getNormalInitSts();
            if (initSts == null || initSts == 0)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.GROUP_NOT_HAVE_INITSTS, groupCyclePattern.getPatternId());
            }

            return initSts;
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.GROUP_NOT_HAVE_INITSTS, groupCyclePattern.getPatternId());
        }
    }

    public SysGroupCyclePattern queryCaGroupCyclePattern(CmResource res, CmCustomer cust, CaAccount acct)
    {
        return queryCaGroupCyclePattern(res, cust, acct, null);
    }

    /**
     * 查找用户所属的模式ID
     * 
     * @return 模式ID
     */
    public SysGroupCyclePattern queryCaGroupCyclePattern(CmResource res, CmCustomer cust, CaAccount acct, Long mainProdOfferId)
    {
        return queryCaGroupCyclePattern(res, cust, acct, mainProdOfferId, true);
    }

    public SysGroupCyclePattern queryCaGroupCyclePattern(CmResource res, CmCustomer cust, CaAccount acct, Long mainProdOfferId,
            boolean cacheFlag)
    {
        if (null == res)
        {
            // 用户不存在
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_EXIST);
        }

        // if (null == acct)
        // {
        // // 该用户没有对应的账户
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_IS_NULL);
        // }
        //
        // if (null == cust)
        // {
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.CUSTOMER_NOTEXIST);
        // }

        // 1、根据用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type匹配到group_id
        // GroupComponent grpCmp = context.getComponent(GroupComponent.class);
        // SysGroupDef groupDef = null;
        // if (cacheFlag)
        // {
        // groupDef = context.getComponent(CacheQuery.class).queryGroupIDByConditions(cust, res, acct);
        // }
        // else
        // {
        // groupDef = grpCmp.queryGroupIDByConditions(cust, res, acct);
        // }
        //
        // if (CommonUtil.isEmpty(groupDef))
        // {
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
        // }

        // 2、根据组匹配组对应的模式
        SysGroupCyclePattern groupCyclePattern = null;
        if (cacheFlag)
        {
            groupCyclePattern = context.getComponent(CacheQuery.class).queryGrpCycPatternByConditions(cust, res, acct,
                    mainProdOfferId);
        }
        else
        {
            groupCyclePattern = context.getComponent(GroupComponent.class).queryGrpCycPatternByConditions(cust, res, acct,
                    mainProdOfferId);
        }

        if (groupCyclePattern != null)
        {
            return groupCyclePattern;
        }
        else
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_GROUP_NOT_MATCHING_PATTERN, res.getResourceId());
        }
    }

    public List<CmResLifecycle> queryInvalidDate(String[] stsArr, int start, int end)
    {
        // @Date 2012-07-25 tangjl5 :Story # 用户级账本预付费用户主动暂停期间生命周期不自动流转。即在查询过期的数据时，需要过滤掉主动停机的非single balance用户
        List<CmResLifecycle> lifeCycleList = DBUtil.query(CmResLifecycle.class, null, new IntRange(start, end), new DBCondition(
                CmResLifecycle.Field.recSts, EnumCodeDefine.IS_VALID_DATA), new DBCondition(CmResLifecycle.Field.sts, stsArr,
                Operator.IN), new DBCondition(CmResLifecycle.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS),
                new DBCondition(CmResLifecycle.Field.userrequestFlag, EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG, Operator.EQUALS));
        
        
        return lifeCycleList;
        
    }

    /**
     * @author: tangjl5
     * @Date: 2011-10-10
     */
    public CaRescycReg queryCycleResReg(Long soNbr, Long userId)
    {
        CaRescycReg cycResReg = querySingle(CaRescycReg.class, new DBCondition(CaRescycReg.Field.soNbr,
                soNbr), new DBCondition(CaRescycReg.Field.resourceId, userId), new DBOrCondition(new DBCondition(
                CaRescycReg.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS), new DBCondition(
                CaRescycReg.Field.expireDate, context.getRequestDate(), Operator.GREAT)));
        return cycResReg;
    }

    /**
     * 根据账户ID获取有效
     * 
     * @author: tangjl5
     * @Date: 2011-11-25
     */
    public CmResValidity queryValidityByAcctId(Long acctId)
    {
        CmResValidity validity = querySingle(CmResValidity.class, new DBCondition(CmResValidity.Field.accountId, acctId),
                new DBCondition(CmResValidity.Field.resourceId, "0"), new DBCondition(CmResValidity.Field.recSts,
                        EnumCodeDefine.IS_VALID_DATA));
        return validity;
    }

    public CmResValidity queryValidityByUserId(Long userId)
    {
        CmResValidity validity = null;
        // ljc 修改
        // CaAccountRes acctRes = context.getComponent(AccountComponent.class).getBelongAcctResByUserId(userId);
        // if (null != acctRes)
        // {
        validity = queryUserValidity(userId);
        // }

        return validity;
    }

    /**
     * 如果账户有两个用户，则直接用账户的属性来获取group并匹配对应的最大有效期,如果账户只有一个用户，则使用用户属性来获取group并匹配对应的最大有效期
     * 
     * @author: tangjl5
     * @Date: 2011-12-3
     */
    public Long getMaxValidity(Long acctId)
    {
        SysGroupCredit grpCredit = getSysGroupCredit(acctId);
        if (grpCredit == null || !CommonUtil.isValid(grpCredit.getMaxValidity()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_MAX_VALIDITY_IS_NULL, acctId);
        }
        else
        {
            return grpCredit.getMaxValidity();
        }
    }

    /**
     * @Description: 根据账户ID查找sys_group_credit
     * @param acctId
     * @return
     * @author: tangjl5
     * @Date: 2012-5-10
     */
    public SysGroupCredit getSysGroupCredit(Long acctId)
    {
        List<CmResource> userList = context.getComponent(UserComponent.class).queryUsersByAcctID(acctId);
        SysGroupCredit grpCredit = null;
        try
        {
            CmCustomer cust = context.get3hTree().loadAcct3hBean(acctId).getCustomer();
            CaAccount acct = context.get3hTree().loadAcct3hBean(acctId).getAccount();    
        // a)如果账户有两个用户，则直接用账户的属性来获取group并匹配对应的最大有效期
        // b)如果账户只有一个用户，则使用用户属性来获取group并匹配对应的最大有效期
        if (CommonUtil.isEmpty(userList) || (CommonUtil.isNotEmpty(userList) && userList.size() > 1))
        {

            grpCredit = context.getComponent(ConfigQuery.class).queryGroupCredit(cust, acct, null, null);
        }
        else
        {
            Long mainProductOfferingId = (long) context.getComponent(BaseProductComponent.class).getUserMainProdOfferingId(
                    userList.get(0).getResourceId());
            grpCredit = context.getComponent(ConfigQuery.class).queryGroupCredit(cust, acct, userList.get(0),
                    mainProductOfferingId);
        }
        }
        catch(IMS3hNotFoundException e)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, acctId);
        }

        return grpCredit;
    }

    /**
     * 获取用户的停机保号产品
     */
    public CoProd getUserSuspendReqProd(Long userId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.queryProdListByUserId(userId, SpecCodeDefine.USER_SUSPEND_REQUEST);
        if (!CommonUtil.isEmpty(prodList))
        {
            return prodList.get(0);
        }

        return null;
    }


    /**
     * 根据来源状态，流转事件获取流转实体
     */
    public SysCyclePatternTransfer getLifeCycleStsToByEvent(Integer patternId, Integer event, CmResLifecycle curLifeCycle)
    {
        // 根据用户当前生命周期状态，促发事件，查询目标状态
        SysCyclePatternTransfer cyclePatternTrs = getSysCyclePatternTransfer(patternId, curLifeCycle.getSts(), event,
                curLifeCycle.getOsSts());
        if (cyclePatternTrs == null)
        {
            return null;
        }

        // 调用lua表达式，计算状态流转的条件是否满足
        if (cyclePatternTrs.getPolicyId() != null)
        {
            SysPolicy sysPolicy = querySysPolicyByPolicy(cyclePatternTrs.getPolicyId());
            String policyExpr = sysPolicy.getPolicyExpr();

            // 执行lua脚本，若计算结果为1则，流转成功，否则计算结果作为异常码抛出
            String result = JavaInvokeLua.executeLuaScript(policyExpr, packageLuaParam(curLifeCycle));
            if (!ConstantDefine.LUA_RUN_OK.equals(result))
            {
                // 状态转换非法，抛出异常
                throw IMSUtil.throwBusiException(Long.parseLong(result));
            }
        }

        return cyclePatternTrs;
    }

    public SysCyclePatternTransfer getfireEventType(Integer patternId, Integer event, Integer stsFrom, Integer stsTo,
            Integer osStsFrom, Integer osStsTo, CmResLifecycle curLifeCycle)
    {
        // 根据用户当前生命周期状态，促发事件，查询目标状态
        SysCyclePatternTransfer cyclePatternTrs = getSysCyclePatternTransfer(patternId, event, stsFrom, stsTo, osStsFrom, osStsTo);
        if (cyclePatternTrs == null)
        {
            return null;
        }

        // 调用lua表达式，计算状态流转的条件是否满足
        if (cyclePatternTrs.getPolicyId() != null)
        {
            SysPolicy sysPolicy = querySysPolicyByPolicy(cyclePatternTrs.getPolicyId());
            String policyExpr = sysPolicy.getPolicyExpr();

            // 执行lua脚本，若计算结果为1则，流转成功，否则计算结果作为异常码抛出
            String result = JavaInvokeLua.executeLuaScript(policyExpr, packageLuaParam(curLifeCycle));
            if (!ConstantDefine.LUA_RUN_OK.equals(result))
            {
                // 状态转换非法，抛出异常
                throw IMSUtil.throwBusiException(Long.parseLong(result));
            }
        }

        return cyclePatternTrs;
    }

    /**
     * 根据模式Id,状态来源，促发事件查找周期流转模式
     * 
     * @param patternId
     * @param stsFrom
     * @param triggerEventType
     * @return
     */
    public SysCyclePatternTransfer getSysCyclePatternTransfer(Integer patternId, Integer stsFrom, Integer triggerEventType,
            Integer osStsFrom)
    {
        SysCyclePatternTransfer cyclePatterTrs = DBConfigInitBean.getSingleCached(SysCyclePatternTransfer.class,
                new CacheCondition(SysCyclePatternTransfer.Field.patternId, patternId), new CacheCondition(
                        SysCyclePatternTransfer.Field.stsFrom, stsFrom), new CacheCondition(
                        SysCyclePatternTransfer.Field.osStsFrom, osStsFrom), new CacheCondition(
                        SysCyclePatternTransfer.Field.triggerEventType, triggerEventType));
        return cyclePatterTrs;
    }

    public SysCyclePatternTransfer getSysCyclePatternTransfer(Integer patternId, Integer event, Integer stsFrom, Integer stsTo,
            Integer osStsFrom, Integer osStsTo)
    {
    	List<CacheCondition> conList = new ArrayList<CacheCondition>();
    	if(patternId != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.patternId, patternId));
    	if(event != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.triggerEventType, event));
    	if(stsFrom != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.stsFrom, stsFrom));
    	if(stsTo != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.stsTo, stsTo));
    	if(osStsFrom != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.osStsFrom, osStsFrom));
    	if(osStsTo != null)
    		conList.add(new CacheCondition(SysCyclePatternTransfer.Field.osStsTo, osStsTo));
        SysCyclePatternTransfer cyclePatterTrs = DBConfigInitBean.getSingleCached(SysCyclePatternTransfer.class, conList.toArray(new CacheCondition[conList.size()]));
        return cyclePatterTrs;
    }

    /**
     * 根据模式Id,状态来源，os_sts来源，新生命周期状态查找新的os_sts
     * 
     * @author: tangjl5
     * @Date: 2012-3-2
     */
    public Integer getUserTagetOsts(Integer patternId, CmResLifecycle curLifeCycle, Integer triggerEventType, Integer stsTo)
    {
        SysCyclePatternTransfer cyclePatterTrs = DBConfigInitBean.getSingleCached(SysCyclePatternTransfer.class,
                new CacheCondition(SysCyclePatternTransfer.Field.patternId, patternId), new CacheCondition(
                        SysCyclePatternTransfer.Field.stsFrom, curLifeCycle.getSts()), new CacheCondition(
                        SysCyclePatternTransfer.Field.osStsFrom, curLifeCycle.getOsSts()), new CacheCondition(
                        SysCyclePatternTransfer.Field.triggerEventType, triggerEventType), new CacheCondition(
                        SysCyclePatternTransfer.Field.stsTo, stsTo));

        if (cyclePatterTrs == null)
        {
            // 状态转换非法，抛出异常
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_TARGET_OSSTS_IS_NULL, patternId, curLifeCycle.getSts(),
                    curLifeCycle.getOsSts(), stsTo, EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE);
        }

        // 调用lua表达式，计算状态流转的条件是否满足
        if (cyclePatterTrs.getPolicyId() != null)
        {
            SysPolicy sysPolicy = querySysPolicyByPolicy(cyclePatterTrs.getPolicyId());
            String policyExpr = sysPolicy.getPolicyExpr();

            // 执行lua脚本，若计算结果为1则，流转成功，否则计算结果作为异常码抛出
            String result = JavaInvokeLua.executeLuaScript(policyExpr, packageLuaParam(curLifeCycle));
            if (!ConstantDefine.LUA_RUN_OK.equals(result))
            {
                // 状态转换非法，抛出异常
                throw IMSUtil.throwBusiException(Long.parseLong(result));
            }
        }

        return cyclePatterTrs.getOsStsTo();
    }

    /**
     * 根据patternId,stsFrom,osStsFrom获取目标状态列表
     * 
     * @author: tangjl5
     * @Date: 2011-12-9
     */
    public List<SysCyclePatternTransfer> getUserTargetStsByStsFrom(Integer patternId, Integer stsFrom, Integer osStsFrom)
    {
        List<SysCyclePatternTransfer> cyclePatterTrs = query(SysCyclePatternTransfer.class, new DBCondition(
                SysCyclePatternTransfer.Field.patternId, patternId), new DBCondition(SysCyclePatternTransfer.Field.stsFrom,
                stsFrom), new DBCondition(SysCyclePatternTransfer.Field.osStsFrom, osStsFrom));
        return cyclePatterTrs;
    }

}
