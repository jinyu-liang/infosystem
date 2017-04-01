package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmParty;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctsForGUIReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;

/**
 * @Description: search account list via different conditions
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author hunan
 * @Date 2011-10-30
 */
public class QueryAcctsForGUIBusiBean extends BaseBusiBean
{
    private BaseComponent baseCmp;
    private PartyComponent partyCmp;
    private CustomerQuery custQuery;
    private AccountQuery acctQuery;
    private ProductQuery prodQuery;
    private UserQuery userQuery;

    @Override
    public void init(Object... input) throws IMSException
    {
        baseCmp = context.getComponent(BaseComponent.class);
        partyCmp = context.getComponent(PartyComponent.class);
        custQuery = context.getComponent(CustomerQuery.class);
        acctQuery = context.getComponent(AccountQuery.class);
        prodQuery = context.getComponent(ProductQuery.class);
        userQuery = context.getComponent(UserQuery.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryAcctsForGUIReq req = (SQueryAcctsForGUIReq) input[0];
        if (req == null)
            return null;
        SAccount[] accts = queryAccts(req);
        SAccountList result = new SAccountList();
        if (accts != null)
        {
            result.setSAccountList_Item(accts);
        }
        return new Object[] { result };
    }

    private SAccount[] queryAccts(SQueryAcctsForGUIReq req)
    {
        Long acctId = req.getAcct_id();
        String acctName = req.getAcct_name();
        String phoneId = req.getPhone_id();
        Long custId = req.getCust_id();
        String identity = req.getIdentity();
        Integer identityType = req.getIdentity_type();
        Integer offerId = req.getOffer_id();
        String firstName = req.getFirst_name();
        String middleName = req.getMiddle_name();
        String familyName = req.getFamily_name();
        
        List<Long> firstList = queryByAcctId(acctId,acctName);
        
        List<Long> secondList = queryByPhoneId(phoneId,firstList);
        
        List<Long> thirdList = queryByCustId(custId,secondList);
        
        List<Long> fourthList = queryByParty(identity,identityType,thirdList);
        
        List<Long> fifthList = queryByOfferId(offerId,fourthList);
        
        List<Long> sixList = queryByIndividualName(firstName,middleName,familyName,fifthList);
        if (CommonUtil.isEmpty(sixList))
        {
            return null;
        }
        List<SAccount> sAccts = new ArrayList<SAccount>();
        for (Long id : sixList)
        {
            CaAccount caAccount = context.get3hTree().loadAcct3hBean(id).getAccount();
            if (caAccount ==null)
                continue;
            
            SAccount sAccount = encapsulateSAccount(caAccount);
            sAccts.add(sAccount);
        }
        
        SAccount[] acctArr1 = sAccts.toArray(new SAccount[sAccts.size()]);
        for (int i = 0; i < acctArr1.length - 1; i++)
        {
            for (int j = i + 1; j < acctArr1.length; j++)
            {
                // LogUtil.getLogger(FuzzyMatchCustsForGUIBusiBean.class).info(i+ "   "+j);
                if (acctArr1[i] != null && acctArr1[j] != null && acctArr1[i].getAcct_id() > acctArr1[j].getAcct_id())
                {
                    // LogUtil.getLogger(FuzzyMatchCustsForGUIBusiBean.class).info(custArr1[i].getCust_id() + " "
                    // +custArr1[j].getCust_id());
                    SAccount temp = acctArr1[i];
                    acctArr1[i] = acctArr1[j];
                    acctArr1[j] = temp;
                }
            }
        }
        return acctArr1;
    }
    
    private List<Long> queryByIndividualName(String firstName, String middleName, String familyName, List<Long> fifthList)
    {
        if (!CommonUtil.isValid(firstName) &&!CommonUtil.isValid(middleName) &&!CommonUtil.isValid(familyName) )
            return fifthList;
        
        List<CmIndividualName> partys = userQuery.queryIndividualNames(firstName, middleName, familyName);
        
        if (CommonUtil.isEmpty(partys))
            return null;
        
        List<Long> partyIds = CommonUtil.getFieldValueList(partys, "partyId");
        if (CommonUtil.isEmpty(partyIds))
            return null;
        
        List<CmCustomer> custs = baseCmp.query(CmCustomer.class,new DBCondition(CmCustomer.Field.custId,partyIds.toArray(),Operator.IN));
        if (CommonUtil.isEmpty(custs))
            return null;
        
        List<Long> custIds =  CommonUtil.getFieldValueList(partys, "custId");
        if (CommonUtil.isEmpty(custIds))
            return null;
        
        List<CaCustomerRel> custRels = baseCmp.query(CaCustomerRel.class,new DBCondition(CaCustomerRel.Field.custId,custIds.toArray(),Operator.IN));
        List<Long> acctIds = CommonUtil.getFieldValueList(custRels, "acctId");
        
        List<Long> results = CommonUtil.getAcctIdListBetween2List(acctIds, fifthList);
        
        return results;
    }

    private List<Long> queryByOfferId(Integer offerId, List<Long> fourthList)
    {
    	if (!CommonUtil.isValid(offerId))
    		return fourthList;
    	List<CoProd> coProds=baseCmp.query(CoProd.class,new DBCondition(CoProd.Field.objectId, fourthList,Operator.IN),new DBCondition(CoProd.Field.productOfferingId, offerId));
    	coProds = prodQuery.mergeProdList(coProds);
    	if(CommonUtil.isEmpty(coProds)){
    		return null;
    	}
        
//        List<CoProd> prods = prodQuery.queryPrimaryProdsByOffer(offerId.longValue());
//        if (CommonUtil.isEmpty(prods))
//            return null;
//        
//        List<Long> prodIds = CommonUtil.getFieldValueList(prods, "productId");
//        if (CommonUtil.isEmpty(prodIds))
//            return null;
//        
//        List<CoProdInvObj> invObjs = baseCmp.query(CoProdInvObj.class,new DBCondition(CoProdInvObj.Field.productId,prodIds.toArray(),Operator.IN));
//        
//        if (CommonUtil.isEmpty(invObjs))
//            return null;
//            
        List<Long> acctIds = new ArrayList<Long>();
        for (CoProd inv : coProds)
        {
            if (inv.getObjectType().intValue() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT )
            {
                acctIds.add(inv.getObjectId());
            }
        }
        
        List<Long> results = CommonUtil.getAcctIdListBetween2List(acctIds, fourthList);
        
        return results;
    }

    private List<Long> queryByParty(String identity, Integer identityType, List<Long> thirdList)
    {
        if (!CommonUtil.isValid(identity) && !CommonUtil.isValid(identityType))
            return thirdList;
        
        if (CommonUtil.isNotEmpty(thirdList) && thirdList.size() == 1)
        {
            CmParty party = partyCmp.queryPartyByIdentity(identityType,identity);
            if (party == null)
                return null;
            
            CmCustomer customer = custQuery.queryCustomerByPartyId(party.getPartyId());
            if (customer == null)
                return null;
            
            List<Long> acctIds = acctQuery.queryAcctIdsByCustId(customer.getCustId());
            if (CommonUtil.isEmpty(acctIds) || !acctIds.contains(thirdList.get(0)))
                return null;
            
            return thirdList;
        }
        else
        {
            CmParty party = partyCmp.queryPartyByIdentity(identityType, identity);
            if (party == null)
                return null;

            CmCustomer customer = custQuery.queryCustomerByPartyId(party.getPartyId());
            if (customer == null)
                return null;

            List<Long> acctIds = acctQuery.queryAcctIdsByCustId(customer.getCustId());

            List<Long> resultList = CommonUtil.getAcctIdListBetween2List(thirdList, acctIds);

            return resultList;
        }
    
        
    
    }

    private List<Long> queryByCustId(Long custId, List<Long> secondList)
    {
        if (!CommonUtil.isValid(custId))
            return secondList;
        
        if (CommonUtil.isNotEmpty(secondList) && secondList.size() == 1)
        {
            CaCustomerRel customerRel =  baseCmp.querySingle(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.custId, custId),
                    new DBCondition(CaCustomerRel.Field.acctId, secondList.get(0)));
            
            if(customerRel == null)
                return null;
            
            return secondList;
        }
        else
        {
            List<Long> acctIdList = acctQuery.queryAcctIdsByCustId(custId);
            
            List<Long> acctIds = CommonUtil.getAcctIdListBetween2List(secondList, acctIdList);
            
            return acctIds;
        }
    }
    
