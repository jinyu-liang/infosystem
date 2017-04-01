package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.helper.AcctHelper;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Cust3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmParty;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmPartyRole;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

/**
 * @Description: 客户组件，用于定义跟客户操作相关的各种方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-12011-08-02 hunan 增加modifyCustomer方法。 2011-08-02 wuyujie : 实例化SCustomer中的扩展字段 2011-08-05 zengxr move
 *       Tax_Exemption_No field to CM_CUSTOMER 2011-08-08 zengxr set address1-address4 of SContact 2011-08-18 wuyujie :
 *       party_id,party_role_id改成一致
 * @Date 2012-3-29 wangjt 修改isEmpty方法用来判断对象的问题 ，修改判断账期对象合法的方法调用
 * @Date 2012-3-30 wangjt 修改createCustExtParams方法的参数，上海版本屏蔽客户强制账期的处理
 * @Date 2012-04-25 yangjh sCustTransform中把新建对象放到判断后面
 * @Date 2012-09-18 yugb :[57408]API中字段清空方案
 */
public class CustomerComponent extends CustomerQuery
{
    public CustomerComponent()
    {
    }

    /**
     * @Description: 创建参与人角色
     * @author : wuyj
     * @date : 2011-9-26
     */
    private CmPartyRole createParyRole(SCustomer customer, long partyId) throws IMSException
    {
        CmPartyRole dmPartyRole = new CmPartyRole();
        // 2011-08-18 wuyujie : party_id,party_role_id改成一致
        dmPartyRole.setPartyRoleId(partyId);
        dmPartyRole.setPartyId(partyId);
        dmPartyRole.setPartyRoleName("Customer");
        dmPartyRole.setPartyRoleType(EnumCodeDefine.PARTY_ROLETYPE_CUSTOMER);
        dmPartyRole.setSts(EnumCodeDefine.PARTY_ROLESTS_EFFECTIVE);
        dmPartyRole.setSoNbr(context.getSoNbr());
        dmPartyRole.setCreateDate(IMSUtil.formatDate(customer.getCreate_date(), context.getRequestDate()));
        dmPartyRole.setExpireDate(IMSUtil.formatExpireDate(null));

        super.insert(dmPartyRole);
        return dmPartyRole;
    }

