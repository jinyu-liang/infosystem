package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.helper.AcctHelper;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactOper;
import com.ailk.openbilling.persistence.imsintf.entity.SContactOperList;

/**
 * 联系信息组件
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-5 2011-08-05 wuyujie : 增加扩展字段的联系信息createExtContact
 * @Date 2012-04-16 yangjh instanceAccountContact接口参数修改
 * @Date 2012-06-28 luojb 查询条件不能是数据库查询出的对象，必须new #49656
 * @Date 2012-07-05 zhangzj3 联系人信息增加region_code,county_code实例化到表cmContactMedium
 */

public class ContactComponent extends BaseComponent
{
    public ContactComponent()
    {
    }

    /**
     * @author yanchuan 2012-6-15
     * @describe 联系信息表合并后代码改造 
     * @param contact
     */
    public void createContact(SContact contact)
    {
        if (contact.getContact_type() == null)
        {
            return;
        }

        if (CustomerComponent.isCustomerContact(contact.getContact_type()))
        {
            Long custId = contact.getCust_id();

            // this contact belongs to customer
            if (CommonUtil.isValid(custId))
            {
                // 客户联系人对于部分contact_type只能存一条记录，否则报错 yanchuan 2011-11-26
                boolean isQuery = CommonUtil.isIn(contact.getContact_type(),SysUtil.getIntArray(SysCodeDefine.busi.CUST_CONTACT_TYPE));
                // 是否需要查询客户联系人
                if (isQuery)
                {
                    CmContactMedium cmContact = querySingleCustContact(custId, contact.getContact_type());
                    if (cmContact != null)// 客户地址记录不能重复的类型
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_CUSTOMER_CONTACT_IS_EXISTS,
                                cmContact.getContactMediumId());
                    }
                }

             this.createCustomerContact(contact, custId);
            }
        }
        else if (AcctHelper.isAccountContact(contact.getContact_type()))
        {
            // this contact belongs to account
            // AccountComponent acctCmp = context.getComponent(AccountComponent.class);
            Acct3hBean hbean = context.get3hTree().loadAcct3hBean(contact.getAcct_id());
            CaAccount dmAcct = hbean.getAccount();
            if (dmAcct == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_ACCT_FORCONTACT_NOTEXIST,
                        new Object[] { contact.getAcct_id(), contact.getContact_id() });
            }
            Long acctId = dmAcct.getAcctId();

            if (CommonUtil.isValid(acctId))
            {
                // 帐户联系人对于部分contact_type只能存一条记录，否则报错 yanchuan 2011-11-26
                boolean isQuery = CommonUtil.isIn(contact.getContact_type(),SysUtil.getIntArray(SysCodeDefine.busi.ACCT_CONTACT_TYPE));
                // 是否查询判断帐户联系人
                if (isQuery)
                {
                    CmContactMedium cmContact = querySingleAcctContact(acctId, contact.getContact_type());
                    if (cmContact != null)
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_ACCOUNT_CONTACT_IS_EXISTS,
                                cmContact.getContactMediumId());
                    }
                }
                this.createAccountContact(contact,acctId);
            }
        }
        // 如果是公司联系信息
        else if(contact.getContact_type() == EnumCodeDefine.COMP_CONTACT_TYPE)
        {
            //公司信息属于客户级别
            Long custId = contact.getCust_id();
            
            // this contact belongs to customer
            if(CommonUtil.isValid(custId))
            {
                // 客户联系人对于部分contact_type只能存一条记录，否则报错 yanchuan 2011-11-26
                boolean isQuery = CommonUtil.isIn(contact.getContact_type(),
                                                  SysUtil.getIntArray(SysCodeDefine.busi.CUST_CONTACT_TYPE));
                // 是否需要查询客户联系人
                if(isQuery)
                {
                    CmContactMedium cmContact = querySingleCustContact(custId,
                                                                       contact.getContact_type());
                    if(cmContact != null)// 客户地址记录不能重复的类型
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_CUSTOMER_CONTACT_IS_EXISTS,
                                                   cmContact.getContactMediumId());
                    }
                }
                
                this.createCompanyContact(contact,custId);
            }
        }
    }

   
    /**
     * @author yanchuan 2012-6-15
     * @describe 联系信息表合并后代码改造 
     * @param object_id
     * @param object_type
     * @param validDate
     * @param emailBMCall
     * @param faxBMCall
     * @param emailBMSummary
     * @param faxBMSummary
     * @param attrCustEmail
     * @return
     */
    public List<CmContactMedium> createExtContact(Long object_id, int object_type, Date validDate, String emailBMCall, String faxBMCall,
            String emailBMSummary, String faxBMSummary, String attrCustEmail)
    {
        List<CmContactMedium> contactList = null;
        List<DBCondition> cond  = new ArrayList<DBCondition>();
        cond.add(new DBCondition(CmContactMedium.Field.objectId,object_id));
        cond.add(new DBCondition(CmContactMedium.Field.objectType,object_type));
        contactList = this.query(CmContactMedium.class,cond.toArray(new DBCondition[cond.size()]));
        // 处理联系地址
        CmContactMedium contact_qry_type5 = null;
        CmContactMedium contact_qry_type7 = null;
        CmContactMedium contact_qry_type8 = null;

        if (!CommonUtil.isEmpty(contactList))
        {
            for (CmContactMedium contact : contactList)
            {
                if (contact.getContactType() == null)
                {
                    continue;
                }
                int typeTmp = contact.getContactType().intValue();

                // 2012-06-28 luojb 查询条件不能是数据库查询出的对象，必须new
                if (typeTmp == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY)
                {
                    contact_qry_type5 = new CmContactMedium();// billing_address -summary deliver
                    contact_qry_type5.setContactMediumId(contact.getContactMediumId());
                    contact_qry_type5.setContactType(typeTmp);
                }
                else if (typeTmp == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL)
                {
                    contact_qry_type7 = new CmContactMedium();// billing_address -call detail deliver
                    contact_qry_type7.setContactMediumId(contact.getContactMediumId());
                    contact_qry_type7.setContactType(typeTmp);
                }
                else if (typeTmp == EnumCodeDefine.CONTACT_TYPE_ESTATEMENT)
                {
                    contact_qry_type8 = new CmContactMedium();// e-Statement
                    contact_qry_type8.setContactMediumId(contact.getContactMediumId());
                    contact_qry_type8.setContactType(typeTmp);
                }
            }
        }

        CmContactMedium contact_new_type5 = null;
        CmContactMedium contact_new_type7 = null;
        CmContactMedium contact_new_type8 = null;

        if (CommonUtil.isValid(emailBMCall))
        {
            contact_new_type7 = new CmContactMedium();
            contact_new_type7.setEmailAddress(emailBMCall);
        }
        if (CommonUtil.isValid(faxBMCall))
        {
            if (contact_new_type7 == null)
            {
                contact_new_type7 = new CmContactMedium();
            }
            contact_new_type7.setFax(faxBMCall);
        }

        if (CommonUtil.isValid(emailBMSummary))
        {
            contact_new_type5 = new CmContactMedium();
            contact_new_type5.setEmailAddress(emailBMSummary);
        }
        if (CommonUtil.isValid(faxBMSummary))
        {
            if (contact_new_type5 == null)
            {
                contact_new_type5 = new CmContactMedium();

            }
            contact_new_type5.setFax(faxBMSummary);
        }
        if (CommonUtil.isValid(attrCustEmail))
        {
            contact_new_type8 = new CmContactMedium();
            contact_new_type8.setEmailAddress(attrCustEmail);
        }

        if (contact_new_type5 != null)
        {
            if (contact_qry_type5 == null)
            {
                // 新增
                contact_new_type5.setContactMediumId(DBUtil.getSequence(CmContactMedium.class));
                contact_new_type5.setContactType(EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY);
                contact_new_type5.setObjectId(object_id);
                contact_new_type5.setObjectType(object_type);
                contact_new_type5.setValidDate(validDate);
                this.insert(contact_new_type5);
            }
            else
            {
                // 修改
                this.updateByCondition(contact_new_type5, cond.toArray(new DBCondition[cond.size()]));
            }
        }
        if (contact_new_type7 != null)
        {
            if (contact_qry_type7 == null)
            {
                // 新增
                contact_new_type7.setContactMediumId(DBUtil.getSequence(CmContactMedium.class));
                contact_new_type7.setContactType(EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL);
                contact_new_type7.setObjectId(object_id);
                contact_new_type7.setObjectType(object_type);
                contact_new_type7.setValidDate(validDate);
                this.insert(contact_new_type7);
            }
            else
            {
                // 修改
                this.updateByCondition(contact_new_type7, cond.toArray(new DBCondition[cond.size()]));
            }
        }
        if (contact_new_type8 != null)
        {
            if (contact_qry_type8 == null)
            {
                // 新增
                contact_new_type8.setContactMediumId(DBUtil.getSequence(CmContactMedium.class));
                contact_new_type8.setContactType(EnumCodeDefine.CONTACT_TYPE_ESTATEMENT);
                contact_new_type8.setObjectId(object_id);
                contact_new_type8.setObjectType(object_type);
                contact_new_type8.setValidDate(validDate);
                this.insert(contact_new_type8);
            }
            else
            {
                // 修改
                this.updateByCondition(contact_new_type8, cond.toArray(new DBCondition[cond.size()]));
            }
        }
        List<CmContactMedium> result = new ArrayList<CmContactMedium>();
        result.add(contact_new_type5);
        result.add(contact_new_type7);
        result.add(contact_new_type8);

        return result;
    }

    /**
     * 查询所有客户联系信息
     * 
     * @author yanchuan 2012-02-08
     * @param custId
     * @param contact_type
     * @return
     */
    public List<CmContactMedium> queryCustContactMediums(Long custId, Short contact_type)
    {
        return queryContactMediums(custId,EnumCodeDefine.CUST_CONTACT_TYPE,contact_type);
    }
    /**
     * 查询所有帐户联系信息
     * 
     * @author yanchuan 2012-02-08
     * @param AcctId
     * @param contact_type
     * @return
     */
    public List<CmContactMedium> queryAcctContactMediums(Long AcctId, Short contact_type)
    {
        return queryContactMediums(AcctId,EnumCodeDefine.ACCT_CONTACT_TYPE,contact_type);
    }
    /**
     * @author yanchuan 2012-6-15
     * @describe 查询联系信息私有方法
     * @param object_id
     * @param object_type
     * @param contact_type
     * @return
     */
    private List<CmContactMedium> queryContactMediums(Long object_id,int object_type,Short contact_type)
    {
        List<DBCondition> cond  = new ArrayList<DBCondition>();
        cond.add(new DBCondition(CmContactMedium.Field.objectId,object_id));
        cond.add(new DBCondition(CmContactMedium.Field.objectType,object_type));
        if(contact_type !=null)
            cond.add(new DBCondition(CmContactMedium.Field.contactType,contact_type));
        return this.query(CmContactMedium.class,cond.toArray(new DBCondition[cond.size()]));
    }
    /**
     * 查询单个客户联系人信息
     * 
     * @author yanchuan 2011-11-26
     * @param custId
     * @param type
     * @return
     */
    public CmContactMedium querySingleCustContact(Long custId, Short contact_type)
    {
        List<CmContactMedium> contactList = queryCustContactMediums(custId, contact_type);
        return CommonUtil.isEmpty(contactList) ? null : contactList.get(0);
    }

    /**
     * 查询单个帐户联系人信息
     * 
     * @author yanchuan 2011-11-26
     * @param acctId
     * @param type
     * @return
     */
    public CmContactMedium querySingleAcctContact(Long acctId, Short contact_type)
    {
        List<CmContactMedium> contactList = queryAcctContactMediums(acctId, contact_type);
        return CommonUtil.isEmpty(contactList) ? null : contactList.get(0);
    }


    /**
     * @Description: 查询指定的客户下的联系方式 列表
     * @param custId * @Date 2011-12-06
     * @author hunan
     * @return
     */
    public SContactList queryCustomerContactsByCustId(Long custId)
    {
        List<Long> custIds = new ArrayList<Long>();
        custIds.add(custId);
        return this.queryCustomerContactsByCustIds(custIds);
    }


    /**
     * @Description: 查询多个客户下的联系方式列表
     * @param custIds * @Date 2011-12-06
     * @author hunan
     * @return
     */
    public SContactList queryCustomerContactsByCustIds(List<Long> custIds)
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
                custIdArr, Operator.IN),new DBCondition(CmContactMedium.Field.objectType,EnumCodeDefine.CUST_CONTACT_TYPE));
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
     * @Description: 将CmContactMedium转换为SContact结构实体
     * @param dmMedi
     * @Date 2011-12-06
     * @author hunan
     */
    public SContact sContactTransfer(CmContactMedium dmMedi)
    {
        SContact con = new SContact();
        if (dmMedi == null)
        {
            return con;
        }
        con.setContact_id(dmMedi.getContactMediumId());
        if (dmMedi.getContactType() != null)
        {
            con.setContact_type(dmMedi.getContactType().shortValue());
        }
        if(dmMedi.getObjectType() != null && dmMedi.getObjectType() == EnumCodeDefine.CUST_CONTACT_TYPE)
        {
            con.setCust_id(dmMedi.getObjectId());
        }
        else if(dmMedi.getObjectType() != null && dmMedi.getObjectType() == EnumCodeDefine.ACCT_CONTACT_TYPE)
        {
            con.setAcct_id(dmMedi.getObjectId());
        }
        con.setHome_phone(dmMedi.getTelephone());
        con.setMobile_phone(dmMedi.getMobile());
        con.setOffice_phone(dmMedi.getOfficePhone());
        con.setFax(dmMedi.getFax());
        con.setEmail(dmMedi.getEmailAddress());
        con.setAddress(dmMedi.getAddress());
        con.setAddress1(dmMedi.getExtAddress1());
        con.setAddress2(dmMedi.getExtAddress2());
        con.setAddress3(dmMedi.getExtAddress3());
        con.setAddress4(dmMedi.getExtAddress4());
        if (dmMedi.getProvinceCode() != null)
            con.setProvince_code(String.valueOf(dmMedi.getProvinceCode()));
        if (dmMedi.getCountryId() != null)
            con.setCountry_id(dmMedi.getCountryId().longValue());
        if(dmMedi.getPostCode() != null && !"".equals(dmMedi.getPostCode())){
            con.setPost_code(dmMedi.getPostCode().trim());
        }
        if(dmMedi.getPostsect()!=null&&!"".equals(dmMedi.getPostsect())){
        	con.setPostsect(dmMedi.getPostsect());
        }
        con.setContact_name(dmMedi.getContactName());

        return con;
    }
    
    /**
     * @Description: 创建客户联系信息
     * @author : wuyj
     * @date : 2011-9-26
     *  @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     */
    public long createCustomerContact(SContact contact, Long custId) throws IMSException
    {
        return createContactMedium(contact,custId,EnumCodeDefine.CUST_CONTACT_TYPE);
    }
    /**
     * @Description: 创建帐户联系信息
     * @author : yanchuan
     * @date : 2012-06-15
     */
    public long createAccountContact(SContact contact,Long acctId) throws IMSException
    {
        return createContactMedium(contact,acctId,EnumCodeDefine.ACCT_CONTACT_TYPE);
    }
    
    /**
     * @Description: 创建客户联系信息
     * @author : yanxl
     * @date : 2012-12-6
     * @update 联系信息表合并后代码改造 yanchuan 2012-06-15
     */
    public long createCompanyContact(SContact contact,
                                     Long custId)  throws IMSException{
        return createContactMedium(contact,
                                   custId,
                                   EnumCodeDefine.COMP_CONTACT_TYPE);
    }
    
    /**
     * @author yanchuan 2012-6-15
     * @param contact
     * @param object_id
     * @param object_type
     *  @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     * @return
     */
    private long createContactMedium(SContact contact,Long object_id,int object_type)
    {
        if (contact == null)
        {
            return 0;
        }
        CmContactMedium cmContactMedium = new CmContactMedium();
        // hunan 修改 主键值必须通过squence获取
        cmContactMedium.setContactMediumId(DBUtil.getSequence(CmContactMedium.class));

        if (contact.getContact_type() != null)
        {
            cmContactMedium.setContactType(Integer.valueOf(contact.getContact_type()));
        }
        cmContactMedium.setObjectId(object_id);
        cmContactMedium.setObjectType(object_type);
        if (!CommonUtil.isEmpty(contact.getMobile_phone()))
            cmContactMedium.setMobile(contact.getMobile_phone());
        if (!CommonUtil.isEmpty(contact.getHome_phone()))
            cmContactMedium.setTelephone(contact.getHome_phone());
        if (!CommonUtil.isEmpty(contact.getEmail()))
            cmContactMedium.setEmailAddress(contact.getEmail());
        if (!CommonUtil.isEmpty(contact.getFax()))
            cmContactMedium.setFax(contact.getFax());
        if (!CommonUtil.isEmpty(contact.getPost_code()))
            cmContactMedium.setPostCode(contact.getPost_code());
        cmContactMedium.setSoNbr(context.getSoNbr());
        // 2011-08-08 zengxr set address1-address4 of SContact
        if (!CommonUtil.isEmpty(contact.getAddress()))
            cmContactMedium.setAddress(contact.getAddress());
        if (!CommonUtil.isEmpty(contact.getAddress1()))
            cmContactMedium.setExtAddress1(contact.getAddress1());
        if (!CommonUtil.isEmpty(contact.getAddress2()))
            cmContactMedium.setExtAddress2(contact.getAddress2());
        if (!CommonUtil.isEmpty(contact.getAddress3()))
            cmContactMedium.setExtAddress3(contact.getAddress3());
        if (!CommonUtil.isEmpty(contact.getAddress4()))
            cmContactMedium.setExtAddress4(contact.getAddress4());
        if (contact.getCountry_id() != null)
            cmContactMedium.setCountryId(contact.getCountry_id().intValue());
        if (CommonUtil.isNotEmpty(contact.getProvince_code()))
            cmContactMedium.setProvinceCode(CommonUtil.string2Integer(contact.getProvince_code()));
        if (CommonUtil.isNotEmpty(contact.getOffice_phone()))
            cmContactMedium.setOfficePhone(contact.getOffice_phone());
        if (!CommonUtil.isEmpty(contact.getContact_name()))
        {
            cmContactMedium.setContactName(contact.getContact_name());
        }
        //2012-07-05 zhangzj3 联系人信息增加region_code,county_code实例化到表cmContactMedium
        if(!CommonUtil.isEmpty(contact.getRegion_code())){
        	cmContactMedium.setRegionCode(CommonUtil.string2Integer(contact.getRegion_code()));
        }
        if(!CommonUtil.isEmpty(contact.getCounty_code())){
        	cmContactMedium.setCountyCode(CommonUtil.string2Integer(contact.getCounty_code()));
        }
        cmContactMedium.setValidDate(context.getRequestDate());
        cmContactMedium.setExpireDate(IMSUtil.formatExpireDate(null));

        insert(cmContactMedium);
        return cmContactMedium.getContactMediumId();
    }
    /**
     * 修改帐户联系人信息
     * 
     * @author yanchuan 2011-12-6
     */
    public void modifyAccountContact(SContact contact, Long acctId) throws IMSException
    {
        CmContactMedium contactMedium = querySingleAcctContact(acctId,contact.getContact_type());
        if (contactMedium != null)
        {
           this.updateContactMedium(contact,new CmContactMedium(),contactMedium.getContactMediumId());
        }
        else
        {
            // 增加帐户联系信息
            createAccountContact(contact,acctId);
        }
        return;
    }
    
    /**
     * @desc 修改客户联系信息
     * @author yanchuan 2012-2-2
     * @param contactList
     * @param custId
     * @param extendParams
     * @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     */
    public void modifyContactMedium(SContactList contactList, Long custId, Map<String, String> extendParams)
    {
        if (contactList == null || CommonUtil.isEmpty(contactList.getSContactList_Item()))
            return;

        for (SContact contact : contactList.getSContactList_Item())
        {
            if (contact == null)
                continue;
            //CmContactMedium contactMedium = querySingleCustContact(custId,contact.getContact_type());
            
            // @Date 2012-10-9 yanxl 单号58668 支持所有contact_type 目前就是2种
            CmContactMedium contactMedium = null;
            if(contact.getContact_type() == EnumCodeDefine.CUST_CONTACT_TYPE 
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY
                    || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_ESTATEMENT){
                contactMedium = querySingleCustContact(contact.getCust_id(),contact.getContact_type());
            }
            else if(contact.getContact_type() == EnumCodeDefine.ACCT_CONTACT_TYPE){
                contactMedium = querySingleAcctContact(contact.getAcct_id(),contact.getContact_type());
            }
            
            
            if (contactMedium != null)
            {
                CmContactMedium cmContact = new CmContactMedium();
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
                    cmContact.setAddress(emailAddress);
                if (!CommonUtil.isEmpty(fax) && !CommonUtil.isEmpty(contact.getFax()))
                    cmContact.setFax(fax);
                this.updateContactMedium(contact, cmContact, contactMedium.getContactMediumId());

            }
            else
            {
                // 新装客户联系信息
               // this.createCustomerContact(contact, custId);
                // @Date 2012-10-9 yanxl 单号58668 支持所有contact_type 目前就是2种
                if(contact.getContact_type() == EnumCodeDefine.CUST_CONTACT_TYPE 
                        || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_DETAIL
                        || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_BILLINGADDRESS_SUMMARY
                        || contact.getContact_type() == EnumCodeDefine.CONTACT_TYPE_ESTATEMENT){
                    this.createCustomerContact(contact, contact.getCust_id());
                }
                else if(contact.getContact_type() == EnumCodeDefine.ACCT_CONTACT_TYPE){
                    this.createAccountContact(contact, contact.getAcct_id());
                }
            }
        }
    }

    /**
     * 区分客户、账户的联系人列表
     * wukl 2012-4-10
     * @param contactOperList 包含客户、账户的联系人列表
     * @param acctOperList 账户联系人列表
     * @param custOperList 客户联系人列表
     * @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     */
    public void separateContactList(SContactOperList contactOperList, SContactOperList acctOperList, SContactOperList custOperList)
    {
        List<SContactOper> custList = new ArrayList<SContactOper>();
        List<SContactOper> acctList = new ArrayList<SContactOper>();
        if (contactOperList != null && !CommonUtil.isEmpty(contactOperList.getItem()))
        {
            for (int i = 0; i < contactOperList.getItem().length; i++)
            {
                SContactOper contactOper = contactOperList.getItem()[i];
                if (contactOper == null)
                {
                    continue;
                }
                // Oper_type必须输入
                if (contactOper.getOper_type() == null)
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SContactOper.Oper_type");
                }

                SContactList contacts = contactOper.getContact_list();
                if (contacts == null || CommonUtil.isEmpty(contacts.getSContactList_Item()))
                {
                    continue;
                }

                for (int j = 0; j < contacts.getSContactList_Item().length; j++)
                {
                    SContact contact = contacts.getSContactList_Item()[j];
                    if (contact == null)
                    {
                        continue;
                    }
                    if (contact.getContact_type() == null)
                    {
                        IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SContact.Contact_type");
                    }

                    if (CustomerComponent.isCustomerContact(contact.getContact_type()))
                    {
                        custList.add(contactOper);
                    }
                    else if (AcctHelper.isAccountContact(contact.getContact_type()))
                    {
                        acctList.add(contactOper);
                    }
                    else
                    {
                        // 只能修改客户或 账户类型 联系方式
                        IMSUtil.throwBusiException(ErrorCodeDefine.MODIFY_CONTACT_TYPE_IS_INVALID, contact.getContact_type());
                    }
                }
            }
        }

        custOperList.setItem(custList.toArray(new SContactOper[custList.size()]));
        acctOperList.setItem(acctList.toArray(new SContactOper[acctList.size()]));
    }
    
    /**
     * 创建账户人联系方式
     * wukl 2012-4-10
     * @param contactOperList
     * @param acctId
     * @param extendParams
     * @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     */
    public void instanceAccountContact(SContactOperList contactOperList,Date validDate, Long acctId, Map<String, String> extendParams)
    {
        if(CommonUtil.isValid(acctId))
        {
            String emailAcctBMCall = extendParams.get(ConstantDefine.EXTPARAM_ACCT_EMAILACCTBMCALL);
            String faxAccBMCall = extendParams.get(ConstantDefine.EXTPARAM_ACCT_FAXACCBMCALL);
            String emailAcctBMSummary = extendParams.get(ConstantDefine.EXTPARAM_ACCT_EMAILACCTBMSUMMARY);
            String faxAccBMSum = extendParams.get(ConstantDefine.EXTPARAM_ACCT_FAXACCBMSUM);
            String attrAcctEmail = extendParams.get(ConstantDefine.EXTPARAM_ACCT_ATTRACCTEMAIL);
            this.createExtContact(acctId, EnumCodeDefine.ACCT_CONTACT_TYPE,validDate, emailAcctBMCall, faxAccBMCall,
                    emailAcctBMSummary, faxAccBMSum, attrAcctEmail);
        }
         
        if (contactOperList == null || CommonUtil.isEmpty(contactOperList.getItem()))
        {
            return;
        }
        for (int i = 0; i < contactOperList.getItem().length; i++)
        {
            SContactOper contactlist = contactOperList.getItem()[i];
            if (contactlist.getContact_list() == null || CommonUtil.isEmpty(contactlist.getContact_list().getSContactList_Item()))
            {
                continue;
            }
            for (int j = 0; j < contactlist.getContact_list().getSContactList_Item().length; j++)
            {
                SContact contact = contactlist.getContact_list().getSContactList_Item()[j];
                long operType = contactlist.getOper_type();// 在validate中已经进行校验
                if (contact == null)
                {
                    continue;
                }
//              
//                if (operType == EnumCodeDefine.ACCOUNT_RELATION_OPER_DEL)
//                {
//                    context.getComponent(AccountComponent.class).delContactMediumInfo(contact, acctId);
//                }
                    if (operType == EnumCodeDefine.ACCOUNT_RELATION_OPER_ADD)
                    {
                        this.createAccountContact(contact,contact.getAcct_id());
                    }
                    else if (operType == EnumCodeDefine.ACCOUNT_RELATION_OPER_MOD)
                    {
                        this.modifyAccountContact(contact, contact.getAcct_id());
                    }
                    else if (operType == EnumCodeDefine.ACCOUNT_RELATION_OPER_DEL)
                    {
                        this.delAcctContactMedium(contact, contact.getAcct_id());
                    }
            }
        }
    }
    
    /**
     * @author yanchuan 2012-3-19
     * @describe 修改客户联系信息
     * @param contact
     * @param cmContact
     * @param contactMediumId
     */
    private void updateContactMedium(SContact contact, CmContactMedium cmContact, Long contactMediumId)
    {
        if (!CommonUtil.isEmpty(contact.getMobile_phone()))
            cmContact.setMobile(contact.getMobile_phone());
        if (!CommonUtil.isEmpty(contact.getHome_phone()))
            cmContact.setTelephone(contact.getHome_phone());
        if (!CommonUtil.isEmpty(contact.getEmail()))
            cmContact.setEmailAddress(contact.getEmail());
        if (!CommonUtil.isEmpty(contact.getFax()))
            cmContact.setFax(contact.getFax());
        if (!CommonUtil.isEmpty(contact.getPost_code()))
            cmContact.setPostCode(contact.getPost_code());
        // 2011-08-08 zengxr set address1-address4 of SContact
        if (!CommonUtil.isEmpty(contact.getAddress()))
            cmContact.setAddress(contact.getAddress());
        if (!CommonUtil.isEmpty(contact.getAddress1()))
            cmContact.setExtAddress1(contact.getAddress1());
        if (!CommonUtil.isEmpty(contact.getAddress2()))
            cmContact.setExtAddress2(contact.getAddress2());
        if (!CommonUtil.isEmpty(contact.getAddress3()))
            cmContact.setExtAddress3(contact.getAddress3());
        if (!CommonUtil.isEmpty(contact.getAddress4()))
            cmContact.setExtAddress4(contact.getAddress4());
        cmContact.setSoNbr(context.getSoNbr());
        if (contact.getCountry_id() != null)
            cmContact.setCountryId(contact.getCountry_id().intValue());
        if (CommonUtil.isNotEmpty(contact.getProvince_code()))
            cmContact.setProvinceCode(CommonUtil.string2Integer(contact.getProvince_code()));
        if (CommonUtil.isNotEmpty(contact.getOffice_phone()))
            cmContact.setOfficePhone(contact.getOffice_phone());
        //2012-07-05 zhangzj3 联系人信息增加region_code,county_code实例化到表cmContactMedium
        if(!CommonUtil.isEmpty(contact.getRegion_code())){
        	cmContact.setRegionCode(CommonUtil.string2Integer(contact.getRegion_code()));
        }
        if(!CommonUtil.isEmpty(contact.getCounty_code())){
        	cmContact.setCountyCode(CommonUtil.string2Integer(contact.getCounty_code()));
        }
        this.updateByCondition(cmContact, new DBCondition(CmContactMedium.Field.contactMediumId, contactMediumId));
    }
    
    /**
     * @Description: 删除帐户联系方式
     * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
     * @Author liuyang
     * @Date 2011-7-27 2011-07-27 liuyang,增加方法delContactMediumInfo 2011-8-22 liuyang 根据mediumId，partyRoleId，contactType进行删除
     * @update 联系信息表合并后代码改造   yanchuan 2012-06-15
     */
    public void delAcctContactMedium(SContact contact, Long acctId)
    {
        if (contact.getContact_type() == null || !CommonUtil.isValid(acctId))
            return;
        List<CmContactMedium> acctContacts= queryAcctContactMediums(acctId,contact.getContact_type());
        if (CommonUtil.isEmpty(acctContacts))
            IMSUtil.throwBusiException(ErrorCodeDefine.DEL_ACCOUNT_CONTACT_NOT_EXITS);
        
        delContactMediumInfo(acctId,EnumCodeDefine.ACCT_CONTACT_TYPE,contact.getContact_type());
    }
    
    /**
     * @Author yanchuan2012-06-15
     * @Description: 删除客户联系方式
     * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
     * 
     */
    public void delCustContactMedium(SContact contact, Long cust_id)
    {
        if (contact.getContact_type() == null || !CommonUtil.isValid(cust_id))
            return;
        List<CmContactMedium> custContacts= queryCustContactMediums(cust_id,contact.getContact_type());
        if (CommonUtil.isEmpty(custContacts))
            IMSUtil.throwBusiException(ErrorCodeDefine.DEL_CUST_CONTACT_NOT_EXITS);
        
        delContactMediumInfo(cust_id,EnumCodeDefine.CUST_CONTACT_TYPE,contact.getContact_type());
    }
    /**
     * @author yanchuan 2012-6-15
     * @describe 删除联系信息
     * @param object_id
     * @param object_type
     * @param contact_type
     */
    private void delContactMediumInfo(Long object_id,int object_type,Short contact_type)
    {
       
       List<DBCondition> conditions = new ArrayList<DBCondition>();
       conditions.add(new DBCondition(CmContactMedium.Field.objectId,object_id));
       conditions.add(new DBCondition(CmContactMedium.Field.objectType,object_type));
       if(contact_type != null)
           conditions.add(new DBCondition(CmContactMedium.Field.contactType,contact_type));
        super.deleteByCondition(CmContactMedium.class,conditions.toArray(new DBCondition[conditions.size()]));


    }

}