    private List<Long> queryByPhoneId(String phoneId, List<Long> firstList)
    {
        if (!CommonUtil.isValid(phoneId))
            return firstList;

        List<CaAccountRes> accountResList = null;
      //2012-08-28 linzt 整理组件方法  采用load3hBean
//        CmResIdentity resIdentity = userQuery.queryResIdentityByPhoneId(phoneId);
//        if(CommonUtil.isEmpty(resIdentity)){
//            return null;
//        }
        User3hBean userBean=null;
        try
        {
        	userBean=context.get3hTree().loadUser3hBean(phoneId);
        }
        catch(IMS3hNotFoundException e)
        {
        	return null;
        }
        Long userId=userBean.getUserId();
        if (CommonUtil.isNotEmpty(firstList))
        {
            accountResList = baseCmp.query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId,
            		userId), new DBCondition(CaAccountRes.Field.acctId, firstList.toArray(), Operator.IN));
        }
        else
        {
            accountResList = baseCmp.query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId,
            		userId));

        }
        List<Long> acctIdList = CommonUtil.getFieldValueList(accountResList,"acctId");
        
        return acctIdList;
    }
    

    private List<Long> queryByAcctId(Long acctId, String acctName)
    {
        if (!CommonUtil.isValid(acctId) && !CommonUtil.isValid(acctName))
            return null;
            
        List<CaAccount> accts = null;
        if (CommonUtil.isValid(acctId) && CommonUtil.isValid(acctName))
            accts = baseCmp.query(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId),new DBCondition(CaAccount.Field.name, acctName));
        else if (CommonUtil.isValid(acctId) && !CommonUtil.isValid(acctName))//TODO 
            accts = baseCmp.query(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
        else if (!CommonUtil.isValid(acctId) && CommonUtil.isValid(acctName))//TODO 
            accts = baseCmp.query(CaAccount.class, new DBCondition(CaAccount.Field.name, acctName));
        
        return CommonUtil.getFieldValueList(accts,"acctId");
    }


    
    /**
     * 将CaAccount封装成SAccount
     */
    private SAccount encapsulateSAccount(CaAccount ca)
    {
        SAccount sAcct = new SAccount();
        sAcct.setAcct_id(ca.getAcctId());
        if (ca.getAccountClass() == null)
        {
            sAcct.setAcct_class(ca.getAccountClass().shortValue());
        }
        sAcct.setAcct_name(ca.getName());
        if (ca.getAccountSegment() != null)
        {
            sAcct.setAcct_segment(ca.getAccountSegment().shortValue());
        }
        if (ca.getAccountType() != null)
        {
            sAcct.setAcct_type(ca.getAccountType().shortValue());
        }
//        if (ca.getCompanyId() != null)
//        {
//            sAcct.setCompany(ca.getCompanyId().shortValue());
//        }
        if (ca.getForceCycle() != null)
        {
            sAcct.setForce_cycle(ca.getForceCycle().shortValue());
        }
        if (ca.getOrgId() != null)
        {
            // ca.getOrgId();
        }
        if (ca.getProvinceId() != null)
        {
            sAcct.setProv_code(ca.getProvinceId().shortValue());
        }
        if (ca.getRegionCode() != null)
        {
            sAcct.setRegion_code(ca.getRegionCode().shortValue());
        }
        if (ca.getAccountStatus() != null)
        {
            sAcct.setStatus(ca.getAccountStatus().shortValue());
        }
        //yanchuan 2012-02-03 增加返回blance_type
        sAcct.setBalance_type(ca.getBalanceType());
        return sAcct;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAcctsForGUIResponse req = new Do_queryAcctsForGUIResponse();
        if (CommonUtil.isEmpty(result))
        {
            return req;
        }
        req.setSAccountList((SAccountList) result[0]);
        return req;
    }

    @Override
    public void destroy()
    {
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryAcctsForGUIReq req = (SQueryAcctsForGUIReq) input[0];
		Long acctId = req.getAcct_id();
	    String phoneId = req.getPhone_id();
	    Long custId = req.getCust_id();
	    if (!CommonUtil.isValid(acctId) && !CommonUtil.isValid(phoneId) && !CommonUtil.isValid(custId))
        {
           return null;
        }
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().load3hBean(null, acctId, null, phoneId));
        return list;
	}
}
