package com.ailk.ims.component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmOrganization;
import com.ailk.openbilling.persistence.cust.entity.CmParty;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactOper;
import com.ailk.openbilling.persistence.imsintf.entity.SContactOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;

/**
 * @Date 2011-08-02 hunan 增加queryPartyIdByCustId方法 & operateContactMedium方法 & deleteContactMedium方法 & addContactMedium方法
 *       modifyCustBasicInfo方法 & modifyPartyIdentity方法 & modifyIndividual方法 & modifyIndividualName 方法 & modifyOrgName方法
 * @Date 2011-08-08 wuyujie : 增加方法queryInvidualByCustId
 * @Date 2011-09-22 wuyujie : 内外id映射修改
 * @Date 2012-07-12 wangdw5:#51392 增加支持contact_type = 5,6,7,8等扩展字段的入库
 */
public class PartyComponent extends BaseComponent
{

    public PartyComponent()
    {
    }

    /**
     * @Description: 创建客户（包括个人客户和集团客户）的参与人信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmParty createParty(SCustomer customer) throws IMSException
    {
        CmParty dmParty = new CmParty();

        // 个人客户流程
        if (customer.getCust_type() != null && IMSUtil.isPersonCust(customer.getCust_type().intValue()))
        {
            dmParty.setPartyType(EnumCodeDefine.PARTY_TYPE_INDIVIDUAL);
        }
        // 集团客户流程
        else
        {
            dmParty.setPartyType(EnumCodeDefine.PARTY_TYPE_ORGANIZATION);
        }
        // 2011-09-22 wuyujie : 内外id映射修改
        if (customer.getCust_id() == null)
        {
            dmParty.setPartyId(DBUtil.getSequence(CmCustomer.class));
        }
        else
        {
            dmParty.setPartyId(customer.getCust_id());
        }

        dmParty.setSts(EnumCodeDefine.PARTY_STS_VALID);
        dmParty.setCreateDate(IMSUtil.formatDate(customer.getCreate_date(), context.getRequestDate()));
        dmParty.setValidDate(IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate()));
        dmParty.setExpireDate(IMSUtil.formatExpireDate(null));

        dmParty.setSoNbr(context.getSoNbr());
        super.insert(dmParty);

        return dmParty;
    }

    /**
     * @Description: 创建个人客户的个人信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmIndividual createIndividual(SCustomer customer, long partyId) throws IMSException
    {
        CmIndividual dmIndividual = new CmIndividual();
        dmIndividual.setPartyId(partyId);
        if (!CommonUtil.isEmpty(customer.getBirthday()))
            dmIndividual.setBirthDate(DateUtil.getFormattedDate(customer.getBirthday()));
        dmIndividual.setCreateDate(IMSUtil.formatDate(customer.getCreate_date(), context.getRequestDate()));
        if (customer.getGender() != null)
            dmIndividual.setGender(customer.getGender().intValue());
        dmIndividual.setSoNbr(context.getSoNbr());
        dmIndividual.setValidDate(IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate()));
        dmIndividual.setExpireDate(IMSUtil.getDefaultExpireDate());
        insert(dmIndividual);

        return dmIndividual;
    }

    /**
     * @Description: 创建个人客户的名称信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmIndividualName createIndividualName(SCustomer customer, long partyId) throws IMSException
    {
        Date validDate = DateUtil.getFormattedDate(customer.getValid_date());
        if (validDate == null)
            validDate = context.getRequestDate();

        CmIndividualName dmIndividualName = new CmIndividualName();
        dmIndividualName.setPartyId(partyId);
        // 2011-10-10 zengxr cust_title changed to String
        if (!CommonUtil.isEmpty(customer.getCust_title()))
            dmIndividualName.setFormOfAddress(customer.getCust_title());
        if (!CommonUtil.isEmpty(customer.getFamily_name()))
            dmIndividualName.setFamilyNames(customer.getFamily_name());
        if (!CommonUtil.isEmpty(customer.getMiddle_name()))
            dmIndividualName.setMiddleNames(customer.getMiddle_name());
        if (!CommonUtil.isEmpty(customer.getFirst_name()))
            dmIndividualName.setPreferredGivenName(customer.getFirst_name());
        dmIndividualName.setSoNbr(context.getSoNbr());
        dmIndividualName.setValidDate(validDate);
        dmIndividualName.setExpireDate(IMSUtil.formatExpireDate(null));
        if (customer.getLanguage() != null)
            dmIndividualName.setLanguageId(customer.getLanguage().intValue());
        else
            dmIndividualName.setLanguageId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        insert(dmIndividualName);

        return dmIndividualName;
    }

    /**
     * @Description: 创建集团客户的参与人信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmOrganization createOrganization(SCustomer customer, long partyId) throws IMSException
    {
        CmOrganization dmOrg = new CmOrganization();
        dmOrg.setPartyId(partyId);
        dmOrg.setSoNbr(context.getSoNbr());
        dmOrg.setCreateDate(IMSUtil.formatDate(customer.getCreate_date(), context.getRequestDate()));
        if (customer.getCust_type() != null)
            dmOrg.setType(Integer.valueOf(customer.getCust_type()));
        
        if(customer.getReg_no() != null)
            dmOrg.setRegNo(customer.getReg_no());
        
        if(customer.getReg_date() != null)
            dmOrg.setRegDate(DateUtil.getFormatDate(customer.getReg_date()));
        
        // caohm5 add 2012-04-12
        // 一级属地和二级属地
        if (customer.getFirst_address() != null)
        {
            dmOrg.setFirstAddress(CommonUtil.ShortToInteger(customer.getFirst_address()));
        }
        if (customer.getSecond_address() != null)
        {
            dmOrg.setSecondAddress(CommonUtil.ShortToInteger(customer.getSecond_address()));
        }
        dmOrg.setValidDate(IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate()));
        dmOrg.setExpireDate(IMSUtil.getDefaultExpireDate());
        super.insert(dmOrg);
        return dmOrg;
    }

    /**
     * @Description: 创建集团客户的名称信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmOrgName createOrganizationName(SCustomer customer, long partId) throws IMSException
    {
        CmOrgName dmOrgName = new CmOrgName();
        dmOrgName.setPartyId(partId);
        dmOrgName.setTradingName(customer.getFamily_name());
        dmOrgName.setSoNbr(context.getSoNbr());
        dmOrgName.setValidDate(IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate()));
        dmOrgName.setExpireDate(IMSUtil.formatExpireDate(null));
        if (customer.getLanguage() != null)
            dmOrgName.setLanguageId(customer.getLanguage().intValue());
        else
            dmOrgName.setLanguageId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        insert(dmOrgName);

        return dmOrgName;
    }

    /**
     * @Description: 创建参与人的身份信息
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmPartyIdentity createIdentification(SCustomer customer, long partyId) throws IMSException
    {
        CmPartyIdentity dmIndentify = new CmPartyIdentity();
        dmIndentify.setIdentificationId(DBUtil.getSequence(CmPartyIdentity.class));
        dmIndentify.setPartyId(partyId);
        if (customer.getReg_type() != null)
        {
            dmIndentify.setPartyIdentificationType(customer.getReg_type().intValue());
        }

        if (customer.getReg_nbr() != null)
        {
            dmIndentify.setPartyIdentificationSpec(customer.getReg_nbr());
        }

        if (customer.getIssue_date() != null)
        {
            dmIndentify.setIssueDate(IMSUtil.formatDate(customer.getIssue_date()));
        }

        if (customer.getLanguage() != null)
        {
            dmIndentify.setLanguageId(customer.getLanguage().intValue());
        }
        // 设置默认语言 yanchuan 2011-11-18
        else
        {
            dmIndentify.setLanguageId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        }

        dmIndentify.setValidDate(IMSUtil.formatDate(customer.getValid_date(), context.getRequestDate()));
        dmIndentify.setExpireDate(IMSUtil.formatExpireDate(null));
        dmIndentify.setSoNbr(context.getSoNbr());
        // 增加reg_name字段 yanchuan 2011-10-17
        if (customer.getReg_name() != null)
        {
            dmIndentify.setPartyIdentificationName(customer.getReg_name());
        }
        // caohm5 add 实名制日期和实名制状态
        if (customer.getReal_name_date() != null && !customer.getReal_name_date().trim().equals(""))
        {
            dmIndentify.setRealNameDate(IMSUtil.formatDate(customer.getReal_name_date()));
        }
        if (customer.getReal_name_sts() != null)
        {
            dmIndentify.setRealNameSts(CommonUtil.ShortToInteger(customer.getReal_name_sts()));
        }

        insert(dmIndentify);
        return dmIndentify;
    }

    /**
     * @Description: 根据参与人id查询参与人
     * @author : wuyj
     * @date : 2011-9-26
     */
    public CmParty queryPartyById(long partyId) throws IMSException
    {
        // CmParty dmParty = new CmParty();
        // dmParty.setPartyId(partyId);
        CmParty dmParty = querySingle(CmParty.class, new DBCondition(CmParty.Field.partyId, partyId));
        return dmParty;
    }

