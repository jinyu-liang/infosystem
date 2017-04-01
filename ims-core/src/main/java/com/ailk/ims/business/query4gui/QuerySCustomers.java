package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyCustsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySCustomersReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomerList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;

/**
 * @Description 提供给界面的接口，便于界面操作人员通过指定的编号返回返回客户信息。编号可以是客户编号，用户编号，账户编号
 * @author hunan
 * @Date 2012-1-6
 * @Date 2012-6-5 tangjl5 BUG #47696 该接口不需要创建3hBean
 */
public class QuerySCustomers extends BaseBusiBean
{
    private BaseComponent baseCmp;
    private CustomerComponent custCmp;
    private PartyComponent partyCmp;
    
    private SContactList  contList = new SContactList();
    private SCustomerList custList = new SCustomerList();
    private SAccountList acctList = new SAccountList();
    private SUserList userList = new SUserList();
    private List<SUser> users = new ArrayList<SUser>();

    @Override
    public void init(Object... input) throws IMSException
    {
        baseCmp = context.getComponent(BaseComponent.class);
        custCmp = context.getComponent(CustomerComponent.class);
        partyCmp = context.getComponent(PartyComponent.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQuerySCustomersReq req = (SQuerySCustomersReq) input[0];
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQuerySCustomersReq");
        }
        if (!CommonUtil.isValid(req.getGui_acct_id())
                && !CommonUtil.isValid(req.getGui_cust_id())
                && !CommonUtil.isValid(req.getGui_phone_id())
                && !CommonUtil.isValid(req.getGui_user_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id , user_id , cust_id , phone_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQuerySCustomersReq req = (SQuerySCustomersReq) input[0];
        Long custId = req.getGui_cust_id();
        Long acctId = req.getGui_acct_id();
        Long userId = req.getGui_user_id();
        String phoneId = req.getGui_phone_id();
        queryCustomerInfo(custId);
        queryAccountInfo(acctId);
        queryUserInfo(userId ,phoneId);
        return null;
    }

    private void queryUserInfo(Long userId, String phoneId)
    {
        if ( CommonUtil.isValid(userId))
        {
            CmResource res=null;
            if(!CommonUtil.isValid(userId))
            {
                throw new IMSException("both userId and phoneId is null");
            }
            try
            {
                 res = context.get3hTree().loadUser3hBean(userId,null).getUser();        
            }
            catch(IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
            if(res != null && res.getResourceId()!=null)
            {
              //2012-08-27 linzt 整理组件方法  采用load3hBean
                String identity=null;
                try
                {
                    User3hBean userBean=context.get3hTree().loadUser3hBean(userId);
                    identity=userBean.getPhoneId();
                }
                catch(IMS3hNotFoundException e)
                {
                	imsLogger.error(e,e);
                }
                CaAccountRes acctRes =baseCmp.querySingle(CaAccountRes.class, 
                        new DBCondition(CaAccountRes.Field.resourceId,res.getResourceId()),
                        new DBCondition(CaAccountRes.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING)); 
                CmResLifecycle lifeCycle=baseCmp.querySingle(CmResLifecycle.class, 
                        new DBCondition(CmResLifecycle.Field.resourceId ,res.getResourceId()));
                SUser sUser = context.getComponent(UserComponent.class).sUserTransform(res, identity, acctRes, null, null, lifeCycle);
                if(sUser!= null)
                {
                    users.add(sUser);
                }
            }
           
        }
        
        if( CommonUtil.isValid(phoneId)&& !isInSUserList(phoneId,null , users))
        { 
            
            CmResource res=null;
            if(CommonUtil.isValid(phoneId))
            {
                throw new IMSException("both userId and phoneId is null");
            }
            try
            {
                res=context.get3hTree().loadUser3hBean(phoneId).getUser();
            }
            catch(IMSException e)
            {
            	imsLogger.error(e,e);
            }
            if(res!=null)
            {
                CaAccountRes acctRes =baseCmp.querySingle(CaAccountRes.class, 
                        new DBCondition(CaAccountRes.Field.resourceId,res.getResourceId()),
                        new DBCondition(CaAccountRes.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING)); 
                CmResLifecycle lifeCycle=baseCmp.querySingle(CmResLifecycle.class, 
                        new DBCondition(CmResLifecycle.Field.resourceId ,res.getResourceId()));
                SUser sUser = context.getComponent(UserComponent.class).sUserTransform(res, phoneId, acctRes, null, null, lifeCycle);
                if(sUser!= null)
                {
                    users.add(sUser);
                    
                }
            }
        }
        
        userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
    }

    private boolean isInSUserList(String phoneId, Long userId, List<SUser> users2)
    {
        if(!CommonUtil.isValid(phoneId)&&  !CommonUtil.isValid(userId) )
        {
            return true;
        }
        for(SUser user : users2)
        {
            if(CommonUtil.isValid(userId) && user.getUser_id().equals(userId))
            {
                return true;
            }
            if(CommonUtil.isValid(phoneId) && user.getPhone_id().equals(phoneId))
            {
                return true;
            }
        }
        
         return false;
    }

    private void queryAccountInfo(Long acctId)
    {
        
        if (CommonUtil.isValid(acctId))
        {
            CaAccount ca=context.get3hTree().loadAcct3hBean(acctId).getAccount();
            CaAccountExt acctAttri =baseCmp.querySingle(CaAccountExt.class, 
                    new DBCondition(CaAccountExt.Field.acctId ,acctId));
            CaCustomerRel custRel = baseCmp.querySingle(CaCustomerRel.class, 
                    new DBCondition(CaCustomerRel.Field.acctId , acctId),
                    new DBCondition(CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
            CaCustomInv custInv = baseCmp.querySingle(CaCustomInv.class, new DBCondition(CaCustomInv.Field.acctId , acctId));
            SAccount sAcct = context.getComponent(AccountComponent.class).sAccountTransform(ca, custInv, acctAttri,custRel);
            if( sAcct != null)
            {
                acctList.setSAccountList_Item(new SAccount[]{sAcct});
            }
        }
        
        
                                     
         
    }

    private void queryCustomerInfo(Long custId) throws BaseException
    {
        
        List<Long> custIds = new ArrayList<Long>();
        
        if (CommonUtil.isValid(custId))
        {
            if (!isInCustomerIdList(custId, custIds))
            {
                custIds.add(custId);
            }
        }

//        if (CommonUtil.isValid(acctId))
//        {
//            CaCustomerRel custRel = baseCmp.querySingle(CaCustomerRel.class,
//                    new DBCondition(CaCustomerRel.Field.acctId, acctId),
//                    new DBCondition(CaCustomerRel.Field.relationshipType, 
//                            EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
//            if (custRel != null && !isInCustomerIdList(custRel.getCustId(), custIds))
//            {
//                custIds.add(custRel.getCustId());
//            }
//        }

//        if (CommonUtil.isValid(userId))
//        {
//            CaAccountRes acctRes = baseCmp.querySingle(CaAccountRes.class,
//                    new DBCondition(CaAccountRes.Field.resourceId, userId),
//                    new DBCondition(CaAccountRes.Field.relationshipType,
//                            EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
//            if (acctRes != null && acctRes.getAcctId()!=null)
//            {
//                CaCustomerRel custRel = baseCmp.querySingle(CaCustomerRel.class, 
//                        new DBCondition(CaCustomerRel.Field.acctId, acctRes.getAcctId()),
//                        new DBCondition(CaCustomerRel.Field.relationshipType,
//                        EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
//                if (custRel != null && !isInCustomerIdList(custRel.getCustId(), custIds))
//                {
//                    custIds.add(custRel.getCustId());
//                }
//            }
//        }

//        if (CommonUtil.isValid(phoneId))
//        {
//            CmResIdentity resIden = baseCmp.querySingle(CmResIdentity.class, 
//                    new DBCondition(CmResIdentity.Field.identity,phoneId), 
//                    new DBCondition(CmResIdentity.Field.identityType, 
//                            EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
//            if (resIden != null && resIden.getResourceId()!= null)
//            {
//                CaAccountRes acctRes = baseCmp.querySingle(CaAccountRes.class, 
//                        new DBCondition(CaAccountRes.Field.resourceId,resIden.getResourceId()), 
//                        new DBCondition(CaAccountRes.Field.relationshipType,
//                        EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
//                if (acctRes != null && acctRes.getAcctId()!= null)
//                {
//                    CaCustomerRel custRel = baseCmp.querySingle(CaCustomerRel.class, 
//                            new DBCondition(CaCustomerRel.Field.acctId,acctRes.getAcctId()),
//                            new DBCondition(CaCustomerRel.Field.relationshipType,
//                            EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
//                    if (custRel != null && !isInCustomerIdList(custRel.getCustId(), custIds))
//                    {
//                        custIds.add(custRel.getCustId());
//                    }
//                }
//            }
//        }
        querySCustomerByIds(custIds);

    }

    private void querySCustomerByIds(List<Long> custIds)
    {
        List<SCustomer> custs = new ArrayList<SCustomer>();
        for (Long custId : custIds)
        {
            if (custId == null)
            {
                continue;
            }
            SCustomer cust = querySCustomer(custId);
            if (cust != null)
            {
                custs.add(cust);
            }

        }
        
        if( !CommonUtil.isEmpty(custs))
        {
            custList.setSCustomerList_Item(custs.toArray(new SCustomer[custs.size()]));
            contList = context.getComponent(ContactComponent.class).queryCustomerContactsByCustIds(custIds);
        }

    }

    private SCustomer querySCustomer(Long custId)
    {
        CmCustomer dmCust = context.get3hTree().loadCust3hBean(custId).getCustomer();
        
        CmIndividualName indiName = null;
        CmIndividual indi = null;
        CmOrgName orgName = null;
        CmPartyIdentity identity = partyCmp.querySingle(CmPartyIdentity.class, 
                new DBCondition(CmPartyIdentity.Field.partyId,custId));
        if (IMSUtil.isPersonCust(dmCust.getCustomerType()))
        {
            indiName = partyCmp.queryInvidualNameByCustId(custId);
            indi = partyCmp.queryInvidualByCustId(custId);
        }
        else
        {
            orgName = partyCmp.queryCmOrgNameByPartyId(custId);
        }
        return custCmp.sCustTransform(dmCust, indi, indiName, orgName, identity);
    }

    private boolean isInCustomerIdList(Long custId, List<Long> custIds)
    {
        if(custId == null)
        {
            return true;
        }
        if (!CommonUtil.isEmpty(custIds) && custId != null)
        {
            for (Long id : custIds)
            {
                if (custId.equals(id))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_fuzzyCustsForGUIResponse req = new Do_fuzzyCustsForGUIResponse();
        req.setSCustomerList(custList);
        req.setSContactList(contList);
        req.setSAccountList(acctList);
        req.setSUserList(userList);
        return req;
    }

    @Override
    public void destroy()
    {
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
//		SQuerySCustomersReq req = (SQuerySCustomersReq) input[0];
//		List<com.ailk.ims.ims3h.IMS3hBean> list = new ArrayList<com.ailk.ims.ims3h.IMS3hBean>();
//        list.add(context.get3hTree().load3hBean(req.getGui_cust_id(),req.getGui_acct_id(),req.getGui_user_id(),req.getGui_phone_id()));
//        return list;
	    // @Date 2012-6-5 tangjl5 BUG #47696 该接口不需要创建3hBean
	    return null;
	}

}
