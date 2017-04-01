package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCommonUserInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 供帐管调用，通过用户的编号查询账号的编号。
 * @author hunan
 * @Date 2012-1-10
 */
public class QryCommInfoByUserIdBean extends BaseBusiBean
{
    private CustomerComponent custCmp;
    private UserComponent userCmp;

    private String custName = null;
    private Long acctId = null;
    private String acctName = null;
    private Integer userSts = null;
    private Integer paymode = null;
    private Long promotion = null;
    private String offerName = null;
    private String phoneId = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        custCmp = context.getComponent(CustomerComponent.class);
        userCmp = context.getComponent(UserComponent.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryUserByIdReq req = (SQueryUserByIdReq) input[0];
        if (req == null || !CommonUtil.isValid(req.getUser_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQeryAccctInfoReq");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryUserByIdReq req = (SQueryUserByIdReq) input[0];
        Long userId = req.getUser_id();

        queryUserInfo(userId);
        queryUserMainPromoiton(userId);
        queryCustNameInfo(userId);
        queryAcctInfo(userId);
        // queryCreditInfo(reqAcctId);
        // queryBalanceInfo(reqAcctId);
        return null;
    }

    private void queryUserMainPromoiton(Long userId)
    {
        CoProd dmProd = context.getComponent(BaseProductComponent.class).queryPrimaryProductByUserId(userId);
        if (dmProd == null)
        {
            return;
        }
        promotion = dmProd.getProductId();

        PmProductOffering offer = context.getComponent(BaseProductComponent.class).queryProdOffering(dmProd.getProductOfferingId());
        offerName = offer.getName();
    }

    private void queryUserInfo(Long userId)
    {
        User3hBean bean = context.get3hTree().loadUser3hBean(userId);
        if (bean.getUser() == null)
        {
            return;
        }
        CmResource res = bean.getUser();
        phoneId = bean.getPhoneId();
        if (res != null)
        {
            paymode = res.getBillingType();
        }
//        CmResIdentity resIdentity = userCmp.queryResIdentityByUserId(userId);
//        if (resIdentity != null)
//        {
//            phoneId = resIdentity.getIdentity();
//        }
      //2012-08-27 linzt 整理组件方法  采用load3hBean
        CmResLifecycle lifecycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
        if (lifecycle != null)
        {
            userSts = lifecycle.getSts();
        }
    }

    private void queryAcctInfo(Long userId)
    {
    	CaAccount dmAcct = null;
    	try
        {
    	    dmAcct=context.get3hTree().loadUser3hBean(userId).getAccount();
        }
        catch(IMS3hNotFoundException e)
        { 
            return;
        }
        acctId = dmAcct.getAcctId();
        acctName = dmAcct.getName();
    }

    // private void queryCreditInfo(Long acctId2)
    // {
    // BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
    // Long cr = bdgCmp.getAccountCreditLimit(acctId2);
    // if (cr != null)
    // {
    // credit = new Double(cr);
    // }
    // }

    // private void queryBalanceInfo(Long acctId2)
    // {
    // Long ba = context.getComponent(BalanceComponent.class).queryBalance(acctId2, null);
    // if (ba != null)
    // {
    // balance = new Double(ba);
    // }
    // }

    private void queryCustNameInfo(Long userId)
    {
        IMS3hBean bean = context.get3hTree().loadUser3hBean(userId);
        CmCustomer dmCust = bean.getCustomer();
        if (dmCust == null)
        {
            return;
        }
        Long custId = bean.getCustId();
        if (IMSUtil.isPersonCust(dmCust.getCustomerClass()))
        {
            CmIndividualName indiName = custCmp.queryCmIndividualName(custId);
            if (indiName != null)
            {
                custName = indiName.getPreferredGivenName() + " " + indiName.getFamilyNames();
            }
        }

        else
        {
            CmOrgName orgName = custCmp.queryCmOrgName(custId);
            if (orgName != null)
            {
                custName = orgName.getTradingName();
            }
        }
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_QueryCommonUserInfoResponse resp = new Do_QueryCommonUserInfoResponse();
        resp.setAcct_id(acctId);
        resp.setAcct_name(acctName);
        resp.setCust_name(custName);
        resp.setPaymode(paymode);
        resp.setPhoneId(phoneId);
        resp.setPromotion(promotion);
        resp.setUser_sts(userSts);
        resp.setOffer_name(offerName);
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	SQueryUserByIdReq req = (SQueryUserByIdReq) input[0];
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(req.getUser_id()));
        return list;
    }

}
