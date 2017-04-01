package com.ailk.ims.business.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jef.common.wrapper.IntRange;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.CustomerQuery;
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
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustBaseInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustBaseInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomerList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description: 根据客户的编号，姓名，证件类型，证件，手机号码查询客户信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author hunan
 * @Date 2011-10-19
 */
public class QueryCustBasicInfoBusiBean extends BaseBusiBean
{

    private CustomerComponent custCmp;
    private BaseComponent baseCmp;
    private CustomerQuery custQuery;
    private PartyComponent pcCmp ;
    private BaseProductComponent prodCmp = null;
    
    private SContactList  contList = new SContactList();
    private SCustomerList custList = new SCustomerList();
    private SAccountList acctList = new SAccountList();
    private SUserList userList = new SUserList();
    private Integer totalCount = 0;
    
    @Override
    public void init(Object... input) throws IMSException
    {
        custCmp = context.getComponent(CustomerComponent.class);
        baseCmp = context.getComponent(BaseComponent.class);
        custQuery = context.getComponent(CustomerQuery.class);
        pcCmp = context.getComponent(PartyComponent.class); 
        prodCmp = context.getComponent(BaseProductComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryCustBaseInfoReq req = (SQueryCustBaseInfoReq) input[0];
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQueryCustBaseInfoReq");
        }
        Integer type = req.getIdentity_type();
        String identity = req.getIdentity();
        // 如果只输入证件类型，则抛错。
        if (type != null && CommonUtil.isEmpty(identity))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "id type and number can't be empty!");
        }
        if (type == null && !CommonUtil.isEmpty(identity))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "id type and number can't be empty!");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryCustBaseInfoReq req = (SQueryCustBaseInfoReq) input[0];

        this.querySCustomers(req);

      
        List<Long> custIds = new ArrayList<Long>();
        SCustomer[] custs = custList.getSCustomerList_Item();
        if(!CommonUtil.isEmpty(custs)){
            for (SCustomer cus : custs)
            {
                if (cus == null)
                {
                    continue;
                }
                custIds.add(cus.getCust_id());
            }
            contList = context.getComponent(ContactComponent.class).queryCustomerContactsByCustIds(custIds);
            
        }

       
        return null;
    }

    /*
     * 查询客户信息
     */
    private Object[] querySCustomers(SQueryCustBaseInfoReq req)
    {
    	CmCustomer cust = new CmCustomer();
    	if(!CommonUtil.isEmpty(req.getCust_id())){
    		if (CommonUtil.isValid(req.getCust_id())){//1
    			cust =context.get3hTree().loadCust3hBean(req.getCust_id()).getCustomer();
    			List<Long> custIds = new ArrayList<Long>();
    			custIds.add(cust.getCustId());
    			List<SCustomer> customers = this.queryCustomerByCustIds(custIds);
    			custList.setSCustomerList_Item(customers.toArray(new SCustomer[customers.size()]));
    			totalCount = customers.size();
    			return null;
    		}
    	}else if(!CommonUtil.isEmpty(req.getAcct_id())){
    	    Long acctId = req.getAcct_id();
            SAccount sAcct = context.getComponent(AccountComponent.class).querySAccountInfo(acctId);
            if( sAcct != null)
            {
                acctList.setSAccountList_Item(new SAccount[]{sAcct});
                totalCount = 1;
            }
            return null;
            
        }else if(!CommonUtil.isEmpty(req.getPhone_id())||!CommonUtil.isEmpty(req.getUser_id())){
                
            List<SUser> users = queryUserInfo(req.getUser_id(),req.getPhone_id());
            userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
            totalCount = users.size();
            return null;
            
        }else if(!CommonUtil.isEmpty(req.getAccount_name())){
            List<CaAccount> list = queryByAcctName(req.getAccount_name(),req.getStart(),req.getLimit());
            List<SAccount> tmp =new ArrayList();
            for(CaAccount acct:list){
              Long acctId = acct.getAcctId();
              SAccount sAcct = context.getComponent(AccountComponent.class).querySAccountInfo(acctId);
              if(sAcct != null){
                  tmp.add(sAcct);
              }
            }
            acctList.setSAccountList_Item(tmp.toArray(new SAccount[tmp.size()]));
            totalCount  = baseCmp.queryCount(CaAccount.class,new DBCondition(CaAccount.Field.name,req.getAccount_name()));
            return null;
    		
    	}else if(!CommonUtil.isEmpty(req.getIdentity()) && !CommonUtil.isEmpty(req.getIdentity_type())){
    		Long eighthCustId = queryByPartyIden(req.getIdentity(),req.getIdentity_type()); //4
    		if(eighthCustId != null){
    			List<Long> custIds = new ArrayList<Long>();
    			custIds.add(eighthCustId);
    			List<SCustomer> customers = this.queryCustomerByCustIds(custIds);
    			custList.setSCustomerList_Item(customers.toArray(new SCustomer[customers.size()]));
    			totalCount = customers.size();
    			return null;
    		}
    	}else if(!CommonUtil.isEmpty(req.getCustomer_type())&&!CommonUtil.isEmpty(req.getCustomer_name())){
    		Object[] twelveCountAndCustIds;
			try {
				twelveCountAndCustIds = queryByCustomerTypeAndCustName(req.getCustomer_name(),req.getCustomer_type(),req.getStart(),req.getLimit());
				if(twelveCountAndCustIds != null){
	    			List<SCustomer> customers = this.queryCustomerByCustIds((List<Long>)twelveCountAndCustIds[1]);
	    			custList.setSCustomerList_Item(customers.toArray(new SCustomer[customers.size()]));
	    			totalCount = (Integer)twelveCountAndCustIds[0];
	    			
	    		}
			} catch (SQLException e) {
				throw new IMSException(e); 
			}
			return null;
    	}else if(!CommonUtil.isEmpty(req.getObject_type())&&!CommonUtil.isEmpty(req.getOffer_name())){
            PmProductOffering offer = prodCmp.queryOfferingByOfferName(req.getOffer_name());
            if(offer!=null&&offer.getProductOfferingId()!=null){
                Integer offerId = offer.getProductOfferingId();
                if(req.getObject_type().equals("0")){
                    //user order product
                    List<CoProd> list=prodCmp.queryProdByOfferId(offerId, EnumCodeDefine.PROD_OBJECTTYPE_DEV, req.getStart(), req.getLimit());
                    totalCount = prodCmp.queryProdCountByOfferId(offerId,EnumCodeDefine.PROD_OBJECTTYPE_DEV);
                    List<SUser> all=new ArrayList<SUser>();
                    for(CoProd prod:list){
                        if(!CommonUtil.isValid(prod.getObjectId())){
                            continue;
                        }
                        List<SUser> users= queryUserInfo(prod.getObjectId(),null);
                        if(!CommonUtil.isEmpty(users)){
                            all.addAll(users);
                        }
                    }
                    userList.setSUserList_Item(all.toArray(new SUser[all.size()]));
                    
                }else if(req.getObject_type().equals("1")){
                    //account order product
                    List<CoProd> list=prodCmp.queryProdByOfferId(offerId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT, req.getStart(), req.getLimit());
                    totalCount = prodCmp.queryProdCountByOfferId(offerId,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
                    List<SAccount> all=new ArrayList<SAccount>();
                    for(CoProd prod:list){
                        if(!CommonUtil.isValid(prod.getObjectId())){
                            continue;
                        }
                        SAccount sAcct = context.getComponent(AccountComponent.class).querySAccountInfo(prod.getObjectId());
                        if(!CommonUtil.isEmpty(sAcct)){
                            all.add(sAcct);
                        }
                     }                   
                    acctList.setSAccountList_Item(all.toArray(new SAccount[all.size()]));
                }
            }
            
    	}
    	return null;

    }
    
    private List<SCustomer> queryCustomerByCustIds(List<Long> custIds){
    	List<SCustomer> sCusts = new ArrayList<SCustomer>();
    	for(Long custId : custIds)
    	{
    		CmCustomer cmCust = context.get3hTree().loadCust3hBean(custId).getCustomer();
    		
    		Long partyId = pcCmp.queryPartyIdByCustId(custId);
     	    CmIndividualName individualName = pcCmp.queryInvidualNameByCustId(custId);
     	    CmIndividual individual = null;
     	    CmOrgName orgName = null;
     	    CmPartyIdentity partyIdentity = null;
     	    if(CommonUtil.isValid(partyId))
   	      	{
   	         individual = pcCmp.queryCmIndividualByPartyId(partyId);
   	         orgName = pcCmp.queryCmOrgNameByPartyId(partyId);
   	         partyIdentity = pcCmp.queryPartyByIdentity(partyId);
   	        }
     	   SCustomer sCust = custCmp.sCustTransform(cmCust, individual, individualName, orgName, partyIdentity);
      	   sCusts.add(sCust);
    	}
    	return sCusts;
    }
    
    private Object[] queryByCustomerTypeAndCustName(String customerName , Integer customer_type,int start,int limit) throws SQLException 
    {
    	if(customer_type == null)
    	{
    		return null;
    	}
    	OrderCondition[]  order = new OrderCondition[]{new OrderCondition(CmCustomer.Field.custId)};
	     List<CmCustomer> custs = baseCmp.query(CmCustomer.class,order,
	    		  new IntRange(start + 1, start+limit),
	    		  new DBCondition(CmCustomer.Field.customerClass,customer_type));
	     //TODO 这里需要加上customer_name这个条件，目前DB变更还没做。
	     int count = baseCmp.queryCount(CmCustomer.class,new DBCondition(CmCustomer.Field.customerClass,customer_type));
	     
	     
    	List<Long> custIds = new ArrayList<Long>();
    	if(CommonUtil.isNotEmpty(custs))
    	{
    		for(CmCustomer cmCustomer : custs)
    		{
    			custIds.add(cmCustomer.getCustId());
    		}
    	}
    	if(CommonUtil.isEmpty(custIds))
	   		 return null;
    	
    	Object[] obj = new Object[2];
	    obj[0] = count;
	    obj[1] = custIds;
		return obj;
	}

//	private List<Long> queryByOfferId(Integer main_offering_id) 
//    {
//    	Long custId = null;
//		List<Long> custIds = new ArrayList<Long>();
//		if(!CommonUtil.isValid(main_offering_id))
//			return null;
//		ResultTable<CoProdInvObj> resultTable = new ResultTable<CoProdInvObj>(CoProdInvObj.Field.productId,
//				                       new DBCondition(CoProdInvObj.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV));
//		resultTable.addCondTable(CoProd.Field.productId, new DBCondition(CoProd.Field.productOfferingId, main_offering_id),
//				new DBCondition(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN));
//
//		List<CoProdInvObj> resultList = context.getComponent(ResultComponent.class).getResultList(resultTable);
//		if (CommonUtil.isEmpty(resultList))
//            return null;
//		for(CoProdInvObj objs:resultList)
//		{
//			Long userId = objs.getObjectId();
//			custId = this.queryByUserId(userId);
//			custIds.add(custId);
//		}
//		if(CommonUtil.isEmpty(custIds))
//			return null;
//		return custIds;
//	}

	

	private Long queryByPartyIden(String identity, Integer identity_type) 
    {
    	 Long custId =  null ;
	   	 if(!CommonUtil.isValid(identity_type) && !CommonUtil.isValid(identity))
	   		 return null;
	   	 if(CommonUtil.isValid(identity_type) && !CommonUtil.isValid(identity))
	   		 return null;
	   	 if(CommonUtil.isValid(identity_type) && CommonUtil.isValid(identity))
	   	 {
	   		 CmPartyIdentity where = new CmPartyIdentity();
	   		 where.setPartyIdentificationSpec(identity);
	   		 where.setPartyIdentificationType(identity_type);
	   		 CmPartyIdentity partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, identity),
	   		      new DBCondition(CmPartyIdentity.Field.partyIdentificationType, identity_type));
	   		 if(partyIdentity == null)
	   			 return null;
	   		 custId = partyIdentity.getPartyId();
	   	 }else{
	    	 CmPartyIdentity where = new CmPartyIdentity();
			 where.setPartyIdentificationSpec(identity);
//			 where.setPartyIdentificationType(identity_type);
			 CmPartyIdentity partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, identity));
			 if(partyIdentity == null)
				 return null;
			 custId = partyIdentity.getPartyId();
	   	 }
		 return custId;
    }

	private Object[] queryByFirstName(String first_name,int start,int limit) throws SQLException   
    {
    	 Long custId = null;
	   	 if(!CommonUtil.isValid(first_name))
	   		 return null;
	 	OrderCondition[]  order = new OrderCondition[]{new OrderCondition(CmIndividualName.Field.partyId)};
	     List<CmIndividualName> indiName = baseCmp.query(CmIndividualName.class,order,
	    		  new IntRange(start + 1, start+limit),
	    		  new DBCondition(CmIndividualName.Field.preferredGivenName,first_name));
	     
	     int count = baseCmp.queryCount(CmIndividualName.class,new DBCondition(CmIndividualName.Field.preferredGivenName,first_name));
	     
//	   	 List<CmIndividualName> indiName = baseCmp.query(where);
	   	 if(CommonUtil.isEmpty(indiName))
	   		 return null;
	   	 List<Long> custIds =  new ArrayList<Long>();
	   	 for(CmIndividualName indiNames:indiName)
	   	 {
	   		 Long partyId = indiNames.getPartyId();
	   		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
	   		 if(cust != null){
	   			 custId = cust.getCustId();
	   			 custIds.add(custId);
	   		 }
	   	 }
	   	 if(CommonUtil.isEmpty(custIds))
	   		 return null;
	   	 
	   	Object[] obj = new Object[2];
	    obj[0] = count;
	    obj[1] = custIds;
		return obj;
	}

	private Object[] queryByOrgFamilyName(String family_name,int start,int limit) throws SQLException  
    {
    	 Long custId = null;
	   	 if(!CommonUtil.isValid(family_name))
	   		 return null;
	   	 
	   	OrderCondition[]  order = new OrderCondition[]{new OrderCondition(CmOrgName.Field.partyId)};
	     List<CmOrgName> org = baseCmp.query(CmOrgName.class,order,
	    		  new IntRange(start + 1, start+limit),
	    		  new DBCondition(CmOrgName.Field.tradingName,family_name));
	     int count = baseCmp.queryCount(CmOrgName.class,new DBCondition(CmOrgName.Field.tradingName,family_name));
	     
	   	 if(CommonUtil.isEmpty(org))
	   		 return null;
	   	 List<Long> custIds  = new ArrayList<Long>();
	   	 for(CmOrgName orgs:org)
	   	 {
	   		 Long partyId = orgs.getPartyId();
	   		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
	   		 if(cust != null){
	   			 custId = cust.getCustId();
	   			 custIds.add(custId);
	   		 }
	   	 }
	   	 if(CommonUtil.isEmpty(custIds))
	   		 return null;
	   	 
	   	 
	   	Object[] obj = new Object[2];
	    obj[0] = count;
	    obj[1] = custIds;
		return obj;
	}

	private Object[] queryByIndiFamilyName(String family_name,int start,int limit) throws SQLException 
    {
    	 Long custId = null;
	   	 if(!CommonUtil.isValid(family_name))
	   		 return null;
	   	 
	   	 OrderCondition[]  order = new OrderCondition[]{new OrderCondition(CmIndividualName.Field.partyId)};
	     List<CmIndividualName> individual = baseCmp.query(CmIndividualName.class,order,
	    		  new IntRange(start + 1, start+limit),
	    		  new DBCondition(CmIndividualName.Field.familyNames,family_name));
	     int count = baseCmp.queryCount(CmIndividualName.class,new DBCondition(CmIndividualName.Field.familyNames,family_name));
	     
	   	 if(CommonUtil.isEmpty(individual))
	   		 return null;
	   	 List<Long> custIds  = new ArrayList<Long>();
	   	 for(CmIndividualName partys:individual)
	   	 {
	   		 Long partyId = partys.getPartyId();
	   		 CmCustomer cust = custQuery.queryCustomerByPartyId(partyId);
	   		 if(cust != null){
	   			 custId = cust.getCustId();
	   			 custIds.add(custId);
	   		 }
	   	 }
	   	 if(CommonUtil.isEmpty(custIds))
	   		 return null;
	   	 
	   	 
	   	Object[] obj = new Object[2];
	    obj[0] = count;
	    obj[1] = custIds;
		return obj;
	}

	private List<CaAccount> queryByAcctName(String AccountName,Integer start,Integer limit)
    {
    	Long custId = null;
	     if(!CommonUtil.isValid(AccountName))
	    	return null;
	      
	      OrderCondition[]  order = new OrderCondition[]{new OrderCondition(CaAccount.Field.acctId)};
	      List<CaAccount> accts = baseCmp.query(CaAccount.class,order,
	    		  new IntRange(start + 1, start+limit),
	    		  new DBCondition(CaAccount.Field.name,AccountName));
//	     int count = baseCmp.queryCount(CaAccount.class,new DBCondition(CaAccount.Field.name,AccountName));
	     
	     return accts;
	}

	private Long queryByAcctId(Long acctId)
	 {
	 	Long custId = null;
	     if (!CommonUtil.isValid(acctId))
	         return null;
	     try
	     {
	         custId = context.get3hTree().loadAcct3hBean(acctId).getCustId();
	     }
	     catch(IMS3hNotFoundException e)
	     {
	         return null;
	     }
	    return custId;
	 }

    /**
     * @Description: 排序操作
     */
    private List<SCustomer> sortSCustomers(List<SCustomer> sCusts)
    {
        SCustomer[] custArr = sCusts.toArray(new SCustomer[sCusts.size()]);
        for (int i = 0; i < custArr.length - 1; i++)
        {
            for (int j = i + 1; j < custArr.length; j++)
            {
                if (custArr[i].getCust_id() > custArr[j].getCust_id())
                {
                    SCustomer temp = custArr[i];
                    custArr[i] = custArr[j];
                    custArr[j] = temp;
                }
            }
        }
        return sCusts;
    }

    private boolean custIsContaitedInList(CmCustomer cust, List<SCustomer> sCusts)
    {
        if (cust == null)
        {
            return true;
        }
        for (SCustomer sCust : sCusts)
        {
            if (sCust == null)
            {
                continue;
            }
            if (cust.getCustId().equals(sCust.getCust_id()))
            {
                return true;
            }
        }
        return false;

    }

    
    /**
     * 更具userId,phoneId 查询用户列表
     * xieqr 2012-3-13
     * @param userId
     * @param phoneId
     */
    private List<SUser> queryUserInfo(Long userId, String phoneId)
    {   
        List<SUser> users = new ArrayList<SUser>();
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
                SUser sUser = context.getComponent(UserComponent.class).sUserTransform(res,identity, acctRes, null, null, lifeCycle);
                if(sUser!= null)
                {
                    users.add(sUser);
                }
            }
           
        }
        
        if( CommonUtil.isValid(phoneId)&& !isInSUserList(phoneId,null , users))
        { 
            
            CmResource res=null;
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
        return users;

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
    
    
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryCustBaseInfoResponse req = new Do_queryCustBaseInfoResponse();

        req.setScustomrList(custList);
        req.setSContactList(contList);
        req.setTotalCount(totalCount);
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
		SQueryCustBaseInfoReq req = (SQueryCustBaseInfoReq) input[0];
		
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
//    	list.add(context.get3hTree().load3hBean(req.getCust_id(), req.getAcct_id(), req.getUser_id(), req.getPhone_id()));
		return list;
		
	}

}
