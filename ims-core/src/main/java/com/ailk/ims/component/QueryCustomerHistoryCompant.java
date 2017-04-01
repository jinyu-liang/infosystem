package com.ailk.ims.component;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.ims.common.CondTable;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResExt;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.SProductOrderHistory;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.pm.entity.PmPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * 用于查询历史客户资料的方法类
 * 
 * @author zengqm 2012-02-23
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QueryCustomerHistoryCompant extends BaseComponent
{
    /**
     * @Description: 根据传入userid,hisDate查询历史用户
     * @author : zengqm
     * @date : 2012-2-23
     */
    public CmResource queryUserHistoryByUserId(Long userId, Date hisDate)
    {
        CmResource res = null;
        if (userId != null)
        {
            res = querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(
                    CmResource.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmResource.Field.expireDate, hisDate,
                    Operator.GREAT));
        }
        else
        {
            throw new IMSException("userId  is null");
        }
        return res;
    }

    /**
     * @Description: 根据传入phoneId,hisDate查询历史用户
     * @author : zengqm
     * @date : 2012-2-23
     */
    public CmResource queryUserHistoryByPhoneId(String phoneId, Date hisDate)
    {
        CmResource res = null;

        if (CommonUtil.isValid(phoneId))
        {
            CmResIdentity iden = querySingle(CmResIdentity.class, new DBCondition(CmResIdentity.Field.identity, phoneId),
                    new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE),
                    new DBCondition(CmResIdentity.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                            CmResIdentity.Field.expireDate, hisDate, Operator.GREAT));
            if (iden == null)
            {
                res = null;
            }
            else
            {
                res = querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, iden.getResourceId()),
                        new DBCondition(CmResource.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                                CmResource.Field.expireDate, hisDate, Operator.GREAT));
            }
        }
        else
        {
            throw new IMSException("phoneId is null");
        }
        return res;
    }

    /**
     * zengqm
     * 
     * @param userId
     * @param iden
     * @param idenType
     * @param hisDate
     * @return
     * @throws IMSException
     */
    public CmResIdentity queryResIdentityHistory(Long userId, String iden, Date hisDate) throws IMSException
    {
        if (userId == null && !CommonUtil.isValid(iden))
            return null;
        List<DBCondition> conList = new ArrayList<DBCondition>();
        if (userId != null)
            conList.add(new DBCondition(CmResIdentity.Field.resourceId, userId));
        if (CommonUtil.isValid(iden))
            conList.add(new DBCondition(CmResIdentity.Field.identity, iden));

        conList.add(new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
        conList.add(new DBCondition(CmResIdentity.Field.validDate, hisDate, Operator.LESS));
        conList.add(new DBCondition(CmResIdentity.Field.expireDate, hisDate, Operator.GREAT));

        return querySingle(CmResIdentity.class, conList.toArray(new DBCondition[conList.size()]));
    }

    /**
     * @author zengqm 根据帐户id查询历史帐户信息
     * @param acctId 2012-2-21
     * @return
     */
    public CaAccount queryAccountHistoryById(Long acctId, Date hisDate)
    {
        return querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId), new DBCondition(
                CaAccount.Field.validDate, hisDate, Operator.LESS), new DBCondition(CaAccount.Field.expireDate, hisDate,
                Operator.GREAT));
    }

    /**
     * @author zengqm 根据帐户id查询历史帐户信息
     * @param acctId 2012-2-21
     * @return
     */
    public CaCustomerRel queryCaCustomerRelByAcctId(Long acctId, Date hisDate)
    {
        return querySingle(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.acctId, acctId), new DBCondition(
                CaAccount.Field.validDate, hisDate, Operator.LESS), new DBCondition(CaAccount.Field.expireDate, hisDate,
                Operator.GREAT));
    }

    /**
     * 查询历史客户信息 zengqm
     * 
     * @param custId
     * @param hisDate
     * @return2012-02-23
     */
    public CmCustomer queryCustomerHistory(Long custId, Date hisDate)
    {
        return querySingle(CmCustomer.class, new DBCondition(CmCustomer.Field.custId, custId), new DBCondition(
                CmCustomer.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmCustomer.Field.expireDate, hisDate,
                Operator.GREAT));
    }

    /**
     * zengqm
     * 
     * @param custId
     * @param hisDate
     * @return2012-02-23
     */
    public CmPartyIdentity queryCmPartyIdentity(Long custId, Date hisDate)
    {
        CmPartyIdentity identity = super.querySingle(CmPartyIdentity.class,
                new DBCondition(CmPartyIdentity.Field.partyId, custId), new DBCondition(CmPartyIdentity.Field.validDate, hisDate,
                        Operator.LESS), new DBCondition(CmPartyIdentity.Field.expireDate, hisDate, Operator.GREAT));
        return identity;
    }

    /**
     * zengqm 2012-02-23
     * 
     * @param partyId
     * @param hisDate
     * @return
     */
    public CmIndividualName queryCmIndividualName(Long partyId, Date hisDate)
    {
        CmIndividualName cmIndividualName = super.querySingle(CmIndividualName.class, new DBCondition(
                CmIndividualName.Field.partyId, partyId), new DBCondition(CmIndividualName.Field.validDate, hisDate,
                Operator.LESS), new DBCondition(CmIndividualName.Field.expireDate, hisDate, Operator.GREAT));
        return cmIndividualName;
    }

    /**
     * zengqm 2012-02-23
     * 
     * @param partyId
     * @param hisDate
     * @return
     */
    public CmIndividual queryCmIndividual(Long partyId, Date hisDate)
    {
        CmIndividual cmIndividual = super.querySingle(CmIndividual.class, new DBCondition(CmIndividual.Field.partyId, partyId),
                new DBCondition(CmIndividual.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CmIndividual.Field.expireDate, hisDate, Operator.GREAT));
        return cmIndividual;
    }

    /**
     * zengqm 2012-02-23
     * 
     * @param custId
     * @param hisDate
     * @return
     */
    public CmOrgName queryCmOrgName(Long custId, Date hisDate)
    {
        CmOrgName cmOrgName = super.querySingle(CmOrgName.class, new DBCondition(CmOrgName.Field.partyId, custId),
                new DBCondition(CmOrgName.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmOrgName.Field.expireDate,
                        hisDate, Operator.GREAT));
        return cmOrgName;
    }

    public CaAccountExt queryCaAccountAttribute(Long acctId, Date hisDate)
    {
        CaAccountExt caAccountAttribute = super.querySingle(CaAccountExt.class, new DBCondition(
                CaAccountExt.Field.acctId, acctId), new DBCondition(CaAccountExt.Field.validDate, hisDate,
                Operator.LESS), new DBCondition(CaAccountExt.Field.expireDate, hisDate, Operator.GREAT));
        return caAccountAttribute;
    }

    public CaCustomerRel queryCaCustomerRel(Long acctId, Date hisDate)
    {
        CaCustomerRel caCustomerRel = super.querySingle(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.acctId, acctId),
                new DBCondition(CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE),
                new DBCondition(CaCustomerRel.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CaCustomerRel.Field.expireDate, hisDate, Operator.GREAT));
        return caCustomerRel;
    }

    public CaCustomInv queryCaCustomizedInvoice(Long acctId, Date hisDate)
    {
        CaCustomInv caCustomInv = super.querySingle(CaCustomInv.class, new DBCondition(
                CaCustomInv.Field.acctId, acctId), new DBCondition(CaCustomInv.Field.validDate, hisDate,
                Operator.LESS), new DBCondition(CaCustomInv.Field.expireDate, hisDate, Operator.GREAT));
        return caCustomInv;
    }

    public CaAccountRes queryCaAccountRes(Long resId, Date hisDate)
    {
        CaAccountRes caAccountRes = super.querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, resId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING),
                new DBCondition(CaAccountRes.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CaAccountRes.Field.expireDate, hisDate, Operator.GREAT));
        return caAccountRes;
    }

    public CmResLifecycle queryCmResLifecycle(Long resId, Date hisDate)
    {
        CmResLifecycle cmResLifecycle = super.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId,
                resId), new DBCondition(CmResLifecycle.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                CmResLifecycle.Field.expireDate, hisDate, Operator.GREAT));
        return cmResLifecycle;
    }

    /**
     * zengqm 2012-02-23 通过acctId查询CmResource
     * 
     * @param acctId
     * @param hisDate
     * @return
     */
    public List<CmResource> queryCmResourceByAcctId(Long acctId, Date hisDate)
    {
        List<CaAccountRes> list = super.query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CaAccountRes.Field.expireDate, hisDate, Operator.GREAT));

        Set<Long> set = new HashSet<Long>();
        List<CmResource> returnList = new ArrayList<CmResource>();
        for (CaAccountRes caAccountRes : list)
        {

            if (set.contains(caAccountRes.getResourceId()))
            {// 去重
                continue;
            }
            else
            {
                set.add(caAccountRes.getResourceId());
            }
            CmResource res = queryUserHistoryByUserId(caAccountRes.getResourceId(), hisDate);
            if (res != null)
            {
                returnList.add(res);
            }

        }
        return returnList;
    }

    /**
     * zengqm 2012-02-23
     * 
     * @param custId
     * @param hisDate
     * @return
     */
    public List<CaAccount> queryCaAccountByCustId(Long custId, Date hisDate)
    {
        List<CaCustomerRel> list = super.query(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.custId, custId),
                new DBCondition(CaCustomerRel.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CaCustomerRel.Field.expireDate, hisDate, Operator.GREAT));

        Set<Long> set = new HashSet<Long>();
        List<CaAccount> returnList = new ArrayList<CaAccount>();
        for (CaCustomerRel caCustomerRel : list)
        {
            if (set.contains(caCustomerRel.getAcctId()))
            {// 去重
                continue;
            }
            else
            {
                set.add(caCustomerRel.getAcctId());
            }
            CaAccount caAccount = queryAccountHistoryById(caCustomerRel.getAcctId(), hisDate);
            if (caAccount != null)
            {
                returnList.add(caAccount);
            }

        }
        return returnList;
    }

    /**
     * 查询用户的所有产品
     * 
     * @Description
     * @Author zengqm
     * @param userId 用户或帐户id查询用户或帐户订购的产品
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CoProd> queryUserNotMainProducts(Long userIdOrAcctId, Date hisDate)
    {
        List<CoProd>  prod_list = this.query(CoProd.class,new DBCondition(CoProd.Field.objectId,userIdOrAcctId),
                                                          new DBCondition(CoProd.Field.validDate,hisDate,Operator.LESS),
                                                          new DBCondition(CoProd.Field.expireDate,hisDate, Operator.GREAT));
        return prod_list;
    }

    /**
     * 返回记录列表，可能为空
     * 
     * @author wangjt 2012-1-10
     */
    public List<CoProd> getResultList(ResultTable<CoProd> resultTable, Date hisDate)
    {
        List<CondTable> condTableList = resultTable.getCondTableList();
        if (resultTable.getCondTableList().isEmpty())
        {
            throw new RuntimeException("please invoke ResultTable.addCondTable() method first");
        }
        Set keyFieldValueSet = null;
        for (int i = 0; i < condTableList.size(); i++)
        {
            CondTable condTable = condTableList.get(i);
            if (keyFieldValueSet != null)
            {
                condTable.getConds().add(new DBCondition(condTable.getKeyField(), keyFieldValueSet, Operator.IN));

            }

            List list = super.query(condTable.getTableClass(),
                    condTable.getConds().toArray(new DBCondition[condTable.getConds().size()]));
            if (list == null || list.isEmpty())
            {
                return null;
            }
            keyFieldValueSet = new HashSet();
            for (int j = 0; j < list.size(); j++)
            {
                Object object = list.get(j);
                Object fieldValue = getFieldValue(object, condTable.getKeyField().name());
                keyFieldValueSet.add(fieldValue);
            }
        }

        resultTable.getConds().add(new DBCondition(resultTable.getIdInField(), keyFieldValueSet, Operator.IN));
        resultTable.getConds().add(new DBCondition(CoProd.Field.validDate, hisDate, Operator.LESS));
        resultTable.getConds().add(new DBCondition(CoProd.Field.expireDate, hisDate, Operator.GREAT));
        List<CoProd> list = super.query(IMSUtil.getClassByField(resultTable.getIdInField()),
                resultTable.getConds().toArray(new DBCondition[resultTable.getConds().size()]));
        if (list == null || list.isEmpty())
        {
            return null;
        }
        return list;
    }

    /**
     * 自定义方法，不抛异常
     */
    private Object getFieldValue(Object obj, String fieldName)
    {
        try
        {
            return ClassUtil.getPropertyValue(obj, fieldName);
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将内部实体CoProd转换为接口实体SProductOrder返回给外部接口
     * 
     * @param productOrder
     * @param coProd
     * @author zengqm 2012-02-24
     */
    public SProductOrderHistory sProductOrderTransformHistory(CoProd coProd, Date hisDate)
    {
        SProductOrderHistory productOrder = new SProductOrderHistory();
        if (coProd == null)
        {
            return null;
        }
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        productOrder.setProduct_id(coProd.getProductId());// 产品订购实例编号
        productOrder.setOffer_id(CommonUtil.IntegerToLong(coProd.getProductOfferingId()));// 产品编号
        // hunan 增加如果是主产品 product_type= 2；
        if (coProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        {
            productOrder.setProduct_type(EnumCodeDefine.PROD_TYPE_MAIN);
        }
        else
        {
            productOrder.setProduct_type(coProd.getProdTypeId());

        }
        productOrder.setBilling_type(CommonUtil.IntegerToShort(coProd.getBillingType()));
        productOrder.setProd_state(CommonUtil.IntegerToShort(coProd.getLifecycleStatusId()));
        Date createDate = coProd.getCreateDate();
        if (createDate != null)
            productOrder.setCreate_date(DateUtil.formatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date validDate = coProd.getValidDate();
        if (validDate != null)
            productOrder.setValid_date(DateUtil.formatDate(validDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date expireDate = coProd.getExpireDate();
        if (expireDate != null)
            productOrder.setExpire_date(DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        List<CoProdCharValue> charValues = this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                CoProdCharValue.Field.validDate, hisDate, Operator.LESS), new DBCondition(CoProdCharValue.Field.expireDate,
                hisDate, Operator.GREAT));

        // _product_param_list CoProdCharValue表中
        SProductParamList paramList = new SProductParamList();
        List<SProductParam> params = new ArrayList<SProductParam>();
        for (CoProdCharValue val : charValues)
        {
            if (val == null)
            {
                continue;
            }
            SProductParam param = new SProductParam();
            param.setParam_id(val.getSpecCharId());
            param.setParam_value(String.valueOf(val.getValue()));
            params.add(param);
        }
        paramList.setSProductParamList_Item(params.toArray(new SProductParam[params.size()]));
        productOrder.setParam_list(paramList);

        // 2011-12-07 如果二次议价存在，也要返回
        CoProdPriceParam dm = new CoProdPriceParam();
        dm.setProductId(coProd.getProductId());
        List<CoProdPriceParam> priceParams = this.query(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdPriceParam.Field.objectId, coProd.getObjectId()), new DBCondition(
                CoProdPriceParam.Field.validDate, hisDate, Operator.LESS), new DBCondition(CoProdPriceParam.Field.expireDate,
                hisDate, Operator.GREAT));

        List<ExtendParam> extendParams = new ArrayList<ExtendParam>();
        if (priceParams != null)
        {
            for (CoProdPriceParam param : priceParams)
            {
                if (param == null)
                {
                    continue;
                }
                ExtendParam extendParam = new ExtendParam();
                extendParam.setParam_name(String.valueOf(param.getParamId()));
                extendParam.setParam_value(param.getParamValue());
                extendParams.add(extendParam);
            }
            ExtendParamList extendParamList = new ExtendParamList();
            extendParamList.setExtendParamList_Item(extendParams.toArray(new ExtendParam[extendParams.size()]));
            productOrder.setReguid_price_param(extendParamList);
        }

        if (CommonUtil.isValid(coProd.getPricingPlanId()))
        {
            PmPricingPlan plan = querySingle(PmPricingPlan.class, new DBCondition(PmPricingPlan.Field.pricingPlanId, coProd.getPricingPlanId()));

            if (plan != null)
            {
                productOrder.setPrice_description(plan.getPricingPlanDesc());
            }
        }
        PmProductOffering pmProductOffering = this.querySingle(PmProductOffering.class, new DBCondition(
                PmProductOffering.Field.productOfferingId, coProd.getProductOfferingId()), new DBCondition(
                PmProductOffering.Field.validDate, hisDate, Operator.LESS), new DBCondition(PmProductOffering.Field.expireDate,
                hisDate, Operator.GREAT));
        if (productOrder != null)
        {
            productOrder.setOffering_name(pmProductOffering.getName());
        }

        return productOrder;
    }

    /**
     * 将内部实体CaAccount转换为接口实体SAccount返回给外部接口
     * 
     * @Date 2012-2-21 zengqm : 只针对历史客户信息查询接口
     */
    public SAccount sAccountTransformHistory(CaAccount ca, CaCustomInv custInv, CaAccountExt acctAttri,
            CaCustomerRel rel, Date hisDate)
    {
        SAccount sAcct = new SAccount();
        if (ca == null)
        {
            return null;
        }
//        if (rel != null && rel.getCustId() != null)
//        {
//            sAcct.setCust_id(rel.getCustId());
//        }
        //caohm5 modify 2012-04-27
        //客户编号用户CaAccount中的字段
        if(ca!=null&& ca.getCustId()!=null){
        	sAcct.setCust_id(ca.getCustId());
        }
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
        }

        sAcct.setBudget(CommonUtil.long2Double(getBudgetValueByAcctIdHistory(ca.getAcctId(), hisDate)));
        sAcct.setList_ext_param(packageAcctParam(acctAttri, custInv, ca));

        CaAccountRel caRel = querySingle(CaAccountRel.class, new DBCondition(CaAccountRel.Field.relGroupId, ca.getAcctId()),
                new DBCondition(CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_GCA_RELATION), new DBCondition(
                        CaAccountRel.Field.validDate, hisDate, Operator.LESS), new DBCondition(CaAccountRel.Field.expireDate,
                        hisDate, Operator.GREAT));
        if (caRel == null)
        {
            sAcct.setParent_acct_id(null);
        }
        else
        {
            sAcct.setParent_acct_id(caRel.getGroupId());
        }

        if (null != ca.getMeasureId())
            sAcct.setMeasure_id(ca.getMeasureId());
        sAcct.setProv_code(CommonUtil.IntegerToShort(ca.getProvinceId()));
        sAcct.setRegion_code(CommonUtil.IntegerToShort(ca.getRegionCode()));

        // yanchuan 2012-02-03 增加返回blance_type
        sAcct.setBalance_type(ca.getBalanceType());
        return sAcct;
    }

    /**
     * @Date 2011-11-21 hunan :提取yindm写的方法
     */
    private ExtendParamList packageAcctParam(CaAccountExt acctAttri, CaCustomInv custInv, CaAccount ca)
    {
        ExtendParamList paramList = new ExtendParamList();
        List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
        ExtendParam param = new ExtendParam();

        param.setParam_name("next_bill_start_date");
        param.setParam_value(IMSUtil.formatDate(context.getComponent(AccountComponent.class).queryAcctNextCycleStart(
                ca.getAcctId())));
        paramArr.add(param);

        if (null == acctAttri)
        {
            return null;
        }
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

        if (null != custInv)
        {
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

        return paramList;
    }

    /**
     * 查询用户的预算值，没有预算返回0(付费模式为2)
     */
    public long getBudgetValueByUserIdHistory(Long userId, Date hisDate)
    {
        return getBudgetValueHistory(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV, hisDate);
    }

    private long getBudgetValueHistory(Long objectId, Integer objectType, Date hisDate)
    {

        ResultTable<CoProdCharValue> resultTable = new ResultTable<CoProdCharValue>(CoProdCharValue.Field.productId,
                new DBCondition(CoProdCharValue.Field.objectId, objectId), new DBCondition(CoProdCharValue.Field.specCharId,
                        SpecCodeDefine.BUDGET_AMOUNT), new DBCondition(CoProdCharValue.Field.validDate, hisDate, Operator.LESS),
                new DBCondition(CoProdCharValue.Field.expireDate, hisDate, Operator.GREAT));
        resultTable.addCondTable(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.BUDGET),
                new DBCondition(CoProd.Field.objectId, objectId),  new DBCondition(CoProd.Field.objectType, objectType),
                new DBCondition(CoProd.Field.billingType,EnumCodeDefine.USER_PAYMODE_HYBRID), new DBCondition(CoProd.Field.validDate, hisDate, Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, hisDate, Operator.GREAT));// 混合模式预算

        List<CoProdCharValue> resultList = context.getComponent(ResultComponent.class).getResultList(resultTable);
        if (CommonUtil.isEmpty(resultList))
        {
            return 0;
        }
        CoProdCharValue coProdCharValue = resultList.get(0);

        String budgetValue = coProdCharValue.getValue();

        if (null != budgetValue)
        {
            return CommonUtil.string2Long(budgetValue);
        }
        return 0;
    }

    /**
     * @Description: 查询多个客户下的联系方式列表
     * @param custIds * @Date 2011-12-06
     * @author hunan
     * @return
     */
    public SContactList queryCustomerContactsByCustIdsHistory(List<Long> custIds, Date hisDate)
    {
        SContactList sContactList = new SContactList();
        if (CommonUtil.isEmpty(custIds))
        {
            return sContactList;
        }
        List<String> custIdStrList = new ArrayList<String>();
        for (Long id : custIds)
        {
            if (id == null)
            {
                continue;
            }
            custIdStrList.add(String.valueOf(id));
        }
        String[] custIdArr = custIdStrList.toArray(new String[custIdStrList.size()]);
        List<CmContactMedium> medis = this.query(CmContactMedium.class, new DBCondition(CmContactMedium.Field.objectId,
                custIdArr, Operator.IN),new DBCondition(CmContactMedium.Field.objectType,EnumCodeDefine.CUST_CONTACT_TYPE), new DBCondition(CmContactMedium.Field.validDate, hisDate, Operator.LESS),
                new DBCondition(CmContactMedium.Field.expireDate, hisDate, Operator.GREAT));
        List<SContact> contacts = new ArrayList<SContact>();
        for (CmContactMedium dmMedi : medis)
        {
            SContact cont = context.getComponent(ContactComponent.class).sContactTransfer(dmMedi);
            contacts.add(cont);
        }
        sContactList.setSContactList_Item(contacts.toArray(new SContact[contacts.size()]));
        return sContactList;
    }

    /**
     * 将内部实体CmResource/CmResIdentity转换为接口实体SUser返回给外部接口
     * 
     * @param zengqm
     * @Date 2012-2-22
     */
    public SUser sUserTransformHistory(CmResource res, String phoneId, CaAccountRes acRes, CmCustomer cust, CaAccount ca,
            CmResLifecycle lifeCycle, Date hisDate)
    {
        SUser sUser = new SUser();
        if (res == null)
        {
            return null;
        }

        // imsi+imei...
        List<CmResIdentity> resIdentityResult = queryResIdentitysByUserIdHistory(res.getResourceId(), hisDate);
        if (CommonUtil.isNotEmpty(resIdentityResult))
        {
            for (CmResIdentity resIdenOther : resIdentityResult)
            {
                // imsi
                if (resIdenOther.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)
                {
                    sUser.setImsi(resIdenOther.getRelIdentity());
                }

                if (null != resIdenOther.getResourcePasswd())
                    sUser.setPassword(resIdenOther.getResourcePasswd());
            }
        }
        CmIdentityVsImei resImei = this.getCmIdentityVsImeiHistory(res.getResourceId(), hisDate);
        if (null != resImei)
        {
            //sUser.setIMEI(resImei.getImei());
        }

        // other fields
        sUser.setRegion_code(CommonUtil.IntegerToShort(res.getRegionCode()));
        sUser.setPhone_id(phoneId);// phone number
        sUser.setUser_id(res.getResourceId());

        if (null != res.getResSegment())
            sUser.setUser_segment(CommonUtil.IntegerToShort(res.getResSegment()));

        if (null != res.getBillingType())
            sUser.setPayment_mode(CommonUtil.IntegerToShort(res.getBillingType()));
        if (null != res.getBrandId())
            sUser.setBrand(CommonUtil.IntegerToShort(res.getBrandId()));
        if (null != res.getListeningLanguage())
            sUser.setIvr_language(CommonUtil.IntegerToShort(res.getListeningLanguage()));
        if (null != res.getReadingLanguage())
            sUser.setSms_language(CommonUtil.IntegerToShort(res.getReadingLanguage()));
        if (null != res.getWritingLanguage())
            sUser.setUssd_language(CommonUtil.IntegerToShort(res.getWritingLanguage()));

        Date createDate = res.getCreateDate();
        if (createDate != null)
            sUser.setCreate_date(DateUtil.getFormatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date expireDate = res.getExpireDate();
        if (expireDate != null)
            sUser.setExpire_date(DateUtil.getFormatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        if (null != res.getActiveDate())
        {

            sUser.setActive_date(DateUtil.getFormatDate(res.getActiveDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            // 2011-10-28 hunan 修改: 返回的用户validDate是cm_resource.active_date
            sUser.setValid_date(DateUtil.getFormatDate(res.getActiveDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        // 将获取支付帐户实体替换为获取支付帐户id yanchuan 2012-02-09
        // CaAccountRes caRes = context.getComponent(AccountComponent.class).queryBillAcctResByUserID(res.getResourceId());
        // if (!CommonUtil.isEmpty(caRes))
        // {
        // sUser.setBillable_acct_id(caRes.getAcctId());
        // }
        Long billAcctId = queryBillAcctIdByUserIdHistory(res.getResourceId(), hisDate);
        if (CommonUtil.isValid(billAcctId))
        {
            sUser.setBillable_acct_id(billAcctId);
        }
        
        // @Date 2012-08-22 tangjl5 :Story # 49447 将计费的measureId转换为ims的measureId返回给Bss broker
        long budget = getBudgetValueByUserIdHistory(res.getResourceId(), hisDate);
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Integer imsMeasureId = amountCmp.getImsMeasureId(context.get3hTree().loadUser3hBean(res.getResourceId()).getAccount().getMeasureId());
        if (budget != 0)
        {
            Integer orgMeasureId = context.getComponent(BudgetComponent.class).queryBudgetMeasureID(res.getResourceId(), EnumCodeDefine.USER_PAYMODE_PREPAID);
            budget = amountCmp.parseAmount((double)budget, orgMeasureId, imsMeasureId).longValue();
        }
        sUser.setMeasure_id(imsMeasureId);
        sUser.setBudget((double) budget);

        if (null != res.getCountyCode())
            sUser.setCounty_code(CommonUtil.IntegerToShort(res.getCountyCode()));
        // 2011-11-24 zengxr no credit return for query customer information.
        // long creditLimit = bdgCmp.getUserCreditLimit(acRes.getAcctId(), res.getResourceId());
        // sUser.setCredit((int)creditLimit);
        if (cust != null)
        {
            sUser.setCust_id(cust.getCustId());
        }

        CmResExt resExt = this.getCmResExtHistory(res.getResourceId(), hisDate);
        if (null != resExt)
        {
            ExtendParamList paramList = new ExtendParamList();
            List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
            ExtendParam param = new ExtendParam();
            if (null != resExt.getProp1())
            {
                param.setParam_name(ConstantDefine.EXTPARAM_USER_PHFLAG);
                param.setParam_value(resExt.getProp1());
                paramArr.add(param);
            }
            if (null != resExt.getProp2())
            {
                param.setParam_name(ConstantDefine.EXTPARAM_USER_VOICEMAILFLAG);
                param.setParam_value(resExt.getProp2());
                paramArr.add(param);
            }
            if (null != resExt.getProp3())
            {
                param.setParam_name(ConstantDefine.EXTPARAM_USER_USSDCALLBACKFLAG);
                param.setParam_value(resExt.getProp3());
                paramArr.add(param);
            }
            if (!CommonUtil.isEmpty(paramArr))
            {
                paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
            }
            sUser.setList_ext_param(paramList);
        }
        // 修改主产品判断ljc
        // List<CoProd> prods = context.getComponent(ProductComponent.class).queryProdListByUserId(res.getResourceId(),
        // SpecCodeDefine.MAIN_PRODUCT);
        // if (!CommonUtil.isEmpty(prods))
        // {
        // sUser.setMain_promotion(prods.get(0).getProductOfferingId());
        // }
        CoProd dmProd = queryPrimaryProductByUserIdHistory(res.getResourceId(), hisDate);
        if (dmProd != null)
        {
            sUser.setMain_promotion(dmProd.getProductOfferingId());
        }
        if (null != res.getRegionCode())
            sUser.setRegion_code(CommonUtil.IntegerToShort(res.getRegionCode()));

        if (null != acRes.getTitleRoleId())
            sUser.setTitle_role_id(CommonUtil.IntegerToShort(acRes.getTitleRoleId()));
        if (null != res.getResourceSpecId())
            sUser.setUser_type(CommonUtil.IntegerToShort(res.getResourceSpecId()));
        if (ca != null && CommonUtil.isValid(ca.getAcctId()))
        {
            sUser.setAcct_id(ca.getAcctId());
        }
        if (lifeCycle != null)
        {
            sUser.setStatus(lifeCycle.getSts().shortValue());
        }
        // sUser.setStatus(obj);
        // sUser.setMNP_home_area(obj);
        // sUser.setOs_status(obj);
        // sUser.setDevice_type(obj);
        // sUser.setCompany();
        // sUser.setBill_cycle(re);
        return sUser;
    }

    /**
     * @Description: 查询用户的附加信息
     * @author: zengqm
     * @Date: 2011-2-22
     */
    public CmResExt getCmResExtHistory(Long userId, Date hisDate)
    {
        // 查询CM_RES_EXT表
        CmResExt cmResExt = querySingle(CmResExt.class, new DBCondition(CmResExt.Field.resourceId, userId), new DBCondition(
                CmResExt.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmResExt.Field.expireDate, hisDate,
                Operator.GREAT));
        return cmResExt;
    }

    /**
     * zengqm 2012-2-22
     * 
     * @param userId
     * @param hisDate
     * @return
     */
    public List<CmResIdentity> queryResIdentitysByUserIdHistory(Long userId, Date hisDate)
    {
        return query(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, userId), new DBCondition(
                CmResIdentity.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmResIdentity.Field.expireDate, hisDate,
                Operator.GREAT));
    }

    /**
     * @author: zengqm
     * @Date: 2012-2-22
     */
    public CmIdentityVsImei getCmIdentityVsImeiHistory(Long userId, Date hisDate) throws IMSException
    {
        CmIdentityVsImei update = new CmIdentityVsImei();
        update.setResourceId(userId);
        CmIdentityVsImei result = querySingle(CmIdentityVsImei.class,
                new DBCondition(CmIdentityVsImei.Field.resourceId, update.getResourceId()), new DBCondition(
                        CmIdentityVsImei.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CmIdentityVsImei.Field.expireDate, hisDate, Operator.GREAT));
        return result;
    }

    /**
     * 查询账户的预算值，没有预算返回0(付费模式为2),针对历史客户信息查询 zengqm 2012-02-22
     */
    public long getBudgetValueByAcctIdHistory(Long acctId, Date hisDate)
    {
        long objectId = acctId;
        long objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        ResultTable<CoProdCharValue> resultTable = new ResultTable<CoProdCharValue>(CoProdCharValue.Field.productId,
                new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.BUDGET_AMOUNT), new DBCondition(
                        CoProdCharValue.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CoProdCharValue.Field.expireDate, hisDate, Operator.GREAT));

        resultTable.addCondTable(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.BUDGET),
                new DBCondition(CoProd.Field.objectId, objectId),  new DBCondition(CoProd.Field.objectType, objectType),
                new DBCondition(CoProd.Field.billingType, EnumCodeDefine.USER_PAYMODE_HYBRID), 
                new DBCondition(CoProd.Field.validDate, hisDate, Operator.LESS), new DBCondition(CoProd.Field.expireDate, hisDate,
                        Operator.GREAT));// 混合模式预算

        List<CoProdCharValue> resultList = context.getComponent(ResultComponent.class).getResultList(resultTable);
        if (CommonUtil.isEmpty(resultList))
        {
            return 0;
        }
        CoProdCharValue coProdCharValue = resultList.get(0);

        String budgetValue = coProdCharValue.getValue();

        if (null != budgetValue)
        {
            return CommonUtil.string2Long(budgetValue);
        }
        return 0;
    }

    /**
     * 根据用户ID查询帐户关联设备定义表
     * 
     * @author zengqm 2012-02-22
     */
    public CaAccountRes queryAccountResByUserIDHistory(Long userId, Date hisDate) throws IMSException
    {
        CaAccountRes acctRes = querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING),
                new DBCondition(CaAccountRes.Field.validDate, hisDate, Operator.LESS), new DBCondition(
                        CaAccountRes.Field.expireDate, hisDate, Operator.GREAT));
        return acctRes;
    }

    /**
     * 查询用户的支付账户id zengqm 2012-2-22
     */
    public Long queryBillAcctIdByUserIdHistory(Long userId, Date hisDate) throws IMSException
    {
        CaAccountRes acctRes = this.queryAccountResByUserIDHistory(userId, hisDate);
        return acctRes == null ? null : acctRes.getResAcctId();
    }

    /**
     * 将内部实体CmCustomer转换为接口实体SCustomer返回给外部接口 zengqm
     * 
     * @Date 2012-02-21 这个方法只针对查询历史客户信息的接口。
     */
    public SCustomer sCustTransformHistory(CmCustomer cust, CmIndividual individual, CmIndividualName individualName,
            CmOrgName orgName, CmPartyIdentity partyIdentity, Date hisDate)
    {
        SCustomer sCust = new SCustomer();
        if (cust == null)
        {
            return sCust;
        }
        sCust.setCust_id(cust.getCustId());
        sCust.setCust_status((short) EnumCodeDefine.CUSTOMER_STS_ACTIVE);

        if (null != cust.getCustomerClass())
            sCust.setCust_type(CommonUtil.IntegerToShort(cust.getCustomerClass()));

        if (null != cust.getCustomerType())
            sCust.setSub_cust_type(CommonUtil.IntegerToShort(cust.getCustomerType()));

        if (null != cust.getCustomerSegment())
            sCust.setCust_segment(CommonUtil.IntegerToShort(cust.getCustomerSegment()));

        // if (null != cust.getReadingLanguage())
        // sCust.setLanguage(CommonUtil.IntegerToShort(cust.getReadingLanguage()));
        //
        if (null != cust.getRegionCode())
            sCust.setRegion_code(CommonUtil.IntegerToShort(cust.getRegionCode()));

        if (null != cust.getCountryId())
        {
            // 2011-12-15 hunan 修改
            // sCust.setCountry_id(CommonUtil.IntegerToShort(cust.getCountyCode()));
            sCust.setCountry_id(CommonUtil.IntegerToShort(cust.getCountryId()));
        }
        if (null != cust.getProvCode())
        {
            sCust.setProv_code(CommonUtil.IntegerToShort(cust.getProvCode()));
        }

        Date createDate = cust.getCreateDate();
        if (createDate != null)
            sCust.setCreate_date(DateUtil.getFormatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        Date validDate = cust.getValidDate();
        if (validDate != null)
            sCust.setValid_date(DateUtil.getFormatDate(validDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

        if (IMSUtil.isPersonCust(cust.getCustomerClass()))
        {
            if (null != individual)
            {
                if (null != individual.getBirthDate())
                    sCust.setBirthday(DateUtil.getFormatDate(individual.getBirthDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

                if (null != individual.getGender())
                    sCust.setGender(CommonUtil.IntegerToShort(individual.getGender()));
            }

            if (null != individualName)
            {
                if (null != individualName.getFormOfAddress())
                    sCust.setCust_title(individualName.getFormOfAddress());

                if (null != individualName.getFamilyNames())
                    sCust.setFamily_name(individualName.getFamilyNames());

                // if (null != individualName.getFamilyNames())
                if (null != individualName.getPreferredGivenName())
                    sCust.setFirst_name(individualName.getPreferredGivenName());

                // if (null != individualName.getPreferredGivenName())
                if (null != individualName.getMiddleNames())
                    sCust.setMiddle_name(individualName.getMiddleNames());

                if (null != individualName.getLanguageId())
                {
                    sCust.setLanguage(CommonUtil.IntegerToShort(individualName.getLanguageId()));
                }
            }
        }
        else
        {
            if (null != orgName && null != orgName.getTradingName())
                sCust.setFamily_name(orgName.getTradingName());

            if (null != orgName && null != orgName.getLanguageId())
            {
                sCust.setLanguage(CommonUtil.IntegerToShort(orgName.getLanguageId()));
            }
        }

        if (null != partyIdentity)
        {
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
        }

        if (null != cust.getTaxExemptionNo())
            sCust.setTax_exempt_code(cust.getTaxExemptionNo());

        CmCustExt dmExt = null;
        if (!CommonUtil.isValid(cust.getCustId()))
            dmExt = querySingle(CmCustExt.class, new DBCondition(CmCustExt.Field.custId, cust.getCustId()), new DBCondition(
                    CmCustExt.Field.validDate, hisDate, Operator.LESS), new DBCondition(CmCustExt.Field.expireDate, hisDate,
                    Operator.GREAT));
        if (null != dmExt)
        {
            ExtendParamList paramList = new ExtendParamList();
            List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
            ExtendParam param = new ExtendParam();
            if (dmExt.getForceCycle() != null)
            {
                param.setParam_name(ConstantDefine.EXTPARAM_CUST_HIERARCHYBILLINGFLAG);
                param.setParam_value(String.valueOf(dmExt.getForceCycle()));
                paramArr.add(param);
            }

            if (dmExt.getBillFormatId() != null)
            {
                param.setParam_name(ConstantDefine.EXTPARAM_CUST_BILLHANDINGCODE);
                param.setParam_value(String.valueOf(dmExt.getBillFormatId()));
                paramArr.add(param);
            }

            if (dmExt.getDetailDeliver() != null)
            {
                param.setParam_name(ConstantDefine.EXTPARAM_CUST_CUSTBMCALLDETAIL);
                param.setParam_value(String.valueOf(dmExt.getDetailDeliver()));
                paramArr.add(param);
            }

            if (dmExt.getSummaryDeliver() != null)
            {
                param.setParam_name(ConstantDefine.EXTPARAM_CUST_CUSTBMSUMMARY);
                param.setParam_value(String.valueOf(dmExt.getSummaryDeliver()));
                paramArr.add(param);
            }

            if (!CommonUtil.isEmpty(paramArr))
            {
                paramList.setExtendParamList_Item(paramArr.toArray(new ExtendParam[paramArr.size()]));
                sCust.setList_ext_param(paramList);
            }

        }

        // sCust.setCust_rank(obj);
        // sCust.setProv_code(obj);

        // sCust.setVat_name(obj);
        return sCust;
    }

    /**
     * 查询用户或帐户订购的产品的公用方法 zengqm 2012-2-22
     */
    public List<CoProd> queryProdListHistory(Long userIdOrAcctId, int objectType, Date hisDate)
    {
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId);

        resultTable.addCondTable(CoProd.Field.productId, new DBCondition(CoProd.Field.objectId, userIdOrAcctId),
                new DBCondition(CoProd.Field.objectType, objectType), new DBCondition(CoProd.Field.validDate,
                        hisDate, Operator.LESS), new DBCondition(CoProd.Field.expireDate, hisDate, Operator.GREAT));

        return context.getComponent(ResultComponent.class).getResultList(resultTable);
    }

    /**
     * 查询用户已订购的主产品信息
     * 
     * @author zengqm
     * @date 2012-2-22
     */
    public CoProd queryPrimaryProductByUserIdHistory(Long userId, Date hisDate)
    {
        List<CoProd> prodList = this.queryProdListHistory(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV, hisDate);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        for (CoProd dmProd : prodList)
        {
            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                return dmProd;
            }
            else
            {
                continue;
            }
        }
        return null;
    }

    public static void fileWrite(String s)
    {
        try
        {
            File file = new File("d:/mylog.log");
            FileWriter fw = new FileWriter(file, true);
            fw.write(s + "\n");
            fw.flush();
            fw.close();
        }
        catch (Exception e)
        {
           ImsLogger imsLogger = new ImsLogger(QueryCustomerHistoryCompant.class);
           imsLogger.error(e,e);
        }

    }
}
