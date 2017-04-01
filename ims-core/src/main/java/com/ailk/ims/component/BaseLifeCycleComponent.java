package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.helper.LifeCycleHelper;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.component.query.LifeCycleQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.AlarmCodeDefine;
import com.ailk.ims.define.BusiCodeDefine;
import com.ailk.ims.define.BusiSpecDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.Cust3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaRescycReg;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.ImsBusiStsControl;
import com.ailk.openbilling.persistence.cust.entity.ImsCreditStatusSync;
import com.ailk.openbilling.persistence.cust.entity.ImsResLifecycleNotify;
import com.ailk.openbilling.persistence.cust.entity.ImsResLifecycleNotifyRst;
import com.ailk.openbilling.persistence.cust.entity.ImsResStsSync;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsintf.entity.SBalanceList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SRemoveReq;
import com.ailk.openbilling.persistence.imsintf.entity.SUserStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChg4Abm;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternDetail;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternTransfer;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCredit;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;

/**
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-1 2011-08-01 wuyujie : 增加initUserLifeCycle方法
 * @Date 2011-8-30 tangjl5: 修改定期任务的新状态的生效天数
 * @Date 2011-1-15 tangjl5: 帐管top-up时，只有修改用户状态后，记录到用户状态同步给CRM的接口表中
 * @Date 2011-9-30 tangjl5: 帐管top-up时，预付费用户若表中没有配置最大有效期则抛出异常
 * @Date 2011-10-19 tangjl5: 若用户的生命周期状态为active时，则将生命周期的失效时间与用户有效期的失效时间一致，避免cm_res_validity未过期时，active状态已失效
 * @Date 2012-3-9 tangjl5 预付费的fraud_flag为1需要订购用户主动停机产品,订购规格类型为123的产品,为0时，退订主动停机产品
 * @Date 2012-3-16 wukl 清理余额和免费资源接口合并及调整
 * @Date 2012-3-22 tangjl Bug #42070 过期用户，调整有效期的时候，账本有效期的生效时间写的不对，不应该使用当前时间
 * @Date 2012-3-30 wangjt: 修改isEmpty方法判断对象的问题
 * @Date 2012-3-29 tangjl5 用户主动停机时，reason_days不能为空
 * @Date 2012-4-2 tangjl5 Bug #40000 将数据库中nextSts的存储形式修改为同mdb中的格式一致
 * @Date 2012-4-4 tangjl5 预后付费在新装时都设置下一个状态，下一个状态与当前状态一致
 * @Date 2012-4-5 tangjl5 修改用户没有获取到下一个sts问题
 * @Date 2012-4-7 tangjl5 增加判断接口是否收取一次性费用的方法
 * @Date 2012-4-12 tangjl5 Task #44143 ChangeOwner，changePaymentMode时，若用户没有有效期则，则根据active状态的有效天数来初始化用户的有效期
 * @Date 2012-4-23 tangjl5 修改异常提示
 * @Date 2012-04-24 lijc3 上海需求，停机需要取消停机保号产品，立即失效,订购的停机保号产品要下周期生效
 * @Date 2012-5-11 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
 * @Date 2012-05-15 wuyujie ：转移lifeCycleChgByABM方法至LifeCycleChgByABMBusiBean
 * @Date 2012-05-15 wangdw5 非内部接口 验证main3hbean业务状态
 * @Date 2012-05-15 yangjh checkMain3hBeanBusiState方法增加判断，避免空指针
 * @Date 2012-05-28 wangdw5:[46866]IMS_BUSI_STS_CONTROL 中level字段为Oracle关键字
 * @Date 2012-6-1 yangjh story：46326 状态从1003改1001 不用修改 直接返回
 * @Date 2012-06-01 zhangzj3 规范表名，ImsResLifecycleOnceNotify表在加结果表的时候显示表名过长，所以改为ImsResLifecycleNotify
 * @Date 2012-6-8 tangjl5 Bug #48034 用户的归属账户从用户级账本转移到账户级账本，有效期前后没有变化
 * @Date 2012-06-14 wukl 增加生命周期状态停机位的上发
 * @Date 2012-06-19 yangjh 状态从1003改1001 不用修改 直接返回 增加日志输出
 * @Date 2012-6-21 tangjl5 BUG #45585 用户next_sts设置不对
 * @Date 2012-07-04 yangjh bug：50191 通知扣费：生命周期busi_flag=1 其他情况为0
 * @date 2012-07-07 luojb 获取告警id参考channelId #50629 
 * @date 2012-07-20 luojb 应该取ImsResStsSync的sequence
 * @Date 2012-08-14 wangdw5 :[54028]suspend date & suspend date & source system
 * @Date 2012-08-16 tangjl5 :Bug # 52819 change owner 转移有效期时，应该使用用户的change owner后的新账户
 * @Date 2012-09-13 wangdw5 : #59025: AdjustValid报错 0自动补齐到000格式
 * @Date 2012-09-26 wangdw5 : #60031 [54028]设计中,以下逻辑仅针对预付费
 * @Date 2012-11-07 zengxr #64264 if validity change, expire date of active also need change
 * @Date 2012-11-07 tangjl5 有效期的长度决定状态的变更
 */
public class BaseLifeCycleComponent extends LifeCycleQuery
{
    public BaseLifeCycleComponent()
    {
    }

    /**
     * @Description: 有定时器更新用户生命周期
     */
    public void updateUserLifeCycleByTimer(User3hBean user3hBean, CmResLifecycle curResLifecycle,
            Integer event)
    {
        CmResource res = user3hBean.getUser();
        CaAccount acct = user3hBean.getAccount();
        CmCustomer cust = user3hBean.getCustomer();
        
        // 查询改目标状态的对应的策略，并执行
        SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

//        if (null == groupCyclePatten)
//        {
//            // 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
//        }

        // 根据用户当前生命周期状态，促发事件，查询目标状态
        SysCyclePatternTransfer transfer = getLifeCycleStsToByEvent(groupCyclePatten.getPatternId(), event, curResLifecycle);

        if (transfer == null)
        {
            // 状态转换非法，抛出异常
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, res.getResourceId(),
                    groupCyclePatten.getPatternId(), curResLifecycle.getSts(), curResLifecycle.getOsSts(), event);
        }
        
        // @date 2012-8-6 tangjl5 若是对用户销户，则直接调用terminal 方法进行terminal
        if (transfer.getStsTo() == EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
            terminalUser(res.getResourceId());
            return;
        }

        SysCyclePatternDetail sysCycPatternDetail = querySysCyclePatternDetailByStsID2Cache(transfer.getStsTo().shortValue(),
                groupCyclePatten.getPatternId());

//        if (null == sysCycPatternDetail)
//        {
//            // 用户在sys_cycle_pattern_detail中未找到对应的模式
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, transfer.getStsTo(),
//                    groupCyclePatten.getPatternId());
//        }
        // @Date 2011-8-30: 修改定期任务的新状态的生效天数
        Calendar lifeCycleExpireCal = calculateExpireDateByExtendDays(context.getRequestDate(),
                sysCycPatternDetail.getValidDays());

        CmResLifecycle newCmResLifeCycle = new CmResLifecycle();
        newCmResLifeCycle.setSts(transfer.getStsTo());

        // disable状态 调用帐管接口清理资金和免费资源 2011-10-31 wukl
        // @Date 2012-3-16 wukl 清理余额和免费资源接口合并及调整
        if (transfer.getStsTo() == EnumCodeDefine.LIFECYCLE_DISABLE)
        {
            String fireEventType = null;
            if (transfer.getFireEventType() != null)
                //@Date 2012-09-13 wangdw5 : #59025: AdjustValid报错 0自动补齐到000格式
                fireEventType = CommonUtil.string2String5Bit(transfer.getFireEventType());
            else
                fireEventType = SysUtil.getString(ConstantDefine.CLEAN_BALANCE_FREERESOURCE_CONFIG);
            clearDisabledAsset(acct.getAcctId(), res.getResourceId(), fireEventType,null);
        }
        newCmResLifeCycle.setOsSts(transfer.getOsStsTo());
        newCmResLifeCycle.setResourceId(res.getResourceId());
        newCmResLifeCycle.setExpireDate(lifeCycleExpireCal.getTime());
        newCmResLifeCycle.setSoNbr(context.getSoNbr());
        newCmResLifeCycle.setValidDate(context.getRequestDate());

        CmResLifecycle updateLifeCycle = modifyUserLifeCycle4Timer(newCmResLifeCycle, acct.getAcctId(), curResLifecycle);

        // 将CM_RES_LIFECYCLE插入CA_CYCLE_RESOURCE_REG中
        insertResLifeCycReg(curResLifecycle, updateLifeCycle);

        String phoneId = user3hBean.getPhoneId();
        insertUserStsSync(packageCmResStsSync4Timer(updateLifeCycle, curResLifecycle, acct.getAcctId(), phoneId, event,
                res.getBillingType()));

        // 调用短信接口
        // wukl 2011-10-27 首次激活不需发送生命周期状态变更短信
        // wukl 2012-04-11 首次激活判断增加上海接口的busi_code
        if (!context.getComponent(FirstActiveComponent.class).isFirstActvie()
                && updateLifeCycle.getSts() != curResLifecycle.getSts().intValue())
        {
            IMSUtil.doNotification(createNotifications(res, acct, cust, updateLifeCycle, curResLifecycle), context);
        }

