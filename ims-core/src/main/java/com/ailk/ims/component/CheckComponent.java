package com.ailk.ims.component;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.CheckedComplex;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

/**
 * 账户用户相关检查方法类 <br>
 * 1、检查用户，账户必须为激活状态， <br>
 * 因此，在三户新装、生命周期等不要求账户用户为激活状态的业务中，不可使用该类中的方法。
 * 
 * @author wangjt
 * @Date 2011-10-8
 * @Date 2012-03-28 lijc3 上海优先校验userId
 */
public class CheckComponent extends BaseComponent
{
    public CheckComponent()
    {
    }

    /**
     * 验证客户ID对应的客户是否存在，无需验证客户状态<br>
     * 返回的对象不为空，无需再次检查
     */
    public CmCustomer checkCustId(Long custId)
    {
        if (!CommonUtil.isValid(custId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_CUSTID_EMPTY);
        }
        CmCustomer customer = super.querySingle(CmCustomer.class, new DBCondition(CmCustomer.Field.custId, custId));
        if (customer == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_NOTEXIST, custId);
        }
        return customer;
    }

    public void checkCust(SCustomer customer)
    {
        CmCustomer cmCustomer = checkCustId(customer.getCust_id());
        if (customer.getCust_type() == null)
            return;
        // 判断客户类是否为指定的类型
        if (!IMSUtil.isCustType(customer.getCust_type()))
            throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_CUSTOMER_TYPE_IS_INVALID, customer.getCust_type());
        // 个人客户类型不能修改为集团客户类型,集团客户类型不能修改为个人客户类型 yanchuan 2011-12-06
        if (IMSUtil.isPersonCust(CommonUtil.ShortToInteger(customer.getCust_type()))
                && !IMSUtil.isPersonCust(cmCustomer.getCustomerClass()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_CUSTOMER_TYPE_IS_INVALID, customer.getCust_type());
        }
        if (!IMSUtil.isPersonCust(CommonUtil.ShortToInteger(customer.getCust_type()))
                && IMSUtil.isPersonCust(cmCustomer.getCustomerClass()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_CUSTOMER_TYPE_IS_INVALID, customer.getCust_type());
        }

    }

    /**
     * 检查账户ID对应的账户是否存在且为激活状态<br>
     * 
     * @param acctId：输入的acctId必须保证不为null
     * @return:返回的CaAccount,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CaAccount checkAcctId(Long acctId)
    {
        if (!CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_EMPTY);
        }
        //帐户实体通过3hBean获取 yanchuan 2012-05-08
        Acct3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccount caAccount = bean.getAccount();
        if (caAccount == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
        }

        if (needCheckAcctSts())
        {
            // 验证账户状态
            // 0 - expired; 1- active; 2 - suspend.
            Integer accountStatus = caAccount.getAccountStatus();
            if (accountStatus == null || accountStatus != EnumCodeDefine.ACCOUNT_ACTIVE)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCOUNT_NOT_ACTIVE, acctId);
            }
        }

        return caAccount;
    }

    /**
     * 检查用户ID对应的用户和<归属>账户是否存在且为激活状态<br>
     * 
     * @param userId：输入的userId必须保证不为null
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkUserId(Long userId)
    {
        if (!CommonUtil.isValid(userId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USERID_EMPTY);
        }
        // 查询用户支付账号
        CheckedComplex complex = this.getCheckedComplexByUserId(userId);
        if (complex == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
        }

        if (needCheckUserSts())
        {
            // 验证用户状态(必须为激活状态1001)
            CmResLifecycle cmResLifecycle = complex.getUserLifecycle();
            Integer sts = cmResLifecycle.getSts();
            if (sts == null || sts != EnumCodeDefine.LIFECYCLE_ACTIVE)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USER_NOT_ACTIVE, userId);
            }
        }

        if (needCheckAcctSts())
        {
            // 验证账户状态（必须为激活状态）
            CaAccount account = complex.getAccount();
            // 0 - expired; 1- active; 2 - suspend.
            Integer accountStatus = account.getAccountStatus();
            if (accountStatus == null || accountStatus != EnumCodeDefine.ACCOUNT_ACTIVE)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCOUNT_NOT_ACTIVE, account.getAcctId());
            }
        }

        return complex;
    }

    /**
     * 检查手机号码对应的用户和账户是否存在且为激活状态<br>
     * 
     * @param phoneId：输入的phoneId必须保证不为null
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkPhoneId(String phoneId)
    {
        if (!CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONEID_EMPTY);
        }

        CheckedComplex complex = this.getCheckedComplexByPhoneId(phoneId);
        if (complex == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PHONE_NOT_EXIST, phoneId);
        }

        Integer identityType = complex.getUserResIdentity().getIdentityType();
        if (identityType != null && identityType == EnumCodeDefine.RESOURCE_IDENTITYTYPE_FAXNUMBER)
        {
            if (context.isNotQueryBusi())// 非查询业务，不可用fax number
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_FAX_NUMBER_ERROR, phoneId);
            }
        }

        // 验证用户状态
        CmResLifecycle cmResLifecycle = complex.getUserLifecycle();
        Integer sts = cmResLifecycle.getSts();
        if (sts == null || sts != EnumCodeDefine.LIFECYCLE_ACTIVE)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONE_USER_NOT_ACTIVE, phoneId);
        }

        // 验证账户状态
        CaAccount account = complex.getAccount();
        // 0 - expired; 1- active; 2 - suspend.
        Integer accountStatus = account.getAccountStatus();
        if (accountStatus == null || accountStatus != EnumCodeDefine.ACCOUNT_ACTIVE)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONE_ACCOUNT_NOT_ACTIVE, phoneId);
        }

        return complex;
    }

    /**
     * 检查用户，用户归属账户是否存在且为激活状态 <br>
     * userId和phoneId不可同时传空
     * 
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkUserIdPhoneId(Long userId, String phoneId)
    {
    	if(userId==null&&CommonUtil.isEmpty(phoneId)){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL,"userId,phoneId");
    	}
    	//@Date 2012-03-28 lijc3 上海优先校验userId
    	if(ProjectUtil.is_CN_SH()){
    		if (CommonUtil.isValid(userId))
    		{
    			CheckedComplex complex = this.checkUserId(userId);
    			if (CommonUtil.isValid(phoneId) && !complex.getPhoneId().equals(phoneId))
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONEID_USERID_ERROR, userId, phoneId);
    			}
    			return complex;
    		}
    		return this.checkPhoneId(phoneId);
    	}else{
    		if (CommonUtil.isValid(phoneId))// 手机号码不为空
    		{
    			CheckedComplex complex = this.checkPhoneId(phoneId);// 根据手机号码查询
    			// 输入的用户ID不为空，但却不等于手机号码对应的用户ID，报异常
    			if (CommonUtil.isValid(userId) && userId.longValue() != complex.getUserId())
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USERID_PHONEID_ERROR, phoneId, userId);
    			}
    			return complex;
    		}
    		// 根据用户ID检查
    		return this.checkUserId(userId);
    	}
    }

    /**
     * userId和acctId不可同时传空
     * 
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkAcctIdUserId(Long acctId, Long userId)
    {
        if (CommonUtil.isValid(userId))// userId不为空
        {
            CheckedComplex complex = this.checkUserId(userId);// 根据userId查询
            // 输入的acctId不为空，但却不等于用户的归属acctId，报异常
            if (CommonUtil.isValid(acctId) && acctId.longValue() != complex.getAcctId())
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_USERID_ERROR, userId, acctId);
            }
            return complex;
        }
        // 根据acctId检查
        CaAccount caAccount = this.checkAcctId(acctId);

        CheckedComplex complex = new CheckedComplex();
        complex.setAccount(caAccount);
        return complex;
    }

    /**
     * phoneId和acctId不可同时传空
     * 
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkAcctIdPhoneId(Long acctId, String phoneId)
    {
        if (CommonUtil.isValid(phoneId))// 手机号码不为空
        {
            CheckedComplex complex = this.checkPhoneId(phoneId);// 根据手机号码查询

            // 输入的acctId不为空，但却不等于手机号码对应的acctId，报异常
            if (CommonUtil.isValid(acctId) && acctId.longValue() != complex.getAcctId())
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_PHONEID_ERROR, phoneId, acctId);
            }
            return complex;
        }

        // 根据acctId检查
        CaAccount caAccount = this.checkAcctId(acctId);

        CheckedComplex complex = new CheckedComplex();
        complex.setAccount(caAccount);
        return complex;
    }

    /**
     * phoneId和acctId和userId不可同时传空
     * 
     * @return:返回的CheckedComplex,必然不为空，可直接使用，无需再次检查是否为空
     */
    public CheckedComplex checkAcctIdUserIdPhoneId(Long acctId, Long userId, String phoneId)
    {
    	if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId) && !CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "user_id,phone_id,acct_id");
        }
    	//@Date 2012-03-28 lijc3 上海优先校验userId
    	if(ProjectUtil.is_CN_SH()){
    		if (CommonUtil.isValid(userId))
    		{
    			CheckedComplex complex = this.checkUserId(userId);
    			//phoneId不为空，又不等于userId对应的phoneId,抛出异常
    			if (CommonUtil.isValid(phoneId) &&complex.getUserResIdentity()!=null && !complex.getUserResIdentity().getIdentity().equals(phoneId))
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONEID_USERID_ERROR, userId, phoneId);
    			}
    			//acctId不为空，又不等于userId对应的acctId,抛出异常
    			if (CommonUtil.isValid(acctId) && acctId.longValue() != complex.getAcctId())
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_USERID_ERROR, userId, acctId);
    			}
    			return complex;
    		}
    		if (CommonUtil.isValid(phoneId))
    		{
    			return this.checkAcctIdPhoneId(acctId, phoneId);
    		}
    	}else{
    		if (CommonUtil.isValid(phoneId))
    		{
    			CheckedComplex complex = this.checkPhoneId(phoneId);// 根据手机号码查询
    			
    			// 输入的用户ID不为空，但却不等于手机号码对应的用户ID，报异常
    			if (CommonUtil.isValid(userId) && userId.longValue() != complex.getUserId())
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_USERID_PHONEID_ERROR, phoneId, userId);
    			}
    			// 输入的acctId不为空，但却不等于手机号码对应的acctId，报异常
    			if (CommonUtil.isValid(acctId) && acctId.longValue() != complex.getAcctId())
    			{
    				throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_PHONEID_ERROR, phoneId, acctId);
    			}
    			return complex;
    		}
    		if (CommonUtil.isValid(userId))
    		{
    			return this.checkAcctIdUserId(acctId, userId);
    		}
    	}


        // 根据acctId检查
        CaAccount caAccount = this.checkAcctId(acctId);

        CheckedComplex complex = new CheckedComplex();
        complex.setAccount(caAccount);
        return complex;
    }

    private CheckedComplex getCheckedComplexByPhoneId(String phoneId)
    {
    	CheckedComplex checked = new CheckedComplex();
    	
//        CmResIdentity cmResIdentityWhere = new CmResIdentity();
//        cmResIdentityWhere.setIdentity(phoneId);
        
        CmResIdentity cmResIdentity = super.querySingle(CmResIdentity.class,new DBCondition(CmResIdentity.Field.identity,phoneId));
        CmResource cmResource = null;
        CmResLifecycle cmResLifecycle = null;
        CaAccount caAccount = null;
        
        Long resourceId = null;
        Long acctId = null;
        if(cmResIdentity != null)
        {
        	resourceId = cmResIdentity.getResourceId();
        }
        
        if(resourceId != null)
        {
            try
            {
                acctId=context.get3hTree().loadUser3hBean(resourceId).getAcctId();
            }
            catch(IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
        	cmResource = super.querySingle(CmResource.class,new DBCondition(CmResource.Field.resourceId, resourceId));
        	cmResLifecycle = super.querySingle(CmResLifecycle.class,new DBCondition(CmResLifecycle.Field.resourceId, resourceId));
        }
        
        if(acctId != null)
        {
        	caAccount = super.querySingle(CaAccount.class,new DBCondition(CaAccount.Field.acctId, acctId));
        }

        if(cmResource!=null && cmResLifecycle!=null && cmResIdentity!=null && caAccount!=null)
        {
        	checked.setUser(cmResource);
        	checked.setUserLifecycle(cmResLifecycle);
        	checked.setAccount(caAccount);
        	checked.setUserResIdentity(cmResIdentity);
        	
        	return checked;
        }
        
        return null;
    }

    private CheckedComplex getCheckedComplexByUserId(Long userId)
    {
    	 CheckedComplex complex = new CheckedComplex();
    	 CmResIdentity identity=super.querySingle(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, userId),
    			 new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
    	 
    	 CmResLifecycle cmResLifecycle = super.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, userId));
    	 CmResource cmResource = super.querySingle(CmResource.class,new DBCondition(CmResource.Field.resourceId, userId));
    	 CaAccount caAccount = null;
    	 
    	 CaAccountRes caAccountRes = super.querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
    			 new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    	 Long acctId = null;
    	 if(caAccountRes != null)
    	 {
    		 acctId = caAccountRes.getResAcctId();
    		 caAccount = super.querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
    	 }
    	
    	 if(cmResource != null && cmResLifecycle != null && caAccount != null)
    	 {
    		 complex.setUser(cmResource);
    		 complex.setUserLifecycle(cmResLifecycle);
    		 complex.setAccount(caAccount);
    		 complex.setUserResIdentity(identity);
    		 return complex;
    	 }
    	
    	 return null;
    }

    /**
     * 查询账户
     */
    private CaAccount getAccountByAcctId(Long acctId)
    {
        return super.querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
    }

    /**
     * 子类可覆盖
     */
    public boolean needCheckUserSts()
    {
        return true;
    }

    /**
     * 子类可覆盖
     */
    public boolean needCheckAcctSts()
    {
        return true;
    }
}
