package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyCustsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SFuzzyCustsForGUIReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomerList;

/**
 * @Description: 根据客户的编号，姓名，证件类型，证件，手机号码查询客户信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author hunan
 * @Date 2011-11-02
 * @Date 2012-04-21 wangdw5 : in For-each,use 'continue' instead  of throwing Exception which means 'break'
 * @Date 2012-06-1 wuyujie：解决create3hbean参数错误bug
 * @Date 2012-06-25 zhangzj3：界面按名称查询时，create3hbean报错。
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class FuzzyMatchCustsBusiBean extends BaseBusiBean
{
    private CustomerComponent custCmp;
    private BaseComponent baseCmp;
    private CustomerQuery custQuery;
//    private UserQuery userQuery;
//    private ProductQuery prodQuery;
    private PartyComponent pc ;


    @Override
    public void init(Object... input) throws IMSException
    {
        baseCmp = context.getComponent(BaseComponent.class);
        custCmp = context.getComponent(CustomerComponent.class);
        custQuery = context.getComponent(CustomerQuery.class);
//        userQuery = context.getComponent(UserQuery.class);
//        prodQuery = context.getComponent(ProductQuery.class);
        pc = context.getComponent(PartyComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SFuzzyCustsForGUIReq req = (SFuzzyCustsForGUIReq) input[0];
        List<SCustomer> sCusts = new ArrayList<SCustomer>();
        sCusts = this.querySCustomers(req);
        SCustomerList custList = new SCustomerList();
        SContactList contactList = new SContactList();
        //@Date 2012-11-08 yangjh : 解决sCusts查不到空指针
        if(sCusts != null){
            custList.setSCustomerList_Item(sCusts.toArray(new SCustomer[sCusts.size()]));
            contactList = this.querySContacts(sCusts);
        }
        return new Object[] { custList, contactList };
    }

    //查询查出客户的联系方式
    private SContactList querySContacts(List<SCustomer> sCusts)
    {
        List<Long> custIds = new ArrayList<Long>();
        for(SCustomer sc :sCusts)
        {
            if(sc == null )
            {
                continue;
            }
            custIds.add(sc.getCust_id());
        }
         return  context.getComponent(ContactComponent.class).queryCustomerContactsByCustIds(custIds);
    }
    //依据不同的条件查询客户的信息
    private List<SCustomer> querySCustomers(SFuzzyCustsForGUIReq req)
    {
    	CmCustomer custm = new CmCustomer();
//    	cust, individual, individualName, orgName, partyIdentity
    	if (CommonUtil.isValid(req.getGui_cust_id())){
    		custm = context.get3hTree().loadCust3hBean(req.getGui_cust_id()).getCustomer();
    	}
    	Long firstcustId = custm.getCustId();
    	Long secondcustId  = queryByAcctId(req.getGui_acct_id());
    	List<Long> thirdcustIds = queryByAcctName(req.getAccount_name());
    	List<Long> fourthcustIds = queryByIndiFamilyName(req.getFamily_name());
    	List<Long> fifthcustIds = queryByOrgFamilyName(req.getFamily_name());
    	List<Long> sixthcustIds = queryByMiddleName(req.getMiddle_name());
    	List<Long> seventhcustIds = queryByFirstName(req.getFirst_name());
    	Long eighthcustId = queryByPartyIden(req.getIdentity(),req.getIdentity_type()); 
    	Long ninthcustId = queryByIdentity(req.getGui_phone_id());
    	Long tenthcustId = queryByUserId(req.getGui_user_id());
    	List<Long> eleventhcustIds = queryByOfferId(req.getOffering_id());
    	Set<Long> custSets =  new HashSet<Long>();
    	if(firstcustId != null)
    	    custSets.add(firstcustId);
    	if(secondcustId != null){
    	    custSets.add(secondcustId);
    	}
    	if(eighthcustId != null){
    	    custSets.add(eighthcustId);
        }
    	if(ninthcustId != null){
    	    custSets.add(ninthcustId);
        }
    	if(tenthcustId != null){
    	    custSets.add(tenthcustId);
        }
    	if(!CommonUtil.isEmpty(thirdcustIds)){
    		for(Long acctcusts:thirdcustIds)
        	{
        		custSets.add(acctcusts);
        	}
    	}
    	if(!CommonUtil.isEmpty(fourthcustIds)){
	    	for(Long indicusts:fourthcustIds)
	    	{
	    		custSets.add(indicusts);
	    	}
    	}
    	if(!CommonUtil.isEmpty(fifthcustIds)){
	    	for(Long orgcusts:fifthcustIds)
	    	{
	    		custSets.add(orgcusts);
	    	}
    	}
    	if(!CommonUtil.isEmpty(sixthcustIds)){
	    	for(Long middcusts:sixthcustIds)
	    	{
	    		custSets.add(middcusts);
	    	}
    	}
    	if(!CommonUtil.isEmpty(seventhcustIds)){
	    	for(Long firstnamecusts:seventhcustIds)
	    	{
	    		custSets.add(firstnamecusts);
	    	}
    	}
    	if(!CommonUtil.isEmpty(eleventhcustIds)){
	    	for(Long offercusts:eleventhcustIds)
	    	{
	    		custSets.add(offercusts);
	    	}
    	}
    	if(CommonUtil.isEmpty(custSets))
    		return null;
    	List<SCustomer> sCusts = new ArrayList<SCustomer>();
    	for(Long custs:custSets)
    	{
    		/**
    		 * @Date 2012-04-21 wangdw5 : in For-each,use 'continue' instead  of throwing Exception which means 'break'
    		 */
    		CmCustomer cmCust = null;
    		if(custs!=null){
    		    try
    		    {
    		        cmCust = context.get3hTree().loadCust3hBean(custs).getCustomer();
    		    }
    		    catch(IMS3hNotFoundException e )
    		    {
    		        continue;
    		    }
    		}
      		 Long partyId = pc.queryPartyIdByCustId(custs);
      	     CmIndividualName individualName = pc.queryInvidualNameByCustId(custs);
      	     CmIndividual individual = null;
      	     CmOrgName orgName = null;
      	     CmPartyIdentity partyIdentity = null;
      	     if(CommonUtil.isValid(partyId))
      	      {
      	         individual = pc.queryCmIndividualByPartyId(partyId);
      	         orgName = pc.queryCmOrgNameByPartyId(partyId);
      	         partyIdentity = pc.queryPartyByIdentity(partyId);
      	      }
      	   SCustomer sCust = custCmp.sCustTransform(cmCust, individual, individualName, orgName, partyIdentity);
      	   sCusts.add(sCust);
    	}
    	return sCusts;
    }
     private Long queryByUserId(Long userId)
    {
         Long custId = null;
         if(!CommonUtil.isValid(userId))
             return null;
         custId = context.get3hTree().loadUser3hBean(userId).getCustId();
         if(CommonUtil.isValid(custId))
             return null;
         return custId;
    }

