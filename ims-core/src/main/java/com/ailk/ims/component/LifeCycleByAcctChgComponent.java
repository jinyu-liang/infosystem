package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;

/**
 * @Description: 修改用户有效期操作类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-9-27
 * @Date 2012-3-26 tangjl5 有效期天数返回两次，导致返回两个生命周期对象，但是第二次对象里的sts为空的问题
 * @Date 2012-4-12 tangjl5 添加forceFlag的空判断
 * @Date 2012-5-3 tangjl On_Site Defect #44675 去掉有效期已经小于当前时间的判断
 */
public class LifeCycleByAcctChgComponent extends BaseComponent
{

    public LifeCycleByAcctChgComponent()
    {
    }
    
    /**
     * @Description: 更新用户生命周期
     * @param lifeCycleReq
     * @return
     */
    public List<Object[]> updateValidityByTopUp(SLifeCycleByAcctChgReq lifeCycleReq, Short validInsFlag)
    {
        Long acctId = lifeCycleReq.getAcct_id();
        Long resId = lifeCycleReq.getResource_id();
        List<Object[]> returnData = new ArrayList<Object[]>();
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        
        if (!CommonUtil.isValid(acctId))
        {
            User3hBean ims3Hbean = context.get3hTree().loadUser3hBean(resId);
            acctId = ims3Hbean.getAcctId();
        }
        
        // 获取账户的最大有效期，对用户级账本充值时使用用户的归属账户对应的最大有效期
        Integer acctMaxValidity = lifeCycleCmp.getMaxValidity(acctId).intValue();
        
        if (null == acctMaxValidity)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_MAX_VALIDITY_IS_NULL, acctId); 
        }
        
        imsLogger.info("The max validity of account is " +acctMaxValidity, context);
        
        Integer extendDays = lifeCycleReq.getExtendDays();
        
        Integer validityChgDays = extendDays == null ? -lifeCycleReq.getReduceDays(): extendDays;
        
        // 用户级账本更新用户级有效期
        if (lifeCycleReq.getBalanceType() == EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER)
        {
            User3hBean ims3Hbean = context.get3hTree().loadUser3hBean(resId);
            // @Date 2012-10-09 tangjl5 调整有效期时，必须是预付费用户
            if (context.get3hTree().loadUser3hBean(resId).getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID
                    || context.get3hTree().loadUser3hBean(resId).getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.TRANSFER_TARGET_USER_BILLINGTYPE_ILLEGAL); 
            }
            CmResLifecycle resLifeCycle = ims3Hbean.getUserLifeCycle();
            CmResValidity curValidity = lifeCycleCmp.queryUserValidity(resId);
            if (resLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_IDLE_INITIAL)
            {
                return returnData;
            }
            if (!CommonUtil.isValid(resId))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resId");
            }
            
            validityChgDays = updateUserValidity(resId, acctId, lifeCycleReq, validityChgDays, acctMaxValidity, validInsFlag);
            
            if (validityChgDays == null)
            {
            	imsLogger.debug("The actual changes in the effective period is null", context);
                return null;
            }
            
            imsLogger.debug("The actual changes in the effective period is " +validityChgDays + " days", context);
            returnData.add(lifeCycleCmp.updateUserLifeCycleByValidity(resId, curValidity, lifeCycleReq, null, null, validityChgDays));
        }
        // 账户级账本更新账户级和用户级有效期
        else
        {
            if (!CommonUtil.isValid(acctId))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId");
            }
            Date acctExpireDate = null;
            CmResValidity curAcctValidity = lifeCycleCmp.queryAcctValidity(acctId);
            
            // 初始化账户级有效期
            if (null == curAcctValidity)
            {
                // 若没有用户级账本有效期，则使用请求时间来计算失效时间
                acctExpireDate = lifeCycleCmp.calculateExpireDateByExtendDays(context.getRequestDate(), validityChgDays.intValue()).getTime();
                
                acctExpireDate = computeExpireDateByRule(lifeCycleReq, acctExpireDate, null, acctId, acctMaxValidity);
                
                lifeCycleCmp.initAcctValidity4TopUp(acctId, acctExpireDate);
            }
            else // 更新账户级有效期
            {
                Date oldExpireDate = curAcctValidity.getExpireDate();
                Integer haveDays = DateUtil.getBetweenDays(context.getRequestDate(), oldExpireDate).intValue();
                // @Date 2012-4-9 tangjl5 若传入的有效期，或者延长后的有效期，或传入的延长的天数超过最大值，则抛出异常
                if (haveDays >= acctMaxValidity && validityChgDays > 0 && lifeCycleReq.getForceFlag() != null && lifeCycleReq.getForceFlag() == EnumCodeDefine.ADD_VALIDITY_NOT_FORCE)
                {
                    imsLogger.info("The period of validity of the user more than maximum:resourceId="+ resId +
                            ",maxValidity=" + acctMaxValidity + ",haveDays=" +haveDays, context);
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH,acctId); 
                }
                
                // Task #38047 如果有效期已经小于当前时间，则从当前时间开始延长有效期
                /*
                 *  @Date 2012-5-3 tangjl On_Site Defect #44675 去掉有效期已经小于当前时间的判断
                 *  top up时，若延长有效期时，若失效时间小于请求时间，则从当前时间开始延长，top up传入的validInsFlag为null
                 *  adjust validity 时validInsFlag有以下三种情况：
                 *  0-Validity will start from current expire date 
                 *  1-Validity will start from current system date
                 *  2-If subscriber has negative validity, validity will start from current system date
                 */
                if ((validityChgDays > 0 && validInsFlag == null && oldExpireDate.before(context.getRequestDate())) ||
                        (null != validInsFlag && validInsFlag == EnumCodeDefine.VALIDITY_START_FROM_CUR_SYSTEM_DATE ) || 
                        (oldExpireDate.before(context.getRequestDate()) && validInsFlag  == EnumCodeDefine.NEGATIVE_VALIDITY_START_FROM_CUR_SYSTEM_DATE))
                {
                    oldExpireDate = context.getRequestDate();
                }
                
                acctExpireDate = lifeCycleCmp.calculateExpireDateByExtendDays(oldExpireDate, validityChgDays.intValue()).getTime();
                
                acctExpireDate = computeExpireDateByRule(lifeCycleReq, acctExpireDate, oldExpireDate, acctId, acctMaxValidity);
                
                lifeCycleCmp.updateAcctValidity4TopUp(curAcctValidity, acctExpireDate);
                
                // @Date 2012-4-16 tangjl5 返回实际延长的天数
                validityChgDays = DateUtil.getBetweenDays(curAcctValidity.getExpireDate(), acctExpireDate).intValue();
            }
            
            // 更新账户下所有预付费用户的状态
            List<CmResource> prepaidList = context.getComponent(UserComponent.class).queryUserByBillAcctId(acctId);
            if (CommonUtil.isNotEmpty(prepaidList))
            {
                for (CmResource res : prepaidList)
                {
                    User3hBean ims3Hbean = context.get3hTree().loadUser3hBean(res.getResourceId());
                    CmResLifecycle resLifeCycle = ims3Hbean.getUserLifeCycle();
                    if (resLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_IDLE_INITIAL)
                    {
                        continue;
                    }
                    
                    if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
                    {
                        returnData.add(lifeCycleCmp.updateUserLifeCycleByValidity(res.getResourceId(),curAcctValidity, lifeCycleReq, null, null, validityChgDays));
                    }
                }
            }
            else
            {
                returnData.add(new Object[]{null, validityChgDays, null});
            }
        }
        return returnData;
    }
    
    /**
     * @Description: 更新用户级有效期
     *   
     * @author: tangjl5
     * @Date: 2012-2-2
     */
    public Integer updateUserValidity(Long resId, Long acctId, SLifeCycleByAcctChgReq lifeCycleReq, Integer validityChgDays, Integer acctMaxValidity, 
            Short validInsFlag)
    {
        Date expireDate = null;
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        // 查询用户级有效期
        CmResValidity curValidity = lifeCycleCmp.queryUserValidity(resId);
        // 用户下没有有效期则实例化有效期
        if (null == curValidity)
        {
            // 若没有用户级账本有效期，则使用请求时间来计算失效时间
            expireDate = lifeCycleCmp.calculateExpireDateByExtendDays(context.getRequestDate(), validityChgDays.intValue()).getTime();
            
            expireDate = computeExpireDateByRule(lifeCycleReq, expireDate, null, acctId, acctMaxValidity);
            
            // 初始化用户有效期
            lifeCycleCmp.initResValidity4TopUp(resId, acctId, expireDate);
        }
        else // 更新用户级有效期
        {
            Date oldExpireDate = curValidity.getExpireDate();
            Integer haveDays = DateUtil.getBetweenDays(context.getRequestDate(), oldExpireDate).intValue();
            if (haveDays >= acctMaxValidity && validityChgDays > 0)
            {
                validityChgDays = null;
                imsLogger.info("The period of validity of the user more than maximum:resourceId="+ resId +
                        ",maxValidity=" + acctMaxValidity + ",haveDays=" +haveDays, context);
                
                // @Date 2012-4-9 tangjl5 若传入的有效期，或者延长后的有效期，或传入的延长的天数超过最大值，则抛出异常
                // @Date 2012-4-12 tangjl5 添加forceFlag的空判断
                if (lifeCycleReq.getForceFlag() != null && lifeCycleReq.getForceFlag() == EnumCodeDefine.ADD_VALIDITY_NOT_FORCE)
                {                    
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH,acctId); 
                }
            }
            else
            {
                // Task #38047 如果有效期已经小于当前时间，则从当前时间开始延长有效期
                /*
                 *  @Date 2012-5-3 tangjl On_Site Defect #44675 去掉有效期已经小于当前时间的判断
                 *  top up时，若延长有效期时，若失效时间小于请求时间，则从当前时间开始延长，top up传入的validInsFlag为null
                 *  adjust validity 时validInsFlag有以下三种情况：
                 *  0-Validity will start from current expire date 
                 *  1-Validity will start from current system date
                 *  2-If subscriber has negative validity, validity will start from current system date
                 */
                if ((validityChgDays > 0 && validInsFlag == null && oldExpireDate.before(context.getRequestDate())) ||
                        (null != validInsFlag && validInsFlag == EnumCodeDefine.VALIDITY_START_FROM_CUR_SYSTEM_DATE ) || 
                        (oldExpireDate.before(context.getRequestDate()) && validInsFlag  == EnumCodeDefine.NEGATIVE_VALIDITY_START_FROM_CUR_SYSTEM_DATE))
                {
                    oldExpireDate = context.getRequestDate();
                }
                
                expireDate = lifeCycleCmp.calculateExpireDateByExtendDays(oldExpireDate, validityChgDays.intValue()).getTime();
                expireDate = computeExpireDateByRule(lifeCycleReq, expireDate, oldExpireDate, acctId, acctMaxValidity);
                lifeCycleCmp.updateResValidity4TopUp(curValidity, expireDate,null);
                
                // @Date 2012-4-16 tangjl5 返回实际延长的天数
                validityChgDays = DateUtil.getBetweenDays(curValidity.getExpireDate(), expireDate).intValue();
            }
        }
        
        return validityChgDays;
    }
    /**
     * @Description: 获取有效的失效时间
     * @param lifeCycleReq
     * @param expireDate
     * @param acctId
     * @param acctMaxValidity
     * @return   
     * @author: tangjl5
     * @Date: 2012-2-2
     */
    public Date computeExpireDateByRule(SLifeCycleByAcctChgReq lifeCycleReq, Date expireDate, Date oldExpireDate, Long acctId, Integer acctMaxValidity)
    {
        Integer extendDays = lifeCycleReq.getExtendDays();
        Integer validityChgDays = extendDays == null ? -lifeCycleReq.getReduceDays(): extendDays;
        
        // 计算后的有效期若小于请求时间，则根据forceFlag判断是是否强制扣减或扣减至小于请求时间
        if (validityChgDays < 0 && expireDate.before(context.getRequestDate()))
        {
            if (lifeCycleReq.getForceFlag() == null || lifeCycleReq.getForceFlag() == EnumCodeDefine.NOT_REDUCE_VALIDITY_FORCE)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH, acctId); 
            }
            
            // @Date 2012-4-12 tangjl5 添加forceFlag的空判断
            if (lifeCycleReq.getForceFlag() != null && lifeCycleReq.getForceFlag() == EnumCodeDefine.REDUCE_VALIDITY_FORCE_TO_REQDATE)
            {
                validityChgDays = validityChgDays + DateUtil.getBetweenDays(context.getRequestDate(), expireDate).intValue();
                expireDate = context.getRequestDate();
            }
        }
        else
        {
            // 判断延长的有效期是否大于配置的有效期的最大值
            if (CommonUtil.isValid(extendDays) && extendDays > acctMaxValidity)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH,acctId); 
            
            // 计算用户延长后的有效期长度，若延长后的有效期是否大于账户的最大有效期，则使用账户配置的最大有效期
            Integer allValidityDays = DateUtil.getBetweenDays(context.getRequestDate(), expireDate).intValue();
            if(allValidityDays > acctMaxValidity)
            {
                // @Date 2012-4-9 tangjl5 若传入的有效期，或者延长后的有效期，或传入的延长的天数超过最大值，则抛出异常
                // @Date 2012-4-12 tangjl5 添加forceFlag的空判断
                if (lifeCycleReq.getForceFlag() != null && lifeCycleReq.getForceFlag() == EnumCodeDefine.ADD_VALIDITY_NOT_FORCE)
                {                    
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_ACCOUNT_VALIDITY_IS_NOT_ENOUGH,acctId); 
                }
              
                validityChgDays = acctMaxValidity - DateUtil.getBetweenDays(context.getRequestDate(), oldExpireDate).intValue();
                if (null == oldExpireDate)
                {
                    oldExpireDate = context.getRequestDate();
                }
                expireDate = context.getComponent(BaseLifeCycleComponent.class).calculateExpireDateByExtendDays(oldExpireDate, validityChgDays).getTime();
            }
        }
        
        return expireDate;
    }
    /**
     * @Description: 更新用户级有效期 上海专用
     * @author: xieqr
     * @Date: 2012-5-29
     */
    public void updateUserValidity4SH(Long resId, Long acctId, Date expireDate,Integer isValid)
    {
        // 查询用户有效期
        CmResValidity curValidity = querySingle(CmResValidity.class, new DBCondition(CmResValidity.Field.resourceId, resId));
        
        // 用户下没有有效期则实例化有效期
        if (null == curValidity)
        {
            // 初始化用户有效期
            initUserValidity4Sh(resId, acctId, expireDate);
        }
        else
        {
            // 修改用户有效期
            updateUserValidity4Sh(curValidity, expireDate, isValid);

        }

    }

    /**
     * 初始化用户有效期
     * wukl 2012-11-16
     * @param resId
     * @param acctId
     * @param validityExpireDate
     */
    private void initUserValidity4Sh(Long resId, Long acctId, Date validityExpireDate)
    {
        CmResValidity resValidity = new CmResValidity();
        resValidity.setResourceId(resId);
        resValidity.setCreateDate(context.getRequestDate());
        resValidity.setSoNbr(context.getSoNbr());
        resValidity.setSoDate(context.getRequestDate());
        resValidity.setValidDate(context.getRequestDate());
        resValidity.setExpireDate(IMSUtil.getDefaultExpireDate());
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setEffectFlag(EnumCodeDefine.USER_VALIDITY_EFFECT);
        resValidity.setUserExpireDate(validityExpireDate);
        resValidity.setUserValidDate(context.getRequestDate());
        resValidity.setAccountId(0L);//上海不用，置默认值
        insert(resValidity);
    }
    
    /**
     * 修改用户有效期
     * wukl 2012-11-16
     * @param curValidity
     * @param expireDate
     * @param effectFlag
     */
    private void updateUserValidity4Sh(CmResValidity curValidity, Date expireDate,Integer effectFlag)
    {
        CmResValidity newValid = new CmResValidity();
        if(expireDate!=null){
            newValid.setUserExpireDate(expireDate);
        }
        if (effectFlag!=null)
        {
            newValid.setEffectFlag(effectFlag);
        }
        
        updateByCondition(newValid, new DBCondition(CmResValidity.Field.resourceId, curValidity.getResourceId()));
    }
}
