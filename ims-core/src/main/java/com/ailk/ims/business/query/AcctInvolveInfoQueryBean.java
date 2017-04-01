package com.ailk.ims.business.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.BudgetComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.ProductCycleComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctInvolveInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQeryAccctInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannel;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannelList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;

/**
 * @Description: 查询账户的相关信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-10-24
 */
public class AcctInvolveInfoQueryBean extends BaseCancelableBusiBean
{

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQeryAccctInfoReq req = (SQeryAccctInfoReq) input[0];
        if (!CommonUtil.isValid(req.getAcct_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }

    }

    @Override
    /**
     * @author caohm5 modify 2012-04-26
     * 账户表增加冗余字段客户编号
     * 账户找客户不走CA_CUSTOMER_REL这层关系
     */
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQeryAccctInfoReq req = (SQeryAccctInfoReq) input[0];
        Long acctId = req.getAcct_id();

        BaseComponent baseComp = context.getComponent(BaseComponent.class);
        Acct3hBean acct3hBean = context.get3hTree().loadAcct3hBean(acctId);
        // 结果
        CaAccount ca = acct3hBean.getAccount();
        if (ca == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
        }else if(ca.getCustId()==null){
        	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, acctId);
        }

        CmCustomer cust = acct3hBean.getCustomer();
        if (cust == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_NOTEXIST, ca.getCustId());
        }

        CaCustomInv custInv = baseComp.querySingle(CaCustomInv.class, new DBCondition(
                CaCustomInv.Field.acctId, ca.getAcctId()));
        CaAccountExt acctAttri = baseComp.querySingle(CaAccountExt.class, new DBCondition(
                CaAccountExt.Field.acctId, ca.getAcctId()));

        SAccount sAcct = new SAccount();
        CATransform(sAcct, ca, custInv, acctAttri);
        sAcct.setCust_id(cust.getCustId());

        SContactList contactList = ConactTransform(ca.getAcctId(), 0L);

        SProductOrderList sprdList = new SProductOrderList();
        ProdTransform(sprdList, ca.getAcctId(), cust.getCustId());

        return new Object[] { sAcct, sprdList, contactList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAcctInvolveInfoResponse response = new Do_queryAcctInvolveInfoResponse();
        if (CommonUtil.isNotEmpty(result))
        {
            SAccount sAcct = (SAccount) result[0];
            SProductOrderList sprdList = (SProductOrderList) result[1];
            SContactList contactList = (SContactList) result[2];

            response.setAccount(sAcct);
            response.setContactList(contactList);
            response.setProductOrderList(sprdList);
        }

        return response;
    }

    @Override
    public void destroy()
    {
    }

    public void ProdTransform(SProductOrderList sprdList, Long acctId, Long custId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.queryProdListByAcctId(acctId);

        List<SProductOrder> prdArr = new ArrayList<SProductOrder>();

        if (CommonUtil.isNotEmpty(prodList))
        {
            for (CoProd prod : prodList)
            {
                SProductOrder sProd = new SProductOrder();
                // 账期
                CoProdBillingCycle billCycle = context.getComponent(ProductCycleComponent.class).queryProdBillingCycle(
                        prod.getProductId(), prod.getObjectId());
                if (null != billCycle)
                {
                    SBillCycle sBillCycle = new SBillCycle();
                    sBillCycle.setCycle_type(billCycle.getCycleType());
                    sBillCycle.setCycle_unit(billCycle.getCycleUnit());
                    // sBillCycle.setFirst_bill_day(Integer.parseInt(DateUtil.formatDate(billCycle.getFirstBillDate(),
                    // DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS)));
                    sProd.setBill_cycle(sBillCycle);
                }

                // sProd.setBillable_acct_id(obj);
                // sProd.setBilling_type(obj);
                if (null != prod.getCreateDate())
                    sProd.setCreate_date(DateUtil.formatDate(prod.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

                sProd.setCust_id(custId);
                if (null != prod.getExpireDate())
                    sProd.setExpire_date(DateUtil.formatDate(prod.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                // sProd.setNextcycle_start_date(obj);

                sProd.setOffer_id(CommonUtil.IntegerToLong(prod.getProductOfferingId()));

                // 产品规格
                List<CoProdCharValue> charValues = prodCmp.queryProdCharValue(prod.getProductId(), prod.getObjectId());
                if (CommonUtil.isNotEmpty(charValues))
                {
                    SProductParamList charValueList = new SProductParamList();
                    List<SProductParam> charValueArr = new ArrayList<SProductParam>();
                    SProductParam charValueParam = new SProductParam();
                    for (CoProdCharValue charValue : charValues)
                    {
                        charValueParam.setParam_id(charValue.getSpecCharId());
                        // charValueParam.setParam_name();
                        charValueParam.setParam_value(charValue.getValue());
                        charValueParam.setExpire_date(DateUtil.formatDate(charValue.getExpireDate(),
                                DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                        charValueParam.setValid_date(DateUtil.formatDate(charValue.getValidDate(),
                                DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                        charValueArr.add(charValueParam);
                    }
                    charValueList.setSProductParamList_Item(charValueArr.toArray(new SProductParam[charValueArr.size()]));
                    sProd.setParam_list(charValueList);
                }

                // sProd.setParent_serv_sProduct(obj);
                // sProd.setPhone_id(obj);

                sProd.setProduct_id(prod.getProductId());
                // sProd.setProd_state();
                // sProd.setProduct_type(prod.get);

                ExtendParamList priceParamList = new ExtendParamList();
                // 价格参数
                List<CoProdPriceParam> prodParamList = prodCmp.queryProdPriceParam(prod.getProductId(), prod.getObjectId());
                if (CommonUtil.isNotEmpty(prodParamList))
                {
                    List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
                    ExtendParam param = new ExtendParam();
                    for (CoProdPriceParam price : prodParamList)
                    {
                        param.setParam_value(String.valueOf(price.getParamId()));
                        param.setParam_name(price.getParamValue());
                        paramArr.add(param);
                    }

                    priceParamList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
                    sProd.setReguid_price_param(priceParamList);
                }

                // sProd.setSo_id(obj);
                // sProd.setUser_id(obj);
                if (null != prod.getValidDate())
                    sProd.setValid_date(DateUtil.formatDate(prod.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                sProd.setValid_type(CommonUtil.IntegerToShort(prod.getProdTypeId()));

                prdArr.add(sProd);
            }
            sprdList.setItem(prdArr.toArray(new SProductOrder[prdArr.size()]));
        }

    }

    public SContactList ConactTransform(Long acctId, Long custId)
    {
        SContactList contactList = new SContactList();
        List<CmContactMedium> mediums = context.getComponent(ContactComponent.class).queryAcctContactMediums(acctId, null);
        if (CommonUtil.isNotEmpty(mediums))
        {
            List<SContact> contactArr = new ArrayList<SContact>();
            SContact contact = null;
            for (CmContactMedium medium : mediums)
            {
                contact = new SContact();
                if (CommonUtil.isNotEmpty(medium.getPostCode()))
                    contact.setPost_code(medium.getPostCode());
                // contact.setOffice_phone(obj);
                if (CommonUtil.isNotEmpty(medium.getMobile()))
                    contact.setMobile_phone(medium.getMobile());

                if (CommonUtil.isNotEmpty(medium.getTelephone()))
                    contact.setHome_phone(medium.getTelephone());

                if (CommonUtil.isNotEmpty(medium.getEmailAddress()))
                    contact.setEmail(medium.getEmailAddress());

                if (CommonUtil.isNotEmpty(medium.getFax()))
                    contact.setFax(medium.getFax());
                // contact.setCountry_id(medium);
                if (null != medium.getContactType())
                    contact.setContact_type(CommonUtil.IntegerToShort(medium.getContactType()));
                contact.setContact_id(medium.getContactMediumId());
                // contact.setBill_send_type(obj);
                if (CommonUtil.isNotEmpty(medium.getExtAddress4()))
                    contact.setAddress4(medium.getExtAddress4());
                if (CommonUtil.isNotEmpty(medium.getExtAddress3()))
                    contact.setAddress3(medium.getExtAddress3());
                if (CommonUtil.isNotEmpty(medium.getExtAddress2()))
                    contact.setAddress2(medium.getExtAddress2());
                if (CommonUtil.isNotEmpty(medium.getExtAddress1()))
                    contact.setAddress1(medium.getExtAddress1());
                if (CommonUtil.isNotEmpty(medium.getAddress()))
                    contact.setAddress(medium.getAddress());
                if (CommonUtil.isValid(medium.getProvinceCode()))
                    contact.setProvince_code(String.valueOf(medium.getProvinceCode()));
                if (CommonUtil.isNotEmpty(medium.getOfficePhone()))
                    contact.setOffice_phone(medium.getOfficePhone());
                if (CommonUtil.isValid(medium.getCountryId()))
                    contact.setCountry_id(medium.getCountryId().longValue());
                contactArr.add(contact);
            }
            contactList.setSContactList_Item(contactArr.toArray(new SContact[contactArr.size()]));
        }
        return contactList;
    }

    public void CATransform(SAccount sAcct, CaAccount ca, CaCustomInv custInv, CaAccountExt acctAttri)
    {
        if (sAcct == null || ca == null)
            return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sAcct.setAcct_id(ca.getAcctId());

        if (CommonUtil.isNotEmpty(ca.getName()))
            sAcct.setAcct_name(ca.getName());

        if (ca.getAccountStatus() != null)
            sAcct.setStatus(CommonUtil.IntegerToShort(ca.getAccountStatus()));

        if (ca.getForceCycle() != null)
            sAcct.setForce_cycle(CommonUtil.IntegerToShort(ca.getForceCycle()));

        if (ca.getAccountType() != null)
            sAcct.setAcct_type(CommonUtil.IntegerToShort(ca.getAccountType()));

        Date createDate = ca.getCreateDate();
        if (createDate != null)
            sAcct.setCreate_date(dateFormat.format(createDate));

        Date validDate = ca.getValidDate();
        if (validDate != null)
            sAcct.setValid_date(dateFormat.format(validDate));

        Date expireDate = ca.getExpireDate();
        if (expireDate != null)
            sAcct.setExpire_date(dateFormat.format(expireDate));

        if (ca.getAccountClass() != null)
            sAcct.setAcct_class(CommonUtil.IntegerToShort(ca.getAccountClass()));

        if (ca.getAccountSegment() != null)
            sAcct.setAcct_segment(CommonUtil.IntegerToShort(ca.getAccountSegment()));

        List<BillCycleComplex> billCycleList = context.getComponent(AccountComponent.class).queryBillCycle(ca.getAcctId());

        // 当前时间账户的账期只有一个
        if (!CommonUtil.isEmpty(billCycleList))
        {
            SBillCycle billCycle = new SBillCycle();
            BillCycleComplex billCycleComplex = (BillCycleComplex) billCycleList.get(0);
            billCycle.setCycle_type(billCycleComplex.getCycleType());
            billCycle.setCycle_unit(billCycleComplex.getCycleUnit());
            billCycle.setFirst_bill_day(billCycleComplex.getFirstBillDate());
            sAcct.setBill_cycle(billCycle);
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
            // sAcct.setCompany(custInv.getCompanyId());
        }

        BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        
        // @Date 2012-08-22 tangjl5 :Story # 49447 将计费的单位转换为ims的单位展现
        double budget = bdgCmp.getBudgetValueByAcctId(ca.getAcctId());
        Integer orgMeasureId = bdgCmp.queryBudgetMeasureID(ca.getAcctId(), EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        Integer imsMeasureId = amountCmp.getImsMeasureId(context.get3hTree().loadAcct3hBean(ca.getAcctId()).getAccount().getMeasureId());
        if (budget != 0)
        {
            budget = amountCmp.parseAmount(budget, orgMeasureId, imsMeasureId);
        }
        
        sAcct.setBudget(budget);
        sAcct.setMeasure_id(imsMeasureId);

        sAcct.setCredit((double)0);//TODO how to get the credit
        sAcct.setList_ext_param(packageAcctParam(acctAttri, custInv));

        // yanchuan 2012-02-03 增加返回blance_type
        sAcct.setBalance_type(ca.getBalanceType());
        // 支付渠道
        SPayChannelList payChannelList = new SPayChannelList();
        List<SPayChannel> payChannelArr = new ArrayList<SPayChannel>();

        List<CaPayChannel> payChannels = context.getComponent(AccountComponent.class).queryPayChannelByAcctId(ca.getAcctId());
        List<CaBankFund> bankFundList = context.getComponent(AccountComponent.class).queryCaBankFundByAcctId(ca.getAcctId());
        if (CommonUtil.isNotEmpty(payChannels))
        {
            for (CaPayChannel caPayChannel : payChannels)
            {
                SPayChannel payChannel = new SPayChannel();
                if (CommonUtil.isNotEmpty(bankFundList))
                {
                    for (CaBankFund caBankFund : bankFundList)
                    {
                        if (caBankFund.getAssetId().longValue() == caPayChannel.getAssetId().longValue()
                                && caBankFund.getFundType() != null)
                        {
                            if (caBankFund.getFundType() == EnumCodeDefine.CARD_TYPE_CREDIT)
                            {
                                payChannel.setCard_no(caBankFund.getBankAcctNo());
                                payChannel.setBank_id(String.valueOf(caBankFund.getBankId()));
                            }
                            else if (caBankFund.getFundType() == EnumCodeDefine.CARD_TYPE_DEBIT)
                            {
                                payChannel.setBank_acct_nbr(caBankFund.getBankAcctNo());
                                payChannel.setCard_issuing_bank(String.valueOf(caBankFund.getBankId()));
                            }
                            break;
                        }
                    }
                }

                payChannel.setAcct_id(ca.getAcctId());
                payChannel.setPayment_type(CommonUtil.IntegerToShort(caPayChannel.getPaymentMethod()));
                payChannelArr.add(payChannel);
            }

            payChannelList.setSPayChannelList_Item(payChannelArr.toArray(new SPayChannel[payChannelArr.size()]));
            sAcct.setPay_channel_list(payChannelList);
        }

        sAcct.setParent_acct_id(context.getComponent(AccountRelationComponent.class).queryFatherAcctId(ca.getAcctId()));
        if (null != ca.getMeasureId())
            sAcct.setMeasure_id(ca.getMeasureId());
        sAcct.setProv_code(CommonUtil.IntegerToShort(ca.getProvinceId()));
        sAcct.setRegion_code(CommonUtil.IntegerToShort(ca.getRegionCode()));
        // if (null != ca.getCompanyId())
        // sAcct.setCompany(ca.getCompanyId().shortValue());
        // if (null != ca.getCountyCode())
        // sAcct.set_countyCode(ca.getCountyCode());
    }

    private ExtendParamList packageAcctParam(CaAccountExt acctAttri, CaCustomInv custInv)
    {
        ExtendParamList paramList = new ExtendParamList();
        List<ExtendParam> paramArr = new ArrayList<ExtendParam>();

        if (null != acctAttri)
        {
            if (null != acctAttri.getItem1())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM);
                param.setParam_value(acctAttri.getItem1());
                paramArr.add(param);
            }
            if (null != acctAttri.getItem2())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL);
                param.setParam_value(acctAttri.getItem2());
                paramArr.add(param);
            }
            if (null != acctAttri.getItem3())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY);
                param.setParam_value(acctAttri.getItem3());
                paramArr.add(param);
            }
            if (null != acctAttri.getItem4())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID);
                param.setParam_value(acctAttri.getItem4());
                paramArr.add(param);
            }

            if (null != acctAttri.getItem5())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_FAXBILLTO);
                param.setParam_value(acctAttri.getItem5());
                paramArr.add(param);
            }

            if (null != acctAttri.getItem6())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT);
                param.setParam_value(acctAttri.getItem6());
                paramArr.add(param);
            }

            if (null != acctAttri.getItem7())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_CHARGETYPE);
                param.setParam_value(acctAttri.getItem7());
                paramArr.add(param);
            }
            if (null != acctAttri.getItem8())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSBILLTO);
                param.setParam_value(acctAttri.getItem8());
                paramArr.add(param);
            }
        }

        if (null != custInv)
        {
            if (null != custInv.getPrintBill())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_PRINTBILL);
                param.setParam_value(String.valueOf(custInv.getPrintBill()));
                paramArr.add(param);
            }

            if (null != custInv.getMailCode())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLHANDLINGCODE);
                param.setParam_value(String.valueOf(custInv.getMailCode()));
                paramArr.add(param);
            }

            if (null != custInv.getInvoiceType())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_INVOICETYPE);
                param.setParam_value(String.valueOf(custInv.getInvoiceType()));
                paramArr.add(param);
            }

            if (null != custInv.getBillFormatId())
            {
                ExtendParam param = new ExtendParam();
                param.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLSTYLEID);
                param.setParam_value(String.valueOf(custInv.getBillFormatId()));
                paramArr.add(param);
            }

            if (!CommonUtil.isEmpty(paramArr))
            {
                paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
            }
        }

        return paramList;
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	SQeryAccctInfoReq req = (SQeryAccctInfoReq) input[0];
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadAcct3hBean(req.getAcct_id()));
    	return list;
    }

}
