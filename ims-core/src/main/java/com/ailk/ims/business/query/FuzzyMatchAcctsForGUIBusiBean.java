package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyAcctsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SFuzzyAcctsForGUIReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;

/**
 * @Description 查询匹配的帐户集合，提供給界面使用
 * @author yanchuan
 * @Date 2012-2-23
 */
public class FuzzyMatchAcctsForGUIBusiBean extends BaseBusiBean {
    private BaseComponent baseCmp;
    private AccountComponent acctCmp;
    private UserComponent userCmp;

    @Override
    public void init(Object... input) throws IMSException
    {
        baseCmp = context.getComponent(BaseComponent.class);
        acctCmp = context.getComponent(AccountComponent.class);
        userCmp = context.getComponent(UserComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SFuzzyAcctsForGUIReq req = (SFuzzyAcctsForGUIReq) input[0];
        SAccount[] accts = this.FuzzyMatchAccts(req);
        SAccountList list = new SAccountList();
        if (accts != null)
        {
            list.setSAccountList_Item(accts);
        }
        return new Object[] { list };

    }

    // private SAccount[] FuzzyMatchAccts(SFuzzyAcctsForGUIReq req)
    // {
    // Long acctId = req.getGui_acct_id();
    // String acctName = req.getAcct_name();
    // Long custId = req.getGui_cust_id();
    // String identity = req.getIdentity();
    // Integer identityType = req.getIdentity_type();
    // Integer offerId = req.getOffering_id();
    // String firstName = req.getFirst_name();
    // String middleName = req.getMiddle_name();
    // String familyName = req.getFamily_name();
    // String phoneId = req.getGui_phone_id();
    //
    // DBJoinCondition join = new DBJoinCondition(CaAccount.class);
    // join.innerJoin(CaCustomerRel.class, new DBRelation(CaAccount.Field.acctId, CaCustomerRel.Field.acctId));
    // join.innerJoin(CmPartyRole.class, new DBRelation(CaCustomerRel.Field.custId, CmPartyRole.Field.partyRoleId));
    // join.leftJoin(CmOrgName.class, new DBRelation(CmPartyRole.Field.partyId, CmOrgName.Field.partyId));
    // join.leftJoin(CmIndividual.class, new DBRelation(CmPartyRole.Field.partyId, CmIndividual.Field.partyId));
    // join.leftJoin(CmIndividualName.class, new DBRelation(CmPartyRole.Field.partyId, CmIndividualName.Field.partyId));
    //
    // List<DBCondition> cons = new ArrayList<DBCondition>();
    // if (CommonUtil.isValid(custId))
    // {
    // DBCondition con = new DBCondition(CaCustomerRel.Field.custId, custId);
    // cons.add(con);
    // }
    // if (CommonUtil.isValid(acctId))
    // {
    // DBCondition con = new DBCondition(CaAccount.Field.acctId, acctId);
    // cons.add(con);
    // }
    // if (CommonUtil.isValid(acctName))
    // {
    // DBCondition con = new DBCondition(CaAccount.Field.name, acctName);
    // cons.add(con);
    // }
    // if (CommonUtil.isValid(identity) && identityType != null)
    // {
    // join.innerJoin(CmPartyIdentity.class, new DBRelation(CmPartyRole.Field.partyId, CmPartyIdentity.Field.partyId));
    // DBCondition conIden = new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec, identity);
    // DBCondition conType = new DBCondition(CmPartyIdentity.Field.partyIdentificationType, identityType);
    // cons.add(conType);
    // cons.add(conIden);
    // }
    //
    // if (CommonUtil.isValid(familyName))
    // {
    // DBCondition con = new DBCondition(CmIndividualName.Field.familyNames, familyName);
    // // DBCondition con1 = new DBCondition(CmOrgName.Field.tradingName, familyName);
    // cons.add(con);
    // }
    // if (CommonUtil.isValid(firstName))
    // {
    // DBCondition con = new DBCondition(CmIndividualName.Field.preferredGivenName, firstName);
    // cons.add(con);
    // }
    // if (CommonUtil.isValid(middleName))
    // {
    // DBCondition con = new DBCondition(CmIndividualName.Field.middleNames, middleName);
    // cons.add(con);
    // }
    //
    // if (CommonUtil.isValid(phoneId))
    // {
    // join.leftJoin(CaAccountRes.class, new DBRelation(CaAccount.Field.acctId, CaAccountRes.Field.acctId));
    // join.leftJoin(CmResIdentity.class, new DBRelation(CaAccountRes.Field.resourceId, CmResIdentity.Field.resourceId));
    // //ljc delete ,new DBRelation(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)
    // DBCondition con = new DBCondition(CmResIdentity.Field.identity, phoneId);
    // cons.add(con);
    // }
    //
    // if (CommonUtil.isValid(offerId))
    // {
    // join.innerJoin(CoProdInvObj.class, new DBRelation(CaAccount.Field.acctId, CoProdInvObj.Field.objectId),
    // new DBRelation(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
    // join.innerJoin(CoProd.class, new DBRelation(CoProdInvObj.Field.productId, CoProd.Field.productId));
    // DBCondition con = new DBCondition(CoProd.Field.productOfferingId, offerId);
    // cons.add(con);
    // }
    // DBCondition[] conArr = cons.toArray(new DBCondition[cons.size()]);
    //
    // // DBOrCondition or = new DBOrCondition(conA)
    // List<Map> maps = null;
    // if (conArr.length > 1)
    // {
    // maps = baseCmp.queryJoin(join, new DBOrCondition( conArr));
    // }
    // else
    // {
    // if (conArr.length == 1)
    // {
    // maps = baseCmp.queryJoin(join, conArr);
    // }
    // }
    //
    // if (maps == null)
    // {
    // return null;
    // }
    // List<CaAccount> sets = new ArrayList<CaAccount>();
    // for (Map map : maps)
    // {
    // CaAccount acct = (CaAccount) map.get(CaAccount.class);
    // // 2011-11-7 去重复
    // boolean isAcctExist = false;
    // for (CaAccount ca : sets)
    // {
    // if (ca != null && acct != null && ca.getAcctId().equals(acct.getAcctId()))
    // {
    // isAcctExist = true;
    // }
    // }
    // if (!isAcctExist)
    // {
    // sets.add(acct);
    // }
    // }
    //
    // List<SAccount> sAccts = new ArrayList<SAccount>();
    // for (CaAccount ca : sets)
    // {
    // if (ca == null)
    // {
    // continue;
    // }
    // SAccount sAcct = encapsulateSAccount(ca);
    // sAccts.add(sAcct);
    //
    // }
    //
    // // sort
    // // SAccount[] acctArr = sAccts.toArray(new SAccount[sAccts.size()]);
    // // for(int i = acctArr.length - 1 ; i>= 0 ; i --){
    // // if( acctArr[i]==null){
    // // sAccts.remove(i);
    // // }
    // // }
    // SAccount[] acctArr1 = sAccts.toArray(new SAccount[sAccts.size()]);
    // for (int i = 0; i < acctArr1.length - 1; i++)
    // {
    // for (int j = i + 1; j < acctArr1.length; j++)
    // {
    // // LogUtil.getLogger(FuzzyMatchCustsForGUIBusiBean.class).info(i+ "   "+j);
    // if (acctArr1[i] != null && acctArr1[j] != null && acctArr1[i].getAcct_id() > acctArr1[j].getAcct_id())
    // {
    // // LogUtil.getLogger(FuzzyMatchCustsForGUIBusiBean.class).info(custArr1[i].getCust_id() + " "
    // // +custArr1[j].getCust_id());
    // SAccount temp = acctArr1[i];
    // acctArr1[i] = acctArr1[j];
    // acctArr1[j] = temp;
    // }
    // }
    // }
    // // for(SAccount c :acctArr1){
    // // LogUtil.getLogger(FuzzyMatchCustsForGUIBusiBean.class).info(c.getAcct_id());
    // // }
    //
    // return acctArr1;
    // }

    private SAccount[] FuzzyMatchAccts(SFuzzyAcctsForGUIReq req)
    {
        List<SAccount> sAccts = new ArrayList<SAccount>();
        Long acctId = req.getGui_acct_id();
        String acctName = req.getAcct_name();
        
        Long custId = req.getGui_cust_id();
        
        String identity = req.getIdentity();
        Integer identityType = req.getIdentity_type();
        
        Integer offerId = req.getOffering_id();
        
        String firstName = req.getFirst_name();
        String middleName = req.getMiddle_name();
        
        String familyName = req.getFamily_name();
        
        String phoneId = req.getGui_phone_id();
        
        List<Long > acctIds = new ArrayList<Long>();
        if(CommonUtil.isValid(acctId))
        {
            acctIds.add(acctId);
        }
        CmPartyIdentity partyIdentity =null;
        if(CommonUtil.isValid(identity)&&identityType != null)
        {
             partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, 
                    new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec,identity));
           
        }else
        {
            partyIdentity = baseCmp.querySingle(CmPartyIdentity.class, 
                    new DBCondition(CmPartyIdentity.Field.partyIdentificationSpec,identity),
                    new DBCondition(CmPartyIdentity.Field.partyIdentificationType,identityType));
        }
        if(partyIdentity != null)
        {
            List<CaCustomerRel> custRels = acctCmp.queryCaCustRelByCustId(partyIdentity.getPartyId());
            if(custRels!= null)
            {
                for(CaCustomerRel rel : custRels)
                {
                    if(!isInAcctIds(rel.getAcctId(),acctIds))
                    {
                        acctIds.add(rel.getAcctId());
                    }
                }
            }
        }
        