        // 状态失效提前告警时间计算，并添加到IMS_RES_LIFECYCLE_ONCE_NOTIFY表中
        if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            lifeCyclePreNotificationSync(updateLifeCycle);
        }

    }
    
    /**
     * @Description: 销户操作
     * @param userId     
     * @author: tangjl5
     * @Date: 2012-7-26
     */
    public void terminalUser(Long userId)
    {
    	BaseBusiBean bean = (BaseBusiBean)BusiUtil.getBusiBean(BusiCodeDefine.TERMINATE_USER);
        SRemoveReq removeReq = new SRemoveReq();
        String phoneId = context.get3hTree().loadUser3hBean(userId).getPhoneId();
        removeReq.setPhone_id(phoneId);
        removeReq.setDrop_promotion_flag(EnumCodeDefine.TERMINAL_USER_DROP_EXIST_PROMOTION_SERVICE);
        removeReq.setReset_flag(EnumCodeDefine.TERMINAL_USER_RESET_BUDGET);
        Object[] inputObject = new Object[]{removeReq};
        bean.setContext(context);
        bean.init(inputObject);
        bean.doBusiness(inputObject);
    }

    /**
     * @Description: 组装用户状态变更数据4timer
     */
    private ImsResStsSync packageCmResStsSync4Timer(CmResLifecycle newResLifeCycle, CmResLifecycle oldResLifeCycle, Long acctId,
            String phoneId, Integer triggerEventType, Integer billingType)
    {
        ImsResStsSync resStsSync = new ImsResStsSync();
        resStsSync.setAcctId(acctId);
        resStsSync.setCreateDate(context.getRequestDate());
        resStsSync.setNexpireDate(newResLifeCycle.getExpireDate());
        resStsSync.setPexpireDate(oldResLifeCycle.getExpireDate());
        resStsSync.setNsts(newResLifeCycle.getSts());
        resStsSync.setPsts(oldResLifeCycle.getSts());
        resStsSync.setPhoneId(phoneId);
        resStsSync.setResourceId(newResLifeCycle.getResourceId());
        resStsSync.setReasonCode(LifeCycleHelper.getImsResStsSyncReasonCode(triggerEventType, null));
        resStsSync.setNsubType(newResLifeCycle.getOsSts());
        resStsSync.setPsubType(oldResLifeCycle.getOsSts());
        resStsSync.setChangeTime(context.getRequestDate());

        if (newResLifeCycle.getUnbillingFlag() != null
                && newResLifeCycle.getUnbillingFlag() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
        {
            resStsSync.setNsubType(3);
        }
        else if (newResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY)
        {
            resStsSync.setNsubType(1);
        }
        else if (newResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY)
        {
            resStsSync.setNsubType(2);
        }
        else
        {
            resStsSync.setNsubType(0);
        }

        if (newResLifeCycle.getUnbillingFlag() != null
                && oldResLifeCycle.getUnbillingFlag() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
        {
            resStsSync.setPsubType(3);
        }
        else if (oldResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY)
        {
            resStsSync.setPsubType(1);
        }
        else if (oldResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY)
        {
            resStsSync.setPsubType(2);
        }
        else
        {
            resStsSync.setPsubType(0);
        }

        if (EnumCodeDefine.USER_PAYMODE_PREPAID == billingType)
        {
            resStsSync.setNuserrequestFlag(newResLifeCycle.getUserrequestFlag());
            resStsSync.setPuserrequestFlag(oldResLifeCycle.getUserrequestFlag());
            resStsSync.setNfrauldFlag(newResLifeCycle.getFrauldFlag());
            resStsSync.setPfrauldFlag(oldResLifeCycle.getFrauldFlag());
        }
        return resStsSync;
    }

    /**
     * @Description: 添加用户状态变更数据
     * @param ImsResStsSync ljc 11.30 添加没有值传入设置为默认值
     */
    public void insertUserStsSync(ImsResStsSync imsResStsSync)
    {
        ImsResStsSync resStsSync = new ImsResStsSync();
        resStsSync.setAcctId(imsResStsSync.getAcctId());

        resStsSync.setCreateDate(imsResStsSync.getCreateDate());

        if (imsResStsSync.getNbalance() != null)
        {
            resStsSync.setNbalance(imsResStsSync.getNbalance());
        }
        resStsSync.setChangeTime(context.getRequestDate());
        resStsSync.setNexpireDate(imsResStsSync.getNexpireDate());
        resStsSync.setNsts(imsResStsSync.getNsts());

        if (imsResStsSync.getPbalance() != null)
        {
            resStsSync.setPbalance(imsResStsSync.getPbalance());
        }

        resStsSync.setPexpireDate(imsResStsSync.getPexpireDate());
        resStsSync.setPhoneId(imsResStsSync.getPhoneId());
        resStsSync.setPsts(imsResStsSync.getPsts());
        resStsSync.setReasonCode(imsResStsSync.getReasonCode());
        resStsSync.setResourceId(imsResStsSync.getResourceId());
        resStsSync.setSoNbr(context.getSoNbr());
        // 2012-07-20 luojb 应该取ImsResStsSync的sequence
        resStsSync.setId(DBUtil.getSequence(ImsResStsSync.class));
        if (imsResStsSync.getNfrauldFlag() != null)
            resStsSync.setNfrauldFlag(imsResStsSync.getNfrauldFlag());

        if (imsResStsSync.getNsubType() != null)
            resStsSync.setNsubType(imsResStsSync.getNsubType());

        if (imsResStsSync.getNuserrequestFlag() != null)
            resStsSync.setNuserrequestFlag(imsResStsSync.getNuserrequestFlag());
        if (imsResStsSync.getPuserrequestFlag() != null)
            resStsSync.setPuserrequestFlag(imsResStsSync.getPuserrequestFlag());
        if (imsResStsSync.getPfrauldFlag() != null)
            resStsSync.setPfrauldFlag(imsResStsSync.getPfrauldFlag());

        if (imsResStsSync.getPsubType() != null)
            resStsSync.setPsubType(imsResStsSync.getPsubType());

        resStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        insert(resStsSync);
    }

    /**
     * @Description: 撤单操作更改有效期，与用户状态
     * @author: tangjl5
     * @Date: 2011-12-13
     * @Date: 2012-2-2 实现用户级账本和账户级账本的撤单
     */
    public void unDoUserValidity(SLifeCycleByAcctChgReq lifeCycleReq, Date unDealDate, Long unDealSoNbr)
    {
        Long acctId = lifeCycleReq.getAcct_id();
        Long resId = lifeCycleReq.getResource_id();
        List<Object[]> returnData = new ArrayList<Object[]>();
        LifeCycleByAcctChgComponent validityChg = context.getComponent(LifeCycleByAcctChgComponent.class);

        User3hBean user3Hbean = null;
        if (CommonUtil.isValid(resId))
        {
            user3Hbean = context.get3hTree().loadUser3hBean(resId);
            acctId = user3Hbean.getAcctId();
        }

        // 获取账户的最大有效期，对用户级账本充值时使用用户的归属账户对应的最大有效期
        Integer acctMaxValidity = getMaxValidity(acctId).intValue();

        Integer extendDays = lifeCycleReq.getExtendDays();
        Integer validityChgDays = extendDays == null ? -lifeCycleReq.getReduceDays() : extendDays;

        // 用户级有效期撤单
        if (lifeCycleReq.getBalanceType() == EnumCodeDefine.ACCOUNT_VALIDITY_TYPE_USER
                && user3Hbean.getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            CmResValidity curValidity = queryUserValidity(resId);
            validityChg.updateUserValidity(resId, acctId, lifeCycleReq, validityChgDays, acctMaxValidity, null);

            // 更新用户生命周期状态
            updateUserLifeCycleByValidity(resId, curValidity, lifeCycleReq, unDealSoNbr, unDealDate, validityChgDays);
            returnData.add(new Object[] { null, validityChgDays, null });
        }
        // 账户级账本有效期撤单
        else
        {
            if (!CommonUtil.isValid(acctId))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId");
            }
            Date acctExpireDate = null;
            CmResValidity curAcctValidity = queryAcctValidity(acctId);

            // 初始化账户级有效期
            if (null == curAcctValidity)
            {
                // 若没有用户级账本有效期，则使用请求时间来计算失效时间
                acctExpireDate = calculateExpireDateByExtendDays(context.getRequestDate(), validityChgDays.intValue()).getTime();

                acctExpireDate = validityChg.computeExpireDateByRule(lifeCycleReq, acctExpireDate, null, acctId, acctMaxValidity);

                // 初始化用户有效期
                initAcctValidity4TopUp(acctId, acctExpireDate);
            }
            else
            // 更新账户级有效期
            {
                acctExpireDate = calculateExpireDateByExtendDays(curAcctValidity.getExpireDate(), validityChgDays.intValue())
                        .getTime();

                acctExpireDate = validityChg.computeExpireDateByRule(lifeCycleReq, acctExpireDate,
                        curAcctValidity.getExpireDate(), acctId, acctMaxValidity);

                updateAcctValidity4TopUp(curAcctValidity, acctExpireDate);
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
                        returnData.add(updateUserLifeCycleByValidity(res.getResourceId(), curAcctValidity, lifeCycleReq, null,
                                null, validityChgDays));
                    }
                }
            }
        }
    }

    /**
     * @Description: 根据有效期更新用户状态
     * @param res
     * @param acct
     * @param cust
     * @param lifeCycleReq
     * @param unDoSoNbr
     * @param unDealDate
     * @return
     * @author: tangjl5
     * @Date: 2012-3-1 tangjl5设值有效期变更前天数和变更后天数
     * @Date: 2011-10-11
     */
    public Object[] updateUserLifeCycleByValidity(Long resId, CmResValidity oldValidity, SLifeCycleByAcctChgReq lifeCycleReq,
            Long unDoSoNbr, Date unDealDate, Integer validityChgDays)
    {
        User3hBean ims3Hbean = context.get3hTree().loadUser3hBean(resId);
        CmResource res = ims3Hbean.getUser();
        CaAccount acct = ims3Hbean.getAccount();
        CmCustomer cust = ims3Hbean.getCustomer();

        Date validiExpireDate = null;
        if (lifeCycleReq.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
        {
            validiExpireDate = queryUserValidity(resId).getExpireDate();
        }
        else
        {
            validiExpireDate = queryAcctValidity(lifeCycleReq.getAcct_id()).getExpireDate();
        }
        CmResLifecycle curResLifecycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
//        if (curResLifecycle == null)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, res.getResourceId());
//        }

        if (curResLifecycle.getSts() == EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
            throw IMSUtil
                    .throwBusiException(ErrorCodeDefine.LIFECYCLE_TERMINAL_USER_CAN_NOT_ADJUST_VALIDITY, res.getResourceId());
        }

        CmResLifecycle newCmResLifeCycle = new CmResLifecycle();
        newCmResLifeCycle.setResourceId(res.getResourceId());
        newCmResLifeCycle.setSoNbr(context.getSoNbr());
        newCmResLifeCycle.setValidDate(context.getRequestDate());

        SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

//        if (null == groupCyclePatten)
//        {
//            // 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
//        }
//        Integer curSts = curResLifecycle.getSts();
        Integer stsTo = curResLifecycle.getSts();
        Integer osStsTo = curResLifecycle.getOsSts();
        Date epireDateTo = null;

        SysCyclePatternDetail suspendDetail = querySysCyclePatternDetailByStsID2Cache(
                (short) EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY, groupCyclePatten.getPatternId());
        SysCyclePatternDetail disableDetail = querySysCyclePatternDetailByStsID2Cache((short) EnumCodeDefine.LIFECYCLE_DISABLE,
                groupCyclePatten.getPatternId());
        SysCyclePatternDetail terminalDetail = querySysCyclePatternDetailByStsID2Cache((short) EnumCodeDefine.LIFECYCLE_TERMINAL,
                groupCyclePatten.getPatternId());
        
        int suspendDays = suspendDetail.getValidDays();
        int disableDays = disableDetail.getValidDays();
        int terminalDays = terminalDetail.getValidDays();

        imsLogger.debug("The validity days of suspned is  ", suspendDetail.getValidDays()
                , "; The validity days of disable is", disableDetail.getValidDays()
                , "; The validity days of termianl is", terminalDetail.getValidDays());
        int differDays = DateUtil.getBetweenDays(context.getRequestDate(), validiExpireDate).intValue();
        imsLogger.debug("The days between requset date and target validity expire date is ", differDays);
        
        // @Date 2012-11-07 tangjl5 有效期的长度决定状态的变更
        if (differDays > 0)
        {
            stsTo = EnumCodeDefine.LIFECYCLE_ACTIVE;
            osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE;
            epireDateTo = validiExpireDate;
        }
        else
        {
            differDays = -differDays;
            if (differDays <= suspendDays)
            {
                stsTo = EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY;
                osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY;
                epireDateTo = calculateExpireDateByExtendDays(context.getRequestDate(), suspendDays - differDays).getTime(); 
            }
            else if (suspendDays < differDays  && differDays <= (suspendDays + disableDays))
            {
                stsTo = EnumCodeDefine.LIFECYCLE_DISABLE;
                osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY;
                epireDateTo = calculateExpireDateByExtendDays(context.getRequestDate(), (suspendDays + disableDays - differDays)).getTime(); 
            }
            else if ((suspendDays + disableDays) < differDays && differDays <= (suspendDays + disableDays + terminalDays))
            {
                stsTo = EnumCodeDefine.LIFECYCLE_TERMINAL;
                osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY;
                epireDateTo = calculateExpireDateByExtendDays(context.getRequestDate(), (suspendDays + disableDays + terminalDays - differDays)).getTime(); 
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_CAN_NOT_DEDUCT_AFTER_TERMINAL, res.getResourceId());
            }
        }
        
        newCmResLifeCycle.setSts(stsTo);
        newCmResLifeCycle.setOsSts(osStsTo);

        // 若用户的生命周期状态为active时，则将生命周期的失效时间与用户有效期的失效时间一致，避免cm_res_validity未过期时，active状态已失效
        if (epireDateTo != null)
        {
            newCmResLifeCycle.setExpireDate(epireDateTo);
        }

        if (stsTo == EnumCodeDefine.LIFECYCLE_ACTIVE)
        {
            newCmResLifeCycle.setExpireDate(validiExpireDate);
        }
        //@Date 2012-11-06 yugb :调整有效期至用户为termianl状态问题
        if(stsTo == EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
        	terminalUser(resId);
        }
        // disable状态 调用帐管接口清理资金和免费资源 2011-10-31 wukl
        // @Date 2012-3-16 wukl 清理余额和免费资源接口合并及调整
      //disable清理免费资源，balance，已经清理了一次，就不用再清了
        if(curResLifecycle.getSts() != EnumCodeDefine.LIFECYCLE_DISABLE || stsTo != EnumCodeDefine.LIFECYCLE_DISABLE)
        {
	        if (stsTo == EnumCodeDefine.LIFECYCLE_DISABLE)
	        {
	            // 根据用户当前生命周期状态，促发事件，查询目标状态
	            SysCyclePatternTransfer transfer = getfireEventType(groupCyclePatten.getPatternId(),
	                    EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE, curResLifecycle.getSts(), stsTo, curResLifecycle.getOsSts(),
	                    osStsTo, curResLifecycle);
	
	            if (transfer == null)
	            {
	                // 状态转换非法，抛出异常
	                // @Date 2012-4-23 tangjl5 修改异常提示
	                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, res.getResourceId(),groupCyclePatten.getPatternId(),
	                        curResLifecycle.getSts(), stsTo, EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE);
	            }
	
	            String fireEventType = null;
	            if (transfer.getFireEventType() != null)
	                //@Date 2012-09-13 wangdw5 : #59025: AdjustValid报错 0自动补齐到000格式
	                fireEventType = CommonUtil.string2String5Bit(transfer.getFireEventType());
	            else
	                fireEventType = SysUtil.getString(ConstantDefine.CLEAN_BALANCE_FREERESOURCE_CONFIG);
	            clearDisabledAsset(acct.getAcctId(), res.getResourceId(), fireEventType,null);
	            /*
	             * // 清理余额 ：0 账户级；1 用户级；3 所有用户和账户；默认为账户级 balanceCmp.cleanBalance(acct.getAcctId(), res.getResourceId(),
	             * EnumCodeDefine.CLEAN_BALANCE_TYPE_USER); balanceCmp.clearOtFreeRes(acct.getAcctId(), res.getResourceId(),
	             * EnumCodeDefine.CLEAR_OTFREERES_ACTFLAG_ALL);
	             */
	        }
        }
        CmResLifecycle insertLifecycle = null;
        if(stsTo != EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
	        insertLifecycle = modifyUserLifeCycle(new SUserStatus(), newCmResLifeCycle, curResLifecycle);
	
	        // 将CM_RES_LIFECYCLE插入CA_CYCLE_RESOURCE_REG中
	        insertResLifeCycReg4UnDeal(curResLifecycle, insertLifecycle, unDoSoNbr, unDealDate);
	
	        if (insertLifecycle.getSts() != curResLifecycle.getSts())
	        {
	            insertUserStsSync(packageCmResStsSync4Event(insertLifecycle, curResLifecycle, acct.getAcctId(),
	                    ims3Hbean.getPhoneId(), lifeCycleReq, res.getBillingType()));
	        }
        }
        else if(stsTo == EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
        	User3hBean bean = context.get3hTree().loadUser3hBean(resId);
        	insertLifecycle = bean.getUserLifeCycle();
        }

        // @Date 2011-11-11 tangjl5 若预付费用户状态为active时，则计算suspend-no-balnace/validity和disable状态的生效、失效时间、
        Map<String, Date> datePeriod = calculateActiveSuspendDisabelStopTime(insertLifecycle.getSts(),
                insertLifecycle.getValidDate(), insertLifecycle.getExpireDate(), groupCyclePatten.getPatternId());

        return new Object[] { insertLifecycle, validityChgDays, datePeriod };
    }

    /**
     * @Description: 根据用户状态、生效时间、失效时间计算用户的active，suspend，disable状态的时间段
     * @param curSts
     * @param curValidDate
     * @param curExpireDate
     * @param patternId
     * @return
     * @author: tangjl5
     * @Date: 2011-11-11
     */
    public Map<String, Date> calculateActiveSuspendDisabelStopTime(Integer curSts, Date curValidDate, Date curExpireDate,
            Integer patternId)
    {
        return calculateActiveSuspendDisabelStopTime(curSts, curValidDate, curExpireDate, patternId, false);
    }

    public Map<String, Date> calculateActiveSuspendDisabelStopTime(Integer curSts, Date curValidDate, Date curExpireDate,
            Integer patternId, boolean cacheFlag)
    {
        List<Integer> stsList = new ArrayList<Integer>();
        stsList.add(EnumCodeDefine.LIFECYCLE_ACTIVE);
        stsList.add(EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY);
        stsList.add(EnumCodeDefine.LIFECYCLE_DISABLE);

        List<SysCyclePatternDetail> details = queryPatternDetails(stsList, patternId, cacheFlag);

        Integer activeValidDays = null;
        Integer suspendValidDays = null;
        Integer disableValidDays = null;

        if (null == details)
        {
            throw IMSUtil.throwBusiException(
                    ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST,
                    String.valueOf(EnumCodeDefine.LIFECYCLE_ACTIVE) + ","
                            + String.valueOf(EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY) + ","
                            + String.valueOf(EnumCodeDefine.LIFECYCLE_DISABLE), patternId);
        }

        for (SysCyclePatternDetail detail : details)
        {
            if (detail.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE)
            {
                activeValidDays = detail.getValidDays();
            }
            else if (detail.getSts() == EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY)
            {
                suspendValidDays = detail.getValidDays();
            }
            else if (detail.getSts() == EnumCodeDefine.LIFECYCLE_DISABLE)
            {
                disableValidDays = detail.getValidDays();
            }
        }
        if (activeValidDays == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST,
                    String.valueOf(EnumCodeDefine.LIFECYCLE_ACTIVE), patternId);
        }

        if (suspendValidDays == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST,
                    String.valueOf(EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY), patternId);
        }

        if (disableValidDays == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST,
                    String.valueOf(EnumCodeDefine.LIFECYCLE_DISABLE), patternId);
        }

        Date activeValidDate = null;
        Date activeExpireDate = null;
        Date suspendExpireDate = null;
        Date disableExpireDate = null;
        if (curSts.intValue() == EnumCodeDefine.LIFECYCLE_ACTIVE)
        {
            activeValidDate = curValidDate;
            activeExpireDate = curExpireDate;
            suspendExpireDate = calculateExpireDateByExtendDays(curExpireDate, suspendValidDays).getTime();
            disableExpireDate = calculateExpireDateByExtendDays(suspendExpireDate, disableValidDays).getTime();
        }
        else if (curSts.intValue() == EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY)
        {
            suspendExpireDate = curExpireDate;
            activeValidDate = calculateExpireDateByExtendDays(curValidDate, -activeValidDays).getTime();
            activeExpireDate = curValidDate;
            disableExpireDate = calculateExpireDateByExtendDays(suspendExpireDate, disableValidDays).getTime();
        }
        else if (curSts.intValue() == EnumCodeDefine.LIFECYCLE_DISABLE)
        {
            activeExpireDate = calculateExpireDateByExtendDays(curValidDate, -suspendValidDays).getTime();
            activeValidDate = calculateExpireDateByExtendDays(activeExpireDate, -activeValidDays).getTime();
            suspendExpireDate = curValidDate;
            disableExpireDate = curExpireDate;
        }

        Map<String, Date> datePeriod = new HashMap<String, Date>();
        datePeriod.put(ConstantDefine.TOP_UP_ACTIVE_VALIDDATE, activeValidDate);
        datePeriod.put(ConstantDefine.TOP_UP_ACTIVE_EXPIREDATE, activeExpireDate);
        datePeriod.put(ConstantDefine.TOP_UP_SUSPEND_EXPIREDATE, suspendExpireDate);
        datePeriod.put(ConstantDefine.TOP_UP_DISABLE_EXPIREDATE, disableExpireDate);

        return datePeriod;
    }

    /**
     * @Description: 组装top-up时用户状态变更数据
     */
    public ImsResStsSync packageCmResStsSync4Event(CmResLifecycle newCmResLifeCycle, CmResLifecycle oldResLifecycle, Long acctId,
            String phoneId, SLifeCycleByAcctChgReq lifeCycleReq, Integer billType)
    {
        ImsResStsSync resStsSync = packageCmResStsSync(newCmResLifeCycle, oldResLifecycle, acctId, phoneId, lifeCycleReq,
                billType);

        Integer reasonCode = LifeCycleHelper.getImsResStsSyncReasonCode(lifeCycleReq.getTriggerEventType(), billType);
        resStsSync.setReasonCode(reasonCode);
        return resStsSync;
    }

    /**
     * @Description: 构造ImsResStsSync对象，注：缺少reasonCode的设值
     */
    private ImsResStsSync packageCmResStsSync(CmResLifecycle newCmResLifeCycle, CmResLifecycle oldResLifecycle, Long acctId,
            String phoneId, SLifeCycleByAcctChgReq lifeCycleReq, Integer billingType)
    {
        ImsResStsSync resStsSync = new ImsResStsSync();
        resStsSync.setAcctId(acctId);
        resStsSync.setCreateDate(context.getRequestDate());
        resStsSync.setNbalance(lifeCycleReq.getNbalance());
        resStsSync.setNexpireDate(newCmResLifeCycle.getExpireDate());
        resStsSync.setNsts(newCmResLifeCycle.getSts());
        resStsSync.setPbalance(lifeCycleReq.getPbalance());
        resStsSync.setPexpireDate(oldResLifecycle.getExpireDate());
        resStsSync.setPhoneId(phoneId);
        resStsSync.setPsts(oldResLifecycle.getSts());
        resStsSync.setSoNbr(context.getSoNbr());
        resStsSync.setChangeTime(context.getRequestDate());
        resStsSync.setResourceId(newCmResLifeCycle.getResourceId());
        if (newCmResLifeCycle.getUnbillingFlag() != null
                && newCmResLifeCycle.getUnbillingFlag() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
        {
            resStsSync.setNsubType(3);
        }
        else if (newCmResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY)
        {
            resStsSync.setNsubType(1);
        }
        else if (newCmResLifeCycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY)
        {
            resStsSync.setNsubType(2);
        }
        else
        {
            resStsSync.setNsubType(0);
        }

        if (newCmResLifeCycle.getUnbillingFlag() != null
                && oldResLifecycle.getUnbillingFlag() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
        {
            resStsSync.setPsubType(3);
        }
        else if (oldResLifecycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY)
        {
            resStsSync.setPsubType(1);
        }
        else if (oldResLifecycle.getOsSts() == EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY)
        {
            resStsSync.setPsubType(2);
        }
        else
        {
            resStsSync.setPsubType(0);
        }

        if (EnumCodeDefine.USER_PAYMODE_PREPAID == billingType)
        {
            resStsSync.setNuserrequestFlag(newCmResLifeCycle.getUserrequestFlag());
            resStsSync.setPuserrequestFlag(newCmResLifeCycle.getUserrequestFlag());
            resStsSync.setNfrauldFlag(newCmResLifeCycle.getFrauldFlag());
            resStsSync.setPfrauldFlag(newCmResLifeCycle.getFrauldFlag());
        }
        return resStsSync;
    }

    /**
     * @Description: 计算生命周期新状态的失效时间
     * @author: tangjl5
     * @Date: 2011-10-31
     */
    private Date packageUserNewStsExpireDate(SUserStatus userStatus, Integer patternID, CmResource res)
    {
        Date expireDate = null;
        // 若接口未传入失效时间，则根据用户对应得模式，状态查找持续天数与生效时间，计算失效时间
        if (null == userStatus.getExpire_date() || userStatus.getExpire_date().trim().length() == 0)
        {
            SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(userStatus.getNew_state(),
                    patternID);
            if (null != sysCycPatternDetail)
            {
                // 后付费状态的有效期天数配置成-1，代表与用户的有效长度一致
                if (sysCycPatternDetail.getValidDays() == -1)
                {
                    expireDate = res.getExpireDate();
                }
                else
                {
                    Calendar calendar = this.calculateExpireDateByExtendDays(
                            IMSUtil.formatDate(userStatus.getValid_date(), context.getRequestDate()),
                            sysCycPatternDetail.getValidDays());
                    expireDate = calendar.getTime();
                }
            }
            else
            {
                // 用户在sys_cycle_pattern_detail中未找到对应的模式
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, userStatus.getNew_state(),
                        patternID);
            }
        }
        else
        {
            expireDate = IMSUtil.formatExpireDate(userStatus.getExpire_date());
        }

        return expireDate;
    }

    /**
     * @Description: 构造用户的生命周期
     */
    public CmResLifecycle packageNewResLifeCyc(SUserStatus userStatus, Integer patternID) throws IMSException
    {
        CmResLifecycle newResLifeCyc = new CmResLifecycle();

        // 若接口未传入失效时间，则根据用户对应得模式，状态查找持续天数与生效时间，计算失效时间
        if (null == userStatus.getExpire_date() || userStatus.getExpire_date().trim().length() == 0)
        {
            SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(userStatus.getNew_state(),
                    patternID);
            if (null != sysCycPatternDetail)
            {
                Calendar calendar = this.calculateExpireDateByExtendDays(
                        IMSUtil.formatDate(userStatus.getValid_date(), context.getRequestDate()),
                        sysCycPatternDetail.getValidDays());
                newResLifeCyc.setExpireDate(calendar.getTime());
            }
            else
            {
                // 用户在sys_cycle_pattern_detail中未找到对应的模式
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, userStatus.getNew_state(),
                        patternID);
            }
        }
        else
        {
            newResLifeCyc.setExpireDate(IMSUtil.formatExpireDate(userStatus.getExpire_date()));
        }

        newResLifeCyc.setResourceId(userStatus.getUser_id());
        newResLifeCyc.setSoNbr(context.getSoNbr());
        newResLifeCyc.setSts((int) userStatus.getNew_state());
        newResLifeCyc.setValidDate(IMSUtil.formatDate(userStatus.getValid_date(), context.getRequestDate()));
        newResLifeCyc.setSoDate(IMSUtil.formatDate(userStatus.getAction_date(), context.getRequestDate()));
        if (null != userStatus.getSub_type())
            newResLifeCyc.setOsSts(CommonUtil.ShortToInteger(userStatus.getSub_type()));
        return newResLifeCyc;
    }

    public void insertResLifeCycReg4UnDeal(CmResLifecycle oldResLifeCyc, CmResLifecycle newResLifeCyc, Long unDoSoNbr,
            Date unDealDate) throws IMSException
    {
        CaRescycReg caCycleResourceReg = new CaRescycReg();
        caCycleResourceReg.setSoNbr(context.getSoNbr());
        caCycleResourceReg.setPsts(oldResLifeCyc.getSts());
        caCycleResourceReg.setNsts(newResLifeCyc.getSts());
        caCycleResourceReg.setPvalidDate(oldResLifeCyc.getValidDate());
        caCycleResourceReg.setPexpireDate(oldResLifeCyc.getExpireDate());

        caCycleResourceReg.setNexpireDate(newResLifeCyc.getExpireDate());
        caCycleResourceReg.setNvalidDate(newResLifeCyc.getValidDate());
        caCycleResourceReg.setResourceId(oldResLifeCyc.getResourceId());
        // 2011-11-19 wukl 首次激活时设置老记录的失效时间，及新记录的生效时间
        caCycleResourceReg.setValidDate(context.getRequestDate());
        Date oldExpireDate = null;
        if (context.getExtendParam(ConstantDefine.ACTIVE_DATE) != null)
        {
            oldExpireDate = (Date) context.getExtendParam(ConstantDefine.ACTIVE_DATE);
            caCycleResourceReg.setValidDate(oldExpireDate);
        }

        caCycleResourceReg.setExpireDate(IMSUtil.getDefaultExpireDate());
        // 设置撤单撤单sonbr
        if (CommonUtil.isValid(unDoSoNbr))
        {
            caCycleResourceReg.setUnSoNbr(unDoSoNbr);

        }

        // 设置撤单处理时间
        if (null != unDealDate)
        {
            caCycleResourceReg.setUnDealDate(unDealDate);
        }
        
        //@Date 2012-08-14 wangdw5 :[54028]suspend date & suspend date & source system
        //@Date 2012-09-26 wangdw5 : #60031 [54028]设计中,以下逻辑仅针对预付费
        User3hBean user = context.get3hTree().loadUser3hBean(newResLifeCyc.getResourceId());
        if(user.getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID && CommonUtil.isIn(newResLifeCyc.getSts(), new Integer[]{
                EnumCodeDefine.LIFECYCLE_ACTIVE,
                EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY,
                EnumCodeDefine.LIFECYCLE_DISABLE
            }))
        {
            CaRescycReg oldCaCycleResReg = querySingle(CaRescycReg.class, new DBCondition(
                    CaRescycReg.Field.soNbr, oldResLifeCyc.getSoNbr()));
            if(oldCaCycleResReg != null)
            {
                caCycleResourceReg.setOldDisableStopDate(oldCaCycleResReg.getNewDisableStopDate());
                caCycleResourceReg.setOldSuspendStopDate(oldCaCycleResReg.getNewSuspendStopDate());
            }
            User3hBean user3hBean = context.get3hTree().loadUser3hBean(newResLifeCyc.getResourceId());
            SysGroupCyclePattern pattern = queryCaGroupCyclePattern(user3hBean.getUser(),user3hBean.getCustomer(),user3hBean.getAccount());
            Integer patternID = pattern.getPatternId();
            SysCyclePatternDetail disablePattern = querySysCyclePatternDetailByStsID2Cache(
                    (short) EnumCodeDefine.LIFECYCLE_DISABLE, patternID);
            SysCyclePatternDetail suspendPattern = querySysCyclePatternDetailByStsID2Cache(
                    (short) EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY, patternID);
            if(newResLifeCyc.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE)
            {
                Date temp = newResLifeCyc.getExpireDate();
                temp = DateUtil.offsetDate(temp,
                        Calendar.DAY_OF_MONTH, disablePattern.getValidDays());
                caCycleResourceReg.setNewSuspendStopDate(temp);
                temp = DateUtil.offsetDate(temp,
                        Calendar.DAY_OF_MONTH, suspendPattern.getValidDays());
                caCycleResourceReg.setNewDisableStopDate(temp);
            }
            else if(newResLifeCyc.getSts() == EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY)
            {
                caCycleResourceReg.setNewSuspendStopDate(newResLifeCyc.getExpireDate());
                caCycleResourceReg.setNewDisableStopDate(DateUtil.offsetDate(newResLifeCyc.getExpireDate(),
                        Calendar.DAY_OF_MONTH, disablePattern.getValidDays()));
            }
            else if(newResLifeCyc.getSts() == EnumCodeDefine.LIFECYCLE_DISABLE)
            {
                caCycleResourceReg.setNewDisableStopDate(newResLifeCyc.getExpireDate());
            }
        }
        
        super.insert(caCycleResourceReg);
    }

    /**
     * @Description: 将用户生命周期插入到历史表中
     * @param oldResLifeCyc
     * @param newResLifeCyc
     * @param oldResValidity
     * @param newResValidity
     * @param unDoSoNbr 若是撤单输入撤单的soNbr
     * @return
     * @throws IMSException
     * @author: tangjl5
     * @Date: 2011-10-10
     */
    public void insertResLifeCycReg(CmResLifecycle oldResLifeCyc, CmResLifecycle newResLifeCyc) throws IMSException
    {
        insertResLifeCycReg4UnDeal(oldResLifeCyc, newResLifeCyc, null, null);
    }

    /**
     * @Description: 更新用户生命周期并发送告警
     * @author: tangjl5
     * @Date: 2012-2-28
     */
    public CmResLifecycle modifyUserLifeCycle(SUserStatus userStatus, CmResLifecycle newLifeCycle, CmResLifecycle oldLifeCycle)
    {
        CmResLifecycle updateLifeCycle = new CmResLifecycle();
        updateLifeCycle.setSoNbr(context.getSoNbr());
        if (null != newLifeCycle.getSts())
        {
            updateLifeCycle.setSts(newLifeCycle.getSts());
            if (newLifeCycle.getSts() != oldLifeCycle.getSts().intValue())
            {
                // 用户状态发生变化时，状态的创建时间置为请求时间
                updateLifeCycle.setCreateDate(context.getRequestDate());
            }
        }

        if (null != newLifeCycle.getReratingFlag())
            updateLifeCycle.setReratingFlag(newLifeCycle.getReratingFlag());

        if (null != newLifeCycle.getFrauldFlag())
            updateLifeCycle.setFrauldFlag(newLifeCycle.getFrauldFlag());

        if (null != newLifeCycle.getUnbillingFlag())
            updateLifeCycle.setUnbillingFlag(newLifeCycle.getUnbillingFlag());

        if (null != newLifeCycle.getUserrequestFlag())
            updateLifeCycle.setUserrequestFlag(newLifeCycle.getUserrequestFlag());

        //@Date 2012-09-19 yangjh : 59404 首次激活1001的记录生效时间也取active_time
        if(null != userStatus && null != userStatus.getValid_date()){
            updateLifeCycle.setValidDate(IMSUtil.formatDate(userStatus.getValid_date()));
        }else{
            updateLifeCycle.setValidDate(context.getRequestDate());
        }
        updateLifeCycle.setSoDate(context.getRequestDate());
        if (null != userStatus && null != userStatus.getAction_date())
            updateLifeCycle.setSoDate(IMSUtil.formatDate(userStatus.getAction_date(), context.getRequestDate()));

        updateLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);

        // 2011-11-19 wukl 首次激活时设置老记录的失效时间，及新记录的生效时间
        Date oldExpireDate = null;
        if (context.getExtendParam(ConstantDefine.ACTIVE_DATE) != null)
        {
            oldExpireDate = (Date) context.getExtendParam(ConstantDefine.ACTIVE_DATE);
            updateLifeCycle.setValidDate(oldExpireDate);
        }

        if (null != newLifeCycle.getExpireDate())
        {
            updateLifeCycle.setExpireDate(newLifeCycle.getExpireDate());
        }

        User3hBean user3hBean = context.get3hTree().loadUser3hBean(oldLifeCycle.getResourceId());

        // @Date 2012-5-14 tangjl5 Bug #45585后付费用户与混合模式用户的next_sts,next_sts_expiredate字段填与当前值一致
        Integer nextUserSts = newLifeCycle.getSts() == null ? oldLifeCycle.getSts() : newLifeCycle.getSts();
        Integer nextUserOsSts = newLifeCycle.getOsSts() == null ? oldLifeCycle.getOsSts() : newLifeCycle.getOsSts();
        SysGroupCyclePattern pattern = null;
        if (user3hBean.getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID && newLifeCycle.getSts() != null
                && newLifeCycle.getSts() != EnumCodeDefine.LIFECYCLE_POOL
                && newLifeCycle.getSts() != EnumCodeDefine.LIFECYCLE_IDLE_INITIAL)
        {
            pattern = queryCaGroupCyclePattern(user3hBean.getUser(), user3hBean.getCustomer(), user3hBean.getAccount());
            // 计算用户的后续状态及生失效时间
            SysCyclePatternTransfer transfer = getLifeCycleStsToByEvent(pattern.getPatternId(),
                    EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, newLifeCycle);
            if (null == transfer)
            {
                // 状态转换非法，抛出异常
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, newLifeCycle.getResourceId(),
                        pattern.getPatternId(), newLifeCycle.getSts(), newLifeCycle.getOsSts(),
                        EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE);
            }
            nextUserSts = transfer.getStsTo();
            nextUserOsSts = transfer.getOsStsTo();
        }

        // @Date 2012-4-2 tangjl5 Bug #40000 将数据库中nextSts的存储形式修改为同mdb中的格式一致
        StringBuffer nextSts = new StringBuffer();
        // 如果OsSts为空则表10
        nextSts.append(nextUserOsSts.toString());
        // 2012-04-02 zengxr 暂时屏蔽掉，和计费讨论后再修改
        // @Date 2012-5-11 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
        if (null != newLifeCycle.getReratingFlag())
            nextSts.append(newLifeCycle.getReratingFlag());
        else
            nextSts.append(oldLifeCycle.getReratingFlag());

        if (null != newLifeCycle.getUnbillingFlag())
            nextSts.append(newLifeCycle.getUnbillingFlag());
        else
            nextSts.append(oldLifeCycle.getUnbillingFlag());

        if (null != newLifeCycle.getUserrequestFlag())
            nextSts.append(newLifeCycle.getUserrequestFlag());
        else
            nextSts.append(oldLifeCycle.getUserrequestFlag());

        if (null != newLifeCycle.getFrauldFlag())
            nextSts.append(newLifeCycle.getFrauldFlag());
        else
            nextSts.append(oldLifeCycle.getFrauldFlag());

        // 截取后两位
        // @Date 2012-4-5 tangjl5 修改用户没有获取到下一个sts问题
        nextSts.append(nextUserSts.toString().substring(2));

        // 当前状态的下一个状态的失效时间与用户的失效时间一致
        updateLifeCycle.setNextSts(CommonUtil.string2Integer(nextSts.toString()));
        updateLifeCycle.setNextStsExpiredate(user3hBean.getUser().getExpireDate());
        if (null != newLifeCycle.getOsSts())
        {
            updateLifeCycle.setOsSts(newLifeCycle.getOsSts());
        }

        // 更改历史数据的状态
        updateResLifeCycleHisSts4Nor(newLifeCycle.getResourceId(), updateLifeCycle.getValidDate());
        //@Date 2012-09-10 yugb :On_Site Defect #58502
        List<CmResLifecycle> updateList = updateByCondition(updateLifeCycle, oldExpireDate, new DBCondition(
                CmResLifecycle.Field.resourceId, newLifeCycle.getResourceId()), new DBCondition(CmResLifecycle.Field.validDate,
                context.getRequestDate(), Operator.LESS), new DBCondition(CmResLifecycle.Field.expireDate,
                        context.getRequestDate(), Operator.GREAT));

        
        CmResLifecycle insertLifeCycle = null;
        if (CommonUtil.isEmpty(updateList))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED, context.getSoNbr());
        }
        else
        {
            for (CmResLifecycle lifecycle : updateList)
            {
                if (lifecycle.getSoNbr() == context.getSoNbr())
                {
                    insertLifeCycle = lifecycle;
                    break;
                }
            }

            if (null == insertLifeCycle)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED, context.getSoNbr());
        }

        // 调用短信接口
        // wukl 2011-10-27 首次激活不需发送生命周期状态变更短信
        // wukl 2012-04-11 首次激活判断增加上海接口的busi_code
        if (!context.getComponent(FirstActiveComponent.class).isFirstActvie()
                && insertLifeCycle.getSts() != EnumCodeDefine.LIFECYCLE_POOL)
        {
            IMSUtil.doNotification(
                    createNotifications(user3hBean.getUser(), user3hBean.getAccount(), user3hBean.getCustomer(), insertLifeCycle,
                            oldLifeCycle), context);
        }

        if (user3hBean.getUser().getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            // 状态失效提前告警时间计算，并添加到IMS_RES_LIFECYCLE_ONCE_NOTIFY表中
            lifeCyclePreNotificationSync(insertLifeCycle);
        }

        return insertLifeCycle;
    }

    /**
     * @Description: 根据用户Id删除用户生命周期
     * @param userId
     */
    public void deleteUserLifeCycle(Long userId)
    {
        // 更改历史数据的状态
        updateResLifeCycleHisSts4Nor(userId, null);

        // CmResLifecycle delLifeCycle = new CmResLifecycle();
        // delLifeCycle.setResourceId(userId);
        deleteByCondition(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, userId));

        deleteResValidity(userId);
    }

    public void deleteResValidity(Long resId)
    {
        updateResValidityHisSts4Nor(resId);

        // 删除用户有效期
        deleteByCondition(CmResValidity.class, new DBCondition(CmResValidity.Field.resourceId, resId));
    }

    public void deleteAcctValidity(Long acctId)
    {
        updateAcctValidityHisSts4Nor(acctId);

        // 删除用户有效期
        deleteByCondition(CmResValidity.class, new DBCondition(CmResValidity.Field.accountId, acctId), new DBCondition(
                CmResValidity.Field.resourceId, 0));
    }

    /**
     * @Description: 更新用户级有效期的历史数据
     * @author: tangjl5
     * @Date: 2011-11-9
     */
    private void updateResValidityHisSts4Nor(Long resId)
    {
        CmResValidity oldvalid = new CmResValidity();
        oldvalid.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);

        DBUtil.updateByCondition(oldvalid, new DBCondition(CmResValidity.Field.resourceId, resId), new DBCondition(
                CmResValidity.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
    }

    /**
     * @Description: 更新账户级有效期的历史数据
     * @param acctId
     * @author: tangjl5
     * @Date: 2012-2-2
     */
    public void updateAcctValidityHisSts4Nor(Long acctId)
    {
        CmResValidity oldvalid = new CmResValidity();
        oldvalid.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);

        DBUtil.updateByCondition(oldvalid, new DBCondition(CmResValidity.Field.accountId, acctId), new DBCondition(
                CmResValidity.Field.resourceId, 0), new DBCondition(CmResValidity.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
    }

    /**
     * @Description: 将CM_RES_LIFECYCLE表的的正常状态置为历史状态
     * @param userId
     * @author: tangjl5
     * @Date: 2011-11-9
     */
    private void updateResLifeCycleHisSts4Nor(Long userId, Date nextStsExpireDate)
    {
        // @Date 2012-10-12 tangjl5 更改用户状态前先把next_sts_expireDate 置为失效
        CmResLifecycle oldvalid = new CmResLifecycle();
        oldvalid.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);
        if (nextStsExpireDate != null)
        {
            oldvalid.setNextStsExpiredate(nextStsExpireDate);
        }
        else
        {
            oldvalid.setNextStsExpiredate(context.getRequestDate());
        }
        DBUtil.updateByCondition(oldvalid, new DBCondition(CmResLifecycle.Field.resourceId, userId), new DBCondition(
                CmResLifecycle.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
    }

    /**
     * @Description: 将CM_RES_VALIDITY表的的处理中的状态置为历史状态
     * @param userId
     */
    public void updateResValidityHisSts(Long userId)
    {
        CmResValidity oldvalid = new CmResValidity();
        oldvalid.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);

        DBUtil.updateByCondition(oldvalid, new DBCondition(CmResValidity.Field.resourceId, userId),
                new DBCondition(CmResValidity.Field.recSts, EnumCodeDefine.IN_DEALING));
    }

    /**
     * @Description: 将CM_RES_LIFECYCLE表的是否是历史数据的状态修改为是
     * @param userId
     */
    public void updateUserLifeCycleHisSts(Long userId)
    {
        CmResLifecycle oldLifeCycle = new CmResLifecycle();
        oldLifeCycle.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);

        DBUtil.updateByCondition(oldLifeCycle, new DBCondition(CmResLifecycle.Field.resourceId, userId),
                new DBCondition(CmResValidity.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
    }

    /**
     * @Description: 批量删除用户生命周期
     * @param userIds
     */
    public void deleteBathUserLifeCycle(List<Long> userIds)
    {
        for (Long userId : userIds)
        {
            deleteUserLifeCycle(userId);
        }
    }

    /**
     * @Description: 更改用户有效期
     * @param cmResValidity
     */
    public void modifyUserLifeCycleValid4Timer(CmResValidity cmResValidity)
    {
        // 更改历史数据的状态
        updateResValidityHisSts(cmResValidity.getResourceId());

        CmResValidity updateValidity = new CmResValidity();
        updateValidity.setSoNbr(context.getSoNbr());
        updateValidity.setExpireDate(context.getRequestDate());
        updateValidity.setValidDate(context.getRequestDate());
        updateValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);

        updateByCondition(updateValidity, new DBCondition(CmResValidity.Field.accountId, cmResValidity.getAccountId()),
                new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS), new DBCondition(
                        CmResValidity.Field.validDate, cmResValidity.getValidDate()), new DBCondition(CmResValidity.Field.recSts,
                        EnumCodeDefine.IS_HISTORY_DATA));
    }

    /**
     * @Description: 更新用户生命周期
     * @param newCmResLifeCycle
     * @param acctId
     * @param oldCmResLifeCycle
     */
    public CmResLifecycle modifyUserLifeCycle4Timer(CmResLifecycle newCmResLifeCycle, Long acctId,
            CmResLifecycle oldCmResLifeCycle)
    {
        // 更改历史数据的状态
        CmResLifecycle oldLifeCycle = new CmResLifecycle();
        if (oldCmResLifeCycle.getExpireDate().before(context.getRequestDate()))
        {
            oldLifeCycle.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);
            oldLifeCycle.setResourceId(newCmResLifeCycle.getResourceId());
            DBUtil.updateByCondition(oldLifeCycle,
                    new DBCondition(CmResLifecycle.Field.resourceId, oldLifeCycle.getResourceId()), new DBCondition(
                            CmResLifecycle.Field.recSts, EnumCodeDefine.IN_DEALING));
        }
        else
        {
            oldLifeCycle.setRecSts(EnumCodeDefine.IS_HISTORY_DATA);
            oldLifeCycle.setResourceId(newCmResLifeCycle.getResourceId());
            DBUtil.updateByCondition(oldLifeCycle,
                    new DBCondition(CmResLifecycle.Field.resourceId, oldLifeCycle.getResourceId()), new DBCondition(
                            CmResLifecycle.Field.recSts, EnumCodeDefine.IS_VALID_DATA));
        }

        CmResLifecycle updateLifeCycle = new CmResLifecycle();
        updateLifeCycle.setSoNbr(newCmResLifeCycle.getSoNbr());
        updateLifeCycle.setSts(newCmResLifeCycle.getSts());
        updateLifeCycle.setSoDate(newCmResLifeCycle.getSoDate());
        updateLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        updateLifeCycle.setExpireDate(newCmResLifeCycle.getExpireDate());
        updateLifeCycle.setOsSts(newCmResLifeCycle.getOsSts());
        if (newCmResLifeCycle.getSts() != oldCmResLifeCycle.getSts().intValue())
        {
            updateLifeCycle.setCreateDate(context.getRequestDate());
        }

        if (updateLifeCycle.getSts() != EnumCodeDefine.LIFECYCLE_POOL)
        {
            User3hBean user3hBean = context.get3hTree().loadUser3hBean(oldCmResLifeCycle.getResourceId());
            CmResource res = user3hBean.getUser();
            CaAccount acct = user3hBean.getAccount();
            CmCustomer cust = user3hBean.getCustomer();
            if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                SysGroupCyclePattern pattern = queryCaGroupCyclePattern(res, cust, acct);
                // 计算用户的后续状态及生失效时间
                SysCyclePatternTransfer transfer = getLifeCycleStsToByEvent(pattern.getPatternId(),
                        EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, newCmResLifeCycle);
                if (null == transfer)
                {
                    // 状态转换非法，抛出异常
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL,
                            newCmResLifeCycle.getResourceId(), pattern.getPatternId(), newCmResLifeCycle.getSts(),
                            newCmResLifeCycle.getOsSts(), EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE);
                }
                //@Date 2012-10-17 yugb :On_Site Defect #61091
                Integer nextUserOsSts = transfer.getOsStsTo();
                Integer nextUserSts = transfer.getStsTo();
                
                StringBuffer nextSts = new StringBuffer();
                // 如果OsSts为空则表10
                nextSts.append(nextUserOsSts.toString());
                if (null != newCmResLifeCycle.getReratingFlag())
                    nextSts.append(newCmResLifeCycle.getReratingFlag());
                else
                    nextSts.append(oldCmResLifeCycle.getReratingFlag());

                if (null != newCmResLifeCycle.getUnbillingFlag())
                    nextSts.append(newCmResLifeCycle.getUnbillingFlag());
                else
                    nextSts.append(oldCmResLifeCycle.getUnbillingFlag());

                if (null != newCmResLifeCycle.getUserrequestFlag())
                    nextSts.append(newCmResLifeCycle.getUserrequestFlag());
                else
                    nextSts.append(oldCmResLifeCycle.getUserrequestFlag());

                if (null != newCmResLifeCycle.getFrauldFlag())
                    nextSts.append(newCmResLifeCycle.getFrauldFlag());
                else
                    nextSts.append(oldCmResLifeCycle.getFrauldFlag());

                // 截取后两�?
                // @Date 2012-4-5 tangjl5 修改用户没有获取到下一个sts问题
                nextSts.append(nextUserSts.toString().substring(2));
                // 当前状态的下一个状态的失效时间与用户的失效时间一�?
//                Integer nextSts = CommonUtil.string2Integer(transfer.getOsStsTo().toString()
//                        + transfer.getStsTo().toString().substring(2));
                updateLifeCycle.setNextSts(CommonUtil.string2Integer(nextSts.toString()));
                updateLifeCycle.setNextStsExpiredate(res.getExpireDate());
            }
        }

        CmResLifecycle resLife = new CmResLifecycle();
        resLife.setResourceId(newCmResLifeCycle.getResourceId());

        List<CmResLifecycle> resultList = updateByCondition(updateLifeCycle, new DBCondition(CmResLifecycle.Field.resourceId,
                resLife.getResourceId()),
                new DBOrCondition(
                        new DBCondition(CmResLifecycle.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS),
                        new DBCondition(CmResLifecycle.Field.expireDate, context.getRequestDate(), Operator.GREAT)),
                new DBCondition(CmResLifecycle.Field.soNbr, oldCmResLifeCycle.getSoNbr()));

        CmResLifecycle newLifeCycle = null;
        if (CommonUtil.isEmpty(resultList))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED, context.getSoNbr());
        }
        else
        {
            for (CmResLifecycle lifecycle : resultList)
            {
                if (lifecycle.getSoNbr() == context.getSoNbr())
                {
                    newLifeCycle = lifecycle;
                    break;
                }
            }

            if (null == newLifeCycle)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_VALIDITY_HAS_NOT_BE_CHANGED, context.getSoNbr());
        }

        return newLifeCycle;
    }

    /**
     * @Description: 变更用户生命周期状态，由CRM请求
     * @date 2011-10-31 tangjl5 由CRM请求，状态不进行流转，预后付费不同处理
     * @date 2011-11-25 tangjl5 预付费用户不订购主动停机产品
     */
    public SBalanceList lifeCycleManager(SUserStatus sLifeCycleMgnt)
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(sLifeCycleMgnt.getUser_id(), sLifeCycleMgnt.getPhone_id());
        CmResource res = user3hBean.getUser();

        // 获取用户当前生命周期状态
        CmResLifecycle curResLifecycle = user3hBean.getUserLifeCycle();
        /*if (null == curResLifecycle)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, res.getResourceId());
        }*/

        CmCustomer cust = null;
        CaAccount acct = null;
        // @Date 2012-4-16 tangjl5 On_Site Defect #43689 若用户在销户时没有清理账户，则使用账户信息，若则置为null
        if (curResLifecycle.getSts() == EnumCodeDefine.LIFECYCLE_TERMINAL)
        {
            try
            {
                context.get3hTree().loadUser3hBean(res.getResourceId()).getAccount();
                acct = user3hBean.getBillAcct3hBean().getAccount();
                cust = user3hBean.getCustomer();
            }
            catch(IMS3hNotFoundException e)
            {   
            	imsLogger.error(e, e);
            }
        }
        else
        {
            acct = user3hBean.getBillAcct3hBean().getAccount();
            cust = user3hBean.getCustomer();
        }

        // 停机状态：10：正常 11：单停 12：双停
        Integer newOsSts = CommonUtil.ShortToInteger(sLifeCycleMgnt.getSub_type());
        Integer newSsts = CommonUtil.ShortToInteger(sLifeCycleMgnt.getNew_state());

        Short newFraudFlag = sLifeCycleMgnt.getFraud_flag();
        Short newSuspendReqFlag = sLifeCycleMgnt.getSuspend_request_flag();
        Short newUnbillingFlag = sLifeCycleMgnt.getUnbilling_flag();

        CmResLifecycle newLifeCycle = new CmResLifecycle();
        Short curSuspendRegFlag = curResLifecycle.getUserrequestFlag().shortValue();
        Integer curSts = curResLifecycle.getSts();
        Integer savedValidDays = null;
        if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            if (newSuspendReqFlag != null)
            {
                if (null != curSuspendRegFlag && newSuspendReqFlag == EnumCodeDefine.LIFECYCLE_USERREQUEST_FLAG)
                {
                    if (newSuspendReqFlag == curSuspendRegFlag.shortValue())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_FLAG_IS_ALREADY_EXIST, res.getResourceId(),
                                "suspend_request_flag", newSuspendReqFlag);
                    }
                    
                    // @Date 2012-07-24 tangjl5 :Story # 51474 预付费用户为非active状态时，不允许设置为主动请求停机
                    if (curSts != EnumCodeDefine.LIFECYCLE_ACTIVE)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_STS_ISNOT_ACTIVE_CANNOT_SUSPEND, res.getResourceId(),
                                curResLifecycle.getSts());
                    }
                }
                // @Date 2012-07-24 tangjl5 :Story # 51474 预付费订购主动停机产品
                if (newSuspendReqFlag == EnumCodeDefine.LIFECYCLE_USERREQUEST_FLAG)
                {
                    // @Date 2012-04-24 lijc3 AIS才传入reason_days
                    // 若没有传入reason_days则从当天时间开始
                    if (sLifeCycleMgnt.getReason_days() == null && ProjectUtil.is_TH_AIS())
                    {
                        sLifeCycleMgnt.setReason_days((short)0);
                    }
                    
                    // 停机保号产品需要增加特征规格值—12302，用于保存用户在设置为主动停机时的有效期天数。在取消主动停机时，需要将active状态的有效期设置为请求时间加上特征规格值12302存放的天数。
                    createUserSpecProd(sLifeCycleMgnt, res, SpecCodeDefine.USER_SUSPEND_REQUEST);
                }
                else if (curSuspendRegFlag == EnumCodeDefine.LIFECYCLE_USERREQUEST_FLAG  && newSuspendReqFlag == EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG)
                {
                    // @Date 2012-04-24 tangjl5 在取消主动停机时，需要将active状态的有效期设置为请求时间加上特征规格值12302存放的天数
                    savedValidDays = cancelUserSpecProd(res, SpecCodeDefine.USER_SUSPEND_REQUEST);
                }

                newLifeCycle.setUserrequestFlag(newSuspendReqFlag.intValue());
            }
        }
        else
        {
            if (newSsts != null)
            {
                if (newSsts == curSts.intValue())
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_STS_IS_ALREADY_EXIST, res.getResourceId(), newSsts);
                }

                if (newSsts == EnumCodeDefine.LIFECYCLE_SUSPEND_REQUEST && curSts != EnumCodeDefine.LIFECYCLE_SUSPEND_REQUEST)
                {
                    // @Date 2012-3-29 tangjl5 用户主动停机时，reason_days不能为空
                    // @Date 2012-04-24 lijc3 AIS才传入reason_days
                    if (sLifeCycleMgnt.getReason_days() == null && ProjectUtil.is_TH_AIS())
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_USER_REQ_SUSPEND_REASON_DAYS_IS_NULL,
                                res.getResourceId());
                    }
                    // 用户主动请求停机,则订购规格类型为123的产品
                    createUserSpecProd(sLifeCycleMgnt, res, SpecCodeDefine.USER_SUSPEND_REQUEST);
                }

                if (newSsts != EnumCodeDefine.LIFECYCLE_SUSPEND_REQUEST && curSts == EnumCodeDefine.LIFECYCLE_SUSPEND_REQUEST)
                {
                    // 若用户取消停机保号业务，则该产品的失效时间置成当前时间
                    cancelUserSpecProd(res, SpecCodeDefine.USER_SUSPEND_REQUEST);
                }

                // 状态为初始状态为；suspend_debt或suspend_fraud目标状态为active的时候，并且unbilling_flag为1时，将unbilling设置为0，并退订133的产品
                if ((curSts == EnumCodeDefine.LIFECYCLE_SUSPEND_DEBT || curSts == EnumCodeDefine.LIFECYCLE_SUSPEND_FRAULD)
                        && newSsts == EnumCodeDefine.LIFECYCLE_ACTIVE
                        && curResLifecycle.getUnbillingFlag() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
                {
                    newLifeCycle.setUnbillingFlag(EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_NO);
                    // 则取消规格类型为133的undentity产品
                    cancelUserSpecProd(res, SpecCodeDefine.USER_SUSPEND_UNIDENTIFIED);
                }
            }

            if (newUnbillingFlag != null)
            {
                if (null != curResLifecycle.getUnbillingFlag()
                        && newUnbillingFlag == curResLifecycle.getUnbillingFlag().shortValue()
                        && newUnbillingFlag != EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_NO)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_FLAG_IS_ALREADY_EXIST, res.getResourceId(),
                            "unbilling_flag", newUnbillingFlag);
                }
                newLifeCycle.setUnbillingFlag(newUnbillingFlag.intValue());
                if (newUnbillingFlag.intValue() == EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_UNIDENTIFY)
                {
                    // 则订购规格类型为133的undentity产品
                    this.createUnidentityProd(sLifeCycleMgnt, res);
                }
            }
        }

        // 预付费用户与混合模式可以设置
        if (newFraudFlag != null
                && (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID
                        || res.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID || res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID))
        {
            if (curResLifecycle.getFrauldFlag() != null && newFraudFlag == curResLifecycle.getFrauldFlag().shortValue()
                    && newFraudFlag != EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_FLAG_IS_ALREADY_EXIST, res.getResourceId(),
                        "fraud_flag", newFraudFlag);
            }

            newLifeCycle.setFrauldFlag(newFraudFlag.intValue());
        }

        SBalanceList userBalanceList = null;
        SysGroupCyclePattern groupCyclePatten = null;
        if (newSsts != null)
        {
            newLifeCycle.setSts(newSsts);
            groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct, null);

