package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.common.wrapper.Holder;
import jef.database.Condition.Operator;
import jef.database.QueryArg.MyTableName;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.CheckComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.billcycle.BillCycleSpecBusiness;
import com.ailk.ims.component.billcycle.QueryBillCycleDate;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.acct.entity.CaPaymentPlan;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpec;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecIn;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecOut;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanIn;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanOut;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmNotificationFlag;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCredit;

/**
 * 账户相关的信息查询的方法类
 * 
 * @author wangjt
 * @Date 2011-12-21 ljc 查询账户的支付账户 2012-02-07
 * @Date 2012-06-19 yangjh bug:47881,47882 yinzh3让修改为query_BillCycleDate方法
 */
public class AccountQuery extends ConfigQuery
{
    public AccountQuery()
    {
    }

    /**
     * 
     * luojb 2012-11-12
     * @param userId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public Long queryBelongAcctIdByUserId(Long userId)
    {
        CaAccountRes acctRes = this.queryAccountResByUserID(userId);
        return acctRes == null ? null : acctRes.getAcctId();
    }
    
    /**
     * 
     * luojb 2012-11-12
     * @param userId
     * @return
     * @throws IMSException
     * @deprecated 仅仅提供给上海使用
     */
    public CaAccountRes queryAccountResByUserID(Long userId) throws IMSException
    {
        CaAccountRes acctRes = querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        return acctRes;
    }
    
    /**
     * 
     * luojb 2012-11-12
     * @param acctId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public CaAccount queryAccountById(Long acctId)
    {
        return querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
    }
    
    /**
     * 判断账户下，是否还有对应付费模式的用户
     * 
     * @author wangjt 2011-12-23
     * @param acctId
     * @param paymode
     * @return:存在返回true，没有对应付费模式的用户，返回false
     */
    public boolean isAccountHasUser(Long acctId, int... paymode)
    {
        /*
         * List<DBCondition> mainConds = new ArrayList<DBCondition>(); mainConds.add(new DBCondition(CaAccountRes.Field.acctId,
         * acctId)); DBExistsCond existsCond = new DBExistsCond(CmResource.Field.resourceId, CaAccountRes.Field.resourceId);
         * existsCond.addCondition(new DBCondition(CmResource.Field.billingType, paymode, Operator.IN));
         */
        List<CmResource> resources = query(CmResource.class, new DBCondition(CmResource.Field.billingType, paymode, Operator.IN));
        if (CommonUtil.isEmpty(resources))
        {
            return false;
        }
        List<CaAccountRes> userList = new ArrayList<CaAccountRes>();
        for (CmResource resource : resources)
        {
            CaAccountRes res = querySingle(CaAccountRes.class,
                    new DBCondition(CaAccountRes.Field.resourceId, resource.getResourceId()), new DBCondition(
                            CaAccountRes.Field.acctId, acctId));
            userList.add(res);
        }
        return CommonUtil.isNotEmpty(userList);

    }

    /**
     * @Description: 判断账户下是否有用户不包含当前用户
     * @param acctId
     * @param userId
     * @return
     * @author: tangjl5
     * @Date: 2012-2-15
     */
    public boolean isAcctHasUserExpCurrentOrNoUser(Long acctId, Long userId)
    {
        List<Long> userIds = context.getComponent(UserQuery.class).queryUserIdsByAcctID(acctId);
        if (CommonUtil.isEmpty(userIds) || (userIds.size() == 1 && userIds.get(0) == userId.longValue()))
        {
            return true;
        }
        return false;
    }

    /**
     * 查询specid
     */
    public Integer queryBillCycleSpecId(Integer cycle_type, Integer cycle_unit, Integer first_bill_day)
    {
        SBillCycle billCycle = new SBillCycle();
        billCycle.setCycle_type(cycle_type);
        billCycle.setCycle_unit(cycle_unit);
        billCycle.setFirst_bill_day(first_bill_day);
        
        Integer cycleSpecId = CommonUtil.long2Int(context.getComponent(BillingPlanQueryComponent.class).queryCaBillCycleSpec(billCycle).getCycleSpecId());
        
        return cycleSpecId;
    }

    /**
     * 查询支付计划信息
     * 
     * @Author liuyang
     * @Date 2011-7-27 2011-07-27
     */
    public CaPaymentPlan queryPayPlanInfo(Long acctId)
    {
        // CaPaymentPlan dmPayPlan = new CaPaymentPlan();
        // dmPayPlan.setAcctId(acctId);
        return querySingle(CaPaymentPlan.class, new DBCondition(CaPaymentPlan.Field.acctId, acctId));
    }

    public CaPayChannel queryPayChannel(Long planId, int accountPaytypeCard)
    {
        // CaPaymentChannel payChannelCon = new CaPaymentChannel();
        // payChannelCon.setPaymentPlanId(planId);
        // payChannelCon.setPaymentMethod(accountPaytypeCard);
        return super.querySingle(CaPayChannel.class, new DBCondition(CaPayChannel.Field.paymentPlanId, planId), new DBCondition(
                CaPayChannel.Field.paymentMethod, accountPaytypeCard));
    }