//    	     // 2011-11-24 排除重复的数据以及空数据
//    	     if (!custIsContaitedInList(cmCust, sCusts))
//    	      {
//    	      SCustomer sCust = custCmp.sCustTransform(cmCust, individual, individualName, orgName, partyIdentity);
//    	      sCusts.add(sCust);
//    	      }
     private List<Long> queryByOfferId(Integer offeringId) 
     {
		Long custId = null;
		List<Long> custIds = new ArrayList<Long>();
		if(!CommonUtil.isValid(offeringId))
			return null;
		List<CoProd> prodList= pc.query(CoProd.class, 
		        new DBCondition(CoProd.Field.productOfferingId, offeringId),
		        new DBCondition(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN),
                new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                );
		for(CoProd prod:prodList)
		{
			Long userId = prod.getObjectId();
			custId = context.get3hTree().loadUser3hBean(userId).getCustId();
			custIds.add(custId);
		}
		if(CommonUtil.isEmpty(custIds))
			return null;
		return custIds;
	}

	

	private Long queryByIdentity(String phoneId) 
     {
    	 Long custId = null;
    	 if(!CommonUtil.isValid(phoneId))
    		 return null;
    	 CmCustomer cust =context.get3hTree().loadUser3hBean(phoneId).getCustomer();
    	 if(cust == null)
    		 return null;
    	 custId = cust.getCustId();
    	 return custId;
	 }

	private Long queryByPartyIden(String partIdentity, Integer partyIdentityType)
     {
    	 Long custId =  null ;
    	 if(!CommonUtil.isValid(partyIdentityType) && !CommonUtil.isValid(partIdentity))
    		 return null;
    	 if(CommonUtil.isValid(partyIdentityType) && !CommonUtil.isValid(partIdentity))
    		 return null;
    	 if(CommonUtil.isValid(partyIdentityType) && CommonUtil.isValid(partIdentity))
    	 {
    		 CmPartyIdentity partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, partIdentity),
    		         new DBCondition(CmPartyIdentity.Field.partyIdentificationType, partyIdentityType));
    		 if(partyIdentity == null)
    			 return null;
    		 custId = partyIdentity.getPartyId();
    	 }else{
	    	 CmPartyIdentity where = new CmPartyIdentity();
			 where.setPartyIdentificationSpec(partIdentity);
//			 where.setPartyIdentificationType(partyIdentityType);
			 CmPartyIdentity partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, partIdentity));
			 if(partyIdentity == null)
				 return null;
			 custId = partyIdentity.getPartyId();
    	 }
		// TODO Auto-generated method stub
		return custId;
	 }

	private List<Long> queryByFirstName(String firstName)
     {
    	 Long custId = null;
    	 if(!CommonUtil.isValid(firstName))
    		 return null;
    	 List<CmIndividualName> indiName = baseCmp.query(CmIndividualName.class, new DBCondition(CmIndividualName.Field.preferredGivenName, firstName));
    	 if(CommonUtil.isEmpty(indiName))
    		 return null;
    	 List<Long> custIds =  new ArrayList<Long>();
    	 for(CmIndividualName indiNames:indiName)
    	 {
    		 Long partyId = indiNames.getPartyId();
    		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
    		 custId = cust.getCustId();
    		 custIds.add(custId);
    	 }
    	 if(CommonUtil.isEmpty(custIds))
    		 return null;
		return custIds;
	 }

	private List<Long> queryByMiddleName(String middleName) 
     {
    	 Long custId = null;
    	 if(!CommonUtil.isValid(middleName))
    		 return null;
    	 List<CmIndividualName> indiName = baseCmp.query(CmIndividualName.class, new DBCondition(CmIndividualName.Field.middleNames, middleName));
    	 if(CommonUtil.isEmpty(indiName))
    		 return null;
    	 List<Long> custIds =  new ArrayList<Long>();
    	 for(CmIndividualName indiNames:indiName)
    	 {
    		 Long partyId = indiNames.getPartyId();
    		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
    		 custId = cust.getCustId();
    		 custIds.add(custId);
    	 }
    	 if(CommonUtil.isEmpty(custIds))
    		 return null;
		return custIds;
	 }

	private List<Long> queryByOrgFamilyName(String familyName) 
     {
    	 Long custId = null;
    	 if(!CommonUtil.isValid(familyName))
    		 return null;
    	 List<CmOrgName> org =  baseCmp.query(CmOrgName.class, new DBCondition(CmOrgName.Field.tradingName, familyName));
    	 if(CommonUtil.isEmpty(org))
    		 return null;
    	 List<Long> custIds  = new ArrayList<Long>();
    	 for(CmOrgName orgs:org)
    	 {
    		 Long partyId = orgs.getPartyId();
    		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
    		 custId = cust.getCustId();
    		 custIds.add(custId);
    	 }
    	 if(CommonUtil.isEmpty(custIds))
    		 return null;
		return custIds;
	 }

	private List<Long> queryByIndiFamilyName(String familyName) 
     {
    	 Long custId = null;
    	 if(!CommonUtil.isValid(familyName))
    		 return null;
    	 List<CmIndividualName> individual = baseCmp.query(CmIndividualName.class, new DBCondition(CmIndividualName.Field.familyNames, familyName));
    	 if(CommonUtil.isEmpty(individual))
    		 return null;
    	 List<Long> custIds  = new ArrayList<Long>();
    	 for(CmIndividualName partys:individual)
    	 {
    		 Long partyId = partys.getPartyId();
    		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
    		 custId = cust.getCustId();
    		 custIds.add(custId);
    	 }
    	 if(CommonUtil.isEmpty(custIds))
    		 return null;
		return custIds;
	 }


	 private List<Long> queryByAcctName(String accountName) 
	 {
		 Long custId = null;
	     if(!CommonUtil.isValid(accountName))
	    	return null;
	     List<CaAccount> acct = baseCmp.query(CaAccount.class, new DBCondition(CaAccount.Field.name, accountName));
	     if(CommonUtil.isEmpty(acct))
	    	 return null;
	     List<Long> custIds = new ArrayList<Long>();
	     for(CaAccount accts:acct)
	     {
	    	 Long acctId = accts.getAcctId();
	    	 custId =context.get3hTree().loadAcct3hBean(acctId).getCustId();
	    	 custIds.add(custId);
	     }
	     if(CommonUtil.isEmpty(custIds))
    		 return null;
		return custIds;
	 }

	private Long queryByAcctId(Long acctId)
	 {
	 	Long custId = null;
	     if (!CommonUtil.isValid(acctId))
	         return null;
	     try
	     {
	         custId =  context.get3hTree().loadAcct3hBean(acctId).getCustId();
	     }
	     catch(IMS3hNotFoundException e)
	     {
	    	return null;
	     }
	     return custId;
	 }