    /**
     * @Description: 创建客户完整信息。 这个方法是一套完整的流程，包括创建参与人、参与人角色、客户等。
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmCustomer createCustomer(SCustomer customer) throws IMSException
    {
        if (customer == null)
        {
            return null;
        }

        // 设置默认客户类型,将默认客户类型赋值到客户结构对象中 yanchaun 2011-11-22
        if (customer.getCust_type() == null)
        {
            customer.setCust_type(CommonUtil.IntegerToShort(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CUSTOMER_TYPE)));
        }

        PartyComponent partyCmp = context.getComponent(PartyComponent.class);
        // 1:创建客户（包括个人客户和集团客户）的参与人信息
        CmParty dmParty = partyCmp.createParty(customer);
        long partyId = dmParty.getPartyId();

        // 2:创建参与人的身份信息
        partyCmp.createIdentification(customer, partyId);

        if (IMSUtil.isPersonCust(customer.getCust_type().intValue()))
        {
            // 个人客户处理流程
            partyCmp.createIndividual(customer, partyId);
            partyCmp.createIndividualName(customer, partyId);
        }
        else
        {
            // 集团客户处理流程
            partyCmp.createOrganization(customer, partyId);
            partyCmp.createOrganizationName(customer, partyId);
        }
        // 4:创建参与人角色
        CmPartyRole dmPartyRole = createParyRole(customer, partyId);

        // 5:仅仅创建客户表(cm_customer)的信息
        CmCustomer cmCustomer = insertCmCustomer(customer, dmPartyRole.getPartyRoleId());

        // 6:处理扩展信息
        createCustExtParams(customer, cmCustomer.getCustId());

        return cmCustomer;
    }

    /**
     * @Description:实例化SCustomer中的扩展字段
     */
    private void createCustExtParams(SCustomer customer, Long custId)
    {
        if (customer.getList_ext_param() == null)
        {
            return;
        }

        ExtendParamList paramList = customer.getList_ext_param();
        if (CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
        {
            return;
        }

        String hierarchyBillingFlag = null;
        String billHandingCode = null;
        String custBMCallDetail = null;
        String custBMSummary = null;
        String emailBMCall = null;
        String emailBMSummary = null;
        String attrCustEmail = null;
        String faxBMCall = null;
        String faxBMSummary = null;
        String vatName = customer.getVat_name();

        String cycleType = null;
        String cycleUnit = null;
        String firstBillDay = null;
        for (ExtendParam param : paramList.getExtendParamList_Item())
        {
            if (CommonUtil.isEmpty(param.getParam_name()) || CommonUtil.isEmpty(param.getParam_value()))
            {
                continue;
            }

            String name = param.getParam_name();
            String value = param.getParam_value();
            if (ConstantDefine.EXTPARAM_CUST_HIERARCHYBILLINGFLAG.equals(name))
            {
                hierarchyBillingFlag = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_BILLHANDINGCODE.equals(name))
            {
                billHandingCode = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CUSTBMCALLDETAIL.equals(name))
            {
                custBMCallDetail = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CUSTBMSUMMARY.equals(name))
            {
                custBMSummary = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CYCLE_TYPE.equals(name))
            {
                cycleType = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CYCLE_UNIT.equals(name))
            {
                cycleUnit = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_START_BILL_DAY.equals(name))
            {
                firstBillDay = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_EMAILBMCALL.equals(name))
            {
                emailBMCall = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_EMAILBMSUMMARY.equals(name))
            {
                emailBMSummary = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_ATTRCUSTEMAIL.equals(name))
            {
                attrCustEmail = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_FAXBMCALL.equals(name))
            {
                faxBMCall = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_FAXBMSUMMARY.equals(name))
            {
                faxBMSummary = value;
            }
        }
        CmCustExt cmCustExt = new CmCustExt();
        cmCustExt.setCustId(custId);

        if (CommonUtil.isNotEmpty(hierarchyBillingFlag))
        {
            cmCustExt.setForceCycle(Integer.valueOf(hierarchyBillingFlag));
        }
        else
        {
            cmCustExt.setForceCycle(0);
        }

        if (!CommonUtil.isEmpty(billHandingCode))
        {
            cmCustExt.setBillFormatId(Integer.valueOf(billHandingCode));
        }

        if (!CommonUtil.isEmpty(custBMCallDetail))
        {
            cmCustExt.setDetailDeliver(Integer.valueOf(custBMCallDetail));
        }

        if (!CommonUtil.isEmpty(custBMSummary))
        {
            cmCustExt.setSummaryDeliver(Integer.valueOf(custBMSummary));
        }

        if (!CommonUtil.isEmpty(vatName))
        {
            cmCustExt.setVatName(vatName);
        }

        if (ProjectUtil.is_TH_AIS())// 上海没有客户强制账期的概念
        {
            // ForceCycle为1的时候处理specid
            if (cmCustExt.getForceCycle() != null && cmCustExt.getForceCycle().intValue() == 1)
            {
                // 对账期结构进行判断 yanchuan 20110817
                if (cycleType == null || cycleUnit == null || firstBillDay == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_BILL_CYCLE_NOT_FULL);
                }

                Integer specId = context.getComponent(AccountComponent.class).queryBillCycleSpecId(Integer.valueOf(cycleType),
                        Integer.valueOf(cycleUnit), Integer.valueOf(firstBillDay));
                if (specId != null)
                {
                    cmCustExt.setCycleSpecId(specId.longValue());
                }
            }
        }

        super.insert(cmCustExt);

        // 处理联系地址
        Date validDate = IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate());
        context.getComponent(ContactComponent.class).createExtContact(custId,EnumCodeDefine.CUST_CONTACT_TYPE,validDate, emailBMCall, faxBMCall,
                emailBMSummary, faxBMSummary, attrCustEmail);

    }

    /**
     * @Description: 仅仅创建客户表(cm_customer)的信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    private CmCustomer insertCmCustomer(SCustomer customer, long partyRoleId) throws IMSException
    {
        Date validDate = DateUtil.checkForNull(customer.getValid_date());
        if (validDate == null)
            validDate = context.getRequestDate();

        CmCustomer dmCust = new CmCustomer();
        dmCust.setCustId(partyRoleId);// custId,即partyrole_id
        // 设置默认客户类型 yanchaun 2011-11-22
        if (customer.getCust_type() != null)
            dmCust.setCustomerClass((int) customer.getCust_type());
        else
            dmCust.setCustomerClass(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CUSTOMER_TYPE));

        // 设置默认客户子类型 yanchaun 2011-11-22
        if (customer.getSub_cust_type() != null)
            dmCust.setCustomerType((int) customer.getSub_cust_type());
        else
            dmCust.setCustomerType(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CUSTOMER_SUB_TYPE));

        if (customer.getCust_segment() != null)
            dmCust.setCustomerSegment((int) customer.getCust_segment());
        else
            dmCust.setCustomerSegment(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CUSTOMER_SEGMENT));

        dmCust.setSts(EnumCodeDefine.CUSTOMER_STS_ACTIVE);
        // create_date取报文中
        dmCust.setCreateDate(IMSUtil.formatDate(customer.getCreate_date(), context.getRequestDate()));
        if (customer.getLanguage() != null)
        {
            dmCust.setReadingLanguage((int) customer.getLanguage());
            dmCust.setListeningLanguage((int) customer.getLanguage());
            dmCust.setWritingLanguage((int) customer.getLanguage());
        }
        else
        {
            dmCust.setReadingLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
            dmCust.setListeningLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
            dmCust.setWritingLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        }

        if (customer.getRegion_code() != null)
            dmCust.setRegionCode(new Integer(customer.getRegion_code()));
        else
            dmCust.setRegionCode(EnumCodeDefine.CUSTOMER_DEFAULT_REGION_CODE);

        if (customer.getCountry_id() != null)
            dmCust.setCountryId(new Integer(customer.getCountry_id()));
        else
            dmCust.setCountryId(EnumCodeDefine.CUSTOMER_DEFAULT_COUNTRY_ID);
        if (customer.getProv_code() != null)
            dmCust.setProvCode(CommonUtil.ShortToInteger(customer.getProv_code()));
        else
            dmCust.setProvCode(EnumCodeDefine.CUSTOMER_DEFAULT_PROV_CODE);
        // 暂时默认存0
        dmCust.setCountyCode(0);
        dmCust.setValidDate(validDate);
        dmCust.setExpireDate(IMSUtil.formatExpireDate(null));
        dmCust.setSoNbr(context.getSoNbr());
        // 2011-08-05 zengxr move Tax_Exemption_No field to CM_CUSTOMER
        if (CommonUtil.isValid(customer.getTax_exempt_code()))
        {
            dmCust.setTaxExemptionNo(customer.getTax_exempt_code());
        }
        //caohm5 add2012-04-12
        //增加group_id
        dmCust.setGroupId(customer.getGroup_id().toString());
        
        if(customer.getTax_no() != null)
            dmCust.setTaxNo(customer.getTax_no());

        insert(dmCust);

        return dmCust;
    }

    /**
     * 实例化客户扩展表
     * 
     * @author yanchuan 2011-10-11
     */
    public void createCustExt(SCustomer customer, Integer cycleSpecId)
    {
        if (customer.getList_ext_param() == null)
            return;
        Long custId = customer.getCust_id();
        ExtendParamList paramList = customer.getList_ext_param();
        if (CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
        {
            return;
        }

        String hierarchyBillingFlag = null;
        String billHandingCode = null;
        String custBMCallDetail = null;
        String custBMSummary = null;
        String vatName = customer.getVat_name();
        for (ExtendParam param : paramList.getExtendParamList_Item())
        {
            if (CommonUtil.isEmpty(param.getParam_name()) || CommonUtil.isEmpty(param.getParam_value()))
                continue;
            String name = param.getParam_name();
            String value = param.getParam_value();
            if (ConstantDefine.EXTPARAM_CUST_HIERARCHYBILLINGFLAG.equals(name))
            {
                hierarchyBillingFlag = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_BILLHANDINGCODE.equals(name))
            {
                billHandingCode = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CUSTBMCALLDETAIL.equals(name))
            {
                custBMCallDetail = value;
            }
            else if (ConstantDefine.EXTPARAM_CUST_CUSTBMSUMMARY.equals(name))
            {
                custBMSummary = value;
            }

        }
        CmCustExt dmExt = new CmCustExt();
        dmExt.setCustId(custId);
        if (CommonUtil.isValid(hierarchyBillingFlag))
        {
            dmExt.setForceCycle(Integer.valueOf(hierarchyBillingFlag));
        }
        else
        {
            dmExt.setForceCycle(0);
        }

        if (!CommonUtil.isEmpty(billHandingCode))
        {
            dmExt.setBillFormatId(Integer.valueOf(billHandingCode));
        }

        if (!CommonUtil.isEmpty(custBMCallDetail))
        {
            dmExt.setDetailDeliver(Integer.valueOf(custBMCallDetail));
        }

        if (!CommonUtil.isEmpty(custBMSummary))
        {
            dmExt.setSummaryDeliver(Integer.valueOf(custBMSummary));
        }

        if (!CommonUtil.isEmpty(vatName))
        {
            dmExt.setVatName(vatName);
        }

        if (cycleSpecId != null)
        {
            dmExt.setCycleSpecId(cycleSpecId.longValue());
        }
        super.insert(dmExt);
    }

    /**
     * @Description: 用于修改CmCustomer表中的信息。
     * @Date 2011-08-02
     * @Date 2012-09-18 yugb :[57408]API中字段清空方案
     */
    public void modifyCustomer(SCustomer sCustomer)
    {
        //@Date 2012-09-25 yangjh : 去掉限制
//      if (sCustomer.getProv_code() == null && sCustomer.getCust_segment() == null
//              && CommonUtil.isEmpty(sCustomer.getTax_exempt_code()))
//          return;
      //@Date 2012-09-18 yangjh : 更新3Hbean中的数据，避免后续set negative balance 取的是老数据
        Cust3hBean bean = context.get3hTree().loadCust3hBean(sCustomer.getCust_id());
        CmCustomer customerVal = new CmCustomer();
        if (sCustomer.getCust_segment() != null){
        	if(IMSUtil.isClean(sCustomer.getCust_segment()))
        		 customerVal.setCustomerSegment(null);
        	else{
        	    customerVal.setCustomerSegment((int) sCustomer.getCust_segment());
        	}
        	 bean.getCustomer().setCustomerSegment((int) sCustomer.getCust_segment());
        }
        if (!CommonUtil.isEmpty(sCustomer.getTax_exempt_code())){
            customerVal.setTaxExemptionNo(sCustomer.getTax_exempt_code());
        }else if(IMSUtil.isClean(sCustomer.getTax_exempt_code())){
        	customerVal.setTaxExemptionNo(null);
        }
        // 2011-12-01 hunan 增加prov_code的修改
        if (sCustomer.getProv_code() != null){
        	if(IMSUtil.isClean(sCustomer.getProv_code()))
        		customerVal.setProvCode(null);
        	else
        		customerVal.setProvCode(sCustomer.getProv_code().intValue());
        }
        if (sCustomer.getCust_type() != null)
        {
        	if(IMSUtil.isClean(sCustomer.getProv_code()))
       		 	customerVal.setCustomerClass(null);
        	else
        		customerVal.setCustomerClass(CommonUtil.ShortToInteger(sCustomer.getCust_type()));
            bean.getCustomer().setCustomerClass(CommonUtil.ShortToInteger(sCustomer.getCust_type()));
        }
        if(DBUtil.isSetValue(customerVal)){
            super.updateByCondition(customerVal, new DBCondition(CmCustomer.Field.custId,sCustomer.getCust_id()));
        }
//        customerVal.setValidDate(this.context.getRequestDate());
//        customerVal.setSoDate(this.context.getRequestDate());
//        customerVal.setSoNbr(this.context.getDoneCode());

//        CmCustomer customerWhere = new CmCustomer();
//        customerWhere.setCustId(sCustomer.getCust_id());

//        super.updateByCondition(customerVal, new DBCondition(CmCustomer.Field.custId,sCustomer.getCust_id()));
    }

    /**
     * @description 修改客户及其关联帐户的强制账期
     * @param cmCustomer
     * @Date 2011-08-15
     * @author yanchuan
     */
    public void modifyCustForceCycle(SCustomer sCustomer, Map<String, String> extendParams)
    {
        // 判断客户是否存在
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        // ProductComponent prodCmp = context.getComponent(ProductComponent.class);
        Long custId = sCustomer.getCust_id();
        ExtendParamList paramList = sCustomer.getList_ext_param();
        // 获取报文的强制账期标识
        if (paramList == null || CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
        {
            return;
        }

        int hierarchyBillingFlag = -1;
        Integer specId = null;
        SBillCycle billCycle = new SBillCycle();
        for (ExtendParam param : paramList.getExtendParamList_Item())
        {
            if (CommonUtil.isEmpty(param.getParam_name()) || CommonUtil.isEmpty(param.getParam_value()))
                continue;
            String name = param.getParam_name();
            String value = param.getParam_value();
            if (ConstantDefine.EXTPARAM_CUST_HIERARCHYBILLINGFLAG.equals(name))
            {
                if (!CommonUtil.isEmpty(value))
                    hierarchyBillingFlag = new Integer(value);
            }
            else if (ConstantDefine.EXTPARAM_CUST_CYCLE_TYPE.equals(name))
            {
                billCycle.setCycle_type(new Integer(value));
            }
            else if (ConstantDefine.EXTPARAM_CUST_CYCLE_UNIT.equals(name))
            {
                billCycle.setCycle_unit(new Integer(value));
            }
            else if (ConstantDefine.EXTPARAM_CUST_START_BILL_DAY.equals(name))
            {
                billCycle.setFirst_bill_day(new Integer(value));
            }
        }
        if (AcctHelper.billCycleValid(billCycle))
        {
            specId = acctCmp.queryBillCycleSpecId(new Integer(billCycle.getCycle_type()), new Integer(billCycle.getCycle_unit()),
                    new Integer(billCycle.getFirst_bill_day()));
            if (specId == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CYCLE_SPEC_ID_NOTEXIST, custId);
        }
        // 获取该客户扩展信息
        CmCustExt cmCustExt = queryCustExt(sCustomer.getCust_id());
        Integer force_cycle = null;
        Long cycleSpecId = null;
        boolean isCreateCustExt = true;
        if (cmCustExt != null)
        {
            force_cycle = cmCustExt.getForceCycle();
            cycleSpecId = cmCustExt.getCycleSpecId();
            isCreateCustExt = false;
        }
        if (specId == null && cycleSpecId != null)
            specId = cycleSpecId.intValue();
        if (hierarchyBillingFlag == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE && specId == null)
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CYCLE_SPEC_ID_NOTEXIST, custId);

        List<CaAccount> accountList = acctCmp.queryAccountsByCustId(custId);
        if (force_cycle != null && force_cycle == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)
        {
            // 该客户已是强制账期
            if (hierarchyBillingFlag == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.FORCE_CYCLE_ALREADY_EXISTS);
            // 修改客户强制账期标识
            else if (hierarchyBillingFlag == EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE)
            {
                modifyCustExt(EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE, sCustomer, extendParams, specId, isCreateCustExt);
                // 修改客户关联的所有帐户的账期账期标识
                if (!CommonUtil.isEmpty(accountList))
                {
                    for (CaAccount account : accountList)
                    {
                        long acctId = account.getAcctId();
                        acctCmp.modifyAcctForceCycle(acctId, EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE);
                    }
                }
            }
            else
            {
                modifyCustExt(null, sCustomer, extendParams, specId, isCreateCustExt);
            }
        }
        else
        {
            // 客户关联的所有帐户使用其客户的强制账期编号
            if (hierarchyBillingFlag == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)
            {
                modifyCustExt(EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE, sCustomer, extendParams, specId, isCreateCustExt);
                // 修改客户关联的所有帐户的账期账期标识,修改账期
                if (!CommonUtil.isEmpty(accountList))
                    for (CaAccount account : accountList)
                    {

                        if (account != null)
                        {
                            acctCmp.modifyAcctForceCycle(account.getAcctId(), EnumCodeDefine.FORCECYCLE_BY_CUSTOMER);
                            // 修改帐户账期
                            acctCmp.modifyAcctBillCycle(CommonUtil.IntegerToLong(specId), account);
                            // 修改帐户及其所归属用户订购的产品的账期
                            context.getComponent(ProductCycleComponent.class).changeProdBillCycle(account.getAcctId());
                        }
                    }

            }
            else
            {
                modifyCustExt(null, sCustomer, extendParams, specId, isCreateCustExt);
            }
        }
    }

    /**
     * 修改客户扩展信息表
     * 
     * @author yanchuan 2011-09-10
     * @param force_cycle
     * @param sCustomer
     * @param extendParams
     * @param cycleSpecId
     * @param isCreateCustExt
     */
    public void modifyCustExt(Integer force_cycle, SCustomer sCustomer, Map<String, String> extendParams, Integer cycleSpecId,
            boolean isCreateCustExt)
    {
        if (isCreateCustExt == true)
        {
            createCustExt(sCustomer, cycleSpecId);
        }
        else
        {
            modifyCustExt(null, sCustomer, extendParams, cycleSpecId);
        }
    }

    /**
     * 修改客户扩展信息表 yanchuan 201108120
     * 
     * @param sCustomer
     * @param extendParams
     */
    public void modifyCustExt(Integer force_cycle, SCustomer sCustomer, Map<String, String> extendParams, Integer cycleSpecId)
    {
        CmCustExt cmCustExt = new CmCustExt();
        if (force_cycle != null)
        {
            cmCustExt.setForceCycle(force_cycle);
        }
        if (!CommonUtil.isEmpty(extendParams) && extendParams.size() > 0)
        {
            if (!CommonUtil.isEmpty(extendParams.get(ConstantDefine.EXTPARAM_CUST_BILLHANDINGCODE)))
                cmCustExt.setBillFormatId(new Integer(extendParams.get(ConstantDefine.EXTPARAM_CUST_BILLHANDINGCODE)));
            if (!CommonUtil.isEmpty(extendParams.get(ConstantDefine.EXTPARAM_CUST_CUSTBMSUMMARY)))
                cmCustExt.setSummaryDeliver(new Integer(extendParams.get(ConstantDefine.EXTPARAM_CUST_CUSTBMSUMMARY)));
            if (!CommonUtil.isEmpty(extendParams.get(ConstantDefine.EXTPARAM_CUST_CUSTBMCALLDETAIL)))
                cmCustExt.setDetailDeliver(new Integer(extendParams.get(ConstantDefine.EXTPARAM_CUST_CUSTBMCALLDETAIL)));
        }
        if (!CommonUtil.isEmpty(sCustomer.getVat_name()))
            cmCustExt.setVatName(sCustomer.getVat_name());
        if (cycleSpecId != null)
            cmCustExt.setCycleSpecId((long) cycleSpecId);
//        CmCustExt cmCustExtWhere = new CmCustExt();
//        cmCustExtWhere.setCustId(sCustomer.getCust_id());
        super.updateByCondition(cmCustExt, new DBCondition(CmCustExt.Field.custId,sCustomer.getCust_id()));

    }

    /**
     * 删除客户
     */
    public void deleteCustById(Long custId)
    {
//        CmCustomer cust = new CmCustomer();
//        cust.setCustId(custId);
        deleteByCondition(CmCustomer.class,new DBCondition(CmCustomer.Field.custId,custId));
    }

    /**
     * 将内部实体CmCustomer转换为接口实体SCustomer返回给外部接口
     * 
     * @Date 2011-11-21hunan 将yindm写的将数据库实体封装成外部实体方法增加到公共组件中。
     */
    public SCustomer sCustTransform(CmCustomer cust, CmIndividual individual, CmIndividualName individualName, CmOrgName orgName,
            CmPartyIdentity partyIdentity)
    {
//      SCustomer sCust = new SCustomer();
        if (cust == null)
        {
        	//2012-4-25 yangjh cust=null,return null;并且把SCustomer sCust = new SCustomer();放到后面
//            return sCust;
        	return null;
        }
        SCustomer sCust = new SCustomer();
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
            //caohm5 增加实名制认证日期和认证状态
            if(partyIdentity.getRealNameDate()!=null){
            	sCust.setReal_name_date(DateUtil.getFormatDate(partyIdentity.getRealNameDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if(partyIdentity.getRealNameSts()!=null){
            	sCust.setReal_name_sts(CommonUtil.IntegerToShort(partyIdentity.getRealNameSts()));
            }
            
        }

        if (null != cust.getTaxExemptionNo())
            sCust.setTax_exempt_code(cust.getTaxExemptionNo());

        CmCustExt dmExt = context.getComponent(CustomerComponent.class).queryCustExt(cust.getCustId());
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
     * @Description: 根据证件类型和证件号码查询客户是否存在
     * @author : wuyj
     * @date : 2011-9-26
     * @param regType
     * @param regNbr
     * @return
     */
    public boolean isCustExistWithReg(Integer regType, String regNbr) throws IMSException
    {
        CmParty cmParty = context.getComponent(PartyComponent.class).queryPartyByIdentity(regType, regNbr);
        return cmParty != null;
    }

    /**
     * wangjt 2011-10-12
     * 
     * @param custId
     * @param acctId:custId为空时，根据acctID查询客户
     * @return
     */
    public CmCustomer loadCustomer(Long custId, Long acctId)
    {
        CmCustomer customer = null;
        if (CommonUtil.isValid(custId))
        {
            customer = context.get3hTree().loadCust3hBean(custId).getCustomer();
            return customer;
        }
        else if (CommonUtil.isValid(acctId))
        {
            try
            {
                customer =context.get3hTree().loadAcct3hBean(acctId).getCustomer();
            }
            catch(IMS3hNotFoundException e)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, acctId);
            }
            return customer;
        }else{
            //@Date 2012-08-10 yangjh : 代码优化
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL,"custId,acctId");
            return customer;
        }
    }

    /**
     * @Description: 判断是否客户类型的地址
     * @author : wuyj
     * @date : 2011-9-26
     */
    public static boolean isCustomerContact(int contactType)
    {
        return CommonUtil.isIn(contactType,SysUtil.getIntArray(SysCodeDefine.busi.INNER_CUST_CONTACT));
    }

    /**
     * @Description: 根据partyId查询CmIndividual
     * @param partyId
     * @return
     * @author: tangjl5
     * @Date: 2012-1-9
     */
    public CmIndividual queryCmIndividual(Long partyId)
    {
//        CmIndividual individual = new CmIndividual();
//        individual.setPartyId(partyId);
        return querySingle(CmIndividual.class,new DBCondition(CmIndividual.Field.partyId,partyId));
    }

    /**
     * @Description: 根据partyId查询CmIndividualName
     * @param partyId
     * @return
     * @author: tangjl5
     * @Date: 2012-1-9
     */
    public CmIndividualName queryCmIndividualName(Long partyId)
    {
//        CmIndividualName individualName = new CmIndividualName();
//        individualName.setPartyId(partyId);
        return querySingle(CmIndividualName.class,new DBCondition(CmIndividualName.Field.partyId,partyId));
    }

    /**
     * @Description: 根据partyId查询CmOrgName
     * @param partyId
     * @return
     * @author: tangjl5
     * @Date: 2012-1-9
     */
    public CmOrgName queryCmOrgName(Long partyId)
    {
//        CmOrgName orgName = new CmOrgName();
//        orgName.setPartyId(partyId);
        return querySingle(CmOrgName.class,new DBCondition(CmOrgName.Field.partyId,partyId));
    }

    /**
     * @description:为帐管提供的返回字段信息
     * @author hunan
     * @date 2011-12-22
     * @param dmCust
     * @param individual
     * @param individualName
     * @param orgName
     * @return
     */
    public SCustomer sCustTransform4Billing(CmCustomer dmCust, CmIndividual individual, CmIndividualName individualName,
            CmOrgName orgName)
    {
        SCustomer cust = new SCustomer();
        if (dmCust == null)
        {
            return cust;
        }
        cust.setCust_id(dmCust.getCustId());
        cust.setCust_status((short) EnumCodeDefine.CUSTOMER_STS_ACTIVE);
        if (dmCust.getCustomerClass() != null)
        {
            cust.setCust_type(dmCust.getCustomerClass().shortValue());
        }
        if (dmCust.getCustomerType() != null)
        {
            cust.setSub_cust_type(dmCust.getCustomerType().shortValue());
        }
        if (dmCust.getCustomerSegment() != null)
        {
            cust.setCust_segment(dmCust.getCustomerSegment().shortValue());
        }
        if (dmCust.getCreateDate() != null)
        {
            cust.setCreate_date(DateUtil.formatDate(dmCust.getCreateDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if (dmCust.getValidDate() != null)
        {
            cust.setValid_date(DateUtil.formatDate(dmCust.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        
        if(dmCust.getGroupId()!=null){
            cust.setGroup_id(StringUtils.isNotBlank(dmCust.getGroupId())?Long.valueOf(dmCust.getGroupId()):0l);
        }
        
        if (individual != null)
        {

            if (individual.getBirthDate() != null)
            {
                cust.setBirthday(DateUtil.formatDate(individual.getBirthDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if (individual.getGender() != null)
            {
                cust.setGender(individual.getGender().shortValue());
            }
        }
        if (individualName != null)
        {
            if (individualName.getPreferredGivenName() != null)
            {
                cust.setFirst_name(individualName.getPreferredGivenName());
            }
            if (individualName.getMiddleNames() != null)
            {
                cust.setMiddle_name(individualName.getMiddleNames());
            }
            if (individualName.getFamilyNames() != null)
            {
                cust.setFamily_name(individualName.getFamilyNames());
            }
        }
        if (orgName != null)
        {
            if (orgName.getTradingName() != null)
            {
                cust.setFamily_name(orgName.getTradingName());
            }
        }
        cust.setCustomer_name(dmCust.getCustomerName());
        return cust;
    }
}
