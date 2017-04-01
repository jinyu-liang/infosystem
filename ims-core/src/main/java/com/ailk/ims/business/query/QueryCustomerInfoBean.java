package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

/**
 * @Description 查询客户信息
 * @author yangyang
 * @Date 2011-9-27
 */
public class QueryCustomerInfoBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
    }

    /*
     * 这个类请增加更为详细的注释 XXX
     */
    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryCustomerInfoReq req = (SQueryCustomerInfoReq) input[0];
        Long acctId = req.getAcct_id();
        Long userId = req.getUser_id();

        if (!CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_ID_AND_USER_ID_BOTH_NULL);
        }

        if (!CommonUtil.isValid(acctId) && CommonUtil.isValid(userId))
        {
            // caohm5 modify 2012-03-08
            CaAccount acct =null;
            try
            {
             // 默认查询归属账户
                acct=context.get3hTree().loadUser3hBean(userId).getAccount();
                acctId = acct.getAcctId();
            }
            catch(IMS3hNotFoundException e)
            {   
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
            }
        }

        if (CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId))
        {
            Acct3hBean acctBean=context.get3hTree().loadAcct3hBean(acctId);
            if (acctBean.isGroup3hBean())
            {
                acctId = acctBean.getAcctId();  
            }
        }
        //caohm5 modify 
        //通过账户查客户；不走CaCustomerRel这层关系

//        CaCustomerRel rel = context.getComponent(AccountComponent.class).queryCaCustRelByAcctID(acctId);
//
//        if (rel == null)
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, acctId);
//
//        Long custId = rel.getCustId();
        CaAccount ca=context.get3hTree().loadAcct3hBean(acctId).getAccount();
        if(ca.getCustId()==null){
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, acctId);
        }
        Long custId=ca.getCustId();
        PartyComponent pc = context.getComponent(PartyComponent.class);

        CmCustomer cmCust =context.get3hTree().loadCust3hBean(custId).getCustomer();
        // 将数据库实体转换为报文实体

        Long partyId = pc.queryPartyIdByCustId(custId);
        CmIndividualName individualName = pc.queryInvidualNameByCustId(custId);
        CmIndividual individual = null;
        CmOrgName orgName = null;
        CmPartyIdentity partyIdentity = null;
        if (CommonUtil.isValid(partyId))
        {
            individual = pc.queryCmIndividualByPartyId(partyId);
            orgName = pc.queryCmOrgNameByPartyId(partyId);
            partyIdentity = pc.queryPartyByIdentity(partyId);
        }
        // if (CommonUtil.isEmpty(individual))
        // {
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.CM_INDIVIDUAL_IS_NOT_EXIST, partyId);
        // }
        // if (CommonUtil.isEmpty(individualName))
        // {
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.CM_INDIVIDUAL_NAME_IS_NOT_EXIST, custId);
        // }
        SCustomer sCust = context.getComponent(CustomerComponent.class).sCustTransform(cmCust, individual, individualName,
                orgName, partyIdentity);

        /*
         * SCustomer cust = new SCustomer(); CmParty cp = cc.queryCmPartyByCustId(custId); if
         * (IMSUtil.isPersonCust(cmCust.getCustomerClass())) { CmParty cp = cc.queryCmPartyByCustId(custId); CmIndividual ci =
         * pc.queryCmIndividualByPartyId(cp.getPartyId()); if (CommonUtil.isEmpty(ci)) { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.CM_INDIVIDUAL_IS_NOT_EXIST, cp.getPartyId()); } CmIndividualName cin =
         * pc.queryInvidualNameByCustId(custId); if (CommonUtil.isEmpty(cin)) { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.CM_INDIVIDUAL_NAME_IS_NOT_EXIST, custId); } // 2011-10-01 zengxr cust_title
         * changed to String cust.setCust_title(cin.getFormOfAddress()); cust.setCust_id(custId);
         *//**** setter ****/
        /*
         * cust.setBirthday(IMSUtil.formatDate(ci.getBirthDate())); //2011-12-15 hunan delete：countryID转移到customer表中 //
         * cust.setCountry_id(ci.getCountryId() == null ? null : ci.getCountryId().shortValue()); if(cmCust.getCountryId() !=
         * null) { cust.setCountry_id(CommonUtil.IntegerToShort(cmCust.getCountryId())); }
         * cust.setCreate_date(IMSUtil.formatDate(cmCust.getCreateDate()));
         * cust.setValid_date(IMSUtil.formatDate(cmCust.getValidDate())); cust.setGender(ci.getGender() == null ? null :
         * ci.getGender().shortValue()); cust.setCust_type(cmCust.getCustomerClass() == null ? null :
         * cmCust.getCustomerClass().shortValue()); cust.setCust_segment(cmCust.getCustomerSegment() == null ? null :
         * cmCust.getCustomerSegment().shortValue()); // 2011.10.17 added by yangyang cust.setRegion_code(cmCust.getRegionCode()
         * == null ? null : cmCust.getRegionCode().shortValue()); cust.setSub_cust_type(cmCust.getCustomerType() == null ? null :
         * cmCust.getCustomerType().shortValue()); } else { CmParty cp = cc.queryCmPartyByCustId(custId); Long partyId =
         * cp.getPartyId(); // CmOrganization co = pc.queryCmOrgByPartyId(partyId); CmCustomer customer =
         * cc.queryCustomerByPartyId(partyId); if (customer == null) { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.PARTY_FOR_CUSTOMER_NOT_EXIST, partyId); } // 2011-12-15 hunan
         * delete：countryID转移到customer表中 cust.setCountry_id(customer.getCountryId() == null ? null :
         * customer.getCountryId().shortValue()); cust.setCust_id(customer.getCustId());
         * cust.setCreate_date(IMSUtil.formatDate(customer.getCreateDate()));
         * cust.setValid_date(IMSUtil.formatDate(customer.getValidDate())); }
         */

        return new Object[] { sCust };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryCustomerInfoResponse resp = new Do_queryCustomerInfoResponse();
        resp.setCustomer((SCustomer) result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	SQueryCustomerInfoReq req = (SQueryCustomerInfoReq) input[0];
        Long acctId = req.getAcct_id();
        Long userId = req.getUser_id();
        
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().load3hBean(null, acctId, userId, null));
		return list;
    }

}
