package com.ailk.ims.business.query4gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.QueryCustomerHistoryCompant;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerHistoryForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SProductOrderHistory;
import com.ailk.openbilling.persistence.imsinner.entity.SProductOrderListHistory;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerHistoryReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomerList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserList;

/**
 * @Description
 * Require GUI to view the customer profile support for several search index, such as CA, BA, Mobile, etc.
 * @author zengqm
 * @Date 2012-2-20
 */
public class QueryCustomerHistoryBean extends BaseBusiBean
{
    private PartyComponent partyCmp;
    private QueryCustomerHistoryCompant hisCmp;
    
    private SContactList  contList = new SContactList();
    private SCustomerList custList = new SCustomerList();
    private SAccountList acctList = new SAccountList();
    private SUserList userList = new SUserList();
    private SProductOrderListHistory productList = new SProductOrderListHistory();
    
    private List<SUser> users = new ArrayList<SUser>();
    private List<SAccount> accts = new ArrayList<SAccount>();
    private List<SCustomer> custs = new ArrayList<SCustomer>();
    private List<SProductOrderHistory> products = new ArrayList<SProductOrderHistory>();
    private Date hisDate;

    @Override
    public void init(Object... input) throws IMSException
    {
        partyCmp = context.getComponent(PartyComponent.class);
        hisCmp = context.getComponent(QueryCustomerHistoryCompant.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryCustomerHistoryReq req = (SQueryCustomerHistoryReq) input[0];
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQueryCustomerHistoryReq");
        }
        if (!CommonUtil.isValid(req.getGui_acct_id())
                && !CommonUtil.isValid(req.getGui_cust_id())
                && !CommonUtil.isValid(req.getGui_phone_id())
                && !CommonUtil.isValid(req.getGui_user_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id , user_id , cust_id , phone_id ");
        }
        if (CommonUtil.isEmpty(req.getGui_his_date()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "his_date");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryCustomerHistoryReq req = (SQueryCustomerHistoryReq) input[0];
        Long custId = req.getGui_cust_id();
        Long acctId = req.getGui_acct_id();
        Long userId = req.getGui_user_id();
        String phoneId = req.getGui_phone_id();
        hisDate = req.getGui_his_date();
        
        if(CommonUtil.isValid(userId)){
        	query3hInfoByUserId(userId);
        }
        if(CommonUtil.isValid(phoneId)){
        	query3hInfoByPhoneId(phoneId);
        }
        if(CommonUtil.isValid(acctId)){
        	query3hInfoByAcctId(acctId);
        }
        if(CommonUtil.isValid(custId)){
        	query3hInfoByCustId(custId);
        }
        return null;
    }

   
 
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryCustomerHistoryForGUIResponse req = new Do_queryCustomerHistoryForGUIResponse();
        req.setSCustomerList(custList);
        req.setSContactList(contList);
        req.setSAccountList(acctList);
        req.setSUserList(userList);
        req.setSProductList(productList);
        return req;
    }

    @Override
    public void destroy()
    {
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryCustomerHistoryReq req = (SQueryCustomerHistoryReq) input[0];
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().load3hBean(req.getGui_cust_id(),req.getGui_acct_id(),req.getGui_user_id(),req.getGui_phone_id()));
        return list;
	}
	/**
	 * zengqm
	 * 
	 * @param userId
	 */
	private void query3hInfoByUserId(Long userId)
    {
        CmResource res=hisCmp.queryUserHistoryByUserId(userId,hisDate);
        CmResIdentity cmResIdentity = hisCmp.queryResIdentityHistory(userId, null,hisDate);
        queryCmResourceExt(res,cmResIdentity==null?null:cmResIdentity.getIdentity());
        userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
        acctList.setSAccountList_Item(accts.toArray(new SAccount[accts.size()]));
        queryProductOrderByUserORAcctId(userId);
        
    }
	private void query3hInfoByPhoneId(String phoneId)
    {
        CmResource res=hisCmp.queryUserHistoryByPhoneId(phoneId,hisDate);
        queryCmResourceExt(res,phoneId);
        userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
        acctList.setSAccountList_Item(accts.toArray(new SAccount[accts.size()]));
        if(res!=null){
        	queryProductOrderByUserORAcctId(res.getResourceId());
        }
        
    }
	/**
	 * 账户找客户不走caCustomer这层关系
	 * caohm5 2012-04-27 modify
	 * @param acctId
	 */
	private void query3hInfoByAcctId(Long acctId)
	{
		//查询帐户
		queryAccountInfo(acctId);
		//查询客户
//		CaCustomerRel caCustomerRel = hisCmp.queryCaCustomerRelByAcctId(acctId, hisDate);
		CaAccount ca=hisCmp.queryAccountHistoryById(acctId,hisDate);
		
		
//    	if(caCustomerRel!=null)
//    	{
//    		queryCustomerInfo(caCustomerRel.getCustId());
//    	}
		if(ca!=null){
			queryCustomerInfo(ca.getCustId());
		}
    	//查询用户
    	List<CmResource> list = hisCmp.queryCmResourceByAcctId(acctId, hisDate);
    	for(CmResource cmResource : list)
    	{
    		 CmResIdentity cmResIdentity = hisCmp.queryResIdentityHistory(cmResource.getResourceId(), null,hisDate);
    		 CaAccountRes acctRes =hisCmp.queryCaAccountRes(cmResource.getResourceId(),hisDate);
             CmResLifecycle lifeCycle=hisCmp.queryCmResLifecycle(cmResource.getResourceId(),hisDate);
             String phoneId = cmResIdentity==null?null:cmResIdentity.getIdentity();
             SUser sUser = hisCmp.sUserTransformHistory(cmResource, phoneId, acctRes, null, null, lifeCycle,hisDate);
             if(sUser!= null)
             {
                 users.add(sUser);
                 
             }
    	}
    	acctList.setSAccountList_Item(accts.toArray(new SAccount[accts.size()]));
    	userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
    	queryProductOrderByUserORAcctId(acctId);
	}
	