//            if (null == groupCyclePatten)
//            {
//                // 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
//                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
//            }

            // 设置新状态的生命周期
            newLifeCycle.setExpireDate(packageUserNewStsExpireDate(sLifeCycleMgnt, groupCyclePatten.getPatternId(), res));
        }
        
        if (savedValidDays != null)
        {
            // 在取消主动停机时，需要将active状态的有效期设置为请求时间加上特征规格值12302存放的天数
            newLifeCycle.setExpireDate(calculateExpireDateByExtendDays(context.getRequestDate(), savedValidDays).getTime());
        }

        if (null != newOsSts)
        {
            newLifeCycle.setOsSts(newOsSts);
        }
        else
        {
            // 用户状态为active时，osSts均设置为10
            if (newSsts != null && newSsts == EnumCodeDefine.LIFECYCLE_ACTIVE)
            {
                newLifeCycle.setOsSts(EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE);
            }
            else if (newSsts != null)
            {
                // 若没有传入os_sts则查询用户
                if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
                {
                    if (groupCyclePatten == null)
                    {
                        groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);
                    }

                    newLifeCycle.setOsSts(getUserTagetOsts(groupCyclePatten.getPatternId(), curResLifecycle,
                            EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, newSsts));
                }
            }
        }
        newLifeCycle.setResourceId(curResLifecycle.getResourceId());

        if (newSsts != null)
        {
            String fireEventType = null;
            if (newSsts == EnumCodeDefine.LIFECYCLE_DISABLE)
            {
                // @Date 2012-3-16 wukl 清理余额和免费资源接口合并及调整
                // 根据用户当前生命周期状态，促发事件，查询目标状态
                SysCyclePatternTransfer transfer = getfireEventType(groupCyclePatten.getPatternId(),
                        EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, curResLifecycle.getSts(), newSsts,
                        curResLifecycle.getOsSts(), newLifeCycle.getOsSts(), curResLifecycle);

                if (transfer == null)
                {
                    // 状态转换非法，抛出异常
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, res.getResourceId(),
                            groupCyclePatten.getPatternId(), curResLifecycle.getSts(), curResLifecycle.getOsSts(),
                            EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE);
                }

                if (transfer.getFireEventType() != null)
                    //@Date 2012-09-13 wangdw5 : #59025: AdjustValid报错 0自动补齐到000格式
                    fireEventType = CommonUtil.string2String5Bit(transfer.getFireEventType());
                else
                    fireEventType = SysUtil.getString(ConstantDefine.CLEAN_BALANCE_FREERESOURCE_CONFIG);
            }
            // @Date 2012-5-17 tangjl5 Bug #45842 用户销户时直接清理免费资源和余额，不根据配置进行
            else if ((context.getOper().getBusi_code() == 1006 || context.getOper().getBusi_code() == 1042)&& newSsts == EnumCodeDefine.LIFECYCLE_TERMINAL)
            {
                if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
                {
                    fireEventType = EnumCodeDefine.CLEAR_BALANCE_FREE_RESOURE_REWARD;
                }
                // 后付费用户只清理免费资源
                else
                {
                   clearOtFreeRes(context.get3hTree().loadUser3hBean(res.getResourceId()).getBillAcctId(),
                            res.getResourceId());
                }
            }

            if (fireEventType != null)
            {

                userBalanceList = clearDisabledAsset(acct.getAcctId(), res.getResourceId(), fireEventType,newSsts);
            }
        }

        CmResLifecycle updateLifeCycle = null;
        
        // 由于由定时器促发对用户terminal时，直接调用了正常状态的变更，所以对用户状态变更需要调用定时器的方法
        if (context.getOper().getBusi_code() == BusiCodeDefine.DEAL_LIFECYCLE_TS)
        {
            updateLifeCycle = modifyUserLifeCycle4Timer(newLifeCycle, acct.getAcctId(), curResLifecycle);
        }
        else
        {
            // 更新用户的生命周期状 
            updateLifeCycle = modifyUserLifeCycle(sLifeCycleMgnt, newLifeCycle, curResLifecycle);
        }
        // 该操作未进行有效期更改，因此传入同个CmResValidity
        insertResLifeCycReg(curResLifecycle, updateLifeCycle);

        return userBalanceList;
    }

    protected void clearOtFreeRes(Long acctId, Long userId)
    {
        
    }
    
    protected SBalanceList clearDisabledAsset(Long acctId, Long userId, String assetFlag,Integer newSsts)
    {
        return null;
    }
    
    
    
    
    /**
     * @Description: 状态失效提前告警时间计算，并添加到IMS_RES_LIFECYCLE_ONCE_NOTIFY表中
     * @param oldLifecycle
     * @param newLifeCycle
     * @author: tangjl5
     * @Date: 2012-2-23
     */
    public void lifeCyclePreNotificationSync(CmResLifecycle curLifecycle)
    {
        if (curLifecycle.getSts() == EnumCodeDefine.LIFECYCLE_POOL)
        {
            return;
        }
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(curLifecycle.getResourceId());

        CmResource user = user3hBean.getUser();

        // 后付费用户状态变更时不发送告警
        if (user.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            return;
        }

        Long mainProductOfferingId = (long) context.getComponent(BaseProductComponent.class).getUserMainProdOfferingId(
                curLifecycle.getResourceId());
        SysGroupCredit groupCredit = context.getComponent(ConfigQuery.class).queryGroupCredit(user3hBean.getCustomer(),
                user3hBean.getAccount(), user3hBean.getUser(), mainProductOfferingId);

        if (groupCredit == null || groupCredit.getNotifRemainedDays() == -1)
        {
            return;
        }

        // ImsResLifecycleOnceNotify notify = new ImsResLifecycleOnceNotify();
        // notify.setResourceId(curLifecycle.getResourceId());
        // 删除未生效的告警
        // 2012-06-01 zhangzj3 规范表名，ImsResLifecycleOnceNotify表在弄结果表的时候显示表名过长，所以改为ImsResLifecycleNotify
        DBUtil.deleteByCondition(ImsResLifecycleNotify.class, new DBCondition(ImsResLifecycleNotify.Field.resourceId,
                curLifecycle.getResourceId()));

        ImsResLifecycleNotify notify = new ImsResLifecycleNotify();
        notify.setResourceId(curLifecycle.getResourceId());
        notify.setPsts(curLifecycle.getSts());
        notify.setNsts(CommonUtil.string2Integer("10" + String.valueOf(curLifecycle.getNextSts()).substring(6, 8)));
        notify.setCreateDate(context.getRequestDate());
        notify.setSts((int) EnumCodeDefine.SYNC_DEAL_STS_INITIAL);
        Integer remindDays = groupCredit.getNotifRemainedDays();
        // @date 2012-8-7 tangjinlu 提前通知，应该是减去
        Date remindDate = this.calculateExpireDateByExtendDays(curLifecycle.getExpireDate(), -remindDays).getTime();
        notify.setNotifyDate(remindDate);

        insert(notify);
    }

    /**
     * @Description: 预付费用户在暂停激活后，用户订购的所有预扣产品，都调用一次通知扣费接口，通知帐处扣费
     * @param curResLifecycle
     * @param newLifeCycle
     * @param res
     * @author: tangjl5
     * @Date: 2012-2-22
     * @Date：2012-3-9 tangjl5 预付费或者后付费用户恢复到active状态时，对于用户的预扣产品，需要调用通知扣费接口通知完成扣费。
     */
    public void callPreOrder(CmResLifecycle curResLifecycle, CmResLifecycle newLifeCycle, CmResource res, CoProd userReqProd)
    {
    	/*
        if (((res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID
                && newLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE
                && newLifeCycle.getUserrequestFlag() == EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG && newLifeCycle
                .getFrauldFlag() == EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG) || (res.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID
                && curResLifecycle.getSts() != EnumCodeDefine.LIFECYCLE_ACTIVE && newLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE))
                && newLifeCycle.getUnbillingFlag() == curResLifecycle.getUnbillingFlag().intValue()
                && newLifeCycle.getReratingFlag() == curResLifecycle.getReratingFlag().intValue())
        {
            List<CoProd> authProdList = context.getComponent(ProductQuery.class).queryPreDeductAndUserReqProds(
                    res.getResourceId());
            if (userReqProd != null)
            {
                if (CommonUtil.isEmpty(authProdList))
                {
                    authProdList = new ArrayList<CoProd>();
                }
                authProdList.add(userReqProd);
            }

            if (CommonUtil.isNotEmpty(authProdList))
            {
                CsdlArrayList<SProdInfo> prodInfoList = new CsdlArrayList<SProdInfo>(SProdInfo.class);
                for (CoProd prod : authProdList)
                {
                    SProdInfo prodInfo = context.getComponent(BaseProductComponent.class).packeageProdInfo(prod, EnumCodeDefine.CALL_DEDUCT_FEE_FOR_REATIVE);
                    prodInfoList.add(prodInfo);
                }

                // 通知扣费
                Long payAcctId =context.get3hTree().loadUser3hBean(res.getResourceId()).getBillAcctId();
                imsLogger.info("Notify to deduct recurring fee for member products...");
                context.getComponent(AuthComponent.class).callPreOrderProdNotUpLoad(prodInfoList, res.getResourceId(),
                        EnumCodeDefine.PROD_OBJECTTYPE_DEV, payAcctId, new CBSErrorMsg());
            }
        }
        */
    }

    /**
     * @Description: 添加未来状态记录，只针对prepaid user
     * @param userId
     * @param sts
     * @param newLifeCycle
     * @author: tangjl5
     * @Date: 2011-12-31
     */
    // public List<CmResLifecycle> lifeCycleStsConfig(CmResLifecycle curLifeCycle)
    // {
    // List<CmResLifecycle> lifeCycleList = packagelifeCycleStsConfig(curLifeCycle);
    //
    // if (CommonUtil.isNotEmpty(lifeCycleList))
    // {
    // super.insertBatch(lifeCycleList);
    // return lifeCycleList;
    // }
    //
    // return null;
    // }

    public List<CmResLifecycle> packagelifeCycleStsConfig(CmResLifecycle curLifeCycle)
    {
        CmResLifecycle newLifeCycle = packageNewResLifeCycle(curLifeCycle);
        Integer sts = newLifeCycle.getSts();
        // 删除未生效的未来状态记录
        if (sts != EnumCodeDefine.LIFECYCLE_POOL)
        {
            CmResLifecycle lifeCycle = new CmResLifecycle();
            lifeCycle.setResourceId(newLifeCycle.getResourceId());
            @SuppressWarnings("unchecked")
            List<CmResLifecycle> deleteLifeCycleList = (List<CmResLifecycle>) DBUtil.deleteByConditionWithReturn(
                    CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, newLifeCycle.getResourceId()),
                    new DBCondition(CmResLifecycle.Field.validDate, context.getRequestDate(), Operator.GREAT));
            // 将失效时间设置为请求时间，用户上发mdb
            for (CmResLifecycle deleteLifeCycle : deleteLifeCycleList)
            {
                deleteLifeCycle.setExpireDate(context.getRequestDate());
            }

            context.cacheList(deleteLifeCycleList);
        }

        User3hBean user3hBean = context.get3hTree().loadUser3hBean(curLifeCycle.getResourceId());
