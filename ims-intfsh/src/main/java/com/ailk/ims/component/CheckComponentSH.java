package com.ailk.ims.component;

import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

/**
 * @author zhangzj3
 * @date 2012-10-25
 * Description:主要用于有效性的校验
 */

public class CheckComponentSH extends BaseComponent{
    /**
     * 公共校验：  传userId一定会返回acctId;
     *           传phoneId一定会返回userId,acctId
     * 规则:1、校验userId
     *         1.1 查询userId在路由中对应的acctId,不存在则查询方法里报错。
     *         1.2 判断是否有传入acctId
     *             1.2.1 有则比较两者是否一致，不一致则 报错
     *             1.2.2 没有则将其赋给temAcctId;
     *      2、校验phoneId
     *         2.1 查询phoneId在路由中对应的acctId,不存在则查询方法报错。
     *         2.2 判断temAcctId是否为空
     *             2.2.1 不为空，判断两者是否一致，不一致报错。
     *             2.2.2 为空，判断acctId是否为空
     *                   2.2.2.1  有则判断两者是否一致，不一致报错
     *                   2.2.2.2  没有则将其赋给temAcctId
     *                   2.2.2.3  通过phoneId去查找resId,没找到报错
     *      3、再次校验userId
     *         3.1 如果不为空则查找其生命周期，结果为空则报错。
     *      4、校验acctId
     *         4.1 如果不为空，查询账户信息，结果为空则报错
     *      4、校验custId
     *         5.1 如果不为空，查询客户信息，结果为空则报错
     */
    public Check3HParamReturn check3HParam(Long custId,Long acctId,Long userId,String phoneId){
    	//设置分表参数，设置前先进行清除
    	IMSUtil.removeRouterParam();
    	if (CommonUtil.isValid(phoneId))
    	{
    		IMSUtil.setIdenRouterParam(phoneId);
    	}
    	else if (CommonUtil.isValid(userId))
    	{
    		IMSUtil.setUserRouterParam(userId);
    	}
    	else if (CommonUtil.isValid(acctId))
    	{
    		IMSUtil.setAcctRouterParam(acctId);
    	}
    	else if (CommonUtil.isValid(custId))
    	{
    		IMSUtil.setCustRouterParam(custId);
    	}
        Check3HParamReturn bean = new Check3HParamReturn();
        Long temAcctId = null;
        CaAccount account = null;
        CmResLifecycle lifeCycle = null;
        CmCustomer customer = null;
       
        if(CommonUtil.isValid(userId)){
            long temId = context.getComponent(RouterComponent.class).getAcctIdByUserIdRout(userId);
            if(CommonUtil.isValid(acctId)){
                if(temId != acctId){
                    IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_ACCT_FORUSER_NOTEXIST, acctId, userId);
                }
            }else{
                temAcctId = temId;
            }
        }
        if(CommonUtil.isNotEmpty(phoneId)){
            long temId = context.getComponent(RouterComponent.class).getAcctIdByPhoneIdRout(phoneId);
            if(CommonUtil.isValid(temAcctId)){
                if(temId != temAcctId){
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USERID_PHONEID_ERROR,phoneId,userId);
                }
            }else{
                if(CommonUtil.isValid(acctId)){
                    if(temId != acctId){
                        IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_ACCT_FORUSER_NOTEXIST, acctId, phoneId);
                    }
                }else{
                    temAcctId = temId;
                }
                userId = context.getComponent(UserQuery.class).queryUserIdByPhone(phoneId);
                if(!CommonUtil.isValid(userId)){
                    IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_EXIST_FOR_PHONEID,phoneId);
                }
            }
        }
        //判断用户生命周期
        if(CommonUtil.isValid(userId)){
            lifeCycle = context.getComponent(UserQuery.class).queryLifeCycleByUserId(userId);
            if(lifeCycle == null){
                IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL,userId);
            }
        }
        //判断账户有效性
        if(CommonUtil.isValid(acctId)){
            account = context.getComponent(AccountQuery.class).queryAccountById(acctId);
            if(account == null){
                IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST, acctId);
            }
        }
        //判断客户有效性
        if(CommonUtil.isValid(custId)){
            //判断账户与客户的关系
            /*if(account != null){
                if(!account.getCustId().equals(custId)){
                    IMSUtil.throwBusiException(ErrorCodeDefine.CUSTOMER_AND_ACCOUNT_NOT_MATCH,custId,account.getAcctId());
                }
            }*/
            customer = context.getComponent(CustomerQuery.class).queryCustomer(custId);
            if(customer == null){
                IMSUtil.throwBusiException(ErrorCodeDefine.CUSTOMER_NOTEXIST, custId);
            }
        }
        if(temAcctId != null){
            bean.setAcctId(temAcctId);
        }else{
            bean.setAcctId(acctId);
        }
        bean.setUserId(userId);
        bean.setPhoneId(phoneId);
        bean.setAccount(account);
        bean.setCustomer(customer);
        bean.setResLifeCycle(lifeCycle);
        return bean;
    }
    public int checkPayType(Long acct_id,String phone_id){
    	int payType=1;
    	if(CommonUtil.isValid(acct_id)){
    		
    		payType=EnumCodeShDefine.PAY_TYPE_ACCT;
    		
    	}else if(CommonUtil.isValid(phone_id)){
    		
    		payType=EnumCodeShDefine.PAY_TYPE_SUB;
    	}
    	return payType;
    }
}
