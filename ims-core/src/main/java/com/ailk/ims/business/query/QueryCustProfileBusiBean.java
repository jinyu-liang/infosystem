package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmOrganization;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustProfileResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

/**
 * @Description: 依据客户编号查询客户的基本信息，联系信息，账户信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author hunan
 * @Date 2011-10-21
 */
public class QueryCustProfileBusiBean extends BaseBusiBean
{

    private CustomerComponent custCmp;
    // private UserComponent userCmp;
    private AccountComponent acctCmp;
    private BaseComponent baseCmp;

    @Override
    public void init(Object... input) throws IMSException
    {
        custCmp = context.getComponent(CustomerComponent.class);
        // userCmp = context.getComponent(UserComponent.class);
        acctCmp = context.getComponent(AccountComponent.class);
        baseCmp = context.getComponent(BaseComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        Long custId = (Long) input[0];
        if (!CommonUtil.isValid(custId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "cust_id");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        Long custId = (Long) input[0];
        SCustomer sCust = this.queryCustBasicInfo(custId);
        SContactList sConts = null;
        SAccountList sAccts = null;
        if (sCust != null)
        {
            sConts = context.getComponent(ContactComponent.class).queryCustomerContactsByCustId(sCust.getCust_id());
            sAccts = this.queryAccountList(sCust.getCust_id());
        }
        return new Object[] { sCust, sConts, sAccts };
    }

    private SAccountList queryAccountList(Long custId)
    {
    	SAccountList list = new SAccountList();
//    	CmCustomer dmCust = baseCmp.querySingle(CmCustomer.class,
//    			new DBCondition(CmCustomer.Field.custId, custId));
    	List<CaCustomerRel> caCustomerRelList = baseCmp.query(CaCustomerRel.class, 
    			new DBCondition(CaCustomerRel.Field.custId, custId),
    			new DBCondition(CaCustomerRel.Field.relationshipType, 
    					EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
    	List<SAccount> accts = new ArrayList<SAccount>();
    	if(CommonUtil.isNotEmpty(caCustomerRelList))
    	{
    		for(int i = 0;i<caCustomerRelList.size();i++)
    		{
    			CaCustomerRel caCustomerRel = caCustomerRelList.get(i);
    			Long acctId = caCustomerRel.getAcctId();
    			CaAccount dmAcct =context.get3hTree().loadAcct3hBean(acctId).getAccount();
    			CaCustomInv custInv = baseCmp.querySingle(CaCustomInv.class, 
    					new DBCondition(CaCustomInv.Field.acctId, acctId));
    			CaAccountExt acctAttri = baseCmp.querySingle(CaAccountExt.class, 
    					new DBCondition(CaAccountExt.Field.acctId, acctId));
    			if(dmAcct!=null && caCustomerRel!=null )
    			{
    				SAccount acct = acctCmp.sAccountTransform(dmAcct, custInv, acctAttri, caCustomerRel);
    	            accts.add(acct);
    			}
    		}
    	}
    	
    	if(CommonUtil.isNotEmpty(accts))
    	{
    		list.setSAccountList_Item(accts.toArray(new SAccount[accts.size()]));
    		return list;
    	}
    	
    	return null;
    }



    private SCustomer queryCustBasicInfo(Long custId)
    {
        CmCustomer dmCust =context.get3hTree().loadCust3hBean(custId).getCustomer();
        
        SCustomer customer = new SCustomer();
        if (IMSUtil.isPersonCust(dmCust.getCustomerClass()))
        {
            customer = this.queryIndivadulCustInfo(custId);
        }
        else
        {
            customer = this.queryOrgCustInfo(custId);
        }
        return customer;
    }

    private SCustomer queryOrgCustInfo(Long custId)
    {
    	CmCustomer dmCust = baseCmp.querySingle(CmCustomer.class, 
    			new DBCondition(CmCustomer.Field.custId, custId));
//    	CmPartyRole cmPartyRole = baseCmp.querySingle(CmPartyRole.class, 
//    			new DBCondition(CmPartyRole.Field.partyRoleId, custId));
//    	Long partyId = null;
//    	
//    	if(cmPartyRole != null)
//    	{
//    		partyId = cmPartyRole.getPartyId();
//    	}
    	CmOrganization cmOrganization = null;
    	CmOrgName orgName = null;
    	CmPartyIdentity partyIdentity = null;
    	if(dmCust != null)
    	{
    		cmOrganization = baseCmp.querySingle(CmOrganization.class, 
    				new DBCondition(CmOrganization.Field.partyId, custId));
    		orgName = baseCmp.querySingle(CmOrgName.class, 
    				new DBCondition(CmOrgName.Field.partyId, custId));
    		partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, 
    				new DBCondition(CmPartyIdentity.Field.partyId, custId));
    	}
    	
    	//modify by qrxie remove partyIdentity!=null condition 2012-2-20
    	if(orgName!= null && cmOrganization!=null && dmCust!=null )
    	{
    		SCustomer cust = custCmp.sCustTransform(dmCust, null, null, orgName, partyIdentity);
    		return cust;
    	}
    	
    	return null;
    }

    private SCustomer queryIndivadulCustInfo(Long custId)
    {
    	CmCustomer dmCust = baseCmp.querySingle(CmCustomer.class, 
    			new DBCondition(CmCustomer.Field.custId, custId));
//    	CmPartyRole cmPartyRole = baseCmp.querySingle(CmPartyRole.class, 
//    			new DBCondition(CmPartyRole.Field.partyRoleId, custId));
//    	Long partyId = null;
    	CmIndividualName indiName = null;
    	CmIndividual indi = null;
    	CmPartyIdentity partyIdentity = null;
    	if(dmCust != null)
    	{
//    		partyId = cmPartyRole.getPartyId();
    		indi = baseCmp.querySingle(CmIndividual.class, 
    				new DBCondition(CmIndividual.Field.partyId, custId));
    		indiName = baseCmp.querySingle(CmIndividualName.class, 
    				new DBCondition(CmIndividualName.Field.partyId, custId));
    		partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, 
    				new DBCondition(CmPartyIdentity.Field.partyId, custId));
    	}
    	//modify by qrxie remove partyIdentity!=null condition 2012-2-20
    	if(indi!=null && indiName!=null && dmCust!=null)
    	{
    		SCustomer cus = custCmp.sCustTransform(dmCust, indi, indiName, null, partyIdentity);
    		return cus;
    	}
    	
    	return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryCustProfileResponse resp = new Do_queryCustProfileResponse();
        resp.setSCustomer((SCustomer) result[0]);
        resp.setSContactList((SContactList) result[1]);
        resp.setSAccountList((SAccountList) result[2]);
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	Long custId = (Long) input[0];
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadCust3hBean(custId));
        return list;
    }

}