//        if (null == user3hBean)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_USER_NOTEXIST, curLifeCycle.getResourceId());
//        }

        Date curStsExpireDate = newLifeCycle.getExpireDate();
        List<CmResLifecycle> lifeCycleList = new ArrayList<CmResLifecycle>();
        if (sts != EnumCodeDefine.LIFECYCLE_POOL)
        {
            SysGroupCyclePattern pattern = queryCaGroupCyclePattern(user3hBean.getUser(), user3hBean.getCustomer(),
                    user3hBean.getAccount());
            // 计算用户的后续状态及生失效时间
            SysCyclePatternTransfer transfer = getLifeCycleStsToByEvent(pattern.getPatternId(),
                    EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, newLifeCycle);
            if (null == transfer)
            {
                // 状态转换非法，抛出异常
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, newLifeCycle.getResourceId(),
                        pattern.getPatternId(), newLifeCycle.getSts(), newLifeCycle.getOsSts(),
                        EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE);
            }

            sts = transfer.getStsTo();

            CmResLifecycle resLifeCycle = new CmResLifecycle();
            lifeCycleList.add(resLifeCycle);
            resLifeCycle.setSts(sts);

            // 当前状态的下一个状态的失效时间与用户的失效时间一致
            resLifeCycle.setExpireDate(user3hBean.getUser().getExpireDate());
            resLifeCycle.setValidDate(curStsExpireDate);
            resLifeCycle.setOsSts(transfer.getOsStsTo());
            resLifeCycle.setReratingFlag(newLifeCycle.getReratingFlag());
            resLifeCycle.setResourceId(newLifeCycle.getResourceId());
            resLifeCycle.setUnbillingFlag(newLifeCycle.getUnbillingFlag());
            resLifeCycle.setUserrequestFlag(newLifeCycle.getUserrequestFlag());
            resLifeCycle.setFrauldFlag(newLifeCycle.getFrauldFlag());
            resLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        }

        return lifeCycleList;
    }

    // public CsdlArrayList<SImUserCycle> packageSImUserCycleForDCCActive(SImUserCycle newSImUserCycle)
    // {
    // CmResource userInfo = new CmResource();
    // CmCustomer custInfo = new CmCustomer();
    // CaAccount acctInfo = new CaAccount();
    // CoProd mainProdInfo = new CoProd();
    // // 获取匹配的参数
    // FirstActiveComponent fircstActiveCmp = context.getComponent(FirstActiveComponent.class);
    // fircstActiveCmp.getMatchParamByDCC(userInfo, custInfo, acctInfo, mainProdInfo);
    //
    // CsdlArrayList<SImUserCycle> userCycleList = new CsdlArrayList<SImUserCycle>(SImUserCycle.class);
    // Integer sImUserCycle = newSImUserCycle.get_userCycle();
    // Integer userDbSts = CommonUtil.string2Integer("10" + String.valueOf(sImUserCycle).substring(6));
    // String userMdbSts = String.valueOf(newSImUserCycle.get_userCycle()).substring(0, 6);
    // CmResLifecycle lifecycle = new CmResLifecycle();
    // lifecycle.setSts(userDbSts);
    // lifecycle.setOsSts(CommonUtil.string2Integer(String.valueOf(sImUserCycle).substring(0, 2)));
    // lifecycle.setExpireDate(DateUtil.UTCToDate(newSImUserCycle.get_expireDate()));
    // lifecycle.setValidDate(DateUtil.UTCToDate(newSImUserCycle.get_validDate()));
    // Date curStsExpireDate = lifecycle.getExpireDate();
    //
    // SImUserCycle userCycle = new SImUserCycle();
    //
    // SysGroupCyclePattern pattern = queryCaGroupCyclePattern(userInfo, custInfo, acctInfo);
    // // 计算用户的后续状态及生失效时间
    // SysCyclePatternTransfer transfer = getLifeCycleStsToByEvent(pattern.getPatternId(),
    // EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, lifecycle);
    // if (null == transfer)
    // {
    // // 状态转换非法，抛出异常
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCYLE_TRANSFER_ILLEGAL, lifecycle.getResourceId(),
    // lifecycle.getSts(), EnumCodeDefine.LIFECYCLE_EVENT_STATE_EXPIRE, pattern.getPatternId());
    // }
    //
    // // 当前状态的下一个状态的失效时间与用户的失效时间一致
    // userCycle.set_nextCycle(transfer.getStsTo());
    // userCycle.set_nextExpireDate(IMSUtil.getMdbDate(userInfo.getExpireDate()));
    //
    // Date stsExpireDate = calculateExpireDateByExtendDays(curStsExpireDate, detail.getValidDays()).getTime();
    //
    // userCycle.set_validDate(IMSUtil.getMdbDate(curStsExpireDate));
    // userCycle.set_syncFlag(context.getSyncFlag());
    // userCycle.set_expireDate(IMSUtil.getMdbDate(stsExpireDate));
    // userCycle.set_userCycle(CommonUtil.string2Integer(userMdbSts + String.valueOf(newSts).substring(2)));
    // userCycle.set_servId(newSImUserCycle.get_servId());
    // curStsExpireDate = stsExpireDate;
    // userCycleList.add(userCycle);
    //
    // return userCycleList;
    // }

    public CmResLifecycle packageNewResLifeCycle(CmResLifecycle resLifeCycle)
    {
        CmResLifecycle newLifeCycle = new CmResLifecycle();
        if (null != resLifeCycle.getCreateDate())
        {
            newLifeCycle.setCreateDate(resLifeCycle.getCreateDate());
        }

        newLifeCycle.setExpireDate(resLifeCycle.getExpireDate());
        newLifeCycle.setFrauldFlag(resLifeCycle.getFrauldFlag());
        newLifeCycle.setOsSts(resLifeCycle.getOsSts());
        newLifeCycle.setReratingFlag(resLifeCycle.getReratingFlag());
        newLifeCycle.setRecSts(resLifeCycle.getRecSts());
        newLifeCycle.setResourceId(resLifeCycle.getResourceId());
        if (null != resLifeCycle.getSoDate())
        {
            newLifeCycle.setSoDate(resLifeCycle.getSoDate());
        }

        newLifeCycle.setSoNbr(resLifeCycle.getSoNbr());
        newLifeCycle.setSts(resLifeCycle.getSts());
        newLifeCycle.setUnbillingFlag(resLifeCycle.getUnbillingFlag());
        newLifeCycle.setUserrequestFlag(resLifeCycle.getUserrequestFlag());
        newLifeCycle.setValidDate(resLifeCycle.getValidDate());
        return newLifeCycle;
    }

    /**
     * @Description: 订购unidentity product
     * @author: tangjl5
     * @Date: 2011-10-14
     */
    private void createUnidentityProd(SUserStatus sUserStatus, CmResource res)
    {
        createUserSpecProd(sUserStatus, res, SpecCodeDefine.USER_SUSPEND_UNIDENTIFIED);
    }

    /**
     * @Description: 根据busiFlag取消用户订购的特殊产品
     * @Date 2012-7-25 tangjl5 若是预付费用户，则返回在请求停机时保存的有效期天数
     */
    private Integer cancelUserSpecProd(CmResource res, Integer busiFlag)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.queryProdListByUserId(res.getResourceId(), busiFlag);
        CoProdCharValue charValue = null;
        if (CommonUtil.isNotEmpty(prodList))
        {
            CoProd coProd = prodList.get(0);
            if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID && context.get3hTree().loadUser3hBean(res.getResourceId()).getAccount().getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
            {
                charValue = context.getComponent(ProductQuery.class).queryCharValueByProdIdAndSpecId(coProd.getProductId(), SpecCodeDefine.USER_SUSPEND_REQUER_VALIDITY_DAYS, res.getResourceId());
            }
            
            context.getComponent(BaseProductComponent.class).deleteProd(coProd);
        }
        if (charValue != null)
        {
            imsLogger.debug("this prepaid user[" + res.getResourceId()+ "],have validity: " + charValue.getValue() + " days" , context);
            return CommonUtil.string2Integer(charValue.getValue());
        }
        
        return null;
    }