// return sortSCustomers(sCusts);
 
//        DBJoinCondition join = new DBJoinCondition(CmCustomer.class);
//        join.innerJoin(CmPartyRole.class, new DBRelation(CmCustomer.Field.custId, CmPartyRole.Field.partyRoleId));
//        join.innerJoin(CmParty.class, new DBRelation(CmPartyRole.Field.partyId, CmParty.Field.partyId));
//        join.innerJoin(CmPartyIdentity.class, new DBRelation(CmParty.Field.partyId, CmPartyIdentity.Field.partyId));
//        join.leftJoin(CmIndividual.class, new DBRelation(CmParty.Field.partyId, CmIndividual.Field.partyId));
//        join.leftJoin(CmIndividualName.class, new DBRelation(CmParty.Field.partyId, CmIndividualName.Field.partyId));
//        join.leftJoin(CmOrgName.class, new DBRelation(CmParty.Field.partyId, CmOrgName.Field.partyId));
//        join.innerJoin(CaCustomerRel.class, new DBRelation(CmCustomer.Field.custId, CaCustomerRel.Field.custId), new DBRelation(
//                CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
//        join.innerJoin(CaAccount.class, new DBRelation(CaCustomerRel.Field.acctId, CaAccount.Field.acctId));
//        join.leftJoin(CaAccountRes.class, new DBRelation(CaAccount.Field.acctId, CaAccountRes.Field.acctId), new DBRelation(
//                CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));// TODO 这里是否需要加支付账户的条件？
//        join.leftJoin(CmResource.class, new DBRelation(CaAccountRes.Field.resourceId, CmResource.Field.resourceId));
//        join.leftJoin(CmResIdentity.class, new DBRelation(CmResource.Field.resourceId, CmResIdentity.Field.resourceId),
//                new DBRelation(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
//        join.leftJoin(CoProdInvObj.class, new DBRelation(CmResource.Field.resourceId, CoProdInvObj.Field.objectId),
//        // 用户订购的产品
//                new DBRelation(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
//        join.leftJoin(CoProd.class, new DBRelation(CoProdInvObj.Field.productId, CoProd.Field.productId),
//        // 主产品
//                new DBRelation(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN));
//        List<DBCondition> conds = new ArrayList<DBCondition>();
//        if (CommonUtil.isValid(req.getAccount_name()))
//        {
//            DBCondition con = new DBCondition(CaAccount.Field.name, req.getAccount_name());
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getGui_acct_id()))
//        {
//            DBCondition con = new DBCondition(CaAccount.Field.acctId, req.getGui_acct_id());
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getGui_cust_id()))
//        {
//            DBCondition con = new DBCondition(CmCustomer.Field.custId, req.getGui_cust_id());
//            conds.add(con);
//        }
//模糊查询不应该使用客户类型
//        if (CommonUtil.isValid(req.getCustomer_type()))
//        {
//            DBCondition con = new DBCondition(CmCustomer.Field.customerClass, req.getCustomer_type()); // TODO
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getFamily_name()))
//        {
//            DBCondition con = new DBCondition(CmIndividualName.Field.familyNames, req.getFamily_name());
//            DBCondition con1 = new DBCondition(CmOrgName.Field.tradingName, req.getFamily_name());
//            DBCondition orCon = new DBOrCondition(con, con1);
//            conds.add(orCon);
//            // conds.add(con1);
//        }
//        if (CommonUtil.isValid(req.getMiddle_name()))
//        {
//            DBCondition con = new DBCondition(CmIndividualName.Field.middleNames, req.getMiddle_name());
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getFirst_name()))
//        {
//            DBCondition con = new DBCondition(CmIndividualName.Field.preferredGivenName, req.getFirst_name());
//            conds.add(con);
//        }

        // 如果证件类型不为空，并且证件号码有效