	/**
	 * 
	 * @param custId
	 */
	private void query3hInfoByCustId(Long custId){
		//查询客户
		queryCustomerInfo(custId);
		//查询帐户
		List<CaAccount> list = hisCmp.queryCaAccountByCustId(custId,hisDate);
		for(CaAccount caAccount : list)
		{
			queryAccountInfo(caAccount.getAcctId());
			List<CmResource> listRes = hisCmp.queryCmResourceByAcctId(caAccount.getAcctId(), hisDate);
	    	for(CmResource cmResource : listRes)
	    	{
	    		 CmResIdentity cmResIdentity = hisCmp.queryResIdentityHistory(cmResource.getResourceId(), null,hisDate);
	    		 CaAccountRes acctRes =hisCmp.queryCaAccountRes(cmResource.getResourceId(),hisDate);
	             CmResLifecycle lifeCycle=hisCmp.queryCmResLifecycle(cmResource.getResourceId(),hisDate);
	             String phoneId = cmResIdentity==null?null:cmResIdentity.getIdentity();
	             SUser sUser = hisCmp.sUserTransformHistory(cmResource, phoneId, acctRes, null, null, lifeCycle,hisDate);
	             if(sUser!= null)
	             {
	                 users.add(sUser);
	                 
	             }
	    	}
		}
		acctList.setSAccountList_Item(accts.toArray(new SAccount[accts.size()]));
		userList.setSUserList_Item(users.toArray(new SUser[users.size()]));
	}
	
	
	/**
	 * caohm modify 2012-04-27
	 * 账户找客户不走caCustomerRel这层关系
	 * @param res
	 * @param phoneId
	 */
	private void queryCmResourceExt(CmResource res,String phoneId){
		if(res!=null)
        {
            CaAccountRes acctRes =hisCmp.queryCaAccountRes(res.getResourceId(),hisDate);
            CmResLifecycle lifeCycle=hisCmp.queryCmResLifecycle(res.getResourceId(),hisDate);
            SUser sUser = hisCmp.sUserTransformHistory(res, phoneId, acctRes, null, null, lifeCycle,hisDate);
            if(sUser!= null)
            {
                users.add(sUser);
                
            }
            if(acctRes!=null){
            	queryAccountInfo(acctRes.getAcctId());
//            	CaCustomerRel caCustomerRel = hisCmp.queryCaCustomerRelByAcctId(acctRes.getAcctId(), hisDate);
//            	if(caCustomerRel!=null){
//            		queryCustomerInfo(caCustomerRel.getCustId());
//            	}
            	CaAccount ca=hisCmp.queryAccountHistoryById(acctRes.getAcctId(), hisDate);
            	if(ca!=null){
            		queryCustomerInfo(ca.getCustId());
            	}
            }
        }
		
	}
	//caohm5 modify 2012-04-27
	//CaCustomerRel查询去掉
	 private void queryAccountInfo(Long acctId)
	    {
	        if (CommonUtil.isValid(acctId))
	        {
	            CaAccount ca=hisCmp.queryAccountHistoryById(acctId,hisDate);
	            CaAccountExt acctAttri =hisCmp.queryCaAccountAttribute(acctId,hisDate);
//	            CaCustomerRel custRel = hisCmp.queryCaCustomerRel(acctId,hisDate);
	            CaCustomerRel custRel =null;
	            CaCustomInv custInv = hisCmp.queryCaCustomizedInvoice(acctId,hisDate);
	            SAccount sAcct = hisCmp.sAccountTransformHistory(ca, custInv, acctAttri,custRel,hisDate);
	            if( sAcct != null)
	            {
	            	accts.add(sAcct);
	                
	            }
	        }
	    }
	