//    /**
//     * @Description: 用户购买停机保号产品
//     */
//    private void createUserReqSuspendProd(SUserStatus sUserStatus, CmResource res)
//    {
//        createUserSpecProd(sUserStatus, res, SpecCodeDefine.USER_SUSPEND_REQUEST);
//    }

    /**
     * @Description: 新建用户主动请求停机产品
     */
    private void createUserSpecProd(SUserStatus sUserStatus, CmResource res, Integer busiFlag) throws IMSException
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);

        PmProductOffering offering = prodCmp.queryOfferingByBusiFlag(busiFlag);

        if (null == offering)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_LACK_UNIDENTITY_OFFERING, busiFlag);
        }

        Long offeringId = offering.getProductOfferingId().longValue();

        if (offering.getIsMain().intValue() == EnumCodeDefine.PRODUCT_MAIN)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_PROD_CONFIG_ERROR, offeringId);
        }

        SProductOrderList prodList = new SProductOrderList();
        SProductOrder prodOrder = new SProductOrder();
        prodOrder.setUser_id(res.getResourceId());
        prodOrder.setOffer_id(offeringId);
        prodOrder.setPhone_id(sUserStatus.getPhone_id());
        if (busiFlag == SpecCodeDefine.USER_SUSPEND_REQUEST)
        {
            if (ProjectUtil.is_TH_AIS())
            {
                Calendar calendar = this.calculateExpireDateByExtendDays(context.getRequestDate(), sUserStatus.getReason_days());
                prodOrder.setValid_date(DateUtil.getFormatDate(calendar.getTime(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                prodOrder.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
            }
            else if (ProjectUtil.is_CN_SH())
            {
                // @Date 2012-04-24 lijc3 上海停机保号产品下周期生效
                prodOrder.setProduct_id(DBUtil.getSequence(CoProd.class));
                User3hBean bean = context.get3hTree().loadUser3hBean(res.getResourceId());
                Date validDate = context.getComponent(AccountQuery.class).queryAcctNextCycleStart(bean.getAcctId());
                validDate = validDate == null ? context.getRequestDate() : validDate;
                prodOrder.setValid_date(IMSUtil.formatDate(validDate));
                prodOrder.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
                prodOrder.setExpire_date(IMSUtil.formatDate(res.getExpireDate()));
            }
        }

        SProductOrder[] prodOrderArr = new SProductOrder[1];
        prodOrderArr[0] = prodOrder;
        
        // 预付费用户订购停机保号产品需要增加特征规格值12302，用于保存用户在设置为主动停机时的有效期天数。
        if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID && context.get3hTree().loadUser3hBean(res.getResourceId()).getAccount().getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
        {
            SProductParamList paramList = new SProductParamList();
            SProductParam[] paramArr = new SProductParam[1];
            SProductParam param = new SProductParam();
            param.setParam_id(SpecCodeDefine.USER_SUSPEND_REQUER_VALIDITY_DAYS);
            param.setParam_value(String.valueOf(DateUtil.getBetweenDays(context.getRequestDate(), context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle().getExpireDate())));
            paramArr[0] = param;
            paramList.setSProductParamList_Item(paramArr);
            prodOrder.setParam_list(paramList);
        }
        
        prodList.setItem(prodOrderArr);
        context.getComponent(BaseProductComponent.class).addProductInfo(prodList);
    }

    /**
     * @Description: 由ABM请求，更改用户的单停，双停，开机状态
     * @param res
     * @param cust
     * @param acct
     * @param sLifeCycleChg
     */
    public void lifeCycleChgByABM(Long resId, SLifeCycleChg4Abm sLifeCycleChg)
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(resId);
        CmResource user = user3hBean.getUser();
        CmResLifecycle curDate = user3hBean.getUserLifeCycle();
        CmResLifecycle updateDate = new CmResLifecycle();
        Integer stsTo = null;
        Integer osStsTo = null;
        
        switch (sLifeCycleChg.getTriggerEventType())
        {
            case EnumCodeDefine.LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_ACTIVE:
            {
                stsTo = EnumCodeDefine.LIFECYCLE_ACTIVE;
                osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE;
            }
                break;
            case EnumCodeDefine.LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_ONEWAY:
            {
                //@Date 2012-10-28 wangdw5 : PPS和POS(hybird按POS处理),ABM都调用这个事件,付费属性区分状态流转
                if(user.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
                {
                    stsTo = EnumCodeDefine.LIFECYCLE_SUSPEND_1WAY;
                    osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY;
                }
                else
                {
                    stsTo = EnumCodeDefine.LIFECYCLE_SUSPEND_CREDITLIMIT;
                    osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_ONEWAY;
                }
            }
                break;
            case EnumCodeDefine.LIFECYCLE_EVENT_CREDIT_REMAIN_CHANGE_TWOWAY:
            {
                stsTo = EnumCodeDefine.LIFECYCLE_SUSPEND_CREDITLIMIT;
                osStsTo = EnumCodeDefine.LIFECYCLE_OSSTS_TWOWAY;
    
            }
                break;
            default:
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_IS_ILLEGAL);
        }
        
        // 2012-5-24 yangjh story：46326 状态从1003改1001 不用修改 直接返回
        if (curDate.getSts() == EnumCodeDefine.LIFECYCLE_SUSPEND_DEBT && stsTo == EnumCodeDefine.LIFECYCLE_ACTIVE){
            //增加日志输出
            imsLogger.info("life cycle sts need not change from 1003 to 1001");
            return;
        }
        CmResource res = user3hBean.getUser();
        CaAccount acct = user3hBean.getAccount();
        CmCustomer cust = user3hBean.getCustomer();
        SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

//        if (null == groupCyclePatten)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
//        }

        if (curDate.getSts() != stsTo)
        {
            updateDate.setValidDate(context.getRequestDate());
            SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(stsTo.shortValue(),
                    groupCyclePatten.getPatternId());
            if (null != sysCycPatternDetail)
            {
                if (sysCycPatternDetail.getValidDays() == -1)
                {
                    updateDate.setExpireDate(res.getExpireDate());
                }
                else
                {
                    Calendar calendar = this.calculateExpireDateByExtendDays(context.getRequestDate(),
                            sysCycPatternDetail.getValidDays());
                    //@Date 2012-10-28 wangdw5 : active -> suspend-1-way ,lifecycle记录的expire_date取配置的失效时间和validity记录的失效时间较短的
                    Date tmpExpireDate = calendar.getTime();
                    if(stsTo == EnumCodeDefine.LIFECYCLE_SUSPEND_1WAY)
                    {
                        CmResValidity cmResValidity = queryUserValidity(resId);
                        if(cmResValidity == null)
                        {
                            tmpExpireDate = context.getRequestDate();
                        }
                        else if(cmResValidity.getExpireDate().before(calendar.getTime()))
                        {
                            tmpExpireDate = cmResValidity.getExpireDate();
                        }
                    }
                    updateDate.setExpireDate(tmpExpireDate);
                }
            }
            else
            {
                // 用户在sys_cycle_pattern_detail中未找到对应的模式
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, stsTo,
                        groupCyclePatten.getPatternId());
            }
        }
        // @Date 2012-04-24 lijc3 上海需求，停机需要取消停机保号产品，立即失效
        if (stsTo != EnumCodeDefine.LIFECYCLE_ACTIVE && ProjectUtil.is_CN_SH())
        {
            CoProd dmProd = getUserSuspendReqProd(resId);
            if (dmProd != null)
            {
                context.getComponent(BaseProductComponent.class).deleteProdImmediate(dmProd);
            }
        }

        updateDate.setSts(stsTo);
        updateDate.setOsSts(osStsTo);
        updateDate.setResourceId(resId);

        // 更新用户的生命周期状态
        CmResLifecycle updateLifeCycle = modifyUserLifeCycle(null, updateDate, curDate);
        
        if (user.getBillingType() != EnumCodeDefine.USER_PAYMODE_PREPAID && ProjectUtil.is_TH_AIS() && 
                sLifeCycleChg.getOldAmount() <= 0 || sLifeCycleChg.getNewAmount() <= 0)
        {
            // @Date 2012-08-03 tangjl5 :Story # 53987 后付费用户信用度变更同步给crm
            ImsCreditStatusSync cmCreditStatusSync = new ImsCreditStatusSync();
            cmCreditStatusSync.setId(DBUtil.getSequence());
            cmCreditStatusSync.setPhoneId(user3hBean.getPhoneId());
            cmCreditStatusSync.setFlag(0);
            cmCreditStatusSync.setCreditLimit(sLifeCycleChg.getNewAmount());
            cmCreditStatusSync.setNewStatus(updateLifeCycle.getSts());
            cmCreditStatusSync.setNewSubType(updateLifeCycle.getOsSts());
            cmCreditStatusSync.setOldStatus(curDate.getSts());
            cmCreditStatusSync.setOldSubType(curDate.getOsSts());
            cmCreditStatusSync.setTriggerDate(context.getRequestDate());
            cmCreditStatusSync.setSoNbr(context.getSoNbr());
            cmCreditStatusSync.setSts(1);
            if (sLifeCycleChg.getNewAmount() < 0)
            {
                cmCreditStatusSync.setOverUsage(sLifeCycleChg.getNewAmount());
            }
            else
            {
                // 来至wuyq邮件：over_usage原来的本意是透支的金额，对于没有透支的情况，金额就填0。
                cmCreditStatusSync.setOverUsage(0L);
            }
            
            cmCreditStatusSync.setCreateDate(context.getRequestDate());
            insert(cmCreditStatusSync);
        }

        insertResLifeCycReg(curDate, updateLifeCycle);

        String phoneId = user3hBean.getPhoneId();

        insertUserStsSync(packageCmResStsSync4ABM(updateLifeCycle, curDate, phoneId, sLifeCycleChg));
    }

    /**
     * @Description: 告警通知
     * @param res
     * @param acct
     * @param cust
     * @param resLifeCycle
     * @return
     */
    public List<IMSNotification> createNotifications(CmResource res, CaAccount acct, CmCustomer cust,
            CmResLifecycle newResLifeCycle, CmResLifecycle oldResLifeCycle)
    {
        List<IMSNotification> notifyList = new ArrayList<IMSNotification>();
        // 状态变更发送告警
        if (newResLifeCycle.getSts() != oldResLifeCycle.getSts().intValue())
        {
            IMSNotification imsNotify = new IMSNotification();
            imsNotify.setAlarmId(IMSUtil.getNotificationIdByBusiSpecId(context.getBusiSpecId(true,
                    BusiSpecDefine.NOTIFY_LIFECYCLE_STS_CHANGE),context.getOper().getSo_mode()));

            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_OLD_STS, String.valueOf(oldResLifeCycle.getSts()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_NEW_STS, String.valueOf(newResLifeCycle.getSts()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_STS_CHANGE_EXPIREDATE,
                    String.valueOf(DateUtil.formatDate(oldResLifeCycle.getExpireDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS)));

            imsNotify.setUserId(res.getResourceId());
            imsNotify.setAcctId(acct.getAcctId());
            imsNotify.setCustId(cust.getCustId());
            imsNotify.setRegionCode(res.getRegionCode().shortValue());

//            imsNotify.addPhone(context.getComponent(UserComponent.class).queryPhoneIdByUserId(res.getResourceId()));
            notifyList.add(imsNotify);
        }

        // active fraud -> active 促发提醒,active->active frauld 充值9次促发frauld已经由充值模块发送告警
        if ((newResLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE
                && newResLifeCycle.getFrauldFlag() == EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG && oldResLifeCycle.getFrauldFlag() == EnumCodeDefine.LIFECYCLE_FRAULD_FLAG)
                || (newResLifeCycle.getSts() != EnumCodeDefine.LIFECYCLE_ACTIVE && oldResLifeCycle.getFrauldFlag() != newResLifeCycle
                        .getFrauldFlag().intValue()))
        {
            IMSNotification imsNotify = new IMSNotification();
            imsNotify.setAlarmId(IMSUtil.getNotificationIdByBusiSpecId(context.getBusiSpecId(true,
                    BusiSpecDefine.NOTIFY_LIFECYCLE_FRAULD_FLAG_CHANGE),context.getOper().getSo_mode()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_OLD_FRAULD_FLAG, String.valueOf(oldResLifeCycle.getFrauldFlag()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_NEW_FRAULD_FLAG, String.valueOf(newResLifeCycle.getFrauldFlag()));
            imsNotify.setUserId(res.getResourceId());
            imsNotify.setAcctId(acct.getAcctId());
            imsNotify.setCustId(cust.getCustId());
            imsNotify.setRegionCode(res.getRegionCode().shortValue());

//            imsNotify.addPhone(context.getComponent(UserComponent.class).queryPhoneIdByUserId(res.getResourceId()));
        }

        if (newResLifeCycle.getUserrequestFlag().intValue() != oldResLifeCycle.getUserrequestFlag())
        {
            IMSNotification imsNotify = new IMSNotification();
            imsNotify.setAlarmId(IMSUtil.getNotificationIdByBusiSpecId(context.getBusiSpecId(true,
                    BusiSpecDefine.NOTIFY_LIFECYCLE_USERREQ_FLAG_CHANGE),context.getOper().getSo_mode()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_OLD_USERREQ_FLAG,
                    String.valueOf(oldResLifeCycle.getUserrequestFlag()));
            imsNotify.addParam(AlarmCodeDefine.PARAM_LIFECYCLE_NEW_USERREQ_FLAG,
                    String.valueOf(newResLifeCycle.getUserrequestFlag()));
            imsNotify.setUserId(res.getResourceId());
            imsNotify.setAcctId(acct.getAcctId());
            imsNotify.setCustId(cust.getCustId());
            imsNotify.setRegionCode(res.getRegionCode().shortValue());

//            imsNotify.addPhone(context.getComponent(UserComponent.class).queryPhoneIdByUserId(res.getResourceId()));
            notifyList.add(imsNotify);

        }

        return notifyList;
    }

    /**
     * @Description: 包装ImsResStsSync
     */
    private ImsResStsSync packageCmResStsSync4ABM(CmResLifecycle newLifeCycle, CmResLifecycle oldLifeCycle, String phoneId,
            SLifeCycleChg4Abm sLifeCycleChg)
    {
        SLifeCycleByAcctChgReq lifeCycleReq = new SLifeCycleByAcctChgReq();
        lifeCycleReq.setNbalance(sLifeCycleChg.getNewAmount());
        lifeCycleReq.setPbalance(sLifeCycleChg.getOldAmount());

        ImsResStsSync imsResStsSync = packageCmResStsSync(newLifeCycle, oldLifeCycle, sLifeCycleChg.getAcctId(), phoneId,
                lifeCycleReq, sLifeCycleChg.getBillType());

        Integer reasonCode = LifeCycleHelper.getImsResStsSyncReasonCode(sLifeCycleChg.getTriggerEventType(),
                sLifeCycleChg.getBillType());
        imsResStsSync.setReasonCode(reasonCode);
        return imsResStsSync;
    }

    /**
     * 初始化一个用户的生命周期
     */
    public CmResLifecycle initUserLifeCycle(CmResource res, CaAccount acct, CmCustomer cust, Long mainProdOfferId)
    {
        CmResLifecycle dmResLife = new CmResLifecycle();
        dmResLife.setResourceId(res.getResourceId());
        dmResLife.setSoNbr(context.getSoNbr());
        dmResLife.setValidDate(res.getValidDate());
        dmResLife.setExpireDate(res.getExpireDate());
        dmResLife.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        Integer sts = queryLifeCycleNorInitSts(res, cust, acct, mainProdOfferId);

        dmResLife.setSts(sts);
        dmResLife.setOsSts(EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE);
        dmResLife.setFrauldFlag(0);
        dmResLife.setReratingFlag(0);
        dmResLife.setUnbillingFlag(0);
        dmResLife.setUserrequestFlag(0);

        // @Date 2012-4-4 tangjl5 预后付费在新装时都设置下一个状态，下一个状态与当前状态一致
        
        // @Date 2012-6-21 tangjl5 BUG #45585 用户next_sts设置不对
        dmResLife.setNextSts(CommonUtil.string2Integer(getUserFullSts(dmResLife)));
        dmResLife.setNextStsExpiredate(res.getExpireDate());
        // @Date 2012-06-14 wukl 增加生命周期状态停机位的上发
        dmResLife.setOsStsDtl(0);

        super.insert(dmResLife);
        return dmResLife;
    }

    /**
     * @Description: 实例化用户生命周期对应的有效期
     * @param res
     * @param sLifeCycleMgnt
     * @param acct
     */
    public void initUserValidity(CmResource res, Long acctId, Date validityExpireDate, SUserStatus sLifeCycleMgnt)
    {
        CmResValidity resValidity = new CmResValidity();
        if (CommonUtil.isValid(sLifeCycleMgnt.getValid_date()))
        {
            resValidity.setValidDate(IMSUtil.formatDate(sLifeCycleMgnt.getValid_date()));
        }
        else
        {
            resValidity.setValidDate(res.getValidDate());
        }
        resValidity.setResourceId(res.getResourceId());
        resValidity.setAccountId(acctId);
        resValidity.setSoNbr(context.getSoNbr());
        if (null != validityExpireDate)
            resValidity.setExpireDate(validityExpireDate);
        else
            resValidity.setExpireDate(res.getExpireDate());
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setUserValidDate(resValidity.getValidDate());
        resValidity.setUserExpireDate(resValidity.getExpireDate());
        resValidity.setEffectFlag(EnumCodeDefine.IS_VALID_DATA);
        insert(resValidity);
    }

    /**
     * @Description: 首次激活初始化用户有效期，将resource_id设置为1：表示这个账户下有用户
     * @param res
     * @param acctId
     * @param validityExpireDate
     * @author: tangjl5
     * @Date: 2011-11-25
     */
    public void initValidity4FirstActive(CmResource res, Long acctId, Date validityExpireDate, Long changeDays,
            SUserStatus sLifeCycleMgnt)
    {
        // 2010-01-06 wukl 先拷贝对象，再修改值
        CmResource resource = new CmResource();
        ClassUtil.copy(res, resource);
        resource.setResourceId(1l);
        initUserValidity(resource, acctId, validityExpireDate, sLifeCycleMgnt);
    }

    /**
     * @Description: 当用户没有用户有效期时，初始化用户有效期
     * @param res
     * @param acctId
     * @param validityExpireDate
     * @author: tangjl5
     * @Date: 2011-11-25
     * @Date: 2012-3-1 tangjl5设值有效期变更前天数和变更后天数
     */
    public void initResValidity4TopUp(Long resId, Long acctId, Date validityExpireDate)
    {
        CmResValidity resValidity = new CmResValidity();
        resValidity.setResourceId(resId);
        resValidity.setSoNbr(context.getSoNbr());
        resValidity.setValidDate(context.getRequestDate());
        resValidity.setExpireDate(validityExpireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        //lijc3 add
        if(ProjectUtil.is_CN_SH()){
            resValidity.setEffectFlag(EnumCodeDefine.IS_VALID_DATA);
            resValidity.setUserExpireDate(validityExpireDate);
            resValidity.setUserValidDate(context.getRequestDate());
            resValidity.setAccountId(0L);
        }else{
            resValidity.setAccountId(acctId);
            resValidity.setUserValidDate(resValidity.getValidDate());
            resValidity.setUserExpireDate(resValidity.getExpireDate());
            resValidity.setEffectFlag(EnumCodeDefine.IS_VALID_DATA);
        }
        resValidity.setBeforeChangeDays(0L);
        resValidity.setAfterChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), validityExpireDate));
        insert(resValidity);
    }

    /**
     * @Description:给账户top-up时，当账户下没有账户级有效期时，初始化有效期
     * @param acctId
     * @param validityExpireDate
     * @param changeDays
     * @author: tangjl5
     * @Date: 2012-2-2
     * @Date: 2012-3-1 tangjl5设值有效期变更前天数和变更后天数
     */
    public void initAcctValidity4TopUp(Long acctId, Date validityExpireDate)
    {
        CmResValidity resValidity = new CmResValidity();
        resValidity.setAccountId(acctId);
        resValidity.setResourceId(0L);
        resValidity.setSoNbr(context.getSoNbr());
        resValidity.setValidDate(context.getRequestDate());
        resValidity.setExpireDate(validityExpireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setBeforeChangeDays(0L);
        resValidity.setAfterChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), validityExpireDate));
        resValidity.setUserValidDate(resValidity.getValidDate());
        resValidity.setUserExpireDate(resValidity.getExpireDate());
        resValidity.setEffectFlag(EnumCodeDefine.IS_VALID_DATA);
        insert(resValidity);
    }

    public void initValidity4PayMent(Long acctId, Date validityExpireDate, Date validDate)
    {
        CmResValidity resVelidity = new CmResValidity();
        resVelidity.setResourceId(1L);
        resVelidity.setAccountId(acctId);
        resVelidity.setSoNbr(context.getSoNbr());
        resVelidity.setValidDate(validDate);
        resVelidity.setExpireDate(validityExpireDate);
        resVelidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resVelidity.setUserValidDate(resVelidity.getValidDate());
        resVelidity.setUserExpireDate(resVelidity.getExpireDate());
        resVelidity.setEffectFlag(EnumCodeDefine.IS_VALID_DATA);
        insert(resVelidity);
    }

    /**
     * @Description: 根据失效时间与延长天数，计算新的失效时间
     * @param oldExpireDate
     * @param extendDays
     * @return
     */
    public Calendar calculateExpireDateByExtendDays(Date oldExpireDate, int extendDays)
    {
        Calendar expireCal = Calendar.getInstance();
        expireCal.setTime(oldExpireDate);

        // 计算延长后的有效期
        expireCal.add(Calendar.DATE, extendDays);
        return expireCal;
    }

    /**
     * @Description: 实例化用户订购主动停机产品的产品特征值表
     * @param sUserStatus
     * @param coProd
     */
    /*
     * public void createCoProdCharValue(SUserStatus sUserStatus, CoProd coProd) { CoProdCharValue coProdCharValue = new
     * CoProdCharValue(); coProdCharValue.setGroupId(EnumCodeDefine.PROD_CHAR_VALUE_NOT_GROUP);
     * coProdCharValue.setProductId(coProd.getProductId());
     * coProdCharValue.setSpecCharId(SpecCodeDefine.USER_SUSPEND_REQEUST_FREE_DAYS);
     * coProdCharValue.setValue(String.valueOf(sUserStatus.getReason_days()));
     * coProdCharValue.setValidDate(coProd.getValidDate()); coProdCharValue.setExpireDate(coProd.getExpireDate());
     * coProdCharValue.setObjectId(coProd.getObjectId()); coProdCharValue.setObjectType(coProd.getObjectType());
     * coProdCharValue.setSoNbr(this.getContext().getDoneCode()); insert(coProdCharValue); }
     */

    /**
     * @Description: 实例化用户订购主动停机产品的账期表
     * @param coProd
     * @throws IMSException
     */
    /*
     * public CoProdBillingCycle createProdBillCycle4UserSuspendReq(Long productId, Long offering, SUserStatus sUserStatus) throws
     * IMSException { Calendar calendar = this.calculateExpireDateByExtendDays( IMSUtil.formatDate(sUserStatus.getValid_date(),
     * context.getRequestDate()), sUserStatus.getReason_days()); // 获取产品的失效时间 CoProdBillingCycle coProdBillCycle =
     * context.getComponent(ProductCycleComponent.class).parseProdBillingCycle(offering, null,
     * DateUtil.getFormatDate(calendar.getTime(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), null, (short)
     * EnumCodeDefine.PROD_VALID_SPECIFIC_DATE,null,null); coProdBillCycle.setSoNbr(context.getDoneCode());
     * coProdBillCycle.setDeductFlag(EnumCodeDefine.BILLING_CYCLE_DEDUCT_FLAG_POSTPAID); coProdBillCycle.setProductId(productId);
     * insert(coProdBillCycle); return coProdBillCycle; }
     */

    /**
     * @Description: 实例化用户订购主动停机产品的产品使用表
     * @param sUserStatus
     * @param coProd
     * @param payId
     * @throws IMSException
     */
    /*
     * public void createProdInvolveObject(SUserStatus sUserStatus, CoProd coProd, long payId) throws IMSException { CoProdInvObj
     * cpoObject = new CoProdInvObj(); cpoObject.setProductId(coProd.getProductId());
     * cpoObject.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV); cpoObject.setObjectId(payId);
     * cpoObject.setValidDate(coProd.getValidDate()); cpoObject.setExpireDate(coProd.getExpireDate());
     * cpoObject.setSoNbr(this.getContext().getDoneCode()); insert(cpoObject); }
     */

    /**
     * @Description: 新建用户主动请求停机产品
     * @param sUserStatus
     * @return
     * @throws IMSException
     */
    /*
     * public CoProd createUserSuspendRequestProd(SUserStatus sUserStatus, CmResource res) throws IMSException { ProductComponent
     * prodCmp = context.getComponent(ProductComponent.class); Integer offeringId =
     * prodCmp.queryOfferingId(SpecCodeDefine.USER_SUSPEND_REQUEST); if (CommonUtil.isEmpty(offeringId)) { throw
     * IMSUtil.throwBusiException(ErrorCodeDefine.LACK_OF_BASE_DATE, "lack date of offering which busi_code is " +
     * SpecCodeDefine.USER_SUSPEND_REQUEST); } CoProd dmProd = new CoProd();
     * dmProd.setProductId(DBUtil.getSequence(CoProd.class)); dmProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);//
     * ProductComponent dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// Active
     * dmProd.setProductOfferingId(offeringId); Calendar calendar = this.calculateExpireDateByExtendDays(
     * IMSUtil.formatDate(sUserStatus.getValid_date(), context.getRequestDate()), sUserStatus.getReason_days());
     * dmProd.setValidDate(calendar.getTime()); // 获取产品的失效时间 CoProdBillingCycle coProdBillCycle =
     * createProdBillCycle4UserSuspendReq(dmProd.getProductId(), offeringId.longValue(), sUserStatus);
     * dmProd.setPricingPlanId(prodCmp.queryPricePlanId(offeringId, null, res.getResourceId()));
     * dmProd.setBusiFlag(SpecCodeDefine.USER_SUSPEND_REQUEST); dmProd.setBillingType(res.getBillingType());// postPaid
     * dmProd.setCreateDate(context.getRequestDate()); dmProd.setObjectId(obj); dmProd.setObjectType(obj);
     * dmProd.setExpireDate(coProdBillCycle.getExpireDate()); dmProd.setSoNbr(this.getContext().getDoneCode()); // ljc
     * dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON); insert(dmProd); return dmProd; }
     */

    /**
     * @Description: 通知错误数据写入错误信息表
     * @param alarmId
     * @param onceNotify
     * @author: tangjl5
     * @Date: 2012-2-24
     */
    public void writeNotifyError(Integer alarmId, ImsResLifecycleNotify onceNotify)
    {
        if (onceNotify == null)
            return;
        // 2012-06-01 zhangzj3 规范表名，imsResLifeCycleNotifyError改为ImsResLifecycleNotifyRst
        ImsResLifecycleNotifyRst error = new ImsResLifecycleNotifyRst();
        error.setCreateDate(context.getRequestDate());
        error.setNsts(onceNotify.getNsts());
        error.setPsts(onceNotify.getPsts());
        // error.setNotificationId(alarmId);
        insert(error);
    }

    public void setReratingFlag(Long resource_id, Integer rerating_flag)
    {
        updateUserLifeCycleHisSts(resource_id);

        CmResLifecycle lifeCycle = new CmResLifecycle();
        lifeCycle.setReratingFlag(rerating_flag);
        lifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        updateByCondition(lifeCycle, new DBCondition(CmResLifecycle.Field.resourceId, resource_id));
    }

    /**
     * @Description: 首次激活调用，先设置用户有效期后更改用户生命周期状态
     * @param res
     * @param cust
     * @param acct
     * @param sLifeCycleMgnt
     * @author: tangjl5
     * @Date: 2011-11-25
     */
    public void updateLifeCycleAndValidityByFirstActive(CmResource res, CmCustomer cust, CaAccount acct,
            SUserStatus sLifeCycleMgnt)
    {
        Acct3hBean billAcct3hBean = context.get3hTree().loadUser3hBean(res.getResourceId()).getBillAcct3hBean();
        Long billAcctId = billAcct3hBean.getAcctId();

        SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

//        if (null == groupCyclePatten)
//        {
//            // 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
//        }

        Date expireDate = null;

        SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(sLifeCycleMgnt.getNew_state(),
                groupCyclePatten.getPatternId());

        if (null == sysCycPatternDetail)
        {
            // 用户在sys_cycle_pattern_detail中未找到对应的模式
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, sLifeCycleMgnt.getNew_state(),
                    groupCyclePatten.getPatternId());
        }

        // 若不存在有效期，则实例化有效期
        // if (null == validity)
        // {
        // expireDate = this.calculateExpireDateByExtendDays(
        // DateUtil.getFormatDate(sLifeCycleMgnt.getValid_date(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),
        // sysCycPatternDetail.getValidDays()).getTime();
        // initUserValidity(res, acct.getAcctId(), expireDate, sLifeCycleMgnt);
        // }
        // else
        // {
        // // 判断是否有用户，若有则不处理，否则延长有效期
        // if (validity.getResourceId() == 0)
        // {
        // expireDate = this.calculateExpireDateByExtendDays(validity.getExpireDate(), sysCycPatternDetail.getValidDays())
        // .getTime();
        // initValidity4FirstActive(res, acct.getAcctId(), expireDate, (long) sysCycPatternDetail.getValidDays(),
        // sLifeCycleMgnt);
        // }
        // }

        // 实例化账户级账本
        if (billAcct3hBean.getAccount().getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE)
        {
            expireDate = this.calculateExpireDateByExtendDays(
                    DateUtil.getFormatDate(sLifeCycleMgnt.getValid_date(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),
                    sysCycPatternDetail.getValidDays()).getTime();
            initAcctValidity(res, acct.getAcctId(), expireDate, sLifeCycleMgnt);
        }
        else
        {
            expireDate = this.calculateExpireDateByExtendDays(
                    DateUtil.getFormatDate(sLifeCycleMgnt.getValid_date(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),
                    sysCycPatternDetail.getValidDays()).getTime();
            initUserValidity(res, billAcctId, expireDate, sLifeCycleMgnt);
        }
        CmResLifecycle curLifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();

        // 2011-11-29 wukl 首次激活时，若用户当前状态不是idle，不允许激活
        if (curLifeCycle == null || curLifeCycle.getSts().intValue() != EnumCodeDefine.LIFECYCLE_IDLE_INITIAL)
            IMSUtil.throwBusiException(ErrorCodeDefine.USER_DO_NOT_ACTIVE);

        CmResLifecycle newCmResLifeCycle = new CmResLifecycle();
        newCmResLifeCycle.setResourceId(res.getResourceId());
        newCmResLifeCycle.setSoNbr(context.getSoNbr());
        newCmResLifeCycle.setSts(sLifeCycleMgnt.getNew_state().intValue());
        newCmResLifeCycle.setExpireDate(expireDate);
        newCmResLifeCycle.setOsSts(EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE);
        if (CommonUtil.isValid(sLifeCycleMgnt.getUnbilling_flag()))
            newCmResLifeCycle.setUnbillingFlag(sLifeCycleMgnt.getUnbilling_flag().intValue());

        // 更新用户有效期
        CmResLifecycle insertLifecycle = modifyUserLifeCycle(sLifeCycleMgnt, newCmResLifeCycle, curLifeCycle);

        insertResLifeCycReg4UnDeal(curLifeCycle, insertLifecycle, null, null);
    }

    /**
     * @Description: 初始化账户有效期
     * @author: tangjl5
     * @Date: 2012-3-15
     */
    public void initAcctValidity(CmResource res, Long acctId, Date validityExpireDate, SUserStatus sLifeCycleMgnt)
    {
        CmResValidity resValidity = new CmResValidity();
        if (CommonUtil.isValid(sLifeCycleMgnt.getValid_date()))
        {
            resValidity.setValidDate(IMSUtil.formatDate(sLifeCycleMgnt.getValid_date()));
        }
        else
        {
            resValidity.setValidDate(res.getValidDate());
        }
        resValidity.setResourceId(0L);
        resValidity.setAccountId(acctId);
        resValidity.setSoNbr(context.getSoNbr());
        if (null != validityExpireDate)
            resValidity.setExpireDate(validityExpireDate);
        else
            resValidity.setExpireDate(res.getExpireDate());
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        insert(resValidity);
    }

    public void updateUserValidity(Long userId, Date expireDate)
    {
        // 更改历史数据的状态
        updateResValidityHisSts4Nor(userId);

        CmResValidity resValidity = new CmResValidity();
        resValidity.setExpireDate(expireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setUserExpireDate(expireDate);

        updateByCondition(resValidity, new DBCondition(CmResValidity.Field.resourceId, userId));
    }

    /**
     * @Description: 更新用户有效期for changeOwner
     * @param userId
     * @param acctId
     * @param expireDate
     * @param validDate
     * @author: tangjl5
     * @Date: 2012-2-9
     */
    public void updateUserValidForChgOwner(Long userId, Long acctId, Date expireDate, Date validDate)
    {
        // 更改历史数据的状态
        updateResValidityHisSts4Nor(userId);

        CmResValidity resValidity = new CmResValidity();
        resValidity.setExpireDate(expireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setAccountId(acctId);
        resValidity.setValidDate(validDate);
        resValidity.setUserExpireDate(expireDate);

        updateByCondition(resValidity, new DBCondition(CmResValidity.Field.resourceId, userId));
    }

    /**
     * changeOwner转移有效期处理方式 (用户级账本&single balance 作为用户级账本处理) 1、single balance 转 single balance 变更为目标账户下的用户中最短的有效期，再参考days字段
     * 2、single balance 转 用户级账本：先取目标账户的账户级有效期，再参考days字段 3、用户级账本 转 用户级账本： 直接取用户原有效期,再参考days字段 4、用户级账本 转single balance：取目标账户的有效期 +
     * 原用户级有效期,再参考days字段 days使用：以老的有效期（不存在则取系统当前时间）为基点，往后推_days天数
     * 
     * @param res 用户的信息
     * @param acct 新的归属账户信息
     * @param cust 客户的信息
     * @param oldAcct 原归属账户信息
     * @param days
     */
    public void updateValidityByChangeOwner(Long userId, CaAccount oldAcct, long days, CmResValidity oldValidity)
    {
        User3hBean ims3hBean = context.get3hTree().loadUser3hBean(userId);
        CmCustomer cust = ims3hBean.getCustomer();
        CaAccount acct = ims3hBean.getAccount();
        CmResource res = ims3hBean.getUser();
        long sbMaxChgDays = SysUtil.getInt(ConstantDefine.SINGLE_BALANCE_MAX_VALIDITY);

        // 若为混合模式（single balance账本和用户级账本）的账户，则按用户级处理
        boolean isFromSingle = oldAcct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE;
        boolean isToSingle = acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE;

        if (isFromSingle && isToSingle)// single balance 转 single balance
        {
            // 判断用户原先账户下是否还有用户，只有该账户下只有这个用户时，才可将原有账户的有效转入目标账户中
            boolean transferFlag = context.getComponent(AccountQuery.class).isAcctHasUserExpCurrentOrNoUser(oldAcct.getAcctId(),
                    res.getResourceId());
            Integer acctMaxValidity = getMaxValidity(acct.getAcctId()).intValue();
            if (sbMaxChgDays > acctMaxValidity)
            {
                sbMaxChgDays = acctMaxValidity;
            }
            
            this.single2single(acct.getAcctId(), oldAcct.getAcctId(), days, sbMaxChgDays, transferFlag, oldValidity);
        }
        else if (isFromSingle && !isToSingle) // single balance 转 用户级账本
        {
            this.single2user(oldAcct.getAcctId(), res, days, oldValidity);
        }

        else if (!isFromSingle && !isToSingle) // 用户级账本 转 用户级账本
        {
            this.user2user(res.getResourceId(), days, oldValidity);
        }
        else if (!isFromSingle && isToSingle) // 用户级账本 转single balance
        {
            Integer acctMaxValidity = getMaxValidity(acct.getAcctId()).intValue();
            if (sbMaxChgDays > acctMaxValidity)
            {
                sbMaxChgDays = acctMaxValidity;
            }

            // 判断用户原先账户下是否还有用户，只有该账户下只有这个用户时，才可将用户的有效转入账户中
            // boolean transferFlag = context.getComponent(AccountQuery.class).isAcctHasUserExpCurrentUser(oldAcct.getAcctId(),
            // res.getResourceId());
            this.user2single(res.getResourceId(), acct.getAcctId(), days, sbMaxChgDays, oldValidity);
        }

        this.updateLifeCycleByChangeOwner(res, acct, cust);
    }

    /**
     * @Description: 用户级账本转single balance
     * @param resId
     * @param acctId
     * @param days
     * @param sbMaxChgDays
     * @return
     * @author: tangjl5
     * @Date: 2012-2-10
     */
    private Date user2single(Long resId, Long acctId, long days, long sbMaxChgDays, CmResValidity userValidity)
    {
        long actulExtendDays = 0;
        long userValidDays = 0;
        long acctValidDays = 0;
        Date newExpireDate = null;
        CmResValidity acctValidity = queryAcctValidity(acctId);
        if (null != acctValidity)
        {
            acctValidDays = DateUtil.getBetweenDays(context.getRequestDate(), acctValidity.getExpireDate());

            // 若sinle balance的有效期天数大于等于账户可延长的最大有效期期天数时，直接返回，不进行延长操作。
            if (acctValidDays >= sbMaxChgDays)
            {
                return acctValidity.getExpireDate();
            }
            else
            {
                if (userValidity != null)
                {
                    // 用户账本转入single balance规则：取目标账户的有效期 + 原用户级有效期+days
                    userValidDays = DateUtil.getBetweenDays(context.getRequestDate(), userValidity.getExpireDate());

                    // 若账户已有的有效期天数+欲延长的用户原有的有效期天数之和大于账户可延长的有效天数，则只延长账户可延长的最大有效天数
                    if (acctValidDays + userValidDays >= sbMaxChgDays)
                    {
                        actulExtendDays = sbMaxChgDays - acctValidDays;
                    }
                    else
                    {
                        if (days > 0)
                        {
                            if (acctValidDays + userValidDays + days >= sbMaxChgDays)
                            {
                                actulExtendDays = sbMaxChgDays - acctValidDays;
                            }
                            else
                            {
                                actulExtendDays = userValidDays + days;
                            }
                        }
                        else
                        {
                            // @Date 2012-6-8 tangjl5 Bug #48034 用户的归属账户从用户级账本转移到账户级账本，有效期前后没有变化
                            actulExtendDays = userValidDays;
                        }
                    }
                }
                else
                {
                    if (days > 0)
                    {
                        if (acctValidDays + days >= sbMaxChgDays)
                        {
                            actulExtendDays = sbMaxChgDays - acctValidDays;
                        }
                        else
                        {
                            actulExtendDays = days;
                        }
                    }
                }
            }

            newExpireDate = calculateExpireDateByExtendDays(acctValidity.getExpireDate(), (int) actulExtendDays).getTime();
        }
        else
        {
            if (null != userValidity)
            {
                userValidDays = DateUtil.getBetweenDays(context.getRequestDate(), userValidity.getExpireDate());
                if (userValidDays >= sbMaxChgDays)
                {
                    actulExtendDays = sbMaxChgDays;
                }
                else
                {
                    if (days > 0)
                    {
                        if (userValidDays + days >= sbMaxChgDays)
                        {
                            actulExtendDays = sbMaxChgDays - userValidDays;
                        }
                        else
                        {
                            actulExtendDays = userValidDays + days;
                        }
                    }
                    else
                    {
                        actulExtendDays = userValidDays;
                    }
                }
            }
            else
            {
                if (days > 0)
                {
                    if (days >= sbMaxChgDays)
                    {
                        actulExtendDays = sbMaxChgDays;
                    }
                    else
                    {
                        actulExtendDays = days;
                    }
                }
            }

            newExpireDate = calculateExpireDateByExtendDays(context.getRequestDate(), (int) actulExtendDays).getTime();
        }

        if (newExpireDate != null)
        {
            deleteResValidity(resId);

            if (queryAcctValidity(acctId) == null)
            {
                this.initAcctValidity4TopUp(acctId, newExpireDate);
            }
            else
            {
                this.updateAcctValidity4TopUp(acctValidity, newExpireDate);
            }
        }

        return newExpireDate;
    }

    private Date user2user(Long resId, long days, CmResValidity validity)
    {
        Date expireDate = null;
        
        if (validity != null)
        {
            expireDate = validity.getExpireDate();
        }

        // 参考days字段
        if (days > 0)
        {
            if (expireDate == null)
            {
                expireDate = calculateExpireDateByExtendDays(context.getRequestDate(), (int) days).getTime();
            }
            else
            {
                expireDate = calculateExpireDateByExtendDays(expireDate, (int) days).getTime();
            }

            updateUserValidity(resId, expireDate);
        }

        return expireDate;
    }

    private Date single2user(Long acctId, CmResource res, long days, CmResValidity acctValidity)
    {
        Date expireDate = null;
        if (acctValidity != null)
        {
            expireDate = acctValidity.getExpireDate();
        }

        // 参考days字段
        if (days > 0)
        {
            if (expireDate == null)
            {
                expireDate = calculateExpireDateByExtendDays(context.getRequestDate(), (int) days).getTime();
            }
            else
            {
                expireDate = calculateExpireDateByExtendDays(expireDate, (int) days).getTime();
            }
        }

        // 若是最后一个用户转出则删除账户的有效期
        if (context.getComponent(AccountQuery.class).isAcctHasUserExpCurrentOrNoUser(acctId, res.getResourceId()))
        {
            deleteAcctValidity(acctId);

            // 如果新的有效期存在，则实例化账户有效期，原账户有效期不处理
            if (expireDate != null)
            {
                SUserStatus sLifeCycleMgnt = new SUserStatus();
                // @Date 2012-08-16 tangjl5 :Bug # 52819 change owner 转移有效期时，应该使用用户的change owner后的新账户
                this.initUserValidity(res, context.get3hTree().loadUser3hBean(res.getResourceId()).getAcctId(), expireDate, sLifeCycleMgnt);
            }
        }
        else if (days > 0)
        {
            expireDate = calculateExpireDateByExtendDays(expireDate, (int) days).getTime();
            SUserStatus sLifeCycleMgnt = new SUserStatus();
            // @Date 2012-08-16 tangjl5 :Bug # 52819 change owner 转移有效期时，应该使用用户的change owner后的新账户
            this.initUserValidity(res, context.get3hTree().loadUser3hBean(res.getResourceId()).getAcctId(), expireDate, sLifeCycleMgnt);
        }

        return expireDate;
    }

    private Date single2single(Long newAcctId, Long oldAcctId, long days, long sbMaxChgDays, boolean isTransferFlag, CmResValidity oldAcctValidity)
    {
        CmResValidity curAcctValidity = queryAcctValidity(newAcctId);
        Date newExpireDate = null;
        long totalValidDays = 0;
        Long curAcctValidDays = null;
        Long newAcctValidDays = null;
        if (null != curAcctValidity)
        {
            curAcctValidDays = DateUtil.getBetweenDays(context.getRequestDate(), curAcctValidity.getExpireDate());
        }

        if (null != curAcctValidity)
        {
            curAcctValidDays = DateUtil.getBetweenDays(context.getRequestDate(), curAcctValidity.getExpireDate());

            // 若sinle balance的有效期天数大于等于账户可延长的最大有效期期天数时，直接返回，不进行延长操作。
            if (curAcctValidDays >= sbMaxChgDays)
            {
                return curAcctValidity.getExpireDate();
            }
            else
            {
                if (oldAcctValidity != null && isTransferFlag)
                {
                    // single balance转入single balance规则：取目标账户的有效期 + 原账户的有效期 + days
                    newAcctValidDays = DateUtil.getBetweenDays(context.getRequestDate(), oldAcctValidity.getExpireDate());

                    // 若账户已有的有效期天数+欲延长的用户原有的有效期天数之和大于账户可延长的有效天数，则只延长账户可延长的最大有效天数
                    if (curAcctValidDays + newAcctValidDays >= sbMaxChgDays)
                    {
                        totalValidDays = sbMaxChgDays - curAcctValidDays;
                    }
                    else
                    {
                        if (days > 0)
                        {
                            if (curAcctValidDays + newAcctValidDays + days >= sbMaxChgDays)
                            {
                                totalValidDays = sbMaxChgDays - curAcctValidDays;
                            }
                        }
                    }
                }
                else
                {
                    if (days > 0)
                    {
                        if (curAcctValidDays + days >= sbMaxChgDays)
                        {
                            totalValidDays = sbMaxChgDays - curAcctValidDays;
                        }
                    }
                }
            }

            newExpireDate = calculateExpireDateByExtendDays(curAcctValidity.getExpireDate(), (int) totalValidDays).getTime();
        }
        else
        {
            if (null != oldAcctValidity && isTransferFlag)
            {
                newAcctValidDays = DateUtil.getBetweenDays(context.getRequestDate(), oldAcctValidity.getExpireDate());
                if (newAcctValidDays >= sbMaxChgDays)
                {
                    totalValidDays = sbMaxChgDays;
                }
                else
                {
                    if (days > 0)
                    {
                        if (newAcctValidDays + days >= sbMaxChgDays)
                        {
                            totalValidDays = sbMaxChgDays - newAcctValidDays;
                        }
                    }
                    else
                    {
                        totalValidDays = newAcctValidDays;
                    }
                }
            }
            else
            {
                if (days > 0)
                {
                    if (days >= sbMaxChgDays)
                    {
                        totalValidDays = sbMaxChgDays;
                    }
                }
            }

            newExpireDate = calculateExpireDateByExtendDays(context.getRequestDate(), (int) totalValidDays).getTime();
        }

        // 删除账户原有的有效期
        if (isTransferFlag)
        {
            deleteAcctValidity(oldAcctId);
        }

        if (newExpireDate != null)
        {
            if (queryAcctValidity(newAcctId) == null)
            {
                this.initAcctValidity4TopUp(newAcctId, newExpireDate);
            }
            else
            {
                this.updateAcctValidity4TopUp(curAcctValidity, newExpireDate);
            }
        }

        return newExpireDate;
    }

    /**
     * @Description: change owner 时判断有效期是否失效，若已失效则置生命周期状态为suspend-no validity
     * @param resId
     * @author: tangjl5
     * @Date: 2011-11-25
     * @Date: 2011-2-15 若是single balance则取账户级有效期，若是用户级账本则取用户级有效期
     */
    public void updateLifeCycleByChangeOwner(CmResource res, CaAccount acct, CmCustomer cust)
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(res.getResourceId());
        CmResLifecycle oldLifeCycle = user3hBean.getUserLifeCycle();
        CmResValidity validity = null;
        if (acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE)
        {
            validity = queryAcctValidity(acct.getAcctId());
        }
        else
        {
            validity = queryUserValidity(res.getResourceId());
        }

        if (validity == null || validity.getExpireDate().before(context.getRequestDate()))
        {
            // @Date 2012-4-12 tangjl5 Task #44143 ChangeOwner，changePaymentMode时，若用户没有有效期则，则根据active状态的有效天数来初始化用户的有效期
            SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

            SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(
                    (short) EnumCodeDefine.LIFECYCLE_ACTIVE, groupCyclePatten.getPatternId());

            if (null == sysCycPatternDetail)
            {
                // 用户在sys_cycle_pattern_detail中未找到对应的模式
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST,
                        EnumCodeDefine.LIFECYCLE_SUSPEND_2WAY, groupCyclePatten.getPatternId());
            }

            // @Date 2012-4-16 tangjl5 账户ID应该为新的支付账户ID
            Date expireDate = this.calculateExpireDateByExtendDays(context.getRequestDate(), sysCycPatternDetail.getValidDays())
                    .getTime();
            if (acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
            {
                initResValidity4TopUp(res.getResourceId(), acct.getAcctId(), expireDate);
            }

            CmResLifecycle newLifeCycle = new CmResLifecycle();
            newLifeCycle.setSts(EnumCodeDefine.LIFECYCLE_ACTIVE);
            newLifeCycle.setSoDate(context.getRequestDate());
            newLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
            newLifeCycle.setOsSts(EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE);
            newLifeCycle.setExpireDate(expireDate);
            newLifeCycle.setResourceId(res.getResourceId());
            modifyUserLifeCycle(null, newLifeCycle, oldLifeCycle);

        }else if(oldLifeCycle.getExpireDate().getTime() != validity.getExpireDate().getTime()){
        	//2012-11-07 zengxr #64264 if validity change, expire date of active also need change
        	CmResLifecycle newLifeCycle = new CmResLifecycle();
            newLifeCycle.setExpireDate(validity.getExpireDate());
            newLifeCycle.setResourceId(res.getResourceId());
            modifyUserLifeCycle(null, newLifeCycle, oldLifeCycle);
        }
    }

    /**
     * @Description: 账户没有用户的情况下重复充值的情况
     * @param acctId
     * @param expireDate
     * @author: tangjl5
     * @Date: 2011-11-25
     * @Date: 2012-3-1 tangjl5设值有效期变更前天数和变更后天数
     */
    public void updateAcctValidity4TopUp(CmResValidity curValidity, Date expireDate)
    {
        // 更改历史数据的状态
        updateAcctValidityHisSts4Nor(curValidity.getAccountId());

        CmResValidity resValidity = new CmResValidity();
        resValidity.setExpireDate(expireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        resValidity.setBeforeChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), curValidity.getExpireDate()));
        resValidity.setAfterChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), expireDate));
        resValidity.setUserExpireDate(resValidity.getExpireDate());
        
        // @Date 2012-3-22 tangjl Bug #42070 过期用户，调整有效期的时候，账本有效期的生效时间写的不对，不应该使用当前时间
        if (curValidity.getExpireDate().before(context.getRequestDate()))
        {
            updateByCondition(resValidity, curValidity.getExpireDate(), curValidity.getExpireDate(), new DBCondition(
                    CmResValidity.Field.accountId, curValidity.getAccountId()),
                    new DBCondition(CmResValidity.Field.resourceId, 0),
                    new DBCondition(CmResValidity.Field.soNbr, curValidity.getSoNbr()), new DBOrCondition(new DBCondition(
                            CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT), new DBCondition(
                            CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS)));
        }
        else
        {
            updateByCondition(resValidity, new DBCondition(CmResValidity.Field.accountId, curValidity.getAccountId()),
                    new DBCondition(CmResValidity.Field.resourceId, 0),
                    new DBCondition(CmResValidity.Field.soNbr, curValidity.getSoNbr()), new DBOrCondition(new DBCondition(
                            CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT), new DBCondition(
                            CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS)));
        }
    }

    /**
     * @Description: 更新用户级账本
     * @param curValidity
     * @param expireDate
     * @param changeDays
     * @param isValid 上海用，0 有效  1无效 可以为空 lijc3 ADD
     * @author: tangjl5
     * @Date: 2012-2-2
     * @Date: 2012-3-1 tangjl5设值有效期变更前天数和变更后天数
     */
    public void updateResValidity4TopUp(CmResValidity curValidity, Date expireDate,Integer isValid)
    {
        // 更改历史数据的状态
        updateResValidityHisSts4Nor(curValidity.getResourceId());

        CmResValidity resValidity = new CmResValidity();
        if(expireDate!=null){
            resValidity.setExpireDate(expireDate);
        }
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        //lijc3 add
        if(ProjectUtil.is_CN_SH()){
            if(expireDate!=null){
                resValidity.setUserExpireDate(expireDate);
            }
            if(isValid!=null){
                resValidity.setEffectFlag(isValid);
            }
        }
        if(expireDate!=null){
        	resValidity.setBeforeChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), curValidity.getExpireDate()));
        	resValidity.setAfterChangeDays(DateUtil.getBetweenDays(context.getRequestDate(), expireDate));
        	// @Date 2012-3-22 tangjl Bug #42070 过期用户，调整有效期的时候，账本有效期的生效时间写的不对，不应该使用当前时间
        	if (curValidity.getExpireDate().before(context.getRequestDate()))
        	{
        		updateByCondition(resValidity, curValidity.getExpireDate(), curValidity.getExpireDate(), new DBCondition(
        				CmResValidity.Field.resourceId, curValidity.getResourceId()), new DBCondition(CmResValidity.Field.soNbr,
        						curValidity.getSoNbr()),
        						new DBOrCondition(new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT),
        								new DBCondition(CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS)));
        	}
        	else
        	{
        		// @Date 2012-5-29 tangjl5 #46715
        		resValidity.setValidDate(context.getRequestDate());
        		updateByCondition(resValidity, new DBCondition(CmResValidity.Field.resourceId, curValidity.getResourceId()),
        				new DBCondition(CmResValidity.Field.soNbr, curValidity.getSoNbr()), new DBOrCondition(new DBCondition(
        						CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT), new DBCondition(
        								CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS)));
        	}
        }else{
        	// @Date 2012-8-22 caohm5
    		resValidity.setValidDate(context.getRequestDate());
    		updateByCondition(resValidity, new DBCondition(CmResValidity.Field.resourceId, curValidity.getResourceId()),
    				new DBCondition(CmResValidity.Field.soNbr, curValidity.getSoNbr()), new DBOrCondition(new DBCondition(
    						CmResValidity.Field.expireDate, context.getRequestDate(), Operator.GREAT), new DBCondition(
    								CmResValidity.Field.expireDate, context.getRequestDate(), Operator.LESS_EQUALS)));
        }
    }

    public void updateValidityHaveUser4TopUp(Long acctId, Date expireDate)
    {
        // 更改历史数据的状态
        updateResValidityHisSts4Nor(acctId);

        CmResValidity resValidity = new CmResValidity();
        resValidity.setExpireDate(expireDate);
        resValidity.setRecSts(EnumCodeDefine.IS_VALID_DATA);

        updateByCondition(resValidity, new DBCondition(CmResValidity.Field.accountId, acctId));
    }

    /**
     * @Description: change payment时对用户生命周期，生效时间的处理
     * @param res
     * @param acctId
     * @param newBillingType
     * @param oldBillingType
     * @author: tangjl5
     * @Date: 2011-11-26
     */
    public void updateLifeCycleByChgPayMent(CmResource res, CaAccount acct, CmCustomer cust, Integer newBillingType,
            Integer oldBillingType)
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(res.getResourceId());
        CmResLifecycle oldLifeCycle = user3hBean.getUserLifeCycle();
        List<CmResource> resList = context.getComponent(UserComponent.class).queryUsersByAcctID(acct.getAcctId());
        boolean isHavaPrepaid = false;
        for (CmResource cmRes : resList)
        {
            if (cmRes.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                isHavaPrepaid = true;
                break;
            }
        }
        // 后付费或混合模式转为预付费时，若账户下没有有效期，则初始化账户有效期，失效时间同active状态的失效时间
        if (newBillingType == EnumCodeDefine.USER_PAYMODE_PREPAID && !isHavaPrepaid)
        {
            updateValidityByChgPayMent(res.getResourceId(), acct.getAcctId());

        }
        // 预付费转后付费，将cm_res_validity的数据置为失效，将cm_res_lifecycle表中的expire_date设置为cm_resource表中的expire_date一致。
        else
        {
            // cm_res_validity的数据置为失效
            if (!isHavaPrepaid && acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
            {
                deleteAcctValidity(acct.getAcctId());
            }

            if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID
                    && acct.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_USER)
            {
                deleteResValidity(acct.getAcctId());
            }

            // cm_res_lifecycle表中的expire_date设置为cm_resource表中的expire_date一致
            CmResLifecycle newLifeCycle = new CmResLifecycle();
            newLifeCycle.setSoDate(context.getRequestDate());
            newLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
            newLifeCycle.setExpireDate(res.getExpireDate());
            newLifeCycle.setResourceId(res.getResourceId());
            newLifeCycle.setSts(oldLifeCycle.getSts());
            newLifeCycle.setOsSts(oldLifeCycle.getOsSts());
            modifyUserLifeCycle(null, newLifeCycle, oldLifeCycle);
        }
    }

    /**
     * @Description: 若账户下没有有效期，则实例化有效期，有效期的长度与active的有效期长度一致
     * @param resId
     * @param acctId
     * @author: tangjl5
     * @Date: 2012-1-11
     */
    public void updateValidityByChgPayMent(Long resId, Long acctId)
    {
        if (queryUserValidity(acctId) == null || queryUserValidity(acctId).getExpireDate().before(context.getRequestDate()))
        {
            CmResLifecycle curLifeCycle = context.get3hTree().loadUser3hBean(resId).getUserLifeCycle();
            initValidity4PayMent(acctId, curLifeCycle.getExpireDate(), curLifeCycle.getValidDate());
        }
    }

    /**
     * @Description: 根据用户状态，用户Id更新用户生命周期
     * @param resourceId
     * @param sts
     * @author: tangjl5
     * @Date: 2011-12-1
     */
    public void updateLifeCycleBysts(Long resourceId, CmResLifecycle curLifeCycle, Integer sts)
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(resourceId);
        CmResource res = user3hBean.getUser();
        CaAccount acct = user3hBean.getAccount();
        CmCustomer cust = user3hBean.getCustomer();

        SysGroupCyclePattern groupCyclePatten = queryCaGroupCyclePattern(res, cust, acct);

        SysCyclePatternDetail sysCycPatternDetail = this.querySysCyclePatternDetailByStsID(sts.shortValue(),
                groupCyclePatten.getPatternId());

        if (null == sysCycPatternDetail)
        {
            // 用户在sys_cycle_pattern_detail中未找到对应的模式
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_CYCLE_PATTERN_DETAIL_NOTEXIST, sts,
                    groupCyclePatten.getPatternId());
        }

        CmResLifecycle newLifeCycle = new CmResLifecycle();
        newLifeCycle.setSts(sts);
        newLifeCycle.setSoDate(context.getRequestDate());
        newLifeCycle.setRecSts(EnumCodeDefine.IS_VALID_DATA);
        newLifeCycle.setExpireDate(calculateExpireDateByExtendDays(context.getRequestDate(), sysCycPatternDetail.getValidDays())
                .getTime());
        newLifeCycle.setResourceId(resourceId);
        newLifeCycle.setOsSts(curLifeCycle.getOsSts());
        CmResLifecycle updateLifeCycle = modifyUserLifeCycle(null, newLifeCycle, curLifeCycle);

        // 将CM_RES_LIFECYCLE插入CA_CYCLE_RESOURCE_REG中
        insertResLifeCycReg4UnDeal(curLifeCycle, updateLifeCycle, null, null);
    }

    /**
     * 判断接口是否收取一次性费用
     * 
     * @author tangjl5
     * @Date 2012-4-7
     */
    public boolean isOneTimeFee()
    {
        BaseNode methodNode = context.getMethodConfig();
        Boolean serviceOTC = methodNode.getParent().getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);
        Boolean methodOTC = methodNode.getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);

        if (!serviceOTC || !methodOTC)
        {
            imsLogger.info("no need to charge onetime fee", context);
            return false;
        }

        Short chargeFlag = context.getOper().getCharge_flag();

        imsLogger.info("***************** chargeFlag= " + chargeFlag + "  *****************");
        if (chargeFlag != null && chargeFlag.intValue() == 1)
        {
            imsLogger.info("no need to charge onetime fee", context);
            return false;// 如果charge_flag填了1表示不收取费用，直接返回
        }

        imsLogger.info("need to charge onetime fee", context);
        return true;
    }

    /**
     * @Date 2012-05-15 wangdw5 非内部接口 验证main3hbean业务状态
     */
    public void checkMain3hBeanBusiState(List<IMS3hBean> main3hBeans, Integer busi_code)
    {
        int level = EnumCodeDefine.IMSBUSISTSCONTROL_LEVEL_USER;
        int sts = CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION);
        int index = 0;
        // 2012-5-24 yangjh 避免空指针
        if (CommonUtil.isEmpty(main3hBeans))
            return;
        for (IMS3hBean ims3hBean : main3hBeans)
        {
            index = ims3hBean.getIndex() == null ? 0 : ims3hBean.getIndex();
            if (ims3hBean.isUser3hBean())
            {
                User3hBean user3hBean = context.get3hTree().loadUser3hBean(ims3hBean.getUserId());
                CmResLifecycle cmResLifecycle = user3hBean.getUserLifeCycle();
                int frauldFlag = cmResLifecycle.getFrauldFlag();
                int userreqFlag = cmResLifecycle.getUserrequestFlag();
                int osSts = cmResLifecycle.getOsSts();
                sts = cmResLifecycle.getSts();
                int billType = user3hBean.getUser().getBillingType();
                if (billType == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID
                        || billType == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID)
                {
                    billType = EnumCodeDefine.USER_PAYMODE_HYBRID;
                }
                List<ImsBusiStsControl> controls = DBUtil.query(ImsBusiStsControl.class, new DBCondition(ImsBusiStsControl.Field.busiCode, busi_code), 
                        new DBOrCondition(new DBCondition(ImsBusiStsControl.Field.billingType,billType),
                        		new DBCondition(ImsBusiStsControl.Field.billingType,ConstantDefine.INT_UNIFIED_CONFIGURATION)), 
                        new DBCondition(ImsBusiStsControl.Field.objLevel, level), 
                        new DBCondition(ImsBusiStsControl.Field.objIndex, index), 
                        new DBOrCondition(new DBCondition(ImsBusiStsControl.Field.sts,sts), 
                        		new DBCondition(ImsBusiStsControl.Field.sts, ConstantDefine.INT_UNIFIED_CONFIGURATION)),
                        new DBOrCondition(new DBCondition(ImsBusiStsControl.Field.frauldFlag, frauldFlag), new DBCondition(
                                ImsBusiStsControl.Field.frauldFlag, ConstantDefine.INT_UNIFIED_CONFIGURATION)),
                        new DBOrCondition(new DBCondition(ImsBusiStsControl.Field.userrequestFlag, userreqFlag), new DBCondition(
                                ImsBusiStsControl.Field.userrequestFlag, ConstantDefine.INT_UNIFIED_CONFIGURATION)),
                        new DBOrCondition(new DBCondition(ImsBusiStsControl.Field.osSts, osSts), new DBCondition(
                                ImsBusiStsControl.Field.osSts, ConstantDefine.INT_UNIFIED_CONFIGURATION)));
                if (CommonUtil.isEmpty(controls))
                {
                    if (billType == EnumCodeDefine.USER_PAYMODE_POSTPAID)
                    {
                        if (sts != EnumCodeDefine.LIFECYCLE_ACTIVE || osSts != EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE)
                        {
                            IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_BUSISTS, ims3hBean.getUserId(), sts, busi_code);
                        }
                    }
                    else
                    {
                        if (sts != EnumCodeDefine.LIFECYCLE_ACTIVE || osSts != EnumCodeDefine.LIFECYCLE_OSSTS_ACTIVE
                                || frauldFlag != EnumCodeDefine.LIFECYCLE_UNFRAULD_FLAG
                                || userreqFlag != EnumCodeDefine.LIFECYCLE_USERREQUEST_NO_FLAG)
                        {
                            IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_BUSISTS, ims3hBean.getUserId(), sts, busi_code);
                        }
                    }
                }
            }
            else if (ims3hBean.isAcct3hBean())
            {
                level = EnumCodeDefine.IMSBUSISTSCONTROL_LEVEL_ACCOUNT;
                Acct3hBean acct3hBean = context.get3hTree().loadAcct3hBean(ims3hBean.getAcctId());
                sts = acct3hBean.getAccount().getAccountStatus();
                List<ImsBusiStsControl> controls = DBUtil.query(ImsBusiStsControl.class, new DBCondition(
                        ImsBusiStsControl.Field.busiCode, busi_code), new DBCondition(ImsBusiStsControl.Field.objLevel, level),
                        new DBCondition(ImsBusiStsControl.Field.objIndex, index), new DBOrCondition(new DBCondition(
                                ImsBusiStsControl.Field.sts, sts), new DBCondition(ImsBusiStsControl.Field.sts,
                                ConstantDefine.INT_UNIFIED_CONFIGURATION)));
                if (CommonUtil.isEmpty(controls))
                {
                    if (sts != EnumCodeDefine.ACCOUNT_ACTIVE)
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCOUNT_BUSISTS, ims3hBean.getAcctId(), sts, busi_code);
                    }
                }
            }
            else if (ims3hBean.isCust3hBean())
            {
                level = EnumCodeDefine.IMSBUSISTSCONTROL_LEVEL_CUSTOMER;
                Cust3hBean cust3hBean = context.get3hTree().loadCust3hBean(ims3hBean.getCustId());
                sts = cust3hBean.getCustomer().getSts();
                List<ImsBusiStsControl> controls = DBUtil.query(ImsBusiStsControl.class, new DBCondition(
                        ImsBusiStsControl.Field.busiCode, busi_code), new DBCondition(ImsBusiStsControl.Field.objLevel, level),
                        new DBCondition(ImsBusiStsControl.Field.objIndex, index), new DBOrCondition(new DBCondition(
                                ImsBusiStsControl.Field.sts, sts), new DBCondition(ImsBusiStsControl.Field.sts,
                                ConstantDefine.INT_UNIFIED_CONFIGURATION)));
                if (CommonUtil.isEmpty(controls))
                {
                    if (sts != EnumCodeDefine.CUSTOMER_STS_ACTIVE)
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_CUSTOMER_BUSISTS, ims3hBean.getCustId(), sts, busi_code);
                    }
                }
            }
        }
    }
    
    /**
     * @Description: 预付费用户在暂停激活后，用户订购的所有预扣产品，都调用一次通知扣费接口，通知帐处扣费
     *   
     * @author: tangjl5
     * @Date: 2012-6-20
     */
    public void lifeCycleProdPreOrder()
    {
        // 预付费用户在暂停激活后，用户订购的所有预扣产品，都调用一次通知扣费接口，通知帐处扣费
        List<DataObject> userCycles = context.getAllCacheList(CmResLifecycle.class);
        if (CommonUtil.isNotEmpty(userCycles) && userCycles.size() == 2)
        {
            CmResLifecycle lifeCycleA = (CmResLifecycle) userCycles.get(0);
            CmResLifecycle lifeCycleB = (CmResLifecycle) userCycles.get(1);
            CmResLifecycle oldLifeCycle = null;
            CmResLifecycle newLifeCycle = null;
            if (lifeCycleA.getExpireDate().after(lifeCycleB.getExpireDate()))
            {
                oldLifeCycle = lifeCycleB;
                newLifeCycle = lifeCycleA;
            }
            else
            {
                oldLifeCycle = lifeCycleA;
                newLifeCycle = lifeCycleB;
            }
            
            List<CoProd> prods = context.getAllCacheList(CoProd.class);
            // @Date 2012-6-5 tangjl5 查找在操作中删除了的用户主用停机产品在通知计费扣费时使用
            CoProd userReqProd = null;
            if (CommonUtil.isNotEmpty(prods))
            {
                for (CoProd prod : prods)
                {
                    if (prod.getBusiFlag() == SpecCodeDefine.USER_SUSPEND_REQUEST && prod.getValidDate() != context.getRequestDate())
                    {
                        userReqProd = prod;
                    }
                }
            }

            User3hBean user3hBean = context.get3hTree().loadUser3hBean(newLifeCycle.getResourceId());
            callPreOrder(oldLifeCycle, newLifeCycle, user3hBean.getUser(), userReqProd);
        }
    }

    /**
     * @Date 2012-05-25 zhangzj3 获取用户的状态串,总的位数8位
     */
    public String getUserFullSts(CmResLifecycle lifeCycle)
    {
        StringBuffer nextSts = new StringBuffer();
        nextSts.append(lifeCycle.getOsSts());
        nextSts.append(lifeCycle.getReratingFlag());
        nextSts.append(lifeCycle.getUnbillingFlag());
        nextSts.append(lifeCycle.getUserrequestFlag());
        nextSts.append(lifeCycle.getFrauldFlag());
        nextSts.append(lifeCycle.getSts().toString().substring(2));
        return nextSts.toString();
    }
    
    /**
     * 根据soNbr查询有效期变更记录
     * luojb 2012-7-12
     * @param soNbrs
     * @return
     */
    public List<CmResValidity> queryValidityLog(Long[] soNbrs){
        if(CommonUtil.isEmpty(soNbrs))
            return null;
        
        List<CmResValidity> validity = null;
        
        if(soNbrs.length == 1){
            validity = query(CmResValidity.class, new DBCondition(CmResValidity.Field.soNbr,soNbrs[0]));
        }
        else{
            validity = query(CmResValidity.class, new DBCondition(CmResValidity.Field.soNbr,soNbrs,Operator.IN));
        }
        return validity;
    }
}
