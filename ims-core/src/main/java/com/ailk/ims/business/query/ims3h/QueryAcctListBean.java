package com.ailk.ims.business.query.ims3h;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.component.BudgetComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.imsinner.entity.AccountRet;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryRet;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_acctQueryResponse;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReqHolder;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannel;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannelList;

/**
 * @Description 优化后的查询帐户信息接口
 * @author yanchuan
 * @Date 2012-2-16
 * @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
 */
public class QueryAcctListBean extends QueryCustListBean
{
    public Boolean isCaCustInvoice;
    Boolean isCaAcctAttr;
    // caohm5 2012-02-24 add
    // 用来标示查询账户地址和账单地址
    Boolean isCmConactMedium;
    //caohm52012-05-23 add
    //查询账户的支付渠道
    Boolean isCaPaymentChannel;
    Boolean isCaAccount;
    Boolean isCaBuget;
    Boolean isCaParentAcctId;
    UserQuery userQry = null;
    IMS3hBean bean = null;

    public <T extends BaseQueryRet> void query(BaseQueryReqHolder baseReq, List<T> retList) throws BaseException
    {
        AcctQueryReqHolder reqHolder = (AcctQueryReqHolder) baseReq;
        if (reqHolder == null || reqHolder.getAcctReq() == null)
            return;
        AcctQueryReq req = reqHolder.getAcctReq();
        CustQueryReq custReq = reqHolder.getCustReq();
        Boolean isCustomer = null;
        if (custReq != null)
            isCustomer = custReq.getIsCmCustomer();
        if (context != null)
            userQry = context.getComponent(UserQuery.class);
        isCaAccount = req.getIsCaAccount();
        if (isCaAccount == null || isCaAccount != true)
        {
            return;
        }
        if (req.getAcctIds() == null)
            return;
      
        isCaCustInvoice = req.getIsCaCustInvoice();
        isCaAcctAttr = req.getIsCaAcctAttribute();
        isCaBuget = req.getIsCaBuget();
        isCaParentAcctId = req.getIsCaParentAcctId();
        //联系人参数
        isCmConactMedium = req.getIsCmContactMedium();
        //账户支付渠道参数
        isCaPaymentChannel=req.getIsCaPaymentChannel();
        List<Long> ids = req.getAcctIds();
        
        if (CommonUtil.isEmpty(ids))
            return;
        // 本次查询
        for (Long acctId : ids)
        {
            if (!CommonUtil.isValid(acctId))
                continue;
            
          //往session里面设置分表的字段ACCT_ID lijc3
            //TODO @Date 2012-09-01 wukl 设置前先清一把分表参数值，避免影响后续操作
            
            IMSUtil.removeRouterParam();
            IMSUtil.setAcctRouterParam(acctId);
            
            bean = context.get3hTree().load3hBean(null, acctId, null, null);
            
            AccountRet acctRet = new AccountRet();
            
            queryAcct(acctRet, acctId, req);
            // 查询账户下的用户信息
            if (req.getIsQueryUsers() != null && req.getIsQueryUsers() == true)
            {
                List<Long> userIds = userQry.queryUserIdsByAcctID(acctId);
                UserQueryReq userReq = null;
                if (reqHolder.getAcctReq() != null && reqHolder.getAcctReq().getUserReq() != null)
                    userReq = reqHolder.getAcctReq().getUserReq();
                else
                    userReq = new UserQueryReq();
                userReq.setIsCmResource(true);
                req.setIsCaAccount(false);
                userReq.setUserIds(userIds);
                userReq.setPhoneIds(null);
                UserQueryReqHolder userReqHolder = new UserQueryReqHolder();
                userReqHolder.setUserReq(userReq);
                userReqHolder.setAcctReq(req);
                QueryAcctListBean busiBean = null;
                try
                {
                    String beanName = BusiUtil.getMethodNode(9102).getChildByTagName(BusiUtil.TAG_BUSI)
                            .getAttribute(BusiUtil.ATTR_BEAN);
                    busiBean = (QueryUserListBean) Class.forName(beanName).newInstance();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                busiBean.setContext(this.context);
                busiBean.query(userReqHolder, retList);
                Query3hHelper.tranFormAcct(retList, acctRet);
            }
            // 是否向上查询客户
            else
            {
                retList.add((T) acctRet);
                if (isCustomer != null && isCustomer == true)
                {
                    Long custId = bean.getCustId();
                    if (CommonUtil.isValid(custId))
                    {
                        List<Long> custIds = new ArrayList<Long>();
                        custIds.add(custId);
                        reqHolder.getCustReq().setCustIds(custIds);
                        super.query(reqHolder, retList);
                    }
                }
            }

        }

    }
    

    public <T extends BaseQueryRet> void queryAcct(AccountRet acctRet, Long acctId, AcctQueryReq req)
    {
        if (!CommonUtil.isValid(acctId))
            return;

        CaAccount account = bean.getAccount();
        if (account == null)
            return;
        accountTransform(account, acctRet);
        if (isCaCustInvoice != null && isCaCustInvoice == true)
        {
            CaCustomInv custInv = context.getComponent(CustomerQuery.class).queryCaCustInvoice(acctId);
            if(custInv!=null&&custInv.getSummaryDeliver()!=null){
                acctRet.setSummary_deliver(custInv.getSummaryDeliver());
            }
            this.acctAttriTransform(null, custInv, acctRet);
        }
        if (isCaAcctAttr != null && isCaAcctAttr == true)
        {
            CaAccountExt acctAttribute = context.getComponent(AccountQuery.class).queryAcctAttr(acctId);
            this.acctAttriTransform(acctAttribute, null, acctRet);
        }
        if (isCaBuget != null && isCaBuget == true)
        {
            BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
            acctRet.setBudget(CommonUtil.long2Double(bdgCmp.getBudgetValueByAcctId(account.getAcctId())));
        }
        if (isCaParentAcctId != null && isCaParentAcctId == true)
        {
            acctRet.setParent_acct_id(context.getComponent(AccountRelationComponent.class).queryFatherAcctId(account.getAcctId()));
        }
        // caohm5 2012-01-24 add
        // 查询账单地址和账户地址
        if (isCmConactMedium != null && isCmConactMedium == true)
        {
            ContactComponent contactCmp = context.getComponent(ContactComponent.class);
            List<SContact> sContactList = new ArrayList<SContact>();
            List<CmContactMedium> contact_list = contactCmp.queryAcctContactMediums(acctId, null);
            if (CommonUtil.isNotEmpty(contact_list))
            {
                for (CmContactMedium contactMedium :contact_list)
                {
                    SContact sContact = contactCmp.sContactTransfer(contactMedium);
                    if(sContact!=null){
                    	sContactList.add(sContact);
                    }
                }
            }
            this.sContactTransform(acctRet, sContactList);
        }
        //caohm5 add 2012-05-23
        //支付渠道信息
        if(isCaPaymentChannel!=null &&isCaPaymentChannel==true&&ProjectUtil.is_CN_SH()){
        	
        	//上海这边，每个账户只有一种支付方式
        	CaPayChannel caPayChannel = context.getComponent(AccountComponent.class).queryPayChannelByAcctId4Sh(account.getAcctId());
        	CaBankFund caBankFund = context.getComponent(AccountComponent.class).queryCaBankFundByAcctId4Sh(account.getAcctId());
        	if (caPayChannel != null)
        	{
            	//支付渠道实体，用来存储支付渠道List
            	SPayChannelList payChannelList = new SPayChannelList();
            	//支付渠道List，用来存储支付渠道信息
            	List<SPayChannel> sPayChannelList=new ArrayList<SPayChannel>();
            	
            	SPayChannel sPayChannel = new SPayChannel();
            	sPayChannel.setAcct_id(account.getAcctId());
            	sPayChannel.setPayment_type(CommonUtil.IntegerToShort(caPayChannel.getPaymentMethod()));
            	if (caBankFund != null)
            	{
            		sPayChannel.setCard_no(caBankFund.getBankAcctNo());
            		sPayChannel.setBank_id(String.valueOf(caBankFund.getBankId()));
            	}
            	
            	sPayChannelList.add(sPayChannel);
                payChannelList.setSPayChannelList_Item(sPayChannelList.toArray(new SPayChannel[sPayChannelList.size()]));
                //把支付渠道信息赋值给返回结构
                this.transFormPayChannel(acctRet, payChannelList);
            }
        }
    }

	@Override
    public <T extends BaseQueryRet> List createRet()
    {
        return new ArrayList<AccountRet>();
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<AccountRet> retList = (List<AccountRet>) result[0];

        Do_acctQueryResponse resp = new Do_acctQueryResponse();
        resp.setAcctList(retList);
        return resp;
    }

    private void accountTransform(CaAccount ca, AccountRet sAcct)
    {
        if (null == ca)
            return;

        sAcct.setAcct_id(ca.getAcctId());

        if (CommonUtil.isValid(ca.getName()))
            sAcct.setAcct_name(ca.getName());

        if (ca.getAccountStatus() != null)
            sAcct.setStatus(CommonUtil.IntegerToShort(ca.getAccountStatus()));

        if (ca.getForceCycle() != null)
            sAcct.setForce_cycle(CommonUtil.IntegerToShort(ca.getForceCycle()));

        if (ca.getAccountType() != null)
            sAcct.setAcct_type(CommonUtil.IntegerToShort(ca.getAccountType()));

        Date createDate = ca.getCreateDate();
        if (createDate != null)
            sAcct.setCreate_date(DateUtil.formatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        Date validDate = ca.getValidDate();
        if (validDate != null)
            sAcct.setValid_date(DateUtil.formatDate(validDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        Date expireDate = ca.getExpireDate();
        if (expireDate != null)
            sAcct.setExpire_date(DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        if (ca.getAccountClass() != null)
            sAcct.setAcct_class(CommonUtil.IntegerToShort(ca.getAccountClass()));

        if (ca.getAccountSegment() != null)
            sAcct.setAcct_segment(CommonUtil.IntegerToShort(ca.getAccountSegment()));

        // List<BillCycleComplex> billCycleList = context.getComponent(AccountComponent.class).queryBillCycle(ca.getAcctId());
        sAcct.setMeasure_id(ca.getMeasureId());
        sAcct.setProv_code(CommonUtil.IntegerToShort(ca.getProvinceId()));
        sAcct.setRegion_code(CommonUtil.IntegerToShort(ca.getRegionCode()));
        // if (null != ca.getCompanyId())
        // sAcct.setCompany(ca.getCompanyId().shortValue());
        // yanchuan 2012-02-03 增加返回blance_type
        sAcct.setBalance_type(ca.getBalanceType());
        //caohm5 2012-06-15 增加返回cust_id
        sAcct.setCust_id(ca.getCustId());
        if (ca.getCreditSignControl() != null)
            sAcct.setCredit_sign_control(ca.getCreditSignControl());

    }

    private void acctAttriTransform(CaAccountExt acctAttri, CaCustomInv custInv, AccountRet sAcct)
    {
        ExtendParamList paramList = new ExtendParamList();
        List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
        if (null != acctAttri)
        {
            ExtendParam param = new ExtendParam();
            param.setParam_name("next_bill_start_date");
            param.setParam_value(String.valueOf(context.getComponent(AccountComponent.class).queryAcctNextCycleStart(
                    acctAttri.getAcctId())));
            paramArr.add(param);
            if (null != acctAttri.getItem1())
            {
                ExtendParam param1 = new ExtendParam();
                param1.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM);
                param1.setParam_value(acctAttri.getItem1());
                paramArr.add(param1);
            }
            if (null != acctAttri.getItem2())
            {
                ExtendParam param2 = new ExtendParam();
                param2.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL);
                param2.setParam_value(acctAttri.getItem2());
                paramArr.add(param2);
            }
            if (null != acctAttri.getItem3())
            {
                ExtendParam param3 = new ExtendParam();
                param3.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY);
                param3.setParam_value(acctAttri.getItem3());
                paramArr.add(param3);
            }
            if (null != acctAttri.getItem4())
            {
                ExtendParam param4 = new ExtendParam();
                param4.setParam_name(ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID);
                param4.setParam_value(acctAttri.getItem4());
                paramArr.add(param4);
            }

            if (null != acctAttri.getItem5())
            {
                ExtendParam param5 = new ExtendParam();
                param5.setParam_name(ConstantDefine.EXTPARAM_ACCT_FAXBILLTO);
                param5.setParam_value(acctAttri.getItem5());
                paramArr.add(param5);
            }

            if (null != acctAttri.getItem6())
            {
                ExtendParam param6 = new ExtendParam();
                param6.setParam_name(ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT);
                param6.setParam_value(acctAttri.getItem6());
                paramArr.add(param6);
            }

            if (null != acctAttri.getItem7())
            {
                ExtendParam param7 = new ExtendParam();
                param7.setParam_name(ConstantDefine.EXTPARAM_ACCT_CHARGETYPE);
                param7.setParam_value(acctAttri.getItem7());
                paramArr.add(param7);
            }
            if (null != acctAttri.getItem8())
            {
                ExtendParam param8 = new ExtendParam();
                param8.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSBILLTO);
                param8.setParam_value(acctAttri.getItem8());
                paramArr.add(param8);
            }
            if (!CommonUtil.isEmpty(paramArr))
            {
                paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
            }
        }
        if (null != custInv)
        {
            if (null != custInv.getMailCode())
                sAcct.setBill_dispatching(custInv.getMailCode().shortValue());

            if (null != custInv.getDueDay())
                sAcct.setDue_day(CommonUtil.IntegerToShort(custInv.getDueDay()));

            if (null != custInv.getLanguageId())
                sAcct.setBill_language_id(CommonUtil.IntegerToShort(custInv.getLanguageId()));
            // CA_CUSTOMIZED_INVOICE 表去掉company_id字段 yanchuan 2012-02-27
            // if (null != custInv.getCompanyId())
            // sAcct.setCompany(custInv.getCompanyId().shortValue());
            if (null != custInv.getPrintBill())
            {
                ExtendParam param9 = new ExtendParam();
                param9.setParam_name(ConstantDefine.EXTPARAM_ACCT_PRINTBILL);
                param9.setParam_value(String.valueOf(custInv.getPrintBill()));
                paramArr.add(param9);
            }

            if (null != custInv.getMailCode())
            {
                ExtendParam param10 = new ExtendParam();
                param10.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLHANDLINGCODE);
                param10.setParam_value(String.valueOf(custInv.getMailCode()));
                paramArr.add(param10);
            }

            if (null != custInv.getInvoiceType())
            {
                ExtendParam param11 = new ExtendParam();
                param11.setParam_name(ConstantDefine.EXTPARAM_ACCT_INVOICETYPE);
                param11.setParam_value(String.valueOf(custInv.getInvoiceType()));
                paramArr.add(param11);
            }

            if (null != custInv.getBillFormatId())
            {
                ExtendParam param12 = new ExtendParam();
                param12.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLSTYLEID);
                param12.setParam_value(String.valueOf(custInv.getBillFormatId()));
                paramArr.add(param12);
            }

            if (!CommonUtil.isEmpty(paramArr))
            {
                paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
            }
        }
        sAcct.setList_ext_param(paramList);
    }
    /**
     * 联系人信息
     * @author caohm5
     */
    private void sContactTransform(AccountRet sAcct, List<SContact> scontactList)
    {
        if (!CommonUtil.isEmpty(scontactList))
        {

            sAcct.setSAccountContact(scontactList);
        }

    }
    /**
     * 支付渠道信息
     * @author caohm5
     */
    private void transFormPayChannel(AccountRet acctRet,SPayChannelList payChannelList) {
		if(payChannelList!=null&&payChannelList.getSPayChannelList_Item()!=null){
			acctRet.setSPayChannelList(payChannelList);
		}
	}
    
    @Override
    public void setMain(BaseQueryReqHolder reqHolder)
    {
        AcctQueryReqHolder req = (AcctQueryReqHolder) reqHolder;
        req.getAcctReq().setIsCaAccount(true);
    }

}