	    private void queryCustomerInfo(Long custId) throws BaseException
	    {
	        
	        List<Long> custIds = new ArrayList<Long>();
	        if (CommonUtil.isValid(custId))
	        {
	        	custIds.add(custId);
	        }
	        
	        CmCustomer dmCust = hisCmp.queryCustomerHistory(custId,hisDate);
	        
	        if (dmCust == null)
	        {
	            return;
	        }
	        
	        CmIndividualName indiName = null;
	        CmIndividual indi = null;
	        CmOrgName orgName = null;
	        CmPartyIdentity identity = hisCmp.queryCmPartyIdentity(custId, hisDate);
	        if (IMSUtil.isPersonCust(dmCust.getCustomerType()))
	        {
	            Long partyId = partyCmp.queryPartyIdByCustId(custId);
	            if(CommonUtil.isValid(partyId))
	            	indiName = hisCmp.queryCmIndividualName(partyId, hisDate);
	            
	            if(CommonUtil.isValid(partyId))
	            	indi = hisCmp.queryCmIndividual(partyId, hisDate);
	        }
	        else
	        {
	            orgName =hisCmp.queryCmOrgName(custId,hisDate);
	        }
	        SCustomer cust = hisCmp.sCustTransformHistory(dmCust, indi, indiName, orgName, identity, hisDate);
	        if (cust != null)
	        {
	            custs.add(cust);
	        }
	        if( !CommonUtil.isEmpty(custs))
	        {
	            custList.setSCustomerList_Item(custs.toArray(new SCustomer[custs.size()]));
	            contList = hisCmp.queryCustomerContactsByCustIdsHistory(custIds,hisDate);
	        }
	    }
	    /**
	     * zengqm 查询产品信息
	     * @param userId
	     */
	    private void queryProductOrderByUserORAcctId(Long userId){
	    	List<CoProd> resultList = hisCmp.queryUserNotMainProducts(userId,hisDate);
	    	if(resultList!=null){
	    		for(CoProd coProd:resultList){
		    		SProductOrderHistory productOrder = hisCmp.sProductOrderTransformHistory(coProd,hisDate);
		    		if(productOrder!=null){
		    			products.add(productOrder);
		    		}
		    	}
		    	productList.setItem(products.toArray(new SProductOrderHistory[products.size()]));
	    	}
	    	
	    }

	    


}
