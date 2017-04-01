package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
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
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctByPhoneReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 提供给界面的接口，依据设备的号码查询账号的基础信息
 * @author hunan
 * @Date 2012-1-10
 * 2012-06-23 zengxr for query credit error
 */
public class QryCommInfoByPhoneIdBean extends BaseBusiBean
{
    private CustomerComponent custCmp;
    private UserComponent userCmp;

    private Long custId = null;
    private String custName = null;
    private Integer custSegment = null;
    private Long acctId = null;
    private String acctName = null;
    private Integer acctSts = null;
    private Integer userSts = null;
    private Integer paymode = null;
    private Long promotion = null;
    private String offerName = null;
    private String phoneId = null;
    private Double credit = null;
    private Double balance = null;
    private SQueryAcctByPhoneReq req = null;
    private IMS3hBean bean = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SQueryAcctByPhoneReq) input[0];
        phoneId = req.getDevId();
        custCmp = context.getComponent(CustomerComponent.class);
        userCmp = context.getComponent(UserComponent.class);
        bean = context.get3hTree().loadUser3hBean(phoneId);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (req == null || !CommonUtil.isValid(req.getDevId()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQeryAccctInfoReq");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        queryUserInfo(phoneId);
        queryUserMainPromoiton(phoneId);
        queryCustNameInfo(phoneId);
        queryAcctInfo(phoneId);
        //2012-06-23 zengxr for query credit error
//        queryCreditInfo(acctId);
//        queryBalanceInfo(acctId);

        return null;
    }

    private void queryUserMainPromoiton(String phone)
    {
        Long userId = bean.getUserId();
        if (!CommonUtil.isValid(userId))
        {
            return;
        }
        CoProd dmProd = context.getComponent(BaseProductComponent.class).queryPrimaryProductByUserId(userId);
        if (dmProd == null)
        {
            return;
        }
        promotion = dmProd.getProductId();

        PmProductOffering offer = context.getComponent(BaseProductComponent.class).queryProdOffering(dmProd.getProductOfferingId());
        offerName = offer.getName();
    }

    private void queryUserInfo(String phone)
    {
        if (!bean.isUser3hBean())
        {
            return;
        }
        CmResource res = ((User3hBean)bean).getUser();
        long userId = bean.getUserId();
        if (res != null)
        {
            paymode = res.getBillingType();
        }

        CmResLifecycle lifecycle = userCmp.queryLifeCycleByUserId(userId);
        if (lifecycle != null)
        {
            userSts = lifecycle.getSts();
        }
    }

    private void queryAcctInfo(String phone)
    {
        CmResource resource = ((User3hBean)bean).getUser();
        CaAccount dmAcct = bean.getAccount();
        if (dmAcct == null)
        {
            return;
        }
        acctId = bean.getAcctId();
        acctName = dmAcct.getName();
        acctSts = dmAcct.getAccountStatus();
    }


    private void queryCustNameInfo(String phone)
    {
        CmCustomer dmCust = bean.getCustomer();
        custId = bean.getCustId();
        custSegment = dmCust.getCustomerSegment();

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
        resp.setAcct_sts(acctSts);
        if (CommonUtil.isNotEmpty(custName))
        {
            resp.setCust_name(custName);
        }
        resp.setCust_id(custId);
        resp.setCust_segment(custSegment);
        resp.setPaymode(paymode);
        resp.setPhoneId(phoneId);
        resp.setPromotion(promotion);
        resp.setUser_sts(userSts);
        resp.setOffer_name(offerName);
        resp.setBalance(balance);
        resp.setLimit_credit(credit);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(req.getUserId(),req.getDevId()));
        return list;
    }

}
