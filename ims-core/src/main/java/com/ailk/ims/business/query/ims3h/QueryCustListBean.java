package com.ailk.ims.business.query.ims3h;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.component.ContactComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.ims3h.Cust3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmOrganization;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.AccountRet;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryRet;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.CustomerRet;
import com.ailk.openbilling.persistence.imsinner.entity.Do_custQueryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;

/**
 * @Description 优化后的查询客户信息接口
 * @author yanchuan
 * @Date 2012-2-16
 */
public class QueryCustListBean extends BaseQuery3hBusiBean
{
    Boolean isCmIndividual ;
    Boolean isCmIndividualName ;
    Boolean isCmOrganization ;
    Boolean isCmOrganizationName;
    Boolean isCmCustExt;
    Boolean isCmCustomer;
    Boolean isCmPartyIdentity;
    Boolean isCmContactMedium;
    PartyComponent partyCmp;
    AccountQuery acctQry;
    @Override
    public void init(Object... input) throws BaseException
    {
     
    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }
    @Override
    public <T extends BaseQueryRet> void query(BaseQueryReqHolder baseReq,List<T> retList)throws BaseException
    {
        CustQueryReqHolder reqHolder = (CustQueryReqHolder)baseReq;
        if(reqHolder == null)
            return;
        CustQueryReq req = reqHolder.getCustReq();
        if(req == null)
            return;
        partyCmp = context.getComponent(PartyComponent.class);
        acctQry = context.getComponent(AccountQuery.class);
        isCmCustomer = req.getIsCmCustomer();
        if(isCmCustomer==null || isCmCustomer!=true)
            return;
        if(req.getCustIds() == null)
            return;
        List<Long> ids =  req.getCustIds();
        if(CommonUtil.isEmpty(ids))
            return;
        isCmCustomer = req.getIsCmCustomer();
        isCmIndividual = req.getIsCmIndividual();
        isCmIndividualName = req.getIsCmIndividualName();
        isCmOrganization = req.getIsCmOrganization();
        isCmOrganizationName = req.getIsCmOrganizationName();
        isCmPartyIdentity = req.getIsCmPartyIdentity();
        isCmCustExt = req.getIsCmCustExt();
        isCmContactMedium=req.getIsCmContactMedium();
        //由账户向上查
        if(CommonUtil.isNotEmpty(retList))
        {
            AccountRet ret = (AccountRet)retList.get(0);
            Long custId = ids.get(0);
            queryCust(ret ,custId);
            return;
        }
        //本次查询
        for(Long custId : ids)
        {
            CustomerRet ret = new  CustomerRet();
            if(!CommonUtil.isValid(custId))
                continue;
            queryCust(ret ,custId);
            //查询客户下的子帐户
            if(req.getIsQueryAccts()!=null && req.getIsQueryAccts()==true)
            {
                List<Long> acctIds = acctQry.queryAcctIdsByCustId(custId);
                if(CommonUtil.isNotEmpty(acctIds))
                { 
                    AcctQueryReq acctReq = null;
                    if(reqHolder.getCustReq() != null && reqHolder.getCustReq().getAcctReq()!= null)
                       acctReq = reqHolder.getCustReq().getAcctReq();
                    else
                       acctReq = new AcctQueryReq();
                    acctReq.setAcctIds(acctIds);
                    acctReq.setIsCaAccount(true);
                    AcctQueryReqHolder acctReqHolder = new AcctQueryReqHolder();
                    req.setIsCmCustomer(false);
                    acctReqHolder.setAcctReq(acctReq);
                    acctReqHolder.setCustReq(req);
                    QueryAcctListBean bean = null;
                    try
                    {
                        String beanName = BusiUtil.getMethodNode(9101).getChildByTagName(BusiUtil.TAG_BUSI).getAttribute(BusiUtil.ATTR_BEAN);
                        bean =(QueryAcctListBean)Class.forName(beanName).newInstance();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    bean.setContext(this.context);
                    bean.query(acctReqHolder, retList);
                    //将ret里的数据复制给retList
                    Query3hHelper.tranFormCust(retList, ret);
                }
            }
            //不需要查询客户下的子帐户
            else
            {
                retList.add((T)ret);
            }
            
        }
    }

   private <T extends BaseQueryRet> void queryCust(CustomerRet ret ,Long custId)
   {
       CmCustomer customer = null;
       //查询客户信息
       Cust3hBean cust3hbean = context.get3hTree().loadCust3hBean(custId);
       customer = cust3hbean.getCustomer();
       if(customer == null)
          return;
       this.custTransform(customer,ret);
       if(isCmIndividual!=null && isCmIndividual==true && IMSUtil.isPersonCust(customer.getCustomerClass()))
       {
           CmIndividual individual = partyCmp.queryInvidualByCustId(CommonUtil.Long2long(custId));
          
           this.individualTransform(individual, null, ret);
       }
       if(isCmIndividualName!=null && isCmIndividualName==true && IMSUtil.isPersonCust(customer.getCustomerClass()))
       {
           CmIndividualName individualName = partyCmp.queryInvidualNameByCustId(custId);
           this.individualTransform(null, individualName, ret);
       }
       if(isCmOrganization!=null && isCmOrganization==true && !IMSUtil.isPersonCust(customer.getCustomerClass()))
       {
           CmOrganization org =  partyCmp.queryCmOrgByPartyId(custId);
           this.orgTransform(org, null, ret);
       }
       if(isCmOrganizationName!=null && isCmOrganizationName==true && !IMSUtil.isPersonCust(customer.getCustomerClass()))
       {
           CmOrgName orgName =  partyCmp.queryCmOrgNameByPartyId(custId); 
           this.orgTransform(null, orgName, ret);
       }
       if(isCmPartyIdentity!=null && isCmPartyIdentity==true)
       {
           CmPartyIdentity partyIdentity = partyCmp.queryPartyByIdentity(custId);
           this.identityTransform(partyIdentity, ret);
       }
       if(isCmCustExt!=null && isCmCustExt==true)
       {
           CmCustExt dmExt = context.getComponent(CustomerComponent.class).queryCustExt(custId);
           this.custExtTransform(dmExt, ret);
       }
       //caohm 2012-02-24 add 
       //联系信息
       if(isCmContactMedium!=null && isCmContactMedium==true)
       {
    	   List<SContact> sContactList=new ArrayList<SContact>();
    	   
    	   List<CmContactMedium> cmContacts=context.getComponent(ContactComponent.class).queryCustContactMediums(custId, null);
    	   if(CommonUtil.isNotEmpty(cmContacts)){
	       		for(CmContactMedium contactMedium : cmContacts){
	       			SContact sContact=context.getComponent(ContactComponent.class).sContactTransfer(contactMedium);
	       			if(sContact!=null){
	       				sContactList.add(sContact);
	       			}
	       		}
       		}
	       this.sContactTransform(sContactList, ret);
       }
   }
    //caohm 2012-02-24 add
    private void sContactTransform(List<SContact> sContactList, CustomerRet ret){
    	if(!CommonUtil.isEmpty(sContactList)){
    		ret.setSCustomerConact(sContactList);
    	}
    }

	private void custTransform(CmCustomer cust,CustomerRet sCust)
    {
        if(cust == null)
            return;
        sCust.setCust_id(cust.getCustId());
        /*
        if (null != cust.getExt1())
            sCust.setExt1(cust.getExt1());
		*/
        if (null != cust.getCustomerClass())
            sCust.setCust_type(CommonUtil.IntegerToShort(cust.getCustomerClass()));

        if (null != cust.getCustomerType())
            sCust.setSub_cust_type(CommonUtil.IntegerToShort(cust.getCustomerType()));

        if (null != cust.getCustomerSegment())
            sCust.setCust_segment(CommonUtil.IntegerToShort(cust.getCustomerSegment()));
        if (null != cust.getRegionCode())
            sCust.setRegion_code(CommonUtil.IntegerToShort(cust.getRegionCode()));

        if (null != cust.getCountryId())
        {
            // 2011-12-15 hunan 修改
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
        if (null != cust.getTaxExemptionNo())
            sCust.setTax_exempt_code(cust.getTaxExemptionNo());
        sCust.setCustomer_name(cust.getCustomerName());
    }
    
    private void individualTransform(CmIndividual individual,CmIndividualName individualName,CustomerRet sCust){
        if(individual != null)
        {
            if (null != individual.getBirthDate())
                sCust.setBirthday(DateUtil.getFormatDate(individual.getBirthDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
    
            if (null != individual.getGender())
                sCust.setGender(CommonUtil.IntegerToShort(individual.getGender()));
        }
        if(individualName != null)
        {
            if (null != individualName.getFormOfAddress())
                sCust.setCust_title(individualName.getFormOfAddress());

            if (null != individualName.getFamilyNames())
                sCust.setFamily_name(individualName.getFamilyNames());

            if (null != individualName.getPreferredGivenName())
                sCust.setFirst_name(individualName.getPreferredGivenName());

            if (null != individualName.getMiddleNames())
                sCust.setMiddle_name(individualName.getMiddleNames());

            if (null != individualName.getLanguageId())
                sCust.setLanguage(CommonUtil.IntegerToShort(individualName.getLanguageId()));
        }
    }

     private void orgTransform(CmOrganization org,CmOrgName orgName,CustomerRet sCust){
         if(null == orgName )
         {
             return;
         }
         if (null != orgName.getTradingName())
             sCust.setFamily_name(orgName.getTradingName());
         if (null != orgName.getLanguageId())
             sCust.setLanguage(CommonUtil.IntegerToShort(orgName.getLanguageId()));
     }
     
     private void identityTransform(CmPartyIdentity partyIdentity,CustomerRet sCust){
         if(null == partyIdentity)
             return;
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
         //caohm5 add 实名制信息
         if (null!=partyIdentity.getRealNameDate())
         {
             sCust.setReal_name_date(DateUtil.getFormatDate(partyIdentity.getRealNameDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
         }
         if (null!=partyIdentity.getRealNameSts())
         {
             sCust.setReal_name_sts(partyIdentity.getRealNameSts());
         }
        
     }
     
     private void custExtTransform(CmCustExt dmExt,CustomerRet sCust){
         if(null == dmExt)
             return;
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

    
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<CustomerRet> retList = (List<CustomerRet>)result[0];
        
        Do_custQueryResponse resp = new Do_custQueryResponse();
        resp.setCustList(retList);
        return resp;
    }

    @Override
    public <T extends BaseQueryRet>List createRet()
    {
        return new ArrayList<CustomerRet>();
    }

    @Override
    public void setMain(BaseQueryReqHolder reqHolder)
    {
        CustQueryReqHolder req = (CustQueryReqHolder)reqHolder;
        req.getCustReq().setIsCmCustomer(true);
    }
   

    
    
}