    /**
     * @author: tangjl5
     * @Date: 2011-10-21
     */
    public List<CaPayChannel> queryPayChannelByAcctId(Long acctId)
    {
        // DBJoinCondition joinCondition = new DBJoinCondition(CaPaymentPlan.class);
        // joinCondition.innerJoin(CaPaymentChannel.class, new DBRelation(CaPaymentPlan.Field.paymentPlanId,
        // CaPaymentChannel.Field.paymentPlanId));
        //
        // List<Map> result = queryJoin(joinCondition, new DBCondition(CaPaymentPlan.Field.acctId, acctId));
        // if (CommonUtil.isEmpty(result))
        // {
        // return null;
        // }
        List<CaPaymentPlan> payPlanList = query(CaPaymentPlan.class, new DBCondition(CaPaymentPlan.Field.acctId, acctId));
        if (CommonUtil.isEmpty(payPlanList))
        {
            return null;
        }
        List<CaPayChannel> payChannelList = new ArrayList<CaPayChannel>();
        for (CaPaymentPlan plan : payPlanList)
        {
            List<CaPayChannel> payChannelOneList = query(CaPayChannel.class, new DBCondition(CaPayChannel.Field.paymentPlanId,
                    plan.getPaymentPlanId()));
            if (CommonUtil.isEmpty(payChannelOneList))
            {
                continue;
            }
            payChannelList.addAll(payChannelOneList);
        }
        return payChannelList;
    }
    
    /**
     * 上海查询账户的支付渠道信息
     * @param acctId
     * @return
     */
    public CaPayChannel queryPayChannelByAcctId4Sh(Long acctId)
    {
        return querySingle(CaPayChannel.class, new DBCondition(CaPayChannel.Field.acctId, acctId));
    }

    // public Map<Class,DataObject> queryAccountAndCustRelNoSts(Long acctId){
    // return queryAccountAndCustRel(acctId,null);
    // }

    /**
     * 查询账户及和归属客户关系 luojb 2011-12-31
     * 
     * @param acctId
     * @return
     */
    // public Map<Class,DataObject> queryAccountAndCustRel(Long acctId,Integer sts){
    // List<DBCondition> condition = new ArrayList<DBCondition>();
    // condition.add(new DBCondition(CaAccount.Field.acctId, acctId));
    // if(sts != null){
    // condition.add(new DBCondition(CaAccount.Field.accountStatus,EnumCodeDefine.LIFECYCLE_ACTIVE));
    // }
    // DBCondition[] conditions = condition.toArray(new DBCondition[condition.size()]);
    //
    // DBJoinCondition joinCondition = new DBJoinCondition(CaAccount.class);
    // joinCondition.innerJoin(CaCustomerRel.class, new DBRelation(CaAccount.Field.acctId,
    // CaCustomerRel.Field.acctId),
    // new DBRelation(CaCustomerRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
    // return queryJoinSingle(joinCondition, conditions);
    // }

    /**
     * 查询账户的接收通知电话 luojb 2011-9-28
     */
    public String queryAcctContactPhone(Long acctId) throws IMSException
    {
        List<CmContactMedium> mediums = context.getComponent(ContactComponent.class).queryAcctContactMediums(acctId, null);
        if (CommonUtil.isEmpty(mediums))
        {
            return null;
        }
        return mediums.get(0).getMobile();
    }

    // /**
    // * 该接口在查询信s用度时使用，用户可以为terminal状态
    // * @Date 2012-4-23 tangjl5
    // */
    // public CaAccountRes queryAcctResByUserIDForCredit(Long userId) throws IMSException
    // {
    // CaAccountRes acctRes = null;
    // CmResLifecycle lifeCycle = null;
    // if(ProjectUtil.is_CN_SH())
    // {
    // try
    // {
    // lifeCycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
    // }
    // catch(IMS3hNotFoundException e)
    // {
    // IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, userId);
    // }
    // }
    // else
    // {
    // try
    // {
    // lifeCycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
    // if(lifeCycle.getRecSts()!=EnumCodeDefine.IS_VALID_DATA)
    // {
    // // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
    // IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL,userId);
    // }
    // else if (lifeCycle.getExpireDate().before(context.getRequestDate()))
    // {
    // lifeCycle.setOsSts(CommonUtil.string2Integer(String.valueOf(lifeCycle.getNextSts()).substring(0, 2)));
    //
    // // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
    // lifeCycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(lifeCycle.getNextSts()).substring(6, 8)));
    // }
    // }
    // catch(IMS3hNotFoundException e)
    // {
    // IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL,userId);
    // }
    // }
    //
    // if (lifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_TERMINAL)
    // {
    // // 根据lifecycle的sonbr查找置为失效的账户用户关系
    // // CaAccountRes queryBean = new CaAccountRes ();
    // // queryBean.setSoNbr(lifeCycle.getSoNbr());
    // // queryBean.setResourceId(userId);
    // // acctRes = (CaAccountRes)DBUtil.querySingle(queryBean);
    // acctRes = DBUtil.querySingle(CaAccountRes.class,new DBCondition(CaAccountRes.Field.soNbr,lifeCycle.getSoNbr()),
    // new DBCondition(CaAccountRes.Field.resourceId,userId));
    // }
    // else
    // {
    // acctRes = querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
    // new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    // }
    //
    // return acctRes;
    // }