//        if (req.getIdentity_type() != null && CommonUtil.isValid(req.getIdentity()))
//        {
//            DBOrCondition orCon = new DBOrCondition(
//                    new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec,req.getIdentity()),
//                    new DBCondition(CmPartyIdentity.Field.partyIdentificationType, req.getIdentity_type()));
//            conds.add(orCon);
//
//        }
//        else
//        {
//            if (CommonUtil.isValid(req.getIdentity()))
//            {
//                DBCondition con = new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, req.getIdentity());
//                conds.add(con);
//            }
//        }
//        //这个是主销售品
//        if (CommonUtil.isValid(req.getOffering_id()))
//        {
//            DBCondition con = new DBCondition(CoProd.Field.productOfferingId, req.getOffering_id());
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getGui_phone_id()))
//        {
//            DBCondition con = new DBCondition(CmResIdentity.Field.identity, req.getGui_phone_id());
//            conds.add(con);
//        }
//        if (CommonUtil.isValid(req.getGui_user_id()))
//        {
//            DBCondition con = new DBCondition(CmResource.Field.resourceId, req.getGui_user_id());
//            conds.add(con);
//        }
//        DBCondition[] conArr = conds.toArray(new DBCondition[conds.size()]);
//        DBOrCondition orCond = new DBOrCondition(conArr);
//        OrderCondition[] orderCond = new OrderCondition[]{new OrderCondition(CmCustomer.Field.custId)};
//        //排序
//        List<Map> maps = baseCmp.queryJoin(join, orderCond , null, orCond);
//        List<SCustomer> sCusts = new ArrayList<SCustomer>();
//        if (maps == null)
//        {
//            return sCusts;
//        }
//        for (Map map : maps)
//        {
//            if (map == null)
//            {
//                continue;
//            }
//            CmCustomer cust = (CmCustomer) map.get(CmCustomer.class);
//            CmIndividual individual = (CmIndividual) map.get(CmIndividual.class);
//            CmIndividualName individualName = (CmIndividualName) map.get(CmIndividualName.class);
//            CmPartyIdentity partyIdentity = (CmPartyIdentity) map.get(CmPartyIdentity.class);
//            CmOrgName orgName = (CmOrgName) map.get(CmOrgName.class);
//            // 2011-11-24 排除重复的数据以及空数据
//             if (!custIsContaitedInList(cust, sCusts))
//             {
//             SCustomer sCust = custCmp.sCustTransform(cust, individual, individualName, orgName, partyIdentity);
//             sCusts.add(sCust);
//             }
//        }
//        // return sortSCustomers(sCusts);
//        return sCusts;

    
    //判断客户是否已经存在在我们查出的列表里了