        if(CommonUtil.isValid(custId))
        {
            List<CaCustomerRel> custRels = acctCmp.queryCaCustRelByCustId(custId);
            if(custRels!= null)
            {
                for(CaCustomerRel rel : custRels)
                {
                    if(!isInAcctIds(rel.getAcctId(),acctIds))
                    {
                        acctIds.add(rel.getAcctId());
                    }
                }
            }
        }
        
        if(CommonUtil.isValid(phoneId))
        {
          //2012-08-27 linzt 整理组件方法  采用load3hBean
            User3hBean userBean=null;
            Long resAcctId =null;
            try
            {
                userBean=context.get3hTree().loadUser3hBean(phoneId);
                resAcctId=userBean.getAcctId();
                if(CommonUtil.isValid(resAcctId) && !isInAcctIds(resAcctId, acctIds))
                {
                    acctIds.add(resAcctId);
                }
            }
            catch(IMS3hNotFoundException e)
            { 
            	imsLogger.error(e,e);
            }
        }
        
        if(CommonUtil.isValid(firstName)||CommonUtil.isValid(middleName)||CommonUtil.isValid(familyName))
        {
            List<DBCondition> conds = new ArrayList<DBCondition>();
            if(CommonUtil.isValid(firstName))
            {
                conds.add(new DBCondition(CmIndividualName.Field.preferredGivenName,firstName));
            }
            if(CommonUtil.isValid(middleName))
            {
                conds.add(new DBCondition(CmIndividualName.Field.middleNames, middleName));
            }
            if(CommonUtil.isValid(familyName))
            {
                conds.add(new DBCondition(CmIndividualName.Field.formattedName,familyName));
            }
            DBOrCondition orcond = new DBOrCondition(conds.toArray(new DBCondition[conds.size()]));
            
            List<CmIndividualName> indinames = baseCmp.query(CmIndividualName.class,orcond);
            List<Long> custIds = new ArrayList<Long>();
            if(indinames!= null)
            {
                for(CmIndividualName indiName : indinames)
                {
                    if(indiName!= null)
                    {
                        custIds.add(indiName.getPartyId());
                    }
                }
            }
            if(CommonUtil.isValid(familyName))
            {
                List<CmOrgName> orgNames = baseCmp.query(CmOrgName.class,
                        new DBCondition(CmOrgName.Field.tradingName,familyName)
                );
                if(orgNames != null)
                {
                    for(CmOrgName orgName : orgNames)
                    {
                        if(orgName!= null)
                        {
                            custIds.add(custId);
                        }
                    }
                }
            }
            
            if(!CommonUtil.isEmpty(custIds))
            {
                List<CaCustomerRel> custRels = baseCmp.query(CaCustomerRel.class,
                        new DBCondition(CaCustomerRel.Field.custId, custIds,Operator.IN));
                if(custRels != null)
                {
                   for(CaCustomerRel rel : custRels)
                   {
                       if(rel != null)
                       {
                           
                           acctIds.add(rel.getAcctId());
                       }
                   }
                }
            }
            
        }
        List<CaAccount> accts = new ArrayList<CaAccount>();
        if(!CommonUtil.isEmpty(acctIds))
        {
            
            accts = baseCmp.query(CaAccount.class, 
                    new DBCondition(CaAccount.Field.acctId,acctIds,Operator.IN));
        }
        if(CommonUtil.isValid(acctName))
        {
            List<CaAccount> cas = baseCmp.query(CaAccount.class,new DBCondition(CaAccount.Field.name,acctName));
            if(cas != null)
            {
                accts.addAll(cas);
            }
        }
        