    // public CaAccountRes queryBelongAcctResByUserId(Long userId) throws IMSException
    // {
    // List<CaAccountRes> result = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
    // new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    // if (CommonUtil.isEmpty(result))
    // {
    // return null;
    // }
    // else
    // {
    // return result.get(0);
    // }
    // }

    // /**
    // * 查询用户的支付账户 yanchuan 2011-12-20
    // */
    // public CaAccount queryBillAccountByUserId(Long userId) throws IMSException
    // {
    // CaAccountRes acctRes = this.queryBillAcctResByUserID(userId);
    // return acctRes == null ? null : this.queryAccountById(acctRes.getAcctId());
    //
    // }

    // private CaAccountRes queryAccountResByUserId(Long resourceId, Integer accountRelationResBillable) throws IMSException
    // {
    // CaAccountRes acctRes = querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, resourceId),
    // new DBCondition(CaAccountRes.Field.relationshipType, accountRelationResBillable));
    // return acctRes;
    // }

    // public CaAccountRes queryBillAccountResByUserId(Long resourceId) throws IMSException
    // {
    // return queryAccountResByUserId(resourceId, EnumCodeDefine.ACCOUNT_RELATION_RES_BILLABLE);
    // }
    //
    // public CaAccountRes queryBelongAccountResByUserId(Long resourceId) throws IMSException
    // {
    // return queryAccountResByUserId(resourceId, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
    // }