//    private boolean custIsContaitedInList(CmCustomer cust, List<SCustomer> sCusts)
//    {
//        if (cust == null)
//        {
//            return true;
//        }
//        for (SCustomer sCust : sCusts)
//        {
//            if (sCust == null)
//            {
//                continue;
//            }
//            if (cust.getCustId().equals(sCust.getCust_id()))
//            {
//                return true;
//            }
//        }
//        return false;
//
//    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_fuzzyCustsForGUIResponse req = new Do_fuzzyCustsForGUIResponse();
        if (result == null)
        {
            return req;
        }
        req.setSCustomerList((SCustomerList) result[0]);
        req.setSContactList((SContactList) result[1]);
        return req;
    }

    @Override
    public List<CmBusi> createBusiRecord(Object[] input)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SFuzzyCustsForGUIReq req = (SFuzzyCustsForGUIReq) input[0];
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	//2012-06-1 wuyujie：解决create3hbean参数错误bug
		//@Date 2012-06-25 zhangzj3：界面按名称查询时，create3hbean报错。
		if(req.getGui_cust_id() != null || req.getGui_acct_id() != null || req.getGui_user_id()!= null || req.getGui_phone_id() != null){
			list.add(context.get3hTree().load3hBean(req.getGui_cust_id(), req.getGui_acct_id(), req.getGui_user_id(), req.getGui_phone_id()));
		}
		return list;
	}

}