        //暂时不提供，返回数据量太大
        if(CommonUtil.isValid(offerId))
        {
            
        }
        if(!CommonUtil.isEmpty(accts))
        {
            for(CaAccount ca : accts)
            {
                if(ca!=null)
                {
                    SAccount ac= encapsulateSAccount(ca);
                    sAccts.add(ac);
                }
            }
        }

        return sAccts.toArray(new SAccount[sAccts.size()]);
    }

    private boolean isInAcctIds(Long acctId, List<Long> acctIds)
    {
        if(!CommonUtil.isValid(acctId))
        {
            return true;
        }
        for(Long id : acctIds)
        {
            if(acctId.equals(id))
            {
                return true;
            }
        }
         return false;
    }

    /**
     * 将CaAccount封装成SAccount
     * 
     * @param ca
     * @return
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
        
        // ca.getPin();
        // ca.getCountyCode();
        // ca.getProvinceId();

        return sAcct;
    }

    @Override
    public List<CmBusi> createBusiRecord(Object[] input)
    {
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_fuzzyAcctsForGUIResponse resp = new Do_fuzzyAcctsForGUIResponse();
        if (result == null)
        {
            return resp;
        }
        resp.setSAccountList((SAccountList) result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

 

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        // TODO Auto-generated method stub                                     
         return null;
    }

}