    /**
     * @Description: 根据身份信息查询参与人
     * @author : wuyj
     * @date : 2011-9-26
     * @param regType，身份证件类型，枚举值，如身份证，军官证，残疾证等
     * @param regNo，身份证件号码
     * @return
     * @throws IMSException
     */
    public CmParty queryPartyByIdentity(int regType, String regNo) throws IMSException
    {
        List<CmPartyIdentity> identities = query(CmPartyIdentity.class, new DBCondition(
                CmPartyIdentity.Field.partyIdentificationType, regType), new DBCondition(
                CmPartyIdentity.Field.partyIdentificationSpec, regNo));
        if (CommonUtil.isEmpty(identities))
            return null;
        long partyId = identities.get(0).getPartyId();
        return queryPartyById(partyId);
    }

    /**
     * 根据custId查询paryId
     * 
     * @author yanchuan
     * @date 2011-01-09
     */
    public Long queryPartyIdByCustId(Long custId)
    {
        // 目前party等于custId直接返回custId即可 yanchuan 2011-12-22
        // CmParty cmParty = queryCmPartyByCustId(custId);

        return custId;
    }

    /**
     * @Description: 对客户的联系方式进行 增加 or 删除 or修改操作
     * @param sContactOperList
     * @param custId
     * @author hunan
     * @update yanchuan
     */
    public void operateContactMedium(SContactOperList sContactOperList, Long custId, Map<String, String> extendParams)
    {
    	//@Date 2012-07-12 wangdw5:#51392 增加支持contact_type = 5,6,7,8等扩展字段的入库
    	ContactComponent contCmp = context.getComponent(ContactComponent.class);
        if(CommonUtil.isValid(custId))
        {
            String emailBMCall = extendParams.get(ConstantDefine.EXTPARAM_CUST_EMAILBMCALL);
            String faxBMCall = extendParams.get(ConstantDefine.EXTPARAM_CUST_FAXBMCALL);
            String emailBMSummary = extendParams.get(ConstantDefine.EXTPARAM_CUST_EMAILBMSUMMARY);
            String faxBMSum = extendParams.get(ConstantDefine.EXTPARAM_CUST_FAXBMSUMMARY);
            String attrEmail = extendParams.get(ConstantDefine.EXTPARAM_CUST_ATTRCUSTEMAIL);
            contCmp.createExtContact(custId, EnumCodeDefine.CUST_CONTACT_TYPE,null, emailBMCall, faxBMCall,
                    emailBMSummary, faxBMSum, attrEmail);
        }
        if (sContactOperList == null || CommonUtil.isEmpty(sContactOperList.getItem()))
            return;
        for (int i = 0; i < sContactOperList.getItem().length; i++)
        {
            SContactOper contactOper = sContactOperList.getItem()[i];
            if (contactOper == null || contactOper.getOper_type() == null)
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MODIFY_OPER_TYPE_NOT_EXITS);
            switch (contactOper.getOper_type())
            {
            case EnumCodeDefine.MODIFY_CUSTOMER_CONTACT_MEDIUM_ADD:
                addContactMedium(contactOper.getContact_list(), custId, extendParams);
                break;
            case EnumCodeDefine.MODIFY_CUSTOMER_CONTACT_MEDIUM_DELETE:
                deleteContactMedium(contactOper.getContact_list());
                break;
            case EnumCodeDefine.MODIFY_CUSTOMER_CONTACT_MEDIUM_MODIFY:
                contCmp.modifyContactMedium(contactOper.getContact_list(), custId, extendParams);
                break;
            default:
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MODIFY_CUST_CONTACTOPER_OPER_TYPE);
            }

        }
    }

    /**
     * @Description: delete contactMedium
     * @param contactList
     * @author hunan
     */
    private void deleteContactMedium(SContactList contactList)
    {
        if (contactList == null || CommonUtil.isEmpty(contactList.getSContactList_Item()))
            return;
        ContactComponent contactCmp = context.getComponent(ContactComponent.class);
        for (SContact contact : contactList.getSContactList_Item())
        {
            if (contact == null)
                continue;
            
            // @Date 2012-10-9 yanxl 单号58668 支持所有contact_type 目前就是2种
            if(contact.getContact_type() == EnumCodeDefine.CUST_CONTACT_TYPE 
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_ESTATEMENT){
                contactCmp.delCustContactMedium(contact,contact.getCust_id());
            }
            else if(contact.getContact_type() == EnumCodeDefine.ACCT_CONTACT_TYPE){
                contactCmp.delAcctContactMedium(contact,contact.getAcct_id());
            }
        }
    }

    /**
     * @Description: add contactMedium
     * @param contactList
     * @param custId
     */
    private void addContactMedium(SContactList contactList, Long custId, Map<String, String> extendParams)
    {
        if (contactList == null || CommonUtil.isEmpty(contactList.getSContactList_Item()))
            return;
        ContactComponent contactCmp = context.getComponent(ContactComponent.class);
        for (SContact contact : contactList.getSContactList_Item())
        {
            if (contact.getContact_type() != null
                    && (CommonUtil.isEmpty(contact.getAddress()) || CommonUtil.isEmpty(contact.getFax())))
            {
                String emailAddress = null;
                String fax = null;
                int contactType = contact.getContact_type();
                switch (contactType)
                {
                case EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL:
                {
                    emailAddress = extendParams.get(ConstantDefine.EXTPARAM_CUST_EMAILBMCALL);
                    fax = extendParams.get(ConstantDefine.EXTPARAM_CUST_FAXBMCALL);
                }
                    ;
                    break;
                case EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY:
                {
                    emailAddress = extendParams.get(ConstantDefine.EXTPARAM_CUST_EMAILBMSUMMARY);
                    fax = extendParams.get(ConstantDefine.EXTPARAM_CUST_FAXBMSUMMARY);
                }
                    ;
                    break;
                case EnumCodeDefine.CONTACT_TYPE_ESTATEMENT:
                    emailAddress = extendParams.get(ConstantDefine.EXTPARAM_CUST_ATTRCUSTEMAIL);
                    break;
                }
                if (!CommonUtil.isEmpty(emailAddress))
                    contact.setAddress(emailAddress);
                if (!CommonUtil.isEmpty(fax) && !CommonUtil.isEmpty(contact.getFax()))
                    contact.setFax(fax);
            }
            
            // @Date 2012-10-9 yanxl 单号58668 支持所有contact_type 目前就是2种
            if(contact.getContact_type() == EnumCodeDefine.CUST_CONTACT_TYPE 
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_ESTATEMENT){
                contactCmp.createCustomerContact(contact, contact.getCust_id());
            }
            else if(contact.getContact_type() == EnumCodeDefine.ACCT_CONTACT_TYPE){
                contactCmp.createAccountContact(contact, contact.getAcct_id());
            }
            
        }
    }
   
    /**
     * @Description: 修改客户基本信息(不包含联系方式)
     * @param sCustomer
     * @author hunan
     */
    public void modifyCustBasicInfo(SCustomer sCustomer)
    {
        Long custId = sCustomer.getCust_id();
        Long partyId = this.queryPartyIdByCustId(custId);
        if (partyId == null)
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MODIFY_CUST_INFO_CUST_NOEXSIT, custId);
        // 修改参与人标识信息
        this.modifyPartyIdentity(sCustomer, partyId);

        CmParty party = this.queryPartyById(partyId);
        if (party == null)
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MODIFY_CUST_INFO_PARTY_NOEXSIT, partyId);

        // 如果是organination客户修改信息
        if (party.getPartyType() == EnumCodeDefine.PARTY_TYPE_ORGANIZATION)
        {
            // 修改organination_name信息(organination信息不需要修改)
            this.modifyOrgName(sCustomer, partyId);
        }
        // 如果是individual客户修改信息
        if (party.getPartyType() == EnumCodeDefine.PARTY_TYPE_INDIVIDUAL)
        {
            // 修改individual信息
            this.modifyIndividual(sCustomer, partyId);
            // 修改individual_name 信息
            this.modifyIndividualName(sCustomer, partyId);
        }

        // 修改customer信息
        CustomerComponent custCmp = context.getComponent(CustomerComponent.class);
        custCmp.modifyCustomer(sCustomer);
    }

    /**
     * @Description: 修改CmOrgName里的信息
     * @param sCustomer
     * @param partyId
     * @author hunan
     */
    private void modifyOrgName(SCustomer sCustomer, Long partyId)
    {
        if (CommonUtil.isEmpty(sCustomer.getFamily_name()) && sCustomer.getLanguage() == null)
            return;
        CmOrgName orgNameVal = new CmOrgName();
        if (!CommonUtil.isEmpty(sCustomer.getFamily_name()))
            orgNameVal.setTradingName(sCustomer.getFamily_name());
        if (sCustomer.getLanguage() != null)
            orgNameVal.setLanguageId((int) sCustomer.getLanguage());
        orgNameVal.setValidDate(this.context.getRequestDate());
        orgNameVal.setSoDate(this.context.getRequestDate());
        orgNameVal.setSoNbr(this.context.getSoNbr());

        // CmOrgName orgNameWhere = new CmOrgName();
        // orgNameWhere.setPartyId(partyId);

        super.updateByCondition(orgNameVal, new DBCondition(CmOrgName.Field.partyId, partyId));

    }

    /**
     * @Description: 修改CmIndividualName里的信息
     * @author hunan
     */
    private void modifyIndividualName(SCustomer sCustomer, Long partyId)
    {
        if (sCustomer.getLanguage() == null && CommonUtil.isEmpty(sCustomer.getFamily_name())
                && CommonUtil.isEmpty(sCustomer.getFirst_name()) && CommonUtil.isEmpty(sCustomer.getCust_title()))
            return;
        CmIndividualName individualNameVal = new CmIndividualName();
        if (sCustomer.getLanguage() != null)
            individualNameVal.setLanguageId((int) sCustomer.getLanguage());
        if (!CommonUtil.isEmpty(sCustomer.getFamily_name()))
            individualNameVal.setFamilyNames(sCustomer.getFamily_name());
        if (!CommonUtil.isEmpty(sCustomer.getFirst_name()))
            individualNameVal.setPreferredGivenName(sCustomer.getFirst_name());
        // 2011-12-01 hunan add ：if cust_title is not null ，modify it
        if (!CommonUtil.isEmpty(sCustomer.getCust_title()))
        {
            individualNameVal.setFormOfAddress(sCustomer.getCust_title());
        }
        individualNameVal.setValidDate(this.context.getRequestDate());
        individualNameVal.setSoDate(this.context.getRequestDate());
        individualNameVal.setSoNbr(this.context.getSoNbr());

        // CmIndividualName individulNameWhere = new CmIndividualName();
        // individulNameWhere.setPartyId(partyId);

        super.updateByCondition(individualNameVal, new DBCondition(CmIndividualName.Field.partyId, partyId));

    }

    /**
     * @Description: 修改CmIndividual里的信息
     * @author hunan
     */
    private void modifyIndividual(SCustomer sCustomer, Long partyId)
    {
        if (CommonUtil.isEmpty(sCustomer.getBirthday()) && sCustomer.getGender() == null)
            return;
        CmIndividual individualVal = new CmIndividual();
        if (!CommonUtil.isEmpty(sCustomer.getBirthday()))
            individualVal.setBirthDate(IMSUtil.formatDate(sCustomer.getBirthday(), DateUtil.currentDate()));
        if (sCustomer.getGender() != null)
            individualVal.setGender((int) sCustomer.getGender());
        individualVal.setValidDate(this.context.getRequestDate());
        individualVal.setSoDate(this.context.getRequestDate());
        individualVal.setSoNbr(this.context.getSoNbr());

        // CmIndividual individulWhere = new CmIndividual();
        // individulWhere.setPartyId(partyId);

        super.updateByCondition(individualVal, new DBCondition(CmIndividual.Field.partyId, partyId));

    }

    /**
     * @Description: 修改CmPartyIdentity表里的信息
     * @author hunan
     */
    public void modifyPartyIdentity(SCustomer sCustomer, Long partyId)
    {
        if (CommonUtil.isEmpty(sCustomer.getReg_nbr()) && sCustomer.getReg_type() == null
                && CommonUtil.isEmpty(sCustomer.getIssue_date()) && sCustomer.getLanguage() == null)
            return;
        CmPartyIdentity partyIdentityVal = new CmPartyIdentity();
        if (sCustomer.getLanguage() != null)
            partyIdentityVal.setLanguageId((int) sCustomer.getLanguage());
        if (!CommonUtil.isEmpty(sCustomer.getIssue_date()))
            partyIdentityVal.setIssueDate(IMSUtil.formatDate(sCustomer.getIssue_date()));
        if (sCustomer.getReg_type() != null)
            partyIdentityVal.setPartyIdentificationType((int) sCustomer.getReg_type());
        if (!CommonUtil.isEmpty(sCustomer.getReg_nbr()))
            partyIdentityVal.setPartyIdentificationSpec(sCustomer.getReg_nbr());
        partyIdentityVal.setValidDate(this.context.getRequestDate());
        partyIdentityVal.setSoDate(this.context.getRequestDate());
        partyIdentityVal.setSoNbr(this.context.getSoNbr());
        // 增加reg_name字段 yanchuan 2011-10-17
        if (sCustomer.getReg_name() != null)
            partyIdentityVal.setPartyIdentificationName(sCustomer.getReg_name());
        // CmPartyIdentity partyIdentityWhere = new CmPartyIdentity();
        // 修改参与人身份认证状态和认证日期 caohm5
        if (sCustomer.getReal_name_date() != null && !sCustomer.getReal_name_date().trim().equals(""))
        {
            partyIdentityVal.setRealNameDate(IMSUtil.formatDate(sCustomer.getReal_name_date()));
        }
        if (sCustomer.getReal_name_sts() != null)
        {
            partyIdentityVal.setRealNameSts(CommonUtil.ShortToInteger(sCustomer.getReal_name_sts()));
        }
        // partyIdentityWhere.setPartyId(partyId);

        super.updateByCondition(partyIdentityVal, new DBCondition(CmIndividual.Field.partyId, partyId));

    }

    /**
     * @Description: 通过custId，查询个人信息
     * @param custId
     * @return
     */
    public CmIndividual queryInvidualByCustId(long custId)
    {
        Long partyId = queryPartyIdByCustId(custId);
        CmIndividual individual = null;
        if (CommonUtil.isValid(partyId))
            individual = this.querySingle(CmIndividual.class, new DBCondition(CmIndividual.Field.partyId, partyId));

        return individual;
    }

    /**
     * @Description: 通过custId，查询个人名称信息
     * @param custId
     * @return
     * @author wuyj
     */
    public CmIndividualName queryInvidualNameByCustId(long custId)
    {
        Long partyId = queryPartyIdByCustId(custId);
        CmIndividualName individualName = null;
        if (CommonUtil.isValid(partyId))
            individualName = this.querySingle(CmIndividualName.class, new DBCondition(CmIndividualName.Field.partyId, partyId));
        return individualName;
    }

    /**
     * @description 个人用户
     * @author yangyang
     * @date 2011-9-26
     * @param partyId
     * @return
     */
    public CmIndividual queryCmIndividualByPartyId(Long partyId)
    {
        // CmIndividual cm = new CmIndividual();
        // cm.setPartyId(partyId);
        CmIndividual cm = querySingle(CmIndividual.class, new DBCondition(CmIndividual.Field.partyId, partyId));

        if (cm == null)
            return null;

        return cm;
    }

    /**
     * @description 非个人用户
     * @author yangyang
     * @date 2011-9-26
     * @param partyId
     * @return
     */
    public CmOrganization queryCmOrgByPartyId(Long partyId)
    {
        // CmOrganization cm = new CmOrganization();
        // cm.setPartyId(partyId);
        CmOrganization cm = querySingle(CmOrganization.class, new DBCondition(CmOrganization.Field.partyId, partyId));

        if (cm == null)
            return null;

        return cm;
    }

    /**
     * @description 查询集团客户名称信息
     * @author yanchuan
     * @date 2011-12-22
     * @param partyId
     * @return
     */
    public CmOrgName queryCmOrgNameByPartyId(Long partyId)
    {

        return querySingle(CmOrgName.class, new DBCondition(CmOrgName.Field.partyId, partyId));
    }

    /**
     * @Description: 根据partyId查询参与人的身份信息
     * @author : yanchuan
     * @date : 2011-12-22
     * @param partyId
     * @return
     * @throws IMSException
     */
    public CmPartyIdentity queryPartyByIdentity(Long partyId) throws IMSException
    {
        CmPartyIdentity partyIdentity = querySingle(CmPartyIdentity.class,
                new DBCondition(CmPartyIdentity.Field.partyId, partyId));

        return partyIdentity;
    }
}