    /**
     * 根据账户ID与账户、用户的关系类型取得账户用户关系列表 ljc 修改 如果relationship为0 查归属下的用户，其他查被这个账户支付的用户关系
     */
    public List<CaAccountRes> queryAcctResByAcctIDAndType(Long acctId, int relationship) throws IMSException
    {
        // CaAccountRes value = new CaAccountRes();
        // value.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
        if (relationship == EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING)
        {
            // value.setAcctId(acctId);
            return this.query(CaAccountRes.class, new OrderCondition[] { new OrderCondition(CaAccountRes.Field.resourceId) },
                    null, new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING),
                    new DBCondition(CaAccountRes.Field.acctId, acctId));
        }
        else
        {
            // value.setResAcctId(acctId);
            return this.query(CaAccountRes.class, new OrderCondition[] { new OrderCondition(CaAccountRes.Field.resourceId) },
                    null, new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING),
                    new DBCondition(CaAccountRes.Field.acctId, acctId));
        }

    }

    /**
     * 查询账户下所有支付关系的用户 luojb 2012-2-9
     */
    public List<CaAccountRes> queryBillAcctResByAcctId(Long acctId) throws IMSException
    {
        return query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId), new DBCondition(
                CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    }

    // /**
    // * 根据设备ID查询CaAccountRes列表
    // *
    // * @author yangyang
    // * @date 2011-10-31
    // */
    // public List<CaAccountRes> queryCaAccountResByResouceId(long resourceId) throws IMSException
    // {
    // List<CaAccountRes> list = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, resourceId));
    //
    // return list;
    // }

    // /**
    // * 根据userid查询归属账户
    // */
    // public CaAccount queryBelongAccountByUserId(Long userId)
    // {
    // Long acctId =null;
    // CaAccountRes acctRes=querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
    // new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    // if(acctRes!=null)
    // {
    // acctId=acctRes.getAcctId();
    // }
    // CaAccount account = null;
    // if (CommonUtil.isValid(acctId))
    // account=querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
    // return account;
    //
    // }

    // /**
    // * 根据手机号码查询归属账户
    // */
    // public CaAccount queryBelongAccountByPhoneId(String phoneId)
    // {
    // // CmResIdentity resIdentity = context.getComponent(UserComponent.class).queryResIdentityByPhoneId(phoneId);
    // // if (resIdentity == null)
    // // {
    // // return null;
    // // }
    // //2012-08-28 linzt 整理组件方法
    // Long userId=null;
    // try{
    // userId=context.get3hTree().loadUser3hBean(phoneId).getUserId();
    // }
    // catch(IMS3hNotFoundException e)
    // {
    // return null;
    // }
    // CaAccountRes acctRes=querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
    // new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    // Long belongAcctId =null;
    // if(acctRes!=null)
    // {
    // belongAcctId=acctRes.getAcctId();
    // }
    //
    // if (belongAcctId == null)
    // {
    // return null;
    // }
    // return querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, belongAcctId));
    // }

    /**
     * 获取帐户强制账期标识
     * 
     * @author yanchuan 2011-11-24
     */
    public int getFroceCycle(SAccount account)
    {
        int isFroceCycle = 0;
        CmCustExt custExt = context.getComponent(CustomerComponent.class).queryCustExt(account.getCust_id());
        if (custExt != null && custExt.getForceCycle() != null
                && custExt.getForceCycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)
        {
            // 如果客户有强制账期，则使用客户的账期
            isFroceCycle = EnumCodeDefine.FORCECYCLE_BY_CUSTOMER;
            context.addExtendParam(ConstantDefine.CMCUSTEXT_OBJECT, custExt);
        }
        else
        {
            // 查询父帐户的强制账期标识
            CaAccount parentAcct = null;
            if (CommonUtil.isValid(account.getParent_acct_id()))
            {
                // 查询父帐户,父帐户一定是激活状态
                parentAcct = context.getComponent(CheckComponent.class).checkAcctId(account.getParent_acct_id());

            }
            if (parentAcct != null
                    && parentAcct.getForceCycle() != null
                    && (parentAcct.getForceCycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE || parentAcct.getForceCycle() == EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT))
            {
                isFroceCycle = EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT;
            }
            else if (CommonUtil.isValid(account.getForce_cycle()))
            {
                isFroceCycle = account.getForce_cycle();
            }
        }
        return isFroceCycle;
    }

    /**
     * 查询角色联系方式定义表
     */
    public CmContactMedium queryContactMedium(Long contact_id)
    {
        return this.querySingle(CmContactMedium.class, new DBCondition(CmContactMedium.Field.contactMediumId, contact_id));
    }

    /**
     * 根据账号id查找其信用度
     * 
     * @Date 2011-08-31
     * @author fangyw
     */
    public CaCredit queryCreditByAcct(Long acctId)
    {
        CaCredit cacredit = super.querySingle(CaCredit.class, new DBCondition(CaCredit.Field.acctId, acctId));
        return cacredit;
    }

    /**
     * 通过客户编号查询客户下的帐户 编号
     * 
     * @Date 2011-09-03 hunan 2011-09-17 modify： there maybe be many accts under one customer
     */
    public List<Long> queryAcctIdsByCustId(Long custId)
    {

        List<CaAccount> accountList = queryAccountsByCustId(custId);
        List<Long> acctIds = new ArrayList<Long>();
        if (accountList != null && accountList.size() > 0)
        {
            for (CaAccount account : accountList)
            {
                acctIds.add(account.getAcctId());
            }
        }
        return acctIds;
    }

    /**
     * 根据客户id查询客户下的所有帐户实体
     * 
     * @author yanchuan 2011-11-4
     */
    public List<CaAccount> queryAccountsByCustId(Long custId)
    {
        List<CaAccount> accountList = new ArrayList<CaAccount>();
        // DBJoinCondition join = new DBJoinCondition(CaAccount.class);
        // join.innerJoin(CaCustomerRel.class, new DBRelation(CaCustomerRel.Field.acctId, CaAccount.Field.acctId));
        // join.innerJoin(CmCustomer.class, new DBRelation(CmCustomer.Field.custId, CaCustomerRel.Field.custId));
        // List<Map> result = super.queryJoin(join, new DBCondition(CmCustomer.Field.custId, custId), new DBCondition(
        // CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
        // if (!CommonUtil.isEmpty(result))
        // {
        // Iterator it = result.iterator();
        // CaAccount account = null;
        // while (it.hasNext())
        // {
        // Map map = (Map) it.next();
        // account = (CaAccount) map.get(CaAccount.class);
        // accountList.add(account);
        // }
        // }
        List<CaCustomerRel> custRels = queryCaCustRelByCustId(custId);
        if (CommonUtil.isEmpty(custRels))
        {
            return null;
        }
        // AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        for (CaCustomerRel rel : custRels)
        {
            if (rel == null)
                continue;
            // CaAccount acct = acctCmp.queryAccountById(rel.getAcctId());
            // 2012-08-28 linzt 整理组件方法
            CaAccount acct = querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, rel.getAcctId()));
            if (acct == null)
                continue;
            accountList.add(acct);
        }

        return accountList;
    }

    /**
     * hunan 2012-1-9
     * 
     * @param custId
     * @return
     */
    public List<CaCustomerRel> queryCaCustRelByCustId(Long custId)
    {
        return query(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.custId, custId));
    }

    /**
     * 查询免催免停信息 us
     * 
     * @author liuyang8
     * @return Date：2011-09-17
     */
    public List<CaNotifyExempt> queryCaNotiExempt(Long objectId, Integer objectType, Long soNbrIms)
    {
        List<DBCondition> cond = new ArrayList<DBCondition>();

        cond.add(new DBCondition(CaNotifyExempt.Field.objectId, objectId));
        cond.add(new DBCondition(CaNotifyExempt.Field.objectType, objectType));
        if (CommonUtil.isValid(soNbrIms))
        {
            cond.add(new DBCondition(CaNotifyExempt.Field.soNbr, soNbrIms));
        }
        return query(CaNotifyExempt.class, cond.toArray(new DBCondition[cond.size()]));
    }

    /**
     * 查询账户账期编号 yanchuan 2011-9-26
     */
    public Long queryAcctCycle(Long acctId)
    {
        /*
        if (!CommonUtil.isValid(acctId))
            return null;
        Acct3hBean acctBean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccount account = acctBean.getAccount();
        // 调用帐管接口，查询该帐户账期编号
        Long cycleSpecId = null;
        QryBillingPlanIn plan = new QryBillingPlanIn();
        plan.setAcctId(acctId);
        plan.setBillingPlanId(null);
        plan.setNum(new Integer(0));
        plan.setOutDate(context.getRequestDate());
        if (account.getBillSts() == null || account.getBillSts() != EnumCodeDefine.BILL_STS_ACTIVE)
            plan.setIsPrepaid(EnumCodeDefine.QUERY_NOT_DEFINE_CYLCE);
        Holder<QryBillingPlanOut> holder = new Holder<QryBillingPlanOut>();
        //TODO
        Integer returnValue =context.getComponent(PlanServiceComponent.class).query_billingPlan(plan, holder);
        if (returnValue != null && returnValue == 0)
        {
            cycleSpecId = holder.get().getCycleSpecId();
        }
        return cycleSpecId;
        */
        return null;
    }

    /**
     * 根据账户id查询账户账期 ljc 2011-9-26
     */
    public List<BillCycleComplex> queryBillCycle(Long acctId)
    {
        if (CommonUtil.isNotEmpty((List<BillCycleComplex>) context.getExtendParam("acct_bill_cycle" + acctId)))
        {
            return (List<BillCycleComplex>) context.getExtendParam("acct_bill_cycle" + acctId);
        }
        Holder<CaCycleSpecOut> out = new Holder<CaCycleSpecOut>();
        CaCycleSpecIn in = new CaCycleSpecIn();
        in.setAcctId(acctId);
        // 其他三个参数设置为0
        in.setCycleType((short) 0);
        in.setCycleUnit(0);
        in.setStartBillDay(0);
        imsLogger.debug("*****begin to do query account bill cycle*****************");
        imsLogger.debug("***********CaCycleSpecIn:acctId=", acctId, ";cycleType=", in.getCycleType(), ";cycleunit=",
                in.getCycleUnit(), ";first_bill_date=", in.getStartBillDay());
        context.getComponent(BillCycleSpecBusiness.class).qryBillCycleSpec(in, out);;
        if (out != null && out.get() != null && !CommonUtil.isEmpty(out.get().getCaCycleSpecList()))
        {
            List<CaCycleSpec> cycles = out.get().getCaCycleSpecList();
            List<BillCycleComplex> list = new ArrayList<BillCycleComplex>();
            for (CaCycleSpec cycle : cycles)
            {
                BillCycleComplex complex = new BillCycleComplex();
                complex.setCycleType(cycle.getCycleType());
                complex.setCycleUnit(cycle.getCycleUnit());
                complex.setExpireDate(cycle.getExpriedate());
                complex.setFirstBillDate(cycle.getFirstBillDate());
                complex.setValidDate(cycle.getValidate());
                list.add(complex);
            }
            context.addExtendParam("acct_bill_cycle" + acctId, list);
            return list;
        }
        return null;
    }

    /**
     * 查出用户已经订阅的通知设置信息
     * 
     * @author fangyw
     * @param objectId
     * @Date 2011-10-19
     */
    public CmNotificationFlag queryNotification(Long objectId, short level, Short notiType)
    {
        CmNotificationFlag notification = super.querySingle(CmNotificationFlag.class, new DBCondition(
                CmNotificationFlag.Field.objectId, objectId), new DBCondition(CmNotificationFlag.Field.notifyType, notiType),
                new DBCondition(CmNotificationFlag.Field.objectType, level));
        return notification;
    }

    /**
     * 上海根据账户查询CaBankFund
     * @param acctId
     * @return
     */
    public CaBankFund queryCaBankFundByAcctId4Sh(Long acctId)
    {
    	CaBankFund caBankFund = super.querySingle(CaBankFund.class, new DBCondition(CaBankFund.Field.acctId, acctId));
    	return caBankFund;
    }
    /**
     * @author: tangjl5
     * @Date: 2011-10-21
     */
    public List<CaBankFund> queryCaBankFundByAcctId(Long acctId)
    {
        // CaBankFund dmBankFund = new CaBankFund();
        // dmBankFund.setAcctId(acctId);
        List<CaBankFund> caBankFundList = super.query(CaBankFund.class, new DBCondition(CaBankFund.Field.acctId, acctId));
        return caBankFundList;
    }

    /**
     * 根据匹配的组的id查询帐户信用度初始化表对象
     * 
     * @author yanchuan 2011-11-18
     */
    public SysGroupCredit querySysGropuCredit(CaAccount acct, CmResource res, CmCustomer cust)
    {
        return context.getComponent(ConfigQuery.class).queryGroupCredit(cust, acct, res, null);
        // CacheQuery cache = context.getComponent(CacheQuery.class);
        // SysGroupDef groupDef = cache.queryGroupIDByConditions(cmCustomer, cmResource, caAccount);
        // SysGroupCredit groupCredit = null;
        // if (!CommonUtil.isEmpty(groupDef) && !CommonUtil.isEmpty(groupDef.getGroupId()))
        // {
        // // 查询信用度
        // groupCredit = contect.
        // }
        // return groupCredit;
    }

    /**
     * 获取帐户的本次账期开始日
     * 
     * @author yanchuan 2011-05-16
     */
    public Date queryAcctCurrentCycleStart(Long acctId)
    {
        return queryAcctCycleStart(acctId, context.getRequestDate(), EnumCodeDefine.ACCT_CURRENT_BILL_CYCLE);
    }

    /**
     * 获取帐户的下个账期开始日
     * 
     * @author yanchuan 2011-11-8
     */
    public Date queryAcctNextCycleStart(Long acctId)
    {
        return queryAcctCycleStart(acctId, context.getRequestDate(), EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);
    }

    public Date queryAcctCycleStart(Long acctId, Date soDate, int type)
    {
        // 调用帐管接口，查询该帐户账期编号
        Acct3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccount account = bean.getAccount();
        if (account != null && account.getBillSts() != null && account.getBillSts() == EnumCodeDefine.BILL_STS_ACTIVE)
        {
            QryBillingPlanIn plan = new QryBillingPlanIn();
            plan.setAcctId(acctId);
            plan.setBillingPlanId(null);
            plan.setNum(type);
            plan.setOutDate(soDate);
            plan.setIsSupress(EnumCodeDefine.SUSPEND_MONTH_START);
            QryBillingPlanOut qryBillingPlanOut = context.getComponent(QueryBillCycleDate.class).queryBillCycleDate(plan);
            Date startDate = null;
            if(qryBillingPlanOut!=null){
                startDate=qryBillingPlanOut.getStartDate();
            }
            return startDate;
        }
        return null;
    }

    /**
     * @description:为帐管封装的实体结构，只返回帐管所需要的字段
     * @author hunan
     * @date 2011-12-22
     */
    public SAccount sAccountTransform4Billing(CaAccount dmAcct)
    {
        if (dmAcct == null)
        {
            return null;
        }
        SAccount sAcct = new SAccount();
        sAcct.setAcct_id(dmAcct.getAcctId());
        sAcct.setAcct_name(dmAcct.getName());
        if (dmAcct.getAccountClass() != null)
        {
            sAcct.setAcct_class(dmAcct.getAccountClass().shortValue());
        }
        if (dmAcct.getAccountType() != null)
        {
            sAcct.setAcct_type(dmAcct.getAccountType().shortValue());
        }
        if (dmAcct.getAccountSegment() != null)
        {
            sAcct.setAcct_segment(dmAcct.getAccountSegment().shortValue());
        }
        if (dmAcct.getAccountStatus() != null)
        {
            sAcct.setStatus(dmAcct.getAccountStatus().shortValue());
        }
        // if(dmAcct.getStsDate() != null)
        // {
        // sAcct.set;
        // }
        if (dmAcct.getCreateDate() != null)
        {
            sAcct.setCreate_date(DateUtil.formatDate(dmAcct.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }

        if(dmAcct.getExpireDate()!=null){
        	sAcct.setExpire_date(DateUtil.formatDate(dmAcct.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if(dmAcct.getValidDate()!=null){
        	sAcct.setValid_date(DateUtil.formatDate(dmAcct.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if (dmAcct.getProvinceId() != null)
        {
            sAcct.setProv_code(dmAcct.getProvinceId().shortValue());
        }
        if (dmAcct.getRegionCode() != null)
        {
            sAcct.setRegion_code(dmAcct.getRegionCode().shortValue());
        }
        if (dmAcct.getMeasureId() != null)
        {
            sAcct.setMeasure_id(dmAcct.getMeasureId());
        }
        // yanchuan 2012-02-03 增加返回blance_type
        sAcct.setBalance_type(dmAcct.getBalanceType());
        sAcct.setCust_id(dmAcct.getCustId());
        // xieqr 2012-07-09 增加返回CreditSignControl标志
        sAcct.setSign_credit_control(dmAcct.getCreditSignControl());
        // yanchuan 2012-07-25 增加返回operator_id
        if (dmAcct.getOperatorId() != null)
            sAcct.setCompany(dmAcct.getOperatorId());

        if (dmAcct.getBillSts() != null)
        {
            sAcct.setBill_sts(dmAcct.getBillSts());
        }
        return sAcct;
    }

    /**
     * @Description: 判断账户下是否有预付费用户
     * @param acctId
     * @return
     * @author: tangjl5
     * @Date: 2011-12-29
     */
    public List<Long> queryPrepaidUserIdsByAcctId(Long acctId)
    {
        List<CmResource> resList = context.getComponent(UserComponent.class).queryUsersByAcctID(acctId);
        if (CommonUtil.isEmpty(resList))
            return null;

        List<Long> prepaidUserIds = new ArrayList<Long>();
        for (CmResource res : resList)
        {
            if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
            {
                prepaidUserIds.add(res.getResourceId());
            }
        }
        return prepaidUserIds;
    }

    /**
     * @Description: 根据新老账户账期，获取用户最终账户账期
     * @author wukl 2011-10-11
     */
    public List<BillCycleComplex> getBillCycle(Long oldAcctId, Long newAcctId)
    {
        List<BillCycleComplex> oldBillCycleList = queryBillCycle(oldAcctId);
        if (CommonUtil.isEmpty(oldBillCycleList))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, oldAcctId);
        }

        List<BillCycleComplex> newBillCycleList = queryBillCycle(newAcctId);
        if (CommonUtil.isEmpty(newBillCycleList))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, newAcctId);
        }

        int oldLen = oldBillCycleList.size();
        int newLen = newBillCycleList.size();

        // 1、若新老账户账期个数不一致，则直接返回新账户账期
        if (newLen != oldLen)
        {
            return newBillCycleList;
        }

        // 2、若新老账户账期个数一致，若内容不一致 返回新账户账期
        for (int i = 0; i < newBillCycleList.size(); i++)
        {
            BillCycleComplex newBillCycle = newBillCycleList.get(i);
            BillCycleComplex oldBillCycle = oldBillCycleList.get(i);

            if (!newBillCycle.equals(oldBillCycle))
            {
                return newBillCycleList;
            }
        }

        // 3、若账期一致，则返回null
        return null;
    }

    /**
     * 查询账户的支付账户
     * 
     * @Description
     * @Author lijingcheng
     * @param dmAccount
     * @return
     */
    public Long queryBillAcctIdByAcct(CaAccount dmAccount)
    {
        Long billAcctId = null;
        if (dmAccount.getAccountClass().intValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA)
        {
            // billAcctId = context.getComponent(VPNComponent.class).queryBillAcctId(dmAccount.getAcctId());
            // if (billAcctId == null)
            // {
            // throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_BILLACCT_NOT_EXIST, dmAccount.getAcctId());
            // }
            // 2012-08-28 linzt 整理组件方法 采用load3hBean
            billAcctId = context.get3hTree().loadGroup3hBean(dmAccount.getAcctId()).getBillAcctId();
        }
        else
        {
            billAcctId = dmAccount.getAcctId();
        }
        return billAcctId;
    }

    /**
     * @author yanchuan 查询帐户扩展属性 2012-2-16
     * @param acctId
     * @return
     */
    public CaAccountExt queryAcctAttr(Long acctId)
    {
        if (!CommonUtil.isValid(acctId))
            return null;
        return querySingle(CaAccountExt.class, new DBCondition(CaAccountExt.Field.acctId, acctId));
    }

    /**
     * 根据账户id先查支付关系的用户，如果没有则查询归属关系的用户
     * 
     * @author liuyang
     * @param acctId 2012-2-224
     */
    public List<CaAccountRes> queryAcctResByAcctId(Long acctId)
    {
        List<CaAccountRes> accountRes = this.queryBillAcctResByAcctId(acctId);
        if (CommonUtil.isEmpty(accountRes))
        {
            accountRes = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId), new DBCondition(
                    CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        }

        return accountRes;
    }

    /**
     * 根据acctId 查询返回SAccount结构 xieqr 2012-3-13
     * 
     * @param acctId
     * @return
     */
    public SAccount querySAccountInfo(Long acctId)
    {

        if (CommonUtil.isValid(acctId))
        {
            CaAccount ca = querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
            CaAccountExt acctAttri = querySingle(CaAccountExt.class, new DBCondition(CaAccountExt.Field.acctId, acctId));
            CaCustomerRel custRel = querySingle(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.acctId, acctId),
                    new DBCondition(CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
            CaCustomInv custInv = CaCustomInv(acctId);
            SAccount sAcct = context.getComponent(AccountComponent.class).sAccountTransform(ca, custInv, acctAttri, custRel);
            if (sAcct != null)
            {
                return sAcct;
            }
        }
        return null;
    }

    public CaCustomInv CaCustomInv(Long acct_id)
    {
        List<CaCustomInv> invoices = queryCustomizedInvoIces(acct_id);
        return CommonUtil.isEmpty(invoices) ? null : invoices.get(0);
    }

    public List<CaCustomInv> queryCustomizedInvoIces(Long acct_id)
    {
        if (!CommonUtil.isValid(acct_id))
            return null;
        return query(CaCustomInv.class, new DBCondition(CaCustomInv.Field.acctId, acct_id));

    }

    /**
     * 根据acctId 查询返回CaNotificationExemption caohm5 2012-03-21
     * 
     * @param acctId
     * @return
     */
    public CaNotifyExempt queryCaNotificationExemption(Long acctId)
    {
        if (!CommonUtil.isValid(acctId))
        {
            return null;
        }
        else
        {
            CaNotifyExempt caNotificationExemption = this.querySingle(CaNotifyExempt.class, new DBCondition(
                    CaNotifyExempt.Field.objectId, acctId), new DBCondition(CaNotifyExempt.Field.objectType,
                    EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
            return caNotificationExemption;
        }
    }

    /**
     * @Date 2012-05-22 wangdw5 传入userId,查询返回用户级CaNotificationExemption 传入acctId,查询返回账户级CaNotificationExemption
     */
    public List<CaNotifyExempt> queryCaNotificationExemption(Long acctId, Long userId)
    {
        if (!CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId))
        {
            return null;
        }
        else
        {

        	int objectType=0;
        	long objectId=0;
        	if(CommonUtil.isValid(acctId)){
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
                objectId = acctId.longValue();
                
            }
        	else if(CommonUtil.isValid(userId)){
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV; 
                objectId = userId.longValue();
            }
        	List<CaNotifyExempt> caNotificationExemption = this.query(CaNotifyExempt.class, 
            		new DBCondition(CaNotifyExempt.Field.objectId, objectId), 
            		new DBCondition(CaNotifyExempt.Field.objectType,objectType));
            return caNotificationExemption;
        
        }
    }

    /**
     * @Date 2012-05-29 yugb 根据serviceId 与accountId 查询累积量
     */
    /*
    public CsdlArrayList<SAccumulate> querySAccumulate(Integer serviceId, Long acctId)
    {
        CsdlArrayList<SAccumulate> accumulateList = new CsdlArrayList<SAccumulate>(SAccumulate.class);
        SAccumulate sAccumulate = null;
        Integer accumulate_cond_id = null;
        List<PmAccumulateCondDtl> queryList = new ArrayList<PmAccumulateCondDtl>();
        PmUsageAccumulate pmUsageAccumulate = querySingle(PmUsageAccumulate.class, new DBCondition(
                PmUsageAccumulate.Field.priceRuleId, serviceId));
        if (pmUsageAccumulate != null)
        {
            accumulate_cond_id = pmUsageAccumulate.getAccumulateCondId();
            queryList = query(PmAccumulateCondDtl.class, new DBCondition(PmAccumulateCondDtl.Field.accumulateCondId,
                    accumulate_cond_id));
        }
        List<Integer> accumulateItemList = new ArrayList<Integer>();
        SAbmCommonQueryUp abmCommonQueryUp = new SAbmCommonQueryUp();
        IAbmInterfaceCommonInt abmInterfaceCommonInt = new IAbmInterfaceCommonInt();
        CBSErrorMsg cErrorMsg = new CBSErrorMsg();
        CsdlStructObjectHolder<SAbmCommonQueryRet> holder = new CsdlStructObjectHolder<SAbmCommonQueryRet>(
                new SAbmCommonQueryRet());

        String cycleBeginDate = DateUtil.getFormatDate(queryAcctCurrentCycleStart(acctId), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        String cycleEndDate = DateUtil.getFormatDate(queryAcctNextCycleStart(acctId), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        if (queryList != null)
        {
            for (PmAccumulateCondDtl pm : queryList)
            {
                accumulateItemList.add(pm.getAccumulateItem());
            }
        }
        if (accumulateItemList != null)
        {
            for (Integer i : accumulateItemList)
            {
                abmCommonQueryUp.set_tableFlag(32);
                abmCommonQueryUp.set_itemCode(i);
                abmCommonQueryUp.set_acctId(acctId);
                abmCommonQueryUp.set_cycleBegin(Long.parseLong(cycleBeginDate));
                abmCommonQueryUp.set_cycleEnd(Long.parseLong(cycleEndDate));
                abmInterfaceCommonInt.query_abmTable(abmCommonQueryUp, holder, cErrorMsg);
                if (holder != null && holder.get().get_accumulateList().size() > 0)
                {
                    sAccumulate = holder.get().get_accumulateList().get(0);
                }
                accumulateList.add(sAccumulate);
            }
        }
        return accumulateList;
    }
*/
    /**
     * @Date 2012-08-18 yugb :根据资费科目编号查询名称 itemId
     */
    public String queryItemNameById(Integer itemId)
    {
        PmPriceEvent event = querySingle(PmPriceEvent.class, new DBCondition(PmPriceEvent.Field.itemId, itemId));
        if (event != null)
            return event.getName();
        return null;
    }
    
    /**
     * 获取指定时间有效的账户账期对象
     * luojb 2012-11-1
     * @param cycles
     * @param targetDate
     * @return
     */
    public BillCycleComplex getTargetAcctCycle(List<BillCycleComplex> cycles,Date targetDate)
    {
        if(CommonUtil.isEmpty(cycles))
            return null;
        for (BillCycleComplex complex : cycles)
        {
            if(complex.getValidDate().before(targetDate) && complex.getExpireDate().after(targetDate))
                return complex;
        }
        return null;
    }
    
    /**
     * 根据确定的分表，批量查询账户信息
     * luojb 2012-11-27
     * @param acctIds
     * @param tableName
     * @return
     */
    public List<CaAccount> queryAccountsByTableName(List<Long> acctIds,String tableName)throws Exception
    {
        List<DBCondition> list=new ArrayList<DBCondition>();
        list.add(new DBCondition(CaAccount.Field.acctId, acctIds,Operator.IN));
        list.add(new DBCondition(CaAccount.Field.validDate,context.getRequestDate(),Operator.LESS_EQUALS));
        list.add(new DBCondition(CaAccount.Field.expireDate,context.getRequestDate(),Operator.GREAT_EQUALS));
        
         return (List<CaAccount>) DBUtil.getDBClient().select(DBUtil.getQueryCondition(CaAccount.class, list.toArray(new DBCondition[list.size()]), null), null,
                 new MyTableName(tableName));
    }
}
