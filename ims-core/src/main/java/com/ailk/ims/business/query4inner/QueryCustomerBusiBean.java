package com.ailk.ims.business.query4inner;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

public class QueryCustomerBusiBean extends BaseBusiBean
{

    private CustomerComponent custCmp;
    private SCustomer cust;
    private Long custId = null;
    private Long acctId = null;
    private Long userId = null;
    private String phoneId = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        custCmp = context.getComponent(CustomerComponent.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryCustomerReq req = (SQueryCustomerReq) input[0];
        custId = req.getCust_id();
        acctId = req.getAcct_id();
        phoneId = req.getPhone_id();
        userId = req.getUser_id();
        if (!CommonUtil.isValid(custId) && !CommonUtil.isValid(acctId) && !CommonUtil.isValid(phoneId)
                && !CommonUtil.isValid(userId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "cust_id ,acct_id , user_id, phone_id");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        IMS3hBean bean = null;
        //2012-08-23 yanchuan #56399 修改判断方式
        if(CommonUtil.isValid(custId) || CommonUtil.isValid(acctId))
        {
             bean = context.get3hTree().load3hBean(custId, acctId, null, null);
        }
        else if(CommonUtil.isValid(userId) || CommonUtil.isValid(phoneId))
        {
            bean = context.get3hTree().load3hBean(null, null, userId, phoneId);
        }
        // 虚账户没有归属客户
        // 2012-08-03 yanchuan 目前虚帐户获取不到客户不会报错
        CmCustomer dmCust = bean.getCustomer();
        if(dmCust != null)
        {
            cust = querySCustomerInfo(dmCust);
            CmPartyIdentity partyIdentity = context.getComponent(PartyComponent.class).queryPartyByIdentity(dmCust.getCustId());
            if (partyIdentity != null)
            {
                identityTransform(partyIdentity, cust);
            }
        }
        return null;
    }

    private SCustomer querySCustomerInfo(CmCustomer dmCust)
    {
        if (dmCust == null)
        {
            return null;
        }
        // 查询个人客户信息
        if (IMSUtil.isPersonCust(dmCust.getCustomerClass()))
        {
            CmIndividual individual = custCmp.queryCmIndividual(dmCust.getCustId());
            CmIndividualName individualName = custCmp.queryCmIndividualName(dmCust.getCustId());
            return custCmp.sCustTransform4Billing(dmCust, individual, individualName, null);
        }
        else
        {
            // 查询集团客户信息
            CmOrgName orgName = custCmp.queryCmOrgName(dmCust.getCustId());
            return custCmp.sCustTransform4Billing(dmCust, null, null, orgName);
        }
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryCustomerInfoResponse resp = new Do_queryCustomerInfoResponse();
        resp.setCustomer(cust);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    /**
     * 客户参与人信息 转换，实名制信息等 xieqr 2012-5-26
     * 
     * @param partyIdentity
     * @param sCust
     */
    private void identityTransform(CmPartyIdentity partyIdentity, SCustomer sCust)
    {
        if (null == partyIdentity)
            return;
        if (null != partyIdentity.getIssueDate())
            sCust.setIssue_date(DateUtil.getFormatDate(partyIdentity.getIssueDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        if (null != partyIdentity.getPartyIdentificationSpec())
            sCust.setReg_nbr(partyIdentity.getPartyIdentificationSpec());

        if (null != CommonUtil.IntegerToShort(partyIdentity.getPartyIdentificationType()))
            sCust.setReg_type(CommonUtil.IntegerToShort(partyIdentity.getPartyIdentificationType()));
        // hunan 增加证件名字返回
        if (CommonUtil.isValid(partyIdentity.getPartyIdentificationName()))
        {
            sCust.setReg_name(partyIdentity.getPartyIdentificationName());
        }
        // caohm5 add 实名制信息
        if (null != partyIdentity.getRealNameDate())
        {
            sCust.setReal_name_date(DateUtil.getFormatDate(partyIdentity.getRealNameDate(),
                    DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if (null!=partyIdentity.getRealNameSts())
        {
            sCust.setReal_name_sts(partyIdentity.getRealNameSts().shortValue());
        }

    }
}
